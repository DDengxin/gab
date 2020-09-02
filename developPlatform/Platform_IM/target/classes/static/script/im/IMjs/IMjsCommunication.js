function __CreateJSPath(js, recall) {
    // 兼容IE
    if (typeof String.prototype.startsWith !== 'function') {
        String.prototype.startsWith = function (prefix) {
            return this.indexOf(prefix.length) === 0;
        };
    }
    if (typeof String.prototype.endsWith !== 'function') {
        String.prototype.endsWith = function (suffix) {
            return this.indexOf(suffix, this.length - suffix.length) !== -1;
        };
    }

    var scripts = document.getElementsByTagName("script");
    var path = "";
    for (var i = 0, l = scripts.length; i < l; i++) {
        var src = scripts[i].src;
        if (src.indexOf(js) != -1) {
            var ss = src.split(js);
            path = ss[0];
            break;
        }
    }
    var href = location.href;
    href = href.split("#")[0];
    href = href.split("?")[0];
    var ss = href.split("/");
    ss.length = ss.length - 1;
    href = ss.join("/");
    if (path.indexOf("https:") == -1 && path.indexOf("http:") == -1 && path.indexOf("file:") == -1 && path.indexOf("\/") != 0) {
        path = href + "/" + path;
    }
    if (null != recall && !isNaN(recall) && parseInt(recall) > 0) {
        path = path.endsWith('/') ? path.substring(0, path.length - 2) : path;
        if (!window.location.origin) {
            window.location.origin = window.location.protocol + "//" + window.location.hostname + (window.location.port ? ':' + window.location.port : '');
        }
        var origin = window.location.origin;
        for (var i = 0, max = parseInt(recall); i < max; i += 1) {
            if (0 !== path.indexOf(origin) || path === origin) {
                break;
            } else {
                path = path.substring(0, path.lastIndexOf('/'));
            }
        }
        path = path.endsWith('/') ? path : path + '/';
    }
    return path;
}
//当前目录
var bootPATH = __CreateJSPath("IMjsCommunication.js", 1);
document.write('<script src="/dwr/engine.js" type="text/javascript"></sc' + 'ript>');
document.write('<script src="/dwr/interface/Dwrmsg.js" type="text/javascript"></sc' + 'ript>')
document.write('<script src="/dwr/util.js" type="text/javascript"></sc' + 'ript>');
document.write('<link href="' + bootPATH + 'layui/css/layui.css" rel="stylesheet" type="text/css" />');
document.write('<script src="' + bootPATH + 'layui/layui.js" type="text/javascript"></script>');

//layim实例
var im = null;
//socket实例
var socket = null;
//session实例
var currentsession = null;
//重连机制
var reconnectflag = false;
//定义父页面角标元素
var parentEle = $('#messageTips', window.parentcument);

/**
 * 当前消息总数记录器
 * @type {*}
 * {userid:...,count:...}
 */
window.countMessageMap = {};


/**
 * layim初始化
 * @param userid
 * @param option layim配置属性
 * @constructor
 */
function IMinit(userid, options, callback) {
    //消息计数器
    var consent = 1;
    //初始化当前SessionUserID
    currentsession = (null == userid ? '' : String(userid)).trim();
    //初始化路径(项目路径 & web socket路径)
    var IMPATH = __CreateJSPath("IMjs.js", 4);
    var websocketurl = IMPATH + "imserver/" + userid;
    websocketurl = websocketurl.replace("https", "ws").replace("http", "ws");

    //初始化layui路径配置
    layui.config({
        //扩展 JS 所在目录
        base: '../../static/script/im/Fish_ProgrammerJS/'
    }).extend({
        //扩展右键菜单
        contextMenu: 'contextMenu'
    });


    initConnect();
    layui.use(['layer', 'jquery', 'contextMenu'], function () {
        var layer = layui.layer, $ = layui.jquery;
        /**
         * layim主动发消息
         * @param msg 消息正文
         * @param receiver 未知
         * @param group 用户组
         * @param my 发送者
         * @param to 接受者
         * @returns {boolean}
         */
        function sendMessage(msg, receiver, group, my, to) {
            var jsonObj = {
                "msg": msg,
                "receiver": receiver,
                "group": group,
                "my": my,
                "to": to,
            };
            if (isDwrContext()) {
                Dwrmsg.onMessage(userid, JSON.stringify(jsonObj));
                return true;
            } else if (isSocketContext()) {
                socket.send(JSON.stringify(jsonObj));
                return true;
            } else {
                return false;
            }
        }

        /**
         * 拉取离线消息
         * @param layim layim实例
         */
        function getOfflineMessage(layim) {
            $.ajax({
                type: "post",
                url: "/Im/getofflinemsg",
                async: true,
                success: function (data) {
                    var dataObj = data;
                    if (dataObj != null && dataObj.length > 0) {
                        for (var i = 0; i < dataObj.length; i++) {
                            if (dataObj[i].type == "group") {
                                layim.getMessage({
                                    username: dataObj[i].username,
                                    avatar: "/static/script/im/img/team.png",
                                    id: dataObj[i].senduser,
                                    type: dataObj[i].type,
                                    content: dataObj[i].content,
                                    timestamp: dataObj[i].timestam
                                });
                            } else {
                                layim.getMessage({
                                    username: dataObj[i].username,
                                    avatar: dataObj[i].avatar,
                                    id: dataObj[i].senduser,
                                    type: dataObj[i].type,
                                    content: dataObj[i].content,
                                    timestamp: dataObj[i].timestam
                                });
                            }
                        }
                    }
                }, error: function () {
                    console.error('拉取离线消息失败');
                }, complete: function () {
                    //拉取提醒消息总数
                    $.post("/Im/remind", function (res) {
                        if (res.code == 200 && res.data != 0) {
                            layim.msgbox(res.data);
                        }
                    })
                }
            });
        }

        /**
         * 修改用户签名
         * @param userId 用户Id
         * @param text 签名文本
         */
        function editUserSign(userId, text) {
            $.ajax({
                type: "post",
                url: "/Im/sign",
                contentType: 'application/json',
                data: JSON.stringify({
                    "userid": userId,
                    "sign": text,
                }),
                success: function (data) {
                    if (200 === data.code) {
                        layer.msg('修改成功');
                    }
                }
            });
        }

        layui.use('layim', function (layim) {
            // 基础配置
            options = $.extend({
                init: {
                    url: '/Im/init',// 接口地址（返回的数据格式见layim文档）
                    type: 'get', // 默认get，一般可不填
                    // 额外参数
                }, // 获取主面板列表信息
                title: "腾企通讯",
                // 获取群员接口 请自行实现获取群用户
                members: {
                    url: '/Im/GroupMembers'
                },
                // 上传图片接口（返回的数据格式见下文）
                uploadImage: {
                    url: '/Mes/upload', // 接口地址
                    type: 'post' // 默认post
                },
                // 上传文件接口（返回的数据格式见下文）
                uploadFile: {
                    url: '/Mes/upload', // 接口地址
                    type: 'post' // 默认post
                },
                isAudio: false, // 开启聊天工具栏音频
                isVideo: false, // 开启聊天工具栏视频
                copyright: true, //授权
                brief: false, //是否客户模式
                min: false,  //是否第一次打开页面最小化
                isgroup: true,

                notice: false,//消息提醒开启
                find: layui.cache.dir + 'css/modules/layim/html/find.html',
                msgbox: layui.cache.dir + 'css/modules/layim/html/msgbox.html',
                groupChat: layui.cache.dir + 'css/modules/layim/html/groupchat.html',
                chatLog: layui.cache.dir + 'css/modules/layim/html/tzchatlog.html'
            }, options);
            layim.config(options);


            $(window).bind('resize', function (e) {
                var $el = $('#layui-layim');
                var $el_head = $el.siblings(".layui-layer-title");
                $el.css('height', ($el.parent().height() - ($el_head.outerHeight())) + 'px');
            });
            $(window).on("load", function () {
                $(window).trigger('resize');
            });

            layim.on('ready', function (res) {
                im = layui.layim;
                getOfflineMessage(layim);
            });
            //绑定layim发送消息事件，根据浏览器环境切换发送方式
            layim.on('sendMessage', function (data) {
                var To = data.to;
                var my = data.mine;
                var message = my.content;
                var receiver = String(To.id);
                if ('' === currentsession) {
                    console.err('Current SessionUser is Null,Can\'t send message !');
                    return false;
                } else if (null === message || '' === message.trim()) {
                    layer.msg("请输入要发送的消息!");
                    return false;
                } else {
                    if (!!~['friend', 'company'].indexOf(To.type)) {
                        sendMessage(message, receiver, null, my, To)
                    } else {
                        sendMessage(message, null, receiver, my, To)
                    }
                }
            });
            //绑定layim监听签名修改事件
            layim.on('sign', function (text) {
                editUserSign(currentsession, text);
            });


            //监听聊天窗口的切换(更新用户未读消息)
            layim.on('chatChange', function (res) {
                updateReaded(userid, res.data.id, res.data.type)
            });
            if ($.isFunction(callback)) {
                callback.call(this, layim);
            }
        });
    });

    /**
     * 初始化连接
     */
    function initConnect() {
        if (isDwrContext()) {
            //这个方法用来启动该页面的ReverseAjax功能
            dwr.engine.setActiveReverseAjax(true);
            //设置在页面关闭时，通知服务端销毁会话
            dwr.engine.setNotifyServerOnPageUnload(true);
            //重新DWR服务器错误不弹窗打印日志
            dwr.engine._errorHandler = function (message, ex) {
                dwr.engine._debug("Error: " + ex.name + ", " + ex.message, true);
            };
        } else if (isSocketContext()) {
            // 创建websokect连接
            createWebSocket(websocketurl, initEventHandle, userid);
        } else {
            //浏览器环境不兼容
            console.error('dwr、socket未能加载成功');
        }
    }

    /**
     * 判断是否为dwr上下文
     */
    function isDwrContext() {
        return 'undefined' != typeof (window.dwr);
    }

    /**
     * 判断是否存在web socket上下位
     * @returns {boolean}
     */
    function isSocketContext() {
        return undefined !== (window.WebSocket || window.MozWebSocket);
    }

    /**
     * 事件绑定
     */
    function initEventHandle() {
        // 收到消息后
        socket.onmessage = function (event) {
            var data = event.data; // 后端返回的是文本帧时触发
            var json = eval('(' + data + ')');
            if (json.type === "1") {
                var res = layim.thisChat();
                var group = json.group;
                if (group != null) {
                    im.getMessage({
                        username: json.my.username,
                        avatar: json.my.avatar,
                        id: group,
                        type: "group",
                        content: json.msg,
                        timestamp: new Date().getTime(),
                    });
                } else {
                    getMessageHandler(json.fromUserId);
                    im.getMessage({
                        username: json.my.username,
                        avatar: json.my.avatar,
                        id: json.fromUserId,
                        type: "friend",
                        content: json.msg,
                        timestamp: new Date().getTime(),
                    });
                }
                if (res != null && res.data.id == group) {
                    updateReaded(userid, group, "group")
                } else if (res != null && res.data.id == json.fromUserId) {
                    updateReaded(userid, json.fromUserId, "friend")
                }
            } else if (json.type === "2") {
                for (var i = 0, max = json.offline.length; i < max; i++) {
                    layim.setFriendStatus(json.offline[i], 'offline');
                }
                for (var ii = 0, maxx = json.online.length; ii < maxx; i++) {
                    layim.setFriendStatus(json.online[ii], 'online');
                }
            } else if (json.type === "3") {
                layim.setFriendStatus(json.userid, json.flag);
            } else if (json.type === "4") {
                //删除群聊
                layim.removeList({
                    id: json.groupId
                    , type: 'group'
                });
            } else if (json.type === "5") {
                if ($('.layim-tool-msgbox').val() == "0") {
                    consent = 1;
                }
                layim.msgbox(consent++);
                if (json.hasOwnProperty("imLog")) {
                    if (json.imLog == "clear") {
                        //清除消息记录
                        var cache = layui.layim.cache();
                        var local = layui.data('layim')[cache.mine.id]; //获取当前用户本地数据
                        var flockId = "group" + json.flockId;
                        //删除聊天通知
                        for (var key in local.history) {
                            if (local.history.hasOwnProperty(key)) {
                                if (key == flockId) {
                                    delete local.history[key];
                                    $(".layim-list-history  [data-index=" + flockId + "]").remove();
                                }
                            }
                        }
                        //删除聊天通知内容
                        for (var keylog in local.chatlog) {
                            if (local.history.hasOwnProperty(keylog)) {
                                if (keylog == flockId) {
                                    delete local.chatlog[keylog];
                                }
                            }
                        }
                        layui.data('layim', {
                            key: cache.mine.id
                            , value: local
                        });
                    }
                }
            } else if (json.type === "6") {
                //增加一个群组
                layim.addList({
                    type: 'group'
                    , avatar: "/static/script/im/img/team.png"
                    , groupname: json.name
                    , id: json.id
                    , members: 0
                });
            }
        };
        // 连接关闭
        socket.onclose = function (event) {
            layim.setFriendStatus(currentsession, 'offline');
            /*layer.confirm('您的IM已下线，重新上线?', function (index) {
                reconnect(websocketurl, initEventHandle);
                layer.close(index);
            });*/
        };
        socket.onerror = function () {
            reconnect(websocketurl, initEventHandle);
        };
    }
}


/**
 * 创建websocket连接
 * @param url 请求地址
 * @param callback 回调函数
 * @param userid 用户ID
 */
function createWebSocket(url, callback, userid) {
    try {
        if (!window.WebSocket) {
            window.WebSocket = window.MozWebSocket;
        }
        if (window.WebSocket) {
            socket = new WebSocket(url);
            socket.binaryType = "arraybuffer";
            callback();
        } else {
            Dwrmsg.onPageLoad(userid);
            dwr.engine.setActiveReverseAjax(true);
            dwr.engine.setNotifyServerOnPageUnload(true);

            function errh(errorString, exception) {

            }

            dwr.engine.setErrorHandler(errh);
        }
    } catch (e) {
        reconnect(url, callback);
    }
}

/**
 * websock重连
 * @param url 请求地址
 * @param callback 回调函数
 */
function reconnect(url, callback) {
    if (reconnectflag) {
        return false;
    } else {
        reconnectflag = true;
        // 没连接上会一直重连，设置延迟避免请求过多
        setTimeout(function () {
            createWebSocket(url, callback);
            reconnectflag = false;
        }, 2000);
    }
}

/**
 * 收到消息后触发
 * @param userId 对话用户ID
 * @returns {boolean}
 */
function getMessageHandler(userId) {
    if (null == userId || '' == userId) {
        return false;
    }
    userId = String(userId);
    if (!countMessageMap.hasOwnProperty(userId)) {
        countMessageMap[userId] = 0;
    } else {
        countMessageMap[userId] = isNaN(countMessageMap[userId]) ? 0 : parseInt(countMessageMap[userId]);
    }
    countMessageMap[userId] += 1;

    var count = 0;
    for (var key in countMessageMap) {
        if (countMessageMap.hasOwnProperty(key) && !isNaN(countMessageMap[key])) {
            count += countMessageMap[key];
        }
    }
    parentEle.css('display', 'block');
    parentEle.text(count);
}

/**
 * 阅读消息后触发
 * @param userId 对话用户ID
 * @returns {boolean}
 */
function readedMessageHandler(userId) {
    if (null == userId || '' == userId) {
        return false;
    }
    userId = String(userId);
    delete countMessageMap[userId];

    var count = 0;
    for (var key in countMessageMap) {
        if (countMessageMap.hasOwnProperty(key) && !isNaN(countMessageMap[key])) {
            count += countMessageMap[key];
        }
    }
    parentEle.css('display', count > 0 ? 'block' : 'none');
    parentEle.text(count > 0 ? count : '');
}


/*function addEvents(target, eventType, handle) {
    if (document.addEventListener) {
        Event.addEvents = function (target, eventType, handle) {
            target.addEventListener(eventType, handle, false);
        };
    } else {
        Event.addEvents = function (target, eventType, handle) {
            target.attachEvent('on' + eventType, function () {
                handle.call(target, arguments);
            });
        };
    }
    Event.addEvents(target, eventType, handle);
}*/

/**
 * 更新用户已读消状态
 * @param userId 用户ID
 * @param targetId 对话用户ID
 * @param type 类型
 */
function updateReaded(userId, targetId, type) {
    $.ajax({
        type: "post",
        url: "/Im/updateRead",
        data: JSON.stringify({
            "userid": userId,
            "updateid": targetId,
            "type": type
        }),
        contentType: 'application/json',
        success: function () {
            readedMessageHandler(targetId);
        }
    });
}

/**
 * [DWR反转控制]判断是否在线
 * @param data
 */
function heartbeat(data) {
    Dwrmsg.heartbeat(data);
}

/**
 * [DWR反转控制]设置用户在线状态
 * @param data
 */
function setOnlineState(data) {
    if (data.type === "2") {
        for (var i = 0, max = data.offline.length; i < max; i++) {
            layim.setFriendStatus(data.offline[i], 'offline');
        }
        for (var ii = 0, maxx = data.online.length; ii < maxx; ii++) {
            layim.setFriendStatus(data.online[ii], 'online');
        }
    } else if (data.type === "3") {
        layim.setFriendStatus(data.userid, data.flag);
    }
}

/**
 * [DWR反转控制]推送消息方法
 * @param data
 */
function showMessage(data) {
    var json = eval('(' + data + ')');
    if (json.type === "1") {
        var res = im.thisChat();
        var group = json.group;
        if (group != null) {
            im.getMessage({
                username: json.my.username,
                avatar: json.my.avatar,
                id: group,
                type: "group",
                content: json.msg,
                timestamp: new Date().getTime(),
            });
        } else {
            getMessageHandler(json.fromUserId);
            im.getMessage({
                username: json.my.username,
                avatar: json.my.avatar,
                id: json.fromUserId,
                type: "friend",
                content: json.msg,
                timestamp: new Date().getTime(),
            });
        }
        if (res != null && res.data.id == group) {
            updateReaded(currentsession, group, "group");
        } else if (res != null && res.data.id == json.fromUserId) {
            updateReaded(currentsession, json.fromUserId, "friend");
        }
    } else if (json.type === "4") {
        //删除群聊
        im.removeList({
            id: json.groupId
            , type: 'group'
        });
    } else if (json.type === "5") {
        if ($('.layim-tool-msgbox').val() === "0") {
            consent = 1;
        }
        im.msgbox(consent++);
    } else if (json.type === "6") {
        //增加一个群组
        im.addList({
            type: 'group'
            , avatar: "/static/script/im/img/team.png"
            , groupname: json.name
            , id: json.id
            , members: 0
        });
    }
}

function flockTrigger(gid) {
    var my_spread = $('.layim-list-group > li')
    var data = {
        width: 140, // width
        itemHeight: 30, // 菜单项height
        bgColor: "#fff", // 背景颜色
        color: "#333", // 字体颜色
        fontSize: 15, // 字体大小
        hoverBgColor: "#009bdd", // hover背景颜色
        hoverColor: "#fff", // hover背景颜色
        menu: []
    };
    //todo:采用金字塔模式判断防止代码冗余 || 后期可在添加
    $.post("/Im/GroupManagerUser", {GroupId: gid}, function (res) {
        if (res.type == 1 || res.type == 2) {//群主与管理员
            data.menu.push({
                text: "添加成员",
                icon: "",
                callback: function (ele) {
                    layer.open({
                        type: 2
                        , title: '删除成员'
                        , shade: false
                        , maxmin: true
                        , area: ['830px', '572px']
                        , skin: 'layui-box layui-layer-border'
                        , resize: false
                        , content: layui.cache.dir + 'css/modules/layim/html/FlockCURD.html'
                        , success: function (layero, index) {
                            //获取子页面Body主体元素
                            var body = layer.getChildFrame('body', index);
                            //存data值 todo:后期可以扩展
                            body.find(".layui-container").attr("data-gid", gid);
                            body.find(".layui-container").attr("data-type", res.type);
                            body.find(".layui-container").attr("data-action", "1");
                        }
                    });
                }
            });   //功能可用
            data.menu.push({
                text: "踢出成员",
                icon: "",
                callback: function (ele) {
                    layer.open({
                        type: 2
                        , title: '删除成员'
                        , shade: false
                        , maxmin: true
                        , area: ['830px', '572px']
                        , skin: 'layui-box layui-layer-border'
                        , resize: false
                        , content: layui.cache.dir + 'css/modules/layim/html/FlockCURD.html'
                        , success: function (layero, index) {
                            //获取子页面Body主体元素
                            var body = layer.getChildFrame('body', index);
                            //存data值 todo:后期可以扩展
                            body.find(".layui-container").attr("data-gid", gid);
                            body.find(".layui-container").attr("data-type", res.type);
                            body.find(".layui-container").attr("data-action", "2");
                        }
                    });
                }
            });   //功能可用
        }
        if (res.type == 1) {//群主
            data.menu.push({
                text: "设置管理员",
                icon: "",
                callback: function (ele) {
                    layer.open({
                        type: 2
                        , title: '设置管理员'
                        , shade: false
                        , maxmin: true
                        , area: ['830px', '572px']
                        , skin: 'layui-box layui-layer-border'
                        , resize: false
                        , content: layui.cache.dir + 'css/modules/layim/html/FlockCURD.html'
                        , success: function (layero, index) {
                            //获取子页面Body主体元素
                            var body = layer.getChildFrame('body', index);
                            //存data值 todo:后期可以扩展
                            body.find(".layui-container").attr("data-gid", gid);
                            body.find(".layui-container").attr("data-type", res.type);
                            body.find(".layui-container").attr("data-action", "0");
                        }
                    });
                }
            }); //功能可用
            data.menu.push({
                text: "修改群名",
                icon: "",
                callback: function (ele) {
                    layer.prompt({title: '修改群名', formType: 0,}, function (nickName, index) {
                        $.post("/Im/InnovateFlockName", {
                            GroupName: nickName,
                            GroupId: gid
                        }, function (res) {
                            if (res.code == 200) {
                                var groupName = $(".layim-group" + gid).find('span');
                                groupName.html(nickName);
                                layer.close(index);
                            }
                        });
                    });
                }
            });   //功能可用
            data.menu.push({
                text: "解散群",
                icon: "",
                callback: function (ele) {
                    layer.confirm('确定解散群吗？', function (index) {
                        $.post('/Im/dissolution', {GroupId: gid}, function (res) {
                            if (res.code == 500) {
                                return layer.msg(res.msg);
                            }
                            layer.close(index);
                            im.removeList({
                                id: gid
                                , type: 'group'
                            });
                            //清除消息记录
                            var cache = layui.layim.cache();
                            var local = layui.data('layim')[cache.mine.id]; //获取当前用户本地数据
                            var flockId = "group" + gid;
                            for (var key in local.history) {
                                if (local.history.hasOwnProperty(key)) {
                                    if (key == flockId) {
                                        delete local.history[key];
                                        $(".layim-list-history  [data-index=" + flockId + "]").remove();
                                    }
                                }
                            }
                            //删除聊天通知内容
                            for (var keylog in local.chatlog) {
                                if (local.history.hasOwnProperty(keylog)) {
                                    if (keylog == flockId) {
                                        delete local.chatlog[keylog];
                                    }
                                }
                            }
                            layui.data('layim', {
                                key: cache.mine.id
                                , value: local
                            });

                        });
                    });
                }
            })      //功能可用
        }
        if (res.type == 2 || res.type == 0) {//管理员与普通用户
            data.menu.push({
                text: "退出群",
                icon: "",
                callback: function (ele) {
                    layer.confirm('确定退出群吗？', function (index) {
                        $.post('/Im/exit', {GroupId: gid}, function (res) {
                            if (res.code == 500) {
                                return layer.msg(res.msg);
                            }
                            layer.close(index);
                            im.removeList({
                                id: gid
                                , type: 'group'
                            });
                            //清除消息记录
                            var cache = layui.layim.cache();
                            var local = layui.data('layim')[cache.mine.id]; //获取当前用户本地数据
                            var flockId = "group" + gid;
                            //删除聊天通知
                            for (var key in local.history) {
                                if (local.history.hasOwnProperty(key)) {
                                    if (key == flockId) {
                                        delete local.history[key];
                                        $(".layim-list-history  [data-index=" + flockId + "]").remove();
                                    }
                                }
                            }
                            //删除聊天通知内容
                            for (var keylog in local.chatlog) {
                                if (local.history.hasOwnProperty(keylog)) {
                                    if (keylog == flockId) {
                                        delete local.chatlog[keylog];
                                    }
                                }
                            }
                            layui.data('layim', {
                                key: cache.mine.id
                                , value: local
                            });
                        });
                    });
                }
            })      //功能可用
        }
        my_spread.contextMenu(data);
    })
}


(function () {
    "use strict";
    var head = document.getElementsByTagName("HEAD")[0];
    var $style = $("<style/>");
    $style.attr('type', 'text/css');
    var style_text = ''
        //组织架构样式
        + '.layim-tab-content li ul{padding-left:15px;}'
        //隐藏最小化图标
        + ".layui-layim-min.layui-layim-close{display:none !important;}"
        + " .layer-anim{-webkit-animation-duration:0s !important;animation-duration:0s !important;-webkit-animation-fill-mode:both;animation-fill-mode:both;}"
        //隐藏换肤图标
        + ".layim-tool-skin{display:none !important;}";
    $style.html(style_text);
    head.appendChild($style[0]);
})(document);
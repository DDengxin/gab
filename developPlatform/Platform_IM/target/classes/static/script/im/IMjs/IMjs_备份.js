function __CreateJSPath(js, recall) {
	// 兼容IE
	if (typeof String.prototype.startsWith !== 'function') {
		String.prototype.startsWith = function(prefix) {
			return this.indexOf(prefix.length) === 0;
		};
	}
	if (typeof String.prototype.endsWith !== 'function') {
		String.prototype.endsWith = function(suffix) {
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
};
var bootPATH = __CreateJSPath("IMjs.js", 1);

document.write('<script src="/dwr/engine.js" type="text/javascript"></sc' + 'ript>');
document.write('<script src="/dwr/interface/Dwrmsg.js" type="text/javascript"></sc' + 'ript>')
document.write('<link href="' + bootPATH + 'layui/css/layui.css" rel="stylesheet" type="text/css" />');
document.write('<script src="' + bootPATH + 'layui/layui.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/websocketconfig.js" type="text/javascript"></script>');
var showmsg, lm, currentsession;
function IMinit(userid) {
	var IMPATH = __CreateJSPath("IMjs.js", 4);// 项目路径
	var websocketurl = IMPATH + "imserver/" + userid;
	websocketurl = websocketurl.replace("https", "ws").replace("http", "ws");
	currentsession = userid;
	layui.use([ 'layer', 'jquery' ], function() {
		var layer = layui.layer, $ = layui.jquery;
		// 发送消息
		var sendMsg = function(msg, receiver, group, my, to) {
			var jsonObj = {
				"msg" : msg,
				"receiver" : receiver,
				"group" : group,
				"my" : my,
				"to" : to,
			};
			if (!window.WebSocket) {
				Dwrmsg.onMessage(userid, JSON.stringify(jsonObj));
				return;
			} else {
				socket.send(JSON.stringify(jsonObj));
				return;
			}
		}

		// 拉取离线消息
		var showOfflineMsg = function(layim) {
			$.ajax({
				type : "post",
				url : "/Im/getofflinemsg",
				async : true,
				success : function(data) {
					var dataObj = data;
					if (dataObj != null && dataObj.length > 0) {
						for (var i = 0; i < dataObj.length; i++) {
							if (dataObj[i].type == "group") {
								layim.getMessage({
									username : dataObj[i].username,
									avatar : dataObj[i].avatar,
									id : "1",
									type : dataObj[i].type,
									content : dataObj[i].content,
									timestamp : dataObj[i].timestam
								});
							} else {
								layim.getMessage({
									username : dataObj[i].username,
									avatar : dataObj[i].avatar,
									id : dataObj[i].senduser,
									type : dataObj[i].type,
									content : dataObj[i].content,
									timestamp : dataObj[i].timestam
								});
							}
						}
					}
				}
			});
		}

		layui.use('layim', function(layim) {
			// 基础配置
			layim.config({
				init : {
					url : '/Im/init',// 接口地址（返回的数据格式见layim文档）
					type : 'get', // 默认get，一般可不填
					data : {}
				// 额外参数
				}, // 获取主面板列表信息
				title : "IM",
				notice : false,
				// 获取群员接口 请自行实现获取群用户
				members : {
					url : '', // 接口地址
					type : 'get', // 默认get，一般可不填
					data : {}
				// 额外参数
				},
				// 上传图片接口（返回的数据格式见下文）
				uploadImage : {
					url : '/Mes/upload', // 接口地址
					type : 'post' // 默认post
				},
				// 上传文件接口（返回的数据格式见下文）
				uploadFile : {
					url : '/Mes/upload', // 接口地址
					type : 'post' // 默认post
				},
				isAudio : true, // 开启聊天工具栏音频
				isVideo : true, // 开启聊天工具栏视频
				brief : false,
				min : false,
				isgroup : true,
				voice : false,
				copyright : true,
				chatLog : layui.cache.dir + 'css/modules/layim/html/tzchatlog.html'
			});

			layim.on('ready', function(res) {
				lm = layui.layim;
				showOfflineMsg(layim);
			});

			// 监听发送消息
			layim.on('sendMessage', function(data) {
				var To = data.to;
				var my = data.mine;
				var message = my.content;
				var receiver = To.id + "";
				if ($.trim(currentsession) == '') {
					return;
				}
				if ($.trim(message) == '') {
					layer.msg("请输入要发送的消息!");
					return;
				}

				if (To.type == "friend") {
					sendMsg(message, receiver, null, my, To)
				} else {
					sendMsg(message, null, receiver, my, To)
				}
			});

			// 监听签名事件
			layim.on('sign', function(value) {
				$.ajax({
					type : "post",
					url : "/Im/sign",
					data : JSON.stringify({
						"userid" : userid,
						"sign" : value,
					}),
					contentType : 'application/json',
					success : function(data) {
						if (200 === data.code) {
							layer.msg('修改成功');
						}
					}
				});
			});

			// 监听聊天窗口的切换
			layim.on('chatChange', function(res) {
				update(userid, res.data.id, res.data.type)
			});

			var initEventHandle = function() {
				// 收到消息后
				socket.onmessage = function(event) {
					var data = event.data; // 后端返回的是文本帧时触发
					var json = eval('(' + data + ')');
					if (json.type == "3") {
						layim.setFriendStatus(json.userid, json.flag);
						return;
					}
					if (json.type == "2") {
						for (var i = 0, l = json.offline.length; i < l; i++) {
							layim.setFriendStatus(json.offline[i], 'offline');
						}
						for (var i = 0, l = json.online.length; i < l; i++) {
							layim.setFriendStatus(json.online[i], 'online');
						}
					}
					if (json.type == "1") {
						var res = layim.thisChat();
						var group = json.group;
						if (group != null) {
							lm.getMessage({
								username : json.my.username,
								avatar : json.my.avatar,
								id : group,
								type : "group",
								content : json.msg,
								timestamp : new Date().getTime(),
							});
						} else {
							lm.getMessage({
								username : json.my.username,
								avatar : json.my.avatar,
								id : json.fromUserId,
								type : "friend",
								content : json.msg,
								timestamp : new Date().getTime(),
							});
						}
						if (res != null && res.data.id == group) {
							update(userid, group, "group")
						} else if (res != null && res.data.id == json.fromUserId) {
							update(userid, json.fromUserId, "friend")
						}
					}
				};
				// 连接关闭
				socket.onclose = function(event) {
					// layim.setFriendStatus(currentsession, 'offline');
					// layer.confirm('您的IM已下线，重新上线?', function(index) {
					// reconnect(websocketurl, initEventHandle);
					// layer.close(index);
					// });
				};
				socket.onerror = function() {
					reconnect(websocketurl, initEventHandle);
				};
			}
			// 创建websokect连接
			createWebSocket(websocketurl, initEventHandle, userid);
		});

	});
}

function update(userid, id, type) {
	$.ajax({
		type : "post",
		url : "/Im/updateRead",
		data : JSON.stringify({
			"userid" : userid,
			"updateid" : id,
			"type" : type
		}),
		contentType : 'application/json',
	});
}

// dwr判断是否在线
function heartbeat(data) {
	Dwrmsg.heartbeat(data);
}

/**
 * dwr
 * 设置用户在线状态
 * @param data
 */
function setOnlineState(data) {
	if (data.type == "2") {
		for (var i = 0, l = data.offline.length; i < l; i++) {
			layim.setFriendStatus(data.offline[i], 'offline');
		}
		for (var i = 0, l = data.online.length; i < l; i++) {
			layim.setFriendStatus(data.online[i], 'online');
		}
	}
	if (data.type == "3") {
		layim.setFriendStatus(data.userid, data.flag);
	}
}

// dwr推送消息方法
function showMessage(data) {
	var json = eval('(' + data + ')');
	if (json.type == "1") {
		var res = lm.thisChat();
		var group = json.group;
		if (group != null) {
			lm.getMessage({
				username : json.my.username,
				avatar : json.my.avatar,
				id : group,
				type : "group",
				content : json.msg,
				timestamp : new Date().getTime(),
			});
		} else {
			lm.getMessage({
				username : json.my.username,
				avatar : json.my.avatar,
				id : json.fromUserId,
				type : "friend",
				content : json.msg,
				timestamp : new Date().getTime(),
			});
		}
		if (res != null && res.data.id == group) {
			update(userid, group, "group")
		} else if (res != null && res.data.id == json.fromUserId) {
			update(userid, json.fromUserId, "friend")
		}
	}
}

/**
 * lgl
 * 覆盖layim样式
 */
(function(){
	var head = document.getElementsByTagName("HEAD")[0];
	var style = document.createElement('STYLE');
	style.type = 'text/css';
	style.innerHTML = ".layui-layim-min.layui-layim-close{display:none;}";
	head.appendChild(style);
})(document);
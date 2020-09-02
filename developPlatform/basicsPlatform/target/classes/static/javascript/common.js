'use strict';
/** *************************************扩展原生对象*************************************** */
/**
 * 扩展时间格式化换数
 * @param format
 * @returns {*}
 * @exp new Date().format('yyyyMMdd');
 */
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, // month
        "d+": this.getDate(),    // day
        "h+": this.getHours(),   // hour
        "m+": this.getMinutes(), // minute
        "s+": this.getSeconds(), // second
        "q+": Math.floor((this.getMonth() + 3) / 3),  // quarter
        "S": this.getMilliseconds() // millisecond
    }
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
        (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) if (new RegExp("(" + k + ")").test(format))
        format = format.replace(RegExp.$1,
            RegExp.$1.length == 1 ? o[k] :
                ("00" + o[k]).substr(("" + o[k]).length));
    return format;
};
Date.prototype.addMonths= function(m)
{
    var d = this.getDate();
    this.setMonth(this.getMonth() + m);
    if (this.getDate() < d)
        this.setDate(0);
};
Date.prototype.addDays = function(d)
{
    this.setDate(this.getDate() + d);
};
/**
 * 扩展String 字符串格式化
 *
 * @param args
 *            格式化参数值
 * @exp: "1{0}2".format("二") "1{trow}2".format({two:"二"})
 */
String.prototype.format = function (args) {
    var result = this;
    if (arguments.length < 1) {
        return result;
    }

    var data = arguments; // 如果模板参数是数组
    if (arguments.length == 1 && typeof (args) == "object") {
        // 如果模板参数是对象
        data = args;
    }
    for (var key in data) {
        var value = data[key];
        if (undefined != value) {
            result = String(result).replace(new RegExp("\\{" + key + "\\}", "gm"), value);
        }
    }
    return result;
};
/**
 * 兼容IE8 Function.bind
 */
Function.prototype.bind = Function.prototype.bind || function (obj) {
    var _self = this, args = arguments;
    return function () {
        _self.apply(obj, Array.prototype.slice.call(args, 1));
    }
};
/**
 * 兼容IE8 String.startsWith
 */
String.prototype.startsWith = String.prototype.startsWith || function (prefix) {
    return this.indexOf(prefix) === 0;
};
/**
 * 兼容IE8 String.endsWith
 */
String.prototype.endsWith = String.prototype.endsWith || function (suffix) {
    return this.indexOf(suffix, this.length - suffix.length) !== -1;
};
/**
 * 兼容IE 8 Array.map
 *
 * @type {*|(function(*=, *=): any[])}
 */
Array.prototype.map = Array.prototype.map || function (callback, thisArg) {
    var T, A, k;
    if (this == null) {
        throw new TypeError(" this is null or not defined");
    }
    var O = Object(this);
    var len = O.length >>> 0;
    if (typeof callback !== "function") {
        throw new TypeError(callback + " is not a function");
    }
    if (thisArg) {
        T = thisArg;
    }
    A = new Array(len);
    k = 0;
    while (k < len) {
        var kValue, mappedValue;
        if (k in O) {
            kValue = O[k];
            mappedValue = callback.call(T, kValue, k, O);
            A[k] = mappedValue;
        }
        k++;
    }
    return A;
};
/**
 * 兼容IE8 Array.isArray
 */
Array.isArray = Array.isArray || function (obj) {
    return Object.prototype.toString.call(obj) == "[object Array]";
};
/**
 *  兼容IE8 Array.prototype.filter
 * @type {*|(function(*=, *=): any[])}
 */
Array.prototype.filter = Array.prototype.filter || function (func, thisArg) {
    'use strict';
    if (!((typeof func === 'Function' || typeof func === 'function') && this))
        throw new TypeError();

    var len = this.length >>> 0,
        res = new Array(len), // preallocate array
        t = this, c = 0, i = -1;

    var kValue;
    if (thisArg === undefined) {
        while (++i !== len) {
            // checks to see if the key was set
            if (i in this) {
                kValue = t[i]; // in case t is changed in callback
                if (func(t[i], i, t)) {
                    res[c++] = kValue;
                }
            }
        }
    } else {
        while (++i !== len) {
            // checks to see if the key was set
            if (i in this) {
                kValue = t[i];
                if (func.call(thisArg, t[i], i, t)) {
                    res[c++] = kValue;
                }
            }
        }
    }

    res.length = c; // shrink down array to proper size
    return res;
};
/**
 * 兼容IE8 window.console
 */
window.console = window.console || {
    log: hmq.noop,
    debug: hmq.noop,
    info: hmq.noop,
    warn: hmq.noop,
    exception: hmq.noop,
    assert: hmq.noop,
    dir: hmq.noop,
    dirxml: hmq.noop,
    trace: hmq.noop,
    group: hmq.noop,
    groupCollapsed: hmq.noop,
    groupEnd: hmq.noop,
    profile: hmq.noop,
    profileEnd: hmq.noop,
    count: hmq.noop,
    clear: hmq.noop,
    time: hmq.noop,
    timeEnd: hmq.noop,
    timeStamp: hmq.noop,
    table: hmq.noop,
    error: hmq.noop
};
/***************************************END 扩展原生对象****************************************/


/***************************************jQuery扩展/覆写****************************************/
(function ($) {
    var OVERWRITE = true;// 是否重写ajax
    // 备份ajax方法
    var __private_ajax__ = $.ajax;


    $.ajax = function (options) {
        if (!OVERWRITE) {
            return __private_ajax__.apply(this, arguments);
        } else {
            // 备份方法
            var fn = $.extend({
                beforeSend: $.noop,
                success: $.noop,
                error: $.noop,
                complete: $.noop,
            }, options);
            var loadingOptions = $.extend({
                status: (undefined !== options.loading),
                message: $.extend({}, options.loading)['message'] || '请求中...',
                title: $.extend({}, options.loading)['title'] || ''
            }, options.loading);
            var _opt = $.extend(options, {
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    try {
                        // 输出ajajx错误信息
                        if (mini && mini.debug) {
                            console.error("ajax请求失败:[" + textStatus + "]:" + (XMLHttpRequest.responseText || ''));
                        }
                        // 请求超时
                        var errorMsg = null;
                        if ('error' === textStatus) {
                            try {
                                errorMsg = JSON.parse(XMLHttpRequest.responseText).message;
                            } catch (e) {
                                errorMsg = '请求错误,code:' + XMLHttpRequest.status + '!'
                            }
                        }
                        if (!!~['timeout', 'error', 'parsererror'].indexOf(textStatus) && mini) {
                            var content = {
                                timeout: '服务器响应超时,请重试!',
                                error: errorMsg,
                                abort: '请求终止!',
                                parsererror: '响应数据解析失败!'
                            }[textStatus] || '请求失败,错误状态未知';
                            mini.showTips({content: content, state: 'danger', x: "right", y: "bottom", timeout: 1500});
                        }
                    } catch (e) {
                        console.error(e);
                    } finally {
                        fn.error(XMLHttpRequest, textStatus, errorThrown);
                    }
                },
                beforeSend: function (xhr) {
                    try {
                        if (loadingOptions.status && mini) {
                            mini.mask({
                                el: window.document.body,
                                cls: 'mini-mask-loading',
                                html: loadingOptions.message || '*',
                            });
                        }
                    } catch (e) {
                        console.error(e);
                    } finally {
                        fn.beforeSend(xhr);
                    }
                },
                success: function (data, textStatus, XMLHttpRequest) {
                    try {
                        // 鉴权重定向
                        var _data_ = data;
                        if ('string' === typeof (_data_) && hmq.checkIsJSON(_data_)) {
                            _data_ = JSON.parse(_data_);
                        }
                        if (401 === _data_.code && _data_.hasOwnProperty('redirect')) {
                            alert(_data_.msg);
                            window.top.location.href = _data_.redirect;
                        }
                    } catch (e) {
                        console.error(e);
                    } finally {
                        fn.success(data, textStatus, XMLHttpRequest);
                    }
                },
                complete: function (XMLHttpRequest, textStatus) {
                    try {
                        if (loadingOptions.status && mini) {
                            mini.unmask();
                        }
                    } catch (e) {
                        console.error(e);
                    } finally {
                        fn.complete(XMLHttpRequest, textStatus);
                    }
                }
            });
            return __private_ajax__.call(this, _opt);
        }
    };
    /**
     * 扩展ajax快捷请求方法,以便于restful风格请求 $.方法{ url:请求地址 data：请求数据(可传入函数)
     * callback：请求成功回调函数 type:返回数据目标类型 options:其他ajax参数(可覆盖前面的参数) }
     */
    $.each(["get", "post", "head", "put", "delete", "options", "trace", "connect"], function (i, method) {
        $[method] = function (url, data, callback, type, options) {
            if ($.isFunction(data)) {
                type = type || callback;
                callback = data;
                data = undefined;
            }
            options = $.extend({}, options);
            options = $.extend({
                url: url,
                type: method,
                dataType: type,
                data: data,
                success: callback
            }, options);
            return $.ajax(options);
        };
    });
})(jQuery);


/***************************************工具函数****************************************/
var hmq = {
    settings: {
        ROOT_PATH: null
    },
    /**
     * 检查是否是个JSON
     * @param str
     * @returns {boolean}
     */
    checkIsJSON: function (str) {
        if (typeof str == 'string') {
            try {
                var obj = JSON.parse(str);
                if (typeof obj == 'object' && obj) {
                    return true;
                } else {
                    return false;
                }
            } catch (e) {
                return false;
            }
        }
        return false;
    },
    /**
     * null
     */
    noop: function () {
    },
    isEmpty: function (v) {
        return (null == v || "" == v);
    },
    isNotEmpty: function (v) {
        return !hmq.isEmpty(v);
    },
    /**
     * 判断当前环境是否为mini上下文
     *
     * @returns {boolean}
     */
    isMiniContext: function () {
        return 'undefined' !== typeof (window['mini']);
    },
    // 兼容
    /**
     * 获取计算属性(兼容IE8)
     *
     * @param obj
     * @param attr
     * @returns {*}
     */
    getComputedStyle: function (obj, attr) {
        return obj.currentStyle ? obj.currentStyle[attr] : getComputedStyle(obj)[attr];
    },
    setGridColor: function (grid, fieldName, options, isFg) {
        if (!grid instanceof mini.DataGrid) {
            console.warn('grid is not a DataGrid');
        } else {
            var datas = grid.getData();
            for (var i = 0, max = datas.length; i < max; i += 1) {
                var row = datas[i];
                grid.addRowCls(grid.getRow(i), this.getRowColorCls(row[fieldName], options, isFg));
            }
        }
    }
    ,
    /**
     * 获取URL参数
     *
     * @param sArgName
     *            参数名
     * @returns {string}
     * @constructor
     */
    getQueryString: function (sArgName) {
        var sHref = document.URL;
        var args = sHref.split("?");
        var retval = "";
        if (args[0] == sHref) {
            return retval; /* 无需做任何处理 */
        }
        var str = args[1];
        args = str.split("&");
        for (var i = 0; i < args.length; i++) {
            str = args[i];
            var arg = str.split("=");
            if (arg.length <= 1)
                continue;
            if (arg[0] == sArgName) {
                retval = arg[1];
            }
        }
        return decodeURIComponent(retval);
    },
    setTreeGridColor: function (grid, fieldName, options, isFg) {
        if (!grid instanceof mini.DataGrid) {
            console.error('treeGrid is not a treeGrid');
        } else {
            var datas = grid.getData();
            for (var i = 0, max = datas.length; i < max; i += 1) {
                var rows = datas[i].children;
                for (var j = 0, jmax = rows.length; j < jmax; j += 1) {
                    var row = rows[j];
                    if (row[fieldName] == options.danger) {
                        grid.addRowCls(row, this.getRowColorCls(row[fieldName], options, isFg));
                    }
                }

            }
        }
    }
    ,
    /**
     * DataGrid渲染行，获取颜色行样式
     *
     * @param value
     *            值
     * @param options
     *            jsondui对象 {success:'启用',dagner:'禁用'}
     * @param isFg
     *            默认背景色，true:前景色
     * @returns {string}
     * @example: getRowColorCls(e.value,{success:'启用',dagner:'禁用'});
     */
    getRowColorCls: function (value, options, isFg) {
        var prefix = (true === isFg ? 'hmq-' : 'hmq-row-bg-');
        var rowColors = ['success', 'green',
            'warning', 'orange',
            'danger', 'red',
            'primary', 'blue',
            'info', 'lightgreen',
            'secondary', 'gray',
            'dark'];
        for (var key in options) {
            if (options.hasOwnProperty(key) && !!~rowColors.indexOf(key)) {
                if (options[key] == value) {
                    return prefix + key;
                }
            }
        }
    }
    ,
    /**
     * toptipt提示
     *
     * @param text
     * @param state
     * @param options {
     *            exec:立即执行函数 complete:完成提示框后执行函数 }
     * @options mini.showTips原有配置 + complete回调
     */
    tip: function (text, state, options) {
        options = $.extend({
            content: text,
            state: state || 'default',
            x: 'center',
            y: 'center',
            timeout: 2000,
            complete: undefined,
            exec: undefined,
            targetWindow: window.top
        }, options);
        var _mini_ = (null == options.targetWindow ? options.targetWindow : options.targetWindow.mini) || window.parent.mini || window.self.mini;

        if ($.isFunction(options.exec)) {
            try {
                options.exec.call(this);
            } catch (e) {
                console.error(e);
            }
        }
        _mini_.showTips(options);
        if ($.isFunction(options.complete)) {
            setTimeout(function () {
                options.complete.call(this);
            }, options.timeout);
        }
    }
    ,
    tipSuccess: function (text, options) {
        return this.tip.call(this, text, 'success', options);
    }
    ,
    tipDanger: function (text, options) {
        return this.tip.call(this, text, 'danger', options)
    }
    ,
    tipWarning: function (text, options) {
        return this.tip.call(this, text, 'warning', options)
    }
    ,
    tipInfo: function (text, options) {
        return this.tip.call(this, text, 'info', options)
    }
    ,
    setPageReadOnly: function (flag) {
        $(mini.getChildControls(document.body)).each(function (index, row, item) {
            if (!!~['textbox', "combobox", "datepicker"].indexOf(row.type)) {
                row.setReadOnly(flag);
            }
            if (!!~['datagrid', "treegrid"].indexOf(row.type)) {
                row.setEnabled(!flag);
            }
        })
    },
    /**
     * 获取script路径js
     *
     * @param js
     *            script文件名称
     * @param recall
     *            回溯路径
     * @returns {string}
     * @private
     */
    __CreateJSPath: function (js, recall) {
        var scripts = document.getElementsByTagName("script");
        var path = "";
        for (var i = 0, l = scripts.length; i < l; i++) {
            var src = scripts[i].src;
            if (src.indexOf(js) !== -1) {
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
        if (path.indexOf("https:") === -1 && path.indexOf("http:") === -1 && path.indexOf("file:") === -1 && path.indexOf("\/") !== 0) {
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
    },
    setRootPath: function (rootPath) {
        this.settings.ROOT_PATH = rootPath;
    }, getRootPath: function (rootPath) {
        if ($.isEmptyObject(this.settings.ROOT_PATH)) {
            this.setRootPath(this.__CreateJSPath("common.js", 3));
        }
        return this.settings.ROOT_PATH;
    },
    /**
     * 获取默认根路径
     *
     * @param path
     *            相对路径
     * @param rootPath
     *            根路径,滞空时以setRootPath设定的路径为准
     * @returns {string}
     */
    getPath: function (path, rootPath) {
        path = (path || '').replace(/\\/g, '/');
        path = path.startsWith("/") ? path.substr(1, path.length - 1) : path;
        return (this.getRootPath() || '') + path;
    },
    /**
     * 判断IE版本，非IE系列固定返回999
     *
     * @returns {number}
     * @constructor
     */
    IEVersion: function () {
        var userAgent = navigator.userAgent;
        var isLessIE11 = userAgent.indexOf('compatible') > -1 && userAgent.indexOf('MSIE') > -1;
        var isEdge = userAgent.indexOf('Edge') > -1 && !isLessIE11;
        var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf('rv:11.0') > -1;
        if (isLessIE11) {
            var IEReg = new RegExp('MSIE (\\d+\\.\\d+);');
            IEReg.test(userAgent);
            var IEVersionNum = parseFloat(RegExp['$1']);
            if (IEVersionNum === 7) {
                return 7;
            } else if (IEVersionNum === 8) {
                return 8;
            } else if (IEVersionNum === 9) {
                return 9;
            } else if (IEVersionNum === 10) {
                return 10;
            } else {
                return 6;
            }
        } else if (isEdge) {
            return 12;
        } else if (isIE11) {
            return 11;
        } else {
            return 999;
        }
    }
    ,
    /**
     * 函数防抖(延时-再执行)
     *
     * @param fn
     *            {Function} 实际要执行的函数
     * @param delay
     *            {Number} 延迟时间，也就是阈值，单位是毫秒（ms）
     * @param ctx
     *            {Object} 上下文
     * @return {Function} 返回一个“去弹跳”了的函数
     */
    debounce: function (fn, delay, ctx) {
        var timer = null;
        return function () {
            var context = ctx || this;
            var args = arguments;
            if (null != timer) {
                clearTimeout(timer);
            }
            timer = setTimeout(function () {
                fn.apply(context, args);
            }, delay)
        }
    }
    ,
    /**
     * 函数节流(立马执行-延时)
     *
     * @param fn
     *            {Function} 实际要执行的函数
     * @param delay
     *            {Number} 延迟时间，也就是阈值，单位是毫秒（ms）
     * @param ctx
     *            {Object} 上下文
     * @return {Function} 返回一个“去弹跳”了的函数
     */
    throttle: function (fn, delay, ctx) {
        var isAvail = true;
        return function () {
            var context = ctx || this;
            var args = arguments;
            if (isAvail) {
                fn.apply(context, args);
                isAvail = false;
                setTimeout(function () {
                    isAvail = true;
                }, delay);
            }
        }
    },
    /**
     * 监听事件(兼容IE)
     *
     * @param target
     *            目标节点
     * @param eventType
     *            事件名
     * @param handle
     *            触发动作
     */
    addEvents: function (target, eventType, handle) {
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

    }
    ,
    /**
     * 动态加载外部js文件，并执行回调
     *
     * @param url
     *            请求地址
     * @param callback
     *            加载后回调
     */
    loadJS: function (url, callback) {
        var script = document.createElement('script');
        script.type = 'text/javascript';
        script.src = url;
        if (typeof callback == 'function') {
            script.onload = script.onreadystatechange = function () {
                if (!this.readyState || this.readyState == 'loaded'
                    || this.readyState == 'complete') {
                    callback();
                    script.onload = script.onreadystatechange = null;
                }
            }
        }
        document.body.appendChild(script);
        // document.getElementsByTagName('body')[0].appendChild(script);
    }
    ,
    /**
     * 动态加载外部js文件，并执行回调
     *
     * @param jsText
     *            javascript片段
     */
    loadJSText: function (jsText) {
        var script = document.createElement('script');
        script.type = 'text/javascript';
        try {
            // Firefox,Safari,Chrome,Opera支持
            script.appendChild(document.createTextNode(jsText));
        } catch (ex) {
            // IE早期的浏览器，需要使用script的text属性来指定js代码
            script.text = jsText;
        }
        document.body.appendChild(script);
    }
    ,
    /**
     * 动态加载外部CSS文件
     *
     * @param url
     *            css文件地址
     */
    loadCSS: function (url) {
        var link = document.createElement('link');
        link.rel = 'stylesheet';
        link.type = 'text/css';
        link.url = url;
        document.getElementsByTagName('head')[0].appendChild(link);
    }
    ,
    /**
     * 使用<style>标签包含嵌入式CSS
     *
     * @param cssText
     *            css
     */
    loadCSSText: function (cssText) {
        var style = document.createElement('style');
        style.type = 'text/css';
        try {
            // Firefox,Safari,Chrome,Opera支持
            style.appendChild(document.createTextNode(cssText));
        } catch (ex) {
            // IE早期浏览器，需要使用style元素的styleSheet属性的cssText属性
            style.styleSheet.cssText = cssText;
        }
    },
    /**
     * url解析,可将url分解为各个部门，支持绝对路径以及相对路径
     *
     * @exp: hmq.parseURL('http://localhost/project/pathA/pathB?id=1&name=test#hash001')
     * @param url
     * @returns {} { path: (void|string|*),//相对路径(不带GET参数和hash) protocol:
     *          string,//协议 file: (*|string), //文件名 port: *, //端口号 query: *,
     *          //GET参数(String) host: *, //主机名 source: *,//源字符串
     *          params:{},//GET参数(JSON) hash: (void|string|*), //hash(不带#)
     *          relative:(*|string),//相对路径(+GET参数+hahs) segments: string[]
     *          //路径分隔数组 }
     */
    parseURL: function (url) {
        var a = document.createElement('A');
        a.href = url;
        a.href = a.href;
        return {
            source: url,
            protocol: a.protocol.replace(':', ''),
            host: a.hostname,
            port: a.port,
            query: a.search,
            params: (function () {
                var ret = {},
                    seg = a.search.replace(/^\?/, '').split('&'),
                    len = seg.length, i = 0, s;
                for (; i < len; i++) {
                    if (!seg[i]) {
                        continue;
                    }
                    s = seg[i].split('=');
                    ret[s[0]] = s[1];
                }
                return ret;
            })(),
            file: (a.pathname.match(/\/([^\/?#]+)$/i) || [, ''])[1],
            hash: a.hash.replace('#', ''),
            path: a.pathname.replace(/^([^\/])/, '/$1'),
            relative: (a.href.match(/tps?:\/\/[^\/]+(.+)/) || [, ''])[1],
            segments: a.pathname.replace(/^\//, '').split('/')
        };
    },
    business: {
        /**
         * 审批-提交动作
         *
         * @param options {
         *            menuId:菜单ID(默认通过GET参数取得，无需特意赋值) title:标题
         *            businessId:审批流模板ID url:查阅：审批Url
         *            url:审批为了预览数据时传入的url
         *            error回调传入参数{code:状态码,msg:提示信息} { code:400 请求参数错误 code:403
         *            流程操作错误 code:404 审批模板不存在 code:405 流程配置错误 code:500 流程运行错误 }
         */
        approvalConfirm: function (options) {
            options = $.extend({
                menuId: hmq.getQueryString('menu_id'),
                title: null,
                businessId: null,
                url: null,
                error: null
            }, options);
            mini.confirm("是否确认流程？", "提醒", function (action) {
                if ("ok" === action) {
                    $.ajax({
                        loading: true,
                        url: '/system/workflow/process/startFinishFirstStep',
                        method: 'put',
                        contentType: 'application/json',
                        data: JSON.stringify(options),
                        beforeSend: function () {
                        },
                        success: function (res) {
                            if (200 === res.code) {
                                hmq.tipSuccess(res.msg || '提交成功', {
                                    exec: function () {
                                        if ($.isFunction(search_option)) {
                                            search_option();
                                        }
                                    }
                                });
                            } else {
                                if ($.isFunction(options.error)) {
                                    options.error.call(this, res);
                                } else {
                                    hmq.tipDanger(res.msg || '服务器返回错误,审批流程提交失败!');
                                }
                            }
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            if ($.isFunction(options.error)) {
                                console.error(res.msg + " 将直接执行页内处理逻辑!");
                                options.error.call(this, {code: XMLHttpRequest.status, msg: textStatus || '请求超时!'});
                            } else {
                                hmq.tipDanger('网络请求错误,审批流程提交失败!');
                            }
                        },
                        complete: function () {
                        }
                    });
                }
            });

        },
        /**
         * 审批取消动作
         *
         * @param options {
         *            businessId:取消审核 error:错误回调 }
         *            error回调传入参数{code:状态码,msg:提示信息} { code:400 请求参数错误 code:403
         *            流程操作错误 code:404 审批模板不存在 code:405 流程配置错误 code:500 流程运行错误 }
         */
        approvalCancel: function (options) {
            options = $.extend({
                businessId: null,
                error: function () {
                }
            }, options);
            mini.confirm("确定取消流程？", "警告", function (action) {
                if ("ok" === action) {
                    $.ajax({
                        loading: true,
                        url: '/system/workflow/process/repeal',
                        method: 'post',
                        contentType: 'application/json',
                        data: JSON.stringify({businessId: options.businessId}),
                        beforeSend: function () {
                        },
                        success: function (res) {
                            if (200 === res.code) {
                                hmq.tipSuccess(res.msg || '取消成功', {
                                    exec: function () {
                                        if ($.isFunction(window.search_option)) {
                                            search_option();
                                        }
                                    }
                                });
                            } else {
                                if ($.isFunction(options.error)) {
                                    options.error.call(this, res);
                                } else {
                                    hmq.tipDanger(res.msg || '取消失败');
                                }
                            }
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            if ($.isFunction(options.error)) {
                                console.error(res.msg + " 将直接执行页内处理逻辑!");
                                options.error.call(this, {code: XMLHttpRequest.status, msg: textStatus || '请求超时!'});
                            } else {
                                hmq.tipDanger('网络请求错误,审批流程取消失败!');
                            }
                        },
                        complete: function () {
                        }
                    });
                }
            });
        },
        /**
         * 动态产品参数
         *
         * @param cpCodeType
         *            行业类型
         * @param options
         *            {*}
         * {
         *     miniDataGrid:{[]|*}, //需要动态处理的grid对象
         *     miniForm:{[]|*}, //需要动态处理的form对象
         *     formMapping:{*}}, //form表单中需要动态处理的字段与实际数据库中的字段映射关系
         *     success:function(map,res), //请求成功回调
         *     error:function(res) //请求失败回调
         * }
         * 使用说明：传入grid对象，在grid列对象中设置正确的name属性，就能自行改变表头标识
         * 传入form对象，在input控件中设置正确的name属性能自行改变控件类型和加载数据，在lableh或者td属性上设置正确的for属性,能自行改变描述
         */
        dynamicProductParams: function (cpCodeType, options) {
            $.ajax({
                loading: true,
                url: "/system/parameter/getByAddName/{0}".format(cpCodeType || ''),
                method: 'get',
                cache: false,
                contentType: 'application/json',
                beforeSend: function () {
                    //传入数据规范处理
                    options.formMapping = $.extend({}, options.formMapping);
                    if (!$.isArray(options.miniDataGrid)) {
                        if (null == options.miniDataGrid) {
                            options.miniDataGrid = [];
                        } else {
                            options.miniDataGrid = [options.miniDataGrid];
                        }
                    }
                    if (!$.isArray(options.miniForm)) {
                        if (null == options.miniForm) {
                            options.miniForm = null;
                        } else {
                            options.miniForm = [options.miniForm];
                        }
                    }
                },
                success: function (res) {
                    if (200 === res.code) {
                        var map = MappingKeyValuePairs(res.data);
                        // datagrid动态更新数据列
                        $(options.miniDataGrid).each(function (index, grid) {
                            if (hmq.isMiniContext()) {
                                if (grid instanceof mini.DataGrid || grid instanceof mini.TreeGrid) {
                                    for (var key in map) {
                                        if (map.hasOwnProperty(key) && null != map[key]) {
                                            grid.updateColumn(key, {header: map[key]});
                                        }
                                    }
                                }
                            }
                        });
                        // form动态更新字段
                        $(options.miniForm).each(function (index, form) {
                            if (hmq.isMiniContext()) {
                                if (form instanceof mini.Form) {
                                    var $form = $(form.el);
                                    for (var key in map) {
                                        if (map.hasOwnProperty(key) && null != map[key]) {
                                            var controlName = key;
                                            //映射控件名
                                            var mapControlName = (null != options.formMapping[controlName] ? options.formMapping[controlName] : controlName);
                                            var controlType = controlName + 'Type';
                                            var controlUrl = controlName + 'Url';

                                            //动态调整控件
                                            if (!!map[controlName] && !!map.hasOwnProperty(controlType) && !!map.hasOwnProperty(controlUrl)) {
                                                //调整控件类型
                                                setCombobxAttribute(form.getField(mapControlName), map[controlType], map[controlUrl]);
                                                // 修改表单描述
                                                var $td = $form.find('td[for={0}],td span[for={1}],td label[for={2}]'.format(mapControlName, mapControlName, mapControlName));
                                                if ($td.length > 0) {
                                                    $td.text((map[controlName] || '') + ':');
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        });
                        if ($.isFunction(options.success)) {
                            options.success.call(this, map, res);
                        }
                    } else {
                        if ($.isFunction(options.error)) {
                            options.error.call(this, res);
                        }
                    }
                }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                    if ($.isFunction(options.error)) {
                        options.error.call(this, {code: XMLHttpRequest.status, msg: textStatus});
                    }
                }, complete: function () {

                }
            });

            /**
             * 修改combobx控件类型
             * @param control 控件实例
             * @param type [combobox|treecombobox|treeselect|textbox] 控件类型
             * @param url 数据加载地址
             */
            function setCombobxAttribute(control, type, url) {
                if (mini.isControl(control)) {
                    switch (type) {
                        case 'combobox' :
                            if (control.__proto__ !== mini.ComboBox.prototype) {
                                control = changeControlType(control, mini.ComboBox);
                            }
                            if ($.isFunction(control['load']) && null != url && '' != String(url).trim()) {
                                control.setAjaxType('GET');
                                control.setValueField('id');
                                control.setTextField('text');
                                control.load(url);
                            }
                            break;
                        case 'treeselect' :
                            if (control.__proto__ !== mini.TreeSelect.prototype) {
                                control = changeControlType(control, mini.TreeSelect);
                            }
                            if ($.isFunction(control['load']) && null != url && '' != String(url).trim()) {
                                control.setAjaxType('POST');
                                control.setValueField('id');
                                control.setTextField('text');
                                control.load(url);
                            }
                            break;
                        default:
                            //Combobox || TreeSelect 变更属性转 TextBox
                            if (control.__proto__ === mini.ComboBox.prototype || control.__proto__ === mini.TreeSelect.prototype) {
                                control.setShowButton(false);
                                control.setIndentSpace(false);
                                control.setValueFromSelect(false);
                                control.setAllowInput(true);
                                control.setShowClose(false);
                                control.setShowPopupOnClick(false);
                                control.on('beforeshowpopup', function (e) {
                                    e.cancel = !e.sender.getShowButton();
                                });
                            }
                            break;
                    }
                }
            }

            /**
             * 映射键值对 处理
             * 该处主要是转码URL
             * @param map
             * @return {*}
             */
            function MappingKeyValuePairs(map) {
                for (var key in map) {
                    if (map.hasOwnProperty(key) && key.endsWith("Url") && null != map[key]) {
                        // 该处解码后编码是为了兼容IE
                        // 因为chrome会自动编码如果直接使用encodeURI会导致编码两次,而不编码会让url中存在中文的url访问失败
                        map[key] = encodeURI(decodeURI(map[key]));
                    }
                }
                return map;
            }

            /**
             * 动态改变控件类型
             * @param control 原控件
             * @param controlType 新控件类型
             * @returns {*}
             * TODO:需要优化
             */
            function changeControlType(control, controlType) {
                var newControl = new controlType();
                var className = 'mini-' + controlType.prototype.type;
                for (var key in control.__proto__) {
                    var setFunction = "set" + key.slice(0, 1).toUpperCase() + key.slice(1);
                    var getFunction = "get" + key.slice(0, 1).toUpperCase() + key.slice(1);
                    if (typeof (control.__proto__[setFunction]) === 'function'
                        && typeof (newControl.__proto__[setFunction]) === 'function'
                        && !mini.isControl(control[key])
                        && control.__proto__[key] !== control[key]
                        && !~['url'].indexOf(key)) {
                        newControl[setFunction](control[key]);
                    }
                }
                newControl.render(control.el.parentNode);
                control.destroy();
                return newControl;

            }
        }
    }
};

/***************************************END 工具函数****************************************/

/**
 * excel导出
 *
 * @grid 表格对象
 * @from from表单对象
 * @url 后台处理的地址
 * @json 排除的字段[] 数组
 */
function assembling(options) {
    var defaults = {
        grid: null,// 表格对象
        from: null,// from表单对象
        url: null,// 后台处理的地址
        json: null,// 排除的字段[] 数组
    };
    var opts = $.extend(defaults, options);
    var columns = $(opts.grid.getBottomColumns()).filter(function (index, item) {
        return item.visible == true;
    });
    for (var i = columns.length - 1; i >= 0; i--) {
        var column = columns[i];
        if (!isNull(opts.json) && opts.json.indexOf(column.field) >= 0) {
            columns.removeAt(i);
            continue;
        }
    }
    var data = opts.form.getData(true);
    var fields = {
        type: "excel",
        columns: columns,

        parms: mini.encode(data),
        // pageSize: (grid.getTotalPage()<= 0 ? parseInt(0x7fffffffffffffff,10)
        // : grid.getTotalPage() *grid.getPageSize()),
        // pageIndex:grid.getPageIndex(),
        sortOrder: opts.grid.getSortOrder(),
        sortField: opts.grid.getSortField()
    };
    var submitfrm = document.createElement("form");// 创建Form
    submitfrm.action = opts.url;
    submitfrm.method = "post";
    submitfrm.target = "_blank";
    document.body.appendChild(submitfrm);
    if (fields) {
        for (var p in fields) {
            var input = mini.append(submitfrm, "<input name='" + p + "'>");
            var v = fields[p];
            if (typeof v != "string")
                v = mini.encode(v);
            input.value = v;
        }
    }
    submitfrm.submit();
    setTimeout(function () {
        submitfrm.parentNode.removeChild(submitfrm);
    }, 1000);
}

/**
 * 初始化时间 场景：查询页面中开始时间与结束时间自动填充
 *
 * @param stype
 *            类型
 * @param fristdate
 *            开始日期控件ID
 * @param currentdate
 *            结束日期控件ID
 * @param spare
 *            分隔符号
 */
function init(stype, fristdate, currentdate, spare) {
    var _do_ = function (data) {
        if (null != data) {
            window.top.__init__data__ = data;
        } else if (null != window.top.__init__data__) {
            data = window.top.__init__data__;
        } else {
            return false;
        }
        if (stype == 'date') {
            if (null != mini.get(fristdate)) {
                mini.get(fristdate).setValue(data.beginofmonth);
            }
            var strs = new Array();
            strs = currentdate.split("|");
            for (var i = 0; i < strs.length; i++) {
                if (null != mini.get(strs[i])) {
                    mini.get(strs[i]).setValue(data.currentdate);
                }
            }
            if (spare != '' && null != mini.get(spare)) {
                mini.get(spare).setValue(data.corp);
            }
        }
    }
    if (false === _do_()) {
        $.ajax({
            url: hmq.getPath("/system/init"),// 跳转到 action
            type: "post",
            cache: false,
            dataType: "json",
            success: function (data) {
                _do_(data);
            },
            error: function () {
                console.warn('init请求失败!');
            }
        });
    }
}

/**
 * 打开弹窗
 *
 * @param options {} {
 *            url:弹窗url地址 title:弹窗标题 action:操作标识 width:弹窗宽度(默认：600)
 *            height:弹窗高度(默认：400) ... //mini.open其他参数 //自定义参数 _targetObject:触发对象
 *            data:数据负载(将会自动调用子窗体SetData函数) autoresize:自动调整弹窗尺寸(默认:true) }
 *
 * @return {mini.Window} 弹出窗体对象
 */
function miniopen(options) {
    options = $.extend({
        targetWindow: window,   // 页面对象。默认是顶级页面。
        url: null,        // 页面地址
        title: null,      // 标题
        iconCls: null,    // 标题图标
        width: 300,      // 宽度
        height: 300,     // 高度
        allowResize: true,       // 允许尺寸调节
        allowDrag: true,         // 允许拖拽位置
        showCloseButton: true,   // 显示关闭按钮
        showMaxButton: false,     // 显示最大化按钮
        showModal: false,         // 显示遮罩
        loadOnRefresh: false,       // true每次刷新都激发onload事件
        onload: function () {       // 弹出页面加载完成
            var iframe = this.getIFrameEl();
            var data = {
                action: options.action,
                data: options.data
            };
            try {
                if (!!options.autoresize) {
                    setTimeout(function () {
                        AutoResizePopupWindow.call(iframe.contentWindow);
                    }, 1);
                }
            } catch (e) {
                console.error(e);
            }
            if ($.isFunction(iframe.contentWindow.SetData)) {
                iframe.contentWindow.SetData(data);
            } else if (window['mini_debugger']) {
                console.warn('子页面中没有SetData函数，调用失败!');
            }

        },
        ondestroy: function (res) {  // 弹出页面关闭前
            // 返回的参数中包含以下关键词触发父窗体刷新函数
            if (!!~['save', 'refresh'].indexOf(String(res).toLowerCase())) {
                if ($.isFunction(window.self.search_option)) {
                    search_option();
                } else if (window['mini_debugger']) {
                    console.warn('父页面中没有search_option函数，调用失败!');
                }
            }
        },

        data: null,
        autoresize: true,
    }, options);
    // 根据父页面以及按钮描述创建标题
    if ($.isEmptyObject(options.title) && null != options._targetObject) {
        var action_text = '';
        if (options._targetObject instanceof HTMLButtonElement) {
            action_text = options._targetObject.text;
        } else if (options._targetObject instanceof mini.Button) {
            action_text = options._targetObject.getText();
        }
        // 获取子页面标题
        var title = window.document.title;
        try {
            if (window.parent != window) {
                var selfIframe = window.parent.$("iframe").filter(function (index, iframe) {
                    // IE8不能用===
                    return iframe.contentWindow == window;
                });
                if (selfIframe.length > 0 && window.parent && window.parent.tabs instanceof window.parent.mini.Tabs) {
                    var tabs = window.parent.tabs;
                    for (var i = 0, max = tabs.getTabs().length; i < max; i += 1) {
                        if (selfIframe[0] === tabs.getTabIFrameEl(i)) {
                            title = tabs.getTab(i).title;
                            break;
                        }
                    }
                }
            }
        } catch (e) {
            console.error(e);
        }

        options.title = '【{0}】{1}'.format(title, action_text);
    }
    return mini.open(options);

}

/**
 * @author lgl 自适应弹窗窗体 如果子窗体初始尺寸大于计算出的最小尺寸则以子窗体初始尺寸为主()
 * @param win
 * @param relocation
 *            重新定位(居中)，默认true
 * @param relocation
 *            是否强制适配尺寸，默认false 使用miniopen函数中传入autoresize属性可开启自动调整子页面大小
 *            示例：miniopen({... autoresize:true ...});
 * @pram force 是否强制改变子窗体大小
 *
 * 子页面元素改变后和使用该函数，强制适配页面大小(最大不超过父容器视窗大小)
 * 在子页面中使用AutoResizePopupWindow(window.self, true, true);
 * @constructor
 */
function AutoResizePopupWindow(win, relocation, force) {
    var thisWindow = (win || this);
    if (null == thisWindow) {
        console.warn('找不到window对象,请检查');
        return false;
    }
    if (thisWindow === thisWindow.top) {
        console.debug('当前窗体非子窗体,无需自适应窗体大小');
    } else if (null == thisWindow.parent.mini) {
        console.debug('父窗体未引入mini,无法自适应窗体大小');
    } else {
        // 获取父窗体对象
        var selfIframe = thisWindow.parent.$("iframe").filter(function (index, iframe) {
            return iframe.contentWindow === thisWindow;
        });
        var iframeId = selfIframe.parents('div.mini-window').attr('id');
        var miniControl = thisWindow.parent.mini.get(iframeId);
        if (null != miniControl) {
            // 查找弹出窗体的body边框
            var controlBody = thisWindow.parent.$(miniControl.getEl()).find('div.mini-panel-viewport > div.mini-panel-body');

            // 处理页面尺寸的延时时间
            if (true === force) {
                controlBody.each(function (i, control) {
                    control.style.height = null;
                });
            }

            // mini-fit清除
            var _fit = thisWindow.$('body  .mini-fit');
            _fit.map(function (index, element) {
                element.style.height = null;
                thisWindow.$(element).removeClass('mini-fit');
            });
            // 计算页面尺寸
            var selfWidth = thisWindow.document.body.scrollWidth;
            var selfHeight = 0;
            thisWindow.$('body > *').map(function (index, element) {
                if ('none' !== element.style.display &&
                    !~['absolute', 'fixed'].indexOf(hmq.getComputedStyle(element, 'position')) &&
                    !~['left', 'right'].indexOf(hmq.getComputedStyle(element, 'float'))) {
                    return {width: element.scrollWidth, height: element.scrollHeight};
                } else {
                    return {width: 0, height: 0};
                }
            }).each(function (index, value) {
                selfHeight += value.height;
                selfWidth = (selfWidth > value.width ? selfWidth : value.width);
            });
            selfHeight = Math.max(selfHeight, thisWindow.document.body.scrollHeight);

            /**
             * 该部分代码抽离，为了在强制改变窗体大小时候能使用延时函数给DOM节点时间相应变化
             */
            var controlMarginAndPaddingWidth = controlBody.outerWidth() - controlBody.width() + 16;
            var controlMarginAndPaddingHeight = controlBody.outerHeight() - controlBody.height() + 8;
            selfWidth += controlMarginAndPaddingWidth;
            selfHeight += controlMarginAndPaddingHeight;
            // 通过父窗体可视区域尺寸限制弹窗最大尺寸
            var boxWidth = Math.min(thisWindow.parent.document.body.clientWidth, selfWidth);
            var boxHeight = Math.min(thisWindow.parent.document.body.clientHeight, (selfHeight + miniControl.getHeaderHeight() + miniControl.getFooterHeight()));
            // 如果窗体初始尺寸大于计算后的最小尺寸则无需动态改变子窗体大小
            var needChange = (boxWidth !== thisWindow.parent.document.body.clientWidth || boxHeight !== thisWindow.parent.document.body.clientHeight);

            // 通过父窗体对象设置尺寸
            if ((true === force) || needChange) {
                miniControl.setWidth(boxWidth);
                miniControl.setHeight(boxHeight);
            }
            // mini-fit归还
            _fit.map(function (index, element) {
                thisWindow.$(element).addClass('mini-fit')
            });
            // 重新计算尺寸
            thisWindow.mini && thisWindow.mini.layout();

            // 通过父窗体对象重新居中子窗体
            if ((false !== relocation) && ((true === force) || needChange)) {
                miniControl.showAtEl(thisWindow.parent.document.body, {
                    xAlign: 'center',
                    yAlign: 'middle'
                });
            }
        }
    }
}


/**
 * 设置form表单中控件的只读状态
 *
 * @param form
 *            form对象
 * @param obj
 *            需要处理的name集合
 * @param flag
 *            ：true 只读， false:可写
 * @returns undefinde
 */
function setFormReadOnly(form, obj, flag) {
    if (form instanceof mini.Form) {
        var array = [];
        if ('string' === typeof (obj)) {
            array = obj.split(",");
        } else if (Array.isArray(obj)) {
            array = obj;
        } else {
            $(form.getFields()).each(function (i, ctl) {
                ctl.setReadOnly(false !== flag);
            });
            return;
        }
        $(array).each(function (i, fieldName) {
            if (mini.isControl(form.getField(fieldName))) {
                form.getField(fieldName).setReadOnly(false !== flag);
            }
        });
    } else {
        console.error("Form is not mini.Form type ");
    }
}

/**
 * 设置form表单中控件的只读状态
 *
 * @param form
 *            form对象
 * @param obj
 *            需要处理的name集合
 * @param flag
 *            ：true 只读， false:可写
 * @returns undefinde
 */
function setFormEnabled(form, obj, flag) {
    if (form instanceof mini.Form) {
        var array = [];
        if ('string' === typeof (obj)) {
            array = obj.split(",");
        } else if (Array.isArray(obj)) {
            array = obj;
        } else {
            $(form.getFields()).each(function (i, ctl) {
                ctl.setReadOnly(false !== flag);
            });
            return;
        }
        $(array).each(function (i, fieldName) {
            if (mini.isControl(form.getField(fieldName))) {
                form.getField(fieldName).setEnabled(false !== flag);
            }
        });
    } else {
        console.error("Form is not mini.Form type ");
    }
}


/**
 * 设置id集合只读
 *
 * @param obj
 */
function setIdReadOnly(obj) {
    var array = [];
    if ('string' === typeof (obj)) {
        array = obj.split(",");
    } else if (Array.isArray(obj)) {
        array = obj;
    } else {
        console.error("Obj is not of type string or array");
        return false;
    }
    for (var i = 0; i < array.length; i++) {
        var ctl = mini.get(array[i]);
        if (mini.isControl(ctl)) {
            ctl.setReadOnly(true);
        }
    }
}

/**
 * 设置id不可编辑
 *
 * @param obj
 */
function setIdDisable(obj) {
    setIdEnable(obj, false);
}

/**
 * 设置id可编辑
 *
 * @param obj
 * @param flag
 */
function setIdEnable(obj, flag) {
    var array = [];
    if ('string' === typeof (obj)) {
        array = obj.split(",");
    } else if (Array.isArray(obj)) {
        array = obj;
    } else {
        console.error("Obj is not of type string or array");
        return false;
    }
    for (var i = 0; i < array.length; i++) {
        var ctl = mini.get(array[i]);
        if (mini.isControl(ctl)) {
            ctl.setEnabled(false !== flag);
        }
    }
}

/**
 *
 * @param form
 *            表单对象
 * @param obj {} {
 *            arr_names：name的表单控件数组
 *            flag：true：只清空包含name的表单控件，false：清空不包含name的表单控件 }
 * @returns
 */
function setEmpty(form, obj) {
    hmq.tipSuccess("保存成功");
    // 获取表单所有的对象
    var form_data = form.getFields();
    if (obj.flag && Array.isArray(obj.include)) {
        // 清空包含传递过来的控件值
        for (var i = 0; i < obj.include.length; i++) {
            var temp = obj.include[i];
            form.getField(temp).setValue(null);
            // 文本值处理
            if (form.getField(temp).hasOwnProperty("text"))
                form.getField(temp).setText("");
        }

    } else {
        // 过滤包含的数据
        for (var j = 0; j < form_data.length; j++) {
            var temp = form_data[j];
            // 隐藏属性跳过,针对action 或id
            var visible_flag = temp.el.type;
            if (visible_flag === "hidden") continue;
            // 过滤传递过滤的form表单的name
            var isflag = obj.include.includes(temp.getName());
            if (!isflag) {
                form.getField(temp.getName()).setValue(null);
                if (form.getField(temp.getName()).hasOwnProperty("text"))
                    form.getField(temp.getName()).setText("");
            }
        }

    }

}

/**
 * 新增成功后，不关闭页面 ，多次新增，点击关闭按钮刷新父页面
 *
 * @param action
 *            null:默认为close refresh:执行父窗体search_optionhuan
 *            null:不传入参数，默认通过页面中的PAGE_SAVE_FLAG变量设定action
 * @param ischange
 *            null:不传入参数，默认通过页面中的form表单的变动判断页面中的数据是否有变动
 *            true:页面有变动，弹出提示框，提示用户是否关闭窗体 false:不管页面是否存在变动，直接关闭窗体
 * @returns
 */
function CloseWindow(action, ischange) {
    var options = {
        action: ('undefined' != typeof (PAGE_SAVE_FLAG) && PAGE_SAVE_FLAG ? 'refresh' : (action || 'close')),
        ischange: null == ischange ? ('undefined' != typeof (form) && form.isChanged()) : ischange
    };
    if (options.ischange) {
        mini.confirm("数据被修改是否保存?", "提示", function (res) {
            if (res === "ok") {
                // do nothing
            } else {
                _CloseAction_(options.action);
            }
        });
    } else {
        _CloseAction_(options.action);
    }

    function _CloseAction_(action) {
        if (window.CloseOwnerWindow) {
            return window.CloseOwnerWindow(action);
        } else {
            window.close();
        }
    }
}

/** *************************************加载菜单按钮*************************************** */
$(document).ready(function () {
    var mid = hmq.getQueryString("menu_id");
    if (null == mid || '' === mid) {
        // 获取不到菜单ID
    } else {

        initButtonBar();
        var $el = $('[id="btnbar"]');
        if ($el.length <= 0) {
            console.warn('找不到菜单容器');
        } else {
            loadButton($el, mid);
        }
    }

    /**
     * 通过DataGird控件上data-pagebuttonbar属性初始化pager控件中的按钮区域
     */
    function initButtonBar() {
        try {
            $('[data-pagebuttonbar="true"]').each(function (index, datagrid) {
                var Mdatagrid = mini.get(datagrid);
                if (Mdatagrid instanceof mini.DataGrid || Mdatagrid instanceof mini.TreeGrid) {
                    var pager = $(Mdatagrid.getBottomPager().getEl());
                    var pageLeft = pager.children("div.mini-pager-left");
                    pageLeft.css('width', '100%');
                    var pageTable = pageLeft.children("table");
                    pageTable.css('width', '100%').css('float', 'none');
                    var pageTableFirstRow = pageTable[0].rows[0];
                    // 调整页码信息区块位置
                    var pageInfo = pager.children('div.mini-pager-right');
                    if (pageInfo.length > 0) {
                        pageTableFirstRow.insertCell();
                        pageInfo.appendTo($(pageTableFirstRow.cells[pageTableFirstRow.cells.length - 1]));
                    }
                    // 增加按钮区块
                    pageTableFirstRow.insertCell();
                    $(pageTableFirstRow.cells[pageTableFirstRow.cells.length - 1]).append('<div id="btnbar" style="text-align: right" >按钮加载中...</div>');
                }
            });
        } catch (e) {
            console.error(e);
        }
    }

    /**
     * 通过ajax请求加载当前页面按钮
     *
     * @param $el
     *            按钮父容器
     * @param mid
     *            菜单ID
     */
    function loadButton($el, mid) {
        $.get(hmq.getPath("/system/button/{0}".format(mid)), function (res) {
            if (200 === res.code) {
                var html = "";
                for (var i = 0; i < res.data.length; i++) {
                    //html += "<a class='mini-button'   style='margin-right:2px;' id='{id}'  onclick='{trigger}' iconCls='{iconCls}' data-url='{url}' data-action='{resource}' >{text}</a>".format(res.data[i]);
                    //防止js注入
                    var a = $('<a class="mini-button" style="margin-right:2px;"></a>');
                    html += a.attr('id', res.data[i]['id'])
                        .attr('onclick', res.data[i]['trigger'])
                        .attr('iconCls', res.data[i]['iconCls'])
                        .attr('data-url', res.data[i]['url'])
                        .attr('data-action', res.data[i]['resource'])
                        .text(res.data[i]['text'])[0].outerHTML;
                }
                $el.html(html);
                $el.each(function (index, el) {
                    // 指定渲染dom
                    $(window).ready(function () {
                        mini.parse(el);
                    });
                });
                // 执行页面的查询方法search_option
                initPageSearchOption(res);
                // 触发按钮加载完成事件
                $(window).trigger('loadedbutton');
            } else {
                $el.text('按钮加载失败');
                hmq.tipDanger("按钮加载失败");
            }
        });
    }

    /**
     * 调用页面的search_option方法，
     */
    function initPageSearchOption(res) {
        try {
            if ("false" !== $(document.body).attr("autoload") && typeof search_option === "function") { // 是函数其中
                // FunName
                // 为函数名称
                setTimeout(search_option, 500);
            } else { // 不是函数
                console.debug("search_option is not function,Cannot query automatically");
            }
        } catch (e) {
        }
    }
});
/**
 * *************************************END
 * 加载菜单按钮***************************************
 */

/** *************************************自执行函数*************************************** */
/**
 * 初始化表格布局
 * 默认会处理“class="hmq-modify-table"”的table元素，会依据表格中的最大数据列定义表格最小宽度
 * 但是如果table属性中已经存在min-width属性则不会覆盖。
 */
(function () {
    'use strict';
    var initTable = function () {
        // 禁止重复执行;
        initTable = function () {
        };
        /**
         * 修改table节点minWidth属性
         * @param minWidth
         * @private
         */
        var setMinWidthImportant = function (table, minWidth) {
            table.style.removeProperty('min-width');
            table.style.setProperty('min-width', minWidth, 'important');
        };

        var WIDTH = {
            // 标题宽度
            title_width: '85px',
            // 内容列宽度
            content_min_width: '100px',
            // 控件默认宽度
            control_width: '100%',
            // 列平均宽度(用于计算table最小宽度)
            col_avg_width: 120
        };
        $('table.hmq-modify-table').each(function (index, table) {
            var collength = 0;
            $(table.rows).each(function (index, row) {  // 获取列数
                var _length = 0;
                $(row.cells).each(function (iindex, cell) {
                    _length += cell.colSpan;
                });
                collength = collength < _length ? _length : collength;
            });
            if (collength > 0) {
                var $table = $(table);
                table.style.width = null;
                var oldMaxWidth = parseInt(hmq.getComputedStyle(table, 'min-width'));
                if (isNaN(oldMaxWidth) || 0 === oldMaxWidth) {
                    var key_mini_width = "_minWidth_";
                    var autoMinWidth = (collength * WIDTH.col_avg_width);
                    $table.data(key_mini_width, autoMinWidth);
                    setMinWidthImportant(table, autoMinWidth + 'px');

                    //该处监听页面尺寸变化，保证table的最小宽度不会超出页面宽度
                    /**
                     * 自动调整table最小宽度
                     * @param e
                     */
                    var autoResizeTableMinWidth = hmq.debounce(function (e) {
                        if (!isNaN(parseInt($table.data(key_mini_width)))) {
                            var _minWidth_ = $table.data(key_mini_width);
                            var currentMaxWidth = parseInt(hmq.getComputedStyle(table, 'min-width'));
                            if (document.body.clientWidth < currentMaxWidth) {
                                setMinWidthImportant(table, '100%');
                            } else {
                                setMinWidthImportant(table, _minWidth_ + 'px');
                            }
                        }
                    }, 200);
                    hmq.addEvents(window, "resize", autoResizeTableMinWidth);
                }
                // 列宽
                var colgroup = $('<COLGROUP>');
                for (var i = 0; i < collength; i += 1) {
                    var col = $('<COL>').css((i % 2 === 0) ? 'width' : 'min-width', (i % 2 === 0) ? WIDTH.title_width : WIDTH.content_min_width);
                    colgroup.append(col);
                }
                table.insertAdjacentElement('afterBegin', colgroup[0]);
            }
        });
        // css选择器-控件宽度
        var mini_control_cls = [
            'table.hmq-modify-table > tr > td > input[class*="mini-"]',
            'table.hmq-modify-table > tr > td > select[class*="mini-"]',
            'table.hmq-modify-table > tbody > tr > td > input[class*="mini-"]',
            'table.hmq-modify-table > tbody > tr > td > select[class*="mini-"]'
        ];
        // css选择器-需要排除的控件
        var exclude_control = $(['mini-checkbox', 'mini-fileupload']);
        $(mini_control_cls.join(',')).each(function (index, element, item) {
            if (null == element.getAttribute("width") &&
                exclude_control.filter(function (index, clsName) {
                    return $(element).hasClass(clsName);
                }).length <= 0) {
                $(element).css('width', WIDTH.control_width);
            }
        });
    };
    //TODO:该处需要将事件提取出来,触发自定义事件（LGL）
    if ('undefined' !== typeof (window.mini)) {
        // 编写mini.parsed属性，用于监听mini.parse()方法
        if (null == window.ActiveXObject) {
            // 非IE环境
            mini._parsed = false;
            Object.defineProperty(mini, "parsed", {
                configurable: false,
                // writable: true,
                enumerable: true,
                set: function (value) {
                    if (true !== mini._parsed) {
                        initTable();
                    }
                    mini._parsed = value;
                },
                get: function () {
                    return mini._parsed;
                }
            });
        } else {
            // IE环境
            var __parse = mini.parse;
            mini.parse = function () {
                try {
                    initTable();
                } catch (e) {
                    console.error(e);
                }
                return __parse.apply(this, arguments);
            }
        }
    } else {
        console.warn(new Error("mini框架尚未加载，initTable初始化失败,请将该js文件移动至boot.js后!"));
    }
})(document);


/**
 * 初始化页面查询时间
 */
(function () {
    'use strict';
    hmq.addEvents(window, "load", function () {
        if (window['mini'] && "false" !== document.body.hasAttribute("init-search-date")) {
            var monthFirst = mini.formatDate(new Date(), 'yyyy/MM/01');
            var now = mini.formatDate(new Date(), 'yyyy/MM/dd HH:mm:ss');
            var times = {
                srchRq1: monthFirst,
                srchRq2: now,
            };
            $(mini.getChildControls(document)).each(function (index, control) {
                if (mini.ToolBar.prototype.type === control.type) {
                    $($(mini.getChildControls(control)).filter(function (i, c) {
                        return mini.DatePicker.prototype.type === c.type;
                    })).each(function (index, datePicker) {
                        var defaultValue = times[datePicker.id] || times[datePicker.name];
                        if (null != defaultValue && "" !== defaultValue) {
                            datePicker.setValue(defaultValue);
                        }
                    });
                }
            });
        }
    })
})();
/**
 * 默认图片自动切换
 *
 * @param document
 * @param own
 */
(function (window, own) {
    'use strict';
    own.addEvent = function (elm, evType, fn, useCapture) {
        if (elm == null || !(elm instanceof HTMLDocument || elm instanceof HTMLElement) || elm instanceof Window) {
            return false;
        }
        if (typeof (elm.addEventListener) == "function") {
            elm.addEventListener(evType, fn, useCapture); // DOM2.0
            return true;
        } else if (typeof (elm.attachEvent) == "function") {
            var r = elm.attachEvent('on' + evType, fn); // IE5+
            return r;
        } else {
            elm["on" + evType] = fn; // DOM 0
        }
    };
    var max_loop = 500;
    var _interval_ = null;
    _interval_ = setInterval(function () {
        if (typeof (layui) != "undefined") {
            clearInterval(_interval_);
            // 默认图片
            own.addEvent(window, "error", function (event) {
                var $$ = event.target;
                var default_img = $$.getAttribute("default-img");
                if (default_img != null) {
                    var tmp = new Image();
                    tmp.onerror = function () {
                        return false;
                    };
                    tmp.onload = function () {
                        $$.src = this.src;
                    };
                    tmp.src = default_img;
                }
            }, true);
        } else if (--max_loop <= 0) {
            clearInterval(_interval_);
        }
    }, 4);

})(window, {});

/**
 * *************************************END* 自执行函数****************************************/

/**
 * *************************************
 * map***************************************
 */
function Maps() {
    var struct = function (key, value) {
        this.key = key;
        this.value = value;
    };

    var put = function (key, value) {
        for (var i = 0; i < this.arr.length; i++) {
            if (this.arr[i].key === key) {
                this.arr[i].value = value;
                return;
            }
        }
        this.arr[this.arr.length] = new struct(key, value);
    };

    var get = function (key) {
        for (var i = 0; i < this.arr.length; i++) {
            if (this.arr[i].key === key) {
                return this.arr[i].value;
            }
        }
        return null;
    };

    var remove = function (key) {
        var v;
        for (var i = 0; i < this.arr.length; i++) {
            v = this.arr.pop();
            if (v.key === key) {
                continue;
            }
            this.arr.unshift(v);
        }
    };

    var size = function () {
        return this.arr.length;
    };

    var isEmpty = function () {
        return this.arr.length <= 0;
    };
    this.arr = [];
    this.get = get;
    this.put = put;
    this.remove = remove;
    this.size = size;
    this.isEmpty = isEmpty;
}

/**
 * *************************************END
 * map***************************************
 */
function getMiniValue(key, defaultValue) {
    if (key == null || key === "") {
        return defaultValue;
    }
    return key;
}

function getMiniDate(key) {
    return mini.formatDate(new Date(key), "yyyy-MM-dd");
}

// 判读是否为空
function isNull(key) {
    return key == null || key === "";

}


// 清空
function clearValue(obj) {
    var array = obj.split(",");
    for (var i = 0; i < array.length; i++) {
        mini.get(array[i]).setValue("");
    }
}

/**
 * 用于清空下拉框，弹出框的值
 *
 * @param e
 */
function onCloseClickClearValue(e) {
    if (null != e && mini.isControl(e.sender)) {
        e.sender.setValue('');
        if ('undefined' !== typeof (e.sender.setText)) {
            e.sender.setText('');
        }
        if ('function' === typeof (e.sender.doValueChange)) {
            e.sender.doValueChange();
        }
        if ('function' === typeof (e.sender.hidePopup)) {
            e.sender.hidePopup();
        }
        return true;
    }
    return false;
}

/**
 * 用于树形选择器 禁止选中父级节点
 *
 * @param e
 */
function disableParentSelection(e) {
    if (null != e && mini.isControl(e.sender)) {
        if ('undefined' !== typeof (e.isLeaf) && 'undefined' !== typeof (e.cancel)) {
            if (e.isLeaf === false) {
                e.cancel = true;
            }
        }
    }
}


/** * ***************开始********************* */
// 动态表单
var dynamicForm = (function () {
    var initGrid = null;

    function init(grid) {
        initGrid = grid.getColumns();
    }

    return {
        initGrid: function () {
            init(grid)
        },
        dynamicForm: function (options) {
            var defaults = {
                grid: null,// 表格对象
                index: null,// 插入位置 从1开始
                classify: null,// 分类
                width: 120,// 宽度
                headerAlign: "center",
                align: "center",
            };
            var opts = $.extend(defaults, options);
            var myColumns = new Array(), dataColumns = new Array(), columns = initGrid;
            if (opts.classify == null || opts.classify == "") {
                console.error("请传入分类");
                return;
            }
            if (opts.index == null) {
                opts.index = columns.length;
            }
            if (opts.index == 0) {
                opts.index = 1;
            }
            if (columns.length < opts.index) {
                console.error("插入位置大于表格总列");
                return;
            }
            $.get("/platform/form/getParams?type=" + opts.classify, function (res) {
                if (200 === res.code) {
                    for (var i = 0; i < res.data.length; i++) {
                        var datajson = res.data[i];
                        dataColumns.push({field: datajson.parameter, header: datajson.parameterName, width: opts.width, headerAlign: opts.headerAlign, align: opts.align})
                    }
                    for (var i = 0; i < columns.length; i++) {
                        myColumns.push(columns[i])
                        if ((i + 1) == opts.index) {
                            for (var j = 0; j < dataColumns.length; j++) {
                                myColumns.push(dataColumns[j])
                            }
                        }
                    }
                    grid.set({
                        columns: myColumns
                    });
                }
            });
        }
    }
})();
/** * ***************结束********************* */
document.onkeydown = function (e) {
    //兼容各个版本浏览器的支持
    var event = e || window.event;
    var key = event.keyCode || event.charCode || event.which;
    if (key == 13) search_option();
};
/** ****************动态生成列静态对象Head*********************_by_wx_*/

var initializeGridColumn = {
    //剔除空白行
    readSetting: function (columns) {
        var setting = [];
        $(columns).each(function (index, column) {
            if (column.columns && column.columns.length > 0) {
                setting = setting.concat(initializeGridColumn.readSetting(column.columns));
            } else {
                setting.push({
                    currencyUnit: column.currencyUnit,
                    dateFormat: column.dateFormat,
                    numberFormat: column.numberFormat,
                    align: column.align,
                    displayField: column.displayField,
                    sortField: column.sortField,
                    header: column.header,
                    field: column.field,
                    width: column.width,
                    visible: column.visible,
                    hideable: column.hideable
                });
            }
        });
        //隐藏右击菜单中的空白列
        if (setting[(setting.length - 1)].width == "100%") {
            setting[(setting.length - 1)]["hideable"] = true;
        }
        return setting;
    },
    //核心操作方法
    init: function () {
        $.get("/system/right/right.html/{0}".format(hmq.getQueryString("menu_id")), function (res) {
            if (200 === res.code) {
                var timer = "";
                if (res.data.rightType == "MENU") {
                    var grids = $(mini.getChildControls(document.body)).filter(function (index, control) {
                        if (!!~['datagrid', "treegrid"].indexOf(control.type)) {
                            return true;
                        }
                    }).toArray();
                    var userFlag = false;
                    $.get("/grid/dynamic/column/whetherTheAdministrator", function (res) {
                        if (res.code == 200) {
                            userFlag = res.data;
                        }
                    }, null, null, {async: false});//判断是否是管理员|管理员加操作
                    /** ****************第一部分Start(数据清除|绑定标题触发事件)**********************/
                    var CheckGridId = [];
                    for (var i = 0, finalSize = grids.length; i < finalSize; i++) {
                        var gridData = grids[i].getData();
                        grids[i].clearData();

                        //////事件Start
                        grids[i].on("ColumnsChanged", function (e) {
                            if (timer == "isToModify") {
                                var dataPack = {
                                    gridId: e.sender.id,
                                    menuId: hmq.getQueryString("menu_id"),
                                    rowsData: JSON.stringify(initializeGridColumn.readSetting(e.sender.getColumns()))
                                };
                                $.post("/grid/dynamic/column/currentSavaAndUpdate", JSON.stringify(dataPack), function (res) {
                                    if (200 === res.code) {
                                        console.count("动态更新");
                                    }
                                }, null, {contentType: 'application/json'});
                            }
                        });
                        //////事件End
                        if (userFlag) {
                            var touchtime = new Date().getTime();
                            grids[i].on('headercellclick', function (e) {
                                if (new Date().getTime() - touchtime < 400) {
                                    mini.open({
                                        url: "/system/main/managerfield.html", title: "管理员调整配置",
                                        width: 600, height: 350,
                                        onload: function () {
                                            var iframe = this.getIFrameEl();
                                            iframe.contentWindow.SetData({data: e, menuId: hmq.getQueryString("menu_id")});
                                        }
                                    });
                                } else {
                                    touchtime = new Date().getTime();
                                    console.log("单击事件");
                                }
                            });
                        }//通过时间差模拟双击

                        CheckGridId.push(grids[i].id);
                        grids[i].setData(gridData);
                    }
                    $['delete']("/grid/dynamic/column/DeleteIn", JSON.stringify({MenuId: hmq.getQueryString("menu_id"), RowIds: CheckGridId}), $.noop(), null, {contentType: 'application/json'});

                    /******************第二部分Start(列宽渲染以及性能考虑)**********************/
                        //查出数据库中配置的数据 |从数据库中进行匹配当前Grid只需一次请求
                    var griddaquan = [];
                    for (var i = 0, finalSize = grids.length; i < finalSize; i++) {
                        griddaquan.push({id: grids[i].id});
                    }
                    $.post("/grid/dynamic/column/currentAll", JSON.stringify({menuId: hmq.getQueryString("menu_id"), gridsItem: griddaquan}), function (res) {
                        if (200 === res.code) {

                            //多个Grid表格双重循环判断需要修改那个Grid列宽
                            for (var t = 0, finalSiz = res.data.length; t < finalSiz; t++) {
                                for (var i = 0, finalSize = grids.length; i < finalSize; i++) {
                                    var grid = grids[i];
                                    var finalGrId = res.data[t].rowId.split("@")[1];
                                    if (grids[i].id == finalGrId) {
                                        var final = (JSON.parse(res.data[t].rowsData));
                                        $(final).each(function (i, setting_column) {
                                            grid.updateColumn(grid.getColumn(i), setting_column);
                                        });
                                    }
                                }
                            }


                        }
                    }, null, {contentType: 'application/json'});
                    setTimeout(function () {
                        timer = "isToModify";
                    }, 1500);
                }
            }
        });
    }
}


/*** ****************动态生成列静态对象Foot********************* **/
!(function () {//公共自加载函数|后续可填写需要自加载的函数
    setTimeout(function () {
        if (hmq.isMiniContext()) {
            if(null == mini.Cookie.get("INITIALIZEGRIDCOLUMN_DISABLE")){
                initializeGridColumn.init();
            }
        }
    }, 500);
})();


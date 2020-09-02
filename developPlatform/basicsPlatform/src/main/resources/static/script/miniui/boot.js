﻿var bootPATH = __CreateJSPath("boot.js");

mini_debugger = true; //

var skin = getCookie("miniuiSkin") || 'bootstrap';             //skin cookie   cupertino
var mode = getCookie("miniuiMode") || 'medium';                 //mode cookie     medium

//miniui（不要使用jquery2.0以上版本，否则不兼容IE8）
document.write('<script src="' + bootPATH + 'jquery.min.js?v=1.9.0" type="text/javascript"></sc' + 'ript>');
document.write('<script src="' + bootPATH + 'miniui.min.js?v=3.9.2" type="text/javascript" ></sc' + 'ript>');
//miniui自定义“插件”
document.write('<script src="' + bootPATH + 'res/mini.plugins.copyexcel.js?v=1.0.0" type="text/javascript" async ></sc' + 'ript>');
document.write('<script src="' + bootPATH + 'res/mini.plugins.js?v=1.0.0" type="text/javascript" ></sc' + 'ript>');

document.write('<link href="' + bootPATH + '../../fonts/albbFont/iconfont.css?v=1.0.0" rel="stylesheet" type="text/css" />');
document.write('<link href="' + bootPATH + 'themes/default/miniui.css?v=3.9.2" rel="stylesheet" type="text/css" />');


//skin
if (skin && skin != "default") {
    document.write('<link href="' + bootPATH + 'themes/' + skin + '/skin.css?v=3.9.2" rel="stylesheet" type="text/css" />');

}
//mode
if (mode && mode != "default") {
    document.write('<link href="' + bootPATH + 'themes/default/' + mode + '-mode.css" rel="stylesheet" type="text/css" />');

}
//icon
document.write('<link href="' + bootPATH + 'themes/icons.css" rel="stylesheet" type="text/css" />');

////////////////////////////////////////////////////////////////////////////////////////
function getCookie(sName) {
    var aCookie = document.cookie.split("; ");
    var lastMatch = null;
    for (var i = 0; i < aCookie.length; i++) {
        var aCrumb = aCookie[i].split("=");
        if (sName == aCrumb[0]) {
            lastMatch = aCrumb;
        }
    }
    if (lastMatch) {
        var v = lastMatch[1];
        if (v === undefined) return v;
        return unescape(v);
    }
    return null;
}

function __CreateJSPath(js) {
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
    return path;
}


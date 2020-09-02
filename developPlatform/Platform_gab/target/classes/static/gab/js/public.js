var flag = true;
var r = window.location.href;
$(function () {

	// 搜索框自动联想
	var data = [
		{ "value": "one", "label": "夹具1" },
		{ "value": "two", "label": "夹具2" },
		{ "value": "three", "label": "夹具3" }
	];
	if ($('.autoCompleted').length != 0) {
		$('.autoCompleted').autocompleter({ source: data });
	}

	/**
	 * 全局搜索
	 * 1、点击搜索按钮搜索
	 * 2、回车键搜索
	 */
	$('.allSearch').click(function (e) {
		window.location.href = '/gab/search.html?keyward='+$('.keyword').val();
	});
	$('.allSearch').keypress(function (evt) {
		var e = evt || window.event;
		debugger;
		if (e.keyCode == 13) {
			window.location.href = '/gab/search.html'
		}
	});



	// 收藏
	var layer;
	layui.use('layer', function () {
		layer = layui.layer;
	});
	$.ajax({
		url: '/platform/need/getSrchAllCollection',
		type: 'post',
		dataType: 'json',
		contentType: 'application/json',
		async: false,
		success: function (res) {
			if (res.code == 200) {
				var allCollect = res.data;
				$.each(allCollect, function (i, e) {
					if (e.collect_url == r) {
						flag = false;
						$('.collect i').removeClass('icon-xingxing');
						$('.collect i').addClass('icon-xingxing-');
						$('.collect b').text('已收藏');
					}
				})
			}
		}
	});


	$('.collect').click(function () {
		verifyLogin({
			type: 'func'
		}, collect);
	});

	// 右边工具栏
	$('.tips').hide();
	$('.chat').mouseenter(function () {
		$('.tips').show();
		$('.tips').mouseleave(function () {
			$('.tips').hide();
		});
	});
	// .mouseleave(function () {
	// $('.tips').hide();
	// });

	$('.toTop').click(function () {
		// document.body.scrollTop = document.documentElement.scrollTop = 0;
		$('html,body').animate({
			scrollTop: 0
		}, 500)
	});

	$('.choice_firm').mouseenter(function () {
		$(this).find('.choice').css('display', 'block');
	}).mouseleave(function () {
		$(this).find('.choice').css('display', 'none');
	});

	// 登录后，显示用户名
	$.ajax({
		url: '/login/getUser',
		type: 'get',
		dataType: 'json',
		success: function (res) {
			if (res.code == 200) {
				$('.yesLogin b').html(res.data.sysUser.userId);
				// $('.yesLogin b').text(res.data.sysUser.nickName);
				$('.noLogin').css('display', 'none');
				$('.yesLogin').css('display', 'inline-block');
			}
		}
	});

	$('.yesLogin a:last').css('color', "#e80000");
});


// 退出登录
/**
	* 注销
	**/

function loginOut() {
	layui.use('layer', function () {
		layer.open({
			content: '确定退出吗？',
			btn: ['确定', '取消'],
			area: '200px',
			skin: 'layer-confirm',
			yes: function (index, layero) {
				$.ajax({
					url: '/login/quit',
					success: function (res) {
						window.location.href = "/gab/index.html";
						$('.noLogin').css('display', 'inline-block');
						$('.yesLogin').css('display', 'none');
					},complete:function(){
						layer.close(index);
					}
				});
			},
			btn2: function (index, layero) {
				// 取消的回调
			},
			cancel: function () {
				// 右上角关闭回调
				// return false //开启该代码可禁止点击该按钮关闭
			}
		});
	});
}

function GetFlag(data) {
	var index1 = layer.load();
	if (data == 'true') {
		layer.close(index1);
		$('.collect i').removeClass('icon-xingxing');
		$('.collect i').addClass('icon-xingxing-');
		$('.collect b').text('已收藏');
		layer.msg('收藏成功', {
			skin: 'msg-tips'
		});
		flag = false;
	}
}

// 收藏
function collect() {
	var index1 = layer.load();
	var _type = $('h2').text();

	if (_type.indexOf('专家') > -1) {
		_type = "专家团队"
	}
	if (_type.indexOf('产品') > -1) {
		_type = "产品"
	}
	if (_type == '') {
		_type = "供应厂商"
	}
	var collectType = _type;
	if (flag) {
		layer.open({
			type: 2,
			closeBtn: 1,
			area: ['560px', '345px'],// 宽，高
			shadeClose: false,
			title: ['填写收藏信息', 'font-size:16px;color:#fff;background:rgb(46,141,237);'],
			content: ['./collect.html', 'no'],
			success: function (layero, index) {
				var body = layer.getChildFrame('body', index);// 少了这个是不能从父页面向子页面传值的
				// 获取子页面的元素，进行数据渲染
				body.contents().find("#collectType").val(collectType);
				var iframe = window['layui-layer-iframe' + index];
				iframe.getUrl(r);
			},complete:function(){
				layer.close(index1);
			}
		});
	} else {
		$.ajax({
			url: '/platform/need/deleteByNote',
			type: 'post',
			dataType: 'json',
			data: r,
			contentType: 'application/json',
			async: false,
			success: function (res) {
				if (res.code == 200) {
					$('.collect i').removeClass('icon-xingxing-');
					$('.collect i').addClass('icon-xingxing');
					$('.collect b').text('我要收藏');
					layer.msg('已取消收藏', {
						skin: 'msg-tips'
					});
					flag = true;
				}
			},complete:function(){
				layer.close(index1);
			}
		})

	}
}

/**
 * sys_param获取值
 * 
 * @param dom
 *            元素id属性
 * @param paramMod
 *            paramMod字段值
 * @param paramType
 *            paramType字段值
 * @param parentId
 *            parentId字段值
 * @returns dom不为空?对dom节点添加option标签并赋值 : 返回参数集合
 */
function getSysParams(dom, paramMod, paramType, parentId) {
	var url = "";

	if (!!paramMod && !!paramType && !!parentId) {
		url = "/system/parameter/getParamsSelectListByParentId/" + paramMod + "/" + paramType + "/" + parentId;
	} else if (paramMod == null || paramMod == "") {
		url = "/system/parameter/getParamsSelectListByTypeParent/" + paramType + "/" + parentId;
	}
	var returned = $.ajax({
		url: url,
		type: 'GET',
		async: false,
		success: function (res) {
			if (!!dom) {
				dom = "#" + dom;
				$(dom).empty();
				$(dom).append("<option value='' selected:disabled style='diaplay: none'>请选择</option>");
				for (var i = 1; i <= res.length; i++) {

					$(dom).append("<option value='" + res[i - 1].id + "'>" + res[i - 1].text + "</option>");
				}
			} else {
				return res;
			}
		}
	});
	return returned.responseJSON;
}






/**
 * 登录js验证
 * 
 * @param type
 *            登录后进行的操作类型
 * @param val
 *            携带值
 * @returns
 */
function verifyLogin(options, callback) {
	console.debug("进入登录验证");
	var defaults = {
		type: "default",
		url: '',// 不传默认跳转首页
	};
	var opts = $.extend(defaults, options);
	if (opts.type == null || opts.type == "") {
		console.error("请传入操作类型");
		return;
	}
	$.ajax({
		url: "/login/verifyLogin",
		type: 'POST',
		async: false,
		success: function (res) {
			if (res) {
				switch (opts.type) {
					case "default":// 成功后直接跳转页面
						console.info("默认不做操作");
						break;
					case "func":// 成功后调用回调函数
						callback();
						break;
					case "url":// 成功后直接跳转页面
						window.top.location.href = opts.url;
						break;
					default:
						console.info("没有匹配的操作类型");
						return;
				}
			} else {
				layui.use('layer', function () {
					layer.open({
						content: '您还未登录，请先登录',
						btn: ['确定', '取消'],
						area: '200px',
						skin: 'layer-confirm',
						yes: function (index, layero) {
							// 确定的回调
							var url = "../gabLogin";
							if (opts.type == "url") {
								url += "?url=" + opts.url;
							}
							window.location.href = url;
						},
						btn2: function (index, layero) {
							// 取消的回调
						},
						cancel: function () {
							// 右上角关闭回调
							// return false //开启该代码可禁止点击该按钮关闭
						}
					});
				});
			}
		}
	});
}

/**
 * 默认图片自动切换
 * 
 * @param document
 * @param own
 */
(function (window, own) {
	'use strict';
	own.addEvent = function (elm, evType, fn, useCapture) {
		if (elm == null || !(elm instanceof HTMLDocument || elm instanceof HTMLElement) || elm instanceof  Window) {
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

})(window, ____init____ = {});
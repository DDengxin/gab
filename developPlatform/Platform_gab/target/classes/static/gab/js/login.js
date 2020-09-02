
    var $document = $(document);
    var $VerificationCode = $(".verification-code");
    var $mainCover = $('#main-cover');
    var $btnLogin = $('.btn-login');
    var $btnTar = $('.tar-btn');
    var $allInput = $('input[type="text"]');
    var $captcha = $('a.captcha');
    var $rember = $('#remember');
    //当前登录入口所在表单
    var $currentLoginForm = null;
    
	
    //手动触发
    $(window).on('load', function () {
    	//表单切换
        $("#tablist li").each(function (index) {
	        $(this).click(function () {
	            $(".contentin").removeClass("contentin");
	            $(".tabin").removeClass("tabin");
	            $(".content-box>div").eq(index).addClass("contentin");
	            $(this).addClass("tabin");
	            $currentLoginForm= $(".content-box>div>form").eq(index);
	        });
	    });
        $currentLoginForm= $(".content-box>div>form").eq(0);
        $VerificationCode.trigger('click');
        $currentLoginForm.find('input[name="username"]').focus()
    });

    //验证码切换
    $VerificationCode.on('click', function (e) {
        if (e.target) {
            e.target.src = "/login/captcha?" + Math.random();
        }
    });

    //验证码点击
    $captcha.on('click', function (e) {
        var $target = $(e.target);
        var $phone = $currentLoginForm.find('input[name=username]');
        if ('' == $phone.val()) {
            toast($phone.attr('placeholder'), 'warning');
        } else {
            $.ajax({
                url: "login/captcha",
                data: {
                    mobile: $phone.val()
                },
                type: "post",
                cache: false,
                dataType: "json",
                beforeSend: function () {
                    $target.attr("disabled", true);
                },
                success: function (res) {
                    if (200 === res.code) {
                        //倒计时
                        $target.data("text", $target.text());
                        var timeout = 60;
                        var _interval_ = setInterval(function () {
                            $target.text(timeout + 's');
                            if ((--timeout) <= 0) {
                                clearInterval(_interval_);
                                $target.text($target.data('text'));
                                $target.attr("disabled", false);
                            }
                        }, 1000);
                    } else {
                        $target.attr("disabled", false);
                    }
                    toast(res.msg, (200 === res.code) ? 'success' : 'danger');
                },
                error: function () {
                    toast("网络操作请求失败!", 'danger');
                    $target.attr("disabled", false);
                }
            });
        }
    });

    //登录按钮监听
    $btnLogin.on('click', function (e) {
        loginHandler();
    });

    //切换Tab页面
    $btnTar.on('click', function (e) {
        $btnTar.each(function (i, dom) {
            var page = $($(dom).attr('link'));
            page.css('display', e.target == dom ? 'block' : 'none');
            if (e.target == dom) {
                $currentLoginForm = page.parent('form');
            }
        });
        $(this).parent().addClass('active');
        $(this).parent().siblings().removeClass("active");
        return false;
    });

    $document.ready(function (e) {
        //显示第一个登录入口
        $($btnTar[0]).trigger('click');
        //读取cookie
        remberInfo('r');
        //验证码加载
        $VerificationCode.trigger('click');
        //回车事件监听
        $document.on('keydown', function (e) {
            if (e.keyCode == 13) {
                loginHandler();
            }
        });
        //初始化图片轮播
        /* initCoverWhell(); */

        //兼容IEplaceholder属性
        if (IEVersion() < 11) {
            $allInput.on('focus', function (e) {
                var $target = $(e.target);
                if ($target.val() == $target.attr('placeholder')) {
                    $target.val(null);
                    $target.css('color', 'auto');
                }
            });
            $allInput.on('blur', function (e) {
                var $target = $(e.target);
                if ('' == $target.val()) {
                    $target.val($target.attr('placeholder'));
                    $target.css('color', '#666');
                }
            });
        }
    });

    /**
     * 登录事件
     */
    function loginHandler() {
        if (null == $currentLoginForm) {
            console && console.error('login form is null');
            return false;
        } else {
            var username = $currentLoginForm.find("[name=username]").val();
            var password = $currentLoginForm.find("[name=password]").val();
            var randcode = $currentLoginForm.find("[name=randcode]").val();
            password = hex_md5(String(password));
            $.ajax({
                url: "/login/checkout",
                data: {
                    type: $currentLoginForm.attr("id"),
                    username: username,
                    password: password,
                    randcode: randcode
                },
                type: "post",
                dataType: "json",
                beforeSend: function () {
                    $btnLogin.attr("disabled", true);
                },
                success: function (res) {
                    if (res.code && res.code == '200') {
                        window.top.location.href = "/gab/index.html";
                    } else {
                        toast(res.msg, 'danger');
                    }
                },
                error: function (e) {
                    toast("网络操作请求失败!", 'danger');
                },
                complete: function () {
                    $btnLogin.attr("disabled", false);
                    remberInfo('w');
                }
            });
        }
    }

    /**
     * 初始化图片轮播
     **/
    /* function initCoverWhell() {
        //图片轮播
        var ar = [
            [[@{/static/img/login/a3.jpg}]],
            [[@{/static/img/login/a2.jpg}]],
            [[@{/static/img/login/a1.jpg}]],
        ];
        var index = 0;
        var _do_ = function () {
            $mainCover.attr('src', (ar[++index % ar.length]));
        }
        setInterval(_do_, 2000); //每两秒钟切换一次
    } */

//     // 轮播图
//     var mySwiper = new Swiper('.swiper-container', {
//         loop: true,
//         autoplay: 3000,
//         pagination: '.pagination'
//     })

    function IEVersion() {
        // 取得浏览器的userAgent字符串
        var userAgent = navigator.userAgent;
        // 判断是否为小于IE11的浏览器
        var isLessIE11 = userAgent.indexOf('compatible') > -1
            && userAgent.indexOf('MSIE') > -1;
        // 判断是否为IE的Edge浏览器
        var isEdge = userAgent.indexOf('Edge') > -1 && !isLessIE11;
        // 判断是否为IE11浏览器
        var isIE11 = userAgent.indexOf('Trident') > -1
            && userAgent.indexOf('rv:11.0') > -1;
        if (isLessIE11) {
            var IEReg = new RegExp('MSIE (\\d+\\.\\d+);');
            // 正则表达式匹配浏览器的userAgent字符串中MSIE后的数字部分，，这一步不可省略！！！
            IEReg.test(userAgent);
            // 取正则表达式中第一个小括号里匹配到的值
            var IEVersionNum = parseFloat(RegExp['$1']);
            if (IEVersionNum === 7) {
                // IE7
                return 7
            } else if (IEVersionNum === 8) {
                // IE8
                return 8
            } else if (IEVersionNum === 9) {
                // IE9
                return 9
            } else if (IEVersionNum === 10) {
                // IE10
                return 10
            } else {
                // IE版本<7
                return 6
            }
        } else if (isEdge) {
            // edge
            return 99
        } else if (isIE11) {
            // IE11
            return 11
        } else {
            // 不是ie浏览器
            return -1
        }
    }

    function toast(message, type, position) {
        if (null != message && '' != message) {
            return bootoast({
                message: message,
                type: type || 'info',
                position: position || 'bottom-right',
                icon: undefined,
                timeout: true,
                animationDuration: 500,
                dismissable: true
            });
        } else {
            return null;
        }
    }

    /**
     * 记住/读取用户信息
     * @param action r:读取，w：写入
     */
    function remberInfo(action) {
        //需要保存的键值对
        var $form = $('#login-by-user');
        var $userid = $form.find('input[name="username"]');
        var $userpwd = $form.find('input[name="password"]');
        if ('r' === action) {
            //读取
            $userid.val($.cookie('userid'));
            $userpwd.val($.cookie('userpwd'));
            $rember.prop('checked', null != $.cookie('userpwd'));
        } else if ('w' === action) {
            if (null != $currentLoginForm
                && $currentLoginForm.attr('id') == $form.attr('id')) {
                if ($rember.prop('checked')) {
                    $.cookie('userid', $userid.val());
                    $.cookie('userpwd', $userpwd.val());
                } else {
                    $.removeCookie('userid');
                    $.removeCookie('userpwd');
                }

            }
        }
    }
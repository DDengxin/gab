$(function () {
    var layer;
    var form;
    var $loginDiv = $('#login-div');

    layui.use(['layer', 'form'], function () {
        layer = layui.layer;
        form = layui.form;
        // 提交表单数据
        form.on('submit(login-form)', function () {
            saveData();
            return false;
        });
        //表单验证
        form.verify({
            userid: function (value, item) {
                if (!new RegExp("^[a-zA-Z0-9_]+$").test(value)) {
                    return '用户名只能包含数字、字母以及下划线';
                }
            },
            phone: function (value, item) {
                if (!/^[1][3,4,5,7,8][0-9]{9}$/.test(value)) {
                    return '手机号码格式不正确';
                }
            },
            obligatory:function (value, item) {
                if('on' != value){
                    return '请查阅并同意《用户协议》';
                }
            }
        });
    });

    //用户名输入框失去焦点后自动去除前后空格
    $loginDiv.find('input[name=userId]').on('blur',function(e){
        var event = e || window.event;
        event.target.value = event.target.value.trim();
    });

    //绑定验证码点击监听时间
    $loginDiv.find('#request-randcode').click(function () {
        var phone = $loginDiv.find("input[name=userMtel]").val();
        var phoneErrorText = form.config.verify['phone'](phone);
        if ($.isEmptyObject(phoneErrorText)) {
            var formData = $loginDiv.children("#login-form").serializeObject();
            if ($.isEmptyObject(formData.userId) || $.isEmptyObject(formData.userMtel)) {
                layer.msg('请先填写用户名和手机号', {time: 5000, icon: 5});
                return false;
            } else {
                var index = layer.load();
                $.ajax({
                    url: "/gab/platform/user/register/getSmsCode",
                    type: 'POST',
                    dataType: 'json',
                    data: JSON.stringify(formData),
                    contentType: 'application/json',
                    success: function (res) {
                        layer.msg(res.msg, {time: 5000, icon: (200 === res.code ? 6 : 5)});
                    }, error: function (data) {
                        layer.msg('网络请求错误!', {time: 5000, icon: 5});
                    }, complete: function () {
                        layer.close(index);
                    }
                })
            }
        } else {
            layer.msg(phoneErrorText, {time: 5000, icon: 5});
        }
    });

    /**
     * 提交数据
     * @returns {boolean}
     */
    function saveData() {
        var formData = $loginDiv.children("#login-form").serializeObject();
        formData.userPwd = md5(formData.userPwd)
        if ($("#rem-firm")[0].checked === false) {
            layer.msg('请勾选用户协议');
            return false;
        }


        if ($.isEmptyObject(formData.randCode)) {
            // 验证码不能为空
            layer.msg('请输入验证码');
            return false;
        } else {
            var index = layer.load();
            $.ajax({
                url: "/gab/platform/user/register",
                type: 'POST',
                dataType: 'json',
                data: JSON.stringify(formData),
                contentType: 'application/json',
                success: function (res) {
                    if (200 === res.code) {
                        layer.msg('恭喜您注册成功,请在跳转后登录!', {time: 5000, icon: 6});
                        setTimeout(function(){
                            window.location.href = "/gabLogin";
                        },5000);
                    } else {
                        layer.msg(res.msg || '操作失败', {time: 5000, icon: 5});
                    }
                }, error: function (data) {
                    layer.msg(data.message || '操作失败', {time: 5000, icon: 6});
                }, complete: function () {
                    layer.close(index);
                }
            })
        }
    }

});



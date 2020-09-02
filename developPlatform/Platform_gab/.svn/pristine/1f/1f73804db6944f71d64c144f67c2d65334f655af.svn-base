$(function () {
    // 上传文件的控件初始化
    _init_upload(['#License', '#BusinessLicense']);

    // 修改手机号码
    $('.editPhone').click(function () {
        $('input[name="telphone"]').removeAttr('disabled');
    })

    //富文本编辑器处理
    var ue = UE.getEditor('myEditor', {
        maximumWords: 99999999999,
        wordCount: false,
        elementPathEnabled: false,
        autoFloatEnabled: false
    });
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function (action) {
        if (action == 'uploadimage' || action == 'uploadfile' || action == 'uploadvideo') {
            return '/ueditor/upload';
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    };

    layui.use(['form', 'layer'], function () {
        var form = layui.form;
        var layer = layui.layer;

        // 省市联动
        // 省数据
        var province, city, area;
        $.ajax({
            url: '/gab/combobox/getProvince',
            type: 'get',
            dataType: 'json',
            async: false,
            success: function (res) {
                $.each(res, function (i, el) {
                    $('#province').append(new Option(el.text, el.id));
                })
                form.render('select');
            }
        });

        // 省市联动
        form.on('select(province)', function (data) {
            province = data.value;

            $('#city').find('option').remove();
            form.render('select', 'city');

            getCity(province);
            form.render('select');
            $('#city').siblings('div.layui-form-select ').find('dl > dd:first').click();
        });
        // 省市区联动
        form.on('select(city)', function (data) {
            city = data.value;

            $('#area').find('option').remove();
            form.render('select');

            getArea(province, city);
            form.render('select');
        });

        // 表单值填充
        $.get("/platform/personal/personById/" + TZ_SETTING.gabUserId, function (res) {

            res.data.personal.headImg == null || res.data.personal.headImg == '' ? _init_upload(['#License', '#BusinessLicense']) : fileShow('License', res.data.supply.headImg);

            //给表单赋值
            form.val('users', res.data.personal);
            if (res.data.personal.telphone == null || res.data.personal.telphone == '') {
                form.val("users", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                    "telphone": res.data.gUserInfo.userMtel
                });
            }

            //设置编辑器内容：
            var editContent = res.data.personal.content == null ? '' : res.data.personal.content;
            ue.ready(function () {
                ue.setContent(editContent);
            });

            // 回显市
            getCity(res.data.personal.province);
            var dds = $('#city').find('option');
            $.each(dds, function (i, el) {
                if ($(el).val() == res.data.personal.city) {
                    $(el).prop('selected', true);
                }
            })

            //回显区 
            getArea(res.data.personal.province, res.data.personal.city);
            var dds = $('#area').find('option');
            $.each(dds, function (i, el) {
                if ($(el).val() == res.data.personal.area) {
                    $(el).prop('selected', true);
                }
            })

            form.render();
            // $('.sidebar').css('height', '1373px');
            $('.sidebar').css('height',$('.right').height+'px');
        })

        form.on('submit(users)', function (data) {
            var jsonData = {};
            jsonData.gUserInfo = {};
            jsonData.personal = data.field;

            //添加富文本编辑器的值
            jsonData.personal.content = ue.getContent();

            var layerTip = layer.load();
            $.ajax({
                url: "/platform/personal/gab/authPersonSave",
                data: JSON.stringify(jsonData),
                type: 'post',
                dataType: 'json',
                contentType: 'application/json',
                success: function (res) {
                    if (res.code == 200) {
                        layer.msg(res.msg, { icon: 1 });
                    } else {
                        layer.msg(res.msg || '操作失败', { time: 3000, icon: 5 });
                    }
                },
                error: function () {
                    layer.msg('请求有错误', { icon: 5 });
                },
                complete: function () {
                    layer.close(layerTip);
                }
            })

            return false;
        })
    })
})
// 回显文件名
function fileShow(el, is_init_img) {
    $("#" + el).resetUploader({
        pickerid: is_init_img,
        filecount: 1,
        view: "edit",
    });
}

// 获取市的数据
function getCity(provinceId) {
    $.ajax({
        url: '/gab/combobox/getCity/' + provinceId,
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (res) {
            $.each(res, function (i, el) {
                $('#city').append(new Option(el.text, el.id));
            });
        }
    });
}

// 获取区的数据
function getArea(provinceId, cityId) {
    $.ajax({
        url: '/gab/combobox/getArea/' + provinceId + '/' + cityId,
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (res) {
            $.each(res, function (i, el) {
                $('#area').append(new Option(el.text, el.id));
            });
        }
    });
}
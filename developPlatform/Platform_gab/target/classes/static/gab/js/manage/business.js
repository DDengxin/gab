$(function () {
    // 上传文件的控件初始化
    _init_upload(['#License', '#BusinessLicense']);

    // 初始化
    _vendorInit();

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

    // 修改手机号码
    $('.editPhone').click(function () {
        $('input[name="telphone"]').removeAttr('disabled');
    })

    layui.use(['form', 'layer'], function () {
        var form = layui.form;
        var layer = layui.layer;

        // 省市联动
        // 省数据
        var province,city;
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

        // 供应商类型下拉框
        var firmValue = '';
        form.on('select(type)', function (data) {
            firmValue = data.value;
            getSupplyType(firmValue);
        });

        // 表单值填充
        $.get("/platform/enroll/SupplyInfo/" + TZ_SETTING.gabUserId, function (res) {
            // 文件名字回显
            res.data.supply.headImg == null ? _init_upload(['#License', '#BusinessLicense']) : fileShow('License', res.data.supply.headImg);

            //给表单赋值
            form.val('supply', res.data.supply);

            // 手机号的回显
            if (res.data.supply.telphone == null || res.data.supply.telphone == '') {
                form.val("supply", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                    "telphone": res.data.gUserInfo.userMtel
                });
            }

            // 关键字的回显
            var keyWord = res.data.supply.keyWord == null ? '' : res.data.supply.keyWord;
            $('#tags').importTags(keyWord);

            //编辑器内容回显
            var editContent = res.data.supply.content == null ? '' : res.data.supply.content;
            ue.ready(function () {
                ue.setContent(editContent);
            });

            // 回显市
            getCity(res.data.supply.province);
            var dds = $('#city').find('option');
            $.each(dds, function (i, el) {
                if ($(el).val() == res.data.supply.city) {
                    $(el).prop('selected', true);
                }
            })

            //回显区 
            getArea(res.data.supply.province, res.data.supply.city);
            var dds = $('#area').find('option');
            $.each(dds, function (i, el) {
                if ($(el).val() == res.data.supply.area) {
                    $(el).prop('selected', true);
                }
            })

            form.render();
        })

        form.on('submit(supply)', function (data) {
            var jsonData = {};
            jsonData.gUserInfo = { type: data.field.supplyType };
            jsonData.supply = data.field;


            //添加富文本编辑器的值
            jsonData.supply.content = ue.getContent();

            var layerTip = layer.load();
            $.ajax({
                url: "/platform/enroll/gab/authsupplysave",
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
    });
})

// 厂商切换展示
function _vendorShow(Template) {
    _pageShow(Template, "container");
}

// 根据不同的厂商渲染不同的模板
function _pageShow(Template, name) {
    var getTpl = Template.innerHTML, view = document.getElementById(name);
    laytpl(getTpl).render({}, function (html) {
        view.innerHTML = html;
        layui.use('form', function () {
            var form = layui.form;
            form.render();
        })
    });
}

// 厂商初始化
function _vendorInit() {
    _vendorShow(JjBussinessMsg);
    // 关键字
    $('#tags').tagsInput();
    $('.sidebar').css('height', '1845px');
}

// 供应厂商的不同类型渲染不同模板
function getSupplyType(firmValue) {
    if (firmValue == "夹具厂商") {
        _vendorShow(JjBussinessMsg);
        // 关键字
        $('#tags').tagsInput();
        $('.sidebar').css('height', $('.right').height() + 'px');
    }
    if (firmValue == "辅件厂商") {
        _vendorShow(FjBussinessMsg);
        // 关键字
        $('#tags').tagsInput();
        $('.sidebar').css('height', $('.right').height() + 'px');
    }
    if (firmValue == "整合厂商") {
        _vendorShow(ZhBussinessMsg);
        // 关键字
        $('#tags').tagsInput();
        $('.sidebar').css('height', $('.right').height() + 'px');
    }
    if (firmValue == "特色经贸商") {
        _vendorShow(TsBussinessMsg);
        // 关键字
        $('#tags').tagsInput();
        $('.sidebar').css('height', $('.right').height() + 'px');
    }
}
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
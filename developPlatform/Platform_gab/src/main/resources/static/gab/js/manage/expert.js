$(function () {
    // 上传文件的控件初始化
    _init_upload(['#License', '#BusinessLicense']);
    
    _vendorInit();

    // 关键字
    $('#tags').tagsInput();

    // 修改手机号码
    $('.editPhone').click(function () {
        $('input[name="telphone"]').removeAttr('disabled');
    })

    $('input[name="concatPerson"]').val(TZ_SETTING.gabUserId);

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

        // 我的企业下拉框赋值
        $.ajax({
            url: '/platform/enroll/getAllsupplyxx',
            type: 'get',
            dataType: 'json',
            success: function (res) {
                $.each(res, function (i, el) {
                    $('#bindSupplyid').append(new Option(el.text, el.id));
                })
                form.render('select');
            }
        })

        // 专家类型下拉框
        var firmValue = '';
        form.on('select(type)', function (data) {
            // console.log(data.value); //得到被选中的值
            firmValue = data.value;
            if (firmValue == "工艺专家") {
                _vendorShow(GyExpertMsg);
                $('.sidebar').css('height', $('.right').height() + 'px');
            }
            if (firmValue == "售前专家") {
                _vendorShow(SqExpertMsg);
                $('.sidebar').css('height', $('.right').height() + 'px');
            }
            if (firmValue == "售后专家") {
                _vendorShow(ShExpertMsg);
                $('.sidebar').css('height', $('.right').height() + 'px');
            }
        });

        // 表单值填充
        $.get("/platform/expert/expertInfo/" + TZ_SETTING.gabUserId, function (res) {
            res.data.expert.headImg == null || res.data.expert.headImg == '' ? _init_upload(['#License', '#BusinessLicense']) : fileShow('License', res.data.expert.headImg);
            //给表单赋值
            form.val("expert", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                "userMtel": res.data.gUserInfo.userMtel,
                "userId": res.data.gUserInfo.userId,
                "userEmail": res.data.gUserInfo.userEmail
            });

            form.val('expert', res.data.expert);
            var skills = res.data.expert.skills == null ?'' :res.data.expert.skills;
            $('#tags').importTags(skills);
            
            var editContent = res.data.expert.content == null ? '' : res.data.expert.content;
            //设置编辑器内容：
            ue.ready(function () {
                ue.setContent(editContent);
            });

            form.render();
        })

        form.on('submit(expert)', function (data) {
            var jsonData = {};
            jsonData.gUserInfo = {
                type: data.field.type,
                userMtel: data.field.userMtel,
                userId: data.field.userId,
                userEmail: data.field.userEmail
            };
            jsonData.expert = data.field;
            delete jsonData.expert.userMtel;
            delete jsonData.expert.userId;
            delete jsonData.expert.userEmail;

            //添加富文本编辑器的值
            jsonData.expert.content = ue.getContent();

            var layerTip = layer.load();
            $.ajax({
                url: "/platform/expert/gab/authExpertSave",
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

// 专家切换展示
function _vendorShow(Template) {
    _pageShow(Template, "container");
}

// 根据不同的专家渲染不同的模板
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

// 初始化
function _vendorInit() {
    _vendorShow(GyExpertMsg);
    $('.sidebar').css('height', '1510px');
}
// 回显文件名
function fileShow(el, is_init_img) {
    $("#" + el).resetUploader({
        pickerid: is_init_img,
        filecount: 1,
        view: "edit",
    });
}
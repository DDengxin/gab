$(function () {
    var layer;
    var form;
    var register_type;
    layui.use(['layer', 'form'], function () {
        layer = layui.layer;
        form = layui.form;
        // 提交表单数据
        //供应商
        form.on('submit(firm)', function () {
            saveData();
            return false;
        });
        //专家
        form.on('submit(expert)', function () {
            saveData();
            return false;
        });
        //需求用户
        form.on('submit(user)', function () {
            saveData();
            return false;
        });

        form.verify({
            username: function (value, item) { //value：表单的值、item：表单的DOM对象
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '用户名不能有特殊字符';
                }
            }
        });

        // 下拉框联动
        form.on('select(appType)', function (data) {

            register_type = data.value


            // if (data.value == '供应厂商') {
            //     $('#add').removeClass('hidden');
            //     var str = '<option value = "夹具厂商">夹具厂商</option>' +
            //         '<option value = "辅件厂商">辅件厂商</option>' +
            //         '<option value = "整合厂商">整合厂商</option>' +
            //         '<option value = "特色经贸商">特色经贸商</option>'
            //     $('#type').html(str);
            //     $('#add .layui-word-aux').text('请选择厂商类型');
            //     form.render('select');
            // }

            // if (data.value == '专家团队') {
            //     $('#add').removeClass('hidden');
            //     var str = '<option value = "工艺专家">工艺专家</option>' +
            //         '<option value = "售前专家">售前专家</option>' +
            //         '<option value = "售后专家">售后专家</option>'
            //     $('#type').html(str);
            //     $('#add .layui-word-aux').text('请选择专家类型');
            //     form.render('select');
            // }

            // if (data.value == '需求用户') {
            //     $('#add').addClass('hidden');
            //     $('#type').html('');
            //     form.render('select');
            // }


        });
    });


    // tab栏切换
    /* var register_type = "我是供应厂商";
     $("#tablist > li").each(function (index) {
         $(this).click(function (e) {
             debugger
             register_type = e.currentTarget.innerHTML
             if (register_type == "我是专家团队") {
                 // 动态填充机构名称
                 $.ajax({
                     url: "/platform/enroll/getAllsupply",
                     type: 'POST',
                     dataType: 'json',
                     data: {},
                     contentType: 'application/json',
                     success: function (res) {
                         var arr = res.data.data;
                         var exFirmType_html = "";
                         var exFirmType = $("#exFirmType");
                         $.each(arr, function (i, obj) {
                             exFirmType_html += "<option value=" + obj.supply_note + ">" + obj.supply_name + "</option>";
                         });
                         exFirmType.html(exFirmType_html);
                     }, error: function (data) {
                         layer.msg("获取供应厂商失败", { time: 5000, icon: 5 });
                     }
                 })
                 // _expertsShow(gy);
                 // _init_upload(["#artImg"]);
             // }else if (register_type == "我是需求用户") {
             //     // _init_upload(["#pserson"])
             //     if(xqtype){
             //         tree("xqtree");
             //         xqtype=false;
             //     }
             // }else{
             //     if(gystype){
             //         tree("gystree");
             //         gystype=false;
             //     }
             }
 
 
 
 
 
             // 显示对应的注册界面,默认展示供应商注册
             $(".contentin").removeClass("contentin");
             // 移除选中状态（背景高亮）
             $(".tabin").removeClass("tabin");
             // 动态切换界面
             $(".content-box>div").eq(index).addClass("contentin");
             $(this).addClass("tabin")
         })
     })*/


    //保存成功后重置数据
    // $("form").reset();

    // 点击验证码

    $('.layui-btn-danger').click(function () {
        var phone = document.getElementsByName("mobile")[0].value;
        if (isPoneAvailable(phone)) {
            var data = $(".contentin > form").serializeArray();
            var sysuser = {};
            var user_data = "userId,mobile";
            $.each(data, function (_index, obj) {
                if (user_data.indexOf(obj.name) != -1) {
                    if (!!obj.value) {
                        sysuser[obj.name] = obj.value;
                    }
                }
            });
            if ((!!sysuser.userId) == false || (!!sysuser.mobile) == false) {
                layer.msg('请先填写用户名和手机号', { time: 5000, icon: 5 });
                return false;
            }
            var index1 = layer.load();
            $.ajax({
                url: "/platform/enroll/getCode",
                type: 'POST',
                dataType: 'json',
                data: JSON.stringify(sysuser),
                contentType: 'application/json',
                success: function (res) {
                    if (200 === res.code) {
                        layer.msg(res.msg, { time: 5000, icon: 6 });
                    } else {
                        layer.msg(res.msg, { time: 5000, icon: 5 });
                    }
                }, error: function (data) {
                    layer.msg(rdata.message, { time: 5000, icon: 5 });
                },complete:function(){
                    layer.close(index1);
                }
            })
        } else {
            layer.msg('请输入合法手机号码', { time: 5000, icon: 5 });
        }
    });

    // 手机号码验证
    function isPoneAvailable(_poneInput) {
        var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
        if (!myreg.test(_poneInput)) {
            return false;
        } else {
            return true;
        }
    }

    // 根据input 获取值
    function getFormInput(obj) {
        var new_obj = {};
        $(obj).each(function (_index, el) {
            if ($(el).attr("type") == "radio") {
                var temp = $(el).attr("name").slice(2);
                new_obj[temp] = $("input[name='" + $(el).attr("name") + "']:checked").val();
            } else {
                new_obj[$(el).attr("name")] = $(el).val();
            }
        });
        return new_obj;
    }
    //保存数据
    function saveData() {
        // debugger
        var sysuser = {}, expert = {}, supply = {}, personal = {};
        // 获取表单数据
        // 注册类型
        if ("专家团队" == register_type) {
            expert = getFormInput($("#Expert input, #Firm select"));
        } else if ("供应厂商" == register_type) {
            supply = getFormInput($("#Firm input, #Firm select"));
        } else if ("需求用户" == register_type) {
            personal = getFormInput($("#Firm input"));
        } else { }
        var data = $(".contentin >  form").serializeArray();
        var user_data = "userId,nickName,mobile,randCode,password,email,orgName,orgId,expertType,type,customerExp";
        $.each(data, function (_index, obj) {
            if (user_data.indexOf(obj.name) != -1) {
                if (!!obj.value) {
                    sysuser[obj.name] = obj.value;
                }
            }
        });

        // 判断对象是否为空
        var all_data = {};
        all_data.sysuser = sysuser;
        if (!$.isEmptyObject(sysuser)) {
            // 用户基础信息
            // all_data.sysuser=sysuser;
        }
        if (!$.isEmptyObject(expert)) {
            // 专家
            all_data.expert = expert;
        }
        if (!$.isEmptyObject(supply)) {
            // 供应商
            all_data.supply = supply;
        }
        if (!$.isEmptyObject(personal)) {
            // 个人需求用户
            all_data.personal = personal
        }
        // 验证码不能为空
        if (!!all_data.sysuser.randCode) {

        } else {
            layer.msg('请输入验证码');
            return;
        }
        
        var index = layer.load();

        $.ajax({
            url: "/platform/enroll/add",
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(all_data),
            contentType: 'application/json',
            success: function (res) {if (300 === res.code) {
                    layer.msg(res.msg || '操作失败', { time: 5000, icon: 5 });
                } else if (200 === res.code) {
                    layer.msg(res.data || '操作成功', { time: 5000, icon: 6 });
                    //数据重置
                    _restDataPage(all_data);
                    // window.location.reload();
                }
            }, error: function (data) {
                layer.msg(data.message || '操作失败', { time: 5000, icon: 6 });
            },complete:function(){
                layer.close(index);
            }
        })
    }






});

$(function () {
    var layer;
    layui.use(['layer', 'form'], function () {
        layer = layui.layer;
        var form = layui.form;
    });
    var xqtype=true;
    var gystype=true;
    var  _final_district;

    //需求用户的等级下拉框值
    getSysParams("personalSupplyLevel","销售","客户等级","KFDJ");
    
    var indexdistinguish;
    // 判断tab
    var register_type = "我是供应厂商";
    // 判断下拉框选中的lei'x
    // var select_type = "";   
    // 动态切换不同类型的注册界面
    $("#tablist > li").each(function (index) {
        $(this).click(function (e) {
            register_type = e.target.innerHTML;
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
                });
                _expertsShow(gy);
                _init_upload(["#artImg"]);
            }else if (register_type == "我是需求用户") {
                _init_upload(["#pserson"]);
                if(xqtype){
                    tree("xqtree");
                    xqtype=false;
                }
            }else{
                if(gystype){
                    tree("gystree");
                    gystype=false;
                }
            }
            // 显示对应的注册界面,默认展示供应商注册
            $(".contentin").removeClass("contentin");
            // 移除选中状态（背景高亮）
            $(".tabin").removeClass("tabin");
            // 动态切换界面
            $(".content-box>div").eq(index).addClass("contentin");
            $(this).addClass("tabin");
            indexdistinguish = index;
        });
    });

    var firmValue = '';
    var expertValue = '';
    // 供应商类型下拉框
    function getFirmValue() {
        var myselect = document.getElementById("type");　　// 获取select对象
        var index = myselect.selectedIndex;　　　　　　　　　// 获取被选中的索引
        // var myselect = document.getElementById("supplyType"); // 获取select对象
        // var index = myselect.selectedIndex; // 获取被选中的索引
        firmValue = myselect.options[index].value;
    }
    // 专家类型
    function getExpertValue() {
        var myselect = document.getElementById("expertType");　　// 获取select对象
        var index = myselect.selectedIndex;　　　　　　　　　// 获取被选中的索引
        expertValue = myselect.options[index].value;　　　　　　// 获取被选中的值
    }

    getFirmValue();
    getExpertValue();
    layui.config({
        base: '/static/gab/js/'
    }).extend({
        treeSelect: 'treeSelect'
    });

    function tree(id){
        layui.use(['treeSelect','form'], function () {
            var treeSelect= layui.treeSelect;
            treeSelect.render({
                elem: '#'+id,
                data: '/gab/getRegion',  // 数据
                type: 'get', // 异步加载方式：get/post，默认get
                placeholder: '请选择单位区域',  // 占位符
                search: true, // 是否开启搜索功能：true/false，默认false
                click: function(d){  // 点击回调
                 _final_district =	d.current.name  ;
                	  // 选中节点，根据id筛选
                   // treeSelect.checkNode(id, 3);
                 //   获取zTree对象，可以调用zTree方法
                    var treeObj = treeSelect.zTree(id);

                   //  刷新树结构
                     treeSelect.refresh();
                	
                	
                	
                	
                	
                	

                },
                success: function (d) {
                }
            });
        });
    }
    // 厂商切换展示
    function _vendorShow(Template) {
        _pageShow(Template, "_vendorInit");
    }
    // 专家切换展示
    function _expertsShow(Template) {
        _pageShow(Template, "_expertsInit")
    }
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
    _vendorInit();
    // 厂商初始化
    function _vendorInit() {
        _vendorShow(jj);
        _init_upload(["#jjHeadImg", "#jjSupplyImg"]);
        // 关键字
        $.FreeTags('jjTags');
        if(gystype){
            tree("gystree");
            gystype=false;
        }
    }

    //初始化上传控件
    function _init_upload(arr) {
        if ($.isArray(arr) && arr.length > 0) {
            $.each(arr, function (_index, value) {
                var fild_id = getFileId();
                $(value + "+input").val(fild_id);
                $(value).filesUpload({
                    pickerid: fild_id,
                    filecount: 1
                });
            });

        }
    }
    //保存成功后重置数据
    function _restDataPage(obj) {
        var supply_type = "夹具厂商,辅件厂商,整合厂商,特色经贸商";
        var expert_type = "工艺专家,售前专家,售后专家";
        var supply_upload = ["#jjHeadImg", "#jjSupplyImg", "#fjSupplyHeadImg", "#fjSupplyImg", "#mixSupplyHeadImg", "#mixSupplyImg", "#strengthSupplyHeadImg", "#strengthSupplyImg"];
        var expert_upload = ["#artImg", "#saleBeforeImg", "#saleAfterImg"];
        if (supply_type.indexOf(obj.sysuser.type) != -1) {
            $("#applicationFirm")[0].reset();
            _init_upload(supply_upload);

        } else if (expert_type.indexOf(obj.sysuser.expertType) != -1) {
            $("#applicationExpert")[0].reset();  //
            _init_upload(expert_upload);
        } else {
            $("#applicationUser")[0].reset();
            _init_upload(["#pserson"])


        }
        $('#jjTags a').remove();//清空关键字
        //重置表单数据
        //初始化上传控件
    }

    // 厂商下拉选择事件
    $('#type').change(function () {
        getFirmValue();
        if (firmValue == "夹具厂商") {
            _vendorShow(jj);
            _init_upload(["#jjHeadImg", "#jjSupplyImg"]);
            // 关键字
            $.FreeTags('jjTags');
        }
        if (firmValue == "辅件厂商") {
            _vendorShow(fj);
            _init_upload(["#fjSupplyHeadImg", "#fjSupplyImg"]);
            // 关键字
            $.FreeTags('fjTags');
        }
        if (firmValue == "整合厂商") {
            _vendorShow(zh);
            _init_upload(["#mixSupplyHeadImg", "#mixSupplyImg"]);
            // 关键字
            $.FreeTags('zhTags');
        }
        if (firmValue == "特色经贸商") {
            _vendorShow(tm);
            _init_upload(["#strengthSupplyHeadImg", "#strengthSupplyImg"]);
            // 关键字
            $.FreeTags('tmTags');
        }

    });

    // 专家下拉选择事件
    $('#expertType').change(function () {
        getExpertValue();
        if (expertValue == "工艺专家") {
            _expertsShow(gy);
            _init_upload(["#artImg"]);

        }
        if (expertValue == "售前专家") {
            _expertsShow(bs);
            _init_upload(["#saleBeforeImg"]);

        }
        if (expertValue == "售后专家") {
            _expertsShow(as);
            _init_upload(["#saleAfterImg"]);
        }
    });

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
        var sysuser = {}, expert = {}, supply = {}, personal = {};
        // 获取表单数据
        // 注册类型
        if ("我是专家团队" == register_type) {
            if ("工艺专家" == expertValue) {
                expert = getFormInput($("#ArtExpert input,  #ArtExpert  textarea"));
            } else if ("售前专家" == expertValue) {
                expert = getFormInput($("#BeforeExpert input,#BeforeExpert  textarea,#BeforeExpert  select"));
            } else if ("售后专家") {
                expert = getFormInput($("#AfterExpert input,#AfterExpert  textarea,#AfterExpert  select"));
            }
        } else if ("我是供应厂商" == register_type) {
            if (firmValue == "夹具厂商") {
                supply = getFormInput($("#FixtureSupply input, #FixtureSupply  textarea, #FixtureSupply  select"));
            } else if (firmValue == "辅件厂商") {
                supply = getFormInput($("#PartSupply input, #PartSupply  textarea"));
            } else if (firmValue == "整合厂商") {
                supply = getFormInput($("#MixSupply input, #MixSupply  textarea, #MixSupply  select"));
            } else if (firmValue == "特色经贸商") {
                supply = getFormInput($("#StrengthSupply input, #StrengthSupply  textarea"));
            } else {

            }

        } else if ("我是需求用户" == register_type) {
            personal = getFormInput($("#Personal input, #Personal  textarea,#Personal  select"));
        } else {}
        var data = $(".contentin >  form").serializeArray();
        var user_data = "userId,nickName,mobile,randCode,password,email,orgName,orgId,expertType,type,customerExp,district";
        $.each(data, function (_index, obj) {
            if (user_data.indexOf(obj.name) != -1) {
                if (!!obj.value) {
                    sysuser[obj.name] = obj.value;
                }
            }
        });
        //填充单位区域
        sysuser.district = _final_district; 
       
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
        if(!!all_data.sysuser.customerExp || !!all_data.sysuser.district){
            supply["customerExp"] = all_data.sysuser.customerExp;
            supply["district"] = all_data.sysuser.district
        }
        if(!!all_data.sysuser.district == false  && ( !!all_data.sysuser.expertType  && all_data.sysuser.expertType.indexOf("专家") == -1 ) ){
        	return   layer.msg(data.message || '请选择单位区域', { time: 5000, icon: 6 });
        }
        
        
        var index = layer.load();
        $.ajax({
            url: "/platform/enroll/add",
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(all_data),
            contentType: 'application/json',
            success: function (res) {
                if (300 === res.code) {
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


    //表单验证
    layui.use(['layer', 'form'], function () {
        layer = layui.layer;
        var form = layui.form;
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
    });

    // 点击验证码
    $('.code').click(function () {
        var phone = $(this).parents("li").find("input:eq(0)").val();
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
                layer.msg('请先填写 用户id和手机号', { time: 5000, icon: 5 });
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
});

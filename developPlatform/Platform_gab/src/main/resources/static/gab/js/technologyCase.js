$(function () {
    function GetQueryValue(queryName) {
        var query = decodeURI(window.location.search.substring(1));
        var vars = query.split("&");
        for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split("=");
            if (pair[0] == queryName) { return pair[1]; }
        }
        return null;
    }

    var tabcur = GetQueryValue("present");

    $("#tablist li").each(function (index) {
        var text = $(this).text();
        if(text == '学术论文' && tabcur == 'academic'){
            $(".contentin").removeClass("contentin");
            $(".tabin").removeClass("tabin");
            $(".content-box>div").eq(index).addClass(" contentin");
            $(this).addClass("tabin");
        }
        
        if(text == '工艺知识' && tabcur == 'know'){
            $(".contentin").removeClass("contentin");
            $(".tabin").removeClass("tabin");
            $(".content-box>div").eq(index).addClass(" contentin");
            $(this).addClass("tabin");
        }

        if(text == '经验分享' && tabcur == 'share'){
            $(".contentin").removeClass("contentin");
            $(".tabin").removeClass("tabin");
            $(".content-box>div").eq(index).addClass(" contentin");
            $(this).addClass("tabin");
        }

        if(text == '行业动态' && tabcur == 'news'){
            $(".contentin").removeClass("contentin");
            $(".tabin").removeClass("tabin");
            $(".content-box>div").eq(index).addClass(" contentin");
            $(this).addClass("tabin");
        }

        if(text == '商会活动' && tabcur == 'activity'){
            $(".contentin").removeClass("contentin");
            $(".tabin").removeClass("tabin");
            $(".content-box>div").eq(index).addClass(" contentin");
            $(this).addClass("tabin");
        }
        if(text == '技术案例' && tabcur == 'technology'){
            $(".contentin").removeClass("contentin");
            $(".tabin").removeClass("tabin");
            $(".content-box>div").eq(index).addClass(" contentin");
            $(this).addClass("tabin");
        }
                                   
        $(this).click(function () {
            $(".contentin").removeClass("contentin");
            $(".tabin").removeClass("tabin");
            $(".content-box>div").eq(index).addClass(" contentin");
            $(this).addClass("tabin");
            setTitle();
        });
    });

    setTitle();
    function setTitle() {
        $("#tablist li").each(function (index) {
            if ($(this).is('.tabin')) {
                $('.changeTitle').text($(this).text());
                first = true;
                getData($(this).text(),8, 1);
            }
        })
    }

    //获取数据
    function getData(classify,pageSize, pageIndex) {
    	title = classify;
        $.ajax({
            url: "/gab/getShare",
            type: 'POST',
            async: true,
            data: {
            	classify: classify,
                pageSize: pageSize,
                pageIndex: pageIndex
            },
            success: function (res) {
                total = res.total;
                var data = { "list": res.data };
                var getTpl = teachCaseTemplate.innerHTML;
                var view = document.getElementById("teachCase");
                if (classify == "技术案例"){
                	getTpl = teachCaseTemplate.innerHTML;
                	view = document.getElementById("teachCase");
                	page = "page6";
                }
                
                
                
                
                laytpl(getTpl).render(data, function (html) {
                    view.innerHTML = html;
                });
                if (first) {
                    pageView();
                }
            }
        });
    }
    
    function pageView() {
        layui.use(['laypage', 'element'], function () {
            var element = layui.element;
            var laypage = layui.laypage;
            first = false;
            laypage.render({
                elem: page
                , count: total
                , limit: 8
                ,layout:['prev', 'page', 'next','skip']
                , jump: function (obj, first) {
                    if (!first) {
                    	pageIndex = obj.curr;
                    	getData(title,8, obj.curr);
                    }
                }
            })
        });
    }

    
    var total = 0;
    var pageIndex = 1;
    var first = true;
    var page = "page1";
    var title = "学术论文";
    
});
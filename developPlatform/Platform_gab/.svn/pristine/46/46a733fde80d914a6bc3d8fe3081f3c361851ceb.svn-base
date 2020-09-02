$(function () {

    var uuid;
    var tabcur = GetQueryValue("classify");
    var reqId = GetQueryValue("reqId");
    $("#tablist li").each(function (index) {
        var text = $(this).text();
        if (tabcur == "学术论文" && tabcur == text) {
            $(".contentin").removeClass("contentin");
            $(".tabin").removeClass("tabin");
            $(".content-box>div").eq(index).addClass(" contentin");
            $(this).addClass("tabin");
            $('.changeTitle').text($(this).text())
        }
        if (tabcur == "技术案例" && tabcur == text) {
            $(".contentin").removeClass("contentin");
            $(".tabin").removeClass("tabin");
            $(".content-box>div").eq(index).addClass(" contentin");
            $(this).addClass("tabin");
            $('.changeTitle').text($(this).text())
        }

        if (tabcur == "工艺知识" && tabcur == text) {
            $(".contentin").removeClass("contentin");
            $(".tabin").removeClass("tabin");
            $(".content-box>div").eq(index).addClass(" contentin");
            $(this).addClass("tabin");
            $('.changeTitle').text($(this).text())
        }

        if (tabcur == "经验分享" && tabcur == text) {
            $(".contentin").removeClass("contentin");
            $(".tabin").removeClass("tabin");
            $(".content-box>div").eq(index).addClass(" contentin");
            $(this).addClass("tabin");
            $('.changeTitle').text($(this).text())
        }

        if (tabcur == "行业动态" && tabcur == text) {
            $(".contentin").removeClass("contentin");
            $(".tabin").removeClass("tabin");
            $(".content-box>div").eq(index).addClass(" contentin");
            $(this).addClass("tabin");
            $('.changeTitle').text($(this).text())
        }

        if (tabcur == "商会活动" && tabcur == text) {
            $(".contentin").removeClass("contentin");
            $(".tabin").removeClass("tabin");
            $(".content-box>div").eq(index).addClass(" contentin");
            $(this).addClass("tabin");
            $('.changeTitle').text($(this).text())
        }

        /* if(tabcur == "商会活动" && tabcur == text){
             $(".contentin").removeClass("contentin");
             $(".tabin").removeClass("tabin");
             $(".content-box>div").eq(index).addClass(" contentin");
             $(this).addClass("tabin");
             $('.changeTitle').text($(this).text())
         }*/
    });


    getTemplate();

    function getTemplate() {
        var layer, index;
        layui.use(['layer'], function () {
            layer = layui.layer;
            index = layer.load();
        })
        if (tabcur === "在线报名") {
            tabcur = "商会活动"
        }
        $.ajax({
            url: "/gab/getAcademic",
            type: 'POST',
            data: {
                reqId: reqId,
                type: tabcur
            },
            async: true,
            success: function (res) {
                var uuid;
                date = res.academic.eventDateEnd;
                var articleId = res.academic.articleId;
                var whetherToPay = res.academic.whetherToPay;
                TimeStamp = Date.parse(date);
                nowTimeStamp = Date.parse(new Date());
                var data = res;
                var getTpl = Template2.innerHTML, view = document.getElementById("templateDiv");

                if (tabcur == "学术论文" || tabcur == "经验分享") {
                    getTpl = Template1.innerHTML;
                }
                if (tabcur == "商会活动") {
                    getTpl = Template3.innerHTML;
                }
                if (tabcur == "技术案例") {
                    getTpl = Template2.innerHTML;
                }
                laytpl(getTpl).render(data, function (html) {
                    view.innerHTML = html;
                });
                if (res.Annex.length != 0 && whetherToPay == "免费") {
                    for (var i = 0; i < res.Annex.length; i++) {
                        uuid = res.Annex[i].uuid;
                        var div = $('<div></div>').attr('id', "fileName" + i).html("<a href='javascript:void(0)' onclick='downLoadFile(\"" + uuid + "\")'>" + res.Annex[i].fileName + "</a>");
                        div.appendTo($(".annex"));
                        $(".pay").attr("style", "display:none;");
                    }
                } else if (res.Annex.length == 0 && whetherToPay == "免费" || whetherToPay == undefined) {
                    $("#fileName").html("<a href='javascript:void(0)'>暂无附件</a>");
                    $(".pay").attr("style", "display:none;");
                } else if (whetherToPay == "付费") {
                    $('.annex').css('display', 'none')
                }

                $('title').html($('.title>h2').text());
            },complete:function(){
                layer.close(index);
            }
        });
    }

    //商业活动在线报名登录确认
    $(document).on('click', '#signUp', function () {
        verifyLogin({ type: 'func' }, signUp);
    })

});

function downLoadFile(uuid) {
    window.open('/system/upload/download?uuid=' + uuid)
}




//获取Url参数
function GetQueryValue(queryName) {
    var query = decodeURI(window.location.search.substring(1));
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == queryName) { return pair[1]; }
    }
    return null;
}

//商业活动在线报名跳转页面
function signUp() {

    if (nowTimeStamp > TimeStamp) {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                closeBtn: 1,
                area: ['500px', '240px'],
                type: 2,
                resize: false,
                title: ['填写报名信息', 'font-size:16px;color:#fff;background:rgb(46,141,237);'],
                content: ['./activityEnd.html', 'no']
            });
        });
    } else {

        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                closeBtn: 1,
                area: ['500px', '240px'],
                type: 2,
                resize: false,
                title: ['填写报名信息', 'font-size:16px;color:#fff;background:rgb(46,141,237);'],
                content: ['./signUp.html?id=' + GetQueryValue("reqId"), 'no']
            });
        });
    }
}

var nowTimeStamp;
var TimeStamp;
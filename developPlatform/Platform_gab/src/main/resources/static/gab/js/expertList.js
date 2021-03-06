$(function () {
    //获取url地址栏中的参数
    function GetQueryValue(queryName) {
        var query = decodeURI(window.location.search.substring(1));
        var vars = query.split("&");
        for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split("=");
            if (pair[0] == queryName) { return pair[1]; }
        }
        return null;
    }

    var tabcur = GetQueryValue("type");
    if (tabcur == "artTeam") {
        $('.changeTitle').text("工艺专家");
    } else if (tabcur == "saleBeforeTeam") {
        $('.changeTitle').text("售前专家");
    } else if (tabcur == "saleAfterTeam") {
        $('.changeTitle').text("售后专家");
    }
});

function findExpertParam(param) {
    var layer, index;
    layui.use(['layer'], function () {
        layer = layui.layer;
        index = layer.load();
    });
    var cur = "artTeam";//专家类型
    var pageIndex = 1;//分页页码
    var pageSize = 8;
    if (!!GetQueryValue("type") == false) {
        cur = "artTeam";//专家类型
    } else {
        cur = GetQueryValue("type");//专家类型
    }
    var params = [];
    for (i=0;i<param.length;i++){
        var vals = {"type":param[i].classList[0],"value":param[i].innerText,"cur":cur,"pageIndex":pageIndex,"pageSize":pageSize};
        params.push(vals)
    }

    $.ajax({
        url: '/gab/getExperTeamByParam',
        contentType: "application/json",
        data: JSON.stringify(params),
        type: 'POST',
        async: false,
        success: function (res) {
            total = res.total;
            Rendering(res.data);
            pageView("artTeam", total);
        },complete:function(){
            layer.close(index);
        }
    });
}

function findExpertByWords(keyWords){
    var layer, index;
    layui.use(['layer'], function () {
        layer = layui.layer;
        index = layer.load();
    })
    $.ajax({
        url: '/gab/getExperTeamByWords',
        data: {"keyWords": keyWords},
        type: 'POST',
        async: false,
        success: function (res) {
            total = res.total;
            Rendering(res.data);
            pageView("artTeam", total);
        },complete:function(){
            layer.close(index);
        }
    });
}
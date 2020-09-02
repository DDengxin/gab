$(function () {
    // 立即报价弹框
    $('.offer').click(function () {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                type: 2,
                closeBtn: 1,
                area: ['1100px', '700px'],//宽，高
                shadeClose: false,
                title: ['填写报价单', 'font-size:16px;color:#fff;background:rgb(46,141,237);'],
                content: ['/gab/offer.html'],
                // success: function (layero, index) {
                //     var body = layer.getChildFrame('body', index);//少了这个是不能从父页面向子页面传值的
                //     //获取子页面的元素，进行数据渲染
                //     body.contents().find("#collectType").val(collectType);

                //     var iframe = window['layui-layer-iframe' + index];
                //     iframe.getUrl(r);
                // },
            });
        });
    })
});

getNeedService();
function getNeedService() {
    $.ajax({
        type: "get",
        url: "/system/parameter/getParams/CP/夹具分类",
        success: function (res) {
            // $("h3[name='introduceName']").text("需求介绍");
            // $("h3[name='applyName']").text("应用需求");
            for(var i=0;i<res.length;i++){
                var _name=res[i].paramValue1;
                var _value=res[i].paramName;
                $("i[name='"+_name+"_Name']").text(_value)
            }

        }
    });

}


function GetQueryValue(queryName) {
    var query = decodeURI(window.location.search.substring(1));
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == queryName) { return pair[1]; }
    }
    return null;
}

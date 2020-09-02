$(function () {
    layui.use(['laypage', 'element'], function () {
        var element = layui.element;
        var laypage = layui.laypage;
        var total = 20;
        first = false;
        laypage.render({
            elem: 'page'
            , count: total
            , limit: 8
            ,layout:['prev', 'page', 'next','skip']
            // , jump: function (obj, fir) {
            //     if (!fir) {
            //     	 pageIndex = obj.curr;
            //     	 getProductsList(8, obj.curr);
            //     }
            // }
        })
    });
    noOrder();
});

// 显示没有订单的效果
function noOrder() {
    var oTbody = $('table').find('tbody');
    if (oTbody.length == 0) {
        $('.no').css("display", 'block');
        $('#page').hide();
    }
}

// 删除操作
function deleteOrder(el) {
    $(el).parent().parent().remove();
    noOrder();
}
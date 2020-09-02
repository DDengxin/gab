$(function () {
    //产品分页
    layui.use(['laypage', 'element'], function () {
        var element = layui.element;
        var laypage = layui.laypage;
        first = false;
        laypage.render({
            elem: 'page1'
            , count: 20
            , limit: 8
            , layout: ['prev', 'page', 'next', 'skip']
            // , jump: function (obj, fir) {
            //     if (!fir) {
            //         pageIndex = obj.curr;
            //         getProductsList(8, obj.curr);
            //     }
            // }
        })
    });




    $('title').html($('.showContent>b').text());
});
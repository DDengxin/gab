$(function () {
    $('.sidebar').css('height', $('.right').height() + 'px');
    for (var i = 0; i < $(".count").length; i++) {
        $(".count")[i].innerText = formatMoney($(".count")[i].innerText, 1)
        $(".money")[i].innerText = formatMoney($(".money")[i].innerText, 1)
    }
    layui.use(['laypage', 'element'], function () {
        var element = layui.element;
        var laypage = layui.laypage;
        var total = 20;
        first = false;
        laypage.render({
            elem: 'page'
            , count: total
            , limit: 8
            , layout: ['prev', 'page', 'next', 'skip']
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


function formatMoney(s, type) {
    if (/[^0-9\.]/.test(s))
        return "0";
    if (s == null || s == "")
        return "0";
    s = s.toString().replace(/^(\d*)$/, "$1.");
    s = (s + "00").replace(/(\d*\.\d\d)\d*/, "$1");
    s = s.replace(".", ",");
    var re = /(\d)(\d{3},)/;
    while (re.test(s))
        s = s.replace(re, "$1,$2");
    s = s.replace(/,(\d\d)$/, ".$1");
    if (type == 0) {// 不带小数位(默认是有小数位)
        var a = s.split(".");
        if (a[1] == "00") {
            s = a[0];
        }
    }
    return s;
}


// 显示没有订单的效果
function noOrder() {
    var oTbody = $('table').find('tbody');
    if (oTbody.length == 0) {
        $('.no').css("display", 'block');
        $('#page').hide();
    }
}
// 删除操作
function deleteOrder(orderId, el) {

    layer.open({
        content: '确定删除该订单吗？',
        btn: ['确定', '取消'],
        area: '200px',
        skin: 'layer-confirm',
        yes: function (index, layero) {
            $.ajax({
                url: "/gab/manage/order/delete",
                type: 'POST',
                data: {
                    "orderId": orderId
                },
                success: function (res) {
                    if (res.code === 200) {
                        $(el).parent().parent().remove();
                        layer.msg(res.msg, {icon: 6, time: 1500});
                    }
                }
            });


        },
        btn2: function (index, layero) {
            // 取消的回调
        },
        cancel: function () {
            // 右上角关闭回调
            // return false //开启该代码可禁止点击该按钮关闭
        }
    });
    noOrder();
}
$(function () {
    var rate;
    layui.use(['rate'], function () {
        rate = layui.rate;

        //渲染
        var ins1 = rate.render({
            elem: '#quantity'  //绑定元素
            , theme: '#e80000'
            , value: 3
            , readonly: true
        });

        var ins2 = rate.render({
            elem: '#speed'  //绑定元素
            , theme: '#e80000'
            , value: 3
            , readonly: true
        });

        var ins3 = rate.render({
            elem: '#attitude'  //绑定元素
            , theme: '#e80000'
            , value: 3
            , readonly: true
        });

        var ins4 = rate.render({
            elem: '#rate1,#rate2'  //绑定元素
            , theme: '#e80000'
            , value: [5, 4]
            , readonly: true
        });
    });

    $(".tabContainer a").click(function () {
        window.location.href = '../../gab/supply.html'
    });

    // var x =  $("#dprice").text()
    // var price =  parseInt(price)
    // price.toFixed(2)
    // $("#dprice").html()
    // price.toFixed(2)

});

function addToCart() {
    var layer,index;
    layui.use(['layer'], function () {
        layer = layui.layer;
        index = layer.load();
    })
    var productNum = document.getElementById("productId").value
    var dataMan = document.getElementById("dataMan").value
    $.ajax({
        url: "/platform/shopping/addShoppingCart",
        type: 'POST',
        async: true,
        data: {
            "productNum": productNum,
            "dataMan": dataMan
        },
        success: function (res) {
            if (res.code == 200) {
                layer.msg(res.msg, {
                    skin: 'msg-tips'
                });
            } else {
                layer.msg(res.msg, {
                    skin: 'msg-tips'
                });
            }
        },complete:function(){
            layer.close(index);
        }
    });

}
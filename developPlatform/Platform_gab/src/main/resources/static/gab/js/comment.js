$(function () {
    layui.use('rate', function () {
        var rate = layui.rate;

        //渲染
        rate.render({
            elem: '#quality',  //绑定元素
            value: 0,
            text: true,
            half: false,
            setText: function (value) {
                this.span.text(value + "分");
            }
        });
        rate.render({
            elem: '#speed',  //绑定元素
            value: 0,
            text: true,
            half: false,
            setText: function (value) {
                this.span.text(value + "分");
            }
        });
        rate.render({
            elem: '#attitude',  //绑定元素
            value: 0,
            text: true,
            half: false,
            setText: function (value) {
                this.span.text(value + "分");
            }
        });
        rate.render({
            elem: '#evaluation',  //绑定元素
            value: 0,
            text: true,
            half: false,
            setText: function (value) {
                this.span.text(value + "分");
            }
        });
    });
});


function getProductInfo() {
    var quality = $("#quality")[0].innerText;
    var speed = $("#speed")[0].innerText;
    var attitude = $("#attitude")[0].innerText;
    var evaluation = $("#evaluation")[0].innerText;
    var evaluationContent = $("#evaluationContent")[0].value;
    var productName = $("#productName")[0].innerText;
    var productNum = $("#productNum")[0].value;
    $.ajax({
        url: "/platform/evaluation/addEvaluation",
        type: "POST",
        data: JSON.stringify({
            "quality": quality,
            "speed": speed,
            "attitude": attitude,
            "evaluation": evaluation,
            "evaluationContent": evaluationContent,
            "productName": productName,
            "productNum": productNum
        }),
        contentType: 'application/json',
        success: function (res) {
            if (200 === res.code) {
                layer.msg(res.msg, {time: 5000, icon: 6});
            } else {
                layer.msg(res.msg, {time: 5000, icon: 6});
            }
        }
    });
}



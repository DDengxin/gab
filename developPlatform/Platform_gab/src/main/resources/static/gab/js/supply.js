$(function () {
    var introHeight = $("#introduce").height() + 35;//企业介绍的高度
    var productHeight = $("#product").height() + 20;//服务产品的高度

    $(".business").each(function () {
        var len = $(this).text().length;   //当前HTML对象text的长度
        if (len > 36) {
            var str = "";
            str = $(this).text().substring(0, 30) + "......";  //使用字符串截取，获取前30个字符，多余的字符使用“......”代替
            $(this).html(str);                   //将替换的值赋值给当前对象
        }
    });

    // 关键词进行“,”处理
    var keywords = $('.keyword b').text().replace(/,/g,'  ');
    $('.keyword b').text(keywords);
    
    



    // 锚点
    $(".tabContainer .list a").click(function () {
        $('.tabin').removeClass('tabin');
        $(this).addClass("tabin");

        if ($(this).is('.tabin')) {
            var text = $(this).text();
            if (text == '企业介绍') {
                $('html,body').animate({
                    scrollTop: 0
                }, 500)
            } else if (text == '服务产品') {
                $('html,body').animate({
                    scrollTop: introHeight
                }, 500)
            } else if (text == '客户案例') {
                $('html,body').animate({
                    scrollTop: introHeight + productHeight
                }, 500)
            }
        }
    });

    $("#product ul li").each(function (i) {
        if ((i + 1) % 5 == 0) {
            $(this).css('margin-right', '0');
        }
    });


    function findSuplyAdvisory() {
        var suppyUid = $("#supplyUid").val();
        $.ajax({
            url: "/gab/getAdvisory/" + suppyUid,
            type: 'get',
            async: true,
            success: function (res) {
                if (res.data.length > 0) {
                    // $('#anli').append($("<li name="anli"><a>+"res.data[0].title"+</a></li>"));
                    for (var i = 0; i < res.data.length; i++) {
                        $('#anli').append($("<li><a href=" + '/gab/academic.html?reqId=' + res.data[i].article_id + "&classify=" + res.data[i].classify + ">" + res.data[i].title + " </a></li>" + res.data[i].title));
                        // <li><b>></b><a href="#">方案名称方案名称方案名称方案名称方案名称方案名称</a></li>
                        $('#anli1').append($("<li><b>></b><a href=" + '/gab/academic.html?reqId=' + res.data[i].article_id + "&classify=" + res.data[i].classify + ">" + res.data[i].title + " </a></li>" + res.data[i].title));
                    }
                } else {
                    $('#anli1').append($("<div class=" + 'noData' + "><h1>暂无相关内容</h1></div>"))

                }
            }
        });
    }
    findSuplyAdvisory()
});

$(function () {
    $('.show_items ul li').each(function (index) {
        if ((index + 1) % 4 == 0) {
            $(this).css('margin-right', '0');
        }
    });

    $('.fl ul li').mouseenter(function () {
        $(this).addClass('liin');
        $(this).siblings().removeClass('liin');

        // 综合上箭头点击事件
        $(this).find('#syn-up').click(function () {
            params.rank = "ASC";
            params.rankType = "data_date,end_time";
            getNeedsList(16, pageIndex);
        });
        // 综合下箭头点击事件
        $(this).find('#syn-down').click(function () {
            params.rank = "DESC";
            params.rankType = "data_date,end_time";
            getNeedsList(16, pageIndex);
        });

        // 发布时间上箭头点击事件
        $(this).find('#time-up').click(function () {
            params.rank = "ASC";
            params.rankType = "data_date";
            getNeedsList(16, pageIndex);
        });
        // 发布时间下箭头点击事件
        $(this).find('#time-down').click(function () {
            params.rank = "DESC";
            params.rankType = "data_date";
            getNeedsList(16, pageIndex);
        });

        // 订单金额上箭头点击事件
        $(this).find('#money-up').click(function () {
            console.info('3');
        });
        // 订单金额下箭头点击事件
        $(this).find('#money-down').click(function () {
            console.info('4');
        });

        // 截止时间上箭头点击事件
        $(this).find('#end-up').click(function () {
            params.rank = "ASC";
            params.rankType = "end_time";
            getNeedsList(16, pageIndex);
        });
        // 截止时间下箭头点击事件
        $(this).find('#end-down').click(function () {
            params.rank = "DESC";
            params.rankType = "end_time";
            getNeedsList(16, pageIndex);
        })

    });

    // 搜索的点击事件
    $('#search').click(function () {
        search();
    });

    $('#searchContent').keypress(function (evt) {
        var e = evt || window.event;
        if (e.keyCode == 13) {
            search();
        }
    });

    function search() {
        first = true;
        params.search = $('#searchContent').val();
        getNeedsList(16, pageIndex);
    }

    //获取行业分类
    var cpList = getSysParams("", "技术", "夹具大类", "CP");
    var hwList = getSysParams("", "技术", "服务大类", "HW");
    for (var i = 0; i < hwList.length; i++) {
        cpList[cpList.length] = hwList[i];
    }
    var data = { "list": cpList };
    var getTpl = needTypes.innerHTML, view = document.getElementById("allTypeUl");
    laytpl(getTpl).render(data, function (html) {
        view.innerHTML = html;
    });

    $('.conditions li').click(function () {
        $(this).addClass('selected');
        $(this).siblings().removeClass('selected');
    })
});

function needTypeChange(type) {
    first = true;
    params.type = type;
    getNeedsList(16, pageIndex)
}

function needFlagChange(flag) {
    first = true;
    params.flag = flag;
    getNeedsList(16, pageIndex)
}


//获取需求数据
function getNeedsList(pageSize, pageIndex) {
    var layer, index;
    layui.use(['layer'], function () {
        layer = layui.layer;
        index = layer.load();
        $.ajax({
            url: "/gab/getNeedPaging",
            type: 'POST',
            async: true,
            data: {
                params: JSON.stringify(params),
                pageSize: pageSize,
                pageIndex: pageIndex
            },
            success: function (res) {
                total = res.total;
                var data = { "list": res.data };
                var getTpl = needMarket.innerHTML, view = document.getElementById("needMarketUl");
                laytpl(getTpl).render(data, function (html) {
                    view.innerHTML = html;
                });
                if (first) {
                    pageView()
                }
            },complete:function(){
                layer.close(index);
            }
        });
    })
}

//需求分页
function pageView() {
    layui.use(['laypage', 'element'], function () {
        var element = layui.element;
        var laypage = layui.laypage;
        first = false;
        laypage.render({
            elem: 'page'
            , count: total
            , limit: 16
            , layout: ['prev', 'page', 'next', 'skip']
            , jump: function (obj, fir) {
                if (!fir) {
                    pageIndex = obj.curr;
                    getNeedsList(16, obj.curr);
                }
            }
        })
    });
}

//初始化数据
var params = {
    type: "",
    flag: "",
    search: "",
    rank: "",
    rankType: ""
};
var total = 0;
var pageIndex = 1;
var first = true;
getNeedsList(16, 1);

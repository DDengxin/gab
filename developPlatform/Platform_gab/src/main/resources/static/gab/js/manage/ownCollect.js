$(function () {
    $('.sidebar').css('height', $('.right').height() + 'px');
    
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
    noCollect();
});

// 显示没有收藏的效果
function noCollect() {
    var oTr = $('table').find('tbody tr');
    if (oTr.length == 0) {
        $('.no').css("display", 'block');
        $('#page').hide();
    }
    if($('table tr').length == 1){
        $('.all-collect input').prop("checked", false);
    }
}

// 单行删除操作
function deleteCollect(el) {
    layer.open({
        title: '提示',
        content: '是否确认删除？',
        btn: ['确定', '取消'],
        area: '200px',
        skin: 'layer-confirm',
        yes: function (index, layero) {
            // 确定的回调
            $(el).parent().parent().remove();
            noCollect();
            layer.close(index); //如果设定了yes回调，需进行手工关闭
        },
        btn2: function (index, layero) {
            // 取消的回调
        },
        cancel: function () {
            // 右上角关闭回调
        }
    });

}
// 批量删除
function removeSelect(chk) {
    var collectIds = [];
    var checkArry = document.getElementsByName("checkboxname");
    for (var i = 0; i < checkArry.length; i++) {
        if (checkArry[i].checked == true) {
            collectIds.push(checkArry[i].value)
        }
    }
    if (collectIds.length > 0) {
        layer.open({
            title: '提示',
            content: '是否确认删除？',
            async: false,
            btn: ['确定', '取消'],
            area: '200px',
            skin: 'layer-confirm',
            yes: function (index, layero) {
                var oTr = $("table tr");
                var b = 0;
                for (var i = 0; i < oTr.length; i++) {
                    var oIns = $(oTr[i]).find("td input[type='checkbox']").prop("checked");
                    if (oIns) {
                        b += 1;
                        $(oTr[i]).remove();
                        noCollect();
                    }
                }

                layer.close(index); //如果设定了yes回调，需进行手工关闭
            }
        })
    }
    else {
        var oTr = $("table tr");
        var b = 0;
        for (var i = 0; i < oTr.length; i++) {
            var oIns = $(oTr[i]).find("td input[type='checkbox']").prop("checked");
            if (oIns) {
                b += 1;
                $(oTr[i]).remove();
            }
        }
    }
    if (b == 0) {
        layer.msg('请勾选需要删除的收藏！', {
            skin: 'msg-tips'
        });
        return false;
    }
}

// 全选
function selectAll(chk) {
    $("input[type='checkbox']").each(function () {
        this.checked = chk;
    });
}

// 单个选中
var k = 0;//计算选中的checkbox的个数
function selectC(chk) {
    var oCheckBox = $('td input[type="checkbox"]');

    // 当checkbox选中时,数量加1
    if ($(chk).prop("checked")) {
        k += 1;
        console.log(k);
    } else {
        k -= 1;
        console.log(k);
    }

    // 所有的checkbox选中时，全选选中
    if (oCheckBox.length == k) {
        selectAll('true');
    } else {
        $('.all-collect input').prop("checked", false);
    }
}
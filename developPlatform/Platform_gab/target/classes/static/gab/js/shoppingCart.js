var layer;
$(document).ready(function () {
    layui.use('layer', function () {
        layer = layui.layer;
    });
    findAll();
    total();
    isNull();
});

function isNull() {
    if ($("#tab tr").length == 1) {
        $('.cartNull').css("display", 'block');
        $('input[type="checkbox"]').prop("checked", false);
        $('.fr').find("em:eq(1)").html('0.00');
    }
}

//遍历购物车
function findAll() {
    $.ajax({
        url: "/platform/shopping/findAllShoppingCart",
        type: 'GET',
        async: false,
        success: function (res) {
            if (res.code == 200) {
                if(!!res.data){
                    console.log(res);
                    creatShopHtml(res.data);
                }
                total();
                isNull();
            }
        }
    });
}

//构建分组shop信息html
function creatShopHtml(data) {
    var html = "";
    var supply = "";
    for (let i = 0; i < data.length; i++) {
        if (supply != data[i].productCompany) {
            if (i > 0) {
                html += '</tbody>';
            }
            supply = data[i].productCompany;
            html += '<tbody>' +
                '<tr>' +
                '<td colspan="6">' +
                '<div class="company-name">' +
                '<input type="checkbox" onclick="selectP(this)">' +
                '<a href="#">' + ( !!data[i].productcompany ?data[i].productcompany:"" ) + '</a>' +
                '</div>' +
                '</td>' +
                '</tr>';
        }
        html += '<tr name="tr1" class="goodsList">' +
            '<td>' +
            '<div class="all-sp">' +
            '<input type="checkbox" name="checkboxname" onchange="selectC(this)" value="' + (!!data[i].productId?data[i].productId:"" ) + '">' +
            '<a href="javascript:void(0);"><img src="/system/upload/getImage?line_primary=' + data[i].coverPicture + '" default-img="/static/gab/images/wt.png"></a>' +
            '</div>' +
            '</td>' +
            '<td>' +
            '<p class="sp-p"><a href="#">' + data[i].cpcodename + '</a></p>' +
            '</td>' +
            '<td align="middle"><span class="under-span"><em>￥</em><em>' + data[i].cpcodePrice + '</em></span></td>' +
            '<td align="middle">' +
            '<div class="num-div">' +
            '<a href="javascript:void(0);" onclick="clickNumber(this,-1);">-</a>' +
            '<input type="text" value="1" onchange="changeNumber(this);" name="productCount">' +
            '<a href="javascript:void(0);" onclick="clickNumber(this,1);">+</a>' +
            '</div>' +
            '</td>' +
            '<td align="middle"><span class="under-span">' +
            '<em>￥</em><em class="smallTotal">0.00</em></span></td>' +
            '<td align="middle">' +
            '<a class="remove-a" href="javascript:void(0);" onclick="deleteR(this,\'' + data[i].productId + '\')">' +
            '<i class="layui-icon layui-icon-delete"></i>' +
            '</a>' +
            '</td>' +
            '</tr>';
    }
    $(".js-tab").append(html);
    total();
    isNull();
}

// 全选
function selectAll(chk) {
    $("input[type='checkbox']").each(function () {
        this.checked = chk;
    });
    total();
}

// 公司的全选
var c = 0;
function selectP(chk) {
    var oIn = $(chk).parent().parent().parent().parent().find("tr").find("td:eq(0)>div>input[type='checkbox']");
    var oComI = $('table').find('.company-name>input');

    $.each(oIn, function (i, e) {
        e.checked = $(chk).prop("checked");
    });

    // 所有的所属公司的checkbox选中时，全选选中
    if ($(chk).prop("checked")) {
        c += 1;
    } else {
        c = 0;
    }
    console.log(c);
    if (oComI.length == c) {
        selectAll('true');
        c = 0;
    } else {
        $('thead tr').find("td:eq(0)>span>input").prop("checked", false);
        $('.all-cart>input').prop("checked", false);
    }
    total();
}

// 单个商品选中
var k = 0;//计算选中的checkbox的个数
var j = 0;//计算所属公司选中的checkbox的个数
function selectC(chk) {
    var oIn = $(chk).parent().parent().parent().parent().find('.goodsList');
    var oInP = $(chk).parent().parent().parent().parent().find("tr:eq(0)").find("td>div>input[type='checkbox']");
    var oComI = $('table').find('.company-name>input');


    // 当子checkbox都选中时，所属公司的checkbox选中
    if (oIn.find("input[type='checkbox']").prop('checked')) {
        k += 1;
    } else {
        k = 0;
    }

    if (oIn.length == k) {
        oInP.prop("checked", true);
        k = 0;
    } else {
        oInP.prop("checked", false);
        $('thead tr').find("td:eq(0)>span>input").prop("checked", false);
        $('.all-cart>input').prop("checked", false);
    }


    // 所有的所属公司的checkbox选中时，全选选中
    if (oInP.prop("checked")) {
        j += 1;
    }
    if (oComI.length == j) {
        selectAll('true');
        j = 0;
    }
    total();
}

// 单行删除
var j = 0;
function deleteR(chk, productId) {

    layer.open({
        title: '提示',
        content: '是否确认移出购物车？',
        btn: ['确定', '取消'],
        area: '200px',
        skin: 'layer-confirm',
        yes: function (index, layero) {
            // 确定的回调
            $.ajax({
                url: "/platform/shopping/delShoppingCart",
                type: 'POST',
                data: { _method: "DELETE", productId: productId },
                async: false,
                success: function (res) {
                    if (res.code == 200) {
                        j += 1;
                        var oTbody = $(chk).parent().parent().parent();
                        var oTbTr = $(chk).parent().parent().parent().find('.goodsList');
                        var oTr = $(chk).parent().parent();
                        var oSmallToltal = oTr.find('.smallTotal').text();
                        var oTotal = $('.cart-div').find('.fr').find('em:eq(1)');
                        $(".js-tab tbody").remove();
                        var finalPrice = parseFloat(oTotal.text()) - parseFloat(oSmallToltal);
                        if (finalPrice <= 0) {
                            oTotal.text("0.00");
                        } else {
                            oTotal.text(finalPrice);
                        }
                        findAll();
                    }

                }
            });
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

// 批量删除所选商品
function removeSelect(chk) {
    var productIds = [];
    var checkArry = document.getElementsByName("checkboxname");
    for (var i = 0; i < checkArry.length; i++) {
        if (checkArry[i].checked == true) {
            productIds.push(checkArry[i].value)
        }
    }
    if (productIds.length>0) {
        layer.open({
            title: '提示',
            content: '是否确认移出购物车？',
            async: false,
            btn: ['确定', '取消'],
            area: '200px',
            skin: 'layer-confirm',
            yes: function (index, layero) {

                $.ajax({
                    url: "/platform/shopping/delShoppingCarts",
                    type: 'POST',
                    traditional: true,
                    data: {
                        "productIds": productIds
                    },
                    async: true,
                    success: function (res) {
                        if (res.code == 200) {
                            // creatShopHtml(res.data);
                            j += 1;
                            var oTbody = $(chk).parent().parent().parent();
                            var oTbTr = $(chk).parent().parent().parent().find('.goodsList');
                            var oTr = $(chk).parent().parent();
                            var oSmallToltal = oTr.find('.smallTotal').text();
                            var oTotal = $('.cart-div').find('.fr').find('em:eq(1)');
                            $(".js-tab tbody").remove();
                            var finalPrice = parseFloat(oTotal.text()) - parseFloat(oSmallToltal);
                            if (finalPrice <= 0) {
                                oTotal.text("0.00");
                            } else {
                                oTotal.text(finalPrice);
                            }
                            findAll();
                        }

                    }
                });
                layer.close(index); //如果设定了yes回调，需进行手工关闭
            }
        })
    }
    else {
        var oTr = $("#tab tr");
        var b = 0;
        for (var i = 0; i < oTr.length; i++) {
            var oIns = $(oTr[i]).find("td:eq(0)>div>input[type='checkbox']").prop("checked");
            if (oIns) {
                b += 1;
                $(oTr[i]).remove();
            }
        }
    }
    if (b == 0) {
        layer.msg('请勾选需要删除的商品！', {
            skin: 'msg-tips'
        });
        return false;
    }


    isNull();
}

function clickNumber(obj, n) {
    var old = $(obj).parent().find("input[type='text']");
    old.val(parseInt(old.val()) + n);
    if (old.val() <= 0) {
        old.val(1);
    }
    total();
}

function changeNumber(obj) {
    var reg = /^\d+$/;
    if (!(reg.test(obj.value)) || obj.value == 0) {
        obj.value = 1;
        return false;
    }
    total();
}

function total() {
    var total = Number(0);
    $("#tab tr").each(function (i) {
        if (i > 0) {
            if ($(this).find("td").length == 1) {

            } else {
                var price = parseFloat($(this).find("td:eq(2)>span>em:eq(1)").html()).toFixed(2);
                var number = parseInt($(this).find("td:eq(3)>div>input[type='text']").val());
                var towTotal = (price * number).toFixed(2);
                if (towTotal == 'NaN') {
                    towTotal = 0;
                }
                $(this).find("td:eq(4)>span>em:eq(1)").html(towTotal);
                if ($(this).find("td:first input[type='checkbox']").prop("checked")) {
                    total = (parseFloat(total) + parseFloat(towTotal)).toFixed(2);
                }
            }
        }
    });
    if (total > 0) {
        $(".cart-div>span>em:eq(1)").html(total);
    } else {
        $(".cart-div>span>em:eq(1)").html("0.00");
    }
}

//购物车结算确认
function saveCart() {
    var products = [];
    var productIds = [];
    var checkArry = document.getElementsByName("checkboxname");
    var productCount = document.getElementsByName("productCount");
    for (var i = 0; i < checkArry.length; i++) {
        if (checkArry[i].checked == true) {
            var num = { productId: checkArry[i].value, productCount: productCount[i].value};
            products.push(num);
            productIds.push(checkArry[i].value)
        }
    }
    if (productIds.length == []) {
        layer.msg('请勾选需要结算的商品！', {
            skin: 'msg-tips'
        });
        return false;
    }
    $.ajax({
        url: "/platform/shopping/settlement",
        type: 'POST',
        contentType: "application/json",
        data: JSON.stringify(products),
        async: false,
        success: function (res) {
            window.sessionStorage.setItem("productIds", productIds);
            window.location.href = '/gab/manage/orderSure.html'
        }
    });
}
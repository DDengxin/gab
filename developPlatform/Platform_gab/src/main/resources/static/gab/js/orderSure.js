$(function () {
    // 省市联动
    // new PCAS("district","city");
    layui.use('form', function () {
        var form = layui.form;
        form.on('submit(productAction)', function (data) {
            saveGoods();
            return false;
        });
        form.on('submit(contactsForm)', function (data) {
            var gShopAddr = JSON.stringify(getFrontForm(".needsInp"));
            var url = "/platform/shop/address/saveAddress";
            if (!!$("#addressNote").val()) {
                url = "/platform/shop/address/updateAddress";
            }
            $.ajax({
                url: url,
                data: gShopAddr,
                contentType: "application/json",
                dataType: "json",
                type: "POST",
                success: function (res) {
                    if (res.code == 200) {
                        address();
                        $('.needsInp').css('display', 'none');
                        $("#addressNote").val("");
                        $('#saveAddress')[0].reset();
                    }
                }
            });
            return false;
        })
    });
    getSysParams("htCurrency", "财务", "交易币种", "JYBZ");
    getSysParams("htSettlement", "销售", "结算方式", "JSFS");
    getSysParams("htType", "销售", "订单类型", "DDLX");
    getSysParams("htItemType", "销售", "合同类型", "LLJG");
    getSysParams("htTax", "财务", "发票税率", "FPSL");
    getSysParams("htTransportCosts", "销售", "运输方式", "YSFS");

    //获取产品信息
    getMyShoppingCart();
    //获取地址信息
    address();

    $('.needsInp').hide();
});

// 设为默认地址
function defaultAddress(el, addressNote) {
    $.ajax({
        url: "/platform/shop/address/updateDefault",
        data: {
            addressNote: addressNote
        },
        type: "POST",
        success: function (res) {
            if (res.code == 200) {
                address();
            }
        }
    });
}

// 新增地址
function addAddress() {
    // $('.needsInp').css('display', 'block');
    $('.needsInp').toggle();
}

// 编辑地址
function edit(el, addressNote) {
    $.ajax({
        url: "/platform/shop/address/getAddress",
        type: "POST",
        data: {
            "addressNote": addressNote
        },
        success: function (res) {
            $("#addressNote").val(res.data.addressNote);
            $("#address").val(res.data.address);
            $("#contacts").val(res.data.contacts);
            $("#contactMethod").val(res.data.contactMethod);
            $("#sortOrd").val(res.data.sortOrd);
        }
    });
    $('.needsInp').css('display', 'block');
}

// 删除地址
function deleteAddress(el, addressNote) {
    layer.open({
        title: '提示',
        content: '是否确认删除该联系人？',
        btn: ['确定', '取消'],
        area: '200px',
        skin: 'layer-confirm',
        yes: function (index, layero) {
            // 确定的回调
            $.ajax({
                url: "/platform/shop/address/delAddress",
                type: "POST",
                data: {
                    "addressNote": addressNote
                },
                success: function (res) {

                }
            });
            $(el).parent().parent().remove();
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

//获取地址信息
function address() {
    $.ajax({
        url: "/platform/shop/address/findDefaultAddress",
        type: "GET",
        success: function (res) {
            if (res.code == 200 && res.data.length > 0) {
                var data = {"list": res.data};
                var getTpl = contactsHtml.innerHTML, view = document.getElementById("contactsOl");
                laytpl(getTpl).render(data, function (html) {
                    view.innerHTML = html;
                });
                
            }
        }
    });
}

//合同类别
function changeType() {
    getSysParams("htItemType", "销售", "订单类型", $("#htType").val());
}

//保存订单
function saveGoods() {
    var goods = getFrontForm("#saveGoods");
    var productIds = [];
    $('[name="productId"]').each(function (j, item) {
        productIds.push(item.value);
    });
    goods.productId = productIds;
    goods.addressNote = $("#defAddressNote").val();
    if (!!goods.addressNote) {
        $.ajax({
            type: "POST",
            url: "/platform/shopping/sureShop",
            data: {
                goods: JSON.stringify(goods)
            },
            type: "POST",
            success: function (res) {
                if (res.code == 200) {
                    layer.msg('提交成功', { time: 5000, icon: 6 });
                }
            }
        });
    } else {
        layer.msg('请选择默认地址', { time: 5000, icon: 5 });
    }
}

//获取产品信息
function getMyShoppingCart() {
    var productIds = window.sessionStorage.getItem("productIds").split(',');
    $.ajax({
        url: "/platform/shopping/getorderSure",
        type: 'POST',
        data: {
            "productIds": productIds,
        },
        traditional: true,
        async: false,
        success: function (res) {
            if (res.code == 200) {
                creatShopHtml(res.data);
            }
        }
    });
}

//拼接产品html
function creatShopHtml(data) {
    var supply = "";
    var html = '<p>产品清单</p>';
    for (var i = 0; i < data.length; i++) {
        if (supply != data[i].productCompany) {
            if (i > 0) {
                html += '</table>';
            }
            supply = data[i].productCompany;
            html += '<div class="company">' +
                '<div class="companyName">' + data[i].productcompany + '</div>' +
                '<input type="hidden" id="productId" value="' + data[i].productId + '">' +
                '</div>' +
                '<table>' +
                '<tr id="ProductHead">' +
                '<td></td>' +
                '<td class="proName">产品名称</td>' +
                '<td>单价</td>' +
                '<td>数量</td>' +
                '<td>小计</td>' +
                '</tr>';
        }

        html += '<tr>' +
            '<td>' +
            '<img src="/system/upload/getImage?line_primary=' + data[i].coverpicture + '" default-img="/static/gab/images/wt.png">' +
            '</td>' +
            '<td class="proNameAlign"><input type="hidden" name="productId" value="' + data[i].productId + '">' + data[i].cpcodename + '</td>' +
            '<td class="price">￥<em>' + data[i].cpcodePrice + '</em></td>' +
            '<td class="number"><em>' + data[i].productCount + '</em></td>' +
            '<td class="sum">￥<em>' + data[i].cpcodePrice * data[i].productCount + '</em></td>' +
            '</tr>'
    }
    $(".product-msg").html(html);
    bottomInit();
}

//底部金额数及件数
function bottomInit() {
    // 总件数
    var num = 0;
    $("table").find("tr").each(function (i, e) {
        if ($(e).attr("id") == 'ProductHead') {
            num += 0;
        } else {
            num += parseInt($(e).find("td:eq(3)>em").text());
        }
        return num;
    });
    $('.summary').find("div:eq(0)>span").text(num);
    // 商品总计
    var total = 0;
    $("table").find("tr").each(function (i, e) {
        if ($(e).attr("id") == 'ProductHead') {
            total += 0;
        } else {
            total += parseInt($(e).find("td:eq(4)>em").text());
        }
        return total;
    });
    $('.summary').find("div:eq(0)>strong>em").text(total);
}
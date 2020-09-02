$(function () {
    // 省市联动
    new PCAS("province", "city", "area");
    
    $('.sidebar').css('height', $('.right').height() + 'px');
    
    layui.use('form', function () {
        var form = layui.form;
        //绑定提交事件(保存/更新地址信息)
        form.on('submit(contactsForm)', function (data) {
            var filed = JSON.stringify(data.field);
            var url = "/platform/shop/address/saveAddress";
            if (!!$("#addressNote").val()) {
                url = "/platform/shop/address/updateAddress";
            }
            $.ajax({
                url: url,
                data: filed,
                contentType: "application/json",
                dataType: "json",
                type: "POST",
                success: function (res) {
                    if (res.code == 200) {
                        $('#saveAddress')[0].reset();
                        $('#saveAddress').hide();
                        getAddressList();
                    }else{
                        layer.msg(res.msg || '操作失败', { time: 5000, icon: 5 });
                    }
                }
            });
            return false;
        })
    });
    $('#saveAddress').hide();
    //获取地址信息
    getAddressList();
});

/**
 * 新增地址(按钮点击触发)
 */
function addAddress() {
    var $saveAddress = $('#saveAddress');
    $saveAddress.toggle();
    $saveAddress[0].reset();
}
/**
 * 设置当前地址未默认地址
 * @param el
 * @param addressNote
 */
function setDefaultAddress(el, addressNote) {
    $.ajax({
        url: "/platform/shop/address/updateDefault",
        data: {
            addressNote: addressNote
        },
        type: "POST",
        success: function (res) {
            if (res.code == 200) {
                getAddressList();
            }
        }
    });
}

/**
 * 编辑地址信息
 * @param el
 * @param addressNote
 */
function editAddressInfo(el, addressNote) {
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
        },complete:function(){
            $('#saveAddress').show();
        }
    });
}

/**
 * 删除地址
 * @param el 节点
 * @param addressNote 数据单号
 */
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

/**
 * 获取地址信息(列表)
 */
function getAddressList() {
    $.ajax({
        url: "/platform/shop/address/findAllAddress",
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
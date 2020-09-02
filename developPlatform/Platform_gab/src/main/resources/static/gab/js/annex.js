$(function () {
    var i = 0;
    $('.addRow').click(function () {
        i++;
        var str = '<div class="addBody">'
            + '<div class="annexName"><input type="text" name="annex' + i + '" id="annex' + i + '" required></div>'
            + '<div class="annexFile"><span id="annexDiv' + i + '" style="width: 100%;padding: 0 17px;"></span>'
            + '<input type="text" id="annexPlan' + i + '" name="annexPlan' + i + '" />'
            + '</div> '
            + '<div class="delete"><i class="layui-icon layui-icon-subtraction" onclick="deleteRow(this);"></i></div>'
            + '</div>';
        $('#addRow').append(str);
    });

    $('.addRowProduct').click(function () {
        i++;
        var str = '<div class="addBody">'
            + '<div class="annexName"><input type="text" name="annexProduct' + i + '" id="annexProduct' + i + '" required></div>'
            + '<div class="annexFile"><span id="annexProductDiv' + i + '" style="width: 100%;padding: 0 17px;"></span>'
            + '<input type="text" id="annexProductPlan' + i + '" name="annexProductPlan' + i + '" />'
            + '</div>'
            + '<div class="delete"><i class="layui-icon layui-icon-subtraction" onclick="deleteRow(this);"></i></div>'
            + '</div>';
        $('#addRowProduct').append(str);
    })
});

//移除
function deleteRow(obj) {
    $(obj).parent().parent().remove();
}

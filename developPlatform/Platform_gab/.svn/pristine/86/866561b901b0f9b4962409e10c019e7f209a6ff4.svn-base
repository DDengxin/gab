$(function () {
    var getHref = window.location.href;
    var getA = $('.sidebar dl').find('dd>a');
    $.each(getA, function (i, e) {
        var href = $(e).attr('href');
        if (getHref.indexOf(href) > -1) {
            $(e).addClass('selected');
            $("h3").text($(e).text());
        }
    });

})

function get(obj, arr) {
    if (obj.currentStyle) {
        return obj.currentStyle[arr];
    }
    else {
        return getComputedStyle(obj)[arr];
    }
}


//初始化上传控件
function _init_upload(arr) {
    if ($.isArray(arr) && arr.length > 0) {
        $.each(arr, function (_index, value) {
            var fild_id = getFileId();
            $(value + "+input").val(fild_id);
            $(value).filesUpload({
                pickerid: fild_id,
                filecount: 1
            });
        });

    }
}

$(function () {
    $('.top dl dd').click(function () {
        $('.top dl dd').removeClass('selected');
        $(this).addClass('selected');
    })
})
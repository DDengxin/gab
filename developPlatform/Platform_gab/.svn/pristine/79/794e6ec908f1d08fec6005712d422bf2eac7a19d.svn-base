$(function () {
    var getHref = window.location.href;
    var getA = $('.sidebar dl').find('dd>a');
    $.each(getA,function(i,e){
        var href = $(e).attr('href');
        if(getHref.indexOf(href) > -1){
            $(e).addClass('selected');
        }
    })
    
})
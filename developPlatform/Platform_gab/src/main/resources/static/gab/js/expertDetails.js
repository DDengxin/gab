$(function () {
    var layer;
    layui.use('layer', function () {
        layer = layui.layer;
    });

    layui.use(['rate'], function () {
        var rate = layui.rate; //评分
        rate.render({
            elem: '#peo'
            , value: 3
            , theme: '#FE0000' //自定义主题色
            ,readonly: true
        });
    });
    
    //后台数动态渲染  teachmore_href teachmore_href
    $('#teachmore_href').attr('href','./expertDetailsTech.html?present=technology&id='+ GetQueryValue('id')); 
    $('#learnmore_href').attr('href','./expertDetailsTech.html?present=academic&id='+ GetQueryValue('id')); 
    $('#experiencemore_href').attr('href','./expertDetailsTech.html?present=share&id='+ GetQueryValue('id'));
    
    
    
    function getCaption(url,parameter) {
        var index = url.lastIndexOf(parameter);
        url = url.substring(index + 1, url.length);
        return url;
    }
    
   var imgsrc =    getCaption(window.location.href, "=/");
    
   

    function GetQueryValue(queryName) {
		var query = decodeURI(window.location.search.substring(1));
		var vars = query.split("&");
		for (var i = 0; i < vars.length; i++) {
			var pair = vars[i].split("=");
			if (pair[0] == queryName) {
				return pair[1];
			}
		}
		return null;
	}

});
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
    
    //动态切换表头
    if(GetQueryValue("present") =="technology" ){
    	$('.title').html("技术案例");
    	$('title').html("技术案例");
    }else if(GetQueryValue("present") =="academic" ){
    	$('.title').html("学术论文");
    	$('title').html("学术论文");
    }else if(GetQueryValue("present") =="share"){
    	$('.title').html("经验分享");
    	$('title').html("经验分享");
    }
       
    
    //后台数动态渲染  teachmore_href teachmore_href
    $('#teachmore_href').attr('href','./expertDetailsTech.html?present=technology&id='+ GetQueryValue('id')); 
    $('#learnmore_href').attr('href','./expertDetailsTech.html?present=academic&id='+ GetQueryValue('id')); 
    $('#experiencemore_href').attr('href','./expertDetailsTech.html?present=share&id='+ GetQueryValue('id'));
    
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

		// 获取后台数据
	function findExpertTeachCase(type,expert_id, pageIndex,pageSize,flag) {	
		$.ajax({
			url : '/platform/expert/getExpertTech' ,
			type : 'post',
			data:{			
				params:JSON.stringify({type,expert_id}),
				pageIndex,
				pageSize
			},
			async : true,
			success : function(res) {
				total = res.total;
				Rendering(res.data);
				if(flag){
					pageView(pageIndex, total);
				}
				
			}
		});
	}
	findExpertTeachCase(  GetQueryValue("present"),GetQueryValue("id"),0,20,true);

	function pageView(_type, total) {
		layui.use('laypage', function () {
			var laypage = layui.laypage;
			laypage.render({
				elem: 'page',
				count: total,
				limit: 20,
	            layout:['prev', 'page', 'next','skip'],
				jump: function (obj, first) {
					//非首次加载
					if (!first) {						
						findExpertTeachCase(GetQueryValue("present"),GetQueryValue("id"),obj.curr-1,2);		
					}
				}
			});
		});
	}
    
    
	function Rendering(res) {

		var data = {
			"list": res
		};
		var getTpl = teachmoreTemplate.innerHTML, view = document
			.getElementById("techCaseWrapper");
		laytpl(getTpl).render(data, function (html) {
			view.innerHTML = html;
		});
	}
	
    
    
    
    
    
    
    
    
    
    
    
  
});
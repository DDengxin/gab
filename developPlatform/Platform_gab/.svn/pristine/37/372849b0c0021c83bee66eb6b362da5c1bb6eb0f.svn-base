$(function () {
	//info的数据获取
	$.ajax({
		url: "/gab/getInfo",
		type: 'POST',
		async: false,
		success: function (res) {
			var data = { "list": res.industryDynamics };
			var getTpl = slideshow.innerHTML, view = document.getElementById("slideshowDiv");
			laytpl(getTpl).render(data, function (html) {
				view.innerHTML = html;
			});
			data = { "list": res.chamberActivities };
			getTpl = chamberActivities.innerHTML, view = document.getElementById("chamberActivitiesUl");
			laytpl(getTpl).render(data, function (html) {
				view.innerHTML = html;
			});
		}
	});
	
	var mySwiper = new Swiper('.swiper-container', {
		loop: true,
		autoplay: 3000,
		pagination: '.pagination',
	});
});
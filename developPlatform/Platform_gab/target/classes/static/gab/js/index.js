$(function () {
	// 懒加载
	// layui.use('flow', function () {
	// 	var flow = layui.flow;
	// 	flow.lazyimg();
	// });


	// 厂商tab切换
	$("#tablist li").each(function (index) {
		$(this).click(function () {
			// $(".contentin").removeClass("contentin");
			$(".tabin").removeClass("tabin");
			// $(".content-box div").eq(index).addClass("contentin");
			$(this).addClass("tabin");
			if ($(this).text() == "特色厂商") {
				getSupply("特色经贸商")
			}
			getSupply($(this).text())
		});
	});

	$('.tcontent').each(function (index) {
		if (index % 2 != 0) {
			$(this).css('background', '#f6f6f6');
		}
	});


	// 需求市场的自动向上滚动
	$('.myscroll').myScroll({
		speed: 90, //数值越大，速度越慢
		rowHeight: 45 //li的高度
	});

	//需求市场的数据获取
	$.ajax({
		url: "/gab/getNeed",
		type: 'POST',
		data: {
			type: "flag",
			value: "投标中"
		},
		async: true,
		success: function (res) {
			var data = { "list": res };
			var getTpl = needMarket.innerHTML, view = document.getElementById("needMarketUl");
			laytpl(getTpl).render(data, function (html) {
				view.innerHTML = html;
			});

			$('.tcontent:odd').css('background', '#f6f6f6')
		}
	});

	// 行业解决方案
	$('.show_hover').hide();
	$('.case_list ul li').mouseenter(function () {
		$(this).find('.show_hover').show();
		$(this).find('.show').hide();
	}).mouseleave(function () {
		$(this).find('.show_hover').hide();
		$(this).find('.show').show();
	});

	//咨询数据获取
	$.ajax({
		url: "/gab/getAdvisory",
		type: 'POST',
		async: true,
		success: function (res) {
			var data = { "list": res.technicalCase };
			var getTpl = technicalCase.innerHTML, view = document.getElementById("technicalCaseUl");
			laytpl(getTpl).render(data, function (html) {
				view.innerHTML = html;
			});
			var data = { "list": res.academicPaper };
			var getTpl = academicPaper.innerHTML, view = document.getElementById("academicPaperUl");
			laytpl(getTpl).render(data, function (html) {
				view.innerHTML = html;
			});
			var data = { "list": res.experienceSharing };
			var getTpl = experienceSharing.innerHTML, view = document.getElementById("experienceSharingUl");
			laytpl(getTpl).render(data, function (html) {
				view.innerHTML = html;
			});
			var data = { "list": res.industryKnowledge };
			var getTpl = industryKnowledge.innerHTML, view = document.getElementById("industryKnowledgeUl");
			laytpl(getTpl).render(data, function (html) {
				view.innerHTML = html;
			});
			var data = { "list": res.industryDynamics };
			var getTpl = industryDynamics.innerHTML, view = document.getElementById("industryDynamicsUl");
			laytpl(getTpl).render(data, function (html) {
				view.innerHTML = html;
			});
		}
	});

	//咨询数据获取
	$.ajax({
		url: "/gab/getgChamberActivities",
		type: 'POST',
		async: true,
		success: function (res) {
			var data = { "list": res };
			var getTpl = commercialActivity.innerHTML, view = document.getElementById("commercialActivityDiv");
			laytpl(getTpl).render(data, function (html) {
				view.innerHTML = html;
			});
		}
	});

	//获取供应单号
	$.ajax({
		url: "/gab/getAdvisory",
		type: 'POST',
		async: true,
		success: function (res) {
			var data = { "list": res.technicalCase };
			var getTpl = technicalCase.innerHTML, view = document.getElementById("technicalCaseUl");
			laytpl(getTpl).render(data, function (html) {
				view.innerHTML = html;
			});
			var data = { "list": res.academicPaper };
			var getTpl = academicPaper.innerHTML, view = document.getElementById("academicPaperUl");
			laytpl(getTpl).render(data, function (html) {
				view.innerHTML = html;
			});
			var data = { "list": res.experienceSharing };
			var getTpl = experienceSharing.innerHTML, view = document.getElementById("experienceSharingUl");
			laytpl(getTpl).render(data, function (html) {
				view.innerHTML = html;
			});
			var data = { "list": res.industryKnowledge };
			var getTpl = industryKnowledge.innerHTML, view = document.getElementById("industryKnowledgeUl");
			laytpl(getTpl).render(data, function (html) {
				view.innerHTML = html;
			});
			var data = { "list": res.industryDynamics };
			var getTpl = industryDynamics.innerHTML, view = document.getElementById("industryDynamicsUl");
			laytpl(getTpl).render(data, function (html) {
				view.innerHTML = html;
			});
		}
	});


	//优质服务厂商的数据获取
	getSupply("夹具厂商");
	function getSupply(type) {
		$.ajax({
			url: "/gab/getSupply",
			type: 'POST',
			data: {
				supplyType: type
			},
			async: true,
			success: function (res) {
				for (var i = 0; i < res.length; i++) {
					if (res[i].keyWord != null)
						res[i].keyWord = res[i].keyWord.split(",");
				}

				var data = { "list": res };
				var getTpl = supply.innerHTML, view = document.getElementById("supplyUl");
				laytpl(getTpl).render(data, function (html) {
					view.innerHTML = html;
				});
				$('.firm .content-box .content ul li').each(function (index) {
					if ((index + 1) % 4 != 0) {
						$(this).css('margin-right', '13.33px');
					}
				});
			}
		});
	}

});

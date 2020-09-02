$(function () {
	$('.sidebar').css('height', $('.right').height() + 'px');
	
	$('#userName').val(TZ_SETTING.gabUserId);

	$('#surePwd').blur(function () {
		if (!($('#newPwd').val() === $('#surePwd').val())) {
			layer.msg("两次密码输入不一致，请重新输入", { icon: 5 });
		}
	})
	
	layui.use('form', function () {
		var form = layui.form;
		form.on('submit(securityForm)', function (data) {
			// data.field.oldPwd = md5(data.field.oldPwd)
			data.field.newPwd = md5(data.field.newPwd)
			data.field.surePwd = md5(data.field.surePwd)
			if (!(data.field.newPwd === data.field.surePwd)) {
				layer.msg("两次密码输入不一致，请重新输入", { icon: 5});
			} else {
				$.ajax({
					url: "/gab/platform/user/manage/modifyPwd",
					type: 'post',
					dataType: 'json',
					contentType: "application/json",
					data: JSON.stringify(data.field),
					success: function (data) {
						layer.msg(data.msg, {time: 2000, icon: 6});
						setTimeout(function () {
							location.reload();
						}, 1000)
					}
				});
			}
			return false;
		})
	})

    /**
	 * 搜索
	 * 1、点击搜索按钮搜索
	 * 2、回车键搜索
	 */
	$('#search').click(function () {
	});
	$('#searchContent').keypress(function (evt) {
		var e = evt || window.event;
		if (e.keyCode == 13) {
		}
	});
})

// function modifyPwd() {
// 	var userName = $("#username").val();
// 	var oldPwd = md5($("#oldPwd").val());
// 	var newPwd = md5($("#newPwd").val());
// 	var surePwd = md5($("#surePwd").val());
// 	$.ajax({
// 		url: "/gab/platform/user/manage/modifyPwd",
// 		type: 'post',
// 		dataType: 'json',
// 		contentType: "application/json",
// 		data: JSON.stringify({
// 			"userName": userName,
// 			"oldPwd": oldPwd,
// 			"newPwd": newPwd,
// 			"surePwd": surePwd
// 		}),
// 		success: function (data) {
// 			layer.msg(data.msg, {time: 2000, icon: 6});
// 			setTimeout(function(){
// 				location.reload();
// 			},1000)
// 		}
// 	});
// }
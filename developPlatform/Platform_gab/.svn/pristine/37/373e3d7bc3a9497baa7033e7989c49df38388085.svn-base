$(function () {
	var laydate;
	layui.use('laydate', function () {
		laydate = layui.laydate;
		//报价日期
		laydate.render({
			elem: '#offerDate'
			// ,show: true
		});
	});

	// 大写自动转换
	// document.getElementById("chineseWord").value = chineseNumber(document.getElementById("taxTotal").value);

	// getSysParams("productType", "平台参数", "表单配置", "BJGL");

	// //报价分类值改变事件
	// $("#productType").change(function () {
	// 	offerConfig($("#productType").val());
	// });

	offerFile();


	//去除千页符
	function clearComma(s) {
		if ($.trim(s) == "") {
			return s;
		} else {
			return (s + "").replace(/[,]/g, "");
		}
	}

	//验证只能输入
	function clearNoNum(inpVal) {
		inpVal = inpVal.replace(/^[^0-9\.]+$/g, ""); //清除"数字"和"."以外的字符  
		inpVal = inpVal.replace(/^\./g, ""); //验证第一个字符是数字而不是  
		inpVal = inpVal.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的  
		inpVal = inpVal.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
		inpVal = inpVal.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'); //只能输入两个小数  
		return inpVal;
	}

	//价格千分位
	$('input[name="offerPriceText"]').blur(function () {
		var temp = $(this).val();
		temp = clearNoNum(temp);
		
		if (temp.indexOf('.') > 0) {
			var i = temp.indexOf('.');
			var s = temp.substring(0, i);
			var inp = Number(s).toLocaleString() + temp.substring(i);
			$(this).val(inp);
			$('input[name="offerPrice"]').val(inp);
		}
		if (temp.indexOf('.') < 0) {
			var inp = Number(temp).toLocaleString() + '.00';
			$(this).val(inp);
			$('input[name="offerPrice"]').val(inp);
		}
		if(temp == ''){
			$(this).val('0.00');
			$('input[name="offerPrice"]').val('0.00');
		}



		// var temp = clearComma($(this).val()) - 0;
		// (!!temp ? temp : 0).toLocaleString()

		// $(this).val((!!temp ? temp : 0).toLocaleString());
		// $('input[name="offerPrice"]').val(temp);
	});
});
function currency() {
	var obj = document.getElementById("unit"); //定位id
	var index = obj.selectedIndex; // 选中索引
	var value = obj.options[index].value; // 选中值
	var el = $(".money-type");
	var add_level = { 'RMB': '￥', 'USD': '$', 'HKD': 'HK$', 'GBP': '￡', 'KRW': 'JP￥', 'KRW': '₩', 'AUD': 'A$', 'EUR': '€' }[value] || '￥';
	el.text(add_level);
}




function offerFile() {
	var fileId = getFileId();
	$("#offerFile").val(fileId);
	$("#fileDiv").filesUpload({
		pickerid: fileId,
		filecount: 1,
	});
}
// function offerConfig(type){
// 	if(type == null||type == ""){
// 		return;
// 	}
// 	$.ajax({
// 		url : "/platform/priceconfigure/findByType",
// 		type : 'POST',
// 		data : {
// 			type : type,
// 		},
// 		async : false,
// 		success : function(data) {
// 			var html = "";
// 			for (var i = 0; i < data.length; i++) {
// 				html += "<tr>";
// 				html += jointOfferHtml(data[i]);
// 				html += "</tr>";
// 			}
// 			$("#ConfigForm").html(html);
// 		}
// 	});
// }

// function jointOfferHtml(data) {
// 	var html = "";
// 	html += 
// 		'<td class="kind">'+data.pcName+'<input type="text" style="width: 0;height: 0;" name="addItem" value="'+data.pcNote+'"></td>' +
// 		'<td class="cost"><input type="text" name="addCost" required lay-verify="required" autocomplete="off"></td>' +
// 		'<td class="number"><input type="text" name="addNumber" required lay-verify="number" autocomplete="off"></td>' +
// 		'<td class="price"><input type="text" name="addPrice" required lay-verify="number" autocomplete="off"></td>' +
// 		'<td class="unit"><input type="text" name="addUnit" required lay-verify="required" autocomplete="off"></td>' +
// 		'<td class="money"><input type="text" name="addMoney" required lay-verify="number" autocomplete="off"></td>' +
// 		'<td class="remark"><input type="text" name="addRemarks" required lay-verify="required" autocomplete="off"></td>'
// 	return html
// }

$("#btn-submit").click(function () {
		save();
});

function save() {
	var jsonData = "";
	var url = "";
	var flow = {};
	//菜单ID
	if (type == "product") {
		var need = getFrontForm("#productNeedForm");
		url = "saveNeed";
		flow = {
			menuId: "R400024",
			title: need.cpcodeName,
			url: "/platform/specialist/need/examination.html",
		};
		jsonData = JSON.stringify({ 'g_Need': JSON.stringify(need), 'dynamic': JSON.stringify(getFrontForm("#ConfigForm")), 'flow': JSON.stringify(flow) });
	}
	$.ajax({
		type: "POST",
		url: "save",
		data: jsonData,
		contentType: 'application/json',
		success: function (res) {
			if (200 === res.code) {
				layui.use('layer', function () {
					var layer = layui.layer;
					layer.msg('提交成功', { time: 5000, icon: 6 });
					layer.closeAll();
				
				});
				if (type == "product") {
					$('form')[0].reset();
					needImg();
					needFile();
					makingPlanFile();
				} else if (type == "service") {
					$('form')[1].reset();
					productDrawingFile();
					productImg();
					productFile();
				}
			} else {
				layui.use('layer', function () {
					var layer = layui.layer;
					layer.msg('提交失败', { time: 5000, icon: 5 });
				});
			}
		}
	});
}

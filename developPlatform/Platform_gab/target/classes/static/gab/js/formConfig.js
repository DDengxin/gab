

/**
 * 生成表单控件
 * @param classify行业分类
 * @returns
 */
function formConfig(options) {
	var defaults = {
		classify:"",//分类
		index:4,//展示列数
		show:"backstage",//区分前后台构建html，默认为backstage(后端)
		container:"#ConfigForm",//构建html容器
	};
	var opts = $.extend(defaults,options);
	if(opts.classify == null||opts.classify == ""){
		console.error("classify(分类)无传值");
		return;
	}
	$.ajax({
		url : "/platform/form/getDynamicFormByClassify",
		type : 'POST',
		data : {
			classify : opts.classify,
		},
		async : false,
		success : function(res) {
			if (res.length > 0) {
				buildHtml(res,opts.index,opts.show,opts.container);
			}else{
				$(opts.container).html("");
			}
		}
	});
}

/**
 * 构建html
 * @returns
 */
function buildHtml(data,index,show,container){
	var html = "";
	//后台html
	if(show=="backstage"){
		for (var i = 0; i < data.length; i++) {
			if (i % index == 0) {
				if (i == 0) {
					html += "<tr>"
				}else{
					html += "</tr><tr>"
				}
			}
			html += jointTableHtml(data[i]);
		}
		if (html != "") {
			html += "</tr>";
		}
		$(container).html(html);
		mini.parse();
	//前台html
	}else if(show == "front"){
		for (var i = 0; i < data.length; i++) {
			if (i % index == 0) {
				if (i == 0) {
					html += "<li>"
				}else{
					html += "</li><li>"
				}
			}
			html += jointDivHtml(data[i]);
			if (html != "") {
				html + "</li>";
			}
		}
		$(container).html(html);
		setSelect(data);
	}
}

/**
 * show:div
 * @param data
 * @returns
 */
function jointDivHtml(data) {
	var html = "";
	switch (data.parameterWay) {
	case "XL":
		html += '<div>' +
					'<span>' + data.parameterName + '</span>' +
					'<div class="com-sel">' +
						'<select  class="com-opt" id="' + data.parameter + '" name="' + data.parameter + '"></select>' +
					'</div>' +
				'</div>';
		break;
	case "WB":
		html += '<div>' +
					'<span>' + data.parameterName + '</span>' +
					'<input  type="text" id="' + data.parameter + '" name="' + data.parameter + '" >' +
				'</div>';
		break;
	default:
		break;
	}
	return html
}

/**
 * show:table
 * @param data
 * @returns
 */
function jointTableHtml(data) {
	var html = "";
	switch (data.parameterWay) {
	case "XL":
		html += '<td title>' + data.parameterName + ':</td>' + '<td content>' + '<input  style="width:100%" class="mini-combobox" id="' + data.parameter + '" name="' + data.parameter
				+ '" url="/system/parameter/getParamsSelectListByTypeParent/' + data.defaultType + '/' + data.defaultVal + '" textField="text" valueField="id"/>' + '</td>';
		break;
	case "WB":
		html += '<td title>' + data.parameterName + ':</td>' + '<td content>' + ' <input  style="width:100%" id="' + data.parameter + '" name="' + data.parameter + '" class="mini-textbox" '
				+ parameterType(data.parameterWay, data.parameterType) + '/></td>';
		break;
	default:
		break;
	}
	return html
}

/**
 * 按照方式、类型对控件追加属性
 * @param way
 * @param type
 * @returns ""
 */
function parameterType(way, type) {
	var returnVal = "";
	if (way == "WB") {
		switch (type) {
		case "SZ":
			returnVal = 'vtype="float"';
			break;
		default:
			break;
		}
	}
	return returnVal;
}

/**
 * 遍历下拉框值
 * @param data
 * @returns
 */
function setSelect(data) {
	for(var i = 0; i < data.length; i++){
		if(data[i].parameterWay == "XL"){
			getSysParams(data[i].parameter, '', data[i].defaultType, data[i].defaultVal);
		}
	}
}


function getServiceNeed() {
	$.ajax({
		type: "get",
		url: "/system/parameter/getParams/CP/夹具分类",
		success: function (res) {
			$("span[name='introduceName']").text("需求介绍");
			$("span[name='applyName']").text("应用需求");
			for(var i=0;i<res.length;i++){
				var _name=res[i].paramValue1;
				var _value=res[i].paramName;
				$("span[name='"+_name+"_Name']").text(_value)

			}

		}
	});
}





/**
 * 获取容器内的表单数据
 * @param id 容器id属性
 * @param isFormValue 是否提取显示值
 * @return {*}
 * @exp:getForm('div1');
 */
function getFormData(id, isFormValue) {
	// 获取控件
	var formFieldControls = $(mini.getChildControls(id)).filter(function(indexc, control) {
		if (!control.el || control.formField != true) {
			return false;
		} else {
			return true;
		}
	});
	// 提取数据
	if (mini.isNull(isFormValue)) {
		isFormValue = true;
	}
	var fun = isFormValue ? "getFormValue" : "getValue", result = {};
	for (var i = 0, max = formFieldControls.length; i < max; i++) {
		var control = formFieldControls[i], controlValueFunciton = control[fun];
		if (!controlValueFunciton) {
			continue;
		}
		if (control.name) {
			if (isFormValue == true) {
				mini._setMap(control.name, controlValueFunciton.call(control), result);
			} else {
				result[control.name] = controlValueFunciton.call(control);
			}
		}
		if (control.textName && control['getText']) {
			if (isFormValue == true) {
				mini._setMap(control.textName, control.getText(), result);
			} else {
				result[control.textName] = control.getText();
			}
		}
	}
	return result;
}

/**
 * 前台遍历获取容器内的值
 * @param dom
 * @returns {*}
 */
function getFrontForm(dom) {
	var data = {};
	var chooser = dom + " input," + dom + " select," + dom + " textarea";
	$(chooser).each(function (_index, el) {
		if($(el).attr("type")=="radio"){
			var temp = $(el).attr("name").slice(2);
			data[temp] = $("input[name='"+$(el).attr("name")+"']:checked").val();
		}else{
			data[$(el).attr("name")] = $(el).val();
		}
    });
    return data;
}
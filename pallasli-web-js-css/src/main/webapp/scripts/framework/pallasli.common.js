if(typeof(pallasli)=="undefined") pallasli = {};

/**
 * 方法名：pallasli.loadFormData
 * 
 * 功能：加载表单数据
 * 
 * 参数： formName：要加载数据的form url：生成数据的URL地址 params: url 参数 call: 回调函数
 */
pallasli.ajax = function(formName, url, params, call) {
	$.ajax({
		url : url,
		data : params,
		type : 'post',
		timeout : 1000,
		cache : false,
		success : function(result) {
			resultdata = result.data;
			$("[name=" + formName + "]").setForm(resultdata);
			call(result);
		},
		error : function(result) {
			pallasli._erryFunction();
			call(result);
		}, // 错误执行方法
		beforeSend : function() {
			// 禁用按钮防止重复提交
			$("#submit").attr({
				disabled : "disabled"
			});
			pallasli.showProgress();
		},
		complete : function() {
			$("#submit").removeAttr("disabled");
			pallasli.hideProgress();
		}
	});
};
/**
 * 方法名：pallasli.loadFormData
 * 
 * 功能：加载表单数据
 * 
 * 参数： formName：要加载数据的form url：生成数据的URL地址 params: url 参数 call: 回调函数
 */
pallasli.loadFormData = function() {
	var formName = arguments[0];
	var url = arguments[1];
	var params, call;
	var tmp = arguments[2];
	if (jQuery.isFunction(tmp)) {
		call = tmp;
		params = {};
	} else {
		params = arguments[2];
		call = arguments[3];
	}
	pallasli.ajax(formName, url, params, function(result) {
		resultdata = result.data;
		$("[name=" + formName + "]").setForm(resultdata);
		call(result);
	});
};
/**
 * 方法名：pallasli.commitForm
 * 
 * 功能：提交表单数据
 * 
 * 参数： formName：要加载数据的form url：调用的URL地址 params: url 参数 call: 回调函数
 */
pallasli.commitForm = function() {

	var formName = arguments[0];
	var url = arguments[1];
	var params, call;
	var tmp = arguments[2];
	var formData = pallasli.getFormJsonData(formName);
	if (jQuery.isFunction(tmp)) {
		call = tmp;
		params = formData;
	} else {
		params = arguments[2];
		param = $.extend(true, {}, param, formData);
		call = arguments[3];
	}

	pallasli.ajax(formName, url, params, function(result) {
		call(result);
	});
};
/**
 * 方法名：pallasli.convertToJson
 * 
 * 功能：提交表单数据
 * 
 * 参数：
 */
pallasli.getFormJsonData = function(formName) {
	return pallasli
			.convertToJson($("[name=" + formName + "]").serializeArray());
};

pallasli.validateForm = function(formName) {
	console.log(formName)
	console.log($("[name=" + formName + "]"))
	console.log($("[name=" + formName + "]").validate().form())
	return $("[name=" + formName + "]").validate().form();
}; 

pallasli.addFormField = function(formName, field) {

	var tmpId = formName + field + Math.random();
	var html;
	switch (field.type) {
	case "button":
		html = pallasli.TextField(field);
		break;
	case "combo":
		html = pallasli.TextField(field);
		break;
	case "file":
		html = pallasli.TextField(field);
		break;
	case "password":
		html = pallasli.TextField(field);
		break;
	case "email":
		html = pallasli.EmailField(field);
		break;
	default:
		html = pallasli.TextField(field);
		break;
	}
	$(html).appendTo($("[name=" + formName + "]"));

};
pallasli.addButton = function(divId, btn) {
	var btnHtml = '<button  type="button" class="btn btn-default">' + btn.text
			+ '</button>';
	$(btnHtml).appendTo($("#" + divId)).click(function() {
		btn.handler()
	});
};
pallasli.andNavigation = function(divId, navigation) {
	var html = pallasli.Navigation(navigation); 
	$(html).appendTo($(document.body));
};

pallasli.addIFrame=function(){
	$('<div class="embed-responsive embed-responsive-16by9">'
	+'<iframe name="main" class="embed-responsive-item" src=""></iframe>'
+'</div>').appendTo($(document.body));
}

pallasli.initLayout = function(parent, layout) {
	if (layout.form) {
		$('<form name="tmp_form"></form>').appendTo(parent);
	}
	if (layout.btnDiv) {
		$('<div id="tmp_div"></div>').appendTo(parent);
	}
};
pallasli.initBody = function(layout) {
	var parent = $(document.body);
	pallasli.initLayout(parent, layout);
};

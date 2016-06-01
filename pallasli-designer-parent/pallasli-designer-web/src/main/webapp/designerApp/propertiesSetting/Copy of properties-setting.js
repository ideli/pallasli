(function($) {
	alert(0)
	var configs = shineyue.getCache("currentConfigs");
	var pageComponentId = shineyue.getCache("currentPageComponentId");
	shineyue.find("pageComponentId").val(pageComponentId);
	var properties = shineyue.find("id-properties-setting-configs");
	var currentRow = $("<div class=\"row\"></div>");
	var getField = function(config) {
		var field = $("<div class=\"col-sm-3\"> ");
		var label = $("<label class=\" col-sm-4 label-div\"> </label>");
		label.text(config.caption + ":");
		label.appendTo(field);
		var inputDiv = $("<div class=\"col-sm-7\">  </div>");
		var input = $(" <input type=\"text\"  class=\"form-control\" placeholder=\"\" /> ");
		input.prop("name", config.name);
		inputDiv.appendTo(field);
		input.appendTo(inputDiv);
		return field;
	};

	for (var i = 0; i < configs.length; i++) {
		if (i % 3 == 0) {
			currentRow = $("<div class=\"row\"></div>");
			currentRow = currentRow.appendTo(properties);
			var field = getField(configs[i]);
			field.appendTo(currentRow);
		} else {
			var field = getField(configs[i]);
			field.appendTo(currentRow);
		}
	}

	var $form = shineyue.find("id-properties-setting-configs");
	shineyue.setLoadUrl($form, "/pagecomponent/loadPageComponentConfig");
	shineyue.loadFormData($form, {
		id : pageComponentId
	}, function() {

	});
	alert(1)
	console.log(shineyue.find("id-field-configs-save"))
	shineyue.find("id-field-configs-save").click(function() {
		alert(0)
		console.log(shineyue.getFormJsonData($form));
		var data = shineyue.getFormJsonData($form);
		console.log(data);
		return;
		var id = 100;
		for ( var key in data) {
			shineyue.ajax("/pagecomponent/savePageComponentConfig", "POST", {
				id : id++,
				configKey : key,
				configValue : data[key],
				pageComponentId : pageComponentId
			}, function(result) {

			});
		}

	});
})(jQuery);
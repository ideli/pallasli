(function($) {
 	var pageComponentId = shineyue.getCache("currentPageComponentId");
	shineyue.find("pageComponentId").val(pageComponentId);
 
	var $form = shineyue.find("id-properties-setting-configs");
	shineyue.setLoadUrl($form, "/pagecomponent/loadPageComponentConfig");
	shineyue.loadFormData($form, {
		id : pageComponentId
	}, function() {

	});
	console.log(shineyue.find("id-field-configs-save"))
	shineyue.find("id-field-configs-save").click(function() {
		console.log(shineyue.getFormJsonData($form));
		var data = shineyue.getFormJsonData($form); 
		var id = 100;
		var datas=[];
		for ( var key in data) {
			datas[datas.length]=JSON.stringify({
				id : id++,
				configKey : key,
				configValue : data[key],
				pageComponentId : pageComponentId
			});
		}
		
		shineyue.ajax("/pagecomponent/savePageComponentConfig", "POST", {
			"datas" : [1,2,3],
			pageCompConfigs:datas 
			}, function(result) {

		});

	});
})(jQuery);
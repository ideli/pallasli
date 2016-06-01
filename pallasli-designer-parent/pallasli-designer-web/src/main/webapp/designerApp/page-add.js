(function($) {

	shineyue.initComboData("id_parentModuleList", "/common/loadModules",
			function() {
				shineyue.find("id_parentModuleList").prepend(
						"<option value='0'>请选择</option>");

			});
	shineyue.initComboData("id_MenuTypeList", "/common/loadMenuTypes",
			function() {
			});
	shineyue.find("id-page-add").find(".btn-ok").click(function() {
		var params = shineyue.getFormJsonData(shineyue.find("id-page-add"));
		var url = "/menu/addModule";
		if (params.menuType == 1) {
			url = "/menu/addPage";
		}
		params.moduleId = params.parentId;
		shineyue.ajax(url, "post", params, function(result) {
			console.log(result);
		});
	});
})(jQuery);
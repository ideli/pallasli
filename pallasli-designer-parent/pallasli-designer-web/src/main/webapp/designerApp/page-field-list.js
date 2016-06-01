(function($) {
	var loadProperties = function(configs) {
		shineyue.setCache("currentConfigs", configs);
		shineyue.loadHtml(shineyue.find("id-properties-setting"),
				"propertiesSetting/properties-setting.html",
				"propertiesSetting/properties-setting.js");

	};

	var loadPropertiesHtml = function(configs) {
		var htmlurl = "propertiesSetting/properties-setting.html";
		var jsurl = "propertiesSetting/properties-setting.js";
		console.log(configs);
		console.log(configs.componentTypeId);
		switch (configs.componentTypeId) {
		case "1":
			htmlurl = "propertiesSetting/properties-setting-text.html";
			break;
		case "2":
			htmlurl = "propertiesSetting/properties-setting-number.html";
			break;
		case "3":
			htmlurl = "propertiesSetting/properties-setting-textarea.html";
			break;
		case "4":
			htmlurl = "propertiesSetting/properties-setting-checkbox.html";
			break;
		case "5":
			htmlurl = "propertiesSetting/properties-setting-radio.html";
			break;
		case "6":
			htmlurl = "propertiesSetting/properties-setting.html";
			break;
		case "7":
			htmlurl = "propertiesSetting/properties-setting-panel.html";
			break;
		case "8":
			htmlurl = "propertiesSetting/properties-setting.html";
			break;
		case "9":
			htmlurl = "propertiesSetting/properties-setting.html";
			break;
		case "10":
			htmlurl = "propertiesSetting/properties-setting.html";
			break;
		case "11":
			htmlurl = "propertiesSetting/properties-setting.html";
			break;
		case "12":
			htmlurl = "propertiesSetting/properties-setting.html";
			break;
		case "13":
			htmlurl = "propertiesSetting/properties-setting.html";
			break;
		case "14":
			htmlurl = "propertiesSetting/properties-setting.html";
			break;
		case "15":
			htmlurl = "propertiesSetting/properties-setting.html";
			break;
		default:
			break;
		} 

		shineyue.loadHtml(shineyue.find("id-properties-setting"), htmlurl,
				jsurl);

	};

	var addLoadPropertyEvent = function(pageComponentId, componentTypeId) {

	};
	shineyue.setLoadUrl(shineyue.find("id-page-panel-fields-list"),
			"/pagecomponent/loadChildPageComponent");
	shineyue.loadGridData(shineyue.find("id-page-panel-fields-list"), {
		pageId :shineyue.getCache("id_page_id")
	}, function(result) {
		shineyue.find("id-page-panel-fields-list").find("tr").click(
				function() {
					// 组件加载后加载相应点击事件
					var selectCompData = shineyue.getDataFromRow(shineyue
							.find("id-page-panel-fields-list"),$(this));
					if(!selectCompData)return;
					shineyue.setCache("currentPageComponentId",selectCompData.id);
					loadPropertiesHtml(selectCompData);
					addLoadPropertyEvent();
				});
	});


	$("#btn-page-field-add").click(
			function(e) {
				var msgBox = shineyue.find("mymodal-dialog").modal("show");
				msgBox.find(".modal-title").text("增加");
				msgBox.find(".modal-body").html("");
				shineyue.loadHtml(msgBox.find(".modal-body"),
						"page-field-add.html", "page-field-add.js");

			});
})(jQuery);
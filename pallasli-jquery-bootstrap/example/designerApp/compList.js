(function($) { 

	var addLoadPropertyEvent=function(pageComponentId,componentTypeId){
		
	};
	
	shineyue.setLoadUrl(shineyue.find("id-page-panels-list"), "/pagecomponent/loadPanels");
	shineyue.loadGridData(shineyue.find("id-page-panels-list"), { pageId:1 }, function(result){
		shineyue.find("id-page-panels-list").find("tr").click(function(){
			//组件加载后加载相应点击事件
			console.log($(this));
			addLoadPropertyEvent();
		});
	});
	shineyue.setLoadUrl(shineyue.find("id-page-panel-fields-list"), "/pagecomponent/loadChildPageComponent");
	shineyue.loadGridData(shineyue.find("id-page-panel-fields-list"), { id:1 }, function(result){
		console.log(result)
	});

	$("#btn-fieldgroup-add").click(function(e){ 
		var msgBox = shineyue.find("mymodal-dialog").modal("show");
		msgBox.find(".modal-title").text("增加");
		msgBox.find(".modal-body").html(""); 
		shineyue.loadHtml(msgBox.find(".modal-body"), "page-fieldgroup-add.html", "page-fieldgroup-add.js");
		
	});

	$("#btn-page-field-add").click(function(e){ 
		var msgBox = shineyue.find("mymodal-dialog").modal("show");
		msgBox.find(".modal-title").text("增加");
		msgBox.find(".modal-body").html(""); 
		shineyue.loadHtml(msgBox.find(".modal-body"), "page-field-add.html", "page-field-add.js");
		
	});

	$(".button-toggle-panel-comp").click(
			function() {
				$(".toggle-flag").toggle(
						"fast",
						function(e) {
							if ($(this).is(':hidden')) { 
								$(".panel-list-panel").removeClass("col-md-2")
										.addClass("col-md-11");
								$(".hidewhencollapse").toggleClass("hidden");
								$(".panel-component-list-panel" ).toggleClass("hidden");
							} else {
								$(".panel-component-list-panel" ).toggleClass("hidden");
								$(".panel-list-panel").removeClass("col-md-11")
								.addClass("col-md-2");
								$(".hidewhencollapse").toggleClass("hidden"); 
							}
						});
			});

})(jQuery);
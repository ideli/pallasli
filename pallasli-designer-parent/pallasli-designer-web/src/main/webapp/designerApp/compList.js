(function($) {
 
	shineyue.loadHtml(shineyue.find("id-page-setting"),"page-setting.html","page-setting.js");
	shineyue.loadHtml(shineyue.find("id-page-fieldgroup-list"),"page-fieldgroup-list.html","page-fieldgroup-list.js");
	shineyue.loadHtml(shineyue.find("id-page-field-list"),"page-field-list.html","page-field-list.js");
	
	
	
	shineyue.find("btn-page-setting").click(
			function(){
				shineyue.find("id-page-setting").toggleClass("hidden");
			});
	shineyue.find("btn-page-export").click(
			function(){
				window.open("/html/initHtmlFile");
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
								$(".panel-component-list-panel").toggleClass(
										"hidden");
							} else {
								$(".panel-component-list-panel").toggleClass(
										"hidden");
								$(".panel-list-panel").removeClass("col-md-11")
										.addClass("col-md-2");
								$(".hidewhencollapse").toggleClass("hidden");
							}
						});
			});

})(jQuery);
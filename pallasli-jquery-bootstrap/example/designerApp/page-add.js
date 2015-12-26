(function($){
 
	shineyue.find("#id-page-add .btn-ok").click(function(){
		var params=shineyue.getFormJsonData(shineyue.find("#id-page-add" ));
		shineyue.ajaxSubmit(shineyue.find("#id-page-add" ), 
				"/designer/panelcomponent/addPanel", "post", params, function(result){
			console.log(result)
		});
	});
})(jQuery);
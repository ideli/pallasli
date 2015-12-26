(function($){

	shineyue.find("id-fieldgroup-add").find(".btn-ok").click(function(){
		var params=shineyue.getFormJsonData(shineyue.find("id-fieldgroup-add" )); 
		console.log(params);
		shineyue.ajaxSubmit(shineyue.find("id-fieldgroup-add" ),
				"/pagecomponent/addPageComponent", "post", params, function(result){
			console.log(result)
		});
	});
})(jQuery);
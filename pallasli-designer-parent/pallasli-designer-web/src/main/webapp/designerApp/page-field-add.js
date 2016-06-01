(function($){

	shineyue.find("id_page_id_forpagefield").val(shineyue.getCache("id_page_id"));
	
	shineyue.initComboData("id_PageFieldGroupList2",
			"/common/loadPageFieldgroups", function() {
				shineyue.find("id_PageFieldGroupList2").prepend(
						"<option value='0'>请选择</option>");

			});
	shineyue.initComboData("id_CompTypeList2", "/common/loadCompTypes",
			function() {
			});
	shineyue.find("id-field-add").find(".btn-ok").click(function(){
		var params=shineyue.getFormJsonData(shineyue.find("id-field-add" )); 
	 
		shineyue.ajaxSubmit(shineyue.find("id-field-add" ),
				"/pagecomponent/addPageComponent", "post", {}, function(result){
			console.log(result)
		});
	});
})(jQuery);
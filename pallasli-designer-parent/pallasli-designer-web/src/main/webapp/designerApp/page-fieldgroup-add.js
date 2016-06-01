 (function($) {
	 
	shineyue.find("id_page_id_for_pagefieldgroup").val(shineyue.getCache("id_page_id"));
	shineyue.initComboData("id_PageFieldgroupList",
			"/common/loadPageFieldgroups", function() {
				shineyue.find("id_PageFieldgroupList").prepend(
						"<option value='0'>请选择</option>");

			});
	shineyue.initComboData("id_CompTypeList", "/common/loadCompTypes",
			function() {
			});
	shineyue.find("id-fieldgroup-add").find(".btn-ok").click(
			function() { 
				var params = shineyue.getFormJsonData(shineyue.find("id-fieldgroup-add")); 
				shineyue.ajax( "/pagecomponent/addPanel", "post", params,
						function(result) {
							console.log(result)
						});
			});
 })(jQuery);
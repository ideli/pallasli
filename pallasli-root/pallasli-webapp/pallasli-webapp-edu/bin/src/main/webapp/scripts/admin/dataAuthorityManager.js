$(document).ready(function(){
	$(".admin_dataAuthorityManager_menu input").click(function(){
		var name=$(this).attr("name").split("?");
		pallasli.loadHtml($(".admin_dataAuthorityManager_main"),"/pages/admin/function/"+name[0]+".html","/scripts/admin/function/"+name[0]+".js");

	});
	$(".admin_dataAuthorityManager_main").load("/pages/admin/function/dataAuthorityList.html",function(result){
	});
});
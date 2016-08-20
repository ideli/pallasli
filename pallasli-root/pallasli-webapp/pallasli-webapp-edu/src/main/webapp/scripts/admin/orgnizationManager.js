$(document).ready(function(){
	$(".admin_orgnizationManager_menu input").click(function(){
		var name=$(this).attr("name").split("?");
		pallasli.loadHtml($(".admin_orgnizationManager_main"),"/pages/admin/function/"+name[0]+".html","/scripts/admin/function/"+name[0]+".js");

	});
	$(".admin_orgnizationManager_main").load("/pages/admin/function/orgnizationList.html",function(result){
	});
});
$(document).ready(function(){
	$(".admin_resourceManager_menu input").click(function(){
		var name=$(this).attr("name").split("?");
		pallasli.loadHtml($(".admin_resourceManager_main"),"/pages/admin/function/"+name[0]+".html","/scripts/admin/function/"+name[0]+".js");

	});
	$(".admin_resourceManager_main").load("/pages/admin/function/resourceList.html",function(result){
	});
});
$(document).ready(function(){
	$(".admin_roleManager_menu input").click(function(){
		var name=$(this).attr("name").split("?");
		pallasli.loadHtml($(".admin_roleManager_main"),"/pages/admin/function/"+name[0]+".html","/scripts/admin/function/"+name[0]+".js");

	});
	$(".admin_roleManager_main").load("/pages/admin/function/roleList.html",function(result){
	});
});
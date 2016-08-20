$(document).ready(function(){
	$(".admin_userManager_menu input").click(function(){
		var name=$(this).attr("name").split("?");
		pallasli.loadHtml($(".admin_userManager_main"),"/pages/admin/function/"+name[0]+".html","/scripts/admin/function/"+name[0]+".js");

	});
	$(".admin_userManager_main").load("/pages/admin/function/userList.html",function(result){
	});
});
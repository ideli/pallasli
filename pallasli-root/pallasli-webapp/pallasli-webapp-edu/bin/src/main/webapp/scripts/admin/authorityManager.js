$(document).ready(function(){
	$(".admin_authorityManager_menu input").click(function(){
		var name=$(this).attr("name").split("?");
		pallasli.loadHtml($(".admin_authorityManager_main"),"/pages/admin/function/"+name[0]+".html","/scripts/admin/function/"+name[0]+".js");
	});
	$(".admin_authorityManager_main").load("/pages/admin/function/authorityList.html",function(result){
	});
});
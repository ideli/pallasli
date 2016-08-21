$(document).ready(function(){
	$(".admin_loginLog_menu input").click(function(){
		var name=$(this).attr("name").split("?");
		pallasli.loadHtml($(".admin_loginLog_main"),"/pages/admin/function/"+name[0]+".html","/scripts/admin/function/"+name[0]+".js");

	});
	$(".admin_loginLog_main").load("/pages/admin/function/loginLogList.html",function(result){
	});
});
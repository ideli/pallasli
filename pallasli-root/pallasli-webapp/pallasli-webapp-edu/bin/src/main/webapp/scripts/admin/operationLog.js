$(document).ready(function(){
	$(".admin_operationLog_menu input").click(function(){
		var name=$(this).attr("name").split("?");
		pallasli.loadHtml($(".admin_operationLog_main"),"/pages/admin/function/"+name[0]+".html","/scripts/admin/function/"+name[0]+".js");

	});
	$(".admin_operationLog_main").load("/pages/admin/function/operationLogList.html",function(result){
	});
});
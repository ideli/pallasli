var menuList = [ {
	title : "私信",
	subTitle : "",
	icon : "iconG01"
}, {
	title : "邮件",
	subTitle : "",
	icon : "iconG01"
}, {
	title : "短信",
	subTitle : "",
	icon : "iconG01"
} ]
for (var i = 0; i < menuList.length; i++) {
	var $table = $('<table width="100%" cellpadding="0" cellspacing="0"></table>');
	var $tr = $("<tr></tr>");
	$tr.append('<td width="35"><i class="' + menuList[i].icon
			+ '">&nbsp;</i></td>');
	$tr.append('<td><span>' + menuList[i].title + '</span><p class="fontC06">'
			+ menuList[i].subTitle + '</p></td>');
	$tr
			.append('<td width="25" align="right"><i class="iconG01 iconG01_9">&nbsp;</i>');
	$table.append($tr);
	var $li = $("<li></li>");
	$li.append($table);
	var $item = $("<div></div>");
	$item.append($table);
	$li.append($item);
	$(".simpleMenuList").append($li);
}

var menuList = [ {
		title : "试题布置",
		subTitle : "布置试题信息",
		icon : "iconG01"
	}, {
		title : "待发试题",
		subTitle : "数量:",
		icon : "iconG01"
	}, {
		title : "待做试题",
		subTitle : "数量:",
		icon : "iconG01"
	}, {
		title : "错题卡",
		subTitle : "数量:",
		icon : "iconG01"
	}, {
		title : "待审试题",
		subTitle : "数量:",
		icon : "iconG01"
	}, {
		title : "已审试题",
		subTitle : "数量:",
		icon : "iconG01"
	}, {
		title : "成绩查询",
		subTitle : "成绩历史曲线横向纵向分析",
		icon : "iconG01"
	}, {
		title : "我的题库",
		subTitle : "数量:",
		icon : "iconG01"
	} ]
	for (var i = 0; i < menuList.length; i++) {
		var $table = $('<table width="100%" cellpadding="0" cellspacing="0"></table>');
		var $tr = $("<tr></tr>");
		$tr
				.append('<td width="35"><i class="'+menuList[i].icon+'">&nbsp;</i></td>');
		$tr.append('<td><span>' + menuList[i].title
				+ '</span><p class="fontC06">' + menuList[i].subTitle
				+ '</p></td>');
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

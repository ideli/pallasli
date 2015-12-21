<%@page language="java" pageEncoding="utf-8"  contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Expires" CONTENT="0">
<meta http-equiv="Cache-Control" CONTENT="no-cache">
<meta http-equiv="Pragma" CONTENT="no-cache">
<link rel="stylesheet" type="text/css"
	href="extjs/ext-3.2.1/resources/css/ext-all.css" />

<script type="text/javascript"
	src="extjs/ext-3.2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="extjs/ext-3.2.1/ext-all-debug.js"></script>
<script type="text/javascript"
	src="extjs/ext-3.2.1/locale/ext-lang-zh_CN.js"></script>

</head>
<script language=javascript>
	Ext.onReady(function() {
		// 数据访问
		var store = new Ext.data.ArrayStore({
			data : [],
			fields : []
		});

		var sm = new Ext.grid.RowSelectionModel({
			singleSelect : true
		});

		// 显示列

		var columns = [ {
			header : "test"
		} ];

		var buttons = [

		{
			text : "dragdropzones",
			handler : function() {
				window.open("jsp/dragdropzones.html");
			}

		}, {
			text : "dnd_grid_to_formpanel",
			handler : function() {
				window.open("jsp/dnd_grid_to_formpanel.html");
			}

		}, {
			text : "dnd_grid_to_grid",
			handler : function() {
				window.open("jsp/dnd_grid_to_grid.html");
			}

		}, {
			text : "field-to-grid-dd",
			handler : function() {
				window.open("jsp/field-to-grid-dd.html");
			}

		}, {
			text : "test",
			handler : function() {
				window.open("jsp/test.html");
			}

		}

		];
		var contextmenus = [];
		// 表格对象
		var grid = new Ext.grid.GridPanel({
			border : false,
			stripeRows : true,
			lineBreak : false,
			cellSelect : true,
			loadMask : {
				msg : '正在装载...'
			},
			sm : sm,
			columns : columns,
			store : store,
			tbar : buttons,
			contextMenu : new Ext.menu.Menu({
				items : contextmenus
			}),
			ddGroup : 'grid2tree'
		});

		function getSelectedRecords() {
			return grid.getSelectedRecords();
		}

		new Ext.Viewport({
			items : grid,
			layout : 'fit'
		});
	});
</script>
</html>
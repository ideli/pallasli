<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script language='javascript'>
	Ext.onReady(function() {

		var panel = Ext.getCmp("${panelId}");

		var gridStoreFields = [ {
			name : "id",
			mapping : "id"
		}, {
			name : "key",
			mapping : "key"
		}, {
			name : "name",
			mapping : "name"
		}, {
			name : "createTime",
			mapping : "createTime",
			type : "date"
		}, {
			name : "lastUpdateTime",
			mapping : "lastUpdateTime",
			type : "date"
		}, {
			name : "metaInfo",
			mapping : "metaInfo",
			type : "json"
		} ];
		var grid_columns = [ {
			"dataIndex" : "id",
			"text" : "id"
		}, {
			"dataIndex" : "key",
			"text" : "项目代号",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "项目名称",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "开始日期",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "结束日期",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "状态",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "角色",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "加盟日",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "工时/小时",
			"flex" : 1,
			"align" : "left"
		} ];

		var grid_toolbar = [ {
			text : "查询",
			handler : function() {

			}
		} ];

		Ext.define('GridStoreModel', {
			extend : 'Ext.data.Model',
			fields : gridStoreFields

		});
		var grid_store = Ext.create('Ext.data.ArrayStore', {
			model : 'GridStoreModel',
		});
		var knowledgePanel = Ext.create("Ext.grid.Panel", {
			width : 540,
			height : 200,
			border : false,
			region : "center",
			stripeRows : true,
			lineBreak : false,
			cellSelect : true,
			loadMask : {
				msg : '正在装载...'
			},
			tbar : grid_toolbar,
			columns : grid_columns,
			store : grid_store
		});
		var ui = Ext.create('Ext.Panel', {
			width : 500,
			border : false,
			layout : "border",
			items : [ knowledgePanel ]
		});
		panel.add(ui);
	});
</script>

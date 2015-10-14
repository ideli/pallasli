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
			"text" : "优先级",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "所属产品",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "指派给",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "指派时间",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "解决者",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "解决时间",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "预计",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "消耗",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "剩余",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "截止",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "状态",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "相关需求",
			"flex" : 1,
			"align" : "left"
		} ];

		var grid_toolbar = [
				Ext.create("Ext.form.field.ComboBox", {
					emptyText : "查询分类",
					displayField : "display",
					valueField : "value",
					queryMode : 'local',
					store : Ext.create("Ext.data.ArrayStore", {
						data : [ [ "01", "所有任务" ], [ "02", "指派给我" ],
								[ "03", "由我完成" ] ],
						fields : [ 'value', 'display' ]

					})

				}),
				Ext.create("Ext.form.field.ComboBox", {
					emptyText : "查询分类",
					displayField : "display",
					valueField : "value",
					queryMode : 'local',
					store : Ext.create("Ext.data.ArrayStore", {
						data : [ [ "01", "未开始" ], [ "02", "进行中" ],
								[ "03", "已完成" ], [ "04", "已关闭" ],
								[ "05", "已延期" ] ],
						fields : [ 'value', 'display' ]

					})

				}), Ext.create("Ext.form.field.Text", {
					emptyText : "请输入姓名"

				}), {
					text : "分组查看",
					handler : function() {

					}
				}, {
					text : "需求变动",
					handler : function() {

					}
				}, {
					text : "导出",
					handler : function() {

					}
				}, {
					text : "统计报表",
					handler : function() {

					}
				}, {
					text : "新增任务",
					handler : function() {

					}
				}, {
					text : "完成",
					handler : function() {

					}
				}, {
					text : "关闭",
					handler : function() {

					}
				}, {
					text : "编辑",
					handler : function() {

					}
				}, {
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

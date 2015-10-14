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
			"text" : "日期",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "操作者",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "动作",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "对象类型",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "对象名称",
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
						data : [ [ "01", "今天" ], [ "02", "昨天" ],
								[ "03", "本周" ], [ "04", "上周" ], [ "05", "本月" ],
								[ "06", "上月" ] ],
						fields : [ 'value', 'display' ]

					})

				}), Ext.create("Ext.form.field.Text", {
					emptyText : "开始日期",

				}), Ext.create("Ext.form.field.Text", {
					emptyText : "结束日期",
					labelWitdh : 20,
					labelField : "至"

				}), Ext.create("Ext.form.field.Text", {
					emptyText : "请输入姓名"

				}), {
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

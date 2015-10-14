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
			"text" : "员工帐号",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "姓名",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "工作信息",
			"flex" : 1,
			"align" : "left",
			"columns" : [ {
				"dataIndex" : "name",
				"text" : "所属部门",
				"flex" : 1,
				"align" : "left"
			}, {
				"dataIndex" : "name",
				"text" : "职位",
				"flex" : 1,
				"align" : "left"
			}, {
				"dataIndex" : "name",
				"text" : "薪资",
				"flex" : 1,
				"align" : "left"
			}, {
				"dataIndex" : "name",
				"text" : "创建日期",
				"flex" : 1,
				"align" : "left"
			} ]
		}, {
			"dataIndex" : "name",
			"text" : "基本信息",
			"flex" : 1,
			"align" : "left",
			"columns" : [ {
				"dataIndex" : "name",
				"text" : "性别",
				"flex" : 1,
				"align" : "left"
			}, {
				"dataIndex" : "name",
				"text" : "年龄",
				"flex" : 1,
				"align" : "left"
			}, {
				"dataIndex" : "name",
				"text" : "出生日期",
				"flex" : 1,
				"align" : "left"
			}, {
				"dataIndex" : "name",
				"text" : "婚否",
				"flex" : 1,
				"align" : "left"
			}, {
				"dataIndex" : "name",
				"text" : "邮箱",
				"flex" : 1,
				"align" : "left"
			} ]
		}, {
			"dataIndex" : "name",
			"text" : "学历信息",
			"flex" : 1,
			"align" : "left",
			"columns" : [ {
				"dataIndex" : "name",
				"text" : "学历",
				"flex" : 1,
				"align" : "left"
			}, {
				"dataIndex" : "name",
				"text" : "毕业院校",
				"flex" : 1,
				"align" : "left"
			} ]
		} ];

		var grid_toolbar = [ Ext.create("Ext.form.field.Text", {
			emptyText : "请输入姓名"

		}), {
			text : "增加",
			handler : function() {

			}
		}, {
			text : "编辑",
			handler : function() {

			}
		}, {
			text : "删除",
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

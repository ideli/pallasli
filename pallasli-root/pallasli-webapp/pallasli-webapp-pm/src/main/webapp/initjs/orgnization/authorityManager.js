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
			"text" : "权限名称",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "描述",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "所属角色",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "包含资源",
			"flex" : 1,
			"align" : "left"
		} ];

		var grid_toolbar = [ Ext.create("Ext.form.field.Text", {
			emptyText : "权限名"

		}), Ext.create("Ext.form.field.Text", {
			emptyText : "资源名"

		}), {
			text : "搜索",
			handler : function() {

			}

		}, {
			text : "增加",
			handler : function() {

			}
		}, {
			text : "查看",
			handler : function() {

			}
		}, {
			text : "修改",
			handler : function() {

			}
		}, {
			text : "删除",
			handler : function() {

			}
		}, {
			text : "归属角色设置",
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

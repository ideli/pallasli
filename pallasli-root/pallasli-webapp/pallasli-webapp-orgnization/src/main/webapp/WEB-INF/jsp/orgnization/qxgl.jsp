<%@page contentType="text/html; charset=utf-8"%>
<%
	String panelId = request.getParameter("panelId");
%>
<script language='javascript'>
	Ext.onReady(function() {
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
			"text" : "权限描述",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "createTime",
			"text" : "包含资源",
			"flex" : 1,
			"align" : "left",
			renderer : new Ext.util.Format.dateRenderer("Y-m-d h:i:s")
		} ];

		var grid_toolbar = [ {
			text : "增加",
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
			text : "设置资源",
			handler : function() {

			}
		} ];

		var panel = Ext.getCmp("${panelId}");
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

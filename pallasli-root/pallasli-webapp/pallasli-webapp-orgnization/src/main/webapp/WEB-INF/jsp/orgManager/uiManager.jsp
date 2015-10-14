
<%@page contentType="text/html; charset=utf-8"%>
<%
	String panelId = request.getParameter("panelId");
%>
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
			header : '删除',
			dataIndex : 'delete',
			width : 35,
		}, {
			header : '元素Dom标识',
			dataIndex : 'cmpid',
			sortable : true,
			width : 150

		}, {
			header : '元素类型',
			dataIndex : 'cmptype',
			width : 120
		}, {
			header : '元素描述',
			dataIndex : 'remark',
			width : 200
		}, {
			header : '托管页面功能菜单',
			dataIndex : 'menuname',
			width : 150
		}, {
			header : '元素编号',
			dataIndex : 'partid',
			width : 120
		}, {
			header : '菜单编号',
			dataIndex : 'menuid',
			hidden : true,
			width : 120
		} ];

		var grid_toolbar = [ {
			text : "增加编辑行",
			handler : function() {
			}
		}, {
			text : "保存",
			handler : function() {
			}
		}, {
			text : "引入",
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
		Ext.define('Task', {
			extend : 'Ext.data.Model',
			fields : [ {
				name : 'id',
				type : 'number'
			}, {
				name : 'text',
				type : 'string'
			}, {
				name : 'path',
				type : 'string'
			}, {
				name : 'attributes',
				type : 'json'
			} ]
		});
		var tree = Ext.create('Ext.tree.Panel', {
			title : '功能菜单树',
			autoScroll : true,
			width : 200,
			store : Ext.create('Ext.data.TreeStore', {

			}),
			region : "west",
			hideHeaders : true,
			rootVisible : false,
			plugins : [ {
				ptype : "cellediting",

				listeners : {
					beforeedit : function(e) {
						if (e.record.isRoot())
							return false;
					}
				}
			} ],
			columns : [ {
				xtype : "treecolumn",
				dataIndex : "text",
				flex : 1,
				field : {
					allowBlank : false
				}
			} ]
		});
		var ui = Ext.create('Ext.panel.Panel', {
			border : false,
			layout : "border",
			items : [ knowledgePanel, tree ]
		});
		panel.add(ui);
	});
</script>
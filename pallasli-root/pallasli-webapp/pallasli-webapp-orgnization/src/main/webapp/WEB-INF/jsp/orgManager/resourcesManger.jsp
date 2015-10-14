
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
			header : '菜单名称',
			dataIndex : 'menuname',
			width : 130
		}, {
			header : '请求路径',
			dataIndex : 'request',
			width : 200
		}, {
			header : '排序号',
			dataIndex : 'sortno',
			sortable : true,
			width : 50
		}, {
			header : '上级菜单',
			dataIndex : 'parentmenuname',
			width : 130
		}, {
			header : '菜单类型',
			dataIndex : 'menutype',
		}, {
			header : '节点类型',
			dataIndex : 'leaf',
			hidden : true,
		}, {
			header : '图标CSS类名',
			dataIndex : 'iconcls',
			width : 120
		}, {
			header : 'Tab导航图标',
			dataIndex : 'icon',
			width : 120
		}, {
			header : '展开状态',
			dataIndex : 'expanded',
			width : 60
		}, {
			header : '桌面图标文件',
			dataIndex : 'shortcut',
			width : 100
		}, {
			header : '桌面弹窗宽度',
			dataIndex : 'width',
			width : 100
		}, {
			header : '桌面弹窗高度',
			dataIndex : 'height',
			width : 100
		}, {
			header : '桌面弹窗滚动条',
			dataIndex : 'scrollbar',
			width : 100,
			renderer : function(value) {
				if (value == '1')
					return '是';
				else
					return '';
			}
		}, {
			header : '授权状态',
			dataIndex : 'count',
			hidden : true,
			renderer : function(value) {
				if (value == '0')
					return '未授权';
				else
					return '已授权';
			}
		}, {
			header : '菜单编号',
			dataIndex : 'menuid',
			hidden : false,
			width : 130,
			sortable : true
		}, {
			header : '备注',
			dataIndex : 'remark'
		}, {
			header : '父节点编号',
			hidden : true,
			dataIndex : 'parentid'
		} ];

		var grid_toolbar = [ {
			text : "增加",
			handler : function() {
				var params = {
						url : 'orgManager/modelPage/resource',
						title : '新增资源',
						width : 450,
						height : 550
					};
					var appkey = "pallasli-webapp-orgnization";
					openDocument(appkey, params);
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
			text : "查询",
			handler : function() {

			}
		}, {
			text : "引入",
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
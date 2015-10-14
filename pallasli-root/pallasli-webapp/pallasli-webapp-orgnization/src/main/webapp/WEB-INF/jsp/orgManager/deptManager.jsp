
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
			header : '部门名称',
			dataIndex : 'deptname',
			width : 130
		}, {
			header : '业务对照码',
			dataIndex : 'customid',
			width : 100
		}, {
			header : '上级部门',
			dataIndex : 'parentdeptname',
			width : 130
		}, {
			header : '排序号',
			dataIndex : 'sortno',
			sortable : true,
			width : 50
		}, {
			header : '节点类型',
			dataIndex : 'leaf',
			hidden : true,
		}, {
			header : '父节点编号',
			hidden : true,
			dataIndex : 'parentid'
		}, {
			header : '下属用户数目',
			hidden : true,
			dataIndex : 'usercount'
		}, {
			header : '下属角色数目',
			hidden : true,
			dataIndex : 'rolecount'
		}, {
			header : '部门编号',
			dataIndex : 'deptid',
			hidden : false,
			width : 130,
			sortable : true
		}, {
			header : '备注',
			dataIndex : 'remark'
		} ];

		var grid_toolbar = [
				{
					text : "增加",
					handler : function() {
						var params = {
							url : 'orgManager/modelPage/dept',
							title : '新增部门',
							width : 450,
							height : 250
						};
						var appkey = "pallasli-webapp-orgnization";
						openDocument(appkey, params);
					}
				},
				{
					text : "修改",
					handler : function() {
					}
				},
				{
					text : "删除",
					handler : function() {
						Ext.Msg.alert('提示', '<b>您选中的项目中包含如下系统内置的只读项目</b><br>'
								+ fields + '<font color=red>只读项目不能删除!</font>');
						return;
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
			title : "部门信息",
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
		var appkey = "pallasli-webapp-orgnization";
		var tree = Ext.create('Ext.tree.Panel', {
			title : '组织结构树',
			autoScroll : true,
			width : 200,
			store : Ext.create('Ext.data.TreeStore', {
				// 根节点的参数是parentId
				nodeParam : 'parentId',
				// 根节点的参数值是0
				defaultRootId : 0,
				// 属性域
				fields : [ {
					name : 'className',
					type : 'string'
				}, {
					name : 'text',
					type : 'string'
				}, {
					name : 'iconCls',
					type : 'string'
				} ],
				// 数据代理
				proxy : {
					// 请求方式
					type : 'ajax',
					// 请求网址
					url : '/' + appkey + '/jsppage.do?url=orgManager/testData/deptData' 
				}
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
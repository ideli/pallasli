
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
			header : '授权',
			dataIndex : 'roleid',
			renderer : function(value, cellmeta, record) {
				//if (login_account == parent.DEFAULT_DEVELOP_ACCOUNT
				//		|| login_account == parent.DEFAULT_ADMIN_ACCOUNT) {
				//	if (record.data['roletype'] == '1') {
				//		return '<img src=""/>';
				//	}
				//}
				return '<a href="javascript:void(0);"><img src=""/></a>';
			},
			width : 35
		}, {
			header : '角色名称',
			dataIndex : 'rolename',
			width : 250
		}, {
			header : '角色类型',
			dataIndex : 'roletype',
			width : 80,
		}, {
			header : '所属部门',
			dataIndex : 'deptname',
			width : 150
		}, {
			header : '角色状态',
			dataIndex : 'locked',
			width : 60,
		}, {
			header : '角色编号',
			dataIndex : 'roleid',
			hidden : false,
			width : 80,
			sortable : true
		}, {
			header : '备注',
			dataIndex : 'remark'
		}, {
			header : '所属部门编号',
			dataIndex : 'deptid',
			hidden : true
		} ];

		var grid_toolbar = [
				{
					text : "增加",
					handler : function() {
						var params = {
							url : 'orgManager/modelPage/role',
							title : '新增角色',
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
					text : "授权",
					handler : function() {
						roletype = 2;
						if (roletype == '1') {
							Ext.MessageBox.alert('提示',
									'超级管理员和开发人员不能对业务角色授权<br>'
											+ '业务角色只能被其所属部门或上级'
											+ '部门的管理员授予经办权限');
							return;
						}
						var params = {
							url : 'orgManager/modelPage/roleSetting',
							title : '角色授权',
							width : 450,
							height : 450
						};
						var appkey = "pallasli-webapp-orgnization";
						openDocument(appkey, params);
					}
				},
				{
					text : "查询",
					handler : function() {
					}
				},
				{
					text : "删除",
					handler : function() {
						Ext.Msg.alert('提示', '<b>您选中的项目中包含如下'
								+ '系统内置的只读项目</b><br>' + fields
								+ '<font color=red>只读项目不能删除!</font>');
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
			title : "角色信息",
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
			title : '组织结构树',
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
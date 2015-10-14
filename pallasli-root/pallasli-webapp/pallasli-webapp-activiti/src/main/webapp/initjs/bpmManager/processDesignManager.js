(function(){
	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		width:"1200",
		tbar:[  '->',{
			text : "增加",
			handler : function() {
			}
		},'-',{
			text : "编辑",
			handler : function() {
			}
		},'-',{
			text : "删除",
			handler : function() {
			}
		},'-',{
			text : "发布",
			handler : function() {
			}
		},'-',{
			text : "取消发布",
			handler : function() {
			}
		},'-',{
			text : "导入",
			handler : function() {
			}
		},'-',{
			text : "导出",
			handler : function() {
			}
		}, '-',{
			text : "搜索",
			handler : function() {
				var me=grid;
				me.load("/pallasli-webapp-activiti/bpmtest/taskDoneList?userId=kermit");
			}
		}],
		thead : [ {
			"dataIndex" : "assignee",
			"width" : 200,
			"text" : "流程名称"
		}, {
			"dataIndex" : "assignee",
			"width" : 100,
			"text" : "流程key"
		}, {
			"dataIndex" : "assignee",
			"width" : 320,
			"text" : "流程描述"
		}, {
			"dataIndex" : "assignee",
			"width" : 130,
			"text" : "最后一次修改时间"
		}, {
			"dataIndex" : "assignee",
			"width" : 60,
			"text" : "版本号"
		}, {
			"dataIndex" : "assignee",
			"width" : 40,
			"text" : "",
			"renderer" : function(){
				return "编辑";
			}
		}, {
			"dataIndex" : "assignee",
			"width" : 40,
			"text" : "",
			"renderer" : function(){
				return "删除";
			}
		}, {
			"dataIndex" : "assignee",
			"width" : 100,
			"text" : "",
			"renderer" : function(){
				return "发布／取消发布";
			}
		}, {
			"dataIndex" : "assignee",
			"width" : 40,
			"text" : "",
			"renderer" : function(){
				return "导出";
			}
		}],
		datastore : [ ]
	});
	new Pallasli.window.ViewPort({
		layout:"column",
		
		items : [grid]
	});

})();

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script language='javascript'>
	Ext.require([ 'Ext.direct.*', 'Ext.data.*', 'Ext.tree.*' ]);
	Ext.onReady(function() {

		var panel = Ext.getCmp("${panelId}");
		
		var gridStoreFields = [ "assignee"];
		var grid_columns = [];

		var grid_toolbar = [];

		Ext.define('GridStoreModel', {
			extend : 'Ext.data.Model',
			fields : gridStoreFields,
			proxy : {
				type : 'direct',
				api : {
					read : TaskDirectAction.taskDoneList
				},
				paramOrder : [ 'userId' ],
				extraParams : {
					'userId' : 'kermit'
				}
			}

		});
		var grid_store = Ext.create('Ext.data.Store', {
			model : 'GridStoreModel',
			autoLoad : true
		});
		var grid = Ext.create("Ext.grid.Panel", {
			border : false,
			stripeRows : true,
			lineBreak : true,
			region : "center",
			loadMask : {
				msg : '正在装载...'
			},
			tbar : grid_toolbar,
			columns : []
		});
		
		
		var tree = Ext.create('Ext.tree.Panel', {
			title : '流程管理',
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
					url : '/pallasli-webapp-activiti/jsppage.do?url=bpmManager/testData/processCatalogData' 
				}
			}),
			region : "west",
			hideHeaders : true,
			rootVisible : false,

			listeners : {
				itemclick : function(a,b,c) {
					var params = {
						title : b.raw.text,
						url : b.raw.url
					};
					openTab(params);
				}
			},
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
			items : [ grid ,tree]
		});

		grid.reconfigure(grid_store,grid_columns);
		panel.add(ui);
		
	});
</script>

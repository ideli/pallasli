(function(){
	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		width:"1200",
		tbar:[ {
			text : "增加行",
			handler : function() {
			}
		},{
			text : "搜索",
			handler : function() {
				var me=grid;
				me.load("/pallasli-webapp-activiti/bpmtest/taskDoneList?userId=kermit");
			}
		}],
		thead : [  {
			"dataIndex" : "assignee",
			"width" : 100,
			"text" : "分类"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "编码"
		}, {
			"dataIndex" : "assignee",
			"width" : 550,
			"text" : "内容"
		}, {
			"dataIndex" : "assignee",
			"width" : 50,
			"text" : "",
			"renderer" : function(){
				return "保存";
			}
		}, {
			"dataIndex" : "assignee",
			"width" : 50,
			"text" : "",
			"renderer" : function(){
				return "删除";
			}
		} ],
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
	Ext
			.onReady(function() {

				var panel = Ext.getCmp("${panelId}");

				var unSelectedGridStoreFields = [ {
					name : "id",
					mapping : "id"
				}, {
					name : "firstName",
					mapping : "firstName"
				}, {
					name : "lastName",
					mapping : "lastName"
				} ];
				var un_selected_grid_columns = [ {
					"dataIndex" : "id",
					"text" : "id"
				}, {
					"dataIndex" : "firstName",
					"text" : "姓",
					"flex" : 1,
					"align" : "left"
				}, {
					"dataIndex" : "lastName",
					"text" : "名",
					"flex" : 1,
					"align" : "left"
				} ];

				Ext.define('UnSelectedGridStoreModel', {
					extend : 'Ext.data.Model',
					fields : unSelectedGridStoreFields

				});
				var un_selected_grid_store = Ext.create('Ext.data.ArrayStore',
						{
							model : 'UnSelectedGridStoreModel',
						});
				var selectedGridStoreFields = [ {
					name : "id",
					mapping : "id"
				}, {
					name : "firstName",
					mapping : "firstName"
				}, {
					name : "lastName",
					mapping : "lastName"
				} ];
				var selected_grid_columns = [ {
					"dataIndex" : "id",
					"text" : "id"
				}, {
					"dataIndex" : "firstName",
					"text" : "姓",
					"flex" : 1,
					"align" : "left"
				}, {
					"dataIndex" : "lastName",
					"text" : "名",
					"flex" : 1,
					"align" : "left"
				} ];

				Ext.define('SelectedGridStoreModel', {
					extend : 'Ext.data.Model',
					fields : selectedGridStoreFields

				});
				var selected_grid_store = Ext.create('Ext.data.ArrayStore', {
					model : 'SelectedGridStoreModel',
				});

				Ext.Ajax.request({
					url : '/pallas_activiti/workflow/group/selectUsers.sp',
					params : {},
					success : function(response) {
						var text = response.responseText;
						selected_grid_store.loadData(eval(text));
					}
				});
				Ext.Ajax.request({
					url : '/pallas_activiti/workflow/group/unSelectUsers.sp',
					params : {},
					success : function(response) {
						var text = response.responseText;
						un_selected_grid_store.loadData(eval(text));
					}
				});

				var unSelectedUsersPanel = Ext.create("Ext.grid.Panel", {
					title : "待选用户",
					region : "west",
					width : 400,
					stripeRows : true,
					lineBreak : false,
					cellSelect : true,
					loadMask : {
						msg : '正在装载...'
					},
					columns : un_selected_grid_columns,
					store : un_selected_grid_store
				});
				var actionsPanel = Ext
						.create(
								"Ext.panel.Panel",
								{
									layout : "column",
									region : "center",
									items : [
											{
												margin : "100 0 0 5",
												xtype : "button",
												columnWidth : 0.95,
												text : ">"
											},
											{
												margin : "30 0 0 5",
												xtype : "button",
												columnWidth : 0.95,
												text : "<"
					},{
						margin:"30 0 0 5",
						xtype:"button",columnWidth: 0.95,
						text:">>"
											}, {
												margin : "30 0 0 5",
												xtype : "button",
												columnWidth : 0.95,
												text : "<<"
											} ]
								});
				var selectedUsersPanel = Ext.create("Ext.grid.Panel", {
					title : "已选用户",
					region : "east",
					width : 400,
					stripeRows : true,
					lineBreak : false,
					cellSelect : true,
					loadMask : {
						msg : '正在装载...'
					},
					columns : selected_grid_columns,
					store : selected_grid_store

				});

				var ui = Ext.create('Ext.Panel', {
					width : 500,
					border : false,
					layout : "border",
					items : [ selectedUsersPanel, actionsPanel,
							unSelectedUsersPanel ]
				});
				panel.add(ui);

			});
</script>

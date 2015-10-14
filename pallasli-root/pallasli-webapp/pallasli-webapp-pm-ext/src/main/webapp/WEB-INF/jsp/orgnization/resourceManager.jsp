 
<%
	String panelId = request.getParameter("panelId"); 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<script language='javascript'>
		Ext.onReady(function(){
			var gridStoreFields=[ 
			                {name:"id",mapping:"id"},
			                {name:"key",mapping:"key"},
			                {name:"name",mapping:"name"},
			                {name:"createTime",mapping:"createTime",type:"date" },
			                {name:"lastUpdateTime",mapping:"lastUpdateTime",type:"date"},
			                {name:"metaInfo",mapping:"metaInfo",type:"json"}
			        ];
			 var grid_columns = [
							{
								"dataIndex":"id",
								"text":"id"
							},{
								"dataIndex":"key",
								"text":"资源名称",
								"flex":1,
								"align":"left"
							},{
								"dataIndex":"name",
								"text":"描述",
								"flex":1,
								"align":"left"
							},{
								"dataIndex":"name",
								"text":"资源类型",
								"flex":1,
								"align":"left"
							},{
								"dataIndex":"name",
								"text":"资源路径",
								"flex":1,
								"align":"left"
							},{
								"dataIndex":"name",
								"text":"所属权限",
								"flex":1,
								"align":"left"
							}];
			 
			 var grid_toolbar = [Ext.create("Ext.form.field.Text",{
					emptyText:"所属模块"
						
				}),Ext.create("Ext.form.field.Text",{
					emptyText:"资源名"
					
				}),{
					text : "搜索",
					handler:function(){

					}
				},{
					text : "查看",
					handler:function(){

					}
				},{
					text : "修改",
					handler:function(){

					}
				},{
					text : "删除",
					handler:function(){

					}
				},{
					text : "增加模块",
					handler:function(){

					}
				},{
					text : "增加功能",
					handler:function(){

					}
				},{
					text : "归属权限设置",
					handler:function(){

					}
				}];

				
				
				
				var panel=Ext.getCmp("<%=panelId%>");
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
</html>

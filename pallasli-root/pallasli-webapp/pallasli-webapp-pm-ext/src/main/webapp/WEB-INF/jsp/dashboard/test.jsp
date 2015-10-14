 
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
								"dataIndex":"name",
								"text":"任务名称",
								"flex":1,
								"align":"left"
							},{
								"dataIndex":"name",
								"text":"所属项目",
								"flex":1,
								"align":"left"
							},{
								"dataIndex":"name",
								"text":"build",
								"flex":1,
								"align":"left"
							},{
								"dataIndex":"name",
								"text":"开始日期",
								"flex":1,
								"align":"left"
							},{
								"dataIndex":"name",
								"text":"结束日期",
								"flex":1,
								"align":"left"
							},{
								"dataIndex":"name",
								"text":"状态",
								"flex":1,
								"align":"left"
							}];
			 
			 var grid_toolbar = [Ext.create("Ext.form.field.Text",{
					emptyText:"请输入姓名"
					
				}),{
					text : "关联用例",
					handler:function(){

					}
				},{
					text : "编辑",
					handler:function(){

					}
				},{
					text : "删除",
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

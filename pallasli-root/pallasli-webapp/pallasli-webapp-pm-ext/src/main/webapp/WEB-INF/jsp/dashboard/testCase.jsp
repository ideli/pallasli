 
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
								"text":"优先级",
								"flex":1,
								"align":"left"
							},{
								"dataIndex":"name",
								"text":"用例标题",
								"flex":1,
								"align":"left"
							},{
								"dataIndex":"name",
								"text":"类型",
								"flex":1,
								"align":"left"
							},{
								"dataIndex":"name",
								"text":"状态",
								"flex":1,
								"align":"left"
							},{
								"dataIndex":"name",
								"text":"创建者",
								"flex":1,
								"align":"left"
							},{
								"dataIndex":"name",
								"text":"创建时间",
								"flex":1,
								"align":"left"
							}];
			 
			 var grid_toolbar = [Ext.create("Ext.form.field.ComboBox",{
					emptyText:"查询分类",
					displayField:"display",
					valueField:"value",
				    queryMode: 'local',
					store:Ext.create("Ext.data.ArrayStore",{
						data:[["01","指派给我"],["02","由我创建"]],
						fields: [ 'value','display']
							
					})
					
				}),Ext.create("Ext.form.field.Text",{
					emptyText:"请输入姓名"
					
				}),{
					text : "编辑",
					handler:function(){

					}
				},{
					text : "查询",
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

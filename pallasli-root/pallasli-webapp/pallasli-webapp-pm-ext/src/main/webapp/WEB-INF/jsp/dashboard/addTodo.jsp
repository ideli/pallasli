 
<%
	String panelId = request.getParameter("panelId"); 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<script language='javascript'>
		Ext.onReady(function(){
				
				var panel=Ext.getCmp("<%=panelId%>");
				var form=Ext.create("Ext.form.Panel",{
					items:[{
								fieldLabel:"日期",
								xtype:"textfield"
							},{
								fieldLabel:"类型",
								xtype:"textfield"
							},{
								fieldLabel:"优先级",
								xtype:"textfield"
							},{
								fieldLabel:"名称",
								xtype:"textfield"
							},{
								fieldLabel:"描述",
								xtype:"textfield"
							},{
								fieldLabel:"状态",
								xtype:"textfield"
							},{
								fieldLabel:"起止时间",
								xtype:"textfield"
							},{
								fieldLabel:"私人事务",
								xtype:"textfield"
							}
					       ],
					fbar:[{
									text:"保存"
								},{
									text:"重填"
								}
							]
				});			
				var ui = Ext.create('Ext.Panel', {
					width : 500,
					border : false,
					layout : "fit",
					items : [ form ]
				});
				panel.add(ui);
		});
	</script>
</html>

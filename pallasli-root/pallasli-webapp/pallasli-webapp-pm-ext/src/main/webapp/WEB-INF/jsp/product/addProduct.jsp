 
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
								fieldLabel:"产品名称",
								xtype:"textfield"
							},{
								fieldLabel:"产品代号",
								xtype:"textfield"
							},{
								fieldLabel:"产品负责人",
								xtype:"textfield"
							},{
								fieldLabel:"测试负责人",
								xtype:"textfield"
							},{
								fieldLabel:"发布负责人",
								xtype:"textfield"
							},{
								fieldLabel:"产品描述",
								xtype:"textfield"
							},{
								fieldLabel:"访问控制",
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

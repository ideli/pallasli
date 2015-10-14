 
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
								fieldLabel:"所属部门",
								xtype:"textfield"
							},{
								fieldLabel:"用户名",
								xtype:"textfield"
							},{
								fieldLabel:"姓名",
								xtype:"textfield"
							},{
								fieldLabel:"邮箱",
								xtype:"textfield"
							},{
								fieldLabel:"开户日期",
								xtype:"textfield"
							},{
								fieldLabel:"访问次数",
								xtype:"textfield"
							},{
								fieldLabel:"最后登录ip",
								xtype:"textfield"
							},{
								fieldLabel:"最后登录时间",
								xtype:"textfield"
							},{
								fieldLabel:"qq",
								xtype:"textfield"
							},{
								fieldLabel:"手机",
								xtype:"textfield"
							},{
								fieldLabel:"电话",
								xtype:"textfield"
							},{
								fieldLabel:"通讯地址",
								xtype:"textfield"
							},{
								fieldLabel:"邮政编码",
								xtype:"textfield"
							}
					       ],
					fbar:[{
									text:"修改"
								},{
									text:"退出"
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

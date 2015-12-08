<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.mixky.engine.workflow.transaction.ClientData"%>
<%
	// 读取参数
	String panelid = request.getParameter("panelid");
	ClientData clientdata = (ClientData)request.getAttribute("clientdata");
%>
<script language='javascript'>
Ext.onReady(function(){
	var panel = Ext.getCmp('<%=panelid%>');
	// 设置状态
	panel.setpWindow.step = Mixky.wasoft.workflow.STEP_ERROR;
	// 设置按钮
	panel.setpWindow.showButtons(false, false);
	// 设置窗口标题
	panel.setpWindow.setFlowTitle('错误信息提示');
});
</script>
<table width=100% cellpadding=3>
	<tr>
		<td>当前节点 ：</td>
		<td><%=clientdata.sourceNode==null?"未知":clientdata.sourceNode.getF_caption()%></td>
	</tr>
	<tr>
		<td>消息内容 ：</td>
		<td><%=clientdata.message%></td>
	</tr>
</table>
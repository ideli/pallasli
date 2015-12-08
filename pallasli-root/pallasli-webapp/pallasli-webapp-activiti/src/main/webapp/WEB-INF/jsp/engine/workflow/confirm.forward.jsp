<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.mixky.engine.workflow.transaction.ClientData"%>
<%
	// 读取参数
	String panelid = request.getParameter("panelid");
	ClientData clientdata = (ClientData)request.getAttribute("clientdata");
	String processers = "";
	if(clientdata.processors!=null){
		for(int i=0;i<clientdata.processors.size();i++){
			processers = processers + clientdata.processors.get(i).getF_caption() + ";";
		}
	}
	String assistants = "";
	if(clientdata.assistants!=null){
		for(int i=0;i<clientdata.assistants.size();i++){
			assistants = assistants + clientdata.assistants.get(i).getF_caption() + ";";
		}
	}
	String readers = "";
	if(clientdata.readers!=null){
		for(int i=0;i<clientdata.readers.size();i++){
			readers = readers + clientdata.readers.get(i).getF_caption() + ";";
		}
	}
%>
<script language='javascript'>
Ext.onReady(function(){
	var panel = Ext.getCmp('<%=panelid%>');
	// 设置状态
	panel.setpWindow.step = Mixky.wasoft.workflow.STEP_CONFIRM;
	// 设置按钮
	panel.setpWindow.showButtons(true, false);
	// 设置窗口标题
	panel.setpWindow.setFlowTitle('转办确认窗口');
	// 提交确认
	panel.beforeSubmit = function(params){
		return true;
	}
});
</script>
<table width=100% cellpadding=3>
	<tr>
		<td width=100px>执行操作 ：</td>
		<td>转移办理</td>
	</tr>
	<tr>
		<td>当前节点 ：</td>
		<td><%=clientdata.sourceNode.getF_caption()%></td>
	</tr>
<%
	if(!"".equals(processers)){
%>
	<tr>
		<td>办理人员 ：</td>
		<td><%=processers%></td>
	</tr>
<%
	}
%>
	<tr>
		<td colspan=2>点击完成后确认执行操作</td>
	</tr>
</table>
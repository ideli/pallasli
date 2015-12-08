<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.google.gson.JsonArray"%>
<%@ page import="com.google.gson.JsonPrimitive"%>
<%@ page import="com.mixky.engine.workflow.transaction.ClientData"%>
<%@ page import="com.mixky.engine.design.workflow.Route"%>
<%@page import="com.mixky.engine.workflow.dataservice.TemplateDataService"%>
<%
	// 读取参数
	String panelid = request.getParameter("panelid");
	ClientData clientdata = (ClientData)request.getAttribute("clientdata");
	JsonArray routes = new JsonArray();
	if(clientdata.routes != null){
		for(int i=0;i<clientdata.routes.size();i++){
			Route route = clientdata.routes.get(i);
			JsonArray routearray = new JsonArray();
			routearray.add(new JsonPrimitive(route.getKey()));
			routearray.add(new JsonPrimitive(route.getF_caption()));
			routearray.add(new JsonPrimitive(TemplateDataService.instance().getDestinationNode(route).getF_caption()));
			if(TemplateDataService.instance().getDestinationNode(route).getF_note() != null){
				routearray.add(new JsonPrimitive(TemplateDataService.instance().getDestinationNode(route).getF_note()));
			}else{
				routearray.add(new JsonPrimitive(""));
			}
			routes.add(routearray);
		}
	}
	/*
	// 测试数据
	JsonArray routearray = new JsonArray();
	routearray.add(new JsonPrimitive("test"));
	routearray.add(new JsonPrimitive("测试路由"));
	routearray.add(new JsonPrimitive("测试节点"));
	routearray.add(new JsonPrimitive("路由说明"));
	routes.add(routearray);
	*/
%>
<script language='javascript'>
Ext.onReady(function(){
	var panel = Ext.getCmp('<%=panelid%>');
	// 数据访问
	var store = new Ext.data.SimpleStore({
		idIndex: 0,
		fields : ['routekey', 'routecaption', 'nodecaption', 'note'],
		data : <%=routes%>
	});
	// 路由列表
	var grid = new Ext.grid.GridPanel({
		enableColumnMove : false,
		enableHdMenu : false,
		autoExpandColumn : 'note',
		store : store,
		sm : new Ext.grid.CheckboxSelectionModel({singleSelect : true}),
		columns : [new Ext.grid.RowNumberer(), {
			id : 'routecaption',
			dataIndex : 'routecaption',
			header : '路由名称'
		},{
			id : 'nodecaption',
			dataIndex : 'nodecaption',
			header : '目标节点'
		},{
			id : 'note',
			dataIndex : 'note',
			header : '说明'
		}]
	});
	// 提交表单数据
	panel.beforeSubmit = function(params){
		if(grid.getSelectionModel().getCount() == 0){
			return false;
		}else{
			params.routekey = grid.getSelectionModel().getSelected().get('routekey');
			return true;
		}
	}
	
	panel.add(grid);
	panel.doLayout();
<%
	if(clientdata.route != null){
%>
	// 初始化已选择路由
	var index = store.indexOfId('<%=clientdata.route.getKey()%>');
	grid.getSelectionModel().selectRow(index);
<%
	}
%>
	// 设置状态
	panel.setpWindow.step = Mixky.wasoft.workflow.STEP_NODESELECT;
	// 设置按钮
	panel.setpWindow.showButtons(false, true);
	// 设置窗口标题
	panel.setpWindow.setFlowTitle('选择下一办理节点');
});
</script>
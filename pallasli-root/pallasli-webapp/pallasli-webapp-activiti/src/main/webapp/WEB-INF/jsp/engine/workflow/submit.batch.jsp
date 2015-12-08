<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.JsonArray"%>
<%@page import="com.google.gson.JsonPrimitive"%>
<%@page import="com.mixky.engine.workflow.dataservice.InstanceDataService"%>
<%@page import="com.mixky.engine.workflow.FlowInstance"%>
<%@page import="com.mixky.engine.workflow.instance.FlowLog"%>
<%@page import="com.mixky.engine.certification.MixkyUserCertification"%>
<%@page import="com.mixky.engine.organization.dao.User"%>
<%@ page contentType="text/html; charset=utf-8"%>

<%
	// 流程实例列表
	Object object = request.getAttribute("flowlogs");

	JsonArray datas = new JsonArray();
	if (object != null) {
		List<FlowLog> flowlogs = (List<FlowLog>)object;
		for (int i = 0; i < flowlogs.size(); i ++) {
			JsonArray data = new JsonArray();
			JsonPrimitive idItem = new JsonPrimitive(flowlogs.get(i).getId());
			data.add(idItem);
			JsonPrimitive titleItem = new JsonPrimitive(flowlogs.get(i).getF_data_title());
			data.add(titleItem);
			datas.add(data);
		}
	}
	
	object = request.getAttribute("routeButtons");
	// 流程路由按钮
	JsonArray buttons = new JsonArray();
	if (object != null) {
		List<Map<String, String>> routeButtons = (List<Map<String, String>>)object;
		if (routeButtons.size() > 0) {
			for (int i = 0; i < routeButtons.size(); i ++) {
				Map<String, String> map = (Map<String, String>)routeButtons.get(i);
				JsonObject btn = new JsonObject();
				btn.addProperty("text", map.get("actionName"));
				btn.addProperty("routeKey", map.get("routeKey"));
				buttons.add(btn);
			}
		}
	}
%>

<script language='javascript'>
Ext.onReady(function(){
	var panel = Ext.getCmp('p-mutilapproval');

	var store = new Ext.data.ArrayStore({
		idIndex: 0,
		fields : [
			'ID', 
			'F_TITLE',
			'ACTION'
		],
		data : <%=datas%>
	});

	var fnSubmit = function(a){
		var routeKey = a.routeKey;
		var ids = [];
		for (var i=0; i<store.getCount(); i++) {
			//if(store.getAt(i).get('F_FLOWLOGID') > 0){
			ids.push(store.getAt(i).get('ID'));
			//}
		}
		var opinion = panel.getOpinion();
		WorkFlowInstanceDirect.batchSubmit(routeKey, opinion, ids, function(result, e) {
			if (result && result.success) {
				for (var i=0; i<result.results.length; i++) {
					var index = store.find('ID', result.results[i].flowlogId);
					if (index >= 0) {
						var record = store.getAt(index);
						if(!result.results[i].message || result.results[i].message == '') {
							record.set('ACTION', "成功");
						} else {
							record.set('ACTION', result.results[i].message);
						}
					}
				}
				store.commitChanges();
				MixkyApp.showInfoMessage('所选审批项已审批完毕' , '信息提示');
			}
		});
	}

	var buttons = <%=buttons%>;
	for (var i=0; i<buttons.length; i++) {
		buttons[i].handler = fnSubmit;
	}
	buttons.push({
		text : '关闭',
		handler : function() {
			panel.findParentByType('window').close();
		}
	});
	
	var grid = new Ext.grid.GridPanel({
		height : 260,
		store : store,
		autoExpandColumn : 'F_TITLE',
		columns : [
			new Ext.grid.RowNumberer(),
			{header : '标题', id : 'F_TITLE', dataIndex : 'F_TITLE'},
			{header : '处理结果', width:160, id : 'ACTION', dataIndex : 'ACTION', renderer : function(v){
				if (v && v != '' && v != '成功') {
					return '<font color=red>' + v + '</font>';
				} else {
					return v;
				}
			}}
		]
	})

	var form = new Ext.form.FormPanel({
		border : false,
		items : [grid, {
			xtype : 'textarea',
			name : "OPINION",
			anchor : '100%',
			fieldLabel : '审批意见'
		}],
		buttons : buttons
	})

	panel.getOpinion = function(){
		return form.getForm().findField('OPINION').getValue();
	}
	
	panel.add(form);
	panel.doLayout();
});
</script>
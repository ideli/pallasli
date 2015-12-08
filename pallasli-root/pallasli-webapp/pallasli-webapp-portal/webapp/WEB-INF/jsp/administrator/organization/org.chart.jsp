<%@ page contentType="text/html; charset=utf-8"%>
<%
	String id = request.getParameter("id");
%>
<script language='javascript'>
Ext.onReady(function(){
	var id = '<%=id%>';

	var panel = Ext.getCmp(id);
	panel.setTitle('组织结构图');

	var orgChartCt = new Ext.Panel({
         layout: 'border',
         defaults: {border: false},
         items: [{                            
             xtype: 'gvisualization',
             visualizationCfg: {allowHtml:true},
             region: 'center',
             bodyStyle: 'padding: 30px;',
             visualizationPkg: 'orgchart',
             columns: ['name','pname']
         }]
     })

	
    
	// 刷新
	panel.refresh = function(){
		//store.load();
	}

	panel.add(orgChartCt);
	panel.doLayout();
});
</script>
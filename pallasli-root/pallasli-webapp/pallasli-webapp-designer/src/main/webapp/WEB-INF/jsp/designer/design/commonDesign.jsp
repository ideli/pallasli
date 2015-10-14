<%@page import="com.pallas.designer.design.bean.SimpleGridAuthPage"%>
<%@page import="com.pallas.designer.design.bean.AuthGrid"%>
<%@page import="com.pallas.designer.design.bean.AuthComp"%>
<%@page import="java.util.List"%>
<%@page import="com.pallas.designer.design.action.AuthPageAction"%>
<%@page import="com.pallas.designer.design.bean.AuthPage"%>
<%@page import="com.pallas.sys.util.JsonFunction"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.JsonArray"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%String basePath=request.getContextPath();
String nid=request.getAttribute("nid").toString();


//根据menuKey，user 获取权限组件
//---过程--
//1.根据menuKey,user过滤权限,pageComplist
//2.根据 pageComplist进行布局
//3.输出到页面
//
//AuthPage authPage = AuthPageAction.instance().loadAuthPage(menu,user);
//
AuthPage authPage = AuthPageAction.instance().loadAuthPage("",0);
List<AuthComp> authCompList=authPage.getAuthCompList();
String columns="";
String api=""; 
String extraParams="";
String buttons="";
String modelName="";
/**for(AuthComp comp:authCompList){
	if(comp instanceof AuthGrid){**/
		AuthComp comp=((SimpleGridAuthPage)authPage).getAuthGrid();
		columns=((AuthGrid)comp).getF_columns().toString();
		api=((AuthGrid)comp).getF_api().toString();
		extraParams=((AuthGrid)comp).getF_extraParams().toString();
		buttons=((AuthGrid)comp).getF_buttons().toString();
		buttons=buttons.replaceAll("\\\\t", "");
		buttons=buttons.replaceAll("\\\\r\\\\n", "");
		System.out.println(buttons);
		modelName=((AuthGrid)comp).getF_modelName();
	/**}
}**/

String title=authPage.getTitle();
String customString=authPage.getCustomScripts(); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript">

        Ext.onReady(function(){
			var api=<%=api%>; 
		    var columns = <%=columns%>;
       		var extraParams=<%=extraParams%>;
			var buttons=<%=buttons%>; 
			var panelId="<%=nid%>";
			var modelName="<%=modelName%>";
			var title="<%=title%>";
		
			<%out.print(authPage.getCustomScripts() + '\n');%>

			
			var parentPanel=Ext.getCmp(panelId);
			
			parentPanel.setTitle(title);
			
			var store = Ext.create('Ext.data.Store', {
			    model: modelName, 
			    autoLoad:true,
				proxy: {  
			          type: 'direct',  
			          api:api,
			          extraParams: extraParams
			    } 
			});
			var grid=Ext.create("Ext.grid.Panel",{
				region:"center",
				stripeRows: true,
				lineBreak : false,
				cellSelect : true,
				loadMask: {msg:'正在装载...'},
				columns : columns,
				dockedItems: [{
				       dock: 'top',
				       xtype: 'toolbar',
				       items: buttons
			    }],
				store : store
			}); 
			  
        	parentPanel.add(grid);
        	parentPanel.doLayout();
        });
    </script>
</head>
</html>

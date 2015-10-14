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
			var menuTypeStore = Ext.create('Ext.data.Store', {
				model: 'MenuType', 
				autoLoad:false,
				proxy : {  
			        type : 'direct',  
			        api : {  
				        read : MenuAction.loadMenuTypes
			        }
			    } 
			});
			var pageTypeStore = Ext.create('Ext.data.Store', {
				model: 'PageType', 
				autoLoad:false,
				proxy : {  
			        type : 'direct',  
			        api : {  
				        read : MenuAction.loadPageTypes
			        }
			    } 
			});
			var menuStore = Ext.create('Ext.data.Store', {
				model: 'MenuPath', 
				autoLoad:false,
				proxy : {  
			        type : 'direct',  
			        api : {  
				        read : MenuAction.loadMenus
			        }
			    } 
			});
			
			var menuTypeCombobox=Ext.create('Ext.form.ComboBox', {
			    fieldLabel: '菜单分类',
			    name:'menuTypeCode',
			    store: menuTypeStore,
			    queryMode: 'remote',
			    displayField: 'menuTypeCaption',
			    valueField: 'menuTypeCode',
			    anchor:'100%'
			});

			var pageTypeCombobox=Ext.create('Ext.form.ComboBox', {
			    fieldLabel: '页面分类',
			    name:'pageTypeCode',
			    store: pageTypeStore,
			    queryMode: 'remote',
			    displayField: 'pageTypeCaption',
			    valueField: 'pageTypeCode',
			    anchor:'100%'
			});
			var menuCombobox=Ext.create('Ext.form.ComboBox', {
			    fieldLabel: '父菜单',
			    name:'parentKey',
			    store: menuStore,
			    queryMode: 'remote',
			    displayField: 'menuCaption',
			    valueField: 'menuKey',
			    anchor:'100%'
			});
        	 var form_addType=Ext.create('Ext.form.Panel',{
                  border: false,
                  fieldDefaults: {
                      labelWidth: 100
                  },
                  defaultType: 'textfield',
                  bodyPadding: 5,
                  floatable: false,
                  items:[
                          	{fieldLabel:'关键字',name:'menuKey',anchor:'100%'},
                          	{fieldLabel:'英文名',name:'menuName',anchor:'100%'},
                          	{fieldLabel:'中文名',name:'menuCaption',anchor:'100%'},
                          	menuTypeCombobox,pageTypeCombobox,menuCombobox , 
                          	{fieldLabel:'页面路径',name:'urlPath',anchor:'100%'},
                          	{fieldLabel:'child页面路径',name:'childUrlPath',anchor:'100%'},
                          	{fieldLabel:'表名',name:'menuTableName',anchor:'100%'},
                          	{fieldLabel:'查询条件',name:'menuWhereSql',anchor:'100%'},
                          	{fieldLabel:'配置项',xtype:'textarea',name:'menuConfig',anchor:'100%'}                          
                  ],
                  bbar:['->',{
                	  text:'保存',handler:function(){
                		 var formValues=form_addType.getForm().getValues(); 
                		 
                		 var rec=new Knowledge({  
				            	    menuName : formValues['menuName'], 
				            	    menuKey : formValues['menuKey'], 
				            	    menuCaption : formValues['menuCaption'], 
				            	    menuTypeCode : formValues['menuTypeCode'], 
				            	    pageTypeCode : formValues['pageTypeCode'], 
				            	    menuWhereSql : formValues['menuWhereSql'], 
				            	    menuTableName : formValues['menuTableName'], 
				            	    urlPath : formValues['urlPath'], 
				            	    childUrlPath : formValues['childUrlPath'], 
				            	    menuConfig : formValues['menuConfig'], 
				            	    parentKey :formValues['parentKey'] 
			            	});  
			            	rec.save({ 
			            	    success:function(rec,opt){  
			            	       gridstore.reload();
			            	    },  
			            	    failure:function(e,op){  
			            	        Ext.Msg.alert('发生错误',op.error);  
			            	    },  
			            	    scope:knowledgePanel  
			            	}); 
                	  }
                  }]
              
             });   
        	 var win = Ext.create('widget.window', {
                  title: '增加节点',
                  closable: true,
                  closeAction: 'hide',
                  width: 600,
                  minWidth: 350,
                  height: 350,
                  modal: true,
                  layout: {
                      type: 'fit',
                      padding: 5
                  },
                  items: [form_addType]
              });
        	 win.show(); 
        });
    </script>
</head>
</html>

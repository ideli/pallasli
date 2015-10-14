<%@page import="com.pallas.sys.util.MashalUtil"%>
<%@page import="com.pallas.sys.util.JsonUtils"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.JsonArray"%>
<%@page import="com.pallas.design.action.MenuDirectAction"%>
<%@page import="com.pallas.design.bean.Menu"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%String basePath=request.getContextPath();
JsonArray data=new JsonArray();
data.add(new JsonObject());
List<Menu> sysMenu=new MenuDirectAction().loadSysMenu(data);
Menu[] a=new Menu[sysMenu.size()];
String menuArray=MashalUtil.marshal(sysMenu);
System.out.println(menuArray);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>设计工具--组件定义</title>
	
   <script type="text/javascript" src="/pallasli-web-js-css/scripts/include-ext.js?theme=classic"></script>
	 <link rel="stylesheet" type="text/css" href="/pallasli-web-js-css/styles/designer/designer.css" />
  <script type="text/javascript" src="<%=basePath%>/extDirect/djn/djn-remote-call-support.js"></script>
  <script type="text/javascript" src="<%=basePath%>/extDirect/ejn/ejn-assert.js"></script>
	<script type="text/javascript" src="<%=basePath%>/extDirect/api/api.js"></script>
	<script type="text/javascript" src="<%=basePath%>/scripts/designer/definedModels.js"></script>
	
	 <style>
.x-panel-header-text-container-default {
  color: #04408c;
  font-size: 11px;
  font-weight: bold;
  font-family: tahoma, arial, verdana, sans-serif;
  line-height: 21px;
  padding: 0 2px 1px;
  text-transform: none;
}
    </style>
    <script type="text/javascript">
	    Ext.require([
	        'Ext.direct.*',
	        'Ext.data.*',
	        'Ext.tree.*'
	    ]);
	    
	    Ext.Loader.setPath({
	        'Pallas.designer.portal': 'scripts/designer/portal'
	    });

        Ext.onReady(function(){
        	var deigener={};
        	
        	function getWindow(urlPath,title){
				var nid=Ext.id();
				 var searchPanel= Ext.create('Ext.panel.Panel',{
						layout:'fit',
						id:nid,
		                border:false,
		            	autoLoad  : {
				            	url : '<%=basePath%>/jsppage.do?url=designer'+urlPath,
				            	scripts : true,
				            	loadScripts : true,
				            	params : {
				            		userId : 1,
				            		nid:nid
				            	}
			            	}
		            	});
		         var searchWin = Ext.create('Ext.window.Window', {  
		                title : title,  
		                width : 1280,  
		                height : 600,  
						layout:'fit',
		                items : searchPanel, 
		                autoScroll : true,  
		                modal : true,  
		                constrain:true,
		                bodyStyle : {  
		                    background : '#ffffff',  
		                    margin : 'auto'  
		                }  
		            }); 
				searchWin.show();
            }
            function getPage(urlPath,title){ 
				 window.open('<%=basePath%>/jsppage.do?url=designer'+urlPath, title);
            }
        	function getPanel(sysMenuKey,title){

            	var nid="workTabPanel"+sysMenuKey;
            	if(Ext.getCmp(nid)){
            		deigener.portal.pageMain.setActiveTab(nid);
		        	return;
            	}
        		var tab=Ext.create('Pallas.designer.portal.SysMenuPage',{
        			id:nid,
        			closable:true,
        			title:title,
    				openviewInMain:openviewInMain,
        			menuKey :sysMenuKey
        		});
            		      
        		deigener.portal.pageMain.add(tab);
        		deigener.portal.pageMain.setActiveTab(tab);
            	 
           }
        	function openviewInMain(workPanel,record){
              	var nid="tabPanel"+record.id;
              	if(Ext.getCmp(nid)){
              		workPanel.setActiveTab(nid);
    		        	return;
              	}
              	var params=record.data;
              	params.nid=nid;
    				 var searchPanel= Ext.create('Ext.panel.Panel',{
    						layout:'fit',
    						title:record.data.text,
    						border:false,
    						closable:true,
    						id:nid,
    		            	autoLoad  : {
    				            	url : '<%=basePath%>/jsppage.do?url=designer/'+record.data.urlPath,
    				            	scripts : true,
    				            	loadScripts : true,
    				            	params :params
    			            	}
    		            	});

    				 workPanel.add(searchPanel);
    				 workPanel.setActiveTab(searchPanel);
             }
        	var menuButtons=<%=menuArray%>;
        	for(var i=0;i<menuButtons.length;i++){
        		menuButtons[i].xtype='button';
        		menuButtons[i].text=menuButtons[i].menuCaption; 
        		menuButtons[i].handler=function(){
        			if(this.pageTypeCode=='01'){
    					getPanel(this.menuKey,this.menuCaption);        				
        			}else if(this.pageTypeCode=='02'){
        				this.urlPath='/home';
    					getPage(this.urlPath,this.menuCaption);        				
        			}else if(this.pageTypeCode=='03'){
    					getWindow(this.urlPath,this.menuCaption);          				
        			}
				};
        	}
        	var tbar=Ext.create("Ext.toolbar.Toolbar", {
        		items:menuButtons
        	                                                   
        	});
			var menuHeader=Ext.create('Pallas.designer.portal.SysHeader',{
				region:'north',
				height:26,
				tbar:tbar
			});
			deigener.portal=Ext.create('Pallas.designer.portal.Portal',{
				menuHeader:menuHeader
			});
          
        	
			Ext.create('Ext.container.Viewport', {
				layout:'fit',
				items:[deigener.portal]
			});
			
        });
    </script>
</head>
</html>

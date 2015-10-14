<%@page import="com.pallasli.utils.MashalUtil"%>
<%@page import="com.pallas.design.bean.Menu"%>
<%@page import="java.util.List"%>
<%@page import="com.pallas.design.action.MenuDirectAction"%>
<%@page import="com.pallasli.utils.FileUtils"%>
<%@page import="com.pallasli.utils.JsonUtils"%>
<%@page import="java.io.File"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonArray"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%String basePath=request.getContextPath();

 
JsonArray menuArray=new MenuDirectAction().loadSysMenus();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>可视化设计工具</title>
	
 	<script type="text/javascript" src="/pallasli-web-js-css/scripts/include-ext.js?theme=classic"></script>
    <link rel="stylesheet" type="text/css" href="/pallasli-web-js-css/styles/designer/designer.css" />
    
	<script type="text/javascript" src="<%=basePath%>/extDirect/djn/djn-remote-call-support.js"></script>
	<script type="text/javascript" src="<%=basePath%>/extDirect/ejn/ejn-assert.js"></script>
	<script type="text/javascript" src="<%=basePath%>/extDirect/api/api.js"></script>
	<script type="text/javascript" src="<%=basePath%>/scripts/design/definedModels.js"></script>
	<script type="text/javascript" src="<%=basePath%>/scripts/design/comp.js"></script>
  
	
	   <%
	   File f=new File(request.getRealPath("/")+"/scripts/design/home/SysMenu.js");
	   String  menuItems=FileUtils.readFileToString(f).trim();//.replaceAll("\\s", "")
	   
	   %>
	
	 <!--style>
.x .x-panel-header-text-container-default {
  color: #04408c;
  font-size: 11px;
  font-weight: bold;
  font-family: tahoma, arial, verdana, sans-serif;
  line-height: 21px;
  padding: 0 2px 1px;
  text-transform: none;
}

    </style-->
    <script type="text/javascript">
    var basePath="<%=basePath%>";
    var menuArray=<%=menuArray.toString()%>;
    for(var i=0;i<  menuArray[1].items.length;i++){
    	var h=menuArray[1].items[i].handler;
    	menuArray[1].items[i].handler=eval(h);
    }
    Ext.require([
     	        'Ext.direct.*',
     	        'Ext.data.*',
     	        'Ext.tree.*'
     	    ]);
    Ext.Loader.setPath({
     	'Pallas.design.portal': '/pallasli-webapp-designer/scripts/design/app',
     	'Pallas.common': '/pallasli-webapp-designer/scripts/design/common',
     	'Pallas.common.window': '/pallasli-webapp-designer/scripts/design/common/window',
         	'Pallas.design.designWin': '/pallasli-webapp-designer/scripts/design/designWin'
    });
    Ext.require([ 'Pallas.common.window.Form' ]);
    
    function getWindow(urlPath,title){
		var nid=Ext.id();
		 var searchPanel= Ext.create('Ext.panel.Panel',{
				layout:'fit',
				id:nid,
                border:false,
                constrain:true,
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
    function sysMenuAction(){
		if(this.pageTypeCode=='01'){
			getPanel(this.menuKey,this.menuCaption);
		}else if(this.pageTypeCode=='02'){
			this.urlPath='/home';
			getPage(this.urlPath,this.menuCaption);
		}else if(this.pageTypeCode=='03'){
			getWindow(this.urlPath,this.menuCaption);
		}
	}
    function getPage(urlPath,title){ 
		 window.open('<%=basePath%>/jsppage.do?url=design'+urlPath, title);
   }
   var getPanel=function(sysMenuKey,title){
		var nid="workTabPanel"+sysMenuKey;
       if(Ext.getCmp(nid)){
           deigener.portal.pageMain.setActiveTab(nid);
		    return;
       }
       var tab=Ext.create('Pallas.design.portal.SysMenuPage',{
        			id:nid,
        			closable:true,
        			title:title,
    				openviewInMain:openviewInMain,
    				open:open, 
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
           	if(!record.data.urlPath||record.data.urlPath.trim()==''){
           		return;
           	}
           	var params=record.data;
           	params.tableName=params.nodeName;
           	params.projectName='projectManager';
           	params.nid=nid;
    				 var searchPanel= Ext.create('Ext.panel.Panel',{
    						layout:'fit',
    						title:record.data.text,
    						border:false,
    						closable:true,
    						id:nid,
			            	params :params,
    		            	autoLoad  : {
    				            	url : '<%=basePath%>/jsppage.do?url=design_all/'+record.data.urlPath,
    				            	scripts : true,
    				            	loadScripts : true,
    				            	params :params
    			            	}
    		            	}); 
    				 workPanel.add(searchPanel);
    				 workPanel.setActiveTab(searchPanel);
        }
      
        var deigener={};
        function open(workPanel){

           	var nid="tabPanel"+1;
           	if(Ext.getCmp(nid)){
           		workPanel.setActiveTab(nid);
    		        	return;
           	} 
           	var params={};
           	params.nid=nid;
    				 var searchPanel= Ext.create('Ext.panel.Panel',{
    						layout:'fit', 
    						border:false,
    						closable:true,
    						id:nid,
    		            	autoLoad  : {
    				            	url : '<%=basePath%>/jsppage.do?url=designer/design/commonDesign',
    				            	scripts : true,
    				            	loadScripts : true,
    				            	params :params
    			            	}
    		            	}); 
    				 workPanel.add(searchPanel);
    				 workPanel.setActiveTab(searchPanel);
        return;
    		var tmpTab = workPanel.add({
    				title:"test",closable:true,
    				items:[{xtype:"textfield",fieldLabel:"test"},
    				       {xtype:"textarea",fieldLabel:"test"},
    					   {xtype:"combobox",fieldLabel:"test"}
    				]
    			});
    		
    		workPanel.setActiveTab(tmpTab);
    	}
       
             	
    Ext.onReady(function(){
             	

       
             	var menuButtons=menuArray;
              console.log(menuButtons)
             	var tbar=Ext.create("Ext.toolbar.Toolbar", {
             		items:menuButtons                                 
             	});
     			var menuHeader=Ext.create('Pallas.design.portal.SysHeader',{
     				region:'north',
     				height:26,
     				tbar:tbar
     			});
     			deigener.portal=Ext.create('Pallas.design.portal.Portal',{
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

<%@page import="com.pallas.sys.util.FileUtils"%>
<%@page import="com.pallas.sys.util.JsonUtils"%>
<%@page import="java.io.File"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonArray"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%String basePath=request.getContextPath();%>
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
  
	
	   <%
	   File f=new File(request.getRealPath("/")+"/scripts/designer/home/SysMenu.js");
	   System.out.println(f.exists());
	   System.out.println(f);
	   System.out.println(f);
	   System.out.println(f);
	   String  menuItems=FileUtils.readFileToString(f).trim();//.replaceAll("\\s", "")
	   
	   %>
	
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
    var basePath="<%=basePath%>";
    Ext.Loader.setPath({
        'Pallas.designer': 'scripts/designer'
    });
	    Ext.require([
	        'Ext.direct.*',
	        'Ext.data.*',
	        'Ext.tree.*',
	        'Pallas.designer.DesignWindow'
	    ]);
	    Pallas.design.api.REMOTING_API.enableBuffer = 0;  
	    var remotingProvider =Ext.Direct.addProvider(Pallas.design.api.REMOTING_API);  
	    Djn.RemoteCallSupport.addCallValidation(remotingProvider);
	    Djn.RemoteCallSupport.validateCalls = true;
	    
        Ext.onReady(function(){
        	Ext.define('PageComponent', {
				extend : 'Ext.data.Model',
				fields : [ {
					name : 'id',
					type : 'string'
				}, {
					name : 'text',
					type : 'string'
				}, {
					name : 'path',
					type : 'string'
				}, {
					name : 'attributes',
					type : 'json'
				} ]
			});

			var store = Ext
					.create(
							'Ext.data.TreeStore',
							{
								model : 'PageComponent',
								proxy : {
									type : 'ajax',
									url : 'data/designer/modules/module1.json'
								},
								reader : {
									type : 'json'
								},
								root : {
									text : 'root',
									id : '0',
								}
							});

        	
        	
        	Ext.WorkshopDisplayPanel=Ext.extend(Ext.tab.Panel ,{
    			autoScroll:false,
    			border:false,
    			initComponent: function(){
        			Ext.WorkshopDisplayPanel.superclass.initComponent.call(this);
    			}
    		});
        	Ext.WorkshopDesignPanel=Ext.extend(Ext.panel.Panel ,{
            	title:'页面组件、布局设置initialConfig',
    			xtype:"panel",
    			autoScroll:false,
    			border:false,
    			initComponent: function(){
        			Ext.WorkshopDesignPanel.superclass.initComponent.call(this);
    			}
    		});
        	function open(){
        		var tmpTab = portal.workshopPanel.workshopDisplayPanel.add({
        				title:"test",closable:true,
        				items:[{xtype:"textfield",fieldLabel:"test"},
        				       {xtype:"textarea",fieldLabel:"test"},
        					   {xtype:"combobox",fieldLabel:"test"}
        				]
        			});
        		
        		portal.workshopPanel.workshopDisplayPanel.setActiveTab(tmpTab);
        	}
        	function design(){
        		var tmpTab=portal.workshopPanel.workshopDisplayPanel.getActiveTab();
        		var win=new Pallas.designer.DesignWindow({initComponents:tmpTab.items.items}).createWindow();
	        	win.on(
						"beforeclose",
						function() {
							if (win.items.items[2].items.items) {
								var len = win.items.items[2].items.items.length;
								for (var tmpi = 0; tmpi < len; tmpi++) {
									tmpTab.add(win.items.items[2].items.items[0]);

								}
								tmpTab.doLayout();

							}
						});
        	}
        	Ext.MoudleTreeDisplayPanel=Ext.extend(Ext.tree.Panel ,{
        		store: store,
    			xtype:"panel",
    			autoScroll:false,
    			border:false,
    			rootVisible:false,
    			viewConfig: {
    		        plugins: {
    		            ptype: 'treeviewdragdrop'
    		        }
    		    },
    		    listeners:{
    		    	itemcontextmenu:function(panel,record, item, index,  e,  eOpts){
    		    		var contextmenu=Ext.create('Ext.menu.Menu', {
    		    		    width: 100,
    		    		    height: 100,
    		    		    plain: true,
    		    		    floating: true,  // usually you want this set to True (default)
    		    		    items: [{
    		    		        text: '设计',handler:function(){
    		    		        	//open();
    		    		        	design();
    		    		        }
    		    		    },{
    		    		        text: '删除'
    		    		    },{
    		    		        text: '打开',handler:function(){
    		    		        	open();
    		    		        }
    		    		    }]
    		    		});
    		    		contextmenu.showAt(e.browserEvent.clientX,e.browserEvent.clientY);

    		    	}
    		    },
    			initComponent: function(){
        			Ext.MoudleTreeDisplayPanel.superclass.initComponent.call(this);
    			}
    		}); 
           
        	Ext.WorkshopPanel=Ext.extend(Ext.panel.Panel ,{
    			xtype:"panel",
    			layout:"border",
    			autoScroll:false,
    			border:false,
    			workshopDisplayPanel:null,
    			workshopDesignPanel:null,
    			initComponent: function(){
					this.workshopDisplayPanel=new Ext.WorkshopDisplayPanel({region:'center'});
					this.workshopDesignPanel=new Ext.WorkshopDesignPanel({
						region:'east',width:200,split:true,collapsible:true,collapsed:true
					});
					this.items=[this.workshopDisplayPanel,this.workshopDesignPanel];
					Ext.WorkshopPanel.superclass.initComponent.call(this);
    			}
    		});
        	Ext.SysMenuPanel=Ext.extend(Ext.panel.Panel ,{
    			xtype:"panel",
    			layout:"border",
    			autoScroll:false,
    			border:false,
    			sysMenuDisplayPanel:null, 
    			initComponent: function(){
					this.sysMenuDisplayPanel=Ext.create("Pallas.designer.home.SysMenuDisplayPanel" ,{
						layout:"column",
						region:'center',
						menuItems:<%=menuItems%>,
		    			bodyStyle:{'background-color': 'transparent',"padding":"5px","margin":"0px"},
		    			border:false
				}); 
					this.items=[this.sysMenuDisplayPanel ];
					Ext.SysMenuPanel.superclass.initComponent.call(this);
    			}
    		});
        	Ext.MoudleTreePanel=Ext.extend(Ext.panel.Panel ,{
    			xtype:"panel",
    			autoScroll:false,
    			layout:"border",
    			border:false,
    			moudleTreeDisplayPanel:null,
    			moudleTreeDesignPanel:null,
    			initComponent: function(){
					this.moudleTreeDisplayPanel=new Ext.MoudleTreeDisplayPanel({region:'center'});
					this.moudleTreeDesignPanel=Ext.create(
							'Pallas.designer.CompPropertiesPanel', {
								title : '页面属性配置',
								region : "south"
							});
						 
					this.items=[this.moudleTreeDisplayPanel,this.moudleTreeDesignPanel];
					Ext.MoudleTreePanel.superclass.initComponent.call(this);
    			}
    		});
        	Ext.PreviewPortal=Ext.extend(Ext.panel.Panel ,{
        			xtype:"panel",
        			layout:"border",
        			autoScroll:false,
        			border:false,
        			sysMenuPanel:null,
        			moudleTreePanel:null,
        			workshopPanel:null,
        			initComponent: function(){
						this.sysMenuPanel=new Ext.SysMenuPanel({
							region:'north',split:true,height:75
							});
						this.moudleTreePanel=new Ext.MoudleTreePanel({
							region:'west',width:300,split:true,collapsible:true
							});
						this.workshopPanel=new Ext.WorkshopPanel({region:'center'});
						this.items=[this.sysMenuPanel,this.moudleTreePanel,this.workshopPanel];
        				Ext.PreviewPortal.superclass.initComponent.call(this);
        			}
        		});
        	var portal=new Ext.PreviewPortal();
			Ext.create('Ext.container.Viewport', {
				layout:'fit',
				items:[portal]
			});
			
			portal.doLayout();
        });
    </script>
</head>
</html>

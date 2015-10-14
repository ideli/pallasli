<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%String basePath=request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>extjs-login</title>
	
	<script type="text/javascript" src="<%=basePath%>/scripts/include-ext.js"></script>
	<script type="text/javascript" src="<%=basePath%>/scripts/treemenus.js"></script>
	 <style>

.x-panel-header-default-horizontal-noborder{
	padding: 3px 3px 3px 3px;
}
.x-panel-header-default-horizontal{
	padding: 3px 3px 3px 3px;
}
.x-panel-header-default-vertical-noborder{
	padding: 3px 3px 3px 3px;
}
.x-panel-header-default-vertical{
	padding: 3px 3px 3px 3px;
}

.btn-comp {
		padding:0px;
		margin:'0px;
		border-style:none;
        background-color: #FFF;
        background-image: url() ;
    }
    </style>
    <script type="text/javascript">

	    Ext.require([
	        'Ext.direct.*',
	        'Ext.data.*',
	        'Ext.tree.*'
	    ]);
    
        Ext.onReady(function(){
           
            function preview(){

				 var searchPanel= Ext.create('Ext.panel.Panel',{
						layout:'fit',
						id:'ddddd',
		            	autoLoad  : {
				            	url : '/designer/jsppage.do?url=preview/preview',
				            	scripts : true,
				            	loadScripts : true,
				            	params : {
				            		userId : 1
				            	}
			            	}
		            	});
		         var searchWin = Ext.create('Ext.window.Window', {  
		                title : "可视化预览",  
		                width : 1024,  
		                height : 600,  
						layout:'fit',
		                items : searchPanel, 
		                autoScroll : true,  
		                modal : true,  
		                bodyStyle : {  
		                    background : '#ffffff',  
		                    margin : 'auto'  
		                }  
		            }); 
				searchWin.show();
            }
            
            
            function getPanel(treeType){

            	var treeStore=portal.treePanel.getRootNode();
            	treeStore.removeAll();
            	var store=portal.treePanel.getStore();
            	Ext.apply(store.proxy.extraParams,{treeType :treeType});
            	store.load();
           }
            

            function openviewInMain(url){

				 var searchPanel= Ext.create('Ext.panel.Panel',{
						layout:'fit',
						id:'eeeee',
		            	autoLoad  : {
				            	url : '/designer/jsppage.do?url='+url,
				            	scripts : true,
				            	loadScripts : true,
				            	params : {
				            		userId : 1
				            	}
			            	}
		            	});

		        	Ext.getCmp('main').add(searchPanel);
           }
            Ext.define('ctreemodel', {   
                extend: 'Ext.data.Model',   
                fields: [   
                    {name: 'aa',  type: 'string'},   
                    {name: 'text',  type: 'string'} ,   
                    {name: 'url',  type: 'string'}   
                ]   
            });
        	Ext.PortalPanel=Ext.extend(Ext.panel.Panel ,{
        			xtype:"panel",
        			layout:"border",
        			autoScroll:false,
        			treePanel:null,
        			initComponent: function(){
        				var treePanel=Ext.create('Ext.tree.Panel', {
    		                title: 'Simple Tree',
    						region:"west",
    		                width: 170,
    		                height: 150,
    		                listeners:{
    		                	itemclick :function( view,  record, item, index, e,  eOpts){
    		                		console.log(record);
    		                		openviewInMain(record.data.url);
    		                	}
    		                },
    		                store: Ext.create('Ext.data.TreeStore', {
        		                model: 'ctreemodel',
    		                    root: {
    		                        expanded: true,
    		                    },   
    		                    proxy: {   
    		                        type: 'ajax',   
    		                        url: '/designer/jsppage.do?url=gettree',   
    		                        extraParams: {"treeType": "0"},   
    		                        method: "POST",   
    		                        reader: {   
    		                            type: 'json'  
    		                        }   
    		                   }
    		                }),
    		                rootVisible: false
    		            });
        				console.log(treePanel.getStore());
        				this.treePanel=treePanel;
        				var menuPanel=Ext.create('Ext.panel.Panel',{
        						region:"north",
        						autoHeight:true,
        						bodyStyle:{'background-color': 'transparent'},
        						layout:"table",
        						items:[
        						 		{
        									xtype:"button",
        									text:"字段设置",
        									handler:function(){
        										getPanel(0);
        									}
        								},
        								{
        									xtype:"button",
        									text:"数据分组",
        									handler:function(){
        										getPanel(1);
        									}
        								},
        								{
        									xtype:"button",
        									text:"页面",
        									handler:function(){
        										getPanel(2);
        									}
        								},
        								{
        									xtype:"button",
        									text:"预览",
        									handler:function(){
        										preview();
        									}
        								},
        								{
        									xtype:"button",
        									text:"系统设置",
        									handler:function(){
        										getPanel(3);
        									}
        								}
        							]
        					});
        				this.menuPanel=menuPanel;
        				this.items=[
        					menuPanel,treePanel,
        					{
        						xtype:"panel",
        						region:"center",
        						layout:'fit',
        						id:'main',
        						width:614
        					}
        				];
        				Ext.PortalPanel.superclass.initComponent.call(this);
        			}
        		});
        	var portal=new Ext.PortalPanel();
			Ext.create('Ext.container.Viewport', {
				layout:'fit',
				items:[portal]
			});
			
			MenuAction.loadMenus({},function(result,e){
				console.log(result);
				portal.menuPanel.add();
				
			});
        });
    </script>
</head>
</html>

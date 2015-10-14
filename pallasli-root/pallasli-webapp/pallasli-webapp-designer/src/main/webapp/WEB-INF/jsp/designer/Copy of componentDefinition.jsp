<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%String basePath=request.getContextPath();%>
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
	    Pallas.design.api.REMOTING_API.enableBuffer = 0;  
	    var remotingProvider =Ext.Direct.addProvider(Pallas.design.api.REMOTING_API);  
	    Djn.RemoteCallSupport.addCallValidation(remotingProvider);
	    Djn.RemoteCallSupport.validateCalls = true;
        Ext.onReady(function(){
           
            
            

            function preview(){
				var nid=Ext.id();
				 var searchPanel= Ext.create('Ext.panel.Panel',{
						layout:'fit',
						id:nid,
		                border:false,
		            	autoLoad  : {
				            	url : '<%=basePath%>/jsppage.do?url=designer/preview/preview',
				            	scripts : true,
				            	loadScripts : true,
				            	params : {
				            		userId : 1,
				            		nid:nid
				            	}
			            	}
		            	});
		         var searchWin = Ext.create('Ext.window.Window', {  
		                title : "可视化预览",  
		                constrain:true,
		                width : 1280,  
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
            function newpreview(){

				var nid=Ext.id();
				 var searchPanel= Ext.create('Ext.panel.Panel',{
						layout:'fit',
						id:nid,
		                border:false,
		            	autoLoad  : {
				            	url : '<%=basePath%>/jsppage.do?url=designer/preview/preview2',
				            	scripts : true,
				            	loadScripts : true,
				            	params : {
				            		userId : 1,
				            		nid:nid
				            	}
			            	}
		            	});
		         var searchWin = Ext.create('Ext.window.Window', {  
		                title : "可视化预览",  
		                constrain:true,
		                width : 1280,  
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
            function getPanel(treeType,title){

            	var nid="workTabPanel"+treeType;
            	if(Ext.getCmp(nid)){
            		portal.workTabPanel.setActiveTab(nid);
		        	return;
            	}
            	var zdpanel=new Ext.WorkPanel({id:nid,closable:true,title:title,treeType :treeType});      
            	portal.workTabPanel.add(zdpanel);
            	portal.workTabPanel.setActiveTab(zdpanel);
            	
            	  // 定义右键菜单  
                var rightClick = new Ext.menu.Menu({  
                            floating :true,  
                            plain : true,  
                            items : [{  
                                        text : '添加节点',  
                                        // 增加菜单点击事件  
                                        handler : function() {  
                                            alert('添加节点的实现！');  
                                        }  
                                    }, {  
                                        text : '编辑节点'  
                                    }, {  
                                        text : '删除节点'  
                                    }]  
                        });  
                zdpanel.treePanel.on('itemcontextmenu', function(groupTree, record, item, index, e) {// 声明菜单类型  
                    e.preventDefault();// 阻止浏览器默认右键菜单显示  
                    rightClick.showAt(e.getXY());// 取得鼠标点击坐标，展示菜单  
                });  
           }

            function getPagePanel(pagePath,title){

            	var nid="workTabPanel"+treeType;
            	if(Ext.getCmp(nid)){
            		portal.workTabPanel.setActiveTab(nid);
		        	return;
            	}
            	var zdpanel=new Ext.WorkPanel({id:nid,closable:true,title:title,treeType :treeType});      
            	portal.workTabPanel.add(zdpanel);
            	portal.workTabPanel.setActiveTab(zdpanel);
            	
            	  // 定义右键菜单  
                var rightClick = new Ext.menu.Menu({  
                            floating :true,  
                            plain : true,  
                            items : [{  
                                        text : '添加节点',  
                                        // 增加菜单点击事件  
                                        handler : function() {  
                                            alert('添加节点的实现！');  
                                        }  
                                    }, {  
                                        text : '编辑节点'  
                                    }, {  
                                        text : '删除节点'  
                                    }]  
                        });  
                zdpanel.treePanel.on('itemcontextmenu', function(groupTree, record, item, index, e) {// 声明菜单类型  
                    e.preventDefault();// 阻止浏览器默认右键菜单显示  
                    rightClick.showAt(e.getXY());// 取得鼠标点击坐标，展示菜单  
                });  
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
				            	url : '<%=basePath%>/jsppage.do?url=designer/'+record.data.url,
				            	scripts : true,
				            	loadScripts : true,
				            	params :params
			            	}
		            	});

				 workPanel.add(searchPanel);
				 workPanel.setActiveTab(searchPanel);
           }
            Ext.define('ctreemodel', {   
                extend: 'Ext.data.Model',   
                fields: [   
                    {name: 'aa',  type: 'string'},   
                    {name: 'text',  type: 'string'} ,   
                    {name: 'url',  type: 'string'}   
                ]   
            });
            
            
        	Ext.WorkPanel=Ext.extend(Ext.panel.Panel ,{
    			xtype:"panel",
    			layout:"border",
    			autoScroll:false,
    			border:false,
    			treePanel:null,
    			treeType:0,
    			workPanel:null,
    			initComponent: function(){
    				var me=this;
    				var treePanel=Ext.create('Ext.tree.Panel', {
		                title: '字段设置',
						region:"west",
		                border:false,
		                width: 170,
		                height: 150,
		                listeners:{
		                	itemclick :function( view,  record, item, index, e,  eOpts){
		                		openviewInMain(me.workPanel,record);
		                	}
		                },
		                tools:[{
		                    type:'expand',
		                    tooltip: 'Refresh form Data',
		                    // hidden:true,
		                    handler: function(event, toolEl, panel){
		                    	var nodes=treePanel.getRootNode().childNodes;
		                    	for(var ni=0,len=nodes.length;ni<len;ni++){
		                    		nodes[ni].expand();
		                    	}
		                    }
		                },
		                {
		                    type:'collapse',
		                    tooltip: 'Get Help',
		                    handler: function(event, toolEl, panel){
		                    	var nodes=treePanel.getRootNode().childNodes;
		                    	for(var ni=0,len=nodes.length;ni<len;ni++){
		                    		nodes[ni].collapse();
		                    	}
		                    }
		                },
		                {
		                    type:'left',
		                    tooltip: 'Get Help',
		                    handler: function(event, toolEl, panel){
		                    	treePanel.collapse();
		                    }
		                }],
		                store: Ext.create('Ext.data.TreeStore', {
		                    root: {
		                        expanded: true
		                    },
	                	    fields:['text', 'url', 'leaf', 'expanded','prefixion','menuId','tableName'],
		                    proxy: {
		                        type: 'direct',
		                        // directFn: TreeDirect.getTree,
				                // paramOrder: ['treeType','isRoot' ,'prefixion'],
			                      directFn: MenuAction.loadModuleTreeMenus,
				                  paramOrder: ['treeType','isRoot' ],
		                        extraParams:{'treeType':me.treeType,'isRoot':true},
    		        	        reader: {
    		        	            root: 'elements'
    		        	        }
		                    },   
		                    listeners : {   
		                        exception : function(proxy, response, operation) {   
		                            Ext.MessageBox.show({   
		                                title : '服务器错误',   
		                                msg : operation.getError(),   
		                                icon : Ext.MessageBox.ERROR,   
		                                buttons : Ext.Msg.OK   
		                            });   
		                        },   
		                        beforeload : function(store, operation,e,d,f) {
		                            var new_params = { 
		                            		prefixion : operation.node.data.prefixion?operation.node.data.prefixion:"",
		                            		treeType : operation.node.data.menuId?operation.node.data.menuId:me.treeType,
		    		                        isRoot : operation.node.isRoot()
		                            
		                            };   
		                            Ext.apply(store.proxy.extraParams, new_params);// 通过<SPAN style="BACKGROUND-COLOR: #ffffff">extraParams传递</SPAN>   
		                        },
		                        load:function(tree,store, node){
    		                       // console.log(node.expand);
    		                        //if(node.expand)node.expand();
    		                    }   
		                    }
		                }),
		                rootVisible: false
		            });
    				this.treePanel=treePanel;
    				this.workPanel=Ext.create('Ext.tab.Panel',{region:"center"});
    				
    				this.items=[treePanel,this.workPanel];
    				Ext.WorkPanel.superclass.initComponent.call(this);
    			}
    		});

        	var tbar=Ext.create("Ext.toolbar.Toolbar", {items:[
        	               					 		{
        	            								xtype:"button",
        	            								text:"字段设置",
        	            								handler:function(){
        	            									getPanel(0,"字段设置");
        	            								}
        	            							},
        	            							{
        	            								xtype:"button",
        	            								text:"数据分组",
        	            								handler:function(){
        	            									getPanel(1,"数据分组");
        	            								}
        	            							},
        	            							{
        	            								xtype:"button",
        	            								text:"页面",
        	            								handler:function(){
        	            									getPanel(2,"页面");
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
        	            								text:"预览new",
        	            								handler:function(){
        	            									newpreview();
        	            								}
        	            							},
        	            							{
        	            								xtype:"button",
        	            								text:"系统设置",
        	            								handler:function(){
        	            									getPanel(3,"系统设置");
        	            								}
        	            							},'->',{

        	            								xtype:"button",
        	            								text:"切换工作空间",
        	            								handler:function(){
        	            								}
        	            							},{

        	            								xtype:"button",
        	            								text:"接口设置",
        	            								handler:function(){
        	            								}
        	            							}
        	            						]});
        	
        	Ext.PortalPanel=Ext.extend(Ext.panel.Panel ,{
        			layout:'fit',
        			autoScroll:false,
        			border:false,
        			workTabPanel :null,
        			tbar:tbar ,
        			initComponent: function(){
        				this.workTabPanel=Ext.create('Ext.tab.Panel',{})
                		this.items=[this.workTabPanel];
        				Ext.PortalPanel.superclass.initComponent.call(this);
        			}
        		});
          	
        	var portal=new Ext.PortalPanel( );
           
          
        	
			Ext.create('Ext.container.Viewport', {
				layout:'fit',
				items:[portal]
			});
			
			MenuAction.loadSysMenu({},function(result,e){
				if(result)for(var i=0;i<result.length;i++){
					result[i].xtype='button';
					result[i].text=result[i].menuCaption; 
					result[i].handler=function(){
						getPanel(this.id,this.menuCaption);
					};
					tbar.add(result[i]);
				}
				portal.doLayout();
			});
        });
    </script>
</head>
</html>

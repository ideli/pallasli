<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%String basePath=request.getContextPath();
String nid=request.getAttribute("nid").toString();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript">

	    Ext.require([
	        'Ext.direct.*',
	        'Ext.data.*',
	        'Ext.tree.*'
	    ]);
    
        Ext.onReady(function(){

			Ext.define('MenuType',{
				extend : 'Ext.data.Model',
			    fields : [ {name:"menuTypeCode",type:"number"},'menuTypeCaption','id' ],
				proxy : {  
					        type : 'direct',  
					        api : {  
						        read : MenuAction.loadMenuTypes
					        }
					    } 
			});
			Ext.define('PageType',{
				extend : 'Ext.data.Model',
			    fields : [ {name:"pageTypeCode",type:"number"},'pageTypeCaption','id' ],
				proxy : {  
					        type : 'direct',  
					        api : {  
						        read : MenuAction.loadPageTypes
					        }
					    } 
			});

			Ext.define('MenuPath',{
				extend : 'Ext.data.Model',
			    fields : [ 'menuKey','menuCaption' ],
				proxy : {  
					        type : 'direct',  
					        api : {  
						        read : MenuAction.loadMenus
					        }
					    } 
			});

			var menuTypeStore = Ext.create('Ext.data.Store', {
				model: 'MenuType', 
				autoLoad:true
			});
			var pageTypeStore = Ext.create('Ext.data.Store', {
				model: 'PageType', 
				autoLoad:true
			});
			var menuStore = Ext.create('Ext.data.Store', {
				model: 'MenuPath', 
				autoLoad:true
			});
        	 Ext.define('Knowledge',{
			        extend: 'Ext.data.Model',
			        fields: [ 'menuKey', 'menuName','menuTableName','menuWhereSql', 'menuCaption', 'menuTypeCode','pageTypeCode',  'urlPath','parentKey' ,'menuTypeCaption' ,'parentCaption' ,
						        'id'
						    ],
						    proxy: {  
					              type: 'direct',  
					              api:{  
						            read: MenuAction.loadMenus,  
						            create: MenuAction.saveMenu,  
						            update: MenuAction.saveMenu
					              },
					              //paramOrder:["node"],
					              extraParams: {
					                  parentKey: "",
						              id: ""
					              },  
					              reader:{  
					                messageProperty:"Msg",  
					              }
					          } 
			    });

			    // create the Data Store
			    var gridstore = Ext.create('Ext.data.Store', {
			        model: 'Knowledge', 
			        autoLoad:true
			    });
			    var columns = [
								{
									"dataIndex":"id",
									"hidden":true
								},{
									"dataIndex":"menuName",
									"text":"模型英文名",
									"flex":2,
									"align":"left"
								},{
									"dataIndex":"menuCaption",
									"text":"中文名",
									"flex":2,
									"align":"left"
								},{
									"dataIndex":"menuTypeCode",
									"text":"字段列表",
									"flex":3,
									"align":"left" 
								},{
									"dataIndex":"pageTypeCode",
									"text":"direct方法",
									"flex":3,
									"align":"left" 
								},{
									"dataIndex":"parentKey",
									"text":"参数次序",
									"flex":3,
									"align":"left"  ,
									"field": {
							                xtype: 'combo',
					        			    store: menuStore,
					        			    queryMode: 'remote',
					        			    displayField: 'menuCaption',
					        			    valueField: 'id',
							            }
								},{
									"dataIndex":"urlPath",
									"text":"默认参数",
									"flex":3,
									"align":"left"   
								},{
									"dataIndex":"parentKey",
									"hidden":true 
								}];
			    var knowledgePanel=Ext.create("Ext.grid.Panel",{
			        width: 540,
			        height: 200,
					region:"center",
					stripeRows: true,
					lineBreak : false,
					cellSelect : true,
					loadMask: {msg:'正在装载...'},
					columns : columns,
					 dockedItems: [{
				            dock: 'top',
				            xtype: 'toolbar',
				            items: ["->",{
				                text: '增加',
				                handler: function(){	
 
				        			var menuTypeCombobox=Ext.create('Ext.form.ComboBox', {
				        			    fieldLabel: '菜单分类',
				        			    name:"menuTypeCode",
				        			    store: menuTypeStore,
				        			    queryMode: 'remote',
				        			    displayField: 'menuTypeCaption',
				        			    valueField: 'menuTypeCode',
				        			    anchor:"100%"
				        			});

				        			var pageTypeCombobox=Ext.create('Ext.form.ComboBox', {
				        			    fieldLabel: '页面分类',
				        			    name:"pageTypeCode",
				        			    store: pageTypeStore,
				        			    queryMode: 'remote',
				        			    displayField: 'pageTypeCaption',
				        			    valueField: 'pageTypeCode',
				        			    anchor:"100%"
				        			});
				        			var menuCombobox=Ext.create('Ext.form.ComboBox', {
				        			    fieldLabel: '父菜单',
				        			    name:"parentKey",
				        			    store: menuStore,
				        			    queryMode: 'remote',
				        			    displayField: 'menuCaption',
				        			    valueField: 'menuKey',
				        			    anchor:"100%"
				        			});
				                	 var form_addType=Ext.create("Ext.form.Panel",{
				                          border: false,
				                          fieldDefaults: {
				                              labelWidth: 100
				                          },
				                          defaultType: 'textfield',
				                          bodyPadding: 5,
				                          floatable: false,
				                          items:[
						                          	{fieldLabel:"关键字",name:"menuKey",anchor:"100%"},
						                          	{fieldLabel:"英文名",name:"menuName",anchor:"100%"},
						                          	{fieldLabel:"中文名",name:"menuCaption",anchor:"100%"},
						                          	menuTypeCombobox,pageTypeCombobox,menuCombobox , 
						                          	{fieldLabel:"页面路径",name:"urlPath",anchor:"100%"},
						                          	{fieldLabel:"表名",name:"menuTableName",anchor:"100%"},
						                          	{fieldLabel:"查询条件",name:"menuWhereSql",anchor:"100%"},
						                          	{fieldLabel:"配置项",xtype:"textarea",name:"menuConfig",anchor:"100%"}                          
				                          ],
				                          bbar:["->",{
				                        	  text:"保存",handler:function(){
				                        		 var formValues=form_addType.getForm().getValues(); 
				                        		 
				                        		 var rec=new Knowledge({  
					 				            	    menuName : formValues["menuName"], 
					 				            	    menuKey : formValues["menuKey"], 
					 				            	    menuCaption : formValues["menuCaption"], 
					 				            	    menuTypeCode : formValues["menuTypeCode"], 
					 				            	    pageTypeCode : formValues["pageTypeCode"], 
					 				            	    menuWhereSql : formValues["menuWhereSql"], 
					 				            	    menuTableName : formValues["menuTableName"], 
					 				            	    urlPath : formValues["urlPath"], 
					 				            	    menuConfig : formValues["menuConfig"], 
					 				            	    parentKey :formValues["parentKey"] 
				 				            	});  
				 				            	rec.save({ 
				 				            	    success:function(rec,opt){  
				 				            	       gridstore.reload();
				 				            	    },  
				 				            	    failure:function(e,op){  
				 				            	        Ext.Msg.alert("发生错误",op.error);  
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
			                	
				                }
				            },{
				                text: '修改',
				                handler: function(btn,e){	
				                	var records=knowledgePanel.getSelectionModel().getSelection();
				                	var recordData={};
				                	if(records.length>0){
					                	  recordData=records[0].data;
				                	}else{
				                		return;
				                	}

				        			var menuTypeCombobox=Ext.create('Ext.form.ComboBox', {
				        			    fieldLabel: '菜单分类',
				        			    name:"menuTypeCode",
				        			    store: menuTypeStore,
				        			    value:recordData.menuTypeCode,
				        			    queryMode: 'remote',
				        			    displayField: 'menuTypeCaption',
				        			    valueField: 'menuTypeCode',
				        			    anchor:"100%"
				        			});
				        			var pageTypeCombobox=Ext.create('Ext.form.ComboBox', {
				        			    fieldLabel: '页面分类',
				        			    name:"pageTypeCode",
				        			    store: pageTypeStore,
				        			    value:recordData.pageTypeCode,
				        			    queryMode: 'remote',
				        			    displayField: 'pageTypeCaption',
				        			    valueField: 'pageTypeCode',
				        			    anchor:"100%"
				        			});
				        			var menuCombobox=Ext.create('Ext.form.ComboBox', {
				        			    fieldLabel: '父菜单',
				        			    name:"parentKey",
				        			    store: menuStore,
				        			    value:recordData.parentKey,
				        			    queryMode: 'remote',
				        			    displayField: 'menuCaption',
				        			    valueField: 'menuKey',
				        			    anchor:"100%"
				        			});
				                	 var form_addType=Ext.create("Ext.form.Panel",{
				                          border: false,
				                          fieldDefaults: {
				                              labelWidth: 100
				                          },
				                          defaultType: 'textfield',
				                          bodyPadding: 5,
				                          floatable: false,
				                          items:[
						                          	{xtype:"hidden",name:"id",value:recordData.id,anchor:"100%"},
						                          	{fieldLabel:"关键字",name:"menuKey",value:recordData.menuKey,anchor:"100%"},
						                          	{fieldLabel:"英文名",name:"menuName",value:recordData.menuName,anchor:"100%"},
						                          	{fieldLabel:"中文名",name:"menuCaption",value:recordData.menuCaption,anchor:"100%"},
						                          	menuTypeCombobox,pageTypeCombobox,menuCombobox , 
						                          	{fieldLabel:"表名",name:"menuTableName",value:recordData.menuTableName,anchor:"100%"},
						                          	{fieldLabel:"查询条件",name:"menuWhereSql",value:recordData.menuWhereSql,anchor:"100%"},
						                          	{fieldLabel:"页面路径",name:"urlPath",value:recordData.urlPath,anchor:"100%"},
						                          	{fieldLabel:"配置项",xtype:"textarea",name:"menuConfig",anchor:"100%"}                          
				                          ],
				                          bbar:["->",{
				                        	  text:"保存",handler:function(){
				                        		 var formValues=form_addType.getForm().getValues(); 
				                        		 
				                        		 var rec=new Knowledge({  
					 				            	    id : formValues["id"], 
					 				            	    menuKey : formValues["menuKey"], 
					 				            	    menuName : formValues["menuName"], 
					 				            	    menuCaption : formValues["menuCaption"], 
					 				            	    urlPath : formValues["urlPath"], 
					 				            	    menuTypeCode : formValues["menuTypeCode"], 
					 				            	    menuWhereSql : formValues["menuWhereSql"], 
					 				            	    menuTableName : formValues["menuTableName"], 
					 				            	    pageTypeCode : formValues["pageTypeCode"], 
					 				            	    menuConfig : formValues["menuConfig"],
					 				            	    parentKey :formValues["parentKey"] 
				 				            	});  
				 				            	rec.save({ 
				 				            	    success:function(rec,opt){  
				 				            	       gridstore.reload();
				 				            	    },  
				 				            	    failure:function(e,op){  
				 				            	        Ext.Msg.alert("发生错误",op.error);  
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
			                	
				                
								}
				            },{
				                text: '删除',
				                handler: function(btn,e){	
				                	var records=knowledgePanel.getSelectionModel().getSelection();

				                	if(records.length>0){
					                	var recordData=records[0].data;
					                	Ext.Msg.show({
					                	     title:'是否删除?',
					                	     msg: '你确定要删除'+recordData.caption+'吗?',
					                	     buttons: Ext.Msg.YESNOCANCEL,
					                	     icon: Ext.Msg.QUESTION,
					                	     fn:function(btn){
													if(btn=="yes"){
								                	MenuAction.deleteMenu(recordData,function(result){
								                		knowledgePanel.getStore().removeAll();
							                			knowledgePanel.getStore().reload();
								                	}); 
													}
					                	     }
					                	});
				                	}
				                }
				            }]
				        }],
					store : gridstore
				  }); 
        	Ext.getCmp('<%=nid%>').add(knowledgePanel);
    		Ext.getCmp('<%=nid%>').doLayout();
        });
    </script>
</head>
</html>

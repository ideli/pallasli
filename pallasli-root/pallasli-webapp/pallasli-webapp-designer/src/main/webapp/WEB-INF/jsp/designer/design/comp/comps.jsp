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
        	 Ext.define('Comp',{
			        extend: 'Ext.data.Model',
			        fields: [ 'compName', 'compCaption', 'compTypeId',  'id'
						    ],
						    proxy: {  
					              type: 'direct',  
					              api:{  
						            read: CompAction.loadComps,  
						            create: CompAction.saveComp
					              },
					              //paramOrder:["node"],
					              extraParams: {
					                  parentId: "",
						              id: ""
					              },  
					              reader:{  
					                messageProperty:"Msg",  
					              }
					          } 
			    });

			    // create the Data Store
			    var gridstore = Ext.create('Ext.data.Store', {
			        model: 'Comp', 
			        autoLoad:true
			    });
			    var columns = [
								{
									"dataIndex":"id",
									"hidden":true
								},{
									"dataIndex":"compName",
									"text":"组件英文名",
									"flex":1,
									"align":"left"
								},{
									"dataIndex":"compCaption",
									"text":"组件中文名",
									"flex":3,
									"align":"left"
								},{
									"dataIndex":"compTypeCaption",
									"text":"组件所属分类",
									"flex":4,
									"align":"left"   
								},{
									"dataIndex":"compClass",
									"text":"组件对应的控件类",
									"flex":4,
									"align":"left"   
								},{
									"dataIndex":"parentId",
									"text":"备注",
									"flex":4,
									"align":"left"   
								},{
									"dataIndex":"compTypeId",
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
				                	 var form_addType=Ext.create("Ext.form.Panel",{
				                          border: false,
				                          fieldDefaults: {
				                              labelWidth: 100
				                          },
				                          defaultType: 'textfield',
				                          bodyPadding: 5,
				                          floatable: false,
				                          items:[
						                          	{fieldLabel:"英文名",name:"compName",anchor:"100%"},
						                          	{fieldLabel:"中文名",name:"compCaption",anchor:"100%"},
						                          	{fieldLabel:"对应js组件",name:"compClass",anchor:"100%"},
						                          	{fieldLabel:"所属分类",name:"compTypeId",anchor:"100%"}                          
				                          ],
				                          bbar:["->",{
				                        	  text:"保存",handler:function(){
				                        		 var formValues=form_addType.getForm().getValues(); 
				                        		 
				                        		 var rec=new Comp({  
					 				            	   compName : formValues["compName"], 
					 				            	   compCaption : formValues["compCaption"], 
					 				            	   compClass : formValues["compClass"], 
					 				            	   compTypeId :formValues["compTypeId"]  
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
								                	KnowledgeAction.deleteKnowledge(recordData,function(result){
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

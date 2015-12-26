<%@page import="com.pallasli.constant.SystemConstant"%>
<%@page import="com.pallasli.utils.ZipUtils"%>
<%@page import="com.pallasli.utils.FileUtils"%>
<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*"
	pageEncoding="gb2312"%>
<%@page import="com.zhuozhengsoft.pageoffice.excelwriter.*"%>
<%@page import="java.awt.Color"%>
<%@page import="java.text.*"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
	String panelId = request.getParameter("panelId");
	String zipPath=SystemConstant.WEB_ROOT+ "download/test.zip"; 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>
	<script language='javascript'>

	Pallasli.study.api.REMOTING_API.enableBuffer = 0;  
	Ext.Direct.addProvider(Pallasli.study.api.REMOTING_API);  
    Ext.Loader.setPath({
        'Ext.ux.desktop': 'scripts/desktop/js',
        'MyDesktop': 'scripts/desktop/mydesktop'
    });

    Ext.require('MyDesktop.Notepad');
		Ext.onReady(function(){
			
			var panel=Ext.getCmp("<%=panelId%>");
			  Ext.define('Task', { 
				  autoLoad:true,
			        extend: 'Ext.data.Model',  
			        fields: [  
			            {name: 'id',  type: 'number'},  
			            {name: 'text',  type: 'string'}  ,  
			            {name: 'path',  type: 'string'}  ,  
			            {name: 'attributes',  type: 'json'}  
			        ], 
			        proxy: {  
			              type: 'direct',  
			              api:{  
				            read: KnowledgeAction.loadKnowledgeType,  
				            create: KnowledgeAction.addKnowledgeType,  
				            destroy: KnowledgeAction.deleteKnowledgeType,  
				            update: KnowledgeAction.alterKnowledgeType 
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

			  //KnowledgeAction.loadKnowledgeType
			  //KnowledgeAction.saveKnowledgeType
			  //KnowledgeAction.alterKnowledgeType
			    var store = Ext.create('Ext.data.TreeStore',{  
			        model: 'Task',  
			     //   proxy: {  
			     //       type: 'ajax',  
			     //       url: 'data/knowledgeTypeTree.json'    //必须要搭建个服务器才可以访问json，不然会报错。拒绝访问  
			     //   },  
			        reader: {  
			            type: 'json'  
			        },  
			        root: {  
			            text: 'root',  
			            id: '0',  
			        }  
			    });  
			    Ext.define('Knowledge',{
			        extend: 'Ext.data.Model',
			        fields: [ 'caption',  'content' ,
						        'id'
						    ]
			    });

			    // create the Data Store
			    var gridstore = Ext.create('Ext.data.Store', {
			        model: 'Knowledge', 
			        proxy: {
			            type: 'direct',
			            directFn: KnowledgeAction.loadKnowledges,
			            paramOrder: ['parentId']
			         } ,
			        
			        //proxy: {
			            // load using HTTP
			          //  type: 'ajax',
			          //  url: ' ',
			            // the return will be XML, so lets set up a reader
			           // reader: {
			           //     type: 'xml',
			                // records will have an "Item" tag
			           //     record: 'knowledge',
			           //     idProperty: 'id',
			           //     totalRecords: '@TotalResults'
			          //  }
			       // }, 
			        listeners : {  
			            load : function(store,records){      
			                store.filterBy(function(record,id){         
			                  return true;  
			               });         
			                       
			               }  
			             } 
			    });


			    var columns = [
								{
									"id" : "id",
									"dataIndex":"id",
									"hidden":true
								},{
									"id" : "caption",
									"dataIndex":"caption",
									"text":"知识点",
									"flex":1,
									"align":"left"
								},{
									"id" : "content",
									"dataIndex":"content",
									"text":"内容",
									"flex":4,
									"align":"left",
									renderer:function(value, metadata, record, rowIndex, columnIndex, store) { 
										   // metadata.attr = 'ext:qtip="课题简介:<br/>' +  "<code>"+value+"</code>"+"'"; 
										    return value; 
									}    
								}];
			    var knowledgePanel=Ext.create("Ext.grid.Panel",{
					title:"知识点列表",
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
				                tooltip: '添加知识点',
				                text: '增加',
				                handler: function(){	
				                	var xmlpath=gridstore.proxy.url;
			                		var win=new MyDesktop.KnowledgeWindow({

				                		parentId:gridstore.proxy.extraParams.parentId,
				                		xmlpath:xmlpath,app:myDesktopApp}).createWindow();
			                		
			                		win.on("close",function(){
			                			gridstore.reload();
			                		});
			                		win.on("hide",function(){
			                			gridstore.reload();
			                		});
			                		win.show();
			                	
				                }
				            },{
				                tooltip: '修改知识点',
				                text: '修改',
				                handler: function(btn,e){	
				                	var records=knowledgePanel.getSelectionModel().getSelection();

				                	if(records.length>0){
					                	var recordData=records[0].data;

					                	var xmlpath=gridstore.proxy.url;
					                	var win=new MyDesktop.KnowledgeWindow({
					                		parentId:gridstore.proxy.extraParams.parentId,
					                		xmlpath:xmlpath,id:recordData.id,operationFlag:"chg",caption:recordData.caption,content:recordData.content   ,app:myDesktopApp}).createWindow();
					                	win.on("close",function(){
					                		knowledgePanel.getStore().removeAll();
					                		knowledgePanel.getStore().reload();
				                		});
				                		win.on("hide",function(){
					                		knowledgePanel.getStore().removeAll();
				                			knowledgePanel.getStore().reload();
				                		});
				                		win.show();
				                	}
				                }
				            },{
				                tooltip: '删除知识点',
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
				            },{
				                tooltip: '查看知识点',
				                text: '查看',
				                handler: function(btn,e){	
				                	var records=knowledgePanel.getSelectionModel().getSelection();

				                	if(records.length>0){
					                	var recordData=records[0].data;
					                	new MyDesktop.KnowledgeWindow({id:recordData.id,operationFlag:"read",caption:recordData.caption,content:recordData.content,app:myDesktopApp}).createWindow().show();
				                	}
				                }
				            },{
				                tooltip: '导出知识点',
				                text: '导出word',
				                handler: function(){
				                	var selectionMode = menuTree.getSelectionModel();  
				                    var selection = selectionMode.getSelection();//获取选中的值  
			                		var recordData={};
			                		recordData.parentId=gridstore.proxy.extraParams.parentId;
			                		recordData.wordTitle=selection[0].data.text;
				                	KnowledgeAction.exportAllDocKnowledge(recordData,function(result){
				                		 
				                	}); 
				                }
				            },{
				                tooltip: '导出知识点',
				                text: '导出html',
				                handler: function(){
				                	
				                	var selectionMode = menuTree.getSelectionModel();  
				                    var selection = selectionMode.getSelection();//获取选中的值  
			                		var recordData={};
			                		recordData.parentId=gridstore.proxy.extraParams.parentId;
			                		recordData.wordTitle=selection[0].data.text;
				                	KnowledgeAction.exportAllHtmlKnowledge(recordData,function(result){
				                		
				                		var jsIframe = document.createElement("iframe");
				            			jsIframe.style.display = "none";//none iframe
				            			document.body.appendChild(jsIframe);
				            			with(window.frames[window.frames.length - 1]){
				            				document.open();
				            				var downloadform=document.createElement("form");
				            				downloadform.action="Download";
				            				downloadform.method="post";
				            				var filename=document.createElement("input");
				            				filename.name="filename";
				            				filename.value="学习总结.zip";
				            				downloadform.appendChild(filename);
				            				
				            				document.appendChild(downloadform);
				            				downloadform.submit();
				            				document.close();
				            			} 
				                		 
				                		//window.open('jsppage.do?url=common/download');
				                		return;
				                		Ext.Ajax.request({
				                			//url:'download.action',
				                			url:'jsppage.do?url=common/download',
				                		    
				                			//method:'post',
				                			params:{path:'<%=zipPath%>'}
				                		});
					                	 
				                	}); 
				                }
				            }]
				        }],
					store : gridstore
				  });
			    function rowdblclickFn(grid, rowindex, e){    
			    	grid.getSelectionModel().each(function(rec){     
				        alert(rec.get('caption')); //fieldName，记录中的字段名     
			        });     
			    }
			    knowledgePanel.addListener('itemcontextmenu', itemcontextmenu); 
			    knowledgePanel.addListener('itemdblclick', rowdblclickFn);  
			 // 调用的函数  
			 function itemcontextmenu(his, record, item, index, e) {    
			     // itemcontextmenu( Ext.view.View this, Ext.data.Model record,  
			     // HTMLElement item, Number index, Ext.EventObject e, Object eOpts )  
			     // 分类代码表的右键菜单   
			     e.preventDefault();    
			     e.stopEvent();// 取消浏览器默认事件     
			     var array = [ {    
			                 text : '添加代码',    
			                 handler : function() {   
			   
			                 }    
			             }, {    
			                 text : '修改代码',    
			                 handler : function() {  
			   
			                 }    
			             }, {    
			                 text : '删除代码',    
			                 handler : function() {  
			                     alert(record.raw.id);    
			                 }    
			             }];    
			     var nodemenu = new Ext.menu.Menu({    
			         items : array    
			     });    
			     nodemenu.showAt(e.getXY());// 菜单打开的位置    
			 };  
			    var menuTree = Ext.create('Ext.tree.Panel',{  
			        title: '知识分类',  
			        autoScroll:true,    
			        width: 200,    
			        store: store,  
					region:"west",
				    hideHeaders:true,  
			        rootVisible: false,  

			        viewConfig:{  
			            toggleOnDblClick:false,  
			            plugins: {  
			                ptype: 'treeviewdragdrop'  
			            },  
			            listeners:{  
			                refresh:function(){  
			                    this.select(0);  
			                    this.focus(0);  
			                }  
			            }  
			        },  
			        plugins:[{ptype:"cellediting",  
			        	
			            listeners:{  
			                beforeedit:function(e){  
			                    if(e.record.isRoot()) return false;  
			                }  
			            }  
			        }], 
			        columns:[  
			                 {xtype:"treecolumn",dataIndex:"text",flex:1,  
			                     field:{allowBlank:false}  
			                 }  
			             ], 
			        tbar:[  
			              {text:"增加",id:"add",handler:function(){
				               var tree=this.up("treepanel"); 
				            	var selectionMode = tree.getSelectionModel();  
			                    var selection = selectionMode.getSelection()[0];//获取选中的值  
			            	  var button=this;
			                 var form_addType=Ext.create("Ext.form.Panel",{
		                          border: false,
		                          fieldDefaults: {
		                              labelWidth: 100
		                          },
		                          defaultType: 'textfield',
		                          bodyPadding: 5,
		                          floatable: false,
		                          items:[
				                          	{xtype:"hidden",name:"parentId",value:selection.data.id},
				                          	{fieldLabel:"所属分类",name:"parentText",anchor:"100%",readOnly:true,value:selection.data.text},
				                          	{fieldLabel:"分类名称",name:"text",anchor:"100%"},	,		
				                          	{fieldLabel:"是否根节点分类",anchor:"100%",xtype: 'radiogroup',
				                                // Arrange radio buttons into two columns, distributed vertically
				                                columns: 2,
				                                vertical: true,
				                                items: [
				                                    { boxLabel: '否', name: 'isRootChild', inputValue: '0' , checked: true},
				                                    { boxLabel: '是', name: 'isRootChild', inputValue: '1'}
				                                ]},	
				                          	{fieldLabel:"是否包含子分类",anchor:"100%",xtype: 'radiogroup',
				                                // Arrange radio buttons into two columns, distributed vertically
				                                columns: 2,
				                                vertical: true,
				                                items: [
				                                    { boxLabel: '否', name: 'hasChild', inputValue: '0' },
				                                    { boxLabel: '是', name: 'hasChild', inputValue: '1', checked: true}
				                                ]}	                          
		                          ],
		                          bbar:["->",{
		                        	  text:"保存",handler:function(){
		                        		 var formValues=form_addType.getForm().getValues();
		                        		 var parentId=formValues["parentId"];
		                        		 var parent=selection;
		                        		 if(formValues["isRootChild"]=="1"){
		                        			 parentId=0;
		                        			 parent=tree.store.tree.root;
		                        		 }
		                        		 
		                        		 var rec=new Task({  
			 				            	    text:formValues["text"], 
			 				            	    leaf:formValues["hasChild"]=="0", 
		 				            	    //id:"sssssss",  有id则alter，无id则create
		 				            	    	parentId:parentId 
		 				            	});  
		 				            	rec.save({ 
		 				            	    parentNode:parent,  
		 				            	    success:function(rec,opt){  
		 				            	        if(opt.parentNode.isExpanded()) { 
		 				            	            opt.parentNode.appendChild(rec);  
		 				            	        }else {
		 				            	            opt.parentNode.expand();  
		 				            	            opt.parentNode.appendChild(rec);  
		 				            	        } 
		 				            	    },  
		 				            	    failure:function(e,op){  
		 				            	        Ext.Msg.alert("发生错误",op.error);  
		 				            	    },  
		 				            	    scope:tree  
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
			              }},  
			              {text:"删除",id:"delete",disabled:true,handler:function(){  
				               var tree=this.up("treepanel"); 
				            	var selectionMode = tree.getSelectionModel();  
			                    var selection = selectionMode.getSelection()[0];//获取选中的值  
			            	  
			                    var parent=selection.parentNode;
                     		 var rec=new Task({  
				            	        id:selection.data.id
				            	});  
                     		Ext.Msg.show({
		                	     title:'是否删除?',
		                	     msg: '你确定要删除'+selection.data.text+'吗?',
		                	     buttons: Ext.Msg.YESNOCANCEL,
		                	     icon: Ext.Msg.QUESTION,
		                	     fn:function(btn){
										if(btn=="yes"){

							            	rec.destroy({ 
							            	    parentNode:parent,  
							            	    success:function(rec,opt){  
							            	    	console.log(rec,opt);
						            	            opt.parentNode.removeChild(selection);   
							            	    },  
							            	    failure:function(e,op){  
							            	        Ext.Msg.alert("发生错误",op.error);  
							            	    },  
							            	    scope:tree  
							            	});  
										}
		                	     }
		                	});
			              }},  
			              {text:"修改",id:"update",disabled:true,handler:function(){  
			            	  var tree=this.up("treepanel"); 
				            	var selectionMode = tree.getSelectionModel();  
			                    var selection = selectionMode.getSelection()[0];//获取选中的值  
			            	  var button=this;
			                 var form_addType=Ext.create("Ext.form.Panel",{
		                          border: false,
		                          fieldDefaults: {
		                              labelWidth: 100
		                          },
		                          defaultType: 'textfield',
		                          bodyPadding: 5,
		                          floatable: false,
		                          items:[
				                          	{xtype:"hidden",name:"id",value:selection.data.id},
				                          	{xtype:"hidden",name:"parentId",value:selection.data.parentId},
				                          	{fieldLabel:"所属分类",name:"parentText",anchor:"100%",readOnly:true,value:selection.data.text},
				                          	{fieldLabel:"分类名称",name:"text",anchor:"100%",value:selection.data.text},
				                          	{fieldLabel:"是否包含子分类",anchor:"100%",xtype: 'radiogroup',
				                                // Arrange radio buttons into two columns, distributed vertically
				                                columns: 2,
				                                vertical: true,
				                                items: [
				                                    { boxLabel: '否', name: 'hasChild', inputValue: '0' , checked: selection.data.leaf},
				                                    { boxLabel: '是', name: 'hasChild', inputValue: '1', checked: !selection.data.leaf}
				                                ]}
		                          ],
		                          bbar:["->",{
		                        	  text:"保存",handler:function(){
		                        		 console.log( form_addType.getForm().getValues());
		                        		 var formValues=form_addType.getForm().getValues();
		                        		 var parentId=formValues["parentId"];
		                        		 var parent=selection;
		                        		 if(formValues["isRootChild"]=="1"){
		                        			 parentId=0;
		                        			 parent=tree.store.tree.root;
		                        		 }
 
		                        		 var rec=new Task({  
			 				            	    text:formValues["text"], 
			 				            	    leaf:formValues["hasChild"]=="0", 
		 				            	        id:selection.data.id, // 有id则alter，无id则create
		 				            	    	parentId:parentId 
		 				            	});  
 
		 				            	rec.save({ 
		 				            	    parentNode:parent,  
		 				            	    success:function(rec,opt){  
		 				            	    	opt.parentNode.set('text', rec.data.text);
		 				            	    	opt.parentNode.set('leaf', rec.data.leaf);
		 				            	    	opt.parentNode.set('parentId', rec.data.parentId);
		 				            	    	opt.parentNode.commit();
		 				            	    },  
		 				            	    failure:function(e,op){  
		 				            	        Ext.Msg.alert("发生错误",op.error);  
		 				            	    },  
		 				            	    scope:tree  
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
			              }},  
			              "|",  
			              {text:"刷新",handler:function(){  
			                  this.up("treepanel").store.load();  
			              }}  
			          ],  
			        listeners : {  
			            'itemclick' : function(view,record,itemEl,index,e){
			            	var selectionMode = this.getSelectionModel();  
		                    var modeType = selectionMode.getSelectionMode();//SINGLE, MULTI or SIMPLE  
		                    var selection = selectionMode.getSelection();//获取选中的值  
		                    var parentN=selection[0].parentNode;
		                    var dir="";
		                    while(!parentN.data.root){
		                    	dir=parentN.data.text+"/"+dir;
		                    		parentN=parentN.parentNode;
		                    }

			                if(record.data.leaf){ 
			                		gridstore.proxy.extraParams.parentId=record.data.id;
			                	//	gridstore.proxy.url="data/学习总结/"+dir.toLowerCase()+record.data.text+".xml";
			                		gridstore.load(record.data.id);
			                }else{  
			                    if(record.data.expanded){  
			                        view.collapse(record);  
			                    }else{  
			                        view.expand(record);  
			                    }  
			                }  
			            } ,  
			            selectionchange:function(view,rs){  
			                Ext.getCmp("delete").setDisabled(rs.length==0); 
			                Ext.getCmp("update").setDisabled(rs.length==0);  
			            }
			        }  
			    });  

			var ui=Ext.create('Ext.Panel', {
				layout:"border",
				items:[menuTree,knowledgePanel]
        	});

			panel.add(ui);
		});
	</script>
</html>

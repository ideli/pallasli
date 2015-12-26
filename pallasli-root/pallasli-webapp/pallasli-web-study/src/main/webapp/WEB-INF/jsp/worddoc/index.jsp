<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*"
	pageEncoding="utf-8"%>
<%@page import="com.zhuozhengsoft.pageoffice.excelwriter.*"%>
<%@page import="java.awt.Color"%>
<%@page import="java.text.*"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
	String panelId = request.getParameter("panelId");


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
			        extend: 'Ext.data.Model',  
			        fields: [  
					            {name: 'id',  type: 'string'},  
					            {name: 'text',  type: 'string'}  ,  
					            {name: 'path',  type: 'string'}  ,  
					            {name: 'attributes',  type: 'json'}   
			        ],  
			        proxy: {  
			              type: 'direct',  
			              api:{  
				            read: WordAction.loadWordType,  
				            create: WordAction.addWordType,  
				            destroy: WordAction.alterWordType,  
				            update: WordAction.alterWordType 
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
			        //proxy: {  
			        //    type: 'ajax',  
			        //    url: 'data/worddocMenuTree.json'    //必须要搭建个服务器才可以访问json，不然会报错。拒绝访问  
			        //},  
			        reader: {  
			            type: 'json'  
			        },  
			        root: {  
			            text: 'root',  
			            id: '0',  
			        }  
			    });  

			    var knowledgePanel=Ext.create("Ext.panel.Panel",{

			        region:"center"
				  });
			    var menuTree = Ext.create('Ext.tree.Panel',{  
			        title: 'word列表',  
			        autoScroll:true,    
			        store: store,  
				    hideHeaders:true,  
			        rootVisible: false,
			        region:"west",
					width:"30%",
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
		                        		 console.log( form_addType.getForm().getValues());
		                        		 var formValues=form_addType.getForm().getValues();
		                        		 var parentId=formValues["parentId"];
		                        		 var parent=selection;
		                        		 if(formValues["isRootChild"]=="1"){
		                        			 parentId=0;
		                        			 parent=tree.store.tree.root;
		                        			 console.log(parent);
		                        		 }
		                        		 
		                        		 
		                        		var parentN=parent;
		                        		var dir="";
		  			                    while(parentN&&!parentN.data.root){
		  			                    	dir=parentN.data.text+"/"+dir;
		  			                    		parentN=parentN.parentNode;
		  			                    }
		  			                    dir="data/word/"+dir.toLowerCase()+"/"+formValues["text"];
		  				            	
		                        		 var rec=new Task({  
			 				            	    text:formValues["text"], 
			 				            	    leaf:formValues["hasChild"]=="0", 
		 				            	        path:dir,
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
			               win.show(button, function() {
			                   // button.disable();
			               }); 

			               return;
			               var tree=this.up("treepanel"), 
			               		parent=tree.getSelectionModel().getSelection()[0]; 
			               win.on('hide',function(){
			            	   var selectionMode = tree.getSelectionModel();  
			                    var selection = selectionMode.getSelection();//获取选中的值  
			                    var parentN=selection[0].parentNode;
			                    var dir="";
			                    while(!parentN.data.root){
			                    	dir=parentN.data.text+"/"+dir;
			                    		parentN=parentN.parentNode;
			                    }
			                    dir="data/word/"+dir.toLowerCase()+selection[0].data.text+"/create";
				            	if(! parent){  
				            	    parent=tree.store.tree.root;  
				            	}  
				            	var rec=new Task({  
				            	    text:"create", 
				            	    path:dir,
				            	    //id:"sssssss",  有id则alter，无id则create
				            	    parentId:parent.data.id  
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
			            	    
			               }); 
			              }},  
			              {text:"删除",id:"delete",disabled:true,handler:function(){  
			            	  var tree=this.up("treepanel");  
			            	  var rs=tree.getSelectionModel().getSelection();  
			            	  if(rs.length>0){  
			            	      rs=rs[0];  
			            	      if(rs.data.root){  
			            	          Ext.Msg.alert("删除节点","根节点不允许删除！");  
			            	          return;  
			            	      }  
			            	      if(rs.isExpandable() || rs.hasChildNodes()){  
			            	          Ext.Msg.alert("删除节点","请先删除所有子节点，再删除该节点！");  
			            	          return;  
			            	      }else{  
			            	          var content="确定删除节点："+rs.data.text+"？";  
			            	          Ext.Msg.confirm("删除节点",content,function(btn){  
			            	              if(btn=="yes"){  
			            	                  var rs=this.getSelectionModel().getSelection();  
			            	                  if(rs.length>0){  
			            	                      rs=rs[0];  
			            	                      rs.remove();  
			            	                      this.store.sync();  
			            	                      this.view.select(0);  
			            	                      this.view.focus(false);  
			            	                  }  
			            	              }  
			            	          },tree)  
			            	      }  
			            	  } 
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
			                	var fileurl="data/word/"+dir.toLowerCase()+record.data.text+".doc";
			                	knowledgePanel.getEl().load({
			                	       url: "jsppage.do?url=worddoc/pageOffice",
			                	       params: {"path":fileurl},
			                	       scripts:true, 
			                	  })
			                	return;
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

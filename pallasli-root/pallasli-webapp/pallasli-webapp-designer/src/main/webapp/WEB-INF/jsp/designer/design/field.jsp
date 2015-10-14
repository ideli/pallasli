<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%String basePath=request.getContextPath();
String nid=request.getAttribute("nid").toString();
Object tableNameO=request.getAttribute("nodeName");
String tableName=tableNameO==null?"":tableNameO.toString();
%>
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
        	
        	var store=Ext.create('Ext.data.Store', {
        		model: 'TableField', 
    			autoLoad:false
    		});   
        	Ext.apply(store.proxy.extraParams,{tableName:"<%=tableName%>"}); 
        	store.reload();
        	var propertystore=Ext.create('Ext.data.Store', {
        	    fields:['configName', 'PROPERTY_VALUE'],
        	    paramOrder: ['params'],
    	        autoLoad:true,
    	        pageSize:5,
        	    proxy: {
        	        type: 'direct',
        	        autoLoad:true,
        	        directFn:TableDirect.getTableFieldPropetyValues,
                    extraParams:{tableName:"<%=tableName%>",fieldName:""}
        	    }
        	});    
	       	 var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
	 	        clicksToEdit: 1
	 	     });    
	       	 var nullablestore =new Ext.data.ArrayStore({
         		autoDestroy: true,
         		idIndex: 0,
         		fields: 
         		[
         			{name: 'value', type: 'string'},
         			'display'
         		],
         		data : [['Y','是'],['N','否']]
         	});
	       	var datatypestore =new Ext.data.ArrayStore({
         		autoDestroy: true,
         		idIndex: 0,
         		fields: 
         		[
         			{name: 'value', type: 'string'},
         			'display'
         		],
         		data : [['NUMBER','NUMBER'],['VARCHAR2','VARCHAR2'],['DATE','DATE'],['TIMESTAMP','TIMESTAMP']]
         	});
			var gridp=Ext.create('Ext.grid.Panel', {
					region:"west",
	                border:false,split:true,
				    store: store,
	                tbar:['->',{
	                	text:'引入字段',
	                	handler:function(){
	                		
			            	 var win = Ext.create('Pallas.designer.portal.ImportTableFieldWindow', {tableName:'<%=tableName%>' });
			            	 win.show();
	                	}	                
	                },{text:'同步到数据库',
	                	handler:function(){

		                	 var form_addType=Ext.create("Ext.form.Panel",{
		                          border: false,
		                          fieldDefaults: {
		                              labelWidth: 80
		                          },
		                          defaultType: 'textfield',
		                          layout:'column',
		                          bodyPadding: 5,
		                          floatable: false,
		                          items:[
				                          	{fieldLabel:"数据库类型",margin: '5 5 5 5',name:"menuKey",columnWidth:0.2,anchor:"100%"},
				                          	{fieldLabel:"数据库实例名",margin: '5 5 5 5',name:"menuName",columnWidth:0.2,anchor:"100%"},
				                          	{fieldLabel:"端口",margin: '5 5 5 5',name:"menuCaption",columnWidth:0.2,anchor:"100%"},
				                          	{fieldLabel:"用户名",margin: '5 5 5 5',name:"urlPath",columnWidth:0.2,anchor:"100%"},
				                          	{fieldLabel:"用户密码",margin: '5 5 5 5',name:"menuTableName",columnWidth:0.2,anchor:"100%"},
				                          	{fieldLabel:"是否全选",margin: '5 5 5 5',name:"menuWhereSql",columnWidth:0.2,anchor:"100%"},
				                          	Ext.create('Ext.tab.Panel')
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
			                      title: '同步到数据库',
			                      closable: true,
			                      closeAction: 'hide',
			                      width: 1000,
			                      minWidth: 350,
			                      height: 550,
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
            			text:'增加',handler:function(){
    	                	
        				}
       				}],
				    columns: [
				        { header: '字段英文名', dataIndex: 'fieldName', flex: 2 },
				        { header: '字段中文名', dataIndex: 'fieldCaption', flex: 2,
				            field: {
			                xtype: 'textfield',
			                allowBlank: false
			            } },
				        { header: '长度', dataIndex: 'fieldLength', xtype: 'numbercolumn', format:'0,000', flex: 1,
				            field: {
			                xtype: 'numberfield',
			                allowBlank: false
			            } },
				        { header: '精度', dataIndex: 'fieldPrecision', xtype: 'numbercolumn', format:'0,000',flex: 1,
				            field: {
			                xtype: 'numberfield',
			                allowBlank: false
			            } },
				        { header: '类型', dataIndex: 'fieldType', flex: 2,
			                renderer: function(value, cellmeta, record) {
			                    //通过匹配value取得ds索引
			                    var index = datatypestore.find('value',value); 
			                    //通过索引取得记录ds中的记录集
			                    var record = datatypestore.getAt(index); 
			                    //返回记录集中的value字段的值
			                    return record.data.display;
			                  },
				            field: {
				                xtype: 'combo',
				                store : datatypestore,
								queryMode : 'local',
								displayField: "display",
								valueField: "value",
				                allowBlank: false
			            } },
				        { header: '允许空', dataIndex: 'fieldAllowblank', flex: 1,
			                renderer: function(value, cellmeta, record) {
			                    //通过匹配value取得ds索引
			                    var index = nullablestore.find('value',value); 
			                    //通过索引取得记录ds中的记录集
			                    var record = nullablestore.getAt(index); 
			                    //返回记录集中的value字段的值
			                    return record.data.display;
			                  },
				            field: {
			                xtype: 'combo',
			                store : nullablestore,
							queryMode : 'local',
							displayField: "display",
							valueField: "value",
			                allowBlank: false
			            } },
				        { header: '默认值', dataIndex: 'fieldDefault', flex: 2,
				            field: {
			                xtype: 'textfield',
			                allowBlank: false
			            } },
				        { header: '字段说明', dataIndex: 'COLUMN_COMMENTS', flex: 2,
				            field: {
			                xtype: 'textfield',
			                allowBlank: false
			            } },
				        { header: '关联表', dataIndex: 'phone', flex: 2,
				            field: {
			                xtype: 'textfield',
			                allowBlank: false
			            } },
				        { header: '关联字段', dataIndex: 'phone', flex: 2,
				            field: {
			                xtype: 'textfield',
			                allowBlank: false
			            } }
				    ],
				    width: 800,
				    plugins: [cellEditing],
				    //分页功能   
                    bbar: Ext.create('Ext.PagingToolbar', {   
                                    store: store,   
                                    displayInfo: true,   
                                    displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
                                    emptyMsg: "没有数据"   
                          } ) 
				}); 
				var gridConfig=Ext.create('Pallas.designer.portal.PropertiesSettingPanel',{

					region:"center",

				    store: propertystore
				});
				var tableitems=[gridp,gridConfig];
        	var p=Ext.create("Ext.panel.Panel" ,{
        			xtype:"panel",
        			layout:"border",
        			autoScroll:false,
        			items:tableitems
        		});
        	gridp.on("itemcontextmenu", function(his, record, item, index, e) {    
			    // itemcontextmenu( Ext.view.View this, Ext.data.Model record,  
			    // HTMLElement item, Number index, Ext.EventObject e, Object eOpts )  
			    // 分类代码表的右键菜单   
			    e.preventDefault();    
			    e.stopEvent();// 取消浏览器默认事件     
			    var array = [ {    
			                text : '修改',    
			                handler : function() {   
			  
			                }    
			            }, {    
			                text : '删除',    
			                handler : function() {  
			                    alert(record.raw.id);    
			                }    
			            }];    
			    var nodemenu = new Ext.menu.Menu({    
			        items : array    
			    });    
			    nodemenu.showAt(e.getXY());// 菜单打开的位置    
		    });
        	Ext.getCmp('<%=nid%>').add(p);
    		Ext.getCmp('<%=nid%>').doLayout();
        });
    </script>
</head>
</html>
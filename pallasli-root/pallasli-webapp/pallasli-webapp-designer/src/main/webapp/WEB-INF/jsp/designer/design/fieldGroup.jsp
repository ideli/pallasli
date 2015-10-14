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
        	    fields:['tableName', 'fieldName','fieldCaption', 'DATA_TYPE', 'DATA_LENGTH', 'DATA_PRECISION','NULLABLE','DATA_DEFAULT','COLUMN_ID'],
        	    paramOrder: ['params'],
    	        autoLoad:true,
    	        pageSize:5,
        	    proxy: {
        	        type: 'direct',
        	        autoLoad:true,
        	        directFn:FieldSetDirect.getFieldSetFields,
                    extraParams:{fieldsetKey:"<%=tableName%>"}
        	    }
        	});    
        	var propertystore=Ext.create('Ext.data.Store', {
        	    fields:['configName', 'PROPERTY_VALUE'],
        	    paramOrder: ['params'],
    	        autoLoad:true,
    	        pageSize:5,
        	    proxy: {
        	        type: 'direct',
        	        autoLoad:true,
        	        directFn:FieldSetDirect.getFieldsetFieldPropetyValues,
                    extraParams:{tableName:"BM_A003",fieldName:""}
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
	                tbar:['->',{text:'引入字段',handler:function(){ 

		            	 var win = Ext.create('Pallas.designer.portal.ImportTableFieldToGroupWindow', {tableName:'<%=tableName%>' });
		            	 win.show();
		                }},{
            			text:'增加',handler:function(){

                			var searchPanel=Ext.create('Ext.panel.Panel',{});
		                    var searchWin = Ext.create('Ext.window.Window', {  
				                title : "增加",  
				                constrain:true,
				                width : 300,  
				                height : 200,  
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
       				}],
				    columns: [
				        { header: '字段英文名', dataIndex: 'fieldName', flex: 2 },
				        { header: '字段中文名', dataIndex: 'fieldCaption', flex: 2,
				            field: {
			                xtype: 'textfield',
			                allowBlank: false
			            } },
				        { header: '数据表', dataIndex: 'DATA_DEFAULT', flex: 2,
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
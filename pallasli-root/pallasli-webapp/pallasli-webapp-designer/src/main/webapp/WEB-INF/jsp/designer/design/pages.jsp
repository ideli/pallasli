<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%String basePath=request.getContextPath();
String nid=request.getAttribute("nid").toString();
Object tableNameO=request.getAttribute("tableName");
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
        	    fields:['page_name', 'page_caption','page_comment', 'page_layout'],
        	    paramOrder: ['params'],
    	        autoLoad:true,
    	        pageSize:5,
        	    proxy: {
        	        type: 'direct',
        	        autoLoad:true,
        	        directFn:PageDirect.getPages,
        	        reader: {
                	    idProperty: 'NAME',
                	    totalProperty: 'members.total',
        	            type: 'json',
        	            root: 'members.results'
        	        },
                    extraParams:{ }
        	    }
        	});    
        	var propertystore=Ext.create('Ext.data.Store', {
        	    fields:['PROPERTY_NAME', 'PROPERTY_VALUE'],
        	    paramOrder: ['params'],
    	        autoLoad:true,
    	        pageSize:5,
        	    proxy: {
        	        type: 'direct',
        	        autoLoad:true,
        	        directFn:TableDirect.getFieldPropetyValues,
        	        reader: {
                	    idProperty: 'NAME',
                	    totalProperty: 'members.total',
        	            type: 'json',
        	            root: 'members.results'
        	        },
                    extraParams:{tableName:"bm_a003",fieldName:""}
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
            			text:'增加',handler:function(){
    	                	
        				}
       				}],
				    columns: [
						        { header: '页面英文名', dataIndex: 'page_name', flex : 2 },
						        { header: '页面表中文名',  dataIndex: 'page_caption', flex: 2,
						            field: {
					                xtype: 'textfield',
					                allowBlank: false
					            } },
						        { header: '标题',  dataIndex: 'page_key', flex: 1,
						            field: {
					                xtype: 'textfield',
					                allowBlank: false
					            } },
						        { header: '页面类型',  dataIndex: 'page_comments', flex: 4,
						            field: {
					                xtype: 'textfield',
					                allowBlank: false
					            } },
						        { header: '子类型',  dataIndex: 'config', flex: 4,
						            field: {
					                xtype: 'textfield',
					                allowBlank: false
					            } },
						        { header: '字段组合',  dataIndex: 'config', flex: 4,
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
			 var cellEditing2 = Ext.create('Ext.grid.plugin.CellEditing', {
	       	        clicksToEdit: 1
	       	     });
				var gridConfig=Ext.create('Ext.grid.Panel', {
					region:"center",
					tbar:[{xtype:'label',text:'字段组合配置'},'->',{text:'保存'}],
	                border:false,
				    store: propertystore,
				    columns: [
				        { header: '配置项', dataIndex: 'PROPERTY_NAME', flex : 1 },
				        { header: '配置值',  dataIndex: 'PROPERTY_VALUE', flex: 3,
				            field: {
			                xtype: 'textfield',
			                allowBlank: false
			            } }
				    ],
				    plugins: [cellEditing2]
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
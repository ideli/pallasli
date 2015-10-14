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
         	Ext.create('Ext.data.Store', {
        	    storeId:'simpsonsStore',
        	    fields:['TABLE_NAME', 'table_caption', 'table_id', 'table_comment', 'table_config'],
    	        autoLoad:true,
    	        pageSize:5,
        	    proxy: {
        	        type: 'direct',
        	        autoLoad:true,
        	        directFn:TableDirect.getTables
        	    }
        	});     
        	Ext.FieldPanel=Ext.extend(Ext.panel.Panel ,{
        			xtype:"panel",
        			layout:"border",
        			autoScroll:false,
	                border:false,
        			initComponent: function(){
        				this.items=[
        					Ext.create('Ext.grid.Panel', {
								    title: '基本属性',
	        						region:"west",
	        		                border:false,
								    store: Ext.data.StoreManager.lookup('simpsonsStore'),
								    columns: [
								        { header: '配置分类',  dataIndex: 'name' },
								        { header: '配置项', dataIndex: 'email', flex: 1 },
								        { header: '配置项类型', dataIndex: 'email', flex: 1 },
								        { header: '配置项对应属性', dataIndex: 'phone' }
								    ],
								    width: 800,
								    //分页功能   
				                    bbar: Ext.create('Ext.PagingToolbar', {   
				                                    store: Ext.data.StoreManager.lookup('simpsonsStore'),   
				                                    displayInfo: true,   
				                                    displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
				                                    emptyMsg: "没有数据"   
				                          } ) 
								}),{
        						xtype:"panel",
        						region:"center",
        						layout:'fit',
        		                border:false,
        						title:'默认配置',
        						width:614
        					}
        				];
        				Ext.FieldPanel.superclass.initComponent.call(this);
        			}
        		});
        	
        	Ext.getCmp('<%=nid%>').add(new Ext.FieldPanel());
    		Ext.getCmp('<%=nid%>').doLayout();
        });
    </script>
</head>
</html>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%String basePath=request.getContextPath();
String nid=request.getAttribute("nid").toString();
Object prefixionO=request.getAttribute("prefixion");
String prefixion=prefixionO==null?"":prefixionO.toString();
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
        	    fields:['name', 'caption', 'key', 'config','comments'],
        	    //paramOrder: ['sort', 'dir', 'start', 'limit','params'], 
        	    //paramOrder: ['sort', 'dir', 'start', 'limit','pre'],
        	    paramOrder: ['params'],
    	        autoLoad:true,
    	        pageSize:5,
        	    proxy: {
        	        type: 'direct',
        	        autoLoad:true,
        	        directFn:TableDirect.getTables,
        	        reader: {
                	    idProperty: 'name',
                	    totalProperty: 'members.total',
        	            type: 'json',
        	            root: 'members.results'
        	        },
                    extraParams:{prefixion:"<%=prefixion%>"}
        	    }
        	});        
        	 var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
        	        clicksToEdit: 1
        	     });

        	 Ext.create('Ext.button.Button', {
        	        text: 'Update source',
        	        handler: function(){
        				 gridp.setSource({
        	                '(name)': 'Property Grid',
        	                grouping: false,
        	                autoFitColumns: true,
        	                productionQuality: true,
        	                created: new Date(),
        	                tested: false,
        	                version: 0.8,
        	                borderWidth: 2
        	            });
        	        }
        	    });
     	    var pageBaseSource={
                    "name": "Properties Grid",
                    "caption": false,
                    "title": true,
                    "layout": false,
                    "path": Ext.Date.parse('10/15/2006', 'm/d/Y')
                };
     	    var pageBasePropertyNames={
                    name:'页面英文名',
                    caption:'页面中文名',
                    title:'页面标题',
                    layout:'页面布局方式',
                    path:'页面路径'
                };
			var gridp=Ext.create('Ext.grid.property.Grid', {
				region:"west",
                width: 700,
                propertyNames:pageBasePropertyNames,
                source: pageBaseSource
            });

            Ext.create('Ext.grid.Panel', {
					region:"west",
	                border:false,
				    store: store,
				    columns: [
				        { header: '页面英文名', dataIndex: 'name', flex : 2 },
				        { header: '页面中文名',  dataIndex: 'caption', flex: 2,
				            field: {
			                xtype: 'textfield',
			                allowBlank: false
			            } },
				        { header: '页面标题',  dataIndex: 'key', flex: 1,
				            field: {
			                xtype: 'textfield',
			                allowBlank: false
			            } },
				        { header: '页面布局方式',  dataIndex: 'key', flex: 1,
				            field: {
			                xtype: 'textfield',
			                allowBlank: false
			            } },
				        { header: '页面路径',  dataIndex: 'comments', flex: 4,
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


     	    var pageConfigSource={
                    "name": "Properties Grid",
                    "caption": false,
                    "title": true,
                    "layout": false,
                    "path": Ext.Date.parse('10/15/2006', 'm/d/Y')
                };
     	    var pageConfigPropertyNames={
                    name:'页面英文名',
                    caption:'页面中文名',
                    title:'页面标题',
                    layout:'页面布局方式',
                    path:'页面路径'
                };
			var gridConfig=Ext.create('Ext.grid.property.Grid', {
				title:'默认配置',
				region:"center",
                propertyNames : pageConfigPropertyNames,
                source: pageConfigSource
            });
			var tableitems=[gridp,gridConfig];

        	var pageJbxx=Ext.create("Ext.panel.Panel" ,{
            		title:'基本属性',
        			xtype:"panel",
        			layout:"border",
        			autoScroll:false,
        			items:tableitems
        		});



        	var cellEditing2 = Ext.create('Ext.grid.plugin.CellEditing', {
    	        clicksToEdit: 1
    	     });
		var gridp2=Ext.create('Ext.grid.Panel', {
				region:"west",
                border:false,
			    store: store,
			    columns: [
			        { header: '字段组合英文名', dataIndex: 'name', flex : 3 },
			        { header: '字段组合显示名',  dataIndex: 'caption', flex: 3,
			            field: {
		                xtype: 'textfield',
		                allowBlank: false
		            } },
			        { header: '排序',  dataIndex: 'config', hidden:true,flex: 1,
			            field: {
		                xtype: 'textfield',
		                allowBlank: false
		            } }
			    ],
			    width: 300,
			    plugins: [cellEditing2],
			    //分页功能   
                bbar: Ext.create('Ext.PagingToolbar', {   
                                store: store,   
                                displayInfo: true,   
                                displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
                                emptyMsg: "没有数据"   
                      } ) 
			});
		var cellEditing3 = Ext.create('Ext.grid.plugin.CellEditing', {
	        clicksToEdit: 1
	     });
	var gridp3=Ext.create('Ext.grid.Panel', {
			region:"west",
            border:false,
		    store: store,
		    columns: [
		        { header: '字段英文名', dataIndex: 'name', flex : 2 },
		        { header: '字段显示名',  dataIndex: 'caption', flex: 2,
		            field: {
	                xtype: 'textfield',
	                allowBlank: false
	            } },
		        { header: '排序',  dataIndex: 'comments', hidden:true,flex: 2,
		            field: {
	                xtype: 'textfield',
	                allowBlank: false
	            } }
		    ],
		    width: 300,
		    plugins: [cellEditing3],
		    //分页功能   
            bbar: Ext.create('Ext.PagingToolbar', {   
                            store: store,   
                            displayInfo: true,   
                            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
                            emptyMsg: "没有数据"   
                  } ) 
		});


	    var pageFiledsetConfigSource={
            "name": "Properties Grid",
            "caption": false,
            "title": true,
            "layout": false,
            "path": Ext.Date.parse('10/15/2006', 'm/d/Y')
        };
	    var pageFiledsetConfigPropertyNames={
            name:'页面英文名',
            caption:'页面中文名',
            title:'页面标题',
            layout:'页面布局方式',
            path:'页面路径'
        };
		var gridFiledsetConfig=Ext.create('Ext.grid.property.Grid', {
			title:'默认配置',
			region:"center",
	        propertyNames : pageFiledsetConfigPropertyNames,
	        source: pageFiledsetConfigSource
	    });



	    var pageFiledConfigSource={
            "name": "Properties Grid",
            "caption": false,
            "title": true,
            "layout": false,
            "path": Ext.Date.parse('10/15/2006', 'm/d/Y')
        };
	    var pageFiledConfigPropertyNames={
            name:'页面英文名',
            caption:'页面中文名',
            title:'页面标题',
            layout:'页面布局方式',
            path:'页面路径'
        };
		var gridFiledConfig=Ext.create('Ext.grid.property.Grid', {
			title:'默认配置',
			region:"center",
	        propertyNames : pageFiledConfigPropertyNames,
	        source: pageFiledConfigSource
	    });
		var tableitems2=[{
			region:"center",
			layout:"border",items:[gridp2,gridFiledsetConfig]},{
			region:"east",
            title:'字段',
			layout:"border",width:600,items:[gridp3,gridFiledConfig]}
		];



    		
        	var pageFields=Ext.create("Ext.panel.Panel" ,{
        		title:'页面组件属性',
    			xtype:"panel",
    			layout:"border",
    			autoScroll:false,
    			items:tableitems2
    		});
        	var p=Ext.create("Ext.tab.Panel" ,{
        			xtype:"panel",
        			layout:"border",
        			autoScroll:false
        		});
			p.add(pageJbxx);
			p.add(pageFields);
			p.setActiveTab(pageJbxx);

        	Ext.getCmp('<%=nid%>').add(p);
        	//Ext.getCmp('<%=nid%>').setTitle(Ext.getCmp('<%=nid%>').title+'---页面设置');
    		Ext.getCmp('<%=nid%>').doLayout();
        });
    </script>
</head>
</html>

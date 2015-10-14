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
        	
        	 
        	alert(0)
        	var store=Ext.create('Ext.data.Store', {
        	    fields:['tableName', 'tableCaption', 'tableKey'],
        	    paramOrder: ['params'],
    	        autoLoad:true,
    	        pageSize:5,
        	    proxy: {
        	        type: 'direct',
        	        autoLoad:true,
        	        directFn:TableDirect.getTables,
        	      /**  reader: {
                	    idProperty: 'name',
                	    totalProperty: 'members.total',
        	            type: 'json',
        	            root: 'members.results'
        	        },**/
                    extraParams:{prefixion:"<%=prefixion%>"}
        	    }
        	});  
        	var fieldstore=Ext.create('Ext.data.Store', {
        	    fields:['COLUMN_NAME'],
        	    paramOrder: ['params'],
        	    proxy: {
        	        type: 'direct',
        	        autoLoad:true,
        	        directFn:TableDirect.getTableColumns,
        	        reader: {
                	    idProperty: 'COLUMN_NAME',
                	    totalProperty: 'members.total',
        	            type: 'json',
        	            root: 'members.results'
        	        },
                    extraParams:{tableName:"BM_A003"}
        	    }
        	});        
        	 var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
        	        clicksToEdit: 1
        	     });
			var gridp=Ext.create('Ext.grid.Panel', {
					region:"center",
	                border:false,
	                tbar:['->',{
		            			text:'导入数据表',handler:function(){
					            	 var win = Ext.create('Pallas.designer.portal.ImportTableWindow', { });
					            	 win.show();				               
		        				}
		       				},{
		            			text:'增加',handler:function(){
		    	                	
		        				}
		       				}
	                ],
				    store: store,
				    columns: [
				        { header: '数据表英文名', dataIndex: 'tableName', flex : 2 },
				        { header: '数据表中文名',  dataIndex: 'tableCaption', flex: 2,
				            field: {
			                xtype: 'textfield',
			                allowBlank: false
			            } },
				        { header: '主键',  dataIndex: 'tableKey', flex: 1 }
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
			  var p=Ext.create("Ext.panel.Panel" ,{
      			xtype:"panel",
      			layout:"border",
      			autoScroll:false,
      			items:[gridp]
      		});
        	Ext.getCmp('<%=nid%>').add(p);
    		Ext.getCmp('<%=nid%>').doLayout();
        });
    </script>
</head>
</html>

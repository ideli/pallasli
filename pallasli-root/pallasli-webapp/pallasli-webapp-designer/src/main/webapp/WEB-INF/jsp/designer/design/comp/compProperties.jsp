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
        	 Ext.define('Config',{
			        extend: 'Ext.data.Model',

			        fields: [ 'configName', 'configCaption', 'configType', 'configParentid', 'id'
							    ],
							    proxy: {  
						              type: 'direct',  
						              api:{  
							            read: CompAction.loadCompConfigs,  
							            create: CompAction.saveCompConfigs
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
			        model: 'Config', 
			        autoLoad:true
			    });
			    var columns=[
						        { header: '组件分类', dataIndex: 'compTypeCaption', flex : 2,
						            field: {
					                xtype: 'textfield',
					                allowBlank: false
					            } },
						        { header: '组件名',  dataIndex: 'compCaption', flex: 2,
						            field: {
					                xtype: 'textfield',
					                allowBlank: false
					            } },
						        { header: '可配置属性',  dataIndex: 'configCaption', flex: 1,
						            field: {
					                xtype: 'textfield',
					                allowBlank: false
					            } },
						        { header: '可配置属性类型',  dataIndex: 'configType', flex: 1,
						            field: {
					                xtype: 'combo',
					                allowBlank: false
					            } },
						        { header: '备注',  dataIndex: 'caption', flex: 3,
						            field: {
					                xtype: 'textfield',
					                allowBlank: false
					            } }
						    ];
        	 var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
        	        clicksToEdit: 1
        	     });
        	 
        	var  dockedItems= [{
		            dock: 'top',
		            xtype: 'toolbar',
		            items: ["->",{
		                text: '增加行',
		                handler: function(){ 
		                	var rec=new Config();  
		                	gridstore.add(rec);
		                }
		            },{
		                text: '保存',
		                handler: function(btn,e){
		                	var records=gridp.getStore().getModifiedRecords( ) ;
		                	var recordDatas=[];
		                	for(var i=0;i<records.length;i++){
		                		recordDatas[i]=records[i].data;
		                	}
		                	console.log(records);
		                	CompAction.saveCompConfigs(recordDatas,function(){
		                		
		                	});
						}
		            },{
		                text: '删除',
		                handler: function(btn,e){	
		                	var records=gridp.getSelectionModel().getSelection();

		                	if(records.length>0){
			                	var recordData=records[0].data;
			                	Ext.Msg.show({
			                	     title:'是否删除?',
			                	     msg: '你确定要删除'+recordData.caption+'吗?',
			                	     buttons: Ext.Msg.YESNOCANCEL,
			                	     icon: Ext.Msg.QUESTION,
			                	     fn:function(btn){
											if(btn=="yes"){
						                	CompAction.deleteCompConfigs(recordData,function(result){
						                		knowledgePanel.getStore().removeAll();
					                			knowledgePanel.getStore().reload();
						                	}); 
											}
			                	     }
			                	});
		                	}
		                }
		            }]
		        }];
			var gridp=Ext.create('Ext.grid.Panel', {
					region:"west",
	                border:false,
	                dockedItems:dockedItems,
				    columns: columns,
				    store: gridstore,
				    width: 800,
				    plugins: [cellEditing],
				    //分页功能   
                    bbar: Ext.create('Ext.PagingToolbar', {   
                                    store: gridstore,   
                                    displayInfo: true,   
                                    displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
                                    emptyMsg: "没有数据"   
                          } ) 
				});
			var tableitems=[gridp,{
				xtype:"panel",
				region:"center",
                border:false,
				layout:'fit',
				title:'可配置的子属性',
				tbar:[{text:'添加'}],//树grid，分级展示各级可配置属性及类型
				width:614
			}
		];
    	var p=Ext.create("Ext.panel.Panel" ,{
    			xtype:"panel",
    			layout:"border",
    			autoScroll:false,
    			items:tableitems
    		});

        	Ext.getCmp('<%=nid%>').add(p);
    		Ext.getCmp('<%=nid%>').doLayout();
        });
    </script>
</head>
</html>

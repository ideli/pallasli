<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%String basePath=request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <link rel="stylesheet" type="text/css" href="/pallasli-web-js-css/styles/desktop/desktop.css" />

	<script type="text/javascript" src="/pallasli-web-js-css/scripts/include-ext.js?theme=classic"></script>
    <script type="text/javascript" src="<%=basePath%>/scripts/desktop/options-toolbar.js"></script>
	<script type="text/javascript" src="<%=basePath%>/extDirect/djn/djn-remote-call-support.js"></script>
	<script type="text/javascript" src="<%=basePath%>/extDirect/ejn/ejn-assert.js"></script>
	<script type="text/javascript" src="<%=basePath%>/extDirect/api/api.js"></script>

    <style>

.btn-comp {
		padding:0px;
		margin:'0px;
		border-style:none;
        background-color: #FFF;
        background-image: url() ;
    }
    </style>
    <script type="text/javascript">

    Ext.require([
        'Ext.direct.*',
        'Ext.data.*',
        'Ext.tree.*'
    ]);
    
  
        Ext.onReady(function(){
        	

            var store = Ext.create('Ext.data.TreeStore', {
                root: {
                    expanded: true,
                    children: [
                        { text: "detention", leaf: true },
                        { text: "homework", expanded: true, children: [
                            { text: "book report", leaf: true },
                            { text: "alegrbra", leaf: true}
                        ] },
                        { text: "buy lottery tickets", leaf: true }
                    ]
                }
            });

            var containerItems=[
{
	xtype:"button",
	border:false,
	text:'文本框',
	cType:'Ext.form.field.Text',
	iconCls:'icon-field-text',
	cls:'btn-comp',
	listeners: {
        click: function() { 
        }
    }
},{
	xtype:"button",
	text:'多行文本',
	cType:'Ext.form.field.TextArea',
	iconCls:'icon-field-textarea',
	cls:'btn-comp',
	listeners: {
        click: function() { 
        }
    }
},{
	xtype:"button",
	text:'单选框',
	cType:'Ext.form.field.Radio',
	iconCls:'icon-radio',
	cls:'btn-comp',
	listeners: {
        click: function() { 
        }
    }
},{
	xtype:"button",
	text:'复选框',
	cType:'Ext.form.field.Checkbox',
	iconCls:'icon-checked',
	cls:'btn-comp',
	listeners: {
        click: function() { 
        }
    }
},{
	xtype:"button",
	text:'日期框',
	cType:'Ext.form.field.Date',
	iconCls:'icon-field-date',
	cls:'btn-comp',
	listeners: {
        click: function() { 
        }
    }
},{
	xtype:"button",
	text:'下拉框',
	cType:'Ext.form.field.ComboBox',
	iconCls:'icon-combobox',
	cls:'btn-comp',
	listeners: {
        click: function() { 
        }
    }
},{
	xtype:"button",
	text:'按钮',
	cType:'Ext.button.Button',
	iconCls:'icon-button',
	cls:'btn-comp',
	listeners: {
        click: function() { 
        }
    }
}                         
                                
                                ];
            var treePanel1 = Ext.create('Ext.panel.Panel', {
                title: '容器',
                id:'cars',
                border:false,
				autoHeight:true,
                collapsible :true,
                items: containerItems
                
            });
            var treePanel2 = Ext.create('Ext.panel.Panel', {
                title: '数据源',
				autoHeight:true,
                collapsible :true,
                border:false,
                items: []
            });
            var treePanel3 = Ext.create('Ext.panel.Panel', {
                title: '工具条',
				autoHeight:true,
                collapsible :true,
                border:false,
                items: []
            });
            var treePanel4 = Ext.create('Ext.panel.Panel', {
                title: '表单组件',
				autoHeight:true,
                collapsible :true,
                border:false,
                items: []
            });
            var treePanel = Ext.create('Ext.tree.Panel', {
                title: '页面元素',
                border:false,
                width: 200,
				region:"west",
                store: store,
                tools:[{
                    type:'expand',
                    tooltip: 'Refresh form Data',
                    // hidden:true,
                    handler: function(event, toolEl, panel){
                    	treePanel.getRootNode().expand();
                    	
                    }
                },
                {
                    type:'collapse',
                    tooltip: 'Get Help',
                    handler: function(event, toolEl, panel){
                    	treePanel.getRootNode().collapse();
                    }
                },
                {
                    type:'left',
                    tooltip: 'Get Help',
                    handler: function(event, toolEl, panel){
                    	treePanel.collapse();
                    }
                }],
                rootVisible: false
            });

            var compPanel = Ext.create('Ext.panel.Panel', {
                title: '组件选择',
				region:"east",
                border:false,
                width: 200,
                //collapsible :true,
                tools:[{
                    type:'expand',
                    tooltip: 'Refresh form Data',
                    // hidden:true,
                    handler: function(event, toolEl, panel){
                    	treePanel1.expand();
                    	treePanel2.expand();
                    	treePanel3.expand();
                    	treePanel4.expand();
                    	
                    }
                },
                {
                    type:'collapse',
                    tooltip: 'Get Help',
                    handler: function(event, toolEl, panel){
                    	treePanel1.collapse();
                    	treePanel2.collapse();
                    	treePanel3.collapse();
                    	treePanel4.collapse();
                    }
                },
                {
                    type:'right',
                    tooltip: 'Get Help',
                    handler: function(event, toolEl, panel){
                    	compPanel.collapse();
                    }
                }],
                items: [treePanel1,treePanel2,treePanel3,treePanel4],
                rootVisible: false
            });
            var mainPanel= Ext.create('Ext.panel.Panel',
					{
						xtype:"panel",
						region:"center",
						layout:{type:'column'},
		                id:'trucks',
		                autoScroll:true,
						items:[{xtype:'panel',columnWidth:1,border:false,height:1000}]
					});
        	Ext.PortalPanel2=Ext.extend(Ext.panel.Panel ,{
        			xtype:"panel",
        			layout:"border",
                    border:false,
        			autoScroll:false,
        			initComponent: function(){
        				this.items=[
        					{
        						xtype:"panel",
        						region:"north",
        						autoHeight:true,
        		                border:false,
        						bodyStyle:{'background-color': 'transparent'},
        						layout:"table",
        						tbar:[
        						 		{
        									xtype:"button",
        									text:"新建"
        								},
        								{
        									xtype:"button",
        									text:"打开"
        								},
        								{
        									xtype:"button",
        									text:"保存"
        								},
        								{
        									xtype:"button",
        									text:"另存"
        								},
        								{
        									xtype:"button",
        									text:"导出"
        								},
        								{
        									xtype:"button",
        									text:"导入"
        								}
        							]
        					},compPanel,treePanel,mainPanel
        				];
        				Ext.PortalPanel2.superclass.initComponent.call(this);
        			}
        		});
	//	Ext.create('Ext.container.Viewport', {
	//		layout:'fit',
	//		items:[new Ext.PortalPanel()]
	//	});
		

     			Ext.create('Ext.container.Viewport', {
     				layout:'fit',
     				items:[new Ext.PortalPanel2()]
     			});
		 var cars = Ext.getCmp('cars');   
		 var trucks = Ext.getCmp('trucks'); 
		Ext.each(cars.items.items, function(comp) {  
			var proxy = new Ext.dd.DragSource(comp.getEl(), {group:'dd'});
			proxy.afterDragDrop = function(target, e, id) {
				var destComp=Ext.getCmp(id);  
				var srcCompd=Ext.getCmp(proxy.id);
				if(!srcCompd.cType){return;}
				var srcComp=Ext.create(srcCompd.cType, {
						fieldLabel:srcCompd.cType,
						columnWidth:0.3
					});		

	         	for(var i=0,len=trucks.items.items.length;i<len;i++){
	            	if(trucks.items.items[i].id==destComp.id){
	            		trucks.insert(i,srcComp);
	            		addNewProxy(srcComp);
	            		break;
	            	}
	            }
           		Ext.getCmp('trucks').doLayout();
                new Ext.dd.DDTarget(srcComp.getEl(), 'dd');
			};
		});  
		function addNewProxy(srcComp){

    		var proxy2 =new Ext.dd.DragSource(srcComp.getEl(), {group:'eee'});
            var eee = new Ext.dd.DDTarget(srcComp.getEl(), 'eee');
            proxy2.afterDragDrop = function(target2, e2, id2) {
				var destCompe=Ext.getCmp(id2);  
				var srcCompe=Ext.getCmp(proxy2.id);
				for(var ie=0,lene=trucks.items.items.length;ie<lene;ie++){
	            	if(trucks.items.items[ie].id==destCompe.id){
	            		trucks.insert(ie,srcCompe);
	            		break;
	            	}
	            }
           		Ext.getCmp('trucks').doLayout();
            }
		}

  		 Ext.each(trucks.items.items, function(comp) {
  			     var dd = new Ext.dd.DDTarget(comp.getEl(), 'dd');
  			 });  

});
    </script>
</head>
<body>
</body>

Ext.define('Pallas.design.portal.SysMenuPage', {
	extend : "Ext.panel.Panel",
	requires : [ 'Pallas.design.portal.ModuleTreePanel',
	             'Pallas.design.portal.ModuleMain'
	             ],
	border : false,
	menuKey : null,
	layout : 'border',
	autoHeight : true,
	collapsible : true,
	moduleTree : null,
	moduleMain : null,
	openviewInMain : Ext.emptyFn(),
	open : Ext.emptyFn(),
	design : Ext.emptyFn(),
	initComponent : function() {
		var MoudleTreeDesignPanel=Ext.create("Ext.panel.Panel",{
        	title:'模块菜单属性配置',
			region:'south',
			height:200,
			collapsible : true,
			collapsed:true,
			autoScroll:false,
			border:false 
		});
		var MoudlePageDesignPanel=Ext.create("Ext.panel.Panel",{
        	title:'页面组件、布局设置initialConfig',
			collapsible : true,
			collapsed:true,
			region:'east',
			width:200,
			autoScroll:false,
			border:false
		});
		  
		var me = this; 
		if(!me.moduleMain){
			me.moduleMain=Ext.create('Pallas.design.portal.ModuleMain',{
				region:'center'
				
			});
		}
		if(!me.moduleTree){
			me.moduleTree=Ext.create('Pallas.design.portal.ModuleTreePanel',{
				region:'center',
				menuKey:me.menuKey?me.menuKey:"",
				openviewInMain:me.openviewInMain,
				open : me.open,
				design : me.design,
				sysMenuPage:me.moduleMain,
				listeners:{
                	itemclick :function( view,  record, item, index, e,  eOpts){
                		me.openviewInMain(me.moduleMain,record);
                	}
                }
				
			});
		}

		me.moudleLeftPanel=Ext.create("Ext.panel.Panel",{
			region:'west',
			collapsible : true,
			width:200,
			layout:'border',
			items:[me.moduleTree,MoudleTreeDesignPanel]
		});
		me.moudleCenterPanel=Ext.create("Ext.panel.Panel",{
			region:'center', 
			layout:'border',
			items:[me.moduleMain,MoudlePageDesignPanel]
		});
		
 
		me.items=[me.moudleLeftPanel,me.moudleCenterPanel];
        me.callParent();
	}
});

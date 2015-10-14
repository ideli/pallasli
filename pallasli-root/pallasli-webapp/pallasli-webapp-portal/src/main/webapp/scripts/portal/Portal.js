Ext.define('Pallas.portal.Portal', {
	extend : "Ext.panel.Panel",
	requires : [ 'Pallas.portal.SysNavigation',
	             'Pallas.portal.ModuleNavigation',
	             'Pallas.portal.StateBar',
	             'Pallas.portal.Workshop',
	             'Pallas.lib.Message',
	             'Pallas.portal.SysMenu'
	             ],
	border : false,
	autoHeight : true,
	layout:"border",
	collapsible : false,
	sysMenuButtons:null,
	appMenuButtons:null,
	initComponent : function() {
		var me = this;
		me.sysNavigation=Ext.create('Pallas.portal.SysNavigation',Ext.apply({region:"north",height:26},{
			sysMenuButtons:me.sysMenuButtons,
			appMenuButtons:me.appMenuButtons
		}));
		me.stateBar=Ext.create('Pallas.portal.StateBar',{region:"south",height:20});
		

		me.firstWorkshop=Ext.create('Pallas.portal.Workshop',Ext.apply({
			id:"firstWorkshop",
			closable:false,
			title:"我的桌面",
			layout:"fit",
			items: [ ]
		},{}));
		me.workshopTabs=Ext.create('Ext.tab.Panel',{
			items: [me.firstWorkshop],
			region:"center"
		});
		me.items=[me.sysNavigation ,me.stateBar,me.workshopTabs];
		Pallas.portal.SinglePortal=me;
        me.callParent();
	},
	addWorkshopView:function(appKey,url,id,title,params){
		var me = this; 
		var workshop=Ext.create('Pallas.portal.Workshop',Ext.apply({
				id:id,
				closable:true,
				title:title,
				layout:"fit",
		        loader: {
		            url: '/'+appKey+'/jsppage.do?url='+url+'&panelId='+id+'&nid='+id,
		            autoLoad:true,
		            scripts:true,
	            	params :params
		        }
			},{}));
		me.workshopTabs.add(workshop);
		me.workshopTabs.setActiveTab(workshop);
	},
	openModule:function(appKey,moduleKey,params){
		var me = this;
		var directFn=ModuleMenuDirectAction.loadModuleTreeMenus;
		if(appKey=="pallas_design")directFn=MenuDirectAction.loadModuleTreeMenus;
		var moduleNavigation=Ext.create('Pallas.portal.ModuleNavigation',{
			menuKey:moduleKey,
			appKey:appKey,
			directFn:directFn,
			region:"west",width:300
		});
		
		var viewPanel=Ext.create('Ext.tab.Panel',{
			region:"center"
		});
		var workshopId=appKey+"-"+moduleKey;
		var workshop;
		if(Ext.getCmp(workshopId)){
			workshop=Ext.getCmp(workshopId);
		}else{
			 workshop=Ext.create('Pallas.portal.Workshop',Ext.apply({
				id:workshopId,
				closable:true,
				viewPanel:viewPanel,
				layout:"border",
				items: [moduleNavigation,viewPanel]
			},params));
			me.workshopTabs.add(workshop);
		}
		me.workshopTabs.setActiveTab(workshop);
	},
	getView:function(id){
		var view =Ext.getCmp(id);
		if(view){
			return view;
		}
		return false;
	},
	openView:function(appKey,viewKey,params){
		var panelId=appKey+"_"+viewKey+"_"+params.nodeName; 
		var me = this;
		var viewPanel=me.workshopTabs.activeTab.viewPanel;
	 
		var me=this;
		var view=me.getView(panelId);
		if(view){
			viewPanel.setActiveTab(view);
			return;
		}
		
		if(appKey=="pallas_design"){
			params.url="design_all/"+params.url;
		}
		
		var tabPage=Ext.create("Ext.panel.Panel",{
			title : params.title,
			region : 'center', // center region is required, no width/height specified
			xtype : 'tab',
			layout : 'fit',
			border : false,
			closable:true,
        	params :params,
			//margins : '5 5 0 0',
			id:panelId,
	        loader: {
	            url: '/'+appKey+'/jsppage.do?url='+params.url+'&panelId='+panelId+'&nid='+panelId,
	            autoLoad:true,
	            scripts:true,
            	params :params
	        },listeners: { 
	        	activate: function(tab){ 
	        		tab.loader.load(); 
        		}  
    		} 
		}); 
		viewPanel.add(tabPage);
		viewPanel.setActiveTab(tabPage);
	}
});

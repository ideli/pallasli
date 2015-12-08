/*
 * 文档面板、标签
 */
Mixky.wasoft.ViewPanel = function(appKey, viewKey, panelId, businessId,params, cfg){
	this.appKey = appKey;
	this.panelKey = viewKey;	
	this.params = params || {}; 
	this.cfg=cfg || {};
	
	this.type = this.params != null&&this.params.type != null?this.params.type : 0;
	this.businessId=businessId;
	this.panelId=panelId || Ext.id();
		
	this.app = Mixky.wasoft.cache.Applications[this.appKey];
	var url = this.app.url + '/view.do';
	this.processUNID=this.params != null&&this.params.unid != null?this.params.unid : '';
	this.processInstanceId=this.params != null&&this.params.docid != null?this.params.docid : '';		
	
	var view = Mixky.wasoft.lib.cache.getAppModuleView(appKey, viewKey);
	
	var title=view.title;
	var icon=view.icon;
			
	var panel = new Ext.Panel(Ext.apply({
			id : this.panelId,			
			key : view.name,
			layout : 'fit',
			autoLoad : {
				url : url,
				params : {
					panelid:this.panelId, 
					appkey : this.app.key,
					viewkey : this.panelKey, 					
					unid : this.processUNID,
					docid : this.processInstanceId,
					type : this.type,
					documentid :this.businessId
				},
				loadScripts : true,
				scripts	: true
				}				
	}, {}));
	
	
	panel.viewkey = viewKey;
	panel.initParams = view.params || {};
		
	var winId='win-'+viewKey+"-"+this.processUNID;	
		
	var config = {
		id:winId,
		title : title,
		iconCls : icon,
		width : 800,
		height: 600,				
		modal :true,
		layout : 'fit',
		border : false,
		maximizable : true,
		minimizable : false,
		closable : true,
		resizable : false,
		constrain : true,
		items : panel,		
        manager : MixkyApp.desktop.getManager(),
        items:panel,
        bbar : [{
        	text : '关闭',
        	minWidth : 50,
        	hidden : this.type == 0 ? false : true,
        	//iconAlign: 'top',
        	iconCls : 'icon-common-close',
        	handler : function(){
        		this.close();
	        },
	        scope: this
        }, '->']
	};
	
	Mixky.wasoft.ViewPanel.superclass.constructor.call(this, Ext.apply(config, cfg));
	
    Ext.onReady(this.initPanel, this);
};


Ext.extend(Mixky.wasoft.ViewPanel,Ext.Window,{
	
	appKey:undefined,
	
	app:undefined,
	
	panelKey:undefined,
	
	panelId:undefined,
	
	businessId:undefined,
		
	params:null,
	
	type:0,
	
	processUNID:null,
	
	processInstanceId:null,
	
	initPanel: function(){
				
		var viewPanel = this;		
		
		var fnGetButtons = eval(this.app.keyPrefix + 'AppDirect.getBpmButtons');
				
		// 获得有权限的按钮
		fnGetButtons(this.panelKey, this.businessId, this.processUNID, this.processInstanceId, this.type, function(result, e){
			if(result && result.success){
				if(viewPanel.type == 0){
					var bar = viewPanel.getBottomToolbar();
					// 处理普通按钮
					for(var i=0;i<result.buttons.length;i++){
						var button = viewPanel.buttons[result.buttons[i]];
						if(Ext.isDefined(button)){
							//var b = bar.add(Ext.apply({minWidth : 50, iconAlign : 'top', iconCls : button.icon}, button));
							var b = bar.add(Ext.apply({minWidth : 50}, button));	
						}
					}
					//处理bpm流程按钮
					if(viewPanel.processUNID!= ''){ 
						for(var i=0;i<result.bpmbuttons.length;i++){
							var b = bar.add(Mixky.wasoft.lib.BpmAction(viewPanel.app, viewPanel.panelKey,viewPanel.businessId,result.bpmbuttons[i],viewPanel.panelId));
				
						}	
					}
				}
				viewPanel.doLayout();
			}else{
				MixkyApp.showErrorMessage("打开按钮失败");
			}
		});
	}	
	
});


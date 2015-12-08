/*
 * 文档面板、标签
 */
Mixky.wasoft.DocumentPanel = function(appKey, documentPanelKey, panelId, businessId,params, cfg){
	this.appKey = appKey;
	this.panelKey = documentPanelKey;	
	this.params = params || {}; 
	this.cfg=cfg || {};
	
	this.type = this.params != null&&this.params.type != null?this.params.type : 0;
	this.businessId=businessId;
	this.panelId=panelId;
	
	var id = this.panelId;
		console.log(id);
	this.doc=Mixky.wasoft.DocumentPanel.getDocument(this.appKey,this.panelKey);		
	var dp=Mixky.wasoft.DocumentPanel.getPanel(this.appKey,this.panelKey);
	if(!dp){
		throw new Error("未查找到设计元素数据");
	}
	
	this.app = Mixky.wasoft.cache.Applications[this.appKey];
	var url = this.app.url + '/documentpanel.do';
	this.processUNID=this.params != null&&this.params.unid != null?this.params.unid : '';
	this.processInstanceId=this.params != null&&this.params.docid != null?this.params.docid : '';
	var panel = new Ext.Panel(Ext.apply({
			id : id,			
			key : dp.name,
			layout : 'fit',
			autoLoad : {
				url : url,
				params : {
					panelid:id, 
					appkey : this.app.key,
					panelkey : this.panelKey, 
					documentid : businessId,
					unid : this.processUNID,
					docid : this.processInstanceId,
					type : this.type
				},
				loadScripts : true,
				scripts	: true
				}				
	}, dp.config));
	
	panel.document=this;
	
	var config = {
		title : dp.title,
		iconCls : dp.icon,
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
	
	Mixky.wasoft.DocumentPanel.superclass.constructor.call(this, Ext.apply(config, cfg));
	
    Ext.onReady(this.initPanel, this);
};


Ext.extend(Mixky.wasoft.DocumentPanel,Ext.Window,{
	
	appKey:undefined,
	
	app:undefined,
	
	panelKey:undefined,
	
	panelId:undefined,
	
	businessId:undefined,
	
	doc:undefined,
	
	params:null,
	
	type:0,
	
	processUNID:null,
	
	processInstanceId:null,
	
	initPanel: function(){
				
		var documentPanel = this;
		
		var documentKey=Mixky.wasoft.DocumentPanel.getDocumentKey(this.appKey,this.panelKey);
		
		var fnGetpanelbuttons = eval(this.app.keyPrefix + 'AppDirect.getPanelButtons');
		
		// 获得有权限的按钮
		fnGetpanelbuttons(documentKey, this.businessId, this.processUNID, this.processInstanceId, this.type, function(result, e){
			if(result && result.success){
				if(documentPanel.type == 0){
					var bar = documentPanel.getBottomToolbar();
					// 处理普通按钮
					for(var i=0;i<result.buttons.length;i++){
						var button = documentPanel.doc.buttons[result.buttons[i]];
						if(Ext.isDefined(button)){
							//var b = bar.add(Ext.apply({minWidth : 50, iconAlign : 'top', iconCls : button.icon}, button));
							var b = bar.add(Ext.apply({minWidth : 50}, button));
							// 按钮初始值
	
							b.document = documentPanel;
						}
					}
					//处理bpm流程按钮
					if(documentPanel.processUNID!= ''){ 
						for(var i=0;i<result.bpmbuttons.length;i++){
							var b = bar.add(Mixky.wasoft.lib.BpmAction(documentPanel.app, documentKey,documentPanel.businessId,result.bpmbuttons[i],documentPanel.panelId));
						}	
					}
				}
				documentPanel.doLayout();
			}else{
				MixkyApp.showErrorMessage("打开文档（" + documentPanel.doc.title + "）标签及按钮失败");
			}
		});
	}	
	
});


Mixky.wasoft.DocumentPanel.getPanel=function(appKey,panelKey){
	
	var doc=Mixky.wasoft.DocumentPanel.getDocument(appKey,panelKey);
	if(doc){
			var panels=doc.panels;
			if(panels){
				var documentPanel=panels[panelKey];
				if(documentPanel){
					return documentPanel;
				}
			}
	}
	
	return false;
};

Mixky.wasoft.DocumentPanel.getDocumentKey=function(appKey,panelKey){
	var keys=panelKey.split('.');
	if(keys && keys.length>2){
		keys=keys[0]+'.'+keys[1];
		return keys;
	}
	return false;
};



Mixky.wasoft.DocumentPanel.getDocument=function(appKey,panelKey){
	var documentKey=Mixky.wasoft.DocumentPanel.getDocumentKey(appKey,panelKey);
	if(documentKey){
		return Mixky.wasoft.lib.cache.getAppDocument(appKey,documentKey);
	}
	return false;
};



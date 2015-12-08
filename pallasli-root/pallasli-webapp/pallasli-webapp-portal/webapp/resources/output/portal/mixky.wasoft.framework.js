
//=================================================================
//	�ļ�����mixky.wasoft.desktop.js
//=================================================================
/*
 *	Mixap 桌面架构
*/


Ext.namespace("Mixky.wasoft.desktop");


Mixky.wasoft.IDesktop = function(app, config){

	this.app = app;
	Ext.apply(this, config);
};

Mixky.wasoft.IDesktop.prototype = {
	// 应用
	app : null,
	// 初始化桌面
	init : Ext.emptyFn,
	// 设置消息窗口显示位置
	getAnimateTarget : function(){
		return document;
	},
	getModulePanel : function(appkey, modulekey, cfg){
		var panel;
		var id = 'mp-' + appkey + '.' + modulekey;
		var module = Mixky.wasoft.lib.cache.getAppModule(appkey, modulekey);
		var app = Mixky.wasoft.cache.Applications[appkey];
		if(Ext.isDefined(module.url)){
			panel = new Ext.Panel(Ext.apply({
				id : id,
				iconCls : module.icon,
				layout : 'fit',
				autoLoad : {
					url : app.url + '/' + module.url,
					params : {panelid:id, modulekey : modulekey},
					loadScripts : true,
					scripts	: true
				}
			}, cfg));
			panel.applicationkey = appkey;
			panel.modulekey = modulekey;
			panel.openView = Ext.emptyFn;
			panel.openUrl = Ext.emptyFn;
		}else{
			panel = new Mixky.wasoft.Module(Ext.apply({
				'applicationkey' : appkey,
				'modulekey' : modulekey,
				border : false
			}, cfg));
		}
		return panel;
	},
	getDocumentPanel : function(appkey, documentkey, id, params, cfg){
		return new Mixky.wasoft.Document(appkey, documentkey, id, params, cfg);
	},
	// 设置墙纸
	setWallpaper : Ext.emptyFn,
	// 设置墙纸位置
	setWallpaperPosition : Ext.emptyFn,
	// 设置透明度
	setTransparency : Ext.emptyFn,
	// 设置背景颜色
	setBackgroundColor : Ext.emptyFn,
	// 设置前景色
	setFrontColor : Ext.emptyFn,
	// 设置样式
	setTheme : Ext.emptyFn,
	// 添加桌面菜单项
	addContextMenuItem : Ext.emptyFn,
	// 添加快捷菜单
	addShortcut : Ext.emptyFn,
	// 移除快捷菜单
	removeShortcut : Ext.emptyFn,
	// 添加快速启动按钮
	addQuickStart : Ext.emptyFn,
	// 移除快速启动按钮
	removeQuickStart : Ext.emptyFn,
	// 添加桌面栏目
	addSubject : Ext.emptyFn,
	// 移除桌面栏目
	removeSubject : Ext.emptyFn,
	// 显示桌面
	showDesktop : Ext.emptyFn,
	// 获得文档
	getAppDocument : Ext.emptyFn,
	// 获得文档
	getDocument : Ext.emptyFn,
	// 获得模块
	getDocument : function(document, id){
		this.getAppDocument(Mixky.wasoft.lib.Context.activeApplicationKey, documentkey, id);
	},
	// 打开模块
	openModule : Ext.emptyFn,
	// 打开文档
	_openDocument : Ext.emptyFn,
	// 打开文档
	openAppDocument : function(appkey, documentkey, id, params, cfg){
		if(!Ext.isDefined(appkey)){
			alert('未指定应用标识');return;
		}
		if(typeof(id) == "string"){
			id = parseInt(id);
		}
		var doc = Mixky.wasoft.lib.cache.getAppDocument(appkey, documentkey);
		console.log(doc);
		if(!doc){
			MixkyApp.showErrorMessage('打开文档失败，文档类型[' + documentkey + ']没有定义。');
		}else{
			if(!id || id == 0){
				if(Ext.isDefined(doc.tablename) && doc.tablename != ''){
					Mixky.wasoft.lib.getNewTableRecordId(appkey, doc.tablename, function(id){
						MixkyApp.desktop.openAppDocument(appkey, documentkey, id, params, cfg);
					});
				}else{
					MixkyApp.showErrorMessage('打开文档失败，文档类型[' + documentkey + ']没有指定数据表。');
				}
			}else{
				this._openDocument(appkey, doc, id, params, cfg);
			}
		}
	},
	openDocument : function(documentkey, id, params, cfg){
		this.openAppDocument(Mixky.wasoft.lib.Context.activeApplicationKey, documentkey, id, params, cfg);
	},
	// 关闭文档
	_closeDocument : Ext.emptyFn,
	// 关闭文档
	closeAppDocument : function(appkey, documentkey, id){
		this._closeDocument(appkey, documentkey, id);
	},
	closeDocument : function(documentkey, id){
		this.closeAppDocument(Mixky.wasoft.lib.Context.activeApplicationKey, documentkey, id);
	},
	// 打开指定路径的窗口（do路径）
	openWindowWithDoUrl : function(appkey, id, dourl, wincfg, panelcfg, params){
		var app = Mixky.wasoft.cache.Applications[appkey];
		var win = this.getWindow(id);
		if(!Ext.isDefined(win)){
			var panel = new Ext.Panel(Ext.apply({
				id : 'p-' + id,
				layout : 'fit',
				autoLoad : {
					url : app.url + '/' + dourl,
					params : Ext.apply({panelid:'p-' + id}, params),
					loadScripts : true,
					scripts	: true
				}
			}, panelcfg));
			win = this.createWindow(Ext.apply({
				id : id,
				title : 'window - ' + dourl,
				layout : 'fit',
				border : false,
				maximizable : false,
				minimizable : true,
				resizable : false,
				constrain : true,
				width : 500,
				height : 500,
				items : panel
			}, wincfg));
		}
		win.show();
		return win;
	},
	// 打开指定路径的窗口（jsp路径）
	openWindowWithJspUrl : function(appkey, id, jspurl, wincfg, panelcfg, params){
		var app = Mixky.wasoft.cache.Applications[appkey];
		var win = this.getWindow(id);
		if(!Ext.isDefined(win)){
			var panel = new Ext.Panel(Ext.apply({
				id : 'p-' + id,
				layout : 'fit',
				autoLoad : {
					url : app.url + '/jsppage.do',
					params : Ext.apply({url: jspurl, panelid:'p-' + id}, params),
					loadScripts : true,
					scripts	: true
				}
			}, panelcfg));
			win = this.createWindow(Ext.apply({
				id : id,
				title : 'window - ' + jspurl,
				layout : 'fit',
				border : false,
				maximizable : false,
				minimizable : true,
				resizable : false,
				constrain : true,
				width : 500,
				height : 500,
				items : panel
			}, wincfg));
		}
		win.show();
		return win;
	},
	
	openWindow : Ext.emptyFn,
	
	closeWindow : Ext.emptyFn,
	
	closeAllWindow : Ext.emptyFn,
	// 删除文档
	deleteAppDocument : function(appkey, documentkey, id, fn){
		if(!Ext.isDefined(appkey)){
			alert('未指定应用标识');return;
		}
		if(typeof(id) == "string"){
			id = parseInt(id);
		}
		Ext.MessageBox.confirm('危险操作提示', '删除文档，该操作不可恢复，您确定吗？', function(btn){
			if(btn == 'yes'){
				var app = Mixky.wasoft.cache.Applications[appkey];
				var directFn = eval(app.keyPrefix + 'AppDirect.deleteDocument');
				directFn(documentkey, id, function(result, e){
					if(result && result.success){
						// 调用回调函数
						if(Ext.isDefined(fn)){
							fn.call();
						}
						MixkyApp.desktop.closeDocument(appkey, documentkey, id);
					}else{
						MixkyApp.showErrorMessage('删除文档失败');
					}
				});
			}
		});
	},
	// 删除文档
	deleteDocument : function(documentkey, id, fn){
		this.deleteAppDocument(Mixky.wasoft.lib.Context.activeApplicationKey, documentkey, id, fn);
	}
};
//=================================================================
//	�ļ�����mixky.wasoft.document.js
//=================================================================
/*
 * 系统模块标签
 */
Mixky.wasoft.Document = function(appkey, documentkey, documentid, params, cfg){
	this.applicationkey = appkey;
	this.documentkey = documentkey;
	this.documentid = documentid;
	if(Ext.isDefined(params)){
		this.params = params; 
	}
	var doc = Mixky.wasoft.lib.cache.getAppDocument(appkey, documentkey);
	var documentPanel = this;
	if(cfg && cfg.customSaveDirect){
		cfg.customSaveDirect = eval(cfg.customSaveDirect);
	}
	this.type = this.params != null&&this.params.type != null?this.params.type : 0;
	var config = {
            enableTabScroll:true,
            defaults: {
        		autoScroll:true
        	},
        	openerId : this.getOpenerId(),
            plugins: new Ext.ux.TabCloseMenu(),
          	autoWidth : true,
            manager : MixkyApp.desktop.getManager(),
            bbar : [{
            	text : '关闭',
            	minWidth : 50,
            	hidden : this.type == 0 ? false : true,
            	//iconAlign: 'top',
            	iconCls : 'icon-common-close',
            	handler : function(){
            		MixkyApp.desktop.closeAppDocument(this.applicationkey, this.documentkey, this.documentid);
    	        },
    	        scope: this
            }, '->']
    	};
	Mixky.wasoft.Document.superclass.constructor.call(this, Ext.apply(config, cfg));
	this.addEvents('afterdocumentupdate');
    Ext.onReady(this.initDocument, this);
};

Ext.extend(Mixky.wasoft.Document, Ext.TabPanel, {
	// Application Key
	applicationkey : undefined,
	// Document Key
	documentkey : '',
	// Document Id
	documentid : 0,
	// 初始参数
	params : null,
	// 初始化模块
	type : 0,

	initDocument : function(){
		console.log("initDocument");
		var documentPanel = this;
		var app = Mixky.wasoft.cache.Applications[this.applicationkey];
		var doc = Mixky.wasoft.lib.cache.getAppDocument(this.applicationkey, this.documentkey);
		var unid  = this.params != null&&this.params.unid  != null?this.params.unid :  '';
		var docid = this.params != null&&this.params.docid != null?this.params.docid : '';
		var fnGetpanelbuttons = eval(app.keyPrefix + 'AppDirect.getPanelButtons');
		
		// 获得有权限的按钮
		fnGetpanelbuttons(this.documentkey, this.documentid, unid, docid, this.type, function(result, e){
			if(result && result.success){
				for(var i=0;i<result.panels.length;i++){
					var panel = documentPanel.openPanel(result.panels[i]);
					if(i==0 && Ext.isDefined(panel)){
						documentPanel.activate(panel);
					}
				}
				if(documentPanel.type == 0){
					var bar = documentPanel.getBottomToolbar();
					// 处理普通按钮
					for(var i=0;i<result.buttons.length;i++){
						var button = doc.buttons[result.buttons[i]];
						if(Ext.isDefined(button)){
							//var b = bar.add(Ext.apply({minWidth : 50, iconAlign : 'top', iconCls : button.icon}, button));
							var b = bar.add(Ext.apply({minWidth : 50}, button));
							// 按钮初始值
	
							b.document = documentPanel;
						}
					}
					//处理bpm流程按钮
					if(unid != ''){ 
						for(var i=0;i<result.bpmbuttons.length;i++){
							
							var b = bar.add(Mixky.wasoft.lib.BpmAction(app, documentPanel.documentkey,documentPanel.documentid,result.bpmbuttons[i],documentPanel.id,{isDocument:true}));
							
						}	
					}
					
					// 处理流程按钮
					for(var i=0;i<result.flowbuttons.length;i++){
						var b = bar.add(Mixky.wasoft.workflow.FlowAction(documentPanel, result.flowbuttons[i]));
					}
					// 处理流程按钮
					for(var i=0;i<result.routebuttons.length;i++){
						var cfg = {routeKey : result.routebuttons[i].routeKey, text : result.routebuttons[i].actionName}
						//var b = bar.add(Mixky.wasoft.workflow.FlowAction(documentPanel, Mixky.wasoft.workflow.ACTION_COMPLETE, cfg));
						var b = bar.add(Mixky.wasoft.workflow.FlowAction(documentPanel, Mixky.wasoft.workflow.ACTION_COMPLETE, result.routebuttons[i].routeKey, cfg)); 
					}
				    documentPanel.customFlowSave = result.customFlowSave;
				}
				documentPanel.doLayout();
			}else{
				MixkyApp.showErrorMessage("打开文档（" + doc.title + "）标签及按钮失败");
			}
		});
	},
	refreshDocument : function(addNew){
		var documentPanel = this;
		var doc = Mixky.wasoft.lib.cache.getAppDocument(this.applicationkey, this.documentkey);
		// 清除文档标签
		documentPanel.removeAll();
		// 清除按钮
		var bar = documentPanel.getBottomToolbar();
		bar.removeAll();
		bar.add({
        	text : '关闭',
        	minWidth : 50,
        	iconAlign: 'top',
        	iconCls : 'icon-common-close',
        	handler : function(){
        		MixkyApp.desktop.closeAppDocument(this.applicationkey, this.documentkey, this.documentid);
	        },
	        scope: documentPanel
        });
		bar.addFill();
		documentPanel.doLayout();
		documentPanel.disable();
		if(addNew){
			Mixky.wasoft.lib.getNewTableRecordId(this.applicationkey, doc.tablename, function(id){
				documentPanel.documentid = id;
				// 重新装载
				documentPanel.initDocument();
				documentPanel.enable();
			});
		}else{
			// 重新装载
			documentPanel.initDocument();
			documentPanel.enable();
		}
	},
	deleteDocument : function(){
		var doc = this;
		MixkyApp.desktop.deleteAppDocument(doc.applicationkey, doc.documentkey, doc.documentid, function(){
			doc.afterDeleteDocument();
			MixkyApp.desktop.closeAppDocument(doc.applicationkey, doc.documentkey, doc.documentid);
		});
	},
	afterDeleteDocument : function(){
		this.afterDocumentUpdate();
	},
	afterDocumentUpdate : function(){
		if(Ext.isDefined(this.openerId)){
			var opener = Ext.getCmp(this.openerId);
			if(Ext.isDefined(opener) && Ext.isDefined(opener.refresh)){
				opener.refresh();
			}
		}
		this.fireEvent('afterdocumentupdate', this.applicationkey, this.documentkey, this.documentid);
	},
	confirmDocument : function(){
		var appkey = this.applicationkey;
		var key = this.documentkey;
		var id = this.documentid;
		var fnConfirm = function(){
			MixkyApp.desktop.closeAppDocument(appkey, key, id);
		};
		this.submitDocument(fnConfirm);
	},
	submitDocument : function(fnSubmit){
		var panel = this.get(0);
		if(!panel){
			alert('panel is no defined');
			return;
		}else if(this.customSaveDirect){
			var doc = this;
			var data = this.getSubmitData();
			this.customSaveDirect(this.documentkey, this.documentid, data, function(result, e){
				if(result && result.success){
					if(Ext.isFunction(fnSubmit)){
						fnSubmit.call(doc);
					}
					doc.afterDocumentUpdate();
					MixkyApp.showInfoMessage('文档保存完毕！');
				}
			});
		}else{
			this.submitPanel(panel, fnSubmit);
		}
	},
	submitPanel : function(panel, fnSubmit){
		if(panel != null){
			panel.submit(fnSubmit);
		}
	},
	submitPanelOver : function(panel, fnSubmit, msg){
		var next = panel.nextSibling();
		if(next != null){
			this.submitPanel(next, fnSubmit);
		}else{
			if(Ext.isFunction(fnSubmit)){
				fnSubmit.call(this);
			}
			else{
				if(!Ext.isDefined(msg)){
				   msg="文档保存完毕！";
				}
				//MixkyApp.showInfoMessage(msg);
				//IM.notify("提示", msg);
				Ext.MessageBox.show({title:'提示',msg:msg,modal:true,buttons:Ext.Msg.OK,
		         icon:Ext.Msg.INFO,width:250,closable:false});
			}
			this.afterDocumentUpdate();
			/*Ext.MessageBox.show({title:'提示',msg:msg,modal:true,buttons:Ext.Msg.OK,
            icon:Ext.Msg.INFO,width:250,closable:false});*/
		}
	},
	// 打开标签页

	openPanel : function(panelkey){
		var id = 'p-' + this.applicationkey + '.' + panelkey + '-' + this.documentid;
	 
		console.log(id);
		var panel = this.getItem(id);
		if(!panel){
			var doc = Mixky.wasoft.lib.cache.getAppDocument(this.applicationkey, this.documentkey);
			var dp = doc.panels[panelkey];
			if(!dp){
				return;
			}else{
				var app = Mixky.wasoft.cache.Applications[this.applicationkey];
				var url = app.url + '/documentpanel.do';
				panel = new Ext.Panel(Ext.apply({
					id : id,
					title : dp.title,
					iconCls : dp.icon,
					key : dp.name,
					layout : 'fit',
					autoLoad : {
						url : url,
						params :Ext.applyIf({
							panelid:id, 
							appkey : app.key, 
							panelkey : panelkey,
							documentid : this.documentid,
							unid : this.params != null&&this.params.unid != null?this.params.unid : '',
							docid : this.params != null&&this.params.docid != null?this.params.docid : '',
							type : this.type
						}, this.params || {}),
						loadScripts : true,
						scripts	: true
					},
					reload:function(){
						this.removeAll();
						this.load(this.initialConfig.autoLoad);
					},
					document : this,
					refresh : Ext.emptyFn,
					submit : function(afterSubminfn){
						this.document.submitPanelOver(this, afterSubminfn);
					}
				}, dp.config));
				this.add(panel);
			}
		}
		return panel;
	},
	openJspPage : function(id, url, cfg){
		var app = Mixky.wasoft.cache.Applications[this.applicationkey];
		var panel = this.getItem(id);
		if(!panel){
			panel = new Ext.Panel(Ext.apply({
				id : id,
				layout : 'fit',
				key : id,
				autoLoad : {
					url : app.url + '/jsppage.do',
					params : {
						url : url,
						panelid : id,
						documentkey : this.documentkey, 
						documentid : this.documentid
					},
					loadScripts : true,
					scripts	: true
				},
				reload:function(){
					this.removeAll();
					this.load(this.initialConfig.autoLoad);
				},
				document : this,
				refresh : Ext.emptyFn,
				submit : Ext.emptyFn
			}, cfg));
			this.add(panel);
		}
		this.activate(panel);
		return panel;
	},
	openUrl : function(id, url, cfg){
		var panel = this.getItem(id);
		if(!panel){
			panel = new Ext.Panel(Ext.apply({
				id : id,
				layout : 'fit',
				key : id,
				autoLoad : {
					url : url,
					params : {
						panelid : id,
						documentkey : this.documentkey, 
						documentid : this.documentid
					},
					loadScripts : true,
					scripts	: true
				},
				reload:function(){
					this.removeAll();
					this.load(this.initialConfig.autoLoad);
				},
				document : this,
				refresh : Ext.emptyFn,
				submit : Ext.emptyFn
			}, cfg));
			this.add(panel);
		}
		this.activate(panel);
		return panel;
	},
	getOpenerId : function(){
		var module = MixkyApp.desktop.getCurrentModule();
		if(module){
			var view = module.getCurrentView();
			if(view){
				return view.getId();
			}
		}
	},
	getSubmitData : function(){
		var data = {};
		for(var i=0;i<this.items.getCount();i++){
			var panel = this.items.get(i);
			if(Ext.isFunction(panel.getSubmitData)){
				var d = panel.getSubmitData();
				if(d !== false){
					data[panel.key] = d;
				}else{
					return false;
				}
			}
		}
		return data;
	},
	setButtonEnable : function(text, enable){
		var bar = this.getBottomToolbar();
		for(var i=0;i<bar.items.getCount();i++){
			var btn = bar.items.get(i);
			if(btn && Ext.isFunction(btn.getText) && btn.getText() == text){
				if(enable){
					btn.enable();
				}else{
					btn.disable();
				}
				return;
			}
		}
	}
});
//=================================================================
//	�ļ�����mixky.wasoft.documentpanel.js
//=================================================================
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



//=================================================================
//	�ļ�����mixky.wasoft.module.js
//=================================================================

Ext.namespace("Mixky.wasoft.module");

Mixky.wasoft.module.getModuleViewPanelId = function(appkey, viewkey){
	return 'v-' + appkey + '.' + viewkey;
};

/*
 * 系统模块标签
 */
Mixky.wasoft.Module = function(cfg){
	var appkey = cfg.applicationkey;
	var modulekey = cfg.modulekey;
	var module = Mixky.wasoft.lib.cache.getAppModule(appkey, modulekey);
	var modulePanel = this;
	var tree = new Ext.tree.TreePanel({
		region : 'west',
        width: 200,
        minSize: 150,
        maxSize: 400,
		split : true,
        collapsible: true,
        enableDrop: true,
        ddGroup: 'grid2tree',
        collapseMode:'mini',
        rootVisible:true,
        autoScroll:true,
        animCollapse:false,
        iconCls : 'icon-portal-modulemenu',
        title : '模块菜单',
        tools : [{
        	id : 'refresh',
        	qtip : '刷新选中菜单的下级菜单',
        	handler : function(){
        		tree.refresh();
	        }
        },{
        	id : 'maximize',
        	qtip : '打开选中节点',
        	handler : function(){
	        	tree.openNode();
	        }
        }],
        cmenu : new Ext.menu.Menu({
        	items : [{
        		text : '刷新',
        		iconCls : 'icon-common-refresh',
        		handler : function(){
        			tree.refresh();
        		}
        	}, {
        		text : '打开',
        		iconCls : 'icon-common-open',
        		handler : function(){
        			tree.openNode();
        		}
        	}]
        }),
        listeners	:{
	        'afterrender' : function(p){
	        	// p.getRootNode().expand();
	        	if('archive'==appkey){
	        		tree.expandAll();
	        	}else{
	        		p.getRootNode().expand();
	        	}
			},
				'render' : function(p){
		    	/*p.el.on('mouseleave',function(e,t,o){
		    		p.collapse();
		    	});
		    	
		    	p.getLayoutTarget().on('mouseenter',function(e,t,o){
		            p.expand();
		    	});
		    	p.getLayoutTarget().on('mouseover',function(e,t,o){
		            p.expand();
		    	});
		    	document.getElementById("ptree-xcollapsed").onmouseover = function() {
		            p.expand();
		        }*/
			},
	        'contextmenu' : function(node, e){
	        	node.select();
	            var c = node.getOwnerTree().cmenu;
	            c.contextNode = node;
	            c.showAt(e.getXY());
			},
			'dblclick' : function(node, e){
				//if(node.attributes.type == 'modulemenu'){
					Mixky.wasoft.lib.actions.openMenu(appkey, node.attributes.key, e);
				//}
			}
	    },
        root: {
        	id : 'root',
        	text : module.title,
        	iconCls : module.icon,
        	qtip : module.qtip,
        	type : 'root',
            params : {'modulekey' : module.key}
		},
        loader: new Ext.tree.TreeLoader({
            directFn : Ext.emptyFn,
        	paramOrder : ['params'],
        	baseParams : {params : {}},
        	listeners : {
        		'beforeload':function(loader, node){
        			loader.baseParams.params = tree.getNodeParams(node);
        			loader.baseParams.params.type = node.attributes.type;
        			
					var app = Mixky.wasoft.cache.Applications[appkey];
					loader.directFn = eval(app.keyPrefix + 'AppDirect.getModuleOutline');
					if(typeof(loader.directFn) != 'function'){
						alert(app.keyPrefix + 'AppDirect.getModuleOutline is not a function');
						return false;
					}
        		}
        	}
		}),
		selModel : new Ext.tree.DefaultSelectionModel({
			listeners	: {
				'selectionchange' :function(s, n){
					tree.openNode(n);
				}
			}
		}),
		getNodeParams : function(node){
			var params = {};
			if(node.parentNode){
				Ext.apply(params, this.getNodeParams(node.parentNode));
			}
			Ext.apply(params, node.attributes.params);
			return params;
		},
		refresh : function(node){
			if(!node){
				node = this.getSelectionModel().getSelectedNode();
			}
			if(!node || node.isLeaf()){
				return;
			}
			node.reload();
		},
		refreshParentNode : function(node){
			if(!node){
				node = this.getSelectionModel().getSelectedNode();
			}
			if(!node){
				return;
			}
			this.refresh(node.parentNode);
		},
		openNode : function(node){
			if(!node){
				node = this.getSelectionModel().getSelectedNode();
			}
			if(!node){
				return;
			}
			if(node.attributes.isFunctionMenu){
				Mixky.wasoft.lib.actions.openMenu(appkey, node.attributes.key);
				return;
			}
			var params = this.getNodeParams(node);
			if(Ext.isDefined(params.viewurl)){
				var id = Mixky.wasoft.module.getModuleViewPanelId(appkey, params.modulemenu);
				var urlPanel = modulePanel.openUrl(id, params.viewurl);
				if(urlPanel){
					if(urlPanel.refresh){
					    if(node.attributes.type == 'groupitem'){
							urlPanel.refresh(params);	
						}
					    else{
					    	if(node.firstChild){
								if(node.firstChild.attributes.type  == 'groupitem'){
									urlPanel.refresh(params);
								}
							}
					    }
					}else{
						urlPanel.initParams = params;
					}
				}
			}else if(Ext.isDefined(params.viewarchive)){
				// var id = Mixky.wasoft.module.getModuleViewPanelId(appkey, params.modulemenu);
				var viewPanel = modulePanel.openJspUrl(params.id,params.viewarchive,params,cfg);
				if(viewPanel){
					if(viewPanel.refresh){
						if (node.attributes.type == 'groupitem') {
							viewPanel.refresh(params);
						}
					}else{
						viewPanel.initParams = params;
					}
				}
			}else if(Ext.isDefined(params.viewkey)){
				var viewPanel = modulePanel.openView(params.viewkey);
				if(viewPanel){
					if(viewPanel.refresh){
						if (node.attributes.type == 'groupitem') {
							viewPanel.refresh(params);
						}
						else{
							if(node.firstChild){
								if(node.firstChild.attributes.type  == 'groupitem'){
									viewPanel.refresh(params);
								}
							}
						}
					}else{
						viewPanel.initParams = params;
					}
				}
			}else{
				node.expand();
			}
		}
	});
	var container = new Ext.TabPanel({
		region:'center',
        enableTabScroll:true,
        defaults: {
    		autoScroll:true,
    		closable : true
    	},
        plugins: new Ext.ux.TabCloseMenu()
	});

	container.on('tabchange', function(tabpanel, panel){
		if(panel){
			Mixky.wasoft.lib.Context.activeViewKey = panel.viewkey;
		}else{
			Mixky.wasoft.lib.Context.activeViewKey = '';
		}
	});
	var config = {
		layout : 'border',
		shim:false,
		animCollapse:false,
		constrainHeader:true,
		minimizable:true,
		maximizable:true,
		border:false,
		items : [tree, container]
	};
	
	this.refresh = function(rtree, rtab){
		if(rtree){
			tree.refreshParentNode();
		}
		if(rtab){
			var tab = container.getActiveTab();
			if(tab && Ext.isDefined(tab.refresh)){
				tab.refresh();
			}
		}
	};
	
	this.documentUpdateRefresh = function(activate){
		tree.refreshParentNode();
		if(activate){
			var tab = container.getActiveTab();
			if(tab && Ext.isDefined(tab.refresh)){
				tab.refresh();
			}
		}else{
			container.items.each(function(tab){
				if(Ext.isDefined(tab.refresh)){
					tab.refresh();
				}
			});
		}
	};
	
	this.moduleTree = tree;
	this.moduleContainer = container;
	
	Mixky.wasoft.Module.superclass.constructor.call(this, Ext.apply(config, cfg));
};

Ext.extend(Mixky.wasoft.Module, Ext.Panel, {
	closable : true,
	// Application Key
	applicationkey : '',
	// Module Key
	modulekey : '',
	// 获得当前视图
	getCurrentView : function(){
		var view = this.moduleContainer.getActiveTab();
		if(view && view.isModuleView){
			if(view.getCurrentView){
				var stbview = view.getCurrentView();
				if(stbview){
					return stbview;
				}else{
					return view;
				}
			}else{
				return view;
			}
		}
	},
	getViewPanel : function(viewkey){
		var appkey = this.applicationkey;
		var panels = this.moduleContainer.findBy(function(p, c){
			if(p.isModuleView && p.viewkey == viewkey){
				return true;
			}
		});
		var panel;
		if(panels.length > 0){
			panel = panels[0];
		}
		return panel;
	},
	// 打开视图
	openView : function(viewkey){
		var appkey = this.applicationkey;
		var panel = this.getViewPanel(viewkey);
		/*
		if(panels.length > 0){
			panel = panels[0];
		}
		var panels = this.moduleContainer.findBy(function(p, c){
			if(p.isModuleView && p.viewkey == viewkey){
				return true;
			}
		});
		//var id = Mixky.wasoft.module.getModuleViewPanelId(appkey, viewkey);
		//var panel = this.moduleContainer.getItem(id);
		*/
		if(!panel){
			var view = Mixky.wasoft.lib.cache.getAppModuleView(appkey, viewkey);
			if(!view){
				return;
			}else{
				var app = Mixky.wasoft.cache.Applications[appkey];
				if(!Ext.isDefined(app)){
					alert('未定义' + o.applicationKey + '应用');
					return;
				}
				panel = new Ext.Panel({
					title : view.title,
					iconCls : view.icon,
					layout : 'fit',
					autoLoad : {}
				});
				/*panel = MixkyApp.desktop.workspace.add({
					title : view.title,
					iconCls : view.icon,
					layout : 'fit',
					border : false,
					closable : true,
					autoLoad : {}
				});*/
				panel.autoLoad = {
					url : app.url + '/' + 'view.do',
					params : {panelid:panel.getId(), viewkey : view.key, appkey:appkey},
					loadScripts : true,
					scripts	: true
				};
				panel.isModuleView = true;
				panel.module = this;
				panel.viewkey = viewkey;
				panel.initParams = view.params;
				//MixkyApp.desktop.workspace.add(panel);
				this.moduleContainer.add(panel);
			}
		}
		//MixkyApp.desktop.workspace.activate(panel);
		this.moduleContainer.activate(panel);
		return panel;
	},
	openUrl : function(id, url, cfg){
		var appkey = this.applicationkey;
		var app = Mixky.wasoft.cache.Applications[appkey];
		var panel = this.moduleContainer.getItem(id);
		if(!panel){
			panel = new Ext.Panel(Ext.apply({
				id : id,
				layout : 'fit',
				autoLoad : {
					url : app.url + '/' + url,
					params : {panelid : id, appkey:appkey},
					loadScripts : true,
					scripts	: true
				}
			}, cfg));
			panel.module = this;
			this.moduleContainer.add(panel);
		}
		this.moduleContainer.activate(panel);
		return panel;		
	},
	openJspUrl : function(id, url, params, cfg){
		var appkey = this.applicationkey;
		var app = Mixky.wasoft.cache.Applications[appkey];
		var panel = this.moduleContainer.getItem(id);
		if(!panel){
			panel = new Ext.Panel(Ext.apply({
				id : id,
				layout : 'fit',
				autoLoad : {
					url : app.url + '/' + 'jsppage.do',
					params : Ext.apply({url:url, panelid : id, appkey:appkey}, params),
					loadScripts : true,
					scripts	: true
				}
			}, cfg));
			panel.module = this;
			this.moduleContainer.add(panel);
		}
		this.moduleContainer.activate(panel);
		return panel;		
	}
});
//=================================================================
//	�ļ�����mixky.wasoft.viewpanel.js
//=================================================================
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


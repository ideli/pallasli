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
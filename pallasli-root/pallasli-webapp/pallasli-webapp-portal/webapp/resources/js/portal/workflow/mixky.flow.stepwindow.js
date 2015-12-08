
Ext.namespace("Mixky.wasoft.workflow");

Mixky.wasoft.workflow.STEP_ERROR = -1;
Mixky.wasoft.workflow.STEP_PREPARE = 0;
Mixky.wasoft.workflow.STEP_NODESELECT = 1;
Mixky.wasoft.workflow.STEP_USERSELECT = 2;
Mixky.wasoft.workflow.STEP_CONFIRM = 3;
Mixky.wasoft.workflow.STEP_NOTICE = 4;
Mixky.wasoft.workflow.STEP_SUCCESS = 5;

Mixky.wasoft.workflow.OPERATION_NONE = 0;
Mixky.wasoft.workflow.OPERATION_PRE = 1;
Mixky.wasoft.workflow.OPERATION_NEXT = 2;
Mixky.wasoft.workflow.OPERATION_COMPLETE = 3;
Mixky.wasoft.workflow.OPERATION_CANCEL = 4;

Mixky.wasoft.workflow.StepWindow = function(appkey, documentkey, documentid, actiontype, routeKey, config, docdata){

	this.applicationkey = appkey;
	
	this.actiontype = actiontype;
	
	this.documentkey = documentkey;
	
	this.documentid = documentid;
	
	this.routeKey = routeKey;
	
	this.operation = Mixky.wasoft.workflow.OPERATION_NONE;
	
	this.step = 0;
	
	var stepWin = this;

	var app = Mixky.wasoft.cache.Applications[this.applicationkey];
	
	this.onSuccess = function(){
		var doc = MixkyApp.desktop.getAppDocument(this.applicationkey, this.documentkey, this.documentid);
		if(doc){
			var panel = Ext.getCmp(doc.getOpenerId());
			if(panel && panel.refresh){
				panel.refresh();
			}
		}
		this.window.close();
		MixkyApp.desktop.closeAppDocument(this.applicationkey, this.documentkey, this.documentid);
	};
	
	// 按钮
	var previousAction = new Ext.Action({
		text : '上一步',
		iconCls : 'icon-common-previous',
		handler : function(){
			stepWin.operation = Mixky.wasoft.workflow.OPERATION_PRE;
			stepWin.submit();
		}
	});
	var nextAction = new Ext.Action({
		text : '下一步',
		iconCls : 'icon-common-next',
		handler : function(){
			stepWin.operation = Mixky.wasoft.workflow.OPERATION_NEXT;
			stepWin.submit();
		}
	});
	var completeAction = new Ext.Action({
		text : '完成',
		iconCls : 'icon-common-complete',
		handler : function(){
			stepWin.operation = Mixky.wasoft.workflow.OPERATION_COMPLETE;
			stepWin.submit();
		}
	});
	var cancelAction = new Ext.Action({
		text : '取消',
		iconCls : 'icon-common-cancel',
		handler : function(){
			stepWin.operation = Mixky.wasoft.workflow.OPERATION_CANCEL;
			stepWin.submit();
			stepWin.window.close();
			var doc = MixkyApp.desktop.getAppDocument(appkey, documentkey, documentid);
			if(doc){
				var panel = doc.getActiveTab();
				if(panel && panel.show){
					panel.show();
				}
			}
		}
	});
	// 操作区
	this.panel = new Ext.Panel({
        layout: 'fit',
		padding : 10,
		beforeSubmit : Ext.emptyFn
	});
	this.panel.setpWindow = this;
	// 操作窗口
	this.window = new Ext.Window({
		title : '流程办理向导',
		manager : MixkyApp.desktop.getManager(),
		width : 600,
		height : 540,
        layout: 'fit',
        iconCls : 'icon-common-flow',
		modal : true,
		border : false,
        closeable : false,
        maximizable : false,
        minimizable : false,
        animCollapse :false,
        resizable :false,
		items : this.panel,
		bbar : [/*previousAction, '-', */nextAction, '->', completeAction, '-', cancelAction]
	});
	this.setFlowTitle = function(title){
		this.window.setTitle('流程办理向导 —— ' + title);
	};
	// 隐藏按钮
	this.hideButtons = function(){
		nextAction.hide();
		previousAction.hide();
		completeAction.hide();
		cancelAction.hide();
	};
	// 显示按钮
	this.showButtons = function(showPrevious, showNext){
		cancelAction.show();
		if(this.step != Mixky.wasoft.workflow.STEP_ERROR){
			if(this.step == Mixky.wasoft.workflow.STEP_CONFIRM){
				completeAction.show();
			}
			if(this.step > 1 && showPrevious){
				previousAction.show();
			}
			if(this.step < Mixky.wasoft.workflow.STEP_CONFIRM && showNext){
				nextAction.show();
			}
		}
	};
	// 提交表单
	this.submit = function(data){
		var params = {
			panelid : this.panel.id,
			documentkey : this.documentkey,
			documentid : this.documentid,
			actiontype : this.actiontype,
			operation : this.operation,
			routeKey : this.routeKey,
			step : this.step
		};
		if(data){
			params.data = Ext.util.JSON.encode(data);
		}
		if(this.step == Mixky.wasoft.workflow.STEP_ABORT || this.panel.beforeSubmit(params) !== false){
			this.hideButtons();
			this.panel.removeAll();
			this.panel.load({
				url : app.url + '/flow.do',
				params : params,
				timeout: 60,
				text: "正在与服务器交互，请稍候...",
				scripts: true
			});
		}
	};
	
	this.window.show();
	this.window.toFront();
	// 初始化调用准备流程启动
	this.submit(docdata);
};
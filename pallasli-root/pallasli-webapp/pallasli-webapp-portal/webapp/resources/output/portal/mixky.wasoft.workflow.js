
//=================================================================
//	�ļ�����mixky.flow.action.js
//=================================================================

Ext.namespace("Mixky.wasoft.workflow");

Mixky.wasoft.workflow.ACTION_REQUEST = 0;
Mixky.wasoft.workflow.ACTION_COMPLETE = 1;
Mixky.wasoft.workflow.ACTION_READCOMPLETE = 2;
Mixky.wasoft.workflow.ACTION_ASSISTCOMPLETE = 3;
Mixky.wasoft.workflow.ACTION_RETURN = 4;
Mixky.wasoft.workflow.ACTION_TAKEBACK = 5;
Mixky.wasoft.workflow.ACTION_ABORT = 6;
Mixky.wasoft.workflow.ACTION_ARCHIVE = 7;
Mixky.wasoft.workflow.ACTION_OPINION = 8;
Mixky.wasoft.workflow.ACTION_RESUME = 9;
Mixky.wasoft.workflow.ACTION_FORWARD = 10;

Mixky.wasoft.workflow.FlowAction = function(doc, actiontype, routeKey, config) {

    var doHandler = function(data) {
	if (actiontype == Mixky.wasoft.workflow.ACTION_OPINION) {
	    Mixky.wasoft.workflow.OpinionWindow(doc.applicationkey, doc.documentkey, doc.documentid, {objectkey :null});
	} else {
	    Mixky.wasoft.workflow.StepWindow(doc.applicationkey, doc.documentkey, doc.documentid, actiontype, routeKey, undefined, data);
	}
    };
    var handler = function() {
	// 判断是否需要提交表单

	switch (actiontype) {
	case Mixky.wasoft.workflow.ACTION_COMPLETE:
	case Mixky.wasoft.workflow.ACTION_READCOMPLETE:
	case Mixky.wasoft.workflow.ACTION_ASSISTCOMPLETE:
	    if (!doc.customFlowSave) {
		var data = doc.getSubmitData();
		if(data !== false){
		    var myHandler = doHandler.createDelegate(this, [data]);
		    doc.submitDocument(myHandler);
		}else{
		    return;
		}
	    } else {
		var data = doc.getSubmitData();
		if(data !== false){
		    doHandler(data);
		}else{
		    return;
		}
	    }
	    break;
	default:
	    doHandler();
	break;
	}
    };

    var defaultConfig = {
	    minWidth : 50, 
	    iconAlign : 'top', 
	    handler : handler
    };

    // 设置按钮文字及图标

    switch (actiontype) {
    case Mixky.wasoft.workflow.ACTION_COMPLETE:
	defaultConfig.text = '办理完毕';
	defaultConfig.iconCls = 'icon-common-flow-processover';
	break;
    case Mixky.wasoft.workflow.ACTION_READCOMPLETE:
	defaultConfig.text = '阅读完毕';
	defaultConfig.iconCls = 'icon-common-flow-readover';
	break;
    case Mixky.wasoft.workflow.ACTION_ASSISTCOMPLETE:
	defaultConfig.text = '协办完毕';
	defaultConfig.iconCls = 'icon-common-flow-assistover';
	break;
    case Mixky.wasoft.workflow.ACTION_RETURN:
	defaultConfig.text = '退回';
	defaultConfig.iconCls = 'icon-common-flow-turnback';
	break;
    case Mixky.wasoft.workflow.ACTION_TAKEBACK:
	defaultConfig.text = '拿回';
	defaultConfig.iconCls = 'icon-common-flow-takeback';
	break;
    case Mixky.wasoft.workflow.ACTION_REQUEST:
	defaultConfig.text = '申请办理';
	defaultConfig.iconCls = 'icon-common-flow-request';
	break;
    case Mixky.wasoft.workflow.ACTION_ABORT:
	defaultConfig.text = '撤销办理';
	defaultConfig.iconCls = 'icon-common-flow-stop';
	break;
    case Mixky.wasoft.workflow.ACTION_ARCHIVE:
	defaultConfig.text = '归档';
	defaultConfig.iconCls = 'icon-common-flow-archive';
	break;
    case Mixky.wasoft.workflow.ACTION_OPINION:
	defaultConfig.text = '填写意见';
	defaultConfig.iconCls = 'icon-common-flow-opinion';
	break;
    case Mixky.wasoft.workflow.ACTION_RESUME:
	defaultConfig.text = '恢复办理';
	defaultConfig.iconCls = 'icon-common-flow-resume';
	break;
    case Mixky.wasoft.workflow.ACTION_FORWARD:
	defaultConfig.text = '转移办理';
	defaultConfig.iconCls = 'icon-common-flow-forward';
	break;
    }

    return new Ext.Action(Ext.apply(defaultConfig, config));
};
//=================================================================
//	�ļ�����mixky.flow.opinionwindow.js
//=================================================================

Ext.namespace("Mixky.wasoft.workflow");

Mixky.wasoft.workflow.OpinionWindow = function(appkey, documentkey, documentid, config){

    this.applicationkey = appkey;

    this.documentkey = documentkey;

    this.documentid = documentid;

    var opinionWin = this;

    var app = Mixky.wasoft.cache.Applications[this.applicationkey];

    // 按钮
    var confirmAction = new Ext.Action({
	text : '填写完毕',
	iconCls : 'icon-common-confirm',
	handler : function(){
	    opinionWin.submit();
	}
    });
    var cancelAction = new Ext.Action({
	text : '取消',
	iconCls : 'icon-common-cancel',
	handler : function(){
	    opinionWin.window.close();
	}
    });
    var textarea = new Ext.form.TextArea({
	hideLabel: true
    });
    // 操作窗口
    this.window = new Ext.Window({
	title : '流程意见填写',
	manager : MixkyApp.desktop.getManager(),
	width : 350,
	height : 300,
	layout: 'fit',
	iconCls : 'icon-common-flow-opinion',
	modal : true,
	border : false,
	maximizable : false,
	minimizable : false,
	animCollapse :false,
	constrain : true,		
	resizable :false,
	items : textarea,
	bbar : ['->', confirmAction, '-', cancelAction]
    });
    // 提交表单
    this.submit = function(){
	// 获得已输入意见

	var fn = eval(app.keyPrefix + 'AppDirect.setFieldOpinion');
	fn(documentkey, documentid, textarea.getValue(), config.objectkey, function(result, e){
	    if(result && result.success){
		opinionWin.window.close();
		MixkyApp.showInfoMessage('意见保存成功!', result, e);
		if (config.objectid) {
		    Ext.getCmp(config.objectid).loadValue();
		}
	    }else{
		MixkyApp.showDirectActionFail('提交意见', result, e);
	    }
	});
    };
    // 获得已输入意见

    var fn = eval(app.keyPrefix + 'AppDirect.getFieldOpinion');
    fn(documentkey, documentid, config.objectkey, function(result, e){
	if(result && result.success){
	    textarea.setValue(result.opinion);
	}else{
	    MixkyApp.showDirectActionFail('获取意见', result, e);
	}
    });

    this.window.show();
    this.window.toFront();
};
//=================================================================
//	�ļ�����mixky.flow.run.js
//=================================================================

Ext.namespace("Mixky.wasoft.workflow");

//工作流操作类
Mixky.wasoft.workflow.Workflow=function(){};

Mixky.wasoft.BPMToken="0";

//启动工作视图
Mixky.wasoft.openView=Mixky.wasoft.workflow.Workflow.openView=function openView(processUNID,processInstanceId,appKey,viewKey,businessPID,type,extParams){
		
	var panelId='p-' + appKey + '.' +viewKey + '-' + businessPID;
	var viewPanelWin=new Mixky.wasoft.ViewPanel(appKey, viewKey, panelId, businessPID,Ext.applyIf({unid:processUNID,docid:processInstanceId,type:type},extParams || {}), {		
	});	
	
	viewPanelWin.show();
	
};

//启动工作文档
Mixky.wasoft.openDocument=Mixky.wasoft.workflow.Workflow.openDocument=function openDocument(processUNID,processInstanceId,appKey,documentKey,businessPID,type,extParams){
	
	MixkyApp.desktop.openAppDocument(appKey, documentKey, businessPID, Ext.applyIf({unid:processUNID,docid:processInstanceId,type:type},extParams || {}));
	
};

//启动工作文档面板
Mixky.wasoft.openDocumentPanel=Mixky.wasoft.workflow.Workflow.openDocumentPanel=function openDocumentPanel(processUNID,processInstanceId,appKey,documentPanelKey,businessPID,type,extParams){
	var panelId='p-' + appKey + '.' +documentPanelKey + '-' + businessPID;
	var documentPanelWin=new Mixky.wasoft.DocumentPanel(appKey, documentPanelKey, panelId, businessPID,Ext.applyIf({unid:processUNID,docid:processInstanceId,type:type},extParams || {}),{		
	});	
	
	documentPanelWin.show();
};

Mixky.wasoft.openWorkflow=Mixky.wasoft.workflow.Workflow.openWorkflow=function openWorkflow(processUNID,processInstanceId,appKey,formKey,businessPID,type,extParams){
	try{
	
		if(!businessPID || !parseInt(businessPID)){
			businessPID=0;
		}
		else{
			businessPID = parseInt(businessPID,10);
		}
		

		if(!type || !parseInt(type)){
			type=0;
		}		
		
		//console.log(processUNID,processInstanceId,appKey,formKey,businessPID,type,extParams);
		
		//查询视图
		var viewKey=formKey;
		var view = Mixky.wasoft.lib.cache.getAppModuleView(appKey, viewKey);
		if(view){
			Mixky.wasoft.workflow.Workflow.openView(processUNID,processInstanceId,appKey,viewKey,businessPID,type,extParams);
			return;
		}
		
		//查询文档
		var documentKey=formKey;
		var doc=Mixky.wasoft.lib.cache.getAppDocument(appKey,documentKey);
		if(doc){
			Mixky.wasoft.workflow.Workflow.openDocument(processUNID,processInstanceId,appKey,documentKey,businessPID,type,extParams);
			return;
		}
		
		//查询文档面板
		var documentPanel=Mixky.wasoft.DocumentPanel.getPanel(appKey,formKey);		
		if(documentPanel){
			var documentPanelKey=formKey;
			
			Mixky.wasoft.workflow.Workflow.openDocumentPanel(processUNID,processInstanceId,appKey,documentPanelKey,businessPID,type,extParams);
			return;
		}	
		
		Ext.MessageBox.show({title:'提示',msg:"未查询到表单定义，请联系管理员！",
			modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.WARNING,width:300,closable:false});
		
	}catch(e){
		//console.log(e.message);
		Ext.MessageBox.show({title:'提示',msg:"系统出现错误，请联系管理员！",
			modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.WARNING,width:300,closable:false});
	}
};



//=================================================================
//	�ļ�����mixky.flow.stepwindow.js
//=================================================================

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
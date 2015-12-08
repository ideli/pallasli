
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



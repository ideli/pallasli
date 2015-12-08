
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
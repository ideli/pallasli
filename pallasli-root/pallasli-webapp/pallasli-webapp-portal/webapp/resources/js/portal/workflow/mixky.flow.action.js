
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
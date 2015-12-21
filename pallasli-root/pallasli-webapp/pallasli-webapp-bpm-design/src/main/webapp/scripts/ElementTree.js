Ext.define('Pallas.activitiDesigner.ElementTree', {
	extend : "Ext.tree.Panel",
	requires : [ "Pallas.activitiDesigner.config.ElementsConfig",
			"Pallas.activitiDesigner.config.BaseConfig",
			'Pallas.activitiDesigner.utils.HtmlUtils',
			'Pallas.activitiDesigner.utils.AllComponents',
			'Pallas.activitiDesigner.shape.Association',
			'Pallas.activitiDesigner.shape.BoundaryErrorEvent',
			'Pallas.activitiDesigner.shape.BoundaryMessageEvent',
			'Pallas.activitiDesigner.shape.BoundarySignalEvent',
			'Pallas.activitiDesigner.shape.BoundaryTimerEvent',
			'Pallas.activitiDesigner.shape.CallActivity',
			'Pallas.activitiDesigner.shape.CatchMessageEvent',
			'Pallas.activitiDesigner.shape.CatchSignalEvent',
			'Pallas.activitiDesigner.shape.CatchTimerEvent',
			'Pallas.activitiDesigner.shape.EndErrorEvent',
			'Pallas.activitiDesigner.shape.EndNoneEvent',
			'Pallas.activitiDesigner.shape.EventGateway',
			'Pallas.activitiDesigner.shape.EventSubProcess',
			'Pallas.activitiDesigner.shape.ExclusiveGateway',
			'Pallas.activitiDesigner.shape.InclusiveGateway',
			'Pallas.activitiDesigner.shape.Lane',
			'Pallas.activitiDesigner.shape.MailTask',
			'Pallas.activitiDesigner.shape.ManualTask',
			'Pallas.activitiDesigner.shape.ParallelGateway',
			'Pallas.activitiDesigner.shape.Pool',
			'Pallas.activitiDesigner.shape.ReceiveTask',
			'Pallas.activitiDesigner.shape.ScriptTask',
			'Pallas.activitiDesigner.shape.SequenceFlow',
			'Pallas.activitiDesigner.shape.StartErrorEvent',
			'Pallas.activitiDesigner.shape.StartMessageEvent',
			'Pallas.activitiDesigner.shape.StartNoneEvent',
			'Pallas.activitiDesigner.shape.StartTimerEvent',
			'Pallas.activitiDesigner.shape.SubProcess',
			'Pallas.activitiDesigner.shape.TextAnnotation',
			'Pallas.activitiDesigner.shape.ThrowNoneEvent',
			'Pallas.activitiDesigner.shape.ThrowSignalEvent',
			'Pallas.activitiDesigner.shape.UserTask',
			'Pallas.activitiDesigner.shape.BusinessRuleTask',
			'Pallas.activitiDesigner.shape.ServiceTask' ],
	border : true,
	rootVisible : false,

	initComponent : function() {
		var me = this;
		var elementsConfig = Pallas.activitiDesigner.config.ElementsConfig;
		var baseConfig = Pallas.activitiDesigner.config.BaseConfig;
		var children_shijian = [
				{
					text : "开始事件",
					name : "startNoneEvent",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.startNoneEvent.icon,
					leaf : true
				},
				{
					text : "结束事件",
					name : "endNoneEvent",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.endNoneEvent.icon,
					leaf : true
				},
				{
					text : "结束错误事件",
					name : "endErrorEvent",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.endErrorEvent.icon,
					leaf : true
				} ];
		var children_gateway = [
				{
					text : "单选分支（网关）",
					name : "exclusiveGateway",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.exclusiveGateway.icon,
					leaf : true
				},
				{
					text : "并行分支（网关）",
					name : "parallelGateway",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.parallelGateway.icon,
					leaf : true
				},
				{
					text : "多选分支（网关）",
					name : "inclusiveGateway",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.inclusiveGateway.icon,
					leaf : true
				},
				{
					text : "事件分支（网关）",
					name : "eventGateway",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.eventGateway.icon,
					leaf : true
				},
				{
					text : "顺序路由",
					name : "sequenceFlow",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.sequenceFlow.icon,
					leaf : true
				} ];
		var children_process = [
				{
					text : "嵌入子流程",
					name : "subProcess",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.subProcess.icon,
					leaf : true
				},
				{
					text : "事件子流程",
					name : "eventSubProcess",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.eventSubProcess.icon,
					leaf : true
				},
				{
					text : "调用子流程",
					name : "callActivity",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.callActivity.icon,
					leaf : true
				} ];
		var children_task = [
				{
					text : "用户任务",
					name : "userTask",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.userTask.icon,
					leaf : true
				},
				{
					text : "服务任务",
					name : "serviceTask",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.serviceTask.icon,
					leaf : true
				},
				{
					text : "业务规则任务",
					name : "businessRuleTask",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.businessRuleTask.icon,
					leaf : true
				},
				{
					text : "脚本任务",
					name : "scriptTask",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.scriptTask.icon,
					leaf : true
				},
				{
					text : "接受任务",
					name : "receiveTask",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.receiveTask.icon,
					leaf : true
				},
				{
					text : "人工任务",
					name : "manualTask",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.manualTask.icon,
					leaf : true
				},
				{
					text : "邮件任务",
					name : "mailTask",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.mailTask.icon,
					leaf : true
				} ];
		var children_event = [
				{
					text : "开始计时器事件",
					name : "startTimerEvent",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.startTimerEvent.icon,
					leaf : true
				},
				{
					text : "开始消息事件",
					name : "startMessageEvent",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.startMessageEvent.icon,
					leaf : true
				},
				{
					text : "开始错误事件",
					name : "startErrorEvent",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.startErrorEvent.icon,
					leaf : true
				},
				{
					text : "边界错误事件",
					name : "boundaryErrorEvent",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.boundaryErrorEvent.icon,
					leaf : true
				},
				{
					text : "边界定时事件",
					name : "boundaryTimerEvent",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.boundaryTimerEvent.icon,
					leaf : true
				},
				{
					text : "边界信号量事件",
					name : "boundarySignalEvent",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.boundarySignalEvent.icon,
					leaf : true
				},
				{
					text : "边界消息事件",
					name : "boundaryMessageEvent",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.boundaryMessageEvent.icon,
					leaf : true
				},
				{
					text : "中间定时捕获事件",
					name : "catchTimerEvent",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.catchTimerEvent.icon,
					leaf : true
				},
				{
					text : "中间信号捕获事件",
					name : "catchSignalEvent",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.catchSignalEvent.icon,
					leaf : true
				},
				{
					text : "中间消息捕获事件",
					name : "catchMessageEvent",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.catchMessageEvent.icon,
					leaf : true
				},
				{
					text : "中间空抛出事件",
					name : "throwNoneEvent",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.throwNoneEvent.icon,
					leaf : true
				},
				{
					text : "中间信号抛出事件",
					name : "throwSignalEvent",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.throwSignalEvent.icon,
					leaf : true
				} ];
		var children_other = [
				{
					text : "池",
					name : "pool",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.pool.icon,
					leaf : true
				},
				{
					text : "泳道",
					name : "lane",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.lane.icon,
					leaf : true
				},
				{
					text : "关联",
					name : "association",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.association.icon,
					leaf : true
				},
				{
					text : "文字注释",
					name : "textAnnotation",
					icon : baseConfig.imagesDependencesPath
							+ elementsConfig.textAnnotation.icon,
					leaf : true
				} ];
		me.root = {
			text : "Root node",
			expanded : true,
			children : [ {
				text : "事件",
				leaf : false,
				expanded : true,
				children : children_shijian
			}, {
				text : "网关路由",
				leaf : false,
				expanded : true,
				children : children_gateway
			}, {
				text : "子流程",
				leaf : false,
				expanded : true,
				children : children_process
			}, {
				text : "任务",
				leaf : false,
				expanded : true,
				children : children_task
			}, {
				text : "事件",
				leaf : false,
				expanded : true,
				children : children_event
			}, {
				text : "其他",
				leaf : false,
				expanded : true,
				children : children_other
			} ]
		};
		me.callParent();
		me.items.items[0].on("itemmousedown", function(view, item, element,
				index, e) {
			var shape = null;
			switch (item.raw.name) {
			case "startNoneEvent":
				shape = Ext.create("widget.startnoneevent");
				break;
			case "businessRuleTask":
				shape = Ext.create("widget.businessruletask");
				break;
			case "serviceTask":
				shape = Ext.create("widget.servicetask");
				break;
			case "userTask":
				shape = Ext.create("widget.usertask");
				break;
			case "scriptTask":
				shape = Ext.create("widget.scripttask");
				break;
			case "receiveTask":
				shape = Ext.create("widget.receivetask");
				break;
			case "manualTask":
				shape = Ext.create("widget.manualtask");
				break;
			case "mailTask":
				shape = Ext.create("widget.mailtask");
				break;
			case "exclusiveGateway":
				shape = Ext.create("widget.exclusivegateway");
				break;
			case "parallelGateway":
				shape = Ext.create("widget.parallelgateway");
				break;
			case "inclusiveGateway":
				shape = Ext.create("widget.inclusivegateway");
				break;
			case "eventGateway":
				shape = Ext.create("widget.eventgateway");
				break;
			case "pool":
				shape = Ext.create("widget.pool");
				break;
			case "lane":
				shape = Ext.create("widget.lane");
				break;
			case "subProcess":
				shape = Ext.create("widget.subprocess");
				break;
			case "eventSubProcess":
				shape = Ext.create("widget.eventsubprocess");
				break;
			case "callActivity":
				shape = Ext.create("widget.callactivity");
				break;
			case "startTimerEvent":
				shape = Ext.create("widget.starttimerevent");
				break;
			case "startMessageEvent":
				shape = Ext.create("widget.startmessageevent");
				break;
			case "startErrorEvent":
				shape = Ext.create("widget.starterrorevent");
				break;
			case "boundaryErrorEvent":
				shape = Ext.create("widget.boundaryerrorevent");
				break;
			case "boundaryTimerEvent":
				shape = Ext.create("widget.boundarytimerevent");
				break;
			case "boundarySignalEvent":
				shape = Ext.create("widget.boundarysignalevent");
				break;
			case "boundaryMessageEvent":
				shape = Ext.create("widget.boundarymessageevent");
				break;
			case "catchTimerEvent":
				shape = Ext.create("widget.catchtimerevent");
				break;
			case "catchSignalEvent":
				shape = Ext.create("widget.catchsignalevent");
				break;
			case "catchMessageEvent":
				shape = Ext.create("widget.catchmessageevent");
				break;
			case "throwNoneEvent":
				shape = Ext.create("widget.thrownoneevent");
				break;
			case "throwSignalEvent":
				shape = Ext.create("widget.throwsignalevent");
				break;
			case "endNoneEvent":
				shape = Ext.create("widget.endnoneevent");
				break;
			case "endErrorEvent":
				shape = Ext.create("widget.enderrorevent");
				break;
			case "sequenceFlow":
				shape = Ext.create("widget.sequenceflow");
				break;
			case "association":
				shape = Ext.create("widget.association");
				break;
			case "textAnnotation":
				shape = Ext.create("widget.textannotation");
				break;
			default:
				return;
			}
			shape.drawDragHtml();
		});
	}
});

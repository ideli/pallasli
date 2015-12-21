Ext.define('Pallas.activitiDesigner.Designer', {
	extend : "Ext.window.Window",
	requires : [ 'Pallas.activitiDesigner.utils.AllComponents',
			'Pallas.activitiDesigner.config.ToolBarConfig',
			'Pallas.activitiDesigner.config.ServerConfig',
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
			'Pallas.activitiDesigner.shape.UserTask' ],
	border : false,
	layout : "border",
	draggable : false,
	collapsible : false,
	constrain : true,
	initComponent : function(config) {
		var me = this;
		config = config || {};
		me.id = config.id || "Designer_id";
		var allComponents = Pallas.activitiDesigner.utils.AllComponents;
		allComponents.shapes = config.shapes || {};
		allComponents.elementTree = Ext.create(
				"Pallas.activitiDesigner.ElementTree", {
					region : "west",
					width : 200
				});
		allComponents.editorPanel = Ext.create(
				"Pallas.activitiDesigner.EditorPanel", {
					height : 800,
					width : 1500,
					border : false
				});
		var editorP = Ext.create("Ext.panel.Panel", {
			items : allComponents.editorPanel,
			region : "center",
			autoScroll : true
		});
		allComponents.attributePanel = Ext.create(
				"Pallas.activitiDesigner.BaseAttributePanel", {
					region : "east",
					width : 300
				});
		me.items = [ allComponents.elementTree, editorP,
				allComponents.attributePanel ];
		var toolBarConfig = Pallas.activitiDesigner.config.ToolBarConfig;
		var baseConfig = Pallas.activitiDesigner.config.BaseConfig;
		var serverConfig = Pallas.activitiDesigner.config.ServerConfig;
		me.tbar = [ {
			icon : baseConfig.imagesDependencesPath + toolBarConfig.save.icon,
			handler : function() {

				var saveServer = serverConfig.save;
				var server = serverConfig.server;
				var savePath = serverConfig.savePath;
				var data = allComponents.shapeDatas;
				if (data.toJSONString) {
					data = data.toJSONString();
				} else {
					data = JSON.stringify(data);
				}
				var id = me.id;
				Ext.Ajax.request({
					url : server + saveServer,
					params : {
						id : id,
						savePath : savePath,
						data : data
					},
					success : function(response) {
						var text = response.responseText;
						console.log(text);
					}
				});
			}
		}, {
			icon : baseConfig.imagesDependencesPath + toolBarConfig.del.icon,
			handler : function() {
				var shapes = allComponents.shapes;
				for ( var key in shapes) {
					if (shapes[key].isActived) {
						var activitiName = shapes[key].activitiName;
						allComponents.shapes[activitiName].destroy();
						delete allComponents.shapes[activitiName];
						delete allComponents.shapeDatas[activitiName];
					}
				}
			}
		} ];
		allComponents.designer = me;
		me.callParent();

		var savePath = serverConfig.savePath;
		var loadServer = serverConfig.load;
		var server = serverConfig.server;
		var id = me.id;
		Ext.Ajax.request({
			url : server + loadServer,
			params : {
				id : id,
				savePath : savePath
			},
			success : function(response) {
				var text = response.responseText;
				var shapeDatas = eval("(" + text + ")");

				Ext.apply(allComponents.shapeDatas, shapeDatas);
				for ( var id in shapeDatas) {
					var shapeData = shapeDatas[id];
					var pallasName = shapeData["pallasName"];
					shapeData['activitiName'] = id;
					var shape = null;
					var aliasName = "";
					switch (pallasName) {
					case "startNoneEvent":
						aliasName = "widget.startnoneevent";
						break;
					case "userTask":
						aliasName = "widget.usertask";
						break;
					case "scriptTask":
						aliasName = "widget.scripttask";
						break;
					case "receiveTask":
						aliasName = "widget.receivetask";
						break;
					case "manualTask":
						aliasName = "widget.manualtask";
						break;
					case "mailTask":
						aliasName = "widget.mailtask";
						break;
					case "exclusiveGateway":
						aliasName = "widget.exclusivegateway";
						break;
					case "parallelGateway":
						aliasName = "widget.parallelgateway";
						break;
					case "inclusiveGateway":
						aliasName = "widget.inclusivegateway";
						break;
					case "eventGateway":
						aliasName = "widget.eventgateway";
						break;
					case "pool":
						aliasName = "widget.pool";
						break;
					case "lane":
						aliasName = "widget.lane";
						break;
					case "subProcess":
						aliasName = "widget.subprocess";
						break;
					case "eventSubProcess":
						aliasName = "widget.eventsubprocess";
						break;
					case "callActivity":
						aliasName = "widget.callactivity";
						break;
					case "startTimerEvent":
						aliasName = "widget.starttimerevent";
						break;
					case "startMessageEvent":
						aliasName = "widget.startmessageevent";
						break;
					case "startErrorEvent":
						aliasName = "widget.starterrorevent";
						break;
					case "boundaryErrorEvent":
						aliasName = "widget.boundaryerrorevent";
						break;
					case "boundaryTimerEvent":
						aliasName = "widget.boundarytimerevent";
						break;
					case "boundarySignalEvent":
						aliasName = "widget.boundarysignalevent";
						break;
					case "boundaryMessageEvent":
						aliasName = "widget.boundarymessageevent";
						break;
					case "catchTimerEvent":
						aliasName = "widget.catchtimerevent";
						break;
					case "catchSignalEvent":
						aliasName = "widget.catchsignalevent";
						break;
					case "catchMessageEvent":
						aliasName = "widget.catchmessageevent";
						break;
					case "throwNoneEvent":
						aliasName = "widget.thrownoneevent";
						break;
					case "throwSignalEvent":
						aliasName = "widget.throwsignalevent";
						break;
					case "endNoneEvent":
						aliasName = "widget.endnoneevent";
						break;
					case "endErrorEvent":
						aliasName = "widget.enderrorevent";
						break;
					case "sequenceFlow":
						aliasName = "widget.sequenceflow";
						break;
					case "association":
						aliasName = "widget.association";
						break;
					case "textAnnotation":
						aliasName = "widget.textannotation";
						break;
					default:
						break;
					}
					if (aliasName != "") {
						shape = Ext.create(aliasName, shapeData);
						shape.initCanvas();
						shape.setBaseAttribute();
						allComponents.shapes[shape.activitiName] = shape;

					}
				}
			}
		});
	}
});

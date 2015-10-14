(function(){
	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		width:"1200",
		tbar:[
				{
					text : "创建",
					handler : function() {
						var config = {
							width : 500,
							height : 300,
							bbar : [ {
								text : "确定",
								handler : function() {
									Ext.Ajax
											.request({
												url : '/pallas_activiti/workflow/model/create.sp',
												params : {
													key : "new",
													name : "new",
													description : "new"
												},
												success : function(
														response) {
													var text = response.responseText;
													// process server response here
													//window.open('/pallas_activiti/activiti-modeler/service/editor?id='+text);
													//var someValue=window.showModalDialog('/pallas_activiti/activiti-modeler/service/editor?id='+text,"","dialogWidth=500px;dialogHeight=500px;status=no;help=no;scrollbars=no");
													new Ext.window.Window(
															{
																width : document.body.clientWidth,
																height : document.body.clientHeight,
																layout : "fit",
																items : [ {
																	xtype : "panel",
																	html : '<iframe id="frame_main" src="'
																			+ '/pallas_activiti/activiti-modeler/service/editor?id='
																			+ text
																			+ '" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>'
																} ]
															}).show();
												}
											});
								}

							} ],
							items : [ {
								xtype : "form",
								items : [ {
									xtype : "textfield",
									fieldLabel : "id"
								}, {
									xtype : "textfield",
									fieldLabel : "key"
								}, {
									xtype : "textfield",
									fieldLabel : "name"
								}, {
									xtype : "textfield",
									fieldLabel : "description"
								}

								]
							} ]
						};
						var cfg = Ext.applyIf(config || {}, {
							stateful : false,
							isWindow : true,
							constrainHeader : true,
							modal : true,
							minimizable : true,
							maximizable : true
						});
						win = new Ext.window.Window(cfg);
						win.show();
					}
				},
				{
					text : "修改",
					handler : function() {
						var records = knowledgePanel
								.getSelectionModel().getSelection();

						if (records.length > 0) {
							var recordData = records[0].data;
							new Ext.window.Window(
									{
										width : document.body.clientWidth,
										height : document.body.clientHeight,
										modal : true,
										constrain : true,
										layout : "fit",
										items : [ {
											xtype : "panel",
											html : '<iframe id="frame_main" src="'
													+ '/pallas_activiti/activiti-modeler/service/editor?id='
													+ recordData.id
													+ '" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>'
										} ]
									}).show();
						}
					}
				},
				{
					text : "删除",
					handler : function() {

						var records = knowledgePanel
								.getSelectionModel().getSelection();

						if (records.length > 0) {
							var recordData = records[0].data;
							Ext.Ajax
									.request({
										url : '/pallas_activiti/workflow/model/delete/'
												+ recordData.id + '.sp',
										success : function(response) {
											var text = response.responseText;
											console.log(text);
										}
									});
						}
					}
				},
				{
					text : "导出",
					handler : function() {
						var records = knowledgePanel
								.getSelectionModel().getSelection();

						if (records.length > 0) {
							var recordData = records[0].data;
							window
									.open("/pallas_activiti/workflow/model/export/50.sp");
						}

					}
				},
				{
					text : "导入",
					handler : function() {
						//window.open("/pallas_activiti/workflow/model/export/50.sp?modelId="+recordData.id);
					}
				},
				{
					text : "部署",
					handler : function() {

						Ext.Ajax
								.request({
									url : '/pallas_activiti/workflow/model/deploy.sp',
									params : {},
									success : function(response) {
										var text = response.responseText;
										console.log(text);
										grid_store.loadData(eval(text));
									}
								});
					}
				},
				{
					text : "启动"
				},
				{
					text : "流程设置"
				},
				{
			text : "搜索",
			handler : function() {
				var me=grid;
				me.load("/pallasli-webapp-activiti/workflow/model/list.sp");
			}
		}],
		thead : [ {
			"dataIndex" : "id",
			"text" : "id"
		}, {
			"dataIndex" : "key",
			"text" : "流程key",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "流程名",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "createTime",
			"text" : "创建时间",
			"flex" : 1,
			"align" : "left",
			renderer : new Ext.util.Format.dateRenderer("Y-m-d h:i:s")
		}, {
			"dataIndex" : "lastUpdateTime",
			"text" : "上次修改时间",
			"flex" : 1,
			"align" : "left",
			renderer : new Ext.util.Format.dateRenderer("Y-m-d h:i:s")
		}, {
			"dataIndex" : "metaInfo",
			"text" : "流程描述",
			"flex" : 4,
			"align" : "left",
			renderer : function(v, a, b, c) {
				return eval("(" + v + ")").description;
			}
		} ],
		datastore : [ ]
	});
	new Pallasli.window.ViewPort({
		layout:"column",
		
		items : [grid]
	});

})();


(function(){
	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		width:"1200",
		tbar:[
				{
					text : "增加",
					handler : function() {
					}
				},
				{
					text : "修改",
					handler : function() {
					}
				},
				{
					text : "删除",
					handler : function() {
					}
				},
				{
					text : "设置用户",
					handler : function() {
						var panelId = Ext.id();
						var config = {
							layout : 'fit',
							width : 900,
							height : 500,
							bbar : [ {
								text : "确定",
								handler : function() {
								}

							} ],
							items : [ {
								type : "panel",
								id : panelId,
								layout : 'fit',
								loader : {
									url : '/pallas_activiti/jsppage.do?url=bpm/progress_membership_for_group&panelId='
											+ panelId,
									autoLoad : true,
									scripts : true
								}
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
		},{
			text : "搜索",
			handler : function() {
				var me=grid;
				me.load("/pallasli-webapp-activiti/workflow/group/listGroup.sp");
			}
		}],
		thead : [ {
			"dataIndex" : "id",
			"text" : "id"
		}, {
			"dataIndex" : "name",
			"text" : "岗位名称",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "revision",
			"text" : "版本号",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "type",
			"text" : "岗位类型",
			"flex" : 1,
			"align" : "left"
		} ],
		datastore : [ ]
	});
	new Pallasli.window.ViewPort({
		layout:"column",
		
		items : [grid]
	});

})();

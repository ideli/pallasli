(function(){
	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		width:"1200",
		tbar:[ {
			text : "设置用户组",
			handler : function() {

				var config={
						layout:'fit',
						width:900,
						height:500,
						bbar:[{text:"确定",
							handler:function(){}
							
						}],
						items:[{
							type:"panel",
							id: panelId,
							layout:'fit',
							loader:{
					            url: '/pallas_activiti/jsppage.do?url=bpm/progress_membership_for_user&panelId='+panelId,
					            autoLoad:true,
					            scripts:true
							}
						}]
				};
			
			}
		}, {
			text : "修改",
			handler : function() {
			}
		}, {
			text : "删除",
			handler : function() {
			}
		},{
			text:"查询",
			handler:function(){
				var me=grid;
				me.load("/pallasli-webapp-activiti/bpmtest/taskDoneList?userId=kermit");
			}
		} ],
		thead : [ {
			"dataIndex" : "id",
			"text" : "id"
		},{
			"dataIndex" : "firstName",
			"text" : "姓",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "lastName",
			"text" : "名",
			"flex" : 1,
			"align" : "left"
		},  {
			"dataIndex" : "email",
			"text" : "email",
			"flex" : 1,
			"align" : "left"
		},  {
			"dataIndex" : "password",
			"text" : "密码",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "pictureByteArrayRef",
			"text" : "照片id",
			"flex" : 1,
			"align" : "left",
			"renderer" : function(v){
				return v.id;
			}
		} ],
		datastore : [ ]
	});
	new Pallasli.window.ViewPort({
		layout:"column",
		
		items : [grid]
	});

})();


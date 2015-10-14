(function(){

var form=new Pallasli.panel.Form({
			items : [ {
				fieldLabel : "项目名称",
				xtype : "textfield"
			}, {
				fieldLabel : "项目代号",
				xtype : "textfield"
			}, {
				fieldLabel : "开始日期",
				xtype : "textfield"
			}, {
				fieldLabel : "结束日期",
				xtype : "textfield"
			}, {
				fieldLabel : "团队名称",
				xtype : "textfield"
			}, {
				fieldLabel : "关联产品",
				xtype : "textfield"
			}, {
				fieldLabel : "项目目标",
				xtype : "textfield"
			}, {
				fieldLabel : "项目描述",
				xtype : "textfield"
			}, {
				fieldLabel : "访问控制",
				xtype : "textfield"
			} ],
			fbar : [ {
				text : "保存"
			}, {
				text : "重填"
			} ]
		});
		
		new Pallasli.window.ViewPort({
			items : [ form ]
		});


})();

(function(){

var form=new Pallasli.panel.Form({
			items : [ {
				fieldLabel : "日期",
				xtype : "textfield"
			}, {
				fieldLabel : "类型",
				xtype : "textfield"
			}, {
				fieldLabel : "优先级",
				xtype : "textfield"
			}, {
				fieldLabel : "名称",
				xtype : "textfield"
			}, {
				fieldLabel : "描述",
				xtype : "textfield"
			}, {
				fieldLabel : "状态",
				xtype : "textfield"
			}, {
				fieldLabel : "起止时间",
				xtype : "textfield"
			}, {
				fieldLabel : "私人事务",
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

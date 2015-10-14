(function(){

var form=new Pallasli.panel.Form({
			items : [ {
				fieldLabel : "产品名称",
				xtype : "textfield"
			}, {
				fieldLabel : "产品代号",
				xtype : "textfield"
			}, {
				fieldLabel : "产品负责人",
				xtype : "textfield"
			}, {
				fieldLabel : "测试负责人",
				xtype : "textfield"
			}, {
				fieldLabel : "发布负责人",
				xtype : "textfield"
			}, {
				fieldLabel : "产品描述",
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

(function(){

var form=new Pallasli.panel.Form({
			items : [ {
				fieldLabel : "所属部门",
				xtype : "textfield"
			}, {
				fieldLabel : "用户名",
				xtype : "textfield"
			}, {
				fieldLabel : "姓名",
				xtype : "textfield"
			}, {
				fieldLabel : "邮箱",
				xtype : "textfield"
			}, {
				fieldLabel : "开户日期",
				xtype : "textfield"
			}, {
				fieldLabel : "访问次数",
				xtype : "textfield"
			}, {
				fieldLabel : "最后登录ip",
				xtype : "textfield"
			}, {
				fieldLabel : "最后登录时间",
				xtype : "textfield"
			}, {
				fieldLabel : "qq",
				xtype : "textfield"
			}, {
				fieldLabel : "手机",
				xtype : "textfield"
			}, {
				fieldLabel : "电话",
				xtype : "textfield"
			}, {
				fieldLabel : "通讯地址",
				xtype : "textfield"
			}, {
				fieldLabel : "邮政编码",
				xtype : "textfield"
			} ],
			fbar : [ {
				text : "修改"
			}, {
				text : "退出"
			} ]
		});
		
		new Pallasli.window.ViewPort({
			items : [ form ]
		});


})();
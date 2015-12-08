Ext.define('Pallas.extDesigner.waapp.framework.Titlebar', {
	extend : "Ext.panel.Panel",
	requires : [ 'Pallas.extDesigner.waapp.config.ApplicationInfo' ],
	alias : [ "waapp.titlebar" ],
	border : false,
	tpl : [ '<table class="mixky-titlebar" height="100%" width="100%">',
			'<tr valign=middle>', '<td width=100% class="mixky-appname">',
			'{title} — 开发设计工具', '</td>', '<td nowrap class="mixky-userinfo">',
			'{departmentname}　{username}', '</td>', '</tr>', '</table>' ],
	initComponent : function() {
		var me = this;
		var applicationinfo = Pallas.extDesigner.waapp.config.ApplicationInfo;
		me.data = {
			title : applicationinfo.title,
			userid : applicationinfo.userid,
			username : applicationinfo.username,
			departmentname : applicationinfo.departmentname
		};
		me.callParent();
	}
});
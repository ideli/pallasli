Mixky.awsoft.Titlebar = function(cfg){
	Mixky.awsoft.Titlebar.superclass.constructor.call(this, cfg);
}

Ext.extend(Mixky.awsoft.Titlebar, Ext.Panel, {
    margins : '0 0 0 0',
    cmargins :'0 0 0 0',
    border : false,
    bodyCssClass : 'mixky-administrator-title',
	data : {
		title : ApplicationInfo.title, 
		userid : ApplicationInfo.userid,
		username : ApplicationInfo.username,
		departmentname : ApplicationInfo.departmentname
	},
	tpl : [
		'<table class="mixky-titlebar" height="100%">',
			'<tr valign=middle>',
				//'<td width=100% class="mixky-appname">{title} — 管理工具</td>',
			    '<td width=100%>{title} — 应用管理</td>',
				'<td nowrap class="mixky-userinfo">{departmentname}　{username}</td>',
			'</tr>',
		'</table>'
	]
});
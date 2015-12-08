Mixky.wasoft.desktop.Titlebar = function(cfg){
	Mixky.wasoft.desktop.Titlebar.superclass.constructor.call(this, cfg);
};

Ext.extend(Mixky.wasoft.desktop.Titlebar, Ext.Panel, {
    margins : '0 0 0 0',
    cmargins :'0 0 0 0',
    border : false,
    bodyCssClass : 'mixky-portal-title',
	data : {
		title : ApplicationInfo.title, 
		userid : ApplicationInfo.userid,
		username : ApplicationInfo.username,
		departmentname : ApplicationInfo.departmentname
	},
	tpl : [
		'<table class="mixky-titlebar" height="100%">',
			'<tr valign=middle>',
				//'<td width=100% class="mixky-appname">{title}</td>',
			    '<td width=100%>{title}</td>',
				'<td nowrap class="mixky-userinfo">{departmentname}　{username}</td>',
			'</tr>',
		'</table>'
	]
});
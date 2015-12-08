// 模块配置项定义
Mixky.awsoft.lib.Class.registeModule({
	name : 'application',
	text : '应用系统',
	note : '',
	iconCls : 'icon-administrator-application',
	jsonable : true,
	copyable : true,
	deletable : true,
	subModules : ['flow'],
	editors : [
       'administrator/designobject/properties.do'
    ],
    properties : [{
    	name:'f_url', 
    	text:'应用路径', 
    	xeditor:'string', 
    	note:'应用系统访问路径，用于与门户通讯。'
    },{
    	name:'f_icon', 
    	text:'图标', 
    	xeditor:'string', 
    	note:'应用图标，指定应用的图标名称。'
    }]
});

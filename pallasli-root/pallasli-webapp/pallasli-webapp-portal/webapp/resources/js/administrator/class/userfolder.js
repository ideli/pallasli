// 模块数据表容器配置项定义
Mixky.awsoft.lib.Class.registeModule({
	isSingle : true,
	name : 'userfolder',
	text : '用户管理',
	note : '管理系统用户',
	iconCls : 'icon-administrator-userfolder',
	withoutSave:true,
	editors : [
	           'administrator/organization/user.ui.do?type=info',
	           //'administrator/organization/user.ui.do?type=scope',
	           'administrator/organization/user.ui.do?type=order'
	]
});

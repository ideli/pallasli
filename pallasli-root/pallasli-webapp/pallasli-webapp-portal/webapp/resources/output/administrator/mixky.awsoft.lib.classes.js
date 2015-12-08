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

// 模块容器配置项定义
Mixky.awsoft.lib.Class.registeModule({
	name : 'applicationfolder',
	text : '应用系统容器',
	note : '应用系统容器节点',
	iconCls : 'icon-administrator-applicationfolder',
	subModules : ['application']
});

// 模块数据表容器配置项定义
Mixky.awsoft.lib.Class.registeModule({
	name : 'applicationmenuauthfolder',
	text : '菜单权限',
	note : '管理系统菜单权限设置',
	iconCls : 'icon-administrator-applicationmenuauthfolder',
	editors : [
	    'administrator/authority/menuauth.list.do'
	]
});

// 模块数据表容器配置项定义
Mixky.awsoft.lib.Class.registeModule({
	isSingle : true,
	name : 'applicationroleauthfolder',
	text : '应用权限',
	note : '管理应用模块角色权限定义',
	iconCls : 'icon-administrator-applicationroleauthfolder',
	editors : [
	    'administrator/authority/moduleroleauth.list.do'
	]
});

// 模块数据表配置项定义
Mixky.awsoft.lib.Class.registeModule({
	name : 'dept',
	text : '部门',
	note : '系统定义部门',
	iconCls : 'icon-administrator-dept',
	properties : [{
    	name:'id', 
    	text:'ID', 
    	xeditor:'none', 
    	note:'部门ID，数据库中的唯一标识。'
    },{
    	name:'f_name', 
    	text:'部门全称', 
    	xeditor:'none', 
    	note:'系统自动维护。'
    },{
    	name:'f_caption', 
    	text:'部门名称', 
    	xeditor:'string',
    	xconfig : {
    		config : {
    			maxLength:50,
    			allowBlank:false
    		}
    	},
    	note:'部门名称。'
    },{
    	name:'f_type', 
    	text:'成员类型', 
    	xeditor:'selectkeymap',
    	xconfig:{
			datas:[[0, '普通成员'],[1,'部门主管'],[2,'分管领导']],
    		readOnly:true
    	},
    	note:'部门用户类型。'
    },{
    	name:'f_note', 
    	text:'备注', 
    	xeditor:'textbox',
    	note:'说明信息。'
    },{
    	name:'f_order', 
    	note:'部门排序、自动维护。'
    },{
    	name:'type', 
    	note:'类型、部门或用户。'
    },{
    	name:'f_user_id', 
    	note:'用户ID，部门成员用。'
    },{
    	name:'f_no', 
    	note:''
    }],
	propertyColumns : {
		'id':{width:100, renderer:function(value, p, record) {
			var type = record.get("type");
			return String.format("<div style='height:16px;padding-left:23px;background:transparent url(resources/icon/administrator/{0}.gif) no-repeat'> {1}</div>", type, value);
		}},
		'f_caption':{width:200},
		'f_note':{width:250}
	}
});

// 模块数据表容器配置项定义
Mixky.awsoft.lib.Class.registeModule({
	isSingle : true,
	name : 'deptfolder',
	text : '部门管理',
	note : '管理系统机构定义',
	iconCls : 'icon-administrator-deptfolder',
	editors : [
	           'administrator/organization/dept.ui.do'
	]
});

// 模块数据表配置项定义
Mixky.awsoft.lib.Class.registeModule({
	name : 'dictionary',
	text : '字典数据',
	note : '系统定义字典数据',
	iconCls : 'icon-administrator-dictionary',
	properties : [{
    	name:'id', 
    	text:'ID', 
    	xeditor:'none', 
    	note:'字典ID，数据库中的唯一标识。'
    },{
    	name:'f_name', 
    	text:'数据标识', 
    	xeditor:'string',
    	xconfig : {
    		config : {
    			maxLength:50,
    			allowBlank:false
    		}
    	},
    	note:'数据标识。'
    },{
    	name:'f_caption', 
    	text:'数据名称', 
    	xeditor:'string',
    	xconfig : {
    		config : {
    			maxLength:50,
    			allowBlank:false
    		}
    	},
    	note:'字典数据中文名称。'
    },{
    	name:'f_note', 
    	text:'备注', 
    	xeditor:'textbox',
    	note:'说明信息。'
    },{
    	name:'type', 
    	note:'类型、角色或用户。'
    },{
    	name:'f_order', 
    	note:'排序、自动维护。'
    }],
	propertyColumns : {
		'f_name':{width:100, renderer:function(value, p, record) {
			var type = record.get("type");
			return String.format("<div style='height:16px;padding-left:23px;background:transparent url(resources/icon/administrator/{0}.gif) no-repeat'> {1}</div>", type, value);
		}},
		'f_caption':{width:120},
		'f_note':{width:150}
	}
});

// 模块数据表容器配置项定义
Mixky.awsoft.lib.Class.registeModule({
	isSingle : true,
	name : 'dictionaryfolder',
	text : '字典管理',
	note : '管理系统字典数据定义',
	iconCls : 'icon-administrator-dictionaryfolder',
	editors : [
	    'administrator/dictionary/dictionary.ui.do'
	]
});

// 标签界面配置项定义
Mixky.awsoft.lib.Class.registeModule({
	name : 'flow',
	text : '流程',
	note : '',
	iconCls : 'icon-administrator-flow',
	jsonable : true,
	copyable : true,
	deletable : true,
	subModules : ['node'],
	properties : [{
    	name:'f_type', 
    	text:'类型', 
    	xeditor:'selectkeymap', 
    	xconfig:{
    		datas:[[0,'普通流程'],[1,'子流程']],
    		readOnly:true
    	},
    	note:'流程类型。'
    },{
    	name:'f_administrator', 
    	text:'管理员', 
    	xeditor:'organization', 
    	xconfig:{
			remoteRenderType:'renderUserExpression',
			valueSeparator:'',
    		selectType:'mix'
    	},
    	note:'设定流程管理员，可以管理流程的状态和数据。'
    },{
    	name:'f_reader', 
    	text:'流程读者', 
    	xeditor:'organization', 
    	xconfig:{
			remoteRenderType:'renderUserExpression',
			valueSeparator:'',
    		selectType:'mix'
    	},
    	note:'设定流程读者，可以浏览流程数据。'
    }],
	editors : [
	    'administrator/designobject/properties.do',
	    'administrator/workflow/workflow.do'
    ]
});

// 模块流程容器配置项定义
Mixky.awsoft.lib.Class.registeModule({
	name : 'flowfolder',
	text : '流程容器',
	note : '应用系统流程容器节点',
	iconCls : 'icon-administrator-flowfolder',
	subModules : ['flow']
});

// 标签界面配置项定义
Mixky.awsoft.lib.Class.registeModule({
	name : 'node',
	text : '节点',
	note : '',
	iconCls : 'icon-administrator-node',
	jsonable : true,
	copyable : true,
	deletable : true,
	subModules : ['route'],
	properties : [{
    	name:'f_type', 
    	text:'节点类型', 
    	xeditor:'selectkeymap',  
    	xconfig:{
    		datas:[[0,'开始节点'],[1,'普通节点'],[2,'归档节点'],[3,'子流程节点'],[4,'结束节点'],[5,'计算节点']],
    		readOnly:true
    	},
    	note:'节点类型，0：开始节点；1：普通节点；2：归档节点；3：子流程节点；4：结束节点；5：计算节点' 
    },{
    	name:'f_merge_route', 
    	text:'路由计算', 
    	xeditor:'selectkeymap', 
    	xconfig:{
    		datas:[[0,'交集'],[1,'并集']],
    		readOnly:true
    	},
    	note:'节点办理人与路由办理人计算关系，intersection：交集；outersection：并集。'
    },{
    	name:'f_process_type', 
    	text:'办理类型',  
    	xeditor:'selectkeymap',  
    	xconfig:{
    		datas:[[0,'单人办理'],[1,'多人顺序'],[2,'多人并行'],[3,'申请办理']],
    		readOnly:true
    	},
    	note:'节点办理类型，0：单人办理；1：多人顺序；2：多人并行；3：申请办理'
    },{
    	name:'f_process_user', 
    	text:'办理人', 
    	xeditor:'organization', 
    	xconfig:{
    		selectType:'mix',
    		remoteRenderType:'renderUserExpression'
    	},
    	note:'节点办理人。'
    },{
    	name:'f_allow_forward', 
    	text:'允许转办', 
    	xeditor:'boolean', 
    	note:'允许节点办理时的转办操作。'
    },{
    	name:'f_allow_abort', 
    	text:'允许撤销', 
    	xeditor:'boolean', 
    	note:'允许流程发起人撤销办理。'
    },{
    	name:'f_script', 
    	text:'计算脚本', 
    	xeditor:'textbox',
    	note:'运行在服务器的Java程序，传入参数docData,nodeLog,curUser,availableRoutes，使用说明见手册。'
    }],
	editors : [
       'administrator/designobject/properties.do'
    ]
});

// 模块数据表容器配置项定义
Mixky.awsoft.lib.Class.registeModule({
	isSingle : true,
	name : 'orgchart',
	text : '组织结构图',
	note : '组织结构图',
	iconCls : 'icon-administrator-orgchart',
	editors : [
	           'administrator/organization/org.chart.do'
	]
});

// 模块数据表配置项定义
Mixky.awsoft.lib.Class.registeModule({
	name : 'portalmenu',
	text : '门户菜单',
	note : '定义门户菜单',
	iconCls : 'icon-administrator-portalmenu',
	properties : [{
    	name:'id', 
    	text:'ID', 
    	xeditor:'none', 
    	note:'菜单ID，数据库中的唯一标识。'
    },{
    	name:'f_name', 
    	text:'菜单标识', 
    	xeditor:'string',
    	xconfig : {
    		config : {
    			maxLength:50,
    			allowBlank:false
    		}
    	},
    	note:'标识菜单的英文名称。'
    },{
    	name:'f_caption', 
    	text:'菜单名称', 
    	xeditor:'string',
    	xconfig : {
    		config : {
    			maxLength:50,
    			allowBlank:false
    		}
    	},
    	note:'菜单名称。'
    },{
    	name:'f_icon', 
    	text:'图标', 
    	xeditor:'string',
    	xconfig : {
    		config : {
    			maxLength:50
    		}
    	},
    	note:'菜单图标。'
    },{
    	name:'f_applicationkey', 
    	text:'应用标识', 
    	xeditor:'string',
    	note:'菜单对应的模块标识。'
    },{
    	name:'f_modulekey', 
    	text:'模块标识', 
    	xeditor:'string',
    	note:'菜单对应的模块标识。'
    },{
    	name:'f_handler', 
    	text:'菜单操作', 
    	xeditor:'textbox',
    	note:'菜单操作函数。'
    },{
    	name:'f_note', 
    	text:'备注', 
    	xeditor:'textbox',
    	note:'说明信息。'
    },{
    	name:'f_order', 
    	note:'菜单排序、自动维护。'
    }],
	propertyColumns : {
		'f_name':{width:100},
		'f_caption':{width:100},
		'f_applicationkey':{width:100},
		'f_modulekey':{width:100},
		'f_icon':{width:100},
		'f_handler':{width:150},
		'f_note':{width:150}
	}
});

// 模块容器配置项定义
Mixky.awsoft.lib.Class.registeModule({
	isSingle : true,
	name : 'portalmenufolder',
	text : '门户菜单',
	note : '管理门户应用菜单',
	iconCls : 'icon-administrator-portalmenufolder',
	editors : [
	           'administrator/desktop/portalmenu.ui.do'
	]
});

//=================================================================
//	�ļ���relation.js
//=================================================================
// 模块数据表配置项定义
Mixky.awsoft.lib.Class.registeModule({
	name : 'relation',
	text : '路由关系',
	note : '',
	iconCls : 'icon-administrator-relation',
	properties : [{
    	name:'f_type', 
    	text:'关系类型', 
    	xeditor:'selectkeymap',  
    	xconfig:{
    		datas:[[1,'流程启动者'],[2,'目标节点历史办理人'],[3,'同部门人员'],[4,'直接部门领导'],[5,'所有上级领导'],[6,'上一节点办理人'],[7,'所有已办理人员'],[8,'直接下属'],[9,'所有下属'],[10,'分管领导'],[11,'流程管理员'],[12,'流程读者'],[0,'自定义类型']],
    		readOnly:true
    	},
    	note:'路由关系类型。'
    },{
    	name:'f_source', 
    	text:'关系源', 
    	xeditor:'selectkeymap',  
    	xconfig:{
    		datas:[[0,'按当前办理人'],[1,'按流程启动者'],[2,'按上一主办人']],
    		readOnly:true
    	},
    	note:'关系计算相关类型，0：按流程启动者计算关系；1：按当前办理人计算关系；2：按上一办理人计算关系。'
    },{
    	name:'f_param', 
    	text:'解析参数', 
    	xeditor:'string', 
    	note:'定义路由关系解析参数，自定类型有效。'
    },{
    	name:'f_relate_type', 
    	text:'结果计算', 
    	xeditor:'selectkeymap', 
    	xconfig:{
    		datas:[[0,'交集'],[1,'并集']],
    		readOnly:true
    	},
    	note:'各计算关系之间的结果合并方式，intersection：交集；outersection：并集。'
    }],
	propertyColumns : {
		'f_key':{width:120},
		'f_source':{width:110, header:'关系源'},
		'f_type':{width:110, header:'关系类型'},
		'f_relate_type':{width:110, header:'结果计算'},
		'f_param':{width:120, header:'解析参数'},
		'f_note':{width:150}
	}
});

//=================================================================
//	�ļ���role.js
//=================================================================
// 模块数据表配置项定义
Mixky.awsoft.lib.Class.registeModule({
	name : 'role',
	text : '岗位',
	note : '系统定义岗位',
	iconCls : 'icon-administrator-role',
	properties : [{
    	name:'id', 
    	text:'ID', 
    	xeditor:'none', 
    	note:'岗位ID，数据库中的唯一标识。'
    },{
    	name:'f_name', 
    	text:'英文名称', 
    	xeditor:'string',
    	xconfig : {
    		config : {
    			maxLength:50,
    			allowBlank:false
    		}
    	},
    	note:'岗位英文名称。'
    },{
    	name:'f_caption', 
    	text:'岗位名称', 
    	xeditor:'string',
    	xconfig : {
    		config : {
    			maxLength:50,
    			allowBlank:false
    		}
    	},
    	note:'岗位中文名称。'
    },{
    	name:'f_note', 
    	text:'备注', 
    	xeditor:'textbox',
    	note:'说明信息。'
    },{
    	name:'f_order', 
    	note:'岗位排序、自动维护。'
    },{
    	name:'type', 
    	note:'类型、岗位或用户。'
    },{
    	name:'f_user_id', 
    	note:'用户ID，岗位成员用。'
    }],
	propertyColumns : {
		'id':{width:50, renderer:function(value, p, record) {
			var type = record.get("type");
			return String.format("<div style='height:16px;padding-left:23px;background:transparent url(resources/icon/administrator/{0}.gif) no-repeat'> {1}</div>", type, value);
		}},
		'f_name':{width:100},
		'f_caption':{width:120},
		'f_note':{width:150}
	}
});

// 模块数据表容器配置项定义
Mixky.awsoft.lib.Class.registeModule({
	isSingle : true,
	name : 'rolefolder',
	text : '岗位管理',
	note : '管理系统岗位定义',
	iconCls : 'icon-administrator-rolefolder',
	withoutSave:true,
	editors : [
	    'administrator/organization/role.ui.do'
	]
});

// 标签界面配置项定义
Mixky.awsoft.lib.Class.registeModule({
	name : 'route',
	text : '路由',
	note : '',
	iconCls : 'icon-administrator-route',
	jsonable : true,
	copyable : true,
	deletable : true,
	subModules : ['relation'],
	properties : [{
    	name:'f_type', 
    	text:'路由类型', 
    	xeditor:'selectkeymap',  
    	xconfig:{
    		datas:[[0,'普通路由'],[1,'条件路由']],
    		readOnly:true
    	},
    	note:'路由类型，0：普通路由；1：条件路由。'
    },{
    	name:'f_route_expression', 
    	text:'路由条件', 
    	xeditor:'string', 
    	note:'定义路由的判断条件'
    },{
    	name:'f_access_user', 
    	text:'访问者', 
    	xeditor:'string', 
    	note:'定义路由的访问用户。'
    },{
    	name:'f_allow_return', 
    	text:'允许退回', 
    	xeditor:'boolean', 
    	note:'允许该条路由的退回办理操作。'
    },{
    	name:'f_allow_takeback', 
    	text:'允许拿回', 
    	xeditor:'boolean', 
    	note:'允许该条路的上一办理人进行拿回操作。'
    },{
    	name:'f_asbutton', 
    	text:'路由按钮', 
    	xeditor:'boolean', 
    	note:'允许该路由作为按钮输出。'
    },{
    	name:'f_button_name', 
    	text:'路由按钮标题', 
    	xeditor:'string', 
    	note:'作为按钮输出时，指定按钮标题。'
    },{
    	name:'f_button_order', 
    	text:'路由按钮顺序', 
    	xeditor:'number', 
    	note:'路由按钮在文档当中的现实顺序，数字越小越靠前。'
    },{
    	name:'f_merge_type', 
    	text:'关系计算', 
    	xeditor:'selectkeymap', 
    	xconfig:{
    		datas:[[0,'交集'],[1,'并集']],
    		readOnly:true
    	},
    	note:'指定路由关系之间的计算方式，intersection：交集；outersection：并集。'
    }],
	editors : [
       'administrator/designobject/properties.do',
       'administrator/designobject/objectgrideditor.do?type=relation'
    ]
});

﻿// 模块数据表配置项定义
Mixky.awsoft.lib.Class.registeModule({
	name : 'user',
	text : '用户',
	note : '管理系统用户',
	iconCls : 'icon-administrator-user',
	orderable : true,
	extendsMenu : [{
		text : '清空密码',
		iconCls : 'icon-administrator-clear',
		handler : function(){
			var panel = this.items.get(0);
			alert(panel.title);
		}
	}],
	properties : [{
    	name:'id', 
    	text:'ID', 
    	xeditor:'none', 
    	sortable : true,
    	note:'用户ID，数据库中的唯一标识。'
    },{
    	name:'f_name', 
    	text:'登录名', 
    	xeditor:'string', 
    	sortable : true,
    	xconfig : {
    		config : {
    			maxLength:50
    		}
    	},
    	note:'用于用户登录的登录名。'
    },{
    	name:'f_caption', 
    	text:'姓名', 
    	xeditor:'string',
    	sortable : true,
    	xconfig : {
    		config : {
    			maxLength:50
    		}
    	},
    	note:'用户姓名。'
    },{
    	name:'f_dept_id', 
    	text:'所属部门id', 
    	xeditor:'none',
    	note:'默认部门id。'
    },{
    	name:'f_dept_name', 
    	text:'所属部门', 
    	xeditor:'none',
    	note:'默认部门名称。'
    },{
    	name:'f_role_id', 
    	text:'所属岗位id', 
    	xeditor:'none',
    	note:'默认岗位id。'
    },{
    	name:'f_role_name', 
    	text:'所属岗位', 
    	xeditor:'none',
    	note:'默认岗位名称。'
    },{
    	name:'f_gjdbm', 
    	text:'所属归集点编码', 
    	xeditor:'none',
    	note:'默认归集点编码。'
    },{
    	name:'f_gjdmc', 
    	text:'所属归集点', 
    	xeditor:'none',
    	note:'默认归集点。'
    },{
    	name:'f_yhbm', 
    	text:'所属银行编码', 
    	xeditor:'none',
    	note:'默认银行编码。'
    },{
    	name:'f_yhmc', 
    	text:'所属银行', 
    	xeditor:'none',
    	note:'默认银行。'
    },{
    	name:'f_cbwdbm', 
    	text:'所属网点编码', 
    	xeditor:'none',
    	note:'默认网点编码。'
    },{
    	name:'f_cbwdmc', 
    	text:'所属网点', 
    	xeditor:'none',
    	note:'默认网点。'
    },{
    	name:'f_type', 
    	text:'类型', 
    	xeditor:'none',
    	note:'指定用户类型。'
    },{
    	name:'f_state', 
    	text:'状态', 
    	xeditor:'none', 
    	note:'设置用户状态。'
    },{
    	name:'f_email', 
    	text:'E-mail', 
    	xeditor:'string',
    	xconfig : {
    		config : {
    			maxLength:200
    		}
    	},
    	note:'用户电子邮箱地址。'
    },{
    	name:'f_cellphone', 
    	text:'手机号码', 
    	xeditor:'string',
    	xconfig : {
    		config : {
    			maxLength:50
    		}
    	},
    	note:'用户手机号码，用于短信模块。'
    },{
    	name:'f_note', 
    	text:'备注', 
    	xeditor:'textbox',
    	note:'说明信息。'
    },{
    	name:'f_order', 
    	note:'排序。'
    },{
    	name:'f_depttype', 
    	note:'部门类型。'
    },{
    	name:'dndata', 
    	text:'CA证书', 
    	xeditor:'none',
    	note:'CA证书。'
    }],
	propertyColumns : {
		/*'id':{width:50, renderer:function(value, p, record) {
			return String.format("<div style='height:16px;padding-left:23px;background:transparent url(resources/administrator/icon/user.gif) no-repeat'> {0}</div>", value);
		}},*/
		'f_name':{width:80},
		'f_caption':{width:80},
		'f_dept_name':{width:80},
		'f_role_name':{width:150},
		'f_gjdmc':{width:150},
		'f_yhmc':{width:150},
		'f_cbwdmc':{width:150},
		'f_type':{width:60},
		'f_state':{width:50},
		'f_email':{width:100},
		'f_cellphone':{width:100},
		'f_note':{width:150}
	}
});

// 模块数据表配置项定义
Mixky.awsoft.lib.Class.registeModule({
	name : 'userbgxx',
	text : '用户变更日志',
	note : '管理用户变更信息',
	iconCls : 'icon-administrator-user',
	properties : [{
    	name:'id', 
    	text:'ID'
    },{
    	name:'bgid', 
    	text:'变更用户id'
    },{
    	name:'bgname', 
    	text:'变更用户名'
    },{
    	name:'bgsj', 
    	text:'变更时间'
    },{
    	name:'bgqxx', 
    	text:'变更前信息'
    },{
    	name:'bghxx', 
    	text:'变更后信息'
    },{
    	name:'czyid', 
    	text:'操作员id'
    },{
    	name:'czyname', 
    	text:'操作员'
    },{
    	name:'czyip', 
    	text:'操作员IP'
    }],
	propertyColumns : {
		/*'id':{width:50, renderer:function(value, p, record) {
			return String.format("<div style='height:16px;padding-left:23px;background:transparent url(resources/administrator/icon/user.gif) no-repeat'> {0}</div>", value);
		}},*/
		'bgname':{width:90},
		'bgsj':{width:100},
		'bgqxx':{width:350},
		'bghxx':{width:350},
		'czyname':{width:90},
		'czyip':{width:80}
	}
});

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

// 模块数据表配置项定义
Mixky.awsoft.lib.Class.registeModule({
	name : 'userscope',
	text : '用户数据范围',
	note : '管理系统用户数据范围',
	iconCls : 'icon-administrator-user',
	properties : [{
    	name:'id', 
    	text:'ID', 
    	xeditor:'none', 
    	sortable : true,
    	note:'用户ID，数据库中的唯一标识。'
    },{
    	name:'f_name', 
    	text:'登录名', 
    	xeditor:'string', 
    	sortable : true,
    	xconfig : {
    		config : {
    			maxLength:50
    		}
    	},
    	note:'用于用户登录的登录名。'
    },{
    	name:'f_caption', 
    	text:'姓名', 
    	xeditor:'string',
    	sortable : true,
    	note:'用户姓名。'
    },{
    	name:'f_gjd_codes', 
    	text:'f_gjd_codes'
    },{
    	name:'f_gjd_captions', 
    	text:'归集点'
    },{
    	name:'f_gjd_yh_codes', 
    	text:'f_gjd_yh_codes'
    },{
    	name:'f_gjd_yh_captions', 
    	text:'银行'
    },{
    	name:'f_gjd_yh_wd_codes', 
    	text:'f_gjd_yh_wd_codes'
    },{
    	name:'f_gjd_yh_wd_captions', 
    	text:'网点'
    },{
    	name:'f_gjd_yh_wd_dw_code', 
    	text:'f_gjd_yh_wd_dw_code'
    },{
    	name:'f_gjd_yh_wd_dw_caption', 
    	text:'单位'
    },{
    	name:'f_gjd_yh_wd_dw_bm_codes', 
    	text:'f_gjd_yh_wd_dw_bm_codes'
    },{
    	name:'f_gjd_yh_wd_dw_bm_captions', 
    	text:'部门'
    }],
	propertyColumns : {
		'id':{width:50, renderer:function(value, p, record) {
			return String.format("<div style='height:16px;padding-left:23px;background:transparent url(resources/administrator/icon/user.gif) no-repeat'> {0}</div>", value);
		}},
		'f_name':{width:80},
		'f_caption':{width:80},
		'f_gjd_captions':{width:150},
		'f_gjd_yh_captions':{width:150},
		'f_gjd_yh_wd_captions':{width:150}
	}
});

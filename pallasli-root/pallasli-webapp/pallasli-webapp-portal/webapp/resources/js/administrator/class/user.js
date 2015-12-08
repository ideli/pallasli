// 模块数据表配置项定义
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

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

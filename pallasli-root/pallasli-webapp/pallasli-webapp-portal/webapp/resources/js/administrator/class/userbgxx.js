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

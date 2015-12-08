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

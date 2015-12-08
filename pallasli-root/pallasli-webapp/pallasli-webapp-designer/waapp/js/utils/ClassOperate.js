Ext.define('Pallas.extDesigner.waapp.utils.ClassOperate', {
	requires : [ "Pallas.extDesigner.waapp.utils.Actions",
			"Pallas.extDesigner.waapp.lib.Context",
			'Pallas.extDesigner.waapp.utils.DesignOperate',
			"Pallas.extDesigner.waapp.utils.AllComponents" ],
	"statics" : {
		defaultProperties : [ {
			name : 'f_i_parent',
			text : 'parent',
			xeditor : 'none',
			note : '所属对象，对象的父对象。'
		}, {
			name : 'f_class',
			text : 'Class',
			xeditor : 'none',
			note : '对象类，描述对象的类型。'
		}, {
			name : 'f_key',
			text : 'Key',
			xeditor : 'none',
			note : '对象Key，唯一标识对象。'
		}, {
			name : 'f_name',
			text : '命名',
			xeditor : 'string',
			note : '对象命名，一般为对象的英文名称。'
		}, {
			name : 'f_caption',
			text : '名称',
			xeditor : 'string',
			note : '对象名称，一般为对象的中文名称。'
		}, {
			name : 'f_config',
			text : '配置',
			xeditor : 'jsonobject',
			note : '对象配置，用JSON格式配置对象的相关参数，不同的对象有不同的解析参数。'
		}, {
			name : 'f_note',
			text : '说明',
			xeditor : 'textbox',
			note : '对象说明，说明该对象的定义、用途等描述信息。'
		} ],
		modules : [],
		// 获得客户端对象定义
		getModule : function(name) {
			for (var i = 0; i < this.modules.length; i++) {
				if (this.modules[i].name == name) {
					return this.modules[i];
				}
			}
		},
		// 注册客户端对象定义
		registeModule : function(module) {
			if (module) {
				this.modules.push(module);
			}
		}
	}
});
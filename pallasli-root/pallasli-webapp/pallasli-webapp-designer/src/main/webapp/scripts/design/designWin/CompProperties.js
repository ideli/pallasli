/**
 * name,type,default
 */

var value_text_array = [ 'value', 'text' ]; // 定义一组Field

Ext.define('VALUE_TEXT_MODEL', { // 定义一个Model，用于Combo对应的Store
	extend : 'Ext.data.Model',
	fields : value_text_array,
	idProperty : 'value' // 定义ID，方便comboStore.getById(value)方法获取数据(Record)
});
var comboStore = Ext.create('Ext.data.Store', { // Combo对应的Store，实际应用中数据可以从后台获取
	model : 'VALUE_TEXT_MODEL',
	data : [ {
		value : '1',
		text : 'value1'
	}, {
		value : '2',
		text : 'value2'
	}, {
		value : '3',
		text : 'value3'
	} ]
});

Ext.define('Ext.ux.PropertyCombo', { // 当PropertyGrid中需要用到多个不同Store的Combo时，集中相同的配置参数重定义一个Combo可以大量减少重复代码

	extend : 'Ext.form.field.ComboBox',

	config : {
		valueField : 'value', // Combo选项的值对应的Field
		displayField : 'text', // Combo选项的显示文字对应的Field
		editable : false,
		queryMode : 'local',
		selectOnFocus : true
	},

	constructor : function(config) {
		this.initConfig(config);
		this.callParent([ config ]);
	}

});
Ext.define('Pallas.design.designWin.CompProperties', {
	statics : {
		source : {
			"panel" : {
				ctype : '普通面板',
				layout : 'fit',
				width : 200,
				height : 300,
				autoHeight : false,
				Combo1 : '2'
			},

			"text" : {
				"ctype" : '文本框',
				"allowblank" : true,
				"readOnly" : true
			}
		},
		propertyNames : { // 该参数可以更改属性显示的名称
			Combo1 : '测试'
		},

		customEditors : { // 用于自定义编辑组件，左边名称与source中的名称对应
			ctype : {
				xtype : "textfield",
				readOnly : true
			},
			Combo1 : Ext.create('Ext.ux.PropertyCombo', { // 自定义Combo，因为使用扩展的类，只要再填充自己的store属性就好
				store : comboStore
			})
		},
		customRenderers : { // 用于value(function的参数v)显示之前的格式化
			layout : function(v) { // 将密码文本显示的时候用圆点符号代替，否则在编辑的时候为圆点，显示的时候是明文！
				var password = '';
				for (var i = 0; i < v.length; i++) {
					password = password + '&#9679;';
				}
				return password;
			}
		},

		listeners : { // 当客户离开某组件的编辑框时该事件会响应，本例用用于简单向用户展示修改前后属性的值，超多事件参考API
			'beforepropertychange' : function(source, recordId, newValue,
					oldValue) {
				if (newValue != oldValue && (newValue || oldValue)) {
					// alert('old = ' + getPropertyTextByValue(recordId,
					// oldValue) + ' : new = ' +
					// getPropertyTextByValue(recordId, newValue));
				}
			}
		}
	}
});
Ext.define('Pallas.extDesigner.shape.AbstractShape', {
	extend : "Ext.panel.Panel",
	requires : [ 'Pallas.extDesigner.shape.iconbox.IconBox',
			'Pallas.extDesigner.shape.drag.DD' ],
	layout : 'fit',
	items : [ {
		border : false,
		xtype : "panel"
	} ],
	constrain : true,
	draggable : true,
	iconbox : null,
	context : null,
	padding : 7,
	border : false,
	attributeConfig : {
		width : {
			editor : Ext.create('Ext.form.field.Number', {
				selectOnFocus : true
			}),
			displayName : '宽度'
		},
		height : {
			editor : Ext.create('Ext.form.field.Number', {
				selectOnFocus : true
			}),
			displayName : '高度'
		},
		handler : {
			editor : Ext.create('Ext.form.field.TextArea', {
				selectOnFocus : true
			}),
			displayName : '单击事件'
		},
		name : {
			editor : Ext.create('Ext.form.field.Text', {
				selectOnFocus : true
			}),
			displayName : 'name'
		},
		text : {
			editor : Ext.create('Ext.form.field.Text', {
				selectOnFocus : true
			}),
			displayName : '名称'
		},
		fieldLabel : {
			editor : Ext.create('Ext.form.field.Text', {
				selectOnFocus : true
			}),
			displayName : '标签名称'
		},
		labelWidth : {
			editor : Ext.create('Ext.form.field.Number', {
				selectOnFocus : true
			}),
			displayName : '标签宽度'
		},
		emptyText : {
			editor : Ext.create('Ext.form.field.Number', {
				selectOnFocus : true
			}),
			displayName : '空文本'
		},
		allowBlank : {
			editor : Ext.create('Ext.form.field.Number', {
				selectOnFocus : true
			}),
			displayName : '允许为空'
		},
		blankText : {
			editor : Ext.create('Ext.form.field.Number', {
				selectOnFocus : true
			}),
			displayName : '字段为空验证'
		},
		maxLength : {
			editor : Ext.create('Ext.form.field.Number', {
				selectOnFocus : true
			}),
			displayName : '最大长度'
		},
		maxLengthText : {
			editor : Ext.create('Ext.form.field.Number', {
				selectOnFocus : true
			}),
			displayName : '最大长度验证'
		},
		minLength : {
			editor : Ext.create('Ext.form.field.Number', {
				selectOnFocus : true
			}),
			displayName : '最小长度'
		},
		minLengthText : {
			editor : Ext.create('Ext.form.field.Number', {
				selectOnFocus : true
			}),
			displayName : '最小长度验证'
		},
		regex : {
			editor : Ext.create('Ext.form.field.Number', {
				selectOnFocus : true
			}),
			displayName : '正则'
		},
		regexText : {
			editor : Ext.create('Ext.form.field.Number', {
				selectOnFocus : true
			}),
			displayName : '正则验证'
		},
		columnWidth : {
			editor : Ext.create('Ext.form.field.Number', {
				selectOnFocus : true
			}),
			displayName : '列布局比例'
		}
	},
	ghost : function(cls) {
		return this;
	},
	initDraggable : function() {
		this.dd = new Pallas.extDesigner.shape.drag.DD(this, true);
	},
	initEvent : function() {
		var me = this;
		me.el.dom.onclick = function(e) {
			Ext.getCmp('attributePanel').setSource(me.attributeData,
					me.attributeConfig);

		};
		me.on('resize', function() {
			var me = this;
			me.refreshShape();
		});
	},
	refreshShape : function() {
		var me = this;

		if (me.canvas) {
			me.iconbox.el.dom.removeChild(me.canvas);
		}
		me.iconbox = me.items.items[0];

		me.canvas = document.createElement("canvas");
		me.context = me.canvas.getContext("2d");

		me.iconbox.el.dom.appendChild(me.canvas);
		me.canvas.style.position = "absolute";
		// canvas.style["z-index"] = 99999;
		me.canvas.style.display = "block";
		me.canvas.style.border = "none";
		me.canvas.width = me.width - 14;
		me.canvas.height = me.height - 14;
		me.canvas.style.left = "0px";
		me.canvas.style.top = "0px";
		me.drawDragIcon();
	},
	initComponent : function() {
		var me = this;

		me.on("afterlayout", function() {
			me.refreshShape();
			me.initEvent();

		});
		me.callParent();
	}

});

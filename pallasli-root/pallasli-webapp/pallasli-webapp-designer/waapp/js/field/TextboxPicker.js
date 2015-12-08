Ext.define('Pallas.extDesigner.waapp.field.TextboxPicker', {
	alias : 'widget.textboxpicker',
	extend : 'Ext.panel.Panel',
	layout : 'fit',
	height : 300,
	setValue : function(v) {
		var me = this;
		me.pickerField.setValue(v);
		return me;
	},
	initComponent : function() {

		var me = this;
		me.hiddenValue = me.pickerField.getValue();
		console.log(me.hiddenValue);
		me.hiddenValue = me.hiddenValue.replace(/\\r/g, "\r").replace(/\\n/g,
				"\n");

		console.log(me.hiddenValue);
		// Ext.decode(me.hiddenValue)
		var valueEditor = Ext.create('widget.textarea', {
			region : 'center',
			anchor : '100%',
			value : me.hiddenValue
		});

		var ui = {
			xtype : "panel",
			layout : 'border',
			items : [ valueEditor ]
		};

		var buttons = [];
		buttons = [ '->', {
			text : '确定',
			iconCls : 'icon-common-confirm',
			handler : function() {
				var value = valueEditor.getValue();
				value = value.replace(/\r/g, '\\r').replace(/\n/g, '\\n');
				this.hiddenValue = value;
				this.setValue(this.hiddenValue);
				this.hide();
			},
			scope : this
		}, '-', {
			text : '取消',
			iconCls : 'icon-common-cancel',
			handler : function() {
				valueEditor.setValue(this.hiddenValue);
				this.hide();
			},
			scope : this
		} ];
		me.bbar = buttons;
		me.items = [ ui ];
		me.floating = true;
		me.callParent();

	}
});

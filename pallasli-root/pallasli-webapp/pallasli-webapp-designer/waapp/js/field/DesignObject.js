Ext.define('Pallas.extDesigner.waapp.field.DesignObject', {
	alias : 'widget.designobject',
	extend : 'Ext.form.field.Picker',
	requires : [ 'Pallas.extDesigner.waapp.field.DesignObjectPicker' ],
	matchFieldWidth : true,
	pickerAlign : 'tl-bl?',
	openCls : Ext.baseCSSPrefix + 'pickerfield-open',
	minPickWidth : 700,
	editable : false,
	alignPicker : function() {
		var me = this, picker = me.getPicker();

		if (me.isExpanded) {
			if (me.matchFieldWidth) {
				if (me.bodyEl.getWidth() > me.minPickWidth) {
					picker.setWidth(me.bodyEl.getWidth());
				} else {
					picker.setWidth(me.minPickWidth);
				}
			}
			if (picker.isFloating()) {
				me.doAlign();
			}
		}
	},

	createPicker : function() {
		var me = this;
		me.picker = Ext.create("widget.designobjectpicker", {
			pickerField : me
		});
		return me.picker;
	}

});

Ext.define('Pallas.common.window.Form', {
	mixins : {
		observable : 'Ext.util.Observable'
	},
	requires : [ 'Pallas.common.CompType' ],
	initValues : null,
	constructor : function(config) {
		var me = this;

		me.mixins.observable.constructor.call(this, config);

		if (Ext.isReady) {
			Ext.Function.defer(me.init, 10, me);
		} else {
			Ext.onReady(me.init, me);
		}
	},
	init : function() {
	},

	createWindow : function() {
		var me = this;
		var nid = Ext.id();
		me.form = Ext.create('Ext.panel.Panel', {
			id : nid,
			loader : {
				url : 'jsppage.do?url=design_all/engine/simpleForm',
				autoLoad : true,
				scripts : true,
				params : {
					nid : nid,
					initValues : me.initValues
				}
			}
		});
		var win = Ext.create(Pallas.common.CompType.window, {
			width : 800,
			layout : 'fit',
			modal : true,
			constrain : true,
			height : 540,
			id : nid,
			initValues : me.initValues,
			loader : {
				url : 'jsppage.do?url=design_all/engine/simpleForm',
				autoLoad : true,
				scripts : true,
				params : {
					nid : nid,
					initValues : me.initValues
				}
			}
		});
		return win;
	},

	statics : {}
});

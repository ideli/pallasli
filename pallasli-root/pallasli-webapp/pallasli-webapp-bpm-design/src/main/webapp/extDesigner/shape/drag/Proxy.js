Ext.define('Pallas.extDesigner.shape.drag.Proxy', {
	moveOnDrag : true,

	constructor : function(panel, config) {
		var me = this;
		me.panel = panel;
		me.id = me.panel.id + '-ddproxy';
		Ext.apply(me, config);
	},

	insertProxy : true,

	// private overrides
	setStatus : Ext.emptyFn,
	reset : Ext.emptyFn,
	update : Ext.emptyFn,
	stop : Ext.emptyFn,
	sync : Ext.emptyFn,

	getEl : function() {
		return this.ghost.el;
	},

	getGhost : function() {
		return this.ghost;
	},

	getProxy : function() {
		return this.proxy;
	},

	hide : function() {
		var me = this;

		if (me.ghost) {
			if (me.proxy) {
				me.proxy.remove();
				delete me.proxy;
			}

			// Unghost the Panel, do not move the Panel to where the ghost was
			me.panel.unghost(null, me.moveOnDrag);
			delete me.ghost;
		}
	},

	/**
	 * Shows the proxy
	 */
	show : function() {
		var me = this, panelSize;
		if (!me.ghost) {
			panelSize = me.panel.getSize();
			me.panel.el.setVisibilityMode(Ext.Element.DISPLAY);
			me.ghost = me.panel.ghost();
			if (me.insertProxy) {
				// bc Panels aren't absolute positioned we need to take up the
				// space
				// of where the panel previously was
				me.proxy = me.panel.el.insertSibling({
					cls : Ext.baseCSSPrefix + 'panel-dd-spacer'
				});
				me.proxy.setSize(panelSize);
			}
		}
	},

	// private
	repair : function(xy, callback, scope) {
		this.hide();
		Ext.callback(callback, scope || this);
	},

	moveProxy : function(parentNode, before) {
		if (this.proxy) {
			parentNode.insertBefore(this.proxy.dom, before);
		}
	}
});

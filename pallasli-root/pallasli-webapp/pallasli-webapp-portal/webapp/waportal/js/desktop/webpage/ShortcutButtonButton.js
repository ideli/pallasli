Ext.define('Pallas.portal.waapp.desktop.webpage.ShortcutButton', {
	extend : "Ext.button.Button",

	clickEvent : 'dblclick',
	template : new Ext.Template('<div class="ux-shortcut-btn ' + config.iconCls
			+ '">', '<div class="ux-shortcut-btn-img icon-sys-0shortcut'
			+ config.btntype + '48 ' + config.iconCls + '48"></div>',
			'<div class="ux-shortcut-btn-text">{0}</div>', '</div>'),
	buttonSelector : 'div:first',

	// private
	setButtonClass : function() {

	},
	setIconClass : function(cls) {
		if (this.el) {
			this.btnEl.replaceClass(this.iconCls, cls);
		}
		this.iconCls = cls;
		return this;
	},

	initButtonEl : function(btn, btnEl) {
		Mixky.wasoft.desktop.ShortcutButton.superclass.initButtonEl.apply(this,
				arguments);

		btn.removeClass("x-btn");

		if (this.iconCls) {
			if (!this.cls) {
				btn.removeClass(this.text ? 'x-btn-text-icon' : 'x-btn-icon');
			}
		}
	},

	autoWidth : function() {
		// do nothing
	},

	/**
	 * Sets this shortcut button's text
	 * 
	 * @param {String}
	 *            text The button text
	 */
	setText : function(text) {
		this.text = text;
		if (this.el) {
			this.el.child("div.ux-shortcut-btn-text").update(text);
		}
	}

});
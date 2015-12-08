Ext.define('Pallas.extDesigner.waapp.App', {
	extend : "Ext.container.Viewport",
	requires : [ "Pallas.extDesigner.waapp.framework.Framework",
			"Pallas.extDesigner.waapp.utils.AllComponents" ],
	isReady : false,
	border : false,
	layout : "fit",
	initComponent : function() {
		// 屏蔽右键
		Ext.getBody().on('contextmenu', function(e, el) {
			e.preventDefault();
		});
		// 初始化提示功能
		Ext.QuickTips.init();
		var me = this;
		// 创建桌面对象
		me.framework = Ext.create("waapp.framework");

		// 屏蔽退格键
		var map = new Ext.KeyMap(document, [ {
			key : Ext.EventObject.BACKSPACE,
			// stopEvent: true,
			fn : function(key, e) {
				var t = e.target.tagName;
				if (t != "INPUT" && t != "TEXTAREA") {
					e.stopEvent();
				}
			}
		} ]);
		me.isReady = true;
		me.fireEvent('ready', me);
		Ext.EventManager.on(window, 'beforeunload', me.onUnload, me);

		// 隐藏Loading界面
		setTimeout(function() {
			// Ext.get('mixky-loading').remove();
			// Ext.get('mixky-loading-mask').fadeOut({
			// remove : true
			// });
		}, 500);
		me.items = [ me.framework ];
		me.callParent();
		Pallas.extDesigner.waapp.utils.AllComponents.app = me;
	},
	onReady : function(fn, scope) {
		if (!this.isReady) {
			this.on('ready', fn, scope);
		} else {
			fn.call(scope, this);
		}
	},
	onUnload : function(e) {
		if (this.fireEvent('beforeunload', this) === false) {
			e.stopEvent();
		}
	},
	// 显示等待信息
	showWaitMessage : function(message, title) {
		var win = this.showNotification({
			title : '请稍候...' || title,
			html : message,
			iconCls : 'x-icon-wait'
		});
		return win;
	},
	// 显示信息
	showInfoMessage : function(message, title) {
		var win = this.showNotification({
			title : '信息提示' || title,
			html : message,
			iconCls : 'x-icon-information'
		});
		this.hideNotification(win);

	},
	// 显示警告信息
	showAlertMessage : function(message, title) {
		var win = this.showNotification({
			title : '警告信息' || title,
			html : message,
			iconCls : 'x-icon-alert'
		});
		this.hideNotification(win, 5000);
	},
	// 显示错误信息
	showErrorMessage : function(message, title) {
		var win = this.showNotification({
			title : '错误提示' || title,
			html : message,
			iconCls : 'x-icon-error'
		});
		this.hideNotification(win, 5000);
	},
	// private 显示提示窗口
	showNotification : function(config) {
		var win = new Mixky.lib.Notification(Ext.apply({
			animateTarget : this.framework.getAnimateTarget(),
			autoDestroy : true,
			hideDelay : 2000,
			html : '',
			iconCls : 'x-icon-waiting',
			title : ''
		}, config));
		win.show();
		return win;
	},
	// private 隐藏提示窗口
	hideNotification : function(win, delay) {
		if (win) {
			(function() {
				win.animHide();
			}).defer(delay || 2000);
		}
	}
});
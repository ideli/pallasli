Ext.define('Pallas.portal.waapp.lib.Message', {
	requires:["Pallas.portal.waapp.plugins.Notification",
	          "Pallas.portal.waapp.AppUtil"],
	statics : {
		// public 显示等待信息
		showWaitMessage : function(message, title) {
			var win = this.showNotification({
				title : '请稍候...' || title,
				html : message,
				iconCls : 'x-icon-wait'
			});
			return win;
		},
		// public 显示信息
		showInfoMessage : function(message, title) {
			var win = this.showNotification({
				title : '信息提示' || title,
				html : message,
				iconCls : 'x-icon-information'
			});
			this.hideNotification(win, 5000);

		},
		// public 显示警告信息
		showAlertMessage : function(message, title) {
			var win = this.showNotification({
				title : '警告信息' || title,
				html : message,
				iconCls : 'x-icon-alert'
			});
			this.hideNotification(win, 5000);
		},
		// public 显示错误信息
		showErrorMessage : function(message, title) {
			var win = this.showNotification({
				title : '错误提示' || title,
				html : message,
				iconCls : 'x-icon-error'
			});
			this.hideNotification(win, 5000);
		},
		// public 显示Direct方法错误信息
		showDirectActionFail : function(actionName, result, e) {
			Pallas.portal.waapp.lib.Message.showErrorMessage('【' + actionName + "】操作失败！");
		},
		showFormActionFail : function(f, a) {
			switch (a.failureType) {
			case Ext.form.Action.CLIENT_INVALID:
				Pallas.portal.waapp.lib.Message.showErrorMessage("提交数据非法！");
				break;
			case Ext.form.Action.CONNECT_FAILURE:
				Pallas.portal.waapp.lib.Message.showErrorMessage("服务器连接错误！");
				break;
			case Ext.form.Action.SERVER_INVALID:
				if (a.result.errors && a.result.errors.msg) {
					Pallas.portal.waapp.lib.Message.showErrorMessage('服务器数据处理错误！<br/>'
							+ a.result.errors.msg);
				} else {
					Pallas.portal.waapp.lib.Message.showErrorMessage("服务器数据处理错误！");
				}
				break;
			case Ext.form.Action.LOAD_FAILURE:
				Pallas.portal.waapp.lib.Message.showErrorMessage("表单数据装载失败！");
				break;
			}
		},
		// private 显示提示窗口
		showNotification : function(config) {
			var win = Ext.create("widget.notification",Ext.apply({
				animateTarget : Pallas.portal.waapp.AppUtil.MixkyApp.getAnimateTarget(),
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
					if (win.el.dom) {
						win.animHide();
					}
				}).defer(delay || 2000);
			}
		}
	}
});
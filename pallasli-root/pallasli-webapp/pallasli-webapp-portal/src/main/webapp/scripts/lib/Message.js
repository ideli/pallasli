Ext.define('Pallas.lib.Message', {
	statics : {
		// 消息提示框
		info : function() {
			var argLen = arguments.length;
			var title, msg;
			if (argLen == 0) {
				Pallas.lib.Message.error("消息提示框参数错误");
				return false;
			}
			if (argLen == 1) {
				title = "提示";
				msg = arguments[0];
			} else {
				title = arguments[0];
				msg = arguments[1];
			}

			Ext.Msg.show({
				title : title,
				msg : msg,
				width : 300,
				buttons : Ext.Msg.OK,
				fn : function() {
				},
				icon : Ext.window.MessageBox.INFO
			});
		},
		// 警告提示框
		alert : function() {
			var argLen = arguments.length;
			var title, msg;
			if (argLen == 0) {
				Pallas.lib.Message.error("警告提示框参数错误");
				return false;
			}
			if (argLen == 1) {
				title = "警告";
				msg = arguments[0];
			} else {
				title = arguments[0];
				msg = arguments[1];
			}

			Ext.Msg.show({
				title : title,
				msg : msg,
				width : 300,
				buttons : Ext.Msg.OK,
				fn : function() {
				},
				icon : Ext.window.MessageBox.ERROR
			});
		},
		// 错误提示框
		error : function() {

			var argLen = arguments.length;
			var title, msg;
			if (argLen == 0) {
				Pallas.lib.Message.error("错误提示框参数错误");
				return false;
			}
			if (argLen == 1) {
				title = "错误";
				msg = arguments[0];
			} else {
				title = arguments[0];
				msg = arguments[1];
			}

			Ext.Msg.show({
				title : title,
				msg : msg,
				width : 300,
				buttons : Ext.Msg.OK,
				fn : function() {
				},
				icon : Ext.window.MessageBox.ERROR
			});
		},
		// 提交确认
		confirm : function() {
			var argLen = arguments.length;
			var title, msg;
			if (argLen == 0) {
				Pallas.lib.Message.error("提交确认框参数错误");
				return false;
			}
			if (argLen == 1) {
				title = "提示";
				msg = arguments[0];
			} else {
				title = arguments[0];
				msg = arguments[1];
			}

			Ext.Msg.show({
				title : title,
				msg : msg,
				width : 300,
				buttons : Ext.Msg.OK,
				fn : function() {
				},
				icon : Ext.window.MessageBox.INFO
			});
		},
		// 信息确认框
		confirmWithLine : function() {
			var argLen = arguments.length;
			var title, msg, multiline;
			if (argLen == 0) {
				Pallas.lib.Message.error("信息确认框框参数错误");
				return false;
			}
			if (argLen == 1) {
				title = "确认";
				msg = arguments[0];
				multiline = false;
			} else if (argLen == 2) {
				if (arguments[1] === true || arguments[1] === false) {
					title = "确认";
					msg = arguments[0];
					multiline = arguments[1];
				} else {
					title = arguments[0];
					msg = arguments[1];
					multiline = false;
				}
			} else {
				title = arguments[0];
				msg = arguments[1];
				multiline = arguments[2];
			}

			Ext.Msg.show({
				title : title,
				msg : msg,
				width : 300,
				buttons : Ext.Msg.OKCANCEL,
				multiline : multiline,
				fn : function() {
				},
				icon : Ext.window.MessageBox.ERROR
			});
		},
		notify : function() {

			var argLen = arguments.length;
			var title, msg;
			if (argLen == 0) {
				Pallas.lib.Message.error("消息提示框参数错误");
				return false;
			}
			if (argLen == 1) {
				title = "提示";
				msg = arguments[0];
			} else {
				title = arguments[0];
				msg = arguments[1];
			}
			var notifyWindow ;
			
			notifyWindow= new Ext.window.Window({
				width : 250,
				height : 200,
				title : title,
				html : msg,
			});
			notifyWindow.showAt({
				x : document.documentElement.clientWidth - 250,
				y : document.documentElement.clientHeight
			});
			notifyWindow.animate({
				to : {
					x : document.documentElement.clientWidth - 250,
					y : document.documentElement.clientHeight - 200
				},
				duration :1500
			});
			notifyWindow.animate({
				to : {
					x : document.documentElement.clientWidth - 250,
					y : document.documentElement.clientHeight  - 200
				},
				duration :3000
			});
			notifyWindow.animate({
				to : {
					x : document.documentElement.clientWidth - 250,
					y : document.documentElement.clientHeight 
				},
				duration :1000
			});
		}

	}
});
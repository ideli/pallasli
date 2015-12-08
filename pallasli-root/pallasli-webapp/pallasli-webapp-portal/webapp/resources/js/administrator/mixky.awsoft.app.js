/*
 *	Mixap 应用架构
*/

Mixky.awsoft.App = function(cfg){
    Ext.apply(this, cfg);
    this.addEvents({
        'ready' : true,
        'beforeunload' : true
    });
    Ext.Direct.on('exception', function(e) {
    	this.showErrorMessage(e.message, "服务器交互错误");
	}, this);
    // 初始化应用
    Ext.onReady(this.initApp, this);
};

Ext.extend(Mixky.awsoft.App, Ext.util.Observable, {
	
    isReady : false,
    
    initApp : function(){
		// 创建桌面对象
		this.framework = new Mixky.awsoft.Framework(this);
		
		// 初始化提示功能
		Ext.QuickTips.init();
    	// 屏蔽退格键
		var map = new Ext.KeyMap(document, [{
  			key: Ext.EventObject.BACKSPACE,
  			//stopEvent: true,
  			fn: function(key, e){
  				var t = e.target.tagName;
  				if(t != "INPUT" && t != "TEXTAREA"){
  					e.stopEvent();
  				}
  			}
  		}]);
        this.isReady = true;
        // 屏蔽右键
		Ext.getBody().on('contextmenu', function(e, el) {
			e.preventDefault();
        });
		this.fireEvent('ready', this);
        Ext.EventManager.on(window, 'beforeunload', this.onUnload, this);

        // 隐藏Loading界面
    	setTimeout(function(){
            Ext.get('mixky-loading').remove();
            Ext.get('mixky-loading-mask').fadeOut({remove:true});
        }, 500);
    },
    // 显示等待信息
    showWaitMessage : function(message, title){
    	var win = this.showNotification({
    		title : '请稍候...' || title,
			html : message, 
			iconCls : 'x-icon-wait'
    	});
    	return win;
    },
    // 显示信息
    showInfoMessage : function(message, title){
    	var win = this.showNotification({
    		title : '信息提示' || title,
			html : message, 
			iconCls : 'x-icon-information'
    	});
    	this.hideNotification(win);
    	
    },
    // 显示警告信息
    showAlertMessage : function(message, title){
    	var win = this.showNotification({
    		title : '警告信息' || title,
			html : message, 
			iconCls : 'x-icon-alert'
    	});
    	this.hideNotification(win, 5000);
    },
    // 显示错误信息
    showErrorMessage : function(message, title){
    	var win = this.showNotification({
    		title : '错误提示' || title,
			html : message, 
			iconCls : 'x-icon-error'
    	});
    	this.hideNotification(win, 5000);
    },
    // private 显示提示窗口
	showNotification : function(config){
		var win = new Mixky.lib.Notification(Ext.apply({
			animateTarget : this.framework.getAnimateTarget() ,
			autoDestroy: true, 
			hideDelay: 2000, 
			html: '', 
			iconCls: 'x-icon-waiting', 
			title: ''
		}, config));
		win.show();
		return win;
	},
    // private 隐藏提示窗口
	hideNotification : function(win, delay){
		if(win){
			(function(){ win.animHide(); }).defer(delay || 2000);
		}
	},
    
    onReady : function(fn, scope){
        if(!this.isReady){
            this.on('ready', fn, scope);
        }else{
            fn.call(scope, this);
        }
    },

    onUnload : function(e){
        if(this.fireEvent('beforeunload', this) === false){
            e.stopEvent();
        }
    }
});
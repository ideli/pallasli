/*
 *	Mixap 应用架构
 */

Mixky.wasoft.App = function(cfg){
    Ext.apply(this, cfg);
    this.addEvents({
	'ready' : true,
	'beforeunload' : true
    });
    Ext.Direct.on('exception', function(e) {
	this.showErrorMessage(e.message, "服务器交互错误");
    }, this);
    // 初始化提示功能
    Ext.QuickTips.init();
    // 初始化应用
    Ext.onReady(this.initApp, this);
};

Ext.extend(Mixky.wasoft.App, Ext.util.Observable, {

    isReady : false,
    // 用户桌面配置
    userConfig : undefined,
    // 用户菜单
    userMenus : undefined,
    // private 初始化应用
    initApp : function(){
        
	mockConsole();

	// 创建桌面对象
	this.desktop = new Mixky.wasoft.Desktop(this);

	// 获得用户界面设置
	if(!this.userConfig){
	    this.getUserConfig();
	}else{
	    this.setUserConfig();
	}

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
	// 屏蔽右键
	Ext.getBody().on('contextmenu', function(e, el) {
	    e.preventDefault();
	});

	// 执行自动装载
	this.doAutoOpen.defer(500, this);

	this.fireEvent('ready', this);
	Ext.EventManager.on(window, 'beforeunload', this.onUnload, this);

	// 触发卸载事件
	Ext.EventManager.on(window, 'beforeunload', this.onUnload, this);

	// 完成应用装载
	this.isReady = true;

	// this.rqwarn();
	//this.kqqdadd();
    },
    // private 获得用户设置数据
    getUserConfig : function(){
	var app = this;
	DesktopDirect.getUserConfig('', function(result, e){
	    if(result && result.success){
		app.userConfig = result.userconfig;
		app.setUserConfig();
	    }else{
		app.showError('装载用户设置信息失败！');
	    }
	});
    },
    // private 设置用户界面配置
    setUserConfig : function(){
	this.desktop.setWallpaper(this.userConfig.wallpaper);
	this.desktop.setWallpaperPosition(this.userConfig.wallpaperposition);
	this.desktop.setTransparency(this.userConfig.transparency);
	this.desktop.setBackgroundColor(this.userConfig.backgroundcolor);
	this.desktop.setFrontColor(this.userConfig.frontcolor);
	this.desktop.setTheme(this.userConfig.theme);
	for(var i=0;i<this.userConfig.shortcuts.length;i++){
	    var o = Ext.apply({}, this.userConfig.shortcuts[i]);
	    this.desktop.addShortcut(o);
	}
	this.userConfig.subjects.sort(function(a,b){
	    var rowa = Ext.isDefined(a.row) ? a.row : 0;
	    var rowb = Ext.isDefined(b.row) ? b.row : 0;
	    var cola = Ext.isDefined(a.col) ? a.col : 0;
	    var colb = Ext.isDefined(b.col) ? b.col : 0;
	    return cola == colb ? rowa > rowb : cola > colb;
	});
	for(var i=0;i<this.userConfig.subjects.length;i++){
	    var o = Ext.apply({}, this.userConfig.subjects[i]);
	    this.desktop.addSubject(o);
	}
	for(var i=0;i<this.userConfig.quickstarts.length;i++){
	    var o = Ext.apply({}, this.userConfig.quickstarts[i]);
	    this.desktop.addQuickStart(o);
	}
    },
    // 设置窗口风格
    setTheme : function(theme){
	if(theme){
	    this.userConfig.theme = theme;
	    this.desktop.setTheme(this.userConfig.theme);
	}
    },
    // 设置背景色
    setBackgroundColor : function(hex){
	if(hex){
	    this.userConfig.backgroundcolor = hex;
	    this.desktop.setBackgroundColor(this.userConfig.backgroundcolor);
	}
    },
    // 设置前景色
    setFrontColor : function(hex){
	if(hex){
	    this.userConfig.frontcolor = hex;
	    this.desktop.setFrontColor(this.userConfig.frontcolor);
	}
    },
    // 设置任务栏透明度
    setTransparency : function(v){
	if(v >= 0 && v <= 100){
	    this.userConfig.transparency = v;
	    this.desktop.setTransparency(this.userConfig.transparency);
	}
    },
    // 设置墙纸
    setWallpaper : function(path){
	if(path){
	    this.userConfig.wallpaper = path;
	    this.desktop.setWallpaper(this.userConfig.wallpaper);
	}
    },
    // 设置墙纸位置
    setWallpaperPosition : function(pos){
	if(pos){
	    this.userConfig.wallpaperposition = pos;
	    this.desktop.setWallpaper(this.userConfig.wallpaperposition);
	}
    },
    // 显示桌面
    showDesktop : function(){
	this.desktop.showDesktop();
    },
    // 判断是否存在桌面按钮
    hasShortcut : function(btntype, appkey, key){
	for(var i=0;i<this.userConfig.shortcuts.length;i++){
	    var sc = this.userConfig.shortcuts[i];
	    if(sc.btntype == btntype && sc.applicationkey == appkey && sc.key == key){
		return true;
	    }
	}
    },
    // 添加桌面按钮
    addShortcut : function(o){
	this.userConfig.shortcuts.push(o);
	var c = {};
	c = Ext.apply(c, o);
	this.desktop.addShortcut(c);
    },
    // 删除桌面按钮
    removeShortcut : function(btntype, appkey, key){
	this.desktop.removeShortcut(btntype, appkey, key);
	// 从数组中删除
	for(var i=0;i<this.userConfig.shortcuts.length;i++){
	    var sc = this.userConfig.shortcuts[i];
	    if(sc.btntype == btntype && sc.applicationkey == appkey && sc.key == key){
		this.userConfig.shortcuts.splice(i, 1);
		break;
	    }
	}
    },
    // 判断是否存在快捷菜单
    hasQuickStart : function(btntype, appkey, key){
	for(var i=0;i<this.userConfig.quickstarts.length;i++){
	    var qs = this.userConfig.quickstarts[i];
	    if(qs.btntype == btntype && qs.applicationkey == appkey && qs.key == key){
		return true;
	    }
	}
    },
    // 添加桌面按钮
    addQuickStart : function(o){
	this.userConfig.quickstarts.push(o);
	var c = {};
	c = Ext.apply(c, o);
	this.desktop.addQuickStart(c);
    },
    // 删除桌面按钮
    removeQuickStart : function(btntype, appkey, key){
	this.desktop.removeQuickStart(btntype, appkey, key);
	// 从数组中删除
	for(var i=0;i<this.userConfig.quickstarts.length;i++){
	    var qs = this.userConfig.quickstarts[i];
	    if(qs.btntype == btntype && qs.applicationkey == appkey && qs.key == key){
		this.userConfig.quickstarts.splice(i, 1);
		break;
	    }
	}
    },
    // 判断是否存在桌面栏目
    hasSubject : function(appkey, key){
	for(var i=0;i<this.userConfig.subjects.length;i++){
	    var sj = this.userConfig.subjects[i];
	    if(sj.applicationkey == appkey && sj.key == key){
		return true;
	    }
	}
    },
    // 获得桌面栏目配置信息
    getSubject : function(appkey, key){
	for(var i=0;i<this.userConfig.subjects.length;i++){
	    var sj = this.userConfig.subjects[i];
	    if(sj.applicationkey == appkey && sj.key == key){
		return sj;
	    }
	}
    },
    // 添加桌面栏目
    addSubject : function(o){
	this.userConfig.subjects.push(o);
	var c = {};
	c = Ext.apply(c, o);
	this.desktop.addSubject(c);
    },
    // 删除桌面栏目
    removeSubject : function(appkey, key){
	this.desktop.removeSubject(appkey, key);
	// 从数组中删除
	for(var i=0;i<this.userConfig.subjects.length;i++){
	    var sj = this.userConfig.subjects[i];
	    if(sj.applicationkey == appkey && sj.key == key){
		this.userConfig.subjects.splice(i, 1);
		break;
	    }
	}
    },
    // public 显示等待信息
    showWaitMessage : function(message, title){
	var win = this.showNotification({
	    title : '请稍候...' || title,
	    html : message, 
	    iconCls : 'x-icon-wait'
	});
	return win;
    },
    // public 显示信息
    showInfoMessage : function(message, title){
	var win = this.showNotification({
	    title : '信息提示' || title,
	    html : message, 
	    iconCls : 'x-icon-information'
	});
	this.hideNotification(win, 5000);

    },
    // public 显示警告信息
    showAlertMessage : function(message, title){
	var win = this.showNotification({
	    title : '警告信息' || title,
	    html : message, 
	    iconCls : 'x-icon-alert'
	});
	this.hideNotification(win, 5000);
    },
    // public 显示错误信息
    showErrorMessage : function(message, title){
	var win = this.showNotification({
	    title : '错误提示' || title,
	    html : message, 
	    iconCls : 'x-icon-error'
	});
	this.hideNotification(win, 5000);
    },
    // public 显示Direct方法错误信息
    showDirectActionFail : function(actionName, result, e){
	MixkyApp.showErrorMessage('【' + actionName + "】操作失败！");
    },
    showFormActionFail : function(f, a){
	switch (a.failureType) {
	case Ext.form.Action.CLIENT_INVALID:
	    MixkyApp.showErrorMessage("提交数据非法！");
	    break;
	case Ext.form.Action.CONNECT_FAILURE:
	    MixkyApp.showErrorMessage("服务器连接错误！");
	    break;
	case Ext.form.Action.SERVER_INVALID:
	    if (a.result.errors && a.result.errors.msg) {
		MixkyApp.showErrorMessage('服务器数据处理错误！<br/>' + a.result.errors.msg);
	    } else {
		MixkyApp.showErrorMessage("服务器数据处理错误！");
	    }
	    break;
	case Ext.form.Action.LOAD_FAILURE:
	    MixkyApp.showErrorMessage("表单数据装载失败！");
	    break;
	}
    },
    // private 显示提示窗口
    showNotification : function(config){
	var win = new Mixky.lib.Notification(Ext.apply({
	    animateTarget : this.desktop.getAnimateTarget() ,
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
	    (function(){
		if(win.el.dom){
		    win.animHide();
		}
	    }).defer(delay || 2000);
	}
    },
    // private 装载完毕事件
    onReady : function(readyFn, scope){
	if(!this.isReady){
	    this.on('ready', readyFn, scope);
	}else{
	    readyFn.call(scope, this);
	}
    },
    // private 卸载时间
    onUnload : function(e){
	if(this.fireEvent('beforeunload', this) === false){
	    e.stopEvent();
	}
    },
    // 打开后自动装载文档
    doAutoOpen : function(){
	// 隐藏屏蔽界面
	Ext.get('mixky-loading').remove();
	Ext.get('mixky-loading-mask').fadeOut({remove:true});
	// 自动装载模块、视图、文档
	var isOpenDocument = false;
	if(Ext.isDefined(this.openParams)){
	    if(Ext.isDefined(this.openParams.modulekey)){
		var module = this.desktop.openModule(this.openParams.appkey, this.openParams.modulekey);
		if(this.openParams.viewkey && Ext.isDefined(module)){
		    module.openView(this.openParams.viewkey);
		}
	    }
	    if(Ext.isDefined(this.openParams.documentkey) && Ext.isDefined(this.openParams.documentid)){
		isOpenDocument = true;
		Mixky.wasoft.lib.openFavorite(this.openParams.appkey, this.openParams.documentkey, this.openParams.documentid);
	    }
	}
    },
    // 添加桌面按钮
    kqqdadd : function(){
	Ext.MessageBox.show({title:'考勤提示',msg:'今天是您第一次登录系统，是否签到？',modal:true,buttons:Ext.Msg.YESNO,
	    icon:Ext.Msg.QUESTION,width:300,closable:false,fn:function(btn){
		if(btn == 'yes'){
		    KqglDirect.kqgl_qd(function(result, e){
			if (result && result.success) {
			    Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
				icon:Ext.Msg.INFO,width:300,closable:false});
			}
			else{
			    Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
				icon:Ext.Msg.ERROR,width:300,closable:false});
			}
		    });
		}
	    }}); 
    },

    rqwarn : function(){
	var ywrq = (ApplicationInfo.ywrq).split('：')[1];
	var xtrq = new Date().format('Y年m月d日');
	if(ywrq!=xtrq){
	    Ext.MessageBox.show({title:'警告',msg:'当前系统日期与业务日期不一致！',modal:true,buttons:Ext.Msg.OK,
		icon:Ext.Msg.WARNING,width:280,closable:false});
	}
    }
});

function mockConsole(){
    if(!window.console){
	window.console={
		"log":function(){}
	};
    }
}


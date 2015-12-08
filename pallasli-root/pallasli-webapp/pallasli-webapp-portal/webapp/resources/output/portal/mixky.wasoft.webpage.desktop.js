/**
 * ActiveX Util
 * v1.0
 */

if (EimUtil == null) var EimUtil = {};
EimUtil.ActiveName = 'EIMObj.EIMCOMOBJ';
EimUtil.engine = null;

/*
 * 验证Eim是否安装了。
 * Eim安装后会向系统注册这个对象，可以根据此对象能否实例化来判读客户端是否安装。
 */
EimUtil.isInstall = function (){
	if(EimUtil.engine == null){
		try{
			EimUtil.engine = new ActiveXObject(EimUtil.ActiveName);
		}catch(e){
			//alert(e);
		}
	}
	return EimUtil.engine != null;
};

/*
 * 启动Eim客户端
 */
EimUtil.RunEim = function (userName,password){
	if(EimUtil.isInstall()){
		EimUtil.engine.RunEim(userName, password);
	}
};

/*
 * 获取人员在线状态
 * 返回值：-1 错误;  0 离线;  1 离开;  2 隐身;  3 在线。
 */
EimUtil.getStatus = function (loginName){
	if(loginName != null && EimUtil.isInstall()){
		return EimUtil.engine.getStatus(loginName);
	}
	return -1;
};

/*
 * 打开聊天对话窗口
 */
EimUtil.OpenChatDial = function (loginName){
	if(loginName != null && EimUtil.isInstall()){
		EimUtil.engine.OpenChatDial(loginName, '');
	}
};

/*
 * 在线感知功能
 * 注：本功能只是测试用，实际情况可根据本代码来修改
 */
EimUtil.presenceAwareness = function (loginName){
	//新建一个线程来执行，防止执行时间太长造成页面假死
	setTimeout('EimUtil.agent(\'' + loginName + '\')', 1);
};

/*
 * 在线感知功能代理方法
 */
EimUtil.agent = function (loginName){
	//var div = document.getElementById('EimPresenceAwareness');//获取页面的div
	var div = document.createElement("div");
	if(div == null || loginName == null) return;
	
	div.style.fontSize = '12px';
	
	while(div.hasChildNodes()){//移除div内所有子内容
		div.removeChild(div.firstChild);
	}
	
	if(EimUtil.isInstall()){//安装了客户端
		var image = document.createElement('img');
		image.src = 'images/empty.gif';
		image.style.backgroundRepeat = 'no-repeat';
		image.style.verticalAlign = 'middle';
		
		var _click = document.createElement('a');
		_click.style.fontSize = '12px';
		_click.href = 'javascript:EimUtil.OpenChatDial(\'' + loginName + '\')';
		_click.title = '点击给我留言';
		
		var state = EimUtil.getStatus(loginName);
		switch(state){//-1  错误，0 离线，1 离开，2 隐身， 3 在线
			case 0:
				image.style.backgroundImage = 'url("images/offline.gif")';
				_click.appendChild(document.createTextNode('(离线)'));
				break;
			case 1:
				image.style.backgroundImage = 'url("images/busyline.gif")';
				_click.appendChild(document.createTextNode('(繁忙)'));
				break;
			case 2:
				image.style.backgroundImage = 'url("images/afieldline.gif")';
				_click.appendChild(document.createTextNode('(隐身)'));
				break;
			case 3:
				image.style.backgroundImage = 'url("images/online.gif")';
				_click.appendChild(document.createTextNode('(在线)'));
				
				break;
			case -1:
			default:
				image.style.backgroundImage = 'url("images/error.gif")';
				_click.appendChild(document.createTextNode('(客户端未启动)'));
				_click.title = '点击启动客户端';
				_click.href = 'javascript:EimUtil.RunEim()';
		}
		div.appendChild(image);
		div.appendChild(_click);
		
		if(state == -1){
			var flush = document.createElement('a');
			flush.style.marginLeft = '10px';
			flush.href = 'javascript:EimUtil.presenceAwareness(\'' + loginName + '\')';
			//flush.appendChild(document.createTextNode('[刷新]'));
			div.appendChild(flush);
		}
		
	}else{//没有安装客户端
		var label = document.createElement('label');
		label.style.fontSize = '12px';
		label.style.color = 'red';
		label.appendChild(document.createTextNode('本地客户端未安装的'));
		
		var download = document.createElement('a');
		download.style.marginLeft = '10px';
		download.href = 'javascript:alert("这里是下载地址")';
		download.appendChild(document.createTextNode('[下载客户端]'));
		
		var flush = document.createElement('a');
		flush.style.marginLeft = '10px';
		flush.href = 'javascript:EimUtil.presenceAwareness(\'' + loginName + '\')';
		flush.appendChild(document.createTextNode('[刷新]'));
		
		div.appendChild(label);
		div.appendChild(download);
		div.appendChild(flush);
	}
	return div;
};

/*
 * 退出客户端
 */
EimUtil.ExitEim = function (){
	if(EimUtil.isInstall()){
		EimUtil.engine.ExitEim();
	}
};

/*
 * 隐藏客户端
 */
EimUtil.HideEim = function (){
	if(EimUtil.isInstall()){
 	  EimUtil.engine.HideEim();
	}
};

/*
 * 显示客户端
 */
EimUtil.ShowEim = function (){
	if(EimUtil.isInstall()){
	    alert('123456');
		EimUtil.engine.ShowEim();
	}
};



//=================================================================
//	�ļ���mixky.wasoft.desktop.js
//=================================================================

Mixky.wasoft.Desktop = function(app, config){

	Mixky.wasoft.Desktop.superclass.constructor.call(this, app, config);
    // 应用程序标题区域
    /*this.titlebar = new Mixky.wasoft.desktop.Titlebar({
    	region : 'north',
        split : false,
        height : 1,
        minSize : 39,
        maxSize : 39,
        hideCollapseTool : true,
        collapsible: true,
        collapseMode:'mini',
         collapsed:false
    });*/
	this.cwxx = new Ext.form.DisplayField({
       	value:' '
	});
	this.ztxx = new Ext.form.DisplayField({
       	value:' '
	});
	this.cwrq = new Ext.form.DisplayField({
       	value:' '
	});
	this.ywsj = new Ext.form.DisplayField({
       	//value:ApplicationInfo.ywrq
		value : ' '
	});
	this.dksj = new Ext.form.DisplayField({
       	//value:ApplicationInfo.dkrq
		value : ' '
	});	
	
	var xsmc = '部门：';
	if(ApplicationInfo.usertype==4){
		xsmc = '单位：';
		this.dksj.setVisible(false);
	}
	
	
	this.titlebar = new Ext.Panel({
    	region : 'south',
    	split : false,
    	//hidden:true,
        height : 26,
        minSize : 0,
        maxSize : 0,
        hideCollapseTool : true,
        collapsible: true,
        collapseMode:'mini',
        collapsed:false,
        bbar:['系统日期：',new Date().format('Y年m月d日'),'-',this.ywsj,'-',this.dksj,'-',this.cwrq,'->','-',xsmc + ApplicationInfo.departmentname,'-','操作员：'+ApplicationInfo.username,'-','账号:'+ApplicationInfo.loginname,'-',this.cwxx,'-',this.ztxx]
    });
    // 应用程序操作条
    this.toolbar = new Mixky.wasoft.desktop.Toolbar();
    var toolbar = this.toolbar;
	// 桌面门户
	this.portalPanel = new Mixky.wasoft.desktop.Portal();

	// 内容
	this.workspace = new Ext.TabPanel({
	    enableTabScroll : true,
	    bodyBorder : false,
	    hideBorders : true,
	    border : false,
		activeTab : 0,
	    defaults : {
			border : false,
			autoScroll :true,
		    bodyBorder : false
		},
		items : this.portalPanel,
	    plugins : new Ext.ux.TabCloseMenu()
	});
	this.workspace.on('tabchange', function(tabpanel, panel){
		var modulepanel = panel.items.get(0);
		Mixky.wasoft.lib.Context.activeApplicationKey = modulepanel.applicationkey;
		Mixky.wasoft.lib.Context.activeModuleKey = modulepanel.modulekey;
	});
	// 桌面结构
    var view = new Ext.Viewport( {
		layout :'border',
		items : [ this.titlebar, new Ext.Panel({
			region : 'center',
			border : false,
			layout : 'fit',
			tbar : toolbar,
			items : this.workspace
		})]
	});

	this.view = view;
    // 文档窗口组管理
	var windows = new Ext.WindowGroup();
	
    this.getManager = function(){
        return windows;
    };
    this.getWindow = function(id){
        return windows.get(id);
    };
    
    function minimizeWin(win){
        win.minimized = true;
        win.hide();
    }
    function removeWin(win){
    	toolbar.removeWindow(win);
    }
	this.appendWindow = function(win){

        win.render(Ext.getBody());

        toolbar.appendWindow(win);
        
        win.animateTarget = toolbar.winsButton.getEl();
        
        win.on({
        	'minimize': {
        		fn: minimizeWin
        	},
        	'close': {
        		fn: removeWin
        	}
        });
        
        return win;
	};

    this.createWindow = function(config, cls){
    	var win = new (cls||Ext.Window)(
            Ext.applyIf(config||{}, {
                manager: windows,
                minimizable: true,
                maximizable: true
            })
        );
    	return this.appendWindow(win);
    };

	// 处理右键屏蔽
	Ext.getBody().on('contextmenu', function(e, el) {
		e.preventDefault();
    }); 
	atwasoft.common.lib.getYwrq();
	atwasoft.common.lib.getDkrq();
	//this.layout();
}; 

Ext.extend(Mixky.wasoft.Desktop, Mixky.wasoft.IDesktop, {
	init : function(){},	
	hideAllWindow : function(){
    	this.getManager().each(function(win){
    		win.minimize();
    	});
	},
	getCurrentModule : function(){
		var panel = this.workspace.getActiveTab();
		if(panel != this.portalPanel){
			return this.workspace.getActiveTab().items.get(0);
		}
	},
    // extend method
	showDesktop : function(){
		this.hideAllWindow();
		this.workspace.activate(this.portalPanel);
	},
    // extend method
    setTheme : function(theme){
		Ext.util.CSS.swapStyleSheet('theme', theme);
    },
    // extend method
	setBackgroundColor : function(hex){
    	this.portalPanel.body.setStyle('background-color', hex);
	},
    // extend method
	setFrontColor : function(hex){
		Ext.util.CSS.updateRule('.ux-shortcut-btn-text', 'color', hex);
	},
    // extend method
	setWallpaper : function(path){
		var notifyWin = MixkyApp.showWaitMessage("正在装载墙纸...");
		var wp = new Image();
		wp.src = path;
		var task = new Ext.util.DelayedTask(verify, this);
		task.delay(200);
		var portalEl = this.portalPanel.body;
		function verify(){
			if(wp.complete){
				task.cancel();
				
				notifyWin.setIconClass('x-icon-done');
				notifyWin.setTitle('装载完成');
				notifyWin.setMessage('已完成墙纸装载.');
				MixkyApp.hideNotification(notifyWin);
				
				portalEl.setStyle('background-image','url(' + wp.src + ')');
			}else{
				task.delay(200);
			}
		}
	},
    // extend method
	setWallpaperPosition : function(pos){
		if(pos === "center"){
			var b = this.portalPanel.body;
			b.removeClass('wallpaper-tile');
			b.addClass('wallpaper-center');
		}else if(pos === "tile"){
			var b = this.portalPanel.body;
			b.removeClass('wallpaper-center');
			b.addClass('wallpaper-tile');
		}
	},
    // extend method
	addShortcut : function(o){
		this.portalPanel.addShortcut(o);
	},
    // extend method
	removeShortcut : function(btntype, appkey, key){
		this.portalPanel.removeShortcut(btntype, appkey, key);
	},
    // extend method
	addQuickStart : function(o){
		o.handler = function(b, e){
			Mixky.wasoft.lib.actions.openShortcut(this.btntype, this.applicationkey, this.key, e);
		};
		this.toolbar.quickButton.appendButton(o);
	},
    // extend method
	removeQuickStart : function(btntype, appkey, key){
		this.toolbar.quickButton.removeButton(btntype, appkey, key);
	},
    // extend method
	addSubject : function(o){
		return this.portalPanel.addSubject(o);
	},
    // extend method
	removeSubject : function(appkey, key){
		return this.portalPanel.removeSubject(appkey, key);
	},
	getModule : function(appkey, modulekey){
		/*
		var win = this.getWindow('m-' + modulekey);
		if(Ext.isDefined(win)){
			return win.items.get(0);
		}
		*/
		var id = 'm-' + appkey + '.' + modulekey;
		return this.workspace.get(id);
		
	},
	closeModule : function(appkey, modulekey){
		var module = this.getModule(appkey, modulekey);
		if(module){
			this.workspace.remove(module);
		}
	},
	// extend method
	openModule : function(appkey, modulekey){
		//var workspace = this.workspace;
		var id = 'm-' + appkey + '.' + modulekey;
		var panel = this.getModule(appkey, modulekey);
		if(!panel){
			var module = Mixky.wasoft.lib.cache.getAppModule(appkey, modulekey);
			if(!module){
				alert('未定义模块 ' + appkey + '.' + modulekey);
				return;
			}
			var modulepanel = this.getModulePanel(appkey, modulekey);
			panel = this.workspace.add({
				id : id,
				title : module.title,
				layout : 'fit',
				border : false,
				closable : true,
				iconCls : module.icon,
				items : modulepanel
			});
		}
		this.workspace.activate(panel);
		return panel.items.get(0);
	},
	getAppDocument : function(appkey, documentkey, id){
		// var panelid = 'd-' + appkey + '.' + document.key + '-' + id;
		// var win = this.getWindow(panelid);
		var win = this.getDocumentWindow(appkey, documentkey, id);
		if(Ext.isDefined(win)){
			return win.items.get(0);
		}
	},
	// private method
	getDocumentWindow : function(appkey, documentkey, id){
		var docWindows = this.getManager().getBy(function(win){
			if(win.isDocumentWindow){
				var p = win.items.get(0);
				if(p.applicationkey == appkey && p.documentkey == documentkey && p.documentid == id){
					return true;
				}
			}
			return false;
		});
		if(docWindows.length > 0){
			return docWindows[0];
		}
	},
	// private method
	getDocumentWindowWithoutID : function(appkey, documentkey){
		var docWindows = this.getManager().getBy(function(win){
			if(win.isDocumentWindow){
				var p = win.items.get(0);
				if(p.applicationkey == appkey && p.documentkey == documentkey){
					return true;
				}
			}
			return false;
		});
		if(docWindows.length > 0){
			return docWindows[0];
		}
	},
	// extend method
	_openDocument : function(appkey, document, id, params, cfg){
		// var panelid = 'd-' + appkey + '.' + document.key + '-' + id;
		// var win = this.getWindow(panelid);
		console.log(appkey, document.key, id);
		var win = this.getDocumentWindow(appkey, document.key, id);
		
		if(!Ext.isDefined(win)){
		
			var win2 = this.getDocumentWindowWithoutID(appkey, document.key);
			if(Ext.isDefined(win2)){
				win2.close();
			}
			
			var panel = this.getDocumentPanel(appkey, document.key, id, params, cfg);
			win = this.createWindow(Ext.apply({
				// id : panelid,
				isDocumentWindow : true,
				title : document.title,
				iconCls : document.icon,
				layout : 'fit',
				border : false,
				maximizable : false,
				minimizable : true,
				resizable : false,
				constrain : true,
				width : 500,
				height : 500,
				modal :true,
				items : panel
			}, document.config));
		}
		win.show();
		return win.items.get(0);
	},
	_closeDocument : function(appkey, documentkey, id){
		// var panelid = 'd-' + appkey + '.' + documentkey + '-' + id;
		// var win = this.getWindow(panelid);
		var win = this.getDocumentWindow(appkey, documentkey, id);
		if(Ext.isDefined(win)){
			win.close();
		}
	},
	openWindow : function(id, config){
		var win = this.getWindow(id);
		if(!win){
			win=this.createWindow(Ext.apply(config, {id : id}));
		}
		return win;
	},
	closeWindow : function(id){
		var win = this.getWindow(id);
		if(win){
			win.close();
		}
	},
    closeAllWindow : function(){
    	this.getManager().each(function(win){
    		win.close();
    	});
    },
    settitlebar : function(cwxx,ztxx,rjrq){
		 this.cwxx.setValue(cwxx);
		 this.ztxx.setValue(ztxx);
		 this.cwrq.setValue(rjrq);
    },
    setYwrq : function(ywrq){
		 this.ywsj.setValue(ywrq);
    },
    setDkrq : function(dkrq){
		 this.dksj.setValue(dkrq);
    }
});
//=================================================================
//	�ļ���mixky.wasoft.desktop.portal.js
//=================================================================
Mixky.wasoft.desktop.Portal = function(config){
    this.buttonPanel = new Ext.Panel({
	region:'west',
	border : false,
	bodyStyle : 'background:transparent none',
	width : 15
    });
    var panelItems;
    switch(MixkyApp.userConfig.columns){
    case 2:
	panelItems = [{
	    columnWidth:.5,
	    style:'padding:10px 0 10px 10px'
	},{
	    columnWidth:.5,
	    style:'padding:10px 10px 10px 10px'
	}];
	break;
    case 4:
	panelItems = [{
	    columnWidth:.25,
	    style:'padding:10px 0 10px 10px'
	},{
	    columnWidth:.25,
	    style:'padding:10px 0 10px 10px'
	},{
	    columnWidth:.25,
	    style:'padding:10px 0 10px 10px'
	},{
	    columnWidth:.25,
	    style:'padding:10px 10px 10px 10px'
	}];
	break;
    default :
	panelItems = [{
	    columnWidth:.33,
	    style:'padding:10px 0 10px 10px'
	},{
	    columnWidth:.33,
	    style:'padding:10px 0 10px 10px'
	},{
	    columnWidth:.33,
	    style:'padding:10px 10px 10px 10px'
	}];
    break;
    }
    this.portalPanel = new Ext.ux.Portal({
	region:'center',
	bodyStyle : 'background:transparent none',
	border : false,
	items:panelItems
    });

    this.portalPanel.on('drop', function(){
	this.setPortletsPos();
    }, this);
    Mixky.wasoft.desktop.Portal.superclass.constructor.call( this, Ext.apply({
	title : '工作桌面',
	border : false,
	layout : 'border',
	items : [this.buttonPanel, this.portalPanel],
	iconCls : 'icon-portal-portal'
    }, config));

    this.contextmenu = new Ext.menu.Menu({
	items : [
	         Mixky.wasoft.lib.actions.Preferences,
	         // Mixky.wasoft.lib.actions.SavePreferences,
	         // Mixky.wasoft.lib.actions.SaveAsDefaultPreferences,
	         //  '-',
	         Mixky.wasoft.lib.actions.ChangePassword,
	         //  '-',
	         Mixky.wasoft.lib.actions.OpenAdministrator,
	         Mixky.wasoft.lib.actions.OpenHelp,
	         Mixky.wasoft.lib.actions.Exit
	         ]
    });

    this.buttonPanel.on('afterrender', function(bp){
	this.shortcuts = new Mixky.wasoft.desktop.Shortcuts({
	    renderTo : bp.body
	});	
	this.shortcuts.on('columnsrefresh', function(width){
	    this.buttonPanel.setWidth(width);
	    this.doLayout();
	}, this, { delay: 100 });
    }, this);
};

Ext.extend( Mixky.wasoft.desktop.Portal, Ext.Panel, {

    closable:false,
    // overwrite
    onRender : function(ct, position){
	Mixky.wasoft.desktop.Portal.superclass.onRender.call(this, ct, position);
	this.body.on('contextmenu', function(e){
	    if(e.target.id == this.buttonPanel.body.id || 
		    e.target.id == this.portalPanel.body.id || 
		    e.target.id == this.portalPanel.body.first().id){
		e.stopEvent();
		if(!this.contextmenu.el){
		    this.contextmenu.render();
		}
		var xy = e.getXY();
		xy[1] -= this.contextmenu.el.getHeight();
		this.contextmenu.showAt(xy);
	    }
	}, this);	
    },
    getMinHeightCol : function(){
	var height = 10000;
	var index = 0;
	this.portalPanel.items.each(function(columnitem, col){
	    if(columnitem.getHeight() < height){
		height = columnitem.getHeight();
		index = col;
	    }
	}, this);
	return index;
    },
    // extend method
    addSubject : function(o){
	var id = 'portlet-' + o.key;
	var url = 'portlet.do';
	if(Ext.isDefined(o.applicationkey)){
	    id = 'portlet-' + o.applicationkey + '.' + o.key;
	    var app = Mixky.wasoft.cache.Applications[o.applicationkey];
	    if(!Ext.isDefined(app)){
		alert('未定义' + o.applicationkey + '应用');
		return;
	    }
	    url = app.url + '/' + 'portlet.do';
	}
	var panel = Ext.getCmp(id);
	if(!panel){
	    var col = Ext.isDefined(o.col) ? o.col : this.getMinHeightCol();
	    col = col % MixkyApp.userConfig.columns;
	    panel = new Ext.ux.Portlet({
		id : id,
		title : o.text,
		layout : 'fit',
		iconCls : o.iconCls,
		height : o.webheight,
		tools : [{
		    id : 'refresh',
		    handler : function(){
			panel.refresh();
		    },
		    qtip : "刷新栏目内容"
		}],
		autoLoad : {
		    url : url,
		    params : {key : o.key, appkey : o.applicationkey, height : o.webheight},
		    scripts : true
		},
		refresh : function(){
		    panel.doAutoLoad();
		}
	    });
	    this.portalPanel.items.get(col).add(panel);
	    this.portalPanel.doLayout();
	    panel.key = o.key;
	    panel.applicationkey = o.applicationkey;
	}
	return panel;
    },
    // extend method
    removeSubject : function(appkey, key){
	var id = 'portlet-' + key;
	if(Ext.isDefined(appkey)){
	    id = 'portlet-' + appkey + '.' + key;
	}
	var panel = Ext.getCmp(id);
	if(panel){
	    var pc = panel.findParentByType('portalcolumn');
	    pc.remove(panel);
	    //panel.destroy();
	}
    },
    // extend method
    addShortcut : function(o){
	o.handler = function(b, e){
	    Mixky.wasoft.lib.actions.openShortcut(this.btntype, this.applicationkey, this.key, e);
	};
	this.shortcuts.addShortcut(o);
    },
    // extend method
    removeShortcut : function(btntype, appkey, key){
	var btn = this.shortcuts.getButtonCmp(btntype, appkey, key);
	if(btn){
	    this.shortcuts.removeShortcut(btn);
	}
    },
    getButtonCmp : function(btntype, appkey, key){
	return this.shortcuts.getButtonCmp(btntype, appkey, key);
    },
    setPortletsPos : function(){
	this.portalPanel.items.each(function(columnitem, col){
	    columnitem.items.each(function(panel, row){
		var subject = MixkyApp.getSubject(panel.applicationkey, panel.key);
		subject.col  = col;
		subject.row  = row;
	    },this);
	}, this);
    }
});
//=================================================================
//	�ļ���mixky.wasoft.desktop.quickbar.js
//=================================================================
Mixky.wasoft.desktop.QuickBar = function(config){
	Mixky.wasoft.desktop.QuickBar.superclass.constructor.call( this, Ext.apply({
		text : '应用管理',
	    //scale : 'medium',
		iconAlign : 'top',
		iconCls : 'icon-portal-menu-appman',
		menu : new Ext.menu.Menu({
			items : [
			    Mixky.wasoft.lib.actions.Preferences,
			    Mixky.wasoft.lib.actions.SavePreferences,
			  //  Mixky.wasoft.lib.actions.SaveAsDefaultPreferences,
			   // '-',
			   // Mixky.wasoft.lib.actions.OpenOnlineusers,
			    Mixky.wasoft.lib.actions.OpenSysState,
			  //  '-',
			    Mixky.wasoft.lib.actions.ChangePassword,
			    Mixky.wasoft.lib.actions.OpenAdministrator,
			 //   '-',
			 //   Mixky.wasoft.lib.actions.OpenHelp,
			 //   Mixky.wasoft.lib.actions.Exit,
			    '-']
		})
	}, config));
};

Ext.extend( Mixky.wasoft.desktop.QuickBar, Ext.Button, {
	appendButton : function(item){
		this.menu.add(item);
	},
	removeButton : function(btntype, appkey, key){
		var item = this.getQuickStartCmp(btntype, appkey, key);
		if(Ext.isDefined(item)){
			this.menu.remove(item);
		}
	},
	getQuickStartCmp : function(btntype, appkey, key){
		for (var i = 0; i < this.menu.items.length; i++) {
			var item = this.menu.items.get(i);
			if(item && item.btntype == btntype && item.applicationkey == appkey && item.key == key){
				return item;
			}
		}
	}
});
//=================================================================
//	�ļ���mixky.wasoft.desktop.shortcuts.js
//=================================================================
/*
* qWikiOffice Desktop 0.8.1
* Copyright(c) 2007-2008, Integrated Technologies, Inc.
* licensing@qwikioffice.com
* 
* http://www.qwikioffice.com/license
*/

Mixky.wasoft.desktop.Shortcuts = function(config) {
  var desktopEl = config.renderTo
		, btnHeight = 74
		, btnWidth = 64
		, btnPadding = 15
		, col = null
		, row = null
		, items = []
		, columnsrefreshWidth = 0;

  this.addEvents('columnsrefresh');
  
  this.menu = new Ext.menu.Menu({
	  items : [{
		  iconCls : 'icon-common-open',
		  text : '打开',
		  handler : function(b){
		  	b.parentMenu.button.handler();
		  }
	  },'-', {
		  iconCls : 'icon-common-remove',
		  text : '移除',
		  scope : this,
		  handler : function(b){
		  	MixkyApp.removeShortcut(b.parentMenu.button.btntype, b.parentMenu.button.applicationkey, b.parentMenu.button.key);
		  }
	  }]
  });
  this.initColRow = function () {
    col = { index: 1, x: btnPadding };
    row = { index: 1, y: btnPadding };
    columnsrefreshWidth = btnPadding;
    this.fireEvent('columnsrefresh', columnsrefreshWidth);
  };
  this.initColRow();

  function isOverflow(y) {
    if (y > desktopEl.getHeight()) {
      return true;
    }
    return false;
  }

  this.addShortcut = function(config) {
    var div = desktopEl.createChild({ tag: 'div', cls: 'ux-shortcut-item' }),
			btn = new Mixky.wasoft.desktop.ShortcutButton(Ext.apply(config, {
			  text: Ext.util.Format.ellipsis(config.text, 25)
			}), div),
			menu = this.menu;
    // 系统图标
    
    // 右键菜单
    div.addListener('contextmenu', function(e){
    	menu.button = btn;
    	menu.showAt(e.getXY());
    });
    items.push(btn);
    this.setXY(btn.container);

    return btn;
  };
  // add by zhangchang begin
  this.getButtonCmp = function(btntype, appkey, key){
    for (var i = 0, len = items.length; i < len; i++) {
      if(items[i].btntype == btntype && items[i].applicationkey == appkey && items[i].key == key){
    	  return items[i];
      }
    }
  };
  // add by zhangchang end

  this.removeShortcut = function(b) {
    var d = document.getElementById(b.container.id);

    b.destroy();
    d.parentNode.removeChild(d);

    var s = [];
    for (var i = 0, len = items.length; i < len; i++) {
      if (items[i] != b) {
        s.push(items[i]);
      }
    }
    items = s;

    this.handleUpdate();
  };

  this.handleUpdate = function() {
    this.initColRow();
    for (var i = 0, len = items.length; i < len; i++) {
      this.setXY(items[i].container);
    }
  };

  this.setXY = function(item) {
    var bottom = row.y + btnHeight,
			overflow = isOverflow(row.y + btnHeight);

    if (overflow && bottom > (btnHeight + btnPadding)) {
      col = {
        index: col.index++, 
        x: col.x + btnWidth + btnPadding
      };
      row = {
        index: 1, 
        y: btnPadding
      };
    }
    
    item.setLeftTop(col.x, row.y);

    row.index++;
    row.y = row.y + btnHeight + btnPadding;
    if(columnsrefreshWidth != col.x + btnWidth + btnPadding){
    	columnsrefreshWidth = col.x + btnWidth + btnPadding;
        this.fireEvent('columnsrefresh', columnsrefreshWidth);
    }
  };

  Ext.EventManager.onWindowResize(this.handleUpdate, this, { delay: 500 });
};

Ext.extend(Mixky.wasoft.desktop.Shortcuts, Ext.util.Observable, {}); 


/**
* @class Mixky.wasoft.desktop.ShortcutButton
* @extends Ext.Button
*/
Mixky.wasoft.desktop.ShortcutButton = function(config, el) {

  Mixky.wasoft.desktop.ShortcutButton.superclass.constructor.call(this, Ext.apply(config, {
    renderTo: el,
    clickEvent: 'dblclick',
    template: new Ext.Template(
			'<div class="ux-shortcut-btn ' + config.iconCls + '">',
				'<div class="ux-shortcut-btn-img icon-sys-0shortcut' + config.btntype + '48 ' + config.iconCls + '48"></div>',
				'<div class="ux-shortcut-btn-text">{0}</div>',
			'</div>')
  }));
  
  // add by zhangchang begin
  this.btntype = config.btntype;
  this.applicationkey = config.applicationkey;
  this.key = config.key;
  // add by zhangchang end

};

Ext.extend(Mixky.wasoft.desktop.ShortcutButton, Ext.Button, {

  buttonSelector: 'div:first',

  // private
  setButtonClass : function(){

  },
  setIconClass : function(cls){
      if(this.el){
          this.btnEl.replaceClass(this.iconCls, cls);
      }
      this.iconCls = cls;
      return this;
  },
  
  initButtonEl: function(btn, btnEl) {
    Mixky.wasoft.desktop.ShortcutButton.superclass.initButtonEl.apply(this, arguments);

    btn.removeClass("x-btn");

    if (this.iconCls) {
      if (!this.cls) {
        btn.removeClass(this.text ? 'x-btn-text-icon' : 'x-btn-icon');
      }
    }
  },

  autoWidth: function() {
    // do nothing
  },

  /**
  * Sets this shortcut button's text
  * @param {String} text The button text
  */
  setText: function(text) {
    this.text = text;
    if (this.el) {
      this.el.child("div.ux-shortcut-btn-text").update(text);
    }
  }
});
//=================================================================
//	�ļ���mixky.wasoft.desktop.taskbar.js
//=================================================================
Mixky.wasoft.desktop.TaskBar = function(config){
	Mixky.wasoft.desktop.TaskBar.superclass.constructor.call( this, Ext.apply({
		text : '活动页面',
		hidden:false,
	    //scale : 'medium',
		iconAlign : 'top',
		iconCls : 'icon-portal-menu-livepage',
		menu : new Ext.menu.Menu({
			items : [{
				iconCls : "icon-portal-menu-livepage-closeall",
				text : "关闭所有",
				handler : function(){
					MixkyApp.desktop.closeAllWindow();
				}
			}, '-']
		})
	}, config));
};

Ext.extend( Mixky.wasoft.desktop.TaskBar, Ext.Button, {
	appendWindow : function(win){
		var item = this.menu.addMenuItem({
			iconCls: win.iconCls,
			checked: true,
			text : Ext.util.Format.ellipsis(win.title, 12),
			tooltip: win.taskbuttonTooltip || win.title,
			handler: function() {
				win.show();
				win.toFront();
			}
		});
		item.win = win;
	},
	removeWindow : function(win){
		var item = this.findWindowItem(win);
		if(Ext.isDefined(item)){
			this.menu.remove(item);
		}
	},
	markWindowShow : function(win){
		var item = this.findWindowItem(win);
		if(Ext.isDefined(item)){
			item.setChecked(true);
		}
	},
	markWindowHide : function(win){
		var item = this.findWindowItem(win);
		if(Ext.isDefined(item)){
			item.setChecked(false);
		}
	},
	findWindowItem : function(win){
		for (var i = 0; i < this.menu.items.length; i++) {
			if (this.menu.items.get(i).win == win) {
				return this.menu.items.get(i);
			}
		}
	}
});
//=================================================================
//	�ļ���mixky.wasoft.desktop.titlebar.js
//=================================================================
Mixky.wasoft.desktop.Titlebar = function(cfg){
	Mixky.wasoft.desktop.Titlebar.superclass.constructor.call(this, cfg);
};

Ext.extend(Mixky.wasoft.desktop.Titlebar, Ext.Panel, {
    margins : '0 0 0 0',
    cmargins :'0 0 0 0',
    border : false,
    bodyCssClass : 'mixky-portal-title',
	data : {
		title : ApplicationInfo.title, 
		userid : ApplicationInfo.userid,
		username : ApplicationInfo.username,
		departmentname : ApplicationInfo.departmentname
	},
	tpl : [
		'<table class="mixky-titlebar" height="100%">',
			'<tr valign=middle>',
				//'<td width=100% class="mixky-appname">{title}</td>',
			    '<td width=100%>{title}</td>',
				'<td nowrap class="mixky-userinfo">{departmentname}　{username}</td>',
			'</tr>',
		'</table>'
	]
});
//=================================================================
//	�ļ���mixky.wasoft.desktop.toolbar.js
//=================================================================
//document.write("<script language=javascript src='EimUtil.js'></script>");
Mixky.wasoft.desktop.Toolbar = function(cfg){
    if(!cfg){cfg = {/*hidden:true*/};}

    this.winsButton = new Mixky.wasoft.desktop.TaskBar();	
    this.quickButton = new Mixky.wasoft.desktop.QuickBar();

    Ext.apply(cfg, {/*html:'<a href="javascript:Ext.getCmp(\'treePanel\').collapse()">折叠</a>' ,*/
	items : this.initToolBarItems()
    });

    Mixky.wasoft.desktop.Toolbar.superclass.constructor.call(this, cfg);
};

var IMButton= new Ext.Button(
	{
	    text:"在线交流",
	    iconAlign : 'top',
	    iconCls:'icon-portal-menu-chat',
	    handler:function(){
		//Mixky.wasoft.lib.chatroom();
		IM.openMainWin();
		return;
		DesktopDirect.accessToUserInformation("1",function(result,e){
		    name = result.name;
		    pawd = result.pawd;
		    if("" != name && "" != pawd){
			start(name,pawd);
		    }
		});
		if (EimTest == null)
		    var EimTest = {};
		/**
		 * 获取画面Dom元素对象
		 */
		EimTest.$ = function(id){
		    //alert("EimTest.$");
		    return document.getElementById(id);
		};
		/**
		 * 验证IM是否安装了。

		 */
		EimTest.isInstall = function (){
		    if(EimUtil.engine == null){
			try{
			    EimUtil.engine = new ActiveXObject(EimUtil.ActiveName);
			    Ext.Msg.show({
				title:'提示',
				msg:'请确认客户端已安装并且网络设置正确，客户端及配置说明请在登陆页面下载！',
				buttons:{
				    no:'确定'
				}
			    });
			}catch(e){
			    //alert('IM没有安装！');
			    //Ext.Msg('提示', 'IM没有安装！');
			    Ext.Msg.show({
				title:'提示',
				msg:'IM没有安装,请从登录页面下载IM。',
				buttons:{
				    no:'确定'
				}
			    });
			}
		    }
		    return EimUtil.engine != null;
		};
		/**
		 * 启动客户端

		 */
		EimTest.RunEim = function(name,pawd) {
		    //var userName ="<%=userName%>";
		    //var password ="<%=password%>";
		    var userName = name;
		    var password = pawd;
		    //alert(userName);
		    //alert(password);
		    EimUtil.RunEim(userName,password);

		};
		function  start(name,pawd){
		    EimTest.isInstall();
		    EimTest.RunEim(name,pawd);
		};
	    }
	}
);

Ext.extend(Mixky.wasoft.desktop.Toolbar, Ext.Toolbar, {
    initMenuItems : function(parentId){
	var menus = MixkyApp.userMenus;
	var items = [];		
	for(var i=0;i<menus.length;i++){
	    var menu = Mixky.wasoft.cache.Menus[menus[i]];
	    if(!Ext.isDefined(menu) || menu.parentId != parentId){
		continue;
	    }
	    var menuCfg = {
		    text : menu.text,
		    name : menu.name,
		    iconCls : menu.iconCls,
		    tooltip : menu.qtip
	    };
	    if(parentId == 0){
		menuCfg.iconAlign = 'top';
		if(!Ext.isDefined(menu.modulekey) || menu.modulekey == ''){
		    menuCfg.xtype = 'splitbutton';
		}else{
		    menuCfg.handler = Mixky.wasoft.lib.actions.handlerAction;
		}
	    }else{
		menuCfg.handler = Mixky.wasoft.lib.actions.handlerAction;
	    }
	    var subitems = this.initMenuItems(menu.id);
	    if(subitems.length > 0){
		menuCfg.menu = {items : subitems};
	    }
	    // 增加一个对按钮属性的处理机会
	    Mixky.wasoft.lib.handlerMenuConfig(menuCfg);

	    items.push(new Ext.Action(menuCfg));
	}
	return items;
    },
    initToolBarItems : function(){
	this.menuitems = this.initMenuItems(0);
	//var workdesk = this.menuitems.shift();
	//this.menuitems.unshift( workdesk);//, Mixky.wasoft.lib.actions.ShowDesktop);
	this.menuitems.push(this.quickButton, this.winsButton, Mixky.wasoft.lib.actions.Exit);
	var fontsize = 66;
	var items = [{
	    xtype : 'buttongroup',
	    columns : Math.round(screen.width/fontsize),
	    items:this.menuitems
	}];
	//var items = this.initMenuItems(0);
	//items.push([IMButton,this.quickButton,Mixky.wasoft.lib.actions.Exit]);
	//items.push('->');
	/*items.push( {
			xtype : 'buttongroup',
			columns : 5,
		    items:[
		       IMButton,
			  // Mixky.wasoft.lib.actions.ShowDesktop,
		       this.quickButton,
		       this.winsButton,
		       Mixky.wasoft.lib.actions.Exit
		    ]
		});*/
	return items;
    },
    appendWindow : function(win){
	this.winsButton.appendWindow(win);
    },
    removeWindow : function(win){
	this.winsButton.removeWindow(win);
    }
});
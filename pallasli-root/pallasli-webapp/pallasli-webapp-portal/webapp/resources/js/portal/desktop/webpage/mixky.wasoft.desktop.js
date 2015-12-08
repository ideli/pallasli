
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
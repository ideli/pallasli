
//=================================================================
//	�ļ�����mixky.wasoft.lib.action.js
//=================================================================
/*
 *	Mixky 系统通用操作
*/

Ext.namespace("Mixky.wasoft.lib.actions");

Mixky.wasoft.lib.actions.Preferences = new Ext.Action({
	text : '桌面管理',
    scale : 'medium',
	iconAlign : 'top',
	iconCls : 'icon-portal-menu-appman-deskman',
	handler : Mixky.wasoft.lib.Preferences
});

Mixky.wasoft.lib.actions.SavePreferences = new Ext.Action({
	text : '保存门户设置',
    scale : 'medium',
	iconAlign : 'top',
	iconCls : 'icon-portal-menu-appman-saveportal',
	handler : function(){
		var notifyWin = MixkyApp.showWaitMessage("正在保存门户设置...");
		DesktopDirect.saveUserConfig('', MixkyApp.userConfig, function(result, e){
			if(result && result.success){
				notifyWin.setIconClass('x-icon-done');
				notifyWin.setTitle('保存完成');
				notifyWin.setMessage('门户设置保存完毕.');
			}else{
				notifyWin.setIconClass('x-icon-error');
				notifyWin.setTitle('保存失败');
				notifyWin.setMessage('门户设置保存失败.');
			}
			MixkyApp.hideNotification(notifyWin);
		});
	}
});

Mixky.wasoft.lib.actions.SaveAsDefaultPreferences = new Ext.Action({
	text : '保存为默认门户设置',
    scale : 'medium',
	iconAlign : 'top',
	iconCls : 'icon-common-save',
	// 普通用户隐藏
	hidden : ApplicationInfo.usertype == 1,
	handler : function(){
		var notifyWin = MixkyApp.showWaitMessage("正在保存为默认设置...");
		DesktopDirect.saveUserConfig('(default)', MixkyApp.userConfig, function(result, e){
			if(result && result.success){
				notifyWin.setIconClass('x-icon-done');
				notifyWin.setTitle('保存完成');
				notifyWin.setMessage('默认门户设置保存完毕.');
			}else{
				notifyWin.setIconClass('x-icon-done');
				notifyWin.setTitle('保存失败');
				notifyWin.setMessage('默认门户设置保存失败.');
			}
			MixkyApp.hideNotification(notifyWin);
		});
	}
});

Mixky.wasoft.lib.actions.ChangePassword = new Ext.Action({
	text : '修改密码',
    scale : 'medium',
	iconAlign : 'top',
	iconCls : 'icon-portal-menu-appman-passman',
	handler : Mixky.wasoft.lib.ChangePassword
});

Mixky.wasoft.lib.actions.Exit = new Ext.Action({
	text : '退出系统',
    //scale : 'medium',
	iconAlign : 'top',
	iconCls :'icon-portal-menu-exit',
	handler : function(){
		Ext.Msg.confirm('退出警告','退出系统，该操作将放弃所有未保存数据，您确定吗？',function(btn){
			if(btn == 'yes'){
				/*Ext.MessageBox.show({title:'考勤提示',msg:'退出系统前您是否签退？',
                 modal:true,buttons:Ext.Msg.YESNO,icon:Ext.Msg.WARNING,width:300,closable:false,fn:function(btn1){
                 	if(btn1 == 'yes'){
                 		KqglDirect.kqgl_qt(function(result, e){
			               if (result && result.success) {
			 	               Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
			                   icon:Ext.Msg.INFO,width:300,closable:false,fn:function(){
			                   	   tc=1;
				                   window.location = "logout.do";
			                   }});
			               }
			               else{
			                  Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
			                  icon:Ext.Msg.ERROR,width:300,closable:false,fn:function(){
			                  	   tc=1;
				                   window.location = "logout.do"; 
			                  }});
			               }
			            })
                 	}
                 	else{
                 		tc=1;
				        window.location = "logout.do";
                 	}
                 }});*/
				 tc=1;
			     window.location = "logout.do";
			}
		});
	}
});

Mixky.wasoft.lib.actions.OpenHelp = new Ext.Action({
	text : '查看帮助',
    //scale : 'medium',
	iconAlign : 'top',
	iconCls : 'icon-portal-help',
	hidden : true
});

Mixky.wasoft.lib.actions.OpenAdministrator = new Ext.Action({
	text : '应用管理',
    scale : 'medium',
	iconAlign : 'top',
	iconCls :'icon-portal-menu-appman-appman',
	// 普通用户隐藏
	hidden : ApplicationInfo.usertype==1||ApplicationInfo.usertype>=3,
	handler : function(){
		window.open("administrator.do");
	}
});

Mixky.wasoft.lib.actions.ShowDesktop = new Ext.Action({
	text : '我的桌面',
    //scale : 'medium',
	iconAlign : 'top',
	iconCls :'icon-portal-menu-desktop',
	handler : function(){
		MixkyApp.showDesktop();
	}
});

Mixky.wasoft.lib.actions.OpenOnlineusers = new Ext.Action({
	text : '在线用户',
    scale : 'medium',
	iconAlign : 'top',
	iconCls : 'icon-portal-menu-appman-onlineuser',
	hidden : ApplicationInfo.usertype==1||ApplicationInfo.usertype>=3,
	handler :Mixky.wasoft.lib.onlineusers
});

Mixky.wasoft.lib.actions.OpenSysState = new Ext.Action({
	text : '系统状态管理',
    scale : 'medium',
	iconAlign : 'top',
	iconCls : 'icon-portal-menu-appman-onlineuser',
	hidden : ApplicationInfo.usertype==1||ApplicationInfo.usertype>=3,
	handler :Mixky.wasoft.lib.sysstate
});

Mixky.wasoft.lib.actions.getIconSizeAction = function(button, iconsize){
	button.setIconClass(button.getIconClass() + iconsize);
	return button;
};


// 菜单处理函数
Mixky.wasoft.lib.actions.handlerAction = function(item, e){
	//Mixky.lib.alertObject(item);
	if(item.name!="wacwztbg"&&item.name!="wacwhssz"&&item.name.substring(0,4)=="wacw"){
	 WacwAppDirect.getZtChk(true,function(result,E){
     if (result&& !result.success) {
		 MixkyApp.desktop.closeModule('wacw', 'wacwpzgl');
		 MixkyApp.desktop.closeModule('wacw', 'wacwqmcl');
		 MixkyApp.desktop.closeModule('wacw', 'wacwzbgl');
		 MixkyApp.desktop.closeModule('wacw', 'wacwpjgl');
		 MixkyApp.desktop.closeModule('wacw', 'wacwreport');
		 MixkyApp.desktop.closeModule('wacw', 'wacwpzcx');
     	 var  params={'title':'账套选择','width':340,'height':280}
	     var title=params.title;
	     var width=params.width;
	     var height=params.height;
	     var panel='ztdialog';
	     var url='app/common/ztml';
	     var recordHandler = function(record){
		      var ztbh=record["ztbh"];
              var cznd=record["nd"]+record["yf"];
              WacwAppDirect.getZtxx(ztbh,cznd,function(result,e){});
	     }
	     var win1 = MixkyApp.desktop.openWindowWithJspUrl(
			 'wacw', 
			 panel, 
			 url, 
			 {
				title : title,
				width : width,
				height: height,
				closable : false,
				minimizable : true,
				modal :true,
				manager : MixkyApp.desktop.getManager(),
				bbar : [{
					text : '关闭',
					handler : function() {
						win1.close();												
					}
				},'->',{
					text : '确定',
					handler : function() {
						// 获得回调参数
						var record = win1.items.get(0).getRecord();
						
						win1.winRecordHandler(record);
					}
				}]
			 },
			 {},
			 params
	     );
	     // 视图调用接口
	     win1.winRecordHandler = function(record){
		    if(record && record != null){
			   recordHandler(record);
			   win1.close();			  
		    }
	     }
	     
	  }else{	
	  	 MixkyApp.desktop.settitlebar(result.cznd,result.cwzt,result.rjrq);
		
	  }
    });
    Mixky.wasoft.lib.actions.openMenu.call(item, item.applicationKey, item.name, e);
	}else if (item.name!="wazjztbg"&&(item.name.substring(0,4)=="wazj"||item.name.substring(0,4)=="mkFM"||item.name.substring(0,4)=="FM_C")){
		WazjAppDirect.getZtChk(true,function(result,E){
			//  alert(result.success);
		     if (result&& !result.success) {
		     	 var  params={'title':'账套选择','width':340,'height':280}
			     var title=params.title;
			     var width=params.width;
			     var height=params.height;
			     var panel='ztdialog';
			     var url='app/common/zttxml';
			    var recordHandler = function(record){
				      var ztbh=record["ztbh"];
		              var cznd=record["nd"]+record["yf"];
		              WazjAppDirect.getZtxx(ztbh,cznd,function(result,e){
		              if(result.success){
		              WazjAppDirect.im_txjc(ztbh,cznd,function(result,e){
		             if(result.msg==1){
		        		  var win1 = MixkyApp.desktop.openWindowWithJspUrl('wazj','zjdltx','app/common/dltx', 
					 {
						title :'提醒信息',
						width : 800,
						height: 450,
						closable : false,
						minimizable : true,
						modal :true,
						manager : MixkyApp.desktop.getManager(),
						bbar : ['->',{
							text : '确定',
							handler : function() {
								// 获得回调参数
								win1.close();
							}
						}]
					 });
		              }
		              });
		              }
		              });
			     }
			     var win1 = MixkyApp.desktop.openWindowWithJspUrl(
			    	'wazj', 
					 panel, 
					 url, 
					 {
						title : title,
						width : width,
						height: height,
						closable : false,
						minimizable : true,
						modal :true,
						manager : MixkyApp.desktop.getManager(),
						bbar : [{
							text : '关闭',
							handler : function() {
								win1.close();												
							}
						},'->',{
							text : '确定',
							handler : function() {
								// 获得回调参数
								var record = win1.items.get(0).getRecord();
								win1.winRecordHandler(record);
							}
						}]
					 },
					 {},
					 params
			     );
			     // 视图调用接口
			     win1.winRecordHandler = function(record){
				    if(record && record != null){
					   recordHandler(record);
					   win1.close();
				    }
			     }
			  }else{
			  	 MixkyApp.desktop.settitlebar(result.cznd,result.cwzt,result.rjrq);
					Mixky.wasoft.lib.actions.openMenu.call(item, item.applicationKey, item.name, e);
				}	
			  });
	}else{
		// MixkyApp.desktop.settitlebar(result.cznd,result.cwzt,result.rjrq);
		Mixky.wasoft.lib.actions.openMenu.call(item, item.applicationKey, item.name, e);
	}	
};

// 打开菜单
Mixky.wasoft.lib.actions.openMenu = function(appkey, key, e){
	var menu;
	if(Ext.isDefined(appkey)){
		menu = Mixky.wasoft.lib.cache.getAppModuleMenu(appkey, key);
	}else{
		menu = Mixky.wasoft.cache.Menus[key];
		appkey = menu.applicationkey;
	}
	if(!menu){
		return;
	}
	if(menu.handler){
		menu.handler.call(this, e);
	}else{
		switch(menu.type){
		case 'sysmodulegroup':
			break;
		case 'sysmodulemenu':
			if(!Ext.isDefined(appkey) || appkey == ''){
				MixkyApp.showErrorMessage('模块 ' + key + ' 未指定应用标识！');
				return;
			}
			MixkyApp.desktop.openModule(appkey, menu.modulekey);
			var modulemenus = Mixky.wasoft.lib.cache.getAppModuleMenus(appkey);
			for(var m in modulemenus){
				var item = modulemenus[m];
				if(item.parentId == menu.modulekey && item.isdefault){
					Mixky.wasoft.lib.actions.openMenu(appkey, item.name, e);
					break;
				}
			}
			break;
		case 'modulesubmenu':
		case 'modulemenu':
			var module = MixkyApp.desktop.openModule(appkey, menu.modulekey);
			if(Ext.isDefined(menu.viewurl)){
				if(module && module.openUrl){
					module.openUrl('v-' + appkey + '.' + menu.key, menu.viewurl);
				}
			}else if(Ext.isDefined(menu.viewkey)){
				if(module && module.openView){
					module.openView(menu.viewkey);
				}
			}
			break;
		}
	}
};

// 打开快捷键
Mixky.wasoft.lib.actions.openShortcut = function(type, appkey, key, e){
	switch(type){
	case 'sys':
		switch(key){
		case 'preferences':
			Mixky.wasoft.lib.actions.Preferences.execute();
			break;
		case 'password':
			Mixky.wasoft.lib.actions.ChangePassword.execute();
			break;
		case 'help':
			Mixky.wasoft.lib.actions.OpenHelp.execute();
			break;
		case 'administrator':
			Mixky.wasoft.lib.actions.OpenAdministrator.execute();
			break;
		case 'desktop':
			Mixky.wasoft.lib.actions.ShowDesktop.execute();
			break;
		}
		break;
	case 'menu':
		Mixky.wasoft.lib.actions.openMenu(appkey, key, e);
		break;
	case 'document':
		var docparams = key.split('|');
		if(docparams.length > 1){
			MixkyApp.desktop.openDocument(docparams[0], docparams[1]);
		}
		break;
	case 'folder':
		break;
	}
};
//=================================================================
//	�ļ�����mixky.wasoft.lib.cache.js
//=================================================================

Ext.namespace("Mixky.wasoft.lib.cache");
Ext.namespace("Mixky.wasoft.cache");

// private 根据ID获得菜单
Mixky.wasoft.lib.cache.getMenuById = function(id){
	for(n in Mixky.wasoft.cache.Menus){
		if(Mixky.wasoft.cache.Menus[n].id == id){
			return Mixky.wasoft.cache.Menus[n];
		}
	}
};


Mixky.wasoft.lib.cache.getAppModule = function(appkey, modulekey){
	var modules = eval('Mixky.wasoft.cache.' + appkey + '.Modules');
	return modules[modulekey];
}
Mixky.wasoft.lib.cache.getAppModuleMenus = function(appkey){
	return eval('Mixky.wasoft.cache.' + appkey + '.Menus');
}
Mixky.wasoft.lib.cache.getAppModuleMenu = function(appkey, menukey){
	var menus = eval('Mixky.wasoft.cache.' + appkey + '.Menus');
	return menus[menukey];
}
Mixky.wasoft.lib.cache.getAppModuleView = function(appkey, viewkey){
	var views = eval('Mixky.wasoft.cache.' + appkey + '.Views');
	return views[viewkey];
}
Mixky.wasoft.lib.cache.getAppDocument = function(appkey, documentkey){
	var documents = eval('Mixky.wasoft.cache.' + appkey + '.Documents');
	return documents[documentkey];
}
Mixky.wasoft.lib.cache.getAppDocumentType = function(appkey, documenttypekey){
	var documenttypes = eval('Mixky.wasoft.cache.' + appkey + '.DocumentTypes');
	return documenttypes[documenttypekey];
}
Mixky.wasoft.lib.cache.getDictionary = function(appkey, dictionarykey){
	var dictionarys;
	if(Ext.isDefined(appkey) && appkey != ''){
		dictionarys = eval('Mixky.wasoft.cache.' + appkey + '.Dictionarys');
	}else{
		dictionarys = Mixky.wasoft.cache.Dictionarys;
	}
	return dictionarys[dictionarykey];
}
//=================================================================
//	�ļ�����mixky.wasoft.lib.context.js
//=================================================================

Ext.namespace("Mixky.wasoft.lib");

Mixky.wasoft.lib.Context = {
	activeApplicationKey : undefined,
	activeModuleKey : undefined,
	activeViewKey : undefined
};
//=================================================================
//	�ļ�����mixky.wasoft.lib.dictionary.js
//=================================================================

Ext.namespace("Mixky.wasoft.lib");

Mixky.wasoft.lib.dictionaryRenderer = function(appkey, val, dictionaryname){
	var dictionary = Mixky.wasoft.lib.cache.getDictionary(appkey, dictionaryname);
	var display = val;
	if(Ext.isDefined(dictionary)){
		dictionary.each(function(record){
			if(record.get('display') == val || record.get('value') == val){
				display = record.get('display');
				return false;
			}
		});
	}
	return display;
}

Mixky.wasoft.lib.getDictionaryUrlRender = function(appkey, url, value, fn){
	if(Ext.isDefined(appkey) && appkey != ''){
		var app = Mixky.wasoft.cache.Applications[appkey];
		url = app.url + '/' + url;
	}
	Ext.Ajax.request({
		url: url,
		success: function(response, opts){
			var obj = Ext.decode(response.responseText);
			if(obj && Ext.isDefined(obj.key)){
				fn(obj.key, value);
			}
		},
		params: { value: value, appkey:appkey }
	});
}

Mixky.wasoft.lib.getDictionaryDourlRender = function(appkey, url, value, fn, params){
	var pageurl = 'page.do';
	if(Ext.isDefined(appkey) && appkey != ''){
		var app = Mixky.wasoft.cache.Applications[appkey];
		pageurl = app.url + '/' + pageurl;
	}
	Ext.Ajax.request({
		url: pageurl,
		success: function(response, opts){
			var obj = Ext.decode(response.responseText);
			if(obj && Ext.isDefined(obj.key)){
				fn(obj.key, value);
			}
		},
		params: Ext.apply({url:url, value: value, appkey:appkey}, params)
	});	
}

Mixky.wasoft.lib.getDictionaryUrlStore = function(appkey, url){
	if(Ext.isDefined(appkey) && appkey != ''){
		var app = Mixky.wasoft.cache.Applications[appkey];
		url = app.url + '/' + url;
	}
	var store = new Ext.data.Store({
		proxy		: new Ext.data.HttpProxy({
			url			: url
		}),
		reader		: new Ext.data.JsonReader({
			root		: 'results',
			id			:'display',
			fields		:["display","value"]
		})
	});
	store.load();
	return store;
}

Mixky.wasoft.lib.getDictionaryDoUrlStore = function(appkey, url, params){
	var app = Mixky.wasoft.cache.Applications[appkey];
	var store = new Ext.data.Store({
		proxy		: new Ext.data.HttpProxy({
			url			: app.url + "/page.do"
		}),
		baseParams : Ext.apply({url:url}, params),
		reader		: new Ext.data.JsonReader({
			root		: 'results',
			id			:'display',
			fields		:["display","value"]
		})
	});
	store.load();
	return store;
}

Mixky.wasoft.lib.getDictionaryDirectStore = function(fn, params, panel){
	var remoteStore = new Ext.data.DirectStore(Ext.apply({
	    paramsAsHash: params && params.paramOrder,
	    idProperty: 'display',
	    root: 'results',
	    directFn: fn,
	    fields: ['display', 'value']
	}, params));
	// 设置字段侦听
	if(params && params.paramOrder && panel && panel.getFieldValue){
		remoteStore.on('beforeload', function(s, o){
			for(var i=0;i<s.paramOrder.length;i++){
				var value = panel.getFieldValue(s.paramOrder[i]);
				if(Ext.isDefined(value)){
					s.baseParams[s.paramOrder[i]] = value;
					if(Ext.isDefined(o.params)){
						o.params[s.paramOrder[i]] = value;
					}
				}
			}
		});
	}else{
		remoteStore.load();
	}
	return remoteStore;
}
//=================================================================
//	�ļ�����mixky.wasoft.lib.favorite.js
//=================================================================
Ext.namespace("Mixky.app.common");

Mixky.wasoft.lib.favoriteStore = {};

Mixky.wasoft.lib.showFavoriteById = function(appkey, store, srckey){
	
	if(!Ext.isDefined(Mixky.wasoft.lib.favoriteStore[srckey])){
		
		var app = Mixky.wasoft.cache.Applications[appkey];
		
		FavoriteAppDirect.getUserFavorites(srckey, function(result, e){
			if(result && result.success){
				Mixky.wasoft.lib.favoriteStore[srckey] = result.favorites;
				Mixky.wasoft.lib.showFavoriteById(appkey, store, srckey);
			}
		});
	}else{
		var a = Mixky.wasoft.lib.favoriteStore[srckey];
		for(var i=0;i<store.getCount();i++){
			var record = store.getAt(i);
			for(var j=0;j<a.length;j++){
				if(a[j] == record.get("ID")){
					record.set("F_FAVORITE_FLAG", 1);
					record.commit();
					break;
				}
			}
		}
	}
};

Mixky.wasoft.lib.updateFavorite = function(appkey, srckey, srcparam, title, value, fn){

	var app = Mixky.wasoft.cache.Applications[appkey];
	var dt = Mixky.wasoft.lib.cache.getAppDocumentType(appkey, srckey);
	if(dt){
		var a = Mixky.wasoft.lib.favoriteStore[srckey];
		FavoriteAppDirect.updateFavorite(appkey, dt.caption, srckey, srcparam.toString(), title, value, function(result, e){
			if(result && result.success){
				if(value == 1){
					if(Ext.isDefined(a)){
						// 更新到本地缓存
						a[a.length] = srcparam;
					}
					MixkyApp.showInfoMessage(title + '成功添加到收藏夹!', '操作提示');
				}else{
					for(var i=0;i<a.length;i++){
						if(a[i] == srcparam){
							// 从本地缓存中移除
							a.removeAt(i);
							break;
						}
					}
				}
				fn.call(this, true);
			}else{
	    		MixkyApp.showErrorMessage('收藏夹操作失败!', '错误提示');
			}
		});
	}else{
		MixkyApp.showErrorMessage('未找到收藏的文档类型!', '错误提示');
	}
}

Mixky.wasoft.lib.addFavoriteTag = function(appkey){
	Ext.Msg.prompt('添加收藏夹标签', '请输入标签名称:', function(btn, text){
	    if (btn == 'ok' && text != ''){
	    	
	    	var app = Mixky.wasoft.cache.Applications[appkey];
	    	var directFn = eval(app.keyPrefix + 'AppDirect.submitRowForm');
	    	
	    	directFn('mkFavorite.T_MK_APP_FAVORITE_TAG.frmFavoriteTagEdit', {F_NAME:text}, function(result, e){
				if(result && result.success){
					var m = MixkyApp.desktop.getModule(appkey, 'mkFavorite');
					if(m){
						m = MixkyApp.desktop.openModule(appkey, 'mkFavorite');
						m.refresh(true, false);
					}
					MixkyApp.desktop.openModule(appkey, 'mkFavorite').refresh(true, false);
				}else{
					MixkyApp.showDirectActionFail("添加【" + text + "】标签失败", result, e);
				}
	    	})
	    }
	});
}

Mixky.wasoft.lib.addFavoriteUrl = function(appkey){
	Ext.Msg.prompt('添加网址收藏', '请输入收藏的网址:', function(btn, text){
	    if (btn == 'ok' && text != ''){
	    	
	    	var app = Mixky.wasoft.cache.Applications[appkey];
	    	
	    	FavoriteAppDirect.createFavoriteUrl(appkey, text, function(result, e){
				if(result && result.success){
					MixkyApp.desktop.openAppDocument(appkey, "mkFavorite.docFavorite", result.id);
				}else{
					MixkyApp.showDirectActionFail("添加网址收藏【" + text + "】失败", result, e);
				}
	    	})
	    }
	});
}

Mixky.wasoft.lib.openFavorite = function(appkey, srckey, srcparam){
	
	var dt = Mixky.wasoft.lib.cache.getAppDocumentType(appkey, srckey);
	if(!dt){
		var doc = Mixky.wasoft.lib.cache.getAppDocument(appkey, srckey);
		if(!doc){
			MixkyApp.showErrorMessage('找不到收藏项类型【' + srckey + '】定义，打开操作失败，!', '错误提示');
		}else{
			MixkyApp.desktop.openAppDocument(appkey, srckey, srcparam);
		}
		return;
	}
	switch(dt.type){
	case 0:		// 自定义收藏项
		if(Ext.isDefined(dt.handler)){
			dt.handler(srcparam);
		}
		break;
	case 1:		// 文档收藏项
		MixkyApp.desktop.openAppDocument(appkey, dt.param, srcparam);
		break;
	case 2:		// URL收藏项
	    var hostAddr=srcparam.trim();
	    if (hostAddr.indexOf("http://")==-1&&hostAddr.indexOf("https://")==-1) {
			hostAddr = "http://" + hostAddr;
		} 
		window.open(hostAddr);
		break;
	}
}
//=================================================================
//	�ļ�����mixky.wasoft.lib.js
//=================================================================


Mixky.wasoft.lib.getNewTableRecordId = function(applicationkey, tablename, fnAfterNew){
	if(!Ext.isDefined(applicationkey)){
		applicationkey = Mixky.wasoft.lib.Context.activeApplicationKey;
	}
	var app = Mixky.wasoft.cache.Applications[applicationkey];
	var directFn = eval(app.keyPrefix + 'AppDirect' + '.getNewTableRecordId');
	directFn(tablename, function(result, e){
		if(result && result.success){
			fnAfterNew(result.id);
		}
	});
};

Mixky.wasoft.lib.handlerMenuConfig = function(cfg){
	if(cfg.name == 'cwgl'||cfg.name == 'wacw'||cfg.name == 'wazj'){
		cfg.handleMouseEvents = false;
	}
};
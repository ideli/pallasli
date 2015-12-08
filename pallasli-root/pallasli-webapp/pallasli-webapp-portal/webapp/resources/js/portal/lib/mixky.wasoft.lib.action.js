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
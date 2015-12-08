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
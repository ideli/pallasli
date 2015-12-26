Ext.define('MyDesktop.App', {
    extend: 'Ext.ux.desktop.App',

    requires: [
        'Ext.window.MessageBox',
        'Ext.ux.desktop.ShortcutModel',
        'MyDesktop.SystemStatus',
        'MyDesktop.UploadWindow',
        'MyDesktop.VideoWindow',
        'MyDesktop.SummaryWindow',
        'MyDesktop.WordDocWindow',
        'MyDesktop.Folder',
        'MyDesktop.Notepad',
        'MyDesktop.KnowledgeWindow',
        'MyDesktop.BogusMenuModule',
        'MyDesktop.BogusModule',
        'MyDesktop.DesignerModule',
        'MyDesktop.JQueryMiniUIModule',
        'MyDesktop.ProjectManagerModule',
        'MyDesktop.PallasChartModule',
        'MyDesktop.PallasJbpmModule',
        'MyDesktop.PallasJmsModule',
        'MyDesktop.DatabaseManagerModule',
        'MyDesktop.OrgnizationManagerModule',
        'MyDesktop.Settings',
        'MyDesktop.ServerManagerModule',
        'MyDesktop.LogManagerModule',
        'MyDesktop.PallasliDesignerModule'
    ],
    
    shortcuts:[],//桌面快捷方式
    quickStart: [],//快速启动栏
    trayItems: [], //托盘
    modules : [], //所有程序
    startConfig:[],//开始系统配置
    contextMenuConfigs:[],//右键菜单配置
    taskbarCls: 'ux-desktop-default',
    
    init: function() {
        // custom logic before getXYZ methods get called...

        this.callParent();

        // now ready...
    },

    getModules : function(){
        return this.modules;
    },

    getDesktopConfig: function () {
        var me = this, ret = me.callParent(),contextMenuConfigs=me.contextMenuConfigs;
        var contextMenuItemsMap={
        		ChangeSettings:{ 
        			text: 'Change Settings',
        			handler: me.onSettings, 
        			scope: this 
        		}
    	    };
        var contextMenuItems=[];
        for(var i=0;i<contextMenuConfigs.length;i++){
        	contextMenuItems.push(contextMenuItemsMap[contextMenuConfigs[i]]);
        }
        return Ext.apply(ret, {
            cls: me.taskbarCls,
            contextMenuItems: contextMenuItems,
            shortcuts: Ext.create('Ext.data.Store', {
                model: 'Ext.ux.desktop.ShortcutModel',
                data: me.shortcuts
            }),
            wallpaper: 'scripts/desktop/mydesktop/wallpapers/Blue-Sencha.jpg',
            wallpaperStretch: false
        });
    },

    getStartConfig : function() {
        var me = this, ret = me.callParent(),startConfig=me.startConfig;

        var startConfigMap={
        	Settings:{
	            text:'Settings',
	            iconCls:'settings',
	            handler: me.onSettings,
	            scope: me
	        },
	        Logout:{
	            text:'Logout',
	            iconCls:'logout',
	            handler: me.onLogout,
	            scope: me
	        }
	    };
        var startItems=[];
        for(var i=0;i<startConfig.length;i++){
        	startItems.push(startConfigMap[startConfig[i]]);
        	startItems.push('-');
        }
        
        return Ext.apply(ret, {
            title: '开始菜单',
            iconCls: 'user',
            height: 300,
            toolConfig: {
                width: 100,
                items: startItems
            }
        });
    },

    //任务栏
    getTaskbarConfig: function () {
        var  me = this,ret = this.callParent();

        return Ext.apply(ret, {
        	//快速启动栏
            quickStart:me.quickStart,
            //托盘
            trayItems: me.trayItems
        });
    },

    onLogout: function () {
        Ext.Msg.confirm('Logout', 'Are you sure you want to logout?');
    },

    onSettings: function () {
        var dlg = new MyDesktop.Settings({
            desktop: this.desktop
        });
        dlg.show();
    }
});

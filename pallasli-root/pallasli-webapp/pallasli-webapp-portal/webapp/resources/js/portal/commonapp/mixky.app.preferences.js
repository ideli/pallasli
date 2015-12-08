
Ext.namespace("Mixky.wasoft.lib");

Mixky.wasoft.lib.Preferences = function(){
	var desktop = Mixky.wasoft.lib.PreferencesDesktop();
	var background = Mixky.wasoft.lib.PreferencesBackground();
	var shortcuts = Mixky.wasoft.lib.PreferencesShortcuts();
	var quickstarts = Mixky.wasoft.lib.PreferencesQuickStarts();
	var subjects = Mixky.wasoft.lib.PreferencesSubjects();
    win = new Ext.Window({
        title : '应用参数定制',
        width :500,
        height :500,
        iconCls : 'icon-portal-preference',
        shim : false,
        maximizable : false,
        minimizable : false,
        animCollapse :false,
        resizable :false,
        constrain : true,
        modal : true,
		layout : 'fit',
        items : [{
        	xtype : 'tabpanel',
            activeTab : 0,
            border :false,
            defaults: {
        		autoScroll:true
        	},
            items : [
                desktop,
                background,
              //  shortcuts,
               // quickstarts,
                subjects
            ]
        }],
        buttons : [{
        	text : '保存设置',
            iconCls : 'icon-common-save',
            handler : function(){
        		Mixky.wasoft.lib.actions.SavePreferences.execute();
    		}
        },{
        	text : '关闭',
            iconCls : 'icon-common-cancel',
            handler : function(){
    			win.close();
    		}
        }]
    });
    win.show();
};
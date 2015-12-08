
Ext.namespace("Mixky.wasoft.lib");

Mixky.wasoft.lib.PreferencesDesktop = function(){
	
	var uiWindow = new Ext.form.Radio({
		boxLabel : 'Window模式',
		anchor : '60%',
		checked : MixkyApp.userConfig.uimode == 'window',
		name : 'uimodel'
	});
	uiWindow.on('check', function(checkbox, checked){
		if(checked){
			MixkyApp.userConfig.uimode = 'window';
		}
	});
	var uiWebPage = new Ext.form.Radio({
		boxLabel : '桌面操作模式',
		anchor : '40%',
		checked : MixkyApp.userConfig.uimode == 'webpage',
		name : 'uimodel'
	});
	uiWebPage.on('check', function(checkbox, checked){
		if(checked){
			MixkyApp.userConfig.uimode = 'webpage';
		}
	});
	var desktopColumns2 = new Ext.form.Radio({
		boxLabel : '2栏',
		checked : MixkyApp.userConfig.columns == 2,
		name : 'columns'
	});
	desktopColumns2.on('check', function(checkbox, checked){
		if(checked){
			MixkyApp.userConfig.columns = 2;
		}
	});
	var desktopColumns3 = new Ext.form.Radio({
		boxLabel : '3栏',
		checked : MixkyApp.userConfig.columns != 2 && MixkyApp.userConfig.columns != 4,
		name : 'columns'
	});
	desktopColumns3.on('check', function(checkbox, checked){
		if(checked){
			MixkyApp.userConfig.columns = 3;
		}
	});
	var desktopColumns4 = new Ext.form.Radio({
		boxLabel : '4栏',
		checked : MixkyApp.userConfig.columns == 4,
		name : 'columns'
	});
	desktopColumns4.on('check', function(checkbox, checked){
		if(checked){
			MixkyApp.userConfig.columns = 4;
		}
	});
	
	var store = new Ext.data.DirectStore({
		directFn : DesktopDirect.getDesktopStyles,
		paramOrder:[],
		root : 'results',
		totalProperty : 'totals',
		idProperty : 'path',
		fields:[
		    {name:'id', mapping:'id'},
		    {name:'thumbnail', mapping:'thumbnail'},
		    {name:'path', mapping:'path'}
		]
	});
	var tpl = new Ext.XTemplate(
		'<tpl for=".">',
			'<div class="pref-view-thumb-wrap" id="{id}">',
				'<div class="pref-view-thumb"><img src="{thumbnail}" title="{id}" /></div>',
			'<span>{shortName}</span></div>',
		'</tpl>',
		'<div class="x-clear"></div>'
	);
	var view = new Ext.DataView({
		autoHeight:true,
    	anchor : '-20',
		emptyText : '没有可供选择的样式',
		itemSelector :'div.pref-view-thumb-wrap',
		loadingText : 'loading...',
		singleSelect : true,
		overClass : 'x-view-over',
		prepareData : function(data){
			data.shortName = Ext.util.Format.ellipsis(data.id, 17);
			return data;
		},
		store : store,
		tpl : tpl
	});
	view.on('selectionchange', function(v, sel){
		if(sel.length > 0){
			var record = v.getRecord(sel[0]);
			if(record && record.get('path')){
				MixkyApp.setTheme(record.get('path'));
			}
		}
	});
	store.on('load', function(s, records){
		if(records){
			var t = MixkyApp.userConfig.theme;
			if(t){
				view.select(t);
			}
		}				
	}, this);
	var panel = new Ext.Panel({
		title : '界面设置',
        iconCls : 'icon-sys-desktopui',
        padding : '5px',
        items : [{
        	xtype : 'fieldset',
        	title : '设置桌面栏目最大列数（需刷新门户页面）',
        	items : [{
        		layout:'column',
        		border : false,
        		items : [{
        			columnWidth:.5,
            		border : false,
        			layout: 'form',
        			items : {
                		hideLabel : true,
                		xtype : 'radiogroup',
                        items : [desktopColumns2, desktopColumns3, desktopColumns4]
                	}
        		}]
        	}]
        }, {
        	xtype : 'panel',
			autoScroll : true,
        	height : 280,
        	layout : 'anchor',
			bodyStyle : 'padding:10px',
			border : true,
        	items : view
        }]
	});
	store.load();
	return panel;
};

//=================================================================
//	�ļ�����mixky.awsoft.framework.js
//=================================================================
Mixky.awsoft.Framework = function(app){
	this.app = app;

    // 应用程序标题区域
    this.titlebar = new Mixky.awsoft.Titlebar({
    	region : 'north',
        split : true,
        height : 39,
        minSize : 39,
        maxSize : 39,
        hideCollapseTool : true,
        collapsible: true,
        collapseMode:'mini'
    });
    // 应用程序操作条
    this.toolbar = new Mixky.awsoft.Toolbar({});
    // 应用程序导航区域
    this.outline = new Mixky.awsoft.Outline({
    	region : 'west',
        split : true,
        width : 250,
        minSize : 150,
        maxSize : 500,
        hideCollapseTool : true,
        collapsible: true,
        collapseMode:'mini'
    	
    });
    // 应用程序工作区域
    this.workspace = new Mixky.awsoft.Workspace({
    	region : 'center'
    });
	// 创建视图架构
    var view = new Ext.Viewport({
    	layout :'border',
    	items : [ this.titlebar, new Ext.Panel({
    		region : 'center',
    		border : false,
    		tbar : this.toolbar,
        	layout : 'border',
        	items : [this.outline, this.workspace]
    	})]
	});
};

Mixky.awsoft.Framework.prototype = {
	// 设置消息窗口显示位置
	getAnimateTarget : function(){
		return document;
	},
    closeEditor : function(key){
		return this.workspace.removeObject(key);
    },
    
    openObject : function(oid){
		this.outline.selectObject(oid);
		var cmp = this.workspace.openEditor(oid);
    },
    
    activateObject : function(oid){
    	this.outline.selectObject(oid);
    	this.workspace.selectObject(oid);
    },

    deleteObject : function(key){
    	this.outline.removeObject(key);
    	this.workspace.removeObject(key);
    }
}
//=================================================================
//	�ļ�����mixky.awsoft.outline.js
//=================================================================
Mixky.awsoft.Outline = function(cfg){
	Mixky.awsoft.Outline.superclass.constructor.call(this, cfg);
	// 节点右键菜单
	var menu = new Ext.menu.Menu({
		ignoreParentClicks : true,
		items:[
		    Mixky.awsoft.lib.Actions.Open,'-',
		    Mixky.awsoft.lib.Actions.Add,
		    Mixky.awsoft.lib.Actions.Rename,
		    Mixky.awsoft.lib.Actions.Delete,
		    Mixky.awsoft.lib.Actions.Copy,
		    Mixky.awsoft.lib.Actions.Paste,'-',
		  //  Mixky.awsoft.lib.Actions.Import,
		   // Mixky.awsoft.lib.Actions.Export,
		  //  Mixky.awsoft.lib.Actions.ViewJSON,'-',
		    Mixky.awsoft.lib.Actions.Refresh,'-'
		  //  Mixky.awsoft.lib.Actions.Extends
		]
	});
	this.on('contextmenu', function(node, e){
		this.getSelectionModel().select(node);
		menu.showAt(e.getXY());
	});
	this.on('dblclick', function(n, e){
		if(n.isLeaf()){
			Mixky.awsoft.lib.Actions.Open.execute();
		}
	});
	this.on('click', function(p){
		Mixky.awsoft.lib.Context.activatedObject = this.getObjectIdentity();
	});
	this.getSelectionModel().on('selectionchange', function(sm, node){
		if(!node){
			return;
		}
		var oid = node.getOwnerTree().getObjectIdentity(node);
		if(oid){
			Mixky.awsoft.lib.Context.activateObject(oid, node.getOwnerTree());
		}
	});
};

Ext.extend( Mixky.awsoft.Outline, Ext.tree.TreePanel, {
    margins : '0 0 5 0',
    cmargins :'0 0 0 0',
	autoScroll : true,
	root: {
        text: "功能导航大纲",
        id : 'root',
        key : 'root',
        mclass : 'root'
    },
    loader: new Ext.tree.TreeLoader({
    	paramOrder:['mclass'],
    	listeners:{
    		'beforeload':function(loader, node){
    			Ext.apply(this.baseParams,{'mclass':node.attributes['mclass']});
    		},
    		'load':function(loader, node){
    			node.getOwnerTree().selectObject(Mixky.awsoft.lib.Context.activatedObject);
    		}
    	},
        directFn: OutlineDirect.getOutline
    }),
    // 重命名
    renameObject : function(srcKey, dstKey){
    	var node = this.getNodeById(srcKey);
    	if(node){
    		node.setId(dstKey);
    		node.attributes.key = dstKey;
        	this.refresh(node);
    		return true;
    	}
    	return false;
    },
    // 刷新父节点
    refreshParent : function(node){
		if (!node) {
			node = this.getSelectionModel().getSelectedNode();
		}
		if(!node){
			return;
		}
		node = node.parentNode;
		node.attributes.children = undefined;
		node.reload();
    },
    // 刷新节点下级
	refresh : function(node) {
		if (!node) {
			node = this.getSelectionModel().getSelectedNode();
		}
		if (!node || node.isLeaf()) {
			return false;
		}
		node.attributes.children = undefined;
		node.reload();
		return true;
	},
    // 获得节点对象标识{key,mclass}
    getObjectIdentity:function(node){
    	if(!node){
    		node = this.getSelectionModel().getSelectedNode();
    	}
    	if(!node){
    		return;
    	}
    	return {id:node.id, key:node.attributes.key, mclass:node.attributes.mclass};
    },
    // 选中对象
    selectObject : function(oid){
    	if(!oid){
    		return;
    	}
    	var node = this.getNodeById(oid.id);
    	if(!node){
    		node = this.getNodeById(oid.key);
    	}
    	if(node){
    		node.select();
    		this.expandPath(node.getPath());
    	}
    },
    // 移除对象
    removeObject : function(key){
    	var node = this.getNodeById(key);
    	if(node){
    		// 获得下一节点
    		var nextNode = node.parentNode;
    		if(!node.isLast()){
    			nextNode = node.nextSibling;
    		}else if(!node.isFirst()){
    			nextNode = node.previousSibling;
    		}
        	// 选中下一节点
        	nextNode.select();
    		this.expandPath(nextNode.getPath());
        	node.remove();
    	}
    }
});
//=================================================================
//	�ļ�����mixky.awsoft.titlebar.js
//=================================================================
Mixky.awsoft.Titlebar = function(cfg){
	Mixky.awsoft.Titlebar.superclass.constructor.call(this, cfg);
}

Ext.extend(Mixky.awsoft.Titlebar, Ext.Panel, {
    margins : '0 0 0 0',
    cmargins :'0 0 0 0',
    border : false,
    bodyCssClass : 'mixky-administrator-title',
	data : {
		title : ApplicationInfo.title, 
		userid : ApplicationInfo.userid,
		username : ApplicationInfo.username,
		departmentname : ApplicationInfo.departmentname
	},
	tpl : [
		'<table class="mixky-titlebar" height="100%">',
			'<tr valign=middle>',
				//'<td width=100% class="mixky-appname">{title} — 管理工具</td>',
			    '<td width=100%>{title} — 应用管理</td>',
				'<td nowrap class="mixky-userinfo">{departmentname}　{username}</td>',
			'</tr>',
		'</table>'
	]
});
//=================================================================
//	�ļ�����mixky.awsoft.toolbar.js
//=================================================================
Mixky.awsoft.Toolbar = function(cfg){
	if(!cfg){cfg = {};}
	Ext.apply(cfg, {
		items:[{
			xtype:'buttongroup',
			//title:'对象操作',
			columns:4,
			/*defaults: {
	            scale: 'small',
	            iconCls: 'icon-administrator-button-default'
	        },*/
	        items:[
		      //  Mixky.awsoft.lib.Actions.Add,
		       // Mixky.awsoft.lib.Actions.Copy,
		      //  Mixky.awsoft.lib.Actions.Delete,
		      //  Mixky.awsoft.lib.Actions.Paste,
		      //  Mixky.awsoft.lib.Actions.Save,
		        Mixky.awsoft.lib.Actions.Open,
		        Mixky.awsoft.lib.Actions.Refresh
	        ]
		},'->',{
			xtype:'buttongroup',
			columns:5,
			/*defaults: {
	            iconCls: 'icon-administrator-button-default'
	    	},*/
	        items:[
		       Mixky.awsoft.lib.Actions.SyncToApplication,
		     //  Mixky.awsoft.lib.Actions.SyncToIMServer,
		       Mixky.awsoft.lib.Actions.BuildFiles
	        ]
		}]
	})
	Mixky.awsoft.Toolbar.superclass.constructor.call(this, cfg);
}

Ext.extend(Mixky.awsoft.Toolbar, Ext.Toolbar, {});
//=================================================================
//	�ļ�����mixky.awsoft.workspace.js
//=================================================================
Mixky.awsoft.Workspace = function(cfg){
	if(!cfg){cfg = {};}
	Ext.apply(cfg, {
		items : [{
			title : '主页标签',
			key : 'main',
			mclass : 'main',
			closable : false,
			iconCls : 'icon-administrator-home'
		}],
		listeners : {
			'tabchange' : function(tabs, tab){
				if(tab.initialConfig.mclass == 'main'){
					return;
				}
				var oid = {id:tab.id, key:tab.key, mclass:tab.mclass};
				Mixky.awsoft.lib.Context.activateObject(oid);
			}
		}
	})
	Mixky.awsoft.Workspace.superclass.constructor.call(this, cfg);
}
Ext.extend(Mixky.awsoft.Workspace, Ext.TabPanel, {
	activeTab : 0,
    margins:'0 0 5 0',
    enableTabScroll:true,
    defaults: {
		autoScroll:true,
		closable : true
	},
    plugins: new Ext.ux.TabCloseMenu(),
	getActivatedObject : function(){
		var p = this.getActiveTab();
		if(p){
			return {id:p.id, key:p.key, mclass:p.mclass};
		}
	},
	// 选中对象
	selectObject : function(oid){
		if(!oid){
			return;
		}
		var aoid = this.getActivatedObject();
		if(aoid.key == oid.key){
			return;
		}
		var p = this.getItem('p-' + oid.key);
		if(!p){
			return;
		}
		this.activate(p);
	},
	// 处理重命名
	renameObject : function(oldKey, newKey){
		var result = this.removeObject(oldKey);
		var cmps = this.findBy(function(cmp){
			return cmp.getId().indexOf('p-' + oldKey) >= 0;
		});
		for(var i=0;i<cmps.length;i++){
			this.remove(cmps[i]);
		}
		return result != false;
	},
	// 移除对象
	removeObject : function(key){
		return Ext.isDefined(this.remove('p-' + key));
	},
	savePanel : function(panel, needSaveNext){
		if(panel != null){
			if(!panel.save){
				this.savePanelOver(panel, needSaveNext);
			}else{
				panel.save(needSaveNext);
			}
		}
	},
	savePanelOver : function(panel, needSaveNext){
		if(needSaveNext){
			var next = panel.nextSibling();
			if(next != null){
				this.savePanel(next, needSaveNext);
			}else{
				var p = MixkyApp.framework.workspace.getActiveTab();
				var key = p.getId().substr(2, p.getId().length - 2);
				DesignObjectDirect.forceSaveObject(key, function(result, e){});
			}
		}else{
			var p = MixkyApp.framework.workspace.getActiveTab();
			var key = p.getId().substr(2, p.getId().length - 2);
			DesignObjectDirect.forceSaveObject(key, function(result, e){});
		}
	},
	// 打开对象编辑窗口
	openEditor : function(oid){
		// 获得对象设置
		var module = Mixky.awsoft.lib.Class.getModule(oid.mclass);
		if(!module || !module.editors || module.editors.length == 0){
			return;
		}
		// 打开窗口
		var editor = Ext.getCmp('p-' + oid.key);
		if(!editor){
			var title = module.text;
			if(!module.isSingle){
				title = module.text + '[' + Ext.util.Format.ellipsis(oid.key, 10) + "]";
			}
			var config = {
				id : 'p-' + oid.key,
				key : oid.key,
				mclass : oid.mclass,
		        margins:'0 0 10 0',
				iconCls : module.iconCls,
				title : title,
				tabTip : oid.key,
				buttonAlign : 'left',
				fbar : []
			};
			if(!module.withoutSave){
				config.fbar.push({
					text : '保存',
					scale: 'medium',
					iconCls : 'icon-administrator-save',
					handler : function(){
					 	Mixky.awsoft.lib.Actions.Save.execute();
					}
				},{
					text : '应用',
					scale: 'medium',
					iconCls : 'icon-administrator-apply',
					handler : function(){
						Mixky.awsoft.lib.Actions.Apply.execute();
					}
				});
			
				config.fbar.push('->');
				if(module.extendsMenu){
					for(var i=0;i<module.extendsMenu.length;i++){
						var btn = module.extendsMenu[i];
						Ext.apply(btn, {scale: 'medium'});
						Ext.applyIf(btn, {iconCls : 'icon-administrator-extbutton'});
						config.fbar.push(btn);
					}
				}
				config.fbar.push({
					text : '刷新',
					scale: 'medium',
					iconcls : 'icon-administrator-refresh',
					scope : this,
					handler : function(){
						this.refreshEditor(oid);
					}
				},{
					text : '关闭',
					scale: 'medium',
					iconcls : 'icon-administrator-close',
					scope : this,
					handler : function(){
						this.remove('p-' + oid.key);
					}
				});
			}
			if(module.editors.length == 1){
				var panel = new Ext.Panel({
					autoLoad : {
						url:module.editors[0],
						scripts:true,
						border:false,
						params:{
							key:oid.key,
							mclass:module.name
						}
					},
					border:false,
					layout:'fit'
				});
				Ext.apply(panel.autoLoad.params, {id:panel.getId()});
				Ext.apply(config, {
					layout:'fit',
					border:false,
					items : panel,
					footerCssClass:'x-tab-panel-footer'
				});
			}else{
				var items = [];
				for(var i=0;i<module.editors.length;i++){
					var p = new Ext.Panel({
						autoLoad : {
							url:module.editors[i],
							scripts:true,
							params:{
								key:oid.key,
								mclass:module.name
							}
						},
						title : '窗口[' + i + ']...',
						layout:'fit'
					});
					Ext.apply(p.autoLoad.params, {id:p.getId()});
					items.push(p);
				}
				Ext.apply(config, {
					xtype : 'tabpanel',
					deferredRender:false,
					activeTab : 0,
					items : items
				});
			}
			editor = this.add(config);
		}
		this.activate(editor);
		return editor;
	},
	// 刷新编辑器
	refreshEditor : function(oid){
		var editor = Ext.getCmp('p-' + oid.key);
		if(!editor){return}
		var panel;
		if(editor.items.length > 1){
			panel = editor.getActiveTab();
		}else{
			panel = editor.items.get(0);
		}
		if(!panel.refresh){
			return;
		}
		panel.refresh();
	}
});
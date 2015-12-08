
Ext.namespace("Mixky.wasoft.module");

Mixky.wasoft.module.getModuleViewPanelId = function(appkey, viewkey){
	return 'v-' + appkey + '.' + viewkey;
};

/*
 * 系统模块标签
 */
Mixky.wasoft.Module = function(cfg){
	var appkey = cfg.applicationkey;
	var modulekey = cfg.modulekey;
	var module = Mixky.wasoft.lib.cache.getAppModule(appkey, modulekey);
	var modulePanel = this;
	var tree = new Ext.tree.TreePanel({
		region : 'west',
        width: 200,
        minSize: 150,
        maxSize: 400,
		split : true,
        collapsible: true,
        enableDrop: true,
        ddGroup: 'grid2tree',
        collapseMode:'mini',
        rootVisible:true,
        autoScroll:true,
        animCollapse:false,
        iconCls : 'icon-portal-modulemenu',
        title : '模块菜单',
        tools : [{
        	id : 'refresh',
        	qtip : '刷新选中菜单的下级菜单',
        	handler : function(){
        		tree.refresh();
	        }
        },{
        	id : 'maximize',
        	qtip : '打开选中节点',
        	handler : function(){
	        	tree.openNode();
	        }
        }],
        cmenu : new Ext.menu.Menu({
        	items : [{
        		text : '刷新',
        		iconCls : 'icon-common-refresh',
        		handler : function(){
        			tree.refresh();
        		}
        	}, {
        		text : '打开',
        		iconCls : 'icon-common-open',
        		handler : function(){
        			tree.openNode();
        		}
        	}]
        }),
        listeners	:{
	        'afterrender' : function(p){
	        	// p.getRootNode().expand();
	        	if('archive'==appkey){
	        		tree.expandAll();
	        	}else{
	        		p.getRootNode().expand();
	        	}
			},
				'render' : function(p){
		    	/*p.el.on('mouseleave',function(e,t,o){
		    		p.collapse();
		    	});
		    	
		    	p.getLayoutTarget().on('mouseenter',function(e,t,o){
		            p.expand();
		    	});
		    	p.getLayoutTarget().on('mouseover',function(e,t,o){
		            p.expand();
		    	});
		    	document.getElementById("ptree-xcollapsed").onmouseover = function() {
		            p.expand();
		        }*/
			},
	        'contextmenu' : function(node, e){
	        	node.select();
	            var c = node.getOwnerTree().cmenu;
	            c.contextNode = node;
	            c.showAt(e.getXY());
			},
			'dblclick' : function(node, e){
				//if(node.attributes.type == 'modulemenu'){
					Mixky.wasoft.lib.actions.openMenu(appkey, node.attributes.key, e);
				//}
			}
	    },
        root: {
        	id : 'root',
        	text : module.title,
        	iconCls : module.icon,
        	qtip : module.qtip,
        	type : 'root',
            params : {'modulekey' : module.key}
		},
        loader: new Ext.tree.TreeLoader({
            directFn : Ext.emptyFn,
        	paramOrder : ['params'],
        	baseParams : {params : {}},
        	listeners : {
        		'beforeload':function(loader, node){
        			loader.baseParams.params = tree.getNodeParams(node);
        			loader.baseParams.params.type = node.attributes.type;
        			
					var app = Mixky.wasoft.cache.Applications[appkey];
					loader.directFn = eval(app.keyPrefix + 'AppDirect.getModuleOutline');
					if(typeof(loader.directFn) != 'function'){
						alert(app.keyPrefix + 'AppDirect.getModuleOutline is not a function');
						return false;
					}
        		}
        	}
		}),
		selModel : new Ext.tree.DefaultSelectionModel({
			listeners	: {
				'selectionchange' :function(s, n){
					tree.openNode(n);
				}
			}
		}),
		getNodeParams : function(node){
			var params = {};
			if(node.parentNode){
				Ext.apply(params, this.getNodeParams(node.parentNode));
			}
			Ext.apply(params, node.attributes.params);
			return params;
		},
		refresh : function(node){
			if(!node){
				node = this.getSelectionModel().getSelectedNode();
			}
			if(!node || node.isLeaf()){
				return;
			}
			node.reload();
		},
		refreshParentNode : function(node){
			if(!node){
				node = this.getSelectionModel().getSelectedNode();
			}
			if(!node){
				return;
			}
			this.refresh(node.parentNode);
		},
		openNode : function(node){
			if(!node){
				node = this.getSelectionModel().getSelectedNode();
			}
			if(!node){
				return;
			}
			if(node.attributes.isFunctionMenu){
				Mixky.wasoft.lib.actions.openMenu(appkey, node.attributes.key);
				return;
			}
			var params = this.getNodeParams(node);
			if(Ext.isDefined(params.viewurl)){
				var id = Mixky.wasoft.module.getModuleViewPanelId(appkey, params.modulemenu);
				var urlPanel = modulePanel.openUrl(id, params.viewurl);
				if(urlPanel){
					if(urlPanel.refresh){
					    if(node.attributes.type == 'groupitem'){
							urlPanel.refresh(params);	
						}
					    else{
					    	if(node.firstChild){
								if(node.firstChild.attributes.type  == 'groupitem'){
									urlPanel.refresh(params);
								}
							}
					    }
					}else{
						urlPanel.initParams = params;
					}
				}
			}else if(Ext.isDefined(params.viewarchive)){
				// var id = Mixky.wasoft.module.getModuleViewPanelId(appkey, params.modulemenu);
				var viewPanel = modulePanel.openJspUrl(params.id,params.viewarchive,params,cfg);
				if(viewPanel){
					if(viewPanel.refresh){
						if (node.attributes.type == 'groupitem') {
							viewPanel.refresh(params);
						}
					}else{
						viewPanel.initParams = params;
					}
				}
			}else if(Ext.isDefined(params.viewkey)){
				var viewPanel = modulePanel.openView(params.viewkey);
				if(viewPanel){
					if(viewPanel.refresh){
						if (node.attributes.type == 'groupitem') {
							viewPanel.refresh(params);
						}
						else{
							if(node.firstChild){
								if(node.firstChild.attributes.type  == 'groupitem'){
									viewPanel.refresh(params);
								}
							}
						}
					}else{
						viewPanel.initParams = params;
					}
				}
			}else{
				node.expand();
			}
		}
	});
	var container = new Ext.TabPanel({
		region:'center',
        enableTabScroll:true,
        defaults: {
    		autoScroll:true,
    		closable : true
    	},
        plugins: new Ext.ux.TabCloseMenu()
	});

	container.on('tabchange', function(tabpanel, panel){
		if(panel){
			Mixky.wasoft.lib.Context.activeViewKey = panel.viewkey;
		}else{
			Mixky.wasoft.lib.Context.activeViewKey = '';
		}
	});
	var config = {
		layout : 'border',
		shim:false,
		animCollapse:false,
		constrainHeader:true,
		minimizable:true,
		maximizable:true,
		border:false,
		items : [tree, container]
	};
	
	this.refresh = function(rtree, rtab){
		if(rtree){
			tree.refreshParentNode();
		}
		if(rtab){
			var tab = container.getActiveTab();
			if(tab && Ext.isDefined(tab.refresh)){
				tab.refresh();
			}
		}
	};
	
	this.documentUpdateRefresh = function(activate){
		tree.refreshParentNode();
		if(activate){
			var tab = container.getActiveTab();
			if(tab && Ext.isDefined(tab.refresh)){
				tab.refresh();
			}
		}else{
			container.items.each(function(tab){
				if(Ext.isDefined(tab.refresh)){
					tab.refresh();
				}
			});
		}
	};
	
	this.moduleTree = tree;
	this.moduleContainer = container;
	
	Mixky.wasoft.Module.superclass.constructor.call(this, Ext.apply(config, cfg));
};

Ext.extend(Mixky.wasoft.Module, Ext.Panel, {
	closable : true,
	// Application Key
	applicationkey : '',
	// Module Key
	modulekey : '',
	// 获得当前视图
	getCurrentView : function(){
		var view = this.moduleContainer.getActiveTab();
		if(view && view.isModuleView){
			if(view.getCurrentView){
				var stbview = view.getCurrentView();
				if(stbview){
					return stbview;
				}else{
					return view;
				}
			}else{
				return view;
			}
		}
	},
	getViewPanel : function(viewkey){
		var appkey = this.applicationkey;
		var panels = this.moduleContainer.findBy(function(p, c){
			if(p.isModuleView && p.viewkey == viewkey){
				return true;
			}
		});
		var panel;
		if(panels.length > 0){
			panel = panels[0];
		}
		return panel;
	},
	// 打开视图
	openView : function(viewkey){
		var appkey = this.applicationkey;
		var panel = this.getViewPanel(viewkey);
		/*
		if(panels.length > 0){
			panel = panels[0];
		}
		var panels = this.moduleContainer.findBy(function(p, c){
			if(p.isModuleView && p.viewkey == viewkey){
				return true;
			}
		});
		//var id = Mixky.wasoft.module.getModuleViewPanelId(appkey, viewkey);
		//var panel = this.moduleContainer.getItem(id);
		*/
		if(!panel){
			var view = Mixky.wasoft.lib.cache.getAppModuleView(appkey, viewkey);
			if(!view){
				return;
			}else{
				var app = Mixky.wasoft.cache.Applications[appkey];
				if(!Ext.isDefined(app)){
					alert('未定义' + o.applicationKey + '应用');
					return;
				}
				panel = new Ext.Panel({
					title : view.title,
					iconCls : view.icon,
					layout : 'fit',
					autoLoad : {}
				});
				/*panel = MixkyApp.desktop.workspace.add({
					title : view.title,
					iconCls : view.icon,
					layout : 'fit',
					border : false,
					closable : true,
					autoLoad : {}
				});*/
				panel.autoLoad = {
					url : app.url + '/' + 'view.do',
					params : {panelid:panel.getId(), viewkey : view.key, appkey:appkey},
					loadScripts : true,
					scripts	: true
				};
				panel.isModuleView = true;
				panel.module = this;
				panel.viewkey = viewkey;
				panel.initParams = view.params;
				//MixkyApp.desktop.workspace.add(panel);
				this.moduleContainer.add(panel);
			}
		}
		//MixkyApp.desktop.workspace.activate(panel);
		this.moduleContainer.activate(panel);
		return panel;
	},
	openUrl : function(id, url, cfg){
		var appkey = this.applicationkey;
		var app = Mixky.wasoft.cache.Applications[appkey];
		var panel = this.moduleContainer.getItem(id);
		if(!panel){
			panel = new Ext.Panel(Ext.apply({
				id : id,
				layout : 'fit',
				autoLoad : {
					url : app.url + '/' + url,
					params : {panelid : id, appkey:appkey},
					loadScripts : true,
					scripts	: true
				}
			}, cfg));
			panel.module = this;
			this.moduleContainer.add(panel);
		}
		this.moduleContainer.activate(panel);
		return panel;		
	},
	openJspUrl : function(id, url, params, cfg){
		var appkey = this.applicationkey;
		var app = Mixky.wasoft.cache.Applications[appkey];
		var panel = this.moduleContainer.getItem(id);
		if(!panel){
			panel = new Ext.Panel(Ext.apply({
				id : id,
				layout : 'fit',
				autoLoad : {
					url : app.url + '/' + 'jsppage.do',
					params : Ext.apply({url:url, panelid : id, appkey:appkey}, params),
					loadScripts : true,
					scripts	: true
				}
			}, cfg));
			panel.module = this;
			this.moduleContainer.add(panel);
		}
		this.moduleContainer.activate(panel);
		return panel;		
	}
});
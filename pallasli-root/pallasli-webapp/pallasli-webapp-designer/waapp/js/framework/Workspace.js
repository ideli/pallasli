Ext.define('Pallas.extDesigner.waapp.framework.Workspace', {
	extend : "Ext.tab.Panel",
	requires:["Pallas.extDesigner.waapp.utils.ActionOperate",
	          "Pallas.extDesigner.waapp.utils.ClassOperate",
	          'Pallas.extDesigner.waapp.lib.Context'],
	alias : [ "waapp.workspace" ],
	activeTab : 0,
	margins : '0 0 5 0',
	enableTabScroll : true,
	items : [    {
		title : '主页标签',
		key : 'main',
		mclass : 'main',
		closable : false,
		hidden:true,
		iconCls : 'icon-administrator-home'
	
	}   ],  
	listeners : {
		'tabchange' : function(tabs, tab) {
			if (tab.initialConfig.mclass == 'main') {
				return;
			}
			var oid = {
				id : tab.id,
				key : tab.key,
				mclass : tab.mclass
			};
			Pallas.extDesigner.waapp.lib.Context.activateObject(oid);
		}
	},
	defaults : {
		autoScroll : true,
		closable : true
	},
	// plugins : new Ext.ux.TabCloseMenu(),
	getActivatedObject : function() {
		var p = this.getActiveTab();
		if (p) {
			return {
				id : p.id,
				key : p.key,
				mclass : p.mclass
			};
		}
	},
	// 选中对象
	selectObject : function(oid) {
		if (!oid) {
			return;
		}
		var aoid = this.getActivatedObject();
		 
		if (aoid.key == oid.key&&aoid.mclass==oid.mclass) {
			return;
		}
		var p = this.getComponent('p-' + oid.key+oid.mclass);
		if (!p) {
			return;
		}
		this.setActiveTab(p);
	},
	// 处理重命名
	renameObject : function(oldKey, newKey) {
		var result = this.removeObject(oldKey);
		var cmps = this.findBy(function(cmp) {
			return cmp.getId().indexOf('p-' + oldKey) >= 0;
		});
		for (var i = 0; i < cmps.length; i++) {
			this.remove(cmps[i]);
		}
		return result != false;
	},
	// 移除对象
	removeObject : function(key) {
		return Ext.isDefined(this.remove('p-' + key));
	},
	savePanel : function(panel, needSaveNext) {
		if (panel != null) {
			if (!panel.save) {
				this.savePanelOver(panel, needSaveNext);
			} else {
				panel.save(needSaveNext);
			}
		}
	},
	savePanelOver : function(panel, needSaveNext) {
		if (needSaveNext) {
			var next = panel.nextSibling();
			if (next != null) {
				this.savePanel(next, needSaveNext);
			} else {
				var p = Pallas.extDesigner.waapp.utils.AllComponents.workspace.getActiveTab();
				var key = p.key;
				DesignObjectDirect.forceSaveObject(key, function(result, e) {
				});
			}
		} else {
			var p = Pallas.extDesigner.waapp.utils.AllComponents.workspace.getActiveTab();
			var key = p.key;
			DesignObjectDirect.forceSaveObject(key, function(result, e) {
			});
		}
	},
	// 打开对象编辑窗口
	openEditor : function(oid) {
		// 获得对象设置
		var module = Pallas.extDesigner.waapp.utils.ClassOperate.getModule(oid.mclass);
		if (!module || !module.editors || module.editors.length == 0) {
			return;
		}
		// 打开窗口
		var editor = Ext.getCmp('p-' + oid.key+ oid.mclass);
		if (!editor) {
			var title = module.text;
			if (!module.isSingle) {
				title = module.text + '['
						+ Ext.util.Format.ellipsis(oid.key, 10) + "]";
			}
			var config = {
				id : 'p-' + oid.key+ oid.mclass  ,
				key : oid.key,
				mclass : oid.mclass,
				margins : '0 0 10 0',
				iconCls : module.iconCls,
				title : title,
				tabTip : oid.key,
				buttonAlign : 'left',
				fbar : []
			};
			if (!module.withoutSave) {
				config.fbar.push({
					text : '保存',
					scale : 'medium',
					iconCls : 'icon-administrator-save',
					handler : function() {
						Pallas.extDesigner.waapp.utils.Actions.saveBtn.execute();
					}
				}, {
					text : '应用',
					scale : 'medium',
					iconCls : 'icon-administrator-apply',
					handler : function() {
						Pallas.extDesigner.waapp.utils.Actions.applyBtn.execute();
					}
				});
			}
			config.fbar.push('->');
			if (module.extendsMenu) {
				for (var i = 0; i < module.extendsMenu.length; i++) {
					var btn = module.extendsMenu[i];
					Ext.apply(btn, {
						scale : 'medium'
					});
					Ext.applyIf(btn, {
						iconCls : 'icon-administrator-extbutton'
					});
					config.fbar.push(btn);
				}
			}
			config.fbar.push({
				text : '刷新',
				scale : 'medium',
				iconcls : 'icon-administrator-refresh',
				scope : this,
				handler : function() {
					this.refreshEditor(oid);
				}
			}, {
				text : '关闭',
				scale : 'medium',
				iconcls : 'icon-administrator-close',
				scope : this,
				handler : function() {
					this.remove('p-' + oid.key+ oid.mclass);
				}
			});
			if (module.editors.length == 1) {
				var id=Ext.id();
				var panel=Ext.create('Ext.panel.Panel',Ext.apply({
					id:id,
					layout:"fit",
					border : false,
			        loader: { 
			            autoLoad:true,
						url : module.editors[0],
						scripts : true,
						border : false,
						params : {
							id:id,
							key : oid.key,
							mclass : module.name
						}
					}
				},{}));
				
			 
				Ext.apply(config, {
					layout : 'fit',
					border : false,
					items : panel,
					footerCssClass : 'x-tab-panel-footer'
				});
			} else {
				var items = [];
				for (var i = 0; i < module.editors.length; i++) {
					var id=Ext.id();
					var panel=Ext.create('Ext.panel.Panel',Ext.apply({
						id:id,
						layout:"fit",
				        loader: {
				            autoLoad:true,
							url : module.editors[i],
							scripts : true,
							params : {
								id:id,
								key : oid.key,
								mclass : module.name
							}
						},
						title : '窗口[' + i + ']...',
						layout : 'fit'
					},{}));
				 
					items.push(panel);
				}
				Ext.apply(config, {
					xtype : 'tabpanel',
					deferredRender : false,
					activeTab : 0,
					items : items
				});
			}
			
			editor = this.add(config);
		} 
		this.setActiveTab(editor);
		return editor;
	},
	// 刷新编辑器
	refreshEditor : function(oid) {
		var editor = Ext.getCmp('p-' + oid.key+ oid.mclass);
		if (!editor) {
			return
		}
		var panel;
		if (editor.items.length > 1) {
			panel = editor.getActiveTab();
		} else {
			panel = editor.items.get(0);
		}
		if (!panel.refresh) {
			return;
		}
		panel.refresh();
	},
	initComponent : function() {
		var me = this;
		me.callParent();
		Pallas.extDesigner.waapp.utils.AllComponents.workspace=me;
	}
});
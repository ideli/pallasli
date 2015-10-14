/**
 * 调用方式
 * 
 * Ext.Loader.setPath({ 'Pallas.design': 'scripts/design' });
 * 
 * Ext.require([ 'Pallas.design.DesignWindow' ]);
 */

Ext
		.define(
				'Pallas.design.designWin.DesignWindow',
				{
					mixins : {
						observable : 'Ext.util.Observable'
					},
					requires : [ 'Pallas.design.designWin.Components',
							'Pallas.design.designWin.ComponentSelectPanel',
							'Pallas.design.designWin.CompPropertiesPanel',
							'Pallas.design.designWin.PageCompTree' ],
					pLayout : "fit",
					pSyle : "",
					initComponents : null,
					designWindow : null,
					designPanel : null,
					width : 1200,
					height : 540,
					constructor : function(config) {
						var me = this;

						me.mixins.observable.constructor.call(this, config);

						if (Ext.isReady) {
							Ext.Function.defer(me.init, 10, me);
						} else {
							Ext.onReady(me.init, me);
						}
					},
					init : function() {
					},

					createWindow : function( ) {
						var me = this;
						var initComps = me.initComponents || [];

						Ext.define('PageComponent', {
							extend : 'Ext.data.Model',
							fields : [ {
								name : 'id',
								type : 'string'
							}, {
								name : 'text',
								type : 'string'
							}, {
								name : 'path',
								type : 'string'
							}, {
								name : 'attributes',
								type : 'json'
							} ]
						});

						var store = Ext
								.create(
										'Ext.data.TreeStore',
										{
											model : 'PageComponent',
											proxy : {
												type : 'ajax',
												url : 'data/design/pages/page1/layout.json'
											},
											reader : {
												type : 'json'
											},
											root : {
												text : 'root',
												id : '0',
											}
										});

						var panelComponentPropertiesPanel = Ext.create(
								'Pallas.design.designWin.CompPropertiesPanel', {
									title : '组件属性配置',
									region : "south"
								});

    		    		panelComponentPropertiesPanel.saveButton.on('click',function(){
    		    			initComps=[{xtype:'textfield',fieldLabel:'ddd'}];
    		    			var tmpPanel= Ext.create('Ext.panel.Panel', {
    							xtype : "panel",
    							region : "center",
    							layout : {
    								type : 'form'
    							},
    							autoScroll : true 
    						});  
    		    			tmpPanel.add(initComps);
    		    			 
    		    			me.designWindow.remove(me.designPanel,false);  
    		    			me.designWindow.add(tmpPanel); 
    		    			me.designWindow.doLayout();
    		    		});
						var panelComponentTreePanel = Ext.create(
								'Pallas.design.designWin.PageCompTree', {
									border : false,
									region : "center",
									rootVisible : false,
									viewConfig : {
										plugins : {
											ptype : 'treeviewdragdrop',
											appendOnly : false
										// true只能拖着带非叶节点上
										}
									},
									listeners:{
					    		    	itemclick:function(panel,record, item, index,  e,  eOpts){
					    		    		panelComponentPropertiesPanel.loadProperties("panel"); 
					    		    	}
					    		    },
									store : store
								});

						var componetPanel1 = Ext
								.create(
										'Pallas.design.designWin.ComponentSelectPanel',
										{
											title : '容器',
											items : Pallas.design.designWin.Components.containerItems

										});
						var componetPanel2 = Ext.create(
								'Pallas.design.designWin.ComponentSelectPanel', {
									title : '数据源',
									items : []
								});
						var componetPanel3 = Ext.create(
								'Pallas.design.designWin.ComponentSelectPanel', {
									title : '工具条',
									items : []
								});
						var componetPanel4 = Ext
								.create(
										'Pallas.design.designWin.ComponentSelectPanel',
										{
											title : '表单组件',
											items : Pallas.design.designWin.Components.formItems
										});

						var componentsPanel = Ext.create('Ext.panel.Panel', {
							title : '组件选择',
							region : "east",
							border : false,
							width : 200,
							// collapsible :true,
							tools : [ {
								type : 'expand',
								tooltip : 'Refresh form Data',
								// hidden:true,
								handler : function(event, toolEl, panel) {
									componetPanel1.expand();
									componetPanel2.expand();
									componetPanel3.expand();
									componetPanel4.expand();

								}
							}, {
								type : 'collapse',
								tooltip : 'Get Help',
								handler : function(event, toolEl, panel) {
									componetPanel1.collapse();
									componetPanel2.collapse();
									componetPanel3.collapse();
									componetPanel4.collapse();
								}
							}, {
								type : 'right',
								tooltip : 'Get Help',
								handler : function(event, toolEl, panel) {
									compPanel.collapse();
								}
							} ],
							items : [ componetPanel1, componetPanel2,
									componetPanel3, componetPanel4 ]
						});
						var navigationPanel = Ext.create('Ext.panel.Panel', {
							title : '面板元素',
							region : "west",
							layout : "border",
							border : false,
							split : false,
							collapsible : true,
							collapsed : false,
							width : 200,
							items : [ panelComponentTreePanel,
									panelComponentPropertiesPanel ]
						});

						me.designPanel = Ext.create('Ext.panel.Panel', {
							xtype : "panel",
							region : "center",
							layout : {
								type : 'column'
							},
							autoScroll : true,
							items : initComps
						});

						me.designWindow = new Ext.window.Window({
							width : me.width,
							height : me.height,
							layout : "border",
							animCollapse : false,
							constrainHeader : true,
							items : [ navigationPanel, componentsPanel,
									me.designPanel ]
						});
						me.designWindow.show();
						Ext.each(componetPanel1.items.items,function(comp) {
							var proxy = new Ext.dd.DragSource(comp.getEl(), {group : 'dd'});
							proxy.afterDragDrop = function(target, e, id) {
									var destComp = Ext.getCmp(id);
									var srcCompd = Ext.getCmp(proxy.id);
									if (!srcCompd.cType) {
										return;
									}
									var srcComp = Ext.create(srcCompd.cType,{
										layout : "column",
										autoDestroy : false,
										fieldLabel : srcCompd.cType,
										height : 300,
										columnWidth : 0.8
									});

									var insertedComp = destComp.add(srcComp);
									

									addNewPanelProxy(srcComp);
									new Ext.dd.DDTarget(srcComp.getEl(), 'sss');
									
									Ext.EventManager.on(insertedComp.getEl(),"dblclick",function() {
										var newPanelwin = new Pallas.design.designWin.DesignWindow({
											initComponents : insertedComp.items.items
										});
										var newwin = newPanelwin.createWindow();
										newwin.on("beforeclose",function() {
											if (newwin.items.items[2].items.items) {
												var len = newwin.items.items[2].items.items.length;
												for (var tmpi = 0; tmpi < len; tmpi++) {
													insertedComp.add(newwin.items.items[2].items.items[0]);
												}
												insertedComp.doLayout();
											}
										});
									});
									destComp.doLayout();
									me.designWindow.designPanel = insertedComp.items.items;
							};
						});
						Ext.each(componetPanel4.items.items,function(comp) {
							var proxy = new Ext.dd.DragSource(comp.getEl(), {group : 'dd'});
							proxy.afterDragDrop = function(target, e, id) {
								var destComp = Ext.getCmp(id);
								var srcCompd = Ext.getCmp(proxy.id);
								if (!srcCompd.cType) {
									return;
								}
								var srcComp = Ext.create(srcCompd.cType,{
									fieldLabel : srcCompd.cType,
									columnWidth : 0.3
								});
								destComp.add(srcComp);
								destComp.doLayout();
								me.designWindow.designPanel = destComp.items.items;
								addNewProxy(srcComp);
								new Ext.dd.DDTarget(srcComp.getEl(), 'dd');
							};
						});

						function addNewPanelProxy(srcComp) {
							var proxy2 = new Ext.dd.DragSource(srcComp.getEl(),{group : 'sss'});
							new Ext.dd.DDTarget(srcComp.getEl(), 'sss');
							proxy2.afterDragDrop = function(target2, e2, id2) {
								var destCompe = Ext.getCmp(id2);
								var srcCompe = Ext.getCmp(proxy2.id);
								for (var ie = 0, lene = destCompe.ownerCt.items.items.length; ie < lene; ie++) {
									if (destCompe.ownerCt.items.items[ie].id == destCompe.id) {
										destCompe.ownerCt.insert(ie, srcCompe);
										break;
									}
								}
								me.designPanel.doLayout();
							};
						}
						function addNewProxy(srcComp) {
							var proxy2 = new Ext.dd.DragSource(srcComp.getEl(),{group : 'eee'});
							new Ext.dd.DDTarget(srcComp.getEl(), 'eee');
							proxy2.afterDragDrop = function(target2, e2, id2) {
								var destCompe = Ext.getCmp(id2);
								var srcCompe = Ext.getCmp(proxy2.id);
								for (var ie = 0, lene = destCompe.ownerCt.items.items.length; ie < lene; ie++) {
									if (destCompe.ownerCt.items.items[ie].id == destCompe.id) {
										destCompe.ownerCt.insert(ie, srcCompe);
										break;
									}
								}
								me.designPanel.doLayout();
							};
						}

						new Ext.dd.DDTarget(me.designPanel.getEl(), 'dd');

						return me.designWindow;
					},

					statics : {}
				});

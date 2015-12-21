Ext
		.define(
				'Pallas.extDesigner.shape.iconbox.IconBox',
				{
					extend : 'Ext.AbstractComponent',
					alias : 'shape.iconbox',

					requires : [ 'Ext.util.MixedCollection',
							'Ext.layout.container.Auto', 'Ext.ZIndexManager' ],

					mixins : {
						queryable : 'Ext.Queryable'
					},

					/* End Definitions */

					renderTpl : '{%this.renderContainer(out,values)%}',

					suspendLayout : false,

					autoDestroy : true,
					detachOnRemove : true,
					isContainer : true,
					layoutCounter : 0,

					baseCls : Ext.baseCSSPrefix + 'container',

					defaultLayoutType : 'auto',

					// @private
					initComponent : function() {
						var me = this;

						me.callParent();

						me.getLayout();
						me.initItems();
					},

					// @private
					initItems : function() {
						var me = this, items = me.items;
						me.items = new Ext.util.AbstractMixedCollection(false,
								me.getComponentId);
						me.floatingItems = new Ext.util.MixedCollection(false,
								me.getComponentId);

						if (items) {
							if (!Ext.isArray(items)) {
								items = [ items ];
							}

							me.add(items);
						}
					},
					getFocusEl : function() {
						return this.getTargetEl();
					},

					finishRenderChildren : function() {
						this.callParent();

						var layout = this.getLayout();

						if (layout) {
							layout.finishRender();
						}
					},

					beforeRender : function() {
						var me = this, layout = me.getLayout(), targetCls;

						me.callParent();

						if (!layout.initialized) {
							layout.initLayout();
						}

						targetCls = layout.targetCls;

						if (targetCls) {
							me.applyTargetCls(targetCls);
						}
					},
					applyTargetCls : function(targetCls) {
						this.addCls(targetCls);
					},

					afterComponentLayout : function() {
						var floaters = this.floatingItems.items;
						var floaterCount = floaters.length, i, floater;

						this.callParent(arguments);

						for (i = 0; i < floaterCount; i++) {
							floater = floaters[i];
							if (!floater.rendered && floater.autoShow) {
								floater.show();
							}
						}
					},

					onPosition : function() {
						this.callParent(arguments);
						this.repositionFloatingItems();
					},

					onResize : function() {
						this.callParent(arguments);
						this.repositionFloatingItems();
					},

					repositionFloatingItems : function() {
						var floaters = this.floatingItems.items;
						var floaterCount = floaters.length, i, floater;

						for (i = 0; i < floaterCount; i++) {
							floater = floaters[i];
							if (floater.el && !floater.hidden) {
								floater.setPosition(floater.x, floater.y);
							}
						}
					},

					setupRenderTpl : function(renderTpl) {
						this.callParent(arguments);
						this.getLayout().setupRenderTpl(renderTpl);
					},

					// @private
					getDefaultContentTarget : function() {
						return this.el;
					},

					// @private
					getContentTarget : function() {
						return this.getLayout().getContentTarget();
					},

					// @private
					setLayout : function(layout) {
						var currentLayout = this.layout;

						if (currentLayout && currentLayout.isLayout
								&& currentLayout != layout) {
							currentLayout.setOwner(null);
						}

						this.layout = layout;
						layout.setOwner(this);
					},

					getLayout : function() {
						var me = this;
						if (!me.layout || !me.layout.isLayout) {
							me.setLayout(Ext.layout.Layout.create(me.layout,
									me.self.prototype.layout
											|| me.defaultLayoutType));
						}

						return me.layout;
					},

					doLayout : function() {
						this.updateLayout();
						return this;
					},

					afterLayout : function(layout) {
						var me = this;
						++me.layoutCounter;
						if (me.hasListeners.afterlayout) {
							me.fireEvent('afterlayout', me, layout);
						}
					},

					// @private
					applyDefaults : function(config) {
						var defaults = this.defaults;

						if (defaults) {
							if (Ext.isFunction(defaults)) {
								defaults = defaults.call(this, config);
							}

							if (Ext.isString(config)) {
								config = Ext.ComponentManager.get(config);
							}
							Ext.applyIf(config, defaults);
						}
						return config;
					},

					// @private
					lookupComponent : function(comp) {
						return (typeof comp == 'string') ? Ext.ComponentManager
								.get(comp) : Ext.ComponentManager.create(comp,
								this.defaultType);
					},

					// @private - used as the key lookup function for the items
					getComponentId : function(comp) {
						return comp.getItemId && comp.getItemId();
					},

					add : function() {
					},

					onAdd : Ext.emptyFn,

					onRemove : Ext.emptyFn,

					insert : function(index, comp) {
						var compIdx;
						if (comp && comp.isComponent) {
							compIdx = this.items.indexOf(comp);
							if (compIdx !== -1) {
								return this.move(compIdx, index);
							}
						}
						return this.add(index, comp);
					},

					move : function(fromIdx, toIdx) {
						var items = this.items, item;

						if (fromIdx.isComponent) {
							fromIdx = items.indexOf(fromIdx);
						}
						item = items.removeAt(fromIdx);
						if (item === false) {
							return false;
						}
						items.insert(toIdx, item);
						this.onMove(item, fromIdx, toIdx);
						this.updateLayout();
						return item;
					},

					onMove : Ext.emptyFn,

					onBeforeAdd : function(item) {
						if (item.ownerCt && item.ownerCt !== this) {
							item.ownerCt.remove(item, false);
						}
					},

					remove : function(comp, autoDestroy) {
					},

					// @private
					doRemove : function(component, doDestroy) {
					},

					detachComponent : function(component) {
						Ext.getDetachedBody().appendChild(component.getEl());
					},

					removeAll : function(autoDestroy) {
					},
					getRefItems : function(deep) {
					},

					cascade : function(fn, scope, origArgs) {
						var me = this, cs = me.items ? me.items.items : [], len = cs.length, i = 0, c, args = origArgs ? origArgs
								.concat(me)
								: [ me ], componentIndex = args.length - 1;

						if (fn.apply(scope || me, args) !== false) {
							for (; i < len; i++) {
								c = cs[i];
								if (c.cascade) {
									c.cascade(fn, scope, origArgs);
								} else {
									args[componentIndex] = c;
									fn.apply(scope || cs, args);
								}
							}
						}
						return this;
					},
					isAncestor : function(possibleDescendant) {
						while (possibleDescendant) {
							if (possibleDescendant.ownerCt === this) {
								return true;
							}
							possibleDescendant = possibleDescendant.ownerCt;
						}
					},

					getComponent : function(comp) {
						if (Ext.isObject(comp)) {
							comp = comp.getItemId();
						}

						var c = this.items.get(comp);

						if (!c && typeof comp != 'number') {
							c = this.floatingItems.get(comp);
						}

						return c;
					},

					contains : function(comp, deep) {
						var result = false;
						if (deep) {
							this.cascade(function(c) {
								// Only test if the item is a container
								if (c.contains && c.contains(comp)) {
									result = true;
									return false;
								}
							});
							return result;
						} else {
							return this.items.contains(comp)
									|| this.floatingItems.contains(comp);
						}
					},

					nextChild : function(child, selector) {
						var me = this, result = null, childIndex = me.items
								.indexOf(child);

						if (childIndex !== -1) {
							result = selector ? Ext.ComponentQuery(selector,
									me.items.items.slice(childIndex + 1))
									: me.items.getAt(childIndex + 1);
							if (!result && me.ownerCt) {
								result = me.ownerCt.nextChild(me, selector);
							}
						}
						return result;
					},

					prevChild : function(child, selector) {
						var me = this, result = null, childIndex = me.items
								.indexOf(child);

						if (childIndex !== -1) {
							result = selector ? Ext.ComponentQuery(selector,
									me.items.items.slice(childIndex + 1))
									: me.items.getAt(childIndex + 1);
							if (!result && me.ownerCt) {
								result = me.ownerCt.nextChild(me, selector);
							}
						}
						return result;
					},

					enable : function() {
						this.callParent(arguments);

						var itemsToDisable = this.getChildItemsToDisable(), length = itemsToDisable.length, item, i;

						for (i = 0; i < length; i++) {
							item = itemsToDisable[i];

							if (item.resetDisable) {
								item.enable();
							}
						}

						return this;
					},

					disable : function() {
						this.callParent(arguments);

						var itemsToDisable = this.getChildItemsToDisable(), length = itemsToDisable.length, item, i;

						for (i = 0; i < length; i++) {
							item = itemsToDisable[i];

							if (item.resetDisable !== false && !item.disabled) {
								item.disable();
								item.resetDisable = true;
							}
						}

						return this;
					},

					getChildItemsToDisable : function() {
						return this.query('[isFormField],button');
					},

					// @private
					beforeDestroy : function() {
						var me = this, items = me.items, floatingItems = me.floatingItems, c;

						if (items) {
							while ((c = items.first())) {
								me.doRemove(c, true);
							}
						}

						if (floatingItems) {
							while ((c = floatingItems.first())) {
								me.doRemove(c, true);
							}
						}

						Ext.destroy(me.layout);
						me.callParent();
					}
				});

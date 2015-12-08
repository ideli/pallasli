Ext
		.define(
				'Pallas.extDesigner.waapp.field.JsonObjectDataView',
				{
					extend : 'Ext.view.View',
					alias : 'widget.jsondataview',
					requires : [ 'Ext.layout.component.BoundList',
							'Ext.toolbar.Paging' ],

					mixins : {
						queryable : 'Ext.Queryable'
					},

					pageSize : 0,

					// private overrides
					baseCls : Ext.baseCSSPrefix + 'boundlist',
					itemCls : Ext.baseCSSPrefix + 'boundlist-item',
					listItemCls : '',
					shadow : false,
					trackOver : true,
					refreshed : 0,

					deferInitialRefresh : false,

					// componentLayout : 'boundlist',

					// childEls : [ 'listEl' ],

					renderTpl : [
							'<div id="{id}-listEl" class="{baseCls}-list-ct ',
							Ext.dom.Element.unselectableCls,
							'" style="overflow:auto"></div>',
							'{%',
							'var me=values.$comp, pagingToolbar=me.pagingToolbar;',
							'if (pagingToolbar) {',
							'pagingToolbar.ownerLayout = me.componentLayout;',
							'Ext.DomHelper.generateMarkup(pagingToolbar.getRenderTree(), out);',
							'}', '%}', {
								disableFormats : true
							} ],
					initComponent : function() {
						var me = this, baseCls = me.baseCls, itemCls = me.itemCls;

						me.selectedItemCls = baseCls + '-selected';
						if (me.trackOver) {
							me.overItemCls = baseCls + '-item-over';
						}
						me.itemSelector = "." + itemCls;

						if (me.floating) {
							me.addCls(baseCls + '-floating');
						}

						me.tpl = new Ext.XTemplate(
								'<table cols=7  >',
								'<tr><td colSpan=4>',
								'<label>更名</label>',
								'<input type="text"  style="width:80px"/>',
								'<select style="width:80px"></select>',
								'<button>创建: {name}</button>',
								'<button>删除: {name}</button>',
								'<button>上移: {name}</button>',
								'<button>下移: {name}</button>',
								'</td> <td colSpan=3>',
								'<input type="checkbox"/>',
								'<label>自动更新</label>',
								'<button>更新: {name}</button>',
								'</td></tr>',
								'<tr style="height:200px;"><td   colSpan=2>',
								'<tpl for="kids">',
								'<tpl if="this.isGirl(name)">',
								'<p>Girl: {name} - {age}</p>',
								'<tpl else>',
								'<p>Boy: {name} - {age}</p>',
								'</tpl>',
								'<tpl if="this.isBaby(age)">',
								'<p>{name} is a baby!</p>',
								'</tpl>',
								'</tpl></p>',
								'</td> <td  colSpan=5>',
								'<textarea style="height:200px;width:500px;" id="{id}" {inputAttrTpl}',
								'<tpl if="name"> name="{name}"</tpl>',
								'<tpl if="rows"> rows="{rows}" </tpl>',
								'<tpl if="cols"> cols="{cols}" </tpl>',
								'<tpl if="placeholder"> placeholder="{placeholder}"</tpl>',
								'<tpl if="size"> size="{size}"</tpl>',
								'<tpl if="maxLength !== undefined"> maxlength="{maxLength}"</tpl>',
								'<tpl if="readOnly"> readonly="readonly"</tpl>',
								'<tpl if="disabled"> disabled="disabled"</tpl>',
								'<tpl if="tabIdx"> tabIndex="{tabIdx}"</tpl>',
								' class="{fieldCls} {typeCls} {inputCls}" ',
								'<tpl if="fieldStyle"> style="{fieldStyle}"</tpl>',
								' autocomplete="off">\n',
								'<tpl if="value">{[Ext.util.Format.htmlEncode(values.value)]}</tpl>',
								'</textarea>', '</td></tr>',
								'<tr><td colSpan=5>', '</td> <td colSpan=2>',
								'<button >确定: {name}</button>',
								'<button>取消: {name}</button>', '</td></tr>',
								'</table>', {
									// XTemplate configuration:
									disableFormats : true,
									// member functions:
									save : function(name) {
										alert(name);
										return name == 'Aubrey'
												|| name == 'Nikol';
									},
									isBaby : function(age) {
										return age < 1;
									}
								});
						me.callParent();
					},

					beforeRender : function() {
						var me = this;

						me.callParent(arguments);
						if (me.up('menu')) {
							me.addCls(Ext.baseCSSPrefix + 'menu');
						}
					},

					getRefOwner : function() {
						return this.pickerField || this.callParent();
					},

					getRefItems : function() {
						return this.pagingToolbar ? [ this.pagingToolbar ] : [];
					},

					createPagingToolbar : function() {
						return Ext.widget('pagingtoolbar', {
							id : this.id + '-paging-toolbar',
							pageSize : this.pageSize,
							store : this.dataSource,
							border : false,
							ownerCt : this,
							ownerLayout : this.getComponentLayout()
						});
					},

					finishRenderChildren : function() {
						var toolbar = this.pagingToolbar;

						this.callParent(arguments);
						if (toolbar) {
							toolbar.finishRender();
						}
					},

					refresh : function() {
						var me = this, tpl = me.tpl, toolbar = me.pagingToolbar, rendered = me.rendered;
						tpl.field = me.pickerField;
						tpl.store = me.store;
						me.callParent();
						tpl.field = tpl.store = null;

						if (rendered && toolbar && toolbar.rendered
								&& !me.preserveScrollOnRefresh) {
							me.el.appendChild(toolbar.el);
						}

						if (rendered && Ext.isIE6 && Ext.isStrict) {
							me.listEl.repaint();
						}

					},

					bindStore : function(store, initial) {
						var toolbar = this.pagingToolbar;

						this.callParent(arguments);
						if (toolbar) {
							toolbar.bindStore(store, initial);
						}
					},

					getTargetEl : function() {
						return this.listEl || this.el;
					},

					getInnerTpl : function(displayField) {
						return '{' + displayField + '}';
					},

					onDestroy : function() {
						Ext.destroyMembers(this, 'pagingToolbar', 'listEl');
						this.callParent();
					}
				});

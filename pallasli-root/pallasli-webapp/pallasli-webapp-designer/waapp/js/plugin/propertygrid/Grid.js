Ext
		.define(
				'Pallas.extDesigner.waapp.plugin.propertygrid.Grid',
				{

					extend : 'Ext.grid.Panel',

					alias : 'waapp.propertygrid',

					uses : [
							'Ext.grid.plugin.CellEditing',
							'Pallas.extDesigner.waapp.plugin.propertygrid.Store',
							'Pallas.extDesigner.waapp.plugin.propertygrid.HeaderContainer',
							'Ext.XTemplate', 'Ext.grid.CellEditor',
							'Ext.form.field.Date', 'Ext.form.field.Text',
							'Ext.form.field.Number', 'Ext.form.field.ComboBox' ],
					valueField : 'value',

					nameField : 'name',
					orderColumn : 'name',
					inferTypes : true,

					// private config overrides
					enableColumnMove : false,
					columnLines : true,
					stripeRows : false,
					trackMouseOver : false,
					clicksToEdit : 1,
					enableHdMenu : false,

					gridCls : Ext.baseCSSPrefix + 'property-grid',

					// private
					initComponent : function() {
						var me = this;

						me.source = me.source || {};
						me.addCls(me.gridCls);
						me.plugins = me.plugins || [];
						me.plugins.push(new Ext.grid.plugin.CellEditing({
							clicksToEdit : me.clicksToEdit,
							startEdit : function(record, column) {
								return this.self.prototype.startEdit.call(this,
										record, me.headerCt.child('#'
												+ me.valueField));
							}
						}));

						me.selModel = {
							selType : 'cellmodel',
							onCellSelect : function(position) {
								if (position.column != 1) {
									position.column = 1;
								}
								return this.self.prototype.onCellSelect.call(
										this, position);
							}
						};

						me.sourceConfig = Ext.apply({}, me.sourceConfig);

						if (!me.store) {
							me.propStore = me.store = new Pallas.extDesigner.waapp.plugin.propertygrid.Store(
									me, me.source);
						}
						me.configure(me.sourceConfig);
						if (me.sortableColumns) {
							me.store.sort(me.orderColumn, 'ASC');
						}
						me.columns = new Pallas.extDesigner.waapp.plugin.propertygrid.HeaderContainer(
								me, me.store);

						me.addEvents('beforepropertychange', 'propertychange');
						me.callParent();

						me.getView().walkCells = this.walkCells;

						me.editors = {
							'date' : new Ext.grid.CellEditor({
								field : new Ext.form.field.Date({
									selectOnFocus : true
								})
							}),
							'string' : new Ext.grid.CellEditor({
								field : new Ext.form.field.Text({
									selectOnFocus : true
								})
							}),
							'number' : new Ext.grid.CellEditor({
								field : new Ext.form.field.Number({
									selectOnFocus : true
								})
							}),
							'boolean' : new Ext.grid.CellEditor({
								field : new Ext.form.field.ComboBox({
									editable : false,
									store : [ [ true, me.headerCt.trueText ],
											[ false, me.headerCt.falseText ] ]
								})
							})
						};

						// Track changes to the data so we can fire our events.
						// me.store.on('update', me.onUpdate, me);
					},

					configure : function(config) {
						var me = this, store = me.store, i = 0, len = me.store
								.getCount(), nameField = me.nameField, valueField = me.valueField, name, value, rec, type;

						me.configureLegacy(config);

						if (me.inferTypes) {
							for (; i < len; ++i) {
								rec = store.getAt(i);
								name = rec.get(nameField);
								if (!me.getConfig(name, 'type')) {
									value = rec.get(valueField);
									if (Ext.isDate(value)) {
										type = 'date';
									} else if (Ext.isNumber(value)) {
										type = 'number';
									} else if (Ext.isBoolean(value)) {
										type = 'boolean';
									} else {
										type = 'string';
									}
									me.setConfig(name, 'type', type);
								}
							}
						}
					},

					getConfig : function(fieldName, key, defaultValue) {
						var config = this.sourceConfig[fieldName], out;

						if (config) {
							out = config[key];
						}
						return out || defaultValue;
					},

					setConfig : function(fieldName, key, value) {
						var sourceCfg = this.sourceConfig, o = sourceCfg[fieldName];

						if (!o) {
							o = sourceCfg[fieldName] = {
								__copied : true
							};
						} else if (!o.__copied) {
							o = Ext.apply({
								__copied : true
							}, o);
							sourceCfg[fieldName] = o;
						}
						o[key] = value;
						return value;
					},

					// to be deprecated in 4.2
					configureLegacy : function(config) {
						var me = this, o, key, value;

						me.copyLegacyObject(config, me.customRenderers,
								'renderer');
						me.copyLegacyObject(config, me.customEditors, 'editor');
						me.copyLegacyObject(config, me.propertyNames,
								'displayName');

						// <debug>
						// exclude types since it's new
						if (me.customRenderers || me.customEditors
								|| me.propertyNames) {
							if (Ext.global.console && Ext.global.console.warn) {
								Ext.global.console
										.warn(
												this.$className,
												'customRenderers, customEditors & propertyNames have been consolidated into a new config, see "sourceConfig". These configurations will be deprecated.');
							}
						}
						// </debug>
					},

					copyLegacyObject : function(config, o, keyName) {
						var key, value;

						for (key in o) {
							if (o.hasOwnProperty(key)) {
								if (!config[key]) {
									config[key] = {};
								}
								config[key][keyName] = o[key];
							}
						}
					},

					// @private
					onUpdate : function(store, record, operation) {
						var me = this, v, oldValue;

						if (me.rendered && operation == Ext.data.Model.EDIT) {
							v = record.get(me.valueField);
							oldValue = record.modified.value;
							if (me.fireEvent('beforepropertychange', me.source,
									record.getId(), v, oldValue) !== false) {
								if (me.source) {
									me.source[record.getId()] = v;
								}
								record.commit();
								me.fireEvent('propertychange', me.source,
										record.getId(), v, oldValue);
							} else {
								record.reject();
							}
						}
					},

					// Custom implementation of walkCells which only goes up and
					// down.
					walkCells : function(pos, direction, e, preventWrap,
							verifierFn, scope) {
						if (direction == 'left') {
							direction = 'up';
						} else if (direction == 'right') {
							direction = 'down';
						}
						pos = Ext.view.Table.prototype.walkCells.call(this,
								pos, direction, e, preventWrap, verifierFn,
								scope);
						if (pos && !pos.column) {
							pos.column = 1;
						}
						return pos;
					},

					// @private
					// Returns the correct editor type for the property type, or
					// a custom one keyed by the property name
					getCellEditor : function(record, column) {
						var me = this, propName = record.get(me.nameField), val = record
								.get(me.valueField), editor = me.getConfig(
								propName, 'editor'), type = me.getConfig(
								propName, 'type'), editors = me.editors;

						// A custom editor was found. If not already wrapped
						// with a CellEditor, wrap it, and stash it back
						// If it's not even a Field, just a config object,
						// instantiate it before wrapping it.
						if (editor) {
							if (!(editor instanceof Ext.grid.CellEditor)) {
								if (!(editor instanceof Ext.form.field.Base)) {
									editor = Ext.ComponentManager.create(
											editor, 'textfield');
								}
								editor = me.setConfig(propName, 'editor',
										new Ext.grid.CellEditor({
											field : editor
										}));
							}
						} else if (type) {
							switch (type) {
							case 'date':
								editor = editors.date;
								break;
							case 'number':
								editor = editors.number;
								break;
							case 'boolean':
								editor = me.editors['boolean'];
								break;
							default:
								editor = editors.string;
							}
						} else if (Ext.isDate(val)) {
							editor = editors.date;
						} else if (Ext.isNumber(val)) {
							editor = editors.number;
						} else if (Ext.isBoolean(val)) {
							editor = editors['boolean'];
						} else {
							editor = editors.string;
						}

						// Give the editor a unique ID because the CellEditing
						// plugin caches them
						editor.editorId = propName;
						return editor;
					},

					beforeDestroy : function() {
						var me = this;
						me.callParent();
						me.destroyEditors(me.editors);
						me.destroyEditors(me.customEditors);
						delete me.source;
					},

					destroyEditors : function(editors) {
						for ( var ed in editors) {
							if (editors.hasOwnProperty(ed)) {
								Ext.destroy(editors[ed]);
							}
						}
					},

					setSource : function(source, sourceConfig) {
						var me = this;

						me.source = source;
						if (sourceConfig !== undefined) {
							me.sourceConfig = Ext.apply({}, sourceConfig);
							me.configure(me.sourceConfig);
						}
						me.propStore.setSource(source);
						for ( var key in source) {
							var record = me.propStore.findRecord("name", key);
							me.onUpdate(me.store, record, Ext.data.Model.EDIT);
						}
					},

					getSource : function() {
						return this.propStore.getSource();
					},

					setProperty : function(prop, value, create) {
						var me = this;
						me.propStore.setValue(prop, value, create);
						var record = me.propStore.findRecord("name", prop);
						if (record) {
							me.onUpdate(me.store, record, Ext.data.Model.EDIT);
						}
					},

					removeProperty : function(prop) {
						this.propStore.remove(prop);
					}

				/**
				 * @cfg {Object} store
				 * @private
				 */
				/**
				 * @cfg {Object} columns
				 * @private
				 */
				});

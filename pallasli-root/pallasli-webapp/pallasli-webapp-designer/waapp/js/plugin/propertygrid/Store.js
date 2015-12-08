Ext
		.define(
				'Pallas.extDesigner.waapp.plugin.propertygrid.Store',
				{

					extend : 'Ext.data.Store',

					sortOnLoad : false,

					uses : [ 'Ext.data.reader.Reader', 'Ext.data.proxy.Proxy',
							'Ext.data.ResultSet',
							'Pallas.extDesigner.waapp.plugin.propertygrid.Property' ],

					constructor : function(grid, source) {
						var me = this;

						me.grid = grid;
						me.source = source;
						me
								.callParent([ {
									data : source,
									model : Pallas.extDesigner.waapp.plugin.propertygrid.Property,
									proxy : me.getProxy()
								} ]);
					},

					getProxy : function() {
						if (!this.proxy) {
							Pallas.extDesigner.waapp.plugin.propertygrid.Store.prototype.proxy = new Ext.data.proxy.Memory(
									{
										model : Pallas.extDesigner.waapp.plugin.propertygrid.Property,
										reader : this.getReader()
									});
						}
						return this.proxy;
					},

					getReader : function() {
						if (!this.reader) {
							Pallas.extDesigner.waapp.plugin.propertygrid.Store.prototype.reader = new Ext.data.reader.Reader(
									{
										model : Pallas.extDesigner.waapp.plugin.propertygrid.Property,

										buildExtractors : Ext.emptyFn,

										read : function(dataObject) {
											return this.readRecords(dataObject);
										},

										readRecords : function(dataObject) {
											var val, propName, result = {
												records : [],
												success : true
											};

											for ( var propName in dataObject) {
												if (dataObject
														.hasOwnProperty(propName)) {
													val = dataObject[propName];
													var tmpData = {
														name : propName
													};
													if (this
															.isEditableValue(val)) {
														tmpData["value"] = val;

														result.records
																.push(new Pallas.extDesigner.waapp.plugin.propertygrid.Property(
																		tmpData,
																		propName));
													} else if (Ext
															.isObject(val)) {

														for ( var pName in val) {
															tmpData[pName] = val[pName];
														}
														result.records
																.push(new Pallas.extDesigner.waapp.plugin.propertygrid.Property(
																		tmpData,
																		propName));
													}
												}
											}
											result.total = result.count = result.records.length;
											return new Ext.data.ResultSet(
													result);
										},

										// @private
										isEditableValue : function(val) {
											return Ext.isPrimitive(val)
													|| Ext.isDate(val)
													|| val === null;
										}
									});
						}
						return this.reader;
					},

					// @protected
					setSource : function(dataObject) {
						var me = this;

						me.source = dataObject;
						me.suspendEvents();
						me.removeAll();
						me.proxy.data = dataObject;
						me.load();
						me.resumeEvents();
						me.fireEvent('datachanged', me);
						me.fireEvent('refresh', me);
					},

					// @private
					getProperty : function(row) {
						return Ext.isNumber(row) ? this.getAt(row) : this
								.getById(row);
					},

					// @private
					setValue : function(prop, value, create) {
						var me = this, rec = me.getRec(prop);
						if (Ext.isPrimitive(value) || Ext.isDate(value)
								|| value === null) {

							if (rec) {
								rec.set('value', value);
								me.source[prop] = value;
							} else if (create) {
								// only create if specified.
								me.source[prop] = value;
								rec = new Pallas.extDesigner.waapp.plugin.propertygrid.Property(
										{
											name : prop,
											value : value
										}, prop);
								me.add(rec);
							}
						} else if (Ext.isObject(value)) {
							if (rec) {
								for ( var pName in value) {
									rec.set(pName, value[pName]);
								}
								me.source[prop] = value;
							} else if (create) {
								var tmpData = {
									name : prop
								};
								for ( var pName in value) {
									tmpData[pName] = value[pName];
								}
								me.source[prop] = value;
								rec = new Pallas.extDesigner.waapp.plugin.propertygrid.Property(
										tmpData, prop);
								me.add(rec);
							}
						}
					},

					// @private
					remove : function(prop) {
						var rec = this.getRec(prop);
						if (rec) {
							this.callParent([ rec ]);
							delete this.source[prop];
						}
					},

					// @private
					getRec : function(prop) {
						return this.getById(prop);
					},

					// @protected
					getSource : function() {
						return this.source;
					}
				});
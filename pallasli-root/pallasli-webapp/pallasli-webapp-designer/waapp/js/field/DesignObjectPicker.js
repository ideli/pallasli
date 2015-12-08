Ext
		.define(
				'Pallas.extDesigner.waapp.field.DesignObjectPicker',
				{
					alias : 'widget.designobjectpicker',
					extend : 'Ext.panel.Panel',
					layout : 'fit',
					height : 300,
					setValue : function(v, r) {
						var me = this;
						me.pickerField.setValue(v);
						me.pickerField.value = r;
						me.pickerField.rawvalue = v;
						return me;
					},
					onSelect : function(value, realValue, records) {
						if (this.fireEvent('beforeselect', this, value,
								realValue, records) !== false) {
							this.setValue(value);
							this.realValue = realValue;
							if (this.hiddenField) {
								if (realValue == undefined) {
									this.hiddenField.value = value;
								} else {
									this.hiddenField.value = realValue;
								}
							}
							if (Ext.isDefined(records)) {
								this.recordsValue = records;
							}
							this.collapse();
							this.fireEvent('select', this, value, realValue,
									records);
						}
					},
					panelSelectOver : function(g) {
						var record = g.getSelectionModel().getSelection()[0];
						var value = '';
						var display = '';
						if (record) {
							display = record.get('f_caption') + ' ['
									+ record.get('key') + ']';
							value = {
								caption : record.get('f_caption'),
								classpath : record.get('f_class'),
								data : record.get('key')
							};
							// display = Ext.encode(value);
						}
						this.onSelect(display, value, record);
						this.hide();
					},

					initComponent : function() {

						var me = this;
						me.hiddenValue = me.pickerField.getValue();
						console.log(me.hiddenValue);
						me.hiddenValue = me.hiddenValue.replace(/\\r/g, "\r")
								.replace(/\\n/g, "\n");

						me.gstore = Ext
								.create(
										'Ext.data.Store',
										{
											fields : [ {
												name : 'key',
												mapping : 'key'
											}, {
												name : 'f_key',
												mapping : 'f_key'
											}, {
												name : 'classpath',
												mapping : 'classpath'
											}, {
												name : 'f_class',
												mapping : 'f_class'
											}, {
												name : 'f_name',
												mapping : 'f_name'
											}, {
												name : 'f_caption',
												mapping : 'f_caption'
											}, {
												name : 'f_note',
												mapping : 'f_note'
											} ],
											proxy : {
												type : 'direct',
												directFn : DesignObjectDirect.getDesignObjectList,
												paramOrder : [ 'mclass',
														'parentKey' ],
												extraParams : {
													mclass : '',
													parentKey : ''
												},
												reader : {
													totalProperty : 'totals',
													idProperty : 'key',
													root : 'results'
												}
											}
										});
						var grid = new Ext.grid.GridPanel(
								{
									border : false,
									autoExpandColumn : 'f_note',
									enableHdMenu : false,
									enableColumnMove : false,
									store : me.gstore,
									columns : [
											new Ext.grid.RowNumberer(),
											{
												id : 'key',
												dataIndex : 'key',
												header : 'Key',
												width : 120,
												renderer : function(value, p,
														record) {
													var type = record
															.get("f_class");
													return Ext.String
															.format(
																	"<div style='height:16px;padding-left:23px;background:transparent url(extDesigner/waapp/icon/designer/{0}.gif) no-repeat'> {1}</div>",
																	type, value);
												}
											}, {
												id : 'f_class',
												dataIndex : 'f_class',
												header : '对象类',
												width : 70
											}, {
												id : 'f_name',
												dataIndex : 'f_name',
												header : '命名',
												width : 80
											}, {
												id : 'f_caption',
												dataIndex : 'f_caption',
												header : '名称',
												width : 80
											}, {
												id : 'f_note',
												dataIndex : 'f_note',
												header : '说明'
											} ],
									listeners : {
										'rowdblclick' : {
											fn : function(g, rowIndex, e) {
												this.panelSelectOver(g);
											},
											scope : this
										}
									}
								});

						var filterCombo = new Ext.form.ComboBox({
							width : 120,
							triggerAction : 'all',
							editable : false,
							hideLabel : true,
							value : 'all',
							store : [ 'all', 'same module', 'parent' ]
						});
						filterCombo.on('select', function(c, record, index) {
							var field = me.pickerField;
							var store = grid.getStore();
							var key = field.getParentKey();
							if (key == '') {
								return;
							}
							if (filterCombo.getValue() == 'all') {
								store.clearFilter();
							} else if (filterCombo.getValue() == 'parent') {
								store.filterBy(function(r, id) {
									return r.get('key').indexOf(key) == 0;
								});
							} else {
								key = key.split(".")[0];
								store.filterBy(function(r, id) {
									return r.get('key').indexOf(key) == 0;
								});
							}
						});

						var buttons = [];
						// if (this.confirm) {
						buttons = [ '筛选：', filterCombo, '->', {
							text : '确定',
							iconCls : 'icon-common-confirm',
							handler : function() {
								this.panelSelectOver(grid);
							},
							scope : this
						}, '-', {
							text : '取消',
							iconCls : 'icon-common-cancel',
							handler : function() {
								this.hide();
							},
							scope : this
						} ];
						// }

						var panel = new Ext.Panel(
								{
									layout : 'fit',
									beforeexpend : function() {

										var field = me.pickerField;
										var store = grid.getStore();
										if (store.proxy.extraParams.mclass != field.mclass
												|| store.proxy.extraParams.parentKey != field.parentKey) {
											store.proxy.extraParams.mclass = field.mclass;
											if (field.selectInParent) {
												filterCombo.enable();
												filterCombo.setValue('parent');
											} else {
												filterCombo.setValue('all');
												filterCombo.disable();
											}
											store.reload();
										}
									},
									collapseIf : function(e) {
										if (filterCombo.wrap
												&& e.within(filterCombo.wrap)) {
											return false;
										}
										if (filterCombo.list
												&& e.within(filterCombo.list)) {
											return false;
										}
										return true;
									},
									items : [ new Ext.Panel({
										layout : 'fit',
										height : 300,
										bbar : buttons,
										items : [ grid ]
									}) ]
								});

						me.items = [ panel ];
						panel.beforeexpend();
						me.floating = true;
						me.callParent();

					}
				});

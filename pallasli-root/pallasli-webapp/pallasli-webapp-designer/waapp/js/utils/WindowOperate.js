Ext.define('Pallas.extDesigner.waapp.utils.WindowOperate', {
	"statics" : {

		// 获得编辑器属性
		getEditorConfig : function(xeditor, params) {
			var editor = {};
			switch (xeditor) {
			case 'boolean':
				editor = {
					xtype : 'combo',
					triggerAction : 'all',
					forceSelection : false,
					editable : false,
					store : [ false, true ]
				};
				break;
			case 'number':
				editor = {
					xtype : 'numberfield',
					style : 'text-align:left;',
					selectOnFocus : true
				};
				break;
			case 'textarea':
				editor = {
					xtype : 'textarea',
					selectOnFocus : true
				};
				break;
			case 'date':
				editor = {
					xtype : 'datefield',
					format : 'Y-m-d'
				};
				break;
			case 'datetime':
				editor = {
					xtype : 'datefield',
					format : 'Y-m-d'
				};
				break;
			case 'select':
				editor = {
					xtype : 'combo',
					triggerAction : 'all',
					mode : 'local',
					forceSelection : false,
					store : params.datas
				};
				break;
			case 'selectkeymap':
				editor = {
					xtype : 'combo',
					triggerAction : 'all',
					mode : 'local',
					forceSelection : false,
					store : new Ext.data.SimpleStore({
						fields : [ 'value', 'name' ],
						data : params.datas
					}),
					valueField : 'value',
					displayField : 'name'
				};
				break;
			case 'check':
				editor = {
					xtype : 'checkboxgroup',
					items : params.datas
				};
				break;
			case 'radio':
				editor = {
					xtype : 'radiogroup',
					items : params.datas
				};
				break;
			case 'jsonobject':
				editor = {
					xtype : 'jsonobject'
				};
				break;
			case 'textbox':
				editor = {
					xtype : 'textbox'
				};
				break;
			case 'organization':
				editor = {
					xtype : 'mixkyorganizationfield'
				};
				break;
			case 'designobject':
				editor = {
					xtype : 'designobject'
				// xtype : 'mixkydesignobjectfield'
				};
				break;
			case 'userselector':
				editor = {
					xtype : 'trigger',
					editable : false,
					onTriggerClick : function(e) {
						var field = this;
						var win = Mixky.lib.editor.getUserSelector(this.value,
								function(values) {
									var value = '';
									for (var i = 0; i < values.length; i++) {
										value = value
												+ values[i].get("expression")
												+ ';';
									}
									field.setValue(value);
								}, params);
						win.show();
					}
				};
				break;
			case 'readonly':
				editor = {
					xtype : 'textfield',
					selectOnFocus : true,
					readOnly : true
				};
				break;
			case 'string':
			default:
				editor = {
					xtype : 'textfield',
					selectOnFocus : true
				};
			}
			if (params) {
				if (params.config) {
					Ext.apply(editor, params.config);
				}
			}

			return editor;
		},
		// 获得编辑器对象
		getEditorComponent : function(name, params) {
			return this.getEditorConfig(name, params);
			return Ext.ComponentMgr.create(this.getEditorConfig(name, params));
		},
		// 处理字段属性初始化
		setFieldInitConfig : function(editortype, field, cfg) {
			if (!Ext.isDefined(field) || !Ext.isDefined(cfg)) {
				return;
			}
			switch (editortype) {
			case 'select':
			case 'selectkeymap':
				field.store.removeAll();
				field.store.loadData(cfg.datas);
				if (field.el) {
					field.setEditable(cfg.readOnly === false);
				} else {
					field.editable = (cfg.readOnly === false);
				}
				break;
			case 'textarea':
				if (Ext.isDefined(cfg.height)) {
					field.setHeight(cfg.height);
				} else {
					field.setHeight(60);
				}
			case 'organization':
				if (Ext.isDefined(cfg.selectMulti)) {
					field.selectMulti = cfg.selectMulti;
				} else {
					field.selectMulti = true;
				}
				if (Ext.isDefined(cfg.selectType)) {
					field.selectType = cfg.selectType;
				} else {
					field.selectType = 'mix';
				}
				if (Ext.isDefined(cfg.valueField)) {
					field.valueField = cfg.valueField;
				} else {
					field.valueField = 'expression';
				}
				if (cfg.displayField) {
					field.displayField = cfg.displayField;
				} else {
					field.displayField = 'f_caption';
				}
				if (Ext.isDefined(cfg.valueSeparator)) {
					field.valueSeparator = cfg.valueSeparator;
				} else {
					field.valueSeparator = '';
				}
				break;
			case 'designobject':
				if (Ext.isDefined(cfg.selectInParent)) {
					field.selectInParent = cfg.selectInParent;
				} else {
					field.selectInParent = false;
				}
				if (Ext.isDefined(cfg.mclass)) {
					field.mclass = cfg.mclass;
				} else {
					field.mclass = '';
				}
				if (Ext.isDefined(cfg.parentKey)) {
					field.parentKey = cfg.parentKey;
				} else {
					field.parentKey = '';
				}
				break;
			default:
				break;
			}
		},
		// 服务器端渲染值
		remoteRenderValue : function(type, value, fn, cfg) {
			if (!value || value == '') {
				fn('', cfg);
			}
			switch (type) {
			case 'renderUserExpression':
				if (Ext.isDefined(cfg) && cfg.valueSeparator
						&& cfg.valueSeparator != '') {
					value = value.split(cfg.valueSeparator);
				}
				OrganizationDirect.getExpressionDisplay(value, function(result,
						e) {
					fn(result.display, cfg);
				});
				break;
			case 'renderDesignObject':
				DesignObjectDirect.loadObject(value.data, function(result, e) {

					fn(
							result.object.f_caption + ' [' + result.object.key
									+ ']', cfg);
				});
				break;
			}
		},
		// 服务器端渲染列表字段
		remoteRenderStore : function(store, fields) {
			for (var iii = 0; iii < fields.length; iii++) {
				var field = fields[iii];
				if (Ext.isDefined(field.remoteRenderType)) {
					for (var i = 0; i < store.getCount(); i++) {
						var record = store.getAt(i);
						var v = record.get(field.name);
						if (!(Ext.isDefined(v) && v != ''))
							continue;
						var cfg = {};
						cfg.record = record;
						if (field.remoteRenderType == 'renderUserExpression') {
							cfg.valueSeparator = field.valueSeparator;
						}
						this.remoteRenderValue(field.remoteRenderType, v,
								function(result, cfg) {
									return;
									cfg.record.set(field.name + '_display',
											result);
									cfg.record.commit();
								}, cfg);
					}
				}
			}
		},

		// 获得域选择编辑窗口
		getFieldSelectorWindow : function(winCfg, selApi) {
			Ext.applyIf(selApi, {
				getInitValue : Ext.emptyFn,
				setValue : Ext.emptyFn,
				getValue : Ext.emptyFn,
				onSelectedFn : Ext.emptyFn
			});
			Ext.applyIf(winCfg, {
				modal : true,
				width : 500,
				height : 300,
				minWidth : 300,
				minHeight : 200,
				layout : 'fit',
				plain : true,
				buttonAlign : 'center',
				buttons : [ {
					text : '确定',
					handler : function() {
						var win = this.findParentByType('window');
						if (selApi.onSelectedFn(selApi.getValue()) !== false) {
							if (win.closeAction == 'hide') {
								win.hide();
							} else {
								win.close();
							}
						}
					}
				}, {
					text : '取消',
					handler : function() {
						var win = this.findParentByType('window');
						if (win.closeAction == 'hide') {
							win.hide();
						} else {
							win.close();
						}
					}
				} ],
				listeners : {
					'beforeshow' : function(win) {
						selApi.setValue(selApi.getInitValue());
					}
				}
			});
			var win = new Ext.Window(winCfg);
			return win;
		},

		getNewTableRecordId : function(tablename, fn) {
			CommonLibDirect.getNewTableRecordId(tablename, function(result, e) {
				if (result && result.success) {
					fn(result.id);
				}
			});
		},
		showOrganizationWindow : function(config, fn) {
			var selApi = {
				onSelectedFn : fn
			};
		}

	}
});
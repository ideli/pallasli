Ext.define('Pallas.extDesigner.waapp.field.JsonObjectPicker', {
	alias : 'widget.jsonobjectpicker',
	requires : [ 'Pallas.extDesigner.waapp.utils.ObjectFormat' ],
	extend : 'Ext.panel.Panel',
	layout : 'fit',
	height : 300,
	setValue : function(v) {
		var me = this;
		if (this.hiddenField) {
			this.hiddenField.value = v;
		}
		me.value = v;
		me.pickerField.setValue(v);
		return me;
	},
	onSelect : function(value, realValue, records) {
		var selectValid = this.fireEvent('beforeselect', this, value,
				realValue, records);
		if (selectValid === false) {
			return;
		}
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
		this.hide();
		this.fireEvent('select', this, value, realValue, records);
	},

	initComponent : function() {

		var me = this;
		var objctFormatUtil = Pallas.extDesigner.waapp.utils.ObjectFormat;
		me.getObjectFormatString = objctFormatUtil.getObjectFormatString;
		var valueEditor = Ext.create('widget.textarea', {
			region : 'center',
			anchor : '100%'
		});

		var tree = new Ext.tree.TreePanel({
			region : 'west',
			autoScroll : true,
			minSize : 50,
			width : 200,
			maxSize : 350,
			split : true,
			rootVisible : true,
			root : {
				id : 'object',
				text : 'object',
				nodeType : 'node',
				loaded : true,
				type : 'object',
				editable : false
			},
			appendAttributeNode : function(parentNode, name, type) {
				var editable = true;
				if (parentNode.raw.type == 'array') {
					editable = false;
				}
				var node = parentNode.appendChild({
					id : parentNode.id + '.' + name,
					text : name,
					type : type,
					nodeType : 'node',
					loaded : true,
					editable : editable,
					leaf : type != 'object' && type != 'array',
					iconCls : 'icon-administrator-' + type
				});
				return node;
			},
			addObjectNode : function(parentNode, obj) {
				for ( var n in obj) {
					if (obj.hasOwnProperty(n)) {
						var type = typeof obj[n];
						if (obj[n] instanceof Array) {
							type = 'array';
						}
						var node = this
								.appendAttributeNode(parentNode, n, type);
						if (type == 'object' || type == 'array') {
							this.addObjectNode(node, obj[n]);
						}
					}
				}
			},
			refreshNode : function(node) {
				while (node.firstChild) {
					node.removeChild(node.firstChild);
				}
				this.addObjectNode(node, this.getNodeValue(node));
			},
			setFieldObject : function(obj) {
				if (!obj || obj == '') {
					obj = {};
				}
				this.objectValue = obj;
				var node = this.getRootNode();
				this.refreshNode(node);
			},
			getNodeValue : function(node) {
				if (node.data.root) {
					return this.objectValue;
				} else {
					var object = this.getNodeValue(node.parentNode);
					return object[node.data.text];
				}
			},
			updateObjectName : function(node, name) {
				var parentValue = this.getNodeValue(node.parentNode);
				if (Ext.isDefined(parentValue[name])) {
					return false;
				} else {
					parentValue[name] = parentValue[node.data.text];
					delete parentValue[node.data.text];
					node.setId(node.parentNode + '.' + name);
					return true;
				}
			}
		});
		/***********************************************************************
		 * new Ext.tree.TreeSorter(tree, { sortType : function(node) { return
		 * node.data.text; } });
		 **********************************************************************/
		/***********************************************************************
		 * var treeEditor = new Ext.tree.TreeEditor(tree, { allowBlank : false
		 * });
		 * 
		 * treeEditor.on('beforecomplete', function(ed, value, startValue) { var
		 * result = tree.updateObjectName(ed.editNode, value); if (!result) {
		 * ed.cancelEdit(); } return result; });
		 **********************************************************************/
		var treeSm = tree.getSelectionModel();
		var renameAction = Ext.create('Ext.Action', {
			disabled : true,
			text : '更名',
			iconCls : 'icon-administrator-rename',
			handler : function() {
				var node = treeSm.getSelection()[0];
				treeEditor.triggerEdit(node);
			}
		});
		var nameField = Ext.create('widget.textfield', {
			disabled : true,
			width : 100,
			emptyText : '创建属性名称'
		});
		var typeField = Ext.create('widget.combo', {
			emptyText : '选择类型',
			displayField : "value",
			valueField : "value",
			queryMode : 'local',
			width : 80,
			editable : false,
			disabled : true,
			value : "string",
			store : Ext.create("Ext.data.ArrayStore", {
				data : [ [ 'string' ], [ 'boolean' ], [ 'number' ],
						[ 'object' ], [ 'array' ], [ 'function' ] ],
				fields : [ "value" ],
			})
		});
		var addAction = Ext.create('Ext.Action', {
			text : '创建',
			iconCls : 'icon-common-add',
			handler : function() {
				var parentNode = treeSm.getSelection()[0];
				var type = typeField.getValue();
				var parentType = parentNode.raw.type;
				var name = parentType == 'array' ? parentNode.childNodes.length
						: nameField.getValue();
				if (parentNode.raw.type != 'array' && name == '') {
					return;
				}
				var initValueObj = {
					'boolean' : false,
					'string' : '',
					'number' : 0,
					'object' : {},
					'function' : function() {
					},
					'array' : []
				};
				var value = initValueObj[type];
				var node = tree.appendAttributeNode(parentNode, name, type);
				var obj = tree.getNodeValue(parentNode);
				obj[node.data.text] = value;
				parentNode.expand();
				var record = tree.getStore().getNodeById(node.data.id);
				treeSm.select(record);
			}
		});
		var delAction = Ext.create('Ext.Action', {
			disabled : true,
			text : '删除',
			iconCls : 'icon-common-delete',
			handler : function() {
				var node = tree.getSelectionModel().getSelection()[0];
				var parentNode = node.parentNode;
				var object = tree.getNodeValue(parentNode);
				if (parentNode.raw.type == 'array') {
					for (var i = node.data.text; i < object.length - 1; i++) {
						object[i] = object[i + 1];
					}
					delete object[object.length - 1];
					tree.refreshNode(parentNode);
				} else {
					delete object[node.data.text];
					parentNode.removeChild(node);
				}
				parentNode.expand();
				parentNode.select();
			}
		});
		var upAction = Ext.create('Ext.Action', {
			disabled : true,
			iconCls : 'icon-common-up',
			handler : function() {
				var node = treeSm.getSelection()[0];
				if (node.isFirst()) {
					return;
				}
				var index = parseInt(node.data.text);
				var parentNode = node.parentNode;
				var obj = tree.getNodeValue(parentNode);
				var temp = obj[index];
				obj[index] = obj[index - 1];
				obj[index - 1] = temp;
				tree.refreshNode(parentNode);
				parentNode.expand();
				var node = tree.getNodeById(parentNode.id + '.' + (index - 1));
				node.select();
			}
		});
		var downAction = Ext.create('Ext.Action', {
			disabled : true,
			iconCls : 'icon-common-down',
			handler : function() {
				var node = treeSm.getSelection()[0];
				if (node.isLast()) {
					return;
				}
				var index = parseInt(node.data.text);
				var parentNode = node.parentNode;
				var obj = tree.getNodeValue(parentNode);
				var temp = obj[index];
				obj[index] = obj[index + 1];
				obj[index + 1] = temp;
				tree.refreshNode(parentNode);
				parentNode.expand();
				var node = tree.getNodeById(parentNode.id + '.' + (index + 1));
				node.select();
			}
		});
		var selectNode = null;
		var applyAction = Ext.create('Ext.Action', {
			disabled : true,
			text : '更新',
			iconCls : 'icon-common-apply',
			handler : function() {
				var node = selectNode;
				if (!valueEditor.isDirty() || !node) {
					return;
				}
				var parentNode = node.parentNode;
				var nodeText = node.data.text;
				var parentObject = tree.getNodeValue(parentNode);
				var value = valueEditor.getValue();
				switch (node.raw.type) {
				case 'number':
					parentObject[nodeText] = parseInt(value);
					break;
				case 'string':
					parentObject[nodeText] = value;
					break;
				case 'boolean':
					parentObject[nodeText] = value == 'true';
					break;
				case 'function':
					parentObject[nodeText] = Ext.decode(value);
					break;

				}
			}
		});
		var autoApply = new Ext.form.Checkbox({
			checked : true,
			hideLabel : true
		});
		autoApply.on('check', function(f, checked) {
			if (checked) {
				applyAction.disable();
			} else {
				applyAction.enable();
			}
		});

		var tools = [ renameAction, '-', nameField, typeField, addAction, '-',
				delAction, '-', upAction, downAction, '->', autoApply, '自动更新',
				'-', applyAction ];

		tree.getSelectionModel().on('selectionchange', function(sm, node) {
			renameAction.disable();
			nameField.disable();
			typeField.disable();
			addAction.disable();
			delAction.disable();
			upAction.disable();
			downAction.disable();
			if (!node || !node[0]) {
				valueEditor.setValue('');
				return;
			}
			node = node[0];
			selectNode = node;
			if (node.raw.editable) {
				renameAction.enable();
			}
			if (!node.isRoot) {
				delAction.enable();
				var parentType = node.parentNode.raw.type;
				if (parentType == 'array') {
					if (!node.isFirst()) {
						upAction.enable();
					}
					if (!node.isLast()) {
						downAction.enable();
					}
				}
			}
			var value = tree.getNodeValue(node);
			if (node.raw.type == 'object' || node.raw.type == 'array') {
				var showValue = me.getObjectFormatString(false, value, 0);
				// 设置显示域
				valueEditor.setValue(showValue);
				valueEditor.addClass('x-item-disabled');
				valueEditor.el.dom.readOnly = true;
				// 设置添加子对象
				if (node.raw.type == 'object') {
					nameField.enable();
				}
				typeField.enable();
				addAction.enable();
			} else {
				// 设置显示域
				valueEditor.setValue(value);
				valueEditor.originalValue = value;
				valueEditor.removeCls('x-item-disabled');
				valueEditor.el.dom.readOnly = false;
			}

		});

		valueEditor.on('blur', function(f) {
			if (!valueEditor.el.dom.readOnly) {
				if (autoApply.getValue()) {
					applyAction.execute();
				}
			}
		});

		var ui = {
			xtype : "panel",
			layout : 'border',
			tbar : tools,
			items : [ tree, valueEditor ]
		};

		var buttons = [];
		// if (this.confirm) {
		buttons = [ '->', {
			text : '确定',
			iconCls : 'icon-common-confirm',
			handler : function() {
				var value = tree.objectValue;
				this.onSelect(Ext.encode(value), value);
				this.hide();
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

		// me.beforeexpend = function(field) {
		var value = me
				.getObjectFormatString(false, me.pickerField.realValue, 0);
		var object = Ext.decode(value);
		if (typeof object != 'object') {
			object = {};
		}
		var newValue = me.getObjectFormatString(false, tree.objectValue, 0);
		if (!Ext.isDefined(tree.objectValue) || value != newValue) {
			tree.setFieldObject(object);
		}
		tree.getRootNode().expand();
		// };
		me.collapseIf = function(e) {
			if (treeEditor.editNode) {
				if (treeEditor.el && e.within(treeEditor.el)) {
					return false;
				}
			}
			if (typeField.wrap && e.within(typeField.wrap)) {
				return false;
			}
			if (typeField.list && e.within(typeField.list)) {
				return false;
			}
			return true;
		};
		me.bbar = buttons;
		me.items = [ ui ];
		me.floating = true;
		me.callParent();

	}
});

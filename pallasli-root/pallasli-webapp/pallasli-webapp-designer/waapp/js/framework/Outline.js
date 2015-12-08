Ext.define('Pallas.extDesigner.waapp.framework.Outline', {
	extend : "Ext.tree.Panel",
	alias : [ "waapp.outline" ],
	requires : [ "Pallas.extDesigner.waapp.utils.Actions",
			"Pallas.extDesigner.waapp.utils.ClassOperate",
			"Pallas.extDesigner.waapp.lib.Classes",
			'Pallas.extDesigner.waapp.lib.Context' ],
	margins : '0 0 5 5',
	cmargins : '0 0 0 0',
	autoScroll : true,
	border : false,
	// title : "功能导航大纲",
	// rootVisible : false,
	// 重命名
	renameObject : function(srcKey, dstKey) {
		var node = this.getNodeById(srcKey);
		if (node) {
			node.setId(dstKey);
			node.attributes.key = dstKey;
			this.refresh(node);
			return true;
		}
		return false;
	},
	// 刷新父节点
	refreshParent : function(node) {
		if (!node) {
			node = this.getSelectionModel().getSelection()[0];
		}
		if (!node) {
			return;
		}
		node = node.parentNode;
		node.attributes.children = undefined;
		node.reload();
	},
	// 刷新节点下级
	refresh : function(node) {
		if (!node) {
			node = this.getSelectionModel().getSelection()[0];
		}
		if (!node || node.isLeaf()) {
			return false;
		}
		var opions = {
			node : node
		};// 进行封装
		this.store.load(opions);// 局部加载
		return true;
	},
	// 获得节点对象标识{key,mclass}
	getObjectIdentity : function(node) {
		if (!node) {
			node = this.getSelectionModel().getSelection()[0];
		}
		if (!node) {
			return;
		}
		return {
			id : node.id,
			key : node.raw.key,
			mclass : node.raw.mclass
		};
	},
	// 选中对象
	selectObject : function(oid) {
		if (!oid) {
			return;
		}
		var node = this.getRootNode().findChild("id", oid.id);
		if (!node) {
			node = this.getRootNode().findChild("id", oid.key);
		}
		if (node) {
			this.getSelectionModel().select(node);
			this.expandPath(node.getPath());
		}
	},

	// 移除对象
	removeObject : function(key) {
		var node = this.getRootNode().findChild("id", key, true);
		if (node) {
			// 获得下一节点
			var nextNode = node.parentNode;
			if (!node.isLast()) {
				nextNode = node.nextSibling;
			} else if (!node.isFirst()) {
				nextNode = node.previousSibling;
			}
			// 选中下一节点
			this.getSelectionModel().select(nextNode);
			this.expandPath(nextNode.getPath());
			node.remove();
		}
	},

	initComponent : function() {
		var me = this;
		var actions = Pallas.extDesigner.waapp.utils.Actions;
		var context = Pallas.extDesigner.waapp.lib.Context;
		var classOperate = Pallas.extDesigner.waapp.utils.ClassOperate;
		var classes = Pallas.extDesigner.waapp.utils.Classes.datas;
		for ( var i in classes) {
			classOperate.registeModule(classes[i]);
		}
		var items = [ actions.openBtn, '-', actions.addBtn, actions.renameBtn,
				actions.deleteBtn, actions.copyBtn, actions.pasteBtn, '-',
				actions.viewJSONBtn, '-', actions.refreshBtn, '-',
				actions.extendsBtn ];
		// 节点右键菜单
		var menu = Ext.create('widget.menu', {
			ignoreParentClicks : true,
			items : items
		});
		me.on('itemcontextmenu',
				function(panel, record, item, index, e, eOpts) {
					menu.showAt(e.getXY());
				});
		me.on('itemdblclick', function(tree, n, htmlItem, index, e) {
			if (n.data.leaf) {
				actions.openBtn.execute();
			}
		});
		me.on('itemclick', function(p) {
			context.activatedObject = me.getObjectIdentity();
		});
		me.on('selectionchange', function(sm, nodes) {
			if (!nodes || !nodes[0]) {
				return;
			}
			var oid = me.getObjectIdentity(nodes[0]);
			if (oid) {
				Pallas.extDesigner.waapp.lib.Context.activateObject(oid, me);
			}
		});
		me.store = Ext.create('Ext.data.TreeStore', {
			root : {
				text : "功能导航大纲",
				id : 'root',
				key : 'root',
				mclass : 'root',
				children : [],
				expanded : true
			},
			fields : [ "id", "key", "mclass", "text", "leaf", "iconCls" ],
			proxy : {
				type : 'direct',
				directFn : OutlineDirect.getOutline,
				paramOrder : [ 'f_class', 'f_key' ],
				extraParams : {
					'f_class' : "root",
					'f_key' : "root"
				},
				reader : {
					root : 'elements'
				}
			},
			listeners : {
				exception : function(proxy, response, operation) {
					Ext.MessageBox.show({
						title : '服务器错误',
						msg : operation.getError(),
						icon : Ext.MessageBox.ERROR,
						buttons : Ext.Msg.OK
					});
				},
				beforeload : function(store, operation, e, d, f) {
					var data = operation.node.data;
					var new_params = {
						f_class : data.mclass ? data.mclass : "root",
						f_key : data.key ? data.key : "root"
					};
					Ext.apply(store.proxy.extraParams, new_params);
				},
				load : function(tree, store, node) {
					// console.log(node.expand);
					// if(node.expand)node.expand();
				}
			}
		});

		me.callParent();
		Pallas.extDesigner.waapp.utils.AllComponents.outline = me;
	}
});
Mixky.awsoft.Outline = function(cfg){
	Mixky.awsoft.Outline.superclass.constructor.call(this, cfg);
	// 节点右键菜单
	var menu = new Ext.menu.Menu({
		ignoreParentClicks : true,
		items:[
		    Mixky.awsoft.lib.Actions.Open,'-',
		    Mixky.awsoft.lib.Actions.Add,
		    Mixky.awsoft.lib.Actions.Rename,
		    Mixky.awsoft.lib.Actions.Delete,
		    Mixky.awsoft.lib.Actions.Copy,
		    Mixky.awsoft.lib.Actions.Paste,'-',
		  //  Mixky.awsoft.lib.Actions.Import,
		   // Mixky.awsoft.lib.Actions.Export,
		  //  Mixky.awsoft.lib.Actions.ViewJSON,'-',
		    Mixky.awsoft.lib.Actions.Refresh,'-'
		  //  Mixky.awsoft.lib.Actions.Extends
		]
	});
	this.on('contextmenu', function(node, e){
		this.getSelectionModel().select(node);
		menu.showAt(e.getXY());
	});
	this.on('dblclick', function(n, e){
		if(n.isLeaf()){
			Mixky.awsoft.lib.Actions.Open.execute();
		}
	});
	this.on('click', function(p){
		Mixky.awsoft.lib.Context.activatedObject = this.getObjectIdentity();
	});
	this.getSelectionModel().on('selectionchange', function(sm, node){
		if(!node){
			return;
		}
		var oid = node.getOwnerTree().getObjectIdentity(node);
		if(oid){
			Mixky.awsoft.lib.Context.activateObject(oid, node.getOwnerTree());
		}
	});
};

Ext.extend( Mixky.awsoft.Outline, Ext.tree.TreePanel, {
    margins : '0 0 5 0',
    cmargins :'0 0 0 0',
	autoScroll : true,
	root: {
        text: "功能导航大纲",
        id : 'root',
        key : 'root',
        mclass : 'root'
    },
    loader: new Ext.tree.TreeLoader({
    	paramOrder:['mclass'],
    	listeners:{
    		'beforeload':function(loader, node){
    			Ext.apply(this.baseParams,{'mclass':node.attributes['mclass']});
    		},
    		'load':function(loader, node){
    			node.getOwnerTree().selectObject(Mixky.awsoft.lib.Context.activatedObject);
    		}
    	},
        directFn: OutlineDirect.getOutline
    }),
    // 重命名
    renameObject : function(srcKey, dstKey){
    	var node = this.getNodeById(srcKey);
    	if(node){
    		node.setId(dstKey);
    		node.attributes.key = dstKey;
        	this.refresh(node);
    		return true;
    	}
    	return false;
    },
    // 刷新父节点
    refreshParent : function(node){
		if (!node) {
			node = this.getSelectionModel().getSelectedNode();
		}
		if(!node){
			return;
		}
		node = node.parentNode;
		node.attributes.children = undefined;
		node.reload();
    },
    // 刷新节点下级
	refresh : function(node) {
		if (!node) {
			node = this.getSelectionModel().getSelectedNode();
		}
		if (!node || node.isLeaf()) {
			return false;
		}
		node.attributes.children = undefined;
		node.reload();
		return true;
	},
    // 获得节点对象标识{key,mclass}
    getObjectIdentity:function(node){
    	if(!node){
    		node = this.getSelectionModel().getSelectedNode();
    	}
    	if(!node){
    		return;
    	}
    	return {id:node.id, key:node.attributes.key, mclass:node.attributes.mclass};
    },
    // 选中对象
    selectObject : function(oid){
    	if(!oid){
    		return;
    	}
    	var node = this.getNodeById(oid.id);
    	if(!node){
    		node = this.getNodeById(oid.key);
    	}
    	if(node){
    		node.select();
    		this.expandPath(node.getPath());
    	}
    },
    // 移除对象
    removeObject : function(key){
    	var node = this.getNodeById(key);
    	if(node){
    		// 获得下一节点
    		var nextNode = node.parentNode;
    		if(!node.isLast()){
    			nextNode = node.nextSibling;
    		}else if(!node.isFirst()){
    			nextNode = node.previousSibling;
    		}
        	// 选中下一节点
        	nextNode.select();
    		this.expandPath(nextNode.getPath());
        	node.remove();
    	}
    }
});
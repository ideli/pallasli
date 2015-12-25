
// Configuration:
//    loadUrl : URL to load nodes
TreeSelectPanel = function(config) {
	
	var _config = {
        id:'tree-select',
        width: 225,
        minSize: 175,
        maxSize: 400,
        rootVisible: false,
        lines:false,
        autoScroll:true,
        collapseFirst:false,
        loader: new Ext.tree.TreeLoader({
        	dataUrl: config.url,
        	baseParams: config.params
        })
    }
    
	Ext.apply(_config, config);
	
    TreeSelectPanel.superclass.constructor.call(this, _config);
    
    // add a tree sorter in folder mode
    new Ext.tree.TreeSorter(this, {folderSort:true});
    
    // set the root node
    var root = new Ext.tree.AsyncTreeNode({
        text: 'root', 
        draggable:false, // disable root node dragging
        id:'root'
    });
    this.setRootNode(root);    

    this.getSelectionModel().on({
        'selectionchange' : function(sm, node){
            if(node){
                this.fireEvent('nodeselect', node.attributes);
            }
        },
        scope:this
    });

    this.addEvents({nodeselect:true});
//
//    this.on('contextmenu', this.onContextMenu, this);
};

Ext.extend(TreeSelectPanel, Ext.tree.TreePanel, {
	
	getSelectedNode: function() {
		var node = this.getSelectionModel().getSelectedNode();
		if (node)
			return node.attributes;
		else
			return null;
	}
});
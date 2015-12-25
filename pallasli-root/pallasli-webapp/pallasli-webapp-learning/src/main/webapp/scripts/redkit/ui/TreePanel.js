/**
 * @author torworx
 */

Redkit.TreePanel = function(config) {	
	this._config = {
        id:'nav-panel',        
        title:'TreePanel',
        split:true,
        width: 225,
        minSize: 175,
        maxSize: 400,
        collapsible: true,
        margins:'0 0 5 5',
        cmargins:'0 5 5 5',
        rootVisible: false,
        lines:false,
        autoScroll:true,
        root: {
        	nodeType: 'async',
        	text: 'Root'
        },
        collapseFirst:false
    };
	
	Ext.apply(this._config, config);
	
    Redkit.TreePanel.superclass.constructor.call(this, this._config);

	// Event extention
    this.getSelectionModel().on({
        'beforeselect' : function(sm, node){
            // return node.isLeaf();
			 return this.fireEvent('nodecanselect', node);
        },
        'selectionchange' : function(sm, node){
            if(node){
                this.fireEvent('nodeselect', node.attributes);
            }
        },
        scope:this
    });

    this.addEvents({nodeselect:true});
	this.addEvents({nodecanselect:true});

};

Ext.extend(Redkit.TreePanel, Ext.tree.TreePanel, {

    selectNode: function(url){
        this.getNodeById(url).select();
    },

    removeNode: function(url){
        var node = this.getNodeById(url);
        if(node){
            node.unselect();
            Ext.fly(node.ui.elNode).ghost('l', {
                callback: node.remove, scope: node, duration: .4
            });
        }
    },

    addNode : function(attrs, inactive, preventAnim){
        var exists = this.getNodeById(attrs.url);
        if(exists){
            if(!inactive){
                exists.select();
                exists.ui.highlight();
            }
            return;
        }
		var _atts = {}
		if (attrs.url) {
			_atts = {
				iconCls: 'node-icon',
				leaf: true,
				cls: 'node',
				id: attrs.url
			}
		}
        Ext.apply(_atts, attrs);
        
        var node;
        if ((_atts.nodeType)&&(_atts.nodeType == 'async'))
        	node = new Ext.tree.AsyncTreeNode(_atts)
        else
        	node = Ext.tree.TreeNode(_atts);
        	
		var parent = _atts.parent;		
		
		if (!parent) {
			parent = this.root;
		} 
		else if (typeof parent != 'object') {
			parent = this.getNodeById(parent);
		}
			
        parent.appendChild(node);
        if(!inactive){
            if(!preventAnim){
                Ext.fly(node.ui.elNode).slideIn('l', {
                    callback: node.select, scope: node, duration: .4
                });
            }else{
                node.select();
            }
        }
        return node;
    },

    // prevent the default context menu when you miss the node
    afterRender : function(){
        Redkit.TreePanel.superclass.afterRender.call(this);
        this.el.on('contextmenu', function(e){
            e.preventDefault();
        });
    }
});
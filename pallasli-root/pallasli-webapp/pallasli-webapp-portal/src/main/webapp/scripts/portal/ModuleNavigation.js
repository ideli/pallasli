Ext.define('Pallas.portal.ModuleNavigation', {
	extend : "Ext.tree.Panel",
	menuKey : null,
	appKey : null,
	border : false,
	autoHeight : true,
	collapsible : true,
    rootVisible: false,
    directFn:null,
	openviewInMain : Ext.emptyFn(), 
	initComponent : function() {
		var me = this;
		
		me.store= Ext.create('Ext.data.TreeStore', {
	        root: {
	            expanded: true
	        },
	        fields:['text', 'urlPath','menuCaption','menuTypeCode', 'nodeName','menuTableName','menuWhereSql','leaf', 'expanded','prefixion','menuKey','appKey','tableName'],
	        proxy: {
	            type: 'direct',
	            // directFn: TreeDirect.getTree,
	            // paramOrder: ['treeType','isRoot' ,'prefixion'],
	            // directFn: ModuleNavigationDirect.loadMenus,
	            directFn: me.directFn,
	            paramOrder: ['menuKey','appKey','isRoot','childType','menuTableName' ],
	            extraParams:{'menuKey':-1,'appKey':me.appKey,'isRoot':true,'childType':1,'menuTableName':''},
		        reader: {
		            root: 'elements'
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
	            beforeload : function(store, operation,e,d,f) { 
	                var new_params = { 
	                		appKey:me.appKey,
	                		prefixion : operation.node.data.prefixion?operation.node.data.prefixion:"",
	                		menuKey :  operation.node.data.menuKey?operation.node.data.menuKey:me.menuKey,
	                        isRoot : operation.node.isRoot(),
	                		menuTableName:operation.node.data.menuTableName,
	                        childType:(operation.node.data.menuTypeCode=='40')?0:1
	                };    
	                
	                Ext.apply(store.proxy.extraParams, new_params); 
	            },
	            load:function(tree,store, node){
	               // console.log(node.expand);
	                //if(node.expand)node.expand();
	            } 
	            
	        }
	    });
		me.on('itemdblclick', function(panel,record, item, index,  e,  eOpts){   
			me.desktop.openView("manager",record.data.menuKey,{url:record.data.urlPath,title:record.data.menuCaption});
		    
	    });
		me.on('itemclick', function(panel,record, item, index,  e,  eOpts){  
			record.data.appKey=me.appKey;
			Ext.apply(record.data,{url:record.data.urlPath,title:record.data.menuCaption});
			Pallas.portal.SinglePortal.openView(record.data.appKey,record.data.menuKey,record.data);
		    
	    });
		me.on('itemcontextmenu',function(panel,record, item, index,  e,  eOpts){
    		var contextmenu=Ext.create('Ext.menu.Menu', {
    		    width: 100,
    		    autoHeight: true,
    		    plain: true,
    		    floating: true,  // usually you want this set to True (default)
    		    items: [{
    		        text: '刷新',handler:function(){
    		        	 
    		        }
    		    },{
    		        text: '删除'
    		    },{
    		        text: '打开',handler:function(){
    		        	Pallas.portal.SinglePortal.openView(record.data.appKey,record.data.menuKey,{url:record.data.urlPath,title:record.data.menuCaption});
    		        }
    		    }]
    		});
    		contextmenu.showAt(e.browserEvent.clientX,e.browserEvent.clientY);
	 });
        me.callParent();
	}
});

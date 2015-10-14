Ext.define('Pallas.design.portal.ModuleTreePanel', {
	extend : "Ext.tree.Panel",
	requires : ['Pallas.design.designWin.DesignWindow'],
	menuKey : null,
	border : false,
	autoHeight : true,
	collapsible : false,
    rootVisible: false,
	openviewInMain : Ext.emptyFn(), 
	sysMenuPage:null,
	appKey:"pallas_design",
	initComponent : function() {
		var me = this;
		me.store= Ext.create('Ext.data.TreeStore', {
	        root: {
	            expanded: true
	        },
		    fields:['menuId','text', 'urlPath','menuTypeCode', 'nodeName',
		            'menuTableName','menuWhereSql','leaf', 'expanded',
		            'prefixion','menuKey','tableName','fieldsetName','pageName'],
	        proxy: {
	            type: 'direct',
	            // directFn: TreeDirect.getTree,
	            // paramOrder: ['treeType','isRoot' ,'prefixion'],
	              directFn: MenuDirectAction.loadModuleTreeMenus,
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
	                		menuKey : operation.node.data.menuKey?operation.node.data.menuKey:me.menuKey,
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
		me.on('itemdblclick', function(grid, rowindex, e){      
	    });
		me.on('itemcontextmenu',function(panel,record, item, index,  e,  eOpts){
    		var contextmenu=Ext.create('Ext.menu.Menu', {
    		    width: 100,
    		    autoHeight: true,
    		    plain: true,
    		    floating: true,  // usually you want this set to True (default)
    		    items: [{
    		        text: '刷新',
    		        hidden:true,
    		        handler:function(){
    		        	me.store.reload();
   		        }
	   		    },{
			        text: '增加',
    		        hidden:true,
    		        handler:function(){
			        	Ext.create("Pallas.common.window.Form").createWindow().show();
			        }
			    },{
    		        text: '修改',
    		        handler:function(){
    		        	console.log(record.data.menuId);
    		        	 var SimpleMenu = Ext.ModelManager.getModel('SimpleMenu');
    		        	 SimpleMenu.load(record.data.menuId,{
    		        		success:function(r){
    		        			console.log(r);
    		        			Ext.create("Pallas.common.window.Form",{initValues:r.data}).createWindow().show();    		        	 

    		        		}
    		        	});
			            		        }
    		    },{
    		        text: '删除',
    		        handler:function(){
    		        	 Ext.Msg.show({
    		        		 title:'是否删除?',
    		        		 msg: '你确定要删除菜单【'+record.data.text+'】吗?',
    		        		 buttons: Ext.Msg.YESNOCANCEL,
    		        		 icon: Ext.Msg.QUESTION
    		        	 });
    		        }
    		    },{
    		        text: '设计',
    		        handler:function(){
    		        	var tmpTab=me.sysMenuPage.getActiveTab();
    		        	
    		    		var win=new Pallas.design.designWin.DesignWindow({initComponents:tmpTab.items.items}).createWindow();
    		        	win.on(
    		    				"beforeclose",
    		    				function() {
    		    					if (win.items.items[2].items.items) {
    		    						var len = win.items.items[2].items.items.length;
    		    						for (var tmpi = 0; tmpi < len; tmpi++) {
    		    							tmpTab.add(win.items.items[2].items.items[0]);

    		    						}
    		    						tmpTab.doLayout();

    		    					}
    		    				});
    		        
    		        }
    		    },{
    		        text: '打开',
    		        handler:function(){
    		        	me.open(me.sysMenuPage);
    		        }
    		    }]
    		}); 
    		contextmenu.items.items[0].hidden=(record.data.leaf||false);
    		contextmenu.items.items[1].hidden=(record.data.leaf||false);
    		contextmenu.items.items[2].hidden=(record.data.menuId?false:true);
    		contextmenu.showAt(e.browserEvent.clientX,e.browserEvent.clientY);
	 });
        me.callParent();
	}
});

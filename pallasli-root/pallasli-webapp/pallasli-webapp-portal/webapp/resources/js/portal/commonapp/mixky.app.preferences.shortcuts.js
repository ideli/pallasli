
Ext.namespace("Mixky.wasoft.lib");

Mixky.wasoft.lib.PreferencesShortcuts = function(){

	var tree = new Ext.tree.TreePanel({
    	region : 'west',
		rootVisible: false,
    	autoScroll : true,
    	split : false,
    	width : 200,
		loader: new Ext.tree.TreeLoader({
        	paramOrder:[],
            directFn: DesktopDirect.getShortcuts,
            listeners : {
				'load' : function(loader, node){
					node.eachChild(function(child){
						if(MixkyApp.hasShortcut(child.attributes.btntype, child.attributes.applicationkey, child.attributes.key)){
							child.getUI().toggleCheck(true);
						}
					});
				},
				'beforeload' : function(loader, node){
					var key = node.attributes.key;
					if(Ext.isDefined(key)){
						try{
							var app = Mixky.wasoft.cache.Applications[key];
							var fn = eval(app.keyPrefix + 'AppDirect.getQuickStarts');
							if(typeof(fn) != 'function'){
								return false;
								alert(app.keyPrefix + 'AppDirect.getQuickStarts is not a function');
							}
							loader.directFn = fn;
						}catch(e){
							alert('no found ' + key + 'AppDirect.getQuickStarts function');
							return false;
						}
					}else{
						loader.directFn = DesktopDirect.getQuickStarts;
					}
				}
			}
		}),
		listeners: {
			'checkchange': function(node, checked){
				if(node.leaf && node.id){
		    		if(checked){
						if(!MixkyApp.hasShortcut(node.attributes.btntype, node.attributes.applicationkey, node.attributes.key)){
							MixkyApp.addShortcut({
								text : node.attributes.text, 
								iconCls : node.attributes.iconCls, 
								btntype : node.attributes.btntype, 
								applicationkey : node.attributes.applicationkey,
								key : node.attributes.key
							});
						}
		    		}else{
		    			MixkyApp.removeShortcut(node.attributes.btntype, node.attributes.applicationkey, node.attributes.key);
		    		}
		    	}
		    	node.ownerTree.selModel.select(node);
			}
		},
		root : {id : 'root',text : '桌面按钮'}
	});
	
	var note = new Ext.Panel({
    	region : 'center',
    	border : false,
    	html : '选择桌面按钮'
	});
	
	var panel = new Ext.Panel({
		layout : 'border',
		title : '桌面按钮',
        padding : '5px',
		border : false,
        iconCls : 'icon-portal-shortcut',
		items : [tree, note]
	})
	
	return panel;
}
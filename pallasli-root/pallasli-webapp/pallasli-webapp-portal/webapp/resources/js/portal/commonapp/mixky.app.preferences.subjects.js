
Ext.namespace("Mixky.wasoft.lib");

Mixky.wasoft.lib.PreferencesSubjects = function(){

	var tree = new Ext.tree.TreePanel({
    	region : 'west',
		rootVisible: false,
    	autoScroll : true,
    	split : false,
    	width : 200,
		loader: new Ext.tree.TreeLoader({
        	paramOrder:[],
            directFn: DesktopDirect.getSubjects,
            listeners : {
				'load' : function(loader, node){
					node.eachChild(function(child){
						if(MixkyApp.hasSubject(child.attributes.applicationkey, child.attributes.key)){
							child.getUI().toggleCheck(true);
						}
					});
				},
				'beforeload' : function(loader, node){
					var key = node.attributes.key;
					if(Ext.isDefined(key)){
						try{
							var app = Mixky.wasoft.cache.Applications[key];
							var fn = eval(app.keyPrefix + 'AppDirect.getSubjects');
							if(typeof(fn) != 'function'){
								return false;
								alert(app.keyPrefix + 'AppDirect.getSubjects is not a function');
							}
							loader.directFn = fn;
						}catch(e){
							alert('no found ' + key + 'AppDirect.getSubjects function');
							return false;
						}
					}else{
						loader.directFn = DesktopDirect.getSubjects;
					}
				}
			}
		}),
		listeners: {
			'checkchange': function(node, checked){
				if(node.leaf && node.id){
		    		if(checked){
						if(!MixkyApp.hasSubject(node.attributes.applicationkey, node.attributes.key)){
							MixkyApp.addSubject({
								applicationkey : node.attributes.applicationkey,
								key : node.attributes.key,
								text : node.attributes.text,
								iconCls : node.attributes.iconCls,
								width : 300,
								height : 300,
								webheight :300,
								left : 100,
								top : 50
							});
						}
		    		}else{
		    			MixkyApp.removeSubject(node.attributes.applicationkey, node.attributes.key);
		    		}
		    	}
		    	node.ownerTree.selModel.select(node);
			}
		},
		root : {id : 'root',text : '桌面栏目'}
	});
	
	var note = new Ext.Panel({
    	region : 'center',
    	border : false,
    	html : '选择桌面栏目'
	});
	
	var panel = new Ext.Panel({
		layout : 'border',
		title : '桌面栏目',
        padding : '5px',
		border : false,
        iconCls : 'icon-portal-subject',
		items : [tree, note]
	})
	
	return panel;
}
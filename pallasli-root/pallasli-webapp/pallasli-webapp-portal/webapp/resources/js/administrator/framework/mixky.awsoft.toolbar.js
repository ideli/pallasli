Mixky.awsoft.Toolbar = function(cfg){
	if(!cfg){cfg = {};}
	Ext.apply(cfg, {
		items:[{
			xtype:'buttongroup',
			//title:'对象操作',
			columns:4,
			/*defaults: {
	            scale: 'small',
	            iconCls: 'icon-administrator-button-default'
	        },*/
	        items:[
		      //  Mixky.awsoft.lib.Actions.Add,
		       // Mixky.awsoft.lib.Actions.Copy,
		      //  Mixky.awsoft.lib.Actions.Delete,
		      //  Mixky.awsoft.lib.Actions.Paste,
		      //  Mixky.awsoft.lib.Actions.Save,
		        Mixky.awsoft.lib.Actions.Open,
		        Mixky.awsoft.lib.Actions.Refresh
	        ]
		},'->',{
			xtype:'buttongroup',
			columns:5,
			/*defaults: {
	            iconCls: 'icon-administrator-button-default'
	    	},*/
	        items:[
		       Mixky.awsoft.lib.Actions.SyncToApplication,
		     //  Mixky.awsoft.lib.Actions.SyncToIMServer,
		       Mixky.awsoft.lib.Actions.BuildFiles
	        ]
		}]
	})
	Mixky.awsoft.Toolbar.superclass.constructor.call(this, cfg);
}

Ext.extend(Mixky.awsoft.Toolbar, Ext.Toolbar, {});

// Configuration:
//    url : URL to load nodes
TextEditorPanel = function(config) {
	
	var _config = {
        baseCls: 'x-plain',
        region: 'center',
        labelWidth: 55,
		
        items: [{
            xtype: 'hidden',
            name: 'id'
        }, {
            xtype: 'textarea',
            hideLabel: true,
            name: 'text',
            anchor: '100% 0'  // anchor width by percentage and height by raw adjustment
        }]
    }
    
	Ext.apply(_config, config);
	
    TextEditorPanel.superclass.constructor.call(this, _config);
};

Ext.extend(TextEditorPanel, Ext.form.FormPanel, {
	loadData: function(data) {
		if (data) {
			this.getForm().loadRecord(new Ext.data.Record(data));
		}
	}
});
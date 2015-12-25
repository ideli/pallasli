/*
 * data: for callback parameter;
 * callback: callback function for get the input query;
 * 
 * title, submit_text, cancel_text, label_text: some label text
 */ 


Redkit.QueryDialog = function(cfg) {
    Ext.apply(this, cfg);
    
    this.addEvents({
        'callback' : true
    });
    
    this.init(cfg);
}

Ext.extend(Redkit.QueryDialog, Ext.util.Observable, {
	title: 'Query Dialog',
	submit_text: 'Submit',
	cancel_text: 'Cancel',
	lable_text: 'input',
	
	initialConfig: null,
	
	result: null,
	
	inputForm: null,
	
	callback: Ext.emptyFn,
	
	win: null,
	init: function(cfg) {
		this.initialConfig = cfg;
		
		this.inputForm = new Ext.FormPanel({
			frame: true,
			region: 'center',
			border: false,
			items: [{
				xtype: 'textfield',
				name: 'input',
				fieldLabel: this.lable_text,
				anchor:'98%'
			}]			
		});

		var _cfg = {			
			title: this.title,
            layout: 'border',
            width: 400,
            height: 110,
            modal: true,
            border: false,
            bodyBorder: false, 
            items: [this.inputForm],
			buttonAlign: 'center',
			buttons: [{
				text: this.submit_text,
				owner: this,
				handler: this.onSubmit
			},{
				text: this.cancel_text,
				owner: this,
				handler: this.onCancel
			}]			
		};		
		if (cfg)
			Ext.apply(_cfg, cfg.win);	
		this.win = new Ext.Window(_cfg);
		this.win.on('close', this.onWindowClose, this);
	},
	
	show: function() {
		this.win.show();
	},
	
	onSubmit: function(btn) {
		btn.owner.result = btn.owner.inputForm.getForm().getValues(false).input;
		btn.owner.win.close();
	},
	
	onCancel: function(btn) {
		btn.owner.result = null;
		btn.owner.win.close();
	},
	
	onWindowClose: function(win) {
		if ((this.result)&&(this.callback))
			this.callback(this.initialConfig.data, this.result);
	}
})
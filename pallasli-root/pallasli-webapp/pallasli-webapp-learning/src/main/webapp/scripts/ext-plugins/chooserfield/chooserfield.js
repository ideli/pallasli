/**
 * @author taoyuan
 */
// ChooserField
//
// Extension to Ext JS TriggerField to launch the image chooser
// and put the selected file name in the attached text field.
//
// Author: Yuan Tao
//
// This code can be used for any purpose, subject to the terms 
// of the Ext JS library.
//
// http://www.extjs.com/license.txt
//

// Configuration:
//    loadUrl : URL to load images with
//    urlParams : parameters for the URL (?name=value&name=value...)
//    dlgWidth : initial width of chooser dialog
//    dlgHeight : initial height of chooser dialog

Ext.form.ChooserField = function(config){
    Ext.form.ChooserField.superclass.constructor.call(this, config);
};

Ext.extend(Ext.form.ChooserField, Ext.form.TriggerField,  {
    triggerClass : 'x-form-browse-trigger',
    defaultAutoCreate : {tag: "input", type: "text", size: "10", autocomplete: "off"},


	    // private
    initComponent : function(){
        Ext.form.ComboBox.superclass.initComponent.call(this);
		if (this.chooser)
			this.chooser.callback = this.setField.createDelegate(this);
			
	},
    // Get the current value of our text field.
    
    getValue : function(){
        return Ext.form.ChooserField.superclass.getValue.call(this) || "";
    },

    // Set the current value of our text field.
    
    setValue : function(text){
        Ext.form.ChooserField.superclass.setValue.call(this, text);
    },

    // Trigger button clicked, create and show chooser dialog.
    
    onTriggerClick : function(){
      if(this.disabled){
        return;
      }
	  
	  if (this.chooser)
	  	this.chooser.show();
//      if (!this.chooser)
//        this.chooser = new ImageChooser({url: this.url,
//                                        params: this.params,
//                                        width: this.dlgWidth,
//                                        height: this.dlgHeight});
//      this.chooser.show(null, this.setField.createDelegate(this));
    },

    // Callback from chooser, put the selected file name in our text field.
    
    setField : function(data) {
		var fieldName = this.displayField ? this.displayField : this.valueField;
		
		this.setValue(fieldName ? data[fieldName] : data);

		var form = this.getForm();
			
		if (form && this.hiddenField) {
			var field = form.findField(this.hiddenField);
			if (field) {
				field.setValue(this.valueField ? data[this.valueField] : data);
			}
		}
	  	this.onSetField(data);
	  	return true;
    },
	
	onSetField : Ext.emptyFn,
	
	isFormPanel: function(c) {
		return ((c)&&(c.createForm)&&(c.initFields)&&(c.getForm));
	},
	
	getForm: function() {
		if (!this.form) {			
			var c = this.ownerCt;
			while (c && !this.isFormPanel(c))
				c = c.ownerCt;
			if (c)
				this.form = c.getForm();
		}
		return this.form;
	},

    url : '',
    params : '',
    dlgWidth : 600,
    dlgHeight : 400,
    chooser: null,
	
	form: null,
	
	valueField: null,
	displayField: null,
	hiddenField: null
});
Ext.reg('chooserfield', Ext.form.ChooserField);
Mixky.plugins.FormFieldFocus = function(fieldname){
	this.fieldname = fieldname;
}

Mixky.plugins.FormFieldFocus.prototype = {
	fieldname : undefined,
	init : function(form){
		form.on('afterrender', function(){
			var fields = this.getForm().items;
			var field = undefined;
			for(var i=0;i<fields.getCount();i++){
				var f = fields.get(i);
				var xtype = f.getXType();
				if(xtype && f.readOnly !== true && xtype != 'mixkydisplayfield' && xtype != 'hidden'){
					f.focus.defer(1500, f);
					return;
				}
			}
		});
	}
}
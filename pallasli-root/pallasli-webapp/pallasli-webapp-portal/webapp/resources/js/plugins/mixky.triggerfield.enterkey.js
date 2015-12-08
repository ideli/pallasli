Mixky.plugins.TriggerFieldEnter = function(fn){
	this.fnEnterKey = fn;
}

Mixky.plugins.TriggerFieldEnter.prototype = {
	onKeyDown: function(e){
		if(e.getKey() == e.ENTER){
			e.preventDefault();
			this.fnEnterKey.call(this.field);
		}
	},
	init : function(field){
		this.field = field;
		field.on('afterrender', function(){
			this.field.mon(this.field.el, 'keydown', this.onKeyDown, this);
		}, this);
	},
	destroy : function(){
		this.field.mun(this.field.el, 'keydown', this.onKeyDown, this);
	}
}
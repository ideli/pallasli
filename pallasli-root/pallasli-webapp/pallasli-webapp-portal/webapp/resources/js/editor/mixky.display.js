
Ext.namespace("Mixky.editor");

Mixky.editor.DisplayField = Ext.extend(Ext.form.TextField,  {
    
    applicationkey : undefined,
    
    dictionaryname : undefined,
    
    remoterender : undefined,
    
    params : undefined,
    
    dourl : undefined,
    
    readOnly : true,
    
    anchor : '100%',
    
    selectOnFocus : true,
    
    hideDisplayBorder : undefined,
    
    listeners : {
    	'focus': function(t){
    		t.blur();
    	}
    },
    
    onRender:function (B, A) {
		Mixky.editor.DisplayField.superclass.onRender.call(this, B, A);
		if(this.hideDisplayBorder){
			this.el.applyStyles('background:transparent; border-right: 0px solid;border-top: 0px solid;border-left: 0px solid;border-bottom: #000000 0px solid;');
		}
	}
	
 /*   defaultAutoCreate:{tag:"input", type:"hidden", name:this.name},
    
    htmlEncode: false,
    
    dictionaryname : undefined,
    
    remoterender : undefined,
    
    params : undefined,
    
    dourl : undefined,
    
    url : undefined,
    
    hideDisplayBorder : undefined,
    
    initEvents : function(){
       this.mon(this.displayEl, 'mouseover', this.onMouseover, this);
    },
    
	onRender:function (B, A) {
		Mixky.editor.DisplayField.superclass.onRender.call(this, B, A);
		this.wrap = this.el.wrap();
		if(this.hideDisplayBorder){
			var style = Ext.isDefined(this.style) ? 'color:Blue;padding:3px;' + this.style : 'color:Blue;padding:3px;';
			this.displayEl = this.wrap.createChild({tag:"div",style:style});
		}else{
			var style='';
			if(Ext.isIE){
			    style = Ext.isDefined(this.style) ? 'color:Blue;border:0px solid #C0C0C0;padding:3px;width:100%;' + this.style : 'color:Blue;border:0px solid #C0C0C0;padding:3px;width:100%;';
			}
			else{
				style = Ext.isDefined(this.style) ? 'color:Blue;border:0px solid #C0C0C0;padding:3px;' + this.style : 'color:Blue;border:0px solid #C0C0C0;padding:3px;';
			}
			if(Ext.isIE){
			    style = Ext.isDefined(this.style) ? 'color:Blue;border:1px solid #C0C0C0;padding:3px;' + this.style : 'color:Blue;border:1px solid #C0C0C0;padding:3px;';
			}
			else{
				style = Ext.isDefined(this.style) ? 'color:Blue;border:1px solid #C0C0C0;padding:3px;' + this.style : 'color:Blue;border:1px solid #C0C0C0;padding:3px;';
			}
			this.displayEl = this.wrap.createChild({tag:"div",style:style, name:this.name});
		}
		this.displayEl.dom.innerHTML = '&nbsp;';
	},
	
	onMouseover :function(){
        this.displayEl.dom.setAttribute("ext:qtip", this.getValue());   
    },

    setValue : function(v){
		Mixky.editor.DisplayField.superclass.setValue.call(this, v);
		//var display = v;
		if(this.remoterender){
			this.renderValue(v);
		}else{
			this.setRawValue(v);
		}
        return this;
    },
    setRawValue : function(v){
    	var display = v;
        if(this.htmlEncode){
        	display = Ext.util.Format.htmlEncode(v);
        }
        if(display && typeof(display) == "string"){
        	display = display.replaceAll('\n', '<BR>').replaceAll('\t', '&emsp;').replaceAll(' ', '&nbsp;');
        }
		this.displayEl.dom.innerHTML = (Ext.isEmpty(display) ? '&nbsp;' : display);
    },
    renderValue : function(v){
    	var field = this;
    	var display = v;
    	if(Ext.isDefined(this.dictionaryname)){
    		display = Mixky.wasoft.lib.dictionaryRenderer(this.applicationkey, v, this.dictionaryname);
        	
    	}else if(Ext.isDefined(this.dourl)){
    		Mixky.wasoft.lib.getDictionaryDourlRender(this.applicationkey, this.dourl, v, this.setRawValue.createDelegate(this), this.params);
    	}else if(Ext.isDefined(this.url)){
    		Mixky.wasoft.lib.getDictionaryUrlRender(this.applicationkey, this.url, v, this.setRawValue.createDelegate(this));
    	}else if(Ext.isDefined(this.directFn)){
			Mixky.wasoft.lib.getDictionaryDirectRender(this.applicationkey, this.directFn, this.setRawValue.createDelegate(this), this.params, v, this.document);
		}
    	this.setRawValue(display);
    }*/
});

Ext.reg('mixkydisplayfield', Mixky.editor.DisplayField);

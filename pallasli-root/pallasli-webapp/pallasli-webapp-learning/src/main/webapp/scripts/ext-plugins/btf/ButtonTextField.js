/**
 * @author taoyuan
 */

Ext.form.ButtonTextField = Ext.extend(Ext.form.TextField,  {
    /**
     * @cfg {String} buttonText The button text to display on the upload button (defaults to
     * 'Browse...').  Note that if you supply a value for {@link #buttonCfg}, the buttonCfg.text
     * value will be used instead if available.
     */
    buttonText: '...',
    /**
     * @cfg {Boolean} buttonOnly True to display the file upload field as a button with no visible
     * text field (defaults to false).  If true, all inherited TextField members will still be available.
     */
    buttonOnly: false,
    /**
     * @cfg {Number} buttonOffset The number of pixels of space reserved between the button and the text field
     * (defaults to 3).  Note that this only applies if {@link #buttonOnly} = false.
     */
    buttonOffset: 3,
	
	buttonClick: Ext.emptyFn,
	
    /**
     * @cfg {Object} buttonCfg A standard {@link Ext.Button} config object.
     */

    // private
    readOnly: true,
    
    /**
     * @hide 
     * @method autoSize
     */
    autoSize: Ext.emptyFn,
    
    // private
    initComponent: function(){
        Ext.form.ButtonTextField.superclass.initComponent.call(this);
        
        this.addEvents(
            /**
             * @event fileselected
             * Fires when the underlying file input field's value has changed from the user
             * selecting a new file from the system file selection dialog.
             * @param {Ext.form.ButtonTextField} this
             */
            'buttonclick'
        );
    },
    
    // private
    onRender : function(ct, position){
        Ext.form.ButtonTextField.superclass.onRender.call(this, ct, position);
        
        this.wrap = this.el.wrap({cls:'x-form-field-wrap x-form-bft-wrap'});
        this.el.addClass('x-form-bft-text');
//        this.el.dom.removeAttribute('name');
        
        this.btnPlaceholder = this.wrap.createChild({
            id: this.getButtonId(),
            name: this.getButtonId(),
            cls: 'x-form-file',
			tag: 'div', 
            size: 1
        });
		
		var btf = this;
        
        var btnCfg = Ext.applyIf(this.buttonCfg || {}, {
            text: this.buttonText
        });
        this.button = new Ext.Button(Ext.apply(btnCfg, {
            renderTo: this.wrap,
            cls: 'x-form-bft-btn' + (btnCfg.iconCls ? ' x-btn-icon' : ''),
			handler: function(btn) {
				if ((btf.buttonClick)&&(btf.buttonClick != Ext.emptyFn))
					btf.buttonClick(btf);
				else
					btf.fireEvent('buttonclick', btf);
			}
        }));
        
        if(this.buttonOnly){
            this.el.hide();
            this.wrap.setWidth(this.button.getEl().getWidth());
        }
    },
    
    // private
    getButtonId: function(){
        return this.id+'-button';
    },
    
    // private
    onResize : function(w, h){
        Ext.form.ButtonTextField.superclass.onResize.call(this, w, h);
        
        this.wrap.setWidth(w);
        
        if(!this.buttonOnly){
            var w = this.wrap.getWidth() - this.button.getEl().getWidth() - this.buttonOffset;
            this.el.setWidth(w);
        }
    },
    
    // private
    preFocus : Ext.emptyFn,
    
    // private
    getResizeEl : function(){
        return this.wrap;
    },

    // private
    getPositionEl : function(){
        return this.wrap;
    },

    // private
    alignErrorIcon : function(){
        this.errorIcon.alignTo(this.wrap, 'tl-tr', [2, 0]);
    }
    
});
Ext.reg('buttontextfield', Ext.form.ButtonTextField);
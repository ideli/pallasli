
Ext.namespace("Atwa.editor");

Atwa.editor.NumberTrigger = Ext.extend(Ext.form.TextField,  {
    allowDecimals : false,
    decimalSeparator : ".",
    decimalPrecision : 2,
    allowNegative : false,
    minValue : Number.NEGATIVE_INFINITY,
    maxValue : Number.MAX_VALUE,
    minText : "The minimum value for this field is {0}",
    maxText : "The maximum value for this field is {0}",
    nanText : "{0} is not a valid number",
    baseChars : "0123456789",
    style : 'ime-mode:disabled',

    // private
    initEvents : function(){
        var allowed = this.baseChars + '';
        if (this.allowDecimals) {
            allowed += this.decimalSeparator;
        }
        if (this.allowNegative) {
            allowed += '-';
        }
        this.maskRe = new RegExp('[' + Ext.escapeRe(allowed) + ']');
        Ext.form.NumberField.superclass.initEvents.call(this);
    },
    
    /**
     * Runs all of NumberFields validations and returns an array of any errors. Note that this first
     * runs TextField's validations, so the returned array is an amalgamation of all field errors.
     * The additional validations run test that the value is a number, and that it is within the
     * configured min and max values.
     * @param {Mixed} value The value to get errors for (defaults to the current field value)
     * @return {Array} All validation errors for this field
     */
    getErrors: function(value) {
        var errors = Ext.form.NumberField.superclass.getErrors.apply(this, arguments);
        
        value = value || this.processValue(this.getRawValue());
        
        if (value.length < 1) { // if it's blank and textfield didn't flag it then it's valid
             return errors;
        }
        
        value = String(value).replace(this.decimalSeparator, ".");
        
        if(isNaN(value)){
            errors.push(String.format(this.nanText, value));
        }
        
        var num = this.parseValue(value);
        
        if(num < this.minValue){
            errors.push(String.format(this.minText, this.minValue));
        }
        
        if(num > this.maxValue){
            errors.push(String.format(this.maxText, this.maxValue));
        }
        
        return errors;
    },

    /**
     * Replaces any existing {@link #minValue} with the new value.
     * @param {Number} value The minimum value
     */
    setMinValue : function(value){
        this.minValue = Ext.num(value, Number.NEGATIVE_INFINITY);
    },
    
    /**
     * Replaces any existing {@link #maxValue} with the new value.
     * @param {Number} value The maximum value
     */
    setMaxValue : function(value){
        this.maxValue = Ext.num(value, Number.MAX_VALUE);    
    },

    // private
    parseValue : function(value){
        value = parseFloat(String(value).replace(this.decimalSeparator, "."));
        return isNaN(value) ? '' : value;
    }
   
});

Ext.reg('numberText', Atwa.editor.NumberTrigger);
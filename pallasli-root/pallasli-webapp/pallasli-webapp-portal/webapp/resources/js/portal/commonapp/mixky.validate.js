Ext.form.ComboBox.prototype.validateValue = function(value){
    if(Ext.isFunction(this.validator)){
        var msg = this.validator(value);
        if(msg !== true){
            this.markInvalid(msg);
            return false;
        }
    }
    if(value.length < 1 || value === this.emptyText){ // if it's blank
         if(this.allowBlank){
             this.clearInvalid();
             return true;
         }else{
             this.markInvalid(this.blankText);
             return false;
         }
    }
    return true;
}

Cls.form.DateTimeField.prototype.validateValue = function(value){
    if(Ext.isFunction(this.validator)){
        var msg = this.validator(value);
        if(msg !== true){
            this.markInvalid(msg);
            return false;
        }
    }
    if(value.length < 1 || value === this.emptyText){ // if it's blank
         if(this.allowBlank){
             this.clearInvalid();
             return true;
         }else{
             this.markInvalid(this.blankText);
             return false;
         }
    }
    return true;
}
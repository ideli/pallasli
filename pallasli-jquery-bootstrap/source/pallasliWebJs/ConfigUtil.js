
Ext.Config = function(name) {
    
    
    var me = this,
        capitalizedName = name.charAt(0).toUpperCase() + name.substr(1);
    
    me.name = name;
    
    me.names = {
        internal: '_' + name,
        initializing: 'is' + capitalizedName + 'Initializing',
        apply: 'apply' + capitalizedName,
        update: 'update' + capitalizedName,
        get: 'get' + capitalizedName,
        set: 'set' + capitalizedName,
        initGet: 'initGet' + capitalizedName,
        doSet: 'doSet' + capitalizedName,
        changeEvent: name.toLowerCase() + 'change'
    };
    
    
    me.root = me;
};
Ext.Config.map = {};
Ext.Config.get = function(name) {
    var map = Ext.Config.map,
        ret = map[name] || (map[name] = new Ext.Config(name));
    return ret;
};
Ext.Config.prototype = {
    self: Ext.Config,
    isConfig: true,
    
    
    
    getGetter: function() {
        return this.getter || (this.root.getter = this.makeGetter());
    },
    getInitGetter: function() {
        return this.initGetter || (this.root.initGetter = this.makeInitGetter());
    },
    getSetter: function() {
        return this.setter || (this.root.setter = this.makeSetter());
    },
    
    getInternalName: function(target) {
        return target.$configPrefixed ? this.names.internal : this.name;
    },
    mergeNew: function(newValue, oldValue, target, mixinClass) {
        var ret, key;
        if (!oldValue) {
            ret = newValue;
        } else if (!newValue) {
            ret = oldValue;
        } else {
            ret = Ext.Object.chain(oldValue);
            for (key in newValue) {
                if (!mixinClass || !(key in ret)) {
                    ret[key] = newValue[key];
                }
            }
        }
        return ret;
    },
    
    mergeSets: function(newValue, oldValue, preserveExisting) {
        var ret = oldValue ? Ext.Object.chain(oldValue) : {},
            i, val;
        if (newValue instanceof Array) {
            for (i = newValue.length; i--; ) {
                val = newValue[i];
                if (!preserveExisting || !(val in ret)) {
                    ret[val] = true;
                }
            }
        } else if (newValue) {
            if (newValue.constructor === Object) {
                for (i in newValue) {
                    val = newValue[i];
                    if (!preserveExisting || !(i in ret)) {
                        ret[i] = val;
                    }
                }
            } else if (!preserveExisting || !(newValue in ret)) {
                ret[newValue] = true;
            }
        }
        return ret;
    },
    
    
    makeGetter: function() {
        var name = this.name,
            prefixedName = this.names.internal;
        return function() {
            var internalName = this.$configPrefixed ? prefixedName : name;
            return this[internalName];
        };
    },
    makeInitGetter: function() {
        var name = this.name,
            names = this.names,
            setName = names.set,
            getName = names.get,
            initializingName = names.initializing;
        return function() {
            var me = this;
            me[initializingName] = true;
            
            delete me[getName];
            me[setName](me.config[name]);
            delete me[initializingName];
            return me[getName].apply(me, arguments);
        };
    },
    makeSetter: function() {
        var name = this.name,
            names = this.names,
            prefixedName = names.internal,
            getName = names.get,
            applyName = names.apply,
            updateName = names.update,
            setter;
        
        
        setter = function(value) {
            var me = this,
                internalName = me.$configPrefixed ? prefixedName : name,
                oldValue = me[internalName];
            
            delete me[getName];
            if (!me[applyName] || (value = me[applyName](value, oldValue)) !== undefined) {
                
                
                if (value !== (oldValue = me[internalName])) {
                    me[internalName] = value;
                    if (me[updateName]) {
                        me[updateName](value, oldValue);
                    }
                }
            }
            return me;
        };
        setter.$isDefault = true;
        return setter;
    }
};

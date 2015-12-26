
(function() {
    function toString() {
        var me = this,
            cls = me.sourceClass,
            method = me.sourceMethod,
            msg = me.msg;
        if (method) {
            if (msg) {
                method += '(): ';
                method += msg;
            } else {
                method += '()';
            }
        }
        if (cls) {
            method = method ? (cls + '.' + method) : cls;
        }
        return method || msg || '';
    }
    Ext.Error = function(config) {
        if (Ext.isString(config)) {
            config = {
                msg: config
            };
        }
        var error = new Error();
        Ext.apply(error, config);
        error.message = error.message || error.msg;
        
        
        error.toString = toString;
        return error;
    };
    Ext.apply(Ext.Error, {
        
        ignore: false,
        
        raise: function(err) {
            err = err || {};
            if (Ext.isString(err)) {
                err = {
                    msg: err
                };
            }
            var me = this,
                method = me.raise.caller,
                msg, name;
            if (method) {
                if (!err.sourceMethod && (name = method.$name)) {
                    err.sourceMethod = name;
                }
                if (!err.sourceClass && (name = method.$owner) && (name = name.$className)) {
                    err.sourceClass = name;
                }
            }
            if (me.handle(err) !== true) {
                msg = toString.call(err);
                Ext.log({
                    msg: msg,
                    level: 'error',
                    dump: err,
                    stack: true
                });
                throw new Ext.Error(err);
            }
        },
        
        handle: function() {
            return this.ignore;
        }
    });
})();
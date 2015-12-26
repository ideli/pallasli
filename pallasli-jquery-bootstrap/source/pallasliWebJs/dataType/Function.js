
Ext.Function = (function() {
    
    
    
    
    var lastTime = 0,
        animFrameId,
        animFrameHandlers = [],
        animFrameNoArgs = [],
        idSource = 0,
        animFrameMap = {},
        win = window,
        requestAnimFrame = win.requestAnimationFrame || win.webkitRequestAnimationFrame || win.mozRequestAnimationFrame || win.oRequestAnimationFrame || function(callback) {
            var currTime = Ext.now(),
                timeToCall = Math.max(0, 16 - (currTime - lastTime)),
                id = win.setTimeout(function() {
                    callback(currTime + timeToCall);
                }, timeToCall);
            lastTime = currTime + timeToCall;
            return id;
        },
        fireHandlers = function() {
            var len = animFrameHandlers.length,
                id, i, handler;
            animFrameId = null;
            
            for (i = 0; i < len; i++) {
                handler = animFrameHandlers[i];
                id = handler[3];
                
                if (animFrameMap[id]) {
                    handler[0].apply(handler[1] || Ext.global, handler[2] || animFrameNoArgs);
                    delete animFrameMap[id];
                }
            }
            
            
            animFrameHandlers = animFrameHandlers.slice(len);
        },
        fireElevatedHandlers = function() {
            Ext.elevateFunction(fireHandlers);
        },
        ExtFunction = {
            
            flexSetter: function(setter) {
                return function(name, value) {
                    var k, i;
                    if (name !== null) {
                        if (typeof name !== 'string') {
                            for (k in name) {
                                if (name.hasOwnProperty(k)) {
                                    setter.call(this, k, name[k]);
                                }
                            }
                            if (Ext.enumerables) {
                                for (i = Ext.enumerables.length; i--; ) {
                                    k = Ext.enumerables[i];
                                    if (name.hasOwnProperty(k)) {
                                        setter.call(this, k, name[k]);
                                    }
                                }
                            }
                        } else {
                            setter.call(this, name, value);
                        }
                    }
                    return this;
                };
            },
            
            bind: function(fn, scope, args, appendArgs) {
                if (arguments.length === 2) {
                    return function() {
                        return fn.apply(scope, arguments);
                    };
                }
                var method = fn,
                    slice = Array.prototype.slice;
                return function() {
                    var callArgs = args || arguments;
                    if (appendArgs === true) {
                        callArgs = slice.call(arguments, 0);
                        callArgs = callArgs.concat(args);
                    } else if (typeof appendArgs == 'number') {
                        callArgs = slice.call(arguments, 0);
                        
                        Ext.Array.insert(callArgs, appendArgs, args);
                    }
                    return method.apply(scope || Ext.global, callArgs);
                };
            },
            
            bindCallback: function(callback, scope, args, delay, caller) {
                return function() {
                    var a = Ext.Array.slice(arguments);
                    return Ext.callback(callback, scope, args ? args.concat(a) : a, delay, caller);
                };
            },
            
            pass: function(fn, args, scope) {
                if (!Ext.isArray(args)) {
                    if (Ext.isIterable(args)) {
                        args = Ext.Array.clone(args);
                    } else {
                        args = args !== undefined ? [
                            args
                        ] : [];
                    }
                }
                return function() {
                    var fnArgs = args.slice();
                    fnArgs.push.apply(fnArgs, arguments);
                    return fn.apply(scope || this, fnArgs);
                };
            },
            
            alias: function(object, methodName) {
                return function() {
                    return object[methodName].apply(object, arguments);
                };
            },
            
            clone: function(method) {
                return function() {
                    return method.apply(this, arguments);
                };
            },
            
            createInterceptor: function(origFn, newFn, scope, returnValue) {
                if (!Ext.isFunction(newFn)) {
                    return origFn;
                } else {
                    returnValue = Ext.isDefined(returnValue) ? returnValue : null;
                    return function() {
                        var me = this,
                            args = arguments;
                        newFn.target = me;
                        newFn.method = origFn;
                        return (newFn.apply(scope || me || Ext.global, args) !== false) ? origFn.apply(me || Ext.global, args) : returnValue;
                    };
                }
            },
            
            createDelayed: function(fn, delay, scope, args, appendArgs) {
                if (scope || args) {
                    fn = Ext.Function.bind(fn, scope, args, appendArgs);
                }
                return function() {
                    var me = this,
                        args = Array.prototype.slice.call(arguments);
                    setTimeout(function() {
                        if (Ext.elevateFunction) {
                            Ext.elevateFunction(fn, me, args);
                        } else {
                            fn.apply(me, args);
                        }
                    }, delay);
                };
            },
            
            defer: function(fn, millis, scope, args, appendArgs) {
                fn = Ext.Function.bind(fn, scope, args, appendArgs);
                if (millis > 0) {
                    return setTimeout(function() {
                        if (Ext.elevateFunction) {
                            Ext.elevateFunction(fn);
                        } else {
                            fn();
                        }
                    }, millis);
                }
                fn();
                return 0;
            },
            
            interval: function(fn, millis, scope, args, appendArgs) {
                fn = Ext.Function.bind(fn, scope, args, appendArgs);
                return setInterval(function() {
                    if (Ext.elevateFunction) {
                        Ext.elevateFunction(fn);
                    } else {
                        fn();
                    }
                }, millis);
            },
            
            createSequence: function(originalFn, newFn, scope) {
                if (!newFn) {
                    return originalFn;
                } else {
                    return function() {
                        var result = originalFn.apply(this, arguments);
                        newFn.apply(scope || this, arguments);
                        return result;
                    };
                }
            },
            
            createBuffered: function(fn, buffer, scope, args) {
                var timerId;
                return function() {
                    var callArgs = args || Array.prototype.slice.call(arguments, 0),
                        me = scope || this;
                    if (timerId) {
                        clearTimeout(timerId);
                    }
                    timerId = setTimeout(function() {
                        if (Ext.elevateFunction) {
                            Ext.elevateFunction(fn, me, callArgs);
                        } else {
                            fn.apply(me, callArgs);
                        }
                    }, buffer);
                };
            },
            
            createAnimationFrame: function(fn, scope, args, queueStrategy) {
                var timerId;
                queueStrategy = queueStrategy || 3;
                return function() {
                    var callArgs = args || Array.prototype.slice.call(arguments, 0);
                    scope = scope || this;
                    if (queueStrategy === 3 && timerId) {
                        ExtFunction.cancelAnimationFrame(timerId);
                    }
                    if ((queueStrategy & 1) || !timerId) {
                        timerId = ExtFunction.requestAnimationFrame(function() {
                            timerId = null;
                            fn.apply(scope, callArgs);
                        });
                    }
                };
            },
            
            requestAnimationFrame: function(fn, scope, args) {
                var id = ++idSource,
                    
                    handler = Array.prototype.slice.call(arguments, 0);
                handler[3] = id;
                animFrameMap[id] = 1;
                
                
                
                animFrameHandlers.push(handler);
                if (!animFrameId) {
                    animFrameId = requestAnimFrame(Ext.elevateFunction ? fireElevatedHandlers : fireHandlers);
                }
                return id;
            },
            cancelAnimationFrame: function(id) {
                
                
                
                delete animFrameMap[id];
            },
            
            createThrottled: function(fn, interval, scope) {
                var lastCallTime = 0,
                    elapsed, lastArgs, timer,
                    execute = function() {
                        if (Ext.elevateFunction) {
                            Ext.elevateFunction(fn, scope, lastArgs);
                        } else {
                            fn.apply(scope, lastArgs);
                        }
                        lastCallTime = Ext.now();
                        timer = null;
                    };
                return function() {
                    
                    if (!scope) {
                        scope = this;
                    }
                    elapsed = Ext.now() - lastCallTime;
                    lastArgs = arguments;
                    
                    
                    if (elapsed >= interval) {
                        clearTimeout(timer);
                        execute();
                    }
                    
                    else if (!timer) {
                        timer = Ext.defer(execute, interval - elapsed);
                    }
                };
            },
            
            createBarrier: function(count, fn, scope) {
                return function() {
                    if (!--count) {
                        fn.apply(scope, arguments);
                    }
                };
            },
            
            interceptBefore: function(object, methodName, fn, scope) {
                var method = object[methodName] || Ext.emptyFn;
                return (object[methodName] = function() {
                    var ret = fn.apply(scope || this, arguments);
                    method.apply(this, arguments);
                    return ret;
                });
            },
            
            interceptAfter: function(object, methodName, fn, scope) {
                var method = object[methodName] || Ext.emptyFn;
                return (object[methodName] = function() {
                    method.apply(this, arguments);
                    return fn.apply(scope || this, arguments);
                });
            },
            makeCallback: function(callback, scope) {
                if (!scope[callback]) {
                    if (scope.$className) {
                        Ext.Error.raise('No method "' + callback + '" on ' + scope.$className);
                    }
                    Ext.Error.raise('No method "' + callback + '"');
                }
                return function() {
                    return scope[callback].apply(scope, arguments);
                };
            }
        };
    
    Ext.defer = ExtFunction.defer;
    
    Ext.interval = ExtFunction.interval;
    
    Ext.pass = ExtFunction.pass;
    
    Ext.bind = ExtFunction.bind;
    Ext.deferCallback = ExtFunction.requestAnimationFrame;
    return ExtFunction;
})();


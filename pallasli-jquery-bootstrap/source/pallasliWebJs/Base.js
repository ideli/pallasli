Ext.Base = (function(flexSetter) {
    var noArgs = [],
        baseStaticMember,
        baseStaticMembers = [],
        getConfig = function(name, peek) {
            var me = this,
                ret, cfg, getterName;
            if (name) {
                cfg = Ext.Config.map[name];
                if (!cfg) {
                    Ext.Logger.error("Invalid property name for getter: '" + name + "' for '" + me.$className + "'.");
                }
                getterName = cfg.names.get;
                if (peek && me.hasOwnProperty(getterName)) {
                    ret = me.config[name];
                } else {
                    ret = me[getterName]();
                }
            } else {
                ret = me.getCurrentConfig();
            }
            return ret;
        },
        makeDeprecatedMethod = function(oldName, newName, msg) {
            var message = '"' + oldName + '" is deprecated.';
            if (msg) {
                message += ' ' + msg;
            } else if (newName) {
                message += ' Please use "' + newName + '" instead.';
            }
            return function() {
                Ext.Error.raise(message);
            };
        },
        addDeprecatedProperty = function(object, oldName, newName, message) {
            if (!message) {
                message = '"' + oldName + '" is deprecated.';
            }
            if (newName) {
                message += ' Please use "' + newName + '" instead.';
            }
            if (message) {
                Ext.Object.defineProperty(object, oldName, {
                    get: function() {
                        Ext.Error.raise(message);
                    },
                    set: function(value) {
                        Ext.Error.raise(message);
                    },
                    configurable: true
                });
            }
        },
        makeAliasFn = function(name) {
            return function() {
                return this[name].apply(this, arguments);
            };
        },
        Version = Ext.Version,
        leadingDigitRe = /^\d/,
        oneMember = {},
        aliasOneMember = {},
        Base = function() {},
        BasePrototype = Base.prototype;
    
    Ext.apply(Base, {
        $className: 'Ext.Base',
        $isClass: true,
        
        create: function() {
            return Ext.create.apply(Ext, [
                this
            ].concat(Array.prototype.slice.call(arguments, 0)));
        },
        
        addDeprecations: function(deprecations) {
            var me = this,
                all = [],
                compatVersion = Ext.getCompatVersion(deprecations.name),
                displayName = (me.$className || '') + '#',
                deprecate, versionSpec, index, message, target, enabled, existing, fn, names, oldName, newName, member, statics, version;
            for (versionSpec in deprecations) {
                if (leadingDigitRe.test(versionSpec)) {
                    version = new Ext.Version(versionSpec);
                    version.deprecations = deprecations[versionSpec];
                    all.push(version);
                }
            }
            all.sort(Version.compare);
            for (index = all.length; index--; ) {
                deprecate = (version = all[index]).deprecations;
                target = me.prototype;
                statics = deprecate.statics;
                
                
                
                
                
                
                enabled = compatVersion && compatVersion.lt(version);
                if (!enabled) {} else if (!enabled) {
                    
                    break;
                }
                while (deprecate) {
                    names = deprecate.methods;
                    if (names) {
                        for (oldName in names) {
                            member = names[oldName];
                            fn = null;
                            if (!member) {
                                
                                
                                Ext.Assert.isNotDefinedProp(target, oldName);
                                fn = makeDeprecatedMethod(displayName + oldName);
                            } else if (Ext.isString(member)) {
                                
                                
                                Ext.Assert.isNotDefinedProp(target, oldName);
                                Ext.Assert.isDefinedProp(target, member);
                                if (enabled) {
                                    
                                    
                                    fn = makeAliasFn(member);
                                } else {
                                    fn = makeDeprecatedMethod(displayName + oldName, member);
                                }
                            } else {
                                
                                message = '';
                                if (member.message || member.fn) {
                                    message = member.message;
                                    member = member.fn;
                                }
                                existing = target.hasOwnProperty(oldName) && target[oldName];
                                if (enabled && member) {
                                    member.$owner = me;
                                    member.$name = oldName;
                                    member.name = displayName + oldName;
                                    if (existing) {
                                        member.$previous = existing;
                                    }
                                    fn = member;
                                } else if (!existing) {
                                    fn = makeDeprecatedMethod(displayName + oldName, null, message);
                                }
                            }
                            if (fn) {
                                target[oldName] = fn;
                            }
                        }
                    }
                    
                    names = deprecate.properties;
                    if (names && !enabled) {
                        
                        
                        
                        for (oldName in names) {
                            newName = names[oldName];
                            if (Ext.isString(newName)) {
                                addDeprecatedProperty(target, displayName + oldName, newName);
                            } else if (newName && newName.message) {
                                addDeprecatedProperty(target, displayName + oldName, null, newName.message);
                            } else {
                                addDeprecatedProperty(target, displayName + oldName);
                            }
                        }
                    }
                    
                    deprecate = statics;
                    statics = null;
                    target = me;
                }
            }
        },
        
        extend: function(parent) {
            var me = this,
                parentPrototype = parent.prototype,
                prototype, i, ln, name, statics;
            prototype = me.prototype = Ext.Object.chain(parentPrototype);
            prototype.self = me;
            me.superclass = prototype.superclass = parentPrototype;
            if (!parent.$isClass) {
                for (i in BasePrototype) {
                    if (i in prototype) {
                        prototype[i] = BasePrototype[i];
                    }
                }
            }
            
            statics = parentPrototype.$inheritableStatics;
            if (statics) {
                for (i = 0 , ln = statics.length; i < ln; i++) {
                    name = statics[i];
                    if (!me.hasOwnProperty(name)) {
                        me[name] = parent[name];
                    }
                }
            }
            if (parent.$onExtended) {
                me.$onExtended = parent.$onExtended.slice();
            }
            me.getConfigurator();
        },
        
        $onExtended: [],
        
        triggerExtended: function() {
            Ext.classSystemMonitor && Ext.classSystemMonitor(this, 'Ext.Base#triggerExtended', arguments);
            var callbacks = this.$onExtended,
                ln = callbacks.length,
                i, callback;
            if (ln > 0) {
                for (i = 0; i < ln; i++) {
                    callback = callbacks[i];
                    callback.fn.apply(callback.scope || this, arguments);
                }
            }
        },
        
        onExtended: function(fn, scope) {
            this.$onExtended.push({
                fn: fn,
                scope: scope
            });
            return this;
        },
        
        addStatics: function(members) {
            this.addMembers(members, true);
            return this;
        },
        
        addInheritableStatics: function(members) {
            var inheritableStatics, hasInheritableStatics,
                prototype = this.prototype,
                name, member;
            inheritableStatics = prototype.$inheritableStatics;
            hasInheritableStatics = prototype.$hasInheritableStatics;
            if (!inheritableStatics) {
                inheritableStatics = prototype.$inheritableStatics = [];
                hasInheritableStatics = prototype.$hasInheritableStatics = {};
            }
            var className = Ext.getClassName(this) + '.';
            for (name in members) {
                if (members.hasOwnProperty(name)) {
                    member = members[name];
                    if (typeof member == 'function') {
                        member.name = className + name;
                    }
                    this[name] = member;
                    if (!hasInheritableStatics[name]) {
                        hasInheritableStatics[name] = true;
                        inheritableStatics.push(name);
                    }
                }
            }
            return this;
        },
        
        addMembers: function(members, isStatic, privacy) {
            var me = this,
                
                cloneFunction = Ext.Function.clone,
                target = isStatic ? me : me.prototype,
                defaultConfig = !isStatic && target.defaultConfig,
                enumerables = Ext.enumerables,
                privates = members.privates,
                configs, i, ln, member, name, subPrivacy, privateStatics;
            var displayName = (me.$className || '') + '#';
            if (privates) {
                
                
                delete members.privates;
                if (!isStatic) {
                    privateStatics = privates.statics;
                    delete privates.statics;
                }
                subPrivacy = privates.privacy || privacy || 'framework';
                me.addMembers(privates, isStatic, subPrivacy);
                if (privateStatics) {
                    me.addMembers(privateStatics, true, subPrivacy);
                }
            }
            for (name in members) {
                if (members.hasOwnProperty(name)) {
                    member = members[name];
                    if (privacy === true) {
                        privacy = 'framework';
                    }
                    if (member && member.$nullFn && privacy !== member.$privacy) {
                        Ext.Error.raise('Cannot use stock function for private method ' + (me.$className ? me.$className + '#' : '') + name);
                    }
                    if (typeof member === 'function' && !member.$isClass && !member.$nullFn) {
                        if (member.$owner) {
                            member = cloneFunction(member);
                        }
                        if (target.hasOwnProperty(name)) {
                            member.$previous = target[name];
                        }
                        
                        
                        member.$owner = me;
                        member.$name = name;
                        member.name = displayName + name;
                        var existing = target[name];
                        if (privacy) {
                            member.$privacy = privacy;
                            
                            
                            
                            
                            
                            
                            if (existing && existing.$privacy && existing.$privacy !== privacy) {
                                Ext.privacyViolation(me, existing, member, isStatic);
                            }
                        } else if (existing && existing.$privacy) {
                            Ext.privacyViolation(me, existing, member, isStatic);
                        }
                    }
                    
                    
                    else if (defaultConfig && (name in defaultConfig) && !target.config.hasOwnProperty(name)) {
                        
                        
                        (configs || (configs = {}))[name] = member;
                        
                        continue;
                    }
                    target[name] = member;
                }
            }
            if (configs) {
                
                me.addConfig(configs);
            }
            if (enumerables) {
                for (i = 0 , ln = enumerables.length; i < ln; ++i) {
                    if (members.hasOwnProperty(name = enumerables[i])) {
                        member = members[name];
                        
                        if (member && !member.$nullFn) {
                            if (member.$owner) {
                                member = cloneFunction(member);
                            }
                            member.$owner = me;
                            member.$name = name;
                            member.name = displayName + name;
                            if (target.hasOwnProperty(name)) {
                                member.$previous = target[name];
                            }
                        }
                        target[name] = member;
                    }
                }
            }
            return this;
        },
        
        addMember: function(name, member) {
            oneMember[name] = member;
            this.addMembers(oneMember);
            delete oneMember[name];
            return this;
        },
        
        borrow: function(fromClass, members) {
            Ext.classSystemMonitor && Ext.classSystemMonitor(this, 'Ext.Base#borrow', arguments);
            var prototype = fromClass.prototype,
                membersObj = {},
                i, ln, name;
            members = Ext.Array.from(members);
            for (i = 0 , ln = members.length; i < ln; i++) {
                name = members[i];
                membersObj[name] = prototype[name];
            }
            return this.addMembers(membersObj);
        },
        
        override: function(members) {
            var me = this,
                statics = members.statics,
                inheritableStatics = members.inheritableStatics,
                config = members.config,
                mixins = members.mixins,
                cachedConfig = members.cachedConfig;
            if (statics || inheritableStatics || config) {
                members = Ext.apply({}, members);
            }
            if (statics) {
                me.addMembers(statics, true);
                delete members.statics;
            }
            if (inheritableStatics) {
                me.addInheritableStatics(inheritableStatics);
                delete members.inheritableStatics;
            }
            if (config) {
                me.addConfig(config);
                delete members.config;
            }
            if (cachedConfig) {
                me.addCachedConfig(cachedConfig);
                delete members.cachedConfig;
            }
            delete members.mixins;
            me.addMembers(members);
            if (mixins) {
                me.mixin(mixins);
            }
            return me;
        },
        
        callParent: function(args) {
            var method;
            
            return (method = this.callParent.caller) && (method.$previous || ((method = method.$owner ? method : method.caller) && method.$owner.superclass.self[method.$name])).apply(this, args || noArgs);
        },
        
        callSuper: function(args) {
            var method;
            
            return (method = this.callSuper.caller) && ((method = method.$owner ? method : method.caller) && method.$owner.superclass.self[method.$name]).apply(this, args || noArgs);
        },
        
        mixin: function(name, mixinClass) {
            var me = this,
                mixin, prototype, key, statics, i, ln, staticName, mixinValue, mixins;
            if (typeof name !== 'string') {
                mixins = name;
                if (mixins instanceof Array) {
                    for (i = 0 , ln = mixins.length; i < ln; i++) {
                        mixin = mixins[i];
                        me.mixin(mixin.prototype.mixinId || mixin.$className, mixin);
                    }
                } else {
                    
                    
                    
                    
                    for (var mixinName in mixins) {
                        me.mixin(mixinName, mixins[mixinName]);
                    }
                }
                return;
            }
            mixin = mixinClass.prototype;
            prototype = me.prototype;
            if (mixin.onClassMixedIn) {
                mixin.onClassMixedIn.call(mixinClass, me);
            }
            if (!prototype.hasOwnProperty('mixins')) {
                if ('mixins' in prototype) {
                    prototype.mixins = Ext.Object.chain(prototype.mixins);
                } else {
                    prototype.mixins = {};
                }
            }
            for (key in mixin) {
                mixinValue = mixin[key];
                if (key === 'mixins') {
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    Ext.applyIf(prototype.mixins, mixinValue);
                } else if (!(key === 'mixinId' || key === 'config') && (prototype[key] === undefined)) {
                    prototype[key] = mixinValue;
                }
            }
            
            statics = mixin.$inheritableStatics;
            if (statics) {
                for (i = 0 , ln = statics.length; i < ln; i++) {
                    staticName = statics[i];
                    if (!me.hasOwnProperty(staticName)) {
                        me[staticName] = mixinClass[staticName];
                    }
                }
            }
            if ('config' in mixin) {
                me.addConfig(mixin.config, mixinClass);
            }
            prototype.mixins[name] = mixin;
            if (mixin.afterClassMixedIn) {
                mixin.afterClassMixedIn.call(mixinClass, me);
            }
            return me;
        },
        
        addConfig: function(config, mixinClass) {
            var cfg = this.$config || this.getConfigurator();
            cfg.add(config, mixinClass);
        },
        addCachedConfig: function(config, isMixin) {
            var cached = {},
                key;
            for (key in config) {
                cached[key] = {
                    cached: true,
                    $value: config[key]
                };
            }
            this.addConfig(cached, isMixin);
        },
        
        getConfigurator: function() {
            
            return this.$config || new Ext.Configurator(this);
        },
        
        getName: function() {
            return Ext.getClassName(this);
        },
        
        createAlias: flexSetter(function(alias, origin) {
            aliasOneMember[alias] = function() {
                return this[origin].apply(this, arguments);
            };
            this.override(aliasOneMember);
            delete aliasOneMember[alias];
        })
    });
    
    
    for (baseStaticMember in Base) {
        if (Base.hasOwnProperty(baseStaticMember)) {
            baseStaticMembers.push(baseStaticMember);
        }
    }
    Base.$staticMembers = baseStaticMembers;
    Base.getConfigurator();
    
    Base.addMembers({
        
        $className: 'Ext.Base',
        
        isInstance: true,
        
        $configPrefixed: true,
        
        $configStrict: true,
        
        isConfiguring: false,
        
        isFirstInstance: false,
        
        statics: function() {
            var method = this.statics.caller,
                self = this.self;
            if (!method) {
                return self;
            }
            return method.$owner;
        },
        
        callParent: function(args) {
            
            
            
            
            var method,
                superMethod = (method = this.callParent.caller) && (method.$previous || ((method = method.$owner ? method : method.caller) && method.$owner.superclass[method.$name]));
            if (!superMethod) {
                method = this.callParent.caller;
                var parentClass, methodName;
                if (!method.$owner) {
                    if (!method.caller) {
                        throw new Error("Attempting to call a protected method from the public scope, which is not allowed");
                    }
                    method = method.caller;
                }
                parentClass = method.$owner.superclass;
                methodName = method.$name;
                if (!(methodName in parentClass)) {
                    throw new Error("this.callParent() was called but there's no such method (" + methodName + ") found in the parent class (" + (Ext.getClassName(parentClass) || 'Object') + ")");
                }
            }
            return superMethod.apply(this, args || noArgs);
        },
        
        callSuper: function(args) {
            
            
            
            
            var method,
                superMethod = (method = this.callSuper.caller) && ((method = method.$owner ? method : method.caller) && method.$owner.superclass[method.$name]);
            if (!superMethod) {
                method = this.callSuper.caller;
                var parentClass, methodName;
                if (!method.$owner) {
                    if (!method.caller) {
                        throw new Error("Attempting to call a protected method from the public scope, which is not allowed");
                    }
                    method = method.caller;
                }
                parentClass = method.$owner.superclass;
                methodName = method.$name;
                if (!(methodName in parentClass)) {
                    throw new Error("this.callSuper() was called but there's no such method (" + methodName + ") found in the parent class (" + (Ext.getClassName(parentClass) || 'Object') + ")");
                }
            }
            return superMethod.apply(this, args || noArgs);
        },
        
        self: Base,
        
        constructor: function() {
            return this;
        },
        getConfigurator: function() {
            return this.$config || this.self.getConfigurator();
        },
        
        initConfig: function(instanceConfig) {
            var me = this,
                cfg = me.getConfigurator();
            me.initConfig = Ext.emptyFn;
            
            me.initialConfig = instanceConfig || {};
            cfg.configure(me, instanceConfig);
            return me;
        },
        beforeInitConfig: Ext.emptyFn,
        
        getConfig: getConfig,
        
        setConfig: function(name, value, 
        options) {
            
            
            
            
            
            var me = this,
                config;
            if (name) {
                if (typeof name === 'string') {
                    config = {};
                    config[name] = value;
                } else {
                    config = name;
                }
                me.getConfigurator().reconfigure(me, config, options);
            }
            return me;
        },
        
        getCurrentConfig: function() {
            var cfg = this.getConfigurator();
            return cfg.getCurrentConfig(this);
        },
        
        hasConfig: function(name) {
            return name in this.defaultConfig;
        },
        
        getInitialConfig: function(name) {
            var config = this.config;
            if (!name) {
                return config;
            }
            return config[name];
        },
        $links: null,
        
        link: function(name, value) {
            var me = this,
                links = me.$links || (me.$links = {});
            links[name] = true;
            me[name] = value;
            return value;
        },
        
        unlink: function(names) {
            var me = this,
                i, ln, link, value;
            if (!Ext.isArray(names)) {
                Ext.Error.raise('Invalid argument - expected array of strings');
            }
            for (i = 0 , ln = names.length; i < ln; i++) {
                link = names[i];
                value = me[link];
                if (value) {
                    if (value.isInstance && !value.isDestroyed) {
                        value.destroy();
                    } else if (value.parentNode && 'nodeType' in value) {
                        value.parentNode.removeChild(value);
                    }
                }
                me[link] = null;
            }
            return me;
        },
        
        destroy: function() {
            var me = this,
                links = me.$links;
            me.destroy = Ext.emptyFn;
            me.isDestroyed = true;
            if (links) {
                me.$links = null;
                me.unlink(Ext.Object.getKeys(links));
            }
        }
    });
    
    BasePrototype.callOverridden = BasePrototype.callParent;
    Ext.privacyViolation = function(cls, existing, member, isStatic) {
        var name = member.$name,
            conflictCls = existing.$owner && existing.$owner.$className,
            s = isStatic ? 'static ' : '',
            msg = member.$privacy ? 'Private ' + s + member.$privacy + ' method "' + name + '"' : 'Public ' + s + 'method "' + name + '"';
        if (cls.$className) {
            msg = cls.$className + ': ' + msg;
        }
        if (!existing.$privacy) {
            msg += conflictCls ? ' hides public method inherited from ' + conflictCls : ' hides inherited public method.';
        } else {
            msg += conflictCls ? ' conflicts with private ' + existing.$privacy + ' method declared by ' + conflictCls : ' conflicts with inherited private ' + existing.$privacy + ' method.';
        }
        var compat = Ext.getCompatVersion();
        var ver = Ext.getVersion();
        
        if (ver && compat && compat.lt(ver)) {
            Ext.log.error(msg);
        } else {
            Ext.Error.raise(msg);
        }
    };
    return Base;
}(Ext.Function.flexSetter));

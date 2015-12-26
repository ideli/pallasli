

(function() {
    
    var ExtConfig = Ext.Config,
        configPropMap = ExtConfig.map,
        ExtObject = Ext.Object;
    Ext.Configurator = function(cls) {
        var me = this,
            prototype = cls.prototype,
            zuper = cls.superclass ? cls.superclass.self.$config : null;
        
        me.cls = cls;
        if (zuper) {
            
            me.configs = ExtObject.chain(zuper.configs);
            
            me.cachedConfigs = ExtObject.chain(zuper.cachedConfigs);
            
            me.initMap = ExtObject.chain(zuper.initMap);
            
            me.values = ExtObject.chain(zuper.values);
        } else {
            me.configs = {};
            me.cachedConfigs = {};
            me.initMap = {};
            me.values = {};
        }
        prototype.config = prototype.defaultConfig = me.values;
        cls.$config = me;
    };
    Ext.Configurator.prototype = {
        self: Ext.Configurator,
        
        initList: null,
        
        add: function(config, mixinClass) {
            var me = this,
                Cls = me.cls,
                configs = me.configs,
                cachedConfigs = me.cachedConfigs,
                initMap = me.initMap,
                prototype = Cls.prototype,
                mixinConfigs = mixinClass && mixinClass.$config.configs,
                values = me.values,
                isObject, meta, isCached, merge, cfg, currentValue, name, names, s, value;
            for (name in config) {
                value = config[name];
                isObject = value && value.constructor === Object;
                meta = isObject && '$value' in value ? value : null;
                if (meta) {
                    isCached = !!meta.cached;
                    value = meta.$value;
                }
                merge = meta && meta.merge;
                cfg = configs[name];
                if (cfg) {
                    
                    if (mixinClass) {
                        merge = cfg.merge;
                        if (!merge) {
                            
                            continue;
                        }
                        
                        meta = null;
                    } else {
                        merge = merge || cfg.merge;
                    }
                    
                    
                    if (!mixinClass && isCached && !cachedConfigs[name]) {
                        Ext.Error.raise('Redefining config as cached: ' + name + ' in class: ' + Cls.$className);
                    }
                    
                    currentValue = values[name];
                    if (merge) {
                        value = merge.call(cfg, value, currentValue, Cls, mixinClass);
                    } else if (isObject) {
                        if (currentValue && currentValue.constructor === Object) {
                            value = ExtObject.merge({}, currentValue, value);
                        }
                    }
                } else 
                
                {
                    if (mixinConfigs) { 
                        cfg = mixinConfigs[name];
                        meta = null;
                    } else {
                        cfg = ExtConfig.get(name);
                    }
                    configs[name] = cfg;
                    if (cfg.cached || isCached) {
                        cachedConfigs[name] = true;
                    }
                    names = cfg.names;
                    if (!prototype[s = names.get]) {
                        prototype[s] = cfg.getGetter();
                    }
                    if (!prototype[s = names.set]) {
                        prototype[s] = cfg.getSetter();
                    }
                }
                if (meta) {
                    if (cfg.owner !== Cls) {
                        configs[name] = cfg = Ext.Object.chain(cfg);
                        cfg.owner = Cls;
                    }
                    Ext.apply(cfg, meta);
                    delete cfg.$value;
                }
                
                if (value !== null) {
                    initMap[name] = true;
                } else {
                    if (prototype.$configPrefixed) {
                        prototype[configs[name].names.internal] = null;
                    } else {
                        prototype[configs[name].name] = null;
                    }
                    if (name in initMap) {
                        
                        initMap[name] = false;
                    }
                }
                values[name] = value;
            }
        },
        
        configure: function(instance, instanceConfig) {
            var me = this,
                configs = me.configs,
                initMap = me.initMap,
                initListMap = me.initListMap,
                initList = me.initList,
                prototype = me.cls.prototype,
                
                
                values = ExtObject.fork(me.values),
                remaining = 0,
                firstInstance = !initList,
                cachedInitList, cfg, getter, needsInit, i, internalName, ln, names, name, value, isCached, valuesKey;
            if (firstInstance) {
                
                
                me.initList = initList = [];
                me.initListMap = initListMap = {};
                instance.isFirstInstance = true;
                for (name in initMap) {
                    needsInit = initMap[name];
                    cfg = configs[name];
                    isCached = cfg.cached;
                    if (needsInit) {
                        names = cfg.names;
                        value = values[name];
                        if (!prototype[names.set].$isDefault || prototype[names.apply] || prototype[names.update] || typeof value === 'object') {
                            if (isCached) {
                                
                                
                                
                                
                                
                                (cachedInitList || (cachedInitList = [])).push(cfg);
                            } else {
                                
                                
                                initList.push(cfg);
                                initListMap[name] = true;
                            }
                            
                            
                            
                            instance[names.get] = cfg.initGetter || cfg.getInitGetter();
                        } else {
                            
                            
                            prototype[cfg.getInternalName(prototype)] = value;
                        }
                    } else if (isCached) {
                        prototype[cfg.getInternalName(prototype)] = undefined;
                    }
                }
            }
            ln = cachedInitList && cachedInitList.length;
            if (ln) {
                
                
                
                
                for (i = 0; i < ln; ++i) {
                    internalName = cachedInitList[i].getInternalName(prototype);
                    
                    
                    
                    instance[internalName] = null;
                }
                for (i = 0; i < ln; ++i) {
                    names = (cfg = cachedInitList[i]).names;
                    getter = names.get;
                    if (instance.hasOwnProperty(getter)) {
                        instance[names.set](values[cfg.name]);
                        delete instance[getter];
                    }
                }
                for (i = 0; i < ln; ++i) {
                    internalName = cachedInitList[i].getInternalName(prototype);
                    prototype[internalName] = instance[internalName];
                    delete instance[internalName];
                }
            }
            
            
            
            
            if (instanceConfig && instanceConfig.platformConfig) {
                instanceConfig = me.resolvePlatformConfig(instance, instanceConfig);
            }
            if (firstInstance) {
                
                
                
                if (instance.afterCachedConfig && !instance.afterCachedConfig.$nullFn) {
                    instance.afterCachedConfig(instanceConfig);
                }
            }
            
            instance.isConfiguring = true;
            
            
            
            instance.config = values;
            for (i = 0 , ln = initList.length; i < ln; ++i) {
                cfg = initList[i];
                instance[cfg.names.get] = cfg.initGetter || cfg.getInitGetter();
            }
            
            if (instance.transformInstanceConfig) {
                instanceConfig = instance.transformInstanceConfig(instanceConfig);
            }
            if (instanceConfig) {
                for (name in instanceConfig) {
                    value = instanceConfig[name];
                    cfg = configs[name];
                    if (!cfg) {
                        if (instance.$configStrict && typeof instance.self.prototype[name] === 'function') {
                            
                            Ext.Error.raise("Cannot override method " + name + " on " + instance.$className + " instance.");
                        }
                        
                        
                        instance[name] = value;
                    } else {
                        
                        
                        if (!cfg.lazy) {
                            ++remaining;
                        }
                        if (!initListMap[name]) {
                            instance[cfg.names.get] = cfg.initGetter || cfg.getInitGetter();
                        }
                        if (cfg.merge) {
                            value = cfg.merge(value, values[name], instance);
                        } else if (value && value.constructor === Object) {
                            valuesKey = values[name];
                            if (valuesKey && valuesKey.constructor === Object) {
                                value = ExtObject.merge(values[name], value);
                            } else {
                                value = Ext.clone(value);
                            }
                        }
                    }
                    values[name] = value;
                }
            }
            
            if (instance.beforeInitConfig && !instance.beforeInitConfig.$nullFn) {
                if (instance.beforeInitConfig(instanceConfig) === false) {
                    return;
                }
            }
            if (instanceConfig) {
                for (name in instanceConfig) {
                    if (!remaining) {
                        
                        
                        break;
                    }
                    cfg = configs[name];
                    if (cfg && !cfg.lazy) {
                        --remaining;
                        
                        names = cfg.names;
                        getter = names.get;
                        
                        
                        
                        
                        if (instance.hasOwnProperty(getter)) {
                            instance[names.set](values[name]);
                            
                            
                            
                            delete instance[names.get];
                        }
                    }
                }
            }
            
            for (i = 0 , ln = initList.length; i < ln; ++i) {
                cfg = initList[i];
                names = cfg.names;
                getter = names.get;
                if (!cfg.lazy && instance.hasOwnProperty(getter)) {
                    
                    
                    
                    
                    instance[names.set](values[cfg.name]);
                    delete instance[getter];
                }
            }
            
            delete instance.isConfiguring;
        },
        getCurrentConfig: function(instance) {
            var defaultConfig = instance.defaultConfig,
                config = {},
                name;
            for (name in defaultConfig) {
                config[name] = instance[configPropMap[name].names.get]();
            }
            return config;
        },
        
        merge: function(instance, baseConfig, config) {
            
            
            var configs = this.configs,
                name, value, baseValue, cfg;
            for (name in config) {
                value = config[name];
                cfg = configs[name];
                if (cfg) {
                    if (cfg.merge) {
                        value = cfg.merge(value, baseConfig[name], instance);
                    } else if (value && value.constructor === Object) {
                        baseValue = baseConfig[name];
                        if (baseValue && baseValue.constructor === Object) {
                            value = Ext.Object.merge(baseValue, value);
                        } else {
                            value = Ext.clone(value);
                        }
                    }
                }
                baseConfig[name] = value;
            }
            return baseConfig;
        },
        
        reconfigure: function(instance, instanceConfig, options) {
            var currentConfig = instance.config,
                configList = [],
                strict = instance.$configStrict,
                configs = this.configs,
                defaults = options && options.defaults,
                applyProps = options && options.strict === false,
                cfg, getter, i, len, name, names, setter;
            for (name in instanceConfig) {
                if (defaults && instance.hasOwnProperty(name)) {
                    
                    continue;
                }
                currentConfig[name] = instanceConfig[name];
                cfg = configs[name];
                if (cfg) {
                    
                    
                    instance[cfg.names.get] = cfg.initGetter || cfg.getInitGetter();
                } else if (strict) {
                    if (name !== 'type') {
                        Ext.log.error('No such config "' + name + '" for class ' + instance.$className);
                    }
                    
                    continue;
                }
                configList.push(name);
            }
            for (i = 0 , len = configList.length; i < len; i++) {
                name = configList[i];
                cfg = configs[name];
                if (cfg) {
                    names = cfg.names;
                    getter = names.get;
                    if (instance.hasOwnProperty(getter)) {
                        
                        
                        
                        
                        instance[names.set](instanceConfig[name]);
                        delete instance[getter];
                    }
                } else {
                    cfg = configPropMap[name] || Ext.Config.get(name);
                    names = cfg.names;
                    if (instance[names.set]) {
                        instance[names.set](instanceConfig[name]);
                    } else if (applyProps) {
                        if (instance.$configStrict && typeof instance.self.prototype[name] === 'function') {
                            
                            Ext.Error.raise("Cannot override method " + name + " on " + instance.$className + " instance.");
                        }
                        
                        instance[name] = instanceConfig[name];
                    } else if (name !== 'type') {
                        Ext.Error.raise('Config "' + name + '" has no setter on class ' + instance.$className);
                    }
                }
            }
        },
        
        resolvePlatformConfig: function(instance, instanceConfig) {
            var platformConfig = instanceConfig && instanceConfig.platformConfig,
                ret = instanceConfig,
                i, keys, n;
            if (platformConfig) {
                keys = Ext.getPlatformConfigKeys(platformConfig);
                n = keys.length;
                if (n) {
                    ret = Ext.merge({}, ret);
                    
                    for (i = 0 , n = keys.length; i < n; ++i) {
                        this.merge(instance, ret, platformConfig[keys[i]]);
                    }
                }
            }
            return ret;
        }
    };
}());


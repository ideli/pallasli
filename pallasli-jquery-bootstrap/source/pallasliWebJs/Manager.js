Manager = Ext.apply(new Ext.Inventory(), {
            classes: {},
            classState: {},
            existCache: {},
            namespaceRewrites: [
                {
                    from: 'Ext.',
                    to: Ext
                }
            ],
            enableNamespaceParseCache: true,
            namespaceParseCache: {},
            instantiators: [],
            isCreated: function(className) {
                var i, ln, part, root, parts;
                if (typeof className !== 'string' || className.length < 1) {
                    throw new Error("[Ext.ClassManager] Invalid classname, must be a string and must not be empty");
                }
                if (Manager.classes[className] || Manager.existCache[className]) {
                    return true;
                }
                root = global;
                parts = Manager.parseNamespace(className);
                for (i = 0 , ln = parts.length; i < ln; i++) {
                    part = parts[i];
                    if (typeof part !== 'string') {
                        root = part;
                    } else {
                        if (!root || !root[part]) {
                            return false;
                        }
                        root = root[part];
                    }
                }
                Manager.triggerCreated(className);
                return true;
            },
            
            createdListeners: [],
            
            nameCreatedListeners: {},
            
            existsListeners: [],
            
            nameExistsListeners: {},
            
            overrideMap: {},
            
            triggerCreated: function(className, state) {
                Manager.existCache[className] = state || 1;
                Manager.classState[className] += 40;
                Manager.notify(className, Manager.createdListeners, Manager.nameCreatedListeners);
            },
            
            onCreated: function(fn, scope, className) {
                Manager.addListener(fn, scope, className, Manager.createdListeners, Manager.nameCreatedListeners);
            },
            
            notify: function(className, listeners, nameListeners) {
                var alternateNames = Manager.getAlternatesByName(className),
                    names = [
                        className
                    ],
                    i, ln, j, subLn, listener, name;
                for (i = 0 , ln = listeners.length; i < ln; i++) {
                    listener = listeners[i];
                    listener.fn.call(listener.scope, className);
                }
                while (names) {
                    for (i = 0 , ln = names.length; i < ln; i++) {
                        name = names[i];
                        listeners = nameListeners[name];
                        if (listeners) {
                            for (j = 0 , subLn = listeners.length; j < subLn; j++) {
                                listener = listeners[j];
                                listener.fn.call(listener.scope, name);
                            }
                            delete nameListeners[name];
                        }
                    }
                    names = alternateNames;
                    
                    alternateNames = null;
                }
            },
            
            
            addListener: function(fn, scope, className, listeners, nameListeners) {
                if (Ext.isArray(className)) {
                    fn = Ext.Function.createBarrier(className.length, fn, scope);
                    for (i = 0; i < className.length; i++) {
                        this.addListener(fn, null, className[i], listeners, nameListeners);
                    }
                    return;
                }
                var i,
                    listener = {
                        fn: fn,
                        scope: scope
                    };
                if (className) {
                    if (this.isCreated(className)) {
                        fn.call(scope, className);
                        return;
                    }
                    if (!nameListeners[className]) {
                        nameListeners[className] = [];
                    }
                    nameListeners[className].push(listener);
                } else {
                    listeners.push(listener);
                }
            },
            
            parseNamespace: function(namespace) {
                if (typeof namespace !== 'string') {
                    throw new Error("[Ext.ClassManager] Invalid namespace, must be a string");
                }
                var cache = this.namespaceParseCache,
                    parts, rewrites, root, name, rewrite, from, to, i, ln;
                if (this.enableNamespaceParseCache) {
                    if (cache.hasOwnProperty(namespace)) {
                        return cache[namespace];
                    }
                }
                parts = [];
                rewrites = this.namespaceRewrites;
                root = global;
                name = namespace;
                for (i = 0 , ln = rewrites.length; i < ln; i++) {
                    rewrite = rewrites[i];
                    from = rewrite.from;
                    to = rewrite.to;
                    if (name === from || name.substring(0, from.length) === from) {
                        name = name.substring(from.length);
                        if (typeof to !== 'string') {
                            root = to;
                        } else {
                            parts = parts.concat(to.split('.'));
                        }
                        break;
                    }
                }
                parts.push(root);
                parts = parts.concat(name.split('.'));
                if (this.enableNamespaceParseCache) {
                    cache[namespace] = parts;
                }
                return parts;
            },
            
            setNamespace: function(name, value) {
                var root = global,
                    parts = this.parseNamespace(name),
                    ln = parts.length - 1,
                    leaf = parts[ln],
                    i, part;
                for (i = 0; i < ln; i++) {
                    part = parts[i];
                    if (typeof part !== 'string') {
                        root = part;
                    } else {
                        if (!root[part]) {
                            root[part] = {};
                        }
                        root = root[part];
                    }
                }
                root[leaf] = value;
                return root[leaf];
            },
            
            createNamespaces: function() {
                var root = global,
                    parts, part, i, j, ln, subLn;
                for (i = 0 , ln = arguments.length; i < ln; i++) {
                    parts = this.parseNamespace(arguments[i]);
                    for (j = 0 , subLn = parts.length; j < subLn; j++) {
                        part = parts[j];
                        if (typeof part !== 'string') {
                            root = part;
                        } else {
                            if (!root[part]) {
                                root[part] = {};
                            }
                            root = root[part];
                        }
                    }
                }
                return root;
            },
            
            set: function(name, value) {
                var me = this,
                    targetName = me.getName(value);
                me.classes[name] = me.setNamespace(name, value);
                if (targetName && targetName !== name) {
                    me.addAlternate(targetName, name);
                }
                return this;
            },
            
            get: function(name) {
                var classes = this.classes,
                    root, parts, part, i, ln;
                if (classes[name]) {
                    return classes[name];
                }
                root = global;
                parts = this.parseNamespace(name);
                for (i = 0 , ln = parts.length; i < ln; i++) {
                    part = parts[i];
                    if (typeof part !== 'string') {
                        root = part;
                    } else {
                        if (!root || !root[part]) {
                            return null;
                        }
                        root = root[part];
                    }
                }
                return root;
            },
            
            addNameAliasMappings: function(aliases) {
                this.addAlias(aliases);
            },
            
            addNameAlternateMappings: function(alternates) {
                this.addAlternate(alternates);
            },
            
            getByAlias: function(alias) {
                return this.get(this.getNameByAlias(alias));
            },
            
            getName: function(object) {
                return object && object.$className || '';
            },
            
            getClass: function(object) {
                return object && object.self || null;
            },
            
            create: function(className, data, createdFn) {
                if (className != null && typeof className !== 'string') {
                    throw new Error("[Ext.define] Invalid class name '" + className + "' specified, must be a non-empty string");
                }
                var ctor = makeCtor(className);
                if (typeof data === 'function') {
                    data = data(ctor);
                }
                if (className) {
                    if (Manager.classes[className]) {
                        Ext.log.warn("[Ext.define] Duplicate class name '" + className + "' specified, must be a non-empty string");
                    }
                    ctor.name = className;
                }
                data.$className = className;
                return new Class(ctor,data,function() {
                    var postprocessorStack = data.postprocessors || Manager.defaultPostprocessors,
                        registeredPostprocessors = Manager.postprocessors,
                        postprocessors = [],
                        postprocessor, i, ln, j, subLn, postprocessorProperties, postprocessorProperty;
                    delete data.postprocessors;
                    for (i = 0 , ln = postprocessorStack.length; i < ln; i++) {
                        postprocessor = postprocessorStack[i];
                        if (typeof postprocessor === 'string') {
                            postprocessor = registeredPostprocessors[postprocessor];
                            postprocessorProperties = postprocessor.properties;
                            if (postprocessorProperties === true) {
                                postprocessors.push(postprocessor.fn);
                            } else if (postprocessorProperties) {
                                for (j = 0 , subLn = postprocessorProperties.length; j < subLn; j++) {
                                    postprocessorProperty = postprocessorProperties[j];
                                    if (data.hasOwnProperty(postprocessorProperty)) {
                                        postprocessors.push(postprocessor.fn);
                                        break;
                                    }
                                }
                            }
                        } else {
                            postprocessors.push(postprocessor);
                        }
                    }
                    data.postprocessors = postprocessors;
                    data.createdFn = createdFn;
                    Manager.processCreate(className, this, data);
                });
            },
            processCreate: function(className, cls, clsData) {
                var me = this,
                    postprocessor = clsData.postprocessors.shift(),
                    createdFn = clsData.createdFn;
                if (!postprocessor) {
                    Ext.classSystemMonitor && Ext.classSystemMonitor(className, 'Ext.ClassManager#classCreated', arguments);
                    if (className) {
                        me.set(className, cls);
                    }
                    delete cls._classHooks;
                    if (createdFn) {
                        createdFn.call(cls, cls);
                    }
                    if (className) {
                        me.triggerCreated(className);
                    }
                    return;
                }
                if (postprocessor.call(me, className, cls, clsData, me.processCreate) !== false) {
                    me.processCreate(className, cls, clsData);
                }
            },
            createOverride: function(className, data, createdFn) {
                var me = this,
                    overriddenClassName = data.override,
                    requires = data.requires,
                    uses = data.uses,
                    mixins = data.mixins,
                    mixinsIsArray,
                    compat = data.compatibility,
                    depedenciesLoaded,
                    classReady = function() {
                        var cls, dependencies, i, key, temp;
                        if (!depedenciesLoaded) {
                            dependencies = requires ? requires.slice(0) : [];
                            if (mixins) {
                                if (!(mixinsIsArray = mixins instanceof Array)) {
                                    for (key in mixins) {
                                        if (Ext.isString(cls = mixins[key])) {
                                            dependencies.push(cls);
                                        }
                                    }
                                } else {
                                    for (i = 0 , temp = mixins.length; i < temp; ++i) {
                                        if (Ext.isString(cls = mixins[i])) {
                                            dependencies.push(cls);
                                        }
                                    }
                                }
                            }
                            depedenciesLoaded = true;
                            if (dependencies.length) {
                                
                                
                                
                                Ext.require(dependencies, classReady);
                                return;
                            }
                        }
                        
                        
                        
                        
                        if (mixinsIsArray) {
                            for (i = 0 , temp = mixins.length; i < temp; ++i) {
                                if (Ext.isString(cls = mixins[i])) {
                                    mixins[i] = Ext.ClassManager.get(cls);
                                }
                            }
                        } else if (mixins) {
                            for (key in mixins) {
                                if (Ext.isString(cls = mixins[key])) {
                                    mixins[key] = Ext.ClassManager.get(cls);
                                }
                            }
                        }
                        
                        
                        cls = me.get(overriddenClassName);
                        
                        delete data.override;
                        delete data.compatibility;
                        delete data.requires;
                        delete data.uses;
                        Ext.override(cls, data);
                        
                        
                        
                        Ext.Loader.history.push(className);
                        if (uses) {
                            
                            
                            Ext['Loader'].addUsedClasses(uses);
                        }
                        
                        if (createdFn) {
                            createdFn.call(cls, cls);
                        }
                    };
                
                Manager.overrideMap[className] = true;
                if (!compat || Ext.checkVersion(compat)) {
                    
                    me.onCreated(classReady, me, overriddenClassName);
                }
                me.triggerCreated(className, 2);
                return me;
            },
            
            instantiateByAlias: function() {
                var alias = arguments[0],
                    args = arraySlice.call(arguments),
                    className = this.getNameByAlias(alias);
                if (!className) {
                    throw new Error("[Ext.createByAlias] Unrecognized alias: " + alias);
                }
                args[0] = className;
                return Ext.create.apply(Ext, args);
            },
            
            instantiate: function() {
                Ext.log.warn('Ext.ClassManager.instantiate() is deprecated.  Use Ext.create() instead.');
                return Ext.create.apply(Ext, arguments);
            },
            
            dynInstantiate: function(name, args) {
                args = arrayFrom(args, true);
                args.unshift(name);
                return Ext.create.apply(Ext, args);
            },
            
            getInstantiator: function(length) {
                var instantiators = this.instantiators,
                    instantiator, i, args;
                instantiator = instantiators[length];
                if (!instantiator) {
                    i = length;
                    args = [];
                    for (i = 0; i < length; i++) {
                        args.push('a[' + i + ']');
                    }
                    instantiator = instantiators[length] = new Function('c','a','return new c(' + args.join(',') + ')');
                    instantiator.name = "Ext.create" + length;
                }
                return instantiator;
            },
            
            postprocessors: {},
            
            defaultPostprocessors: [],
            
            registerPostprocessor: function(name, fn, properties, position, relativeTo) {
                if (!position) {
                    position = 'last';
                }
                if (!properties) {
                    properties = [
                        name
                    ];
                }
                this.postprocessors[name] = {
                    name: name,
                    properties: properties || false,
                    fn: fn
                };
                this.setDefaultPostprocessorPosition(name, position, relativeTo);
                return this;
            },
            
            setDefaultPostprocessors: function(postprocessors) {
                this.defaultPostprocessors = arrayFrom(postprocessors);
                return this;
            },
            
            setDefaultPostprocessorPosition: function(name, offset, relativeName) {
                var defaultPostprocessors = this.defaultPostprocessors,
                    index;
                if (typeof offset === 'string') {
                    if (offset === 'first') {
                        defaultPostprocessors.unshift(name);
                        return this;
                    } else if (offset === 'last') {
                        defaultPostprocessors.push(name);
                        return this;
                    }
                    offset = (offset === 'after') ? 1 : -1;
                }
                index = Ext.Array.indexOf(defaultPostprocessors, relativeName);
                if (index !== -1) {
                    Ext.Array.splice(defaultPostprocessors, Math.max(0, index + offset), 0, name);
                }
                return this;
            }
        });
    
    Manager.registerPostprocessor('alias', function(name, cls, data) {
        Ext.classSystemMonitor && Ext.classSystemMonitor(name, 'Ext.ClassManager#aliasPostProcessor', arguments);
        var aliases = Ext.Array.from(data.alias),
            i, ln;
        for (i = 0 , ln = aliases.length; i < ln; i++) {
            alias = aliases[i];
            this.addAlias(cls, alias);
        }
    }, [
        'xtype',
        'alias'
    ]);
    
    Manager.registerPostprocessor('singleton', function(name, cls, data, fn) {
        Ext.classSystemMonitor && Ext.classSystemMonitor(name, 'Ext.ClassManager#singletonPostProcessor', arguments);
        if (data.singleton) {
            fn.call(this, name, new cls(), data);
        } else {
            return true;
        }
        return false;
    });
    
    Manager.registerPostprocessor('alternateClassName', function(name, cls, data) {
        Ext.classSystemMonitor && Ext.classSystemMonitor(name, 'Ext.ClassManager#alternateClassNamePostprocessor', arguments);
        var alternates = data.alternateClassName,
            i, ln, alternate;
        if (!(alternates instanceof Array)) {
            alternates = [
                alternates
            ];
        }
        for (i = 0 , ln = alternates.length; i < ln; i++) {
            alternate = alternates[i];
            if (typeof alternate !== 'string') {
                throw new Error("[Ext.define] Invalid alternate of: '" + alternate + "' for class: '" + name + "'; must be a valid string");
            }
            this.set(alternate, cls);
        }
    });
    
    Manager.registerPostprocessor('debugHooks', function(name, Class, data) {
        Ext.classSystemMonitor && Ext.classSystemMonitor(Class, 'Ext.Class#debugHooks', arguments);
        if (Ext.isDebugEnabled(Class.$className, data.debugHooks.$enabled)) {
            delete data.debugHooks.$enabled;
            Ext.override(Class, data.debugHooks);
        }
        
        var target = Class.isInstance ? Class.self : Class;
        delete target.prototype.debugHooks;
    });
    
    Manager.registerPostprocessor('deprecated', function(name, Class, data) {
        Ext.classSystemMonitor && Ext.classSystemMonitor(Class, 'Ext.Class#deprecated', arguments);
        
        var target = Class.isInstance ? Class.self : Class;
        target.addDeprecations(data.deprecated);
        delete target.prototype.deprecated;
    });

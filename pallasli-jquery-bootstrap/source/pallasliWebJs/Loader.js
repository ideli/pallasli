
Ext.Loader = new function() {
    var Loader = this,
        Manager = Ext.ClassManager,
        
        Boot = Ext.Boot,
        Class = Ext.Class,
        Ready = Ext.env.Ready,
        alias = Ext.Function.alias,
        dependencyProperties = [
            'extend',
            'mixins',
            'requires'
        ],
        isInHistory = {},
        history = [],
        readyListeners = [],
        usedClasses = [],
        _requiresMap = {},
        _missingQueue = {},
        _config = {
            
            enabled: true,
            
            scriptChainDelay: false,
            
            disableCaching: true,
            
            disableCachingParam: '_dc',
            
            paths: Manager.paths,
            
            preserveScripts: true,
            
            scriptCharset: undefined
        },
        
        delegatedConfigs = {
            disableCaching: true,
            disableCachingParam: true,
            preserveScripts: true,
            scriptChainDelay: 'loadDelay'
        };
    Ext.apply(Loader, {
        
        isInHistory: isInHistory,
        
        isLoading: false,
        
        history: history,
        
        config: _config,
        
        readyListeners: readyListeners,
        
        optionalRequires: usedClasses,
        
        requiresMap: _requiresMap,
        
        hasFileLoadError: false,
        
        scriptsLoading: 0,
        
        classesLoading: [],
        
        syncModeEnabled: false,
        
        missingQueue: _missingQueue,
        init: function() {
            
            var scripts = document.getElementsByTagName('script'),
                src = scripts[scripts.length - 1].src,
                path = src.substring(0, src.lastIndexOf('/') + 1),
                meta = Ext._classPathMetadata,
                microloader = Ext.Microloader,
                manifest = Ext.manifest,
                loadOrder, baseUrl, loadlen, l, loadItem;
            if (src.indexOf("packages/sencha-core/src/") !== -1) {
                path = path + "../../";
            } else if (src.indexOf("/core/src/class/") !== -1) {
                path = path + "../../../";
            }
            if (!Manager.getPath("Ext")) {
                Manager.setPath('Ext', path + 'src');
            }
            
            if (meta) {
                Ext._classPathMetadata = null;
                Loader.addClassPathMappings(meta);
            }
            if (manifest) {
                loadOrder = manifest.loadOrder;
                
                
                
                baseUrl = Ext.Boot.baseUrl;
                if (loadOrder && manifest.bootRelative) {
                    for (loadlen = loadOrder.length , l = 0; l < loadlen; l++) {
                        loadItem = loadOrder[l];
                        loadItem.path = baseUrl + loadItem.path;
                    }
                }
            }
            if (microloader) {
                Ready.block();
                microloader.onMicroloaderReady(function() {
                    Ready.unblock();
                });
            }
        },
        
        setConfig: Ext.Function.flexSetter(function(name, value) {
            if (name === 'paths') {
                Loader.setPath(value);
            } else {
                _config[name] = value;
                var delegated = delegatedConfigs[name];
                if (delegated) {
                    Boot.setConfig((delegated === true) ? name : delegated, value);
                }
            }
            return Loader;
        }),
        
        getConfig: function(name) {
            return name ? _config[name] : _config;
        },
        
        setPath: function() {
            
            Manager.setPath.apply(Manager, arguments);
            return Loader;
        },
        
        addClassPathMappings: function(paths) {
            
            Manager.setPath(paths);
            return Loader;
        },
        
        addBaseUrlClassPathMappings: function(pathConfig) {
            for (var name in pathConfig) {
                pathConfig[name] = Boot.baseUrl + pathConfig[name];
            }
            Ext.Loader.addClassPathMappings(pathConfig);
        },
        
        getPath: function(className) {
            
            return Manager.getPath(className);
        },
        require: function(expressions, fn, scope, excludes) {
            if (excludes) {
                return Loader.exclude(excludes).require(expressions, fn, scope);
            }
            var classNames = Manager.getNamesByExpression(expressions);
            return Loader.load(classNames, fn, scope);
        },
        syncRequire: function() {
            var wasEnabled = Loader.syncModeEnabled;
            Loader.syncModeEnabled = true;
            var ret = Loader.require.apply(Loader, arguments);
            Loader.syncModeEnabled = wasEnabled;
            return ret;
        },
        exclude: function(excludes) {
            var selector = Manager.select({
                    require: function(classNames, fn, scope) {
                        return Loader.load(classNames, fn, scope);
                    },
                    syncRequire: function(classNames, fn, scope) {
                        var wasEnabled = Loader.syncModeEnabled;
                        Loader.syncModeEnabled = true;
                        var ret = Loader.load(classNames, fn, scope);
                        Loader.syncModeEnabled = wasEnabled;
                        return ret;
                    }
                });
            selector.exclude(excludes);
            return selector;
        },
        load: function(classNames, callback, scope) {
            if (callback) {
                if (callback.length) {
                    
                    
                    callback = Loader.makeLoadCallback(classNames, callback);
                }
                callback = callback.bind(scope || Ext.global);
            }
            var missingClassNames = [],
                numClasses = classNames.length,
                className, i, numMissing,
                urls = [],
                state = Manager.classState;
            for (i = 0; i < numClasses; ++i) {
                className = Manager.resolveName(classNames[i]);
                if (!Manager.isCreated(className)) {
                    missingClassNames.push(className);
                    _missingQueue[className] = Loader.getPath(className);
                    if (!state[className]) {
                        urls.push(_missingQueue[className]);
                    }
                }
            }
            
            
            numMissing = missingClassNames.length;
            if (numMissing) {
                Loader.missingCount += numMissing;
                Ext.Array.push(Loader.classesLoading, missingClassNames);
                Manager.onCreated(function() {
                    Ext.Array.remove(Loader.classesLoading, missingClassNames);
                    Ext.each(missingClassNames, function(name) {
                        Ext.Array.remove(Loader.classesLoading, name);
                    });
                    if (callback) {
                        Ext.callback(callback, scope, arguments);
                    }
                    Loader.checkReady();
                }, Loader, missingClassNames);
                if (!_config.enabled) {
                    Ext.Error.raise("Ext.Loader is not enabled, so dependencies cannot be resolved dynamically. " + "Missing required class" + ((missingClassNames.length > 1) ? "es" : "") + ": " + missingClassNames.join(', '));
                }
                if (urls.length) {
                    Loader.loadScripts({
                        url: urls,
                        
                        _classNames: missingClassNames
                    });
                } else {
                    
                    
                    
                    Loader.checkReady();
                }
            } else {
                if (callback) {
                    callback.call(scope);
                }
                
                
                
                Loader.checkReady();
            }
            if (Loader.syncModeEnabled) {
                
                if (numClasses === 1) {
                    return Manager.get(classNames[0]);
                }
            }
            return Loader;
        },
        makeLoadCallback: function(classNames, callback) {
            return function() {
                var classes = [],
                    i = classNames.length;
                while (i-- > 0) {
                    classes[i] = Manager.get(classNames[i]);
                }
                return callback.apply(this, classes);
            };
        },
        onLoadFailure: function() {
            var options = this,
                onError = options.onError;
            Loader.hasFileLoadError = true;
            --Loader.scriptsLoading;
            if (onError) {
                
                onError.call(options.userScope, options);
            } else {
                Ext.log.error("[Ext.Loader] Some requested files failed to load.");
            }
            Loader.checkReady();
        },
        onLoadSuccess: function() {
            var options = this,
                onLoad = options.onLoad;
            --Loader.scriptsLoading;
            if (onLoad) {
                
                onLoad.call(options.userScope, options);
            }
            
            Loader.checkReady();
        },
        
        reportMissingClasses: function() {
            if (!Loader.syncModeEnabled && !Loader.scriptsLoading && Loader.isLoading && !Loader.hasFileLoadError) {
                var missingClasses = [],
                    missingPaths = [];
                for (var missingClassName in _missingQueue) {
                    missingClasses.push(missingClassName);
                    missingPaths.push(_missingQueue[missingClassName]);
                }
                if (missingClasses.length) {
                    throw new Error("The following classes are not declared even if their files have been " + "loaded: '" + missingClasses.join("', '") + "'. Please check the source code of their " + "corresponding files for possible typos: '" + missingPaths.join("', '"));
                }
            }
        },
        
        onReady: function(fn, scope, withDomReady, options) {
            if (withDomReady) {
                Ready.on(fn, scope, options);
            } else {
                var listener = Ready.makeListener(fn, scope, options);
                if (Loader.isLoading) {
                    readyListeners.push(listener);
                } else {
                    Ready.invoke(listener);
                }
            }
        },
        
        addUsedClasses: function(classes) {
            var cls, i, ln;
            if (classes) {
                classes = (typeof classes === 'string') ? [
                    classes
                ] : classes;
                for (i = 0 , ln = classes.length; i < ln; i++) {
                    cls = classes[i];
                    if (typeof cls === 'string' && !Ext.Array.contains(usedClasses, cls)) {
                        usedClasses.push(cls);
                    }
                }
            }
            return Loader;
        },
        
        triggerReady: function() {
            var listener,
                refClasses = usedClasses;
            if (Loader.isLoading && refClasses.length) {
                
                usedClasses = [];
                
                
                Loader.require(refClasses);
            } else {
                
                
                Loader.isLoading = false;
                
                
                readyListeners.sort(Ready.sortFn);
                
                
                
                while (readyListeners.length && !Loader.isLoading) {
                    
                    
                    listener = readyListeners.pop();
                    Ready.invoke(listener);
                }
                
                
                
                
                
                
                
                
                Ready.unblock();
            }
        },
        
        historyPush: function(className) {
            if (className && !isInHistory[className] && !Manager.overrideMap[className]) {
                isInHistory[className] = true;
                history.push(className);
            }
            return Loader;
        },
        
        loadScripts: function(params) {
            var manifest = Ext.manifest,
                loadOrder = manifest && manifest.loadOrder,
                loadOrderMap = manifest && manifest.loadOrderMap,
                options;
            ++Loader.scriptsLoading;
            
            
            if (loadOrder && !loadOrderMap) {
                manifest.loadOrderMap = loadOrderMap = Boot.createLoadOrderMap(loadOrder);
            }
            
            
            Loader.checkReady();
            options = Ext.apply({
                loadOrder: loadOrder,
                loadOrderMap: loadOrderMap,
                charset: _config.scriptCharset,
                success: Loader.onLoadSuccess,
                failure: Loader.onLoadFailure,
                sync: Loader.syncModeEnabled,
                _classNames: []
            }, params);
            options.userScope = options.scope;
            options.scope = options;
            Boot.load(options);
        },
        
        loadScriptsSync: function(urls) {
            var syncwas = Loader.syncModeEnabled;
            Loader.syncModeEnabled = true;
            Loader.loadScripts({
                url: urls
            });
            Loader.syncModeEnabled = syncwas;
        },
        
        loadScriptsSyncBasePrefix: function(urls) {
            var syncwas = Loader.syncModeEnabled;
            Loader.syncModeEnabled = true;
            Loader.loadScripts({
                url: urls,
                prependBaseUrl: true
            });
            Loader.syncModeEnabled = syncwas;
        },
        
        loadScript: function(options) {
            var isString = typeof options === 'string',
                isArray = options instanceof Array,
                isObject = !isArray && !isString,
                url = isObject ? options.url : options,
                onError = isObject && options.onError,
                onLoad = isObject && options.onLoad,
                scope = isObject && options.scope,
                request = {
                    url: url,
                    scope: scope,
                    onLoad: onLoad,
                    onError: onError,
                    _classNames: []
                };
            Loader.loadScripts(request);
        },
        
        flushMissingQueue: function() {
            var name, val,
                missingwas = 0,
                missing = 0;
            for (name in _missingQueue) {
                missingwas++;
                val = _missingQueue[name];
                if (Manager.isCreated(name)) {
                    delete _missingQueue[name];
                } else if (Manager.existCache[name] === 2) {
                    delete _missingQueue[name];
                } else {
                    ++missing;
                }
            }
            this.missingCount = missing;
        },
        
        checkReady: function() {
            var wasLoading = Loader.isLoading,
                isLoading;
            Loader.flushMissingQueue();
            isLoading = Loader.missingCount + Loader.scriptsLoading;
            if (isLoading && !wasLoading) {
                Ready.block();
                Loader.isLoading = !!isLoading;
            } else if (!isLoading && wasLoading) {
                Loader.triggerReady();
            }
        }
    });
    
    Ext.require = alias(Loader, 'require');
    
    Ext.syncRequire = alias(Loader, 'syncRequire');
    
    Ext.exclude = alias(Loader, 'exclude');
    
    Class.registerPreprocessor('loader', function(cls, data, hooks, continueFn) {
        Ext.classSystemMonitor && Ext.classSystemMonitor(cls, 'Ext.Loader#loaderPreprocessor', arguments);
        var me = this,
            dependencies = [],
            dependency,
            className = Manager.getName(cls),
            i, j, ln, subLn, value, propertyName, propertyValue, requiredMap;
        
        for (i = 0 , ln = dependencyProperties.length; i < ln; i++) {
            propertyName = dependencyProperties[i];
            if (data.hasOwnProperty(propertyName)) {
                propertyValue = data[propertyName];
                if (typeof propertyValue == 'string') {
                    dependencies.push(propertyValue);
                } else if (propertyValue instanceof Array) {
                    for (j = 0 , subLn = propertyValue.length; j < subLn; j++) {
                        value = propertyValue[j];
                        if (typeof value == 'string') {
                            dependencies.push(value);
                        }
                    }
                } else if (typeof propertyValue != 'function') {
                    for (j in propertyValue) {
                        if (propertyValue.hasOwnProperty(j)) {
                            value = propertyValue[j];
                            if (typeof value == 'string') {
                                dependencies.push(value);
                            }
                        }
                    }
                }
            }
        }
        if (dependencies.length === 0) {
            return;
        }
        if (className) {
            _requiresMap[className] = dependencies;
        }
        var deadlockPath = [],
            detectDeadlock;
        
        if (className) {
            requiredMap = Loader.requiredByMap || (Loader.requiredByMap = {});
            for (i = 0 , ln = dependencies.length; i < ln; i++) {
                dependency = dependencies[i];
                (requiredMap[dependency] || (requiredMap[dependency] = [])).push(className);
            }
            detectDeadlock = function(cls) {
                deadlockPath.push(cls);
                if (_requiresMap[cls]) {
                    if (Ext.Array.contains(_requiresMap[cls], className)) {
                        Ext.Error.raise("Circular requirement detected! '" + className + "' and '" + deadlockPath[1] + "' mutually require each other. Path: " + deadlockPath.join(' -> ') + " -> " + deadlockPath[0]);
                    }
                    for (i = 0 , ln = _requiresMap[cls].length; i < ln; i++) {
                        detectDeadlock(_requiresMap[cls][i]);
                    }
                }
            };
            detectDeadlock(className);
        }
        (className ? Loader.exclude(className) : Loader).require(dependencies, function() {
            for (i = 0 , ln = dependencyProperties.length; i < ln; i++) {
                propertyName = dependencyProperties[i];
                if (data.hasOwnProperty(propertyName)) {
                    propertyValue = data[propertyName];
                    if (typeof propertyValue == 'string') {
                        data[propertyName] = Manager.get(propertyValue);
                    } else if (propertyValue instanceof Array) {
                        for (j = 0 , subLn = propertyValue.length; j < subLn; j++) {
                            value = propertyValue[j];
                            if (typeof value == 'string') {
                                data[propertyName][j] = Manager.get(value);
                            }
                        }
                    } else if (typeof propertyValue != 'function') {
                        for (var k in propertyValue) {
                            if (propertyValue.hasOwnProperty(k)) {
                                value = propertyValue[k];
                                if (typeof value == 'string') {
                                    data[propertyName][k] = Manager.get(value);
                                }
                            }
                        }
                    }
                }
            }
            continueFn.call(me, cls, data, hooks);
        });
        return false;
    }, true, 'after', 'className');
    
    Manager.registerPostprocessor('uses', function(name, cls, data) {
        Ext.classSystemMonitor && Ext.classSystemMonitor(cls, 'Ext.Loader#usesPostprocessor', arguments);
        var manifest = Ext.manifest,
            loadOrder = manifest && manifest.loadOrder,
            classes = manifest && manifest.classes,
            uses, clazz, item, len, i, indexMap;
        if (loadOrder) {
            clazz = classes[name];
            if (clazz && !isNaN(i = clazz.idx)) {
                item = loadOrder[i];
                uses = item.uses;
                indexMap = {};
                for (len = uses.length , i = 0; i < len; i++) {
                    indexMap[uses[i]] = true;
                }
                uses = Ext.Boot.getPathsFromIndexes(indexMap, loadOrder, true);
                if (uses.length > 0) {
                    Loader.loadScripts({
                        url: uses,
                        sequential: true
                    });
                }
            }
        }
        if (data.uses) {
            uses = data.uses;
            Loader.addUsedClasses(uses);
        }
    });
    Manager.onCreated(Loader.historyPush);
    Loader.init();
}();
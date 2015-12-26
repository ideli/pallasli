var Pallasli = Pallasli || {};

Ext.Boot = Ext.Boot || (function (emptyFn) {

    var doc = document,
        apply = function (dest, src, defaults) {
            if (defaults) {
                apply(dest, defaults);
            }

            if (dest && src && typeof src == 'object') {
                for (var key in src) {
                    dest[key] = src[key];
                }
            }
            return dest;
        },
        _config = {
            
            disableCaching: (/[?&](?:cache|disableCacheBuster)\b/i.test(location.search) ||
                !(/http[s]?\:/i.test(location.href)) ||
                /(^|[ ;])ext-cache=1/.test(doc.cookie)) ? false :
                true,
            
            disableCachingParam: '_dc',
            loadDelay: false,
            preserveScripts: true,
            charset: undefined
        },
        cssRe = /\.css(?:\?|$)/i,
        resolverEl = doc.createElement('a'),
        isBrowser = typeof window !== 'undefined',
        _environment = {
            browser: isBrowser,
            node: !isBrowser && (typeof require === 'function'),
            phantom: (typeof phantom !== 'undefined' && phantom.fs)
        },
        _tags = (Ext.platformTags = {}),
        _debug = function (message) {
        },
        _apply = function (object, config, defaults) {
            if (defaults) {
                _apply(object, defaults);
            }
            if (object && config && typeof config === 'object') {
                for (var i in config) {
                    object[i] = config[i];
                }
            }
            return object;
        },
    
        Boot = {
            loading: 0,
            loaded: 0,
            env: _environment,
            config: _config,
            scripts: { },
            currentFile: null,
            suspendedQueue: [],
            currentRequest: null,
            syncMode: false,
            debug: _debug,
            useElements: true,
            listeners: [],
            Request: Request,
            Entry: Entry,
            detectPlatformTags: function () {
                var ua = navigator.userAgent,
                    isMobile = _tags.isMobile = /Mobile(\/|\s)/.test(ua),
                    isPhone, isDesktop, isTablet, touchSupported, isIE10, isBlackberry,
                    element = document.createElement('div'),
                    uaTagChecks = [
                        'iPhone',
                        'iPod',
                        'Android',
                        'Silk',
                        'Android 2',
                        'BlackBerry',
                        'BB',
                        'iPad',
                        'RIM Tablet OS',
                        'MSIE 10',
                        'Trident',
                        'Chrome',
                        'Tizen',
                        'Firefox',
                        'Safari',
                        'Windows Phone'
                    ],
                    isEventSupported = function(name, tag) {
                        if (tag === undefined) {
                            tag = window;
                        }

                        var eventName = 'on' + name.toLowerCase(),
                            isSupported = (eventName in element);

                        if (!isSupported) {
                            if (element.setAttribute && element.removeAttribute) {
                                element.setAttribute(eventName, '');
                                isSupported = typeof element[eventName] === 'function';

                                if (typeof element[eventName] !== 'undefined') {
                                    element[eventName] = undefined;
                                }

                                element.removeAttribute(eventName);
                            }
                        }

                        return isSupported;
                    },
                    uaTags = {},
                    len = uaTagChecks.length, check, c;

                for (c = 0; c < len; c++) {
                    check = uaTagChecks[c];
                    uaTags[check] = new RegExp(check).test(ua);
                }

                isPhone =
                    (uaTags.iPhone || uaTags.iPod) ||
                    (!uaTags.Silk && (uaTags.Android && (uaTags['Android 2'] || isMobile))) ||
                    ((uaTags.BlackBerry || uaTags.BB) && uaTags.isMobile) ||
                    (uaTags['Windows Phone']);

                isTablet =
                    (!_tags.isPhone) && (
                    uaTags.iPad ||
                    uaTags.Android ||
                    uaTags.Silk ||
                    uaTags['RIM Tablet OS'] ||
                    (uaTags['MSIE 10'] && /; Touch/.test(ua))
                    );

                touchSupported =
                    
                    
                    isEventSupported('touchend') ||
                    
                    
                    
                    navigator.maxTouchPoints ||
                    
                    navigator.msMaxTouchPoints;

                isDesktop = !isPhone && !isTablet;
                isIE10 = uaTags['MSIE 10'];
                isBlackberry = uaTags.Blackberry || uaTags.BB;

                apply(_tags, Boot.loadPlatformsParam(), {
                    phone: isPhone,
                    tablet: isTablet,
                    desktop: isDesktop,
                    touch: touchSupported,
                    ios: (uaTags.iPad || uaTags.iPhone || uaTags.iPod),
                    android: uaTags.Android || uaTags.Silk,
                    blackberry: isBlackberry,
                    safari: uaTags.Safari && !isBlackberry,
                    chrome: uaTags.Chrome,
                    ie10: isIE10,
                    windows: isIE10 || uaTags.Trident,
                    tizen: uaTags.Tizen,
                    firefox: uaTags.Firefox
                });
            },

            
            loadPlatformsParam: function () {
                
                var paramsString = window.location.search.substr(1),
                    paramsArray = paramsString.split("&"),
                    params = {}, i,
                    platforms = {},
                    tmpArray, tmplen, platform, name, enabled;

                for (i = 0; i < paramsArray.length; i++) {
                    tmpArray = paramsArray[i].split("=");
                    params[tmpArray[0]] = tmpArray[1];
                }

                if (params.platformTags) {
                    tmpArray = params.platform.split(/\W/);
                    for (tmplen = tmpArray.length, i = 0; i < tmplen; i++) {
                        platform = tmpArray[i].split(":");
                        name = platform[0];
                        if (platform.length > 1) {
                            enabled = platform[1];
                            if (enabled === 'false' || enabled === '0') {
                                enabled = false;
                            } else {
                                enabled = true;
                            }
                        }
                        platforms[name] = enabled;
                    }
                }
                return platform;
            },

            filterPlatform: function (platform) {
                platform = [].concat(platform);
                var len, p, tag;

                for (len = platform.length, p = 0; p < len; p++) {
                    tag = platform[p];
                    if (_tags.hasOwnProperty(tag)) {
                        return !!_tags[tag];
                    }
                }
                return false;
            },

            init: function () {
                var scriptEls = doc.getElementsByTagName('script'),
                    len = scriptEls.length,
                    re = /\/ext(\-[a-z\-]+)?\.js$/,
                    entry, script, src, state, baseUrl, key, n, origin;
                
                for (n = 0; n < len; n++) {
                    src = (script = scriptEls[n]).src;
                    if (!src) {
                        continue;
                    }
                    state = script.readyState || null;

                    
                    if (!baseUrl) {
                        if (re.test(src)) {
                            Boot.hasReadyState = ("readyState" in script);
                            Boot.hasAsync = ("async" in script) || !Boot.hasReadyState;
                            baseUrl = src;
                        }
                    }

                    if (!Boot.scripts[key = Boot.canonicalUrl(src)]) {
                        
                        _debug("creating entry " + key + " in Boot.init");
                        
                        entry = new Entry({
                            key: key,
                            url: src,
                            done: state === null ||  
                                state === 'loaded' || state === 'complete', 
                            el: script,
                            prop: 'src'
                        });
                    }
                }

                if (!baseUrl) {
                    script = scriptEls[scriptEls.length - 1];
                    baseUrl = script.src;
                    Boot.hasReadyState = ('readyState' in script);
                    Boot.hasAsync = ("async" in script) || !Boot.hasReadyState;
                }

                Boot.baseUrl = baseUrl.substring(0, baseUrl.lastIndexOf('/') + 1);
                origin = window.location.origin ||
                    window.location.protocol +
                    "//" +
                    window.location.hostnaBoot +
                    (window.location.port ? ':' + window.location.port: '');
                Boot.origin = origin;

                Boot.detectPlatformTags();
                Ext.filterPlatform = Boot.filterPlatform;
            },

            
            canonicalUrl: function (url) {
                
                
                resolverEl.href = url;

                var ret = resolverEl.href,
                    dc = _config.disableCachingParam,
                    pos = dc ? ret.indexOf(dc + '=') : -1,
                    c, end;

                
                
                if (pos > 0 && ((c = ret.charAt(pos - 1)) === '?' || c === '&')) {
                    end = ret.indexOf('&', pos);
                    end = (end < 0) ? '' : ret.substring(end);
                    if (end && c === '?') {
                        ++pos; 
                        end = end.substring(1); 
                    }
                    ret = ret.substring(0, pos - 1) + end;
                }

                return ret;
            },

            
            getConfig: function (name) {
                return name ? Boot.config[name] : Boot.config;
            },

            
            setConfig: function (name, value) {
                if (typeof name === 'string') {
                    Boot.config[name] = value;
                } else {
                    for (var s in name) {
                        Boot.setConfig(s, name[s]);
                    }
                }
                return Boot;
            },

            getHead: function () {
                return Boot.docHead ||
                    (Boot.docHead = doc.head ||
                        doc.getElementsByTagName('head')[0]);
            },

            create: function (url, key, cfg) {
                var config = cfg || {};
                config.url = url;
                config.key = key;
                return Boot.scripts[key] = new Entry(config);
            },

            getEntry: function (url, cfg) {
                var key = Boot.canonicalUrl(url),
                    entry = Boot.scripts[key];
                if (!entry) {
                    entry = Boot.create(url, key, cfg);
                }
                return entry;
            },

            processRequest: function(request, sync) {
                request.loadEntries(sync);
            },

            load: function (request) {
                
                _debug("Boot.load called");
                
                var request = new Request(request);

                if (request.sync || Boot.syncMode) {
                    return Boot.loadSync(request);
                }
                if (Boot.currentRequest) {
                    _debug("current active request, suspending this request");
                    request.getEntries();
                    Boot.suspendedQueue.push(request);
                } else {
                    Boot.currentRequest = request;
                    Boot.processRequest(request, false);
                }
                return Boot;
            },

            loadSync: function (request) {
                
                _debug("Boot.loadSync called");
                
                var request = new Request(request);

                Boot.syncMode++;
                Boot.processRequest(request, true);
                Boot.syncMode--;
                return Boot;
            },

            loadBasePrefix: function(request) {
                request = new Request(request);
                request.prependBaseUrl = true;
                return Boot.load(request);
            },

            loadSyncBasePrefix: function(request) {
                request = new Request(request);
                request.prependBaseUrl = true;
                return Boot.loadSync(request);
            },

            requestComplete: function(request) {
                var next;

                if (Boot.currentRequest === request) {
                    Boot.currentRequest = null;
                    while(Boot.suspendedQueue.length > 0) {
                        next = Boot.suspendedQueue.shift();
                        if(!next.done) {
                            
                            _debug("resuming suspended request");
                            
                            Boot.load(next);
                            break;
                        }
                    }
                }
                if (!Boot.currentRequest && Boot.suspendedQueue.length == 0) {
                    Boot.fireListeners();
                }
            },

            isLoading: function () {
                return !Boot.currentRequest && Boot.suspendedQueue.length == 0;
            },

            fireListeners: function () {
                var listener;
                while (Boot.isLoading() && (listener = Boot.listeners.shift())) {
                    listener();
                }
            },

            onBootReady: function (listener) {
                if (!Boot.isLoading()) {
                    listener();
                } else {
                    Boot.listeners.push(listener);
                }
            },

            
            getPathsFromIndexes: function (indexMap, loadOrder) {
                return Request.prototype.getPathsFromIndexes(indexMap, loadOrder);
            },

            createLoadOrderMap: function(loadOrder) {
                return Request.prototype.createLoadOrderMap(loadOrder);
            },

            fetch: function(url, complete, scope, async) {
                async = (async === undefined) ? !!complete : async;

                var xhr = new XMLHttpRequest(),
                    result, status, content, exception = false,
                    readyStateChange = function () {
                        if (xhr && xhr.readyState == 4) {
                            status = (xhr.status === 1223) ? 204 :
                                (xhr.status === 0 && ((self.location || {}).protocol === 'file:' ||
                                    (self.location || {}).protocol === 'ionp:')) ? 200 : xhr.status;
                            content = xhr.responseText;
                            result = {
                                content: content,
                                status: status,
                                exception: exception
                            };
                            if (complete) {
                                complete.call(scope, result);
                            }
                            xhr = null;
                        }
                    };

                if (async) {
                    xhr.onreadystatechange = readyStateChange;
                }

                try {
                    
                    _debug("fetching " + url + " " + (async ? "async" : "sync"));
                    
                    xhr.open('GET', url, async);
                    xhr.send(null);
                } catch (err) {
                    exception = err;
                    readyStateChange();
                    return result;
                }

                if (!async) {
                    readyStateChange();
                }

                return result;
            },

            notifyAll: function(entry) {
                entry.notifyRequests();
            }
        };

    
    function Request(cfg) {
        if(cfg.$isRequest) {
            return cfg;
        }

        var cfg = cfg.url ? cfg : {url: cfg},
            url = cfg.url,
            urls = url.charAt ? [ url ] : url,
            charset = cfg.charset || Boot.config.charset;

        _apply(cfg, {
            urls: urls,
            charset: charset
        });
        _apply(this, cfg);
    };
    Request.prototype = {
        $isRequest: true,

        
        createLoadOrderMap: function (loadOrder) {
            var len = loadOrder.length,
                loadOrderMap = {},
                i, element;

            for (i = 0; i < len; i++) {
                element = loadOrder[i];
                loadOrderMap[element.path] = element;
            }

            return loadOrderMap;
        },

        
        getLoadIndexes: function (index, indexMap, loadOrder, includeUses, skipLoaded) {
            var item = loadOrder[index],
                len, i, reqs, entry, stop, added, idx, ridx, url;

            if (indexMap[index]) {
                
                return indexMap;
            }

            indexMap[index] = true;

            stop = false;
            while (!stop) {
                added = false;

                
                
                for (idx in indexMap) {
                    if (indexMap.hasOwnProperty(idx)) {
                        item = loadOrder[idx];
                        if (!item) {
                            continue;
                        }
                        url = this.prepareUrl(item.path);
                        entry = Boot.getEntry(url);
                        if (!skipLoaded || !entry || !entry.done) {
                            reqs = item.requires;
                            if (includeUses && item.uses) {
                                reqs = reqs.concat(item.uses);
                            }
                            for (len = reqs.length, i = 0; i < len; i++) {
                                ridx = reqs[i];
                                
                                
                                
                                
                                if (!indexMap[ridx]) {
                                    indexMap[ridx] = true;
                                    added = true;
                                }
                            }
                        }
                    }
                }

                
                
                if (!added) {
                    stop = true;
                }
            }

            return indexMap;
        },

        getPathsFromIndexes: function (indexMap, loadOrder) {
            var indexes = [],
                paths = [],
                index, len, i;

            for (index in indexMap) {
                if (indexMap.hasOwnProperty(index) && indexMap[index]) {
                    indexes.push(index);
                }
            }

            indexes.sort(function (a, b) {
                return a - b;
            });

            
            for (len = indexes.length, i = 0; i < len; i++) {
                paths.push(loadOrder[indexes[i]].path);
            }

            return paths;
        },

        expandUrl: function (url, indexMap, includeUses, skipLoaded) {
            if (typeof url == 'string') {
                url = [url];
            }

            var me = this,
                loadOrder = me.loadOrder,
                loadOrderMap = me.loadOrderMap;

            if (loadOrder) {
                loadOrderMap = loadOrderMap || me.createLoadOrderMap(loadOrder);
                me.loadOrderMap = loadOrderMap;
                indexMap = indexMap || {};
                var len = url.length,
                    unmapped = [],
                    i, item;

                for (i = 0; i < len; i++) {
                    item = loadOrderMap[url[i]];
                    if (item) {
                        me.getLoadIndexes(item.idx, indexMap, loadOrder, includeUses, skipLoaded);
                    } else {
                        unmapped.push(url[i]);
                    }
                }


                return me.getPathsFromIndexes(indexMap, loadOrder).concat(unmapped);
            }
            return url;
        },

        expandUrls: function (urls, includeUses) {
            if (typeof urls == "string") {
                urls = [urls];
            }

            var expanded = [],
                expandMap = {},
                tmpExpanded,
                len = urls.length,
                i, t, tlen, tUrl;

            for (i = 0; i < len; i++) {
                tmpExpanded = this.expandUrl(urls[i], {}, includeUses, true);
                for (t = 0, tlen = tmpExpanded.length; t < tlen; t++) {
                    tUrl = tmpExpanded[t];
                    if (!expandMap[tUrl]) {
                        expandMap[tUrl] = true;
                        expanded.push(tUrl);
                    }
                }
            }

            if (expanded.length == 0) {
                expanded = urls;
            }

            return expanded;
        },

        expandLoadOrder: function () {
            var me = this,
                urls = me.urls,
                expanded;

            if (!me.expanded) {
                expanded = this.expandUrls(urls, true);
                me.expanded = true;
            } else {
                expanded = urls;
            }

            me.urls = expanded;

            
            
            if (urls.length != expanded.length) {
                me.sequential = true;
            }

            return me;
        },

        getUrls: function () {
            this.expandLoadOrder();
            return this.urls;
        },

        prepareUrl: function(url) {
            if(this.prependBaseUrl) {
                return Boot.baseUrl + url;
            }
            return url;
        },

        getEntries: function () {
            var me = this,
                entries = me.entries,
                i, entry, urls, url;
            if (!entries) {
                entries = [];
                urls = me.getUrls();
                for (i = 0; i < urls.length; i++) {
                    url = me.prepareUrl(urls[i]);
                    entry = Boot.getEntry(url, {
                        buster: me.buster,
                        charset: me.charset
                    });
                    entry.requests.push(me);
                    entries.push(entry);
                }
                me.entries = entries;
            }
            return entries;
        },

        loadEntries: function(sync) {
            var me = this,
                entries = me.getEntries(),
                len = entries.length,
                start = me.loadStart || 0,
                continueLoad, entry, i;

            if(sync !== undefined) {
                me.sync = sync;
            }

            me.loaded = me.loaded || 0;
            me.loading = me.loading || len;

            for(i = start; i < len; i++) {
                entry = entries[i];
                if(!entry.loaded) {
                    continueLoad = entries[i].load(me.sync);
                } else {
                    continueLoad = true;
                }
                if(!continueLoad) {
                    me.loadStart = i;
                    entry.onDone(function(){
                        me.loadEntries(sync);
                    });
                    break;
                }
            }
            me.processLoadedEntries();
        },

        processLoadedEntries: function () {
            var me = this,
                entries = me.getEntries(),
                len = entries.length,
                start = me.startIndex || 0,
                i, entry;

            if (!me.done) {
                for (i = start; i < len; i++) {
                    entry = entries[i];

                    if (!entry.loaded) {
                        me.startIndex = i;
                        return;
                    }

                    if (!entry.evaluated) {
                        entry.evaluate();
                    }

                    if (entry.error) {
                        me.error = true;
                    }
                }
                me.notify();
            }
        },

        notify: function () {
            var me = this;
            if (!me.done) {
                var error = me.error,
                    fn = me[error ? 'failure' : 'success'],
                    delay = ('delay' in me)
                        ? me.delay
                        : (error ? 1 : Boot.config.chainDelay),
                    scope = me.scope || me;
                me.done = true;
                if (fn) {
                    if (delay === 0 || delay > 0) {
                        
                        setTimeout(function () {
                            fn.call(scope, me);
                        }, delay);
                    } else {
                        fn.call(scope, me);
                    }
                }
                me.fireListeners();
                Boot.requestComplete(me);
            }
        },

        onDone: function(listener) {
            var me = this,
                listeners = me.listeners || (me.listeners = []);
            if(me.done) {
                listener(me);
            } else {
                listeners.push(listener);
            }
        },

        fireListeners: function() {
            var listeners = this.listeners,
                listener;
            if(listeners) {
                
                _debug("firing request listeners");
                
                while((listener = listeners.shift())) {
                    listener(this);
                }
            }
        }
    };

    
    function Entry(cfg) {
        if(cfg.$isEntry) {
            return cfg;
        }

        
        _debug("creating entry for " + cfg.url);
        

        var charset = cfg.charset || Boot.config.charset,
            manifest = Ext.manifest,
            loader = manifest && manifest.loader,
            cache = (cfg.cache !== undefined) ? cfg.cache : (loader && loader.cache),
            buster, busterParam;

        if(cache === undefined) {
            cache = !Boot.config.disableCaching;
        }

        if(cache === false) {
            buster = +new Date();
        } else if(cache !== true) {
            buster = cache;
        }

        if(buster) {
            busterParam = (loader && loader.cacheParam) || Boot.config.disableCachingParam;
            buster = busterParam + "=" + buster;
        };

        _apply(cfg, {
            charset: charset,
            buster: buster,
            requests: []
        });
        _apply(this, cfg);
    };
    Entry.prototype = {
        $isEntry: true,
        done: false,
        evaluated: false,
        loaded: false,

        isCrossDomain: function() {
            var me = this;
            if(me.crossDomain === undefined) {
                
                _debug("checking " + me.getLoadUrl() + " for prefix " + Boot.origin);
                
                me.crossDomain = (me.getLoadUrl().indexOf(Boot.origin) !== 0);
            }
            return me.crossDomain;
        },

        isCss: function () {
            var me = this;
            if (me.css === undefined) {
                me.css = me.url && cssRe.test(me.url);
            }
            return this.css;
        },

        getElement: function (tag) {
            var me = this,
                el = me.el;
            if (!el) {
                
                _debug("creating element for " + me.url);
                
                if (me.isCss()) {
                    tag = tag || "link";
                    el = doc.createElement(tag);
                    if(tag == "link") {
                        el.rel = 'stylesheet';
                        me.prop = 'href';
                    } else {
                        me.prop="textContent";
                    }
                    el.type = "text/css";
                } else {
                    tag = tag || "script";
                    el = doc.createElement(tag);
                    el.type = 'text/javascript';
                    me.prop = 'src';
                    if (Boot.hasAsync) {
                        el.async = false;
                    }
                }
                me.el = el;
            }
            return el;
        },

        getLoadUrl: function () {
            var me = this,
                url = Boot.canonicalUrl(me.url);
            if (!me.loadUrl) {
                me.loadUrl = !!me.buster
                    ? (url + (url.indexOf('?') === -1 ? '?' : '&') + me.buster)
                    : url;
            }
            return me.loadUrl;
        },

        fetch: function (req) {
            var url = this.getLoadUrl(),
                async = !!req.async,
                complete = req.complete;

            Boot.fetch(url, complete, this, async);
        },

        onContentLoaded: function (response) {
            var me = this,
                status = response.status,
                content = response.content,
                exception = response.exception,
                url = this.getLoadUrl();
            me.loaded = true;
            if ((exception || status === 0) && !_environment.phantom) {
                me.error =
                    
                    ("Failed loading synchronously via XHR: '" + url +
                        "'. It's likely that the file is either being loaded from a " +
                        "different domain or from the local file system where cross " +
                        "origin requests are not allowed for security reasons. Try " +
                        "asynchronous loading instead.") ||
                    
                    true;
                me.evaluated = true;
            }
            else if ((status >= 200 && status < 300) || status === 304
                || _environment.phantom
                || (status === 0 && content.length > 0)
                ) {
                me.content = content;
            }
            else {
                me.error =
                    
                    ("Failed loading synchronously via XHR: '" + url +
                        "'. Please verify that the file exists. XHR status code: " +
                        status) ||
                    
                    true;
                me.evaluated = true;
            }
        },

        createLoadElement: function(callback) {
            var me = this,
                el = me.getElement(),
                readyStateChange = function(){
                    if (this.readyState === 'loaded' || this.readyState === 'complete') {
                        if(callback) {
                            callback();
                        }
                    }
                },
                errorFn = function() {
                    me.error = true;
                    if(callback) {
                        callback();
                    }
                };
            me.preserve = true;
            el.onerror = errorFn;
            if(Boot.hasReadyState) {
                el.onreadystatechange = readyStateChange;
            } else {
                el.onload = callback;
            }
            
            el[me.prop] = me.getLoadUrl();
        },

        onLoadElementReady: function() {
            Boot.getHead().appendChild(this.getElement());
            this.evaluated = true;
        },

        inject: function (content, asset) {
            
            _debug("injecting content for " + this.url);
            
            var me = this,
                head = Boot.getHead(),
                url = me.url,
                key = me.key,
                base, el, ieMode, basePath;

            if (me.isCss()) {
                me.preserve = true;
                basePath = key.substring(0, key.lastIndexOf("/") + 1);
                base = doc.createElement('base');
                base.href = basePath;
                if(head.firstChild) {
                    head.insertBefore(base, head.firstChild);
                } else {
                    head.appendChild(base);
                }
                
                base.href = base.href;

                if (url) {
                    content += "\n/*# sourceURL=" + key + " */";
                }

                
                el = me.getElement("style");

                ieMode = ('styleSheet' in el);

                head.appendChild(base);
                if(ieMode) {
                    head.appendChild(el);
                    el.styleSheet.cssText = content;
                } else {
                    el.textContent = content;
                    head.appendChild(el);
                }
                head.removeChild(base);

            } else {
                
                
                
                if (url) {
                    content += "\n//# sourceURL=" + key;
                }
                Ext.globalEval(content);
            }
            return me;
        },

        loadCrossDomain: function() {
            var me = this,
                complete = function(){
                    me.loaded = me.evaluated = me.done = true;
                    me.notifyRequests();
                };
            if(me.isCss()) {
                me.createLoadElement();
                me.evaluateLoadElement();
                complete();
            } else {
                me.createLoadElement(function(){
                    complete();
                });
                me.evaluateLoadElement();
                
                
                
                return false;
            }
            return true;
        },

        loadElement: function() {
            var me = this,
                complete = function(){
                    me.loaded = me.evaluated = me.done = true;
                    me.notifyRequests();
                };
            if(me.isCss()) {
                return me.loadCrossDomain();
            } else {
                me.createLoadElement(function(){
                    complete();
                });
                me.evaluateLoadElement();
            }
            return true;
        },

        loadSync: function() {
            var me = this;
            me.fetch({
                async: false,
                complete: function (response) {
                    me.onContentLoaded(response);
                }
            });
            me.evaluate();
            me.notifyRequests();
        },

        load: function (sync) {
            var me = this;
            if (!me.loaded) {
                if(me.loading) {
                    
                    
                    
                    
                    
                    
                    
                    return false;
                }
                me.loading = true;

                
                if (!sync) {
                    
                    
                    if(me.isCrossDomain()) {
                        return me.loadCrossDomain();
                    }
                    
                    
                    
                    else if(!me.isCss() && Boot.hasReadyState) {
                        me.createLoadElement(function () {
                            me.loaded = true;
                            me.notifyRequests();
                        });
                    }

                    else if(Boot.useElements) {
                        return me.loadElement();
                    }
                    
                    
                    else {
                        me.fetch({
                            async: !sync,
                            complete: function (response) {
                                me.onContentLoaded(response);
                                me.notifyRequests();
                            }
                        });
                    }
                }

                
                
                
                else {
                    me.loadSync();
                }
            }
            
            return true;
        },

        evaluateContent: function () {
            this.inject(this.content);
            this.content = null;
        },

        evaluateLoadElement: function() {
            Boot.getHead().appendChild(this.getElement());
        },

        evaluate: function () {
            var me = this;
            if(!me.evaluated) {
                if(me.evaluating) {
                    return;
                }
                me.evaluating = true;
                if(me.content !== undefined) {
                    me.evaluateContent();
                } else if(!me.error) {
                    me.evaluateLoadElement();
                }
                me.evaluated = me.done = true;
                me.cleanup();
            }
        },

        
        cleanup: function () {
            var me = this,
                el = me.el,
                prop;

            if (!el) {
                return;
            }

            if (!me.preserve) {
                me.el = null;

                el.parentNode.removeChild(el); 

                for (prop in el) {
                    try {
                        if (prop !== me.prop) {
                            
                            
                            el[prop] = null;
                        }
                        delete el[prop];      
                    } catch (cleanEx) {
                        
                    }
                }
            }

            
            
            
            el.onload = el.onerror = el.onreadystatechange = emptyFn;
        },

        notifyRequests: function () {
            var requests = this.requests,
                len = requests.length,
                i, request;
            for (i = 0; i < len; i++) {
                request = requests[i];
                request.processLoadedEntries();
            }
            if(this.done) {
                this.fireListeners();
            }
        },

        onDone: function(listener) {
            var me = this,
                listeners = me.listeners || (me.listeners = []);
            if(me.done) {
                listener(me);
            } else {
                listeners.push(listener);
            }
        },

        fireListeners: function() {
            var listeners = this.listeners,
                listener;
            if(listeners && listeners.length > 0) {
                
                _debug("firing event listeners for url " + this.url);
                
                while((listener = listeners.shift())) {
                    listener(this);
                }
            }
        }
    };

    
    Ext.disableCacheBuster = function (disable, path) {
        var date = new Date();
        date.setTime(date.getTime() + (disable ? 10 * 365 : -1) * 24 * 60 * 60 * 1000);
        date = date.toGMTString();
        doc.cookie = 'ext-cache=1; expires=' + date + '; path=' + (path || '/');
    };


    if (_environment.node) {
        Boot.prototype.load = Boot.prototype.loadSync = function (request) {
            
            require(filePath);
            onLoad.call(scope);
        };
        Boot.prototype.init = emptyFn;
    }


    Boot.init();
    return Boot;



}(function () {
}));


Ext.globalEval = Ext.globalEval || (this.execScript
    ? function (code) { execScript(code); }
    : function ($$code) { eval.call(window, $$code); });



if (!Function.prototype.bind) {
    (function () {
        var slice = Array.prototype.slice,
        
        
            bind = function (me) {
                var args = slice.call(arguments, 1),
                    method = this;

                if (args.length) {
                    return function () {
                        var t = arguments;
                        
                        return method.apply(me, t.length ? args.concat(slice.call(t)) : args);
                    };
                }
                

                args = null;
                return function () {
                    return method.apply(me, arguments);
                };
            };
        Function.prototype.bind = bind;
        bind.$extjs = true; 
    }());
}

(function(manifest){
    if(!Ext.manifest) {
        Ext.manifest = manifest;
    } else {
        for(var name in manifest) {
            Ext.manifest[name] = manifest[name];
        }
    }
})({
  "paths": {
    "Ext": "../src",
    "Ext.AbstractManager": "../packages/sencha-core/src/AbstractManager.js",
    "Ext.Ajax": "../packages/sencha-core/src/Ajax.js",
    "Ext.AnimationQueue": "../packages/sencha-core/src/AnimationQueue.js",
  },
  "loadOrder": [
    {
      "path": "../packages/sencha-core/src/class/Mixin.js",
      "requires": [],
      "uses": [],
      "idx": 0
    },
    {
      "path": "../packages/sencha-core/src/util/DelayedTask.js",
      "requires": [],
      "uses": [
        70
      ],
      "idx": 1
    },
    {
      "path": "../packages/sencha-core/src/mixin/Observable.js",
      "requires": [
        0,
        2,
        3
      ],
      "uses": [
        45
      ],
      "idx": 4
    }
  ],
  "classes": {
    "Ext.AbstractManager": {
      "idx": 6,
      "alias": [],
      "alternates": []
    },
    "Ext.Action": {
      "idx": 306,
      "alias": [],
      "alternates": []
    },
    "Ext.window.Window": {
      "idx": 417,
      "alias": [
        "widget.window"
      ],
      "alternates": [
        "Ext.Window"
      ]
    }
  },
  "packages": {
    "ext": {
      "creator": "Sencha",
      "output": "${package.dir}/build",
      "requires": [
        "sencha-core",
        "ext",
        "ext",
        "ext",
        "ext"
      ],
      "type": "framework",
      "version": "5.1.0.107"
    },
    "sencha-core": {
      "creator": "Sencha",
      "output": "${package.dir}/build",
      "requires": [],
      "slicer": {
        "js": []
      },
      "type": "code",
      "version": "5.0.0"
    }
  },
  "bootRelative": true
});

 
if (!Function.prototype.bind) {
    (function() {
        var slice = Array.prototype.slice,
            
            
            bind = function(me) {
                var args = slice.call(arguments, 1),
                    method = this;
                if (args.length) {
                    return function() {
                        var t = arguments;
                        
                        return method.apply(me, t.length ? args.concat(slice.call(t)) : args);
                    };
                }
                
                args = null;
                return function() {
                    return method.apply(me, arguments);
                };
            };
        Function.prototype.bind = bind;
        bind.$extjs = true;
    }());
}

Ext._startTime = Date.now ? Date.now() : (+new Date());
(function() {
    var global = this,
        objectPrototype = Object.prototype,
        toString = objectPrototype.toString,
        enumerables = [
            
            'valueOf',
            'toLocaleString',
            'toString',
            'constructor'
        ],
        emptyFn = function() {},
        privateFn = function() {},
        identityFn = function(o) {
            return o;
        },
        
        
        callOverrideParent = function() {
            var method = callOverrideParent.caller.caller;
            
            return method.$owner.prototype[method.$name].apply(this, arguments);
        },
        manifest = Ext.manifest || {},
        i,
        iterableRe = /\[object\s*(?:Array|Arguments|\w*Collection|\w*List|HTML\s+document\.all\s+class)\]/,
        MSDateRe = /^\\?\/Date\(([-+])?(\d+)(?:[+-]\d{4})?\)\\?\/$/;
    Ext.global = global;
    
    emptyFn.$nullFn = identityFn.$nullFn = emptyFn.$emptyFn = identityFn.$identityFn = privateFn.$nullFn = true;
    privateFn.$privacy = 'framework';
    
    
    Ext['suspendLayouts'] = Ext['resumeLayouts'] = emptyFn;
    for (i in {
        toString: 1
    }) {
        enumerables = null;
    }
    
    Ext.enumerables = enumerables;
    
    Ext.apply = function(object, config, defaults) {
        if (defaults) {
            Ext.apply(object, defaults);
        }
        if (object && config && typeof config === 'object') {
            var i, j, k;
            for (i in config) {
                object[i] = config[i];
            }
            if (enumerables) {
                for (j = enumerables.length; j--; ) {
                    k = enumerables[j];
                    if (config.hasOwnProperty(k)) {
                        object[k] = config[k];
                    }
                }
            }
        }
        return object;
    };
    Ext.buildSettings = Ext.apply({
        baseCSSPrefix: 'x-'
    }, Ext.buildSettings || {});
    Ext.apply(Ext, {
        
        idSeed: 0,
        
        idPrefix: 'ext-',
        
        isSecure: /^https/i.test(window.location.protocol),
        
        enableGarbageCollector: false,
        
        enableListenerCollection: true,
        
        name: Ext.sandboxName || 'Ext',
        
        privateFn: privateFn,
        
        emptyFn: emptyFn,
        
        identityFn: identityFn,
        
        frameStartTime: +new Date(),
        
        manifest: manifest,
        
        debugConfig: Ext.debugConfig || manifest.debug || {
            hooks: {
                '*': true
            }
        },
        
        validIdRe: /^[a-z_][a-z0-9\-_]*$/i,
        
        BLANK_IMAGE_URL: 'data:image/gif;base64,R0lGODlhAQABAID/AMDAwAAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==',
        
        makeIdSelector: function(id) {
            if (!Ext.validIdRe.test(id)) {
                Ext.Error.raise('Invalid id selector: "' + id + '"');
            }
            return '#' + id;
        },
        
        id: function(o, prefix) {
            if (o && o.id) {
                return o.id;
            }
            var id = (prefix || Ext.idPrefix) + (++Ext.idSeed);
            if (o) {
                o.id = id;
            }
            return id;
        },
        
        returnId: function(o) {
            return o.getId();
        },
        
        returnTrue: function() {
            return true;
        },
        
        emptyString: new String(),
        
        baseCSSPrefix: Ext.buildSettings.baseCSSPrefix,
        
        $eventNameMap: {},
        $vendorEventRe: /^(Moz.+|MS.+)/,
        
        
        
        canonicalEventName: function(name) {
            return Ext.$eventNameMap[name] || (Ext.$eventNameMap[name] = (Ext.$vendorEventRe.test(name) ? name : name.toLowerCase()));
        },
        
        applyIf: function(object, config) {
            var property;
            if (object) {
                for (property in config) {
                    if (object[property] === undefined) {
                        object[property] = config[property];
                    }
                }
            }
            return object;
        },
        
        now: (global.performance && global.performance.now) ? function() {
            return performance.now();
        } : (Date.now || (Date.now = function() {
            return +new Date();
        })),
        
        destroy: function() {
            var ln = arguments.length,
                i, arg;
            for (i = 0; i < ln; i++) {
                arg = arguments[i];
                if (arg) {
                    if (Ext.isArray(arg)) {
                        this.destroy.apply(this, arg);
                    } else if (Ext.isFunction(arg.destroy)) {
                        arg.destroy();
                    }
                }
            }
            return null;
        },
        
        destroyMembers: function(object) {
            for (var ref, name,
                i = 1,
                a = arguments,
                len = a.length; i < len; i++) {
                ref = object[name = a[i]];
                
                if (ref != null) {
                    object[name] = Ext.destroy(ref);
                }
            }
        },
        
        override: function(target, overrides) {
            if (target.$isClass) {
                target.override(overrides);
            } else if (typeof target == 'function') {
                Ext.apply(target.prototype, overrides);
            } else {
                var owner = target.self,
                    name, value;
                if (owner && owner.$isClass) {
                    
                    for (name in overrides) {
                        if (overrides.hasOwnProperty(name)) {
                            value = overrides[name];
                            if (typeof value === 'function') {
                                if (owner.$className) {
                                    value.name = owner.$className + '#' + name;
                                }
                                value.$name = name;
                                value.$owner = owner;
                                value.$previous = target.hasOwnProperty(name) ? target[name] : 
                                callOverrideParent;
                            }
                            
                            target[name] = value;
                        }
                    }
                } else {
                    Ext.apply(target, overrides);
                }
            }
            return target;
        },
        
        valueFrom: function(value, defaultValue, allowBlank) {
            return Ext.isEmpty(value, allowBlank) ? defaultValue : value;
        },
        
        
        
        isIterable: function(value) {
            
            if (!value || typeof value.length !== 'number' || typeof value === 'string' || Ext.isFunction(value)) {
                return false;
            }
            
            
            
            if (!value.propertyIsEnumerable) {
                return !!value.item;
            }
            
            
            if (value.hasOwnProperty('length') && !value.propertyIsEnumerable('length')) {
                return true;
            }
            
            return iterableRe.test(toString.call(value));
        },
        
        isDebugEnabled: function(className, defaultEnabled) {
            var debugConfig = Ext.debugConfig.hooks;
            if (debugConfig.hasOwnProperty(className)) {
                return debugConfig[className];
            }
            var enabled = debugConfig['*'],
                prefixLength = 0;
            if (defaultEnabled !== undefined) {
                enabled = defaultEnabled;
            }
            if (!className) {
                return enabled;
            }
            for (var prefix in debugConfig) {
                var value = debugConfig[prefix];
                
                if (className.charAt(prefix.length) === '.') {
                    if (className.substring(0, prefix.length) === prefix) {
                        if (prefixLength < prefix.length) {
                            prefixLength = prefix.length;
                            enabled = value;
                        }
                    }
                }
            }
            return enabled;
        } || emptyFn,
        
        clone: function(item) {
            if (item === null || item === undefined) {
                return item;
            }
            
            
            
            if (item.nodeType && item.cloneNode) {
                return item.cloneNode(true);
            }
            var type = toString.call(item),
                i, j, k, clone, key;
            
            if (type === '[object Date]') {
                return new Date(item.getTime());
            }
            
            if (type === '[object Array]') {
                i = item.length;
                clone = [];
                while (i--) {
                    clone[i] = Ext.clone(item[i]);
                }
            }
            
            else if (type === '[object Object]' && item.constructor === Object) {
                clone = {};
                for (key in item) {
                    clone[key] = Ext.clone(item[key]);
                }
                if (enumerables) {
                    for (j = enumerables.length; j--; ) {
                        k = enumerables[j];
                        if (item.hasOwnProperty(k)) {
                            clone[k] = item[k];
                        }
                    }
                }
            }
            return clone || item;
        },
        
        getUniqueGlobalNamespace: function() {
            var uniqueGlobalNamespace = this.uniqueGlobalNamespace,
                i;
            if (uniqueGlobalNamespace === undefined) {
                i = 0;
                do {
                    uniqueGlobalNamespace = 'ExtBox' + (++i);
                } while (global[uniqueGlobalNamespace] !== undefined);
                global[uniqueGlobalNamespace] = Ext;
                this.uniqueGlobalNamespace = uniqueGlobalNamespace;
            }
            return uniqueGlobalNamespace;
        },
        
        functionFactoryCache: {},
        cacheableFunctionFactory: function() {
            var me = this,
                args = Array.prototype.slice.call(arguments),
                cache = me.functionFactoryCache,
                idx, fn, ln;
            if (Ext.isSandboxed) {
                ln = args.length;
                if (ln > 0) {
                    ln--;
                    args[ln] = 'var Ext=window.' + Ext.name + ';' + args[ln];
                }
            }
            idx = args.join('');
            fn = cache[idx];
            if (!fn) {
                fn = Function.prototype.constructor.apply(Function.prototype, args);
                cache[idx] = fn;
            }
            return fn;
        },
        functionFactory: function() {
            var args = Array.prototype.slice.call(arguments),
                ln;
            if (Ext.isSandboxed) {
                ln = args.length;
                if (ln > 0) {
                    ln--;
                    args[ln] = 'var Ext=window.' + Ext.name + ';' + args[ln];
                }
            }
            return Function.prototype.constructor.apply(Function.prototype, args);
        },
        
        Logger: {
            log: function(message, priority) {
                if (message && global.console) {
                    if (!priority || !(priority in global.console)) {
                        priority = 'log';
                    }
                    message = '[' + priority.toUpperCase() + '] ' + message;
                    global.console[priority](message);
                }
            },
            verbose: function(message) {
                this.log(message, 'verbose');
            },
            info: function(message) {
                this.log(message, 'info');
            },
            warn: function(message) {
                this.log(message, 'warn');
            },
            error: function(message) {
                throw new Error(message);
            },
            deprecate: function(message) {
                this.log(message, 'warn');
            }
        } || {
            verbose: emptyFn,
            log: emptyFn,
            info: emptyFn,
            warn: emptyFn,
            error: function(message) {
                throw new Error(message);
            },
            deprecate: emptyFn
        },
        
        getElementById: function(id) {
            return document.getElementById(id);
        },
        
        splitAndUnescape: (function() {
            var cache = {};
            return function(origin, delimiter) {
                if (!origin) {
                    return [];
                } else if (!delimiter) {
                    return [
                        origin
                    ];
                }
                var replaceRe = cache[delimiter] || (cache[delimiter] = new RegExp('\\\\' + delimiter,'g')),
                    result = [],
                    parts, part;
                parts = origin.split(delimiter);
                while ((part = parts.shift()) !== undefined) {
                    
                    
                    while (part.charAt(part.length - 1) === '\\' && parts.length > 0) {
                        part = part + delimiter + parts.shift();
                    }
                    
                    part = part.replace(replaceRe, delimiter);
                    result.push(part);
                }
                return result;
            };
        })()
    });
    
    Ext.returnTrue.$nullFn = Ext.returnId.$nullFn = true;
}());



Ext.deprecated = function(suggestion) {
    if (!suggestion) {
        suggestion = '';
    }
    function fail() {
        Ext.Error.raise('The method "' + fail.$owner.$className + '.' + fail.$name + '" has been removed. ' + suggestion);
    }
    return fail;
    return Ext.emptyFn;
};

(function() {
    if (typeof window === 'undefined') {
        return;
    }
    
    var last = 0,
        
        notify = function() {
            var cnt = Ext.log && Ext.log.counters,
                n = cnt && (cnt.error + cnt.warn + cnt.info + cnt.log),
                msg;
            
            if (n && last !== n) {
                msg = [];
                if (cnt.error) {
                    msg.push('Errors: ' + cnt.error);
                }
                if (cnt.warn) {
                    msg.push('Warnings: ' + cnt.warn);
                }
                if (cnt.info) {
                    msg.push('Info: ' + cnt.info);
                }
                if (cnt.log) {
                    msg.push('Log: ' + cnt.log);
                }
                window.status = '*** ' + msg.join(' -- ');
                last = n;
            }
        };
    
    
    setInterval(notify, 1000);
}());







Ext.Assert = {
    
    falsey: function(b, msg) {
        if (b) {
            Ext.Error.raise(msg || ('Expected a falsey value but was ' + b));
        }
    },
    
    falseyProp: function(object, property) {
        Ext.Assert.truthy(object);
        var b = object[property];
        if (b) {
            if (object.$className) {
                property = object.$className + '#' + property;
            }
            Ext.Error.raise('Expected a falsey value for ' + property + ' but was ' + b);
        }
    },
    
    truthy: function(b, msg) {
        if (!b) {
            Ext.Error.raise(msg || ('Expected a truthy value but was ' + typeof b));
        }
    },
    
    truthyProp: function(object, property) {
        Ext.Assert.truthy(object);
        var b = object[property];
        if (!b) {
            if (object.$className) {
                property = object.$className + '#' + property;
            }
            Ext.Error.raise('Expected a truthy value for ' + property + ' but was ' + typeof b);
        }
    }
};
(function() {
    function makeAssert(name, kind) {
        var testFn = Ext[name],
            def;
        return function(value, msg) {
            if (!testFn(value)) {
                Ext.Error.raise(msg || def || (def = 'Expected value to be ' + kind));
            }
        };
    }
    function makeAssertProp(name, kind) {
        var testFn = Ext[name],
            def;
        return function(object, prop) {
            Ext.Assert.truthy(object);
            if (!testFn(object[prop])) {
                Ext.Error.raise(def || (def = 'Expected ' + (object.$className ? object.$className + '#' : '') + prop + ' to be ' + kind));
            }
        };
    }
    function makeNotAssert(name, kind) {
        var testFn = Ext[name],
            def;
        return function(value, msg) {
            if (testFn(value)) {
                Ext.Error.raise(msg || def || (def = 'Expected value to NOT be ' + kind));
            }
        };
    }
    function makeNotAssertProp(name, kind) {
        var testFn = Ext[name],
            def;
        return function(object, prop) {
            Ext.Assert.truthy(object);
            if (testFn(object[prop])) {
                Ext.Error.raise(def || (def = 'Expected ' + (object.$className ? object.$className + '#' : '') + prop + ' to NOT be ' + kind));
            }
        };
    }
    for (var name in Ext) {
        if (name.substring(0, 2) == "is" && Ext.isFunction(Ext[name])) {
            var kind = name.substring(2);
            Ext.Assert[name] = makeAssert(name, kind);
            Ext.Assert[name + 'Prop'] = makeAssertProp(name, kind);
            Ext.Assert['isNot' + kind] = makeNotAssert(name, kind);
            Ext.Assert['isNot' + kind + 'Prop'] = makeNotAssertProp(name, kind);
        }
    }
}());



Ext.apply(Ext, {
    _namedScopes: {
        'this': {
            isThis: 1
        },
        controller: {
            isController: 1
        },
        
        
        self: {
            isSelf: 1
        },
        'self.controller': {
            isSelf: 1,
            isController: 1
        }
    },
    escapeId: (function() {
        var validIdRe = /^[a-zA-Z_][a-zA-Z0-9_\-]*$/i,
            escapeRx = /([\W]{1})/g,
            leadingNumRx = /^(\d)/g,
            escapeFn = function(match, capture) {
                return "\\" + capture;
            },
            numEscapeFn = function(match, capture) {
                return '\\00' + capture.charCodeAt(0).toString(16) + ' ';
            };
        return function(id) {
            return validIdRe.test(id) ? id : 
            
            id.replace(escapeRx, escapeFn).replace(leadingNumRx, numEscapeFn);
        };
    }()),
    
    callback: function(callback, scope, args, delay, caller, defaultScope) {
        if (!callback) {
            return;
        }
        var namedScope = (scope in Ext._namedScopes);
        if (callback.charAt) {
            
            if ((!scope || namedScope) && caller) {
                scope = caller.resolveListenerScope(namedScope ? scope : defaultScope);
            }
            if (!scope || !Ext.isObject(scope)) {
                Ext.Error.raise('Named method "' + callback + '" requires a scope object');
            }
            if (!Ext.isFunction(scope[callback])) {
                Ext.Error.raise('No method named "' + callback + '" on ' + (scope.$className || 'scope object'));
            }
            callback = scope[callback];
        } else if (namedScope) {
            scope = defaultScope || caller;
        } else if (!scope) {
            scope = caller;
        }
        var ret;
        if (callback && Ext.isFunction(callback)) {
            scope = scope || Ext.global;
            if (delay) {
                Ext.defer(callback, delay, scope, args);
            } else if (Ext.elevateFunction) {
                ret = Ext.elevateFunction(callback, scope, args);
            } else if (args) {
                ret = callback.apply(scope, args);
            } else {
                ret = callback.call(scope);
            }
        }
        return ret;
    },
    
    coerce: function(from, to) {
        var fromType = Ext.typeOf(from),
            toType = Ext.typeOf(to),
            isString = typeof from === 'string';
        if (fromType !== toType) {
            switch (toType) {
                case 'string':
                    return String(from);
                case 'number':
                    return Number(from);
                case 'boolean':
                    return isString && (!from || from === 'false') ? false : Boolean(from);
                case 'null':
                    return isString && (!from || from === 'null') ? null : from;
                case 'undefined':
                    return isString && (!from || from === 'undefined') ? undefined : from;
                case 'date':
                    return isString && isNaN(from) ? Ext.Date.parse(from, Ext.Date.defaultFormat) : Date(Number(from));
            }
        }
        return from;
    },
    
    copyTo: function(dest, source, names, usePrototypeKeys) {
        if (typeof names === 'string') {
            names = names.split(Ext.propertyNameSplitRe);
        }
        for (var name,
            i = 0,
            n = names ? names.length : 0; i < n; i++) {
            name = names[i];
            if (usePrototypeKeys || source.hasOwnProperty(name)) {
                dest[name] = source[name];
            }
        }
        return dest;
    },
    propertyNameSplitRe: /[,;\s]+/,
    
    copyToIf: function(destination, source, names) {
        if (typeof names === 'string') {
            names = names.split(Ext.propertyNameSplitRe);
        }
        for (var name,
            i = 0,
            n = names ? names.length : 0; i < n; i++) {
            name = names[i];
            if (destination[name] === undefined) {
                destination[name] = source[name];
            }
        }
        return destination;
    },
    
    extend: (function() {
        
        var objectConstructor = Object.prototype.constructor,
            inlineOverrides = function(o) {
                for (var m in o) {
                    if (!o.hasOwnProperty(m)) {
                        
                        continue;
                    }
                    this[m] = o[m];
                }
            };
        return function(subclass, superclass, overrides) {
            
            if (Ext.isObject(superclass)) {
                overrides = superclass;
                superclass = subclass;
                subclass = overrides.constructor !== objectConstructor ? overrides.constructor : function() {
                    superclass.apply(this, arguments);
                };
            }
            if (!superclass) {
                Ext.Error.raise({
                    sourceClass: 'Ext',
                    sourceMethod: 'extend',
                    msg: 'Attempting to extend from a class which has not been loaded on the page.'
                });
            }
            
            var F = function() {},
                subclassProto,
                superclassProto = superclass.prototype;
            F.prototype = superclassProto;
            subclassProto = subclass.prototype = new F();
            subclassProto.constructor = subclass;
            subclass.superclass = superclassProto;
            if (superclassProto.constructor === objectConstructor) {
                superclassProto.constructor = superclass;
            }
            subclass.override = function(overrides) {
                Ext.override(subclass, overrides);
            };
            subclassProto.override = inlineOverrides;
            subclassProto.proto = subclassProto;
            subclass.override(overrides);
            subclass.extend = function(o) {
                return Ext.extend(subclass, o);
            };
            return subclass;
        };
    }()),
    
    iterate: function(object, fn, scope) {
        if (Ext.isEmpty(object)) {
            return;
        }
        if (scope === undefined) {
            scope = object;
        }
        if (Ext.isIterable(object)) {
            Ext.Array.each.call(Ext.Array, object, fn, scope);
        } else {
            Ext.Object.each.call(Ext.Object, object, fn, scope);
        }
    },
    
    urlEncode: function() {
        var args = Ext.Array.from(arguments),
            prefix = '';
        
        if (Ext.isString(args[1])) {
            prefix = args[1] + '&';
            args[1] = false;
        }
        return prefix + Ext.Object.toQueryString.apply(Ext.Object, args);
    },
    
    urlDecode: function() {
        return Ext.Object.fromQueryString.apply(Ext.Object, arguments);
    },
    
    getScrollbarSize: function(force) {
        if (!Ext.isDomReady) {
            Ext.Error.raise("getScrollbarSize called before DomReady");
        }
        var scrollbarSize = Ext._scrollbarSize;
        if (force || !scrollbarSize) {
            var db = document.body,
                div = document.createElement('div');
            div.style.width = div.style.height = '100px';
            div.style.overflow = 'scroll';
            div.style.position = 'absolute';
            db.appendChild(div);
            
            
            Ext._scrollbarSize = scrollbarSize = {
                width: div.offsetWidth - div.clientWidth,
                height: div.offsetHeight - div.clientHeight
            };
            db.removeChild(div);
        }
        return scrollbarSize;
    },
    
    typeOf: (function() {
        var nonWhitespaceRe = /\S/,
            toString = Object.prototype.toString,
            typeofTypes = {
                number: 1,
                string: 1,
                'boolean': 1,
                'undefined': 1
            },
            toStringTypes = {
                '[object Array]': 'array',
                '[object Date]': 'date',
                '[object Boolean]': 'boolean',
                '[object Number]': 'number',
                '[object RegExp]': 'regexp'
            };
        return function(value) {
            if (value === null) {
                return 'null';
            }
            var type = typeof value,
                ret, typeToString;
            if (typeofTypes[type]) {
                return type;
            }
            ret = toStringTypes[typeToString = toString.call(value)];
            if (ret) {
                return ret;
            }
            if (type === 'function') {
                return 'function';
            }
            if (type === 'object') {
                if (value.nodeType !== undefined) {
                    if (value.nodeType === 3) {
                        return nonWhitespaceRe.test(value.nodeValue) ? 'textnode' : 'whitespace';
                    } else {
                        return 'element';
                    }
                }
                return 'object';
            }
            Ext.Error.raise({
                sourceClass: 'Ext',
                sourceMethod: 'typeOf',
                msg: 'Failed to determine the type of "' + value + '".'
            });
            return typeToString;
        };
    }()),
    
    factory: function(config, classReference, instance, aliasNamespace) {
        var manager = Ext.ClassManager,
            newInstance;
        
        
        if (!config || config.isInstance) {
            if (instance && instance !== config) {
                instance.destroy();
            }
            return config;
        }
        if (aliasNamespace) {
            
            if (typeof config == 'string') {
                return manager.instantiateByAlias(aliasNamespace + '.' + config);
            }
            
            else if (Ext.isObject(config) && 'type' in config) {
                return manager.instantiateByAlias(aliasNamespace + '.' + config.type, config);
            }
        }
        if (config === true) {
            return instance || Ext.create(classReference);
        }
        if (!Ext.isObject(config)) {
            Ext.Logger.error("Invalid config, must be a valid config object");
        }
        if ('xtype' in config) {
            newInstance = manager.instantiateByAlias('widget.' + config.xtype, config);
        } else if ('xclass' in config) {
            newInstance = Ext.create(config.xclass, config);
        }
        if (newInstance) {
            if (instance) {
                instance.destroy();
            }
            return newInstance;
        }
        if (instance) {
            return instance.setConfig(config);
        }
        return Ext.create(classReference, config);
    },
    
    log: (function() {
        
        var primitiveRe = /string|number|boolean/;
        function dumpObject(object, level, maxLevel, withFunctions) {
            var member, type, value, name, prefix, suffix,
                members = [];
            if (Ext.isArray(object)) {
                prefix = '[';
                suffix = ']';
            } else if (Ext.isObject(object)) {
                prefix = '{';
                suffix = '}';
            }
            if (!maxLevel) {
                maxLevel = 3;
            }
            if (level > maxLevel) {
                return prefix + '...' + suffix;
            }
            level = level || 1;
            var spacer = (new Array(level)).join('    ');
            
            for (name in object) {
                if (object.hasOwnProperty(name)) {
                    value = object[name];
                    type = typeof value;
                    if (type == 'function') {
                        if (!withFunctions) {
                            
                            continue;
                        }
                        member = type;
                    } else if (type == 'undefined') {
                        member = type;
                    } else if (value === null || primitiveRe.test(type) || Ext.isDate(value)) {
                        member = Ext.encode(value);
                    } else if (Ext.isArray(value)) {
                        member = this.dumpObject(value, level + 1, maxLevel, withFunctions);
                    } else if (Ext.isObject(value)) {
                        member = this.dumpObject(value, level + 1, maxLevel, withFunctions);
                    } else {
                        member = type;
                    }
                    members.push(spacer + name + ': ' + member);
                }
            }
            
            if (members.length) {
                return prefix + '\n    ' + members.join(',\n    ') + '\n' + spacer + suffix;
            }
            return prefix + suffix;
        }
        function log(message) {
            var options, dump,
                con = Ext.global.console,
                level = 'log',
                indent = log.indent || 0,
                stack, out, max;
            log.indent = indent;
            if (typeof message != 'string') {
                options = message;
                message = options.msg || '';
                level = options.level || level;
                dump = options.dump;
                stack = options.stack;
                if (options.indent) {
                    ++log.indent;
                } else if (options.outdent) {
                    log.indent = indent = Math.max(indent - 1, 0);
                }
                if (dump && !(con && con.dir)) {
                    message += dumpObject(dump);
                    dump = null;
                }
            }
            if (arguments.length > 1) {
                message += Array.prototype.slice.call(arguments, 1).join('');
            }
            message = indent ? Ext.String.repeat(' ', log.indentSize * indent) + message : message;
            
            if (level != 'log') {
                message = '[' + level.charAt(0).toUpperCase() + '] ' + message;
            }
            
            
            
            if (con) {
                
                if (con[level]) {
                    con[level](message);
                } else {
                    con.log(message);
                }
                if (dump) {
                    con.dir(dump);
                }
                if (stack && con.trace) {
                    
                    if (!con.firebug || level != 'error') {
                        con.trace();
                    }
                }
            } else if (Ext.isOpera) {
                opera.postError(message);
            } else {
                out = log.out;
                max = log.max;
                if (out.length >= max) {
                    
                    
                    Ext.Array.erase(out, 0, out.length - 3 * Math.floor(max / 4));
                }
                
                out.push(message);
            }
            
            ++log.count;
            ++log.counters[level];
        }
        function logx(level, args) {
            if (typeof args[0] == 'string') {
                args.unshift({});
            }
            args[0].level = level;
            log.apply(this, args);
        }
        log.error = function() {
            logx('error', Array.prototype.slice.call(arguments));
        };
        log.info = function() {
            logx('info', Array.prototype.slice.call(arguments));
        };
        log.warn = function() {
            logx('warn', Array.prototype.slice.call(arguments));
        };
        log.count = 0;
        log.counters = {
            error: 0,
            warn: 0,
            info: 0,
            log: 0
        };
        log.indentSize = 2;
        log.out = [];
        log.max = 750;
        return log;
    }()) || (function() {
        var nullLog = function() {};
        nullLog.info = nullLog.warn = nullLog.error = Ext.emptyFn;
        return nullLog;
    }())
});



(function(manifest) {
    var packages = (manifest && manifest.packages) || {},
        compat = manifest && manifest.compatibility,
        name, pkg;
    for (name in packages) {
        pkg = packages[name];
        Ext.setVersion(name, pkg.version);
    }
    if (compat) {
        if (Ext.isString(compat)) {
            Ext.setCompatVersion('core', compat);
        } else {
            for (name in compat) {
                Ext.setCompatVersion(name, compat[name]);
            }
        }
    }
    if (!packages.ext && !packages.touch) {
        Ext.setVersion('ext', '5');
    }
})(Ext.manifest);





(function(Cache, prototype) {
    
    
    
    (Ext.util || (Ext.util = {})).Cache = Cache = function(config) {
        var me = this,
            head;
        if (config) {
            Ext.apply(me, config);
        }
        
        me.head = head = {
            id: (me.seed = 0),
            key: null,
            value: null
        };
        me.map = {};
        head.next = head.prev = head;
    };
    Cache.prototype = prototype = {
        
        maxSize: 100,
        
        count: 0,
        
        
        clear: function() {
            var me = this,
                head = me.head,
                entry = head.next;
            head.next = head.prev = head;
            if (!me.evict.$nullFn) {
                for (; entry !== head; entry = entry.next) {
                    me.evict(entry.key, entry.value);
                }
            }
            me.count = 0;
        },
        
        each: function(fn, scope) {
            scope = scope || this;
            for (var head = this.head,
                ent = head.next; ent !== head; ent = ent.next) {
                if (fn.call(scope, ent.key, ent.value)) {
                    break;
                }
            }
        },
        
        get: function(key) {
            var me = this,
                head = me.head,
                map = me.map,
                entry = map[key];
            if (entry) {
                if (entry.prev !== head) {
                    
                    
                    me.unlinkEntry(entry);
                    me.linkEntry(entry);
                }
            } else {
                map[key] = entry = {
                    id: ++me.seed,
                    key: key,
                    value: me.miss.apply(me, arguments)
                };
                me.linkEntry(entry);
                ++me.count;
                while (me.count > me.maxSize) {
                    me.unlinkEntry(head.prev, true);
                    --me.count;
                }
            }
            return entry.value;
        },
        
        
        
        evict: Ext.emptyFn,
        
        linkEntry: function(entry) {
            var head = this.head,
                first = head.next;
            entry.next = first;
            entry.prev = head;
            head.next = entry;
            first.prev = entry;
        },
        
        unlinkEntry: function(entry, evicted) {
            var next = entry.next,
                prev = entry.prev;
            prev.next = next;
            next.prev = prev;
            if (evicted) {
                this.evict(entry.key, entry.value);
            }
        }
    };
    prototype.destroy = prototype.clear;
}());


(function() {
    
    
    
    
    
    var ExtClass,
        Base = Ext.Base,
        baseStaticMembers = Base.$staticMembers,
        ruleKeySortFn = function(a, b) {
            
            return (a.length - b.length) || ((a < b) ? -1 : ((a > b) ? 1 : 0));
        };
    
    function makeCtor(className) {
        function constructor() {
            
            
            return this.constructor.apply(this, arguments) || null;
        }
        if (className) {
            constructor.name = className;
        }
        return constructor;
    }
    
    Ext.Class = ExtClass = function(Class, data, onCreated) {
        if (typeof Class != 'function') {
            onCreated = data;
            data = Class;
            Class = null;
        }
        if (!data) {
            data = {};
        }
        Class = ExtClass.create(Class, data);
        ExtClass.process(Class, data, onCreated);
        return Class;
    };
    Ext.apply(ExtClass, {
        makeCtor: makeCtor,
        
        onBeforeCreated: function(Class, data, hooks) {
            Ext.classSystemMonitor && Ext.classSystemMonitor(Class, '>> Ext.Class#onBeforeCreated', arguments);
            Class.addMembers(data);
            hooks.onCreated.call(Class, Class);
            Ext.classSystemMonitor && Ext.classSystemMonitor(Class, '<< Ext.Class#onBeforeCreated', arguments);
        },
        
        create: function(Class, data) {
            var i = baseStaticMembers.length,
                name;
            if (!Class) {
                Class = makeCtor(data.$className);
            }
            while (i--) {
                name = baseStaticMembers[i];
                Class[name] = Base[name];
            }
            return Class;
        },
        
        process: function(Class, data, onCreated) {
            var preprocessorStack = data.preprocessors || ExtClass.defaultPreprocessors,
                registeredPreprocessors = this.preprocessors,
                hooks = {
                    onBeforeCreated: this.onBeforeCreated
                },
                preprocessors = [],
                preprocessor, preprocessorsProperties, i, ln, j, subLn, preprocessorProperty;
            delete data.preprocessors;
            Class._classHooks = hooks;
            for (i = 0 , ln = preprocessorStack.length; i < ln; i++) {
                preprocessor = preprocessorStack[i];
                if (typeof preprocessor == 'string') {
                    preprocessor = registeredPreprocessors[preprocessor];
                    preprocessorsProperties = preprocessor.properties;
                    if (preprocessorsProperties === true) {
                        preprocessors.push(preprocessor.fn);
                    } else if (preprocessorsProperties) {
                        for (j = 0 , subLn = preprocessorsProperties.length; j < subLn; j++) {
                            preprocessorProperty = preprocessorsProperties[j];
                            if (data.hasOwnProperty(preprocessorProperty)) {
                                preprocessors.push(preprocessor.fn);
                                break;
                            }
                        }
                    }
                } else {
                    preprocessors.push(preprocessor);
                }
            }
            hooks.onCreated = onCreated ? onCreated : Ext.emptyFn;
            hooks.preprocessors = preprocessors;
            this.doProcess(Class, data, hooks);
        },
        doProcess: function(Class, data, hooks) {
            var me = this,
                preprocessors = hooks.preprocessors,
                preprocessor = preprocessors.shift(),
                doProcess = me.doProcess;
            for (; preprocessor; preprocessor = preprocessors.shift()) {
                
                if (preprocessor.call(me, Class, data, hooks, doProcess) === false) {
                    return;
                }
            }
            hooks.onBeforeCreated.apply(me, arguments);
        },
        
        preprocessors: {},
        
        registerPreprocessor: function(name, fn, properties, position, relativeTo) {
            if (!position) {
                position = 'last';
            }
            if (!properties) {
                properties = [
                    name
                ];
            }
            this.preprocessors[name] = {
                name: name,
                properties: properties || false,
                fn: fn
            };
            this.setDefaultPreprocessorPosition(name, position, relativeTo);
            return this;
        },
        
        getPreprocessor: function(name) {
            return this.preprocessors[name];
        },
        
        getPreprocessors: function() {
            return this.preprocessors;
        },
        
        defaultPreprocessors: [],
        
        getDefaultPreprocessors: function() {
            return this.defaultPreprocessors;
        },
        
        setDefaultPreprocessors: function(preprocessors) {
            this.defaultPreprocessors = Ext.Array.from(preprocessors);
            return this;
        },
        
        setDefaultPreprocessorPosition: function(name, offset, relativeName) {
            var defaultPreprocessors = this.defaultPreprocessors,
                index;
            if (typeof offset == 'string') {
                if (offset === 'first') {
                    defaultPreprocessors.unshift(name);
                    return this;
                } else if (offset === 'last') {
                    defaultPreprocessors.push(name);
                    return this;
                }
                offset = (offset === 'after') ? 1 : -1;
            }
            index = Ext.Array.indexOf(defaultPreprocessors, relativeName);
            if (index !== -1) {
                Ext.Array.splice(defaultPreprocessors, Math.max(0, index + offset), 0, name);
            }
            return this;
        }
    });
    
    ExtClass.registerPreprocessor('extend', function(Class, data, hooks) {
        Ext.classSystemMonitor && Ext.classSystemMonitor(Class, 'Ext.Class#extendPreProcessor', arguments);
        var Base = Ext.Base,
            basePrototype = Base.prototype,
            extend = data.extend,
            Parent, parentPrototype, i;
        delete data.extend;
        if (extend && extend !== Object) {
            Parent = extend;
        } else {
            Parent = Base;
        }
        parentPrototype = Parent.prototype;
        if (!Parent.$isClass) {
            for (i in basePrototype) {
                if (!parentPrototype[i]) {
                    parentPrototype[i] = basePrototype[i];
                }
            }
        }
        Class.extend(Parent);
        Class.triggerExtended.apply(Class, arguments);
        if (data.onClassExtended) {
            Class.onExtended(data.onClassExtended, Class);
            delete data.onClassExtended;
        }
    }, true);
    
    ExtClass.registerPreprocessor('privates', function(Class, data) {
        Ext.classSystemMonitor && Ext.classSystemMonitor(Class, 'Ext.Class#privatePreprocessor', arguments);
        var privates = data.privates,
            statics = privates.statics,
            privacy = privates.privacy || true;
        delete data.privates;
        delete privates.statics;
        
        
        
        Class.addMembers(privates, false, privacy);
        if (statics) {
            Class.addMembers(statics, true, privacy);
        }
    });
    
    ExtClass.registerPreprocessor('statics', function(Class, data) {
        Ext.classSystemMonitor && Ext.classSystemMonitor(Class, 'Ext.Class#staticsPreprocessor', arguments);
        Class.addStatics(data.statics);
        delete data.statics;
    });
    
    ExtClass.registerPreprocessor('inheritableStatics', function(Class, data) {
        Ext.classSystemMonitor && Ext.classSystemMonitor(Class, 'Ext.Class#inheritableStaticsPreprocessor', arguments);
        Class.addInheritableStatics(data.inheritableStatics);
        delete data.inheritableStatics;
    });
    Ext.createRuleFn = function(code) {
        return new Function('$c','with($c) { return (' + code + '); }');
    };
    Ext.expressionCache = new Ext.util.Cache({
        miss: Ext.createRuleFn
    });
    Ext.ruleKeySortFn = ruleKeySortFn;
    Ext.getPlatformConfigKeys = function(platformConfig) {
        var ret = [],
            platform, rule;
        for (platform in platformConfig) {
            rule = Ext.expressionCache.get(platform);
            if (rule(Ext.platformTags)) {
                ret.push(platform);
            }
        }
        ret.sort(ruleKeySortFn);
        return ret;
    };
    
    ExtClass.registerPreprocessor('platformConfig', function(Class, data, hooks) {
        var platformConfigs = data.platformConfig,
            config = data.config,
            added, classConfigs, configs, configurator, hoisted, keys, name, value, platform, theme, platformConfig, i, ln, j, ln2, themeName;
        delete data.platformConfig;
        if (platformConfigs instanceof Array) {
            
            config = config || {};
            themeName = (Ext.theme || (Ext.theme = {
                name: 'Default'
            })).name;
            for (i = 0 , ln = platformConfigs.length; i < ln; i++) {
                platformConfig = platformConfigs[i];
                platform = platformConfig.platform;
                delete platformConfig.platform;
                theme = [].concat(platformConfig.theme);
                ln2 = theme.length;
                delete platformConfig.theme;
                if (platform && Ext.filterPlatform(platform)) {
                    Ext.merge(config, platformConfig);
                }
                if (ln2) {
                    for (j = 0; j < ln2; j++) {
                        if (themeName === theme[j]) {
                            Ext.merge(config, platformConfig);
                        }
                    }
                }
            }
        } else {
            configurator = Class.getConfigurator();
            classConfigs = configurator.configs;
            
            keys = Ext.getPlatformConfigKeys(platformConfigs);
            for (i = 0 , ln = keys.length; i < ln; ++i) {
                configs = platformConfigs[keys[i]];
                hoisted = added = null;
                for (name in configs) {
                    value = configs[name];
                    
                    if (config && name in config) {
                        
                        (added || (added = {}))[name] = value;
                        (hoisted || (hoisted = {}))[name] = config[name];
                        delete config[name];
                    } else if (name in classConfigs) {
                        
                        (added || (added = {}))[name] = value;
                    } else {
                        
                        data[name] = value;
                    }
                }
                if (hoisted) {
                    configurator.add(hoisted);
                }
                if (added) {
                    configurator.add(added);
                }
            }
        }
    });
    
    ExtClass.registerPreprocessor('config', function(Class, data) {
        
        if (data.hasOwnProperty('$configPrefixed')) {
            Class.prototype.$configPrefixed = data.$configPrefixed;
        }
        Class.addConfig(data.config);
        
        
        
        delete data.config;
    });
    
    ExtClass.registerPreprocessor('cachedConfig', function(Class, data) {
        
        if (data.hasOwnProperty('$configPrefixed')) {
            Class.prototype.$configPrefixed = data.$configPrefixed;
        }
        Class.addCachedConfig(data.cachedConfig);
        
        delete data.cachedConfig;
    });
    
    ExtClass.registerPreprocessor('mixins', function(Class, data, hooks) {
        Ext.classSystemMonitor && Ext.classSystemMonitor(Class, 'Ext.Class#mixinsPreprocessor', arguments);
        var mixins = data.mixins,
            onCreated = hooks.onCreated;
        delete data.mixins;
        hooks.onCreated = function() {
            Ext.classSystemMonitor && Ext.classSystemMonitor(Class, 'Ext.Class#mixinsPreprocessor#beforeCreated', arguments);
            
            
            hooks.onCreated = onCreated;
            Class.mixin(mixins);
            
            
            return hooks.onCreated.apply(this, arguments);
        };
    });
    
    Ext.extend = function(Class, Parent, members) {
        Ext.classSystemMonitor && Ext.classSystemMonitor(Class, 'Ext.Class#extend-backwards-compatible', arguments);
        if (arguments.length === 2 && Ext.isObject(Parent)) {
            members = Parent;
            Parent = Class;
            Class = null;
        }
        var cls;
        if (!Parent) {
            throw new Error("[Ext.extend] Attempting to extend from a class which has not been loaded on the page.");
        }
        members.extend = Parent;
        members.preprocessors = [
            'extend',
            'statics',
            'inheritableStatics',
            'mixins',
            'platformConfig',
            'config'
        ];
        if (Class) {
            cls = new ExtClass(Class,members);
            
            cls.prototype.constructor = Class;
        } else {
            cls = new ExtClass(members);
        }
        cls.prototype.override = function(o) {
            for (var m in o) {
                if (o.hasOwnProperty(m)) {
                    this[m] = o[m];
                }
            }
        };
        return cls;
    };
}());



Ext.Inventory = function() {
    
    
    
    var me = this;
    me.names = [];
    me.paths = {};
    me.alternateToName = {};
    me.aliasToName = {};
    me.nameToAliases = {};
    me.nameToAlternates = {};
};
Ext.Inventory.prototype = {
    _array1: [
        0
    ],
    prefixes: null,
    dotRe: /\./g,
    wildcardRe: /\*/g,
    addAlias: function(className, alias) {
        return this.addMapping(className, alias, this.aliasToName, this.nameToAliases);
    },
    addAlternate: function(className, alternate) {
        return this.addMapping(className, alternate, this.alternateToName, this.nameToAlternates);
    },
    addMapping: function(className, alternate, toName, nameTo) {
        var name = className.$className || className,
            mappings = name,
            array = this._array1,
            a, aliases, cls, i, length, nameMapping;
        if (Ext.isString(name)) {
            mappings = {};
            mappings[name] = alternate;
        }
        for (cls in mappings) {
            aliases = mappings[cls];
            if (Ext.isString(aliases)) {
                array[0] = aliases;
                aliases = array;
            }
            length = aliases.length;
            nameMapping = nameTo[cls] || (nameTo[cls] = []);
            for (i = 0; i < length; ++i) {
                if (!(a = aliases[i])) {
                    
                    continue;
                }
                if (toName[a] !== cls) {
                    if (toName[a]) {
                        Ext.log.warn("Overriding existing mapping: '" + a + "' From '" + toName[a] + "' to '" + cls + "'. Is this intentional?");
                    }
                    toName[a] = cls;
                    nameMapping.push(a);
                }
            }
        }
    },
    
    getAliasesByName: function(name) {
        return this.nameToAliases[name] || null;
    },
    getAlternatesByName: function(name) {
        return this.nameToAlternates[name] || null;
    },
    
    getNameByAlias: function(alias) {
        return this.aliasToName[alias] || '';
    },
    
    getNameByAlternate: function(alternate) {
        return this.alternateToName[alternate] || '';
    },
    
    getNamesByExpression: function(expression, exclude, accumulate) {
        var me = this,
            aliasToName = me.aliasToName,
            alternateToName = me.alternateToName,
            nameToAliases = me.nameToAliases,
            nameToAlternates = me.nameToAlternates,
            map = accumulate ? exclude : {},
            names = [],
            expressions = Ext.isString(expression) ? [
                expression
            ] : expression,
            length = expressions.length,
            wildcardRe = me.wildcardRe,
            expr, i, list, match, n, name, regex;
        for (i = 0; i < length; ++i) {
            if ((expr = expressions[i]).indexOf('*') < 0) {
                
                if (!(name = aliasToName[expr])) {
                    if (!(name = alternateToName[expr])) {
                        name = expr;
                    }
                }
                if (!(name in map) && !(exclude && (name in exclude))) {
                    map[name] = 1;
                    names.push(name);
                }
            } else {
                regex = new RegExp('^' + expr.replace(wildcardRe, '(.*?)') + '$');
                for (name in nameToAliases) {
                    if (!(name in map) && !(exclude && (name in exclude))) {
                        if (!(match = regex.test(name))) {
                            n = (list = nameToAliases[name]).length;
                            while (!match && n-- > 0) {
                                match = regex.test(list[n]);
                            }
                            list = nameToAlternates[name];
                            if (list && !match) {
                                n = list.length;
                                while (!match && n-- > 0) {
                                    match = regex.test(list[n]);
                                }
                            }
                        }
                        if (match) {
                            map[name] = 1;
                            names.push(name);
                        }
                    }
                }
            }
        }
        return names;
    },
    getPath: function(className) {
        var me = this,
            paths = me.paths,
            ret = '',
            prefix;
        if (className in paths) {
            ret = paths[className];
        } else {
            prefix = me.getPrefix(className);
            if (prefix) {
                className = className.substring(prefix.length + 1);
                ret = paths[prefix];
                if (ret) {
                    ret += '/';
                }
            }
            ret += className.replace(me.dotRe, '/') + '.js';
        }
        return ret;
    },
    getPrefix: function(className) {
        if (className in this.paths) {
            return className;
        }
        var prefixes = this.getPrefixes(),
            i = prefixes.length,
            length, prefix;
        
        while (i-- > 0) {
            length = (prefix = prefixes[i]).length;
            if (length < className.length && className.charAt(length) === '.' && prefix === className.substring(0, length)) {
                return prefix;
            }
        }
        return '';
    },
    getPrefixes: function() {
        var me = this,
            prefixes = me.prefixes;
        if (!prefixes) {
            me.prefixes = prefixes = me.names.slice(0);
            prefixes.sort(me._compareNames);
        }
        return prefixes;
    },
    removeName: function(name) {
        var me = this,
            aliasToName = me.aliasToName,
            alternateToName = me.alternateToName,
            nameToAliases = me.nameToAliases,
            nameToAlternates = me.nameToAlternates,
            aliases = nameToAliases[name],
            alternates = nameToAlternates[name],
            i, a;
        delete nameToAliases[name];
        delete nameToAlternates[name];
        if (aliases) {
            for (i = aliases.length; i--; ) {
                
                
                
                if (name === (a = aliases[i])) {
                    delete aliasToName[a];
                }
            }
        }
        if (alternates) {
            for (i = alternates.length; i--; ) {
                
                if (name === (a = alternates[i])) {
                    delete alternateToName[a];
                }
            }
        }
    },
    resolveName: function(name) {
        var me = this,
            trueName;
        
        
        if (!(name in me.nameToAliases)) {
            
            if (!(trueName = me.aliasToName[name])) {
                
                
                trueName = me.alternateToName[name];
            }
        }
        return trueName || name;
    },
    
    select: function(receiver, scope) {
        var me = this,
            excludes = {},
            ret = {
                excludes: excludes,
                exclude: function() {
                    me.getNamesByExpression(arguments, excludes, true);
                    return this;
                }
            },
            name;
        for (name in receiver) {
            ret[name] = me.selectMethod(excludes, receiver[name], scope || receiver);
        }
        return ret;
    },
    selectMethod: function(excludes, fn, scope) {
        var me = this;
        return function(include) {
            var args = Ext.Array.slice(arguments, 1);
            args.unshift(me.getNamesByExpression(include, excludes));
            return fn.apply(scope, args);
        };
    },
    
    setPath: Ext.Function.flexSetter(function(name, path) {
        var me = this;
        me.paths[name] = path;
        me.names.push(name);
        me.prefixes = null;
        return me;
    }),
    _compareNames: function(lhs, rhs) {
        var cmp = lhs.length - rhs.length;
        if (!cmp) {
            cmp = (lhs < rhs) ? -1 : 1;
        }
        return cmp;
    }
};





Ext.feature = {
    has: function(name) {
        return !!this.has[name];
    },
    testElements: {},
    getTestElement: function(tag, createNew) {
        if (tag === undefined) {
            tag = 'div';
        } else if (typeof tag !== 'string') {
            return tag;
        }
        if (createNew) {
            return document.createElement(tag);
        }
        if (!this.testElements[tag]) {
            this.testElements[tag] = document.createElement(tag);
        }
        return this.testElements[tag];
    },
    isStyleSupported: function(name, tag) {
        var elementStyle = this.getTestElement(tag).style,
            cName = Ext.String.capitalize(name);
        if (typeof elementStyle[name] !== 'undefined' || typeof elementStyle[Ext.browser.getStylePrefix(name) + cName] !== 'undefined') {
            return true;
        }
        return false;
    },
    isStyleSupportedWithoutPrefix: function(name, tag) {
        var elementStyle = this.getTestElement(tag).style;
        if (typeof elementStyle[name] !== 'undefined') {
            return true;
        }
        return false;
    },
    isEventSupported: function(name, tag) {
        if (tag === undefined) {
            tag = window;
        }
        var element = this.getTestElement(tag),
            eventName = 'on' + name.toLowerCase(),
            isSupported = (eventName in element);
        if (!isSupported) {
            if (element.setAttribute && element.removeAttribute) {
                element.setAttribute(eventName, '');
                isSupported = typeof element[eventName] === 'function';
                if (typeof element[eventName] !== 'undefined') {
                    element[eventName] = undefined;
                }
                element.removeAttribute(eventName);
            }
        }
        return isSupported;
    },
    
    
    
    getStyle: function(element, styleName) {
        var view = element.ownerDocument.defaultView,
            style = (view ? view.getComputedStyle(element, null) : element.currentStyle) || element.style;
        return style[styleName];
    },
    getSupportedPropertyName: function(object, name) {
        var vendorName = Ext.browser.getVendorProperyName(name);
        if (vendorName in object) {
            return vendorName;
        } else if (name in object) {
            return name;
        }
        return null;
    },
    
    detect: function(isReady) {
        var me = this,
            doc = document,
            toRun = me.toRun || me.tests,
            n = toRun.length,
            div = doc.createElement('div'),
            notRun = [],
            supports = Ext.supports,
            has = me.has,
            name, test, vector, value;
        
        if (!Ext.theme) {
            Ext.theme = {
                name: 'Default'
            };
        }
        Ext.theme.is = {};
        Ext.theme.is[Ext.theme.name] = true;
        
        
        div.innerHTML = '<div style="height:30px;width:50px;">' + '<div style="height:20px;width:20px;"></div>' + '</div>' + '<div style="width: 200px; height: 200px; position: relative; padding: 5px;">' + '<div style="position: absolute; top: 0; left: 0; width: 100%; height: 100%;"></div>' + '</div>' + '<div style="position: absolute; left: 10%; top: 10%;"></div>' + '<div style="float:left; background-color:transparent;"></div>';
        if (isReady) {
            doc.body.appendChild(div);
        }
        vector = me.preDetected[Ext.browser.identity] || [];
        while (n--) {
            test = toRun[n];
            value = vector[n];
            name = test.name;
            if (value === undefined) {
                if (!isReady && test.ready) {
                    
                    notRun.push(test);
                    
                    continue;
                }
                value = test.fn.call(me, doc, div);
            }
            
            supports[name] = has[name] = value;
        }
        if (isReady) {
            doc.body.removeChild(div);
        }
        me.toRun = notRun;
    },
    
    report: function() {
        var values = [],
            len = this.tests.length,
            i;
        for (i = 0; i < len; ++i) {
            values.push(this.has[this.tests[i].name] ? 1 : 0);
        }
        Ext.log(Ext.browser.identity + ': [' + values.join(',') + ']');
    },
    
    preDetected: {},
    
    
    tests: [
        {
            
            name: 'CSSPointerEvents',
            fn: function(doc) {
                return 'pointerEvents' in doc.documentElement.style;
            }
        },
        {
            
            name: 'CSS3BoxShadow',
            fn: function(doc) {
                return 'boxShadow' in doc.documentElement.style || 'WebkitBoxShadow' in doc.documentElement.style || 'MozBoxShadow' in doc.documentElement.style;
            }
        },
        {
            
            name: 'ClassList',
            fn: function(doc) {
                return !!doc.documentElement.classList;
            }
        },
        {
            
            name: 'Canvas',
            fn: function() {
                var element = this.getTestElement('canvas');
                return !!(element && element.getContext && element.getContext('2d'));
            }
        },
        {
            
            name: 'Svg',
            fn: function(doc) {
                return !!(doc.createElementNS && !!doc.createElementNS("http:/" + "/www.w3.org/2000/svg", "svg").createSVGRect);
            }
        },
        {
            
            name: 'Vml',
            fn: function() {
                var element = this.getTestElement(),
                    ret = false;
                element.innerHTML = "<!--[if vml]><br><![endif]-->";
                ret = (element.childNodes.length === 1);
                element.innerHTML = "";
                return ret;
            }
        },
        {
            
            name: 'touchScroll',
            fn: function() {
                var supports = Ext.supports,
                    touchScroll = 0;
                if (navigator.msMaxTouchPoints || (Ext.isWebKit && supports.TouchEvents && Ext.os.is.Desktop)) {
                    touchScroll = 1;
                } else if (supports.Touch) {
                    touchScroll = 2;
                }
                return touchScroll;
            }
        },
        {
            
            name: 'Touch',
            fn: function() {
                
                var maxTouchPoints = navigator.msMaxTouchPoints || navigator.maxTouchPoints;
                
                
                
                
                
                
                
                return (Ext.supports.TouchEvents && maxTouchPoints !== 1) || maxTouchPoints > 1;
            }
        },
        {
            
            name: 'TouchEvents',
            fn: function() {
                return this.isEventSupported('touchend');
            }
        },
        {
            name: 'PointerEvents',
            fn: function() {
                return navigator.pointerEnabled;
            }
        },
        {
            name: 'MSPointerEvents',
            fn: function() {
                return navigator.msPointerEnabled;
            }
        },
        {
            
            name: 'Orientation',
            fn: function() {
                return ('orientation' in window) && this.isEventSupported('orientationchange');
            }
        },
        {
            
            name: 'OrientationChange',
            fn: function() {
                return this.isEventSupported('orientationchange');
            }
        },
        {
            
            name: 'DeviceMotion',
            fn: function() {
                return this.isEventSupported('devicemotion');
            }
        },
        {
            
            
            names: [
                'Geolocation',
                'GeoLocation'
            ],
            fn: function() {
                return 'geolocation' in window.navigator;
            }
        },
        {
            name: 'SqlDatabase',
            fn: function() {
                return 'openDatabase' in window;
            }
        },
        {
            name: 'WebSockets',
            fn: function() {
                return 'WebSocket' in window;
            }
        },
        {
            
            name: 'Range',
            fn: function() {
                return !!document.createRange;
            }
        },
        {
            
            name: 'CreateContextualFragment',
            fn: function() {
                var range = !!document.createRange ? document.createRange() : false;
                return range && !!range.createContextualFragment;
            }
        },
        {
            
            name: 'History',
            fn: function() {
                return ('history' in window && 'pushState' in window.history);
            }
        },
        {
            name: 'CssTransforms',
            fn: function() {
                return this.isStyleSupported('transform');
            }
        },
        {
            name: 'CssTransformNoPrefix',
            fn: function() {
                return this.isStyleSupportedWithoutPrefix('transform');
            }
        },
        {
            
            name: 'Css3dTransforms',
            fn: function() {
                
                return this.has('CssTransforms') && this.isStyleSupported('perspective') && !Ext.browser.is.AndroidStock2;
            }
        },
        
        
        {
            name: 'CssAnimations',
            fn: function() {
                return this.isStyleSupported('animationName');
            }
        },
        {
            
            names: [
                'CssTransitions',
                'Transitions'
            ],
            fn: function() {
                return this.isStyleSupported('transitionProperty');
            }
        },
        {
            
            
            names: [
                'Audio',
                'AudioTag'
            ],
            fn: function() {
                return !!this.getTestElement('audio').canPlayType;
            }
        },
        {
            
            name: 'Video',
            fn: function() {
                return !!this.getTestElement('video').canPlayType;
            }
        },
        {
            
            name: 'LocalStorage',
            fn: function() {
                try {
                    
                    
                    if ('localStorage' in window && window['localStorage'] !== null) {
                        
                        localStorage.setItem('sencha-localstorage-test', 'test success');
                        
                        localStorage.removeItem('sencha-localstorage-test');
                        return true;
                    }
                } catch (e) {}
                
                return false;
            }
        },
        {
            
            name: 'XHR2',
            fn: function() {
                return window.ProgressEvent && window.FormData && window.XMLHttpRequest && ('withCredentials' in new XMLHttpRequest());
            }
        },
        {
            
            name: 'XHRUploadProgress',
            fn: function() {
                if (window.XMLHttpRequest && !Ext.browser.is.AndroidStock) {
                    var xhr = new XMLHttpRequest();
                    return xhr && ('upload' in xhr) && ('onprogress' in xhr.upload);
                }
                return false;
            }
        },
        {
            
            name: 'NumericInputPlaceHolder',
            fn: function() {
                return !(Ext.browser.is.AndroidStock4 && Ext.os.version.getMinor() < 2);
            }
        },
        {
            name: 'ProperHBoxStretching',
            ready: true,
            fn: function() {
                
                var bodyElement = document.createElement('div'),
                    innerElement = bodyElement.appendChild(document.createElement('div')),
                    contentElement = innerElement.appendChild(document.createElement('div')),
                    innerWidth;
                bodyElement.setAttribute('style', 'width: 100px; height: 100px; position: relative;');
                innerElement.setAttribute('style', 'position: absolute; display: -ms-flexbox; display: -webkit-flex; display: -moz-flexbox; display: flex; -ms-flex-direction: row; -webkit-flex-direction: row; -moz-flex-direction: row; flex-direction: row; min-width: 100%;');
                contentElement.setAttribute('style', 'width: 200px; height: 50px;');
                document.body.appendChild(bodyElement);
                innerWidth = innerElement.offsetWidth;
                document.body.removeChild(bodyElement);
                return (innerWidth > 100);
            }
        },
        
        {
            name: 'matchesSelector',
            fn: function() {
                var el = document.documentElement,
                    w3 = 'matches',
                    wk = 'webkitMatchesSelector',
                    ms = 'msMatchesSelector',
                    mz = 'mozMatchesSelector';
                return el[w3] ? w3 : el[wk] ? wk : el[ms] ? ms : el[mz] ? mz : null;
            }
        },
        
        {
            name: 'RightMargin',
            ready: true,
            fn: function(doc, div) {
                var view = doc.defaultView;
                return !(view && view.getComputedStyle(div.firstChild.firstChild, null).marginRight != '0px');
            }
        },
        
        {
            name: 'DisplayChangeInputSelectionBug',
            fn: function() {
                var webKitVersion = Ext.webKitVersion;
                
                return 0 < webKitVersion && webKitVersion < 533;
            }
        },
        
        {
            name: 'DisplayChangeTextAreaSelectionBug',
            fn: function() {
                var webKitVersion = Ext.webKitVersion;
                
                return 0 < webKitVersion && webKitVersion < 534.24;
            }
        },
        
        {
            name: 'TransparentColor',
            ready: true,
            fn: function(doc, div, view) {
                view = doc.defaultView;
                return !(view && view.getComputedStyle(div.lastChild, null).backgroundColor != 'transparent');
            }
        },
        
        {
            name: 'ComputedStyle',
            ready: true,
            fn: function(doc, div, view) {
                view = doc.defaultView;
                return view && view.getComputedStyle;
            }
        },
        
        {
            name: 'Float',
            fn: function(doc) {
                return 'cssFloat' in doc.documentElement.style;
            }
        },
        
        {
            name: 'CSS3BorderRadius',
            ready: true,
            fn: function(doc) {
                var domPrefixes = [
                        'borderRadius',
                        'BorderRadius',
                        'MozBorderRadius',
                        'WebkitBorderRadius',
                        'OBorderRadius',
                        'KhtmlBorderRadius'
                    ],
                    pass = false,
                    i;
                for (i = 0; i < domPrefixes.length; i++) {
                    if (doc.documentElement.style[domPrefixes[i]] !== undefined) {
                        pass = true;
                    }
                }
                return pass && !Ext.isIE9;
            }
        },
        
        {
            name: 'CSS3LinearGradient',
            fn: function(doc, div) {
                var property = 'background-image:',
                    webkit = '-webkit-gradient(linear, left top, right bottom, from(black), to(white))',
                    w3c = 'linear-gradient(left top, black, white)',
                    moz = '-moz-' + w3c,
                    ms = '-ms-' + w3c,
                    opera = '-o-' + w3c,
                    options = [
                        property + webkit,
                        property + w3c,
                        property + moz,
                        property + ms,
                        property + opera
                    ];
                div.style.cssText = options.join(';');
                return (("" + div.style.backgroundImage).indexOf('gradient') !== -1) && !Ext.isIE9;
            }
        },
        
        {
            name: 'MouseEnterLeave',
            fn: function(doc) {
                return ('onmouseenter' in doc.documentElement && 'onmouseleave' in doc.documentElement);
            }
        },
        
        {
            name: 'MouseWheel',
            fn: function(doc) {
                return ('onmousewheel' in doc.documentElement);
            }
        },
        
        {
            name: 'Opacity',
            fn: function(doc, div) {
                
                if (Ext.isIE8) {
                    return false;
                }
                div.firstChild.style.cssText = 'opacity:0.73';
                return div.firstChild.style.opacity == '0.73';
            }
        },
        
        {
            name: 'Placeholder',
            fn: function(doc) {
                return 'placeholder' in doc.createElement('input');
            }
        },
        
        {
            name: 'Direct2DBug',
            fn: function(doc) {
                return Ext.isString(doc.documentElement.style.msTransformOrigin) && Ext.isIE9m;
            }
        },
        
        {
            name: 'BoundingClientRect',
            fn: function(doc) {
                return 'getBoundingClientRect' in doc.documentElement;
            }
        },
        
        {
            name: 'RotatedBoundingClientRect',
            ready: true,
            fn: function(doc) {
                var body = doc.body,
                    supports = false,
                    el = this.getTestElement(),
                    style = el.style;
                if (el.getBoundingClientRect) {
                    style.WebkitTransform = style.MozTransform = style.msTransform = style.OTransform = style.transform = 'rotate(90deg)';
                    style.width = '100px';
                    style.height = '30px';
                    body.appendChild(el);
                    supports = el.getBoundingClientRect().height !== 100;
                    body.removeChild(el);
                }
                return supports;
            }
        },
        
        {
            name: 'ChildContentClearedWhenSettingInnerHTML',
            ready: true,
            fn: function() {
                var el = this.getTestElement(),
                    child;
                el.innerHTML = '<div>a</div>';
                child = el.firstChild;
                el.innerHTML = '<div>b</div>';
                return child.innerHTML !== 'a';
            }
        },
        {
            name: 'IncludePaddingInWidthCalculation',
            ready: true,
            fn: function(doc, div) {
                return div.childNodes[1].firstChild.offsetWidth == 210;
            }
        },
        {
            name: 'IncludePaddingInHeightCalculation',
            ready: true,
            fn: function(doc, div) {
                return div.childNodes[1].firstChild.offsetHeight == 210;
            }
        },
        
        {
            name: 'TextAreaMaxLength',
            fn: function(doc) {
                return ('maxlength' in doc.createElement('textarea'));
            }
        },
        
        
        {
            name: 'GetPositionPercentage',
            ready: true,
            fn: function(doc, div) {
                return Ext.feature.getStyle(div.childNodes[2], 'left') == '10%';
            }
        },
        
        {
            name: 'PercentageHeightOverflowBug',
            ready: true,
            fn: function(doc) {
                var hasBug = false,
                    style, el;
                if (Ext.getScrollbarSize().height) {
                    
                    el = this.getTestElement();
                    style = el.style;
                    style.height = '50px';
                    style.width = '50px';
                    style.overflow = 'auto';
                    style.position = 'absolute';
                    el.innerHTML = [
                        '<div style="display:table;height:100%;">',
                        
                        
                        
                        '<div style="width:51px;"></div>',
                        '</div>'
                    ].join('');
                    doc.body.appendChild(el);
                    if (el.firstChild.offsetHeight === 50) {
                        hasBug = true;
                    }
                    doc.body.removeChild(el);
                }
                return hasBug;
            }
        },
        
        {
            name: 'xOriginBug',
            ready: true,
            fn: function(doc, div) {
                div.innerHTML = '<div id="b1" style="height:100px;width:100px;direction:rtl;position:relative;overflow:scroll">' + '<div id="b2" style="position:relative;width:100%;height:20px;"></div>' + '<div id="b3" style="position:absolute;width:20px;height:20px;top:0px;right:0px"></div>' + '</div>';
                var outerBox = document.getElementById('b1').getBoundingClientRect(),
                    b2 = document.getElementById('b2').getBoundingClientRect(),
                    b3 = document.getElementById('b3').getBoundingClientRect();
                return (b2.left !== outerBox.left && b3.right !== outerBox.right);
            }
        },
        
        {
            name: 'ScrollWidthInlinePaddingBug',
            ready: true,
            fn: function(doc) {
                var hasBug = false,
                    style, el;
                el = doc.createElement('div');
                style = el.style;
                style.height = '50px';
                style.width = '50px';
                style.padding = '10px';
                style.overflow = 'hidden';
                style.position = 'absolute';
                el.innerHTML = '<span style="display:inline-block;zoom:1;height:60px;width:60px;"></span>';
                doc.body.appendChild(el);
                if (el.scrollWidth === 70) {
                    hasBug = true;
                }
                doc.body.removeChild(el);
                return hasBug;
            }
        },
        
        {
            name: 'rtlVertScrollbarOnRight',
            ready: true,
            fn: function(doc, div) {
                div.innerHTML = '<div style="height:100px;width:100px;direction:rtl;overflow:scroll">' + '<div style="width:20px;height:200px;"></div>' + '</div>';
                var outerBox = div.firstChild,
                    innerBox = outerBox.firstChild;
                return (innerBox.offsetLeft + innerBox.offsetWidth !== outerBox.offsetLeft + outerBox.offsetWidth);
            }
        },
        
        {
            name: 'rtlVertScrollbarOverflowBug',
            ready: true,
            fn: function(doc, div) {
                div.innerHTML = '<div style="height:100px;width:100px;direction:rtl;overflow:auto">' + '<div style="width:95px;height:200px;"></div>' + '</div>';
                
                
                
                var outerBox = div.firstChild;
                return outerBox.clientHeight === outerBox.offsetHeight;
            }
        },
        {
            identity: 'defineProperty',
            fn: function() {
                if (Ext.isIE8m) {
                    Ext.Object.defineProperty = Ext.emptyFn;
                    return false;
                }
                return true;
            }
        },
        {
            identify: 'nativeXhr',
            fn: function() {
                if (typeof XMLHttpRequest !== 'undefined') {
                    return true;
                }
                
                XMLHttpRequest = function() {
                    try {
                        return new ActiveXObject('MSXML2.XMLHTTP.3.0');
                    } catch (ex) {
                        return null;
                    }
                };
                return false;
            }
        },
        
        {
            name: 'SpecialKeyDownRepeat',
            fn: function() {
                return Ext.isWebKit ? parseInt(navigator.userAgent.match(/AppleWebKit\/(\d+)/)[1], 10) >= 525 : !((Ext.isGecko && !Ext.isWindows) || (Ext.isOpera && Ext.operaVersion < 12));
            }
        },
        
        {
            name: 'EmulatedMouseOver',
            fn: function() {
                
                return Ext.os.is.iOS;
            }
        },
        
        {
            
            name: 'Hashchange',
            fn: function() {
                
                var docMode = document.documentMode;
                return 'onhashchange' in window && (docMode === undefined || docMode > 7);
            }
        },
        
        {
            name: 'FixedTableWidthBug',
            ready: true,
            fn: function() {
                if (Ext.isIE8) {
                    
                    return false;
                }
                var outer = document.createElement('div'),
                    inner = document.createElement('div'),
                    width;
                outer.setAttribute('style', 'display:table;table-layout:fixed;');
                inner.setAttribute('style', 'display:table-cell;min-width:50px;');
                outer.appendChild(inner);
                document.body.appendChild(outer);
                
                outer.offsetWidth;
                outer.style.width = '25px';
                width = outer.offsetWidth;
                document.body.removeChild(outer);
                return width === 50;
            }
        },
        {
            name: 'FocusinFocusoutEvents',
            fn: function() {
                return !Ext.isGecko;
            }
        }
    ]
};
Ext.supports = {};
Ext.feature.detect();


Ext.env.Ready = {
    blocks: (location.search || '').indexOf('ext-pauseReadyFire') > 0 ? 1 : 0,
    bound: 0,
    delay: 1,
    events: [],
    firing: false,
    generation: 0,
    listeners: [],
    nextId: 0,
    sortGeneration: 0,
    state: 0,
    timer: null,
    bind: function() {
        var me = Ext.env.Ready,
            doc = document;
        if (!me.bound) {
            
            if (doc.readyState == 'complete') {
                
                me.onReadyEvent({
                    type: doc.readyState || 'body'
                });
            } else {
                me.bound = 1;
                if (Ext.browser.is.PhoneGap && !Ext.os.is.Desktop) {
                    me.bound = 2;
                    doc.addEventListener('deviceready', me.onReadyEvent, false);
                }
                doc.addEventListener('DOMContentLoaded', me.onReadyEvent, false);
                window.addEventListener('load', me.onReadyEvent, false);
            }
        }
    },
    block: function() {
        ++this.blocks;
        Ext.isReady = false;
    },
    
    fireReady: function() {
        var me = Ext.env.Ready;
        if (!me.state) {
            Ext._readyTime = Ext.now();
            Ext.isDomReady = true;
            me.state = 1;
            
            Ext.feature.detect(true);
            if (!me.delay) {
                me.handleReady();
            } else if (navigator.standalone) {
                
                
                
                
                me.timer = Ext.defer(function() {
                    me.timer = null;
                    me.handleReadySoon();
                }, 1);
            } else {
                me.handleReadySoon();
            }
        }
    },
    
    handleReady: function() {
        var me = this;
        if (me.state === 1) {
            me.state = 2;
            Ext._beforeReadyTime = Ext.now();
            me.invokeAll();
            Ext._afterReadytime = Ext.now();
        }
    },
    
    handleReadySoon: function(delay) {
        var me = this;
        if (!me.timer) {
            me.timer = Ext.defer(function() {
                me.timer = null;
                me.handleReady();
            }, delay || me.delay);
        }
    },
    
    invoke: function(listener) {
        var delay = listener.delay;
        if (delay) {
            Ext.defer(listener.fn, delay, listener.scope);
        } else {
            if (Ext.elevateFunction) {
                Ext.elevateFunction(listener.fn, listener.scope);
            } else {
                listener.fn.call(listener.scope);
            }
        }
    },
    
    invokeAll: function() {
        if (Ext.elevateFunction) {
            Ext.elevateFunction(this.doInvokeAll, this);
        } else {
            this.doInvokeAll();
        }
    },
    doInvokeAll: function() {
        var me = this,
            listeners = me.listeners,
            listener;
        if (!me.blocks) {
            
            Ext.isReady = true;
        }
        me.firing = true;
        
        
        while (listeners.length) {
            if (me.sortGeneration !== me.generation) {
                me.sortGeneration = me.generation;
                listeners.sort(me.sortFn);
            }
            listener = listeners.pop();
            if (me.blocks && !listener.dom) {
                listeners.push(listener);
                break;
            }
            me.invoke(listener);
        }
        me.firing = false;
    },
    
    makeListener: function(fn, scope, options) {
        var ret = {
                fn: fn,
                id: ++this.nextId,
                
                scope: scope,
                dom: false,
                priority: 0
            };
        if (options) {
            Ext.apply(ret, options);
        }
        ret.phase = ret.dom ? 0 : 1;
        
        return ret;
    },
    
    on: function(fn, scope, options) {
        var me = Ext.env.Ready,
            listener = me.makeListener(fn, scope, options);
        if (me.state === 2 && !me.firing && (listener.dom || !me.blocks)) { 
            me.invoke(listener);
        } else {
            me.listeners.push(listener);
            ++me.generation;
            if (!me.bound) {
                me.bind();
            }
        }
    },
    
    onReadyEvent: function(ev) {
        var me = Ext.env.Ready;
        if (Ext.elevateFunction) {
            Ext.elevateFunction(me.doReadyEvent, me, arguments);
        } else {
            me.doReadyEvent(ev);
        }
    },
    doReadyEvent: function(ev) {
        var me = this;
        if (ev && ev.type) {
            me.events.push(ev);
        }
        if (me.bound > 0) {
            me.unbind();
            me.bound = -1;
        }
        
        if (!me.state) {
            me.fireReady();
        }
    },
    
    sortFn: function(a, b) {
        return -((a.phase - b.phase) || (b.priority - a.priority) || (a.id - b.id));
    },
    unblock: function() {
        var me = this;
        if (me.blocks) {
            if (!--me.blocks) {
                if (me.state === 2 && !me.firing) {
                    
                    
                    me.invokeAll();
                }
            }
        }
    },
    
    
    
    
    
    
    unbind: function() {
        var me = this,
            doc = document;
        if (me.bound > 1) {
            doc.removeEventListener('deviceready', me.onReadyEvent, false);
        }
        doc.removeEventListener('DOMContentLoaded', me.onReadyEvent, false);
        window.removeEventListener('load', me.onReadyEvent, false);
    }
};
(function() {
    var Ready = Ext.env.Ready;
    
    if (Ext.isIE9m) {
        
        Ext.apply(Ready, {
            
            scrollTimer: null,
            
            readyStatesRe: /complete/i,
            
            pollScroll: function() {
                var scrollable = true;
                try {
                    document.documentElement.doScroll('left');
                } catch (e) {
                    scrollable = false;
                }
                
                
                if (scrollable && document.body) {
                    Ready.onReadyEvent({
                        type: 'doScroll'
                    });
                } else {
                    
                    
                    
                    Ready.scrollTimer = Ext.defer(Ready.pollScroll, 20);
                }
                return scrollable;
            },
            bind: function() {
                if (Ready.bound) {
                    return;
                }
                var doc = document,
                    topContext;
                
                try {
                    topContext = window.frameElement === undefined;
                } catch (e) {}
                
                
                if (!topContext || !doc.documentElement.doScroll) {
                    Ready.pollScroll = Ext.emptyFn;
                }
                
                else if (Ready.pollScroll()) {
                    
                    return;
                }
                if (doc.readyState == 'complete') {
                    
                    Ready.onReadyEvent({
                        type: 'already ' + (doc.readyState || 'body')
                    });
                } else {
                    doc.attachEvent('onreadystatechange', Ready.onReadyStateChange);
                    window.attachEvent('onload', Ready.onReadyEvent);
                    Ready.bound = 1;
                }
            },
            unbind: function() {
                document.detachEvent('onreadystatechange', Ready.onReadyStateChange);
                window.detachEvent('onload', Ready.onReadyEvent);
                if (Ext.isNumber(Ready.scrollTimer)) {
                    clearTimeout(Ready.scrollTimer);
                    Ready.scrollTimer = null;
                }
            },
            
            onReadyStateChange: function() {
                var state = document.readyState;
                if (Ready.readyStatesRe.test(state)) {
                    Ready.onReadyEvent({
                        type: state
                    });
                }
            }
        });
    }
    
    
    
    Ext.onDocumentReady = function(fn, scope, options) {
        var opt = {
                dom: true
            };
        if (options) {
            Ext.apply(opt, options);
        }
        Ready.on(fn, scope, opt);
    };
    
    Ext.onReady = function(fn, scope, options) {
        Ready.on(fn, scope, options);
    };
    Ready.bind();
}());




Ext._endTime = new Date().getTime();



if (Ext._beforereadyhandler) {
    Ext._beforereadyhandler();
}


Ext.define('Ext.overrides.util.Positionable', {
    override: 'Ext.util.Positionable',
    
    
    anchorTo: function(anchorToEl, alignment, offsets, animate, monitorScroll, callback) {
        var me = this,
            scroll = !Ext.isEmpty(monitorScroll),
            action = function() {
                me.alignTo(anchorToEl, alignment, offsets, animate);
                Ext.callback(callback, me);
            },
            anchor = me.getAnchor();
        
        me.removeAnchor();
        Ext.apply(anchor, {
            fn: action,
            scroll: scroll
        });
        Ext.on('resize', action, null);
        if (scroll) {
            Ext.getWin().on('scroll', action, null, {
                buffer: !isNaN(monitorScroll) ? monitorScroll : 50
            });
        }
        action();
        
        return me;
    },
    getAnchor: function() {
        var el = this.el,
            data, anchor;
        if (!el.dom) {
            return;
        }
        data = el.getData();
        anchor = data._anchor;
        if (!anchor) {
            anchor = data._anchor = {};
        }
        return anchor;
    },
    
    
    removeAnchor: function() {
        var anchor = this.getAnchor();
        if (anchor && anchor.fn) {
            Ext.un('resize', anchor.fn);
            if (anchor.scroll) {
                Ext.getWin().on('scroll', anchor.fn);
            }
            delete anchor.fn;
        }
        return this;
    },
    
    setBox: function(box, animate) {
        var me = this;
        if (box.isRegion) {
            box = {
                x: box.left,
                y: box.top,
                width: box.right - box.left,
                height: box.bottom - box.top
            };
        }
        if (animate) {
            me.constrainBox(box);
            me.animate(Ext.applyIf({
                to: box,
                listeners: {
                    afteranimate: Ext.Function.bind(me.afterSetPosition, me, [
                        box.x,
                        box.y
                    ])
                }
            }, animate));
        } else {
            me.callParent([
                box
            ]);
        }
        return me;
    }
});

Ext.define('Ext.overrides.event.Event', {
    override: 'Ext.event.Event',
    
    mousedownEvents: {
        mousedown: 1,
        pointerdown: 1,
        touchstart: 1
    },
    
    injectEvent: (function() {
        var API,
            dispatchers = {},
            
            crazyIEButtons;
        
        
        
        if (!Ext.isIE9m && document.createEvent) {
            
            API = {
                createHtmlEvent: function(doc, type, bubbles, cancelable) {
                    var event = doc.createEvent('HTMLEvents');
                    event.initEvent(type, bubbles, cancelable);
                    return event;
                },
                createMouseEvent: function(doc, type, bubbles, cancelable, detail, clientX, clientY, ctrlKey, altKey, shiftKey, metaKey, button, relatedTarget) {
                    var event = doc.createEvent('MouseEvents'),
                        view = doc.defaultView || window;
                    if (event.initMouseEvent) {
                        event.initMouseEvent(type, bubbles, cancelable, view, detail, clientX, clientY, clientX, clientY, ctrlKey, altKey, shiftKey, metaKey, button, relatedTarget);
                    } else {
                        
                        event = doc.createEvent('UIEvents');
                        event.initEvent(type, bubbles, cancelable);
                        event.view = view;
                        event.detail = detail;
                        event.screenX = clientX;
                        event.screenY = clientY;
                        event.clientX = clientX;
                        event.clientY = clientY;
                        event.ctrlKey = ctrlKey;
                        event.altKey = altKey;
                        event.metaKey = metaKey;
                        event.shiftKey = shiftKey;
                        event.button = button;
                        event.relatedTarget = relatedTarget;
                    }
                    return event;
                },
                createUIEvent: function(doc, type, bubbles, cancelable, detail) {
                    var event = doc.createEvent('UIEvents'),
                        view = doc.defaultView || window;
                    event.initUIEvent(type, bubbles, cancelable, view, detail);
                    return event;
                },
                fireEvent: function(target, type, event) {
                    target.dispatchEvent(event);
                }
            };
        } else if (document.createEventObject) {
            
            crazyIEButtons = {
                0: 1,
                1: 4,
                2: 2
            };
            API = {
                createHtmlEvent: function(doc, type, bubbles, cancelable) {
                    var event = doc.createEventObject();
                    event.bubbles = bubbles;
                    event.cancelable = cancelable;
                    return event;
                },
                createMouseEvent: function(doc, type, bubbles, cancelable, detail, clientX, clientY, ctrlKey, altKey, shiftKey, metaKey, button, relatedTarget) {
                    var event = doc.createEventObject();
                    event.bubbles = bubbles;
                    event.cancelable = cancelable;
                    event.detail = detail;
                    event.screenX = clientX;
                    event.screenY = clientY;
                    event.clientX = clientX;
                    event.clientY = clientY;
                    event.ctrlKey = ctrlKey;
                    event.altKey = altKey;
                    event.shiftKey = shiftKey;
                    event.metaKey = metaKey;
                    event.button = crazyIEButtons[button] || button;
                    event.relatedTarget = relatedTarget;
                    
                    return event;
                },
                createUIEvent: function(doc, type, bubbles, cancelable, detail) {
                    var event = doc.createEventObject();
                    event.bubbles = bubbles;
                    event.cancelable = cancelable;
                    return event;
                },
                fireEvent: function(target, type, event) {
                    target.fireEvent('on' + type, event);
                }
            };
        }
        
        
        Ext.Object.each({
            load: [
                false,
                false
            ],
            unload: [
                false,
                false
            ],
            select: [
                true,
                false
            ],
            change: [
                true,
                false
            ],
            submit: [
                true,
                true
            ],
            reset: [
                true,
                false
            ],
            resize: [
                true,
                false
            ],
            scroll: [
                true,
                false
            ]
        }, function(name, value) {
            var bubbles = value[0],
                cancelable = value[1];
            dispatchers[name] = function(targetEl, srcEvent) {
                var e = API.createHtmlEvent(name, bubbles, cancelable);
                API.fireEvent(targetEl, name, e);
            };
        });
        
        
        function createMouseEventDispatcher(type, detail) {
            var cancelable = (type != 'mousemove');
            return function(targetEl, srcEvent) {
                var xy = srcEvent.getXY(),
                    e = API.createMouseEvent(targetEl.ownerDocument, type, true, cancelable, detail, xy[0], xy[1], srcEvent.ctrlKey, srcEvent.altKey, srcEvent.shiftKey, srcEvent.metaKey, srcEvent.button, srcEvent.relatedTarget);
                API.fireEvent(targetEl, type, e);
            };
        }
        Ext.each([
            'click',
            'dblclick',
            'mousedown',
            'mouseup',
            'mouseover',
            'mousemove',
            'mouseout'
        ], function(eventName) {
            dispatchers[eventName] = createMouseEventDispatcher(eventName, 1);
        });
        
        
        Ext.Object.each({
            focusin: [
                true,
                false
            ],
            focusout: [
                true,
                false
            ],
            activate: [
                true,
                true
            ],
            focus: [
                false,
                false
            ],
            blur: [
                false,
                false
            ]
        }, function(name, value) {
            var bubbles = value[0],
                cancelable = value[1];
            dispatchers[name] = function(targetEl, srcEvent) {
                var e = API.createUIEvent(targetEl.ownerDocument, name, bubbles, cancelable, 1);
                API.fireEvent(targetEl, name, e);
            };
        });
        
        if (!API) {
            
            dispatchers = {};
            
            API = {};
        }
        function cannotInject(target, srcEvent) {}
        
        return function(target) {
            var me = this,
                dispatcher = dispatchers[me.type] || cannotInject,
                t = target ? (target.dom || target) : me.getTarget();
            dispatcher(t, me);
        };
    }()),
    
    preventDefault: function() {
        var me = this,
            event = me.browserEvent,
            unselectable, target;
        
        
        
        if (typeof event.type !== 'unknown') {
            if (event.preventDefault) {
                event.preventDefault();
            } else {
                
                if (event.type === 'mousedown') {
                    target = event.target;
                    unselectable = target.getAttribute('unselectable');
                    if (unselectable !== 'on') {
                        target.setAttribute('unselectable', 'on');
                        Ext.defer(function() {
                            target.setAttribute('unselectable', unselectable);
                        }, 1);
                    }
                }
                
                event.returnValue = false;
                
                
                if (event.ctrlKey || event.keyCode > 111 && event.keyCode < 124) {
                    event.keyCode = -1;
                }
            }
        }
        return me;
    },
    stopPropagation: function() {
        var me = this,
            event = me.browserEvent;
        
        
        
        if (typeof event.type !== 'unknown') {
            if (me.mousedownEvents[me.type]) {
                
                
                Ext.GlobalEvents.fireMouseDown(me);
            }
            me.callParent();
        }
        return me;
    },
    deprecated: {
        '5.0': {
            methods: {
                
                clone: function() {
                    return new this.self(this.browserEvent,this);
                }
            }
        }
    }
}, function() {
    var Event = this,
        btnMap,
        onKeyDown = function(e) {
            if (e.keyCode === 9) {
                Event.forwardTab = !e.shiftKey;
            }
        },
        onKeyUp = function(e) {
            if (e.keyCode === 9) {
                delete Event.forwardTab;
            }
        };
    if (Ext.isIE9m) {
        btnMap = {
            0: 0,
            1: 0,
            4: 1,
            2: 2
        };
        Event.override({
            statics: {
                
                enableIEAsync: function(browserEvent) {
                    var name,
                        fakeEvent = {};
                    for (name in browserEvent) {
                        fakeEvent[name] = browserEvent[name];
                    }
                    return fakeEvent;
                }
            },
            constructor: function(event, info, touchesMap, identifiers) {
                var me = this;
                me.callParent([
                    event,
                    info,
                    touchesMap,
                    identifiers
                ]);
                me.button = btnMap[event.button];
                if (event.type === 'contextmenu') {
                    me.button = 2;
                }
                
                
                
                
                me.toElement = event.toElement;
                me.fromElement = event.fromElement;
            },
            mouseLeaveRe: /(mouseout|mouseleave)/,
            mouseEnterRe: /(mouseover|mouseenter)/,
            
            enableIEAsync: function(browserEvent) {
                this.browserEvent = this.self.enableIEAsync(browserEvent);
            },
            getRelatedTarget: function(selector, maxDepth, returnEl) {
                var me = this,
                    type, target;
                if (!me.relatedTarget) {
                    type = me.type;
                    if (me.mouseLeaveRe.test(type)) {
                        target = me.toElement;
                    } else if (me.mouseEnterRe.test(type)) {
                        target = me.fromElement;
                    }
                    if (target) {
                        me.relatedTarget = me.self.resolveTextNode(target);
                    }
                }
                return me.callParent([
                    selector,
                    maxDepth,
                    returnEl
                ]);
            }
        });
        
        
        
        document.attachEvent('onkeydown', onKeyDown);
        document.attachEvent('onkeyup', onKeyUp);
        window.attachEvent('onunload', function() {
            document.detachEvent('onkeydown', onKeyDown);
            document.detachEvent('onkeyup', onKeyUp);
        });
    } else if (document.addEventListener) {
        document.addEventListener('keydown', onKeyDown, true);
        document.addEventListener('keyup', onKeyUp, true);
    }
});

Ext.define('Ext.overrides.event.publisher.Dom', {
    override: 'Ext.event.publisher.Dom'
}, function() {
    var DomPublisher = Ext.event.publisher.Dom,
        prototype = DomPublisher.prototype,
        docBody = document.body,
        prototype, onDirectEvent;
    if (Ext.isIE9m) {
        prototype = DomPublisher.prototype;
        prototype.target = document;
        prototype.directBoundListeners = {};
        
        
        onDirectEvent = function(e, publisher, capture) {
            e.target = e.srcElement || window;
            e.currentTarget = this;
            if (capture) {
                
                
                publisher.onDirectCaptureEvent(e);
            } else {
                publisher.onDirectEvent(e);
            }
        };
        onDirectCaptureEvent = function(e, publisher) {
            e.target = e.srcElement || window;
            e.currentTarget = this;
            
            publisher.onDirectCaptureEvent(e);
        };
        DomPublisher.override({
            addDelegatedListener: function(eventName) {
                this.delegatedListeners[eventName] = 1;
                
                
                this.target.attachEvent('on' + eventName, this.onDelegatedEvent);
            },
            removeDelegatedListener: function(eventName) {
                delete this.delegatedListeners[eventName];
                this.target.detachEvent('on' + eventName, this.onDelegatedEvent);
            },
            addDirectListener: function(eventName, element, capture) {
                var me = this,
                    dom = element.dom,
                    
                    
                    boundFn = Ext.Function.bind(onDirectEvent, dom, [
                        me,
                        capture
                    ], true),
                    directBoundListeners = me.directBoundListeners,
                    handlers = directBoundListeners[eventName] || (directBoundListeners[eventName] = {});
                handlers[dom.id] = boundFn;
                
                
                if (dom.attachEvent) {
                    dom.attachEvent('on' + eventName, boundFn);
                } else {
                    me.callParent(arguments);
                }
            },
            removeDirectListener: function(eventName, element) {
                var dom = element.dom;
                if (dom.detachEvent) {
                    dom.detachEvent('on' + eventName, this.directBoundListeners[eventName][dom.id]);
                } else {
                    this.callParent(arguments);
                }
            },
            doDelegatedEvent: function(e, invokeAfter) {
                e.target = e.srcElement || window;
                if (e.type === 'focusin') {
                    e.relatedTarget = e.fromElement === docBody ? null : e.fromElement;
                } else if (e.type === 'focusout') {
                    e.relatedTarget = e.toElement === docBody ? null : e.toElement;
                }
                return this.callParent([
                    e,
                    invokeAfter
                ]);
            }
        });
        
        
        Ext.apply(prototype.directEvents, prototype.captureEvents);
        prototype.captureEvents = {};
    }
});

Ext.define('Ext.overrides.event.publisher.Gesture', {
    override: 'Ext.event.publisher.Gesture'
}, function() {
    if (Ext.isIE9m) {
        this.override({
            updateTouches: function(e, isEnd) {
                var browserEvent = e.browserEvent,
                    xy = e.getXY();
                
                
                browserEvent.pageX = xy[0];
                browserEvent.pageY = xy[1];
                this.callParent([
                    e,
                    isEnd
                ]);
            },
            doDelegatedEvent: function(e) {
                this.callParent([
                    Ext.event.Event.enableIEAsync(e)
                ]);
            }
        });
    }
});


Ext.define('Ext.overrides.dom.Element', (function() {
    var Element,
        
        WIN = window,
        DOC = document,
        HIDDEN = 'hidden',
        ISCLIPPED = 'isClipped',
        OVERFLOW = 'overflow',
        OVERFLOWX = 'overflow-x',
        OVERFLOWY = 'overflow-y',
        ORIGINALCLIP = 'originalClip',
        HEIGHT = 'height',
        WIDTH = 'width',
        VISIBILITY = 'visibility',
        DISPLAY = 'display',
        NONE = 'none',
        HIDDEN = 'hidden',
        OFFSETS = 'offsets',
        ORIGINALDISPLAY = 'originalDisplay',
        VISMODE = 'visibilityMode',
        ISVISIBLE = 'isVisible',
        OFFSETCLASS = Ext.baseCSSPrefix + 'hidden-offsets',
        boxMarkup = [
            '<div class="{0}-tl" role="presentation">',
            '<div class="{0}-tr" role="presentation">',
            '<div class="{0}-tc" role="presentation"></div>',
            '</div>',
            '</div>',
            '<div class="{0}-ml" role="presentation">',
            '<div class="{0}-mr" role="presentation">',
            '<div class="{0}-mc" role="presentation"></div>',
            '</div>',
            '</div>',
            '<div class="{0}-bl" role="presentation">',
            '<div class="{0}-br" role="presentation">',
            '<div class="{0}-bc" role="presentation"></div>',
            '</div>',
            '</div>'
        ].join(''),
        scriptTagRe = /(?:<script([^>]*)?>)((\n|\r|.)*?)(?:<\/script>)/ig,
        replaceScriptTagRe = /(?:<script.*?>)((\n|\r|.)*?)(?:<\/script>)/ig,
        srcRe = /\ssrc=([\'\"])(.*?)\1/i,
        nonSpaceRe = /\S/,
        typeRe = /\stype=([\'\"])(.*?)\1/i,
        msRe = /^-ms-/,
        camelRe = /(-[a-z])/gi,
        camelReplaceFn = function(m, a) {
            return a.charAt(1).toUpperCase();
        },
        XMASKED = Ext.baseCSSPrefix + "masked",
        XMASKEDRELATIVE = Ext.baseCSSPrefix + "masked-relative",
        EXTELMASKMSG = Ext.baseCSSPrefix + "mask-msg",
        mouseEnterLeaveRe = /^(?:mouseenter|mouseleave)$/,
        bodyRe = /^body/i,
        propertyCache = {},
        getDisplay = function(el) {
            var data = el.getData(),
                display = data[ORIGINALDISPLAY];
            if (display === undefined) {
                data[ORIGINALDISPLAY] = display = '';
            }
            return display;
        },
        getVisMode = function(el) {
            var data = el.getData(),
                visMode = data[VISMODE];
            if (visMode === undefined) {
                data[VISMODE] = visMode = Element.VISIBILITY;
            }
            return visMode;
        },
        garbageBin,
        emptyRange = DOC.createRange ? DOC.createRange() : null,
        inputTags = {
            INPUT: true,
            TEXTAREA: true
        };
    return {
        override: 'Ext.dom.Element',
        mixins: [
            'Ext.util.Animate'
        ],
        uses: [
            'Ext.dom.GarbageCollector',
            'Ext.dom.Fly',
            'Ext.event.publisher.MouseEnterLeave',
            'Ext.fx.Manager',
            'Ext.fx.Anim'
        ],
        skipGarbageCollection: false,
        _init: function(E) {
            Element = E;
        },
        
        statics: {
            selectableCls: Ext.baseCSSPrefix + 'selectable',
            unselectableCls: Ext.baseCSSPrefix + 'unselectable',
            
            tabIndexAttributeName: Ext.isIE8 ? 'tabIndex' : 'tabindex',
            tabbableSelector: 'a[href],button,iframe,input,select,textarea,[tabindex],[contenteditable="true"]',
            
            
            
            naturallyFocusableTags: {
                BUTTON: true,
                IFRAME: true,
                EMBED: true,
                INPUT: true,
                OBJECT: true,
                SELECT: true,
                TEXTAREA: true,
                HTML: Ext.isIE ? true : false
            },
            
            naturallyTabbableTags: {
                BUTTON: true,
                IFRAME: true,
                INPUT: true,
                SELECT: true,
                TEXTAREA: true,
                OBJECT: Ext.isIE8m ? true : false
            },
            tabbableSavedFlagAttribute: 'data-tabindexsaved',
            tabbableSavedAttribute: 'data-savedtabindex',
            normalize: function(prop) {
                if (prop === 'float') {
                    prop = Ext.supports.Float ? 'cssFloat' : 'styleFloat';
                }
                
                return propertyCache[prop] || (propertyCache[prop] = prop.replace(msRe, 'ms-').replace(camelRe, camelReplaceFn));
            },
            getViewportHeight: function() {
                return Ext.isIE9m ? DOC.documentElement.clientHeight : WIN.innerHeight;
            },
            getViewportWidth: function() {
                return (!Ext.isStrict && !Ext.isOpera) ? document.body.clientWidth : Ext.isIE9m ? DOC.documentElement.clientWidth : WIN.innerWidth;
            }
        },
        
        addClsOnClick: function(className, testFn, scope) {
            var me = this,
                dom = me.dom,
                hasTest = Ext.isFunction(testFn);
            me.on("mousedown", function() {
                if (hasTest && testFn.call(scope || me, me) === false) {
                    return false;
                }
                Ext.fly(dom).addCls(className);
                var d = Ext.getDoc(),
                    fn = function() {
                        Ext.fly(dom).removeCls(className);
                        d.removeListener("mouseup", fn);
                    };
                d.on("mouseup", fn);
            });
            return me;
        },
        
        addClsOnFocus: function(className, testFn, scope) {
            var me = this,
                dom = me.dom,
                hasTest = Ext.isFunction(testFn);
            me.on("focus", function() {
                if (hasTest && testFn.call(scope || me, me) === false) {
                    return false;
                }
                Ext.fly(dom).addCls(className);
            });
            me.on("blur", function() {
                Ext.fly(dom).removeCls(className);
            });
            return me;
        },
        
        addClsOnOver: function(className, testFn, scope) {
            var me = this,
                dom = me.dom,
                hasTest = Ext.isFunction(testFn);
            me.hover(function() {
                if (hasTest && testFn.call(scope || me, me) === false) {
                    return;
                }
                Ext.fly(dom).addCls(className);
            }, function() {
                Ext.fly(dom).removeCls(className);
            });
            return me;
        },
        
        addKeyListener: function(key, fn, scope) {
            var config;
            if (typeof key !== 'object' || Ext.isArray(key)) {
                config = {
                    target: this,
                    key: key,
                    fn: fn,
                    scope: scope
                };
            } else {
                config = {
                    target: this,
                    key: key.key,
                    shift: key.shift,
                    ctrl: key.ctrl,
                    alt: key.alt,
                    fn: fn,
                    scope: scope
                };
            }
            return new Ext.util.KeyMap(config);
        },
        
        addKeyMap: function(config) {
            return new Ext.util.KeyMap(Ext.apply({
                target: this
            }, config));
        },
        
        afterAnimate: function() {
            var shadow = this.shadow;
            if (shadow && !shadow.disabled && !shadow.animate) {
                shadow.show();
            }
        },
        
        anchorAnimX: function(anchor) {
            var xName = (anchor === 'l') ? 'right' : 'left';
            this.dom.style[xName] = '0px';
        },
        
        anim: function(config) {
            if (!Ext.isObject(config)) {
                return (config) ? {} : false;
            }
            var me = this,
                duration = config.duration || Ext.fx.Anim.prototype.duration,
                easing = config.easing || 'ease',
                animConfig;
            if (config.stopAnimation) {
                me.stopAnimation();
            }
            Ext.applyIf(config, Ext.fx.Manager.getFxDefaults(me.id));
            
            Ext.fx.Manager.setFxDefaults(me.id, {
                delay: 0
            });
            animConfig = {
                
                target: me.dom,
                remove: config.remove,
                alternate: config.alternate || false,
                duration: duration,
                easing: easing,
                callback: config.callback,
                listeners: config.listeners,
                iterations: config.iterations || 1,
                scope: config.scope,
                block: config.block,
                concurrent: config.concurrent,
                delay: config.delay || 0,
                paused: true,
                keyframes: config.keyframes,
                from: config.from || {},
                to: Ext.apply({}, config)
            };
            Ext.apply(animConfig.to, config.to);
            
            delete animConfig.to.to;
            delete animConfig.to.from;
            delete animConfig.to.remove;
            delete animConfig.to.alternate;
            delete animConfig.to.keyframes;
            delete animConfig.to.iterations;
            delete animConfig.to.listeners;
            delete animConfig.to.target;
            delete animConfig.to.paused;
            delete animConfig.to.callback;
            delete animConfig.to.scope;
            delete animConfig.to.duration;
            delete animConfig.to.easing;
            delete animConfig.to.concurrent;
            delete animConfig.to.block;
            delete animConfig.to.stopAnimation;
            delete animConfig.to.delay;
            return animConfig;
        },
        
        animate: function(config) {
            var me = this,
                animId = me.dom.id || Ext.id(me.dom),
                listeners, anim, end;
            if (!Ext.fx.Manager.hasFxBlock(animId)) {
                
                if (config.listeners) {
                    listeners = config.listeners;
                    delete config.listeners;
                }
                if (config.internalListeners) {
                    config.listeners = config.internalListeners;
                    delete config.internalListeners;
                }
                end = config.autoEnd;
                delete config.autoEnd;
                anim = new Ext.fx.Anim(me.anim(config));
                anim.on({
                    afteranimate: 'afterAnimate',
                    beforeanimate: 'beforeAnimate',
                    scope: me,
                    single: true
                });
                if (listeners) {
                    anim.on(listeners);
                }
                Ext.fx.Manager.queueFx(anim);
                if (end) {
                    anim.jumpToEnd();
                }
            }
            return me;
        },
        
        beforeAnimate: function() {
            var shadow = this.shadow;
            if (shadow && !shadow.disabled && !shadow.animate) {
                shadow.hide();
            }
        },
        
        boxWrap: function(cls) {
            cls = cls || Ext.baseCSSPrefix + 'box';
            var el = Ext.get(this.insertHtml("beforeBegin", "<div class='" + cls + "' role='presentation'>" + Ext.String.format(boxMarkup, cls) + "</div>"));
            el.selectNode('.' + cls + '-mc').appendChild(this.dom);
            return el;
        },
        
        clean: function(forceReclean) {
            var me = this,
                dom = me.dom,
                data = me.getData(),
                n = dom.firstChild,
                ni = -1,
                nx;
            if (data.isCleaned && forceReclean !== true) {
                return me;
            }
            while (n) {
                nx = n.nextSibling;
                if (n.nodeType === 3) {
                    
                    if (!(nonSpaceRe.test(n.nodeValue))) {
                        dom.removeChild(n);
                    }
                    
                    else if (nx && nx.nodeType === 3) {
                        n.appendData(Ext.String.trim(nx.data));
                        dom.removeChild(nx);
                        nx = n.nextSibling;
                        n.nodeIndex = ++ni;
                    }
                } else {
                    
                    Ext.fly(n, '_clean').clean();
                    n.nodeIndex = ++ni;
                }
                n = nx;
            }
            data.isCleaned = true;
            return me;
        },
        
        empty: emptyRange ? function() {
            var dom = this.dom;
            if (dom.firstChild) {
                emptyRange.setStartBefore(dom.firstChild);
                emptyRange.setEndAfter(dom.lastChild);
                emptyRange.deleteContents();
            }
        } : function() {
            var dom = this.dom;
            while (dom.lastChild) {
                dom.removeChild(dom.lastChild);
            }
        },
        clearListeners: function() {
            this.removeAnchor();
            this.callParent();
        },
        
        clearPositioning: function(value) {
            value = value || '';
            return this.setStyle({
                left: value,
                right: value,
                top: value,
                bottom: value,
                'z-index': '',
                position: 'static'
            });
        },
        
        createProxy: function(config, renderTo, matchBox) {
            config = (typeof config === 'object') ? config : {
                tag: "div",
                role: 'presentation',
                cls: config
            };
            var me = this,
                proxy = renderTo ? Ext.DomHelper.append(renderTo, config, true) : Ext.DomHelper.insertBefore(me.dom, config, true);
            proxy.setVisibilityMode(Element.DISPLAY);
            proxy.hide();
            if (matchBox && me.setBox && me.getBox) {
                
                proxy.setBox(me.getBox());
            }
            return proxy;
        },
        
        clearOpacity: function() {
            return this.setOpacity('');
        },
        
        clip: function() {
            var me = this,
                data = me.getData(),
                style;
            if (!data[ISCLIPPED]) {
                data[ISCLIPPED] = true;
                style = me.getStyle([
                    OVERFLOW,
                    OVERFLOWX,
                    OVERFLOWY
                ]);
                data[ORIGINALCLIP] = {
                    o: style[OVERFLOW],
                    x: style[OVERFLOWX],
                    y: style[OVERFLOWY]
                };
                me.setStyle(OVERFLOW, HIDDEN);
                me.setStyle(OVERFLOWX, HIDDEN);
                me.setStyle(OVERFLOWY, HIDDEN);
            }
            return me;
        },
        destroy: function() {
            var me = this,
                dom = me.dom,
                data = me.getData(),
                maskEl, maskMsg;
            if (dom && me.isAnimate) {
                me.stopAnimation();
            }
            me.callParent();
            
            
            
            
            
            if (dom && Ext.isIE8 && (dom.window != dom) && (dom.nodeType !== 9) && (dom.tagName !== 'BODY') && (dom.tagName !== 'HTML')) {
                garbageBin = garbageBin || DOC.createElement('div');
                garbageBin.appendChild(dom);
                garbageBin.innerHTML = '';
            }
            if (data) {
                maskEl = data.maskEl;
                maskMsg = data.maskMsg;
                if (maskEl) {
                    maskEl.destroy();
                }
                if (maskMsg) {
                    maskMsg.destroy();
                }
            }
        },
        
        enableDisplayMode: function(display) {
            var me = this;
            me.setVisibilityMode(Element.DISPLAY);
            if (display !== undefined) {
                me.getData()[ORIGINALDISPLAY] = display;
            }
            return me;
        },
        
        fadeIn: function(o) {
            var me = this,
                dom = me.dom;
            me.animate(Ext.apply({}, o, {
                opacity: 1,
                internalListeners: {
                    beforeanimate: function(anim) {
                        
                        
                        var el = Ext.fly(dom, '_anim');
                        if (el.isStyle('display', 'none')) {
                            el.setDisplayed('');
                        } else {
                            el.show();
                        }
                    }
                }
            }));
            return this;
        },
        
        fadeOut: function(o) {
            var me = this,
                dom = me.dom;
            o = Ext.apply({
                opacity: 0,
                internalListeners: {
                    afteranimate: function(anim) {
                        if (dom && anim.to.opacity === 0) {
                            var el = Ext.fly(dom, '_anim');
                            if (o.useDisplay) {
                                el.setDisplayed(false);
                            } else {
                                el.hide();
                            }
                        }
                    }
                }
            }, o);
            me.animate(o);
            return me;
        },
        
        fixDisplay: function() {
            var me = this;
            if (me.isStyle(DISPLAY, NONE)) {
                me.setStyle(VISIBILITY, HIDDEN);
                me.setStyle(DISPLAY, getDisplay(me));
                
                if (me.isStyle(DISPLAY, NONE)) {
                    
                    me.setStyle(DISPLAY, "block");
                }
            }
        },
        
        frame: function(color, count, obj) {
            var me = this,
                dom = me.dom,
                beforeAnim;
            color = color || '#C3DAF9';
            count = count || 1;
            obj = obj || {};
            beforeAnim = function() {
                var el = Ext.fly(dom, '_anim'),
                    animScope = this,
                    box, proxy, proxyAnim;
                el.show();
                box = el.getBox();
                proxy = Ext.getBody().createChild({
                    role: 'presentation',
                    id: el.dom.id + '-anim-proxy',
                    style: {
                        position: 'absolute',
                        'pointer-events': 'none',
                        'z-index': 35000,
                        border: '0px solid ' + color
                    }
                });
                proxyAnim = new Ext.fx.Anim({
                    target: proxy,
                    duration: obj.duration || 1000,
                    iterations: count,
                    from: {
                        top: box.y,
                        left: box.x,
                        borderWidth: 0,
                        opacity: 1,
                        height: box.height,
                        width: box.width
                    },
                    to: {
                        top: box.y - 20,
                        left: box.x - 20,
                        borderWidth: 10,
                        opacity: 0,
                        height: box.height + 40,
                        width: box.width + 40
                    }
                });
                proxyAnim.on('afteranimate', function() {
                    proxy.destroy();
                    
                    animScope.end();
                });
            };
            me.animate({
                
                duration: (Math.max(obj.duration, 500) * 2) || 2000,
                listeners: {
                    beforeanimate: {
                        fn: beforeAnim
                    }
                },
                callback: obj.callback,
                scope: obj.scope
            });
            return me;
        },
        
        getColor: function(attr, defaultValue, prefix) {
            var v = this.getStyle(attr),
                color = prefix || prefix === '' ? prefix : '#',
                h, len,
                i = 0;
            if (!v || (/transparent|inherit/.test(v))) {
                return defaultValue;
            }
            if (/^r/.test(v)) {
                v = v.slice(4, v.length - 1).split(',');
                len = v.length;
                for (; i < len; i++) {
                    h = parseInt(v[i], 10);
                    color += (h < 16 ? '0' : '') + h.toString(16);
                }
            } else {
                v = v.replace('#', '');
                color += v.length === 3 ? v.replace(/^(\w)(\w)(\w)$/, '$1$1$2$2$3$3') : v;
            }
            return (color.length > 5 ? color.toLowerCase() : defaultValue);
        },
        
        getLoader: function() {
            var me = this,
                data = me.getData(),
                loader = data.loader;
            if (!loader) {
                data.loader = loader = new Ext.ElementLoader({
                    target: me
                });
            }
            return loader;
        },
        
        getPositioning: function(autoPx) {
            var styles = this.getStyle([
                    'left',
                    'top',
                    'position',
                    'z-index'
                ]),
                dom = this.dom;
            if (autoPx) {
                if (styles.left === 'auto') {
                    styles.left = dom.offsetLeft + 'px';
                }
                if (styles.top === 'auto') {
                    styles.top = dom.offsetTop + 'px';
                }
            }
            return styles;
        },
        
        ghost: function(anchor, obj) {
            var me = this,
                dom = me.dom,
                beforeAnim;
            anchor = anchor || "b";
            beforeAnim = function() {
                var el = Ext.fly(dom, '_anim'),
                    width = el.getWidth(),
                    height = el.getHeight(),
                    xy = el.getXY(),
                    position = el.getPositioning(),
                    to = {
                        opacity: 0
                    };
                switch (anchor) {
                    case 't':
                        to.y = xy[1] - height;
                        break;
                    case 'l':
                        to.x = xy[0] - width;
                        break;
                    case 'r':
                        to.x = xy[0] + width;
                        break;
                    case 'b':
                        to.y = xy[1] + height;
                        break;
                    case 'tl':
                        to.x = xy[0] - width;
                        to.y = xy[1] - height;
                        break;
                    case 'bl':
                        to.x = xy[0] - width;
                        to.y = xy[1] + height;
                        break;
                    case 'br':
                        to.x = xy[0] + width;
                        to.y = xy[1] + height;
                        break;
                    case 'tr':
                        to.x = xy[0] + width;
                        to.y = xy[1] - height;
                        break;
                }
                this.to = to;
                this.on('afteranimate', function() {
                    var el = Ext.fly(dom, '_anim');
                    if (el) {
                        el.hide();
                        el.clearOpacity();
                        el.setPositioning(position);
                    }
                });
            };
            me.animate(Ext.applyIf(obj || {}, {
                duration: 500,
                easing: 'ease-out',
                listeners: {
                    beforeanimate: beforeAnim
                }
            }));
            return me;
        },
        
        hide: function(animate) {
            
            if (typeof animate === 'string') {
                this.setVisible(false, animate);
                return this;
            }
            this.setVisible(false, this.anim(animate));
            return this;
        },
        
        highlight: function(color, o) {
            var me = this,
                dom = me.dom,
                from = {},
                restore, to, attr, lns, event, fn;
            o = o || {};
            lns = o.listeners || {};
            attr = o.attr || 'backgroundColor';
            from[attr] = color || 'ffff9c';
            if (!o.to) {
                to = {};
                to[attr] = o.endColor || me.getColor(attr, 'ffffff', '');
            } else {
                to = o.to;
            }
            
            o.listeners = Ext.apply(Ext.apply({}, lns), {
                beforeanimate: function() {
                    restore = dom.style[attr];
                    var el = Ext.fly(dom, '_anim');
                    el.clearOpacity();
                    el.show();
                    event = lns.beforeanimate;
                    if (event) {
                        fn = event.fn || event;
                        return fn.apply(event.scope || lns.scope || WIN, arguments);
                    }
                },
                afteranimate: function() {
                    if (dom) {
                        dom.style[attr] = restore;
                    }
                    event = lns.afteranimate;
                    if (event) {
                        fn = event.fn || event;
                        fn.apply(event.scope || lns.scope || WIN, arguments);
                    }
                }
            });
            me.animate(Ext.apply({}, o, {
                duration: 1000,
                easing: 'ease-in',
                from: from,
                to: to
            }));
            return me;
        },
        
        hover: function(overFn, outFn, scope, options) {
            var me = this;
            me.on('mouseenter', overFn, scope || me.dom, options);
            me.on('mouseleave', outFn, scope || me.dom, options);
            return me;
        },
        
        initDD: function(group, config, overrides) {
            var dd = new Ext.dd.DD(Ext.id(this.dom),group,config);
            return Ext.apply(dd, overrides);
        },
        
        initDDProxy: function(group, config, overrides) {
            var dd = new Ext.dd.DDProxy(Ext.id(this.dom),group,config);
            return Ext.apply(dd, overrides);
        },
        
        initDDTarget: function(group, config, overrides) {
            var dd = new Ext.dd.DDTarget(Ext.id(this.dom),group,config);
            return Ext.apply(dd, overrides);
        },
        
        isFocusable: function() {
            var dom = this.dom,
                focusable = false,
                nodeName;
            if (dom && !dom.disabled) {
                nodeName = dom.nodeName;
                
                focusable = !!Ext.Element.naturallyFocusableTags[nodeName] || ((nodeName === 'A' || nodeName === 'LINK') && !!dom.href) || dom.getAttribute('tabindex') != null || dom.contentEditable === 'true';
                
                
                if (Ext.isIE8 && nodeName === 'INPUT' && dom.type === 'hidden') {
                    focusable = false;
                }
                
                focusable = focusable && this.isVisible(true);
            }
            return focusable;
        },
        
        isInputField: function() {
            var dom = this.dom,
                contentEditable = dom.contentEditable;
            
            
            
            
            
            if ((inputTags[dom.tagName] && dom.type !== 'button') || (contentEditable === '' || contentEditable === 'true')) {
                return true;
            }
            return false;
        },
        
        isTabbable: function() {
            var dom = this.dom,
                tabbable = false,
                nodeName, hasIndex, tabIndex;
            if (dom && !dom.disabled) {
                nodeName = dom.nodeName;
                
                
                
                tabIndex = dom.getAttribute('tabindex');
                hasIndex = tabIndex != null;
                tabIndex -= 0;
                
                
                if (nodeName === 'A' || nodeName === 'LINK') {
                    if (dom.href) {
                        
                        
                        tabbable = hasIndex && tabIndex < 0 ? false : true;
                    } else 
                    
                    {
                        if (dom.contentEditable === 'true') {
                            tabbable = !hasIndex || (hasIndex && tabIndex >= 0) ? true : false;
                        } else {
                            tabbable = hasIndex && tabIndex >= 0 ? true : false;
                        }
                    }
                }
                
                
                else if (dom.contentEditable === 'true' || Ext.Element.naturallyTabbableTags[nodeName]) {
                    tabbable = hasIndex && tabIndex < 0 ? false : true;
                } else 
                
                {
                    if (hasIndex && tabIndex >= 0) {
                        tabbable = true;
                    }
                }
                
                
                if (Ext.isIE8 && nodeName === 'INPUT' && dom.type === 'hidden') {
                    tabbable = false;
                }
                
                
                
                tabbable = tabbable && (!this.component || this.component.isVisible(true)) && this.isVisible(true);
            }
            return tabbable;
        },
        
        isMasked: function(deep) {
            var me = this,
                data = me.getData(),
                maskEl = data.maskEl,
                maskMsg = data.maskMsg,
                hasMask = false,
                parent;
            if (maskEl && maskEl.isVisible()) {
                if (maskMsg) {
                    maskMsg.center(me);
                }
                hasMask = true;
            } else if (deep) {
                parent = me.findParentNode();
                if (parent) {
                    return Ext.fly(parent).isMasked(deep);
                }
            }
            return hasMask;
        },
        
        isScrollable: function() {
            var dom = this.dom;
            return dom.scrollHeight > dom.clientHeight || dom.scrollWidth > dom.clientWidth;
        },
        
        load: function(options) {
            this.getLoader().load(options);
            return this;
        },
        
        mask: function(msg, msgCls, 
        elHeight) {
            var me = this,
                dom = me.dom,
                data = me.getData(),
                maskEl = data.maskEl,
                maskMsg;
            if (!(bodyRe.test(dom.tagName) && me.getStyle('position') === 'static')) {
                me.addCls(XMASKEDRELATIVE);
            }
            
            if (maskEl) {
                maskEl.destroy();
            }
            maskEl = Ext.DomHelper.append(dom, {
                role: 'presentation',
                cls: Ext.baseCSSPrefix + "mask " + Ext.baseCSSPrefix + "border-box",
                children: {
                    role: 'presentation',
                    cls: msgCls ? EXTELMASKMSG + " " + msgCls : EXTELMASKMSG,
                    cn: {
                        tag: 'div',
                        role: 'presentation',
                        cls: Ext.baseCSSPrefix + 'mask-msg-inner',
                        cn: {
                            tag: 'div',
                            role: 'presentation',
                            cls: Ext.baseCSSPrefix + 'mask-msg-text',
                            html: msg || ''
                        }
                    }
                }
            }, true);
            maskMsg = Ext.get(maskEl.dom.firstChild);
            data.maskEl = maskEl;
            me.addCls(XMASKED);
            maskEl.setDisplayed(true);
            if (typeof msg === 'string') {
                maskMsg.setDisplayed(true);
                maskMsg.center(me);
            } else {
                maskMsg.setDisplayed(false);
            }
            
            if (dom === DOC.body) {
                maskEl.addCls(Ext.baseCSSPrefix + 'mask-fixed');
            } else {
                me.saveTabbableState();
            }
            me.saveChildrenTabbableState();
            
            if (Ext.isIE9m && dom !== DOC.body && me.isStyle('height', 'auto')) {
                maskEl.setSize(undefined, elHeight || me.getHeight());
            }
            return maskEl;
        },
        
        monitorMouseLeave: function(delay, handler, scope) {
            var me = this,
                timer,
                listeners = {
                    mouseleave: function(e) {
                        if (Ext.isIE9m) {
                            e.enableIEAsync();
                        }
                        timer = Ext.defer(handler, delay, scope || me, [
                            e
                        ]);
                    },
                    mouseenter: function() {
                        clearTimeout(timer);
                    }
                };
            me.on(listeners);
            return listeners;
        },
        
        puff: function(obj) {
            var me = this,
                dom = me.dom,
                beforeAnim,
                box = me.getBox(),
                originalStyles = me.getStyle([
                    'width',
                    'height',
                    'left',
                    'right',
                    'top',
                    'bottom',
                    'position',
                    'z-index',
                    'font-size',
                    'opacity'
                ], true);
            obj = Ext.applyIf(obj || {}, {
                easing: 'ease-out',
                duration: 500,
                useDisplay: false
            });
            beforeAnim = function() {
                var el = Ext.fly(dom, '_anim');
                el.clearOpacity();
                el.show();
                this.to = {
                    width: box.width * 2,
                    height: box.height * 2,
                    x: box.x - (box.width / 2),
                    y: box.y - (box.height / 2),
                    opacity: 0,
                    fontSize: '200%'
                };
                this.on('afteranimate', function() {
                    var el = Ext.fly(dom, '_anim');
                    if (el) {
                        if (obj.useDisplay) {
                            el.setDisplayed(false);
                        } else {
                            el.hide();
                        }
                        el.setStyle(originalStyles);
                        Ext.callback(obj.callback, obj.scope);
                    }
                });
            };
            me.animate({
                duration: obj.duration,
                easing: obj.easing,
                listeners: {
                    beforeanimate: {
                        fn: beforeAnim
                    }
                }
            });
            return me;
        },
        
        selectable: function() {
            var me = this;
            
            
            me.dom.unselectable = '';
            me.removeCls(Element.unselectableCls);
            me.addCls(Element.selectableCls);
            return me;
        }, 
        setCapture: function() {
            var dom = this.dom;
            if (Ext.isIE9m && dom.setCapture) {
                dom.setCapture();
            }
        },
        
        setDisplayed: function(value) {
            var me = this;
            if (typeof value === "boolean") {
                value = value ? getDisplay(me) : NONE;
            }
            me.setStyle(DISPLAY, value);
            if (me.shadow || me.shim) {
                me.setUnderlaysVisible(value !== NONE);
            }
            return me;
        },
        
        setHeight: function(height, animate) {
            var me = this;
            if (!animate || !me.anim) {
                me.callParent(arguments);
            } else {
                if (!Ext.isObject(animate)) {
                    animate = {};
                }
                me.animate(Ext.applyIf({
                    to: {
                        height: height
                    }
                }, animate));
            }
            return me;
        },
        
        setHorizontal: function() {
            var me = this,
                cls = me.verticalCls;
            delete me.vertical;
            if (cls) {
                delete me.verticalCls;
                me.removeCls(cls);
            }
            
            delete me.setWidth;
            delete me.setHeight;
            if (!Ext.isIE8) {
                delete me.getWidth;
                delete me.getHeight;
            }
            
            delete me.styleHooks;
        },
        
        updateText: function(text) {
            var me = this,
                dom, textNode;
            if (dom) {
                textNode = dom.firstChild;
                if (!textNode || (textNode.nodeType !== 3 || textNode.nextSibling)) {
                    textNode = DOC.createTextNode();
                    me.empty();
                    dom.appendChild(textNode);
                }
                if (text) {
                    textNode.data = text;
                }
            }
        },
        
        setHtml: function(html, loadScripts, callback) {
            var me = this,
                id, dom, interval;
            if (!me.dom) {
                return me;
            }
            html = html || '';
            dom = me.dom;
            if (loadScripts !== true) {
                dom.innerHTML = html;
                Ext.callback(callback, me);
                return me;
            }
            id = Ext.id();
            html += '<span id="' + id + '" role="presentation"></span>';
            interval = Ext.interval(function() {
                var hd, match, attrs, srcMatch, typeMatch, el, s;
                if (!(el = DOC.getElementById(id))) {
                    return false;
                }
                clearInterval(interval);
                Ext.removeNode(el);
                hd = Ext.getHead().dom;
                while ((match = scriptTagRe.exec(html))) {
                    attrs = match[1];
                    srcMatch = attrs ? attrs.match(srcRe) : false;
                    if (srcMatch && srcMatch[2]) {
                        s = DOC.createElement("script");
                        s.src = srcMatch[2];
                        typeMatch = attrs.match(typeRe);
                        if (typeMatch && typeMatch[2]) {
                            s.type = typeMatch[2];
                        }
                        hd.appendChild(s);
                    } else if (match[2] && match[2].length > 0) {
                        if (WIN.execScript) {
                            WIN.execScript(match[2]);
                        } else {
                            WIN.eval(match[2]);
                        }
                    }
                }
                Ext.callback(callback, me);
            }, 20);
            dom.innerHTML = html.replace(replaceScriptTagRe, '');
            return me;
        },
        
        setOpacity: function(opacity, animate) {
            var me = this;
            if (!me.dom) {
                return me;
            }
            if (!animate || !me.anim) {
                me.setStyle('opacity', opacity);
            } else {
                if (typeof animate != 'object') {
                    animate = {
                        duration: 350,
                        easing: 'ease-in'
                    };
                }
                me.animate(Ext.applyIf({
                    to: {
                        opacity: opacity
                    }
                }, animate));
            }
            return me;
        },
        
        setPositioning: function(pc) {
            return this.setStyle(pc);
        },
        
        setVertical: function(angle, cls) {
            var me = this,
                proto = Element.prototype;
            me.vertical = true;
            if (cls) {
                me.addCls(me.verticalCls = cls);
            }
            me.setWidth = proto.setHeight;
            me.setHeight = proto.setWidth;
            if (!Ext.isIE8) {
                
                
                
                
                me.getWidth = proto.getHeight;
                me.getHeight = proto.getWidth;
            }
            
            me.styleHooks = (angle === 270) ? proto.verticalStyleHooks270 : proto.verticalStyleHooks90;
        },
        
        setSize: function(width, height, animate) {
            var me = this;
            if (Ext.isObject(width)) {
                
                animate = height;
                height = width.height;
                width = width.width;
            }
            if (!animate || !me.anim) {
                me.dom.style.width = Element.addUnits(width);
                me.dom.style.height = Element.addUnits(height);
                if (me.shadow || me.shim) {
                    me.syncUnderlays();
                }
            } else {
                if (animate === true) {
                    animate = {};
                }
                me.animate(Ext.applyIf({
                    to: {
                        width: width,
                        height: height
                    }
                }, animate));
            }
            return me;
        },
        
        setVisible: function(visible, animate) {
            var me = this,
                dom = me.dom,
                visMode = getVisMode(me);
            
            if (typeof animate === 'string') {
                switch (animate) {
                    case DISPLAY:
                        visMode = Element.DISPLAY;
                        break;
                    case VISIBILITY:
                        visMode = Element.VISIBILITY;
                        break;
                    case OFFSETS:
                        visMode = Element.OFFSETS;
                        break;
                }
                me.setVisibilityMode(visMode);
                animate = false;
            }
            if (!animate || !me.anim) {
                if (visMode === Element.DISPLAY) {
                    return me.setDisplayed(visible);
                } else if (visMode === Element.OFFSETS) {
                    me[visible ? 'removeCls' : 'addCls'](OFFSETCLASS);
                } else if (visMode === Element.VISIBILITY) {
                    me.fixDisplay();
                    
                    dom.style.visibility = visible ? '' : HIDDEN;
                }
            } else {
                
                if (visible) {
                    me.setOpacity(0.01);
                    me.setVisible(true);
                }
                if (!Ext.isObject(animate)) {
                    animate = {
                        duration: 350,
                        easing: 'ease-in'
                    };
                }
                me.animate(Ext.applyIf({
                    callback: function() {
                        if (!visible) {
                            
                            Ext.fly(dom).setVisible(false).setOpacity(1);
                        }
                    },
                    to: {
                        opacity: (visible) ? 1 : 0
                    }
                }, animate));
            }
            me.getData()[ISVISIBLE] = visible;
            if (me.shadow || me.shim) {
                me.setUnderlaysVisible(visible);
            }
            return me;
        },
        
        setWidth: function(width, animate) {
            var me = this;
            if (!animate || !me.anim) {
                me.callParent(arguments);
            } else {
                if (!Ext.isObject(animate)) {
                    animate = {};
                }
                me.animate(Ext.applyIf({
                    to: {
                        width: width
                    }
                }, animate));
            }
            return me;
        },
        setX: function(x, animate) {
            return this.setXY([
                x,
                this.getY()
            ], animate);
        },
        setXY: function(xy, animate) {
            var me = this;
            if (!animate || !me.anim) {
                me.callParent([
                    xy
                ]);
            } else {
                if (!Ext.isObject(animate)) {
                    animate = {};
                }
                me.animate(Ext.applyIf({
                    to: {
                        x: xy[0],
                        y: xy[1]
                    }
                }, animate));
            }
            return this;
        },
        setY: function(y, animate) {
            return this.setXY([
                this.getX(),
                y
            ], animate);
        },
        
        show: function(animate) {
            
            if (typeof animate === 'string') {
                this.setVisible(true, animate);
                return this;
            }
            this.setVisible(true, this.anim(animate));
            return this;
        },
        
        slideIn: function(anchor, obj, slideOut) {
            var me = this,
                dom = me.dom,
                elStyle = dom.style,
                beforeAnim, wrapAnim, restoreScroll, wrapDomParentNode;
            anchor = anchor || "t";
            obj = obj || {};
            beforeAnim = function() {
                var animScope = this,
                    listeners = obj.listeners,
                    el = Ext.fly(dom, '_anim'),
                    box, originalStyles, anim, wrap;
                if (!slideOut) {
                    el.fixDisplay();
                }
                box = el.getBox();
                if ((anchor == 't' || anchor == 'b') && box.height === 0) {
                    box.height = dom.scrollHeight;
                } else if ((anchor == 'l' || anchor == 'r') && box.width === 0) {
                    box.width = dom.scrollWidth;
                }
                originalStyles = el.getStyle([
                    'width',
                    'height',
                    'left',
                    'right',
                    'top',
                    'bottom',
                    'position',
                    'z-index'
                ], true);
                el.setSize(box.width, box.height);
                
                if (obj.preserveScroll) {
                    restoreScroll = el.cacheScrollValues();
                }
                wrap = el.wrap({
                    role: 'presentation',
                    id: Ext.id() + '-anim-wrap-for-' + el.dom.id,
                    style: {
                        visibility: slideOut ? 'visible' : 'hidden'
                    }
                });
                wrapDomParentNode = wrap.dom.parentNode;
                wrap.setPositioning(el.getPositioning(true));
                if (wrap.isStyle('position', 'static')) {
                    wrap.position('relative');
                }
                el.clearPositioning('auto');
                wrap.clip();
                
                if (restoreScroll) {
                    restoreScroll();
                }
                
                
                
                el.setStyle({
                    visibility: '',
                    position: 'absolute'
                });
                if (slideOut) {
                    wrap.setSize(box.width, box.height);
                }
                switch (anchor) {
                    case 't':
                        anim = {
                            from: {
                                width: box.width + 'px',
                                height: '0px'
                            },
                            to: {
                                width: box.width + 'px',
                                height: box.height + 'px'
                            }
                        };
                        elStyle.bottom = '0px';
                        break;
                    case 'l':
                        anim = {
                            from: {
                                width: '0px',
                                height: box.height + 'px'
                            },
                            to: {
                                width: box.width + 'px',
                                height: box.height + 'px'
                            }
                        };
                        me.anchorAnimX(anchor);
                        break;
                    case 'r':
                        anim = {
                            from: {
                                x: box.x + box.width,
                                width: '0px',
                                height: box.height + 'px'
                            },
                            to: {
                                x: box.x,
                                width: box.width + 'px',
                                height: box.height + 'px'
                            }
                        };
                        me.anchorAnimX(anchor);
                        break;
                    case 'b':
                        anim = {
                            from: {
                                y: box.y + box.height,
                                width: box.width + 'px',
                                height: '0px'
                            },
                            to: {
                                y: box.y,
                                width: box.width + 'px',
                                height: box.height + 'px'
                            }
                        };
                        break;
                    case 'tl':
                        anim = {
                            from: {
                                x: box.x,
                                y: box.y,
                                width: '0px',
                                height: '0px'
                            },
                            to: {
                                width: box.width + 'px',
                                height: box.height + 'px'
                            }
                        };
                        elStyle.bottom = '0px';
                        me.anchorAnimX('l');
                        break;
                    case 'bl':
                        anim = {
                            from: {
                                y: box.y + box.height,
                                width: '0px',
                                height: '0px'
                            },
                            to: {
                                y: box.y,
                                width: box.width + 'px',
                                height: box.height + 'px'
                            }
                        };
                        me.anchorAnimX('l');
                        break;
                    case 'br':
                        anim = {
                            from: {
                                x: box.x + box.width,
                                y: box.y + box.height,
                                width: '0px',
                                height: '0px'
                            },
                            to: {
                                x: box.x,
                                y: box.y,
                                width: box.width + 'px',
                                height: box.height + 'px'
                            }
                        };
                        me.anchorAnimX('r');
                        break;
                    case 'tr':
                        anim = {
                            from: {
                                x: box.x + box.width,
                                width: '0px',
                                height: '0px'
                            },
                            to: {
                                x: box.x,
                                width: box.width + 'px',
                                height: box.height + 'px'
                            }
                        };
                        elStyle.bottom = '0px';
                        me.anchorAnimX('r');
                        break;
                }
                wrap.show();
                wrapAnim = Ext.apply({}, obj);
                delete wrapAnim.listeners;
                wrapAnim = new Ext.fx.Anim(Ext.applyIf(wrapAnim, {
                    target: wrap,
                    duration: 500,
                    easing: 'ease-out',
                    from: slideOut ? anim.to : anim.from,
                    to: slideOut ? anim.from : anim.to
                }));
                
                wrapAnim.on('afteranimate', function() {
                    var el = Ext.fly(dom, '_anim');
                    el.setStyle(originalStyles);
                    if (slideOut) {
                        if (obj.useDisplay) {
                            el.setDisplayed(false);
                        } else {
                            el.hide();
                        }
                    }
                    if (wrap.dom) {
                        if (wrap.dom.parentNode) {
                            wrap.dom.parentNode.insertBefore(el.dom, wrap.dom);
                        } else {
                            wrapDomParentNode.appendChild(el.dom);
                        }
                        wrap.destroy();
                    }
                    
                    if (restoreScroll) {
                        restoreScroll();
                    }
                    
                    animScope.end();
                });
                
                if (listeners) {
                    wrapAnim.on(listeners);
                }
            };
            me.animate({
                
                duration: obj.duration ? Math.max(obj.duration, 500) * 2 : 1000,
                listeners: {
                    beforeanimate: beforeAnim
                }
            });
            
            return me;
        },
        
        slideOut: function(anchor, o) {
            return this.slideIn(anchor, o, true);
        },
        
        swallowEvent: function(eventName, preventDefault) {
            var me = this,
                e, eLen,
                fn = function(e) {
                    e.stopPropagation();
                    if (preventDefault) {
                        e.preventDefault();
                    }
                };
            if (Ext.isArray(eventName)) {
                eLen = eventName.length;
                for (e = 0; e < eLen; e++) {
                    me.on(eventName[e], fn);
                }
                return me;
            }
            me.on(eventName, fn);
            return me;
        },
        
        switchOff: function(obj) {
            var me = this,
                dom = me.dom,
                beforeAnim;
            obj = Ext.applyIf(obj || {}, {
                easing: 'ease-in',
                duration: 500,
                remove: false,
                useDisplay: false
            });
            beforeAnim = function() {
                var el = Ext.fly(dom, '_anim'),
                    animScope = this,
                    size = el.getSize(),
                    xy = el.getXY(),
                    keyframe, position;
                el.clearOpacity();
                el.clip();
                position = el.getPositioning();
                keyframe = new Ext.fx.Animator({
                    target: dom,
                    duration: obj.duration,
                    easing: obj.easing,
                    keyframes: {
                        33: {
                            opacity: 0.3
                        },
                        66: {
                            height: 1,
                            y: xy[1] + size.height / 2
                        },
                        100: {
                            width: 1,
                            x: xy[0] + size.width / 2
                        }
                    }
                });
                keyframe.on('afteranimate', function() {
                    var el = Ext.fly(dom, '_anim');
                    if (obj.useDisplay) {
                        el.setDisplayed(false);
                    } else {
                        el.hide();
                    }
                    el.clearOpacity();
                    el.setPositioning(position);
                    el.setSize(size);
                    
                    animScope.end();
                });
            };
            me.animate({
                
                duration: (Math.max(obj.duration, 500) * 2),
                listeners: {
                    beforeanimate: {
                        fn: beforeAnim
                    }
                },
                callback: obj.callback,
                scope: obj.scope
            });
            return me;
        },
        
        syncContent: function(source) {
            source = Ext.getDom(source);
            var sourceNodes = source.childNodes,
                sourceLen = sourceNodes.length,
                dest = this.dom,
                destNodes = dest.childNodes,
                destLen = destNodes.length,
                i, destNode, sourceNode, nodeType, newAttrs, attLen, attName,
                elData = dest._extData;
            
            
            
            
            if (Ext.isIE9m && dest.mergeAttributes) {
                dest.mergeAttributes(source, true);
                
                
                dest.src = source.src;
            } else {
                newAttrs = source.attributes;
                attLen = newAttrs.length;
                for (i = 0; i < attLen; i++) {
                    attName = newAttrs[i].name;
                    if (attName !== 'id') {
                        dest.setAttribute(attName, newAttrs[i].value);
                    }
                }
            }
            
            if (elData) {
                elData.isSynchronized = false;
            }
            
            if (sourceLen !== destLen) {
                dest.innerHTML = source.innerHTML;
                return;
            }
            
            
            for (i = 0; i < sourceLen; i++) {
                sourceNode = sourceNodes[i];
                destNode = destNodes[i];
                nodeType = sourceNode.nodeType;
                
                if (nodeType !== destNode.nodeType || (nodeType === 1 && sourceNode.tagName !== destNode.tagName)) {
                    dest.innerHTML = source.innerHTML;
                    return;
                }
                
                if (nodeType === 3) {
                    destNode.data = sourceNode.data;
                } else 
                {
                    if (sourceNode.id && destNode.id !== sourceNode.id) {
                        destNode.id = sourceNode.id;
                    }
                    destNode.style.cssText = sourceNode.style.cssText;
                    destNode.className = sourceNode.className;
                    Ext.fly(destNode, '_syncContent').syncContent(sourceNode);
                }
            }
        },
        
        toggle: function(animate) {
            var me = this;
            me.setVisible(!me.isVisible(), me.anim(animate));
            return me;
        },
        
        unmask: function() {
            var me = this,
                data = me.getData(),
                maskEl = data.maskEl,
                style;
            if (maskEl) {
                style = maskEl.dom.style;
                
                if (style.clearExpression) {
                    style.clearExpression('width');
                    style.clearExpression('height');
                }
                if (maskEl) {
                    maskEl.destroy();
                    delete data.maskEl;
                }
                me.removeCls([
                    XMASKED,
                    XMASKEDRELATIVE
                ]);
            }
            me.restoreChildrenTabbableState();
            if (me.dom !== DOC.body) {
                me.restoreTabbableState();
            }
        },
        
        unclip: function() {
            var me = this,
                data = me.getData(),
                clip;
            if (data[ISCLIPPED]) {
                data[ISCLIPPED] = false;
                clip = data[ORIGINALCLIP];
                if (clip.o) {
                    me.setStyle(OVERFLOW, clip.o);
                }
                if (clip.x) {
                    me.setStyle(OVERFLOWX, clip.x);
                }
                if (clip.y) {
                    me.setStyle(OVERFLOWY, clip.y);
                }
            }
            return me;
        },
        translate: function(x, y, z) {
            if (Ext.supports.CssTransforms && !Ext.isIE9m) {
                this.callParent(arguments);
            } else {
                if (x != null) {
                    this.dom.style.left = x + 'px';
                }
                if (y != null) {
                    this.dom.style.top = y + 'px';
                }
            }
        },
        
        unselectable: function() {
            var me = this;
            if (Ext.isOpera) {
                me.dom.unselectable = 'on';
            } 
            me.removeCls(Element.selectableCls);
            me.addCls(Element.unselectableCls);
            return me;
        },
        privates: {
            
            needsTabIndex: function() {
                var dom = this.dom,
                    nodeName, isFocusable;
                if (dom) {
                    nodeName = dom.nodeName;
                    
                    
                    isFocusable = !!Ext.Element.naturallyFocusableTags[nodeName] || ((nodeName === 'A' || nodeName === 'LINK') && !!dom.href) || dom.getAttribute('tabindex') != null || dom.contentEditable === 'true';
                    
                    return !isFocusable;
                }
            },
            
            findTabbableElements: function(asDom, selector, 
            limit, backward) {
                asDom = asDom != undefined ? asDom : true;
                var me = this,
                    selection;
                selection = me.selectTabbableElements(asDom, selector, limit, backward);
                if (me.isTabbable()) {
                    selection.unshift(asDom ? me.dom : me);
                }
                return selection;
            },
            
            selectTabbableElements: function(asDom, selector, 
            limit, backward) {
                var selection = [],
                    nodes, node, el, i, len, to, step, tabIndex;
                asDom = asDom != undefined ? asDom : true;
                nodes = this.dom.querySelectorAll(selector || Ext.Element.tabbableSelector);
                len = nodes.length;
                if (!len) {
                    return selection;
                }
                if (backward) {
                    i = len - 1;
                    to = 0;
                    step = -1;
                } else {
                    i = 0;
                    to = len - 1;
                    step = 1;
                }
                
                for (; ; i += step) {
                    if ((step > 0 && i > to) || (step < 0 && i < to)) {
                        break;
                    }
                    node = nodes[i];
                    
                    tabIndex = node.getAttribute('tabindex') - 0;
                    if (!(tabIndex < 0)) {
                        el = asDom ? Ext.fly(node) : Ext.get(node);
                        if (el.isTabbable()) {
                            selection.push(asDom ? node : el);
                        }
                    }
                    if (selection.length >= limit) {
                        return selection;
                    }
                }
                return selection;
            },
            
            selectFirstTabbableElement: function(asDom, selector) {
                var els = this.selectTabbableElements(asDom, selector, 1, false);
                return els[0];
            },
            
            selectLastTabbableElement: function(asDom, selector) {
                var el = this.selectTabbableElements(true, selector, 1, true)[0];
                return (asDom !== false) ? el : Ext.get(el);
            },
            
            saveTabbableState: function(attribute) {
                var tabbableSavedFlagAttribute = Ext.Element.tabbableSavedFlagAttribute,
                    dom = this.dom;
                
                if (dom.hasAttribute(tabbableSavedFlagAttribute)) {
                    return;
                }
                attribute = attribute || Ext.Element.tabbableSavedAttribute;
                
                
                if (dom.hasAttribute('tabindex')) {
                    dom.setAttribute(attribute, dom.getAttribute('tabindex'));
                } else 
                {
                    dom.setAttribute(attribute, 'none');
                }
                
                
                dom.setAttribute('tabindex', -1);
                dom.setAttribute(tabbableSavedFlagAttribute, true);
                return this;
            },
            
            restoreTabbableState: function(attribute) {
                var tabbableSavedFlagAttribute = Ext.Element.tabbableSavedFlagAttribute,
                    dom = this.dom,
                    idx;
                attribute = attribute || Ext.Element.tabbableSavedAttribute;
                if (!dom.hasAttribute(tabbableSavedFlagAttribute) || !dom.hasAttribute(attribute)) {
                    return;
                }
                idx = dom.getAttribute(attribute);
                
                if (idx === 'none') {
                    dom.removeAttribute('tabindex');
                } else {
                    dom.setAttribute('tabindex', idx);
                }
                dom.removeAttribute(attribute);
                dom.removeAttribute(tabbableSavedFlagAttribute);
                return this;
            },
            
            saveChildrenTabbableState: function(attribute) {
                var children, child, i, len;
                if (this.dom) {
                    children = this.selectTabbableElements();
                    for (i = 0 , len = children.length; i < len; i++) {
                        child = Ext.fly(children[i]);
                        child.saveTabbableState(attribute);
                    }
                }
                return children;
            },
            
            restoreChildrenTabbableState: function(attribute, children) {
                var child, i, len;
                if (this.dom) {
                    attribute = attribute || Ext.Element.tabbableSavedAttribute;
                    children = children || this.dom.querySelectorAll('[' + attribute + ']');
                    for (i = 0 , len = children.length; i < len; i++) {
                        child = Ext.fly(children[i]);
                        child.restoreTabbableState(attribute);
                    }
                }
                return children;
            }
        },
        deprecated: {
            '4.0': {
                methods: {
                    
                    pause: function(ms) {
                        var me = this;
                        Ext.fx.Manager.setFxDefaults(me.id, {
                            delay: ms
                        });
                        return me;
                    },
                    
                    scale: function(w, h, o) {
                        this.animate(Ext.apply({}, o, {
                            width: w,
                            height: h
                        }));
                        return this;
                    },
                    
                    shift: function(config) {
                        this.animate(config);
                        return this;
                    }
                }
            },
            '4.2': {
                methods: {
                    
                    moveTo: function(x, y, animate) {
                        return this.setXY([
                            x,
                            y
                        ], animate);
                    },
                    
                    setBounds: function(x, y, width, height, animate) {
                        return this.setBox({
                            x: x,
                            y: y,
                            width: width,
                            height: height
                        }, animate);
                    },
                    
                    setLeftTop: function(left, top) {
                        var me = this,
                            style = me.dom.style;
                        style.left = Element.addUnits(left);
                        style.top = Element.addUnits(top);
                        if (me.shadow || me.shim) {
                            me.syncUnderlays();
                        }
                        return me;
                    },
                    
                    setLocation: function(x, y, animate) {
                        return this.setXY([
                            x,
                            y
                        ], animate);
                    }
                }
            },
            '5.0': {
                methods: {
                    
                    getAttributeNS: function(namespace, name) {
                        return this.getAttribute(name, namespace);
                    },
                    
                    getCenterXY: function() {
                        return this.getAlignToXY(DOC, 'c-c');
                    },
                    
                    getComputedHeight: function() {
                        return Math.max(this.dom.offsetHeight, this.dom.clientHeight) || parseFloat(this.getStyle(HEIGHT)) || 0;
                    },
                    
                    getComputedWidth: function() {
                        return Math.max(this.dom.offsetWidth, this.dom.clientWidth) || parseFloat(this.getStyle(WIDTH)) || 0;
                    },
                    
                    getStyleSize: function() {
                        var me = this,
                            d = this.dom,
                            isDoc = (d === DOC || d === DOC.body),
                            s, w, h;
                        
                        if (isDoc) {
                            return {
                                width: Element.getViewportWidth(),
                                height: Element.getViewportHeight()
                            };
                        }
                        s = me.getStyle([
                            'height',
                            'width'
                        ], true);
                        
                        
                        if (s.width && s.width !== 'auto') {
                            w = parseFloat(s.width);
                        }
                        
                        if (s.height && s.height !== 'auto') {
                            h = parseFloat(s.height);
                        }
                        
                        return {
                            width: w || me.getWidth(true),
                            height: h || me.getHeight(true)
                        };
                    },
                    
                    isBorderBox: function() {
                        return true;
                    },
                    
                    isDisplayed: function() {
                        return !this.isStyle('display', 'none');
                    },
                    
                    focusable: 'isFocusable'
                }
            }
        }
    };
})(), function() {
    var Element = Ext.dom.Element,
        proto = Element.prototype,
        useDocForId = !Ext.isIE8,
        DOC = document,
        view = DOC.defaultView,
        opacityRe = /alpha\(opacity=(.*)\)/i,
        trimRe = /^\s+|\s+$/g,
        styleHooks = proto.styleHooks,
        supports = Ext.supports,
        removeNode, garbageBin, verticalStyleHooks90, verticalStyleHooks270, edges, k, edge, borderWidth;
    proto._init(Element);
    delete proto._init;
    Ext.plainTableCls = Ext.baseCSSPrefix + 'table-plain';
    Ext.plainListCls = Ext.baseCSSPrefix + 'list-plain';
    
    if (Ext.CompositeElementLite) {
        Ext.CompositeElementLite.importElementMethods();
    }
    styleHooks.opacity = {
        name: 'opacity',
        afterSet: function(dom, value, el) {
            var shadow = el.shadow;
            if (shadow) {
                shadow.setOpacity(value);
            }
        }
    };
    if (!supports.Opacity && Ext.isIE) {
        Ext.apply(styleHooks.opacity, {
            get: function(dom) {
                var filter = dom.style.filter,
                    match, opacity;
                if (filter.match) {
                    match = filter.match(opacityRe);
                    if (match) {
                        opacity = parseFloat(match[1]);
                        if (!isNaN(opacity)) {
                            return opacity ? opacity / 100 : 0;
                        }
                    }
                }
                return 1;
            },
            set: function(dom, value) {
                var style = dom.style,
                    val = style.filter.replace(opacityRe, '').replace(trimRe, '');
                style.zoom = 1;
                
                
                if (typeof (value) === 'number' && value >= 0 && value < 1) {
                    value *= 100;
                    style.filter = val + (val.length ? ' ' : '') + 'alpha(opacity=' + value + ')';
                } else {
                    style.filter = val;
                }
            }
        });
    }
    if (!supports.matchesSelector) {
        
        var simpleSelectorRe = /^([a-z]+|\*)?(?:\.([a-z][a-z\-_0-9]*))?$/i,
            dashRe = /\-/g,
            fragment,
            classMatcher = function(tag, cls) {
                var classRe = new RegExp('(?:^|\\s+)' + cls.replace(dashRe, '\\-') + '(?:\\s+|$)');
                if (tag && tag !== '*') {
                    tag = tag.toUpperCase();
                    return function(el) {
                        return el.tagName === tag && classRe.test(el.className);
                    };
                }
                return function(el) {
                    return classRe.test(el.className);
                };
            },
            tagMatcher = function(tag) {
                tag = tag.toUpperCase();
                return function(el) {
                    return el.tagName === tag;
                };
            },
            cache = {};
        proto.matcherCache = cache;
        proto.is = function(selector) {
            
            if (!selector) {
                return true;
            }
            var dom = this.dom,
                cls, match, testFn, root, isOrphan, is, tag;
            
            if (dom.nodeType !== 1) {
                return false;
            }
            if (!(testFn = Ext.isFunction(selector) ? selector : cache[selector])) {
                if (!(match = selector.match(simpleSelectorRe))) {
                    
                    root = dom.parentNode;
                    if (!root) {
                        isOrphan = true;
                        root = fragment || (fragment = DOC.createDocumentFragment());
                        fragment.appendChild(dom);
                    }
                    is = Ext.Array.indexOf(Ext.fly(root, '_is').query(selector), dom) !== -1;
                    if (isOrphan) {
                        fragment.removeChild(dom);
                    }
                    return is;
                }
                tag = match[1];
                cls = match[2];
                cache[selector] = testFn = cls ? classMatcher(tag, cls) : tagMatcher(tag);
            }
            return testFn(dom);
        };
    }
    
    if (!view || !view.getComputedStyle) {
        proto.getStyle = function(property, inline) {
            var me = this,
                dom = me.dom,
                multiple = typeof property !== 'string',
                prop = property,
                props = prop,
                len = 1,
                isInline = inline,
                styleHooks = me.styleHooks,
                camel, domStyle, values, hook, out, style, i;
            if (multiple) {
                values = {};
                prop = props[0];
                i = 0;
                if (!(len = props.length)) {
                    return values;
                }
            }
            if (!dom || dom.documentElement) {
                return values || '';
            }
            domStyle = dom.style;
            if (inline) {
                style = domStyle;
            } else {
                style = dom.currentStyle;
                
                if (!style) {
                    isInline = true;
                    style = domStyle;
                }
            }
            do {
                hook = styleHooks[prop];
                if (!hook) {
                    styleHooks[prop] = hook = {
                        name: Element.normalize(prop)
                    };
                }
                if (hook.get) {
                    out = hook.get(dom, me, isInline, style);
                } else {
                    camel = hook.name;
                    out = style[camel];
                }
                if (!multiple) {
                    return out;
                }
                values[prop] = out;
                prop = props[++i];
            } while (i < len);
            return values;
        };
    }
    
    if (Ext.isIE8) {
        function getBorderWidth(dom, el, inline, style) {
            if (style[this.styleName] === 'none') {
                return '0px';
            }
            return style[this.name];
        }
        edges = [
            'Top',
            'Right',
            'Bottom',
            'Left'
        ];
        k = edges.length;
        while (k--) {
            edge = edges[k];
            borderWidth = 'border' + edge + 'Width';
            styleHooks['border-' + edge.toLowerCase() + '-width'] = styleHooks[borderWidth] = {
                name: borderWidth,
                styleName: 'border' + edge + 'Style',
                get: getBorderWidth
            };
        }
    }
    Ext.apply(Ext, {
        
        enableGarbageCollector: true,
        
        
        isBorderBox: true,
        
        useShims: false,
        
        getDetachedBody: function() {
            var detachedEl = Ext.detachedBodyEl;
            if (!detachedEl) {
                detachedEl = DOC.createElement('div');
                Ext.detachedBodyEl = detachedEl = new Ext.dom.Fly(detachedEl);
                detachedEl.isDetachedBody = true;
            }
            return detachedEl;
        },
        getElementById: function(id) {
            var el = DOC.getElementById(id),
                detachedBodyEl;
            if (!el && (detachedBodyEl = Ext.detachedBodyEl)) {
                el = detachedBodyEl.dom.querySelector(Ext.makeIdSelector(id));
            }
            return el;
        },
        
        addBehaviors: function(o) {
            if (!Ext.isReady) {
                Ext.onReady(function() {
                    Ext.addBehaviors(o);
                });
            } else {
                var cache = {},
                    
                    parts, b, s;
                for (b in o) {
                    if ((parts = b.split('@'))[1]) {
                        
                        s = parts[0];
                        if (!cache[s]) {
                            cache[s] = Ext.fly(document).select(s, true);
                        }
                        cache[s].on(parts[1], o[b]);
                    }
                }
                cache = null;
            }
        }
    });
    if (Ext.isIE8) {
        
        removeNode = Ext.removeNode;
        Ext.removeNode = function(node) {
            removeNode(node);
            garbageBin = garbageBin || DOC.createElement('div');
            garbageBin.appendChild(node);
            garbageBin.innerHTML = '';
        };
    }
    if (Ext.isIE9m) {
        Ext.getElementById = function(id) {
            var el = DOC.getElementById(id),
                detachedBodyEl;
            if (!el && (detachedBodyEl = Ext.detachedBodyEl)) {
                el = detachedBodyEl.dom.all[id];
            }
            return el;
        };
        proto.getById = function(id, asDom) {
            var dom = this.dom,
                ret = null,
                entry, el;
            if (dom) {
                
                
                el = (useDocForId && DOC.getElementById(id)) || dom.all[id];
                if (el) {
                    if (asDom) {
                        ret = el;
                    } else {
                        
                        
                        entry = Ext.cache[id];
                        if (entry) {
                            if (entry.skipGarbageCollection || !Ext.isGarbage(entry.dom)) {
                                ret = entry;
                            } else {
                                Ext.Error.raise("Stale Element with id '" + el.id + "' found in Element cache. " + "Make sure to clean up Element instances using destroy()");
                                entry.destroy();
                            }
                        }
                        ret = ret || new Ext.Element(el);
                    }
                }
            }
            return ret;
        };
    } else if (!DOC.querySelector) {
        Ext.getDetachedBody = Ext.getBody;
        Ext.getElementById = function(id) {
            return DOC.getElementById(id);
        };
        proto.getById = function(id, asDom) {
            var dom = DOC.getElementById(id);
            return asDom ? dom : (dom ? Ext.get(dom) : null);
        };
    }
    if (Ext.isIE && !(Ext.isIE9p && DOC.documentMode >= 9)) {
        proto.getAttribute = function(name, ns) {
            var d = this.dom,
                type;
            if (ns) {
                type = typeof d[ns + ":" + name];
                if (type != 'undefined' && type != 'unknown') {
                    return d[ns + ":" + name] || null;
                }
                return null;
            }
            if (name === "for") {
                name = "htmlFor";
            }
            return d[name] || null;
        };
    }
    Ext.onReady(function() {
        var transparentRe = /^(?:transparent|(?:rgba[(](?:\s*\d+\s*[,]){3}\s*0\s*[)]))$/i,
            bodyCls = [],
            
            origSetWidth = proto.setWidth,
            origSetHeight = proto.setHeight,
            origSetSize = proto.setSize,
            pxRe = /^\d+(?:\.\d*)?px$/i,
            colorStyles, i, name, camel;
        if (supports.FixedTableWidthBug) {
            styleHooks.width = {
                name: 'width',
                set: function(dom, value, el) {
                    var style = dom.style,
                        needsFix = el._needsTableWidthFix,
                        origDisplay = style.display;
                    if (needsFix) {
                        style.display = 'none';
                    }
                    style.width = value;
                    if (needsFix) {
                        dom.scrollWidth;
                        
                        style.display = origDisplay;
                    }
                }
            };
            proto.setWidth = function(width, animate) {
                var me = this,
                    dom = me.dom,
                    style = dom.style,
                    needsFix = me._needsTableWidthFix,
                    origDisplay = style.display;
                if (needsFix && !animate) {
                    style.display = 'none';
                }
                origSetWidth.call(me, width, animate);
                if (needsFix && !animate) {
                    dom.scrollWidth;
                    
                    style.display = origDisplay;
                }
                return me;
            };
            proto.setSize = function(width, height, animate) {
                var me = this,
                    dom = me.dom,
                    style = dom.style,
                    needsFix = me._needsTableWidthFix,
                    origDisplay = style.display;
                if (needsFix && !animate) {
                    style.display = 'none';
                }
                origSetSize.call(me, width, height, animate);
                if (needsFix && !animate) {
                    dom.scrollWidth;
                    
                    style.display = origDisplay;
                }
                return me;
            };
        }
        if (Ext.isIE8) {
            styleHooks.height = {
                name: 'height',
                set: function(dom, value, el) {
                    var component = el.component,
                        frameInfo, frameBodyStyle;
                    if (component && component._syncFrameHeight && this === component.el) {
                        frameBodyStyle = component.frameBody.dom.style;
                        if (pxRe.test(value)) {
                            frameInfo = component.getFrameInfo();
                            if (frameInfo) {
                                frameBodyStyle.height = (parseInt(value, 10) - frameInfo.height) + 'px';
                            }
                        } else if (!value || value === 'auto') {
                            frameBodyStyle.height = '';
                        }
                    }
                    dom.style.height = value;
                }
            };
            proto.setHeight = function(height, animate) {
                var component = this.component,
                    frameInfo, frameBodyStyle;
                if (component && component._syncFrameHeight && this === component.el) {
                    frameBodyStyle = component.frameBody.dom.style;
                    if (!height || height === 'auto') {
                        frameBodyStyle.height = '';
                    } else {
                        frameInfo = component.getFrameInfo();
                        if (frameInfo) {
                            frameBodyStyle.height = (height - frameInfo.height) + 'px';
                        }
                    }
                }
                return origSetHeight.call(this, height, animate);
            };
            proto.setSize = function(width, height, animate) {
                var component = this.component,
                    frameInfo, frameBodyStyle;
                if (component && component._syncFrameHeight && this === component.el) {
                    frameBodyStyle = component.frameBody.dom.style;
                    if (!height || height === 'auto') {
                        frameBodyStyle.height = '';
                    } else {
                        frameInfo = component.getFrameInfo();
                        if (frameInfo) {
                            frameBodyStyle.height = (height - frameInfo.height) + 'px';
                        }
                    }
                }
                return origSetSize.call(this, width, height, animate);
            };
        }
        
        
        
        Ext.getDoc().on('selectstart', function(ev, dom) {
            var selectableCls = Element.selectableCls,
                unselectableCls = Element.unselectableCls,
                tagName = dom && dom.tagName;
            tagName = tagName && tagName.toLowerCase();
            
            
            
            if (tagName === 'input' || tagName === 'textarea') {
                return;
            }
            
            while (dom && dom.nodeType === 1 && dom !== DOC.documentElement) {
                var el = Ext.fly(dom);
                
                if (el.hasCls(selectableCls)) {
                    return;
                }
                
                if (el.hasCls(unselectableCls)) {
                    ev.stopEvent();
                    return;
                }
                dom = dom.parentNode;
            }
        });
        function fixTransparent(dom, el, inline, style) {
            var value = style[this.name] || '';
            return transparentRe.test(value) ? 'transparent' : value;
        }
        
        function makeSelectionRestoreFn(activeEl, start, end) {
            return function() {
                activeEl.selectionStart = start;
                activeEl.selectionEnd = end;
            };
        }
        
        function getRightMarginFixCleaner(target) {
            var hasInputBug = supports.DisplayChangeInputSelectionBug,
                hasTextAreaBug = supports.DisplayChangeTextAreaSelectionBug,
                activeEl, tag, start, end;
            if (hasInputBug || hasTextAreaBug) {
                activeEl = Element.getActiveElement();
                tag = activeEl && activeEl.tagName;
                if ((hasTextAreaBug && tag === 'TEXTAREA') || (hasInputBug && tag === 'INPUT' && activeEl.type === 'text')) {
                    if (Ext.fly(target).isAncestor(activeEl)) {
                        start = activeEl.selectionStart;
                        end = activeEl.selectionEnd;
                        if (Ext.isNumber(start) && Ext.isNumber(end)) {
                            return makeSelectionRestoreFn(activeEl, start, end);
                        }
                    }
                }
            }
            return Ext.emptyFn;
        }
        
        function fixRightMargin(dom, el, inline, style) {
            var result = style.marginRight,
                domStyle, display;
            
            
            if (result !== '0px') {
                domStyle = dom.style;
                display = domStyle.display;
                domStyle.display = 'inline-block';
                result = (inline ? style : dom.ownerDocument.defaultView.getComputedStyle(dom, null)).marginRight;
                domStyle.display = display;
            }
            return result;
        }
        function fixRightMarginAndInputFocus(dom, el, inline, style) {
            var result = style.marginRight,
                domStyle, cleaner, display;
            if (result !== '0px') {
                domStyle = dom.style;
                cleaner = getRightMarginFixCleaner(dom);
                display = domStyle.display;
                domStyle.display = 'inline-block';
                result = (inline ? style : dom.ownerDocument.defaultView.getComputedStyle(dom, '')).marginRight;
                domStyle.display = display;
                cleaner();
            }
            return result;
        }
        
        if (!supports.RightMargin) {
            styleHooks.marginRight = styleHooks['margin-right'] = {
                name: 'marginRight',
                
                
                get: (supports.DisplayChangeInputSelectionBug || supports.DisplayChangeTextAreaSelectionBug) ? fixRightMarginAndInputFocus : fixRightMargin
            };
        }
        if (!supports.TransparentColor) {
            colorStyles = [
                'background-color',
                'border-color',
                'color',
                'outline-color'
            ];
            for (i = colorStyles.length; i--; ) {
                name = colorStyles[i];
                camel = Element.normalize(name);
                styleHooks[name] = styleHooks[camel] = {
                    name: camel,
                    get: fixTransparent
                };
            }
        }
        
        
        proto.verticalStyleHooks90 = verticalStyleHooks90 = Ext.Object.chain(styleHooks);
        proto.verticalStyleHooks270 = verticalStyleHooks270 = Ext.Object.chain(styleHooks);
        verticalStyleHooks90.width = styleHooks.height || {
            name: 'height'
        };
        verticalStyleHooks90.height = styleHooks.width || {
            name: 'width'
        };
        verticalStyleHooks90['margin-top'] = {
            name: 'marginLeft'
        };
        verticalStyleHooks90['margin-right'] = {
            name: 'marginTop'
        };
        verticalStyleHooks90['margin-bottom'] = {
            name: 'marginRight'
        };
        verticalStyleHooks90['margin-left'] = {
            name: 'marginBottom'
        };
        verticalStyleHooks90['padding-top'] = {
            name: 'paddingLeft'
        };
        
        if (!Ext.scopeCss) {
            bodyCls.push(Ext.baseCSSPrefix + 'body');
        }
        if (supports.Touch) {
            bodyCls.push(Ext.baseCSSPrefix + 'touch');
        }
        if (Ext.isIE && Ext.isIE9m) {
            bodyCls.push(Ext.baseCSSPrefix + 'ie', Ext.baseCSSPrefix + 'ie9m');
            bodyCls.push(Ext.baseCSSPrefix + 'ie8p');
            if (Ext.isIE8) {
                bodyCls.push(Ext.baseCSSPrefix + 'ie8');
            } else {
                bodyCls.push(Ext.baseCSSPrefix + 'ie9', Ext.baseCSSPrefix + 'ie9p');
            }
            if (Ext.isIE8m) {
                bodyCls.push(Ext.baseCSSPrefix + 'ie8m');
            }
        }
        if (Ext.isIE10) {
            bodyCls.push(Ext.baseCSSPrefix + 'ie10');
        }
        if (Ext.isIE11) {
            bodyCls.push(Ext.baseCSSPrefix + 'ie11');
        }
        if (Ext.isGecko) {
            bodyCls.push(Ext.baseCSSPrefix + 'gecko');
        }
        if (Ext.isOpera) {
            bodyCls.push(Ext.baseCSSPrefix + 'opera');
        }
        if (Ext.isOpera12m) {
            bodyCls.push(Ext.baseCSSPrefix + 'opera12m');
        }
        if (Ext.isWebKit) {
            bodyCls.push(Ext.baseCSSPrefix + 'webkit');
        }
        if (Ext.isSafari) {
            bodyCls.push(Ext.baseCSSPrefix + 'safari');
        }
        if (Ext.isChrome) {
            bodyCls.push(Ext.baseCSSPrefix + 'chrome');
        }
        if (Ext.isMac) {
            bodyCls.push(Ext.baseCSSPrefix + 'mac');
        }
        if (Ext.isLinux) {
            bodyCls.push(Ext.baseCSSPrefix + 'linux');
        }
        if (!supports.CSS3BorderRadius) {
            bodyCls.push(Ext.baseCSSPrefix + 'nbr');
        }
        if (!supports.CSS3LinearGradient) {
            bodyCls.push(Ext.baseCSSPrefix + 'nlg');
        }
        if (supports.Touch) {
            bodyCls.push(Ext.baseCSSPrefix + 'touch');
        }
        
        Ext.getBody().addCls(bodyCls);
    }, null, {
        priority: 1500
    });
});



Ext.define('Ext.overrides.GlobalEvents', {
    override: 'Ext.GlobalEvents',
    attachListeners: function() {
        this.callParent();
        Ext.getDoc().on('mousedown', this.fireMouseDown, this);
    },
    fireMouseDown: function(e) {
        this.fireEvent('mousedown', e);
    },
    deprecated: {
        5: {
            methods: {
                addListener: function(ename, fn, scope, options, order, caller, eventOptions) {
                    var name, readyFn;
                    
                    
                    if (ename === 'ready') {
                        readyFn = fn;
                    } else if (typeof ename !== 'string') {
                        for (name in ename) {
                            if (name === 'ready') {
                                readyFn = ename[name];
                            }
                        }
                    }
                    if (readyFn) {
                        Ext.log.warn("Ext.on('ready', fn) is deprecated.  Please use Ext.onReady(fn) instead.");
                        Ext.onReady(readyFn);
                    }
                    this.callParent([
                        ename,
                        fn,
                        scope,
                        options,
                        order,
                        caller,
                        eventOptions
                    ]);
                }
            }
        }
    }
});

Ext.define('Ext.overrides.Widget', {
    override: 'Ext.Widget',
    uses: [
        'Ext.Component'
    ],
    $configStrict: false,
    isComponent: true,
    liquidLayout: true,
    
    
    
    rendered: true,
    rendering: true,
    cachedConfig: {
        baseCls: Ext.baseCSSPrefix + 'widget'
    },
    constructor: function(config) {
        this.callParent([
            config
        ]);
        
        this.getComponentLayout();
    },
    addCls: function(cls) {
        this.el.addCls(cls);
    },
    addClsWithUI: function(cls) {
        this.el.addCls(cls);
    },
    afterComponentLayout: Ext.emptyFn,
    finishRender: function() {
        this.rendering = false;
        this.initBindable();
    },
    getComponentLayout: function() {
        var me = this,
            layout = me.componentLayout;
        if (!layout) {
            layout = me.componentLayout = new Ext.layout.component.Auto();
            layout.setOwner(me);
        }
        return layout;
    },
    
    getTdCls: function() {
        return Ext.baseCSSPrefix + this.getTdType() + '-' + (this.ui || 'default') + '-cell';
    },
    
    getTdType: function() {
        return this.xtype;
    },
    
    getItemId: function() {
        return this.itemId || this.id;
    },
    getSizeModel: function() {
        return Ext.Component.prototype.getSizeModel.apply(this, arguments);
    },
    onAdded: function(container, pos, instanced) {
        var me = this,
            inheritedState = me.inheritedState;
        me.ownerCt = container;
        
        
        
        if (inheritedState && instanced) {
            me.invalidateInheritedState();
        }
        if (me.reference) {
            me.fixReference();
        }
    },
    onRemoved: function(destroying) {
        var me = this,
            refHolder;
        if (me.reference) {
            refHolder = me.lookupReferenceHolder();
            if (refHolder) {
                refHolder.clearReference(me);
            }
        }
        if (!destroying) {
            me.removeBindings();
        }
        if (me.inheritedState && !destroying) {
            me.invalidateInheritedState();
        }
        me.ownerCt = me.ownerLayout = null;
    },
    parseBox: function(box) {
        return Ext.Element.parseBox(box);
    },
    render: function(container, position) {
        var element = this.element,
            nextSibling;
        if (position) {
            nextSibling = container.childNodes[position];
            if (nextSibling) {
                container.insertBefore(element, nextSibling);
                return;
            }
        }
        container.appendChild(element);
    },
    setPosition: function(x, y) {
        this.el.setLocalXY(x, y);
    },
    up: function() {
        return Ext.Component.prototype.up.apply(this, arguments);
    },
    isAncestor: function() {
        return Ext.Component.prototype.isAncestor.apply(this, arguments);
    },
    onFocusEnter: function() {
        return Ext.Component.prototype.onFocusEnter.apply(this, arguments);
    },
    onFocusLeave: function() {
        return Ext.Component.prototype.onFocusLeave.apply(this, arguments);
    }
}, function() {
    var prototype;
    if (Ext.isIE8) {
        prototype = Ext.Widget.prototype;
        
        
        prototype.addElementReferenceOnDemand = prototype.addElementReference;
    }
});

Ext.define('Ext.overrides.app.domain.Component', {
    override: 'Ext.app.domain.Component',
    requires: [
        'Ext.Component'
    ]
}, function(ComponentDomain) {
    
    
    ComponentDomain.monitor(Ext.Component);
});

Ext.application = function(config) {
    var createApp = function(App) {
            
            Ext.onReady(function() {
                Ext.app.Application.instance = new App();
            });
        },
        paths = config.paths,
        ns;
    if (typeof config === "string") {
        Ext.require(config, function() {
            createApp(Ext.ClassManager.get(config));
        });
    } else {
        config = Ext.apply({
            extend: 'Ext.app.Application'
        }, 
        config);
        
        
        Ext.Loader.setPath(config.name, config.appFolder || 'app');
        if (paths) {
            for (ns in paths) {
                if (paths.hasOwnProperty(ns)) {
                    Ext.Loader.setPath(ns, paths[ns]);
                }
            }
        }
        config['paths processed'] = true;
        
        Ext.define(config.name + ".$application", config, function() {
            createApp(this);
        });
    }
};


Ext.define('Ext.overrides.app.Application', {
    override: 'Ext.app.Application',
    uses: [
        'Ext.tip.QuickTipManager'
    ],
    
    
    autoCreateViewport: false,
    config: {
        
        enableQuickTips: true
    },
    applyMainView: function(value) {
        var view = this.getView(value),
            proto = view.prototype,
            config, plugins;
        if (!proto.isViewport) {
            plugins = proto.plugins;
            
            plugins = [
                'viewport'
            ].concat(plugins ? Ext.Array.from(plugins, true) : []);
            config = {
                plugins: plugins
            };
        }
        return view.create(config);
    },
    getDependencies: function(cls, data, requires) {
        var Controller = Ext.app.Controller,
            proto = cls.prototype,
            namespace = data.$namespace,
            viewportClass = data.autoCreateViewport;
        if (viewportClass) {
            if (!namespace) {
                Ext.Error.raise("[Ext.app.Application] Can't resolve namespace for " + data.$className + ", did you forget to specify 'name' property?");
            }
            if (viewportClass === true) {
                viewportClass = 'Viewport';
            } else {
                requires.push('Ext.plugin.Viewport');
            }
            Controller.processDependencies(proto, requires, namespace, 'view', viewportClass);
        }
    },
    onBeforeLaunch: function() {
        var me = this,
            autoCreateViewport = me.autoCreateViewport;
        if (me.getEnableQuickTips()) {
            me.initQuickTips();
        }
        if (autoCreateViewport) {
            me.initViewport();
        }
        this.callParent(arguments);
    },
    getViewportName: function() {
        var name = null,
            autoCreate = this.autoCreateViewport;
        if (autoCreate) {
            name = (autoCreate === true) ? 'Viewport' : autoCreate;
        }
        return name;
    },
    initViewport: function() {
        this.setMainView(this.getViewportName());
    },
    initQuickTips: function() {
        Ext.tip.QuickTipManager.init();
    }
});

Ext.define('Ext.overrides.dom.Helper', (function() {
    var tableRe = /^(?:table|thead|tbody|tr|td)$/i,
        tableElRe = /td|tr|tbody|thead/i,
        ts = '<table>',
        te = '</table>',
        tbs = ts + '<tbody>',
        tbe = '</tbody>' + te,
        trs = tbs + '<tr>',
        tre = '</tr>' + tbe;
    return {
        override: 'Ext.dom.Helper',
        ieInsertHtml: function(where, el, html) {
            var frag = null;
            
            if (Ext.isIE9m && tableRe.test(el.tagName)) {
                frag = this.insertIntoTable(el.tagName.toLowerCase(), where, el, html);
            }
            return frag;
        },
        ieOverwrite: function(el, html) {
            
            
            if (Ext.isIE9m && tableRe.test(el.tagName)) {
                
                while (el.firstChild) {
                    el.removeChild(el.firstChild);
                }
                if (html) {
                    return this.insertHtml('afterbegin', el, html);
                }
            }
        },
        ieTable: function(depth, openingTags, htmlContent, closingTags) {
            var i = -1,
                el = this.detachedDiv,
                ns, nx;
            el.innerHTML = [
                openingTags,
                htmlContent,
                closingTags
            ].join('');
            while (++i < depth) {
                el = el.firstChild;
            }
            
            ns = el.nextSibling;
            if (ns) {
                ns = el;
                el = document.createDocumentFragment();
                while (ns) {
                    nx = ns.nextSibling;
                    el.appendChild(ns);
                    ns = nx;
                }
            }
            return el;
        },
        
        insertIntoTable: function(tag, where, destinationEl, html) {
            var node, before,
                bb = where === 'beforebegin',
                ab = where === 'afterbegin',
                be = where === 'beforeend',
                ae = where === 'afterend';
            if (tag === 'td' && (ab || be) || !tableElRe.test(tag) && (bb || ae)) {
                return null;
            }
            before = bb ? destinationEl : ae ? destinationEl.nextSibling : ab ? destinationEl.firstChild : null;
            if (bb || ae) {
                destinationEl = destinationEl.parentNode;
            }
            if (tag === 'td' || (tag === 'tr' && (be || ab))) {
                node = this.ieTable(4, trs, html, tre);
            } else if (((tag === 'tbody' || tag === 'thead') && (be || ab)) || (tag === 'tr' && (bb || ae))) {
                node = this.ieTable(3, tbs, html, tbe);
            } else {
                node = this.ieTable(2, ts, html, te);
            }
            destinationEl.insertBefore(node, before);
            return node;
        }
    };
})());

Ext.define('Ext.overrides.plugin.Abstract', {
    override: 'Ext.plugin.Abstract',
    $configStrict: false,
    $configPrefixed: false,
    disabled: false,
    
    
    getState: null,
    
    applyState: null,
    
    enable: function() {
        this.disabled = false;
    },
    
    disable: function() {
        this.disabled = true;
    }
});

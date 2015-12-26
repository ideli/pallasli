
Ext.env || (Ext.env = {});
Ext.env.Browser = function(userAgent, publish) {
    
    
    
    
    var me = this,
        browserPrefixes = me.browserPrefixes,
        enginePrefixes = me.enginePrefixes,
        browserMatch = userAgent.match(new RegExp('((?:' + Ext.Object.getValues(browserPrefixes).join(')|(?:') + '))([\\w\\._]+)')),
        engineMatch = userAgent.match(new RegExp('((?:' + Ext.Object.getValues(enginePrefixes).join(')|(?:') + '))([\\w\\._]+)')),
        browserNames = me.browserNames,
        browserName = browserNames.other,
        engineNames = me.engineNames,
        engineName = engineNames.other,
        browserVersion = '',
        engineVersion = '',
        majorVer = '',
        isWebView = false,
        i, prefix, mode, name, maxIEVersion;
    
    me.userAgent = userAgent;
    if (browserMatch) {
        browserName = browserNames[Ext.Object.getKey(browserPrefixes, browserMatch[1])];
        if (browserName === 'Safari' && /^Opera/.test(userAgent)) {
            
            browserName = 'Opera';
        }
        browserVersion = new Ext.Version(browserMatch[2]);
    }
    if (engineMatch) {
        engineName = engineNames[Ext.Object.getKey(enginePrefixes, engineMatch[1])];
        engineVersion = new Ext.Version(engineMatch[2]);
    }
    if (engineName == 'Trident' && browserName != 'IE') {
        browserName = 'IE';
        var version = userAgent.match(/.*rv:(\d+.\d+)/);
        if (version && version.length) {
            version = version[1];
            browserVersion = new Ext.Version(version);
        }
    }
    
    
    if (userAgent.match(/FB/) && browserName == "Other") {
        browserName = browserNames.safari;
        engineName = engineNames.webkit;
    }
    if (userAgent.match(/Android.*Chrome/g)) {
        browserName = 'ChromeMobile';
    }
    if (userAgent.match(/OPR/)) {
        browserName = 'Opera';
        browserMatch = userAgent.match(/OPR\/(\d+.\d+)/);
        browserVersion = new Ext.Version(browserMatch[1]);
    }
    Ext.apply(this, {
        engineName: engineName,
        engineVersion: engineVersion,
        name: browserName,
        version: browserVersion
    });
    this.setFlag(browserName, true, publish);
    
    if (browserVersion) {
        majorVer = browserVersion.getMajor() || '';
        if (me.is.IE) {
            majorVer = parseInt(majorVer, 10);
            mode = document.documentMode;
            
            
            
            
            
            if (mode == 7 || (majorVer == 7 && mode != 8 && mode != 9 && mode != 10)) {
                majorVer = 7;
            } else if (mode == 8 || (majorVer == 8 && mode != 8 && mode != 9 && mode != 10)) {
                majorVer = 8;
            } else if (mode == 9 || (majorVer == 9 && mode != 7 && mode != 8 && mode != 10)) {
                majorVer = 9;
            } else if (mode == 10 || (majorVer == 10 && mode != 7 && mode != 8 && mode != 9)) {
                majorVer = 10;
            } else if (mode == 11 || (majorVer == 11 && mode != 7 && mode != 8 && mode != 9 && mode != 10)) {
                majorVer = 11;
            }
            maxIEVersion = Math.max(majorVer, 11);
            for (i = 7; i <= maxIEVersion; ++i) {
                prefix = 'isIE' + i;
                if (majorVer <= i) {
                    Ext[prefix + 'm'] = true;
                }
                if (majorVer === i) {
                    Ext[prefix] = true;
                }
                if (majorVer >= i) {
                    Ext[prefix + 'p'] = true;
                }
            }
        }
        if (me.is.Opera && parseInt(majorVer, 10) <= 12) {
            Ext.isOpera12m = true;
        }
        Ext.chromeVersion = Ext.isChrome ? majorVer : 0;
        Ext.firefoxVersion = Ext.isFirefox ? majorVer : 0;
        Ext.ieVersion = Ext.isIE ? majorVer : 0;
        Ext.operaVersion = Ext.isOpera ? majorVer : 0;
        Ext.safariVersion = Ext.isSafari ? majorVer : 0;
        Ext.webKitVersion = Ext.isWebKit ? majorVer : 0;
        this.setFlag(browserName + majorVer, true, publish);
        
        this.setFlag(browserName + browserVersion.getShortVersion());
    }
    for (i in browserNames) {
        if (browserNames.hasOwnProperty(i)) {
            name = browserNames[i];
            this.setFlag(name, browserName === name);
        }
    }
    this.setFlag(name);
    if (engineVersion) {
        this.setFlag(engineName + (engineVersion.getMajor() || ''));
        this.setFlag(engineName + engineVersion.getShortVersion());
    }
    for (i in engineNames) {
        if (engineNames.hasOwnProperty(i)) {
            name = engineNames[i];
            this.setFlag(name, engineName === name, publish);
        }
    }
    this.setFlag('Standalone', !!navigator.standalone);
    this.setFlag('Ripple', !!document.getElementById("tinyhippos-injected") && !Ext.isEmpty(window.top.ripple));
    this.setFlag('WebWorks', !!window.blackberry);
    if (typeof window.PhoneGap != 'undefined' || typeof window.Cordova != 'undefined' || typeof window.cordova != 'undefined') {
        isWebView = true;
        this.setFlag('PhoneGap');
        this.setFlag('Cordova');
    } else if (!!window.isNK) {
        isWebView = true;
        this.setFlag('Sencha');
    }
    if (/(Glass)/i.test(userAgent)) {
        this.setFlag('GoogleGlass');
    }
    
    if (/(iPhone|iPod|iPad).*AppleWebKit(?!.*Safari)(?!.*FBAN)/i.test(userAgent)) {
        isWebView = true;
    }
    
    this.setFlag('WebView', isWebView);
    
    this.isStrict = Ext.isStrict = document.compatMode == "CSS1Compat";
    
    this.isSecure = Ext.isSecure;
    
    this.identity = browserName + majorVer + (this.isStrict ? 'Strict' : 'Quirks');
};
Ext.env.Browser.prototype = {
    constructor: Ext.env.Browser,
    browserNames: {
        ie: 'IE',
        firefox: 'Firefox',
        safari: 'Safari',
        chrome: 'Chrome',
        opera: 'Opera',
        dolfin: 'Dolfin',
        webosbrowser: 'webOSBrowser',
        chromeMobile: 'ChromeMobile',
        chromeiOS: 'ChromeiOS',
        silk: 'Silk',
        other: 'Other'
    },
    engineNames: {
        webkit: 'WebKit',
        gecko: 'Gecko',
        presto: 'Presto',
        trident: 'Trident',
        other: 'Other'
    },
    enginePrefixes: {
        webkit: 'AppleWebKit/',
        gecko: 'Gecko/',
        presto: 'Presto/',
        trident: 'Trident/'
    },
    browserPrefixes: {
        ie: 'MSIE ',
        firefox: 'Firefox/',
        chrome: 'Chrome/',
        safari: 'Version/',
        opera: 'OPR/',
        dolfin: 'Dolfin/',
        webosbrowser: 'wOSBrowser/',
        chromeMobile: 'CrMo/',
        chromeiOS: 'CriOS/',
        silk: 'Silk/'
    },
    styleDashPrefixes: {
        WebKit: '-webkit-',
        Gecko: '-moz-',
        Trident: '-ms-',
        Presto: '-o-',
        Other: ''
    },
    stylePrefixes: {
        WebKit: 'Webkit',
        Gecko: 'Moz',
        Trident: 'ms',
        Presto: 'O',
        Other: ''
    },
    propertyPrefixes: {
        WebKit: 'webkit',
        Gecko: 'moz',
        Trident: 'ms',
        Presto: 'o',
        Other: ''
    },
    
    
    is: function(name) {
        return !!this.is[name];
    },
    
    name: null,
    
    version: null,
    
    engineName: null,
    
    engineVersion: null,
    setFlag: function(name, value, publish) {
        if (typeof value == 'undefined') {
            value = true;
        }
        this.is[name] = value;
        this.is[name.toLowerCase()] = value;
        if (publish) {
            Ext['is' + name] = value;
        }
        return this;
    },
    getStyleDashPrefix: function() {
        return this.styleDashPrefixes[this.engineName];
    },
    getStylePrefix: function() {
        return this.stylePrefixes[this.engineName];
    },
    getVendorProperyName: function(name) {
        var prefix = this.propertyPrefixes[this.engineName];
        if (prefix.length > 0) {
            return prefix + Ext.String.capitalize(name);
        }
        return name;
    },
    getPreferredTranslationMethod: function(config) {
        if (typeof config == 'object' && 'translationMethod' in config && config.translationMethod !== 'auto') {
            return config.translationMethod;
        } else {
            return 'csstransform';
        }
    }
};

(function(userAgent) {
    Ext.browser = new Ext.env.Browser(userAgent,true);
    Ext.userAgent = userAgent.toLowerCase();
    
    Ext.SSL_SECURE_URL = Ext.isSecure && Ext.isIE ? 'javascript:\'\'' : 'about:blank';
}(Ext.global.navigator.userAgent));

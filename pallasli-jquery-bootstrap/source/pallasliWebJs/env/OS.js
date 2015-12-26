

Ext.env.OS = function(userAgent, platform, browserScope) {
    
    
    
    
    var me = this,
        names = me.names,
        prefixes = me.prefixes,
        name,
        version = '',
        is = me.is,
        i, prefix, match, item, match1;
    browserScope = browserScope || Ext.browser;
    for (i in prefixes) {
        if (prefixes.hasOwnProperty(i)) {
            prefix = prefixes[i];
            match = userAgent.match(new RegExp('(?:' + prefix + ')([^\\s;]+)'));
            if (match) {
                name = names[i];
                match1 = match[1];
                
                
                if (match1 && match1 == "HTC_") {
                    version = new Ext.Version("2.3");
                } else if (match1 && match1 == "Silk/") {
                    version = new Ext.Version("2.3");
                } else {
                    version = new Ext.Version(match[match.length - 1]);
                }
                break;
            }
        }
    }
    if (!name) {
        name = names[(userAgent.toLowerCase().match(/mac|win|linux/) || [
            'other'
        ])[0]];
        version = new Ext.Version('');
    }
    this.name = name;
    this.version = version;
    if (platform) {
        this.setFlag(platform.replace(/ simulator$/i, ''));
    }
    this.setFlag(name);
    if (version) {
        this.setFlag(name + (version.getMajor() || ''));
        this.setFlag(name + version.getShortVersion());
    }
    for (i in names) {
        if (names.hasOwnProperty(i)) {
            item = names[i];
            if (!is.hasOwnProperty(name)) {
                this.setFlag(item, (name === item));
            }
        }
    }
    
    if (this.name == "iOS" && window.screen.height == 568) {
        this.setFlag('iPhone5');
    }
    if (browserScope.is.Safari || browserScope.is.Silk) {
        
        if (this.is.Android2 || this.is.Android3 || browserScope.version.shortVersion == 501) {
            browserScope.setFlag("AndroidStock");
            browserScope.setFlag("AndroidStock2");
        }
        if (this.is.Android4) {
            browserScope.setFlag("AndroidStock");
            browserScope.setFlag("AndroidStock4");
        }
    }
};
Ext.env.OS.prototype = {
    constructor: Ext.env.OS,
    names: {
        ios: 'iOS',
        android: 'Android',
        windowsPhone: 'WindowsPhone',
        webos: 'webOS',
        blackberry: 'BlackBerry',
        rimTablet: 'RIMTablet',
        mac: 'MacOS',
        win: 'Windows',
        tizen: 'Tizen',
        linux: 'Linux',
        bada: 'Bada',
        chrome: 'ChromeOS',
        other: 'Other'
    },
    prefixes: {
        tizen: '(Tizen )',
        ios: 'i(?:Pad|Phone|Pod)(?:.*)CPU(?: iPhone)? OS ',
        android: '(Android |HTC_|Silk/)',
        
        
        windowsPhone: 'Windows Phone ',
        blackberry: '(?:BlackBerry|BB)(?:.*)Version/',
        rimTablet: 'RIM Tablet OS ',
        webos: '(?:webOS|hpwOS)/',
        bada: 'Bada/',
        chrome: 'CrOS '
    },
    
    is: function(name) {
        return !!this[name];
    },
    
    name: null,
    
    version: null,
    setFlag: function(name, value) {
        if (typeof value == 'undefined') {
            value = true;
        }
        if (this.flags) {
            this.flags[name] = value;
        }
        this.is[name] = value;
        this.is[name.toLowerCase()] = value;
        return this;
    }
};
(function() {
    var navigation = Ext.global.navigator,
        userAgent = navigation.userAgent,
        OS = Ext.env.OS,
        is = (Ext.is || (Ext.is = {})),
        osEnv, osName, deviceType;
    OS.prototype.flags = is;
    
    Ext.os = osEnv = new OS(userAgent,navigation.platform);
    osName = osEnv.name;
    
    Ext['is' + osName] = true;
    
    Ext.isMac = is.Mac = is.MacOS;
    var search = window.location.search.match(/deviceType=(Tablet|Phone)/),
        nativeDeviceType = window.deviceType;
    
    
    if (search && search[1]) {
        deviceType = search[1];
    } else if (nativeDeviceType === 'iPhone') {
        deviceType = 'Phone';
    } else if (nativeDeviceType === 'iPad') {
        deviceType = 'Tablet';
    } else {
        if (!osEnv.is.Android && !osEnv.is.iOS && !osEnv.is.WindowsPhone && /Windows|Linux|MacOS/.test(osName)) {
            deviceType = 'Desktop';
            
            Ext.browser.is.WebView = !!Ext.browser.is.Ripple;
        } else if (osEnv.is.iPad || osEnv.is.RIMTablet || osEnv.is.Android3 || Ext.browser.is.Silk || (osEnv.is.Android4 && userAgent.search(/mobile/i) == -1)) {
            deviceType = 'Tablet';
        } else {
            deviceType = 'Phone';
        }
    }
    
    osEnv.setFlag(deviceType, true);
    osEnv.deviceType = deviceType;
    delete OS.prototype.flags;
}());


(function() {
    
    
    var 
        checkVerTemp = [
            ''
        ],
        endOfVersionRe = /([^\d\.])/,
        notDigitsRe = /[^\d]/g,
        plusMinusRe = /[\-+]/g,
        stripRe = /\s/g,
        underscoreRe = /_/g,
        Version;
    Ext.Version = Version = function(version, defaultMode) {
        var me = this,
            padModes = me.padModes,
            ch, i, pad, parts, release, releaseStartIndex, ver;
        if (version.isVersion) {
            version = version.version;
        }
        me.version = ver = String(version).toLowerCase().replace(underscoreRe, '.').replace(plusMinusRe, '');
        ch = ver.charAt(0);
        if (ch in padModes) {
            ver = ver.substring(1);
            pad = padModes[ch];
        } else {
            pad = defaultMode ? padModes[defaultMode] : 0;
        }
        
        me.pad = pad;
        releaseStartIndex = ver.search(endOfVersionRe);
        me.shortVersion = ver;
        if (releaseStartIndex !== -1) {
            me.release = release = ver.substr(releaseStartIndex, version.length);
            me.shortVersion = ver.substr(0, releaseStartIndex);
            release = Version.releaseValueMap[release] || release;
        }
        me.releaseValue = release || pad;
        me.shortVersion = me.shortVersion.replace(notDigitsRe, '');
        
        me.parts = parts = ver.split('.');
        for (i = parts.length; i--; ) {
            parts[i] = parseInt(parts[i], 10);
        }
        if (pad === Infinity) {
            
            parts.push(pad);
        }
        
        me.major = parts[0] || pad;
        
        me.minor = parts[1] || pad;
        
        me.patch = parts[2] || pad;
        
        me.build = parts[3] || pad;
        return me;
    };
    Version.prototype = {
        isVersion: true,
        padModes: {
            '~': NaN,
            '^': Infinity
        },
        
        release: '',
        
        compareTo: function(other) {
            
            
            var me = this,
                lhsPad = me.pad,
                lhsParts = me.parts,
                lhsLength = lhsParts.length,
                rhsVersion = other.isVersion ? other : new Version(other),
                rhsPad = rhsVersion.pad,
                rhsParts = rhsVersion.parts,
                rhsLength = rhsParts.length,
                length = Math.max(lhsLength, rhsLength),
                i, lhs, rhs;
            for (i = 0; i < length; i++) {
                lhs = (i < lhsLength) ? lhsParts[i] : lhsPad;
                rhs = (i < rhsLength) ? rhsParts[i] : rhsPad;
                
                
                if (lhs < rhs) {
                    return -1;
                }
                if (lhs > rhs) {
                    return 1;
                }
            }
            
            lhs = me.releaseValue;
            rhs = rhsVersion.releaseValue;
            if (lhs < rhs) {
                return -1;
            }
            if (lhs > rhs) {
                return 1;
            }
            return 0;
        },
        
        toString: function() {
            return this.version;
        },
        
        valueOf: function() {
            return this.version;
        },
        
        getMajor: function() {
            return this.major;
        },
        
        getMinor: function() {
            return this.minor;
        },
        
        getPatch: function() {
            return this.patch;
        },
        
        getBuild: function() {
            return this.build;
        },
        
        getRelease: function() {
            return this.release;
        },
        
        getReleaseValue: function() {
            return this.releaseValue;
        },
        
        isGreaterThan: function(target) {
            return this.compareTo(target) > 0;
        },
        
        isGreaterThanOrEqual: function(target) {
            return this.compareTo(target) >= 0;
        },
        
        isLessThan: function(target) {
            return this.compareTo(target) < 0;
        },
        
        isLessThanOrEqual: function(target) {
            return this.compareTo(target) <= 0;
        },
        
        equals: function(target) {
            return this.compareTo(target) === 0;
        },
        
        match: function(target) {
            target = String(target);
            return this.version.substr(0, target.length) === target;
        },
        
        toArray: function() {
            var me = this;
            return [
                me.getMajor(),
                me.getMinor(),
                me.getPatch(),
                me.getBuild(),
                me.getRelease()
            ];
        },
        
        getShortVersion: function() {
            return this.shortVersion;
        },
        
        gt: function(target) {
            return this.compareTo(target) > 0;
        },
        
        lt: function(target) {
            return this.compareTo(target) < 0;
        },
        
        gtEq: function(target) {
            return this.compareTo(target) >= 0;
        },
        
        ltEq: function(target) {
            return this.compareTo(target) <= 0;
        }
    };
    Ext.apply(Version, {
        aliases: {
            from: {
                extjs: 'ext',
                core: 'sencha-core'
            },
            to: {
                ext: [
                    'extjs'
                ],
                'sencha-core': [
                    'core'
                ]
            }
        },
        
        releaseValueMap: {
            dev: -6,
            alpha: -5,
            a: -5,
            beta: -4,
            b: -4,
            rc: -3,
            '#': -2,
            p: -1,
            pl: -1
        },
        
        getComponentValue: function(value) {
            return !value ? 0 : (isNaN(value) ? this.releaseValueMap[value] || value : parseInt(value, 10));
        },
        
        compare: function(current, target) {
            var ver = current.isVersion ? current : new Version(current);
            return ver.compareTo(target);
        },
        set: function(collection, packageName, version) {
            var aliases = Version.aliases.to[packageName],
                ver = version.isVersion ? version : new Version(version),
                i;
            collection[packageName] = ver;
            if (aliases) {
                for (i = aliases.length; i-- > 0; ) {
                    collection[aliases[i]] = ver;
                }
            }
            return ver;
        }
    });
    
    Ext.apply(Ext, {
        
        compatVersions: {},
        
        versions: {},
        
        lastRegisteredVersion: null,
        
        getCompatVersion: function(packageName) {
            var versions = Ext.compatVersions,
                compat;
            if (!packageName) {
                compat = versions.ext || versions.touch || versions.core;
            } else {
                compat = versions[Version.aliases.from[packageName] || packageName];
            }
            return compat || Ext.getVersion(packageName);
        },
        
        setCompatVersion: function(packageName, version) {
            Version.set(Ext.compatVersions, packageName, version);
        },
        
        setVersion: function(packageName, version) {
            Ext.lastRegisteredVersion = Version.set(Ext.versions, packageName, version);
            return this;
        },
        
        getVersion: function(packageName) {
            var versions = Ext.versions;
            if (!packageName) {
                return versions.ext || versions.touch || versions.core;
            }
            return versions[Version.aliases.from[packageName] || packageName];
        },
        
        checkVersion: function(specs, matchAll) {
            var isArray = Ext.isArray(specs),
                aliases = Version.aliases.from,
                compat = isArray ? specs : checkVerTemp,
                length = compat.length,
                versions = Ext.versions,
                frameworkVer = versions.ext || versions.touch,
                i, index, matches, minVer, maxVer, packageName, spec, range, ver;
            if (!isArray) {
                checkVerTemp[0] = specs;
            }
            for (i = 0; i < length; ++i) {
                if (!Ext.isString(spec = compat[i])) {
                    matches = Ext.checkVersion(spec.and || spec.or, !spec.or);
                    if (spec.not) {
                        matches = !matches;
                    }
                } else {
                    if (spec.indexOf(' ') >= 0) {
                        spec = spec.replace(stripRe, '');
                    }
                    
                    
                    index = spec.indexOf('@');
                    if (index < 0) {
                        range = spec;
                        ver = frameworkVer;
                    } else {
                        packageName = spec.substring(0, index);
                        if (!(ver = versions[aliases[packageName] || packageName])) {
                            
                            
                            if (matchAll) {
                                return false;
                            }
                            
                            
                            
                            continue;
                        }
                        range = spec.substring(index + 1);
                    }
                    
                    index = range.indexOf('-');
                    if (index < 0) {
                        
                        if (range.charAt(index = range.length - 1) === '+') {
                            minVer = range.substring(0, index);
                            maxVer = null;
                        } else {
                            minVer = maxVer = range;
                        }
                    } else if (index > 0) {
                        
                        minVer = range.substring(0, index);
                        maxVer = range.substring(index + 1);
                    } else 
                    {
                        
                        minVer = null;
                        maxVer = range.substring(index + 1);
                    }
                    matches = true;
                    if (minVer) {
                        minVer = new Version(minVer,'~');
                        
                        matches = minVer.ltEq(ver);
                    }
                    if (matches && maxVer) {
                        maxVer = new Version(maxVer,'~');
                        
                        matches = maxVer.gtEq(ver);
                    }
                }
                
                if (matches) {
                    
                    if (!matchAll) {
                        return true;
                    }
                } else if (matchAll) {
                    
                    return false;
                }
            }
            
            
            
            
            return !!matchAll;
        },
        
        deprecate: function(packageName, since, closure, scope) {
            if (Version.compare(Ext.getVersion(packageName), since) < 1) {
                closure.call(scope);
            }
        }
    });
}());

Pallasli.String = (function() {
    var trimRegex = /^[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u2028\u2029\u202f\u205f\u3000]+|[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u2028\u2029\u202f\u205f\u3000]+$/g,
        escapeRe = /('|\\)/g,
        escapeRegexRe = /([-.*+?\^${}()|\[\]\/\\])/g,
        basicTrimRe = /^\s+|\s+$/g,
        whitespaceRe = /\s+/,
        varReplace = /(^[^a-z]*|[^\w])/gi,
        charToEntity, entityToChar, charToEntityRegex, entityToCharRegex,
        htmlEncodeReplaceFn = function(match, capture) {
            return charToEntity[capture];
        },
        htmlDecodeReplaceFn = function(match, capture) {
            return (capture in entityToChar) ? entityToChar[capture] : String.fromCharCode(parseInt(capture.substr(2), 10));
        },
        boundsCheck = function(s, other) {
            if (s === null || s === undefined || other === null || other === undefined) {
                return false;
            }
            return other.length <= s.length;
        },
        ExtString;
    return ExtString = {
        
        insert: function(s, value, index) {
            if (!s) {
                return value;
            }
            if (!value) {
                return s;
            }
            var len = s.length;
            if (!index && index !== 0) {
                index = len;
            }
            if (index < 0) {
                index *= -1;
                if (index >= len) {
                    
                    index = 0;
                } else {
                    index = len - index;
                }
            }
            if (index === 0) {
                s = value + s;
            } else if (index >= s.length) {
                s += value;
            } else {
                s = s.substr(0, index) + value + s.substr(index);
            }
            return s;
        },
        
        startsWith: function(s, start, ignoreCase) {
            var result = boundsCheck(s, start);
            if (result) {
                if (ignoreCase) {
                    s = s.toLowerCase();
                    start = start.toLowerCase();
                }
                result = s.lastIndexOf(start, 0) === 0;
            }
            return result;
        },
        
        endsWith: function(s, end, ignoreCase) {
            var result = boundsCheck(s, end);
            if (result) {
                if (ignoreCase) {
                    s = s.toLowerCase();
                    end = end.toLowerCase();
                }
                result = s.indexOf(end, s.length - end.length) !== -1;
            }
            return result;
        },
        
        createVarName: function(s) {
            return s.replace(varReplace, '');
        },
        
        htmlEncode: function(value) {
            return (!value) ? value : String(value).replace(charToEntityRegex, htmlEncodeReplaceFn);
        },
        
        htmlDecode: function(value) {
            return (!value) ? value : String(value).replace(entityToCharRegex, htmlDecodeReplaceFn);
        },
        
        hasHtmlCharacters: function(s) {
            return charToEntityRegex.test(s);
        },
        
        addCharacterEntities: function(newEntities) {
            var charKeys = [],
                entityKeys = [],
                key, echar;
            for (key in newEntities) {
                echar = newEntities[key];
                entityToChar[key] = echar;
                charToEntity[echar] = key;
                charKeys.push(echar);
                entityKeys.push(key);
            }
            charToEntityRegex = new RegExp('(' + charKeys.join('|') + ')','g');
            entityToCharRegex = new RegExp('(' + entityKeys.join('|') + '|&#[0-9]{1,5};' + ')','g');
        },
        
        resetCharacterEntities: function() {
            charToEntity = {};
            entityToChar = {};
            
            this.addCharacterEntities({
                '&amp;': '&',
                '&gt;': '>',
                '&lt;': '<',
                '&quot;': '"',
                '&#39;': "'"
            });
        },
        
        urlAppend: function(url, string) {
            if (!Ext.isEmpty(string)) {
                return url + (url.indexOf('?') === -1 ? '?' : '&') + string;
            }
            return url;
        },
        
        trim: function(string) {
            if (string) {
                string = string.replace(trimRegex, "");
            }
            return string || '';
        },
        
        capitalize: function(string) {
            if (string) {
                string = string.charAt(0).toUpperCase() + string.substr(1);
            }
            return string || '';
        },
        
        uncapitalize: function(string) {
            if (string) {
                string = string.charAt(0).toLowerCase() + string.substr(1);
            }
            return string || '';
        },
        
        ellipsis: function(value, length, word) {
            if (value && value.length > length) {
                if (word) {
                    var vs = value.substr(0, length - 2),
                        index = Math.max(vs.lastIndexOf(' '), vs.lastIndexOf('.'), vs.lastIndexOf('!'), vs.lastIndexOf('?'));
                    if (index !== -1 && index >= (length - 15)) {
                        return vs.substr(0, index) + "...";
                    }
                }
                return value.substr(0, length - 3) + "...";
            }
            return value;
        },
        
        escapeRegex: function(string) {
            return string.replace(escapeRegexRe, "\\$1");
        },
        
        createRegex: function(value, startsWith, endsWith, ignoreCase) {
            var ret = value;
            if (value != null && !value.exec) {
                
                ret = ExtString.escapeRegex(String(value));
                if (startsWith !== false) {
                    ret = '^' + ret;
                }
                if (endsWith !== false) {
                    ret += '$';
                }
                ret = new RegExp(ret,(ignoreCase !== false) ? 'i' : '');
            }
            return ret;
        },
        
        escape: function(string) {
            return string.replace(escapeRe, "\\$1");
        },
        
        toggle: function(string, value, other) {
            return string === value ? other : value;
        },
        
        leftPad: function(string, size, character) {
            var result = String(string);
            character = character || " ";
            while (result.length < size) {
                result = character + result;
            }
            return result;
        },
        
        repeat: function(pattern, count, sep) {
            if (count < 1) {
                count = 0;
            }
            for (var buf = [],
                i = count; i--; ) {
                buf.push(pattern);
            }
            return buf.join(sep || '');
        },
        
        splitWords: function(words) {
            if (words && typeof words == 'string') {
                return words.replace(basicTrimRe, '').split(whitespaceRe);
            }
            return words || [];
        }
    };
}());

Ext.String.resetCharacterEntities();

Ext.htmlEncode = Ext.String.htmlEncode;

Ext.htmlDecode = Ext.String.htmlDecode;

Ext.urlAppend = Ext.String.urlAppend;
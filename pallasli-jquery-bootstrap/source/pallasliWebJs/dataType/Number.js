
Ext.Number = new function() {
    
    
    
    var ExtNumber = this,
        isToFixedBroken = (0.9).toFixed() !== '1',
        math = Math,
        ClipDefault = {
            count: false,
            inclusive: false,
            wrap: true
        };
    Ext.apply(ExtNumber, {
        Clip: {
            DEFAULT: ClipDefault,
            COUNT: Ext.applyIf({
                count: true
            }, ClipDefault),
            INCLUSIVE: Ext.applyIf({
                inclusive: true
            }, ClipDefault),
            NOWRAP: Ext.applyIf({
                wrap: false
            }, ClipDefault)
        },
        
        clipIndices: function(length, indices, options) {
            options = options || ClipDefault;
            var defaultValue = 0,
                
                wrap = options.wrap,
                begin, end, i;
            indices = indices || [];
            for (i = 0; i < 2; ++i) {
                
                
                begin = end;
                
                end = indices[i];
                if (end == null) {
                    end = defaultValue;
                } else if (i && options.count) {
                    end += begin;
                    
                    end = (end > length) ? length : end;
                } else {
                    if (wrap) {
                        end = (end < 0) ? (length + end) : end;
                    }
                    if (i && options.inclusive) {
                        ++end;
                    }
                    end = (end < 0) ? 0 : ((end > length) ? length : end);
                }
                defaultValue = length;
            }
            
            
            
            
            indices[0] = begin;
            indices[1] = (end < begin) ? begin : end;
            return indices;
        },
        
        constrain: function(number, min, max) {
            var x = parseFloat(number);
            
            
            
            if (min === null) {
                min = number;
            }
            if (max === null) {
                max = number;
            }
            
            
            
            return (x < min) ? min : ((x > max) ? max : x);
        },
        
        snap: function(value, increment, minValue, maxValue) {
            var m;
            
            
            if (value === undefined || value < minValue) {
                return minValue || 0;
            }
            if (increment) {
                m = value % increment;
                if (m !== 0) {
                    value -= m;
                    if (m * 2 >= increment) {
                        value += increment;
                    } else if (m * 2 < -increment) {
                        value -= increment;
                    }
                }
            }
            return ExtNumber.constrain(value, minValue, maxValue);
        },
        
        snapInRange: function(value, increment, minValue, maxValue) {
            var tween;
            
            minValue = (minValue || 0);
            
            if (value === undefined || value < minValue) {
                return minValue;
            }
            
            if (increment && (tween = ((value - minValue) % increment))) {
                value -= tween;
                tween *= 2;
                if (tween >= increment) {
                    value += increment;
                }
            }
            
            if (maxValue !== undefined) {
                if (value > (maxValue = ExtNumber.snapInRange(maxValue, increment, minValue))) {
                    value = maxValue;
                }
            }
            return value;
        },
        
        sign: function(x) {
            x = +x;
            
            if (x === 0 || isNaN(x)) {
                return x;
            }
            return (x > 0) ? 1 : -1;
        },
        
        toFixed: isToFixedBroken ? function(value, precision) {
            precision = precision || 0;
            var pow = math.pow(10, precision);
            return (math.round(value * pow) / pow).toFixed(precision);
        } : function(value, precision) {
            return value.toFixed(precision);
        },
        
        from: function(value, defaultValue) {
            if (isFinite(value)) {
                value = parseFloat(value);
            }
            return !isNaN(value) ? value : defaultValue;
        },
        
        randomInt: function(from, to) {
            return math.floor(math.random() * (to - from + 1) + from);
        },
        
        correctFloat: function(n) {
            
            
            
            return parseFloat(n.toPrecision(14));
        }
    });
    
    Ext.num = function() {
        return ExtNumber.from.apply(this, arguments);
    };
}();
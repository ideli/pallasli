
Ext.Array = new (function() {
    
    var arrayPrototype = Array.prototype,
        slice = arrayPrototype.slice,
        supportsSplice = (function() {
            var array = [],
                lengthBefore,
                j = 20;
            if (!array.splice) {
                return false;
            }
            
            
            while (j--) {
                array.push("A");
            }
            array.splice(15, 0, "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F");
            lengthBefore = array.length;
            
            array.splice(13, 0, "XXX");
            
            if (lengthBefore + 1 !== array.length) {
                return false;
            }
            
            return true;
        }()),
        supportsIndexOf = 'indexOf' in arrayPrototype,
        supportsSliceOnNodeList = true;
    
    
    function stableSort(array, userComparator) {
        var len = array.length,
            indices = new Array(len),
            i;
        
        for (i = 0; i < len; i++) {
            indices[i] = i;
        }
        
        indices.sort(function(index1, index2) {
            return userComparator(array[index1], array[index2]) || (index1 - index2);
        });
        
        for (i = 0; i < len; i++) {
            indices[i] = array[indices[i]];
        }
        
        for (i = 0; i < len; i++) {
            array[i] = indices[i];
        }
        return array;
    }
    try {
        
        if (typeof document !== 'undefined') {
            slice.call(document.getElementsByTagName('body'));
        }
    } catch (e) {
        supportsSliceOnNodeList = false;
    }
    var fixArrayIndex = function(array, index) {
            return (index < 0) ? Math.max(0, array.length + index) : Math.min(array.length, index);
        },
        
        replaceSim = function(array, index, removeCount, insert) {
            var add = insert ? insert.length : 0,
                length = array.length,
                pos = fixArrayIndex(array, index);
            
            if (pos === length) {
                if (add) {
                    array.push.apply(array, insert);
                }
            } else {
                var remove = Math.min(removeCount, length - pos),
                    tailOldPos = pos + remove,
                    tailNewPos = tailOldPos + add - remove,
                    tailCount = length - tailOldPos,
                    lengthAfterRemove = length - remove,
                    i;
                if (tailNewPos < tailOldPos) {
                    
                    for (i = 0; i < tailCount; ++i) {
                        array[tailNewPos + i] = array[tailOldPos + i];
                    }
                } else if (tailNewPos > tailOldPos) {
                    
                    for (i = tailCount; i--; ) {
                        array[tailNewPos + i] = array[tailOldPos + i];
                    }
                }
                
                if (add && pos === lengthAfterRemove) {
                    array.length = lengthAfterRemove;
                    
                    array.push.apply(array, insert);
                } else {
                    array.length = lengthAfterRemove + add;
                    
                    for (i = 0; i < add; ++i) {
                        array[pos + i] = insert[i];
                    }
                }
            }
            return array;
        },
        replaceNative = function(array, index, removeCount, insert) {
            if (insert && insert.length) {
                
                if (index === 0 && !removeCount) {
                    array.unshift.apply(array, insert);
                }
                
                else if (index < array.length) {
                    array.splice.apply(array, [
                        index,
                        removeCount
                    ].concat(insert));
                } else 
                {
                    array.push.apply(array, insert);
                }
            } else {
                array.splice(index, removeCount);
            }
            return array;
        },
        eraseSim = function(array, index, removeCount) {
            return replaceSim(array, index, removeCount);
        },
        eraseNative = function(array, index, removeCount) {
            array.splice(index, removeCount);
            return array;
        },
        spliceSim = function(array, index, removeCount) {
            var pos = fixArrayIndex(array, index),
                removed = array.slice(index, fixArrayIndex(array, pos + removeCount));
            if (arguments.length < 4) {
                replaceSim(array, pos, removeCount);
            } else {
                replaceSim(array, pos, removeCount, slice.call(arguments, 3));
            }
            return removed;
        },
        spliceNative = function(array) {
            return array.splice.apply(array, slice.call(arguments, 1));
        },
        erase = supportsSplice ? eraseNative : eraseSim,
        replace = supportsSplice ? replaceNative : replaceSim,
        splice = supportsSplice ? spliceNative : spliceSim,
        
        ExtArray = {
            
            binarySearch: function(array, item, begin, end, compareFn) {
                var length = array.length,
                    middle, comparison;
                if (begin instanceof Function) {
                    compareFn = begin;
                    begin = 0;
                    end = length;
                } else if (end instanceof Function) {
                    compareFn = end;
                    end = length;
                } else {
                    if (begin === undefined) {
                        begin = 0;
                    }
                    if (end === undefined) {
                        end = length;
                    }
                    compareFn = compareFn || ExtArray.lexicalCompare;
                }
                --end;
                while (begin <= end) {
                    middle = (begin + end) >> 1;
                    comparison = compareFn(item, array[middle]);
                    if (comparison >= 0) {
                        begin = middle + 1;
                    } else if (comparison < 0) {
                        end = middle - 1;
                    }
                }
                return begin;
            },
            defaultCompare: function(lhs, rhs) {
                return (lhs < rhs) ? -1 : ((lhs > rhs) ? 1 : 0);
            },
            
            
            lexicalCompare: function(lhs, rhs) {
                lhs = String(lhs);
                rhs = String(rhs);
                return (lhs < rhs) ? -1 : ((lhs > rhs) ? 1 : 0);
            },
            
            each: function(array, fn, scope, reverse) {
                array = ExtArray.from(array);
                var i,
                    ln = array.length;
                if (reverse !== true) {
                    for (i = 0; i < ln; i++) {
                        if (fn.call(scope || array[i], array[i], i, array) === false) {
                            return i;
                        }
                    }
                } else {
                    for (i = ln - 1; i > -1; i--) {
                        if (fn.call(scope || array[i], array[i], i, array) === false) {
                            return i;
                        }
                    }
                }
                return true;
            },
            
            forEach: ('forEach' in arrayPrototype) ? function(array, fn, scope) {
                return array.forEach(fn, scope);
            } : function(array, fn, scope) {
                for (var i = 0,
                    ln = array.length; i < ln; i++) {
                    fn.call(scope, array[i], i, array);
                }
            },
            
            indexOf: supportsIndexOf ? function(array, item, from) {
                return arrayPrototype.indexOf.call(array, item, from);
            } : function(array, item, from) {
                var i,
                    length = array.length;
                for (i = (from < 0) ? Math.max(0, length + from) : from || 0; i < length; i++) {
                    if (array[i] === item) {
                        return i;
                    }
                }
                return -1;
            },
            
            contains: supportsIndexOf ? function(array, item) {
                return arrayPrototype.indexOf.call(array, item) !== -1;
            } : function(array, item) {
                var i, ln;
                for (i = 0 , ln = array.length; i < ln; i++) {
                    if (array[i] === item) {
                        return true;
                    }
                }
                return false;
            },
            
            toArray: function(iterable, start, end) {
                if (!iterable || !iterable.length) {
                    return [];
                }
                if (typeof iterable === 'string') {
                    iterable = iterable.split('');
                }
                if (supportsSliceOnNodeList) {
                    return slice.call(iterable, start || 0, end || iterable.length);
                }
                var array = [],
                    i;
                start = start || 0;
                end = end ? ((end < 0) ? iterable.length + end : end) : iterable.length;
                for (i = start; i < end; i++) {
                    array.push(iterable[i]);
                }
                return array;
            },
            
            pluck: function(array, propertyName) {
                var ret = [],
                    i, ln, item;
                for (i = 0 , ln = array.length; i < ln; i++) {
                    item = array[i];
                    ret.push(item[propertyName]);
                }
                return ret;
            },
            
            map: ('map' in arrayPrototype) ? function(array, fn, scope) {
                Ext.Assert.isFunction(fn, 'Ext.Array.map must have a callback function passed as second argument.');
                return array.map(fn, scope);
            } : function(array, fn, scope) {
                Ext.Assert.isFunction(fn, 'Ext.Array.map must have a callback function passed as second argument.');
                var results = [],
                    i = 0,
                    len = array.length;
                for (; i < len; i++) {
                    results[i] = fn.call(scope, array[i], i, array);
                }
                return results;
            },
            
            every: ('every' in arrayPrototype) ? function(array, fn, scope) {
                Ext.Assert.isFunction(fn, 'Ext.Array.every must have a callback function passed as second argument.');
                return array.every(fn, scope);
            } : function(array, fn, scope) {
                Ext.Assert.isFunction(fn, 'Ext.Array.every must have a callback function passed as second argument.');
                var i = 0,
                    ln = array.length;
                for (; i < ln; ++i) {
                    if (!fn.call(scope, array[i], i, array)) {
                        return false;
                    }
                }
                return true;
            },
            
            some: ('some' in arrayPrototype) ? function(array, fn, scope) {
                Ext.Assert.isFunction(fn, 'Ext.Array.some must have a callback function passed as second argument.');
                return array.some(fn, scope);
            } : function(array, fn, scope) {
                Ext.Assert.isFunction(fn, 'Ext.Array.some must have a callback function passed as second argument.');
                var i = 0,
                    ln = array.length;
                for (; i < ln; ++i) {
                    if (fn.call(scope, array[i], i, array)) {
                        return true;
                    }
                }
                return false;
            },
            
            equals: function(array1, array2) {
                var len1 = array1.length,
                    len2 = array2.length,
                    i;
                
                if (array1 === array2) {
                    return true;
                }
                if (len1 !== len2) {
                    return false;
                }
                for (i = 0; i < len1; ++i) {
                    if (array1[i] !== array2[i]) {
                        return false;
                    }
                }
                return true;
            },
            
            clean: function(array) {
                var results = [],
                    i = 0,
                    ln = array.length,
                    item;
                for (; i < ln; i++) {
                    item = array[i];
                    if (!Ext.isEmpty(item)) {
                        results.push(item);
                    }
                }
                return results;
            },
            
            unique: function(array) {
                var clone = [],
                    i = 0,
                    ln = array.length,
                    item;
                for (; i < ln; i++) {
                    item = array[i];
                    if (ExtArray.indexOf(clone, item) === -1) {
                        clone.push(item);
                    }
                }
                return clone;
            },
            
            filter: ('filter' in arrayPrototype) ? function(array, fn, scope) {
                Ext.Assert.isFunction(fn, 'Ext.Array.filter must have a filter function passed as second argument.');
                return array.filter(fn, scope);
            } : function(array, fn, scope) {
                Ext.Assert.isFunction(fn, 'Ext.Array.filter must have a filter function passed as second argument.');
                var results = [],
                    i = 0,
                    ln = array.length;
                for (; i < ln; i++) {
                    if (fn.call(scope, array[i], i, array)) {
                        results.push(array[i]);
                    }
                }
                return results;
            },
            
            findBy: function(array, fn, scope) {
                var i = 0,
                    len = array.length;
                for (; i < len; i++) {
                    if (fn.call(scope || array, array[i], i)) {
                        return array[i];
                    }
                }
                return null;
            },
            
            from: function(value, newReference) {
                if (value === undefined || value === null) {
                    return [];
                }
                if (Ext.isArray(value)) {
                    return (newReference) ? slice.call(value) : value;
                }
                var type = typeof value;
                
                
                if (value && value.length !== undefined && type !== 'string' && (type !== 'function' || !value.apply)) {
                    return ExtArray.toArray(value);
                }
                return [
                    value
                ];
            },
            
            remove: function(array, item) {
                var index = ExtArray.indexOf(array, item);
                if (index !== -1) {
                    erase(array, index, 1);
                }
                return array;
            },
            
            removeAt: function(array, index, count) {
                var len = array.length;
                if (index >= 0 && index < len) {
                    count = count || 1;
                    count = Math.min(count, len - index);
                    erase(array, index, count);
                }
                return array;
            },
            
            include: function(array, item) {
                if (!ExtArray.contains(array, item)) {
                    array.push(item);
                }
            },
            
            clone: function(array) {
                return slice.call(array);
            },
            
            merge: function() {
                var args = slice.call(arguments),
                    array = [],
                    i, ln;
                for (i = 0 , ln = args.length; i < ln; i++) {
                    array = array.concat(args[i]);
                }
                return ExtArray.unique(array);
            },
            
            intersect: function() {
                var intersection = [],
                    arrays = slice.call(arguments),
                    arraysLength, array, arrayLength, minArray, minArrayIndex, minArrayCandidate, minArrayLength, element, elementCandidate, elementCount, i, j, k;
                if (!arrays.length) {
                    return intersection;
                }
                
                arraysLength = arrays.length;
                for (i = minArrayIndex = 0; i < arraysLength; i++) {
                    minArrayCandidate = arrays[i];
                    if (!minArray || minArrayCandidate.length < minArray.length) {
                        minArray = minArrayCandidate;
                        minArrayIndex = i;
                    }
                }
                minArray = ExtArray.unique(minArray);
                erase(arrays, minArrayIndex, 1);
                
                
                
                minArrayLength = minArray.length;
                arraysLength = arrays.length;
                for (i = 0; i < minArrayLength; i++) {
                    element = minArray[i];
                    elementCount = 0;
                    for (j = 0; j < arraysLength; j++) {
                        array = arrays[j];
                        arrayLength = array.length;
                        for (k = 0; k < arrayLength; k++) {
                            elementCandidate = array[k];
                            if (element === elementCandidate) {
                                elementCount++;
                                break;
                            }
                        }
                    }
                    if (elementCount === arraysLength) {
                        intersection.push(element);
                    }
                }
                return intersection;
            },
            
            difference: function(arrayA, arrayB) {
                var clone = slice.call(arrayA),
                    ln = clone.length,
                    i, j, lnB;
                for (i = 0 , lnB = arrayB.length; i < lnB; i++) {
                    for (j = 0; j < ln; j++) {
                        if (clone[j] === arrayB[i]) {
                            erase(clone, j, 1);
                            j--;
                            ln--;
                        }
                    }
                }
                return clone;
            },
            
            
            slice: ([
                1,
                2
            ].slice(1, undefined).length ? function(array, begin, end) {
                return slice.call(array, begin, end);
            } : function(array, begin, end) {
                
                if (typeof begin === 'undefined') {
                    return slice.call(array);
                }
                if (typeof end === 'undefined') {
                    return slice.call(array, begin);
                }
                return slice.call(array, begin, end);
            }),
            
            sort: function(array, sortFn) {
                return stableSort(array, sortFn || ExtArray.lexicalCompare);
            },
            
            flatten: function(array) {
                var worker = [];
                function rFlatten(a) {
                    var i, ln, v;
                    for (i = 0 , ln = a.length; i < ln; i++) {
                        v = a[i];
                        if (Ext.isArray(v)) {
                            rFlatten(v);
                        } else {
                            worker.push(v);
                        }
                    }
                    return worker;
                }
                return rFlatten(array);
            },
            
            min: function(array, comparisonFn) {
                var min = array[0],
                    i, ln, item;
                for (i = 0 , ln = array.length; i < ln; i++) {
                    item = array[i];
                    if (comparisonFn) {
                        if (comparisonFn(min, item) === 1) {
                            min = item;
                        }
                    } else {
                        if (item < min) {
                            min = item;
                        }
                    }
                }
                return min;
            },
            
            max: function(array, comparisonFn) {
                var max = array[0],
                    i, ln, item;
                for (i = 0 , ln = array.length; i < ln; i++) {
                    item = array[i];
                    if (comparisonFn) {
                        if (comparisonFn(max, item) === -1) {
                            max = item;
                        }
                    } else {
                        if (item > max) {
                            max = item;
                        }
                    }
                }
                return max;
            },
            
            mean: function(array) {
                return array.length > 0 ? ExtArray.sum(array) / array.length : undefined;
            },
            
            sum: function(array) {
                var sum = 0,
                    i, ln, item;
                for (i = 0 , ln = array.length; i < ln; i++) {
                    item = array[i];
                    sum += item;
                }
                return sum;
            },
            
            toMap: function(array, getKey, scope) {
                var map = {},
                    i = array.length;
                if (!getKey) {
                    while (i--) {
                        map[array[i]] = i + 1;
                    }
                } else if (typeof getKey === 'string') {
                    while (i--) {
                        map[array[i][getKey]] = i + 1;
                    }
                } else {
                    while (i--) {
                        map[getKey.call(scope, array[i])] = i + 1;
                    }
                }
                return map;
            },
            
            toValueMap: function(array, getKey, scope, arrayify) {
                var map = {},
                    i = array.length,
                    autoArray, alwaysArray, entry, fn, key, value;
                if (!getKey) {
                    while (i--) {
                        value = array[i];
                        map[value] = value;
                    }
                } else {
                    if (!(fn = (typeof getKey !== 'string'))) {
                        arrayify = scope;
                    }
                    alwaysArray = arrayify === 1;
                    autoArray = arrayify === 2;
                    while (i--) {
                        value = array[i];
                        key = fn ? getKey.call(scope, value) : value[getKey];
                        if (alwaysArray) {
                            if (key in map) {
                                map[key].push(value);
                            } else {
                                map[key] = [
                                    value
                                ];
                            }
                        } else if (autoArray && (key in map)) {
                            if ((entry = map[key]) instanceof Array) {
                                entry.push(value);
                            } else {
                                map[key] = [
                                    entry,
                                    value
                                ];
                            }
                        } else {
                            map[key] = value;
                        }
                    }
                }
                return map;
            },
            _replaceSim: replaceSim,
            
            _spliceSim: spliceSim,
            
            erase: erase,
            
            insert: function(array, index, items) {
                return replace(array, index, 0, items);
            },
            
            replace: replace,
            
            splice: splice,
            
            push: function(target) {
                var len = arguments.length,
                    i = 1,
                    newItem;
                if (target === undefined) {
                    target = [];
                } else if (!Ext.isArray(target)) {
                    target = [
                        target
                    ];
                }
                for (; i < len; i++) {
                    newItem = arguments[i];
                    Array.prototype.push[Ext.isIterable(newItem) ? 'apply' : 'call'](target, newItem);
                }
                return target;
            },
            
            numericSortFn: function(a, b) {
                return a - b;
            }
        };
    
    Ext.each = ExtArray.each;
    ExtArray.union = ExtArray.merge;
    Ext.min = ExtArray.min;
    Ext.max = ExtArray.max;
    Ext.sum = ExtArray.sum;
    Ext.mean = ExtArray.mean;
    Ext.flatten = ExtArray.flatten;
    Ext.clean = ExtArray.clean;
    Ext.unique = ExtArray.unique;
    Ext.pluck = ExtArray.pluck;
    
    Ext.toArray = function() {
        return ExtArray.toArray.apply(ExtArray, arguments);
    };
    return ExtArray;
})();
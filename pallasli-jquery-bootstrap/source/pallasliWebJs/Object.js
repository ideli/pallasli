(function() {
	var TemplateClass = function() {
	}, PallasliObject = Pallasli.Object = {
		equals : (function() {
			var check = function(o1, o2) {
				for ( var key in o1) {
					if (o1.hasOwnProperty(key) && o1[key] !== o2[key]) {
						return false;
					}
				}
				return true;
			};
			return function(object1, object2) {
				return (object1 === object2)
						|| (object1 && object2 && check(object1, object2) && check(
								object2, object1));
			};
		})(),
		clear : function(object) {
			for ( var key in object) {
				delete object[key];
			}
			return object;
		},
		getAllKeys : function(object) {
			var keys = [];
			for (var property in object) {
				keys.push(property);
			}
			return keys;
		},
		getKey : function(object, value) {
			for ( var property in object) {
				if (object.hasOwnProperty(property)
						&& object[property] === value) {
					return property;
				}
			}
			return null;
		},
		getValues : function(object) {
			var values = [];
			for (var property in object) {
				if (object.hasOwnProperty(property)) {
					values.push(object[property]);
				}
			}
			return values;
		},
		getValue : function(object,key) {
			for (var property in object) {
				if (object.hasOwnProperty(property)) {
					return object[property];
				}
			}
			return null;
		},
		getSize : function(object) {
			var size = 0;
			for (var property in object) {
				if (object.hasOwnProperty(property)) {
					size++;
				}
			}
			return size;
		},

		isEmpty : function(object) {
			for ( var key in object) {
				if (object.hasOwnProperty(key)) {
					return false;
				}
			}
			return true;
		},

		
		
		
		each : function(object, fn, scope) {
			var enumerables = Ext.enumerables, i, property;
			scope = scope || object;
			for (property in object) {
				if (object.hasOwnProperty(property)) {
					if (fn.call(scope, property, object[property], object) === false) {
						return;
					}
				}
			}
			if (enumerables) {
				for (i = enumerables.length; i--;) {
					if (object.hasOwnProperty(property = enumerables[i])) {
						if (fn.call(scope, property, object[property], object) === false) {
							return;
						}
					}
				}
			}
		},

		eachValue : function(object, fn, scope) {
			var enumerables = Ext.enumerables, i, property;
			scope = scope || object;
			for (property in object) {
				if (object.hasOwnProperty(property)) {
					if (fn.call(scope, object[property]) === false) {
						return;
					}
				}
			}
			if (enumerables) {
				for (i = enumerables.length; i--;) {
					if (object.hasOwnProperty(property = enumerables[i])) {
						if (fn.call(scope, object[property]) === false) {
							return;
						}
					}
				}
			}
		},

		chain : Object.create || function(object) {
			TemplateClass.prototype = object;
			var result = new TemplateClass();
			TemplateClass.prototype = null;
			return result;
		},

		freeze : Object.freeze ? function(obj, deep) {
			if (obj && typeof obj === 'object' && !Object.isFrozen(obj)) {
				Object.freeze(obj);
				if (deep) {
					for ( var name in obj) {
						ExtObject.freeze(obj[name], deep);
					}
				}
			}
			return obj;
		} : Ext.identityFn,

		toQueryObjects : function(name, value, recursive) {
			var self = ExtObject.toQueryObjects, objects = [], i, ln;
			if (Ext.isArray(value)) {
				for (i = 0, ln = value.length; i < ln; i++) {
					if (recursive) {
						objects = objects.concat(self(name + '[' + i + ']',
								value[i], true));
					} else {
						objects.push({
							name : name,
							value : value[i]
						});
					}
				}
			} else if (Ext.isObject(value)) {
				for (i in value) {
					if (value.hasOwnProperty(i)) {
						if (recursive) {
							objects = objects.concat(self(name + '[' + i + ']',
									value[i], true));
						} else {
							objects.push({
								name : name,
								value : value[i]
							});
						}
					}
				}
			} else {
				objects.push({
					name : name,
					value : value
				});
			}
			return objects;
		},

		toQueryString : function(object, recursive) {
			var paramObjects = [], params = [], i, j, ln, paramObject, value;
			for (i in object) {
				if (object.hasOwnProperty(i)) {
					paramObjects = paramObjects.concat(ExtObject
							.toQueryObjects(i, object[i], recursive));
				}
			}
			for (j = 0, ln = paramObjects.length; j < ln; j++) {
				paramObject = paramObjects[j];
				value = paramObject.value;
				if (Ext.isEmpty(value)) {
					value = '';
				} else if (Ext.isDate(value)) {
					value = Ext.Date.toString(value);
				}
				params.push(encodeURIComponent(paramObject.name) + '='
						+ encodeURIComponent(String(value)));
			}
			return params.join('&');
		},

		fromQueryString : function(queryString, recursive) {
			var parts = queryString.replace(/^\?/, '').split('&'), object = {}, temp, components, name, value, i, ln, part, j, subLn, matchedKeys, matchedName, keys, key, nextKey;
			for (i = 0, ln = parts.length; i < ln; i++) {
				part = parts[i];
				if (part.length > 0) {
					components = part.split('=');
					name = decodeURIComponent(components[0]);
					value = (components[1] !== undefined) ? decodeURIComponent(components[1])
							: '';
					if (!recursive) {
						if (object.hasOwnProperty(name)) {
							if (!Ext.isArray(object[name])) {
								object[name] = [ object[name] ];
							}
							object[name].push(value);
						} else {
							object[name] = value;
						}
					} else {
						matchedKeys = name.match(/(\[):?([^\]]*)\]/g);
						matchedName = name.match(/^([^\[]+)/);
						if (!matchedName) {
							throw new Error(
									'[Ext.Object.fromQueryString] Malformed query string given, failed parsing name from "'
											+ part + '"');
						}
						name = matchedName[0];
						keys = [];
						if (matchedKeys === null) {
							object[name] = value;

							continue;
						}
						for (j = 0, subLn = matchedKeys.length; j < subLn; j++) {
							key = matchedKeys[j];
							key = (key.length === 2) ? '' : key.substring(1,
									key.length - 1);
							keys.push(key);
						}
						keys.unshift(name);
						temp = object;
						for (j = 0, subLn = keys.length; j < subLn; j++) {
							key = keys[j];
							if (j === subLn - 1) {
								if (Ext.isArray(temp) && key === '') {
									temp.push(value);
								} else {
									temp[key] = value;
								}
							} else {
								if (temp[key] === undefined
										|| typeof temp[key] === 'string') {
									nextKey = keys[j + 1];
									temp[key] = (Ext.isNumeric(nextKey) || nextKey === '') ? []
											: {};
								}
								temp = temp[key];
							}
						}
					}
				}
			}
			return object;
		},

		merge : function(destination) {
			var i = 1, ln = arguments.length, mergeFn = ExtObject.merge, cloneFn = Ext.clone, object, key, value, sourceKey;
			for (; i < ln; i++) {
				object = arguments[i];
				for (key in object) {
					value = object[key];
					if (value && value.constructor === Object) {
						sourceKey = destination[key];
						if (sourceKey && sourceKey.constructor === Object) {
							mergeFn(sourceKey, value);
						} else {
							destination[key] = cloneFn(value);
						}
					} else {
						destination[key] = value;
					}
				}
			}
			return destination;
		},

		mergeIf : function(destination) {
			var i = 1, ln = arguments.length, cloneFn = Ext.clone, object, key, value;
			for (; i < ln; i++) {
				object = arguments[i];
				for (key in object) {
					if (!(key in destination)) {
						value = object[key];
						if (value && value.constructor === Object) {
							destination[key] = cloneFn(value);
						} else {
							destination[key] = value;
						}
					}
				}
			}
			return destination;
		},

		fork : function(obj) {
			var ExtArray = Ext.Array, ret, key, value;
			if (obj && obj.constructor === Object) {
				ret = ExtObject.chain(obj);
				for (key in obj) {
					value = obj[key];
					if (value) {
						if (value.constructor === Object) {
							ret[key] = ExtObject.fork(value);
						} else if (value instanceof Array) {
							ret[key] = Ext.Array.clone(value);
						}
					}
				}
			} else {
				ret = obj;
			}
			return ret;
		},
		defineProperty : ('defineProperty' in Object) ? Object.defineProperty
				: function(object, name, descriptor) {
					if (!Object.prototype.__defineGetter__) {
						return;
					}
					if (descriptor.get) {
						object.__defineGetter__(name, descriptor.get);
					}
					if (descriptor.set) {
						object.__defineSetter__(name, descriptor.set);
					}
				},

		classify : function(object) {
			var prototype = object, objectProperties = [], propertyClassesMap = {}, objectClass = function() {
				var i = 0, ln = objectProperties.length, property;
				for (; i < ln; i++) {
					property = objectProperties[i];
					this[property] = new propertyClassesMap[property]();
				}
			}, key, value;
			for (key in object) {
				if (object.hasOwnProperty(key)) {
					value = object[key];
					if (value && value.constructor === Object) {
						objectProperties.push(key);
						propertyClassesMap[key] = ExtObject.classify(value);
					}
				}
			}
			objectClass.prototype = prototype;
			return objectClass;
		}
	};

	Pallasli.merge = Pallasli.Object.merge;
	Pallasli.mergeIf = Pallasli.Object.mergeIf;
}());

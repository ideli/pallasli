
Ext.namespace("Mixky.wasoft.lib");

Mixky.wasoft.lib.dictionaryRenderer = function(appkey, val, dictionaryname){
	var dictionary = Mixky.wasoft.lib.cache.getDictionary(appkey, dictionaryname);
	var display = val;
	if(Ext.isDefined(dictionary)){
		dictionary.each(function(record){
			if(record.get('display') == val || record.get('value') == val){
				display = record.get('display');
				return false;
			}
		});
	}
	return display;
}

Mixky.wasoft.lib.getDictionaryUrlRender = function(appkey, url, value, fn){
	if(Ext.isDefined(appkey) && appkey != ''){
		var app = Mixky.wasoft.cache.Applications[appkey];
		url = app.url + '/' + url;
	}
	Ext.Ajax.request({
		url: url,
		success: function(response, opts){
			var obj = Ext.decode(response.responseText);
			if(obj && Ext.isDefined(obj.key)){
				fn(obj.key, value);
			}
		},
		params: { value: value, appkey:appkey }
	});
}

Mixky.wasoft.lib.getDictionaryDourlRender = function(appkey, url, value, fn, params){
	var pageurl = 'page.do';
	if(Ext.isDefined(appkey) && appkey != ''){
		var app = Mixky.wasoft.cache.Applications[appkey];
		pageurl = app.url + '/' + pageurl;
	}
	Ext.Ajax.request({
		url: pageurl,
		success: function(response, opts){
			var obj = Ext.decode(response.responseText);
			if(obj && Ext.isDefined(obj.key)){
				fn(obj.key, value);
			}
		},
		params: Ext.apply({url:url, value: value, appkey:appkey}, params)
	});	
}

Mixky.wasoft.lib.getDictionaryUrlStore = function(appkey, url){
	if(Ext.isDefined(appkey) && appkey != ''){
		var app = Mixky.wasoft.cache.Applications[appkey];
		url = app.url + '/' + url;
	}
	var store = new Ext.data.Store({
		proxy		: new Ext.data.HttpProxy({
			url			: url
		}),
		reader		: new Ext.data.JsonReader({
			root		: 'results',
			id			:'display',
			fields		:["display","value"]
		})
	});
	store.load();
	return store;
}

Mixky.wasoft.lib.getDictionaryDoUrlStore = function(appkey, url, params){
	var app = Mixky.wasoft.cache.Applications[appkey];
	var store = new Ext.data.Store({
		proxy		: new Ext.data.HttpProxy({
			url			: app.url + "/page.do"
		}),
		baseParams : Ext.apply({url:url}, params),
		reader		: new Ext.data.JsonReader({
			root		: 'results',
			id			:'display',
			fields		:["display","value"]
		})
	});
	store.load();
	return store;
}

Mixky.wasoft.lib.getDictionaryDirectStore = function(fn, params, panel){
	var remoteStore = new Ext.data.DirectStore(Ext.apply({
	    paramsAsHash: params && params.paramOrder,
	    idProperty: 'display',
	    root: 'results',
	    directFn: fn,
	    fields: ['display', 'value']
	}, params));
	// 设置字段侦听
	if(params && params.paramOrder && panel && panel.getFieldValue){
		remoteStore.on('beforeload', function(s, o){
			for(var i=0;i<s.paramOrder.length;i++){
				var value = panel.getFieldValue(s.paramOrder[i]);
				if(Ext.isDefined(value)){
					s.baseParams[s.paramOrder[i]] = value;
					if(Ext.isDefined(o.params)){
						o.params[s.paramOrder[i]] = value;
					}
				}
			}
		});
	}else{
		remoteStore.load();
	}
	return remoteStore;
}
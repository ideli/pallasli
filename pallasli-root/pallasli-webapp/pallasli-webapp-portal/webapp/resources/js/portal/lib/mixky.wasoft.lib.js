

Mixky.wasoft.lib.getNewTableRecordId = function(applicationkey, tablename, fnAfterNew){
	if(!Ext.isDefined(applicationkey)){
		applicationkey = Mixky.wasoft.lib.Context.activeApplicationKey;
	}
	var app = Mixky.wasoft.cache.Applications[applicationkey];
	var directFn = eval(app.keyPrefix + 'AppDirect' + '.getNewTableRecordId');
	directFn(tablename, function(result, e){
		if(result && result.success){
			fnAfterNew(result.id);
		}
	});
};

Mixky.wasoft.lib.handlerMenuConfig = function(cfg){
	if(cfg.name == 'cwgl'||cfg.name == 'wacw'||cfg.name == 'wazj'){
		cfg.handleMouseEvents = false;
	}
};
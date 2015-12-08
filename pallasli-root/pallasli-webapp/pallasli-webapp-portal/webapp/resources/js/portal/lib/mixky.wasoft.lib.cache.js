
Ext.namespace("Mixky.wasoft.lib.cache");
Ext.namespace("Mixky.wasoft.cache");

// private 根据ID获得菜单
Mixky.wasoft.lib.cache.getMenuById = function(id){
	for(n in Mixky.wasoft.cache.Menus){
		if(Mixky.wasoft.cache.Menus[n].id == id){
			return Mixky.wasoft.cache.Menus[n];
		}
	}
};


Mixky.wasoft.lib.cache.getAppModule = function(appkey, modulekey){
	var modules = eval('Mixky.wasoft.cache.' + appkey + '.Modules');
	return modules[modulekey];
}
Mixky.wasoft.lib.cache.getAppModuleMenus = function(appkey){
	return eval('Mixky.wasoft.cache.' + appkey + '.Menus');
}
Mixky.wasoft.lib.cache.getAppModuleMenu = function(appkey, menukey){
	var menus = eval('Mixky.wasoft.cache.' + appkey + '.Menus');
	return menus[menukey];
}
Mixky.wasoft.lib.cache.getAppModuleView = function(appkey, viewkey){
	var views = eval('Mixky.wasoft.cache.' + appkey + '.Views');
	return views[viewkey];
}
Mixky.wasoft.lib.cache.getAppDocument = function(appkey, documentkey){
	var documents = eval('Mixky.wasoft.cache.' + appkey + '.Documents');
	return documents[documentkey];
}
Mixky.wasoft.lib.cache.getAppDocumentType = function(appkey, documenttypekey){
	var documenttypes = eval('Mixky.wasoft.cache.' + appkey + '.DocumentTypes');
	return documenttypes[documenttypekey];
}
Mixky.wasoft.lib.cache.getDictionary = function(appkey, dictionarykey){
	var dictionarys;
	if(Ext.isDefined(appkey) && appkey != ''){
		dictionarys = eval('Mixky.wasoft.cache.' + appkey + '.Dictionarys');
	}else{
		dictionarys = Mixky.wasoft.cache.Dictionarys;
	}
	return dictionarys[dictionarykey];
}
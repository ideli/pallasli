pallasli.fieldConfigs = {
};
pallasli.getFieldConfigs=function(type){
	return pallasli.fieldConfigs[type];
};
pallasli.getFieldConfig=function(type,prop){
	var configs = pallasli.getFieldConfigs(type);
	for(var i =0;i<configs.length;i++){
		if(configs[i].name==prop){
			return configs[i];
		}
	}
	return null;
};
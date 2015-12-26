 
//requireType: 含有类别信息的字串值，如“timeout”，“nodefine”， “scripterror”
//requireModules: 超时的模块名/URL数组。
//IE 6-8中的script.onerror无效
//IE 9+中script.onerror有效
requirejs.onError=function(error){
	console.log(error);
};
dragJs= {};
dragJs.properties={}; 
var baseurl='../designerApp'; 
console.log(baseurl);

requirejs.config({
	// By default load any module IDs from js/lib
	baseUrl : baseurl,
	// except, if the module ID starts with "app",
	// load it from the js/app directory. paths
	// config is relative to the baseUrl, and
	// never includes a ".js" extension since
	// the paths config could be for a directory.
	paths : {
		properties : 'properties' 
	}
});
 shineyue.fieldConfigs={};
// Start the main app logic.
requirejs([ 'properties/fieldconfigs'], function( ) {
	// jQuery, canvas and the app/sub module are all
	// loaded and can be used here now.
	console.log(shineyue.fieldConfigs)
	return;  
});
 
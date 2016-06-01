//JSONP的callback参数为"callback"，因此"callback=define"告诉API将JSON响应包裹到一个"define()"中


//requirejs.undef()用来undefine一个模块,该功能仅在无其他模块持有该模块时的错误处理中
//RequireJS使用head.appendChild()将每一个依赖加载为一个script标签。

//?????shim: 为那些没有使用define()来声明依赖关系、设置模块的"浏览器全局变量注入"型脚本做依赖和导出配置
/**
 * map 配置不同目录下的文件引用脚本文件时使用不同的js文件
requirejs.config({
    map: {
        '*': {
            'foo': 'foo1.2'//foo1.2.js
        },
        'some/oldmodule': {
            'foo': 'foo1.0'//foo1.0.js
        }
    }
});
意思是除了“some/oldmodule”外的所有模块，当要用“foo”时，使用“foo1.2”来替代。对于“some/oldmodule”自己，则使用“foo1.0”。
**/
/**
 * 给不同的js文件配置常量
 * requirejs.config({
    config: {
        'bar': {
            size: 'large'
        },
        'baz': {
            color: 'blue'
        }
    }
});

//bar.js, which uses simplified CJS wrapping:
//http://requirejs.org/docs/whyamd.html#sugar
define(function (require, exports, module) {
    //Will be the value 'large'
    var size = module.config().size;
});
 */
/**
 * 默认按目录加载目录下的main.js
 * 
 * //main.js contents
//Pass a config object to require
require.config({
    "packages": ["cart", "store"]
});

require(["cart", "store", "store/util"],
function (cart,   store,   util) {
    //use the modules as usual.
});

//自定义目录加载文件
require.config({
    packages: [
        "cart",
        {
            name: "store",//目录名
            main: "store"//目录下js文件名
        }
    ]
});
 * 
 */
/**
 * 加载页面js过大可能超时，考虑增加waitSeconds配置项的值
 * 
 * require(['domReady'], function (domReady) {
  domReady(function () {
    //This function is called once the DOM is ready.
    //It will be safe to query the DOM and manipulate
    //DOM nodes in this function.
  });
});

或使用插件

require(['domReady!'], function (doc) {
    //This function is called once the DOM is ready,
    //notice the value for 'domReady!' is the current
    //document.
});
 */
require(["http://example.com/api/data.json?callback=define"],
    function (data) {
        //JSONP的这种用法应仅限于应用的初始化中。一旦JSONP服务超时，
	    //其他通过define()定义了的模块也可能得不得执行，错误处理不是十分健壮。
        console.log(data);
    }
);
//requireType: 含有类别信息的字串值，如“timeout”，“nodefine”， “scripterror”
//requireModules: 超时的模块名/URL数组。
//IE 6-8中的script.onerror无效
//IE 9+中script.onerror有效
requirejs.onError=function(error){
	console.log(error);
};
dragJs={};
dragJs.dragele={};
dragJs.comp={};
dragJs.dropzone={};
var locationUrl=location.href;
console.log(location); 
var baseurl; 
if(locationUrl.substr(0,4)=="file"){
	baseurl='dragJs';
}else{
	baseurl='/dragJs';
}
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
		dragele : 'dragele',
		jquery : ['http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min','../../../dependences/jquery/1.11.3/jquery.min']
	}
});

// Start the main app logic.
requirejs([ 'jquery','Utils', 'Object', 'dragele/Panel' ], function($, canvas, sub) {
	// jQuery, canvas and the app/sub module are all
	// loaded and can be used here now.
});
 
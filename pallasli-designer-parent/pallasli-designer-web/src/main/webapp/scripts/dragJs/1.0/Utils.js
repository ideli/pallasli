
define(["require", "dragele/Panel"], function(require) {
    var mod = require("dragele/Panel");
    var cssUrl = require.toUrl("./style.css");
    console.log(cssUrl);
    
    dragJs.Utils={   
    		//根据拖拽结果和配置信息生成相应代码
    		genSource:function(){},
    		//设置源码根目录
    		setPath:function(){},
    		//保存源码
    		saveSource:function(){},
    		//获取源码
    		getSource:function(){},
    		//初始化编辑区域
    		initEditorView:function(){},
    		//初始化编辑区域前添加编辑元素
    		appendEditorDataContent:function(){},
    		//根据源码初始化预览区域
    		initPreViewBySource:function(){},
    		//初始化拖拽元素
    		initDragSource:function(){},
    	};

});

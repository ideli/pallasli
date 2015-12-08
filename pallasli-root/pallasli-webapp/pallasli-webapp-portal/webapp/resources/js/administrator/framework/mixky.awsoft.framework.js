Mixky.awsoft.Framework = function(app){
	this.app = app;

    // 应用程序标题区域
    this.titlebar = new Mixky.awsoft.Titlebar({
    	region : 'north',
        split : true,
        height : 39,
        minSize : 39,
        maxSize : 39,
        hideCollapseTool : true,
        collapsible: true,
        collapseMode:'mini'
    });
    // 应用程序操作条
    this.toolbar = new Mixky.awsoft.Toolbar({});
    // 应用程序导航区域
    this.outline = new Mixky.awsoft.Outline({
    	region : 'west',
        split : true,
        width : 250,
        minSize : 150,
        maxSize : 500,
        hideCollapseTool : true,
        collapsible: true,
        collapseMode:'mini'
    	
    });
    // 应用程序工作区域
    this.workspace = new Mixky.awsoft.Workspace({
    	region : 'center'
    });
	// 创建视图架构
    var view = new Ext.Viewport({
    	layout :'border',
    	items : [ this.titlebar, new Ext.Panel({
    		region : 'center',
    		border : false,
    		tbar : this.toolbar,
        	layout : 'border',
        	items : [this.outline, this.workspace]
    	})]
	});
};

Mixky.awsoft.Framework.prototype = {
	// 设置消息窗口显示位置
	getAnimateTarget : function(){
		return document;
	},
    closeEditor : function(key){
		return this.workspace.removeObject(key);
    },
    
    openObject : function(oid){
		this.outline.selectObject(oid);
		var cmp = this.workspace.openEditor(oid);
    },
    
    activateObject : function(oid){
    	this.outline.selectObject(oid);
    	this.workspace.selectObject(oid);
    },

    deleteObject : function(key){
    	this.outline.removeObject(key);
    	this.workspace.removeObject(key);
    }
}
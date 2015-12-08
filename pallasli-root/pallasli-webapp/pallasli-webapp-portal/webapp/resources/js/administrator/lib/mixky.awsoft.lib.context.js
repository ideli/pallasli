Ext.namespace("Mixky.awsoft.lib");
// 初始化应用程序上下文
Mixky.awsoft.lib.Context = {};
// 切换客户端选中的对象
Mixky.awsoft.lib.Context.activateObject = function(oid, cmp){
	// oid ：描述客户端实例的基本信息{id,key,mclass}
	// cmp ：当前选中对象的容器实例
	if(!oid){
		Mixky.awsoft.lib.Context.activatedObject = undefined;
	}else if(Mixky.awsoft.lib.Context.activatedObject && 
			oid.key == Mixky.awsoft.lib.Context.activatedObject.key && 
			oid.mclass == Mixky.awsoft.lib.Context.activatedObject.mclass){
		return;
	}else{
		Mixky.awsoft.lib.Context.activatedObject = oid;
		Mixky.awsoft.lib.Class.setActionEnabled(cmp);
		MixkyApp.framework.activateObject(oid);
	}
};

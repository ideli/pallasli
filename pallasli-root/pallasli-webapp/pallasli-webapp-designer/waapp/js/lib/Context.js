Ext.define('Pallas.extDesigner.waapp.lib.Context', {
	"statics" : {
		"type" : "query",
		activateObject : function(oid, cmp) {
			// oid ：描述客户端实例的基本信息{id,key,mclass}
			// cmp ：当前选中对象的容器实例
			if (!oid) {
				this.activatedObject = undefined;
			} else if (this.activatedObject
					&& oid.key == this.activatedObject.key
					&& oid.mclass == this.activatedObject.mclass) {
				return;
			} else {
				this.activatedObject = oid;
				if (cmp && cmp.ownerFramework) {
					Pallas.extDesigner.waapp.utils.ActionOperate
							.setActionEnabled(cmp);
					cmp.ownerFramework.activateObject(oid);
				}
			}
		}
	}
});
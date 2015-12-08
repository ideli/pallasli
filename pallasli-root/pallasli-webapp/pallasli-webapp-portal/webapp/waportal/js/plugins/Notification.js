Ext.define('Pallas.portal.waapp.plugins.Notification', {
	extend : 'Ext.Window',
	alias:"widget.notification",
	initComponent : function() {
		Ext.apply(this, {
			iconCls : this.iconCls || 'x-icon-information',
			width : 200,
			autoHeight : true,
			closable : true,
			plain : false,
			draggable : false,
			bodyStyle : 'text-align:left;padding:10px;',
			resizable : false
		});
		if (this.autoDestroy) {
			this.task = new Ext.util.DelayedTask(this.close, this);
		} else {
			this.closable = true;
		}
		this.callParent();
	},
	setMessage : function(msg) {
		this.body.update(msg);
	},
	setTitle : function(title, iconCls) {
		this.callParent(arguments);
	},
	onRender : function(ct, position) {
		this.callParent(arguments);
	},
	onDestroy : function() {
		Mixky.lib.NotificationMgr.positions.remove(this.pos);
		Mixky.lib.Notification.superclass.onDestroy.call(this);
	},
	afterShow : function() {
		this.callParent( );
		this.on('move', function() {
			Mixky.lib.NotificationMgr.positions.remove(this.pos);
			if (this.autoDestroy) {
				this.task.cancel();
			}
		}, this);
		if (this.autoDestroy) {
			this.task.delay(this.hideDelay || 5000);
		}
	},
	animShow : function() {
		this.pos = 0;
		while (Mixky.lib.NotificationMgr.positions.indexOf(this.pos) > -1) {
			this.pos++;
		}
		Mixky.lib.NotificationMgr.positions.push(this.pos);
		this.setSize(200, 100);
		this.el.alignTo(this.animateTarget || document, "br-br", [ -1,
				-1 - ((this.getSize().height + 10) * this.pos) ]);
		this.el.slideIn('b', {
			duration : .7,
			callback : this.afterShow,
			scope : this
		});
	},
	animHide : function() {
		Mixky.lib.NotificationMgr.positions.remove(this.pos);
		this.el.ghost("b", {
			duration : 1,
			remove : true
		});
	}

});
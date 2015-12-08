/*
 * qWikiOffice Desktop 0.8.1
 * Copyright(c) 2007-2008, Integrated Technologies, Inc.
 * licensing@qwikioffice.com
 * 
 * http://www.qwikioffice.com/license
 *
 * Ext.ux.Notification is based on code from the Ext JS forum.
 * I have made some minor modifications.
 */
Ext.namespace("Mixky.lib");

Mixky.lib.NotificationMgr = {
	positions: []
};

Mixky.lib.Notification = Ext.extend(Ext.Window, {
    initComponent : function(){
	Ext.apply(this, {
	    iconCls: this.iconCls || 'x-icon-information'
	    , width: 200
	    , autoHeight: true
	    , closable: true
	    , plain: false
	    , draggable: false
	    , bodyStyle: 'text-align:left;padding:10px;'
		, resizable: false
	});
	if(this.autoDestroy){
	    this.task = new Ext.util.DelayedTask(this.close, this);
	}else{
	    this.closable = true;
	}
	Mixky.lib.Notification.superclass.initComponent.call(this);
    }, 
    setMessage : function(msg){
	this.body.update(msg);
    },
    setTitle : function(title, iconCls){
	Mixky.lib.Notification.superclass.setTitle.call(this, title, iconCls||this.iconCls);
    },
    onRender : function(ct, position) {
	Mixky.lib.Notification.superclass.onRender.call(this, ct, position);
    },
    onDestroy : function(){
	Mixky.lib.NotificationMgr.positions.remove(this.pos);
	Mixky.lib.Notification.superclass.onDestroy.call(this);
    },
    afterShow : function(){
	Mixky.lib.Notification.superclass.afterShow.call(this);
	this.on('move', function(){
	    Mixky.lib.NotificationMgr.positions.remove(this.pos);
	    if(this.autoDestroy){
    	    	this.task.cancel();
    	   }
	}, this);
	if(this.autoDestroy){
	    this.task.delay(this.hideDelay || 5000);
	}
    }, 
    animShow : function(){
	this.pos = 0;
	while(Mixky.lib.NotificationMgr.positions.indexOf(this.pos)>-1){
	    this.pos++;
	}
	Mixky.lib.NotificationMgr.positions.push(this.pos);
	this.setSize(200,100);
	this.el.alignTo(this.animateTarget || document, "br-br", [ -1, -1-((this.getSize().height+10)*this.pos) ]);
	this.el.slideIn('b', {
	    duration: .7,
	    callback: this.afterShow,
	    scope: this
	});
    }, 
    animHide : function(){
	Mixky.lib.NotificationMgr.positions.remove(this.pos);
        this.el.ghost("b", {
    		duration: 1,
    		remove: true
        });
    }
});
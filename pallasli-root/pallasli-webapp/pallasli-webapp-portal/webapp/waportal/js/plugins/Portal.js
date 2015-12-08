
Ext.define('Pallas.portal.waapp.plugins.Portal' , {
	extend:"Ext.panel.Panel",
	requires : [ 'Pallas.portal.waapp.plugins.DropZone'],
	alias:"widget.uxportal",
    layout : 'column',
    autoScroll : true,
    cls : 'x-portal',
    defaultType : 'portalcolumn',
    
    initComponent : function(){
        this.callParent();
        this.addEvents({
            validatedrop:true,
            beforedragover:true,
            dragover:true,
            beforedrop:true,
            drop:true
        });
    },

    initEvents : function(){
        this.callParent();
       this.dd = new Pallas.portal.waapp.plugins.DropZone( this,this.dropConfig);
    },
    
    beforeDestroy : function() {
        if(this.dd){
            this.dd.unreg();
        }
        this.callParent();
    }
});


 

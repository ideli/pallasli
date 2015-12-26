Ext.define('MyDesktop.UploadWindow', {
    extend: 'Ext.ux.desktop.Module',

    requires: [
        'Ext.data.ArrayStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.grid.RowNumberer'
    ],

    id:'upload-win',

    init : function(){
        this.launcher = {
            text: '上传',
            iconCls:'icon-upload'
        };
    },

    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('grid-win');
        if(!win){
            win = desktop.createWindow({
                id: 'grid-win',
                title:'上传',
                width:1200,
                height:540,
                iconCls: 'icon-upload',
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
                items: [
                    {xtype: 'panel',
            	        border: false,
            	        loader: {
            	            url: 'jsppage.do?url=pageOffice/uploadList',
            	            autoLoad:true,
            	            scripts:true
            	        }
                    }
                ]
            });
        }
        return win;
    },

    statics: {}
});


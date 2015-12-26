Ext.define('MyDesktop.PallasliDesignerModule', {
    extend: 'Ext.ux.desktop.Module',

    requires: [
        'Ext.data.ArrayStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.grid.RowNumberer'
    ],

    id:'pallasli-designer-page',

    init : function(){
        this.launcher = {
            text: '上传',
            iconCls:'icon-upload'
        };
    },

    createWindow : function(){
    	window.open("/pallasli-webapp-designer/pages/home.html");
    },

    statics: {}
});


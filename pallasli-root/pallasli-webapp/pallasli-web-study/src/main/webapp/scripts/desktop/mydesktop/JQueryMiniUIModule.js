Ext.define('MyDesktop.JQueryMiniUIModule', {
    extend: 'Ext.ux.desktop.Module',

    requires: [
        'Ext.data.ArrayStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.grid.RowNumberer'
    ],

    id:'jQueryMiniUI-page',

    init : function(){
        this.launcher = {
            text: '上传',
            iconCls:'icon-upload'
        };
    },

    createWindow : function(){
    	window.open("jQueryMiniUI/demo/index.html");
    },

    statics: {}
});


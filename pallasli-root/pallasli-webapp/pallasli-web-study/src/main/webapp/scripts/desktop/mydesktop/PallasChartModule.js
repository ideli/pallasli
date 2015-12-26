Ext.define('MyDesktop.PallasChartModule', {
    extend: 'Ext.ux.desktop.Module',

    requires: [
        'Ext.data.ArrayStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.grid.RowNumberer'
    ],

    id:'pallaschart-page',

    init : function(){
        this.launcher = {
            text: '图表',
            iconCls:'icon-upload'
        };
    },

    createWindow : function(){
    	window.open("jsppage.do?url=pallasChart/index");
    },

    statics: {}
});


Ext.define('MyDesktop.PallasJbpmModule', {
    extend: 'Ext.ux.desktop.Module',

    requires: [
        'Ext.data.ArrayStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.grid.RowNumberer'
    ],

    id:'pallasjbpm-page',

    init : function(){
        this.launcher = {
            text: '图表',
            iconCls:'icon-upload'
        };
    },

    createWindow : function(){
    	window.open("/pallasli-webapp-activiti/home.do");
    },

    statics: {}
});


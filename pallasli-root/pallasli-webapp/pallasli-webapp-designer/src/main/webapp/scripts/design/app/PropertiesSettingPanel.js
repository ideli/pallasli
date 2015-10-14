Ext.define('Pallas.design.portal.PropertiesSettingPanel', {
	extend : "Ext.grid.Panel",
	border : true,
	autoHeight : true,
	collapsible : false,
	tbar:[{xtype:'label',text:'字段配置'},'->',{text:'保存'}],
    border:false,
    columns: [
        { header: '配置项', dataIndex: 'configName', flex : 1 },
        { header: '配置值',  dataIndex: 'PROPERTY_VALUE', flex: 3,
            field: {
            xtype: 'textfield',
            allowBlank: false
        } }
    ],
	initComponent : function() {
		var me = this;
		var cellEditing2 = Ext.create('Ext.grid.plugin.CellEditing', {
	        clicksToEdit: 1
	     });

	   me.plugins=[cellEditing2];
        me.callParent();
	}
});

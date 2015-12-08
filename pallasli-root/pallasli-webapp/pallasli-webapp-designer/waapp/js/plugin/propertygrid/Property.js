Ext.define('Pallas.extDesigner.waapp.plugin.propertygrid.Property', {
	extend : 'Ext.data.Model',

	fields : [ {
		name : 'name',
		type : 'string'
	}, {
		name : 'value'
	}, {
		name : 'hiddenValue'
	}, {
		name : 'order',
		type : 'number'
	}, {
		name : "note",
		type : 'string'
	}, {
		name : "xeditor",
		type : 'string'
	}, {
		name : "displayName",
		type : 'string'
	} ],
	idProperty : 'name'
});
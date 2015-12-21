Ext.define('Pallas.activitiDesigner.advanceAttributePanel.Message', {
	extend : "Pallas.activitiDesigner.advanceAttributePanel.AbstractPanel",
	title : "消息",
	simpleName : "message",
	alias : [ 'advanceattributepanel.message' ],
	initComponent : function() {
		var me = this;
		Ext.create('Ext.data.Store', {
			storeId : 'simpsonsStore',
			fields : [ 'name', 'email', 'phone' ],
			data : {
				'items' : [ {
					'name' : 'Lisa',
					"email" : "lisa@simpsons.com",
					"phone" : "555-111-1224"
				}, {
					'name' : 'Bart',
					"email" : "bart@simpsons.com",
					"phone" : "555-222-1234"
				}, {
					'name' : 'Homer',
					"email" : "home@simpsons.com",
					"phone" : "555-222-1244"
				}, {
					'name' : 'Marge',
					"email" : "marge@simpsons.com",
					"phone" : "555-222-1254"
				} ]
			},
			proxy : {
				type : 'memory',
				reader : {
					type : 'json',
					root : 'items'
				}
			}
		});

		var jieshourenGrid = Ext.create('Ext.grid.Panel', {
			title : "消息接收人",
			columnWidth : 1,
			store : Ext.data.StoreManager.lookup('simpsonsStore'),
			columns : [ {
				text : 'Name',
				dataIndex : 'name'
			}, {
				text : 'Email',
				dataIndex : 'email',
				flex : 1
			}, {
				text : 'Phone',
				dataIndex : 'phone'
			} ],
			height : 200
		});
		me.items = [ {
			xtype : "radiogroup",
			fieldLabel : "是否发送消息",
			items : [ {
				boxLabel : '是',
				name : 'size',
				inputValue : '1'
			}, {
				boxLabel : '否',
				name : 'size',
				inputValue : '0',
				checked : true
			} ]
		}, {
			xtype : "combo",
			fieldLabel : "消息发送方式",
			value : " "
		}, {
			fieldLabel : "主题",
			value : " "
		}, {
			fieldLabel : "发送内容",
			value : " "
		}, jieshourenGrid ];
		me.callParent();
	}
});

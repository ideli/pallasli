Ext.define('Pallas.design.home.SysMenuDesignPanel', {
	extend : "Ext.panel.Panel",
	requires : [ 'Pallas.design.Components'],
	border : false,
	autoHeight : true,
	//collapsible : true,
	//bodyStyle:"padding:0px;margin:0px;",
	bbar:[{text:"保存"},{text:"清空"}],
	initComponent : function() {
		var me = this;
		var store=Ext.create('Ext.data.Store', {
    	    fields:['name', 'caption', 'key', 'config','comments'],
    	    //paramOrder: ['sort', 'dir', 'start', 'limit','params'], 
    	    //paramOrder: ['sort', 'dir', 'start', 'limit','pre'],
    	    paramOrder: ['params'],
	        autoLoad:true,
	        pageSize:5,
    	    proxy: {
    	        type: 'direct',
    	        autoLoad:true,
    	        directFn:TableDirect.getTables,
    	        reader: {
            	    idProperty: 'name',
            	    totalProperty: 'members.total',
    	            type: 'json',
    	            root: 'members.results'
    	        },
                extraParams:{prefixion:"<%=prefixion%>"}
    	    }
    	});        
    	 var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
    	        clicksToEdit: 1
    	     });
		
		var tmpGrid=Ext.create('Ext.grid.Panel', {
			title:"下级菜单",
            border:false,
		    store: store,
		    columnWidth:0.9,
		    columns: [
		        { header: '名称', dataIndex: 'name', flex : 2 },
		        { header: '操作类型(页面、文档)',  dataIndex: 'caption', flex: 2,
		            field: {
	                xtype: 'textfield',
	                allowBlank: false
	            } },
		        { header: '页面',  dataIndex: 'key', flex: 1,
		            field: {
	                xtype: 'textfield',
	                allowBlank: false
	            } },
		        { header: '是否菜单',  dataIndex: 'key', flex: 1,
		            field: {
	                xtype: 'textfield',
	                allowBlank: false
	            } },
		        { header: '页面路径',  dataIndex: 'comments', flex: 4,
		            field: {
	                xtype: 'textfield',
	                allowBlank: false
	            } }
		    ],
		    width: 800,
		    plugins: [cellEditing],
		    //分页功能   
            bbar: Ext.create('Ext.PagingToolbar', {   
                            store: store,   
                            displayInfo: true,   
                            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
                            emptyMsg: "没有数据"   
                  } ) 
		});
		var sysMenuDesignForm=Ext.create("Ext.form.Panel",{
			border:false,
			layout:"column",
			bodyStyle:"padding:3px;margin:0px;",
			items:[
			       {xtype:"textfield",fieldLabel:"菜单主键",columnWidth:0.25},
			       {xtype:"textfield",fieldLabel:"菜单名",columnWidth:0.25},
			       {xtype: 'radiogroup',fieldLabel:"是否包含子菜单",columnWidth:0.15,
			           columns: 2,
			           vertical: true,
			           items: [
			               { boxLabel: '是', name: 'rb', inputValue: '1' },
			               { boxLabel: '否', name: 'rb', inputValue: '0', checked: true}
			           ]},
			       {xtype:"textfield",fieldLabel:"父菜单",columnWidth:0.25},
			       {xtype:"textarea",rows:3,fieldLabel:"末级菜单操作",columnWidth:0.9},
			       tmpGrid 
			
			]
		});
		me.items=[sysMenuDesignForm];
		//标题
		Pallas.design.ComponentSelectPanel.superclass.initComponent.call(this);
	}
});

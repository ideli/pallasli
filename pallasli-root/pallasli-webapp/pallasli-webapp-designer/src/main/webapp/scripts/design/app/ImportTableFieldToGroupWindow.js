Ext.define('Pallas.design.portal.ImportTableFieldToGroupWindow', {
	extend : "Ext.window.Window",
	//requires : [ 'Pallas.design.portal.ModuleTreePanel' ],
    modal: true,
    constrain:true,
    fieldsetName:'',
    projectName:'',
	needRefreshGrid:null,
	height:500,
	width:1000,
    layout: {
        type: 'fit',
        padding: 5
    },
	initComponent : function() {
		var me = this; 

		var tablesStore=Ext.create('Ext.data.Store', {
    		model: 'Table', 
			autoLoad:false,
			proxy: {
    	        type: 'direct',
    	        directFn:TableDirectAction.getTables
    	    }
		});

		var tableCombobox=Ext.create('Ext.form.ComboBox', {
		    fieldLabel: '选择表',
		    name:"tableName",
		    store: tablesStore,
		    queryMode: 'remote',
		    displayField: 'tableCaption',
		    valueField: 'tableName',
		    anchor:"100%"
		});
		tableCombobox.on('select',function(field,records){ 
			
			Ext.apply(importTablesStore.proxy.extraParams,records[0].data); 
			importTablesStore.reload();
		});
		var importTablesStore=Ext.create('Ext.data.Store', {
    		model: 'TableField', 
			autoLoad:false,
			proxy: {
    	        type: 'direct',
    	        directFn:TableDirectAction.getTableColumns
    	    }
		});

   		 
   		  
		var saveBtn={
	          	  text:"引入分组",handler:function(){ 
	          		  var records=importTablePanel.getSelectionModel().getSelection();
	        		  var recordData=[];
	        		  var configData=[];
	        		  for(var i=0;i<records.length;i++){
	        			  recordData[i]=records[i].data;
	        			  recordData[i].fieldsetName=me.fieldsetName;
	        			  recordData[i].projectName=me.projectName;
	        		  }
	        		  
	        		  FieldsetDirectAction.importFieldToFieldSet(recordData,function(result){
	            			if(result.success){   
	                	    	if(me.needRefreshGrid){
	                	    		me.needRefreshGrid.getStore().reload();
	                	    	}
	                	    }
	              		});
	        	  }
	          };
    	 var importColumns = [
    			{"dataIndex":"fieldName","text":"列名","flex":2,"align":"left"},
    			{"dataIndex":"fieldCaption","text":"中文名","flex":2,"align":"left",
					field: {
		                xtype: 'textfield',
		                allowBlank: false
    				}
				},
    			{"dataIndex":"fieldLength","text":"长度","flex":2,"align":"left",
					field: {
		                xtype: 'textfield',
		                allowBlank: false
    				}
				},
    			{"dataIndex":"fieldPrecision","text":"精度","flex":2,"align":"left",
					field: {
		                xtype: 'textfield',
		                allowBlank: false
    				}
				},
    			{"dataIndex":"fieldType","text":"类型","flex":2,"align":"left",
					field: {
		                xtype: 'textfield',
		                allowBlank: false
    				}
				},
    			{"dataIndex":"fieldAllowBlank","text":"允许空","flex":2,"align":"left",
					field: {
		                xtype: 'textfield',
		                allowBlank: false
    				}
				},
    			{"dataIndex":"fieldDefault","text":"默认值","flex":2,"align":"left",
					field: {
		                xtype: 'textfield',
		                allowBlank: false
    				}
				},
				{"dataIndex":"id","text":"入库id","flex":1,"align":"left"},
				{"dataIndex":"tableName","hidden":true}
			];
 		var tmpImportTableSm=Ext.create('Ext.selection.CheckboxModel');
     	var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
 	        clicksToEdit: 1
 	     }); 
	    var importTablePanel=Ext.create("Ext.grid.Panel",{ 
	        autoHeight: true, 
	        columnWidth :1,
			stripeRows: true,
			lineBreak : false,
			cellSelect : true,
			loadMask: {msg:'正在装载...'},
			tbar:[tableCombobox,saveBtn],
		    plugins: [cellEditing],
			selModel:tmpImportTableSm,
			columns : importColumns,
			store : importTablesStore
	    }); 
        me.items= [importTablePanel];
        me.callParent();
	}
});

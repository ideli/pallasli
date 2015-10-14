Ext.define('Pallas.design.portal.ImportTableFieldWindow', {
	extend : "Ext.window.Window",
	//requires : [ 'Pallas.design.portal.ModuleTreePanel' ],
    modal: true,
    constrain:true,
    tableName:'',
	height:500,
	width:1000,
	needRefreshGrid:null,
    layout: {
        type: 'fit',
        padding: 5
    },
	initComponent : function() {
		var me = this; 

		var tmpDatabaseTypeStore=Ext.create('Ext.data.Store', {
    		model: 'DatabaseType', 
			data:[['1','orcal'],['2','mysql']]
		});
		var tmpDatabaseTypeCombobox=Ext.create('Ext.form.ComboBox', {
		    fieldLabel: '数据库类型',
		    margin: '5 5 5 5',
		    columnWidth:0.25,
		    name:"databaseType",
		    store: tmpDatabaseTypeStore,
		    queryMode: 'local',
		    displayField: 'databaseTypeCaption',
		    valueField: 'databaseType',
		    anchor:"100%"
		});
		var importTablesStore=Ext.create('Ext.data.Store', {
    		model: 'ImportTableField', 
			autoLoad:false
		});
		
		var tmpDatabaseStore=Ext.create('Ext.data.Store', {
    		model: 'Database', 
			autoLoad:true
		}); 

		var tmpDatabaseCombobox=Ext.create('Ext.form.ComboBox', {
		    fieldLabel: '数据库实例',
		    margin: '5 5 5 5',
		    columnWidth:0.25,
		    name:"databaseSchema",
		    store: tmpDatabaseStore,
		    queryMode: 'local',
		    displayField: 'databaseCaption',
		    valueField: 'databaseName',
		    anchor:"100%"
		});
		tmpDatabaseCombobox.on('select',function(field,records){
			var data=records[0].data;
			form_addType.getForm().setValues(data);
		});
		var loadBtn={text:"加载表字段",xtype:"button",columnWidth:0.2,handler:function(){

   		 var formValues=form_addType.getForm().getValues(); 
   		  
				Ext.apply(importTablesStore.proxy.extraParams.table,formValues);
				Ext.apply(importTablesStore.proxy.extraParams.database,formValues);
			importTablesStore.load();
     	}};
		var saveBtn={
          	  text:"导入数据库",handler:function(){ 
          		  var records=importTablePanel.getSelectionModel().getSelection();
        		  var recordData=[];
        		  for(var i=0;i<records.length;i++){
        			  recordData[i]=records[i].data;
        		  }
        		  DatabaseDesignDirectAction.saveTableFields(recordData,function(result){
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
    			{"dataIndex":"fieldAllowblank","text":"允许空","flex":2,"align":"left",
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
				{"dataIndex":"projectName","hidden":true},
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
			tbar:[loadBtn,saveBtn],
		    plugins: [cellEditing],
			selModel:tmpImportTableSm,
			columns : importColumns,
			store : importTablesStore
	    });
    	 var form_addType=Ext.create("Ext.form.Panel",{
              border: false,
              fieldDefaults: {
                  labelWidth: 80
              },
              defaultType: 'textfield',
              layout:'column',
              bodyPadding: 5,
              autoScroll:true,
              floatable: false,
              items:[
                      	tmpDatabaseTypeCombobox,tmpDatabaseCombobox,
                      	{fieldLabel:"ip",margin: '5 5 5 5',name:"databaseIp",columnWidth:0.25,anchor:"100%"},
                      	{fieldLabel:"端口",margin: '5 5 5 5',name:"databasePort",columnWidth:0.25,anchor:"100%"},
                      	{fieldLabel:"项目",margin: '5 5 5 5',name:"projectName",columnWidth:0.25,anchor:"100%"},
                        {fieldLabel:"用户名",margin: '5 5 5 5',name:"databaseUser",columnWidth:0.25,anchor:"100%"},
                      	{fieldLabel:"用户密码",margin: '5 5 5 5',name:"databasePassword",columnWidth:0.25,anchor:"100%"},
                      	{fieldLabel:"表名",margin: '5 5 5 5',name:"tableName",value:me.tableName,readOnly:true,columnWidth:0.25,anchor:"100%"},
                      	{text:"保存数据库",margin: '5 5 5 5',xtype:"button",name:"databasePassword",columnWidth:0.25,anchor:"100%",
                      		handler:function(){
            			       	var formValues=form_addType.getForm().getValues(); 
            			        var rec=new Database();  
            			        Ext.apply(rec.data,formValues);
            					Ext.apply(rec.data,{
            						databaseName:formValues['databaseSchema'],
            						databaseCaption:formValues['databaseSchema']
            					});
				            	rec.save({ 
				            	    success:function(rec,opt){  
				            	    	
				            	    },  
				            	    failure:function(e,op){  
				            	        Ext.Msg.alert("发生错误",op.error);  
				            	    },  
				            	    scope:form_addType  
				            	}); 
                      			
                      		}},
                      	importTablePanel
              ]
         });   
        me.items= [form_addType];
        me.callParent();
	}
});

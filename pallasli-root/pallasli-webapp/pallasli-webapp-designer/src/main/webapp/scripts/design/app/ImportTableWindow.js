Ext.define('Pallas.design.portal.ImportTableWindow', {
	extend : "Ext.window.Window",
    modal: true,
    constrain:true,
	height:500,
	width:1000,
	needRefreshGrid:null,
    layout: {type: 'fit',padding: 5 },
	initComponent : function() {
		var me = this; 
		var tmpDatabaseStore=Ext.create('Ext.data.Store', {
    		model: 'Database', 
			autoLoad:true
		});
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
		var tmpImportTableStore=Ext.create('Ext.data.Store', {
    		model: 'ImportTable', 
			autoLoad:false
		});
		
		var tmpImportTableSm=Ext.create('Ext.selection.CheckboxModel');
    	var tmpImportTableColumns = [
    	            {"dataIndex":"projectName","hidden":true},
    	            {"dataIndex":"tableName","text":"表名","flex":2,"align":"left"},
    				{"dataIndex":"tableCaption","text":"中文名","flex":2,"align":"left",
    					field: {
			                xtype: 'textfield',
			                allowBlank: false
	    				}
    				},
    				{"dataIndex":"tableKey","text":"唯一标示","flex":2,"align":"left",
    					field: {
			                xtype: 'textfield',
			                allowBlank: false
	    				}
    				},
    				{"dataIndex":"id","text":"入库id","flex":1,"align":"left"}
    		];
    	var loadBtn={
    			text:"查询表",
    			xtype:"button",
    			columnWidth:0.2,
    			handler:function(){
			       	var formValues=form_addType.getForm().getValues(); 
					Ext.apply(tmpImportTableStore.proxy.extraParams,formValues);
					tmpImportTableStore.load();
			     }
    	};
    	var saveBtn={
          	  text:"导入设计库",handler:function(){ 
          		  var records=importTablePanel.getSelectionModel().getSelection();
          		  var recordData=[];
          		  for(var i=0;i<records.length;i++){
          			  recordData[i]=records[i].data;
          		  }
          		DatabaseDesignDirectAction.saveTables(recordData,function(result){
          			if(result.success){   
            	    	if(me.needRefreshGrid){
            	    		me.needRefreshGrid.getStore().reload();
            	    	}
            	    }
          		});
          	  }
        };
    	 
      	 var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
	        clicksToEdit: 1
	     });  
	    var importTablePanel=Ext.create("Ext.grid.Panel",{ 
	        autoHeight: true, 
	        columnWidth :1,
			stripeRows: true,
			lineBreak : false,
			cellSelect : true,
			tbar:[loadBtn,saveBtn],
			loadMask: {msg:'正在装载...'},
		    plugins: [cellEditing],
			selModel:tmpImportTableSm,
			columns : tmpImportTableColumns,
			store : tmpImportTableStore
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
              items:[tmpDatabaseTypeCombobox,tmpDatabaseCombobox,
                      	{fieldLabel:"ip",margin: '5 5 5 5',name:"databaseIp",columnWidth:0.25,anchor:"100%"},
                      	{fieldLabel:"端口",margin: '5 5 5 5',name:"databasePort",columnWidth:0.25,anchor:"100%"},
                      	{fieldLabel:"所属项目",margin: '5 5 5 5',name:"projectName",columnWidth:0.25,anchor:"100%"},
                      	{fieldLabel:"用户名",margin: '5 5 5 5',name:"databaseUser",columnWidth:0.25,anchor:"100%"},
                      	{fieldLabel:"用户密码",margin: '5 5 5 5',name:"databasePassword",columnWidth:0.25,anchor:"100%"},
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
				            	    	alert("保存成功");
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

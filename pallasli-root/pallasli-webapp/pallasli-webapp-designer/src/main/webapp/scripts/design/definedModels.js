Pallas.design.api.REMOTING_API.enableBuffer = 0;  
var remotingProvider =Ext.Direct.addProvider(Pallas.design.api.REMOTING_API);  
Djn.RemoteCallSupport.addCallValidation(remotingProvider);
Djn.RemoteCallSupport.validateCalls = true;
Ext.define('SimpleMenu',{
    extend: 'Ext.data.Model',
    fields: [ 'projectName','menuKey', 'menuName','menuTableName','menuWhereSql', 'menuCaption',
              'menuTypeCode','pageTypeCode',  'urlPath','childUrlPath','parentKey',
              'menuTypeCaption' ,'parentCaption' ,'id'
		    ],
		    proxy: {   
	              type: 'direct',  
	              api:{  
		            read: MenuDirectAction.loadMenu
	              },
	              paramOrder:["id"], 
	              reader:{  
	                messageProperty:"Msg",  
	              }
	          } 
});

Ext.define('Menu',{
    extend: 'Ext.data.Model',
    fields: [ 'projectName','menuKey', 'menuName','menuTableName','menuWhereSql', 'menuCaption',
              'menuTypeCode','pageTypeCode',  'urlPath','childUrlPath','parentKey',
              'menuTypeCaption' ,'parentCaption' ,'id'
		    ],
		    proxy: {   
	              type: 'direct',  
	              api:{  
		            read: MenuDirectAction.loadMenus,  
		            create: MenuDirectAction.saveMenu,  
		            update: MenuDirectAction.saveMenu
	              },
	              //paramOrder:["node"],
	              extraParams: {
	                  parentKey: "",
		              id: ""
	              },  
	              reader:{  
	                messageProperty:"Msg",  
	              }
	          } 
});
 
Ext.define('MenuType',{
	extend : 'Ext.data.Model',
	fields : [ {name:"menuTypeCode",type:"string"},'menuTypeCaption','id' ],
	proxy : {  
        type : 'direct',  
        api : {  
	        read : MenuDirectAction.loadMenuTypes
        }
    } 
});
Ext.define('PageType',{
	extend : 'Ext.data.Model',
	fields : [ {name:"pageTypeCode",type:"string"},'pageTypeCaption','id' ],
	proxy : {  
        type : 'direct',  
        api : {  
	        read : MenuDirectAction.loadPageTypes
        }
    } 
});

Ext.define('MenuPath',{
	extend : 'Ext.data.Model',
	fields : [ 'menuKey','menuCaption' ],
	proxy : {  
        type : 'direct',  
        api : {  
	        read : MenuDirectAction.loadMenus
        }
    } 
});
Ext.define('Table', { 
    extend: 'Ext.data.Model',  
    fields:[{name:'id',type:'number'},'tableName', 'tableCaption', 'tableKey']
});  
Ext.define('Fieldset', { 
    extend: 'Ext.data.Model',  
    fields:['id','projectName','fieldsetName', 'fieldsetCaption'],
    //paramOrder: ['params'],
    proxy: {
        type: 'direct',
        api:{  
	        read: FieldsetDirectAction.getFieldSets,
	        create: FieldsetDirectAction.saveFieldSet
       }, 
        extraParams:{ }
    }
});  
Ext.define('TableField', { 
    extend: 'Ext.data.Model',  
    fields:[
		   'fieldName','fieldCaption','fieldType', 
		   {name:'id',type:'string'},
		   {name:'fieldLength',type:'string'},
		   {name:'fieldPrecision',type:'string'},
		   'fieldAllowblank',  'fieldDefault', 'projectName','tableName'  ]
});  
Ext.define('FieldsetField', { 
    extend: 'Ext.data.Model',  
    fields:[
		   'fieldName','fieldCaption','fieldType', 
		   {name:'id',type:'string'},
		   {name:'fieldLength',type:'string'},
		   {name:'fieldPrecision',type:'string'},
		   'fieldAllowblank',  'fieldDefault', 'projectName','fieldsetName'  ]
});  
Ext.define('Database', { 
    extend: 'Ext.data.Model',  
    fields: [ 'id','projectName','databaseName' ,'databaseCaption' ,'databaseType' ,'databaseSchema' ,
              'databasePort' ,'databaseIp' ,'databaseUser', 
			 'databasePassword' , 'menuWhereSql','databaseTypeCaption'
			 ], 
    proxy: {  
           type: 'direct',  
           api:{  
		        read: DatabaseDesignDirectAction.getDatabases,
		        create: DatabaseDesignDirectAction.saveDatabase
           },
           //paramOrder:["node"],
           extraParams: { }
    } 
 }); 

Ext.define('DatabaseType', { 
    extend: 'Ext.data.Model',  
    fields: [ 'databaseType' ,'databaseTypeCaption']
 }); 
Ext.define('ImportTableField', { 
	extend: 'Ext.data.Model',  
	fields: [ 
		   {name:'id',type:'string'},
		   {name:'fieldPrecision',type:'string'},
		   {name:'fieldLength',type:'string'},
		   'fieldName','fieldCaption','fieldType',
		   'fieldAllowblank',  'fieldDefault',     
		   'projectName','tableName','tableCaption','tableKey','tableComments' ,
		   'databaseType' ,'databaseSchema' , 'databasePort' ,'databaseUser', 
		   'databasePassword' , 'menuWhereSql'
		   ], 
	proxy: {  
		   type: 'direct',  
		   api:{  
				read: DatabaseDesignDirectAction.getTableFields,
				create: DatabaseDesignDirectAction.saveTableFields
		   },
		   paramOrder:["table","database"],
		   extraParams:{table:{},database:{}}
	}
}); 
 
Ext.define('ImportTable', { 
    extend: 'Ext.data.Model',  
    fields: [ {name:'id',type:'string'},'projectName','tableName','tableCaption','tableKey','tableComments' ,
			 'databaseType' ,'databaseSchema' , 'databasePort' ,'databaseUser', 
			 'databasePassword' , 'menuWhereSql'
			 ], 
    proxy: {  
           type: 'direct',  
           api:{  
		        read: DatabaseDesignDirectAction.getTables,
		        create: DatabaseDesignDirectAction.saveTables
           },
           //paramOrder:["node"],
           extraParams: { }
    } 
 }); 


Ext.define('PageComponent', {
	extend : 'Ext.data.Model',
	fields : [ {name : 'id',type : 'string'}, 
	           {name : 'text',type : 'string'},
	           {name : 'path',type : 'string'}, 
	           {name : 'attributes',type : 'json'} 
	         ]
});
Ext.define('Page', {
	extend : 'Ext.data.Model',
    fields:['id','projectName','pageName', 'pageCaption','pageTypeCode', 'page_layout'],
    proxy: {  
        type: 'direct',  
        api:{  
	        read: PageDirectAction.getPages,
	        create: PageDirectAction.savePage
        }
    }
});
Ext.define('CompType',{
    extend: 'Ext.data.Model',
    fields: [ 'compTypeCode', 'compTypeCaption','id'],
    proxy: {  
        type: 'direct',  
        api:{  
        	read: CompDirectAction.loadCompTypes,  
        	create: CompDirectAction.saveCompType
        }
    }
});
Ext.define('Comp',{
    extend: 'Ext.data.Model',
    fields: [ 'compName', 'compCaption', 'compTypeCode','compClass',  'id'],
    proxy: {  
	              type: 'direct',  
	              api:{  
		            read: CompDirectAction.loadComps,  
		            create: CompDirectAction.saveComp
	              }
	          } 
});

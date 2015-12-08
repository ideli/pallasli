
Ext.namespace("Mixky.wasoft.lib");

Mixky.wasoft.lib.onlineusers = function(){

	var store = new Ext.data.DirectStore({
		directFn : OrganizationDirect.onlineusers,
		paramOrder : ['params'],
		baseParams : {params:{}},
		remoteSort : false,
		root : 'results',
		totalProperty : 'totals',
		idProperty : 'id',
		fields : ["id","uname","ip"]
	});
	store.load();
	var btnExituser =  new Ext.Action({"text":"强制退出用户","handler":function(){
           var records = grid.getSelectedRecords();
		   if(records.length > 0){
		      var uname = records[0].get('uname');
		      Ext.MessageBox.confirm('操作提示', '您确定要强制退出'+uname+'用户吗？', function(btn){
                if(btn == 'yes'){
			         OrganizationDirect.delonlineusers(uname.toString(),function(result,e){
				   	       if (result&&result.success) {
				    	       Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
				               icon:Ext.Msg.INFO,width:250,closable:false});
				               store.reload();
				           }
				           else{
				               Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
				               icon:Ext.Msg.ERROR,width:250,closable:false});
				           }
				     });
                }
               }); 
		   }
		   else{
		       Ext.MessageBox.show({title:'提示',msg:"请选择需要操作的用户记录！",
		       modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.INFO,width:250,closable:false});
		   }
    }});
	
    var sm = new Ext.grid.RowSelectionModel({singleSelect : true});
	var buttons = ['->','-',btnExituser,'-'];
	var contextmenus = [btnExituser];
	var grid = new Ext.grid.GridPanel({
		border : false,
		stripeRows: true,
		enableHdMenu : false,
		lineBreak : false,
		cellSelect : true,
		autoExpandColumn : 'ip',
		sm : sm,
		columns: [new Ext.PagingRowNumberer(),									
				{"id":"uname","dataIndex":"uname","header":"用户名",width:120,"sortable":true},
			    {"id":"ip","dataIndex":"ip","header":"ip地址"}],
		store : store,
		//tbar : buttons,
		ddGroup : 'grid2tree',
		maskDisabled:true,
		loadMask: {msg:'正在装载数据...'},
		contextMenu : new Ext.menu.Menu({items:contextmenus}),
		 bbar: new Ext.PagingToolbar({
        	displayMsg : '在线用户共有{2} 人',
        	emptyMsg : '没有符合条件的数据',
        	pageSize: 2000000,
        	store: store,
            displayInfo: true
        }),
        viewConfig:{
			getRowClass: function(record, index) {
				    return 'wasoft-grid-cell-inner';
		    }
		},
		getSelectedRecords : function(){
			return this.getSelectionModel().getSelections();
		}
	});
    win = new Ext.Window({
        title : '在线用户',
        width :minwidth,
        height :midheight,
        modal : true,
        maximizable : false,
		minimizable : false,
		resizable : false,
		constrain : true,
        manager : MixkyApp.desktop.getManager(),
		layout : 'fit',
        items : grid
    });
    win.show();
};
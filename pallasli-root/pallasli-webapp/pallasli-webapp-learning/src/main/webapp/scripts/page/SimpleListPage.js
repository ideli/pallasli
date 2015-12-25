SimpleListPage = Ext.extend(Redkit.Page, {

	initPage: function() {
		var self = this;
		var initialConfig = this.initialConfig;
		
		var store = initialConfig.store;
		var cm = initialConfig.cm;
		
		var grid;
		
		// actions initialization
		var actNew = new Ext.Action({
	    	id: 'new',
	    	text: initialConfig.b_new,
	    	iconCls: 'x-add-icon',
	    	handler: function() {
	    		self.getWorkshop().openInTab({
	    			title: initialConfig.b_new,
	    			url: initialConfig.linkNew
	    		});
	    	}        	
	    });
	    
	    var actRead = new Ext.Action({
			id: 'read',
			text: initialConfig.b_read,
			iconCls: 'x-read-icon',
			disabled: true,
			handler: function() {
				var server = getSelectedData(grid);
				
				if (!server)
					return;
				
				self.getWorkshop().openInTab({
					title: initialConfig.b_read,
					url: initialConfig.linkRead + '&value=' + server.id
				}); 
			}
		});
		
		var actEdit = new Ext.Action({
	    	id: 'edit',
	    	text: initialConfig.b_edit,
	    	iconCls: 'x-edit-icon',
	    	disabled: true,
	    	handler: function() { 
				var server = getSelectedData(grid);
				
				if (!server)
					return;
				
				self.getWorkshop().openInTab({
					title: initialConfig.b_edit,
	    			url: initialConfig.linkEdit + '&value=' + server.id
	    		});        		
	    	}
	    });
	    
	    var actDelete = new Ext.Action({
	    	id: 'delete',
	    	text: initialConfig.b_delete,
	    	iconCls: 'x-delete-icon',
	    	disabled: true,
	    	handler: function() {        		
				var server = getSelectedData(grid);
				
				if (!server)
					return;
		
				Ext.Ajax.request({
				   url: initialConfig.linkDel,
				   params: {
				   		value: server.id
				   },
				   success: function(response, options) {
				   		var responseData = Ext.decode(response.responseText);
				   		if (responseData.success) 
				   			window.location.reload()
				   		else {
				   			var message = "未知原因.";
				   			if ((responseData)&&(responseData.msg))
				   				message = responseData.msg;
				   			Ext.MessageBox.alert('操作失败', message);
				   		}
				   },
				   failure: function(response, options) {
				   		Ext.MessageBox.alert('错误', '请求提交失败'); 
				   }				   
				});
	    	}
	    
	    });  
	    
	    var actRefresh = new Ext.Action({
	    	id: 'refresh',
	    	text: '刷新',
	    	iconCls: 'x-refresh-icon',
	    	handler: function() {
	    		window.location.reload();
	    	}
	    });
	    
	    var pagingBar = new Ext.PagingToolbar({
	        pageSize: 20,
	        store: store,
	        displayInfo: true,
	        displayMsg: '显示 {0} - {1} 条记录，共 {2} 条记录',
	        emptyMsg: "记录为空"
	    });
		
	    // create the Grid
	    
	    grid = new Ext.grid.GridPanel({
	    	region: 'center',
	    	border: false,
	    	loadMask: new Ext.LoadMask(Ext.getBody(), {
                msg: "正在加载数据，请稍等..."
            }),
	        store: store,
	        sm: new Ext.grid.RowSelectionModel({
	            singleSelect:true
	        }),
	        cm: cm,
	        bbar: pagingBar,
	        viewConfig: {
	            forceFit:true,
	            enableRowBody:true,
                emptyText: '暂无记录'
	        }
	        
	    });
	    
	    grid.on('dblclick', function(e){
	    	actRead.execute();
	    });
	    
	    var gsm = grid.getSelectionModel();
	    
		gsm.on('rowselect', function(sm, index, record){
	        var items = mainPanel.getTopToolbar().items;
	        
	        setEnabled(items.get('delete'), record.data.type != 'sys');
	        setEnabled(items.get('read'), gsm.hasSelection());
	        setEnabled(items.get('edit'), gsm.hasSelection());
	    }, grid, {buffer:250});
	    
	    grid.getStore().load({
	    	params: {    		
	    		start:0, 
	    		limit:20
	    	}
	    });
	    
	    var mainPanel = new Ext.Panel({
	    	region:'center',
	    	layout:'border',
	    	border: false,
	    	tbar: [actNew,
	    		' ', ' ', actRead,
	    		' ', ' ', actEdit,
	    		' ',' ', actDelete, 
	    		'-', actRefresh        
	        ],
	        items: [grid]
	    }); 	
		
	    var viewport = new Ext.Viewport({
	        layout:'border',
	        items:[
	            mainPanel
	         ]
	    }); 
    
    }
});	
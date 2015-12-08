<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="com.mixky.app.ApplicationParameters"%>
<%
	String id = request.getParameter("id");
%>

<script language='javascript'>
Ext.onReady(function(){
	var id = '<%=id%>';

	// 获得对象的属性列表
	var module = Mixky.awsoft.lib.Class.getModule("user");
	
	var panel = Ext.getCmp(id);
	panel.setTitle('基本信息');

	// 合并属性
	var properties = {};
	for(var i=0;i<module.properties.length;i++){
		var p = module.properties[i];
		properties[p.name] = p;
	}
	// 存储字段
	var fields = [{name:'rowstate', mapping:'rowstate'}];
	for(var n in properties){
		var f = {name:n, mapping:n};
		fields.push(f);
	}
	// 列表字段
	var columns = [new Mixky.editor.RowNumberer()];
	for(n in module.propertyColumns){
		var col = {
			id : properties[n].name,
			dataIndex : properties[n].name,
			header : properties[n].text
		};
		if(properties[n].xeditor == 'selectkeymap'){
			col.renderer = function(bVal){
	        	var n = this.id;
	        	for(var i=0;i<properties[n].xconfig.datas.length;i++){
	        		if(bVal == properties[n].xconfig.datas[i][0]){
	        			return properties[n].xconfig.datas[i][1];
	        		}
	        	}
	        	return bVal;
	        }
		}
		Ext.apply(col, module.propertyColumns[n]);
		columns.push(col);
	}
	
	// 数据访问
	var store = new Ext.data.DirectStore({
		pruneModifiedRecords : true,
		paramOrder : ['limit','start','sort','dir','query'],
		baseParams : {limit:30, start:0, sort:'',dir:'', query:''},
		remoteSort : true,
		directFn : OrganizationDirect.getAllUserListPage,
		root : 'results',
		totalProperty : 'totals',
		idProperty : 'id',
		fields:fields
	});
    // 功能条
    var fieldQuery = new Ext.form.TwinTriggerField({
    	trigger1Class : 'x-form-search-trigger',
    	trigger2Class : 'x-form-clear-trigger',
    	width : 140,
    	enableKeyEvents : true,
    	listeners : {
    		specialkey: function(field, e){
		    	if (e.getKey() == e.ENTER) {
		    		field.onTrigger1Click();
		        }
        	}
    	},
    	onTrigger1Click : function(){
			store.baseParams.query = this.getValue();
			store.load();
    	},
    	onTrigger2Click : function(){
        	this.setValue('');
			store.baseParams.query = '';
			store.load();
    	}
	})
    var AddAction = new Ext.Action({
		text:'添加',
		iconCls:'icon-common-add',
		handler:function(){
			Mixky.lib.getNewTableRecordId('t_mk_sys_user', function(newId){
				var record = new store.recordType({id : newId, rowstate : 'add'}, newId);
				store.insert(store.getCount(), record);
				record.set('f_order', store.getCount() + store.baseParams.start);
				record.set('f_name', 'user' + newId);
				record.set('f_caption', '新建用户');
				record.set('f_type', 0);
				record.set('f_state', 0);
				grid.getSelectionModel().selectLastRow();
			});
		}
	});
    var DelAction = new Ext.Action({
		text:'删除',
		iconCls:'icon-common-delete',
		handler:function(){
			var sm = grid.getSelectionModel()
			var record = sm.getSelected();
			if(record){
				var index = store.indexOf(record);
				if(record.get('rowstate') == 'add'){
					store.remove(record);
				}else{
					record.set('rowstate', 'del');
				}
				sm.deselectRow(index);
			}
			
		}
	});

    var ResetPasswordAction = new Ext.Action({
		text:'重置密码',
		iconCls:'icon-common-reset',
		handler : function(){
			var record = grid.getSelectionModel().getSelected();
			if(!record){
				return;
			}
			OrganizationDirect.resetUserPassword(record.get('id') , function(result, e){
				if(result && result.success){
					Ext.Msg.alert('操作提示', '用户【' + record.get('f_name') + '】重置密码成功。');
				}else{
					Ext.Msg.alert('错误提示', '用户【' + record.get('f_name') + '】重置密码失败。');
				}
			});
		}
	});

	var uploadSignAction = new Mixky.plugins.UploadButton({
		text: '批量上传签名',
		iconCls: 'icon-common-upload',
		tooltip: '签名图片文件名称应与\'用户登录名\'或\'用户姓名\'一致',
		uploadConfig : {
			upload_url : 'servlet/file.upload.to.folder',
			file_types_description : '图形文件',
			file_types : '*.gif;*.jpg;*.png',
			post_params : {
    			targetFolder: '<%=ApplicationParameters.instance().getHandSignFolder()%>'
			}
		}
	})
    var tools = ['查找 ：', fieldQuery, '-', AddAction, '-', DelAction, '-', ResetPasswordAction, uploadSignAction];
    var menus = [AddAction, '-', DelAction, '-', ResetPasswordAction];

	var detail = new Ext.Panel({
    	region : 'east' ,
    	disabled : true,
		autoLoad : {
			url : 'administrator/organization/user.detail.ui.do',
			scripts:true,
			params:{},
			border:false
		},
		width : 350,
		split : true,
		border : false,
		layout : 'fit',
		// 开始编辑记录
		startEditRecord : Ext.emptyFn,
		// 结束编辑记录
		stopEditRecord : Ext.emptyFn
	});
	
	Ext.apply(detail.autoLoad.params, {id:detail.getId()});
    
	// 表格对象
	var grid = new Ext.grid.GridPanel({
    	region : 'center',
		border : false,
		columns : columns,
		autoExpandColumn:'f_note',
		enableHdMenu:false,
		enableColumnMove:false,
		store : store,
		viewConfig:{
			getRowClass: function(record, index) {
				if(record.dirty){
					return 'mixky-grid-row-changed';
				}
		    }
		},
		sm : new Ext.grid.RowSelectionModel({
			listeners : {
				'beforerowselect' : function(sm, index, ke, record){
					if(record.get('rowstate') == 'del'){
						return false;
					}
				}
			}
		}),
		contextMenu : new Ext.menu.Menu({items:menus}),
		listeners : {
			'rowcontextmenu' : function(g, rowIndex, e){
				g.getSelectionModel().selectRow(rowIndex);
				g.contextMenu.showAt(e.getXY());
			}
		},
        bbar: new Ext.PagingToolbar({
        	firstText : '首页',
        	lastText : '尾页',
        	nextText : '下一页',
        	prevText : '上一页',
        	refreshText : '刷新',
        	beforePageText : '第',
        	afterPageText : '页，共 {0} 页',
        	displayMsg : '共 {2} 条，当前显示 {0} 到 {1} 条',
        	emptyMsg : '没有符合条件的数据',
            pageSize: 30,
            store: store,
            displayInfo: true,
            items : [
                '-',
                '每页显示:',
                new Ext.form.ComboBox({
                    editable : false,
                    triggerAction: 'all',
                    width : 50,
               		store : [2, 10, 20, 30, 50, 100, 200],
               		value : 30,
               		listeners : {
               			'select' : function(c, record, index){
               				grid.getBottomToolbar().pageSize = c.getValue();
               				grid.getBottomToolbar().changePage(1);
               			}
                   	}
           		})
            ]
        })
	});

	grid.getSelectionModel().on("rowselect", function(sm, index, record){
		detail.startEditRecord(record);
	});

	grid.getSelectionModel().on("rowdeselect", function(sm, index, record){
		detail.stopEditRecord(record, true);
	});

	var ui = new Ext.Panel({
		layout:'border',
		border : false,
		defaults : {border:false},
		tbar : tools,
		items:[grid, detail]
	});
	// 刷新
	panel.refresh = function(){
		grid.getStore().reload();
	}
	// 保存属性修改
	panel.save = function(needSaveNext){
		detail.stopEditRecord();
		var modifieds = store.getModifiedRecords();
		if(modifieds.length > 0){
			var record = modifieds[0];
			var user = record.getChanges();
			user.id = record.get('id');
			user.rowstate = record.get('rowstate');
			OrganizationDirect.saveUser(user, function(result, e){
				if(result && result.success){
					if(record.get('rowstate') == 'del'){
						store.remove(record);
					}else{
						record.set('rowstate', '');
						record.commit();
					}
					panel.save(needSaveNext);
				}
			});
		}else{
			MixkyApp.framework.workspace.savePanelOver(panel, needSaveNext);
		}
	};
	panel.add(ui);
	panel.doLayout();
	panel.refresh();
});
</script>
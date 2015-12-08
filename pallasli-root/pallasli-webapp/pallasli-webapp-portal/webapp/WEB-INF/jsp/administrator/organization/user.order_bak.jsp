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
	panel.setTitle('用户排序');

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
	    	sortable : properties[n].sortable,
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
		directFn : OrganizationDirect.getAllUserList,
		paramOrder:[],
		root : 'results',
		totalProperty : 'totals',
		idProperty : 'id',
		sortInfo: {field:'f_order', direction: 'ASC'},
		fields:fields
	});
    // 功能条
    var MoveUpAction = new Ext.Action({
		text : '上移',
		iconCls : 'icon-common-up',
		handler : function(){
			var record = grid.getSelectionModel().getSelected();
			if(!record){
				return;
			}
			var index = grid.getStore().indexOf(record);
			if(index == 0){
				return;
			}
			var recordPre = store.getAt(index - 1);
			record.set('f_order', index);
			recordPre.set('f_order', index + 1);
			grid.getStore().sort('f_order', 'ASC');
		}
	});
    var MoveDownAction = new Ext.Action({
		text:'下移',
		iconCls:'icon-common-down',
		handler : function(){
			var record = grid.getSelectionModel().getSelected();
			if(!record){
				return;
			}
			var index = grid.getStore().indexOf(record);
			if(index == grid.getStore().getCount() - 1){
				return;
			}
			var recordNext = store.getAt(index + 1);
			record.set('f_order', index + 2);
			recordNext.set('f_order', index + 1);
			grid.getStore().sort('f_order', 'ASC');
		}
	});
	
    var tools = [MoveUpAction, MoveDownAction];
    
	// 表格对象
	var grid = new Ext.grid.GridPanel({
    	region : 'center',
		border : false,
		columns : columns,
		autoExpandColumn:'f_note',
		enableHdMenu:false,
		enableColumnMove:false,
		tbar : tools,
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
		contextMenu : new Ext.menu.Menu({items:tools}),
		listeners : {
			'rowcontextmenu' : function(g, rowIndex, e){
				g.getSelectionModel().selectRow(rowIndex);
				g.contextMenu.showAt(e.getXY());
			}
		}
	});
	
	// 刷新
	panel.refresh = function(){
		grid.getStore().reload();
	}
	// 保存属性修改
	panel.save = function(needSaveNext){
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
	panel.add(grid);
	panel.doLayout();
	panel.refresh();
});
</script>
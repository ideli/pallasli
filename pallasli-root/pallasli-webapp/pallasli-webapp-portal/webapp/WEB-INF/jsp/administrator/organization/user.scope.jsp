<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="com.mixky.app.ApplicationParameters"%>
<%
	String id = request.getParameter("id");
%>

<script language='javascript'>
Ext.onReady(function(){
	var id = '<%=id%>';

	// 获得对象的属性列表
	var module = Mixky.awsoft.lib.Class.getModule("userscope");
	
	var panel = Ext.getCmp(id);
	panel.setTitle('数据范围');

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
		directFn : UserScopeDirect.getUserScopeListPage,
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
	});
    var btnSetScope = new Ext.Action({
		text:'设置范围',
		iconCls:'icon-common-edit',
		handler:function(){
			panel.setScope();
		}
	});
	
    var tools = ['查找：', fieldQuery, '-', btnSetScope];
    var menus = [btnSetScope];

    
	// 表格对象
	var grid = new Ext.grid.GridPanel({
    	region : 'center',
		border : false,
		columns : columns,
		autoExpandColumn:'f_gjd_yh_captions',
		enableHdMenu:false,
		enableColumnMove:false,
		store : store,
		tbar : tools,
		viewConfig:{
			getRowClass: function(record, index) {
				if(record.dirty){
					return 'mixky-grid-row-changed';
				}
		    }
		},
		contextMenu : new Ext.menu.Menu({items:menus}),
		listeners : {
			'rowcontextmenu' : function(g, rowIndex, e){
				g.getSelectionModel().selectRow(rowIndex);
				g.contextMenu.showAt(e.getXY());
			},
			'rowdblclick' : function(g, rowIndex, e){
				btnSetScope.execute();
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
	// 设置范围
	panel.setScope = function(){
		var record = grid.getSelectionModel().getSelected();
		if(record){
			var scopeTree = new Ext.tree.TreePanel({
				border : true,
				height : 250,
				autoScroll: true,
				rootVisible: false,
				root : {
					text : 'root',
					level : 0,
					bm : 'root'
				},
		        loader: new Ext.tree.TreeLoader({
		            directFn : UserScopeDirect.getOutline,
		        	paramOrder : ['bm', 'level', 'userid'],
		        	baseParams : {userid : record.get('id'), level : 0, bm : 'root'},
		        	listeners : {
		        		'beforeload':function(loader, node){
		        			loader.baseParams.level = node.attributes.level;
		        			loader.baseParams.bm = node.attributes.bm;
		        		}
		        	}
				})
			});
			scopeTree.userid = record.get('id');
			scopeTree.on('checkchange', function(node, checked){
				if(node.noEvent){
					node.noEvent = false;
					return;
				}
				if(checked){
					UserScopeDirect.selectScopeNode(node.attributes.bm, node.attributes.level, scopeTree.userid, function(result, e){
						if(result && result.success){
							switch(node.attributes.level){
							case 1:
								node.collapse();
								break;
							case 2:
								for(var i=0;i<node.parentNode.childNodes.length;i++){
									var child = node.parentNode.childNodes[i];
									if(child.attributes.bm != node.attributes.bm){
										if(child.getUI().isChecked()){
											child.noEvent = true;
											child.getUI().toggleCheck(false);
										}
									}
								}
							case 3:
								node.reload(function(){
									node.expand();
								});
								break;
							case 4:
								node.reload(function(){
									node.expand();
								});
								break;
							}
						}else{
							node.noEvent = true;
							node.getUI().toggleCheck(false);
						}
					});
				}else{
					UserScopeDirect.deselectScopeNode(node.attributes.bm, node.attributes.level, scopeTree.userid, function(result, e){
						if(result && result.success){
							switch(node.attributes.level){
							case 1:
								node.reload(function(){
									node.expand();
								});
								break;
							case 2:
								node.reload(function(){
									node.expand();
								});
								break;
							case 3:
								node.collapse();
								break;
							case 4:
								node.collapse();
								break;
							}
						}else{
							node.noEvent = true;
							node.getUI().toggleCheck(true);
						}
					});
				}
			});
			scopeTree.on('beforeexpandnode', function(node){
				switch(node.attributes.level){
				case 1:
					if(node.getUI().isChecked()){
						return false;
					}
					break;
				case 2:
					var hasSelected = false;
					for(var i=0;i<node.parentNode.childNodes.length;i++){
						if(node.parentNode.childNodes[i].getUI().isChecked()){
							hasSelected = true;
							break;
						}
					}
					if(hasSelected){
						return false;
					}
					break;
				case 3:
					if(!node.getUI().isChecked()){
						return false;
					}
					break;
				case 4:
					if(!node.getUI().isChecked()){
						return false;
					}
					break;
				}
			});
			scopeTree.on('render', function(){
				UserScopeDirect.getInitPath(scopeTree.userid, function(result, e){
					if(result && result.success){
						scopeTree.expandPath('/root/' + result.path, 'bm');
					}
				});
			});
			var win = new Ext.Window({
				title : '设置数据范围 —— （' + record.get("f_caption") + '）',
				modal : true,
				height : 400,
				width : 400,
				layout : 'fit',
				items : scopeTree,
				buttonAlign : 'center',
				maximizable : false,
				minimizable : false,
				resizable : false,
				constrain : true,
				closable : false,
				buttons : [{
					text : '关闭',
					handler : function(){
						UserScopeDirect.getUserScope(scopeTree.userid, function(result, e){
							if(result && result.success){
								record.set('f_gjd_codes', result.data.f_gjd_codes);
								record.set('f_gjd_captions', result.data.f_gjd_captions);
								record.set('f_gjd_yh_codes', result.data.f_gjd_yh_codes);
								record.set('f_gjd_yh_captions', result.data.f_gjd_yh_captions);
								record.set('f_gjd_yh_wd_codes', result.data.f_gjd_yh_wd_codes);
								record.set('f_gjd_yh_wd_captions', result.data.f_gjd_yh_wd_captions);
								record.commit();
							}
						});
						win.close();
					}
				}]
			});
			win.show();
		}
	}
	// 刷新
	panel.refresh = function(){
		grid.getStore().load();
	}
	// 保存属性修改
	panel.save = function(needSaveNext){
		MixkyApp.framework.workspace.savePanelOver(panel, needSaveNext);
	};
	panel.add(grid);
	panel.doLayout();
	panel.refresh();
});
</script>
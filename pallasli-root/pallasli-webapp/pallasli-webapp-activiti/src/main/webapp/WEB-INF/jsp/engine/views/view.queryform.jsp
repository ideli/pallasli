<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="com.google.gson.JsonArray"%>
<%@ page import="com.mixky.engine.authority.AuthorityDataService"%>
<%@ page import="com.mixky.engine.organization.dao.User"%>
<%@ page import="com.mixky.engine.design.view.View"%>
<%@ page import="com.mixky.engine.design.view.ViewDataService"%>
<%@ page import="com.mixky.engine.design.view.Column"%>
<%@ page import="com.mixky.engine.design.Action"%>
<%@ page import="com.mixky.engine.design.module.DocumentType"%>
<%@ page import="com.mixky.engine.design.DesignObjectLoader"%>

<%
	String panelid = request.getParameter("panelid");
	String appkey = request.getParameter("appkey");
	View view = (View)request.getAttribute("view");
	User user = (User)request.getAttribute("user");
	List<Action> actions = AuthorityDataService.instance().getDesignObjectsByUser(view.getF_buttons(), user);
	List<Column> columns = AuthorityDataService.instance().getDesignObjectsByUser(view.getF_columns(), user);
	JsonObject cfg = view.getF_config();
	// 支持自定义数据
	String directFn = "eval(app.keyPrefix + 'AppDirect.getViewResults')";
	if(cfg != null && cfg.has("directFn")){
		directFn = cfg.get("directFn").getAsString();
	}
	boolean autoLoad = true;
	if(cfg != null && cfg.has("autoLoad")){
		autoLoad = cfg.get("autoLoad").getAsBoolean();
	}
	// 查询表单输出项
	JsonArray queryFormItems = ViewDataService.instance().getViewQueryForm(view, user);
	// 计算查询界面高度
	int formHeight = queryFormItems.size() * 28 + 25 + 15 + 40;
%>
<script language='javascript'>
Ext.onReady(function(){

	var panel = Ext.getCmp('<%=panelid%>');
	var win = panel.findParentByType('window');
	var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];
	var autoLoad = <%=autoLoad%>;
	
	panel.getFieldValue = function(name){
	   var field = advanceForm.getForm().findField(name);
	   if(field){
		   return field.getValue();
	   }
	}

	// 数据访问
	var store = new Ext.data.DirectStore({
		directFn : <%=directFn%>,
		paramOrder : ['viewkey','querytype','limit','start','sort','dir','params'],
		baseParams : {viewkey:'<%=view.getKey()%>', querytype:<%=ViewDataService.QT_NORMAL%>,limit:<%=view.getF_page_size()%>, start:0, sort:'',dir:'',params:{}},
		remoteSort : true,
		root : 'results',
		totalProperty : 'totals',
		idProperty : '<%=view.getF_keycolumn()%>',
		fields : <%=ViewDataService.instance().getViewStoreFields(columns, view.getF_enable_favorite())%>
	});
<%
	// 收藏夹字段定义
	if(view.getF_enable_favorite()){
		String dtkey = view.getF_i_documenttype().get("data").getAsString();
		DocumentType dt = DesignObjectLoader.instance().loadDesignObject(dtkey);
%>
	var favoriteColumn = new Mixky.wasoft.favorite.FavoriteColumn({
		applicationkey : app.key,
		dataIndex: 'F_FAVORITE_FLAG',
		id: 'F_FAVORITE_FLAG',
		fixed : true,
		menuDisabled : true,
		documenttypekey : '<%=dtkey%>',
		titleFieldName : '<%=view.getF_title_field()%>',
		width: 20
	});
<%
	}
	// 选择器
	if(view.getF_single_select()){
%>
	var sm = new Ext.grid.RowSelectionModel({singleSelect : true});
<%
	}else{
%>
	var sm = new Ext.grid.CheckboxSelectionModel();
<%
	}
%>
	// 显示列
	var columns = <%=ViewDataService.instance().getViewColumns(columns)%>;
	// 视图操作
<%
		for(int i=0;i<actions.size();i++){
			Action action = actions.get(i);
%>
		var <%=action.getF_key()%> = new Ext.Action({
			text : '<%=action.getF_caption()%>',
			iconCls : '<%=action.getIcon()%>',
			isDefault : <%=action.getF_default()%>,
<%
			if(action.getF_handler() == null || "".equals(action.getF_handler())){
%>
			handler : Ext.emptyFn
<%
			}else{
%>
			handler : <%=action.getF_handler()%>
<%
			}
%>
		});
<%
		}
%>
	var advanceForm = new Ext.form.FormPanel({
    	region : 'north',
    	split : true,
        collapseMode:'mini',
    	height : <%=formHeight%>,
    	maxSize : 500,
    	minSize : 100,
		border : false,
		padding : 5,
		labelWidth : 90,
		autoScroll : true,
<%
        if(cfg!=null && cfg.has("labelWidth")){
            out.print("labelWidth:"+cfg.get("labelWidth").getAsInt()+",");
        }
%>
<%
        if(cfg!=null && cfg.has("labelAlign")){
            out.print("labelAlign:'"+cfg.get("labelAlign").getAsString()+"',");
        }
%>
<%
        if(cfg!=null && cfg.has("labelPad")){
            out.print("labelPad:"+cfg.get("labelPad").getAsInt()+",");
        }
%>
		items : {
			xtype:'fieldset',
			buttonAlign : 'center',
			buttons : [{
				text : '执行检索',
				iconCls : 'icon-sys-confirm',
				handler : function(){
					panel.doAdvanceQuery();
				}
			},{
			    text : '重置',
			    iconCls : 'icon-sys-confirm',
			    handler : function(){
				    advanceForm.getForm().reset();
			    }
		    }],
			items : <%=queryFormItems%>
		}
	});
	var validateHandler = function(){
<%
	if(cfg!=null && cfg.has("validateHandler")){
		out.print(cfg.get("validateHandler").getAsString());
	}
%>
	};
	
	panel.doAdvanceQuery = function(){
		if(validateHandler() === false){
			return;
		}
		store.baseParams.querytype = <%=ViewDataService.QT_ADVANCE%>;
		var params = advanceForm.getForm().getFieldValues();
		var mparams = {};
		for(i in params){
			if(params[i] != ''){
				mparams[i] = params[i];
			}
		}
		panel.queryParams = mparams;
		panel.refresh();
	}
		
	// 刷新按钮
	var <%=ViewDataService.VN_REFRESH_BUTTON_NAME%> = new Ext.Action({
		text : '刷新',
		iconCls : 'icon-common-refresh',
		handler : function(){
			store.baseParams.querytype = <%=ViewDataService.QT_NORMAL%>;
			panel.refresh();
		}
	});

	var buttons = <%=ViewDataService.instance().getViewNoQueryButtonNames(view, actions)%>;
	var contextmenus = <%=ViewDataService.instance().getViewContextMenuNames(actions)%>;
	// 表格对象
	var grid = new Ext.grid.GridPanel({
    	region : 'center',
		border : false,
		stripeRows: true,
		enableHdMenu : true,
		lineBreak : false,
		cellSelect : true,
        loadMask: {msg:'正在装载...'},
		<%
          if(view.getF_autoexpandcolumn()!=null&&!view.getF_autoexpandcolumn().trim().equals("")){
              out.print("autoExpandColumn :'"+view.getF_autoexpandcolumn()+"',");
          }
        %>
		sm : sm,
		columns : columns,
		store : store,
		tbar : buttons,
		contextMenu : new Ext.menu.Menu({items:contextmenus}),
		//enableDragDrop : true,
		// 输出附加脚本 begin
		<%
			if(cfg!=null && cfg.has("enableDragDrop")){
				out.print("enableDragDrop: " + cfg.get("enableDragDrop").getAsBoolean() + ",");
			}
		%>		
		ddGroup : 'grid2tree',
<%
	//收藏夹字段定义
	if(view.getF_enable_favorite()){
%>
		plugins: [favoriteColumn],
<%
	}
		
	if(view.getF_page_size() > 0){
%>
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
            pageSize: <%=view.getF_page_size()%>,
            store: store,
            displayInfo: true,
            items : [
                '-',
                '每页显示:',
                new Ext.form.ComboBox({
                    editable : false,
                    triggerAction: 'all',
                    width : 50,
               		store : [10, 20, 30, 50, 100, 200],
               		value : <%=view.getF_page_size()%>,
               		listeners : {
               			'select' : function(c, record, index){
               				grid.getBottomToolbar().pageSize = c.getValue();
               				grid.getBottomToolbar().changePage(1);
               			}
                   	}
           		})
            ],
            plugins: new Ext.ux.ProgressBarPager({defaultText:'正在装载数据...'}),
            listeners : {
                'beforechange' : function(a, b){
            		store.baseParams.limit = b.limit;
            		store.baseParams.start = b.start;
                }
            }
        }),
<%
	}
%>
		viewConfig:{
			getRowClass: function(record, index) {
				if(record.get("<%=view.getF_keycolumn()%>") == -1){
					return 'mixky-grid-row-sum';
				}
		    }
		},
		listeners : {
			'rowcontextmenu' : function(g, rowIndex, e){
				if(!g.isSumRecords(rowIndex)){
					g.getSelectionModel().selectRow(rowIndex);
					g.contextMenu.showAt(e.getXY());
				}
			},
			'rowdblclick' : function(g, rowIndex, e){
				if(!g.isSumRecords(rowIndex)){
					<%=ViewDataService.instance().getDefaultButtonRun(view)%>
				}
			}
		},
		getSelectedRecords : function(){
			return this.getSelectionModel().getSelections();
		},
		isSumRecords : function(index){
			var record = store.getAt(index);
			if(record && record.get("<%=view.getF_keycolumn()%>") == -1){
				return true;
			}
		}
	});
	
	function getSelectedRecords(){
		return grid.getSelectedRecords();
	}
	
	// 视图刷新
	panel.refresh = function(params){
		if(Ext.isDefined(params)){
			// 恢复查询方式
			if(!Ext.isDefined(params.querytype)){
				params.querytype = <%=ViewDataService.QT_NORMAL%>;
				store.baseParams.querytype = <%=ViewDataService.QT_NORMAL%>;
			}
			panel.params = params;
		}
		// 初始化参数
		store.baseParams.params = {};
		Ext.apply(store.baseParams.params, panel.viewparams);
		Ext.apply(store.baseParams.params, panel.params);
		// 处理查询参数
		if(store.baseParams.querytype != <%=ViewDataService.QT_NORMAL%>){
			Ext.apply(store.baseParams.params, panel.queryParams);
		}
<%
	if(view.getF_page_size() > 0 && ViewDataService.instance().isViewHasQuery(view)){
%>
		grid.getBottomToolbar().moveFirst();
<%
	}
%>
		store.reload();
	}

	// 输出附加脚本 begin
<%
	if(cfg!=null && cfg.has("customscript")){
		out.print(cfg.get("customscript").getAsString());
	}
	// 输出视图参数
	if(cfg!=null && cfg.has("params")){
		out.print("panel.viewparams = " + cfg.get("params") + ";");
	}else{
		out.print("panel.viewparams = {};");
	}
%>
   	/*鼠标移动到序号列时显示序号的值*/
   grid.on('mouseover',function(e){
		var index= grid.getView().findRowIndex(e.getTarget());  //获得行
		var record=store.getAt(index);
		if(record!=null){
		    var index1=grid.getView().findCellIndex(e.getTarget());
		    if(index1<1){
			     var rowEl=Ext.get(e.getTarget()); //把target转换为Ext.Element对象
			     var so = store.lastOptions;
	             var sop = so? so.params : null;
			     rowEl.dom.setAttribute("ext:qtip", ((sop && sop.start && sop.limit)? sop.start : 0)+ index + 1); 
			} 
		} 
   });

	panel.exportToExcel = function() {
		var selects = grid.getSelectionModel().getSelections();
		var ids = [];
		if (selects != null && selects.length > 0) {
			for (var i = 0; i < selects.length; i++) {
				ids[i] = selects[i].get("ID");
			}
		}
		
		var cols = grid.getColumnModel();
		var colsStr = '';
		var colsNames = '';
		for (var i = 0; i < cols.getColumnCount(); i++) {
			var col = cols.getColumnById(cols.getColumnId(i));
			if (!col.hidden && col.id != 'numberer' && col.id != 'checker') {
				colsStr += col.id + ',';
				colsNames += col.header + ',';
			}
		}
		var excelParams = Ext.apply({},grid.getStore().baseParams);
		Ext.apply(excelParams,{ids: ids, colsStr:colsStr, colsNames:colsNames, panelTitle: panel.title});
		excelParams.params = Ext.util.JSON.encode(excelParams.params);
		location.href = app.url + '/view.export.to.excel.do?' + Ext.urlEncode(excelParams);
	}

	
<%
	if(view.getF_issummary()){
%>
	// 增加合计行
	sm.on('beforerowselect', function(s, rowIndex){
		return !grid.isSumRecords(rowIndex);
	});
	store.on('load', function(){
		var newrec = new store.recordType({<%=view.getF_keycolumn()%> : -1});
<%
		for(int i=0;i<columns.size();i++){
			if(columns.get(i).getF_type() == Column.CT_INTEGER || columns.get(i).getF_type() ==Column.CT_FLOAT){
%>
		var <%=columns.get(i).getRealFieldName()%>_sum = 0;
<%
			}
		}
%>
		for(var i=0;i<store.getCount();i++){
			var record = store.getAt(i);
<%
		for(int i=0;i<columns.size();i++){
			if(columns.get(i).getF_type() == Column.CT_INTEGER || columns.get(i).getF_type() ==Column.CT_FLOAT){
%>
			var <%=columns.get(i).getRealFieldName()%>_sum = <%=columns.get(i).getRealFieldName()%>_sum + record.get("<%=columns.get(i).getRealFieldName()%>");
<%
			}
		}
%>
		}

<%
		for(int i=0;i<columns.size();i++){
			if(columns.get(i).getF_type() == Column.CT_INTEGER || columns.get(i).getF_type() ==Column.CT_FLOAT){
%>
		newrec.set("<%=columns.get(i).getRealFieldName()%>", <%=columns.get(i).getRealFieldName()%>_sum);
<%
			}
		}
%>
		store.add(newrec);
	})
<%
	}
%>

	var ui = new Ext.Panel({
		layout : 'border',
		border : false,
		items : [advanceForm, grid]
	})
	
	// 输出附加脚本 end
	panel.add(ui);
	panel.doLayout();
	// 初始化视图数据
	if(autoLoad){
		panel.refresh(panel.initParams);
	}
	panel.setAutoScroll(false);
});
</script>
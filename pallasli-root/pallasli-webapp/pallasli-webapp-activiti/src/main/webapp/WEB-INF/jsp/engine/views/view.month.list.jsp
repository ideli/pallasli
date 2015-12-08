<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="com.mixky.toolkit.DateTool"%>
<%@ page import="com.mixky.engine.authority.AuthorityDataService"%>
<%@ page import="com.mixky.engine.design.Action"%>
<%@ page import="com.mixky.engine.design.view.View"%>
<%@ page import="com.mixky.engine.design.view.ViewDataService"%>
<%@ page import="com.mixky.engine.design.view.Column"%>
<%@ page import="com.mixky.engine.design.module.DocumentType"%>
<%@ page import="com.mixky.engine.organization.dao.User"%>
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
%>
<script language='javascript'>
Ext.onReady(function(){

	var panel = Ext.getCmp('<%=panelid%>');
	var win = panel.findParentByType('window');
	var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];

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
	var favoriteColumn = new Mixky.app.favorite.FavoriteColumn({
		   dataIndex: 'F_FAVORITE_FLAG',
		   id: 'F_FAVORITE_FLAG',
		   fixed : true,
		   menuDisabled : true,
		   documenttypekey : '<%=dtkey%>',
		   titleFieldName : '<%=view.getF_title_field()%>',
		   width: 20
	});
	store.on('load', function(s){
		Mixky.app.common.showFavoriteById(s, '<%=dtkey%>');
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

	// 选择当前月
	var dateSelect = new Ext.form.DateField({
		value:'<%=DateTool.formatDate(new Date(System.currentTimeMillis()), "yyyy-MM")%>-01', 
		editable : false, 
		format : 'Y-m',
		width : 70,
		listeners : {
			'select' : function(){
				panel.refresh({month:dateSelect.getRawValue()});
			}
		}
	});
	// 上月
	var btnNextMonth = new Ext.Action({
		iconCls : 'icon-sys-btnnext',
		handler : function(){
			var date = dateSelect.getValue();
			dateSelect.setValue(date.add(Date.MONTH, 1).format('Y-m-d'));
			panel.refresh({month:dateSelect.getRawValue()});
		}
	});
	// 下月
	var btnPreMonth = new Ext.Action({
		iconCls : 'icon-sys-btnback',
		handler : function(){
			var date = dateSelect.getValue();
			dateSelect.setValue(date.add(Date.MONTH, -1).format('Y-m-d'));
			panel.refresh({month:dateSelect.getRawValue()});
		}
	});
	// 视图操作
<%
	for(int i=0;i<actions.size();i++){
		Action action = actions.get(i);
%>
	<%=action.output()%>
<%
	}
	// 处理查询
	if(ViewDataService.instance().isViewHasQuery(view)){
%>
	var <%=ViewDataService.VN_QUICK_QUERY_FIELD_NAME%> = new Ext.form.TextField({
		width : 100,
		emptyText : '输入快速检索字符',
        listeners: {
	        specialkey: function(field, e){
	            // e.HOME, e.END, e.PAGE_UP, e.PAGE_DOWN,
	            // e.TAB, e.ESC, arrow keys: e.LEFT, e.RIGHT, e.UP, e.DOWN
	            if (e.getKey() == e.ENTER) {
	            	var value = field.getValue();
	    			if(Ext.isDefined(value) && value != ''){
	    				store.baseParams.querytype = <%=ViewDataService.QT_QUICK%>;
	    				panel.queryParams = {<%=ViewDataService.VN_QUICK_QUERY_PARAM_NAME%>: value};
	    				panel.refresh();
	    			}
	            }
	        }
	    }
	});
	var <%=ViewDataService.VN_QUICK_QUERY_BUTTON_NAME%> = new Ext.Action({
		text : '快速检索',
		iconCls : 'icon-sys-query',
		handler : function(){
			var value = <%=ViewDataService.VN_QUICK_QUERY_FIELD_NAME%>.getValue();
			if(Ext.isDefined(value) && value != ''){
				store.baseParams.querytype = <%=ViewDataService.QT_QUICK%>;
				panel.queryParams = {<%=ViewDataService.VN_QUICK_QUERY_PARAM_NAME%>: value};
				panel.refresh();
			}
		}
	});
	var advanceForm = new Ext.form.FormPanel({
		padding : 5,
		labelWidth : 80,
		autoScroll : true,
		items : <%=ViewDataService.instance().getViewQueryForm(view, user)%>,
		bbar : ['->',{
			text : '执行检索',
			iconCls : 'icon-sys-confirm',
			handler : function(){
				var btn = <%=ViewDataService.VN_ADVANCE_QUERY_BUTTON_NAME%>;
				store.baseParams.querytype = <%=ViewDataService.QT_ADVANCE%>;
				var params = advanceForm.getForm().getValues();
				var mparams = {};
				for(i in params){
					if(params[i] != ''){
						mparams[i] = params[i];
					}
				}
				panel.queryParams = mparams;
				advanceWindow.hide();
				panel.refresh();
			}
		}, {
			text : '关闭',
			iconCls : 'icon-sys-cancel',
			handler : function(){
				var btn = <%=ViewDataService.VN_ADVANCE_QUERY_BUTTON_NAME%>;
				advanceWindow.hide();
			}
		}]
	});
	var advanceWindow = new Ext.Window({
		width : 400,
		height : 400,
		maximizable : false,
		minimizable : false,
		resizable : false,
		constrain : true,
		title : '<%=view.getF_caption()%>视图高级查询',
		modal : true,
		closeAction : 'hide',
		layout : 'fit',
		items:[advanceForm]
	});
	var <%=ViewDataService.VN_ADVANCE_QUERY_BUTTON_NAME%> = new Ext.Action({
		text : '高级检索',
		iconCls : 'icon-sys-aquery',
		handler : function(){
			advanceForm.getForm().reset();
			advanceWindow.show();
		}
	});
<%
	}
%>
	// 刷新按钮
	var <%=ViewDataService.VN_REFRESH_BUTTON_NAME%> = new Ext.Action({
		text : '刷新',
		iconCls : 'icon-sys-refresh',
		handler : function(){
			store.baseParams.querytype = <%=ViewDataService.QT_NORMAL%>;
			panel.refresh();
		}
	});
	
	var buttons = <%=ViewDataService.instance().getViewButtonNames(view, actions)%>;
	var monthbuttons = [btnPreMonth, dateSelect, btnNextMonth];
	for(var i=0;i<buttons.length;i++){
		monthbuttons.push(buttons[i]);
	}
	var contextmenus = <%=ViewDataService.instance().getViewContextMenuNames(actions)%>;
	// 表格对象
	var grid = new Ext.grid.GridPanel({
		border : false,
		enableHdMenu : true,
		lineBreak : false,
		cellSelect : true,
        loadMask: {msg:'正在装载...'},
		autoExpandColumn : '<%=view.getF_autoexpandcolumn()%>',
		sm : sm,
		columns : columns,
		store : store,
		tbar : monthbuttons,
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
		listeners : {
			'rowcontextmenu' : function(g, rowIndex, e){
				g.getSelectionModel().selectRow(rowIndex);
				g.contextMenu.showAt(e.getXY());
			},
			'rowdblclick' : function(g, rowIndex, e){
				<%=ViewDataService.instance().getDefaultButtonRun(view)%>
			}
		},
		getSelectedRecords : function(){
			return this.getSelectionModel().getSelections();
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
			store.baseParams.start = 0;
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
	}else{
%>
		store.reload();
<%
	}
%>
	}

	// 输出附加脚本 begin
<%
	if(cfg!=null && cfg.has("customscript")){
		out.print(cfg.get("customscript").getAsString() + '\n');
	}
	// 输出视图参数
	if(cfg!=null && cfg.has("params")){
		out.print("panel.viewparams = " + cfg.get("params") + ";");
	}else{
		out.print("panel.viewparams = {};");
	}
%>

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
		location.href = 'framework/engine/view/export.to.excel.do?' + Ext.urlEncode(excelParams);
	}
	
	// 输出附加脚本 end
	panel.add(grid);
	panel.doLayout();
	// 初始化视图数据
	panel.refresh(panel.initParams);
});
</script>
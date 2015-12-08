<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="com.mixky.engine.organization.dao.User"%>
<%@ page import="com.mixky.engine.certification.MixkyUserCertification"%>
<%@ page import="com.mixky.engine.design.view.ViewDataService"%>
<%@ page import="com.mixky.engine.design.view.View"%>
<%@ page import="com.mixky.engine.design.view.Column"%>
<%@ page import="com.mixky.engine.authority.AuthorityDataService"%>
<%@ page import="com.mixky.engine.design.DesignObjectLoader"%>
<%@ page import="com.mixky.app.ApplicationParameters"%>

<%
	String viewkey = request.getParameter("viewkey");
	// String appkey = request.getParameter("appkey");
	String appkey = ApplicationParameters.instance().getApplicationCode();
	View view = DesignObjectLoader.instance().loadDesignObject(viewkey);
	User user = MixkyUserCertification.instance().getUserInfo(request);
	List<Column> columns = AuthorityDataService.instance().getDesignObjectsByUser(view.getF_columns(), user);
	JsonObject cfg = view.getF_config();
	// 支持自定义数据
	String directFn = "eval(app.keyPrefix + 'AppDirect.getViewResults')";
	if(cfg != null && cfg.has("directFn")){
		directFn = cfg.get("directFn").getAsString();
	}
%>
var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];

// 数据访问
var store = new Ext.data.DirectStore({
	directFn : <%=directFn%>,
	paramOrder : ['viewkey','querytype','limit','start','sort','dir','params'],
	baseParams : {viewkey:'<%=view.getKey()%>', querytype:<%=ViewDataService.QT_NORMAL%>,limit:<%=view.getF_page_size()%>, start:1, sort:'',dir:'',params:{}},
	remoteSort : true,
	root : 'results',
	totalProperty : 'totals',
	idProperty : '<%=view.getF_keycolumn()%>',
	fields : <%=ViewDataService.instance().getViewStoreFields(columns, view.getF_enable_favorite())%>
});
<%
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


// 刷新按钮
var <%=ViewDataService.VN_REFRESH_BUTTON_NAME%> = new Ext.Action({
	text : '刷新',
	iconCls : 'icon-sys-refresh',
	handler : function(){
		store.baseParams.querytype = <%=ViewDataService.QT_NORMAL%>;
		viewpanel.refresh();
	}
});

var buttons = [<%=ViewDataService.VN_REFRESH_BUTTON_NAME%>, '->'];
<%
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
    				viewpanel.queryParams = {<%=ViewDataService.VN_QUICK_QUERY_PARAM_NAME%>: value};
    				viewpanel.refresh();
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
			viewpanel.queryParams = {<%=ViewDataService.VN_QUICK_QUERY_PARAM_NAME%>: value};
			viewpanel.refresh();
		}
	}
});
buttons.push(<%=ViewDataService.VN_QUICK_QUERY_FIELD_NAME%>);
buttons.push(<%=ViewDataService.VN_QUICK_QUERY_BUTTON_NAME%>);
<%
}
%>


// 表格对象
viewpanel = new Ext.grid.GridPanel({
	region : 'center',
	border : false,
	enableHdMenu : false,
	loadMask: {msg:'正在装载...'},
	autoExpandColumn : '<%=view.getF_autoexpandcolumn()%>',
	sm : sm,
	columns : columns,
	store : store,
	tbar : buttons,
<%
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
               '每页显示:<%=view.getF_page_size()%>'
           ],
           plugins: new Ext.ux.ProgressBarPager({defaultText:'正在装载数据...'})
       }),
<%
}
%>
	listeners : {
		'rowdblclick' : function(g, rowIndex, e){
			field.onSelectRecord();
		}
	},
	getSelectedRecords : function(){
		return this.getSelectionModel().getSelections();
	}
});


// 视图刷新
viewpanel.refresh = function(params){
	if(Ext.isDefined(params)){
		// 恢复查询方式
		if(!Ext.isDefined(params.querytype)){
			params.querytype = <%=ViewDataService.QT_NORMAL%>;
			store.baseParams.querytype = <%=ViewDataService.QT_NORMAL%>;
		}
		viewpanel.params = params;
	}
	// 初始化参数
	store.baseParams.params = Ext.apply({},viewpanel.params);
	// 处理查询参数
	if(store.baseParams.querytype != <%=ViewDataService.QT_NORMAL%>){
		Ext.apply(store.baseParams.params, viewpanel.queryParams);
	}
<%
if(view.getF_page_size() > 0 && ViewDataService.instance().isViewHasQuery(view)){
%>
	viewpanel.getBottomToolbar().moveFirst();
<%
}
%>
	store.reload();
}

viewpanel.getKey = function(){
	var records = viewpanel.getSelectedRecords();
	if(records.length > 0){
		return records[0].get('KEY');
	}else{
		return '';
	}
}

viewpanel.getDisplay = function(){
	var records = viewpanel.getSelectedRecords();
	if(records.length > 0){
		return records[0].get('DISPLAY');
	}else{	
		return '';
	}
}

viewpanel.getRecord = function(){
	var records = viewpanel.getSelectedRecords();
	if(records.length > 0){
		return records[0];
	}
}
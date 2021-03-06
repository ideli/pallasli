<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="com.mixky.engine.authority.AuthorityDataService"%>
<%@ page import="com.mixky.engine.organization.dao.User"%>
<%@ page import="com.mixky.engine.design.module.Portlet"%>
<%@ page import="com.mixky.engine.design.view.View"%>
<%@ page import="com.mixky.engine.design.view.ViewDataService"%>
<%@ page import="com.mixky.engine.design.view.Column"%>
<%@ page import="com.mixky.engine.design.Action"%>
<%@ page import="com.mixky.engine.design.module.DocumentType"%>
<%@ page import="com.mixky.engine.design.DesignObjectLoader"%>
<%
	// 读取参数
	String key = request.getParameter("key");
	String appkey = request.getParameter("appkey");
	String panelid = "portlet-" + appkey + "." + key;
	if (request.getParameter("customPanelId") != null) {
		panelid = request.getParameter("customPanelId");
	}
	// 获取属性
	User user = (User)request.getAttribute("user");
	Portlet portlet = (Portlet)request.getAttribute("portlet");
	View view = DesignObjectLoader.instance().loadDesignObject(portlet.getF_viewkey());
	List<Action> actions = AuthorityDataService.instance().getDesignObjectsByUser(view.getF_buttons(), user);
	List<Column> columns = AuthorityDataService.instance().getDesignObjectsByUser(view.getF_columns(), user);
	JsonObject cfg = view.getF_config();
%>
<script language='javascript'>
Ext.onReady(function(){

	var panel = Ext.getCmp('<%=panelid%>');

	var refreshInterval = <%=portlet.getF_refresh_interval()%>;

	var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];
	var directFn = eval(app.keyPrefix + 'AppDirect.getViewResults');
	
	// 数据访问
	var store = new Ext.data.DirectStore({
		directFn : directFn,
		paramOrder : ['viewkey','querytype','limit','start','sort','dir','params'],
		baseParams : {viewkey:'<%=view.getKey()%>', querytype:<%=ViewDataService.QT_NORMAL%>,limit:<%=view.getF_page_size()%>, start:1, sort:'',dir:'',params:{}},
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
%>
	// 选择器
	var sm = new Ext.grid.RowSelectionModel({singleSelect : true});
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
	var contextmenus = <%=ViewDataService.instance().getViewContextMenuNames(actions)%>;
	// 表格对象
	var grid = new Ext.grid.GridPanel({
		border : false,
		stripeRows: true,
		enableHdMenu : false,
		lineBreak : false,
        loadMask: {msg:'正在装载...'},
		autoExpandColumn : '<%=view.getF_autoexpandcolumn()%>',
		sm : sm,
		columns : columns,
		store : store,
		contextMenu : new Ext.menu.Menu({items:contextmenus}),
<%
	//收藏夹字段定义
	if(view.getF_enable_favorite()){
%>
		plugins: [favoriteColumn],
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
	panel.refresh = function(){
		var p = Ext.getCmp('<%=panelid%>');
		if(p){
			store.reload();
			if(refreshInterval > 0){
				p.refresh.defer(1000 * refreshInterval);
			}
		}
	}

	// 输出附加脚本 begin
<%
	if(cfg!=null && cfg.has("customscript")){
		out.print(cfg.get("customscript").getAsString());
	}
%>
	
	// 输出附加脚本 end
	panel.add(grid);
	panel.doLayout();
	// 初始化视图数据
	panel.refresh();
});
</script>
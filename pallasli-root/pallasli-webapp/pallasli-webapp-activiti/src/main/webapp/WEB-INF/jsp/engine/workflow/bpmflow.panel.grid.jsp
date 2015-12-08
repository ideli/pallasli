<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.mixky.engine.organization.dao.User"%>
<%@ page import="com.mixky.engine.design.Action"%>
<%@ page import="com.mixky.engine.design.DesignObjectLoader"%>
<%@ page import="com.mixky.engine.design.document.DocumentManager"%>
<%@ page import="com.mixky.engine.design.document.Document"%>
<%@ page import="com.mixky.engine.design.document.Panel"%>
<%@ page import="com.mixky.engine.design.store.StoreManager"%>
<%@ page import="com.mixky.engine.design.view.View"%>
<%@ page import="com.mixky.engine.design.view.ViewDataService"%>
<%@ page import="com.mixky.engine.design.document.ObjectAuthority"%>
<%@ page import="com.mixky.engine.design.view.Column"%>
<%@ page import="com.mixky.engine.authority.AuthorityDataService"%>
<%@ page import="com.mixky.toolkit.gson.JsonFunction"%>
<%@ page import="com.google.gson.*"%>
<%
	// 读取参数
	String panelid = request.getParameter("panelid");
	String appkey = request.getParameter("appkey");
	String documentid = request.getParameter("documentid");
	String unid = request.getParameter("unid");
	String docid = request.getParameter("docid");
	String type = request.getParameter("type");
	// 获取属性
	User user = (User)request.getAttribute("user");
	Panel panel = (Panel)request.getAttribute("panel");
	Document document = (Document)request.getAttribute("document");
	Map<String, ObjectAuthority> map = (Map<String, ObjectAuthority>)request.getAttribute("authmap");
	// 获得视图对象
	View view = DesignObjectLoader.instance().loadDesignObject(panel.getF_i_view().get("data").getAsString());
	// 获得权限
	List<ObjectAuthority> panelbuttonauths = DocumentManager.instance().getFilterObjectAuthority(map, panel.getF_buttons(), user);
	List<ObjectAuthority> viewbuttonauths = DocumentManager.instance().getFilterObjectAuthority(map, view.getF_buttons(), user);
	// 合并视图及表单按钮
	for(int i=0;i<viewbuttonauths.size();i++){
		panelbuttonauths.add(viewbuttonauths.get(i));
	}
	List<ObjectAuthority> columnauths = DocumentManager.instance().getFilterObjectAuthority(map, view.getF_columns(), user);
	List<Column> columns = AuthorityDataService.instance().getDesignObjectsByUser(view.getF_columns(), user);
	
	String workflowTopHtml=(String)request.getAttribute("workflowTopHtml");
	String viewWorkflowInfoJS=(String)request.getAttribute("viewWorkflowInfoJS");
	String workflowValidator=(String)request.getAttribute("workflowValidator");
	
	JsonArray formLayout=(JsonArray)request.getAttribute("formLayout");	
	JsonArray contextMenus=(JsonArray)request.getAttribute("contextMenus");	
	JsonArray buttons=(JsonArray)request.getAttribute("buttons");	
	
	// 视图默认参数
	JsonObject params = new JsonObject();
	if(panel.getF_config() != null && panel.getF_config().has("documentidParamName")){
		params.addProperty(panel.getF_config().get("documentidParamName").getAsString(), documentid);
	}
	
	JsonObject configParams = new JsonObject();
	if (panel.getF_config() != null&&panel.getF_config().has("params")) {
		configParams = panel.getF_config().get("params").getAsJsonObject();
	}
	// 支持自定义数据
	JsonObject cfg = view.getF_config();
	String directFn = "eval(app.keyPrefix + 'AppDirect.getViewResults')";
	if(cfg != null && cfg.has("directFn")){
		directFn = cfg.get("directFn").getAsString();
	}
%>
<script language='javascript'>
  <%=viewWorkflowInfoJS%>   
</script>
<body scroll='auto'>
  <%=workflowTopHtml %>
</body>
<script language='javascript'>
Ext.onReady(function(){
	var panel = Ext.getCmp('<%=panelid%>');
	var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];

	// 数据访问
	var store = new Ext.data.DirectStore({
		directFn : <%=directFn%>,
		paramOrder : ['viewkey','querytype','limit','start','sort','dir','params'],
		baseParams : {viewkey:'<%=view.getKey()%>', querytype:<%=ViewDataService.QT_NORMAL%>,limit:0, start:0, sort:'',dir:'',params:Ext.apply(<%=params%>, <%=configParams%>)},
		remoteSort : true,
		root : 'results',
		totalProperty : 'totals',
		idProperty : '<%=view.getF_keycolumn()%>',
		fields : <%=ViewDataService.instance().getViewStoreFields(view.getF_columns())%>
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
	var columns = <%=ViewDataService.instance().getViewColumnsByAuths(columnauths)%>;

	// 刷新按钮
	var <%=ViewDataService.VN_REFRESH_BUTTON_NAME%> = new Ext.Action({
		text : '刷新',
		iconCls : 'icon-common-refresh',
		handler : function(){
			store.baseParams.querytype = <%=ViewDataService.QT_NORMAL%>;
			panel.refresh();
		}
	});
	// 视图操作
	var buttons = ['->'];
	var contextmenus = ['-'];
<%
	String defaultAction = "";
	for(int i=0;i<panelbuttonauths.size();i++){
		ObjectAuthority auth = panelbuttonauths.get(i);
		// 判断权限
		if(auth.hasAuth(ObjectAuthority.A_READ) || auth.hasAuth(ObjectAuthority.A_EDIT)){
			Action action = (Action)auth.getObject();
			// 输出按钮
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
	buttons.push(<%=action.getF_key()%>);
	contextmenus.push(<%=action.getF_key()%>);
<%
			// 双击默认操作
			if(action.getF_default()){
				defaultAction = action.getF_key() + ".execute()";
			}
		}
	}
%>
	// 表格对象
	var grid = new Ext.grid.EditorGridPanel({
		border : false,
		stripeRows: true,
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
<%
	if(cfg!=null && cfg.has("enableHdMenu")){
		out.print("enableHdMenu: " + cfg.get("enableHdMenu").getAsBoolean() + ",");
	}
	else{
	   out.print("enableHdMenu: false,");
	}
%>	
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
           // plugins: new Ext.ux.ProgressBarPager({defaultText:'正在装载数据...'}),
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
				<%=defaultAction%>
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

	panel.getRecord = function(){
		return grid.getSelectedRecords();
	}
	
	panel.refresh = function(params){
		if(Ext.isDefined(params)){
			panel.params = params;
		}
		// 初始化参数
		Ext.apply(store.baseParams.params, panel.params);
		Ext.apply(store.baseParams.params, panel.document.params);
<%
	if(view.getF_page_size() > 0){
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
	
	   panel.btnDisable = function(){
<%
      for(int i=0;i<panelbuttonauths.size();i++){
		ObjectAuthority auth = panelbuttonauths.get(i);
		// 判断权限
		if(auth.hasAuth(ObjectAuthority.A_READ) || auth.hasAuth(ObjectAuthority.A_EDIT)){
		   Action action = (Action)auth.getObject();
		   JsonObject json=action.getF_config();
		   if(json!=null&&json.has("disabled")){
		      if(json.get("disabled").getAsString().trim().equals("true")){
		         out.print(action.getF_name()+".disable();");
		      }else{
		         out.print(action.getF_name()+".enable();");
		      }
		   }
		} 
	 }
 %>
    }
   
    
    panel.btnEnable = function(){
<%
      for(int i=0;i<panelbuttonauths.size();i++){
		ObjectAuthority auth = panelbuttonauths.get(i);
		// 判断权限
		if(auth.hasAuth(ObjectAuthority.A_READ) || auth.hasAuth(ObjectAuthority.A_EDIT)){
			Action action = (Action)auth.getObject();
		   JsonObject json=action.getF_config();
		   if(json!=null&&json.has("disabled")){
		      if(json.get("disabled").getAsString().trim().equals("true")){
		         out.print(action.getF_name()+".enable();");
		      }
		   } 
		 }
	 }
 %>
    }
    
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

	// 输出附加脚本 begin
<%
	if(panel.getF_custom_script() != null){
		out.print(panel.getF_custom_script());
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
		location.href = app.url + '/view.export.to.excel.do?' + Ext.urlEncode(excelParams);
 }
	
  // 增加合计行
<%
	if(view.getF_issummary()){
%>
	sm.on('beforerowselect', function(s, rowIndex){
		return !grid.isSumRecords(rowIndex);
	});
	store.on('load', function(){
		var newrec = new store.recordType({<%=view.getF_keycolumn()%> : -1});
<%
		for(int i=0;i<columnauths.size();i++){
		    boolean issum=columns.get(i).getF_summary_type()==null?false:columns.get(i).getF_summary_type().equals("sum")?true:false;
			if((columns.get(i).getF_type() == Column.CT_INTEGER&&issum) || columns.get(i).getF_type() ==Column.CT_FLOAT){
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
		    boolean issum=columns.get(i).getF_summary_type()==null?false:columns.get(i).getF_summary_type().equals("sum")?true:false;
			if((columns.get(i).getF_type() == Column.CT_INTEGER&&issum) || columns.get(i).getF_type() ==Column.CT_FLOAT){
%>
			var <%=columns.get(i).getRealFieldName()%>_sum = atwa.util.Format.round(<%=columns.get(i).getRealFieldName()%>_sum + record.get("<%=columns.get(i).getRealFieldName()%>"),2);
<%
			}
		}
%>
		}

<%
        int j=0;
		for(int i=0;i<columns.size();i++){
		    if(columns.get(i).getF_type() != Column.CT_NONE&&j==0){
		       j=1;
%>
		       newrec.set("<%=columns.get(i).getRealFieldName()%>", "合计");
<%
		    }
		    boolean issum=columns.get(i).getF_summary_type()==null?false:columns.get(i).getF_summary_type().equals("sum")?true:false;
			if((columns.get(i).getF_type() == Column.CT_INTEGER&&issum) || columns.get(i).getF_type() ==Column.CT_FLOAT){
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

	// 输出附加脚本 end
	panel.add(grid);
	panel.doLayout();
	// 初始化视图数据
	panel.refresh();
});
</script>
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

function detailHandler(id, panelid){
	var panel = Ext.getCmp(panelid);
	if(Ext.isDefined(panel)){
		panel.detailHandler(id);
	}
}

Ext.onReady(function(){

	var panel = Ext.getCmp('<%=panelid%>');
	var win = panel.findParentByType('window');
	var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];
	
	var colWidth = 150;
	var rowHeight = 100;
<%
	if(cfg!=null && cfg.has("colWidth")){
		out.print("colWidth = " + cfg.get("colWidth").getAsInt() + ";");
	}
	if(cfg!=null && cfg.has("rowHeight")){
		out.print("rowHeight = " + cfg.get("rowHeight").getAsInt() + ";");
	}
%>
	var fields = [{
		name : 'WEEK',
		mapping : 'WEEK'
	},{
		name : 'MON',
		mapping : 'MON'
	},{
		name : 'TUE',
		mapping : 'TUE'
	},{
		name : 'WED',
		mapping : 'WED'
	},{
		name : 'THU',
		mapping : 'THU'
	},{
		name : 'FRI',
		mapping : 'FRI'
	},{
		name : 'SAT',
		mapping : 'SAT'
	},{
		name : 'SUN',
		mapping : 'SUN'
	}];
	// 数据访问
	var storeWorkday = new Ext.data.DirectStore({
		directFn : eval(app.keyPrefix + 'AttendanceAppDirect.getMonthDaySetting'),
		paramOrder : ['day'],
		baseParams : {day:'<%=DateTool.formatDate(new Date(System.currentTimeMillis()), "yyyy-MM")%>-01'},
		remoteSort : true,
		root : 'results',
		totalProperty : 'totals',
		idProperty : 'WEEK',
		fields : fields
	});
	// 数据访问
	var store = new Ext.data.DirectStore({
		directFn : <%=directFn%>,
		paramOrder : ['viewkey','querytype','limit','start','sort','dir','params'],
		baseParams : {viewkey:'<%=view.getKey()%>', querytype:<%=ViewDataService.QT_NORMAL%>,limit:0, start:0, sort:'',dir:'',params:{}},
		remoteSort : true,
		root : 'results',
		totalProperty : 'totals',
		idProperty : '<%=view.getF_keycolumn()%>',
		fields : <%=ViewDataService.instance().getViewStoreFields(columns, view.getF_enable_favorite())%>,
		listeners : {
			'beforeload' : function(){
				store.baseParams.params.F_MONTH = storeWorkday.baseParams.day.substring(0,7);
			},
			'load' : function(store, records, options){
				if(Ext.isDefined(options.params.params.username)){
					panel.setTitle(panel.baseTitle + ' ' + options.params.params.username);
				}
				storeWorkday.each(function(row){
					for(var i=0;i<7;i++){
						var c = Ext.apply({}, row.get(fields[i+1].name));
						var recs = [];
						for(var j=0;j<records.length;j++){
							var rec = records[j];
							if(rec.get("F_DATE").substring(0,10) == c.date){
								recs.push(rec.id);
							}
						}
						if(recs.length > 0){
							c.records = recs;
							row.set(fields[i+1].name,c);
						}else if(Ext.isDefined(c.records)){
							c.records = undefined;
							row.set(fields[i+1].name, c);
						}
					}
					row.commit();
				});
			}
		}
	});
	storeWorkday.on('load', function(){
		var title = '<%=view.getF_caption()%> ' + storeWorkday.baseParams.day;
		panel.setTitle(title);
		panel.baseTitle = title;
		store.reload();
	})
	// 打开函数
	panel.detailHandler = function(id){
<%
		if(cfg!=null && cfg.has("detailHandler")){
			out.print(cfg.get("detailHandler").getAsString());
		}
%>
	}
	// 渲染定义
	var cellTpl = new Ext.Template('<div style="padding-left:8" class="{titlecss}">{day}</div>');
	cellTpl.compile();
<%
	if(cfg!=null && cfg.has("detailTemplate")){
		out.print("var detailTpl = new Ext.Template('" + cfg.get("detailTemplate").getAsString().replaceAll("'", "\'") + "');");
	}else{
		out.print("var detailTpl = new Ext.Template('<div class=\"mixky-attendance-detail\" title=\"{0} {1}\"><a class=\"mixky-link-normal\" href=# onclick=\"javascript:detailHandler({2},\\\'{3}\\\');\">{0} {1:ellipsis(18)}</a></div>');");
	}
%>
	detailTpl.compile();
	var renderDetail = function(record){
<%
	if(cfg!=null && cfg.has("detailRenderer")){
		out.print(cfg.get("detailRenderer").getAsString());
	}else{
%>
		var dt = Date.parseDate(record.get("F_DATE"), 'Y-m-d H:i');
		return detailTpl.apply([dt.format('H:i'), record.get("F_TITLE"), record.get("ID"), '<%=panelid%>']);
<%
	}
%>
	}
	var renderCell = function(value, metadata, record , rowIndex, colIndex, s){
		metadata.css = 'mixky-attendance-cell';
		metadata.attr = 'style="padding:0"';
		var titlecss = '';
		switch(value.dayType){
		case 0:	// 休息日
			titlecss = 'mixky-attendance-restday-cell';
			break;
		case 1:	// 工作日
			titlecss = 'mixky-attendance-workday-cell';
			break;
		case 2: // 非当前月
			metadata.css = 'mixky-attendance-otherday-cell';
			return;
		}
		if(value.date == '<%=DateTool.formatDate(new Date(System.currentTimeMillis()), null)%>'){
			titlecss = 'mixky-attendance-today-cell';
		}
		var output = cellTpl.apply({
			titlecss : titlecss,
			day : value.day
		});
		if(Ext.isDefined(value.records)){
			for(var i=0;i<value.records.length;i++){
				if(!store.getById(value.records[i])){
					//alert(value.day + " - " + value.records[i]);
				}else{
					output = output + renderDetail(store.getById(value.records[i]));
				}
			}
		}
		return output;
	}
	// 选择器
	var sm = new Ext.grid.CellSelectionModel({
		listeners : {
			'beforecellselect' : function(sm, row, col){
				var record = storeWorkday.getAt(row);
				var fieldname = fields[col].name;
				var data = record.get(fieldname);
				if(data.dayType == 2){
					return false;
				}
			}
		}
	});
	// 显示列
	var columns = [{
		fixed:true,
	    menuDisabled:true,
	    width: 23,
		id : 'numberer',
		dataIndex : 'WEEK',
		renderer : function(value, metadata, record , rowIndex, colIndex, s){
			metadata.attr = "style='height:" + rowHeight + "px'";
			return value;
		}
	}, {
		id : 'MON',
		dataIndex : 'MON',
		header : '周一',
		menuDisabled : true,
	    width: colWidth,
		renderer : renderCell
	}, {
		id : 'TUE',
		dataIndex : 'TUE',
		header : '周二',
		menuDisabled : true,
	    width: colWidth,
		renderer : renderCell
	}, {
		id : 'WED',
		dataIndex : 'WED',
		header : '周三',
		menuDisabled : true,
	    width: colWidth,
		renderer : renderCell
	}, {
		id : 'THU',
		dataIndex : 'THU',
		header : '周四',
		menuDisabled : true,
	    width: colWidth,
		renderer : renderCell
	}, {
		id : 'FRI',
		dataIndex : 'FRI',
		header : '周五',
		menuDisabled : true,
	    width: colWidth,
		renderer : renderCell
	}, {
		id : 'SAT',
		dataIndex : 'SAT',
		header : '周六',
		menuDisabled : true,
	    width: colWidth,
		renderer : renderCell
	}, {
		id : 'SUN',
		dataIndex : 'SUN',
		header : '周日',
		menuDisabled : true,
	    width: colWidth,
		renderer : renderCell
	}];
	// 选择当前月
	var dateSelect = new Ext.form.DateField({
		value:'<%=DateTool.formatDate(new Date(System.currentTimeMillis()), "yyyy-MM")%>-01', 
		editable : false, 
		format : 'Y-m-d'
	});
	// 确定
	var btnChange = new Ext.Action({
		iconCls : 'icon-common-apply',
		handler : function(){
			storeWorkday.baseParams.day = dateSelect.getRawValue();
			storeWorkday.reload();
		}
	});
	// 上月
	var btnNextMonth = new Ext.Action({
		iconCls : 'icon-common-next',
		handler : function(){
			var date = new Date(storeWorkday.baseParams.day);
			dateSelect.setValue(date.add(Date.MONTH, 1).format('Y-m-d'));
			btnChange.execute();
		}
	});
	// 下月
	var btnPreMonth = new Ext.Action({
		iconCls : 'icon-common-back',
		handler : function(){
			var date = new Date(storeWorkday.baseParams.day);
			dateSelect.setValue(date.add(Date.MONTH, -1).format('Y-m-d'));
			btnChange.execute();
		}
	});
	// 刷新
	var btnRefresh = new Ext.Action({
		iconCls : 'icon-common-refresh',
		text : '刷新',
		handler : function(){
			panel.refresh();
		}
	});
	// 视图操作
	var tools = [btnPreMonth, btnNextMonth,'-',dateSelect, btnChange, '->'];
	var contextmenus = [];
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
	tools.push(<%=action.getF_key()%>);
	contextmenus.push(<%=action.getF_key()%>);
<%
	}
%>
	tools.push(btnRefresh);
	
	// 表格对象
	var grid = new Ext.grid.GridPanel({
		border : false,
		stripeRows: false,
		trackMouseOver: false,
		enableHdMenu : true,
		lineBreak : true,
		cellSelect : true,
		columnLines : true,
        loadMask: {msg:'正在装载...'},
        tbar:tools,
		contextMenu : new Ext.menu.Menu({items:contextmenus}),
		listeners : {
			'cellcontextmenu' : function(g, row, col, e){
				g.getSelectionModel().select(row, col);
				g.contextMenu.showAt(e.getXY());
			}
		},
		sm : sm,
		columns : columns,
		store : storeWorkday
	});
	
	panel.getSelectedCell = function(){
		var cell = sm.getSelectedCell();
		if(cell != null){
			var record = storeWorkday.getAt(cell[0]);
			var fieldname = fields[cell[1]].name;
			var data = record.get(fieldname);
			return {record : record, fieldname:fieldname, data:data};
		}
		return undefined;
	}
	
	// 视图刷新
	panel.refresh = function(params, notdoload){
		if(Ext.isDefined(params)){
			panel.params = params;
		}
		// 初始化参数
		store.baseParams.params = {};
		Ext.apply(store.baseParams.params, panel.viewparams);
		Ext.apply(store.baseParams.params, panel.params);
		if(notdoload !== true){
			store.reload();
		}
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
	// 输出附加脚本 end
	panel.add(grid);
	panel.doLayout();
	// 初始化视图数据
	storeWorkday.load();
	panel.refresh(panel.initParams, true);
});
</script>
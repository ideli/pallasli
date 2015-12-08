<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="com.mixky.engine.authority.AuthorityDataService"%>
<%@ page import="com.mixky.engine.organization.dao.User"%>
<%@ page import="com.mixky.engine.design.view.View"%>
<%@ page import="com.mixky.engine.design.view.ViewDataService"%>
<%@ page import="com.mixky.engine.design.view.Column"%>
<%@ page import="com.mixky.engine.design.Action"%>
<%@ page import="com.mixky.engine.design.module.DocumentType"%>
<%@ page import="com.mixky.engine.design.DesignObjectLoader"%>
<%@ page import="com.mixky.toolkit.gson.JsonFunction"%>
<%@ page import="com.google.gson.*"%>
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
	

	JsonArray quickFormFields = new JsonArray();
	ListTool.sort(columns, true, "f_order", "f_name");
	// 查询表单列数
	int curCols = 0;
	int formColumns=3;
	int tmpFormColumns=formColumns;
	if (view.getF_config() != null && view.getF_config().has("queryFormColumns")) {
		formColumns = view.getF_config().get("queryFormColumns").getAsInt();
	}
	JsonArray jsonRowItems = null;
	JsonObject tmpFieldset = new JsonObject();
	tmpFieldset.addProperty("xtype", "fieldset");
	tmpFieldset.addProperty("border", false);
	JsonArray tmpItems = new JsonArray();
	DecimalFormat formator = new DecimalFormat( ".## ");
	for (int i = 0; i < columns.size(); i++) {
		Column column = columns.get(i);
		JsonObject json = QueryEditorManager.instance().getQueryEditor(column, user);
			if(json != null&&!(column.getF_config()!=null
					&&column.getF_config().has("isAdvanceField")&&column.getF_config().get("isAdvanceField").getAsBoolean())){
					if(curCols == 0){
					// 生成新行
					JsonObject row = new JsonObject();
					row.addProperty("layout", "column");
					row.addProperty("border", false);
					jsonRowItems = new JsonArray();
					tmpFormColumns=formColumns;
					boolean isfirst=true;
					for(int j=0;j<tmpFormColumns;j++){
						JsonObject columnJson = new JsonObject();

						Column tmpcolumn = columns.get(i+j);
						 if(tmpcolumn!=null&&tmpcolumn.getF_config()!=null
									&&tmpcolumn.getF_config().has("hidden")&&tmpcolumn.getF_config().get("hidden").getAsBoolean()){

							columnJson.addProperty("columnWidth", formator.format(0));
							columnJson.addProperty("hiddden", true);
						    	
						    tmpFormColumns++;
						}else{

							if(!isfirst){
								columnJson.addProperty("style", "padding-left:10px;");
								isfirst=false;
							}
							float width = (float)((float)1 / (float)formColumns);
							columnJson.addProperty("columnWidth",  width);
						}
						columnJson.addProperty("layout", "form");
						columnJson.addProperty("border", false);
						columnJson.addProperty("labelWidth", 80);
						jsonRowItems.add(columnJson);
						
						
					}
					row.add("items", jsonRowItems);
					tmpItems.add(row);
					tmpFieldset.add("items", tmpItems);
				}
					
				// 加入表单布局中

					if(column.getF_query_type()==3){ 
						float width = formColumns <=2 ?(float)((float)1 / (float)formColumns) : (float)((float)1.2 / (float)formColumns);
						jsonRowItems.get(curCols).getAsJsonObject().addProperty("columnWidth", formator.format(width));
					}
				jsonRowItems.get(curCols).getAsJsonObject().add("items", json);
				curCols ++;
				if(curCols >= tmpFormColumns){
					curCols = 0;
				}
			}
	}
	quickFormFields.add(tmpFieldset);
	

	JsonArray moreFormFields = new JsonArray();
	int  morecurCols = 0;
	int moreformColumns=4;
	JsonArray morejsonRowItems = null;
	morejsonRowItems = new JsonArray();
	JsonObject row = new JsonObject();
	row.addProperty("layout", "column");
	row.addProperty("border", false);
	for (int i = 0; i < columns.size(); i++) {
		Column column = columns.get(i);
		JsonObject json = QueryEditorManager.instance().getQueryEditor(column, user);
		if(json != null&&column.getF_config()!=null
				&&column.getF_config().has("isAdvanceField")&&column.getF_config().get("isAdvanceField").getAsBoolean()){
					// 生成新行
						JsonObject columnJson = new JsonObject();
						float width = (float)((float)1 / (float)moreformColumns);
						if(column.getF_query_type()==3){width=width*2f;}
						columnJson.addProperty("columnWidth", formator.format(width));
						columnJson.addProperty("layout", "form");
						columnJson.addProperty("border", false);
						columnJson.addProperty("labelWidth", 88);
						columnJson.add("items", json);
						columnJson.addProperty("style", "padding-left:10px;");
						morejsonRowItems.add(columnJson);
					row.add("items", morejsonRowItems);
			}
	}
	moreFormFields.add(row);
%>

<%@page import="com.mixky.engine.design.view.QueryEditorManager"%>
<%@page import="com.mixky.toolkit.ListTool"%>
<%@page import="java.text.DecimalFormat"%>
<object classid="clsid:E6E0A751-541A-4855-9A8D-35EB7122C950" id="SynIDCard1" codeBase="SynIDCard.Cab#version=1,0,0,1" width=0 height=0>
  <param name="_Version" value="65536">
  <param name="_ExtentX" value="635">
  <param name="_ExtentY" value="582">
  <param name="_StockProps" value="0">
</object>
<script language='javascript'>
Ext.onReady(function(){

	var panel = Ext.getCmp('<%=panelid%>');
	var win = panel.findParentByType('window');
	var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];
	
	panel.getFieldValue = function(name){
	  <%if(ViewDataService.instance().isViewHasQuery(view)){%>
	   var field = advanceForm.getForm().findField(name);
	   if(field){
		   return field.getValue();
	   }
	   <%}%>
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
	store.on('load', function(s){
		Mixky.wasoft.lib.showFavoriteById(app.key, s, '<%=dtkey%>');
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
    String defaultAction = "";
	for(int i=0;i<actions.size();i++){
		Action action = actions.get(i);
%>
	<%=action.output()%>
<%
        if(action.getF_default()){
			defaultAction = action.getF_key() + ".execute()";
		}
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
	    				panel.queryParams1 = {<%=ViewDataService.VN_QUICK_QUERY_PARAM_NAME%>: value};
	    				panel.refresh();
	    			}
	            }
	        }
	    }
	});
	var <%=ViewDataService.VN_QUICK_QUERY_BUTTON_NAME%> = new Ext.Action({
		text : '快速检索',
		iconCls : 'icon-common-query',
		handler : function(){
			var value = <%=ViewDataService.VN_QUICK_QUERY_FIELD_NAME%>.getValue();
			if(Ext.isDefined(value) && value != ''){
				store.baseParams.querytype = <%=ViewDataService.QT_QUICK%>;
				panel.queryParams1 = {<%=ViewDataService.VN_QUICK_QUERY_PARAM_NAME%>: value};
				panel.refresh();
			}
		}
	});
	var advanceForm = new Ext.form.FormPanel({
		padding : 5,
		labelWidth : 90,
		autoHeight: true,
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
		items : <%=moreFormFields.toString()%>
	});
	
<%
	}
%>

	
	var contextmenus = <%=ViewDataService.instance().getViewContextMenuNames(actions)%>;
	// 表格对象
	var grid = new Ext.grid.GridPanel({
		border : false,
		height: panel.getHeight(),
		stripeRows: true,
		lineBreak : false,
		cellSelect : true,
        loadMask: {msg:'正在装载...'},
        <%
          if(view.getF_autoexpandcolumn()!=null&&!view.getF_autoexpandcolumn().trim().equals("")){
              out.print("autoExpandColumn :'"+view.getF_autoexpandcolumn()+"',");
          }
        %>
		//autoExpandColumn : '<%=view.getF_autoexpandcolumn()%>',
		sm : sm,
		columns : columns,
		store : store,
		contextMenu : new Ext.menu.Menu({items:contextmenus}),
		//enableDragDrop : true,
		// 输出附加脚本 begin
		<%
			if(cfg!=null && cfg.has("enableDragDrop")){
				out.print("enableDragDrop: " + cfg.get("enableDragDrop").getAsBoolean() + ",");
			}
		%>
		<%
			if(cfg!=null && cfg.has("enableHdMenu")){
				out.print("enableHdMenu: " + cfg.get("enableHdMenu").getAsBoolean() + ",");
			}
			else{
			   out.print("enableHdMenu: true,");
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
               		store : [10,20,30,40,50,100,200,300,400,500],
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
				if(record.get("<%=view.getF_keycolumn()%>")<0){
					return 'mixky-grid-row-sum';
				}
				else{
				    return 'wasoft-grid-cell-inner';
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
					   <%
					     if(!defaultAction.trim().equals("")){
					        String btns = defaultAction.substring(0,defaultAction.indexOf("."));
					    %>
					       if(Ext.isFunction(<%=btns+".execute"%>)){
					            <%=defaultAction%>;
					        }
					        else{
					           if(<%=btns%>.handler){
					             <%=btns%>.handler.call();
					           }
					        }
					    <%
					     }
					   %>
				}
			}
		},
		getSelectedRecords : function(){
			return this.getSelectionModel().getSelections();
		},
		isSumRecords : function(index){
			var record = store.getAt(index);
			if(record && record.get("<%=view.getF_keycolumn()%>") <0){
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
			store.baseParams.start = 0;
		}
		
		<% if(ViewDataService.instance().isViewHasQuery(view)){%>
				var params = commonForm.getForm().getFieldValues();
				var mparams = {};
				for(i in params){
					if(params[i] != ''){
						mparams[i] = params[i];
					}
				}
				panel.queryParams = mparams;

				if(!advanceForm.hidden){
					var params = advanceForm.getForm().getFieldValues();
					var mparams = {};
					for(i in params){
						if(params[i] != ''){
							mparams[i] = params[i];
						}
					}
					Ext.apply(panel.queryParams , mparams);
				}
		<%}%>
		// 初始化参数

		store.baseParams.params = {};
		Ext.apply(store.baseParams.params, panel.viewparams);
		Ext.apply(store.baseParams.params, panel.params);
		// 处理查询参数
		//if(store.baseParams.querytype != <-%=ViewDataService.QT_NORMAL%>){
		    Ext.apply(store.baseParams.params,panel.queryParams1);
			Ext.apply(store.baseParams.params, panel.queryParams);
		//}
		iscx=false;
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
	
	store.on('load', function(t,records,o){
		if(records.length > 0) {
			panel.btnEnable();
		}
		else{
		    panel.btnDisable();
		}
		   var so = store.lastOptions;
	        var sop = so? so.params : null;
	        var sonumber = ((sop && sop.start && sop.limit)? sop.start : 0)+ records.length;
	        if(sonumber > 99 && sonumber < 1000){
	         grid.getColumnModel().setColumnWidth(0, 28);
	        }
	        else if(sonumber > 999 && sonumber < 10000){
	         grid.getColumnModel().setColumnWidth(0, 35);
	        }
	        else if(sonumber > 9999){
	         grid.getColumnModel().setColumnWidth(0, 42);
	        }
	        else{
	            grid.getColumnModel().setColumnWidth(0, 23);
	        }
	})
	
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



	var formbuttons=[];
	<%
		for(Action button:actions){
	%> 
			var <%=button.getF_name()+"_Id"%>;
			if(<%=button.getF_name()%>){ <%=button.getF_name()+"_Id"%>=<%=button.getF_name()%>.itemId;}
			var <%=button.getF_name()%> ;
	<%
			String xtype=
					(button.getF_config()!=null&&button.getF_config().has("xtype")
					?button.getF_config().get("xtype").getAsString():"button");
			if("separator".equals(xtype)){}else if(
					button.getF_config()!=null&&button.getF_config().has("hideInToolbar")
							&&button.getF_config().get("hideInToolbar").getAsBoolean()
					){
				%>
				  <%=button.getF_name()%> = new Ext.Button({
						text : '<%=button.getF_caption()%>',
						iconCls : '<%=button.getIcon()%>',
						anchor:'100%',
						handler : (<%=button.getRunHandler()%>)
					});
				  if(<%=button.getF_name()+"_Id"%>){
					  <%=button.getF_name()%>.itemId=<%=button.getF_name()+"_Id"%>;
				  }
	<%		}else{
				if("menu".equals(xtype)){
					   String btns=button.getF_handler();
						String[] button_vs=btns.substring(1,btns.length()-1).split(",");
					String menuitems="";
					for(int bi=0 ;bi<button_vs.length;bi++){
						String btn_v=button_vs[bi];
						String menuitem="{";
						menuitem+="text:"+btn_v+".text";
						menuitem+=","	;
						menuitem+="handler:"+btn_v+".handler";
						menuitem+="}"	;
						menuitems+=menuitem;
						menuitems+=",";
					}
					menuitems=menuitems.substring(0, menuitems.length()-1);
	%>
					  <%=button.getF_name()%> = new Ext.CycleButton({
						iconCls : '<%=button.getIcon()%>',
						anchor:'100%',
						items:[<%=menuitems%>]
					});
					<%=button.getF_name()%>.setText('<%=button.getF_caption()%>');

					  if(<%=button.getF_name()+"_Id"%>){
						  <%=button.getF_name()%>.itemId=<%=button.getF_name()+"_Id"%>;
					  }
	<%			}else{
	%>

					  <%=button.getF_name()%> = new Ext.Button({
						text : '<%=button.getF_caption()%>',
						iconCls : '<%=button.getIcon()%>',
						anchor:'100%',
						handler : (<%=button.getRunHandler()%>)
					});
					  if(<%=button.getF_name()+"_Id"%>){
						  <%=button.getF_name()%>.itemId=<%=button.getF_name()+"_Id"%>;
					  }
	 <%			}

	%>
				formbuttons.push({layout:'anchor',columnWidth:0.25,padding : 1,border:false,items:[<%=button.getF_name()%>]});
	<%		}
		}	
	%>
		var btnGd= new Ext.Button({
			text : '更多',
			anchor:'100%',
			iconCls:'x-toolbar-more-icon',
			handler : function(){

				advanceForm.setVisible(advanceForm.hidden);
				grid.setHeight(panel.getHeight()-queryForm.getHeight());
			}
		});
		// 刷新按钮
		var <%=ViewDataService.VN_ADVANCE_QUERY_BUTTON_NAME%> = new Ext.Button({
			text : '查询',
			anchor:'100%',
			iconCls : 'icon-common-aquery',
			handler : function(){

				<%
		           if(cfg!=null && cfg.has("queryscript")){
			           out.print(cfg.get("queryscript").getAsString() + '\n');
		            }
		         %>
		         
				store.baseParams.querytype = <%=ViewDataService.QT_ADVANCE%>;
				panel.refresh();
				advanceForm.hide();
				grid.setHeight(panel.getHeight()-queryForm.getHeight());
			}
		});
		var <%=ViewDataService.VN_REFRESH_BUTTON_NAME%> = new Ext.Button({
			text : '重置',
			anchor:'100%',
			iconCls : 'icon-common-refresh',
			handler : function(){
				var module = MixkyApp.desktop.getCurrentModule();
				if(module){
					var view = module.getCurrentView();
					if(view.getCurrentView){
						view = view.getCurrentView();
					}
					view.removeAll();
					view.doAutoLoad();
				}
			}
		});
		
		if(<%=morejsonRowItems.toString()%>.length>0)formbuttons.push({layout:'anchor',columnWidth:0.25,padding : 1,border:false,items:[btnGd]});
		formbuttons.push({layout:'anchor',columnWidth:0.25,padding : 1,border:false,items:[<%=ViewDataService.VN_ADVANCE_QUERY_BUTTON_NAME%> ]});
		formbuttons.push({layout:'anchor',columnWidth:0.25,padding : 1,border:false,items:[<%=ViewDataService.VN_REFRESH_BUTTON_NAME%> ]});

		var commonForm = new Ext.FormPanel({
			padding : 5,
			labelWidth : 90,
			layout : 'form',
			border : false,
			columnWidth: 0.7,
			items :  <%=quickFormFields.toString()%>
		});
		
		var buttonForm = new Ext.FormPanel({
			padding : 15,
			labelWidth : 90,
			layout : 'column',
			columnWidth: 0.3,
			border : false,
			items : formbuttons 
		});

		var topqueryForm = new Ext.Panel({
			layout : 'column',
			autoHeight: true,
			border : false,
			items : [commonForm, buttonForm]
		});

		var queryForm = new Ext.Panel({
			layout : 'anchor',
			autoHeight: true,
			border : false,
			items : [topqueryForm,advanceForm]
		});



	   

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
	for(int i=0;i<actions.size();i++){
		Action action = actions.get(i);	
		JsonObject json=action.getF_config();
		if(json!=null&&json.has("disabled")){
		   if(!json.get("disabled").getAsString().trim().equals("")){
		       out.print(action.getF_name()+".disable();");
		   }else{
		        out.print(action.getF_name()+".enable();");
		   }
		} 
	}
%>

panel.btnDisable = function(){
<%
  for(int i=0;i<actions.size();i++){
	Action action = actions.get(i);
	JsonObject json=action.getF_config();
	if(json!=null&&json.has("disabled")){
	   if(json.get("disabled").getAsString().trim().equals("true")){
	       out.print(action.getF_name()+".disable();panel.disableContextMenu("+action.getF_name()+".itemId);");
	   }else{
	        out.print(action.getF_name()+".enable();panel.enableContextMenu("+action.getF_name()+".itemId);");
	   }
	} 
 }
%>
}
 
panel.btnEnable = function(){
<%
  for(int i=0;i<actions.size();i++){
	Action action = actions.get(i);
	JsonObject json=action.getF_config();
	if(json!=null&&json.has("disabled")){
	   if(!json.get("disabled").getAsString().trim().equals("")){
	        out.print(action.getF_name()+".enable();panel.enableContextMenu("+action.getF_name()+".itemId);");
	   }
	} 
 }
%>
}

panel.enableContextMenu=function(actionItemId){
	for(var i=0;i<grid.contextMenu.items.length;i++){
		var tmpcm=grid.contextMenu.items.get(i);
		if(tmpcm.itemId==actionItemId){tmpcm.enable();return;}
	}
}
panel.disableContextMenu=function(actionItemId){
	for(var i=0;i<grid.contextMenu.items.length;i++){
		var tmpcm=grid.contextMenu.items.get(i);
		if(tmpcm.itemId==actionItemId){tmpcm.disable();return;}
	}
}

	panel.exportToExcel = function() {
		var selects = grid.getSelectionModel().getSelections();
		var ids = [];
		if (selects != null && selects.length > 0) {
			for (var i = 0; i < selects.length; i++) {
				ids[i] = selects[i].get("ID") || selects[i].get("id");
			}
		}
		
		var cols = grid.getColumnModel();
		var colsStr = '';
		var colsNames = '';
		for (var i = 0; i < cols.getColumnCount(); i++) {
			var col = cols.getColumnById(cols.getColumnId(i));
			if (!col.hidden && col.id != 'numberer' && col.id != 'checker') {
				colsStr += col.id + ',';
				//colsNames += col.header + ',';
			}
		}
		var excelParams = Ext.apply({},grid.getStore().baseParams);
		Ext.apply(excelParams,{ids: ids, colsStr:colsStr,panelTitle: panel.title});
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
	store.on('load', function(s, records, options){
		var newrec = new store.recordType({<%=view.getF_keycolumn()%> : -1});
<%
		for(int i=0;i<columns.size();i++){
		    boolean issum=columns.get(i).getF_summary_type()==null?false:columns.get(i).getF_summary_type().equals("sum")?true:false;
			if((columns.get(i).getF_type() == Column.CT_INTEGER&&issum)|| columns.get(i).getF_type() ==Column.CT_FLOAT){
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
			if((columns.get(i).getF_type() == Column.CT_INTEGER&&issum)||columns.get(i).getF_type() ==Column.CT_FLOAT){
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
		       newrec.set("<%=columns.get(i).getRealFieldName()%>", "本页小计");
<%
		    }
		    boolean issum=columns.get(i).getF_summary_type()==null?false:columns.get(i).getF_summary_type().equals("sum")?true:false;
			if((columns.get(i).getF_type() == Column.CT_INTEGER&&issum)|| columns.get(i).getF_type() ==Column.CT_FLOAT){
%>
		newrec.set("<%=columns.get(i).getRealFieldName()%>", <%=columns.get(i).getRealFieldName()%>_sum);
<%
			}
		}
%>
		store.add(newrec);
		// 添加总合计行
		var summaryFn = eval(app.keyPrefix + 'AppDirect.getViewSummary');
		var v_params=Ext.apply({}, options.params.params);
		if(records.length>0){
		   var record = records[0];	 
<%
		   for(int i=0;i<columns.size();i++){
			   if((columns.get(i).getF_type() == 0)){
%>
			       Ext.apply(v_params,{"<%=columns.get(i).getRealFieldName()%>": record.get("<%=columns.get(i).getRealFieldName()%>")});
<%
			   }
		   }
%>
       }
        Ext.apply(v_params,{count:store.getTotalCount()});
		summaryFn('<%=view.getKey()%>', options.params.querytype,v_params,function(result, e){
			if(result && result.success&&result.summary){
			     if(store.getCount() > 0){
			         var record = store.getAt(store.getCount()-1);
			  <%
			     for(int i=0;i<columns.size();i++){
			       if(columns.get(i).getF_type() != Column.CT_NONE){
			  %>
			      if(record.get("<%=columns.get(i).getRealFieldName()%>")=='总计'){
			           store.removeAt(store.getCount()-1);
			       } 
			   <%}}%>
			    }
				
				var newrec = new store.recordType({<%=view.getF_keycolumn()%> : -2});
				<%
		        int k=0;
				for(int i=0;i<columns.size();i++){
				    if(columns.get(i).getF_type() != Column.CT_NONE&&k==0){
				       k=1;
		%>
				       newrec.set("<%=columns.get(i).getRealFieldName()%>", "总计");
		<%
				    }
				}
		%>
				for(var n in result.summary){
					newrec.set(n,result.summary[n]);
				}
				store.add(newrec);
			}
		});
	})
<%
	}
%>

	var ui = new Ext.Panel({
		layout : 'anchor',
		border : false,
		items : [queryForm, grid]
	});
	// 输出附加脚本 end
	panel.add(ui);
	panel.doLayout();
	queryForm.doLayout();
	// 初始化视图数据
	advanceForm.setVisible(advanceForm.hidden);
	grid.setHeight(panel.getHeight()-queryForm.getHeight());
	if(<%=autoLoad%>){
		panel.refresh(panel.initParams);
	}
	panel.setAutoScroll(false);
});
</script>
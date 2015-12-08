<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="com.google.gson.JsonArray"%>
<%@ page import="com.mixky.toolkit.gson.JsonFunction"%>
<%@ page import="com.mixky.common.database.JsonObjectDao"%>
<%@ page import="com.mixky.engine.organization.dao.User"%>
<%@ page import="com.mixky.engine.design.Action"%>
<%@ page import="com.mixky.engine.design.DesignObjectLoader"%>
<%@ page import="com.mixky.engine.design.document.DocumentManager"%>
<%@ page import="com.mixky.engine.design.document.Document"%>
<%@ page import="com.mixky.engine.design.document.Panel"%>
<%@ page import="com.mixky.engine.design.store.StoreManager"%>
<%@ page import="com.mixky.engine.design.store.Table"%>
<%@ page import="com.mixky.engine.design.store.Field"%>
<%@ page import="com.mixky.engine.design.store.TableForm"%>
<%@ page import="com.mixky.engine.design.store.TableFormDataService"%>
<%@ page import="com.mixky.engine.design.document.ObjectAuthority"%>
<%@ page import="com.mixky.engine.design.view.View"%>
<%@ page import="com.mixky.engine.design.view.ViewDataService"%>
<%@ page import="com.mixky.engine.design.view.Column"%>
<%@ page import="com.mixky.engine.authority.AuthorityDataService"%>
<%@ page import="com.mixky.toolkit.ListTool"%>
<%@ page import="com.mixky.toolkit.JsonObjectTool"%>
<%@ page import="com.wasoft.process.PROCESSITEM"%>

<%
	// 读取参数
	String panelid = request.getParameter("panelid");
	String documentid = request.getParameter("documentid");
	String appkey = request.getParameter("appkey");
	Panel panel = (Panel)request.getAttribute("panel");
	User user = (User)request.getAttribute("user");
	Document document = (Document)request.getAttribute("document");
	JsonObjectDao data = (JsonObjectDao)request.getAttribute("data");
	Map<String, ObjectAuthority> map = (Map<String, ObjectAuthority>)request.getAttribute("authmap");	

	String unid = request.getParameter("unid");
	String docid = request.getParameter("docid");
	String type = request.getParameter("type");	
	
	String workflowTopHtml=(String)request.getAttribute("workflowTopHtml");
	String viewWorkflowInfoJS=(String)request.getAttribute("viewWorkflowInfoJS");
	String workflowValidator=(String)request.getAttribute("workflowValidator");
	
	// 获得按钮权限
	List<ObjectAuthority> buttonauths = (List<ObjectAuthority>)request.getAttribute("buttonauths");
	List<ObjectAuthority> panelauths = (List<ObjectAuthority>)request.getAttribute("panelauths");
	// 获得字段权限	
	List<ObjectAuthority> fieldauths = (List<ObjectAuthority>)request.getAttribute("fieldauths");
	List<ObjectAuthority> flowauths = (List<ObjectAuthority>)request.getAttribute("flowauths");	

	JsonObject workflowLayout=(JsonObject)request.getAttribute("workfolwLayout");
	
	//JsonArray formLayout=(JsonArray)request.getAttribute("formLayout");	
	JsonArray businessLayout=(JsonArray)request.getAttribute("businessLayout");
	//JsonArray hideLayout=new JsonArray();
	
	//TableForm tableform=(TableForm)request.getAttribute("tableform");	

	PROCESSITEM processitem=(PROCESSITEM)request.getAttribute("processitem");	
	
	// 获得按钮权限
	//List<ObjectAuthority> buttonauths = DocumentManager.instance().getFilterObjectAuthority(map, panel.getF_buttons(), user);
	//List<ObjectAuthority> panelauths = DocumentManager.instance().getFilterObjectAuthority(map, document.getF_panels(), user);
	// 查找对应的表单
	List<TableForm> tableforms = new ArrayList<TableForm>();
	
	for(int i=0;i<panelauths.size();i++){
		if(panelauths.get(i).hasAuth(ObjectAuthority.A_READ) || panelauths.get(i).hasAuth(ObjectAuthority.A_EDIT)){
			Panel p = (Panel)panelauths.get(i).getObject();
			if(p.getF_config() != null && p.getF_config().has("mergeTo")){
				if(panel.getF_name().equals(p.getF_config().get("mergeTo").getAsString())){
					tableforms.add((TableForm)DesignObjectLoader.instance().loadDesignObject(p.getF_i_tableform().get("data").getAsString()));
				}
			}
		}
	}
	// 所有字段权限
	//List<ObjectAuthority> fieldauths = new ArrayList<ObjectAuthority>();
	// 表单布局
	JsonArray formLayout = new JsonArray();
	String formsStr = "";
	for(int i=0;i<tableforms.size();i++){
		
		if(i!=0){
			if(map == null){
				ListTool.linkList(fieldauths, TableFormDataService.instance().getFormFieldAuths(tableforms.get(i)), false);
			}else{
				List<Field> fields = TableFormDataService.instance().getFormFields(tableforms.get(i));
				ListTool.linkList(fieldauths,  DocumentManager.instance().getFilterObjectAuthority(map, fields, user), false);
			}
		}
		
		JsonArray layout = TableFormDataService.instance().getFormColumnLayout(tableforms.get(i));
		JsonObject fieldset = new JsonObject();
		fieldset.addProperty("xtype", "fieldset");
		//fieldset.addProperty("key", tableforms.get(i).getF_key());
		fieldset.addProperty("checkboxToggle", "true");
		fieldset.addProperty("title", tableforms.get(i).getF_caption());
		fieldset.add("items", layout);
		formLayout.add(fieldset);
		if(i > 0){
			formsStr = formsStr + ";";
		}
		formsStr = formsStr + tableforms.get(i).getKey();
		if(tableforms.get(i).getF_config() != null && tableforms.get(i).getF_config().has("configs")){
			JsonObjectTool.applyJson(fieldset, tableforms.get(i).getF_config().get("configs").getAsJsonObject());
		}
	}
	if(flowauths!=null){	
		ListTool.linkList(fieldauths, flowauths, false);
	 }
	
	// 表单数组
	JsonObject formsField = new JsonObject();
	formsField.addProperty("xtype", "hidden");
	formsField.addProperty("name", "forms");
	formsField.addProperty("value", formsStr);
	formLayout.add(formsField);
	
	String loadDirect = "eval(app.keyPrefix + 'AppDirect.loadFormData')";
	String submitDirect = "eval(app.keyPrefix + 'AppDirect.submitFormData')";
	String formValidator = null;
	if(panel.getF_config() != null && panel.getF_config().has("loadDirect")){
		loadDirect = panel.getF_config().get("loadDirect").getAsString();
	}
	if(panel.getF_config() != null && panel.getF_config().has("submitDirect")){
		submitDirect = panel.getF_config().get("submitDirect").getAsString();
	}
	if(panel.getF_config() != null && panel.getF_config().has("validator")){
		formValidator = panel.getF_config().get("validator").getAsString();
	}
	
	String workflowFieldConfig=null;
	if(panel.getF_config() != null && panel.getF_config().has("workflowFieldConfig")){
		workflowFieldConfig = panel.getF_config().get("workflowFieldConfig").getAsString();
	}

%>
<script language='javascript'>
  <%=(viewWorkflowInfoJS==null)?"":viewWorkflowInfoJS%>   
</script>
<body scroll='auto'>
  <%=(workflowTopHtml==null)?"": workflowTopHtml%>
</body>
<script language='javascript'>
Ext.onReady(function(){

	var panel = Ext.getCmp('<%=panelid%>');
	var win = panel.findParentByType('window');
	var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];
	
	var documentid = <%=documentid%>;
	var uname='<%=user.getF_caption()%>';
	var uid='<%=user.getId()%>';
	
	var refreshAction = new Ext.Action({
		text : '刷新',
		iconCls : 'icon-common-refresh',
		handler : function(){
			panel.refresh();
		}
	});

<%
   for(int i=0;i<fieldauths.size();i++){
	   ObjectAuthority auth = fieldauths.get(i);
	   Field field = (Field)auth.getObject();
	   if(field.getF_inputtype() == Field.INPUTT_GRID){
	       JsonObject fcfg = field.getF_config();
	       String viewkey = "";
	       if(fcfg != null && fcfg.has("viewkey")){
				viewkey = fcfg.get("viewkey").getAsString();
		   }
	       if(!viewkey.trim().equals("")){
		      View view = DesignObjectLoader.instance().loadDesignObject(viewkey);
		      JsonObject cfg = view.getF_config();
		      
		      TableForm viewform = null;
		      if(cfg.has("editorform"))viewform =DesignObjectLoader.instance().loadDesignObject(cfg.get("editorform").getAsString());
		   
		      List<Column> columns = AuthorityDataService.instance().getDesignObjectsByUser(view.getF_columns(), user);
		      
		      String directFn = "eval(app.keyPrefix + 'AppDirect.getViewResults')";
		      if(cfg != null && cfg.has("directFn")){
		          directFn = cfg.get("directFn").getAsString();
		      }
		      
%>
          var <%=field.getF_name()+"store"%> = new Ext.data.DirectStore({
				directFn : <%=directFn%>,
				paramOrder : ['viewkey','querytype','limit','start','sort','dir','params'],
				baseParams : {viewkey:'<%=view.getKey()%>',querytype:0,limit:0, start:0, sort:'',dir:'',params:{}},
				remoteSort : true,
				root : 'results',
				totalProperty : 'totals',
				idProperty : '<%=view.getF_keycolumn()%>',
				fields : <%=ViewDataService.instance().getViewStoreFields(view.getF_columns())%>
			});
<%
            if(view.getF_single_select()){
%>
		   var <%=field.getF_name()+"sm"%> = new Ext.grid.RowSelectionModel({singleSelect : true});	
<%
            }else{
%>
           var <%=field.getF_name()+"sm"%> = new Ext.grid.CheckboxSelectionModel();
     
<%		
            }
            if(auth.hasAuth(ObjectAuthority.A_READ)){
%>
             var <%=field.getF_name()+"columns"%> = <%=ViewDataService.instance().getViewColumns(columns)%>;
<% 
            }
            else if(auth.hasAuth(ObjectAuthority.A_EDIT)){
%>
           var <%=field.getF_name()+"columns"%> = <%=ViewDataService.instance().getViewColumnsByTableForm(view.getF_columns(), viewform)%>;
<%}%>
            var <%=field.getF_name()%> = new Ext.grid.EditorGridPanel({
					region:'center',
					border : false,
					autoExpandColumn : '<%=view.getF_autoexpandcolumn()%>',
					enableHdMenu : false,
					autoHeight:true,
					viewConfig:{
						getRowClass: function(record, index) {
							if(record.dirty){
								return 'mixky-grid-row-changed';
							}
					    }
					},
					sm : <%=field.getF_name()+"sm"%>,
					columns : <%=field.getF_name()+"columns"%>,
					store : <%=field.getF_name()+"store"%>,
		<%
			        if(cfg != null && cfg.has("plugins")){
				         out.print("plugins: " + new JsonFunction(cfg.get("plugins").getAsString()) + ",");
			        }
		%>
					listeners : {
						'rowcontextmenu' : function(g, rowIndex, e){
							g.getSelectionModel().selectRow(rowIndex);
							g.contextMenu.showAt(e.getXY());
						}
					},
					getSelectedRecords : function(){
						return this.getSelectionModel().getSelections();
					}
			  });      
<%      
		  }
	   }
	   else{
%>
	      var <%=field.getF_name()%> = <%=StoreManager.instance().getFieldEditor(auth) %>;
	      <%=field.getF_name()%>.document = panel;
<%           
		   if(field.getF_inputtype() == Field.INPUTT_NONE || auth.getMode().trim().equals("1000")){
				  boolean isadd = true;
			      for(int k=0;k<formLayout.size();k++){
			         if(formLayout.get(k).equals(new JsonFunction(field.getF_key()))){
			            isadd = false;
			            break;
			         }
			      }
			      if(isadd){
				      formLayout.add(new JsonFunction(field.getF_key()));
				  }

		   }
	   }
  }
%>
	//var buttons = [refreshAction, '->'];
	var buttons = ['->'];
<%
	for(int i=0;i<buttonauths.size();i++){
		ObjectAuthority auth = buttonauths.get(i);
		if(auth.hasAuth(ObjectAuthority.A_EDIT) || auth.hasAuth(ObjectAuthority.A_READ)){
			Action button = (Action)auth.getObject();;
%>
	var <%=button.getF_name()%> = new Ext.Action({
		text : '<%=button.getF_caption()%>',
		iconCls : '<%=button.getIcon()%>',
		handler : (<%=button.getRunHandler()%>)
	});
	buttons.push(<%=button.getF_name()%>);
<%
		}
	}
	if (formValidator != null && !"".equals(formValidator)) {
%>
	var formValidator = <%=formValidator%>;
<%
	} else {
%>
	var formValidator = function(){return true;};
<%		
	}
%>

<%=workflowFieldConfig==null? "var workflowFieldConfig=function(){};" :"var workflowFieldConfig=function(){"+workflowFieldConfig+"};" %>
<%=workflowValidator==null? "var bpmformValidator=function(){return true};" :workflowValidator %>
workflowFieldConfig();
	
	var form = new Ext.form.FormPanel({
	    autoScroll : true,
		layout:'form',
		border : false,
		tbar : buttons,
		<%=(workflowTopHtml==null)?"": "applyTo : 'TopFormTable',"%>
		fileUpload : true,
		trackResetOnLoad : true,
<%
        if(panel.getF_config() != null && panel.getF_config().has("labelWidth")){
            out.print("labelWidth:"+panel.getF_config().get("labelWidth").getAsInt()+",");
        }
%>
<%
        if(panel.getF_config() != null && panel.getF_config().has("labelAlign")){
            out.print("labelAlign:'"+panel.getF_config().get("labelAlign").getAsString()+"',");
        }
%>
<%
        if(panel.getF_config() != null && panel.getF_config().has("labelPad")){
            out.print("labelPad:"+panel.getF_config().get("labelPad").getAsInt()+",");
        }
%>
		bodyStyle : "padding:10px;padding-left:10px;padding-right:10px;overflow-x:hidden",		
		paramOrder : ['documentkey', 'panelkey', 'documentid', 'params'],
		baseParams : {
			documentkey : '<%=panel.getParent().getKey()%>',
			panelkey : '<%=panel.getKey()%>',
			documentid : <%=documentid%>,
			params : panel.document.params
		},
		defaults : {
			anchor : '-20'
		},
		api : {
			load : <%=loadDirect%>,
			submit : <%=submitDirect%>
		},
		items : <%=formLayout.toString()%>,
		plugins : new Mixky.plugins.FormFieldFocus(),
		keys : [{
			key: [Ext.EventObject.ENTER], 
			fn: function(a, e) {
				if(e.target.tagName == 'DIV' || e.target.tagName == 'INPUT' || (e.target.tagName == 'TEXTAREA' && e.ctrlKey)){
					var fieldName = e.target.name;
					if(e.target.type == 'radio' || e.target.type == 'checkbox'){
						fieldName = fieldName.substr(0, fieldName.length - 1);
					}else if(!fieldName && e.target.previousSibling != null){
						fieldName = e.target.previousSibling.name;
					}
					var field;
					if(e.target.tagName == 'DIV'){
						// 判断是否只读录入框
						if(e.target.childNodes.length > 1){
							var id = e.target.childNodes[1].id;
							if(id.indexOf('x-form-el-') >=0){
								field = Ext.getCmp(id.substr(10));
							}
						}
					}else if(fieldName){
						field = form.getForm().findField(fieldName);
					}
					if(field){
						var index = -1;
						for(var i=0;i<form.getForm().items.getCount();i++){
							if(field == form.getForm().items.get(i)){
								index = i;
								break;
							}
						}
						if(index >= 0){
							var nextField;
							for(var i=index+1;i<form.getForm().items.getCount();i++){
								var nField = form.getForm().items.get(i);
								var xtype = nField.getXType();
								if(xtype != 'mixkydisplayfield' &&nField.isVisible()){
									    nextField = nField;
									    break;
								}
							}
							if(nextField){
								nextField.focus();
							}else{
								var btns = panel.document.getBottomToolbar().items;
								btns.last().focus.defer(100, btns.last());
							}
						}
					}
				}
			},
			scope : form
		}]
	});

	panel.submit = function(fnSubmit){
		if (!form.getForm().isValid()){
			MixkyApp.showErrorMessage("表单数据填写非法，保存失败");
			return;
		}
		if (Ext.isDefined(formValidator)) {
			// 自定义表单校验函数校验失败
			if (formValidator() === false) {
				return;
			}
		}
		if(form.getForm().isDirty()){
			form.getForm().submit({
				success : function(f,a){
					form.getForm().load();
					panel.document.submitPanelOver(panel, fnSubmit);
				},
				failure : function(f, a){
					MixkyApp.showFormActionFail(f, a);
				}
			});
		}else{
			panel.document.submitPanelOver(panel, fnSubmit);
		}
	}
	// 获得需要提交的数据
	panel.getSubmitData = function(){
		if (!form.getForm().isValid()){
			MixkyApp.showErrorMessage("表单数据填写非法");
			return false;
		}else{
			if(Ext.isDefined(formValidator)){
				if (formValidator() === false) {
					return false;
				}
			}
			return form.getForm().getFieldValues();
		}
	}
	
	panel.getFieldValue = function(name){
		var field = form.getForm().findField(name);
		if(field){
			return field.getValue();
		}
	}
	
	panel.refresh = function(){
		form.getForm().load();
	}
	
	<%
		if(workflowLayout!=null){
			out.print("form.add("+workflowLayout+");");			
		}		
	%>	
	
	panel.addProcessFieldset=function(){
		
	};
	

	// 输出附加脚本 begin
<%
	if(panel.getF_custom_script() != null){
		out.print(panel.getF_custom_script());
	}
%>

	panel.form=form;
	panel.documentid=documentid;
	panel.formValidator=formValidator;
	panel.bpmformValidator=bpmformValidator;
		

	// 输出附加脚本 end
	panel.add(form);
	panel.doLayout();
	form.doLayout();
	// 初始化视图数据
	panel.refresh();
	
	
	if(panel.loadedScript){
		panel.loadedScript();
	}
	
	<%
	   if(processitem!=null && processitem.getWFSUCESSFLAG()!=null && 
	         !processitem.getWFSUCESSFLAG().trim().equals("1"))
	   {		   
	%>
		Ext.Msg.show({
			title : '系统警告',
			msg : '<%="对不起，系统发生错误："+processitem.getWFERRORSTR() %>',
			width : 320,
			buttons : Ext.MessageBox.OK,
			icon : Ext.MessageBox.INFO,
			fn:function(){
			    win.close();
			}
		});

	<%
	   }else if(processitem!=null && processitem.getWFLOCKFLAG()!=null && 
		         !processitem.getWFLOCKFLAG().trim().equals("NoLock"))
		   {
	%>	
		Ext.MessageBox.confirm('系统警告', '此流程正在被其他人员编辑，是否继续编辑?', function(btn){
	    	if(btn == 'yes'){
	    		
	    	}else{
	    		win.close();
	   	    }
		}); 
	<%
	   }
	%>	
	panel.setAutoScroll(false);
});
</script>
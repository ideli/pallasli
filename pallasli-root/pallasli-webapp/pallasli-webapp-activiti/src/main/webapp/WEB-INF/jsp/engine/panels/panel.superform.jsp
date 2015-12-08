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
<%@ page import="java.util.Iterator"%>
<%@ page import="com.mixky.toolkit.JsonObjectTool"%>
<%@ page import="com.wasoft.process.PROCESSITEM"%>

<%!

	private void setFiledSets(List<ObjectAuthority> panelauths
			,List<String> parentNameList,List<String> childNamesList
			,List<JsonObject> fieldList){
			for(int i=0;i<panelauths.size();i++){
				ObjectAuthority auth=panelauths.get(i);
				if(auth.hasAuth(ObjectAuthority.A_READ) || auth.hasAuth(ObjectAuthority.A_EDIT)){
					Panel p = (Panel)auth.getObject();
					if(p.getF_config()!=null){
						JsonObject fieldset = new JsonObject();
						fieldset.addProperty("xtype", "fieldset");
						if(p.getF_config().has("mergeTo")){
							String parentName= p.getF_config().get("mergeTo").getAsString();
							fieldset.addProperty("parent", parentName);
							parentNameList.add(parentName);
						}else{
							parentNameList.add("");
						}
						if(p.getF_config().has("childs")){
							String childNames= p.getF_config().get("childs").getAsString();
							fieldset.addProperty("layout", "column");
							childNamesList.add(childNames);
						}else{
							childNamesList.add("");
						}
						JsonArray items=null;
						if(p.getF_config().has("nullFieldSet")&&p.getF_config().get("nullFieldSet").getAsBoolean()){
							items=new JsonArray();
						}else{
							if(	p.getF_i_tableform()!=null){
								if(p.getF_config().has("childs")){
									TableForm tb=(TableForm)DesignObjectLoader.instance().loadDesignObject(p.getF_i_tableform().get("data").getAsString());
									JsonArray items_tmp=TableFormDataService.instance().getFormColumnLayoutFree(tb);

									System.out.println(items_tmp);
										JsonObject fieldset_tmp = new JsonObject();
										fieldset_tmp.addProperty("xtype", "fieldset");
										fieldset_tmp.addProperty("columnWidth", 1);
										fieldset_tmp.addProperty("border", false);
										fieldset_tmp.addProperty("style", "padding:0px;margin:0px;");
										fieldset_tmp.add("items",items_tmp);
										items=new JsonArray();
										items.add(fieldset_tmp);
								}else{
									TableForm tb=(TableForm)DesignObjectLoader.instance().loadDesignObject(p.getF_i_tableform().get("data").getAsString());
									items=TableFormDataService.instance().getFormColumnLayoutFree(tb);

								}
							}
						}
						if( p.getF_config().has("columnWidth")){
							double columnwidth=p.getF_config().get("columnWidth").getAsDouble();
							fieldset.addProperty("columnWidth", columnwidth);
						}else{
							fieldset.addProperty("columnWidth", 1);
						}
						if( p.getF_config().has("itemLength")){
							int itemLength=p.getF_config().get("itemLength").getAsInt();
							fieldset.addProperty("itemLength", itemLength);
						}

						if(p.getF_config().has("needCfg")&&p.getF_config().get("needCfg").getAsBoolean()){
							if(p.getF_i_tableform()!=null){
								TableForm tb=(TableForm)DesignObjectLoader.instance().loadDesignObject(p.getF_i_tableform().get("data").getAsString());
	
								fieldset.addProperty("checkboxToggle", "true");
								fieldset.addProperty("title", tb.getF_caption());
								fieldset.addProperty("style", "padding:10px;margin:5px;");
								fieldset.addProperty("needCfg",true);
								fieldset.addProperty("name", tb.getF_name());
								fieldset.addProperty("pname", p.getF_name());
							}else{

								fieldset.addProperty("checkboxToggle", "true");
								fieldset.addProperty("title", p.getF_caption());
								fieldset.addProperty("style", "padding:10px;margin:5px;");
								fieldset.addProperty("needCfg",true);
								fieldset.addProperty("name", p.getF_name());
								fieldset.addProperty("pname", p.getF_name());
								
							}
						}else{
							fieldset.addProperty("border", false);
							fieldset.addProperty("style", "padding:0px;margin:0px;");
							fieldset.addProperty("needCfg",false);
							fieldset.addProperty("name", p.getF_name());
							fieldset.addProperty("pname", p.getF_name());
						}
						fieldset.add("items",items);
						fieldList.add(fieldset);
					}else{
						parentNameList.add("");
						childNamesList.add("");
						JsonObject fieldset = new JsonObject();
						fieldset.addProperty("xtype", "fieldset");
						fieldset.addProperty("style", "padding:0px;padding-bottom:100px;margin:0px;margin-bottom:110px;");
						fieldset.addProperty("needCfg", false);
						fieldset.addProperty("name", p.getF_name());
						fieldset.addProperty("pname", p.getF_name());
						JsonArray items=TableFormDataService.instance().getFormColumnLayoutFree((TableForm)DesignObjectLoader.instance().loadDesignObject(p.getF_i_tableform().get("data").getAsString()));
						fieldset.add("items",items);
						fieldList.add(fieldset);
					}
				}
			}
	}

	private void addChild(JsonObject fieldSet,String childNames
			,List<String> parentNameList,List<String> childNamesList
			,List<JsonObject> fieldList){
		String[] nameArr=childNames.split(",");
		int len=parentNameList.size();
		for(int i=0;i<len;i++){
			JsonObject p=fieldList.get(i);
			for(String name:nameArr){
				if(name.equals(p.get("pname").getAsString())){
					if(!childNamesList.get(i).equals("")){
						addChild(p,childNamesList.get(i), parentNameList, childNamesList
								,fieldList);
					}
					//if(!p.get("needCfg").getAsBoolean()){
						fieldSet.get("items").getAsJsonArray().add(p);
					//}
				}
			}
		}
	}
	private void getFiledSetsLayout(List<ObjectAuthority> panelauths
			,List<String> parentNameList,List<String> childNamesList
			,List<JsonObject> fieldList){
		setFiledSets(panelauths, parentNameList, childNamesList
				, fieldList);
		int len=parentNameList.size();
		for(int i=0;i<len;i++){
			if(parentNameList.get(i).equals("")){
				JsonObject fieldset=fieldList.get(i);
				JsonArray items = new JsonArray();
				fieldset.addProperty("xtype", "fieldset");
				fieldset.addProperty("name",fieldset.get("name").getAsString());
				fieldset.add("items",items);
				if(!childNamesList.get(i).equals("")){
					addChild(fieldset,childNamesList.get(i), parentNameList, childNamesList
							,fieldList);
				}
			}
		}
	}
%>
<%
	// 读取参数
	
	String panelid = request.getParameter("panelid");
	String documentid = request.getParameter("documentid");
	
	User user = (User)request.getAttribute("user");
	
	String unid = request.getParameter("unid");
	String docid = request.getParameter("docid");
	String type = request.getParameter("type");	
	
	String workflowTopHtml=(String)request.getAttribute("workflowTopHtml");
	String viewWorkflowInfoJS=(String)request.getAttribute("viewWorkflowInfoJS");
	String workflowValidator=(String)request.getAttribute("workflowValidator");

	String appkey = request.getParameter("appkey");
	Panel panel = (Panel)request.getAttribute("panel");
	
	// 获得按钮权限
	List<ObjectAuthority> buttonauths = (List<ObjectAuthority>)request.getAttribute("buttonauths");
	List<ObjectAuthority> panelauths = (List<ObjectAuthority>)request.getAttribute("panelauths");
	// 获得字段权限	
	List<ObjectAuthority> fieldauths = (List<ObjectAuthority>)request.getAttribute("fieldauths");
	List<ObjectAuthority> flowauths = (List<ObjectAuthority>)request.getAttribute("flowauths");
	
	Map<String, ObjectAuthority> map=(Map<String, ObjectAuthority>)request.getAttribute("authmap");
	JsonObject workflowLayout=(JsonObject)request.getAttribute("workfolwLayout");
	
	JsonArray formLayout=(JsonArray)request.getAttribute("formLayout");		
	JsonArray businessLayout=(JsonArray)request.getAttribute("businessLayout");
	JsonArray hideLayout=new JsonArray();
	
	TableForm tableform=(TableForm)request.getAttribute("tableform");
	
	Document document = (Document)request.getAttribute("document");
	JsonObjectDao data = (JsonObjectDao)request.getAttribute("data");
	
	PROCESSITEM processitem=(PROCESSITEM)request.getAttribute("processitem"); 
	
	for(int i=1;i<panelauths.size();i++){
		
		if(panelauths.get(i).hasAuth(ObjectAuthority.A_READ) || panelauths.get(i).hasAuth(ObjectAuthority.A_EDIT)){		
			Panel p = (Panel)panelauths.get(i).getObject();
			if(p.getF_i_tableform() != null && p.getF_i_tableform().has("data")){
					TableForm tb=(TableForm)DesignObjectLoader.instance().loadDesignObject(p.getF_i_tableform().get("data").getAsString());
					if(map == null){
						fieldauths=ListTool.linkList(fieldauths, TableFormDataService.instance().getFormFieldAuths(tb), false);
					}else{
						List<Field> fields = TableFormDataService.instance().getFormFields(tb);
						fieldauths=ListTool.linkList(fieldauths,  DocumentManager.instance().getFilterObjectAuthority(map, fields, user), false);
					}
			}	
			
			if(processitem!=null){
			fieldauths = TableFormDataService.instance().getBpmFieldAuth(p.getF_key(),processitem, fieldauths, type);	
			}	
        }	
     }
	
	 if(flowauths!=null){	
		ListTool.linkList(fieldauths, flowauths, false);
	 }
	

	  List<String> parentNameList=new ArrayList<String>();
	  List<String> childNamesList=new ArrayList<String>();
	  List<JsonObject> fieldList=new ArrayList<JsonObject>();
	getFiledSetsLayout(panelauths, parentNameList, childNamesList
			,fieldList);
		
	//businessLayout.add(formsField);
	
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
<%@page import="java.util.Iterator"%>
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
	
	panel.getFieldValue = function(name){
		var field = form.getForm().findField(name);
		if(field){
			return field.getValue();
		}
	};
	
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
		  boolean singleSelect=view.getF_single_select();
       	  view.setF_single_select(true);
%>
	               var <%=field.getF_name()+"columns"%> = <%=ViewDataService.instance().getViewColumns(columns)%>;
	               var len=<%=field.getF_name()+"columns"%>.length;
	               if(<%=!singleSelect%>) for(var ci=len;ci>1;ci-- ){
	               		<%=field.getF_name()+"columns"%>[ci]=<%=field.getF_name()+"columns"%>[ci-1];
	    	            if(ci==2){<%=field.getF_name()+"columns"%>[1]=<%=field.getF_name()+"sm"%>;
	    	            }
	               }
          var GRIDTYPE=Ext.grid.GridPanel;
<% 
	  		view.setF_single_select(singleSelect);
         }
         else if(auth.hasAuth(ObjectAuthority.A_EDIT)){
   		     if(viewform!=null){
%>
                  var <%=field.getF_name()+"columns"%> = <%=ViewDataService.instance().getViewColumnsByTableForm(view.getF_columns(), viewform)%>;
                  var GRIDTYPE=Ext.grid.EditorGridPanel;
<%
              }else{
        		  boolean singleSelect=view.getF_single_select();
               	  view.setF_single_select(true);
        %>
        	               var <%=field.getF_name()+"columns"%> = <%=ViewDataService.instance().getViewColumns(columns)%>;
        	               var len=<%=field.getF_name()+"columns"%>.length;
        	               if(<%=!singleSelect%>)for(var ci=len;ci>1;ci-- ){
        	               		<%=field.getF_name()+"columns"%>[ci]=<%=field.getF_name()+"columns"%>[ci-1];
        	    	            if(ci==2){<%=field.getF_name()+"columns"%>[1]=<%=field.getF_name()+"sm"%>;
        	    	            }
        	               }
                  var GRIDTYPE=Ext.grid.GridPanel;
                  
        <% 
        	  view.setF_single_select(singleSelect);
        }
%>
<%          }
%>
//视图操作
var buttons = [ '->'];
<%
List<Action> buttons = AuthorityDataService.instance().getDesignObjectsByUser(view.getF_buttons(), user);

String defaultAction = "";
for(int j=0;j<buttons.size();j++){
	Action button = buttons.get(j);
		// 输出按钮
%>
var <%=button.getF_key()%> = new Ext.Action({
	text : '<%=button.getF_caption()%>',
	iconCls : '<%=button.getIcon()%>',
	isDefault : <%=button.getF_default()%>,
	hideOnLeaf: <%=button.getF_config()!=null&&button.getF_config().has("hideOnLeaf")&&
		button.getF_config().get("hideOnLeaf").getAsBoolean()%>,
	hideOnBranch: <%=button.getF_config()!=null&&button.getF_config().has("hideOnBranch")&&
				button.getF_config().get("hideOnBranch").getAsBoolean()%>,
<%
		if(button.getF_handler() == null || "".equals(button.getF_handler())){
%>
	handler : Ext.emptyFn
<%
		}else{
%>
	handler : <%=button.getF_handler()%>
<%
		}
				%>
});
<%			if(button.getF_config()==null||!button.getF_config().has("hideInToolbar")||
						!button.getF_config().get("hideInToolbar").getAsBoolean()){
%>
buttons.push(<%=button.getF_key()%>);
		
<%
		}
	// 双击默认操作
	if(button.getF_default()){
		defaultAction = button.getF_key() + ".execute()";
	}


}
%>
if(<%=view.getF_url()!=null&&view.getF_url().endsWith("treeGrid")%>){
	GRIDTYPE=Ext.ux.tree.TreeGrid;
<%
	JsonObject vcfg = view.getF_config();
	// 支持自定义数据
	String vdirectFn = "eval(app.keyPrefix + 'AppDirect.getTreeViewResults')";
	if(vcfg != null && vcfg.has("directFn")){
		vdirectFn = vcfg.get("directFn").getAsString();
	}
%>
var contextmenus = <%=ViewDataService.instance().getViewContextMenuNames(buttons)%>;

<%	if(view.getF_single_select()){
	%>
	<%=field.getF_name()+"columns"%>.splice(0,1);
	<%
		}else{
	%>
	<%=field.getF_name()+"columns"%>.splice(0,2);
	<%
		}
	%>
var <%=field.getF_name()+"columns2"%>=[];
	for(var ci=0,len=<%=field.getF_name()+"columns"%>.length;ci<len;ci++){
		<%=field.getF_name()+"columns2"%>[ci]={};
		<%=field.getF_name()+"columns2"%>[ci].id=<%=field.getF_name()+"columns"%>[ci].id;
		<%=field.getF_name()+"columns2"%>[ci].dataIndex=<%=field.getF_name()+"columns"%>[ci].dataIndex;
		<%=field.getF_name()+"columns2"%>[ci].header=<%=field.getF_name()+"columns"%>[ci].header;
		<%=field.getF_name()+"columns2"%>[ci].width=<%=field.getF_name()+"columns"%>[ci].width;
	}
    var <%=field.getF_name()%> = new GRIDTYPE({
	border : false,
	stripeRows: true,
	lineBreak : false,
	cellSelect : true,
	autoScroll:false,
	enableHdMenu:false,
	enableColumnMove:false,
	autoLoad:false,
	enableSort:false,
    loadMask: {msg:'正在装载...'},
    <%
      if(view.getF_autoexpandcolumn()!=null&&!view.getF_autoexpandcolumn().trim().equals("")){
          out.print("autoExpandColumn :'"+view.getF_autoexpandcolumn()+"',");
      }
    %>
	columns : <%=field.getF_name()+"columns2"%>,
	tbar : buttons,
	cmenu : new Ext.menu.Menu({items:contextmenus}),
	findIndex:function(colName){
        var cols = <%=field.getF_name()%> .columns;
        for(var ci = 0, len = cols.length; ci < len; ci++){
        	if(cols[ci].id==colName){return ci;}
        }
        return -1;
    },
    getLeafNodes:function(node,childLeafNodes){
        if(!node){node=<%=field.getF_name()%> .getRootNode();}
        if(!childLeafNodes){childLeafNodes=[];}
		if(node.isLeaf()){
			childLeafNodes[childLeafNodes.length]=node;
		}else{
			if(node.hasChildNodes()){
				var nodes=node.childNodes;
				for(var ci=0,len=nodes.length;ci<len;ci++){
					childLeafNodes.concat(<%=field.getF_name()%> .getLeafNodes(nodes[ci],childLeafNodes));
				}
			}
		}
		return childLeafNodes;
    },
    getLeafRecords:function(){
         var leafNodes=<%=field.getF_name()%> .getLeafNodes();
    	 var records=[];
    	 for(var ni = 0, len = leafNodes.length; ni < len; ni++){
        	records[records.length]=new Ext.data.Record(leafNodes[ni].attributes);
         }
         return records;
    },
	addRecord:function(attr,properties,node){
    	if(!node) node = <%=field.getF_name()%> .getSelectionModel().getSelectedNode();
    	if(!node){
			MixkyApp.showErrorMessage("请选择要增加记录的项");
			return;
        }
		node.expand();
		var n=new Ext.tree.TreeNode();
		n.leaf=true;
		Ext.apply(n,properties);
		Ext.apply(n.attributes,attr);
		node.appendChild(n); 
	},
	updateRecord:function(attr,node){
		if(!node) node=<%=field.getF_name()%> .getSelectionModel().getSelectedNode();
    	if(!node){
			MixkyApp.showErrorMessage("请选择要修改的记录");
			return;
        }
        for(var key in attr){
        	var index=<%=field.getF_name()%> .findIndex(key);
        	if(index==0){
        		node.getUI().textNode.innerHTML=attr[key];
            }else if(index>0){
                var colNode = node.getUI().wrap.childNodes[0].childNodes[index].firstChild;
        		colNode.innerHTML=attr[key];
            }
		}
		Ext.apply(node.attributes,attr);
	},
	removeRecord:function(node){
		if(!node)node=<%=field.getF_name()%> .getSelectionModel().getSelectedNode();
    	if(!node){
			MixkyApp.showErrorMessage("请选择要删除的记录");
			return;
        }
		node.remove(); 
	},
	listeners : {
		'contextmenu' : function(node, e){
   	 		node.select();
   	 	if(node.leaf){
	         var c = node.getOwnerTree().cmenu;
            c.contextNode = node;       

			 for(var ti=0,len=<%=field.getF_name()%>.cmenu.items.length;ti<len;ti++){
				if(<%=field.getF_name()%>.cmenu.items.get(ti).hideOnLeaf) {
					<%=field.getF_name()%>.cmenu.items.get(ti).hide();  
				}else{
					<%=field.getF_name()%>.cmenu.items.get(ti).show(); 
				}
			 }       
            c.showAt(e.getXY());           
	    }else{
	         var c = node.getOwnerTree().cmenu;
            c.contextNode = node;       

			 for(var ti=0,len=<%=field.getF_name()%>.cmenu.items.length;ti<len;ti++){
				if(<%=field.getF_name()%>.cmenu.items.get(ti).hideOnBranch) {
					<%=field.getF_name()%>.cmenu.items.get(ti).hide();  
				}else{
					<%=field.getF_name()%>.cmenu.items.get(ti).show(); 
				}
			 }       
            c.showAt(e.getXY()); 

		}
		},
		'click':function(node, e){
        	node.select();
			if(!node.leaf ){
			}else{
			}
		},
		'dblclick':function(node, e){
        	 node.select();
			if(node.leaf){
				for(var ti=0,len=<%=field.getF_name()%>.cmenu.items.length;ti<len;ti++){
					if(!<%=field.getF_name()%>.cmenu.items.get(ti).hideOnLeaf) {
						<%=field.getF_name()%>.cmenu.items.get(ti).fireEvent('click');
					}
				 } 
				return;
			}
		}
    },
	loader: new Ext.ux.tree.TreeGridLoader({
    	preloadChildren : false,
		directFn : <%=vdirectFn%>,
		paramOrder : ['viewkey','querytype','limit','start','sort','dir','params'],
		baseParams : {viewkey:'<%=view.getKey()%>', querytype:<%=ViewDataService.QT_NORMAL%>,limit:<%=view.getF_page_size()%>, start:0, sort:'',dir:'',params:{}},
		listeners : {
			'beforeload':
				<%if(view.getF_config()!=null&&view.getF_config().has("beforeload")){
					
					out.print(new JsonFunction(view.getF_config().get("beforeload").getAsString()));
					}else{
						out.print("function(loader, node){}");
						
						
					}
				%>,
    		'load':
				<%if(view.getF_config()!=null&&view.getF_config().has("load")){
					
					out.print(new JsonFunction(view.getF_config().get("load").getAsString()));
					
					}else{
						out.print("function(loader, node,results){}");
						
						
					}
				%>
	   	
	   	}
	})
	});

	
}else{
         var <%=field.getF_name()%> = new GRIDTYPE({
					region:'center',
					border : false,
					enableHdMenu : false,
					autoHeight:true,
					cellSelect:true,
					tbar:buttons,
					style:"padding:0px;margin:0px;",
					viewConfig:{
						getRowClass: function(record, index) {
							if(record.dirty){
								return 'mixky-grid-row-changed';
							}
					    },
					    layout : function() {  
					        if (!this.mainBody) {  
					            return; // not rendered  
					        }  
					        var g = this.grid;  
					        var c = g.getGridEl();  
					        var csize = c.getSize(true);  
					        var vw = csize.width;
					        console.log(csize);  
					        if (!g.hideHeaders && (vw < 20 || csize.height < 20)) { // display:  
					            // none?  
					            return;  
					        }  
					        if (g.autoHeight) {  
					            this.el.dom.style.width = "100%";  
					            this.el.dom.style.overflow = "auto";  
					            this.el.dom.firstChild.style.overflow = "visible";  
					            this.el.dom.firstChild.style.cssFloat = "left";  
					            this.el.dom.firstChild.firstChild.style.cssFloat = "left";  
					            this.el.dom.firstChild.firstChild.nextSibling.style.cssFloat = "left";  
					            this.el.dom.firstChild.firstChild.firstChild.style.overflow = "visible";  
					            this.el.dom.firstChild.firstChild.nextSibling.style.overflow = "visible";  
					            console.log(this.el.dom);
					        } else {  
					            this.el.setSize(csize.width, csize.height);  
					            var hdHeight = this.mainHd.getHeight();  
					            var vh = csize.height - (hdHeight);  
					            this.scroller.setSize(vw, vh);  
					            if (this.innerHd) {  
					                this.innerHd.style.width = (vw) + 'px';  
					            }  
					        }  
					        if (this.forceFit) {  
					            if (this.lastViewWidth != vw) {  
					                this.fitColumns(false, false);  
					                this.lastViewWidth = vw;  
					            }  
					        } else {  
					            this.autoExpand();  
					            this.syncHeaderScroll();  
					        }  
					        this.onLayout(vw, vh);  
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
						},
						'render' : function(){
							var gwidth=0;
							<%=field.getF_name()%>.findParentByType('panel').setAutoScroll(true);
							
							<%=field.getF_name()%>.findParentByType('panel').getEl().repaint();
							
						},
						'columnresize':function(columnIndex, newSize){
							<%=field.getF_name()+"columns"%>[columnIndex].width=newSize;
							<%=field.getF_name()%>.findParentByType('panel').setAutoScroll(true);
							var gwidth=0;
							for(var ci=0,len=<%=field.getF_name()+"columns"%>.length;ci<len;ci++){
								var cwidth=<%=field.getF_name()+"columns"%>[ci].width;
								gwidth+=(cwidth?cwidth:80);
							}
							var fWidth=<%=field.getF_name()%>.findParentByType('fieldset').getWidth()-35;
							
							<%=field.getF_name()%>.setWidth(gwidth<fWidth?fWidth:gwidth);
							<%=field.getF_name()%>.findParentByType('panel').getEl().repaint();
							
						}
					},
					getSelectedRecords : function(){
						return this.getSelectionModel().getSelections();
					},
					viewConfig : {  
						layout : function() {  

				        if (!this.mainBody) {  
				            return; // not rendered  
				        }  
				        var g = this.grid;  

				        var c = g.getGridEl();  
				        var csize = c.getSize(true);  
				        var vw = csize.width;  
				        if (!g.hideHeaders && (vw < 20 || csize.height < 20)) { // display:  
				            // none?  
				            return;  
				        }  
				        if (g.autoHeight) {  
				            this.el.dom.style.width = "100%"; 
				            this.el.dom.style.overflow = "hidden";  
				            this.el.dom.firstChild.style.overflow = "visible";  
				           // this.el.dom.firstChild.style.cssFloat = "left";  
				           // this.el.dom.firstChild.firstChild.style.cssFloat = "left";  
				           // this.el.dom.firstChild.firstChild.nextSibling.style.cssFloat = "left"; 				            
				            this.el.dom.firstChild.firstChild.firstChild.style.overflow = "visible";  
				            this.el.dom.firstChild.firstChild.nextSibling.style.overflow = "visible";
				        } else {  
				            this.el.setSize(csize.width, csize.height);  
				            var hdHeight = this.mainHd.getHeight();  
				            var vh = csize.height - (hdHeight);  
				            this.scroller.setSize(vw, vh);  
				            if (this.innerHd) {  
				                this.innerHd.style.width = (vw) + 'px';  
				            }  
				        }  
				        if (this.forceFit) {  
				            if (this.lastViewWidth != vw) {  
				                this.fitColumns(false, false);  
				                this.lastViewWidth = vw;  
				            }  
				        } else {  
				            this.autoExpand();  
				            this.syncHeaderScroll();  
				        }  
				        this.onLayout(vw, vh);  
				    }  
					}  
			  });      

         <%=field.getF_name()%>.getStore().on("change",function(){
        	 <%=field.getF_name()%>.doLayout();
          });
     //    <%=field.getF_name()%>.getStore().add(new Ext.data.Record());
       //  <%=field.getF_name()%>.getStore().add(new Ext.data.Record());

		}
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
		      for(int k=0;k<hideLayout.size();k++){
		         if(hideLayout.get(k).toString().trim().equals(field.getF_key().trim())){
		            isadd = false;
		            break;
		         }
		      }
		      if(isadd){
			     hideLayout.add(new JsonFunction(field.getF_key()));
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
		fileUpload : true,
		<%=(workflowTopHtml==null)?"": "applyTo : 'TopFormTable',"%>		
		trackResetOnLoad : true,
		bodyStyle : "padding:10px;overflow-x:hidden",
		paramOrder : ['documentkey', 'panelkey', 'documentid', 'params'],
		baseParams : {
			documentkey : '<%=panel.getParent().getKey()%>',
			panelkey : '<%=panel.getKey()%>',
			documentid : documentid,
			params : panel.document.params
		},
		defaults : {
			anchor : '-30'
		},
		api : {
			load : <%=loadDirect%>,
			submit : <%=submitDirect%>
		},
		items : []
	});

	panel.refresh = function(){
		form.getForm().load();
	}
	
	
	panel.submit = function(fnSubmit){
		if(!panel.checkField(form)) return false;
		if (Ext.isDefined(formValidator)) {
			// 自定义表单校验函数校验失败

			// 自定义表单校验函数校验失败
			if (formValidator() === false) {
				callback(false);
				return;
			}
		}
		
		if (bpmformValidator&&Ext.isDefined(bpmformValidator)) {
			// 自定义表单校验函数校验失败
			if (bpmformValidator(form) === false) {
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
			if(!panel.checkField(form)) return false;
		
			if(Ext.isDefined(formValidator)){

				// 自定义表单校验函数校验失败
				if (formValidator() === false) {
					callback(false);
					return;
				}
			}
			return form.getForm().getFieldValues();
	}
/************************* 页面处理************************************/
	var fieldSets=[];
	<%
			JsonObject[] fieldsets=new JsonObject[fieldList.size()];
			fieldsets=fieldList.toArray(fieldsets);
			int len=fieldsets.length;
			for(int i=0;i<len;i++){
				JsonObject fieldSet=fieldsets[i];
				if(fieldSet!=null&&
						fieldSet.has("needCfg")
						&&fieldSet.get("needCfg").getAsBoolean()){
	%>
					fieldSets[fieldSets.length]=<%=fieldSet.toString().replaceAll("33333334","33")%>;
	<%
				}
			}
	%>

	form.add(<%=fieldsets[0].toString()%>);

	panel.getFieldValues=function(comp){
		var nv={};
		if(comp.isXType('fieldset')||comp.isXType('panel')){
			var items=comp.items.items;
			for(var i=0;i<items.length;i++){
				var fieldItem=items[i];
				if(fieldItem.isXType('fieldset')||fieldItem.isXType('panel')){
					Ext.apply(nv,panel.getFieldValues(fieldItem));
				}else if(fieldItem.getValue){
					nv[fieldItem.name]=fieldItem.getValue();
				}
			}
		}else{
			nv[comp.name]=comp.getValue();
		}
		return nv;
		
	};
	panel.setFormRead=function(comp){
		if(comp.isXType('fieldset')||comp.isXType('panel')){
			var items=comp.items.items;
			for(var i=0;i<items.length;i++){
				var fieldItem=items[i];
				if(fieldItem.isXType('fieldset')||fieldItem.isXType('panel')){
					panel.setFormRead(fieldItem);
				}else if(fieldItem.setReadOnly){
					fieldItem.setReadOnly(true);
				}
			}
		}else{
			if(comp.setReadOnly){
				comp.setReadOnly(true);
			}
		}
	};
	panel.getFormValues=function(){
		panel.formValues={};
		var items=form.items.items;
		for(var i=0;i<items.length;i++){
			var fieldItem=items[i];
			var nv={};
			if(fieldItem.isXType('fieldset')){
				nv=panel.getFieldValues(fieldItem);
			}
			panel.formValues[fieldItem.name]=nv;
		}
		return panel.formValues;
	};

	   panel.showMsgAndFocus=function(f,msg){
			Ext.Msg.show({
				title : '提示',
				msg : msg,
				width : 320,
				buttons : Ext.MessageBox.OK,
				icon : Ext.MessageBox.INFO,
				fn:function(){f.focus(true,10)}
			});
		}
	panel.checkField=function(comp){
		form.getForm().isValid();
		if(comp.isXType('fieldset')||comp.isXType('panel')){
			var items=comp.items.items;
			for(var i=0;i<items.length;i++){
				var fieldItem=items[i];
				if(fieldItem.isXType('fieldset')||fieldItem.isXType('panel')){
					if(! panel.checkField(fieldItem))return false;
				}else if(fieldItem.getValue){
					if(!fieldItem.isValid(true)){
						panel.showMsgAndFocus(fieldItem,'<label style="color:red" >'+fieldItem.fieldLabel+"</label><br/>"+fieldItem.el.dom.qtip);
						return false;
					}
				}
			}
		}else{
			if(!comp.isValid(true)){
				panel.showMsgAndFocus(comp,'<label style="color:red" >'+fieldItem.fieldLabel+"</label><br/>"+fieldItem.el.dom.qtip);
				return false;
			}
		}
		return true;
		
	};
	panel.loadStore=function(comp){
		if(comp.isXType&&comp.isXType('fieldset')||comp.isXType('panel')){
			var items=comp.items.items;
			for(var i=0;i<items.length;i++){
				var fieldItem=items[i];
				if(fieldItem.isXType('fieldset')||fieldItem.isXType('panel')){
					panel.loadStore(fieldItem);
				}else if(fieldItem.isXType&&fieldItem.isXType('combo')){
					fieldItem.store.on('load',function(){
						fieldItem.setValue(fieldItem.getValue());
					});
					fieldItem.store.load();
				}
			}
		}else{
			if(comp.isXType&&comp.isXType('combo')){ 
				comp.store.on('load',function(){
					comp.setValue(comp.getValue());
				});
				comp.store.load();
			}
		}
	};
	

	var inStr=function(src,code){			
			if(!src)return false;
			var reg=eval('/'+code+'/');
			return reg.test(src);
		}
	panel.findFieldset=function(comp,fieldsetName){
		if(comp.isXType('fieldset')||comp.isXType('panel')){
			if(comp.name==fieldsetName)return comp;
			var items=comp.items.items;				
			for(var i=0;i<items.length;i++){
				var fieldItem=items[i];
				if(fieldItem.isXType('fieldset')||fieldItem.isXType('panel')){
					var fieldset=panel.findFieldset(fieldItem,fieldsetName);
					if( fieldset){return fieldset;};
				}
			}
		}
		return;
		
	};
	panel.addFormFieldset=function(f){
		panel.findFieldset(form,f.parent).add(f);
		return;
	}

	panel.resetChildFieldSet=function(fieldsetNames){
		var namesArray=fieldsetNames.split(",");
		var len=namesArray.length;
		
		for(var i=0;i<len-1;i++){
			var fieldsettmp1;
			while(!fieldsettmp1&&i<len){
				fieldsettmp1=panel.findFieldset(form,namesArray[i]);
				i++;
			}
			var fieldsettmp2;
			while(!fieldsettmp2&&i<len){
				fieldsettmp2=panel.findFieldset(form,namesArray[i]);
				i++;
			}
			if(fieldsettmp2){
				
				while(fieldsettmp1.items.items[0].items.length<fieldsettmp1.itemLength
						&&fieldsettmp2.items.items[0].items.length>0){
					var len=fieldsettmp1.items.items[0].items.length;
					fieldsettmp1.items.items[0].add(fieldsettmp2.items.items[0].items.items[0]);
					fieldsettmp1.items.items[0].items.items[len].columnWidth=fieldsettmp1.items.items[0].items.items[len-1].columnWidth;
					fieldsettmp2.items.items[0].remove(fieldsettmp2.items.items[0].items.items[0]);					
				}
				form.doLayout();
			}
		}
	}
	panel.destroyFields=function(fieldNames){
		if(!fieldNames){return;}
		var namesArray=fieldNames.split(",");
		for(var i=0,len=namesArray.length;i<len;i++){
		var field=form.getForm().findField(namesArray[i]);
			field&&field.destroy&&field.destroy();
		}	
	}
	panel.setReadFields=function(fieldNames){
		if(!fieldNames){return;}
		if(fieldNames=='ALL_FIELD'){panel.setFormRead(form);return;}
		var namesArray=fieldNames.split(",");
		for(var i=0,len=namesArray.length;i<len;i++){
		var field=form.getForm().findField(namesArray[i]);
			field&&field.setReadOnly&&field.setReadOnly(true);
		}
	}
	panel.findField=function(comp,fieldsetName){
		if(typeof comp == 'string'){
			fieldsetName=comp;
			comp=form;
		}
		if(comp.name==fieldsetName)return comp;
		if(comp.isXType('fieldset')||comp.isXType('panel')){
			try{
			var items=comp.items.items;	
			for(var i=0;i<items.length;i++){
				var fieldItem=items[i];
				var fieldset=panel.findField(fieldItem,fieldsetName);
				if( fieldset){return fieldset;};
			}
			}catch(e){

			}
		}
		return;		
	};


	panel.loadPanel=function(panelTest,params){
		params.panelid=panelTest.id;
		if(!params.appkey)params.appkey=app.key;
		if(params.panelkey&&params.panelkey.trim()!=''){
			panelTest.removeAll();
			panelTest.document.params=params.params?params.params:{};
			params.documentid=params.documentid?params.documentid:0;	
			panelTest.load({
				url : app.url + "/documentpanel.do",
				params : params,
				scripts : true
			});
		}
		if(params.viewkey&&params.viewkey.trim()!=''){
			panelTest.removeAll();
			panelTest.load({
				url : app.url + "/view.do",
				params : params,
				scripts : true
			});
		}
	}
	
	panel.addProcessFieldset=function(){
		<%
		if(workflowLayout!=null){
			out.print("form.add("+workflowLayout+");");			
		}
		if(hideLayout!=null){
			out.print("form.add("+hideLayout+");");
		}
	%>	
	}
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
		
	if(panel.rebuildForm){
		panel.rebuildForm();
	}else{
		// 输出附加脚本 end
		panel.add(form);
		panel.doLayout();
		form.doLayout();
		// 初始化视图数据
		panel.refresh();		
	}
	
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
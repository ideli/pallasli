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
	String directFn = "eval(app.keyPrefix + 'AppDirect.getTreeViewResults')";
	if(cfg != null && cfg.has("directFn")){
		directFn = cfg.get("directFn").getAsString();
	}
	boolean autoLoad = true;
	if(cfg != null && cfg.has("autoLoad")){
		autoLoad = cfg.get("autoLoad").getAsBoolean();
	}
%>
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
	<%	if(view.getF_single_select()){
%>
	columns.splice(0,1);
<%
	}else{
%>
	columns.splice(0,2);
<%
	}
%>
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
				panel.queryParams1 = {<%=ViewDataService.VN_QUICK_QUERY_PARAM_NAME%>: value};
				panel.refresh();
			}
		}
	});
	var advanceForm = new Ext.form.FormPanel({
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
		items : <%=ViewDataService.instance().getViewQueryForm(view, user)%>,
		bbar : [{
			text : '关闭',
			iconCls : 'icon-common-cancel',
			handler : function(){
				var btn = <%=ViewDataService.VN_ADVANCE_QUERY_BUTTON_NAME%>;
				advanceWindow.hide();
			}
		},'->',{
			text : '搜索',
			iconCls : 'icon-common-aquery',
			handler : function(){
				/*
				var params = advanceForm.getForm().getFieldValues();
				var mparams = {};
				for(i in params){
					if(params[i] != ''){
						mparams[i] = params[i];
					}
				}
				panel.queryParams = mparams;*/
			<%
	           if(cfg!=null && cfg.has("queryscript")){
		           out.print(cfg.get("queryscript").getAsString() + '\n');
	            }
	         %>
				advanceWindow.hide();
				var btn = <%=ViewDataService.VN_ADVANCE_QUERY_BUTTON_NAME%>;
				panel.refresh();
			}
		}, {
			text : '重置',
			iconCls : 'icon-common-confirm',
			handler : function(){
				var btn = <%=ViewDataService.VN_ADVANCE_QUERY_BUTTON_NAME%>;
				advanceForm.getForm().reset();
	     <%
	           if(cfg!=null && cfg.has("queryresetscript")){
		           out.print(cfg.get("queryresetscript").getAsString() + '\n');
	            }
	     %>	
			}
		}]
	});
	
	<%
	//添加  advanceWindow 宽和高的两个动态参数
		 String advnceWidth = "430",advnceHeight = "420" ;
		 if(cfg!=null && cfg.has("advnceWidth")){
		     advnceWidth = cfg.get("advnceWidth").getAsString();
	      }
	      
	       if(cfg!=null && cfg.has("advnceHeight")){
		     advnceHeight = cfg.get("advnceHeight").getAsString();
	      }
	%>
	var advanceWindow = new Ext.Window({
		width : <%=new JsonFunction(advnceWidth)%>,
		height : <%=new JsonFunction(advnceHeight)%>,
		maximizable : false,
		minimizable : false,
		resizable : false,
		constrain : true,
		title : '<%=view.getF_caption()%>搜索',
		modal : true,
		closeAction : 'hide',
		layout : 'fit',
		manager : MixkyApp.desktop.getManager(),
		items:[advanceForm]
	});
	var <%=ViewDataService.VN_ADVANCE_QUERY_BUTTON_NAME%> = new Ext.Action({
		text : '搜索',
		iconCls : 'icon-common-aquery',
		handler : function(){
			advanceWindow.show();
		}
	});
<%
	}
%>
	// 刷新按钮
	/*var <%=ViewDataService.VN_REFRESH_BUTTON_NAME%> = new Ext.Action({
		text : '查询',
		iconCls : 'icon-common-refresh',
		handler : function(){
			panel.refresh();
		}
	});*/
	var <%=ViewDataService.VN_REFRESH_BUTTON_NAME%> = new Ext.Action({
		text : '重置',
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
	
	var buttons = <%=ViewDataService.instance().getViewButtonNames(view, actions)%>;
	var contextmenus = <%=ViewDataService.instance().getViewContextMenuNames(actions)%>;

	var columns2=[];
	for(var ci=0,len=columns.length;ci<len;ci++){
		columns2[ci]={};
		columns2[ci].id=columns[ci].id;
		columns2[ci].dataIndex=columns[ci].dataIndex;
		columns2[ci].header=columns[ci].header;
		columns2[ci].width=columns[ci].width?columns[ci].width:80;
	}
	// 表格对象
	var grid = new Ext.ux.tree.TreeGrid({
		border : false,
		stripeRows: true,
		lineBreak : false,
		cellSelect : true,
		autoScroll:true,
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
		columns : columns2,
		tbar : buttons,
		cmenu : new Ext.menu.Menu({items:contextmenus}),
    	findIndex:function(colName){
            var cols = grid.columns;
            for(var ci = 0, len = cols.length; ci < len; ci++){
            	if(cols[ci].id==colName){return ci;}
            }
            return -1;
        },
        getLeafNodes:function(node,childLeafNodes){
            if(!node){node=grid.getRootNode();}
            if(!childLeafNodes){childLeafNodes=[];}
			if(node.isLeaf()){
				childLeafNodes[childLeafNodes.length]=node;
			}else{
				if(node.hasChildNodes()){
					var nodes=node.childNodes;
					for(var ci=0,len=nodes.length;ci<len;ci++){
						childLeafNodes.concat(grid.getLeafNodes(nodes[ci],childLeafNodes));
					}
				}
			}
			return childLeafNodes;
        },
        getLeafRecords:function(){
             var leafNodes=grid.getLeafNodes();
        	 var records=[];
        	 for(var ni = 0, len = leafNodes.length; ni < len; ni++){
            	records[records.length]=new Ext.data.Record(leafNodes[ni].attributes);
             }
             return records;
        },
        addRecord:function(attr,properties,node){
        	if(!node) node =grid.getSelectionModel().getSelectedNode();
        	if(!node){
    			MixkyApp.showErrorMessage("请选择要增加记录的项");
    			return;
            }
    		var n=new Ext.tree.TreeNode();
    		n.leaf=true;
    		Ext.apply(n,properties);
    		Ext.apply(n.attributes,attr);
    		node.appendChild(n); 
    		node.expand();
    	},
    	updateRecord:function(attr,node){
    		if(!node) node=grid.getSelectionModel().getSelectedNode();
        	if(!node){
    			MixkyApp.showErrorMessage("请选择要修改的记录");
    			return;
            }
            var cols = t.columns;
            for(var key in attr){
            	var index=grid.findIndex(key);
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
    		if(!node)node=grid.getSelectionModel().getSelectedNode();
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

					 for(var ti=0,len=grid.cmenu.items.length;ti<len;ti++){

						if(grid.cmenu.items.get(ti).hideOnLeaf) {
							grid.cmenu.items.get(ti).hide();  
						}else{
							grid.cmenu.items.get(ti).show(); 
						}
					 }       
	                 c.showAt(e.getXY());           
			    }else{
			         var c = node.getOwnerTree().cmenu;
	                 c.contextNode = node;       

					 for(var ti=0,len=grid.cmenu.items.length;ti<len;ti++){
						if(grid.cmenu.items.get(ti).hideOnBranch) {
							grid.cmenu.items.get(ti).hide();  
						}else{
							grid.cmenu.items.get(ti).show(); 
						}
					 }       
	                 c.showAt(e.getXY()); 

				}
			},
			'click':function(node, e){
	        	node.select();
				if(!node.leaf ){
					/**var n=new Ext.tree.TreeNode();
					Ext.copyTo(n,node.parentNode,'attributes');
					grid.getLoader().load(n); 
					grid.addRecord({mc:'dd',xx:'dsds'},{leaf:true});**/
				}else{
					//grid.updateRecord({mc:'d22d',xx:'dsddddddds'});
				}
			},
			'dblclick':function(node, e){
	        	 node.select();
				if(node.leaf){
					for(var ti=0,len=grid.cmenu.items.length;ti<len;ti++){
						if(!grid.cmenu.items.get(ti).hideOnLeaf) {
							grid.cmenu.items.get(ti).fireEvent('click');
						}
					 } 
				}
			}
        },
		loader: new Ext.ux.tree.TreeGridLoader({
        	preloadChildren : false,
        	autoLoad:<%=autoLoad%>,
			directFn : <%=directFn%>,
			paramOrder : ['viewkey','querytype','limit','start','sort','dir','params'],
			baseParams : {viewkey:'<%=view.getKey()%>', querytype:<%=ViewDataService.QT_NORMAL%>,limit:<%=view.getF_page_size()%>, start:0, sort:'',dir:'',params:{}},
			listeners : {
				'beforeload':
					<%if(view.getF_config()!=null&&view.getF_config().has("beforeload")){
						
						out.print(new JsonFunction(view.getF_config().get("beforeload").getAsString()));
						}else{
							out.print("function(){}");
							
							
						}
					%>,
	    		'load':
					<%if(view.getF_config()!=null&&view.getF_config().has("load")){
						
						out.print(new JsonFunction(view.getF_config().get("load").getAsString()));
						
						}else{
							out.print("function(){}");
							
							
						}
					%>
    	   	}
    	})
	});
	
	//档案按钮用 szc 2013/1/22
	//////////////////////////////////////////////////////////////////
	openPanel2 = function(appkey,url,panel,params,returnvalue)
	{
		var title = params.title;
		var width = params.width;
		var height = params.height;
		win2 = MixkyApp.desktop.openWindowWithJspUrl
		(
			appkey,
			panel, 
			url,
		    {
				title : title,
				width : width,
				height: height,
				closable : true,
				minimizable : false,
				constrain : true, // panel不能移出浏览器
				collapsible :false,
				modal :true,
				manager : MixkyApp.desktop.getManager(),
				resizable : false,
				bbar : [{text:'关闭',handler:function(){
								win2.close();
					}}]
			},
			{},
			params
		);
	};
	
	////////////////////////////////////////////////////////////////////
	
	
	
	
	// 视图刷新
	panel.refresh = function(params){
		if(Ext.isDefined(params)){
			// 恢复查询方式
			if(!Ext.isDefined(params.querytype)){
				params.querytype = <%=ViewDataService.QT_NORMAL%>;
			}
			panel.params = params;
		}
		<% if(ViewDataService.instance().isViewHasQuery(view)){%>
				var params = advanceForm.getForm().getFieldValues();
				var mparams = {};
				for(i in params){
					if(params[i] != ''){
						mparams[i] = params[i];
					}
				}
				panel.queryParams = mparams;
		<%}%>
		// 初始化参数

		// 处理查询参数
		iscx=false;
<%
	if(view.getF_page_size() > 0 && ViewDataService.instance().isViewHasQuery(view)){
%>
		grid.getBottomToolbar().moveFirst();
<%
	}else{
%>
<%
	}
%>
	}
	
	
	/*鼠标移动到序号列时显示序号的值*/
   grid.on('mouseover',function(e){
		var index= grid.getView().findRowIndex(e.getTarget());  //获得行
		var record;
		if(record!=null){
		    var index1=grid.getView().findCellIndex(e.getTarget());
		    if(index1<1){
			     var rowEl=Ext.get(e.getTarget()); //把target转换为Ext.Element对象
	             var sop = so? so.params : null;
			     rowEl.dom.setAttribute("ext:qtip", ((sop && sop.start && sop.limit)? sop.start : 0)+ index + 1); 
			} 
		} 
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
		       out.print(action.getF_name()+".disable();");
		   }else{
		        out.print(action.getF_name()+".enable();");
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
		        out.print(action.getF_name()+".enable();");
		   }
		} 
	 }
 %>
    }

	panel.exportToExcel = function() {

		
	}

// 增加合计行
<%
	if(view.getF_issummary()){
%>
	
<%
	}
%>
	
	// 输出附加脚本 end
	panel.add(grid);
	panel.doLayout();
});
</script>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="com.mixky.engine.organization.dao.User"%>
<%
	// 读取参数
	String key = request.getParameter("key");
	String appkey = request.getParameter("appkey");
	String panelid = "portlet-" + key;
	// 获取属性
	User user = (User)request.getAttribute("user");
%>
<script language='javascript'>
Ext.onReady(function(){
	var panel = Ext.getCmp('<%=panelid%>');

	// 数据访问
	var store = new Ext.data.DirectStore({
		directFn : DesktopDirect.getBpmTodoMessage,
		remoteSort : false,
		root : 'results',
		totalProperty : 'totals',
		idProperty : 'id',
		fields : [{"name":"id","mapping":"id"},{"name":"WF_DocNumber","mapping":"WF_DocNumber"},
		          {"name":"WF_Subject","mapping":"WF_Subject"},{"name":"WF_FromUserName","mapping":"WF_FromUserName"},
		          {"name":"WF_CurrentStatus","mapping":"WF_CurrentStatus"},{"name":"WF_Author","mapping":"WF_Author"},
		          {"name":"WF_ArrivTime","mapping":"WF_ArrivTime"},{"name":"WF_AddName","mapping":"WF_AddName"},
		          {"name":"WF_ProcessName","mapping":"WF_ProcessName"},{"name":"WF_TotalTime","mapping":"WF_TotalTime"},
		          {"name":"WF_ProcessUNID","mapping":"WF_ProcessUNID"},{"name":"WF_DocUNID","mapping":"WF_DocUNID"},
		          {"name":"WF_Created","mapping":"WF_Created"},{"name":"dockey","mapping":"dockey"},
		          {"name":"appkey","mapping":"appkey"},{"name":"documentid","mapping":"documentid"}
		         ]
	});
	// 选择器
	var sm = new Ext.grid.RowSelectionModel({singleSelect : true});
	// 显示列
	// 显示列
	var columns = [new Ext.grid.RowNumberer(),{
		"id":"WF_ArrivTime",
		"dataIndex":"WF_ArrivTime",
		"header":"时间",
		"sortable":true,
		"width":110
	},{
		"id":"WF_ProcessName",
		"dataIndex":"WF_ProcessName",
		"header":"待办来源",
		"sortable":true,
		"width":110
	},{
		"id":"WF_FromUserName",
		"dataIndex":"WF_FromUserName",
		"header":"发起者",
		"sortable":true,
		"width":100
	},{
		"id":"WF_Subject",
		"dataIndex":"WF_Subject",
		"header":"内容",
		"sortable":true,
		"renderer":function renderLast(value, p, r){
			return '<b>'+value+'</b>';
		 }
	}];
	
	var btnOpen = new Ext.Action({
		text : '打开待办',
		iconCls : 'icon-sys-btnopen',
		isDefault : true,

		handler : function(){
		   var records = grid.getSelectedRecords();
		   if(records.length > 0){
		      var dockey = records[0].get('dockey');
			  if(dockey != ''){
			       var appkey = records[0].get('appkey');
				   var unid = records[0].get('WF_ProcessUNID');
				   var docid = records[0].get('WF_DocUNID');
				   var id = records[0].get('documentid');
			       Mixky.wasoft.openWorkflow(unid,docid,appkey,dockey,id);
			  }
			  else{
			       Ext.MessageBox.show({title:'提示',msg:"请检查此业务定义的表单！",
		           modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.INFO,width:250,closable:false});
			  }
		   }
		   else{
		      Ext.MessageBox.show({title:'提示',msg:"请选择需要办理的业务！",
		      modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.INFO,width:250,closable:false});
		   }
		}
	});
	
	var btntodo = new Ext.Action({
		text : '进入待办管理',
		isDefault : true,

		handler : function(){
			MixkyApp.desktop.closeModule('OA', 'waWorkBench');
			var module = MixkyApp.desktop.openModule('OA', 'waWorkBench');
			if(module){
			   var view = module.openView('waWorkBench.qryMyTodo.vMyTodo');
			}
		}
	});
	
	var sjxs = new Ext.form.DisplayField({
       	value:''
	});
	var tools = [sjxs,'->', btntodo];
	var contextmenus = [btnOpen,'-'];
	// 表格对象
	var grid = new Ext.grid.GridPanel({
		border : false,
		stripeRows: true,
		enableHdMenu : false,
		lineBreak : false,
        loadMask: {msg:'正在装载...'},
		autoExpandColumn : 'WF_Subject',
		sm : sm,
		columns : columns,
		store : store,
		tbar : tools,
		contextMenu : new Ext.menu.Menu({items:contextmenus}),
		viewConfig:{
			getRowClass: function(record, index) {
				   return 'wasoft-grid-cell-inner';
		    }
		},
		listeners : {
		    'rowcontextmenu' : function(g, rowIndex, e){
					g.getSelectionModel().selectRow(rowIndex);
					g.contextMenu.showAt(e.getXY());
			},
			'rowdblclick' : function(g, rowIndex, e){
        		btnOpen.execute();
			}
		},
		getSelectedRecords : function(){
			return this.getSelectionModel().getSelections();
		}
	});
	
	function getSelectedRecords(){
		return grid.getSelectedRecords();
	}
	
	store.on('load', function(t,records,o){
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
           grid.getColumnModel().setColumnWidth(0, 20);
       }
       sjxs.setValue('共'+sonumber+'条待办');
   });
	
	/*鼠标移动到列时显示列的值*/
   grid.on('mouseover',function(e){
		var index= grid.getView().findRowIndex(e.getTarget());  //获得行
		var record=store.getAt(index);
		var cols = grid.getColumnModel();
		if(record!=null){
		    var index1=grid.getView().findCellIndex(e.getTarget());
		    var rowEl=Ext.get(e.getTarget()); //把target转换为Ext.Element对象
		    if(index1>=1){
		         var str = record.get(cols.getColumnId(index1));
	            if(str!=null && str.toString().trim()!=''){
	                rowEl.dom.setAttribute("ext:qtip", str); 
	             } 
			}
		} 
   });
   
	// 视图刷新
	panel.refresh = function(){
			store.reload();
	}
	// 输出附加脚本 end
	panel.add(grid);
	panel.doLayout();
	// 初始化视图数据
	panel.refresh();
});
</script>
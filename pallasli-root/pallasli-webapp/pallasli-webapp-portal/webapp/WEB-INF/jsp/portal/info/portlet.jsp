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

	var refreshInterval = 60;

	// 数据访问
	var store = new Ext.data.DirectStore({
		directFn : DesktopDirect.getTodoList,
		/*proxy : new Ext.data.HttpProxy({
		    method: 'POST',
		    url: 'jsppage.do?url=portal/todo/todo.data',
		    timeout: 1000
		}),*/
		remoteSort : true,
		root : 'results',
		totalProperty : 'totals',
		idProperty : 'ID',
		fields : [
		  	{"name":"ID","mapping":"ID"},
		  	{"name":"F_SOURCE_APPKEY","mapping":"F_SOURCE_APPKEY"},
		  	{"name":"F_SOURCE_DOCKEY","mapping":"F_SOURCE_DOCKEY"},
		  	{"name":"F_SOURCE_DATAID","mapping":"F_SOURCE_DATAID"},
		  	{"name":"F_CREATED","mapping":"F_CREATED"},
		  	{"name":"F_SENDER_NAME","mapping":"F_SENDER_NAME"},
		  	{"name":"F_SOURCE","mapping":"F_SOURCE"},
		  	{"name":"F_TITLE","mapping":"F_TITLE"},
		  	{"name":"F_TYPE","mapping":"F_TYPE"}
		]
	});
	// 选择器
	var sm = new Ext.grid.RowSelectionModel({singleSelect : true});
	// 显示列
	// 显示列
	var columns = [new Ext.grid.RowNumberer(),{
		"id":"F_SOURCE",
		"dataIndex":"F_SOURCE",
		"header":"模块",
		"sortable":false,
		"width":120
	},{
		"id":"F_TITLE",
		"dataIndex":"F_TITLE",
		"header":"消息内容",
		"sortable":false,
		"renderer":function renderLast(value, p, r){
			return String.format('<b>{0}</b> <br/> <I>{1}</I> {2}', value, r.get('F_SENDER_NAME'), r.get('F_CREATED'));
		}
	}];
	var btnOpen = new Ext.Action({
		text : '打开',
		iconCls : 'icon-sys-btnopen',
		isDefault : true,

		handler : function(){
			var records = grid.getSelectedRecords();
			if(records.length > 0){
				var appkey = records[0].get('F_SOURCE_APPKEY');
				var key = records[0].get('F_SOURCE_DOCKEY');
				var param = records[0].get('F_SOURCE_DATAID');
				var type = records[0].get('F_TYPE');
				var id = records[0].get('ID');
				// 类型为READ的待办信息
				if(key==null||key.trim()==''){
				    DesktopDirect.updateMessageState(id,function(result,e){
					    if (result&&result.success) {
					       panel.refresh();
					    }
					});
				    return;
				}
				else{
					if (type == 1) {
						DesktopDirect.updateMessageState(id,function(result,e){
						    if (result&&result.success) {
						       panel.refresh();
						    }
						});
					}
					Mixky.wasoft.lib.openFavorite(appkey, key, param);
				}
			}
		}
	});
	var contextmenus = [btnOpen,'-'];
	// 表格对象
	var grid = new Ext.grid.GridPanel({
		border : false,
		stripeRows: true,
		enableHdMenu : false,
		lineBreak : false,
        loadMask: {msg:'正在装载...'},
		autoExpandColumn : 'F_TITLE',
		sm : sm,
		columns : columns,
		store : store,
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
	             rowEl.dom.setAttribute("ext:qtip", str); 
			}
		} 
   });
	
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
	// 输出附加脚本 end
	panel.add(grid);
	panel.doLayout();
	// 初始化视图数据
	panel.refresh();
	
	// 遍历服务器列表，保持与应用服务器的连接
	function touchAppServer() {
		// 遍历所有应用系统,请求固定地址
		for (n in Mixky.wasoft.cache.Applications) {
			if (n == 'designer') {
				continue;
			}
			Ext.Ajax.request({
		        url: Mixky.wasoft.cache.Applications[n].url + "/namespace.do",
		        disableCaching: true,
		        timeout: 10000,
		        success: function(response, option) {
		            if (!response || response.responseText == '') {
		                // 请求失败
		                return;
		            } else {
		                // 请求成功
		            }
		        },
		        failure: function(data) {
		            // 请求失败
		        }
		    });
		}
	    return true;
	}

	var taskCheckLoginState = {
	        run: touchAppServer,
	        interval: 120000 //任务间隔，毫秒为单位，这里是120秒
	    };
	Ext.TaskMgr.start(taskCheckLoginState);
});
</script>
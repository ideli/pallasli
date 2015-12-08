<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="com.mixky.app.ApplicationParameters"%>
<%
	String id = request.getParameter("id");
%>
<script language='javascript'>
Ext.onReady(function(){
	var id = '<%=id%>';
	var chg=0;
	// 获得对象的属性列表
	var module = Mixky.awsoft.lib.Class.getModule("userbgxx");
	
	var panel = Ext.getCmp(id);
	panel.setTitle('用户变更日志');

	// 合并属性
	var properties = {};
	for(var i=0;i<module.properties.length;i++){
		var p = module.properties[i];
		properties[p.name] = p;
	}
	// 存储字段
	var fields = [{name:'rowstate', mapping:'rowstate'}];
	for(var n in properties){
		var f = {name:n, mapping:n};
		fields.push(f);
	}
	// 列表字段
	var columns = [new Ext.PagingRowNumberer()];
	for(n in module.propertyColumns){
		var col = {
			id : properties[n].name,
			dataIndex : properties[n].name,
			header : properties[n].text
		};
		if(properties[n].xeditor == 'selectkeymap'){
			col.renderer = function(bVal){
	        	var n = this.id;
	        	for(var i=0;i<properties[n].xconfig.datas.length;i++){
	        		if(bVal == properties[n].xconfig.datas[i][0]){
	        			return properties[n].xconfig.datas[i][1];
	        		}
	        	}
	        	return bVal;
	        }
		}
		Ext.apply(col, module.propertyColumns[n]);
		columns.push(col);
	}
	
	// 数据访问
	var store = new Ext.data.DirectStore({
		pruneModifiedRecords : true,
		paramOrder : ['limit','start','sort','dir','params'],
		baseParams : {limit:60, start:0, sort:'',dir:'', params:{}},
		remoteSort : true,
		directFn : OrganizationDirect.getUserBgxx,
		root : 'results',
		totalProperty : 'totals',
		idProperty : 'id',
		fields:fields
	});
    
    var queryAction = new Ext.Action({
		text:'查 询',
		iconCls:'icon-administrator-query',
		handler:function(){
		    var bgrq1value=bgrq1.getRawValue();
		    if(bgrq1value==''){
		         Ext.MessageBox.show({title:'提示',msg:"开始变更日期不能为空！",modal:true,buttons:Ext.Msg.OK,
		         icon:Ext.Msg.INFO,width:250,closable:false,fn:function(){bgrq1.focus(false,10);}});
		        return;
		   }
		    var bgrq2value=bgrq2.getRawValue();
		    if(bgrq2value==''){
		         Ext.MessageBox.show({title:'提示',msg:"结束变更日期不能为空！",modal:true,buttons:Ext.Msg.OK,
		         icon:Ext.Msg.INFO,width:250,closable:false,fn:function(){bgrq2.focus(false,10);}});
		        return;
		   }
			panel.refresh();    
		}
	});
	
	var now = new Date();  //系统日期
    var nowrq=now.format('Y-m-d'); //日期转换

    var bgrq1 = new Ext.form.DateField({
            width:100,
            value:nowrq,
            format:"Y-m-d",
            altFormats:'Y-m-d H:i:s|m/d/Y|Ymd|Ym-d|Y-md|Y-m-d'
    });
    var bgrq2 = new Ext.form.DateField({
            width:100,
            value:nowrq,
            format:"Y-m-d",
            altFormats:'Y-m-d H:i:s|m/d/Y|Ymd|Ym-d|Y-md|Y-m-d'
    });
    
    var bgname = new Ext.form.TextField({
        width : 120
	});
	
    var tools = ['变更日期：',bgrq1,'至',bgrq2,'-','变更用户名：',bgname,'-',queryAction]; 
    var menus = [queryAction];  
	// 表格对象
	
	var sm = new Ext.grid.RowSelectionModel({singleSelect : true});
	
	var grid = new Ext.grid.GridPanel({
		border : false,
		stripeRows: true,
		lineBreak : false,
		cellSelect : true,
        loadMask: {msg:'正在装载...'},
		columns : columns,
		autoExpandColumn:'bghxx',
		enableHdMenu:false,
		enableColumnMove:false,
		store : store,
		sm : sm,
		tbar :tools,
		viewConfig:{
			getRowClass: function(record, index) {
				if(record.dirty){
					return 'mixky-grid-row-changed';
				}
		    }
		},
		contextMenu : new Ext.menu.Menu({items:menus}),
		listeners : {
			'rowcontextmenu' : function(g, rowIndex, e){
				g.getSelectionModel().selectRow(rowIndex);
				g.contextMenu.showAt(e.getXY());
			},
			'rowdblclick' : function(g, rowIndex, e){
				ChgAction.execute();
			}
		},
		getSelectedRecords : function(){
			return this.getSelectionModel().getSelections();
		},
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
            pageSize: 60,
            store: store,
            displayInfo: true,
            items : [
                '-',
                '每页显示:',
                new Ext.form.ComboBox({
                    editable : false,
                    triggerAction: 'all',
                    width : 50,
               		store : [2, 10, 20, 30, 50, 100, 200],
               		value : 60,
               		listeners : {
               			'select' : function(c, record, index){
               				grid.getBottomToolbar().pageSize = c.getValue();
               				grid.getBottomToolbar().changePage(1);
               			}
                   	}
           		})
            ]
        })
	});
	
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
	// 刷新
	panel.refresh = function(params){
	    if(Ext.isDefined(params)){
			panel.params = params;
			store.baseParams.start = 0;
		}
		
		var v_bgrq1=bgrq1.getRawValue();
		var v_bgrq2=bgrq2.getRawValue();
		var v_bgname=bgname.getValue();
        panel.params1 = {bgrq1:v_bgrq1,bgrq2:v_bgrq2,bgname:v_bgname};
		store.baseParams.params = {};
		Ext.apply(store.baseParams.params, panel.params);
		Ext.apply(store.baseParams.params, panel.params1);
		grid.getBottomToolbar().moveFirst();
	}
	panel.add(grid);
	panel.doLayout();
	//panel.refresh();
});
</script>
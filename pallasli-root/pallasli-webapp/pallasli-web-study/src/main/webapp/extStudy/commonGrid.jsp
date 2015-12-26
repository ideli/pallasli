<%@page language="java" contentType="text/html; charset=utf-8"%>

<%String rootPath=request.getContextPath(); 
	String panelId=request.getParameter("panelId");
%>

	  

<%@page import="java.util.Enumeration"%>
<script type="text/javascript">
	Ext.onReady(function(){

		var dummyData = [
		     	    {projectId: 100, project: 'Ext Forms: Field Anchoring', taskId: 112, description: 'Integrate 2.0 Forms with 2.0 Layouts', estimate: 6, rate: 150, due:'06/24/2007'},
		     	    {projectId: 100, project: 'Ext Forms: Field Anchoring', taskId: 113, description: 'Implement AnchorLayout', estimate: 4, rate: 150, due:'06/25/2007'},
		     	    {projectId: 100, project: 'Ext Forms: Field Anchoring', taskId: 114, description: 'Add support for multiple types of anchors', estimate: 4, rate: 150, due:'06/27/2007'},
		     	    {projectId: 100, project: 'Ext Forms: Field Anchoring', taskId: 115, description: 'Testing and debugging', estimate: 8, rate: 0, due:'06/29/2007'},
		     	    {projectId: 101, project: 'Ext Grid: Single-level Grouping', taskId: 101, description: 'Add required rendering "hooks" to GridView', estimate: 6, rate: 100, due:'07/01/2007'},
		     	    {projectId: 101, project: 'Ext Grid: Single-level Grouping', taskId: 102, description: 'Extend GridView and override rendering functions', estimate: 6, rate: 100, due:'07/03/2007'},
		     	    {projectId: 101, project: 'Ext Grid: Single-level Grouping', taskId: 103, description: 'Extend Store with grouping functionality', estimate: 4, rate: 100, due:'07/04/2007'},
		     	    {projectId: 101, project: 'Ext Grid: Single-level Grouping', taskId: 121, description: 'Default CSS Styling', estimate: 2, rate: 100, due:'07/05/2007'},
		     	    {projectId: 101, project: 'Ext Grid: Single-level Grouping', taskId: 104, description: 'Testing and debugging', estimate: 6, rate: 100, due:'07/06/2007'},
		     	    {projectId: 102, project: 'Ext Grid: Summary Rows', taskId: 105, description: 'Ext Grid plugin integration', estimate: 4, rate: 125, due:'07/01/2007'},
		     	    {projectId: 102, project: 'Ext Grid: Summary Rows', taskId: 106, description: 'Summary creation during rendering phase', estimate: 4, rate: 125, due:'07/02/2007'},
		     	    {projectId: 102, project: 'Ext Grid: Summary Rows', taskId: 107, description: 'Dynamic summary updates in editor grids', estimate: 6, rate: 125, due:'07/05/2007'},
		     	    {projectId: 102, project: 'Ext Grid: Summary Rows', taskId: 108, description: 'Remote summary integration', estimate: 4, rate: 125, due:'07/05/2007'},
		     	    {projectId: 102, project: 'Ext Grid: Summary Rows', taskId: 109, description: 'Summary renderers and calculators', estimate: 4, rate: 125, due:'07/06/2007'},
		     	    {projectId: 102, project: 'Ext Grid: Summary Rows', taskId: 110, description: 'Integrate summaries with GroupingView', estimate: 10, rate: 125, due:'07/11/2007'},
		     	    {projectId: 102, project: 'Ext Grid: Summary Rows', taskId: 111, description: 'Testing and debugging', estimate: 8, rate: 125, due:'07/15/2007'}
		     	];

	    var reader = new Ext.data.JsonReader({
	        idProperty: 'taskId',
	        fields: [
	            {name: 'projectId', type: 'int'},
	            {name: 'project', type: 'string'},
	            {name: 'taskId', type: 'int'},
	            {name: 'description', type: 'string'},
	            {name: 'estimate', type: 'float'},
	            {name: 'rate', type: 'float'},
	            {name: 'cost', type: 'float'},
	            {name: 'due', type: 'date', dateFormat:'m/d/Y'}
	        ]

	    });
		var store= new Ext.data.Store({
            reader: reader,
			// use local data
            data: dummyData
        });
	    var grid = new Ext.grid.GridPanel({ 
		    layout:'fit',
            //layout:'anchor',
            anchor:'-1 -1',
		    store:store,
	        columns: [
				new Ext.grid.RowNumberer({width:44}),
  
	            {
	                id: 'description',
	                header: 'Task',
	                width: 180,
	                sortable: true,
	                dataIndex: 'description',
	                hideable: false
	            },{
	                header: 'Project',
	                width: 120,
	                sortable: true,
	                dataIndex: 'project'
	            },{
	                header: 'Due Date',
	                width: 125,
	                sortable: true,
	                dataIndex: 'due'
	            },{
	                header: 'Estimate',
	                width: 120,
	                sortable: true,
	                dataIndex: 'estimate'
	            },{
	                header: 'Rate',
	                width:120,
	                sortable: true,
	                renderer: Ext.util.Format.usMoney,
	                dataIndex: 'rate'
	            },{
	                id: 'cost',
	                header: 'Cost',
	                width: 120,
	                sortable: false,
	                dataIndex: 'cost',
	                summaryRenderer: Ext.util.Format.usMoney
	            }
	        ],
	        frame: true,
	        trackMouseOver: false,
	        sm: new Ext.grid.RowSelectionModel({singleSelect:true}),
	        //enableColumnMove: false,
	        title: 'Sponsored Projects',
	        bbar: new Ext.PagingToolbar({
	        	firstText : '首页',
	        	lastText : '尾页',
	        	nextText : '下一页',
	        	prevText : '上一页',
	        	refreshText : '刷新',
	        	beforePageText : '第',
	        	afterPageText : '页，共 {0} 页',
	        	displayMsg : '共 {2} 条，当前显示当前显示当前显示 {0} 到 {1} 条',
	        	emptyMsg : '没有符合条件的数据',
	            pageSize: 10,
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
	               		value : 10,
	               		listeners : {
	               			'select' : function(c, record, index){
	               				grid.getBottomToolbar().pageSize = c.getValue();
	               				grid.getBottomToolbar().changePage(1);
	               			}
	                   	}
	           		})
	            ]
	            //,
	           // plugins: new Ext.ux.ProgressBarPager({defaultText:'正在装载数据...'}),
	            //listeners : {
	           //     'beforechange' : function(a, b){
	           // 		store.baseParams.limit = b.limit;
	            //		store.baseParams.start = b.start;
	           //     }
	           // }
	        })
	    });
	    Ext.getCmp('<%=panelId%>').add(grid);
	    Ext.getCmp('<%=panelId%>').doLayout();

});


</script>
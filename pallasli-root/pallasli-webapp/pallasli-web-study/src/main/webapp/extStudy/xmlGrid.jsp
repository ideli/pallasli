<%@page language="java" contentType="text/html; charset=utf-8"%>

<%String rootPath=request.getContextPath(); 
	String panelId=request.getParameter("panelId");
%>

<script type="text/javascript">
	
		/*!
		 * Ext JS Library 3.1.0
		 * Copyright(c) 2006-2009 Ext JS, LLC
		 * licensing@extjs.com
		 * http://www.extjs.com/license
		 */
		Ext.onReady(function(){

		    // create the Data Store
		    var store = new Ext.data.Store({
		        // load using HTTP
		        url: 'sheldon.xml',

		        // the return will be XML, so lets set up a reader
		        reader: new Ext.data.XmlReader({
		               // records will have an "Item" tag
		               record: 'Item',
		               id: 'ASIN',
		               totalRecords: '@total'
		           }, [
		               // set up the fields mapping into the xml doc
		               // The first needs mapping, the others are very basic
		               {name: 'Author', mapping: 'ItemAttributes > Author'},
		               'Title', 'Manufacturer', 'ProductGroup'
		           ])
		    });

		    // create the grid
		    var grid = new Ext.grid.GridPanel({
		        store: store,
	            anchor:'-1 -1',
		        columns: [
		  				new Ext.grid.RowNumberer({width:44}),
		            {header: "Author", width: 120, dataIndex: 'Author', sortable: true},
		            {header: "Title", width: 180, dataIndex: 'Title', sortable: true},
		            {header: "Manufacturer", width: 115, dataIndex: 'Manufacturer', sortable: true},
		            {header: "Product Group", width: 100, dataIndex: 'ProductGroup', sortable: true}
		        ],
		        width:540,
		        height:200,
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
		            ,
		            plugins: new Ext.ux.ProgressBarPager({defaultText:'正在装载数据...'}),
		            listeners : {
		                'beforechange' : function(a, b){
		            		store.baseParams.limit = b.limit;
		            		store.baseParams.start = b.start;
		               }
		            }
		        })
	        });

		    store.load();

	    Ext.getCmp('<%=panelId%>').add(grid);
	    Ext.getCmp('<%=panelId%>').doLayout();
	
});


</script>
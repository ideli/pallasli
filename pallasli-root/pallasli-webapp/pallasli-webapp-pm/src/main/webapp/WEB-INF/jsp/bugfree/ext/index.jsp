<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%String basePath=request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>项目管理</title>


	<script type="text/javascript" src="/pallasli-web-js-css/scripts/include-ext.js?theme=classic"></script>
	<script type="text/javascript" src="<%=basePath%>/extDirect/djn/djn-remote-call-support.js"></script>
	<script type="text/javascript" src="<%=basePath%>/extDirect/ejn/ejn-assert.js"></script>
	<script type="text/javascript" src="<%=basePath%>/extDirect/api/api.js"></script>
	
	
    <script type="text/javascript">
        
    Ext.Loader.setConfig({enabled: true});


    Ext.require([
        'Ext.tip.QuickTipManager',
        'Ext.container.Viewport',
        'Ext.layout.*',
        'Ext.form.Panel',
        'Ext.form.Label',
        'Ext.grid.*',
        'Ext.data.*',
        'Ext.tree.*',
        'Ext.selection.*',
        'Ext.tab.Panel'  
    ]);
    //
    Ext.onReady(function(){
     
        Ext.tip.QuickTipManager.init();

        var detailEl;
        
        var layoutExamples = [];
       
		var appkey="pallasli-webapp-pm";
		

		function openDocument(appkey, params) {
			var panelId = Ext.id();
			var tmpPanel = Ext.create("Ext.panel.Panel", {
				layout : 'fit',
				closable : false,
				border : false,
				id : panelId,
				loader : {
					url : '/' + appkey + '/jsppage.do?url=' + params.url
							+ '&panelId=' + panelId,
					autoLoad : true,
					scripts : true,
					params : params
				}
			});

			
			var addDeptWindow = new Ext.Window(Ext.apply({
				layout : 'fit',
				width : 1200,
				height : 600,
				resizable : false,
				draggable : true,
				closable : true,
				modal : true,
				closeAction : 'hide',
				title : '<span class="commoncss">'+params.title+'</span>',
				// iconCls : 'page_addIcon',
				collapsible : false,
				titleCollapse : false,
				maximizable : false,
				minimizable : false,
				buttonAlign : 'right',
				border : false,
				animCollapse : true,
				autoScroll: true,
				pageY : (document.body.clientHeight  -(params.height?params.height:600))/2,
				pageX : (document.body.clientWidth -(params.width?params.width:1200))/2,
				animateTarget : Ext.getBody(),
				constrain : true,
				items : [ tmpPanel ],
				buttons : [
						{
							text : '保存',
							iconCls : 'acceptIcon',
							id : 'btn_id_save_update',
							handler : function() {
								if (runMode == '0') {
									Ext.Msg.alert('提示',
											'系统正处于演示模式下运行,您的操作被取消!该模式下只能进行查询操作!');
									return;
								}
								var mode = Ext.getCmp('windowmode').getValue();
								if (mode == 'add')
									saveDeptItem();
								else
									updateDeptItem();
							}
						}, {
							text : '重置',
							id : 'btnReset',
							iconCls : 'tbar_synchronizeIcon',
							handler : function() {
								clearForm(addDeptFormPanel.getForm());
							}
						}, {
							text : '关闭',
							iconCls : 'deleteIcon',
							handler : function() {
								addDeptWindow.hide();
							}
						} ]
			},params));
			addDeptWindow.show();
		}
		
        var openTab = function(params) {
        	
        	if(params.isDoc){openDocument(appkey, params);return;}
        	
    		var url = params.url.replace(/\//g, '_');
    		var panelId = "tab_" + url;
    		var tabPanel = Ext.get(panelId);
    		if (tabPanel) {
    			viewPanel.setActiveTab(tabPanel.id);
    			return;
    		}
    		var tabPage = Ext.create("Ext.panel.Panel", {
    			title : params.title,
    			//region : 'center', // center region is required, no width/height specified
    			xtype : 'tab',
    			layout : 'fit',
    			border : false,
    			closable : true,
    			params : params,
    			//margins : '5 5 0 0',
    			id : panelId,
    			loader : {
    				url : '/' + appkey + '/jsppage.do?url=' + params.url
    						+ '&panelId=' + panelId + '&nid=' + panelId,
    				autoLoad : true,
    				scripts : true,
    				params : params
    			},
    			listeners : {
    				activate : function(tab) {
    					tab.loader.load();
    				}
    			}
    		});
    		viewPanel.add(tabPage);
    		viewPanel.setActiveTab(tabPage);
    	};
        var viewPanel = Ext.create("Ext.tab.Panel",{
             region: 'center', // this is what makes this panel into a region within the containing layout
             //layout: 'tab',
             margins: '2 5 5 0',
             activeItem: 0,
             border: false,
             items: layoutExamples
        });
         
        var store = Ext.create('Ext.data.TreeStore', {
            root: {
                expanded: true
            },
            proxy: {
                type: 'ajax',
                url: 'data/projectManager/tree-data-1.json'
            }
        });
        
        // Go ahead and create the TreePanel now so that we can use it below
         var treePanel = Ext.create('Ext.tree.Panel', {
             region:'west',
            title: '模块菜单',
            split: true,
            minSize: 150,
            width:200,
            rootVisible: false,
            autoScroll: true,
            store: store
        });
        var i=0;
        // Assign the changeLayout function to be called on tree node click.
        treePanel.getSelectionModel().on('select', function(selModel, record) {
        	
        	var params = {
					title : record.raw.text,
					url : record.raw.url,
					isDoc : record.raw.isDoc
				};
				openTab(params);
        });
        
        // This is the Details panel that contains the description for each example layout.
        var detailsPanel = {
            title: 'Details',
            region: 'center',
            bodyStyle: 'padding-bottom:15px;background:#eee;',
            autoScroll: true,
            html: '<p class="details-info">When you select a layout from the tree, additional details will display here.</p>'
        };
     
        
        var ButtonPanel = Ext.extend(Ext.Panel, {
            border : false,
            autoScroll: false
        });
        
       var headerPanel= new ButtonPanel({
            region   : "north",
            tbar: [{
                text: '首页',
                iconCls: 'add16'
            },{
                xtype:'splitbutton',
                text: '我的地盘',
                iconCls: 'add16',
                menu: [{text: '我的地盘',handler:function(){
                	store.proxy.url= 'data/projectManager/tree-data-1.json';
                	store.load();
                }}]
            },'-',{
                xtype:'splitbutton',
                text: '产品视图',
                iconCls: 'add16',
                menu: [{text: '产品视图',handler:function(){
                	store.proxy.url= 'data/projectManager/tree-data-2.json';
                	store.load();
                }}]
            },{
                text: '项目视图',
                iconCls: 'add16',
                handler:function(){
                	store.proxy.url= 'data/projectManager/tree-data-3.json';
                	store.load();
                }
            },{
                text: '测试视图',
                iconCls: 'add16',
                menu: [{text: '测试视图',handler:function(){
                	store.proxy.url= 'data/projectManager/tree-data-6.json';
                	store.load();
                }}]
            },'-',{
                text: '文档视图',
                iconCls: 'add16',handler:function(){
                	store.proxy.url= 'data/projectManager/tree-data-4.json';
                	store.load();
                }
            },{
                text: '组织视图',
                iconCls: 'add16',handler:function(){
                	store.proxy.url= 'data/projectManager/tree-data-5.json';
                	store.load();
                }
            },{
                text: '后台管理',
                iconCls: 'add16'
            }]
        });
        
        Ext.create('Ext.container.Viewport', {
            layout: 'border',
            items: [headerPanel,treePanel,  viewPanel
            ]
        });
    });
    </script>
</head>
</html>

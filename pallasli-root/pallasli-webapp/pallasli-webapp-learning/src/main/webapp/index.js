	
Ext.onReady(function() {
	
	
	
	//===============================table页=================================================
	
	
	var tabPanel=new Redkit.TabPanel({
        region: 'center',
        id:'tabPanel',
		activeTab:0,
		animScroll:true,
		enableTabScroll:true
	});

	
	function addTabPage(name,jspPage){
//		var tabPage=tabPanel.add({
//			title:name,  
//			plain:true,           
//			listeners: {activate: handleActivate},
//			autoLoad: {url: jspPage, params: 'foo=bar&wtf=1'},
//			closable:true
//		});
//		tabPanel.setActiveTab(tabPage);
		
		
		
	var tabPage = tabPanel.createTab({
				id : jspPage,
				title : name,
				hideMode : 'offsets',
				useFrame : true,
				closable:true,
				autoLoad :{
					url : 'tabFrame.jsp?url='+jspPage,
					scripts: true
				}
	
			});
	tabPanel.setActiveTab(tabPage);
	}
	
	
	
	
	 function handleActivate(tab){
        alert(tab.title + ' was activated.');
    }

	
	//----------------------------------------------------------------------------
	
	
	//=====================菜单树==============================================
 
    var Tree = Ext.tree;

    var basicVariable= new Tree.TreeNode({
        text: '基本量'
    });
    
    var unitNode=new Tree.TreeNode({
        text: '单位管理'
    });
    
    unitNode.appendChild(
     new Tree.TreeNode({
        text: '基本单位',
        leaf:true,
        id:'manage/basicUnitManage.tab'
    }));
      unitNode.appendChild(
     new Tree.TreeNode({
        text: '扩展单位',
        leaf:true,
        id:'manage/unitManage.tab'
    }));
    basicVariable.appendChild(unitNode);
    basicVariable.appendChild(
     new Tree.TreeNode({
        text: '物理量管理',
        leaf:true,
        id:'manage/physicsVariableManage.tab'

    }));
    basicVariable.appendChild(
     new Tree.TreeNode({
        text: '运算符管理',
        leaf:true,
        id:'manage/operaterManage.jsp',
        href:'manage/operaterManage.jsp',
        hrefTarget:'tabPanel'

    }));
    var expandFunction= new Tree.TreeNode({
        text: '扩展功能'
    });
    expandFunction.appendChild(
     new Tree.TreeNode({
        text: '添加特殊公式',
        leaf:true,
        id:'manage/formulaManage.jsp',
        href:'manage/formulaManage.jsp',
        hrefTarget:tabPanel

    }));
    
	var root = new Tree.TreeNode({
        text: '菜单',
        expanded:true
    });
    root.appendChild(basicVariable);
    root.appendChild(expandFunction);

    
//--------------------------------------------------------------------------

	//========================标题栏=====================================
	var topPanel=new Ext.form.FormPanel({
	region:'north',
	height:50,
	layout : 'table',
	frame : true,
	layoutConfig : {
				columns : 1
			},
	items : [{
				xtype : 'fieldset',
				height : 'auto',
				border:false,
				layout : 'table',
				layoutConfig : {
					columns : 1
				},
				defaults : {
					bodyStyle : 'padding:2px'
				},
				items : [{
							html : "系统管理",
							cls : 'common-text-lab'
						}]
			}]
	});
	//------------------------------------------------------------------
	
	
	//================================================================
	
	var frame = new Ext.Panel({
						region : 'center',
						header : false,
						border : false,
						loadMask : {
							msg : '正在加载页面，请稍候...'
						}
					});
					
	function handleNav(url) {
					frame.setSrc(url);
				
			}
	
	//---------------------------------------------------------------
	
	
	
	//=========================框架==========================================
 var view=new Ext.Viewport({
    layout: 'border',

    items: [{
        region: 'west',
        collapsible: true,
        title: '菜单',
        xtype: 'treepanel',
        width: 200,
        autoScroll: true, 
        split: true,
        root: root,
        rootVisible: false,
        listeners: {
            click: function(n) {

            	if(n.childNodes.length==0){
                	addTabPage(n.attributes.text,n.attributes.id);
            	}else{
            		if(n.isExpanded()){
            			n.collapse(false,true);
            		}else{
            			n.expand(false,true);
            		}
            	}
            }
        }
    },tabPanel,topPanel]
});

			view.render('viewport-div');		
			
			
//----------------------------------------------------------------------------
			
});
	
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


	var root = new Tree.TreeNode({
        text: '菜单',
        id:0,
        leaf:false
    });

    function getMenus(parentMenu,parentMenuId){
    	Ext.Ajax.request({
					url:'treeMenu.tree?method=getMenus',
					params:{
						'id':parentMenuId
					},
					waitTitle:'wait',
					waitMsg:'正在保存.......',
					success:function(response, opts){
									var menus=Ext.decode(response.responseText);

									for(i=0;i<menus.length;i++){
										var isLeaf=menus[i].isLeaf;
										var idOrPath;
										if(isLeaf&&isLeaf!="false"){
											isLeaf=true;
											idOrPath=menus[i].urlPath;
										}else{
											isLeaf=false;
											idOrPath=menus[i].id;
										}
										

										var menu=   new Tree.TreeNode({
        										text: menus[i].name,
       	 										leaf:isLeaf,
        										id:idOrPath

   											 });
   											 
										parentMenu.appendChild(menu);
//										parentMenu.expand(false,true);
   											 if(!isLeaf){
   											 	getMenus(menu,menu.id);
   											 }

									}
					},
					failure:function(){
 					
					}
			});
    }
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

            	if(n.attributes.leaf){
                	addTabPage(n.attributes.text,n.attributes.id);
            	}else{
            		if(n.isExpanded()){
            			n.collapse(false,true);
            		}else{
            			if(n.hasChildNodes()){
            			n.expand(false,true);
            			}else{
            			getMenus(n,n.id);
            			}
            		}
            	}
            }
        }
    },tabPanel,topPanel]
});

getMenus(root,0)
			view.render('viewport-div');		
			
			
//----------------------------------------------------------------------------
			
});
Ext
		.define(
				'Pallas.design.home.SysMenuDisplayPanel',
				{
					extend : "Ext.panel.Panel",
					requires : [ 'Pallas.design.home.SysMenuDesignPanel' ],
					border : false,
					editing : true,
					menuItems : null,
					autoHeight : true,

					injectBeforeHandler:function(item){
						var me=this;
						var childItem=item.items||item.menu||[];
						if(childItem.length==0){
							if(item.handlerFn){
								item.handler=function(){ if(me.editing){
									   me.editingFn(); }else{
										   item.handlerFn();
									   } };
							}else{
								item.handler=function(){ if(me.editing){
									   me.editingFn(); } };
							}
							return;
						}
						for(var i=0;i<childItem.length;i++){
							me.injectBeforeHandler(childItem[i]);
						}
						
					},
					editingFn : function(menu) {

						var sysMenuDesignPanel = Ext.create(
								"Pallas.design.home.SysMenuDesignPanel", {
									title : '设置',
									//headerPosition : 'right',
									autoScroll : false,
									border : false,
									region : 'north',
									height : '100%',
									split : false,
									collapsible : false,
									collapsed : false
								});
						Ext.create("Ext.window.Window", {
							layout : 'fit',
							width : 1280,
							height : 500,
							items : [ sysMenuDesignPanel ]
						}).show();
					},
					collapsible : false,
					initComponent : function() {
						var me = this;
						var items=me.menuItems; 
						
						for(var i=0;i<items.length;i++){
							me.injectBeforeHandler(items[i]);
						} 
						
						me.items = me.menuItems;
						
						me.items[2]={
								xtype : 'buttongroup',
								columns : 2,
								defaults : {
									scale : 'small'
								},
								items :  [{
							text : '编辑',
							handler : function() {

								me.editing = true;
							},
							iconCls : 'add16'
						} ,{
							text : '取消编辑',
							handler : function() {

								me.editing = false;
							},
							iconCls : 'add16'
						}]};
						
						// 标题
						Pallas.design.home.SysMenuDisplayPanel.superclass.initComponent
								.call(this);
						
					}
				});

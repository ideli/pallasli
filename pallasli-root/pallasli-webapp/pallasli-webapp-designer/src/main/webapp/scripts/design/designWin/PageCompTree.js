Ext
		.define(
				'Pallas.design.designWin.PageCompTree',
				{
	    			extend:"Ext.tree.Panel",
	    			initComponent: function(){
	    				var me=this;
						this.tools = [
								{
									type : 'expand',
									tooltip : 'Refresh form Data',
									// hidden:true,
									handler : function(event,
											toolEl, panel) {
										me
												.getRootNode()
												.expand();

									}
								},
								{
									type : 'collapse',
									tooltip : 'Get Help',
									handler : function(event,
											toolEl, panel) {
										me
												.getRootNode()
												.collapse();
									}
								} ];
						Pallas.design.designWin.PageCompTree.superclass.initComponent.call(this);
	    			}
	    		});

Redkit.Page = function(config) {
	config = config || {};

	this.initialConfig = config;

	Ext.apply(this, config);

	this.init();
}

Ext.extend(Redkit.Page, Ext.util.Observable, {
			workshop : null,
			ownerTab : null,

			initPage : Ext.emptyFn,

			init : function() {
				this.workshop = BizFuse.findWindowField('workshop');
				this.initPage();
			},

			close : function() {
				if (this.workshop && this.getOwnerTab()) {
					this.workshop.getTabPanel().remove(this.getOwnerTab());
				} else {
					var iframeWin = top.Ext.getCmp('iframeWin');
					if (iframeWin) {
						iframeWin.close();
					}
				}
			},

			getWorkshop : function() {
				return this.workshop;
			},

			getOwnerTab : function() {
				if (!this.ownerTab) {
					if (this.workshop && this.initialConfig.uc)
						this.ownerTab = this.workshop
								.findTabByUrlCode(this.initialConfig.uc);
				}
				return this.ownerTab;
			},

			setOwnerTabTitle : function(name, defaultName) {
				if (!name) {
					if (!defaultName)
						defaultName = "(未命名)";
					name = defaultName;
				}
				this.getOwnerTab().setTitle(name);
			},

			getSelectedData : function(grid) {
				if (!grid.getSelectionModel().hasSelection())
					return;

				return grid.getSelectionModel().getSelected().data;
			},

			getSelections : function(grid) {
				if (!grid.getSelectionModel().hasSelection())
					return;

				return grid.getSelectionModel().getSelections();
			},

			setEnabled : function(component) {
				if (component) {
					if (enabled)
						component.enable();
					else
						component.disable();
				}
			},

			hideTree : function() {
				if (this.workshop && this.workshop.hideTree) {
					this.workshop.hideTree();
				} else {
					return false;
				}
			}
		})
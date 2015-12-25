// Configuration:
//    loadUrl : URL to load nodes
//	  itemText: Text to show when none item is not selected when click OK
//	  handleResult: function(node)
//			function callback when OK has been clicked.
TreeSelectWindow = function(config) {
	var self = this;
	var single = true;
	if (config.single != null)
		single = config.single;
	this.treeSelectPanel = new TreeSelectPanel( {
		url : config.url,
		params : config.params,
		region : 'center',
		border : false
	});

	var _config = {
		id : 'tree-select-window',
		layout : 'border',
		title : 'Select...',
		itemText : 'item',
		closeAction : !single ? 'close' : 'hide',
		height : 300,
		width : 400,
		modal : true,
		items : [ this.treeSelectPanel ],
		buttons : [ {
			text : '确定',
			handler : function() {
				var node = this.treeSelectPanel.getSelectedNode();
				if (node == null) {
					Ext.MessageBox.alert('提示', '请选择一个' + this.itemText);
					return;
				}
				if (this.callback) {
					if (this.callback(node)) {
						if (single) {
							this.hide();
						} else {
							this.close();
						}
					}
				}
			},
			scope : self
		}, {
			text : '取消',
			handler : function() {
				if (single) {
					this.hide();
				} else {
					this.close();
				}
			},
			scope : self
		} ]
	}

	Ext.apply(_config, config);

	TreeSelectWindow.superclass.constructor.call(this, _config);

};

Ext.extend(TreeSelectWindow, Ext.Window, {

});
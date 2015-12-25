// Configuration:
//    loadUrl : URL to load nodes
//	  itemText: Text to show when none item is not selected when click OK
//	  handleResult: function(node)
//			function callback when OK has been clicked.
TextEditorWindow = function(config) {
	var self = this;
	this.textEditorPanel = new TextEditorPanel(config.editor);
	
	var _config = {
        title: '文本查看',
        width: 600,
        height:400,
        minWidth: 300,
        minHeight: 200,
        layout: 'border',
        plain:true,
        modal: true,
        border: false,
        bodyStyle:'padding:5px;',
        buttonAlign:'center',
        items: this.textEditorPanel,

        buttons: [{
            text: '保存',
            handler: function() {
        		self.textEditorPanel.getForm().submit({
        			url: self.url, 
        			waitMsg:'正在保存数据，请稍候...',
        			success: function(form, action) {
        				var responseData = Ext.decode(action.response.responseText);
        				if (responseData.success)
       						self.close();
       					else
       						Ext.MessageBox.alert("提示", responseData.msg);
        			},
        			failure: function(form, action) {
        			}            	
            	})
            }
        },{
            text: '关闭',
            handler: function() {
            	self.close();
            }
        }]
    }
    
	Ext.apply(_config, config);
	
    TextEditorWindow.superclass.constructor.call(this, _config); 

};

Ext.extend(TextEditorWindow, Ext.Window, {
	editor: null,
	
	show: function() {
		TextEditorWindow.superclass.show.call(this);
		this.textEditorPanel.loadData(this.editor.data);
	}
});
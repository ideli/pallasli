Ext.ux.Wiz.Card = Ext.extend(Ext.FormPanel, {
	
			text : '',
			
			finish : function() {
				return true;
			},

			getData : function() {
				return {};
			},
			
			setData : function () {
				return true;
			}

		});
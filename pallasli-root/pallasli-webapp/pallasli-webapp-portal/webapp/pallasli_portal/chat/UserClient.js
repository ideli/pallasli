Ext.define('Pallas.chat.UserClient', {
	extend : "Ext.window.Window",
	requires:['Pallas.chat.ChatWindow'],
	border : true,
	autoHeight : true,
	collapsible : false,
	layout:"fit",
	title:"交流",
	height:document.documentElement.clientHeight,
	width:220,
	initComponent : function() {
		var me = this;

		var tabPage_online=Ext.create("Ext.panel.Panel",{
			title : "在线",
			region : 'center', // center region is required, no width/height specified
			xtype : 'tab',
			layout : 'form',
			border : false,
			closable:false,
			items:[{xtype:"button",text:"用户1",handler:function(){
				Ext.create("Pallas.chat.ChatWindow").show();
			}}]
		}); 
		var tabPage_group=Ext.create("Ext.panel.Panel",{
			title : "群组",
			region : 'center', // center region is required, no width/height specified
			xtype : 'tab',
			layout : 'fit',
			border : false,
			closable:false
		}); 
		var tabPage_orgnization=Ext.create("Ext.panel.Panel",{
			title : "所有用户",
			region : 'center', // center region is required, no width/height specified
			xtype : 'tab',
			layout : 'fit',
			border : false,
			closable:false
		}); 
		
		var tabs=Ext.create('Ext.tab.Panel',{
			items: [tabPage_online,tabPage_group,tabPage_orgnization ],
			bbar:[{text:"设置",
				menu : [{
					text : '我的资料'
				}, {
					text : '状态设置',
						menu : [{
							text : '正常'
						}, {
							text : '隐身'
						}, {
							text : '忙碌'
						}, {
							text : '离开'
						}]
				}, {
					text : '消息提醒设置'
				}, {
					text : '快捷键设置'
				}, {
					text : '帮助'
				}] }]
		});
		me.items=[tabs];
		
		//Ext.create("Pallas.chat.ChatWindow").show();
		
		/**
		var textshow=Ext.create("Ext.form.field.TextArea",{width:280,height:550});
		me.items=textshow;
		 **/
			/**
			 * 轮询功能可用于聊天室
			 */
			/**Ext.direct.Manager.addProvider(Pallas.portal.api.REMOTING_API, {  
			    type: 'polling',  
			    url: Pallas.portal.api.POLLING_URLS.message,
			    interval: 5000,
			    listeners: {  
			        data: function(provider, event){
			        	ChatDirect.chat({},function(){
			        		
			        	});
			        	textshow.setValue(event.data);
			        	//win.html=event;
			        }
			    }  
			});  **/
		/**
			var iii=0;
			var polling = Ext.create('Ext.direct.PollingProvider', {
			    url: Pallas.portal.api.POLLING_URLS.message,
			    type: 'polling',
			    interval: 5000,
			    listeners: {
			        data: function (provider, event, eOpts) {
			        	iii++;
			        	ChatDirect.chat({},function(){
			        		
			        	});
			        	textshow.setValue(event.data);
			        	if(iii==10){

							polling.disconnect();
			        	}
			        }
			    },
			    id: 'GetMessagePolling'
			});
			//启动连接
			polling.connect();
			
			Ext.direct.Manager.on('message', function (e) {
			   // Ext.MessageBox.alert('提示', e.result);
			});
			**/
        me.callParent();
	}
});

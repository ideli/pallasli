
Ext.apply(Ext.MessageBox,{
	showMine:function(obj){  
		Ext.MessageBox.show( {  
		            title : obj&&obj.title?obj.title:'',  
		            msg : obj&&obj.msg?obj.msg:'',  
		            width : obj&&obj.width?obj.width:150, 
		            height : obj&&obj.height?obj.height:50,  
		            multiline : obj&&obj.multiline?obj.multiline:false,  
		            closable : obj&&obj.closable?obj.closable:true,  
		            buttons : obj&&obj.buttons?obj.buttons:Ext.MessageBox.YESNO,  
		            icon : obj&&obj.icon?obj.icon:Ext.MessageBox.INFO,  
		            fn : obj&&obj.fn?obj.fn:function(btn, txt) {  
		                //Ext.MessageBox.alert("你点击了'" + btn + "'按钮,输入的内容为'" + txt + "'.");  
		            } });  
	}});

var win;
var _showWin_=function(){

    // create the window on the first click and reuse on subsequent clicks
    if(!win){
    	
        win = new Ext.Window({
            layout:'fit',
            width:500,
            height:300,
            closeAction:'hide',
            plain: true,
            modal:true,
            items: new Ext.Panel({
            }),

            buttons: [{
                text:'Submit',
                disabled:true
            },{
                text: 'Close',
                handler: function(){
                    win.hide();
                	Ext.MessageBox.showMine();
                }
            },{
                text: '自定义提示框',
                handler: function(){
                	Ext.MessageBox.showMine({
                		title:'自定义提示框',
                		msg:'这是一个自定义的提示框',
                		fn : function(btn, txt) {  
                            Ext.MessageBox.alert("你点击了'" + btn + "'按钮");  
                        }
                	});
                }
            }]
        });
    }
    win.show(this);

	
};

var windowMenuTreePanel=new Ext.tree.TreePanel({
	layout:'fit',
	rootVisible : false,
	border:false,
	root:{
		text:"根节点",
		expanded:true,
		children:[{
			text:"EXTJS3.1.0测试",
			leaf:true,
			listeners:{
				click:function(){
					var tabs=Ext.getCmp('mainPanelId');
		        	tabs.add({
		                title: 'EXTJS3.1.0测试 ' ,
		                layout:'anchor',
						autoLoad:{url:"ext3.1.0Test.jsp",scripts:true},
						bbar:[{text:'测试javascript/Extjs3.1.0',id:'button_id'}],
						autoScroll:true,
		                closable:true
		            }).show();
				}	
			}
		}	          
		]
		
	}
	
});

var webMenuTreePanel=new Ext.tree.TreePanel({
	layout:'fit',
	rootVisible : false,
	border:false,
	root:{
		text:"根节点",
		expanded:true,
		children:[
		        {
					text:"普通Grid",
					leaf:true,
					listeners:{
						click:function(){
		        			var tabs=Ext.getCmp('mainPanelId');
				        	tabs.add({
				                title: '普通Grid ' ,
				                layout:'fit',
				                id:'Ext.Id',
								autoLoad:{url:"commonGrid.jsp",params:{panelId:'Ext.Id'},scripts:true},
				                closable:true
				            }).show();
//				        	tabs.load({
//				        	    url: 'commonGrid.jsp',
//				        	    params: {param1: 'foo', param2: 'bar'}, // or a URL encoded string
////				        	    callback: yourFunction,
////				        	    scope: yourObject, // optional scope for the callback
//				        	    discardUrl: false,
//				        	    nocache: false,
//				        	    text: 'Loading...',
//				        	    timeout: 30,
//				        	    scripts: false
//				        	}).show();
						}	
					}
				}, {
					text:"可编辑Grid",
					leaf:true,
					listeners:{
						click:function(){
		        			var tabs=Ext.getCmp('mainPanelId');
				        	tabs.add({
				                title: '可编辑Grid ' ,
								autoLoad:{url:"editorGrid.jsp",scripts:true},
				                closable:true
				            }).show();
						}	
					}
				}, {
					text:"普通form",
					leaf:true,
					listeners:{
						click:function(){
		        			var tabs=Ext.getCmp('mainPanelId');
				        	tabs.add({
				                title: '普通form ' ,
								autoLoad:{url:"commonform.jsp",scripts:true},
				                closable:true
				            }).show();
						}	
					}
				},{
					text:"window",
					leaf:true,
					listeners:{
						click:function(){
							_showWin_();
						}	
					}
				}, {
					text:"direct 测试",
					leaf:true,
					listeners:{
						click:function(){
		        			var tabs=Ext.getCmp('mainPanelId');
				        	tabs.add({
				                title: 'direct 测试 ' ,
								autoLoad:{url:"directTest.jsp",scripts:true},
				                closable:true
				            }).show();
						}	
					}
				}
		          
		]
		
	}
	
});





				
				
				
				
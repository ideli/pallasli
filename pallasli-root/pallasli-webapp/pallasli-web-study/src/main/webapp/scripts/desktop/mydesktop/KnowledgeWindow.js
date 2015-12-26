/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

       
Ext.define('MyDesktop.KnowledgeWindow', {
    extend: 'Ext.ux.desktop.Module',

    requires: [
        'Ext.form.field.HtmlEditor'
        //'Ext.form.field.TextArea'
    ],

    xmlpath:"",
    id:"knowledgeWin",
    parentId:"",
    caption:"",
    content:"",
    operationFlag:'add',//chg,del,read

    
    
    init : function(){
        this.launcher = {
            text: '知识点',
            iconCls:'icon-notepad'
        }
    },

    createWindow : function(){
    	var me=this;
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow(me.id+me.operationFlag);
        if(!win){
            win = desktop.createWindow({
                id: me.id+me.operationFlag,
                title:'知识点'+(me.operationFlag=="add"?"":"-"+me.caption),
                width:900,
                height:500,
                iconCls: 'icon-notepad',
                animCollapse:false,
                border: false,
                //defaultFocus: 'notepad-editor', EXTJSIV-1300

                // IE has a bug where it will keep the iframe's background visible when the window
                // is set to visibility:hidden. Hiding the window via position offsets instead gets
                // around this bug.
                hideMode: 'offsets',

                layout: 'form',
                bodyStyle:"padding:10px",
                items: [
                        {xtype:"textfield",labelWidth:45,value:me.caption,
                        	fieldLabel:"知识点",
                        	listeners:{
                        		change:function(){
                        			me.caption=this.value;
                        		}
                        	},
                        	readOnly:(me.operationFlag=='add'||me.operationFlag=='chg')?false:true
                       },
                    {
                        xtype: 'htmleditor',height:400,border:false,value:me.content,
                    	listeners:{
                    		change:function(){
                    			me.content=this.value;
                    		}
                    	},
                    	readOnly:(me.operationFlag=='add'||me.operationFlag=='chg')?false:true
                                
                    
                    }
                ],
                bbar:['->',{text:"保存",handler:function(){
                	var param={
                			  xmlpath:me.xmlpath,
                			  parentId:me.parentId,
                        	  id :me.id,
                        	  caption:me.caption,
                        	  content:me.content
                        	  //.replace(/&/g, "&amp;")
                  			//.replace(/</g, "&lt;").replace(/>/g, "&gt;")
                	};
                	KnowledgeAction.saveKnowledge(param,function(result){
                		if(result.sucess){
                			console.log("save success");
                		}
                	});
                }}]
            });
        }
        return win;
    }
});

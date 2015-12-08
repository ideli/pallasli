<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.mixky.engine.design.view.ViewDataService"%>
<%@ page import="com.mixky.engine.design.view.View"%>
<%@ page import="com.mixky.app.ApplicationParameters"%>
<%@ page import="com.mixky.engine.organization.dao.User"%>
<%@ page import="com.mixky.engine.certification.MixkyUserCertification"%>
<%
	// 读取参数
	String panelid = request.getParameter("panelid");
	String appkey = ApplicationParameters.instance().getApplicationCode();
    User user = MixkyUserCertification.instance().getUserInfo(request);
    String key = request.getParameter("key");
%>
<script language='javascript'>
Ext.onReady(function(){
	var panel = Ext.getCmp('<%=panelid%>');
	var win = panel.findParentByType('window');
	var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];
	
	var key = {"name":"key","xtype":"hidden",value:'<%=key%>'};
	
	var name = {"xtype":"textfield","anchor":"100%","inputValue":"","selectOnFocus":true,"name":"name","fieldLabel":"名称","allowBlank":false,"labelStyle":"color:red"};
	var description = {"xtype":"textarea","anchor":"100%","inputValue":"","selectOnFocus":true,"name":"description","fieldLabel":"描述","allowBlank":true,"maxLength":500,height:200};
	               
	var form = new Ext.form.FormPanel({
		autoScroll : true,
		layout:'form',
		border : false,
		fileUpload : true,
		trackResetOnLoad : true,
		bodyStyle : "padding:10px;padding-top:50px;padding-left:10px;padding-right:20px;overflow-x:hidden",
		items:[{"layout":"column","border":false,"items":[{"border":false,"layout":"form","style":"padding-left:10px",labelWidth:45,"columnWidth":.99 , height:60,"items":name}]},
               {"layout":"column","border":false,"items":[{"border":false,"layout":"form","style":"padding-left:10px",labelWidth:45,"columnWidth":.99 , height:200,"items":description}]},
              key
		],
		keys : [{
			key: [Ext.EventObject.ENTER], 
			fn: function(a, e) {
				if(e.target.tagName == 'DIV' || e.target.tagName == 'INPUT' || (e.target.tagName == 'TEXTAREA' && e.ctrlKey)){
					var fieldName = e.target.name;
					if(e.target.type == 'radio' || e.target.type == 'checkbox'){
						fieldName = fieldName.substr(0, fieldName.length - 1);
					}else if(!fieldName && e.target.previousSibling != null){
						fieldName = e.target.previousSibling.name;
					}
					var field;
					if(e.target.tagName == 'DIV'){
						// 判断是否只读录入框
						if(e.target.childNodes.length > 1){
							var id = e.target.childNodes[1].id;
							if(id.indexOf('x-form-el-') >=0){
								field = Ext.getCmp(id.substr(10));
							}
						}
					}else if(fieldName){
						field = form.getForm().findField(fieldName);
					}
					if(field){
						var index = -1;
						for(var i=0;i<form.getForm().items.getCount();i++){
							if(field == form.getForm().items.get(i)){
								index = i;
								break;
							}
						}
						if(index >= 0){
							var nextField;
							for(var i=index+1;i<form.getForm().items.getCount();i++){
								var nField = form.getForm().items.get(i);
								var xtype = nField.getXType();
								if(xtype != 'mixkydisplayfield' &&nField.isVisible()){
									    nextField = nField;
									    break;
								}
							}
							if(nextField){
								nextField.focus();
							}else{
								panel.submit();
							}
						}
					}
				}
			},
			scope : form
		}]
	});
	
	var formValidator =function(){
	    var v_name=form.getForm().findField('name').getValue();
	 
        if(v_name.trim()== ""){
	       Ext.MessageBox.show({title:'提示',msg:"名称不能为空!",
		   modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.WARNING,width:200,closable:false});
		   return false;
	    }
	    return true;
	}
	
	panel.submit = function(){
		   if(!form.getForm().isValid()){
			   Ext.MessageBox.show({title:'提示',msg:"页面中存在非法数据，请检查！",
			   modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.WARNING,width:300,closable:false});
			   return;
		   }
		   if (Ext.isDefined(formValidator)) {
			   // 自定义表单校验函数校验失败
			   if (formValidator() === false) {
				   return;
			   }
		   } 
		   if(form.getForm().isDirty()){
				   var params = form.getForm().getFieldValues();
		           BpmFlowDirect.processInfoSave(params,function(result,e){
					   	if (result&&result.success) {
		                    win.winRecordHandler(result);
				        }
				        else{
				            Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
			                icon:Ext.Msg.ERROR,width:250,closable:false});
				        }
				   });
			}
			else{
			     Ext.MessageBox.show({title:'提示',msg:'流程信息保存成功！',modal:true,buttons:Ext.Msg.OK,
                 icon:Ext.Msg.INFO,width:250,closable:false});
			}
	}

	panel.refresh = function(){
		form.getForm().reset();
	};
	// 输出附加脚本 end
	panel.add(form);
	panel.doLayout();
	form.doLayout();
});
</script>
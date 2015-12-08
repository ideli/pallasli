<!-- 用户录入对话框 -->
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.google.gson.*"%>
<%@ page import="com.mixky.engine.organization.dao.User"%>
<%@ page import="com.mixky.engine.design.DesignObjectLoader"%>
<%@ page import="com.mixky.engine.design.document.Panel"%>
<%@ page import="com.mixky.engine.certification.MixkyUserCertification"%>
<%@ page import="com.mixky.app.ApplicationParameters"%>
<%
	// 读取参数
	String panelid = request.getParameter("panelid");
	String appkey = ApplicationParameters.instance().getApplicationCode();
	User user = MixkyUserCertification.instance().getUserInfo(request);
	String str = request.getParameter("str") == null ? " " : request.getParameter("str");
	String formValidator = request.getParameter("validator");
	String submitDirect = request.getParameter("submitDirect") == null ? "eval(app.keyPrefix + 'AppDirect.submitFormData')": request.getParameter("submitDirect");
	String wait = request.getParameter("wait") == null ? "正在保存导入数据,请稍候..." : request.getParameter("wait");
%>
<script language='javascript'>
Ext.onReady(function(){
	var panel = Ext.getCmp('<%=panelid%>');
	var win = panel.findParentByType('window');
	var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];
    
   var f_files = {"xtype":"textfield",inputType:'file',"anchor":"95%","name":"f_files","fieldLabel":"请选择导入文件","readOnly":false};
   var isfglc = {"xtype":"radiogroup","anchor":"100%","name":"isfglc","fieldLabel":"是否覆盖原流程","columns":4,items:[{boxLabel:'否',name:'isfglc',inputValue:0,checked:true},{boxLabel:'是',name:'isfglc',inputValue:1}]};
    
    var form = new Ext.form.FormPanel({
        autoScroll : true,
		layout:'form',
		border : false,
		fileUpload : true,
		trackResetOnLoad : true,
		labelWidth : 105,
		bodyStyle : "padding:10px;padding-left:30px;padding-right:15px;overflow-x:hidden",
		api : {
			submit : <%=submitDirect%>
		},
		style:"padding-top:40px",
		html : "<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><a><%=str%></a>",
		items :[{"layout":"column","border":false,"items":[{"border":false,"layout":"form","style":"padding-left:10px","columnWidth":.99 ,height:60,"items":f_files}]},
               {"layout":"column","border":false,"items":[{"border":false,"layout":"form","style":"padding-left:10px","columnWidth":.99 ,height:60,"items":isfglc}]}
		]
		
	});
<%  
  if (formValidator != null && !"".equals(formValidator)) {
%>
	var formValidator = <%=formValidator%>;
<%
	} else {
%>
	var formValidator = function(){return true;};
<%		
	}
%>
 
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
		    Ext.MessageBox.show({title:'提示',wait:true,msg:"<%=wait%>",
            modal:true,icon:Ext.Msg.WARNING,width:300,closable:false});

			form.getForm().submit({
			   success : function(f,a){
			      Ext.MessageBox.show({title:'提示',msg:a.result.msg,modal:true,buttons:Ext.Msg.OK,
                  icon:Ext.Msg.INFO,width:410,closable:false});
				  win.winRecordHandler();
			   },
			   failure : function(f, a){
				   Ext.MessageBox.show({title:'提示',msg:a.result.msg,modal:true,buttons:Ext.Msg.OK,
	               icon:Ext.Msg.ERROR,width:350,closable:false});
			   }
		   });
		}
		else{
		    Ext.MessageBox.show({title:'提示',msg:'导入成功！',modal:true,buttons:Ext.Msg.OK,
               icon:Ext.Msg.INFO,width:250,closable:false});
               win.winRecordHandler();
		}
   }
   
   panel.refresh = function(){
		form.getForm().reset();
	};
	
	panel.add(form);
	panel.doLayout();
	form.doLayout();
});
</script>
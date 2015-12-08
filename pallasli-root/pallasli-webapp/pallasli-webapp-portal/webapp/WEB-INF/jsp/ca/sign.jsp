<%@ page contentType="text/html; charset=utf-8"%>
<%
    String id = request.getParameter("id");
    String panelid = request.getParameter("panelid");
    String cert = request.getParameter("cert");
    String name = request.getParameter("name");
    String dndata = request.getParameter("dndata")==null?"":request.getParameter("dndata");
%>
<script language='javascript'> 
function SaveDS(dn,sn,msg){
     OrganizationDirect.SaveDS(<%=id%>,dn.toString(),sn.toString(),function(result,e){
        if (result.success) {
    	    Ext.MessageBox.show({title:'提示',msg:msg,modal:true,buttons:Ext.Msg.OK,
    	    icon:Ext.Msg.INFO,width:250,closable:false});
        }
    });
}  

function getMsg(msg){
	  Ext.MessageBox.show({title:'提示',msg:msg,modal:true,buttons:Ext.Msg.OK,
	  icon:Ext.Msg.INFO,width:350,closable:false});
}  

function getWait(){
	  Ext.MessageBox.show({title:'提示',wait:true,msg:"正在提交证书信息,请稍候...",
      modal:true,icon:Ext.Msg.WARNING,width:300,closable:false});
}  

function dateformat(rq){
   return new Date(rq).format('Y-m-d');
} 
  
Ext.onReady(function(){
	var panel = Ext.getCmp('<%=panelid%>');
	var win = panel.findParentByType('window');
	
    var form = new Ext.Panel({
	    autoScroll : true,
		border : false,
		trackResetOnLoad : true,
        bodyStyle : "padding:0px;padding-left:0px;padding-right:0px;overflow-x:hidden",
        manager :  new Ext.WindowGroup(),
	    html:"<iframe id='signid' name='signid' marginwidth='0' marginheight='0' width='100%' height='100%' scrolling='auto'  frameborder='0' src='/portal/jsppage.do?url=ca/<%=cert%>&name=<%=name%>&dndata=<%=dndata%>'</iframe>" 
	});
	
	panel.getRecord = function(){
	    return Ext.getDom('signid').contentWindow.getValue();
	}
	panel.add(form);
    panel.doLayout();
    form.doLayout();
});
</script>
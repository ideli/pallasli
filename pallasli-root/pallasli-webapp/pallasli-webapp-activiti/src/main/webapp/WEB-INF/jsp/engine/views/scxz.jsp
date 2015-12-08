<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="com.mixky.engine.authority.AuthorityDataService"%>
<%@ page import="com.mixky.engine.organization.dao.User"%>
<%@ page import="com.mixky.engine.design.view.View"%>
<%@ page import="com.mixky.engine.design.view.ViewDataService"%>
<%@ page import="com.mixky.engine.design.view.Column"%>
<%@ page import="com.mixky.engine.design.Action"%>
<%@ page import="com.mixky.engine.design.module.DocumentType"%>
<%@ page import="com.mixky.engine.design.DesignObjectLoader"%>

<%
	String panelid = request.getParameter("panelid");
	String appkey = request.getParameter("appkey");
	View view = (View)request.getAttribute("view");
	User user = (User)request.getAttribute("user");
	List<Action> actions = AuthorityDataService.instance().getDesignObjectsByUser(view.getF_buttons(), user);
	List<Column> columns = AuthorityDataService.instance().getDesignObjectsByUser(view.getF_columns(), user);
	JsonObject cfg = view.getF_config();
	// 支持自定义数据
	String directFn = "eval(app.keyPrefix + 'AppDirect.getViewResults')";
	if(cfg != null && cfg.has("directFn")){
		directFn = cfg.get("directFn").getAsString();
	}
	boolean autoLoad = true;
	if(cfg != null && cfg.has("autoLoad")){
		autoLoad = cfg.get("autoLoad").getAsBoolean();
	}
%>
<html>
  <head>
    <title>定期存款数据导入</title>
    <script language="javascript">
    var panel = Ext.getCmp('<%=panelid%>');
	var win = panel.findParentByType('window');
	var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];
    
    function win_load(){
    }
    
    function sc_onclick()
  	{
  	    form1.action=app.url +"/upfile.jsp";
  		form1.submit();
  	}
  	function xz_onclick(){
		form1.action=app.url +"/downloadfile.jsp";
  		form1.submit();
  	}
  	function window_close() {}
    </script>
  </head>

  <body bgcolor="E0E9F2" onload="win_load()" onunload="JavaScript:window_close();">
  <form name="form1" action="/engine/file/sysupload.do" method="POST" ENCTYPE="multipart/form-data">
    <table border="1" class="jtpsoft" cellpadding="0" cellspacing="0" align="center" width="95%">
    	<tr>
    		<td colspan="6">上传文件:
			<input type="file" name="filename" size="40">&nbsp;
			<input type="button" value="上 传" onclick="javascript:sc_onclick();" class="button" name="sc" id="sc">
			<input type="button" value="下 载" onclick="javascript:xz_onclick();" class="button" name="xz" id="xz"></td>
    	</tr>
    </table>
  </form>
  </body>
</html>

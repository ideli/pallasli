<%@page import="com.google.gson.JsonNull"%>
<%@page import="com.google.gson.JsonArray"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.mixky.engine.organization.dao.User"%>
<%@page import="com.mixky.portal.desktop.DesktopDataService"%>
<%@page import="com.mixky.portal.sso.certification.SsoServerUserCertification"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
		User user = SsoServerUserCertification.instance().getUserInfo(request);
		JsonObject result = new JsonObject();
		if (user != null) {
			JsonArray jsonResults = DesktopDataService.instance().getTodoMessages(user);
			result.addProperty("totals", jsonResults.size());
			result.add("results", jsonResults);
		} else {
			result.addProperty("totals", 0);
			result.add("results", null);
		}
		out.print(result);
%>
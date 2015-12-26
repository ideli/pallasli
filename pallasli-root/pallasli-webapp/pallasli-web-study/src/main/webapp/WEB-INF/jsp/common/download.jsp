
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.JsonArray"%>
<%@page import="com.pallas.knowledge.action.KnowledgeAction"%>

<%@page import="com.pallasli.contanst.SystemConstant"%>
<%@page import="com.pallas.knowledge.action.DownLoadAction"%>
<%@page import="com.pallasli.utils.ZipUtils"%>
<%@page import="com.pallasli.utils.FileUtils"%>
<%

String zipPath=SystemConstant.WEB_ROOT+ "download/test.zip"; 
// path是指欲下载的文件的路径。
File file = new File(zipPath);

response.setContentType("application/x-download");//设置为下载application/x-download
String filedownload = "/download/test.zip";//即将下载的文件的相对路径
String filedisplay = "最终要显示给用户的保存文件名.zip";//下载文件时显示的文件保存名称
String filenamedisplay = URLEncoder.encode(filedisplay,"UTF-8");
response.addHeader("Content-Disposition","attachment;filename=" + filenamedisplay);

try
{
    RequestDispatcher dis = application.getRequestDispatcher(filedownload);
    if(dis!= null)
    {
        dis.forward(request,response);
    }
    response.flushBuffer();
}
catch(Exception e)
{
    e.printStackTrace();
}
finally
{

}


out.clear();  
out = pageContext.pushBody();  
%> 
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String depts = "{'success': true,'children': ["+
"{ text: '数据表',url:'design/table',expanded: true,"+
    	"children: ["+
        "{ text: 'BM', url:'design/field', leaf: false, attribute:{prefixion:'BM'} },"+
        "{ text: 'GR', url:'design/field', leaf: false, attribute:{prefixion:'GR'}},"+
        "{ text: 'GD', url:'design/field', leaf: false, attribute:{prefixion:'GD'}}"+
    "] "+
    "}"+
"]}";

String treeType=request.getAttribute("treeType").toString();
if("1".equals(treeType)){
	depts = "{'success': true,'children': ["+
			"{ text: '字段域组合',url:'design/fieldGroups',expanded: true,"+
			    	"children: ["+
			        "{ text: '单位基本信息', url:'design/fieldGroup', leaf: true },"+
			        "{ text: '单位法人信息', url:'design/fieldGroup', leaf: true},"+
			        "{ text: '某页面独立信息', url:'design/fieldGroup', leaf: true}"+
			    "] "+
			    "}"+
			"]}";	
}
if("2".equals(treeType)){
	depts = "{'success': true,'children': ["+
			"{ text: '页面设置',url:'design/pages',expanded: true,"+
			    	"children: ["+
			        "{ text: '单位开户增加', url:'design/page', leaf: true },"+
			        "{ text: '单位开户修改', url:'design/page', leaf: true},"+
			        "{ text: '个人开户', url:'design/page', leaf: true}"+
			    "] "+
			    "}"+
			"]}";	
}
if("3".equals(treeType)){
	depts = "{'success': true,'children': ["+
			"{ text: '系统配置可配置属性',url:'design/sys',expanded: true,"+
			    	"children: ["+
			        "{ text: '页面可配置属性', url:'design/sys', leaf: true },"+
			        "{ text: '字段组合可配置属性', url:'design/sys', leaf: true},"+
			        "{ text: '字段可配置属性', url:'design/sys', leaf: true},"+
			        "{ text: '使用解析技术', url:'design/sys', leaf: true}"+
			    "] "+
			    "}"+
			"]}";	
}
if("4".equals(treeType)){
	depts = "{'success': true,'children': ["+
			"{ text: '系统菜单',url:'design/sysmenu',expanded: true,"+
			    	"children: ["+
			        "{ text: '归集', url:'design/sysmenu', leaf: true },"+
			        "{ text: '贷款', url:'design/sysmenu', leaf: true},"+
			        "{ text: '财务', url:'design/sysmenu', leaf: true},"+
			        "{ text: '控制中心', url:'design/sysmenu', leaf: true}"+
			    "] "+
			    "}"+
			"]}";	
}
 response.getWriter().write(depts); 
 %>
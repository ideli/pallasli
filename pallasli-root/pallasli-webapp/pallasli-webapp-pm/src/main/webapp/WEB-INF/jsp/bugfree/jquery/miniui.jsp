<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%String basePath=request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>项目管理</title>



	<script type="text/javascript" src="/pallasli-web-js-css/scripts/jQuery/boot.js"></script>
	
	
    <script type="text/javascript">
    </script>
</head>
<body style="height: 100%; padding: 0;margin: 0;">
<div  class="mini-panel"  style="width:auto;" height="45"  showHeader="false"  borderStyle="border:solid 0px #aaa;">
    <div  region="north" height=35 showHeader="false" showSplit="false"  >
        <ul id="menu1" class="mini-menubar" style="width:100%;" 
		    url="data/projectManager/mainMenu.txt" idField="id" parentField="pid">
		</ul>
    </div>
</div>
 <div id="center-panel" style="width:auto;height:auto;"  class="mini-panel"   borderStyle="border:solid 0px #aaa;" showHeader="false"  showSplit="false"  >
        center
</div>
 

</body>
<script type="text/javascript">
mini.parse();

var menu1 = mini.get("menu1");
menu1.on("itemclick",function(e){
	 // sender: Object,         //树对象
	 // item: Object,           //项对象
	 // isLeaf: Boolean
	var path;
	switch(parseInt(e.item.id)){
		case 1:path="firstpage";break;
		case 17:path="product/addProduct";break;
		case 26:path="project/addProject";break;
		default:return;
	}
	loadurl(path);
	});
var loadurl=function(path){
	var panel = mini.get("center-panel");
	var url="jsppage.do?url=bugfree/"+path;
	var onLoad=true;
	var onDestroy=true;
	panel.load ( url, onLoad, onDestroy );
};
</script>
</html>

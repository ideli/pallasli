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
<div class="mini-fit">
 <div id="center-panel"   class="mini-tabs" style="height: 100%;"  borderStyle="border:solid 0px #aaa;" showHeader="false"  showSplit="false"  >
        center
</div>
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
		case 39:path="dashboard/addTodo";break; 
		case 9:path="dashboard/todo";break;
		case 10:path="dashboard/task";break;
		case 11:path="dashboard/bug";break;
		case 48:path="dashboard/test";break;
		case 49:path="dashboard/testCase";break;
		case 13:path="dashboard/story";break;
		case 14:path="dashboard/project";break;
		case 15:path="dashboard/dynamic";break;
		case 16:path="dashboard/profile";break;
		case 17:path="product/addProduct";break;
		case 18:path="story/story";break;
		case 19:path="product/plan";break;
		case 20:path="product/release";break;
		case 21:path="product/roadmap";break;
		case 22:path="product/doc";break;
		case 23:path="product/info";break;
		case 24:path="product/edit";break;
		case 25:path="product/modules";break;
		case 26:path="project/addProject";break;
		case 27:path="project/task";break;
		case 28:path="story/story";break;
		case 29:path="project/bug";break;
		case 30:path="project/build";break;
		case 31:path="project/burn";break;
		case 32:path="project/team";break;
		case 33:path="project/doc";break;
		case 34:path="project/product";break;
		case 35:path="project/linkStory";break;
		case 36:path="project/info";break;
		case 37:path="project/edit";break; 
		case 40:path="organization/users";break;
		case 41:path="organization/department";break;
		case 42:path="organization/company";break;
		case 43:path="organization/group";break;
		case 44:path="organization/dynamic";break;
		case 45:path="testing/bug";break;
		case 46:path="testing/testCase";break;
		case 47:path="testing/testTask";break;
		case 38:path="story/story";break;
		case 50:path="product/productList";break;
		case 51:path="project/projectList";break;
		case 52:path="testing/define";break;
		
		default:return;
	}
	loadurl(path,e.item.text);
	});
var loadurl=function(path,title){
	var tabs = mini.get("center-panel");
	
	var tab=tabs.getTab(path);
	if(!tab){

	    //add tab
	    tab = {name:path,title: title,showCloseButton:true};
	    tab = tabs.addTab(tab);            

	   
	    //active tab
		var url="jsppage.do?url=bugfree/jquery/"+path;
		var onTabLoad=function(){
			
		};
		var onTabDestroy=function(){
			
		};
		tabs.loadTab ( url, tab, onTabLoad, onTabDestroy );
	}
    tabs.activeTab(tab);
};
</script>
</html>

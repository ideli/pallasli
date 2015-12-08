<%request.getSession().setAttribute("uname", "admin");


request.setAttribute("uname", request.getSession()
		.getAttribute("uname"));
System.out.println(request.getAttribute("uname"));
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>工作平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" type="text/css"
	href="../pallasli-web-js-css/scripts/ext/4.2.1/resources/css/ext-all.css" />
<script type="text/javascript"
	src="../pallasli-web-js-css/scripts/ext/4.2.1/ext-all.js"></script>
<script type="text/javascript"
	src="extDirect/djn/djn-remote-call-support.js"></script>
	
	
<script type="text/javascript" src="extDirect/ejn/ejn-assert.js"></script>
<script type="text/javascript" src="resources/output/administrator/api.js"></script>
<script type="text/javascript" src="resources/output/portal/api.js"></script>

<script type="text/javascript" src="/lytweb/extDirect/ejn/ejn-assert.js"></script>
<script type="text/javascript" src="/lytweb/extDirect/api/api.js"></script>

		<link rel="stylesheet" type="text/css" href="waportal/css/mingpian.css"/>
		<link rel="stylesheet" type="text/css" href="waportal/css/portal.css"/>
		<link rel="stylesheet" type="text/css" href="waportal/css/portal.webpage.css"/>
		<link rel="stylesheet" type="text/css" href="waportal/css/mixky.awsoft.icon.css"/>

<script type="text/javascript">
var midwidth=800;
var midheight=800;
var maxwidth=800;
var maxheight=800; 

Mixky.wasoft.REMOTING_API.enableBuffer = 0;
Ext.Direct.addProvider(Mixky.wasoft.REMOTING_API);
Mixky.wasoft.bpm.REMOTING_API.enableBuffer = 0;
Mixky.wasoft.bpm.REMOTING_API.url='/lytweb/djn/directprovider';
Ext.Direct.addProvider(Mixky.wasoft.bpm.REMOTING_API);
	Ext.Loader.setPath({
		'Pallas.portal.waapp' : 'waportal/js',
		'Pallas.web.waapp.lib.cache.bpm' : '/lytweb/cache/bpm'
	});
	Ext.require([ 'Ext.direct.*', 'Ext.data.*', 'Ext.tree.*',
			'Ext.EventManager', 'Ext.ModelManager' ]);
	Ext.onReady(function() {

		var ApplicationInfo = {"title":"北京安泰伟奥信息技术有限公司内部OA系统","userid":720,"usertype":0,"username":"沈伟","loginname":"sw","caflag":false,"departmentname":"网厅单位","gjd":"0100","sign":false};  
		var usermenus = ["DSK","SYS","SYS1","SYS2","SYS4","SYS6","SYS7","SYS8","SYS9","SYS11","SYS12","report_zjxt","report_guiji","report_dkdb","report_wazj","report_console","report_bzj ","report_unitapp","report_test ","report_wacw","report_wxzj","report_daikuan","report_wada","report_sfk","SYS10","MAN","MAN1","MAN2","MAN3","mkCalendar","mkTodo","mkNotify","mkWorkReport","mkKnowledge","mkMail","mkFavorite","mkRegulations","mkNetFolder","mkVote","mkWorkRequest","mkMeetingManage","mkAddressBook","mktrain","mkSendDocument","mkAttendance","mkgdzc","mkPersonnelManage","mkVehicle","MAN4","waXmgl","waTasks","Log","waKqgl","waLogxx","MAN5","BUS","BUS1","Crm1","gz_dwzhgl","gz_grzhgl","gz_grzhzy","gz_hbjyw","gz_tqyw","gz_jcbg","gz_jz","gz_lxjs","gz_tjcx","gz_gjzfsz","BUS2","waGdzx","waGdsl","waGdsp","waGdphyw","waGdwzxdj","waDkhs","wadhgl","waGdyqgl","waGdllgl","waGdhzgl","waGdjz ","waGdlstj","BUS3","waXdkfsgl","waXdxmgl","waXmzqmx","BUS4","BUS5","waDwzhgl","waSfkjcgl","waSfkzfgl","waSfktzgl","sfkJzgl","waSfkjdjx","waSfktjcx","sfkKzzxsz","BUS6","BUS7","bt_dwzhgl","bt_grzhgl","bt_grzhzy","bt_hbjyw","bt_jcbg","bt_lxjs","bt_tqyw","bt_jz","BUS8","BUS9","BUS10","waArchiveEnregister","waArchive","waArchiveSele","waArchiveChange","waArchiveLogout","waArchiveDestroy","waBorrow","waStatistics","waVirtualHouse","waConfigure","BUS11","waTdxxlr","waTdtxjs","waTdflcx","SER","FIN","SER1","FIN1","wacwztbg","wacwpzgl","wacwzbgl","Crm2","wacwpzcx","wacwreport","wacwqmcl","wacwhssz","SER2","FIN2","NET1","wazjztbg","NET2","wazjzjdb","unit_hbjyw","unit_dwzhgl","unit_grzhgl","unit_tqyw","unit_txl","unit_wzxd","bmtjcx","unit_bbtj","unit_grzhzy","NET3","mkFM_CM_RM","NET4","mkFM_CM_HCM","wazjzjc","FM_CM_CAS","mkFM_CM_CS","wazjzjjh","SER3","FIN3","SER4","waGdgl","waOM","waClient","waZsk","SER5","SER6","JDGL","jdgl1","jdgl2","jdgl3","jdgl4","jdgl5","jdgl6","jdgl7","jdgl18","jdgl9","jdgl10","MAP"] ; 
		var userconfig  = {"key":"sw","uimode":"window","columns":2,"wallpaper":"console","wallpaperposition":"center","transparency":"100","backgroundcolor":"#FFFFFF","frontcolor":"#000000","theme":null,"shortcuts":[],"subjects":[{"key":"sys-todo","text":"待办","iconCls":"icon-portal-menu-sysconf-todo","width":300,"height":300,"top":50,"left":100,"webheight":300}],"quickstarts":[]}; 
		var openParams  = {}; 
		var p=  Ext.create('Pallas.portal.waapp.desktop.webpage.Desktop', {
			ApplicationInfo:ApplicationInfo,
			userMenus : usermenus,
			userConfig : userconfig,
			openParams : openParams
		}); 
		Ext.create('Ext.container.Viewport', {
			  layout:'fit',
				items :p  
			});
	});
</script>
</head>
</html>

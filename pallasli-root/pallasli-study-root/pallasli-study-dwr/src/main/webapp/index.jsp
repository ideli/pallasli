
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link rel="stylesheet" type="text/css" href="generic.css" />
<title>DWR入门--DWR后台调用页面组件 蓝杰信息@NetJava.cn</title>
</head>
<!--引入dwr的js脚本-->
<script src="dwr/interface/ChatWithSamePage.js"></script>
<script src="dwr/engine.js" /></script>
<script src="dwr/util.js" /></script>
<script type="text/javascript">
	function sendMessage() {
		ChatWithSamePage.sendWebMessage(dwr.util.getValue("text"));
	}
</script>
<body>
	<pre>
发送内容：
<input type="text" id="text"
			onkeypress="dwr.util.onReturn(event, sendMessage)" />
<input type="button" value="Send" onclick="sendMessage();" />
<hr>
<ul id="chatlog" style="list-style-type: none;">
</ul>
</pre>
</body>
</html>
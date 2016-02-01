<%@page contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html> 
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <div>  
   		 用户名:<input type="text"  id="name"/>  
   		 <input type="button" value="connection" onclick="Chat.initialize()" />  </br>
   		 发送目标:<input type="text"  id="mubiao"/>   </br>
   		 发送消息:<input type="text"  id="textt"/>  
         <input type="submit" value="Start" onclick="start()" />  
        
    </div>  
    <div id="messages"></div>  
    <script type="text/javascript">  
        //var webSocket =  new WebSocket('ws://localhost:8080/serviceDemo/websocketTest/zhangminle/1234');  
  
          var Chat = {};

        Chat.socket = null;

        Chat.connect = (function(host) {
            if ('WebSocket' in window) {
                Chat.socket = new WebSocket(host);
            } else if ('MozWebSocket' in window) {
                Chat.socket = new MozWebSocket(host);
            } else {
                console.log('Error: WebSocket is not supported by this browser.');
                return;
            }

            Chat.socket.onopen = function (event) {
                console.log('Info: WebSocket connection opened.');
                document.getElementById('messages').innerHTML   
                	= 'Connection established';  
            };
			 Chat.socket.onerror = function(event) {  
	            alert(event.data);  
            	alert("error");  
	        };  
            Chat.socket.onclose = function () {
                document.getElementById('chat').onkeydown = null;
                console.log('Info: WebSocket closed.');
            };

            Chat.socket.onmessage = function (message) {
                console.log(message);
                //document.getElementById('messages').innerHTML   
                //+= '<br />' + message.data;  
                if(message.data){
                	
                }
                alert(message.data);
            };
        });

        Chat.initialize = function() {
        	//alert('1111');
        	var v_name=document.getElementById('name').value;
            if (window.location.protocol == 'http:') {
                Chat.connect('ws://' + window.location.host + '/serviceDemo/websocketTest/'+v_name+'/1234');
            } else {
                Chat.connect('wss://' + window.location.host + '/serviceDemo/websocketTest/'+v_name+'/1234');
            }
        };

        Chat.sendMessage = (function() {
            var message = document.getElementById('chat').value;
            if (message != '') {
                Chat.socket.send(message);
                document.getElementById('chat').value = '';
            }
        });
  
  
        function start() {
        	var v_text=document.getElementById('textt').value;
        	var v_mubiao=document.getElementById('mubiao').value;
            Chat.socket.send(v_mubiao+','+v_text);  
            return false;  
        }  
    </script>  
  </body>
</html>

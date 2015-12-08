<%@page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.wasoft.BpmService"%>
<%@ page import="com.mixky.engine.certification.MixkyUserCertification"%>
<%@ page import="com.mixky.engine.organization.dao.User"%>
<%   
   User user = MixkyUserCertification.instance().getUserInfo(request);   
   String bpmurl = BpmService.instance().getUrl();
   bpmurl+="/bpm/sso1.nsf/ssoform?openform&UserName="+user.getGrbh();
%>
<html>
	<head>
		<meta http-equiv="refresh" content="1800">
	</head>
	<body>
	<iframe id='bpmFrame' style="display:hidden"></iframe>
	<script type="text/javascript">
		var bpmurl="<%=bpmurl%>#";
		bpmurl+=location.href;
		document.getElementById('bpmFrame').src=bpmurl;
		
		if(window.attachEvent){
		    window.attachEvent('onmessage', function(event){	       	    
		            var win=parent;		            
		            var token=event.data;
		            win.Mixky.wasoft.BPMToken=token;
		            //console.log("Mixky.wasoft.BPMToken="+win.Mixky.wasoft.BPMToken);		          
		    });

		    
		}else if(window.addEventListener){
		    window.addEventListener('message', function(event){	       	    
		            var win=parent;
		            var token=event.data;
		            win.Mixky.wasoft.BPMToken=token;
		            //console.log("Mixky.wasoft.BPMToken="+win.Mixky.wasoft.BPMToken);		         
		   }, false);
 
		}
		
	</script>	
	</body>
</html>
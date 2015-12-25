<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>

		
		<script type="text/javascript">
	Ext.onReady(function(){
			var data={
			rows:${data}
		}
		addStore();
	});
	
		</script>
	</head>
	<body>
		<div id="" >

		</div>
	</body>
</html>

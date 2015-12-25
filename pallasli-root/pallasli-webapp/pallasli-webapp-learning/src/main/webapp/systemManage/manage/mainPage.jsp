<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<script type="text/javascript">


  Ext.onReady(function(){
var panel=new Ext.Panel({
renderTo:"main-div",
border:false,
layout : 'table',
			
 style: {
cellspacing:'30px'
			        },
defaultType : 'textfield',
				layoutConfig : {
					columns : 3
				},
items:[
{
xtype:'fieldset',
border:true,
 style: {
						marginTop: '30px',
						marginLeft: '30px',
			            marginBottom: '0px',
	            		paddingTop: '0px',
						paddingLeft: '0px',
						paddingBottom: '0px'
			        },
 items:[
 {
 xtype:'textfield'
 }
 ]
},{
xtype:'fieldset',
border:true,
 items:[
 {
 xtype:'textfield'
 }
 ]

},{
xtype:'fieldset',
border:true,
 items:[
 {
 xtype:'textfield'
 }
 ]

}

]
});
});
        
        
        
        
    </script>
    </head>
    <body>
        <div id="main-div">
        </div>
    </body>
</html>

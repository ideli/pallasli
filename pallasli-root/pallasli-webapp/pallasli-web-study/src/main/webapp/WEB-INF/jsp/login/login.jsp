<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%String basePath=request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>学习总结</title>
	
	<script type="text/javascript" src="<%=basePath%>/scripts/include-ext.js?theme=classic"></script>
	 <style>
.x-panel-header-text-container-default {
  color: #04408c;
  font-size: 11px;
  font-weight: bold;
  font-family: tahoma, arial, verdana, sans-serif;
  line-height: 21px;
  padding: 0 2px 1px;
  text-transform: none;
}
    </style>
    <script type="text/javascript">

	    Ext.require([
	        'Ext.direct.*',
	        'Ext.data.*',
	        'Ext.tree.*'
	    ]);
        Ext.onReady(function(){

        	  Ext.QuickTips.init();  
        	     Ext.form.Field.prototype.msgTarget = 'side';  
        	        var dr = Ext.create('Ext.FormPanel', {
        	        renderTo: Ext.getBody(),
        	        frame: true,
        	        title: '用户登录',
        	        buttonAlign : 'center',   
        	        iconCls:'table_login',
        	        bodyStyle : 'background: White;padding:30 0 0 20;', 
        	        margin:"200 0 0 500", 
        	       height:250,
        	        width: 450,
        	        defaultType: 'textfield',
        	        items: [
        	               // { columnWidth:.28, //列所占的比例(最大值为1,各列的和加起来等于最大值1)
        	                    //html:'<img src="/copyright2/backstage/images/login-user.jpg" />', //左边列放一个logo
        	                    //        margin:"0 0 0 300"
        	                                
        	                    //    },
        	                {
        	                                margin:"60 0 0 20",
        	                        id :'uname',  
        	                    fieldLabel : '用户名',  
        	                    name : 'name',//元素名称  
        	                    //anchor:'95%',//也可用此定义自适应宽度  
        	                    allowBlank : false,//不允许为空  
        	                    iconCls:'table_login',
        	                    blankText : '用户名不能为空'//错误提示内容  
        	                },
        	                {
        	                        margin:"20 0 0 20",
        	                        id : 'pwd',  
        	                    //xtype: 'textfield',  
        	                    inputType : 'password',  
        	                    fieldLabel : '密　码',  
        	                    //anchor:'95%',  
        	                    maxLength : 10,  
        	                    name : 'password',  
        	                    allowBlank : false,  

        	                    blankText : '密码不能为空'  
        	                },

        	        ],
        	        buttons : [ {  
        	                
        	            text : '登录',  
        	            type : 'submit',  
        	            id : 'sb',  
        	            handler : function() {  
        	                save();  
        	            }  

        	        }, {  
        	            text : '重置',  
        	            handler : function() {  
        	                dr.form.reset();  
        	            }  
        	        } ] ,
        	        keys : [ {  
        	            key : Ext.EventObject.ENTER,  
        	            fn : save,  
        	            scope : this  
        	        } ]  
        	    });
        	function save() {  
    	    	window.location.href="home.do";
    	    	return;
        	   // var userName = uname.getValue();  
        	   // var userPass = pwd.getValue();  
        	    //验证合法后使用加载进度条  
        	    if (dr.form.isValid()) { 
        	        //提交到服务器操作  
        	        dr.form.submit({  
        	            waitMsg : '正在进行登陆验证,请稍后...',  
        	            url : 'home.do',  
        	            method : 'post',  
        	            params : {  
        	          //  userName : userName,  
        	          //  userPass : userPass  
        	            },  
        	            //提交成功的回调函数  
        	            success : function(form, action) {  
        	                if (action.result.msg == 'OK') {  
        	                   // window.location.href="login!index.action?userName="+userName;  
        	                }else if(action.result.msg == 'ERROR') {  
        	                    //window.location.href="index.jsp";  
        	                }  
        	            },  
        	            //提交失败的回调函数  
        	            failure : function(form, action) {  
        	                switch (action.failureType) {    
        	                case Ext.form.Action.CLIENT_INVALID:    
        	                    Ext.Msg.alert('错误提示', '表单数据非法请核实后重新输入！');    
        	                    break;    
        	                case Ext.form.Action.CONNECT_FAILURE:    
        	                    Ext.Msg.alert('错误提示', '网络连接异常！');    
        	                    break;    
        	                case Ext.form.Action.SERVER_INVALID:    
        	                   Ext.Msg.alert('错误提示', "您的输入用户信息有误，请核实后重新输入！");    
        	                   simple.form.reset();      
        	                }  
        	            }  
        	        });  
        	    }  
        	};   

    	});
    </script>
</head>
</html>

<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>系统管理</title>
	<script type="text/javascript" src="${scripts}/global.js"></script>
<script type="text/javascript">
var simple;
        Ext.onReady(function(){
            simple = new Ext.form.FormPanel({
                renderTo: "loginForm",
                labelWidth: 75, // label settings here cascade unless overridden 
                method: 'POST',
                frame: true,
                title: '登录窗口',
                bodyStyle: 'padding:5px 5px 0',
                style:{
                marginTop:'200px',
                marginLeft:'500px'
                
                },
                width: 300,
                defaults: {
                    width: 200
                },
                defaultType: 'textfield',
                //实现非AJAX提交表单一定要加下面的两行！
                onSubmit: Ext.emptyFn,
                submit: function(){
                    this.getEl().dom.action = 'main.do'; //连接到服务器的url地址
                    this.getEl().dom.submit();
                },
                
                items: [{
                    fieldLabel: '用户名',
                    id: 'username',
                    name: 'name',
                    allowBlank: false,
                    width: 150
                }, {
                    fieldLabel: '密码',
                    id: 'password',
                    name: 'pwd',
                    allowBlank: false,
                    width: 150,
                    inputType: 'password'
                
                }, {
                	xtype:'checkbox',
                    id: 'rememberMe',
                    name: '_spring_security_remember_me',
                    width: 150,
                    boxLabel:'让系统记住我',
                    inputValue:'remmberMe'
                
                }],
                buttons: [{
                    text: '登录',
                    type: 'button',
                    id: 'login',
                    handler: login //添加事件，执行函数为login()
                }, {
                    text: '重置',
                    type: 'reset',
                    id: 'clear',
                    handler: reset //添加事件，执行函数为reset()
                }]
            });
            
            
            
   
            
        });
        
        function login(){
        if ( simple.form.isValid()) {
        //saveUsername("444");
            simple.form.submit();
        }else{
        Ext.Msg.alert('提示', '请将数据填写完整!');
        }
        }
        function reset(){
            simple.form.reset();
        }
        
        
        
        
    </script>
    </head>
    <body>
        <div id="loginForm">
        </div>
    </body>
</html>

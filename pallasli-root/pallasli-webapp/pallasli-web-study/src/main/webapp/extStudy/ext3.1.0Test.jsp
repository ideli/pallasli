
<%@page language="java" contentType="text/html; charset=utf-8"%>

<%String rootPath=request.getContextPath();%>
	
<script type="text/javascript">

		$().ready(function() {
			
			$('textarea.expanding').ata();
		
		
		
		});
		Ext.onReady(function(){	
				Ext.getCmp('button_id').on('click',function(){
					var height=window.screen.availHeight;
					var width=window.screen.availWidth;
					var winset="dialogWidth:"+width+"px;dialogHeight:"+height+"px;scroll:no;center:yes;status:no";
					//window.showModalDialog("resend.jsp",window,winset);  
					alert(1);
					var idt=Ext.id();
					 var searchPanel= new Ext.Panel({
							layout:'fit',
							id:idt,
			            	height:400,
			            	autoLoad  : {
				            	//url : '/designer/jsppage.do?url=preview/preview',
				            	url : 'commonGrid.jsp',
				            	scripts : true,
				            	loadScripts : true,
				            	//renderer : 'data',
				            	params : {
				            		panelId:idt,
				            		userId : 1
				            	}
			            	},
			            	scripts : true,
			            	title : '首页',
			            	html : '加载失败!'
			            	});
					 var searchWin =new Ext.Window( {  
			                //window标题   
			                title : "设备检索",  
			                //window的id，根据ID可以取得窗口对象   
			                id : 'searchWin',  
			                width : 440,  
			                height : 200,  
			                //window包含的元素   
			                items : searchPanel,  
			                //自适应滚动条   
			                autoScroll : true,  
			                //当弹出窗口时，背景变灰，为不可操作状态   
			                modal : true,  
			                //设置窗口样式   
			                bodyStyle : {  
			                    background : '#ffffff',  
			                    margin : 'auto'  
			                }  
			            }) 
			            searchWin.show();
				});
		});
				
		</script>
<div >
<div style="padding:5px 20px 5px 20px; background-color: silver;height:20%">
<p>
		&lt;link rel=&quot;stylesheet&quot; type=&quot;text/css&quot; href=&quot;extjsPath/resources/css/ext-all.css&quot; /&gt;
		<br>&lt;script type=&quot;text/javascript&quot; src=&quot;extjsPath/adapter/ext/ext-base.js&quot;/&gt;
		<br>&lt;script type=&quot;text/javascript&quot; src=&quot;extjsPath/ext-all.js&quot;/&gt;
		<br>&lt;script type=&quot;text/javascript&quot; src=&quot;extjsPath/src/locale/ext-lang-zh_CN.js&quot;/&gt;
	</p>
</div>
<div style="  height:78%">
	<textarea  id="textarea_id" cols="100"  class="expanding id1" 
	style="margin-left:10px;margin-right:10px;margin-top:10px;"></textarea>
</div>
</div>
	






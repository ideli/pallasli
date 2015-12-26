<%@page language="java" contentType="text/html; charset=utf-8"%>

<%String rootPath=request.getContextPath();  %>
	  
<script type="text/javascript">
//此处通过命名空间，添加初始化远程调用API  
Ext.zhouyang.REMOTING_API.enableBuffer = 0;  
Ext.Direct.addProvider(Ext.zhouyang.REMOTING_API);  
function doit(param){
MyAction.doShow(function(result, e){  
    var t = e.getTransaction();  
    if(e.status){  
        alert(  
                           String.format('<p><b>Successful call to {0}.{1} with response:</b><xmp>{2}</xmp></p>',  
               t.action,   
                           t.method,   
                           Ext.encode(result)));  
    }else{  
        alert(  
                           String.format('<p><b>Call to {0}.{1} failed with message:</b><xmp>{2}</xmp></p>',  
               t.action,   
                           t.method,   
                           e.message));  
        }  
        //out.el.scroll('b', 100000, true);  
    });       
      
    //doEcho函数，此函数有参数。  
    MyAction.doEcho(param, function(result, e){  
        var t = e.getTransaction();  
        if(e.status){  
            alert(String.format('<p><b>Successful call to {0}.{1} with response:</b><xmp>{2}</xmp></p>',  
                t.action, t.method, Ext.encode(result)));  
        }else{  
            alert(String.format('<p><b>Call to {0}.{1} failed with message:</b><xmp>{2}</xmp></p>',  
                t.action, t.method, e.message));  
        }  
        //out.el.scroll('b', 100000, true);  
    });       
}

</script>  

  函数，此函数有参数
   <input type="button" onClick="doit(this.value)" value='myparam' />




  window.onbeforeunload = onbeforeunload_handler;   
  window.onunload = onunload_handler;
  
  var tc=0;
  var key=0; 
  /*var restrictX;  
  var restrictY;
  var x;*/
 
 // 鼠标事件  
 /*function onmousemove_handler(ev){  
	    ev = ev || window.event;  
	    var scrollLeft = document.documentElement.scrollLeft || document.body.scrollLeft;  
        var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;  
	    restrictX = ev.clientX + scrollLeft - document.documentElement.clientLeft;  
	    restrictY = ev.clientY + scrollTop - document.documentElement.clientTop;  
        
	   if(restrictY > 120){   
	       if( MixkyApp.desktop.toolbar.isVisible()){
	           MixkyApp.desktop.toolbar.setVisible(false);
	           MixkyApp.desktop.view.doLayout();
	       }
	    }
	    if(restrictY < 2){
	        x = 0;
	        countSecond();
	    }
	    else{
	        x = 3;
	    } 
 }*/
 
 /*function countSecond(){
    if(x < 2 ){
        x = x + 1;
        setTimeout("countSecond()", 500);
    }
    if(x == 2){
        MixkyApp.desktop.toolbar.setVisible(true);
    }
 }
 */
 
function onbeforeunload_handler(){
    if(tc==0){
	    if(navigator.userAgent.indexOf("MSIE")>0){
	           var width = 20; 
		       var ua = navigator.userAgent.split(";");
		       if(ua[2].indexOf("Windows NT 6.1")!=-1){
		       		width = 51 ;
		       }
		       var n = window.event.screenX - window.screenLeft;  
		       var b = n > document.documentElement.scrollWidth-width; 
		       
		       if(b && window.event.clientY<0 || window.event.altKey){  
		            if(key==37||key==39){
		               history.go(0);
		            }
		            else{
		                url= 'logout.do';
					    Ext.Ajax.request({
					           url: url,    
					           method: "POST",
					           params: {}
				        });
				    }
		       }
		       else if((n>=1 && n<= 30 && window.event.clientY<0)){
		          history.go(0);
		       }
		}
    }
}   
   
function onunload_handler(){   
   
} 
 //键盘控制 
document.onkeydown =  keydown;
function keydown(evt){ 
    var b = !!evt, oEvent = evt || window.event; 
    if(navigator.userAgent.indexOf("MSIE")>0){
		if ((oEvent.altKey)&&((oEvent.keyCode==37)||(oEvent.keyCode==39))){ 
				key=oEvent.keyCode;
		} 
	}
	if (oEvent.keyCode == 8) { 
	    var node = b ? oEvent.target : oEvent.srcElement; 
	    var reg = /^(input|textarea)$/i, regType = /^(text|textarea)$/i; 
	    if(!reg.test(node.nodeName) || !regType.test(node.type) || node.readOnly || node.disabled) {
	       if(node.type!="password"){
		       if (b){ 
		           oEvent.preventDefault(); 
		       } 
		       else{ 
		           oEvent.cancelBubble = true; 
		           oEvent.keyCode = 0; 
		           oEvent.returnValue = false; 
		       } 
		    }
	    } 
	} 
} 
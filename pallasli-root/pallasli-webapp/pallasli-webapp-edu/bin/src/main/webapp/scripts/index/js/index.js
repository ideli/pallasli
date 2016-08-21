//index's rot + login @ St. 2013-03-19
function superRot(object,config){
	this.obj = object;
	this.config = config ? config : {width:"300px",height:"200px",fontsize:"12px",right:"10px",bottom:"10px",time:"5000"};
	this.pause = false;
	var _this = this;
	if(this.config.rotBoxBg == ""){
		this.config.rotBoxBg = "#CCC";
	}
	if(this.config.squareBg == ""){
		this.config.squareBg = "#075fb6";
	}
	if(!this.config.right){
		this.config.right = "45%"
	}
	if(!this.config.bottom){
		this.config.bottom = "0px"
	}
	if(this.config.fontsize == "14px" || !this.config.fontsize){
		this.size = "14px";
		this.height = "50px";

	}else if(this.config.fontsize == "16px"){
		this.size = "16px";
		this.height = "50px";
	}
	$(object).css({ background:this.config.rotBoxBg, border:"#CCC 0px solid" });
	this.count = $(this.obj + " li").size();
	this.n =0;
	this.j =0;
	var t;
	this.factory = function(){
		$(this.obj).css({position:"relative","z-index":"0",margin:"0",padding:"0",width:this.config.width,height:this.config.height,overflow:"hidden"})
		$(this.obj).prepend("<div style='position:absolute;z-index:20;right:"+this.config.right+";bottom:"+this.config.bottom+"'></div>");
		$(this.obj + " li").css({width:"100%",height:"100%",overflow:"hidden"}).each(function(i){$(_this.obj + " div").append("<a>"+(i+1)+"</a>")});

		$(this.obj + " img").css({border:"none",width:"100%",height:"100%"})

		this.resetclass(this.obj + " div a",0);

		$(this.obj + " p").each(function(i){			
			$(this).parent().append($(this).clone(true));
			$(this).html("");
			var ieV = 6 //ie6 hacks
			var bV = $.browser.version
			if( bV <= ieV ){ $(this).css({cursor:"pointer",position:"absolute",margin:"0",padding:"0","z-index":"1",bottom:"0",left:"0",height:"30px",width:"100%",overflow:"hidden",background:"#000",opacity:"0.4"});
			}else{ $(this).css({position:"absolute",margin:"0",padding:"0","z-index":"1",bottom:"0",left:"0",height:_this.height,width:"100%",overflow:"hidden",opacity:"1"});
			}
			$(this).next().css({cursor:"pointer",position:"absolute",margin:"0",padding:"0","z-index":"2",bottom:"0",left:"0",height:_this.height,"line-height":"70px","text-indent":"8px",width:"100%","text-decoration":"none","font-size":_this.size,color:"#FFFFFF",background:"none","z-index":"1",opacity:"1",overflow:"hidden"})
			if(i!= 0){$(this).hide().next().hide()}
		});

		this.slide();
		this.addhover();
		t = setInterval(this.autoplay,this.config.time);
	}
	
	this.slide = function(){
		$(this.obj + " div a").mouseover(function(){
			_this.j = $(this).text() - 1;
			_this.n = _this.j;
			if (_this.j >= _this.count){return;}
			$(_this.obj + " li").hide();
			$(_this.obj + " p").hide();
			$(_this.obj + " li").eq(_this.j).fadeIn("slow");
			$(_this.obj + " li").eq(_this.j).find("p").show();
			_this.resetclass(_this.obj + " div a",_this.j);
		});
	}

	this.addhover = function(){
		$(this.obj).hover(function(){clearInterval(t);}, function(){t = setInterval(_this.autoplay,_this.config.time)});
	}
	
	this.autoplay = function(){
		_this.n = _this.n >= (_this.count - 1) ? 0 : ++_this.n;
		$(_this.obj + " div a").eq(_this.n).triggerHandler('mouseover');
	}
	
	this.resetclass =function(obj,i){
		$(obj).css({float:"left","margin-right":"8px",width:"10px",height:"10px",background:"#CCC",cursor:"pointer",overflow:"hidden","text-indent":"-8888px",border:"none"});
		$(obj).eq(i).css({background:this.config.squareBg,border:"none"});
	}
	this.factory();
}

//rot
$(function(){ new superRot("#rot",{rotBoxBg:"#FFF",squareBg:"#ffc000",width:"560px",height:"438px",fontsize:"14px",time:"10000"});
});

//i
$(function(){
	var i = $('.i')
	i.val('');
	i.click(function(){$(this).val('')});
	i.focus(function(){
		$(this).addClass('f');
	}).blur(function(){
		if( $(this).val() == ''){ 
			$(this).removeClass('f');
		}
	});
});




// JavaScript Document

$(function(){
	/*顶部分类*/
    $(".gengduo").click(function(){
		$(this).toggleClass("shouqi");
		$(this).parent().toggleClass("xueduan02");
	});	
	
	/*main_m*/
	$main_li=$(".main_m_top ul li");
	$main_li.hover(function(){
		$(this).addClass("selected");
		$(this).siblings().removeClass("selected");
		
	});
	$main_li.click(function(){
		var index=$main_li.index(this);
		$(".main_m_main > div")
		.eq(index).show()
		.siblings().hide();
	});
	$(".more").click(function(){
		$(this).toggleClass("more02");	
		$(".more_list").toggle();					  
	});
})
// tab @st.2013-03-13
$(function(){
	$('#tab1').show();//Default1
	$('#tab2').hide();
	$('#tab3').hide();
});

function tabBtn(i){
	selectTabBtn(i);
}
function selectTabBtn(i){
	switch(i){
		case 1:
		$('#tab1').show();
		$('#tab2').hide();
		$('#tab3').hide();
		break;
		case 2:
		$('#tab1').hide();
		$('#tab2').show();
		$('#tab3').hide();
		break;
		case 3:
		$('#tab1').hide();
		$('#tab2').hide();
		$('#tab3').show();
	}
}


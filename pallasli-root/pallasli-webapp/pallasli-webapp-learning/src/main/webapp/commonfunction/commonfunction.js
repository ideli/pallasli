function changeNumberToBig() {

var num=document.getElementById("number").value;
if(isNaN((num))){
	alert("非数字");
	return;
}
num=parseInt(num);

	var rtn = "";
	var mode = 0;
	var value = 0;
	var number = 0;
	var reg0 = /(0){1,}/g;
	
	
//	var endreg = /(〇){1,}$/;
//	var startreg = /^(〇){1,}/;
//	var middlereg = /(〇){1,}/g;
//	
//	var digit = ['〇', '一', '二', '三', '四', '五', '六', '七', '八', '九'];
//	var munit = ['', '十', '百', '千'];
	
	var endreg = /(零){1,}$/;
	var startreg = /^(零){1,}/;
	var middlereg = /(零){1,}/g;
	 var digit =new Array("零","壹","贰","叁","肆","伍","陆","柒","捌","玖");
	 var munit=['','拾', '佰', '仟'];
	
	
	var vunit = ['','万', '亿'];
	for (i = 100000000, vuniti = 2; i >=1; i =i/ 10000, vuniti--) {
		value = parseInt(num / i);
		if (value > 0) {
			if (value < 10000) {
				for (j = 1000, munitj = 3; j >= 1; j = j/ 10, munitj--) {
					var key = parseInt(value / j);
					value = value % j;
					rtn += digit[key];
					if (key != 0) {
						rtn += munit[munitj];
					}
				}
				rtn=rtn.replace(endreg, '');
				
				rtn += vunit[vuniti];
			} else {
				alert('数值过大');
				return;
			}

		}else{
		rtn +='0';
		
		}
		num=parseInt(num % i);
	}
	
	rtn=rtn.replace(reg0, digit[0]).replace(startreg, '').replace(endreg, '').replace(middlereg, digit[0]);
alert(rtn);

}
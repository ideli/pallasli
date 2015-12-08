Ext.namespace("atwa.util");

 maxwidth = (screen.width)*0.95;
 maxheight = (screen.height)*0.85;

 midwidth = (screen.width)*0.65;
 midheight = (screen.height)*0.75;

 minwidth = (screen.width)*0.4;
 minheight = (screen.height)*0.6;

atwa.util.Format = {
	// 用于将数字格式化成钱的格式
	Money : function(v) {
		if (/([0-9]+)([.]?)([0-9]?)/.test(v)) {
			v = (Math.round((v - 0) * 100)) / 100;
			v = (v == Math.floor(v)) ? v + ".00" : ((v * 10 == Math.floor(v
					* 10)) ? v + "0" : v);
			v = String(v);
			var ps = v.split(".");
			var whole = ps[0];
			var sub = ps[1] ? "." + ps[1] : ".00";
			v = whole + sub;
			if (v.charAt(0) == "-") {
				return "-" + v.substr(1)
			}
			return v
		} else {
			return ''
		}
	},
	// 用于将数字格式化成钱的格式并有逗号分隔
	toMoney : function(v,n) {
		/*n = n > 0 && n <= 20 ? n : 2;   
        v = parseFloat((v + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";  
        var l = v.split(".")[0].split("").reverse(),   
        r = v.split(".")[1];   
        t = "";  
        for(i = 0; i < l.length; i ++ )   
        {   
           t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
        }  
        return t.split("").reverse().join("") + "." + r;   */
		
		n = n > 0 && n <= 20 ? n : 2;
		t="";
		r="0,0";
		for(i = 0; i < n; i ++ ){   
           t=t+"0";
        }  
        if(t.trim()!=""){
        	r=r+"."+t;
        }
		return Ext.util.Format.number(v,r);
	},
	
	// 还原数字格式化成钱的格式并有逗号分隔
	rMoney : function(v) {
		if(v!=''){
			v=v.replace(/[^\d\.-]/g, '');
		}
		else{
			v='0.00';
		}
		return v;
	},
	//数字四舍五入转换
	round:function(v,n){
		v=Math.round(v*Math.pow(10,n))/Math.pow(10,n);  
        return   v;  
	},
	//将财务凭证类别转换为中文 pzlx为凭证类型 pzlb为凭证类别
	pzlbview:function(pzlx,pzlb){
		var pzlbstr="";
		if(pzlx==0){
			if(pzlb==0){
				pzlbstr="记";
			}
		}
		else if(pzlx==1){
			if(pzlb==1){
				pzlbstr="收";
			}
			else if(pzlb==2){
				pzlbstr="付";
			}
			else if(pzlb==0){
				pzlbstr="转";
			}
		}
		else if(pzlx==2){
			if(pzlb==1){
				pzlbstr="现";
			}
			else if(pzlb==2){
				pzlbstr="银";
			}
			else if(pzlb==0){
				pzlbstr="转";
			}
		}
		else if(pzlx==3){
			if(pzlb==1){
				pzlbstr="现收";
			}
			else if(pzlb==2){
				pzlbstr="现付";
			}
			else if(pzlb==3){
				pzlbstr="银收";
			}
			else if(pzlb==4){
				pzlbstr="银付";
			}
			else if(pzlb==0){
				pzlbstr="转账";
			}
		} 
        return  pzlbstr;  
	}
};

Ext.namespace("atwa.date");

atwa.date = {
	isDate : function(datestring) {
		try {
			date_array = (new String(datestring)).split("-");
			new Date(parseInt(date_array[0], 10),
					(parseInt(date_array[1], 10) - 1), parseInt(date_array[2],
							10));
			return true;
		} catch (e) {
			return false;
		}
	},

	// 判断输入的年月是否正确
	IsyyyyMM : function(ym) {

		if (new String(ym).length != 6) {

			return false;
		} else {

			var datestring = (new String(ym)).substring(0, 4) + "-"
					+ (new String(ym)).substring(4, 6) + "-" + "01";

			if (atwa.date.CheckDate((new String(datestring)))) {
				return true;
			} else {
				return false;
			}

		}

	},

	// 判断输入的年月是否正确 yyyy-mm

	Isyyyy_MM : function(ym) {

		var datestring = "";

		if (new String(ym).length == 6) {
			datestring = (new String(ym)).substring(0, 4) + "-"
					+ (new String(ym)).substring(4, 6) + "-" + "01";
		} else if (new String(ym).length == 7) {
			datestring = new String(ym) + "-" + "01";
		}
		if (atwa.date.CheckDate((new String(datestring)))) {
			return true;
		} else {
			return false;
		}

	},

	//格式化年月为 yyyy-mm

	Cfgyyyy_MM : function(ym) {

		if (new String(ym).length == 6) {
			return  (new String(ym)).substring(0, 4) + "-"	+ (new String(ym)).substring(4, 6) ;
		}
		else if(new String(ym).length == 7)
		{
			return ym;
		}
	},
	
	// 判断输入的日期是否正确
	CheckDate : function(INDate) {
		if (INDate == "") {
			return false;
		}
		subYY = INDate.substr(0, 4)
		if (isNaN(subYY) || subYY <= 0) {
			return false;
		}
		// 转换月份
		if (INDate.indexOf('-', 0) != -1) {
			separate = "-"
		} else {
			if (INDate.indexOf('/', 0) != -1) {
				separate = "/"
			} else {
				return false;
			}
		}
		area = INDate.indexOf(separate, 0)
		subMM = INDate.substr(area + 1, INDate.indexOf(separate, area + 1)
						- (area + 1))
		if (isNaN(subMM) || subMM <= 0) {
			return false;
		}
		if (subMM.length < 2) {
			subMM = "0" + subMM
		}
		// 转换日
		area = INDate.lastIndexOf(separate)
		subDD = INDate.substr(area + 1, INDate.length - area - 1)
		if (isNaN(subDD) || subDD <= 0) {
			return false;
		}
		if (eval(subDD) < 10) {
			subDD = "0" + eval(subDD)
		}
		NewDate = subYY + "-" + subMM + "-" + subDD
		if (NewDate.length != 10) {
			return false;
		}
		if (NewDate.substr(4, 1) != "-") {
			return false;
		}
		if (NewDate.substr(7, 1) != "-") {
			return false;
		}
		var MM = NewDate.substr(5, 2);
		var DD = NewDate.substr(8, 2);
		if ((subYY % 4 == 0 && subYY % 100 != 0) || subYY % 400 == 0) { // 判断是否为闰年
			if (parseInt(MM) == 2) {
				if (DD > 29) {
					return false;
				}
			}
		} else {
			if (parseInt(MM) == 2) {
				if (DD > 28) {
					return false;
				}
			}
		}
		var mm = new Array(1, 3, 5, 7, 8, 10, 12); // 判断每月中的最大天数
		for (i = 0; i < mm.length; i++) {
			if (parseInt(MM) == mm[i]) {
				if (parseInt(DD) > 31) {
					return false;
				}
			} else {
				if (parseInt(DD) > 30) {
					return false;
				}
			}
		}
		if (parseInt(MM) > 12) {
			return false;
		}
		return true;
	},
	
   formatDateInput : function(v) {
       if(v.length == 8){
           ctl= v.substr(0,4) + "-" + v.substr(4,2) + "-" + v.substr(6,2);
       }
       if(v.length == 6){
          var year = new Date().getYear();
          ctl = year.toString().substr(0,2) + v.substr(0,2) + "-" + v.substr(2,2) + "-" + v.substr(4,2);
       }
       return ctl;
   }
};

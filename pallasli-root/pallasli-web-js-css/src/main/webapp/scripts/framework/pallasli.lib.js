if(typeof(pallasli)=="undefined") pallasli = {};

/**
 * 方法名：pallasli.showMsg
 * 
 * 功能：消息提醒
 * 
 * 参数： formName：要加载数据的form url：生成数据的URL地址 params: url 参数 call: 回调函数
 */
pallasli.showMsg = function() {
	alert("消息");
};

/**
 * 方法名：pallasli.showWarn
 * 
 * 功能：警告提醒
 * 
 * 参数： formName：要加载数据的form url：生成数据的URL地址 params: url 参数 call: 回调函数
 */
pallasli.showWarn = function() {
	alert("警告");
};

/**
 * 方法名：pallasli.showError
 * 
 * 功能：错误提醒
 * 
 * 参数： formName：要加载数据的form url：生成数据的URL地址 params: url 参数 call: 回调函数
 */
pallasli.showError = function() {
	alert("error");
};

// 显示加载标识
pallasli.showLoadMask = function() {

};
// 隐藏加载标识
pallasli.hideLoadMask = function() {

};
// 显示进度条
pallasli.showProgress = function() {

};
// 隐藏进度条
pallasli.hideProgress = function() {

};
// 系统错误
pallasli._erryFunction = function(e) {
	console.log(e);
	alert("error");
};

pallasli.validateForm = function(formName) {
	return $("[name=" + formName + "]").validate().form();
};
var chars = [ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C',
		'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
		'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' ];

pallasli.generateMixed = function(n) {
	var res = "";
	for (var i = 0; i < n; i++) {
		var id = Math.ceil(Math.random() * 35);
		res += chars[id];
	}
	return res;
}

/**
 * 方法名：pallasli.convertToJson
 * 
 * 功能：转换数据类型
 * 
 * 参数：
 */
pallasli.convertToJson = function(formValues) {
	var result = {};
	for (var formValue, j = 0; j < formValues.length; j++) {
		formValue = formValues[j];
		var name = formValue.name;
		var value = formValue.value;
		if (name.indexOf('.') < 0) {
			result[name] = value;
			continue;
		} else {
			var simpleNames = name.split('.');
			// 构建命名空间
			var obj = result;
			for (var i = 0; i < simpleNames.length - 1; i++) {
				var simpleName = simpleNames[i];
				if (simpleName.indexOf('[') < 0) {
					if (obj[simpleName] == null) {
						obj[simpleName] = {};
					}
					obj = obj[simpleName];
				} else { // 数组
					// 分隔
					var arrNames = simpleName.split('[');
					var arrName = arrNames[0];
					var arrIndex = parseInt(arrNames[1]);
					if (obj[arrName] == null) {
						obj[arrName] = []; // new Array();
					}
					obj = obj[arrName];
					multiChooseArray = result[arrName];
					if (obj[arrIndex] == null) {
						obj[arrIndex] = {}; // new Object();
					}
					obj = obj[arrIndex];
				}
			}

			if (obj[simpleNames[simpleNames.length - 1]]) {
				var temp = obj[simpleNames[simpleNames.length - 1]];
				obj[simpleNames[simpleNames.length - 1]] = temp;
			} else {
				obj[simpleNames[simpleNames.length - 1]] = value;
			}

		}
	}
	return result;
};
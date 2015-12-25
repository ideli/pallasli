BizFuse = {version: '1.0'};

// for data transmission between jsp and js
BizFuse.params = {};

BizFuse.text = {};

BizFuse.url = {};

BizFuse.findWindowField = function(varname) {
	
	var field = searchWindowField(window, varname);
	
	//if (!field) {
		//alert('Can not find "' + varname + '" in window');
	//}	
	
	return field;
	
	function searchWindowField(w, varname) {
		if (!w)
			return;
		
		if (w[varname])
			return w[varname];
		
		if (w == w.parent)
			return;
			
		return searchWindowField(w.parent, varname);		
	}
	
	
}

BizFuse.findTopWindow = function() {
		
		return searchTopWindow(window);
		
	
		function searchTopWindow(w) {
			if ((w)&&((w.parent)||(w==w.parent)))
				return w;
				
			return searchTopWindow(w.parent);
		}
}

BizFuse.transferDate = function(data)
	{
		if(BizFuse.isArray(data))
		{
			for(i=0;i<data.length;i++)
			{
				data[i] = transferDate(data[i]);
			}
		}
		else
		{
			for(element in data)
			{
				if(data[element].day && (data[element].month || data[element].month == 0) && data[element].year){
					data[element] = new Date(data[element].time);
				}
			}
		}
		return data;
	}
	
BizFuse.isArray = function (obj) {
	   if (obj.constructor.toString().indexOf("Array") == -1)
	      return false;
	   else
	      return true;
}
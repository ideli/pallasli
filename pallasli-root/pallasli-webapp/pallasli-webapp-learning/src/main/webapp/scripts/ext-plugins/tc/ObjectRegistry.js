/**
 * @author taoy
 */

var ObjectRegistry = function(v) {
	
	var objects = new ArrayList();
	
	var validate = function(p) {
		if (v)
			return v(p);
		return true;
	}
	
	this.register = function(p) {
		if (p&&!objects.contains(p)&&(validate(p)))
			objects.add(p);
	}
	
	this.unregister = function(p) {
		if (p&&objects.contains(p)) {
			var index = objects.indexOf(p);
			objects.remove(index);
		}			
	}
	
	this.size = function() {
		return objects.size();
	}
	
	this.get = function(index) {
		return objects.get(index);
	}
}
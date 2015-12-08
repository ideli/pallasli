Ext.define('Pallas.extDesigner.waapp.utils.ObjectFormat', {
	"statics" : {
		getObjectFormatString : function(name, value, level) {
			var result = '';
			var type = typeof value;
			if (value instanceof Array) {
				type = 'array';
			}
			var preTab = '';
			for (var i = 0; i < level; i++) {
				preTab = preTab + '\t';
			}
			result = preTab;
			if (name) {
				result = result + name + ' : ';
			}
			switch (type) {
			case 'boolean':
			case 'number':
				result = result + value + ',\n';
				break;
			case 'string':
				result = result + Ext.encode(value) + ',\n';
				break;
			case 'object':
				result = result + '{\n';
				var hasNode = false;
				for (n in value) {
					if (value.hasOwnProperty(n)) {
						hasNode = true;
						var tmpV = this.getObjectFormatString(n, value[n],
								level + 1);
						result = result + tmpV;
					}
				}
				if (level > 0 && hasNode) {
					result = result.substring(0, result.length - 2) + '\n';
				}
				result = result + preTab + '},\n';
				break;
			case 'array':
				result = result + '[\n';
				var hasNode = false;
				for (n in value) {
					if (value.hasOwnProperty(n)) {
						hasNode = true;
						var tmpV = this.getObjectFormatString(false, value[n],
								level + 1);
						result = result + tmpV;
					}
				}
				if (level > 0 && hasNode) {
					result = result.substring(0, result.length - 2) + '\n';
				}
				result = result + preTab + '],\n';
				break;
			case 'function':
				result = result
						+ '('
						+ value.toString().replace(new RegExp("\n", "gm"),
								preTab + '\n') + '),\n';
				break;
			case 'undefined':
				result = result + value + ',\n';
				break;
			}
			if (level == 0) {
				return result.substring(0, result.length - 2);
			} else {
				return result;
			}
		}
	}
});
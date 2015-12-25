/*
Ext.urlEncode = function (o, pre){
	var buf = [],
		key,
		e = encodeURIComponent;

	for(key in o) {
		Ext.each(o[key], function(val, i) {
			if(val!=0)
				val != key ? e(val) : "";
			buf.push("&", e(key), "=", val);
		});
	}
	if(!pre) {
		buf.shift();
		pre = "";
	}
	return pre + buf.join('');
}
*/

/*
Ext.grid.GridView.prototype.resolveCell = function(row, col, hscroll){
	if(typeof row != "number"){
           row = row.rowIndex;
       }
       if(!this.ds){
           return null;
       }
       if(row < 0 || row >= this.ds.getCount()){
           return null;
       }
       col = (col !== undefined ? col : 0);

       var rowEl = this.getRow(row), cellEl;
       if(!(hscroll === false && col === 0)&&(this.cm.getColumnCount()>0)){
           while(this.cm.isHidden(col)){
               col++;
           }
           cellEl = this.getCell(row, col);
       }

	return {row: rowEl, cell: cellEl};
}
*/


//firefug + firefox3.5
//Permission denied to access property 'dom' from a non-chrome context 
Ext.override(Ext.Element, {
    contains: function() {
        var isXUL = Ext.isGecko ? function(node) {
            return Object.prototype.toString.call(node) == '[object XULElement]';
        } : Ext.emptyFn;

        return function(el) {
            return !this.dom.firstChild || // if this Element has no children, return false immediately
                   !el ||
                   isXUL(el) ? false : Ext.lib.Dom.isAncestor(this.dom, el.dom ? el.dom : el);
        };
    }()
}); 

Ext.namespace("Mixky.editor");


Mixky.editor.UploadField = Ext.extend(Ext.form.TextField, {
	
	applicationkey : undefined, 

	defaultAutoCreate:{tag:"input", type:"hidden", name:this.name},
	
	buttonText: '上传...',

    // private
    readOnly: false,
    
    uploadFieldName: undefined,

    // private
    onRender : function(ct, position){
		Mixky.editor.UploadField.superclass.onRender.call(this, ct, position);

        this.wrap = this.el.wrap({cls:'x-form-field-wrap x-form-file-wrap'});
        
        // 录入框
        this.fileInput = this.wrap.createChild({
            name: this.uploadFieldName||this.name + '_',
            cls: 'x-form-file',
            tag: 'input',
            type: 'file',
            size:0
        });

        // 文件名显示
        this.filenameDisplay = this.wrap.createChild({
        	tag : 'div',
        	cls : 'x-form-file-msg',
        	html : '&nbsp;'
        });
        
        // 上传按钮
        this.buttonWrap = this.wrap.createChild({
            tag: 'div'
        });
        var btnCfg = Ext.applyIf(this.buttonCfg || {}, {
            text: this.buttonText
        });
        this.button = new Ext.Button(Ext.apply(btnCfg, {
            renderTo: this.buttonWrap,
            cls: 'x-form-file-btn' + (btnCfg.iconCls ? ' x-btn-icon' : '')
        }));

        // 选中文件
        this.fileInput.on('change', function(){
            var v = this.fileInput.dom.value;
            this.setValue(v, true);
            this.fireEvent('fileselected', this, v);
        }, this);
    },
    
    setValue : function(v, isLocal){
    	if(isLocal){
    		// 本地选中
    		if (v == '') {
    			this.filenameDisplay.update(" ");
    		} else {
    			this.filenameDisplay.update('<b>已选文件 :</b> ' + v);
    		}
    		
        	Mixky.editor.UploadField.superclass.setValue.call(this, v);
    	}else{
    		if (v == '' || v == null || v == 'null' || v == '[object Object]') {
    			
    		} else {
	    		// 远程设置文件名
    			var app = Mixky.wasoft.cache.Applications[this.applicationkey];
				var href_url = app.url + "/file/download.do?id=" + this.document.document.documentid + "&fk=" + this.uploadFieldName + "&dk=" + this.document.document.documentkey;
	
	    		if(this.readOnly){
	        		this.filenameDisplay.update('<a href="' + href_url + '" target=hiddenf>' + v + '</a> ');
	    			// 只读
	    		}else{
	    			// 可编辑（允许删除）
	        		this.filenameDisplay.update('<a href="' + href_url + '" target=hiddenf>' + v + '</a> ');
	        		var del = document.createElement("A");
	    			del.innerHTML = '删除';
	    			del.href = "#";
	    			
	    			var delEl = Ext.get(del);
	    			var field = this;
	    			delEl.on('click', function() {
	    				//field.setValue("", true);
	    				field.deleteFieldFile();
	    			});
	        		this.filenameDisplay.appendChild(del);
	        		
	    		}
	        	Mixky.editor.UploadField.superclass.setValue.call(this, v);
    		}
    	}
    },
    
    deleteFieldFile: function() {
    	var fileEl = this;
		var app = Mixky.wasoft.cache.Applications[this.applicationkey];
		var fn = eval(app.keyPrefix + 'AppDirect.deleteDocumentFieldFile');
		fn(this.document.document.documentkey, this.document.document.documentid, this.name, this.uploadFieldName, function(result, e){
			if(result && result.success) {
				fileEl.setValue("", true);
			} else {
				MixkyApp.showAlertMessage('删除文件失败！');
			}
		});
    	
    },

    // private
    onDestroy: function(){
    	Mixky.editor.UploadField.superclass.onDestroy.call(this);
        Ext.destroy(this.fileInput, this.button, this.buttonWrap, this.wrap);
    },

    // private
    onResize : function(w, h){
    	Mixky.editor.UploadField.superclass.onResize.call(this, w, h);
        this.wrap.setWidth(w);
        this.filenameDisplay.setWidth(w - this.button.getEl().getWidth() - 5);
        this.fileInput.setLeft(this.button.getEl().getWidth() - this.fileInput.getWidth());
    },

    // private
    preFocus : Ext.emptyFn,

    // private
    getResizeEl : function(){
        return this.wrap;
    },

    // private
    getPositionEl : function(){
        return this.wrap;
    },

    // private
    alignErrorIcon : function(){
        this.errorIcon.alignTo(this.wrap, 'tl-tr', [2, 0]);
    }
});
Ext.reg('mixkyuploadfield', Mixky.editor.UploadField);
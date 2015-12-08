
Ext.namespace('Mixky.editor');

Mixky.editor.OpinionField = Ext.extend(Ext.form.TextField, {
	applicationkey : undefined, 
    hideDisplayBorder : undefined,
	defaultAutoCreate:{tag:"input", type:"hidden", name:this.name},
	onRender:function (B, A) {
		Mixky.editor.OpinionField.superclass.onRender.call(this, B, A);
		this.wrap = this.el.wrap();
		this.opinionEl = this.wrap.createChild({tag:"div"});
		if(!this.hideDisplayBorder){
			this.wrap.setStyle('border','1px solid #cccccc');
		}
		this.opinionEl.setStyle('padding','3px');
		if(!this.readOnly){
			var buttonEl = this.wrap.createChild({tag:"div", align:"right"});
			var opinionButtonEl = buttonEl.createChild({tag:"div"});
			var opinionButton = new Ext.SplitButton({
				iconCls:'icon-sys-btnedit',
				text:'填写意见',
				handler: this.openOpinionWindow, 
				menu: new Ext.menu.Menu({
					items: [{
						text : '同意',
						handler : function(){
							this.setDirectOpinion('同意');
						},
						scope : this
					},{
						text : '已阅',
						handler : function(){
							this.setDirectOpinion('已阅');
						},
						scope : this
					},{
						text : '不同意',
						handler : function(){
							this.setDirectOpinion('不同意');
						},
						scope : this
					}]
				}),
				scope:this
			});
			opinionButton.render(opinionButtonEl);
		}
		this.loadValue();
	},
	loadValue:function(){
		var app = Mixky.wasoft.cache.Applications[this.applicationkey];
		var fn = eval(app.keyPrefix + 'AppDirect.getFieldDisplayOpinion');
		fn(this.document.document.documentkey, this.document.document.documentid, this.name, function(result, e){
			if(result && result.success){
				this.setOpinion(result.opinion);
			}else{
				MixkyApp.showDirectActionFail('获取意见', result, e);
			}
		}, this);
	},
	openOpinionWindow:function(){
		Mixky.wasoft.workflow.OpinionWindow(this.applicationkey, this.document.document.documentkey, this.document.document.documentid, {objectkey:this.name, objectid: this.id});
	},
	setDirectOpinion:function(opinion){
		var app = Mixky.wasoft.cache.Applications[this.applicationkey];
		var fn = eval(app.keyPrefix + 'AppDirect.setFieldOpinion');
		fn(this.document.document.documentkey, this.document.document.documentid, opinion, this.name, function(result, e){
			if(result && result.success){
				this.loadValue();
			}
		}, this);
	},
	initValue:function () {
		if (this.value !== '') {
			this.setOpinion(this.value);
		} else {
			this.setOpinion("&nbsp;");
		}
	},
	setOpinion:function (A) {
		if(A != '&nbsp;'){
			this.value = A;
		}
		if (this.opinionEl) {
			this.opinionEl.dom.innerHTML = (A === '' || A === null || A === undefined ? "&nbsp;" : A);
		}
	},
	getValue:function () {
		if (!this.rendered) {
			return this.value;
		}
		var A = this.opinionEl.dom.innerHTML;
		if (A === this.emptyText || A === undefined) {
			A = "";
		}
		return A;
	},
	validate:function () {
		return true;
	}
});
Ext.reg("mixkyopinionfield", Mixky.editor.OpinionField);

Mixky.editor.BpmOpinionField = Ext.extend(Ext.form.ComboBox, {
    defaultAutoCreate:{tag:"textarea", name:this.name, style:"height:60px;",autocomplete: "off"},
	valueField:'value',
	displayField:'display',
	store : [[1,'同意！'],[2,'已阅！'],[3,'不同意！'],[4,'请尽快办理！'],[5,'请xxx协助办理！']],
	triggerAction:'all',
	mode : 'local',

	getValue:function () {
		 return this.getRawValue();
	}
});
Ext.reg("bpmopinionfield", Mixky.editor.BpmOpinionField);
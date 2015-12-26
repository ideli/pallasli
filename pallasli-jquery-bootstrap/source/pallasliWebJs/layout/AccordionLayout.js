/**



<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingOne">
      <h4 class="panel-title">
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          每项标题
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body">
		每项组件
      </div>
    </div>
  </div> 
</div>

*/


Pallasli.layout.AccordionLayout =  function(cfg){
	Pallasli.layout.Layout.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["accordion"]=Pallasli.layout.AccordionLayout ;
$.extend( Pallasli.layout.AccordionLayout.prototype, Pallasli.layout.Layout.prototype, {
	initLayout : function(cfg) {
		this.mainWrapperHtml='<div class="col-xs-12 col-md-6"></div>';
		this.childWrapperHtml='<div class="col-xs-12 col-md-6"></div>';
	}
});
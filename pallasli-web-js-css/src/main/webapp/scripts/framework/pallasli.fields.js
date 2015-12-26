if (typeof (pallasli) == "undefined")
	pallasli = {};
pallasli.TextField = function(params) {
	// 0:id 1:fieldLabel 2:emptyText 3:cls 4:name

	var textField = '<div class="col-xs-12 col-md-6"><div class="row form-group">'
			+ '<div class="col-xs-5 col-md-3">'
			+ '<label for="{0}">{1}</label>'
			+ '</div><div class="col-xs-7 col-md-9">'
			+ '<input name="{4}" type="{5}" class="form-control" id="{0}" placeholder="{2}"  {6}>'
			+ '<label class="tipinfo"></label></div></div></div>';
	var id = "pallali-gen-" + pallasli.generateMixed(32);
	var otherParam = "";
	if (params.required)
		otherParam += " required";
	if (params.zipCode)
		otherParam += " zipCode=true";
	if (params.minlength)
		otherParam += " minlength=" + params.minlength;
	if (params.maxlength)
		otherParam += " maxlength=" + params.maxlength;

	textField = $.format(textField, id, params.fieldLabel, params.emptyText,
			params.cls, params.name, params.type ? params.type : "text",
			otherParam);
	return textField;

}
pallasli.EmailField = function(params) {
	// 0:id 1:fieldLabel 2:emptyText 3:cls 4:name

	var textField = '<div class="col-xs-12 col-md-6"><div class="row form-group">'
			+ '<div class="col-xs-5 col-md-3">'
			+ '<label for="{0}">{1}</label>'
			+ '</div><div class="col-xs-7 col-md-9">'
			+ '<input name="{4}" type="{5}" class="form-control" id="{0}" placeholder="{2}"  {6}>'
			+ '<label class="tipinfo"></label></div></div></div>';
	var id = "pallali-gen-" + pallasli.generateMixed(32);
	var otherParam = "";
	if (params.required)
		otherParam += " required";
	if (params.email === false)
		otherParam += " email=false";
	if (params.minlength)
		otherParam += " minlength=" + params.minlength;
	if (params.maxlength)
		otherParam += " maxlength=" + params.maxlength;

	textField = $.format(textField, id, params.fieldLabel, params.emptyText,
			params.cls, params.name, params.type ? params.type : "text",
			otherParam);

	return textField;

}
pallasli.Menu = function(params) {
	// 0:id 1:fieldLabel 2:emptyText 3:cls 4:name

	var textField='<li><a href="{0}" target="main"'
		+ ' class="list-group-item" style="float: left"> {1} </a></li>';
	textField = $.format(textField, params.url, params.text);
	return textField;

}
pallasli.menus = function(params) {
	// 0:id 1:fieldLabel 2:emptyText 3:cls 4:name
	var textField="";
	for(var i=0;i<params.length;i++){
		textField+=pallasli.Menu(params[i]);
	}
	
	return textField;

}
pallasli.Navigation = function(params) {
	// 0:id 1:fieldLabel 2:emptyText 3:cls 4:name

	var textField = '<div class="navbar">'
			+ '<div class="navbar-inner">'
			+ '<div class="container">'
			+ '<div class="nav-collapse">'
			+ '<ul class="nav">'
			+ pallasli.menus(params)
			+ '</ul>' + '</div>' + '</div>' + '</div>' + '</div>'; 

	return textField;

}
(function($) {
	dragJs.properties = {
		panel : [ 
		          {name:"title",caption:"标题",type:"string",defaultValue:""},
		          {name:"height",caption:"高度",type:"int",defaultValue:300},
		          {name:"width",caption:"宽度",type:"int",defaultValue:300},
		          {name:"layout",caption:"布局",type:"string",defaultValue:"fit"}
		],
		form : [
		          {name:"title",caption:"标题",type:"string",defaultValue:""},
		          {name:"height",caption:"高度",type:"int",defaultValue:300},
		          {name:"width",caption:"宽度",type:"int",defaultValue:300}
		],
		tree : [
		          {name:"title",caption:"标题",type:"string",defaultValue:""},
		          {name:"height",caption:"高度",type:"int",defaultValue:300},
		          {name:"width",caption:"宽度",type:"int",defaultValue:300}
		],
		textfield : [
			      {name:"fieldlabel",caption:"字段名称",type:"string",defaultValue:"字段名"},
			      {name:"name",caption:"英文名",type:"string",defaultValue:""},
			      {name:"width",caption:"宽度",type:"int",defaultValue:300}
	    ],
		textarea : [
				  {name:"fieldlabel",caption:"字段名称",type:"string",defaultValue:"字段名"},
				  {name:"name",caption:"英文名",type:"string",defaultValue:""},
			      {name:"height",caption:"高度",type:"int",defaultValue:300},
			      {name:"width",caption:"宽度",type:"int",defaultValue:300}
		],
		checkbox : [
				  {name:"fieldlabel",caption:"字段名称",type:"string",defaultValue:"字段名"},
				  {name:"name",caption:"英文名",type:"string",defaultValue:""},
			      {name:"height",caption:"高度",type:"int",defaultValue:300},
			      {name:"width",caption:"宽度",type:"int",defaultValue:300}
	    ],
		radio : [
			      {name:"fieldlabel",caption:"字段名称",type:"string",defaultValue:"字段名"},
			      {name:"name",caption:"英文名",type:"string",defaultValue:""},
		          {name:"height",caption:"高度",type:"int",defaultValue:300},
		          {name:"width",caption:"宽度",type:"int",defaultValue:300}
		]
	};

})(jQuery);
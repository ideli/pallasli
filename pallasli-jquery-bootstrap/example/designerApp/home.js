
 
	(function($) {
		shineyue.ajax("/component/listComponentTypes", "get", {}, function(result){ 
			shineyue.fields=result;
			console.log("rsult",shineyue.fields)
		});
		
		
		
		
		var loadMenu=function($parent,result){
			for(var i =0 ; i<result.length ; i++){
				var caption=result[i].caption;
				var id=result[i].id;
				var icon_cls=result[i].icon_cls;
				var sourceName=result[i].sourceName; 
				if(result[i].children&&result[i].children.length>0){
					var ul = $(
							' <li ><a herf="javascript:void(0)">'
									+ caption
									+ '</a> <ul class="page-sidebar-menu nav-stacked" style="text-align:left;"> </ul></li> ');
							
					var children=result[i].children;
					loadMenu(ul.find("ul"),children);
					ul.appendTo($parent);
				}else{
					var a = $(
							'<li ><a href="javascript:void(0)"><i class="' +  icon_cls
								+ '"></i>&nbsp;'
									+ caption + '</a> </li>').appendTo(
											$parent ).attr("source_name",
													sourceName);
					$(a).find("a").attr("source_name", sourceName);
				}
			    
			}
			$(".page-left-menu ul   ul li a").click(function(a) {
				var source_name = $(this).attr("source_name");
				 console.log(source_name)
				shineyue.goToHtml(source_name);
return;
				var lis = $(".page-sidebar-menu li");
				for (var i = 0; i < lis.length; i++) {
					if ($(lis[i]).attr("source_name") == source_name) {
						$(lis[i]).addClass("active");
					} else {
						$(lis[i]).removeClass("active");
					}
				}

			});
		}
		shineyue.ajax("/menu/loadMenu", "get", {}, function(result){
			console.log(result);
			loadMenu($(".page-left-menu .nav"),result);
return;
			for ( var key in modelResourceJson) {
				var modelResourceArray = modelResourceJson[key];

				for ( var mkey in modelJson) {
					if (mkey == key) {
						menuDatas[menuDatas.length] = {
							text : modelJson[mkey].view_name
						};
						menuDatas[menuDatas.length - 1].items = [];
					}
				}

				for (var i = 0; i < modelResourceArray.length; i++) {
					menuDatas[menuDatas.length - 1].items.push({
						text : modelResourceArray[i].view_name,
						source_name : modelResourceArray[i].view_key,
						js_url : modelResourceArray[i].view_url + ".js",
						icon_cls : modelResourceArray[i].icon_cls,
						html_url : modelResourceArray[i].view_url + ".html"
					});
				}
				for (var i = 0; i < resourceJson.length; i++) {
					shineyue.resouces.push({
						source_name : resourceJson[i].view_key,
						js_url : resourceJson[i].view_url + ".js",
						html_url : resourceJson[i].view_url + ".html"
					});
				}
			} 

			$("#btn-page-add").click(function(e){ 
				var msgBox = shineyue.find("mymodal-dialog").modal("show");
				msgBox.find(".modal-title").text("增加");
				msgBox.find(".modal-body").html(""); 
				shineyue.loadHtml(msgBox.find(".modal-body"), "page-add.html", "page-add.js");
				
			});

			for (var j = 0; j < menuDatas.length; j++) {
				var menuData = menuDatas[j].items;

				var ul = $(
						'<ul class="nav nav-list"> <li ><a herf="javascript:void(0)">'
								+ menuDatas[j].text
								+ '</a> <ul class="page-sidebar-menu nav-stacked" style="text-align:left;"> </ul></li> </ul> ')
						.appendTo($(".page-left-menu"));

				for (var i = 0; i < menuData.length; i++) {
					var a = $(
							'<li ><a href="javascript:void(0)"><i class="' + menuData[i].icon_cls
								+ '"></i>&nbsp;'
									+ menuData[i].text + '</a> </li>').appendTo(
							ul.find(".page-sidebar-menu")).attr("source_name",
							menuData[i]["source_name"]);
					$(a).find("a").attr("source_name", menuData[i]["source_name"]);
				}
			}

			$(".page-sidebar-menu li a").click(function(a) {

				var source_name = $(this).attr("source_name");
				shineyue.goToHtml(source_name);

				var lis = $(".page-sidebar-menu li");
				for (var i = 0; i < lis.length; i++) {
					if ($(lis[i]).attr("source_name") == source_name) {
						$(lis[i]).addClass("active");
					} else {
						$(lis[i]).removeClass("active");
					}
				}

			});

			 
		});


	}(jQuery));
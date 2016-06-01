var shineyue = shineyue || {};
(function($) {

	/** ************消息框开始***************** */
	shineyue.okbtn = '<button type="button" class="btn btn-default" data-dismiss="modal">确定</button>';
	shineyue.cancelbtn = '<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>';

	shineyue.msgIcon = {
		info : "img/icon-info.gif",
		warning : "img/icon-warning.gif",
		error : "img/icon-error.gif",
		question : "img/icon-question.gif"
	};
	shineyue.modalMsgBody = "<div class='row'>"
			+ "<div  class='col-sm-4 col-md-2'><img src='{0}'></div>"
			+ "<div  class='col-sm-7 col-md-9''><p> {1} </p></div>" + "</div>";

	shineyue.showMsg = function(param) {
		var msgBox = shineyue.find("mymodal-data-msg").modal("show");
		msgBox.find(".modal-title").text(param.title);
		msgBox.find(".modal-body").html("");
		msgBox.find(".modal-body").append(
				$(shineyue.modalMsgBody.replace("{0}", shineyue.msgIcon.info)
						.replace("{1}", param.msg)));
		msgBox.find(".modal-footer").html(shineyue.okbtn);
	};

	shineyue.showWarn = function(param) {
		var msgBox = shineyue.find("mymodal-data-warn").modal("show");

		msgBox.find(".modal-title").text(param.title);
		msgBox.find(".modal-body").html("");
		msgBox.find(".modal-body").append(
				$(shineyue.modalMsgBody
						.replace("{0}", shineyue.msgIcon.warning).replace(
								"{1}", param.msg)));
		msgBox.find(".modal-footer").html(shineyue.okbtn);
	};

	shineyue.showError = function(param) {
		var msgBox = shineyue.find("mymodal-data-error").modal("show");

		msgBox.find(".modal-title").text(param.title);
		msgBox.find(".modal-body").html("");
		msgBox.find(".modal-body").append(
				$(shineyue.modalMsgBody.replace("{0}", shineyue.msgIcon.error)
						.replace("{1}", param.msg)));
		msgBox.find(".modal-footer").html(shineyue.okbtn);
	};

	shineyue.showConfirm = function(param) {
		var msgBox = shineyue.find("mymodal-data-confirm").modal("show");

		msgBox.find(".modal-title").text(param.title);
		msgBox.find(".modal-body").html("");
		msgBox.find(".modal-body").append(
				$(shineyue.modalMsgBody.replace("{0}",
						shineyue.msgIcon.question).replace("{1}", param.msg)));
		msgBox.find(".modal-footer").html(shineyue.okbtn + shineyue.cancelbtn);
		msgBox.find(".btn-default").click(function(e) {
			var btn = 0;
			if (e.target.innerHTML == "确定") {
				btn = 1;
			}
			msgBox.hide();
			param.fn && param.fn(btn);
		});
	};

	shineyue.showInput = function(param) {
		var msgBox = shineyue.find("mymodal-data-input").modal("show");

		msgBox.find(".modal-title").text(param.title);

		msgBox.find(".modal-body").html("");

		for (var i = 0; i < param.items.length; i++) {
			var item = param.items[i];
			msgBox.find(".modal-body")
					.append(
							$('<div class="row" style="padding-top:5px;">'
									+ '<div class="col-sm-4 col-md-2" >'
									+ item.fieldLabel + ':</div>'
									+ '<div class="col-sm-7 col-md-9 ">'
									+ '<input name="' + item.name + '" type="'
									+ (item.type || "text")
									+ '" class="form-control" />' + '</div>'
									+ '</div>'));
		}

		msgBox.find(".modal-footer").html(shineyue.okbtn + shineyue.cancelbtn);
		msgBox.find(".btn-default").click(
				function(e) {
					var data = {};
					for (var i = 0; i < param.items.length; i++) {
						var item = param.items[i];
						data[item.name] = msgBox.find(
								"input[name=" + item.name + "]").val();
					}

					var btn = 0;
					if (e.target.innerText == "确定") {
						btn = 1;
					}
					msgBox.modal("hide");
					param.fn && param.fn(btn, data);
				});
	};

	/** *************消息框结束**************** */

	/** *************加载框开始**************** */
	shineyue.Loading = false;

	shineyue.processGo = function() {
		var msgBox = shineyue.find("progress-data");
		var curPro = $(msgBox.find(".progress-bar")[0]).attr("aria-valuenow");
		curPro = parseInt(curPro);
		if (curPro + 1 > 100) {
			curPro = 1;
		} else {
			curPro += 99;
		}
		$(msgBox.find(".progress-bar")[0]).attr("aria-valuenow", curPro);
		$(msgBox.find(".progress-bar")[0]).css("width", curPro + "%");
		if (shineyue.Loading)
			setTimeout(function() {
				shineyue.processGo();
			}, 600);
	};

	// 显示加载标识
	shineyue.showLoadMask = function() {
		shineyue.find("loadmask-data").modal("show");
		shineyue.Loading = true;
	};
	// 隐藏加载标识
	shineyue.hideLoadMask = function() {
		shineyue.find("loadmask-data").modal("hide");
		shineyue.Loading = false;
	};
	// 显示进度条
	shineyue.showProgress = function() {
		var msgBox = shineyue.find("progress-data");
		$(msgBox.find(".modal-dialog")[0]).css("width", "350px");
		$(msgBox.find(".progress-bar")[0]).attr("aria-valuenow", 1);
		$(msgBox.find(".progress-bar")[0]).css("width", "1%");
		msgBox.modal("show");
		shineyue.Loading = true;
		shineyue.processGo();
	};
	// 隐藏进度条
	shineyue.hideProgress = function() {
		shineyue.find("progress-data").modal("hide");
		shineyue.Loading = false;
	};

	/** *************加载框结束**************** */

	/** *************异步交互请求开始**************** */

	// 系统错误
	shineyue._erryFunction = function(e) {
		shineyue.showError({
			"title" : "错误提示",
			msg : "服务器异常!请于管理员联系!"
		});
	};
	shineyue.ajaxSubmit = function($form, url, type, params, call) {
		$form.ajaxSubmit({
			type : type,
			url : url,
			dataType : "json",
			timeout : 5000,
			cache : false,
			success : function(result) {
				call && call(result);
			},
			error : function() {
				(call && call(result)) || shineyue._erryFunction();
			}
		});
	};
	shineyue.ajax = function(url, type, params, call) {
		$.ajax({
			url : url,
			data : params,
			type : type,
			timeout : 5000,
			cache : false,
			dataType:"json", 
//			traditional:true,
			// data:JSON.stringify(userList),
//			  contentType:"application/json",
			success : function(result) {
				call && call(result);
			},
			error : function(result) {
				(call && call(result)) || shineyue._erryFunction();
			}
		});
	};

	shineyue.request = function(url, type, params, call) {
		shineyue.ajax(url, type, params, call);
	};

	shineyue.loadFormData = function($form, params, call) {
		shineyue.showLoadMask();
		var url = shineyue.getLoadUrl($form);
		shineyue.request(url, "get", params, function(result) {
			if (result.success && result.data) {
				$form.setForm(result.data);
			}
			shineyue.hideLoadMask();
			call && call(result);
		});
	};

	shineyue.commitForm = function($form, url, call) {
		shineyue.showProgress();
		var formData = shineyue.getFormJsonData($form);
		shineyue.ajaxSubmit($form, url, "post", formData, function(result) {
			shineyue.hideProgress();
			call && call(result);
		});
	};
	shineyue.setLoadUrl = function($loadTarget, url) {
		$loadTarget.attr("loadurl", url);
	};
	shineyue.getLoadUrl = function($loadTarget) {
		return $loadTarget.attr("loadurl");
	};
	shineyue.setParams = function($loadTarget, params) {
		$loadTarget.attr("params", JSON.stringify(params));
	};
	shineyue.getParams = function($loadTarget) {
		return eval("(" + $loadTarget.attr("params") + ")");
	};
	shineyue.loadComboData = function($combo, params, call) {
		var url = shineyue.getLoadUrl($combo);
		params = params || {};
		shineyue.setParams($combo, params);
		$combo.html("");
		shineyue.showLoadMask();
		shineyue.request(url, "get", params, function(result) {
			for (var i = 0; i < result.vdMapList.length; i++) {
				$(
						'<option value="' + result.vdMapList[i].value + '">'
								+ result.vdMapList[i].display + '</option>')
						.appendTo($combo);
			}
			shineyue.hideLoadMask();
			call && call(result);
		});
	};
	shineyue.loadGridData = function($grid, params, call) {
		var url = shineyue.getLoadUrl($grid);
		params = params || {};
		shineyue.setParams($grid, params);
		shineyue.clearGridData($grid);
		if (!params.pageNum) {
			params.pageNum = 1;
		}
		if ($grid.find("select").length == 0) {
			params.pageSize = 0;
		} else {
			params.pageSize = $grid.find("select").val();
		}
		shineyue.showLoadMask();
		shineyue.request(url, "get", params, function(result) {
			shineyue.setPager($grid, result.totalcount, params.pageNum);
			shineyue.addGridData($grid, result.results);
			var gridEvents=$grid.attr("event")||{};
			for(var eventName in gridEvents){
				gridEvents[eventName](); 
			}
			shineyue.hideLoadMask();
			call && call(result);
		});
	};

	/** *********异步交互请求结束*************** */

	shineyue.resetGridRowNumberer = function($grid) {

		var trs = $grid.find("tbody").find("tr");
		var curpage = 1;
		var pageSize = 100;
		if ($grid.find("select").length > 0) {
			curpage = $grid.find(".input-go").val();
			pageSize = $grid.find("select").val();
		}
		var startNum = pageSize * (curpage - 1) + 1;
		for (var i = 0; i < trs.length; i++) {
			if (!$(trs[i]).hasClass("tbody-data-css")) {
				$(trs[i]).find(".numberer").text(startNum++);
			}
		}
	};

	shineyue.getColumns = function($grid, resultdata) {
		var columns = [];
		var ths = $grid.find("thead tr th");
		for (var index = 0; index < ths.length; index++) {
			var th = ths[index];
			columns.push([ $(th).attr("title"), $(th).attr("hidden") || "" ]);
		}
		return columns;

	};
	shineyue.setGridEditor=function($grid,title,config){
		$grid.attr("editor_"+title,config);
	};
	shineyue.createGridEditor=function(config){
		var $input = $("<input />"); 
		$input.prop("type",config.type||"text");
		for(var i in config){
			$input.prop(i,config[i]);
		}
		return $input;
	};
	
	
	shineyue.addGridRow = function($grid, row, resetRowNumber) {
		var columns = shineyue.getColumns($grid);
		var tbodydatacss = $grid.find("tbody").find(".tbody-data-css td");
		var hastbodydatacss = tbodydatacss.length > 0;
		var $tr = $("<tr></tr>");
		for (var index = 0; index < columns.length; index++) {
			var col = columns[index];
			var cls = 'class="font12"';
			var tdHtml="";
			if (hastbodydatacss) {
				if ($(tbodydatacss[index]).attr("class")) {
					cls = 'class="' + $(tbodydatacss[index]).attr("class")
							+ '"';
				}  
				tdHtml = $(tbodydatacss[index]).html(); 
			}
			if (col[0] == "numberer") {
				var $td = $('<td  class="numberer" ' + col[1] + '> </td>');
				$td.appendTo($tr);
			} else if (col[0] == "checkbox") {
				var $td = $('<td  class="checkcolumn '+$(tbodydatacss[index]).attr("class")+'" ' + col[1]
				+ '> <input type="checkbox"> </td>');
				$td.appendTo($tr);
			}  else if (col[0] == "radio") {
				var $td = $('<td  class="radiocolumn" ' + col[1]
				+ '> <input type="radio" name="radiocolumn"> </td>');
				$td.appendTo($tr);
			} else {
				var $td ;
				/**if($grid.attr("editor_"+title)){
					var $input=shineyue.createGridEditor($grid.attr("editor_"+title));
					$input.val(row[col[0]]);
					$td= $('<td ' + cls + ' ' + col[1] + '></td>');
					$input.appendTo($td);**/
				if(tdHtml&&tdHtml!="&nbsp;"){
					$td= $('<td ' + cls + ' ' + col[1] + '></td>');
					$(tdHtml).appendTo($td).val(row[col[0]]);
				}else{
					$td= $('<td ' + cls + ' ' + col[1] + '>' + row[col[0]]
					+ '</td>');
				}
				if (col[0] == "id") {
					$tr.attr("dataId", row[col[0]]);
				}
				$td.appendTo($tr);
			}
		}
		$tr.appendTo($grid.find("tbody"));
		if (resetRowNumber !== false) {
			shineyue.resetGridRowNumberer($grid);
		}
	};


	shineyue.updateGridRow = function($grid, row) {
		var columns = shineyue.getColumns($grid);
		var trs = $grid.find("tr");
		for (var j = 0; j < trs.length; j++) {
			var tr = $(trs[j]);
			if (tr.attr("dataId") == row["id"]) {
				var tds = tr.find("td");
				for (var index = 0; index < columns.length; index++) {
					var col = columns[index];
					$(tds[index]).text(row[col[0]]);
				}
				return true;
			}
		}

	};

	
	shineyue.getCheckClomnIndex=function($grid){
		var columns = shineyue.getColumns($grid); 
		for (var index = 0; index < columns.length; index++) {
			var col = columns[index];
			if (  col[0]== "checkbox"||col[0]== "radio")return index;
		} 
		return -1;
	};
	shineyue.getDataFromRow = function($grid, tr) {

		var columns = shineyue.getColumns($grid);
		var data = {};
		var tds = tr.find("td");
		for (var index = 0; index < columns.length; index++) {
			var col = columns[index];
			if (col[0] != "numberer" && col[0] != "checkbox" && col[0] != "radio"){
				if($(tds[index]).find("input").length==1){
					data[col[0]] = $(tds[index]).find("input").val();
				}else{
					data[col[0]] = $(tds[index]).text();
				}
			}
		}
		return data;
	};

	shineyue.getSelectedRowData = function($grid) {
		var checkColumnIndex=shineyue.getCheckClomnIndex($grid);
		if(checkColumnIndex==-1){
			return;
		}
		var trs = $grid.find("tr");
		for (var j = 0; j < trs.length; j++) {
			var tr = $(trs[j]);
			var tds = tr.find("td");
			if (tds.length > checkColumnIndex) {
				var checked = $(tds[checkColumnIndex]).find("input[type=checkbox]").prop("checked");
				if (checked) {
					return shineyue.getDataFromRow($grid, tr);
				}

				var checked = $(tds[checkColumnIndex]).find("input[type=radio]").prop("checked");
				if (checked) {
					return shineyue.getDataFromRow($grid, tr);
				}
				
			}
		}
	};
	shineyue.getSelectedRowsData = function($grid) {
		var checkColumnIndex=shineyue.getCheckClomnIndex($grid);
		if(checkColumnIndex==-1){
			return;
		}
		var trs = $grid.find("tr");
		var rows = [];
		for (var j = 0; j < trs.length; j++) {
			var tr = $(trs[j]);
			var tds = tr.find("td");
			if (tds.length > checkColumnIndex) {
				var checked = $(tds[checkColumnIndex]).find("input[type=checkbox]").prop("checked");
				if (checked) {
					rows[rows.length] = shineyue.getDataFromRow($grid, tr);
				}
			}
		}
		return rows;
	};
	shineyue.selectRow = function($grid, id) {
		var checkColumnIndex=shineyue.getCheckClomnIndex($grid);
		if(checkColumnIndex==-1){
			return;
		}
		var trs = $grid.find("tr");
		for (var j = 0; j < trs.length; j++) {
			var tr = $(trs[j]);
			if (tr.attr("dataId") == id) {
				var tds = tr.find("td");
				if (tds.length > checkColumnIndex) {
					$(tds[checkColumnIndex]).find("input[type=checkbox]").prop("checked",true);
				}
				return;
			}
		}
	};
	shineyue.selectAllRows = function($grid) {
		var checkColumnIndex=shineyue.getCheckClomnIndex($grid);
		if(checkColumnIndex==-1){
			return;
		}
		var trs = $grid.find("tr");
		for (var j = 0; j < trs.length; j++) {
			var tr = $(trs[j]);
			var tds = tr.find("td");
			if (tds.length > checkColumnIndex) {
				$(tds[checkColumnIndex]).find("input[type=checkbox]").prop("checked",true);
			}
		}
	};
	shineyue.deselectRow = function($grid, id) {
		var checkColumnIndex=shineyue.getCheckClomnIndex($grid);
		if(checkColumnIndex==-1){
			return;
		}
		var trs = $grid.find("tr");
		for (var j = 0; j < trs.length; j++) {
			var tr = $(trs[j]);
			if (tr.attr("dataId") == id) {
				var tds = tr.find("td");
				if (tds.length > checkColumnIndex) {
					$(tds[checkColumnIndex]).find("input[type=checkbox]").prop("checked",false);
				}
				return;
			}
		}
	};
	shineyue.deselectAllRows = function($grid) {
		var checkColumnIndex=shineyue.getCheckClomnIndex($grid);
		if(checkColumnIndex==-1){
			return;
		}
		var trs = $grid.find("tr");
		for (var j = 0; j < trs.length; j++) {
			var tr = $(trs[j]);
			var tds = tr.find("td");
			if (tds.length > checkColumnIndex) {
				$(tds[checkColumnIndex]).find("input[type=checkbox]").prop("checked",false);
			}
		}
	};
	
	shineyue.initCheckGrid=function($grid){
		$grid.find("th input[type=checkbox]").click(function(e){
			if(e.target.checked){
				shineyue.selectAllRows($grid);
				
			}else{
				shineyue.deselectAllRows($grid);
			}
		});
	};
	

	shineyue.addOrUpdateGridRow = function($grid, row) {
		shineyue.updateGridRow($grid, row) || shineyue.addGridRow($grid, row);
	};
	shineyue.registerGridEvent =function($grid,eventName,fn){
		var gridEvents=$grid.attr("event")||{};
		gridEvents[eventName]=fn; 
	};
	shineyue.removeGridRow = function($grid, row) {
		var trs = $grid.find("tr");
		for (var j = 0; j < trs.length; j++) {
			var tr = $(trs[j]);
			if (tr.attr("dataId") == row["id"]) {
				tr.remove();
				return;
			}
		}

	};
	shineyue.setPager = function($grid, totalcount, curpage) {
		if (totalcount && totalcount > 0 && $grid.find("select").length == 0) {
			shineyue.addPager($grid, null, totalcount, 1);
		} else {
			shineyue.alterPager($grid, totalcount, curpage);
		}
	};

	shineyue.goGridPage = function($grid, pageNum) {
		var params = shineyue.getParams($grid);
		params.pageNum = pageNum;
		shineyue.loadGridData($grid, params);
	};
	shineyue.addPager = function($grid, $select, totalcount, curpage,display) {
		var $tfoot = $("<tfoot></tfoot>");
		if(display){$tfoot.css("display",display);}
		var $tr = $("<tr></tr>");
		var $td = "";
		var columns = shineyue.getColumns($grid);
		if ($select) {
			var totalPage = Math.ceil(totalcount / $select.val());
			$td = $('<td colspan="'
					+ columns.length
					+ '">'
					+ '   <ul class="pageNo col-sm-5 pagination pagination-sm" style="margin:0px;">'
					+ '   </ul>'
					+ '   <div class="col-sm-5"> <span class="left" style="width:20%;">'
					+ '    <input type="number" class="form-control input-go" value='
					+ curpage
					+ ' />'
					+ '   </span> <span class="left space-mleft10">'
					+ '   <button type="button" class="btn-sm btn-go"></button>'
					+ '   </span> <span class="pagesize left space-mleft10">共'
					+ totalPage + '页&nbsp;&nbsp;每页显示' + '</span> </span> '
					+ '<span class="left space-mleft10">' + '共' + totalcount
					+ '条记录' + '</span></div>' + '</td>');
			if (curpage > 1)
				$('<li><a href="javascript:void(0)">&laquo;</a></li>')
						.appendTo($td.find(".pageNo")).click(function(a, b) {
							shineyue.goGridPage($grid, curpage - 1);
						});
			var i = 1;
			if (totalPage > 10) {
				i = (curpage - 9) < 1 ? 1 : curpage - 9;
			}
			for (var j = 1; i <= totalPage && j <= 10; i++, j++) {
				/***************************************************************
				 * if(totalPage>10){ if(j==9&&i<totalPage){ (function(pageNum,
				 * $grid) { var css = (curpage == pageNum) ? "style='color:red'" :
				 * ""; $( '
				 * <li><a ' + css + ' href="javascript:void(0)" >' + "..." + '</a></li>
				 * ').attr("num", pageNum).appendTo($td.find(".pageNo")).click(
				 * function(a, b) { shineyue.goGridPage($grid, pageNum); });
				 * })(i, $grid); }else if(j==10){ (function(pageNum, $grid) {
				 * var css = (curpage == pageNum) ? "style='color:red'" : ""; $( '
				 * <li><a ' + css + ' href="javascript:void(0)" >' + pageNum+ '</a></li>
				 * ').attr("num", pageNum).appendTo($td.find(".pageNo")).click(
				 * function(a, b) { shineyue.goGridPage($grid, pageNum); });
				 * })(totalPage, $grid); }else { (function(pageNum, $grid) { var
				 * css = (curpage == pageNum) ? "style='color:red'" : ""; $( '
				 * <li><a ' + css + ' href="javascript:void(0)" >' + pageNum + '</a></li>
				 * ').attr("num", pageNum).appendTo($td.find(".pageNo")).click(
				 * function(a, b) { shineyue.goGridPage($grid, pageNum); });
				 * })(i, $grid); } }else{
				 **************************************************************/
				(function(pageNum, $grid) {
					var css = (curpage == pageNum) ? "style='color:red'" : "";
					$(
							'<li><a ' + css + ' href="javascript:void(0)" >'
									+ pageNum + '</a></li>').attr("num",
							pageNum).appendTo($td.find(".pageNo")).click(
							function(a, b) {
								shineyue.goGridPage($grid, pageNum);
							});
				})(i, $grid);
				// }
			}

			if (curpage < totalPage) {
				$('<li><a href="javascript:void(0)">&raquo;</a></li>')
						.appendTo($td.find(".pageNo")).click(function(a, b) {
							shineyue.goGridPage($grid, curpage + 1);
						});
			}
			$td.find(".btn-go").click(
					function() {
						if ($td.find(".input-go").val() > 0
								&& $td.find(".input-go").val() <= totalPage) {
							shineyue.goGridPage($grid, $td.find(".input-go")
									.val());
						} else {
							shineyue.showWarn({
								title : "提示",
								msg : "页码输入错误"
							});
						}
					});
			$select.appendTo($td.find(".pagesize")).change(function() {
				shineyue.goGridPage($grid, 1);
			});
		} else {
			$td = $('<td colspan="21">'
					+ '   <ul class="pageNo col-sm-3 pagination pagination-sm" style="margin:0px;">'
					+ '   </ul>'
					+ '   <div class="col-sm-3"> <span class="left" style="width:20%;">'
					+ '   </span> <span class="left space-mleft10">'
					+ '   </span> <span class="pagesize left space-mleft10"> '
					+ '</span> </span> ' + '<span class="left space-mleft10">'
					+ '共' + totalcount + '条记录' + '</span></div>' + '</td>');
		}
		$td.appendTo($tr);
		$tr.appendTo($tfoot);
		$tfoot.appendTo($grid);
	};
	shineyue.alterPager = function($grid, totalcount, curpage) {
		var $select = $grid.find("select");
		var display=$grid.find("tfoot").css("display");
		$grid.find("tfoot").remove();
		shineyue.addPager($grid, $select, totalcount, curpage,display);
	};

	shineyue.addGridData = function($grid, resultdata) {
		resultdata = resultdata || [];
		for (var index = 0; index < resultdata.length; index++) {
			var row = resultdata[index];
			shineyue.addGridRow($grid, row, false);
		}
		shineyue.resetGridRowNumberer($grid);
	};
	shineyue.clearGridData = function($grid) {
		var tbodydatacss = $grid.find("tbody").find(".tbody-data-css");
		$grid.find("tbody").empty();
		if (tbodydatacss.length > 0)
			$grid.find("tbody").append(tbodydatacss);
	};

	/** ********页面跳转开始********** */
	shineyue.goToHtml = shineyue.loadHtmlForWorkshop = function(sourceName) {
		var source = shineyue.getSource(sourceName);
		shineyue.find("dqwz").attr("currentPageSource", sourceName);
		shineyue.loadHtml(shineyue.find("shineyue-workshop"), source.html_url,
				source.js_url, source.nav_path||"kk");
	};

	shineyue.setCurrentLocation = function(location) {
		$(".dqwz").html("");
		var locations = location.split("/");
		$('<img src="img/dqwz_tb.jpg">当前位置：网上大厅<span></span>').appendTo(
				$(".dqwz"));
		for (var i = 0; i < locations.length; i++) {
			var textAndUrl = locations[i].split("?");
			$("<span>>></span>").appendTo($(".dqwz"));
			if (textAndUrl.length > 1) {
				(function(textAndUrl) {
					var node = $(
							"<a href='javascript:void(0)'>" + textAndUrl[0]
									+ "</a>").appendTo($(".dqwz"));
					node.click(function() {
						shineyue.loadHtmlForWorkshop(textAndUrl[1]);
					});

				})(textAndUrl);

			} else {
				var node = $("<span>" + textAndUrl[0] + "</span>");
				node.appendTo($(".dqwz"));
			}
		}
	};

	shineyue.loadHtml = function($div, html_url, js_url) {
		$.get(html_url, function(result) {
			$div.html(result);
			if (js_url && js_url.trim() != "") {
				$.ajax({
					url : js_url,
					dataType : "script",
					success : function(data, success) {
					},
					error : function(me, errorType, error) {
						var result = {
							success : false,
							msg : errorType + ":" + error.message,
							error : error
						};
					}
				});
			}
		});

	};

	/** *********页面跳转结束********* */

	/** *********页面临时数据缓存处理开始********* */
	shineyue.tmpCache = {};
	shineyue.getPageSourceName = function() {
		return shineyue.find("dqwz").attr("currentPageSource");
	};

	shineyue.initPageCache = function() {
		shineyue.tmpCache[shineyue.getPageSourceName()] = {};
	};

	shineyue.setCache = function(name, data) {
		shineyue.tmpCache[name] = data;
	};

	shineyue.getCache = function(name) {
		return shineyue.tmpCache[name];
	};

	shineyue.clearCache = function(name) {
		if (name === true) {
			shineyue.tmpCache = {};
		} else {
			shineyue.tmpCache[name] = null;
		}
	};

	/** *********页面临时数据缓存处理结束********* */
	shineyue.findElement = function(exp) {
		var eles = $(exp);
		return eles.length ? eles : null;
	};
	shineyue.findElementByClass = function(cls) {
		var eles = $("." + cls);
		return eles.length ? eles : null;
	};
	shineyue.findElementById = function(id) {
		var eles = $("#" + id);
		return eles.length ? eles : null;
	};
	shineyue.findElementByName = function(name) {
		var eles = $("[name=" + name + "]");
		return eles.length ? eles : null;
	};

	shineyue.find = function(idOrNameOrCls) {
		return shineyue.findElementByClass(idOrNameOrCls)
				|| shineyue.findElementById(idOrNameOrCls)
				|| shineyue.findElementByName(idOrNameOrCls)
				|| shineyue.findElement(idOrNameOrCls);
	};

	shineyue.getFormJsonData = function($form) {
		return shineyue.convertToJson($form.serializeArray());
	};

	shineyue.validateForm = function($form) {
		return $form.validate().form();
	};

	shineyue.val = function($ele, text) {
		if (!$ele)
			return;
		var tagName = $ele[0].tagName;
		if (tagName == "LABEL" || tagName == "SPAN") {
			if (text !== undefined) {
				$ele.text(text);
			} else {
				return $ele.text();
			}
			return;
		}

		var type = $ele[0].type;

		if (type == "radio") {
			if (text !== undefined) {
				for (var i = 0; i < $ele.length; i++) {
					var ele = $ele[i];
					if (ele.value == text)
						ele.checked = true;
				}
			} else {
				var checkedVal = null;
				for (var i = 0; i < $ele.length; i++) {
					var ele = $ele[i];
					if (ele.checked)
						checkedVal = ele.value;
				}
				return checkedVal;
			}
		} else if (type == "checkbox") {
			if (text !== undefined) {
				for (var i = 0; i < $ele.length; i++) {
					var ele = $ele[i];
					for (var j = 0; j < text.length; j++) {
						if (ele.value == text[j])
							ele.checked = true;
					}
				}
			} else {
				var checkedVal = [];
				for (var i = 0; i < $ele.length; i++) {
					var ele = $ele[i];
					if (ele.checked)
						checkedVal[checkedVal.length] = ele.value;
				}
				return checkedVal;
			}
		} else {
			if (text !== undefined) {
				$ele.val(text);
			} else {
				return $ele.val();
			}
		}

	};
	shineyue.getField = function(name) {
		return shineyue.find(name);
	};
	shineyue.initValidate = function($form, params) {
		$form.validate({
			onfocusout : function(element) {
				$(element).valid();
			},
			rules : params.rules,
			messages : params.messages
		});
	};

	shineyue.upload = function(url, type, params, fileElementId, call) {
		var data = new FormData();
		data.append('jbr', params.jbr);
		data.append('uploadfile', shineyue.find('uploadfile')[0].files[0]);
		jQuery.ajax({
			url : 'unit/upload',
			type : 'POST',
			data : data,
			processData : false,
			contentType : false
		}).done(function(ret) {
			if (ret) {
				alert(ret);
			} else {
				alert('保存成功！');
				// location = '__URL__';
			}
		});
	};
	shineyue.download = function(url, type, params, call) {
		shineyue.ajax("unit/download", "get", {
			jbr : "jbr"
		}, function(r) {
		});
	};
	shineyue.resetUploadFile = function($file, previewId) {
		var $preview = shineyue.find(previewId||"preview");
		if($preview.length>0){
			$preview.empty();
			$file.after($file.clone().val(""));
			$file.remove();
		}
	};
	shineyue.showUploadImage = function(source,previewId) {
		var $preview = shineyue.find(previewId||"preview");
		var files = source.files;
		  files=filter(files);
		if(files.length==0){
			 return;
		};
		var html = '';
		$preview.html('<div class="upload_loading"></div>');

		function filter(files) {
			var arrFiles = [];
			for (var i = 0, file; file = files[i]; i++) {
				if (file.type.indexOf("image") == 0) {
					if (file.size >= 512000) {
						alert('您这张"' + file.name + '"图片大小过大，应小于500k');
					} else {
						arrFiles.push(file);
					}
					;
				} else {
					alert('文件"' + file.name + '"不是图片。');
				}
				;
			}
			return arrFiles;
		}
		var funAppendImage = function(file) {
			var reader = new FileReader();
			reader.onload = function(e) {
				html = html
						+ '<div id="uploadList_'
						+ i
						+ '" class="upload_append_list"><p><strong>'
						+ file.name
						+ '</strong>'
						//+ '<a href="javascript:" class="upload_delete" title="删除" data-index="'
						//+ i + '">删除</a><br />' 
						+ '<img id="uploadImage_' + i
					    + '" src="' + e.target.result
						+ '" class="upload_image" />'
						+'</p>'
						+ '<span id="uploadProgress_' + i
						+ '" class="upload_progress"></span>' + '</div>';
				$preview.html(html);
			};
			reader.readAsDataURL(file);
		};
		for (var i = 0; i < files.length; i++) {

			file = files[i];
			funAppendImage(file);
		}

	};

	shineyue.append = function($parent, $templete, $data) {
		if ($.isArray($data)) {
			if ($data.length > 0 && !$.isArray($data[0])) {
				$data = [ $data ];
			}

			for (var i = 0; i < $data.length; i++) {
				var tmp = $templete;
				if ($.isArray($data[i])) {
					for (var j = 0; j < $data[i].length; j++) {
						tmp = tmp.replace("{" + j + "}", $data[i][j]);
					}
				}
				$parent.append($(tmp));
			}
		}
	};

	shineyue.convertToJson = function(formValues) {
		var result = {};
		for (var formValue, j = 0; j < formValues.length; j++) {
			formValue = formValues[j];
			var name = formValue.name;
			var value = formValue.value;
			if (name.indexOf('.') < 0) {
				result[name] = value;
				continue;
			} else {
				var simpleNames = name.split('.');
				// 构建命名空间
				var obj = result;
				for (var i = 0; i < simpleNames.length - 1; i++) {
					var simpleName = simpleNames[i];
					if (simpleName.indexOf('[') < 0) {
						if (obj[simpleName] == null) {
							obj[simpleName] = {};
						}
						obj = obj[simpleName];
					} else { // 数组
						// 分隔
						var arrNames = simpleName.split('[');
						var arrName = arrNames[0];
						var arrIndex = parseInt(arrNames[1]);
						if (obj[arrName] == null) {
							obj[arrName] = []; // new Array();
						}
						obj = obj[arrName];
						multiChooseArray = result[arrName];
						if (obj[arrIndex] == null) {
							obj[arrIndex] = {}; // new Object();
						}
						obj = obj[arrIndex];
					}
					;
				}

				if (obj[simpleNames[simpleNames.length - 1]]) {
					var temp = obj[simpleNames[simpleNames.length - 1]];
					obj[simpleNames[simpleNames.length - 1]] = temp;
				} else {
					obj[simpleNames[simpleNames.length - 1]] = value;
				}

			}
		}
		return result;
	};
	shineyue.resouces = [];
	shineyue.getSource = function(sourceName) {
		for ( var i in shineyue.resouces) {
			if (shineyue.resouces[i].source_name == sourceName) {
				return shineyue.resouces[i];
			}
		}
		return;
	};
	shineyue.initComboData=function(id, url, call){
		var $parentModuleList=shineyue.find(id);
		shineyue.setLoadUrl($parentModuleList, url);
		shineyue.loadComboData($parentModuleList, {}, call);
	};
})(jQuery);
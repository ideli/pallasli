var pallasli = pallasli || {};
(function($) {

	/** ************消息框开始***************** */
	pallasli.okbtn = '<button type="button" class="btn btn-default" data-dismiss="modal">确定</button>';
	pallasli.cancelbtn = '<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>';

	pallasli.msgIcon = {
		info : "img/icon-info.gif",
		warning : "img/icon-warning.gif",
		error : "img/icon-error.gif",
		question : "img/icon-question.gif"
	};
	pallasli.modalMsgBody = "<div class='row'>"
			+ "<div  class='col-sm-4 col-md-2'><img src='{0}'></div>"
			+ "<div  class='col-sm-7 col-md-9''><p> {1} </p></div>" + "</div>";

	pallasli.showMsg = function(param) {
		var msgBox = pallasli.find("mymodal-data-msg").modal("show");
		msgBox.find(".modal-title").text(param.title);
		msgBox.find(".modal-body").html("");
		msgBox.find(".modal-body").append(
				$(pallasli.modalMsgBody.replace("{0}", pallasli.msgIcon.info)
						.replace("{1}", param.msg)));
		msgBox.find(".modal-footer").html(pallasli.okbtn);
	};

	pallasli.showWarn = function(param) {
		var msgBox = pallasli.find("mymodal-data-warn").modal("show");

		msgBox.find(".modal-title").text(param.title);
		msgBox.find(".modal-body").html("");
		msgBox.find(".modal-body").append(
				$(pallasli.modalMsgBody
						.replace("{0}", pallasli.msgIcon.warning).replace(
								"{1}", param.msg)));
		msgBox.find(".modal-footer").html(pallasli.okbtn);
	};

	pallasli.showError = function(param) {
		var msgBox = pallasli.find("mymodal-data-error").modal("show");

		msgBox.find(".modal-title").text(param.title);
		msgBox.find(".modal-body").html("");
		msgBox.find(".modal-body").append(
				$(pallasli.modalMsgBody.replace("{0}", pallasli.msgIcon.error)
						.replace("{1}", param.msg)));
		msgBox.find(".modal-footer").html(pallasli.okbtn);
	};

	pallasli.showConfirm = function(param) {
		var msgBox = pallasli.find("mymodal-data-confirm").modal("show");

		msgBox.find(".modal-title").text(param.title);
		msgBox.find(".modal-body").html("");
		msgBox.find(".modal-body").append(
				$(pallasli.modalMsgBody.replace("{0}",
						pallasli.msgIcon.question).replace("{1}", param.msg)));
		msgBox.find(".modal-footer").html(pallasli.okbtn + pallasli.cancelbtn);
		msgBox.find(".btn-default").click(function(e) {
			var btn = 0;
			if (e.target.innerHTML == "确定") {
				btn = 1;
			}
			msgBox.hide();
			param.fn && param.fn(btn);
		});
	};

	pallasli.showInput = function(param) {
		var msgBox = pallasli.find("mymodal-data-input").modal("show");

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

		msgBox.find(".modal-footer").html(pallasli.okbtn + pallasli.cancelbtn);
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
	pallasli.Loading = false;

	pallasli.processGo = function() {
		var msgBox = pallasli.find("progress-data");
		var curPro = $(msgBox.find(".progress-bar")[0]).attr("aria-valuenow");
		curPro = parseInt(curPro);
		if (curPro + 1 > 100) {
			curPro = 1;
		} else {
			curPro += 99;
		}
		$(msgBox.find(".progress-bar")[0]).attr("aria-valuenow", curPro);
		$(msgBox.find(".progress-bar")[0]).css("width", curPro + "%");
		if (pallasli.Loading)
			setTimeout(function() {
				pallasli.processGo();
			}, 600);
	};

	// 显示加载标识
	pallasli.showLoadMask = function() {
		pallasli.find("loadmask-data").modal("show");
		pallasli.Loading = true;
	};
	// 隐藏加载标识
	pallasli.hideLoadMask = function() {
		pallasli.find("loadmask-data").modal("hide");
		pallasli.Loading = false;
	};
	// 显示进度条
	pallasli.showProgress = function() {
		var msgBox = pallasli.find("progress-data");
		$(msgBox.find(".modal-dialog")[0]).css("width", "350px");
		$(msgBox.find(".progress-bar")[0]).attr("aria-valuenow", 1);
		$(msgBox.find(".progress-bar")[0]).css("width", "1%");
		msgBox.modal("show");
		pallasli.Loading = true;
		pallasli.processGo();
	};
	// 隐藏进度条
	pallasli.hideProgress = function() {
		pallasli.find("progress-data").modal("hide");
		pallasli.Loading = false;
	};

	/** *************加载框结束**************** */

	/** *************异步交互请求开始**************** */

	// 系统错误
	pallasli._erryFunction = function(e) {
		pallasli.showError({
			"title" : "错误提示",
			msg : "服务器异常!请于管理员联系!"
		});
	};
	pallasli.ajaxSubmit = function($form, url, type, params, call) {
		$form.ajaxSubmit({
			type : type,
			url : url,
			dataType : "json",
			timeout : 5000,
			cache : false,
			success : function(result) {
				call && call(result);
			},
			error : function(result) {
				(call && call(result)) || pallasli._erryFunction();
			}
		});
	};
	pallasli.ajax = function(url, type, params, call) {
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
				(call && call(result)) || pallasli._erryFunction();
			}
		});
	};

	pallasli.request = function(url, type, params, call) {
		pallasli.ajax(url, type, params, call);
	};

	pallasli.loadFormData = function($form, params, call) {
		pallasli.showLoadMask();
		var url = pallasli.getLoadUrl($form);
		pallasli.request(url, "get", params, function(result) {
			if (result.success && result.data) {
				$form.setForm(result.data);
			}
			pallasli.hideLoadMask();
			call && call(result);
		});
	};

	pallasli.commitForm = function($form, url, call) {
		pallasli.showProgress();
		var formData = pallasli.getFormJsonData($form);
		pallasli.ajaxSubmit($form, url, "post", formData, function(result) {
			pallasli.hideProgress();
			call && call(result);
		});
	};
	pallasli.setLoadUrl = function($loadTarget, url) {
		$loadTarget.attr("loadurl", url);
	};
	pallasli.getLoadUrl = function($loadTarget) {
		return $loadTarget.attr("loadurl");
	};
	pallasli.setParams = function($loadTarget, params) {
		$loadTarget.attr("params", JSON.stringify(params));
	};
	pallasli.getParams = function($loadTarget) {
		return eval("(" + $loadTarget.attr("params") + ")");
	};
	pallasli.loadComboData = function($combo, params, call) {
		var url = pallasli.getLoadUrl($combo);
		params = params || {};
		pallasli.setParams($combo, params);
		$combo.html("");
		pallasli.showLoadMask();
		pallasli.request(url, "get", params, function(result) {
			for (var i = 0; i < result.vdMapList.length; i++) {
				$(
						'<option value="' + result.vdMapList[i].value + '">'
								+ result.vdMapList[i].display + '</option>')
						.appendTo($combo);
			}
			pallasli.hideLoadMask();
			call && call(result);
		});
	};
	pallasli.loadGridData = function($grid, params, call) {
		var url = pallasli.getLoadUrl($grid);
		params = params || {};
		pallasli.setParams($grid, params);
		pallasli.clearGridData($grid);
		if (!params.pageNum) {
			params.pageNum = 1;
		}
		if ($grid.find("select").length == 0) {
			params.pageSize = 0;
		} else {
			params.pageSize = $grid.find("select").val();
		}
		pallasli.showLoadMask();
		pallasli.request(url, "get", params, function(result) {
			pallasli.setPager($grid, result.totalcount, params.pageNum);
			pallasli.addGridData($grid, result.results);
			var gridEvents=$grid.attr("event")||{};
			for(var eventName in gridEvents){
				gridEvents[eventName](); 
			}
			pallasli.hideLoadMask();
			call && call(result);
		});
	};

	/** *********异步交互请求结束*************** */

	pallasli.resetGridRowNumberer = function($grid) {

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

	pallasli.getColumns = function($grid, resultdata) {
		var columns = [];
		var ths = $grid.find("thead tr th");
		for (var index = 0; index < ths.length; index++) {
			var th = ths[index];
			columns.push([ $(th).attr("title"), $(th).attr("hidden") || "" ]);
		}
		return columns;

	};
	pallasli.setGridEditor=function($grid,title,config){
		$grid.attr("editor_"+title,config);
	};
	pallasli.createGridEditor=function(config){
		var $input = $("<input />"); 
		$input.prop("type",config.type||"text");
		for(var i in config){
			$input.prop(i,config[i]);
		}
		return $input;
	};
	
	
	pallasli.addGridRow = function($grid, row, resetRowNumber) {
		var columns = pallasli.getColumns($grid);
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
					var $input=pallasli.createGridEditor($grid.attr("editor_"+title));
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
			pallasli.resetGridRowNumberer($grid);
		}
	};


	pallasli.updateGridRow = function($grid, row) {
		var columns = pallasli.getColumns($grid);
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

	
	pallasli.getCheckClomnIndex=function($grid){
		var columns = pallasli.getColumns($grid); 
		for (var index = 0; index < columns.length; index++) {
			var col = columns[index];
			if (  col[0]== "checkbox"||col[0]== "radio")return index;
		} 
		return -1;
	};
	pallasli.getDataFromRow = function($grid, tr) {

		var columns = pallasli.getColumns($grid);
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

	pallasli.getSelectedRowData = function($grid) {
		var checkColumnIndex=pallasli.getCheckClomnIndex($grid);
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
					return pallasli.getDataFromRow($grid, tr);
				}

				var checked = $(tds[checkColumnIndex]).find("input[type=radio]").prop("checked");
				if (checked) {
					return pallasli.getDataFromRow($grid, tr);
				}
				
			}
		}
	};
	pallasli.getSelectedRowsData = function($grid) {
		var checkColumnIndex=pallasli.getCheckClomnIndex($grid);
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
					rows[rows.length] = pallasli.getDataFromRow($grid, tr);
				}
			}
		}
		return rows;
	};
	pallasli.selectRow = function($grid, id) {
		var checkColumnIndex=pallasli.getCheckClomnIndex($grid);
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
	pallasli.selectAllRows = function($grid) {
		var checkColumnIndex=pallasli.getCheckClomnIndex($grid);
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
	pallasli.deselectRow = function($grid, id) {
		var checkColumnIndex=pallasli.getCheckClomnIndex($grid);
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
	pallasli.deselectAllRows = function($grid) {
		var checkColumnIndex=pallasli.getCheckClomnIndex($grid);
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
	
	pallasli.initCheckGrid=function($grid){
		$grid.find("th input[type=checkbox]").click(function(e){
			if(e.target.checked){
				pallasli.selectAllRows($grid);
				
			}else{
				pallasli.deselectAllRows($grid);
			}
		});
	};
	

	pallasli.addOrUpdateGridRow = function($grid, row) {
		pallasli.updateGridRow($grid, row) || pallasli.addGridRow($grid, row);
	};
	pallasli.registerGridEvent =function($grid,eventName,fn){
		var gridEvents=$grid.attr("event")||{};
		gridEvents[eventName]=fn; 
	};
	pallasli.removeGridRow = function($grid, row) {
		var trs = $grid.find("tr");
		for (var j = 0; j < trs.length; j++) {
			var tr = $(trs[j]);
			if (tr.attr("dataId") == row["id"]) {
				tr.remove();
				return;
			}
		}

	};
	pallasli.setPager = function($grid, totalcount, curpage) {
		if (totalcount && totalcount > 0 && $grid.find("select").length == 0) {
			pallasli.addPager($grid, null, totalcount, 1);
		} else {
			pallasli.alterPager($grid, totalcount, curpage);
		}
	};

	pallasli.goGridPage = function($grid, pageNum) {
		var params = pallasli.getParams($grid);
		params.pageNum = pageNum;
		pallasli.loadGridData($grid, params);
	};
	pallasli.addPager = function($grid, $select, totalcount, curpage,display) {
		var $tfoot = $("<tfoot></tfoot>");
		if(display){$tfoot.css("display",display);}
		var $tr = $("<tr></tr>");
		var $td = "";
		var columns = pallasli.getColumns($grid);
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
							pallasli.goGridPage($grid, curpage - 1);
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
				 * function(a, b) { pallasli.goGridPage($grid, pageNum); });
				 * })(i, $grid); }else if(j==10){ (function(pageNum, $grid) {
				 * var css = (curpage == pageNum) ? "style='color:red'" : ""; $( '
				 * <li><a ' + css + ' href="javascript:void(0)" >' + pageNum+ '</a></li>
				 * ').attr("num", pageNum).appendTo($td.find(".pageNo")).click(
				 * function(a, b) { pallasli.goGridPage($grid, pageNum); });
				 * })(totalPage, $grid); }else { (function(pageNum, $grid) { var
				 * css = (curpage == pageNum) ? "style='color:red'" : ""; $( '
				 * <li><a ' + css + ' href="javascript:void(0)" >' + pageNum + '</a></li>
				 * ').attr("num", pageNum).appendTo($td.find(".pageNo")).click(
				 * function(a, b) { pallasli.goGridPage($grid, pageNum); });
				 * })(i, $grid); } }else{
				 **************************************************************/
				(function(pageNum, $grid) {
					var css = (curpage == pageNum) ? "style='color:red'" : "";
					$(
							'<li><a ' + css + ' href="javascript:void(0)" >'
									+ pageNum + '</a></li>').attr("num",
							pageNum).appendTo($td.find(".pageNo")).click(
							function(a, b) {
								pallasli.goGridPage($grid, pageNum);
							});
				})(i, $grid);
				// }
			}

			if (curpage < totalPage) {
				$('<li><a href="javascript:void(0)">&raquo;</a></li>')
						.appendTo($td.find(".pageNo")).click(function(a, b) {
							pallasli.goGridPage($grid, curpage + 1);
						});
			}
			$td.find(".btn-go").click(
					function() {
						if ($td.find(".input-go").val() > 0
								&& $td.find(".input-go").val() <= totalPage) {
							pallasli.goGridPage($grid, $td.find(".input-go")
									.val());
						} else {
							pallasli.showWarn({
								title : "提示",
								msg : "页码输入错误"
							});
						}
					});
			$select.appendTo($td.find(".pagesize")).change(function() {
				pallasli.goGridPage($grid, 1);
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
	pallasli.alterPager = function($grid, totalcount, curpage) {
		var $select = $grid.find("select");
		var display=$grid.find("tfoot").css("display");
		$grid.find("tfoot").remove();
		pallasli.addPager($grid, $select, totalcount, curpage,display);
	};

	pallasli.addGridData = function($grid, resultdata) {
		resultdata = resultdata || [];
		for (var index = 0; index < resultdata.length; index++) {
			var row = resultdata[index];
			pallasli.addGridRow($grid, row, false);
		}
		pallasli.resetGridRowNumberer($grid);
	};
	pallasli.clearGridData = function($grid) {
		var tbodydatacss = $grid.find("tbody").find(".tbody-data-css");
		$grid.find("tbody").empty();
		if (tbodydatacss.length > 0)
			$grid.find("tbody").append(tbodydatacss);
	};

	/** ********页面跳转开始********** */
	pallasli.goToHtml = pallasli.loadHtmlForWorkshop = function(sourceName) {
		var source = pallasli.getSource(sourceName);
		pallasli.find("dqwz").attr("currentPageSource", sourceName);
		pallasli.loadHtml(pallasli.find("pallasli-workshop"), source.html_url,
				source.js_url, source.nav_path||"kk");
	};

	pallasli.setCurrentLocation = function(location) {
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
						pallasli.loadHtmlForWorkshop(textAndUrl[1]);
					});

				})(textAndUrl);

			} else {
				var node = $("<span>" + textAndUrl[0] + "</span>");
				node.appendTo($(".dqwz"));
			}
		}
	};

	pallasli.loadHtml = function($div, html_url, js_url,unHistory) {
		$.get(html_url, function(result) {
			$div.html(result);
			if(!unHistory)History.push({html_url:html_url,js_url:js_url,mainDiv:$div,nav:[]});
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
	pallasli.tmpCache = {};
	pallasli.getPageSourceName = function() {
		return pallasli.find("dqwz").attr("currentPageSource");
	};

	pallasli.initPageCache = function() {
		pallasli.tmpCache[pallasli.getPageSourceName()] = {};
	};

	pallasli.setCache = function(name, data) {
		pallasli.tmpCache[name] = data;
	};

	pallasli.getCache = function(name) {
		return pallasli.tmpCache[name];
	};

	pallasli.clearCache = function(name) {
		if (name === true) {
			pallasli.tmpCache = {};
		} else {
			pallasli.tmpCache[name] = null;
		}
	};

	/** *********页面临时数据缓存处理结束********* */
	pallasli.findElement = function(exp) {
		var eles = $(exp);
		return eles.length ? eles : null;
	};
	pallasli.findElementByClass = function(cls) {
		var eles = $("." + cls);
		return eles.length ? eles : null;
	};
	pallasli.findElementById = function(id) {
		var eles = $("#" + id);
		return eles.length ? eles : null;
	};
	pallasli.findElementByName = function(name) {
		var eles = $("[name=" + name + "]");
		return eles.length ? eles : null;
	};

	pallasli.find = function(idOrNameOrCls) {
		return pallasli.findElementByClass(idOrNameOrCls)
				|| pallasli.findElementById(idOrNameOrCls)
				|| pallasli.findElementByName(idOrNameOrCls)
				|| pallasli.findElement(idOrNameOrCls);
	};

	pallasli.getFormJsonData = function($form) {
		return pallasli.convertToJson($form.serializeArray());
	};

	pallasli.validateForm = function($form) {
		return $form.validate().form();
	};

	pallasli.val = function($ele, text) {
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
	pallasli.getField = function(name) {
		return pallasli.find(name);
	};
	pallasli.initValidate = function($form, params) {
		$form.validate({
			onfocusout : function(element) {
				$(element).valid();
			},
			rules : params.rules,
			messages : params.messages
		});
	};

	pallasli.upload = function(url, type, params, fileElementId, call) {
		var data = new FormData();
		data.append('jbr', params.jbr);
		data.append('uploadfile', pallasli.find('uploadfile')[0].files[0]);
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
	pallasli.download = function(url, type, params, call) {
		pallasli.ajax("unit/download", "get", {
			jbr : "jbr"
		}, function(r) {
		});
	};
	pallasli.resetUploadFile = function($file, previewId) {
		var $preview = pallasli.find(previewId||"preview");
		if($preview.length>0){
			$preview.empty();
			$file.after($file.clone().val(""));
			$file.remove();
		}
	};
	pallasli.showUploadImage = function(source,previewId) {
		var $preview = pallasli.find(previewId||"preview");
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

	pallasli.append = function($parent, $templete, $data) {
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

	pallasli.convertToJson = function(formValues) {
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
	pallasli.resouces = [];
	pallasli.getSource = function(sourceName) {
		for ( var i in pallasli.resouces) {
			if (pallasli.resouces[i].source_name == sourceName) {
				return pallasli.resouces[i];
			}
		}
		return;
	};
	pallasli.initComboData=function(id, url, call){
		var $parentModuleList=pallasli.find(id);
		pallasli.setLoadUrl($parentModuleList, url);
		pallasli.loadComboData($parentModuleList, {}, call);
	};
})(jQuery);
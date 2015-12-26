/**
 * 
 */
Pallasli.panel.Grid = function(cfg) {
	this.tmpl = '<div id={0} class="table-padding" style="{2}" >'
			+ '<div class="panel-title">{1}</div>'
			+ '<div class="panel-heading"> </div>'
			+ '<div class="panel-body hidden">'
			+ '</div> '
			+ '<div class="table-responsive">'
			+ '<table class="table table-condensed table-hover table-bordered table-striped"> '
			+ '<thead> ' + '<tr class="group table-header hidden"></tr> '
			+ '<tr class="header table-header"></tr> ' + '</thead> '
			+ '<tbody> ' + '</tbody>' + '</table>' + '</div> '
			+ '<div class="panel-footer"> </div>' + '</div>';
	this.dataOrder = [ "id", "title", "style" ];
	this.columns = [];
	Pallasli.panel.Panel.prototype.initialize.call(this, cfg);
};
Pallasli.alias["grid"] = Pallasli.panel.Grid;
$
		.extend(
				Pallasli.panel.Grid.prototype,
				Pallasli.panel.Panel.prototype,
				{
					initComponent : function(cfg) {
						var me = this;
						if (cfg.width)
							me.style = "width:" + cfg.width + "px";
					},
					load : function(url) {
						var me=this;
						$.ajax({
							url : url,
							dataType : "json",
							// params:{},
							success : function(data, success) {
								console.log(data);
								me.setGridData(data);
							},
							error : function(me, errorType, error) {
								var result = {
									success : false,
									msg : errorType + ":" + error.message,
									error : error
								};
								console.log(result);
							}
						});
					},
					setColumns : function() {
						return this.columns;

					},
					createRow : function() {

					},
					getColumns : function() {
						return this.columns;

					},
					addGridRow : function(row) {
						columns = this.getColumns();
						var $tr = $("<tr></tr>");
						for ( var index in columns) {
							var cname = columns[index];
							var $td = $('<td class="font12">' + row[cname]
									+ '</td>');
							$td.appendTo($tr);
						}
						$tr.appendTo($("#" + this.id + " tbody"));
						console.log($tr)
					},
					setGridData : function(resultdata) {
						for ( var index in resultdata) {
							var row = resultdata[index];
							this.addGridRow(row);
						}
					},

					addRow : function(row) {
						var me = this;
						var $tr = me.addTr({
							cls : "success"
						});
						for ( var index in row) {
							var td = row[index];
							var tdHtml = "<td>" + td.text + "</td>";
							$(tdHtml).appendTo($tr).removeClass().addClass(
									td.cls || "default");
						}
					},
					alterRow : function(row) {
						var me = this;
						var $tr = me.addTr({
							cls : "success"
						});
						for ( var index in row) {
							var td = row[index];
							var tdHtml = "<td>" + td.text + "</td>";
							$(tdHtml).appendTo($tr).removeClass().addClass(
									td.cls || "default");
						}
					},
					deleteRow : function(row) {
						var me = this;
						var $tr = me.addTr({
							cls : "success"
						});
						for ( var index in row) {
							var td = row[index];
							var tdHtml = "<td>" + td.text + "</td>";
							$(tdHtml).appendTo($tr).removeClass().addClass(
									td.cls || "default");
						}
					},
					addTr : function(tr) {
						var me = this;
						var trHtml = "<tr></tr>";
						$(trHtml).addClass(tr.cls || "default");
						return $(trHtml).appendTo($("#" + me.id).find("tbody"));
					},
					addTh : function(th) {
						var me = this;
						console.log(th)
						var thHtml = "<th>" + (th.text || th.header) + "</th>";
						console.log(thHtml)
						$(thHtml)
								.appendTo($("#" + me.id).find("table .header"));
						this.columns.push(th.title || th.dataIndex);
					},
					addThGroup : function(thg) {
						var me = this;
						$("#" + me.id).find("table .group").removeClass(
								"hidden");
						var thgHtml = "<th colspan=" + (thg.colspan || 1) + ">"
								+ thg.text + "</th>";
						$(thgHtml)
								.appendTo($("#" + me.id).find("table .group"));
					},
					addPager : function(totalcount, pageSize) {
						var $tfoot = $("<tfoot></tfoot>");
						var $tr = $("<tr></tr>");
						var $td = $('<td colspan="21">'
								+ '   <ul class="col-sm-3 pagination pagination-sm" style="margin:0px;">'
								+ '     <li><a href="#">&laquo;</a></li>'
								+ '     <li><a href="javascript:void(0)">1</a></li> '
								+ '  <li><a href="#">...</a></li>'
								+ '     <li><a href="javascript:void(0)">100</a></li> '
								+ '    <li><a href="#">&raquo;</a></li>'
								+ '   </ul>'
								+ '   <div class="col-sm-3"> <span class="left" style="width:20%; float:left;">'
								+ '    <input class="form-control" type="text"/>'
								+ '   </span> <span class="left space-mleft10">'
								+ '   <button type="button" class="btn-sm btn-go">GO</button>'
								+ '   </span> <span class="left space-mleft10">每页显示'
								+ '   <select name="pagesize">'
								+ '   <option>10</option>'
								+ '  <option>20</option>'
								+ '  <option>30</option>'
								+ '  <option>50</option>'
								+ '  <option selected="selected">100</option>'
								+ '  <option>500</option>' + '</select>'
								+ '</span> </span> '
								+ '<span class="left space-mleft10">'
								+ '共100条记录' + '</span></div>' + '</td>');
						$td.appendTo($tr);
						$tr.appendTo($tfoot);
						$tfoot.appendTo($("#" + this.id + " table"));
					},
					doLayout : function() {
						var me = this;
						for ( var index in me.thead) {
							me.addTh(me.thead[index]);
						}
						for ( var index in me.theadGroup) {
							me.addThGroup(me.theadGroup[index]);
						}
						for ( var index in me.datastore) {
							me.addRow(me.datastore[index]);
						}
						me.addTbar();
						me.addFbar();
						if (me.pager !== false)
							me.addPager();
					}
				});

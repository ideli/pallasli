<script type="text/javascript" src="dwr/interface/service.js"></script>
 <script type="text/javascript" src="dwr/engine.js"></script>
 <script type="text/javascript" src="dwr/util.js"></script>
 <script type="text/javascript">
 dwr.util.useLoadingMessage("正在处理，请秒候. . .");
function refreshYearList() {
 service.getYears(populateYearList);
}
function populateYearList(list) {
 DWRUtil.removeAllOptions("years");
 DWRUtil.addOptions("years", list);
 refreshBikeList();
}
function refreshBikeList() {
 var year = $("years").value;
 service.getBikes(year, populateBikeList);
}
function populateBikeList(list) {
 DWRUtil.removeAllOptions("bikes");
 DWRUtil.addOptions("bikes", list);
}
</script>
    
  <body onload="refreshYearList();">
  年份：
  <select id="years" onchange="refreshBikeList();"></select>
  <br />
  <br />
  型号：
  <select id="bikes"></select>
  <br />
 </body>
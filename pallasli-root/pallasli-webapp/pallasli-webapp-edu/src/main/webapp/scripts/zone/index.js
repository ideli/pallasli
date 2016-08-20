(function($){
$(".home_zone_owner").load("/pages/zone/zoneOwner.html");
$(".home_zone_function").load("/pages/zone/zoneFunction.html");
$(".home_zone_mycare").load("/pages/zone/myCare.html");
$(".home_zone_mydiarylog").load("/pages/zone/myDiarylog.html");
$(".home_zone_friendDynatic").load("/pages/zone/friendDynatic.html");
$(".home_zone_myDynatic").load("/pages/zone/myDynatic.html");
$(".home_zone_siteDynatic").load("/pages/zone/siteDynatic.html");
$(".home_zone_notify").load("/pages/zone/notify.html");
$(".home_zone_myFans").load("/pages/zone/myFans.html");
$("input[name='userInfo']").click(function() {
	window.open("/pages/user/userInfo.html");
});
$("input[name='orgnizationInfo']").click(function() {
	window.open("/pages/orgnization/orgnizationInfo.html");
});
})(jQuery)

Ext.namespace("Mixky.wasoft.lib");

Mixky.wasoft.lib.LoadJsFile = function(id, url){
    var js = document.getElementById(id);
    if(!js){
        js = document.createElement('script');
        js.id = id;
        js.setAttribute('type', 'text/javascript');
        js.setAttribute('src', url);
        document.getElementsByTagName("head")[0].appendChild(js);
    }
};
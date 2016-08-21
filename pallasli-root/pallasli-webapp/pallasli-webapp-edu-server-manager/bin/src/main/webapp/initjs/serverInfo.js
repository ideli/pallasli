(function() {
	var jsonInfo = {
		"g.JVM版本" : "1.7.0_80-ea",
		"h.JVM提供商" : "Oracle Corporation",
		"a.操作系统" : "Mac OS X_x86_64",
		"a.操作系统名称" : "Mac OS",
		"a.操作系统Architecture" : "x86_64",
		"a.操作系统版本" : "10.10.2",
		"e.Web根路径" : "/Users/lyt1987/Documents/g4studio-master/project/webapp",
		"e.临时文件路径" : "/Users/lyt1987/Documents/g4studio-master/project/tmp",
		"d.监听端口" : 16999,
		"k.系统最大内存" : "4096M",
		"k.JVM可用最大内存" : "1820M",
		"f.Servlet版本" : "2.5",
		"b.主机IP" : "192.168.0.103",
		"c.应用服务器" : "jetty/7.6.9.v20130131",
		"l.JVM剩余内存" : "120M",
		"m.JVM已用内存" : "1700M",
		"n.系统启动时间" : "2015-02-03 10：28：21",
		"o.系统运行时长" : "128天3小时24分6秒",
		"p.登录系统用户" : "lyt",
		"q.登录系统用户主目录" : "/Users/lyt",
		"i.JVM安装路径" : "/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre"
	};
	var data=[];
	for(var i in jsonInfo){
		data.push( [ {
			text : i
		}, {
			text : jsonInfo[i]
		} ]);
	}
	
	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		title:"服务器信息",
		region : "west",
		width : 1200,
		thead : [ {
			text : "名称"
		}, {
			text : "值"
		} ],
		datastore : data
	});

	var frame=new Pallasli.window.IFrame({
		xtype : "iframe",
		title:"JVM内存监控视图",
		width : 800,
		region : "center",
		name:"frame"
	});
	new Pallasli.window.ViewPort({
		layout:"column",
		items : [ grid ,frame]
	});
})();
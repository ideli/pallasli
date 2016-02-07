	Ext.require([ 'Ext.direct.*', 'Ext.data.*', 'Ext.tree.*' ]);
	Ext.onReady(function() {

		var panel = Ext.getCmp("${panelId}");

		var form = Ext.create("Ext.panel.Panel", {
			"padding" : 10,
			"border" : false,
			"autoScroll" : true,
			//"html" : "<hr><br><img width=100 height=100 /><img width=100 height=100 /><br><hr><img width=100 height=100 /><img width=100 height=100 /><img width=100 height=100 /><img width=100 height=100 /><img width=100 height=100 /><img width=100 height=100 /><img width=100 height=100 /><img width=100 height=100 /><br><img width=100 height=100 /><br><hr><br><img width=100 height=100 /><br><hr><br><img width=100 height=100 /><img width=100 height=100 /><img width=100 height=100 /><br>"
		});
		
		
		ProcessDirectAction.processList("kermit",function(result){
			
			var  category=result[0].category;
			var drawComponent = Ext.create('Ext.draw.Component', {
			    viewBox: false,
			    html:category,
			    items: [{
			    	type: "path",
			        path: "M10 0 L1200 0 L1200 20 L10 20 Z",    //路径
			        "stroke-width": "1",
			        stroke: "#000",
			        fill: "blue"
			    }]
			});
			form.add(drawComponent);
			
			for(var i=0;i<result.length;i++){
				
				
				if(result[i].category!=category){
					category=result[i].category;
					var drawComponent = Ext.create('Ext.draw.Component', {
					    viewBox: false,
					    html:category,
					    items: [{
					    	type: "path",
					        path: "M10 0 L1200 0 L1200 20 L10 20 Z",    //路径
					        "stroke-width": "1",
					        stroke: "#000",
					        fill: "blue"
					    }]
					});
					form.add(drawComponent);
				}
				
				var pd=result[i];
				var drawComponent = Ext.create('Ext.draw.Component', {
				    viewBox: false,
				    width:200,
				    height:200,
				    processInfo:{name:pd.name,id:pd.id,key:pd.key},
				    html:pd.name,
				    items: [{
				        type: 'circle',
				        fill: '#79BB3F',
				        radius: 100,
				        x: 100,//(i%6),
				        y: 100//+100*(Math.floor(i/6))
				    }]
				});
				drawComponent.on("click",function(){
					console.log(this.processInfo)
				});
				form.add(drawComponent);
				
				var drawComponent = Ext.create('Ext.draw.Component', {
				    viewBox: false,
				    items: [{
				    	type: "path",
				        path: "M10 0 L100 0 L100 20 L10 20 Z",    //路径
				        "stroke-width": "0",
				        stroke: "white",
				        fill: "white"
				    }]
				});
				form.add(drawComponent);
			}
			
		});
		
		panel.add(form);
	});

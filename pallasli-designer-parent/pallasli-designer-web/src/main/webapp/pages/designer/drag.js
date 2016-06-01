var dragJs = {};
(function($) {
	
	dragJs.PageCompTree=function(){
		this.comps=[];
		var me=this;
		this.getCompData=function(comps,id){
			for(var i=0;i<comps.length;i++ ){ 
				if(comps[i].id==id){
					return comps[i];
				}
				if(comps[i].children){
					var cd=me.getCompData(comps[i].children, id);
					if(cd)return cd;
				}
			}
			return null;
		};
		this.creatChildElement=function(child,parent){
			var pC;
			if(parent){ 
				  childEl="<ul><li id="+child.id+">"+child.caption+"</li></ul>";
			    pC=$("#"+parent.id);
			}else{ 
				pC=$(".design-function ul");
				  childEl="<li id="+child.id+">"+child.caption+"</li>";
			}
			$(pC[0]).append($(childEl));
		};
		this.append=function(child,parent){
			console.log(me.comps,parent);
			if(parent){
				var pD=me.getCompData(me.comps,parent.id);
				pD.children=pD.children||[];
				pD.children[pD.children.length]=child; 
			}else{
				me.comps[me.comps.length]=child;
			}
			me.creatChildElement(child, parent);
		};
	};
	
	
	var pageCompTree=new dragJs.PageCompTree();
 
	 
	
	dragJs.loadScript = function(url) {
		var script = document.createElement('script');
		script.type = 'text/javascript';
		script.src = url;
		// document.head.appendChild(script); //document.head 表示<head>
		document.getElementsByTagName('head')[0].appendChild(script);
	};
	dragJs.loadStyles = function(url) {
		var link = document.createElement('link');
		link.rel = 'stylesheet';
		link.type = 'text/css';
		link.href = url;
		document.getElementsByTagName('head')[0].appendChild(link);
	};
	dragJs.creatTmpImage = function() {

		var tip = document.getElementById("tmp");
		if (tip) {
			tip.remove(tip.selectedIndex);
		}
		tip = document.createElement("label");
		tip.id = "tmp";
		tip.style.position = 'absolute';
		tip.style.width = "30px";
		tip.style.height = "15px";
		return tip;
	};

	dragJs.doDragOverEvent = function(mouseE) {
		var mouseoffsetX = mouseE.offsetX, mouseoffsetY = mouseE.offsetY, target = mouseE.target;
		if (mouseoffsetX < 5) {
			// 移动到左侧
			var tip = dragJs.creatTmpImage();
			target.appendChild(tip);
			tip.innerText = "←";
			tip.style.left = 0 + "px";
			tip.style.top = target.clientHeight / 2 + "px";
		} else if (target.clientWidth - mouseoffsetX < 5) {
			// 移动到左侧
			var tip = dragJs.creatTmpImage();
			target.appendChild(tip);
			tip.innerText = "->";
			tip.style.left = target.clientWidth - 15 + "px";
			tip.style.top = target.clientHeight / 2 + "px";

		} else if (mouseoffsetY < 5) {
			// 移动到左侧
			var tip = dragJs.creatTmpImage();
			target.appendChild(tip);
			tip.innerText = "↑";
			tip.style.left = target.clientWidth / 2 + "px";
			tip.style.top = 0 + "px";
			target.appendChild(tip);

		} else if (target.clientHeight - mouseoffsetY < 5) {
			// 移动到左侧
			var tip = dragJs.creatTmpImage();
			target.appendChild(tip);
			tip.innerText = "↓";
			tip.style.left = target.clientWidth / 2 + "px";
			tip.style.top = target.clientHeight - 15 + "px";

		} else if ($(target).attr("isRemoveDiv")) {
			// 不操作即移除
		} else if ($(target).attr("isRemoveDiv")) {
			// 移动到自身内部时不移除
		} else {
			// 移动到内部
			var tip = dragJs.creatTmpImage();
			target.appendChild(tip);
			tip.innerText = "￥";
			tip.style.marginLeft = target.clientWidth / 2;
			tip.style.marginTop = target.clientHeight / 2;
		}

	};

	dragJs.doDropEvent = function(mouseE) {
		var mouseoffsetX = mouseE.offsetX, mouseoffsetY = mouseE.offsetY, target = mouseE.target;
		if (mouseoffsetX - target.offsetLeft < 5) {
			// 移动到左侧

		} else if (target.offsetLeft + target.clientWidth - mouseoffsetX < 5) {
			// 移动到左侧

		} else if (mouseoffsetY - target.offsetTop < 5) {
			// 移动到左侧

		} else if (target.offsetTop + target.clientHeight - mouseoffsetY < 5) {
			// 移动到左侧

		} else if ($(target).attr("isRemoveDiv")) {
			// 不操作即移除
		} else {
			// 移动到内部

		}

	};

	dragJs._dragElements = {
		panel : {
			caption : '普通容器',
			tag : 'div',
			style : {
				height : '300px',
				width : '500px',
				border : 'solid 1px',
			},
			props : {
				draggable : 'true'
			},
			children : [ {
				tag : 'div',
				style : {
					height : '300px',
					width : '500px',
					border : 'solid 1px',
					position : 'absolute'
				}
			} ],
		},
		form : {
			caption : '表单',
			tag : 'form',
			style : {
				height : '300px',
				width : '500px',
				border : 'solid 1px'
			},
			props : {
				draggable : 'true'
			}
		},
		tree : {
			caption : '树',
			tag : 'div',
			style : {
				height : '300px',
				width : '500px',
				border : 'solid 1px'
			},
			children : [],
			props : {
				draggable : 'true'
			}
		},
		textfield : {
			caption : '文本框',
			tag : 'div',
			isSingleComp : true,
			props : {
				'class' : 'form-group',
				type : 'text',
				draggable : 'true'
			},
			children : [ {
				tag : 'label',
				style : {
					height : '20px',
					width : '40px',
					border : 'dotted 1px',
					'min-width' : '60px',
					display : 'inline-block'
				},
				props : {
					contenteditable : "true"
				},
				text : '标签'
			}, {
				tag : 'div',
				children : [ {
					caption : '文本框',
					tag : 'input',
					props : {
						type : 'text'
					},

				} ]
			} ]
		},
		textarea : {
			caption : '文本域',
			tag : 'input',
			props : {
				type : 'textarea',
				draggable : 'true'
			}
		},
		checkbox : {
			caption : '复选框',
			tag : 'input',
			props : {
				type : 'checkbox',
				draggable : 'true'
			}
		},
		radio : {
			caption : '单选框',
			tag : 'div',
			isSingleComp : true,
			props : {
				'class' : 'form-group',
				type : 'text',
				draggable : 'true'
			},
			children : [  {
				tag : 'div',
				children : [ {
					tag : 'input',
					props : {
						type : 'radio'
					},
				} ]
			},{
				tag : 'label',
				style : {
					height : '20px',
					width : '40px',
					border : 'dotted 1px',
					'min-width' : '60px',
					display : 'inline-block'
				},
				props : {
					contenteditable : "true"
				},
				text : '标签'
			} ]
		}
	};

	dragJs.initDragComp = function($dragComps) {
		console.log($dragComps);
		for (var i = 0; i < $dragComps.length; i++) {
			var $dragComp = $dragComps[i];

			$dragComp.onselectstart = function() {
				console.log("onselectstart");
				ev.cancelBubble = true;
				return false;
			};

			$dragComp.ondragstart = function(ev) {
				console.log(ev.target);
				ev.cancelBubble = true;
				console.log("ondragstart");
				
				var dragJs=document.createElement("div");
				label=document.createElement("label");
				label.innerText="test";
				input=document.createElement("input");
				dragJs.appendChild(label).appendChild(input);
				document.body.appendChild(dragJs);
				/* 拖拽开始 */
				// 拖拽效果
				ev.dataTransfer.effectAllowed = "move";
				ev.dataTransfer.setData("text", ev.target.innerHTML);
				ev.dataTransfer.setDragImage( dragJs  , 80, 10);
				return true;
			};
			$dragComp.ondragend = function(ev) {
				ev.cancelBubble = true;
				console.log("ondragend");
				/* 拖拽结束 */
				ev.dataTransfer.clearData("text");
				return false;
			};
			$($dragComp).prop("draggable", "true");
		}
	};

	dragJs.initDropZone = function($dropZones) {
		for (var i = 0; i < $dropZones.length; i++) {
			var $dropZone = $dropZones[i];
			$dropZone.ondragover = function(ev) {
				console.log("ondragover");
				ev.cancelBubble = true;
				/* 拖拽元素在目标元素头上移动的时候 */
				ev.preventDefault();
				dragJs.doDragOverEvent(ev);
				return true;
			};

			$dropZone.ondragenter = function(ev) {
				ev.cancelBubble = true;
				/* 拖拽元素进入目标元素头上的时候 */
				// this.style.color = "#ffffff";
				return true;
			};

			function initChildTag(parent, ele) {
				var child = $(document.createElement(ele.tag));
				if (ele.children) {
					for (var i = 0; i < ele.children.length; i++) {
						initChildTag(child, ele.children[i]);
					}
				}

				if (ele.props) {
					for ( var p in ele.props) {
						child.prop(p, ele.props[p]);
					}
				}

				if (ele.text) {
					child.text(ele.text);
				}
				if (ele.style) {
					for ( var p in ele.style) {
						child.css(p, ele.style[p]);
					}
				}
				child.appendTo(parent); 
			}
			
			var treeId=0;
			var 
			jj=1;;
			function append(parent, ele) {
				var child = $(document.createElement(ele.tag));
				if (ele.children) {
					for (var i = 0; i < ele.children.length; i++) {
						initChildTag(child, ele.children[i]);
					}
				}

				if (ele.props) {
					for ( var p in ele.props) {
						child.prop(p, ele.props[p]);
					}
				}

				if (ele.text) {
					child.text(ele.text);
				}
				child.prop("id","jj"+(jj++)); 
				if (ele.style) {
					for ( var p in ele.style) {
						child.css(p, ele.style[p]);
					}
				}
				child.attr("isSingleComp", ele.isSingleComp);
				if (parent.attr("isSingleComp")) {
					parent.after(child);
					dragJs.initDropZone(child);
					
				 
					pageCompTree.append({id:"tree-"+treeId++,caption:treeId});
				
				
				} else {
					dragJs.initDropZone(child.appendTo(parent));  
					pageCompTree.append({id:"tree-"+treeId++,caption:treeId});
				}
			}
			$dropZone.ondrop = function(ev) {
 

				ev.cancelBubble = true;
				console.log("ondrop");
				ev.preventDefault();

				var type = ev.dataTransfer.getData("text");

				for ( var key in dragJs._dragElements) {
					var ele = dragJs._dragElements[key];
					if (ele.caption == type) {
						append($(this), ele); 
						dragJs.initDragComp  ($("#jj"+(jj-1)))
						break;
					}
				}

				/* 拖拽元素进入目标元素头上，同时鼠标松开的时候 */
				return false;
			};
		}
	};

})(jQuery);
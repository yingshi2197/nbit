


/**
 * 插件封装
 * @param $
 */

(function($) {
	 $.extend(tools,
			{
				getTreeObj:function(treeId) {
					return $.fn.zTree.getZTreeObj(treeId);
				},
				getTreeNode:function(treeId,prop,value) {
					var obj=tools.getTreeObj(treeId);
					return obj.getNodeByParam(prop,value);
				},
				getCheckedNodes:function(treeId) {
					var obj=tools.getTreeObj(treeId);
					return obj.getCheckedNodes();
				},
				checkNode:function(treeId,nodeId) {
					var node=tools.getTreeNode(treeId,"id",nodeId);
					var obj=tools.getTreeObj(treeId);
					return obj.checkNode(node,true);
				}
			}
	 );
	 $.extend($.fn, {
		 	/**
		 	 * 生成树节点
		 	 var param={
						url:"manage/org/tree.do", // 初始化数据的url
						autoParam:{},  // 每个url上面需要添加的参数
		 				operate:{
		 							add:{url:"manage/org/toAdd.do",width:"500px",height:"400px"},
		 							edit:{url:"manage/org/toEdit.do",width:"500px",height:"400px"},
		 							remove:{url:"manage/org/remove.do"}},
		 				able:true, // 是否需要显示后面的操作
		 				permission:false, // 是否需要过滤权限
		 				rule:{"level0":{add:true,edit:false,remove:false},"other":{add:true,edit:true,remove:true}}  // 控制每一层的节点操作规则
		 		} 
		 	 */
		 	initTree: function(param,nodes) {
		 		var that=this;
		 		
		 		//所有当前用户可以访问的url
	        	var allAccessUrl;
	        	
	        	// 获取所有初始化数据
	        	var firstDataUrl=param.url;
	        	if(firstDataUrl&&!nodes) {
	        		tools.ajax(
							{ 
								url: tools.joinUrl(firstDataUrl,param.autoParam), 
								data: null,
								method:"post",
								async: false,
								success: function(data){
									var min=3;
									for(var i=0;i<data.length;i++) {
										var s=data[i];
										if(s.ruleId.length<min)
											min=s.ruleId.length;
									}
									nodes=data;
									
									for(var i=0;i<data.length;i++) {
										if(data[i].ruleId.length<(min+3*(param.open||2)))
											data[i].open=true;
									}
								}
						   });
	        	}
	        	
	        	// 获取所有当前用户可以访问的url
	        	tools.ajax(
						{ 
							url: "manage/user/obtainAllUrl.do", 
							data: null,
							method:"post",
							async: false,
							success: function(data){
								allAccessUrl=data;
							}
					   });
	        	
	        	
	        	// 判断当前url是否是可以显示并访问
	        	var checkUrlPermission=function(url) {
	        		if(!allAccessUrl)
	        			return false;
	        		if(!url)
	        			return false;
	        		for(var i=0;i<allAccessUrl.length;i++) {
	        			//修改逻辑,防止链接后加参数变成无权限
	        			var accessUrl=allAccessUrl[i];
	        			if(url.indexOf(accessUrl)==0) {
	        				return true;
	        			}
	        		}
	        		return false;
	        	}
	        	
		 		
		 		
		 		var _param={
		 				async: {enable:false,	url:null,autoParam:{}},
		 				operate:{add:{url:null,func:null},edit:{url:null,func:null},remove:{url:null,func:null}},
		 				able:true,
		 				open:2,// 默认展开根一下的2级
		 				type: null, //"checkbox","radio",null
		 				chkboxType: {
	 						"Y": "ps",
	 						"N": "ps"
	 					},
		 				permission:true, // 是否需要过滤权限
		 				rule:{1:{add:true,edit:false,remove:false},other:{add:true,edit:false,remove:false}} 
		 		}
		 		
		 		$.extend(true,_param,param);
		 		//alert(param.chkboxType["Y"]);
		 		function removeHoverDom(treeId, treeNode) {
					$("#addBtn_"+treeNode.tId).unbind().remove();
					$("#editBtn_"+treeNode.tId).unbind().remove();
					$("#removeBtn_"+treeNode.tId).unbind().remove();
				};
		 		
		 		var treeId= $(that).attr("id");
		 		var _setting = {
		 				treeId: $(that).attr("id"),
		 				treeObj: null,
		 				view: {
		 					addDiyDom: null,
		 					autoCancelSelected: true,
		 					dblClickExpand: true,
		 					expandSpeed: "fast",
		 					fontCss: {},
		 					addHoverDom: addHoverDom,
		 					removeHoverDom: removeHoverDom,
		 					nameIsHTML: false,
		 					selectedMulti: true,
		 					showIcon: true,
		 					showLine: true,
		 					showTitle: true,
		 					txtSelectedEnable: false
		 				},
		 				data: {
		 					key: {
		 						children: "children",
		 						name: "name",
		 						title: "",
		 						url: ""
		 						//url: "url" 菜单的url属性会加到节点的url中
		 					},
		 					simpleData: {
		 						enable: true,
		 						idKey: "id",
		 						pIdKey: "parentId",
		 						rootPId: null
		 					},
		 					keep: {
		 						parent: false,
		 						leaf: false
		 					}
		 				},
		 				check: {
		 					enable: _param.type ? true : false,
		 					autoCheckTrigger: false,
		 					chkStyle: _param.type,
		 					nocheckInherit: false,
		 					chkDisabledInherit: false,
		 					radioType:"all",
		 					chkboxType:_param.chkboxType
		 				},
		 				/*async: {
		 					enable: _param.async? _param.async.enable : false,
		 					contentType: "application/x-www-form-urlencoded",
		 					type: "post",
		 					dataType: "json",
		 					url:  _param.async? _param.async.url : "",
		 					autoParam:  _param.async? _param.async.autoParam : [],
		 					otherParam: [],
		 					dataFilter: null
		 				},*/
		 				callback: {
		 					beforeAsync:null,
		 					beforeClick:null,
		 					beforeDblClick:null,
		 					beforeRightClick:null,
		 					beforeMouseDown:null,
		 					beforeMouseUp:null,
		 					beforeExpand:null,
		 					beforeCollapse:null,
		 					beforeDrag:null,
		 					
		 					onRemove: null,
		 					onAsyncError:null,
		 					onAsyncSuccess:null,
		 					onNodeCreated:null,
		 					onClick:null,
		 					onDblClick:function(event, treeId, node){
		 						if(_param.type=="radio") {
			 						var data=[node];
			 						tools.getDialogParent().setValue(data);
			 						tools.closeDialog();
		 						}
		 					},
		 					onRightClick:null,
		 					onMouseDown:null,
		 					onMouseUp:null,
		 					onExpand:null,
		 					onCollapse:null,
		 					beforeCheck:null,
		 					onCheck:null,
		 					onRemove:null
		 				}
		 			}
		 			$.extend(true,_setting,param);
		 		
					function addHoverDom(treeId, treeNode) {
						
						var able=_param.able;
						if(!able)
							return "";
						var permission =_param.permission; // 是否需要做权限过滤
						
						var add=_param.operate&&_param.operate.add ? _param.operate.add.url : null;
						var edit=_param.operate&&_param.operate.edit ? _param.operate.edit.url : null;
						var remove=_param.operate&&_param.operate.remove ? _param.operate.remove.url : null;
						
						var autoParam=_param.autoParam;
						
						var _pobj=autoParam||{};
						_pobj["id"]=treeNode.id;
						
						if(_param.rule) {
							var obj=_param.rule["level"+treeNode.level];
							
							if(!obj) {
								obj=_param.rule["other"];
							}
								
							var _op={};
							 if (typeof obj === 'function') {
								 _op=  obj.apply(self,treeNode );
						     }else {
						    	 _op=obj;
						     }
							if(_op) {
								add=_op.add;
								edit=_op.edit;
								remove=_op.remove;
							}
						}
						
						if(permission) {
							if(add) {
								add=checkUrlPermission(_param.operate.add.url);
							}
							if(edit) {
								edit=checkUrlPermission(_param.operate.edit.url);
							}
							if(remove) {
								remove=checkUrlPermission(_param.operate.remove.url);
							}
						}
						
						var sObj = $("#" + treeNode.tId + "_span");
						if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
						if (treeNode.editNameFlag || $("#editBtn_"+treeNode.tId).length>0) return;
						if (treeNode.editNameFlag || $("#removeBtn_"+treeNode.tId).length>0) return;
						if(remove) {
							var removeStr = "<span class='button remove' id='removeBtn_" + treeNode.tId
							+ "' title='删除' dataid='"+treeNode.id+"' onfocus='this.blur();'></span>";
							sObj.after(removeStr);
							var removeBtn = $("#removeBtn_"+treeNode.tId);
							if (removeBtn) removeBtn.bind("click", function(){								
								var zTree = tools.getTreeObj(treeId);
								var dataid=$(this).attr("dataid");
								var tnode=tools.getTreeNode(treeId,"id",dataid);
								tools.confirm("你确定要删除【"+tnode.name+"】？",function() {
									tools.ajax(
		 									{ 
		 										url: tools.joinUrl(_param.operate.remove.url,_pobj), 
		 										data: null, 
		 										success: function(data){
		 											tools.alert(data.msg);
		 											if(data.type=="1")
		 												zTree.removeNode(treeNode);
		 											//$(that).refresh();
		 										}
		 								   });
								}) ;
								
							});
						}
						if(edit) {
							var editStr = "<span class='button edit' id='editBtn_" + treeNode.tId
							+ "' title='修改' dataid='"+treeNode.id+"' onfocus='this.blur();'></span>";
							sObj.after(editStr);
							var editBtn = $("#editBtn_"+treeNode.tId);
							if (editBtn) editBtn.bind("click", function(){
								var zTree = tools.getTreeObj(treeId);
								var dataid=$(this).attr("dataid");
								var tnode=tools.getTreeNode(treeId,"id",dataid);
								window.operateTreeNode=function(node) {
									treeNode.name=node.name;
									zTree.editName(treeNode);
								}
								tools.dialog({
	 			    				name:"修改",
	 			    				url:tools.joinUrl(_param.operate.edit.url,_pobj),
	 			    				width: _param.operate.edit.width,
	 			    				height: _param.operate.edit.height
	 			    			});
							});
						}

						if(add) {
							var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
							+ "' title='新增' dataid='"+treeNode.id+"' onfocus='this.blur();'></span>";
							sObj.after(addStr);
							var addBtn = $("#addBtn_"+treeNode.tId);
							if (addBtn) addBtn.bind("click", function(){
								var zTree = tools.getTreeObj(treeId);
								var dataid=$(this).attr("dataid");
								var tnode=tools.getTreeNode(treeId,"id",dataid);
								window.operateTreeNode=function(node) {
									zTree.addNodes(treeNode, {id:(node.id), parentId:treeNode.id, name:node.name });
								}
								tools.dialog({
	 			    				name:"新增",
	 			    				url:tools.joinUrl(_param.operate.add.url,_pobj),
	 			    				width: _param.operate.add.width,
	 			    				height: _param.operate.add.height
	 			    			});
							});
						}
						
					};
					
		 			var zTreeObj=$.fn.zTree.init(this, _setting, nodes);
		 			
		 			 function addSearchDiv() {
						 var searchId_temp=$(that).attr("id")+"_search_"+new Date().getTime();
						 
						 window.document.body.onkeypress=function(e){
							 if(e.keyCode == 13&&$("#"+searchId_temp).length>0) {
								 $("#"+searchId_temp).click();
							 }
						 }
						 
						 var makeSearchButton=function() {
							 return '<div class="form-group col-xs-4"><button id="'+searchId_temp+'" class="btn btn-default" >GO</button></div>';
						 }
						 var makeSearchInput=function(column) {
							 var str='<div class="form-group col-xs-4">'+
		                         			'<div class="input-group">'+
		                         				'<div class="input-group-addon">'+column.title+'</div>'+
		                         				'<input type="text" name="'+(column.field)+'" placeholder="'+(column.placeholder ? column.placeholder : "")+'" class="form-control" />'+
		                         			'</div>'+
		                         		'</div>&nbsp;&nbsp;&nbsp;';
							 return str;
						 };
						 var columns=_param.search;
						 var searchColumnInputs=[];
						 for(var i=0;i<columns.length;i++) {
							    var column=columns[i];
								searchColumnInputs.push(makeSearchInput(column));
						 }
						 searchColumnInputs.push(makeSearchButton());
						
						 $(document.body).prepend("<div class='form-horizontal'>"+searchColumnInputs.join("")+"</div>");
						 
						 $("#"+searchId_temp).on("click",function(){
							 alert(1);
						 })
					 }
		 			
		 			//addSearchDiv();
		 			if(_param.type) {
		 				var id="select_"+new Date().getTime();
		 				var str='<div class="container"> <div class="tab-form1"><div class="tab-form-submit">'+
		 								'<button type="button" id="'+id+'" class="btn btn-success">'+
		 									'选择'+
		 								'</button>'+
		 							'</div></div></div>'
		 				$(that).after(str);
		 				$("#"+id).on("click",function(){
		 						var data=tools.getCheckedNodes($(that).attr("id"));
		 						if(data.length==0) {
		 							tools.alert("请选择一条数据！");
		 							return;
		 						}
		 						tools.getDialogParent().setValue(data);
		 						tools.closeDialog();
		 				})
		 			}
		 			
		 			if(tools.getDialogParent().getValue&&_param.type) {
	        			var selectData=tools.getDialogParent().getValue();
	        			for(var i=0;i<selectData.length;i++) {
		        			var temp=selectData[i];
		        			tools.checkNode($(that).attr("id"),temp.id);
		        		}
	        		}
		 			
	        }
	 });
})(jQuery);
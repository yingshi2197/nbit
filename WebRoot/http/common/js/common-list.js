
/**
 * 插件封装
 * @param $
 */

(function($) {
	 $.extend($.fn, {
		 	/**
		 	 * 简单的分页方法
		 	 * 只提供简单的分页
		 	 */
		 	initPage: function(param) {
		 		var options={};
		 		$.extend(true,options,param);
		 		var url=options.url;
		 		if((!options.pageUrl)&&url) {
		 			options.pageUrl=function(type, page, current){     
		            	return url+(url.indexOf("?")!=-1 ? "&" : "?")+"current="+page+"&pageSize="+options.rowCount;
		            }
		 		}
		 		
		 		var ref=options.ref;
		 		if((!options.url)&&ref) {
		 			options.url=function(type, page, current){     
		            	return ref+(ref.indexOf("?")!=-1 ? "&" : "?")+"current="+page+"&pageSize="+options.rowCount;
		            }
		 		}
		 		$(this).bootstrapPaginator(options);
	        },
	        /**
	         * 复杂的分页方法
	         * 提供增删改查方法以及列表显示，条件查询，排序等等功能
	         * 提供全选，查询，取消全选，获取选择数据，刷新等方法
	         */
	        initBootTable: function(table) {
	        	var that_=this;
	        	
	        	table.onLoadSuccess=function (data) {
	        		var data=data.rows;
	        		//alert(tools.getDialogParent().getValue);
	        		if(tools.getDialogParent().getValue) {
	        			var selectData=tools.getDialogParent().getValue();
	        			var fields=["id"];
	        			if(selectData.length>0) {
	        				$(that_).addSelectDatas(selectData);
	        			}
	        			var all=$(that_).selectDatas();
	        			for(var i=0;i<data.length;i++) {
		        			var temp=data[i];
		        			for(var j=0;j<all.length;j++) {
		        				var t=all[j];
		        				if(t[fields[0]]==temp[fields[0]]) {
		        					$(that_).checkIndex(i);
		        				}
		        			}
		        		}
	        		}
	        		
	            };
	            
	            table.onCheck=function (row) {
	            	$(that_).replaceSelectDatas(row);
	            };
	            table.onUncheck=function (row) {
	            	$(that_).removeSelectDatas(row);
	            };
	            table.onCheckAll=function (rows) {
	            	for(var i=0;i<rows.length;i++)
	            		$(that_).replaceSelectDatas(rows[i]);
	            };
	            table.onUncheckAll=function (rows) {
	            	for(var i=0;i<rows.length;i++)
	            		$(that_).removeSelectDatas(rows[i]);
	            };
	            
	        	// 获取按钮的url
	        	var getButtonUrl=function(button) {
	        		if(button.url)
	        			return button.url;
	        		if(button.dialog&&button.dialog.url)
	        			return button.dialog.url;
	        		return null;
	        	}
	        	
		 		var options={
		 				tableId:$(this).attr("id"),
		 				searchDiv:undefined,
		 				searchClass:"search-Class",
		 				classes: 'table table-hover',
		 		        undefinedText: '-',
		 		        sortName: undefined,
		 		        sortOrder: 'asc',
		 		        striped: false,
		 		        rowButtons:[],
		 		        columns: [],
		 		        // 如果是顶部按钮，则不能根据行内的data进行判断，为字符串false时则无论点击哪行数据都没有改按钮
		                // 如果是行级按钮，可以根据行内的data进行判断，为字符串false时则该行中的对应样式的按钮不会显示
		 		       permissionOperate:null,
		 		        data: [],
		 		        selectData:[],
		 		        method: 'get',
		 		        url: undefined,
		 		        cache: true,
		 		        contentType: 'application/x-www-form-urlencoded',   //application/json
		 		        dataType: 'json',
		 		        ajaxOptions: {},
		 		        searchRowNum:2,// 搜索组件每行显示几个，默认为2个，最多为3个
		 		        queryParams: function (params) {
		 		            return params;
		 		        },
		 		        queryParamsType: 'limit', // undefined
		 		        responseHandler: function (res) {
		 		            return res;
		 		        },
		 		        pagination: false,
		 		        sidePagination: 'client', // client or server
		 		        totalRows: 0, // server side need to set
		 		        pageNumber: 1,
		 		        pageSize: 10,
		 		        pageList: [10, 25, 50, 100],
		 		        paginationHAlign: 'right', //right, left
		 		        paginationVAlign: 'bottom', //bottom, top, both
		 		        paginationDetailHAlign: 'left', //right, left
		 		        search: false,
		 		        searchAlign: 'right',
		 		        selectItemName: 'btSelectItem',
		 		        showHeader: true,
		 		        showFooter: false,
		 		        showColumns: false,
		 		        showPaginationSwitch: false,
		 		        showRefresh: false,
		 		        showToggle: false,
		 		        buttonsAlign: 'right',
		 		        smartDisplay: true,
		 		        minimumCountColumns: 1,
		 		        idField: undefined,
		 		        uniqueId: undefined,
		 		        cardView: false,
		 		        trimOnSearch: true,
		 		        clickToSelect: false,
		 		        singleSelect: false,
		 		        toolbar: undefined,
		 		        toolbarAlign: 'left',
		 		        checkboxHeader: true,
		 		        sortable: true,
		 		        maintainSelected: true,
		 		        searchTimeOut: 500,
		 		        keyEvents: false,
		 		        searchText: '',
		 		        iconSize: undefined,
		 		        completeLoad:null, // 加载完成后需要做的事情
		 		        iconsPrefix: 'glyphicon'
		 		};
		 		$.extend(true,options,table);
		 		var that=this;
		 		var operateFormatter=null;
		 		var operateEvents={};
		 		var re=[];
		 		var operateColShow=false;  // 是否需要显示操作列
		 		if(options.rowButtons) {
		 			var rowButtons=options.rowButtons;
		 			var top_button=[];
		 			var top_button_index=[];
		 			top_button.push("<div class='pull-right'>");
		 			
			 		for(var i=0;i<rowButtons.length;i++) {
	 						var rowButton=rowButtons[i];	 						
	 						
				 			
	 						var _id=rowButton.id||new Date().getTime()+"_"+i;
	 						if(rowButton.position=='top'){
	 							
	 							var permisson=options.permissionOperate ? options.permissionOperate({}) : [];
		 						//判断当前按钮的Url是够是可以访问的Url,如果不是就不显示
		 						if(rowButton.css!="select") {
		 							if(permisson[rowButton.css]=="false"||!tools.checkUrlPermission(getButtonUrl(rowButton))) {
					 					continue;
					 				}
						 				
		 							
		 						}
	 							if(rowButton.a)	 {
	 								top_button.push(a);
	 							} else {		
	 								top_button_index.push({index:i,id:_id});
		 							top_button.push("<a data-seq='"+i+"' id='"+_id+"' href='javascript:void(0)' class='"+(rowButton.css||'')+"' title='"+(rowButton.name||'')+"'>");
		 							top_button.push(rowButton.name);
		 							top_button.push("</a>");
	 							}
	 						}else {
	 								if(!operateColShow) 
	 									operateColShow=true;
				 					re.push({url:getButtonUrl(rowButton),css:rowButton.css,href:(rowButton.a ? rowButton.a : ('<a data-seq="'+i+'" id="'+_id+'" class="'+(rowButton.css||'')+'" href="javascript:void(0)" title="'+(rowButton.name||'')+'">'+
				 							"<span class='text-success'>"+
				 							(rowButton.name||'')+
				 							"</span>"+
							            '</a>'))});

			 						operateEvents['click #'+_id]=function(e, value, row, index) {
				 						var _index=$(this).data("seq");
				 						var field=rowButtons[_index]["field"]||"id";
				 						if(rowButtons[_index].type=="ajax") {
				 							tools.confirm("确定要执行此操作吗？", function(){
				 								tools.ajax(
					 									{ 
					 										url: tools.joinUrl(tools.replaceField(rowButtons[_index].url, row),{id:row[field]}), 
					 										data: null, 
					 										success: function(data){
					 											var png = "error";
					 											if (data.type == "1")
					 												png = "success";
					 											tools.alert(data.msg, png,function(){});
					 											var _operateType;
					 											if(rowButtons[_index].css=="remove") {
					 												_operateType="remove";
					 											}
					 											 $(that).refresh(_operateType);
					 										}
					 								   });
				 							});
				 							return;
				 						}
				 						if(rowButtons[_index].type=="download") {
				 							var condition=tools.getSearchCondition(options.searchClass, options.searchDiv);
					 						var param={"condition":condition};
				 							var form=tools.makeForm(tools.joinUrl(tools.replaceField(rowButtons[_index].url, row),{id:row[field]}),param);
				 							form.submit();
				 							$(form).remove();
				 							return;
				 						}
				 						if(rowButtons[_index].func) {
				 							rowButtons[_index].func(row);
				 							return;
				 						}
				 						if(rowButtons[_index].dialog) {
				 							tools.dialog({
				 			    				name:rowButtons[_index].name,
				 			    				url:tools.joinUrl(tools.replaceField(rowButtons[_index].dialog.url, row),{id:row[field]}),
				 			    				width:rowButtons[_index].dialog.width,
				 			    				height:rowButtons[_index].dialog.height,
				 			    				close:function(obj,w) {
				 			    						return $(that).refresh();
				 			    				}
				 			    			});
				 							return;
				 						}
				 					}
	 						}
		 			}
			 		
			 		operateFormatter=function(args,row) {
			 			var permisson=options.permissionOperate ? options.permissionOperate(row) : [];
			 			var arr=[];
			 			for(var i=0;i<re.length;i++) {
			 				if(!tools.checkUrlPermission(re[i]["url"]))
			 					continue;
			 				if(permisson[re[i]["css"]]=="true"||!permisson[re[i]["css"]]) {
			 					arr.push(re[i]["href"]);
			 				}
			 			}
			 			return arr.join("&nbsp;&nbsp;");
			 		}
			 		top_button.push("</div>");
			 		
			 		if(top_button_index.length>0)  {// 有按钮的时候才显示
			 			$(that).before(top_button.join(""));
			 			var temp_top_button={};  
			 			for(var i=0;i<top_button_index.length;i++) {
			 				temp_top_button[top_button_index[i].index]=rowButtons[top_button_index[i].index];
			 				var field=temp_top_button[top_button_index[i].index]["field"]||"id";
			 				$("#"+top_button_index[i].id).on("click",function() {
			 					var id=$(this).attr("id");
			 					var i=id.split("_")[id.split("_").length-1];
			 					var select_data=$(that).selectDatas();
				 				if(temp_top_button[i].select=="0"&&select_data.length!=1) {
				 					tools.alert("请选择一条数据！");
				 					return;
				 				}else if(temp_top_button[i].select=="1"&&select_data.length==0) {
									tools.alert("请至少选择一条数据！");		 	
									return;
								}
			 					var id=[];
			 					for(var k=0;k<select_data.length;k++) {
			 						id.push(select_data[k][field]);
			 					}
				 				if(temp_top_button[i].type=="ajax") {
		 							tools.confirm("确定要执行此操作吗？", function(){ 
		 								tools.ajax(
			 									{ 
			 										url: tools.joinUrl(temp_top_button[i].url,{id:id.join(",")}), 
			 										data: {id:id.join(",")}, 
			 										success: function(data){
			 											var png = "error";
			 											if (data.type == "1")
			 												png = "success";
			 											tools.alert(data.msg, png,function(){});
			 											 $(that).refresh();
			 										}
			 								   });
		 							});
		 							return;
		 						}
				 				if(temp_top_button[i].type=="download") {
				 					var condition=tools.getSearchCondition(options.searchClass, options.searchDiv);
				 					var param={"condition":condition};
				 					var form=tools.makeForm(tools.joinUrl(temp_top_button[i].url,{id:id.join(",")}),param);
				 					form.submit();
				 					$(form).remove();
		 							return;
		 						}
			 					if(temp_top_button[i].func) {
			 						temp_top_button[i].func(select_data);
			 						tools.closeDialog();
		 							return;
		 						}
		 						if(temp_top_button[i].dialog) {
		 							tools.dialog({
		 			    				name:temp_top_button[i].name,
		 			    				url:tools.joinUrl(temp_top_button[i].dialog.url,{id:id.join(",")}),
		 			    				width:temp_top_button[i].dialog.width,
		 			    				height:temp_top_button[i].dialog.height,
		 			    				close:function(obj,w) {
		 			    						return $(that).refresh();
		 			    				}
		 			    			});
		 							return;
		 						}

			 				})
			 			}
			 		}
			 		if(operateColShow)
			 			options.columns.push({field: 'operate',title: '操作',align: 'center',valign: 'middle',formatter:operateFormatter,events:operateEvents});
		 		}
		 		// 添加查询div
		 		$(this).addSearchDiv(options);
		 		// 初始化列表
		 		$(this).bootstrapTable(options);
	        },
	        // 获取列表选择的行数（只针对复杂的分页函数）
	        selectDatas:function() {
			        return $(this).bootstrapTable('getSelections');
			 },
			// 记住选择的选项
	        addSelectDatas:function(data) {
	        	 $(this).bootstrapTable('addSelections',data);
			 },
			 resetSelectDatas:function() {
	        	 $(this).bootstrapTable('resetSelections');
			 },
			// 有就替换选择的选项，没有就添加
	        replaceSelectDatas:function(data) {
	        	 $(this).bootstrapTable('replaceSelections',data);
			 },
			// 移除项
	        removeSelectDatas:function(data) {
	        	 $(this).bootstrapTable('removeSelections',data);
			 },
			 // 刷新列表（只针对复杂的分页函数）operateType操作类型，只针对删除来用，add，edit，remove
			 refresh:function(operateType) {
				 var param=[];
				 param["silent"]="server";
				 param["operateType"]=operateType;
				 this.resetSelectDatas();
				 return $(this).bootstrapTable('refresh',param);
			 },
			 // 选择所有（只针对复杂的分页函数）
			 checkAll:function() {
				 $(this).bootstrapTable('checkAll');
			 },
			 // 取消全选（只针对复杂的分页函数）
			 uncheckAll:function() {
				 $(this).bootstrapTable('uncheckAll');
			 },
			 checkIndex:function(index) {
				 $(this).bootstrapTable('check',index);
			 },
			 // 查询方法（只针对复杂的分页函数）
			 search:function(searchClass,id_table,searchDiv) {
				 //$("#"+id_table).showLoading();
				 var param={};
				 var search={};
				 search["condition"]=tools.getSearchCondition(searchClass, searchDiv);
				 //alert(search["condition"]);
				 param["query"]=search;
				 param["silent"]="server";
				 search=null;
				 $("#"+id_table).bootstrapTable('refresh',param);
				 //$("#"+id_table).hideLoading();
			 },	
			 // 添加查询区域（只针对复杂的分页函数）
			 addSearchDiv:function(options) {
				 var id_table=$(this).attr("id");
				 var searchRowNum=options.searchRowNum||2;
				 var searchId_temp=id_table+new Date().getTime();
				 window.document.body.onkeypress=function(e){
					 if(e.keyCode == 13&&$("#"+searchId_temp).length>0) {
						 $("#"+searchId_temp).click();
						 return false;
					 }
				 }
				 
				 var makeSearchButton=function() {
					 var tttt=[];
					 tttt.push('<div class="form-group col-xs-1"><button id="'+searchId_temp+'" class="btn btn-primary" onclick="$(this).search(\''+options.searchClass+'\',\''+id_table+'\',\''+options.searchDiv+'\');">搜索</button>');
					 if(index__>searchRowNum) {					 
						 tttt.push('<a href="javascript:void(0);" title="显示更多查询条件" class="search_con_more_'+id_table+'" style="position: absolute;z-index:1000;left:70px;top:5px;"><div  class="more-down" ></div></a>');
					 }
					 tttt.push('</div>');
					 return tttt.join("");
				 }
				 var makeSearchInput=function(column,css_hide) {
					 var str="";
					 var value=column.value||'';
					 if(typeof (column.formatter)=="string"&&column.formatter.indexOf("date:")==0) {
						 var type=column.type;
						 if(type=="time_select") {
								 var dictCodes="time_select";
								 var selectCode=column.selectCode||"id";
				        		var arr=dictCodes.split(",");
				        		var tt=[];
				        		tt.push('<option value="">请选择</option>')
				        		for(var i=0;i<arr.length;i++) {
				        			var dictCode=arr[i];
				        			var obj=tools.getValueForDict(dictCode);
				        			for(var j=0;j<obj.length;j++) {
				            			tt.push('<option value="'+obj[j][selectCode]+'" '+(obj[j][selectCode]==value? "selected" : " ")+'>'+obj[j]["name"]+'</option>');
				            		}
				        		}
							 str='<div class="form-group col-xs-4 '+css_hide+'">'+
		              			'<div class="input-group">'+
		              				'<div class="input-group-addon">'+column.title+'</div>'+
		              				'<select name="'+(column.field)+'_TS" class="'+options.searchClass+' form-control" >'+tt.join("")+'</select>'+
		              			'</div>'+
		              		'</div>';
						 
						 }else {
							 str='<div class="form-group col-xs-4 '+css_hide+'">'+
				              			'<div class="input-group">'+
				              				'<div class="input-group-addon">'+column.title+'</div>'+
				              				'<input style="width:46%" readonly="readonly"  type="text" name="'+(column.field)+'BT" placeholder="'+(column.placeholder ? column.placeholder : "")+'" class="'+options.searchClass+' form-control date" /><span>-</span>'+
				              				'<input style="width:46%;float:right" readonly="readonly"  type="text" name="'+(column.field)+'ET" placeholder="'+(column.placeholder ? column.placeholder : "")+'" class="'+options.searchClass+' form-control date" />'+
				              			'</div>'+
				              		'</div>';
						 }
					 }else if((column.type=='select'&&typeof (column.formatter)=="string"&&column.formatter.indexOf("dictCode:")==0)||(column.type&&column.type.indexOf("select:")==0)){
							 var dictCodes=null;
							 var selectCode=column.selectCode||"id";
							 if(column.type.indexOf("select:")==0) {
								 dictCodes=column.type.replace("select:","");
							 }else {
								 dictCodes=column.formatter.replace("dictCode:","");
							 }
			        		var arr=dictCodes.split(",");
			        		var tt=[];
			        		tt.push('<option value="">请选择</option>')
			        		for(var i=0;i<arr.length;i++) {
			        			var dictCode=arr[i];
			        			var obj=tools.getValueForDict(dictCode);
			        			for(var j=0;j<obj.length;j++) {
			            			tt.push('<option value="'+obj[j][selectCode]+'" '+(obj[j][selectCode]==value? "selected" : " ")+' >'+obj[j]["name"]+'</option>');
			            		}
			        		}
						 str='<div class="form-group col-xs-4 '+css_hide+'">'+
	              			'<div class="input-group">'+
	              				'<div class="input-group-addon">'+column.title+'</div>'+
	              				'<select name="'+(column.field)+'" class="'+options.searchClass+' form-control" >'+tt.join("")+'</select>'+
	              			'</div>'+
	              		'</div>';
						 
					 }else if(column.type&&column.type.indexOf("dialog:")==0){ // 弹出框
						 var att=column.type.replace("dialog:","");
						 //{chooseCode:'',chooseField:'',chooseWidth:'',chooseHeight:''}
						 var chooseObj=eval('('+att+')');
						 str='<div class="form-group col-xs-4 '+css_hide+'">'+
		              			'<div class="input-group form-control-1">'+
		              				'<div class="input-group-addon">'+column.title+'</div>'+
		              				'<input type="hidden" name="'+(column.field)+'" id="'+(column.field+"_"+options.searchDiv)+'"  class="'+options.searchClass+'" />'+
		              				'<input type="text" readonly id="'+(column.field+"_"+options.searchDiv)+'_n_i_j_k" placeholder="'+(column.placeholder ? column.placeholder : "")+'" class="form-control choose choose_search" '+
		              				' chooseCode="'+chooseObj.chooseCode+'"  chooseField="'+(chooseObj.chooseFiled||"id,name")+'" chooseId="'+(column.field+"_"+options.searchDiv)+'" '+
		              					'chooseValue="'+(column.field+"_"+options.searchDiv)+'_n_i_j_k" chooseData=\''+chooseObj.chooseData+'\' chooseWidth="'+chooseObj.chooseWidth+'" chooseHeight="'+chooseObj.chooseHeight+'"/>'+
		              			'</div>'+
		              		'</div>';
					 }else if(column.autoComplete){ // 自动补全框
						 str='<div class="form-group col-xs-4 '+css_hide+'">'+
		              			'<div class="input-group form-control-1">'+
		              				'<div class="input-group-addon">'+column.title+'</div>'+
		              				'<input type="hidden" name="'+(column.field)+'" id="'+(column.field+"_"+options.searchDiv)+'"  class="'+options.searchClass+'" />'+
		              				'<input type="text"  id="'+(column.field+"_"+options.searchDiv)+'_n_i_j_k" placeholder="'+(column.placeholder ? column.placeholder : "")+'" class="form-control " '+
		              				'"   chooseId="'+(column.field+"_"+options.searchDiv)+'" '+
		              					'chooseValue="'+(column.field+"_"+options.searchDiv)+'_n_i_j_k" />'+
		              			'</div>'+
		              		'</div>';
					 }else {
						 str='<div class="form-group col-xs-4 '+css_hide+'">'+
                         			'<div class="input-group">'+
                         				'<div class="input-group-addon">'+column.title+'</div>'+
                         				'<input type="text" name="'+(column.field)+'" placeholder="'+(column.placeholder ? column.placeholder : "")+'" class="'+options.searchClass+' form-control" />'+
                         			'</div>'+
                         		'</div>';
					 }
					 return str;
				 };
				 var columns=options.columns;
				 var searchColumnInputs=[];
				 var index__=0;
				 var autocom=[];
				 var temp_columns=[];
				 for(var n=0;n<columns.length;n++) {
					 temp_columns.push(columns[n])
				 }
				 temp_columns.sort(  
	                function(a, b)  
	                {  
	                	a.seq=a.seq||1000;
	                	b.seq=b.seq||1000;
	                    if(a.seq < b.seq) return -1;  
	                    if(a.seq > b.seq) return 1;  
	                    return 0;  
	                }  
	            );  
				 for(var i=0;i<temp_columns.length;i++) {					
					 var column=temp_columns[i];
					 if(column.searchable) {
						 var css_hide=index__>(searchRowNum-1) ? "search_con_hide" : "";
						 if(column.searchInput)
							 searchColumnInputs.push(column.searchInput);
						 else 
							 searchColumnInputs.push(makeSearchInput(column,css_hide));
						 index__++;
					 }
					 if(column.autoComplete) {
						 autocom.push({key:column.field,value:column.autoComplete,data:column.filter});
					 }
				 }
				 searchColumnInputs.push(makeSearchButton());
				 //alert(options.searchDiv);
				 $("#"+options.searchDiv).addClass("form-horizontal");
				 $("#"+options.searchDiv).html(searchColumnInputs.join(""));
				 
				 for(var i=0;i<autocom.length;i++) {
					 var atc=autocom[i];
					 var obj=$("#"+atc.key+"_"+options.searchDiv+"_n_i_j_k");
					 $(obj).inputComplete(
							 {
								 code:atc.value,
								 seq:i,
								 param:["id","name"],
								 data:atc.data,
								 callback:function(key,value,seq) {
									 var temp=$("#"+autocom[seq].key+"_"+options.searchDiv+"_n_i_j_k");
									 var chooseId=$(temp).attr("chooseId");
									 var chooseValue=$(temp).attr("chooseValue");
									 $("#"+chooseId).val(key);
									 $("#"+chooseValue).val(value);
								 }
							 }
					 );
				 }
				 
				 $(".date").popDate();
				 $(".search_con_hide").hide();
				 $(".search_con_more_"+id_table).on("click",function(){
					 $("#"+options.searchDiv).find(".search_con_hide").toggle();
					 if($(this).find(".more-down").length>0) {
						 $(this).find(".more-down").removeClass("more-down").addClass("more-up").attr("title","隐藏更多查询条件");
					 }else {
						 $(this).find(".more-up").removeClass("more-up").addClass("more-down").attr("title","显示更多查询条件");
					 }
				});
				// 定义选择器的函数
				 
					$("#"+options.searchDiv+" .choose").on("click",function() {
						var chooseCode=$(this).attr("chooseCode");
						var chooseId=$(this).attr("chooseId");
						var chooseWidth=$(this).attr("chooseWidth");
						var chooseData=$(this).attr("chooseData");
						var chooseHeight=$(this).attr("chooseHeight");
						var chooseValue=$(this).attr("chooseValue");
						var chooseField=$(this).attr("chooseField");
						var defaultField=["id","name"];
						var obj=choose[chooseCode];
						var url=obj.url;
						if(chooseData) {
							var chooseData_=eval('('+chooseData+')');
							url=tools.joinUrl(url, chooseData_);
						}
						
						tools.dialog({
								name:obj.name,
								width:chooseWidth,
								height:chooseHeight,
								url:url
							});
						//if(!window.setValue) {
							// 给页面赋值
							window.setValue=function(data) {
								if(chooseField) {
									defaultField=chooseField.split(",");
								}
								var id=[];
								var name=[];
								for(var i=0;i<data.length;i++) {
									id.push(data[i][defaultField[0]]);
									var tt=[];
									for(var j=0;j<defaultField[1].split("|").length;j++){
										tt.push(data[i][defaultField[1].split("|")[j]]);
									}
									name.push(tt.join("|"));
								}
								if($("#"+chooseId).is("textarea")) {
									$("#"+chooseId).html(id.join(","));
								}else
									$("#"+chooseId).val(id.join(","));
								
								if($("#"+chooseValue).is("textarea")) {
									$("#"+chooseValue).html(name.join(","));	
								}else
									$("#"+chooseValue).val(name.join(","));	
								
								tools.addChooseDelete(chooseId, chooseValue, $("#"+chooseValue).parent().width()-3,4);
								if($("#"+chooseValue).next().is("label")) {
									$("#"+chooseValue).next().css("display","none");
								}
							};
							// 获取选择的值
							window.getValue=function() {
								var data=[];
								if(chooseField) {
									defaultField=chooseField.split(",");
								}
								var ids=$("#"+chooseId).is("textarea") ? $("#"+chooseId).html() : $("#"+chooseId).val();
								if(!ids)
									return data;
								var names=$("#"+chooseValue).is("textarea") ? $("#"+chooseValue).html() : $("#"+chooseValue).val();
								
								var idArr=ids.split(",");
								var nameArr=names.split(",");
								
								for(var i=0;i<idArr.length;i++) {
									var temp={};
									temp[defaultField[0]]=idArr[i];
									temp[defaultField[1]]=nameArr[i];
									data.push(temp);
								}
								return data;
							};
						//}
					})
			 }
	 });
})(jQuery);
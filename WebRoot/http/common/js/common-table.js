/**
	表格处理
*/
(function($){      
	$.fn.extend({      
			// 初始化表单数据
			initTable:function(opt){
				  var obj=$(this);
				  var arr=[];
				  var setting={
						name:"",// 表名，是中文
						id:null,  // id为空时自动赋值
						data:null,  //需要填充的数据
						column:[], // 需要填充的列以及列名，例如[{"title":"性别","prop":"sex","inputcss":"required","dictCode":"sex","hidden":false,"defalutValue":""}]
						tablecss:"table  text-center", // 表样式
						trheadcss:"active", // 标题行样式
						trcss:null, // 行样式
						tdheadcss:null, // 标题行格样式
						tdcss:"table-td-css", // 格样式
						edit:true// 是只读还是可编辑，默认为可编辑
				  }				
				  $.extend(true,setting,opt);
				  $(this).data("setting",setting);
				  var tablecss=setting.tablecss;
				  var trheadcss=setting.trheadcss;
				  var tdheadcss=setting.tdheadcss;
				  var trcss=setting.trcss;
				  var edit=setting.edit;
				  var tdcss=setting.tdcss;
				  var id=setting.id||(new Date().getTime()+"_tb_n");
				  $(this).data("tableId",id);
				  var column=setting.column;
				  var data=setting.data;
				  
				  if(!column) {
					  $(obj).html(arr.join(""));
					  return;
				  }
				  if(!data) {
					  $(obj).html(arr.join(""));
					  return;
				  }
				  var titles=[];
				  var props=[];
				  var hidden_arr=[];
				  $.each(column,function(i) {
					  titles.push(this.title);
					  props.push(this.prop);
					  if(this.hidden) {
						  hidden_arr.push(this.prop);
					  }
				  })
				 var tdwidth=100/props.length;
				 // 拼装table
				  arr.push("<table class='"+(tablecss||'')+"' id='"+id+"'>");
				  arr.push("<tr class='trheadcss "+(trheadcss||'')+"'>");
				  
				  for(var i=0;i<titles.length;i++) {
					  var hidden="";
					  if(tools.contain(hidden_arr,props[i])) {
						  hidden="display:none;";
					  }
					  arr.push("<th  class='"+(tdheadcss||'')+"' style='width:"+tdwidth+"%;"+hidden+"' prop='"+props[i]+"'>"+titles[i]+"</th>");
				  }				  
				  if(edit)
					  arr.push("<th style='width:30px;'  class='"+(tdheadcss||'')+"' ><a href='javascript:void(0);' ><span class='glyphicon glyphicon-plus' aria-hidden='true' style='margin-right:5px;color: #80b448;position: relative;right: 0px;top: 0px;'></span></a></th>");
		  		  arr.push("</tr>");
				  //alert(arr);
				  for(var i=0;i<data.length;i++) {
					  arr.push("<tr class='trdatacss "+(trcss||'')+"'>");
					  for(var j=0;j<props.length;j++) {
						  var hidden="";
						  if(tools.contain(hidden_arr,props[j])) {
							  hidden="display:none;";
						  }
						  if(edit) {// 判断是否只读
							  //alert(tools.objToJson(column[j]));
							  var inputcss=column[j].inputcss||'';
							  var dictCode=column[j].dictCode||'';
							  var chooseCode=column[j].chooseCode||'';
							  var type=column[j].type||'';
							  if (type && type.indexOf("date:")==0) {// 日期选择
								  var format = type.replace('date:', '') || "yyyy-MM-dd";
								  var value = (data[i][props[j]]||column[j].defalutValue||"");
								  var dateValue = "";
								  if (value)
									  dateValue = new Date(value).format(format);
								  arr.push("<td class='"+(tdcss||'')+"' prop='"+props[j]+"' style='"+hidden+"' >");
								  arr.push('<input readonly="readonly"  type="text" name="'+new Date().getTime()+'ew_'+j+'" class="form-control date'+(inputcss||"")+'" value="' + dateValue + '" format="'+format+'"/>');
								  arr.push("</td>");
							  }else if(type && type.indexOf("dialog:")==0){// 弹出选择器
								 var att=type.replace("dialog:","");
								 var chooseObj = eval('('+att+')');
								 var chooseId = new Date().getTime()+'ew_'+j;
								 var chooseName = new Date().getTime()+'ew_'+j+"_name";
								 var nameFiled = chooseObj.nameFiled;
								 str='<div class="form-group'+(tdcss||"")+'>'+
				              			'<div class="input-group form-control-1">'+
				              				'<input type="hidden" name="'+chooseId+'" id="'+chooseId+'" value="' + (data[i][props[j]]||column[j].defalutValue||"") + '" />'+
				              				'<input type="text" readonly id="'+chooseName+'" class="form-control choose choose_search" '+
				              				' value="' + (data[i][nameFiled]||column[j].defalutValue||"") + '"'+
				              				' chooseCode="'+chooseObj.chooseCode+'"  chooseField="'+(chooseObj.chooseFiled||"id,name")+'" chooseId="'+chooseId+'" '+
				              					'chooseValue="'+chooseName+'" chooseData=\''+chooseObj.chooseData+'\' chooseWidth="'+chooseObj.chooseWidth+'" chooseHeight="'+chooseObj.chooseHeight+'"/>'+
				              			'</div>'+
				              		'</div>';
								  arr.push("<td class='"+(tdcss||'')+"' style='"+hidden+"'  prop='"+props[j]+"'>");
								  arr.push(str);
								  arr.push("</td>");
							  }else if(type && type.indexOf("textarea:")==0){// 文本域
								  var att=type.replace("textarea:","");
									 var textareaObj = eval('('+att+')');
									 var rows = textareaObj.rows || 3;
									 var cols = textareaObj.cols || 6;
									 var twidth = textareaObj.width || '100px';
									 arr.push("<td class='"+(tdcss||'')+"' prop='"+props[j]+"' style='"+hidden+"' title='"+data[i][props[j]]+"'>" +
									 		"<label style='display:none;'>"+setting.name+titles[j]+"</label>" +
							 				"<textarea name='"+id+i+j+"' class='form-control "+(inputcss||'')+"' rows='" + rows + "' cols='" + cols + "' style='width:" + twidth + ";'>" +
							 				(data[i][props[j]]||column[j].defalutValue||"") +
					 						"</textarea></td>");
							  }else{
								  if(dictCode){
									  var dicts=tools.getValueForDict(dictCode);
									  arr.push('<td class="'+(tdcss||'')+'"  style="'+hidden+'"  prop="'+props[j]+'"><select name="'+id+i+j+'"  class="form-control '+(inputcss||'')+'" >');						  
									  arr.push("<option value=''>请选择</option>");
									  for(var k=0;k<dicts.length;k++) {
										  arr.push("<option value='"+dicts[k].id+"' "+(data[i][props[j]]==(dicts[k].id||column[j].defalutValue) ? "selected" : " ")+">"+dicts[k].name+"</option>");
									  }
									  arr.push("</select></td>");
								  }else {
									  arr.push("<td class='"+(tdcss||'')+"' style='"+hidden+"'  prop='"+props[j]+"'><label style='display:none;'>"+setting.name+titles[j]+"</label><input name='"+id+i+j+"' class='form-control "+(inputcss||'')+"' value='"+(data[i][props[j]]||column[j].defalutValue||"")+"' /></td>");
								  }
							  }
						  }else {
							  var dictCode=column[j].dictCode||'';
							  var chooseCode=column[j].chooseCode||'';
							  var type=column[j].type||'';
							  if (type && type.indexOf("date:")==0) {// 日期选择
								  var format = type.replace('date:', '') || "yyyy-MM-dd";
								  var value = (data[i][props[j]]||column[j].defalutValue||"");
								  var dateValue = "";
								  if (value)
									  dateValue = new Date(value).format(format);
								  arr.push("<td class='"+(tdcss||'')+"' prop='"+props[j]+"' style='"+hidden+"' >");
								  arr.push('<input readonly="readonly"  type="text" name="'+new Date().getTime()+'ew_'+j+'" class="form-control '+(inputcss||"")+'" value="'+dateValue+'" format="'+format+'"/>');
								  arr.push("</td>");
							  }else if(type && type.indexOf("dialog:")==0){// 弹出选择器
								 var att=type.replace("dialog:","");
								 var chooseObj = eval('('+att+')');
								 var chooseId = new Date().getTime()+'ew_'+j;
								 var chooseName = new Date().getTime()+'ew_'+j+"_name";
								 str='<div class="form-group'+(tdcss||"")+'>'+
				              			'<div class="input-group form-control-1">'+
				              				'<input type="hidden" name="'+chooseId+'" id="'+chooseId+'" value="'+(data[i][props[j]]||column[j].defalutValue||"")+'"/>'+
				              				'<input type="text" readonly id="'+chooseName+'" class="form-control choose_search" value="'+(data[i][props[j]]||column[j].defalutValue||"")+'"/>'+
				              			'</div>'+
				              		'</div>';
								  arr.push("<td class='"+(tdcss||'')+"' style='"+hidden+"'  prop='"+props[j]+"'>");
								  arr.push(str);
								  arr.push("</td>");
							  }else{
								  if(dictCode){// 数据字典下拉框
									  var dicts=tools.getValueForDict(dictCode);
									  arr.push('<td class="'+(tdcss||'')+'" prop="'+props[j]+'"  style="'+hidden+'" ><select class="form-control"  disabled>');						  
									  arr.push("<option value=''>请选择</option>");
									  for(var k=0;k<dicts.length;k++) {
										  arr.push("<option value='"+dicts[k].id+"' "+(data[i][props[j]]==(dicts[k].id||column[j].defalutValue) ? "selected" : " ")+">"+dicts[k].name+"</option>");
									  }
									  arr.push("</select></td>");
								  }else{
									  arr.push("<td class='"+(tdcss||'')+"' prop='"+props[j]+"'  style='"+hidden+"'  title='"+data[i][props[j]]+"'>"+(data[i][props[j]]||column[j].defalutValue||"")+"</td>");
								  }
							  }
						  }
					  }
					  if(edit) {
						  arr.push("<td style='width:30px;'  class='"+(tdcss||'')+"' ><a href='javascript:void(0);' onclick='$(this).parents(\"tr:eq(0)\").remove()'><span class='glyphicon glyphicon-minus' aria-hidden='true' style='margin-right:5px; color:#F00;position: relative;right: 0px;top: 0px;'></span></a></td>");
					  
					  }arr.push("</tr>")
				  }
				  arr.push("</table>");
				  $(obj).html(arr.join(""));
				  
				  $("#"+id).find(".trheadcss th:last a").on("click",function(){
					  $(obj).addTableTr();
				  })
				  // 日期组件
				  $(".date").each(function(){
					  // yyyy-MM-dd HH:mm:ss 
					  var dateFormat=$(this).attr("format");
					  $(this).popDate({ format:dateFormat||'yyyy-MM-dd' });
				  });
				  // 定义选择器的函数
				  var tableId = $(this).data("tableId");
				  $("#" + tableId + " .choose").unbind("click");// 移除事件，防止已经生成的选择器元素多次重复绑定
				  bindChooseClick(tableId);
				  return;
			},
			// 获取表单数据，props如果为空则获取所有属性，如果指定了数组列，则获取指定列属性
			getTableData:function(props) {
				var tableId=$(this).data("tableId");
				if(!tableId) {
					return;
				}
				var table=$("#"+tableId);
				var setting=$(this).data("setting");
				var column=setting.column;
				var edit=setting.edit;
				var trs=$(table).find(".trdatacss");
				var data=[];
				if(trs.length>0) {
					for(var i=0;i<trs.length;i++) {
						var tr=trs[i];
						var tds=$(tr).find("td");
						if(tds.length>0) {
							var temp={};
							var index=0;
							var empty=0;
							for(var j=0;j<tds.length;j++) {								
								var td=tds[j];
								var prop=$(td).attr("prop");
								//alert(prop);
								if(prop&&((!props)||tools.contain(props, prop))) {
									var value=null;
									index++;
									var dictCode=column[j].dictCode;
									var type=column[j].type || '';
									if(type && type.indexOf("textarea:")==0){// 文本域
										value=$(td).find("textarea").val();
									}else if(dictCode) {
										value=$(td).find("select").val();
									}else {
										if(edit)
											value=$(td).find("input").val();
										else {
											value=$(td).html();
										}
									}
									// 判断是否为空
									if(!value)
										empty++;
									temp[prop]=value;
								}
							}
							if(index>=0&&index>empty)
								data.push(temp);
						}
						
					}
				}
				if(data.length>0) {
					return tools.objToJson(data);
				}
				return null;
			},
			// 新增一行
			addTableTr:function() {
				var tableId=$(this).data("tableId");
				if(!tableId) {
					return;
				}
				var table=$("#"+tableId);
				var setting=$(this).data("setting");
				var arr=[];
				var trcss=setting.trcss;
				var tdcss=setting.tdcss;
				var inputcss=setting.inputcss;
				var column=setting.column;
				var edit=setting.edit;
				if(!column) {
					  $(table).append(arr.join(""));
					  return;
				}
				var titles=[];
			    var props=[];
			    var hidden_arr=[];
			    $.each(column,function(i) {
				  titles.push(this.title);
				  props.push(this.prop);
				  if(this.hidden) {
					  hidden_arr.push(this.prop);
				  }
			    })
				arr.push("<tr class='trdatacss "+(trcss||'')+"'>")
				for(var j=0;j<props.length;j++) {
					  var inputcss=column[j].inputcss||'';
					  var dictCode=column[j].dictCode||'';
					  var hidden="";
					  if(tools.contain(hidden_arr,props[j])) {
						  hidden="display:none;";
					  }
					 /* if(!dictCode)
						  arr.push("<td class='"+(tdcss||'')+"' prop='"+props[j]+"' style='"+hidden+"' ><label style='display:none;'>"+setting.name+titles[j]+"</label><input name='"+new Date().getTime()+"ew_"+j+"' class='form-control "+(inputcss||'')+"' value='"+(column[j].defalutValue||'')+"'/></td>");
					  else {
						  var dicts=tools.getValueForDict(dictCode);
						  arr.push('<td class="'+(tdcss||'')+'" prop="'+props[j]+'"  style="'+hidden+'"><select name="'+new Date().getTime()+"ew_"+j+'" class="form-control '+(inputcss||'')+'">');						  
						  arr.push("<option value=''>请选择</option>");
						  for(var k=0;k<dicts.length;k++) {
							  arr.push("<option value='"+dicts[k].id+"' "+(dicts[k].id==(column[j].defalutValue||"") ? "selected" : " ")+">"+dicts[k].name+"</option>");
						  }
						  arr.push("</select></td>");
					  }*/
					  
					  var chooseCode=column[j].chooseCode||'';
					  var type=column[j].type||'';
					  if (type && type.indexOf("date:")==0) {// 日期选择
						  var format = type.replace('date:', '') || "yyyy-MM-dd";
						  arr.push("<td class='"+(tdcss||'')+"' prop='"+props[j]+"' style='"+hidden+"' >");
						  arr.push('<input readonly="readonly"  type="text" name="'+new Date().getTime()+'ew_'+j+'" class="form-control date'+(inputcss||"")+'" value="'+(column[j].defalutValue||"")+'" format="'+format+'"/>');
						  arr.push("</td>");
					  }else if(type && type.indexOf("dialog:")==0){// 弹出选择器
						  var att=type.replace("dialog:","");
						 var chooseObj = eval('('+att+')');
						 var chooseId = new Date().getTime()+'ew_'+j;
						 var chooseName = new Date().getTime()+'ew_'+j+"_name";
						 str='<div class="form-group'+(tdcss||"")+'>'+
		              			'<div class="input-group form-control-1">'+
		              				'<input type="hidden" name="'+chooseId+'" id="'+chooseId+'"/>'+
		              				'<input type="text" readonly id="'+chooseName+'" class="form-control choose choose_search" '+
		              				' chooseCode="'+chooseObj.chooseCode+'"  chooseField="'+(chooseObj.chooseFiled||"id,name")+'" chooseId="'+chooseId+'" '+
		              					'chooseValue="'+chooseName+'" chooseData=\''+chooseObj.chooseData+'\' chooseWidth="'+chooseObj.chooseWidth+'" chooseHeight="'+chooseObj.chooseHeight+'"/>'+
		              			'</div>'+
		              		'</div>';
						  arr.push("<td class='"+(tdcss||'')+"' style='"+hidden+"'  prop='"+props[j]+"'>");
						  arr.push(str);
						  arr.push("</td>");
					  }else if(type && type.indexOf("textarea:")==0){// 文本域
						  var att=type.replace("textarea:","");
							 var textareaObj = eval('('+att+')');
							 var rows = textareaObj.rows || 3;
							 var cols = textareaObj.cols || 6;
							 var twidth = textareaObj.width || '100px';
							 arr.push("<td class='"+(tdcss||'')+"' prop='"+props[j]+"' style='"+hidden+"' >" +
							 		"<label style='display:none;'>"+setting.name+titles[j]+"</label>" +
					 				"<textarea name='"+new Date().getTime()+'ew_'+j+"' rows='" + rows + "' cols='" + cols + "' style='width:" + twidth + ";' class='form-control "+(inputcss||'')+"'>" +
					 				(column[j].defalutValue||'') +
			 						"</textarea></td>");
					  }else{
						  if(dictCode){// 数据字典下拉框
							  var dicts=tools.getValueForDict(dictCode);
							  arr.push('<td class="'+(tdcss||'')+'" prop="'+props[j]+'"  style="'+hidden+'"><select name="'+new Date().getTime()+'ew_'+j+'" class="form-control '+(inputcss||'')+'">');						  
							  arr.push("<option value=''>请选择</option>");
							  for(var k=0;k<dicts.length;k++) {
								  arr.push("<option value='"+dicts[k].id+"' "+(dicts[k].id==(column[j].defalutValue||"") ? "selected" : " ")+">"+dicts[k].name+"</option>");
							  }
							  arr.push("</select></td>");
						  }else{
							  arr.push("<td class='"+(tdcss||'')+"' prop='"+props[j]+"' style='"+hidden+"' ><label style='display:none;'>"+setting.name+titles[j]+"</label><input name='"+new Date().getTime()+'ew_'+j+"' class='form-control "+(inputcss||'')+"' value='"+(column[j].defalutValue||'')+"'/></td>");
						  }
					  }
				}				
				if(edit) {
					arr.push("<td style='width:30px;'  class='"+(tdcss||'')+"' style='"+hidden+"'><a href='javascript:void(0);' onclick='$(this).parents(\"tr:eq(0)\").remove()'><span class='glyphicon glyphicon-minus' aria-hidden='true' style='margin-right:5px; color:#F00;position: relative;right: 0px;top: 0px;'></span></a></td>");
				}
				arr.push("</tr>");
				$(table).append(arr.join(""));
				// 日期组件
				$(".date").each(function(){
					// yyyy-MM-dd HH:mm:ss 
					var dateFormat=$(this).attr("format");
					$(this).popDate({ format:dateFormat||'yyyy-MM-dd' });
				});
				// 定义选择器的函数
				$("#" + tableId + " .choose").unbind("click");// 移除事件，防止已经生成的选择器元素多次重复绑定
				bindChooseClick(tableId);
			},
			// 删除一行 ,index为第几行
			removeTableTr:function(index) {
				index=index-1;
				var tableId=$(this).data("tableId");
				if(!tableId) {
					return;
				}
				var table=$("#"+tableId);
				var trs=$(table).find(".trdatacss");
				if(trs.length<index) { // 移除最后一行
					$(trs[trs.length-1]).remove();
				}else {
					$(trs[index]).remove();
				}
			}
	})      
})(jQuery);


function bindChooseClick(tableId){
	$("#" + tableId + " .choose").on("click",function() {
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
})
}

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
							  if(!dictCode)
								  arr.push("<td class='"+(tdcss||'')+"' style='"+hidden+"'  prop='"+props[j]+"'><label style='display:none;'>"+setting.name+titles[j]+"</label><input name='"+id+i+j+"' class='form-control "+(inputcss||'')+"' value='"+(data[i][props[j]]||column[j].defalutValue||"")+"' /></td>");
							  else {
								  var dicts=tools.getValueForDict(dictCode);
								  arr.push('<td class="'+(tdcss||'')+'"  style="'+hidden+'"  prop="'+props[j]+'"><select name="'+id+i+j+'"  class="form-control '+(inputcss||'')+'" >');						  
								  arr.push("<option value=''>请选择</option>");
								  for(var k=0;k<dicts.length;k++) {
									  arr.push("<option value='"+dicts[k].id+"' "+(data[i][props[j]]==(dicts[k].id||column[j].defalutValue) ? "selected" : " ")+">"+dicts[k].name+"</option>");
								  }
								  arr.push("</select></td>");
							  }
						  }else {
							  var dictCode=column[j].dictCode||'';
							  if(!dictCode)
								  arr.push("<td class='"+(tdcss||'')+"' prop='"+props[j]+"'  style='"+hidden+"'  title='"+data[i][props[j]]+"'>"+(data[i][props[j]]||column[j].defalutValue||"")+"</td>");
							  else {
								  var dicts=tools.getValueForDict(dictCode);
								  arr.push('<td class="'+(tdcss||'')+'" prop="'+props[j]+'"  style="'+hidden+'" ><select class="form-control"  disabled>');						  
								  arr.push("<option value=''>请选择</option>");
								  for(var k=0;k<dicts.length;k++) {
									  arr.push("<option value='"+dicts[k].id+"' "+(data[i][props[j]]==(dicts[k].id||column[j].defalutValue) ? "selected" : " ")+">"+dicts[k].name+"</option>");
								  }
								  arr.push("</select></td>");
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
									if(dictCode) {
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
					  if(!dictCode)
						  arr.push("<td class='"+(tdcss||'')+"' prop='"+props[j]+"' style='"+hidden+"' ><label style='display:none;'>"+setting.name+titles[j]+"</label><input name='"+new Date().getTime()+"ew_"+j+"' class='form-control "+(inputcss||'')+"' value='"+(column[j].defalutValue||'')+"'/></td>");
					  else {
						  var dicts=tools.getValueForDict(dictCode);
						  arr.push('<td class="'+(tdcss||'')+'" prop="'+props[j]+'"  style="'+hidden+'"><select name="'+new Date().getTime()+"ew_"+j+'" class="form-control '+(inputcss||'')+'">');						  
						  arr.push("<option value=''>请选择</option>");
						  for(var k=0;k<dicts.length;k++) {
							  arr.push("<option value='"+dicts[k].id+"' "+(dicts[k].id==(column[j].defalutValue||"") ? "selected" : " ")+">"+dicts[k].name+"</option>");
						  }
						  arr.push("</select></td>");
					  }
				}				
				if(edit) {
					arr.push("<td style='width:30px;'  class='"+(tdcss||'')+"' style='"+hidden+"'><a href='javascript:void(0);' onclick='$(this).parents(\"tr:eq(0)\").remove()'><span class='glyphicon glyphicon-minus' aria-hidden='true' style='margin-right:5px; color:#F00;position: relative;right: 0px;top: 0px;'></span></a></td>");
				}
				arr.push("</tr>");
				$(table).append(arr.join(""));
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
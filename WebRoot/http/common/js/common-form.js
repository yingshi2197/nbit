/**
 * form 表单中文化
 */
jQuery.extend(jQuery.validator.messages, {
    required: "必选字段",
	remote: "请修正该字段",
	email: "请输入正确格式的电子邮件",
	url: "请输入合法的网址",
	date: "请输入合法的日期",
	dateISO: "请输入合法的日期 (ISO).",
	number: "请输入合法的数字",
	digits: "只能输入整数",
	creditcard: "请输入合法的信用卡号",
	equalTo: "请再次输入相同的值",
	accept: "请输入拥有合法后缀名的字符串",
	maxlength: jQuery.validator.format("请输入一个长度最多是 {0} 的字符串或者  [0] 个汉字"),//{i}占位符表示取索引为i的值，[i]占位符表示取索引为i的值的一半
	minlength: jQuery.validator.format("请输入一个长度最少是 {0} 的字符串或者  [0] 个汉字"),
	rangelength: jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
	range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
	max: jQuery.validator.format("请输入一个最大为{0} 的值"),
	min: jQuery.validator.format("请输入一个最小为{0} 的值"),
	english:"只能输入英文、数字、下划线和.",
	phone:"请输入正确的电话号码",
	mobile:"请输入正确的手机号码",
	delSpace:"请不要输入空格",
	step:"请按照提示输入合法的值"
});

/**
 * 是否可以将验证规则写为class  样式类
 * @author vince.wei@noboll.com.cn
 */
jQuery.extend($.validator.classRuleSettings, {
	english: { english: true },
	phone: { phone: true },
	mobile: { mobile: true },
	delSpace: { delSpace: true }
});


jQuery.extend($.validator.format = function( source, params ) {
	if ( arguments.length === 1 ) {
		return function() {
			var args = $.makeArray( arguments );
			args.unshift( source );
			return $.validator.format.apply( this, args );
		};
	}
	if ( arguments.length > 2 && params.constructor !== Array  ) {
		params = $.makeArray( arguments ).slice( 1 );
	}
	if ( params.constructor !== Array ) {
		params = [ params ];
	}
	$.each( params, function( i, n ) {
		source = source.replace( new RegExp( "\\{" + i + "\\}", "g" ), function() {
			return n;
		});
		source = source.replace( new RegExp( "\\[" + i + "\\]", "g" ), function() {//[i]占位符取该占位符值的一半
			return n/2;
		});
	});
	return source;
});
/**
 * 验证规则
 * @author vince.wei@noboll.com.cn
 */
jQuery.extend($.validator.methods, {
    english:function( value, element ) {
    	//验证英文、数字、下划线和.
		return  this.optional( element ) || /^[A-Za-z0-9_\.]+$/.test( value );
	},
	 delSpace:function( value, element ) {
	    	//去除文本中所有空格
			return  this.optional( element ) || !(/\s/.test( value ));
		},
	phone:function( value, element ) {
		//电话号码   规则不带固定：短号、区号+直播号、区号+直播号+分机号、直播号+分机号、(+国际区号)+直播号、(+国际区号)+直播号+分机号等
		return  this.optional( element ) || /^[a-zA-Z\+]?[0-9]+[0-9-]+$/.test( value );
	},
	mobile:function( value, element ){
		//11位手机号 或者亲情3-5位短号 或者86|+86开头的大陆手机号码
		return  this.optional( element ) || tools.checkMobile(value)/*|| /\d{3,5}$/.test( value )*/;
	},
	step:function(value, element ){
		var _step = new Number($(element).attr("step"));
		var _value = new Number(value);
		
		if(_step<1)
			var s=(_value*10000000)/(_step*10000000); 
		else
			var s=_value/_step;
		return  s%1==0; 
	}
	
	
}); 


/**
 * 插件封装
 * @param $
 */
(function($) {
	 $.extend($.fn, {
		 /**
		  * 初始化表单方法，包含验证
		  */
		 	initForm:function(options) {
		 		options=options||{};
		 		var that=this;
		 		var loadingElement=options.loadingTarget ? $("#"+options.loadingTarget) : $(this);
		 		
		 		$(this).find("input").each(function(){
		 			if((!$(this).attr("maxlength"))&&(!$(this).is(":hidden"))) {
		 				if(!$(this).attr("readonly")) {
		 					$(this).attr("maxlength","100");
			 			} 
		 			}
		 			if(($(this).attr("maxlength"))&&($(this).is(":hidden")||$(this).attr("readonly"))) {
		 				$(this).removeAttr("maxlength");
		 			}
		 		})
		 		$(this).find("textarea").each(function(){
		 			if((!$(this).attr("maxlength"))&&(!$(this).is(":hidden"))) {
		 				if(!$(this).attr("readonly")) {
		 					$(this).attr("maxlength","1000");
			 			} 
		 			}
		 			if(($(this).attr("maxlength"))&&($(this).is(":hidden")||$(this).attr("readonly"))) {
		 				$(this).removeAttr("maxlength");
		 			}
		 		})
		 		var uuid=tools.uuid(40);
		 		var token='<input type="hidden" name="msb_token" id="msb_token" value="'+uuid+'" />';
		 		$(this).prepend(token);
		 		
		 		var options_default={
		 				type: 'post', // 提交方式 get/post
		 	            url: '', // 需要提交的 url
		 	            data:{},
		 	            closeDialog:true  // 提交成功后是否关闭对话框
		 		}
		 		$.extend(true,options_default,options);
		 	// 进度条显示
		 		if($(that).attr("enctype")=="multipart/form-data") {
		 			options_default.beforeSend_=function(){
						tools.initProgress(function(data){
							if($(".loading-indicator").length>0) {
								$(".loading-indicator").html('<progress style="display: block;width:100%;height:30px;" value="0" max="100" id="proDownFile"></progress>');
								$("#proDownFile").val(data.rate);
							}
						});
					}
		 		}
		 		
		 		options_default.success=function(data) {// data 保存提交后返回的数据，一般为 json 数据
 	                // 此处可对 data 作相关处理
 	            	//var success_str='<div class="alert alert-success">'+data.msg+'</div>';
 	            	//$(document.body).append(success_str);
		 			if(typeof data=="string") {
		 				data= eval('('+data+')');  
		 			}
		 			tiger=false;
		 			var png="error";
		 			if(data.type=="1") 
		 				png="success";
		 			var showSuccess=(options.successMsg!="false") ? true : false;
		 			if(png=="error"||(png=="success"&&showSuccess)) {
			 			if(options.wx==1) {
		 					tools.showDialog(data.msg,png,function(){
		 	            		if(data.type=="2")
		 	            			top.location.href = "manage/loginUI.do";
		 	            		if(options.close) {
		 	         				options.close(data);
		 	         			}
		 	            	});
		 				}else {
			 				tools.alert(data.msg,png,function(){
		 	            		if(data.type=="2")
		 	            			top.location.href = "manage/loginUI.do";
		 	            		if(options.close) {
		 	         				options.close(data);
		 	         			}
		 	            	});
		 				}
		 			}
		 			if(options.success) {
		 				options.success(data);
         			}
 	            	
 	            	$(loadingElement).hideLoading();
 	            	if(options_default.closeDialog&&data.type=="1") {
	 	            	var int=window.setInterval(function(){
	 	            		if(tiger) {
	 	            			if(options.success) {
	 	            				options.success(data);
	 	            			}
	 	            			
	 	            			if(tools.getDialogParent().operateTreeNode&&data.obj) {
	 	            				tools.getDialogParent().operateTreeNode(data.obj);
	 	            			}
	 	            			tiger=false;
	 	            			
	 	            			tools.closeDialog();
	 	            			//alert(1);
	 	            			window.clearInterval(int);
	 	            			
	 	            		}
	 	            	},50);
 	            	}else  {
 	            		if(options.success) {
            				options.success(data);
            			}
 	            	}
		 		}
		 		//alert(options_default.success);
		 		$(this).validate({     
		 			 showErrors:function(errors,list) {
		 				 if(list.length>0) {
		 					var errorStr=[];
		 					for(var i=0;i<list.length;i++) {
			 	        		   var obj=list[i];
			 	        		   var es=$(obj.element);
			 	        		   var labelText="";
			 	        		   var tep=2;
			 	        		   while(es.length>0&&tep!=0) {
			 	        			   var temp=es;
			 	        			   while(temp.length>0&&!temp.is("label")) {
			 	        				  temp=temp.prev();
			 	        			   }
			 	        			   if(temp.length>0) {
			 	        				  labelText=$(temp).html();
			 	        				  break;
			 	        			   }
			 	        			   es=$(es).parent();
			 	        			  tep--;
			 	        		   }
			 	        		  errorStr.push(labelText+":"+obj.message);
			 	        	   }
		 					if(options_default.customValidata) {
		 						var ttt=options_default.customValidata();
		 						if(ttt) {
		 							errorStr.push(ttt);
		 						}
		 					}
		 					if(options.wx==1) {
		 						tools.showDialog(errorStr.join("</br>"));
		 					}else {
		 						tools.alert(errorStr.join("</br>"));
		 					}
		 					 $(loadingElement).hideLoading();
		 				 }
		 	          },
		 			 submitHandler: function(form) 
		 			   {      
		 				 options_default.url=$(form).attr("action");
		 				 //debugger;
		 				 if( $(loadingElement).showLoading()) {
		 					 $(form).ajaxSubmit(options_default);     
		 				 }
		 			      return false;
		 			   }  
		 		 }).checkForm(); 
		 	}
	 });
})(jQuery);
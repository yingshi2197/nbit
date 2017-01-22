var allAccessUrl;// 获取所有当前用户可以访问的url
var allDict;  // 获取所有数据字典
var allUrl;//获取所有在数据库中保存的菜单
var allValidateRole;
var _time;
$.ajaxSetup ({
    cache: true // AJAX cache  下面加上时间后load的页面中的js、css图片等都会重新加载，    

      //加上这句action会重新加载，但是js、css、图片等会走缓存
 });
if(localStorage&&localStorage.getItem("sysData")) {
	var data=JSON.parse(localStorage.getItem("sysData"));  
	allDict = data["allDict"];
	allAccessUrl = data["allAccessUrl"];
	allUrl = data["allUrl"];
	_time=data["_time"];
	allValidateRole = data["allValidateRole"];
}else {
	$.ajax({
		url : "business/dict/queryAllDict.do",
		data : null,
		method : "post",
		async : false,
		success : function(data) {
			allDict = data["allDict"];
			allAccessUrl = data["allAccessUrl"];
			allUrl = data["allUrl"];
			_time=data["_time"];
			allValidateRole = data["allValidateRole"];
			if(localStorage)
				localStorage.setItem("sysData",JSON.stringify(data));
		}
	});
}

Date.prototype.format = function(format){
	var o = {
		"M+" : this.getMonth()+1, //month
		"d+" : this.getDate(), //day
		"h+" : this.getHours(), //hour
		"m+" : this.getMinutes(), //minute
		"s+" : this.getSeconds(), //second
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter
		"S" : this.getMilliseconds() //millisecond
	}
	
	if(/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	}
	
	for(var k in o) {
		if(new RegExp("("+ k +")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
		}
	}
	return format;
} 

var tiger=false;
/**
 * 选择器对象
 */
var choose={
		// demo选择器
		demoChoose:{name:"demo选择器",url:"demo/toChoose.do"},
		resumeChoose:{name:"简历选择器",url:"manage/resume/toChoose.do"} ,
		dictionaryTypeChoose:{name:"字典类型选择器",url:"manage/dictionaryType/toChoose.do"}, 
		// 字典选择器 chooseData='{type="0"}'  type 1表示多选，0表示单选，默认为0
		orgChoose:{name:"部门选择",url:"manage/org/toChoose.do"} ,
		// 部门选择器 chooseData='{type="0",filter:"1"}'  type 1表示多选，0表示单选，默认为0. filter 1表示查询部门及子部门，0表示查询所有,2表示查询子部门，默认为1
		//filterStatus:"1"过滤已选择的部门及该部门没有招聘专员
		userChoose:{name:"用户选择",url:"manage/user/toChoose.do"},
		// 用户选择器 chooseData='{type="0",filter:"1"}'  type 1表示多选，0表示单选，默认为0. filter 1表示查询部门及子部门人员，包含自己，0表示查询所有，
		// 默认为1，2表示查询部门及子部门人员，排除自己；roleFilter：角色过滤，多个角色code用,隔开
		customerChoose:{name:"客户选择器",url:"manage/customer/toChoose.do"},
		// 客户选择器 chooseData='{type="0",filter:"0"}'  type 1表示多选，0表示单选，默认为0. filter 0表示查询部门及子部门负责的客户，默认为0，1 表示查询所有客户
		// positionFilter 是否要过滤没有审批通过职位的客户，0为不过滤，1或者不传为过滤
		// xianchangFilter 过滤指定现场专员负责的客户
		urlChoose:{name:"菜单选择器",url:"manage/url/toChoose.do"},
		attachmentChoose:{name:"附件选择器",url:"manage/attachment/toChoose.do"},
		// 附件选择器 chooseData='{owner:""}'  owner简历拥有者，针对简历推送来说，默认查询简历拥有者的简历模板
		roleUrlChoose:{name:"角色菜单选择器",url:"manage/roleUrl/toChoose.do"},
		requirementChoose:{name:"需求选择器",url:"manage/requirement/toChoose.do"},
		// 需求选择器 chooseData='{type="0"}'  type 1表示多选，0表示单选，默认为0 ,hasPeopleGap:是否要过滤缺口人数为0的 auditStatus:招聘状态,filter:1表示要做常规招聘可以选择所有需求，尖刀招聘只能选择尖刀或者常规+尖刀的需求过滤
		requirementSimpleChoose:{name:"需求选择器",url:"manage/requirement/toSimpleChoose.do"},
		// 简单需求选择器(只显示基本字段，如薪资范围等字段不显示) chooseData='{type="0"}'  type 1表示多选，0表示单选，默认为0 ,hasPeopleGap:是否要过滤缺口人数为0的 auditStatus:招聘状态
		roleChoose:{name:"角色选择",url:"manage/role/toChoose.do"},
		// 部门选择器 chooseData='{type="0",filter:"1"}'  type 1表示多选，0表示单选，默认为0. filter 1表示查询角色及子角色，0表示查询所有,2表示查询子角色，默认为1
		custPositionChoose:{name:"客户职位选择器",url:"manage/dictionary/toCustPositionChoose.do"},
		// 岗位选择器customerId:客户id  type 1表示多选，0表示单选，默认为0
		positionChoose:{name:"职位选择",url:"business/position/toChoose.do?type=1"},// 岗位选择器parentCode:父级code  type 1表示多选，0表示单选，默认为0
		positionChooseRadio:{name:"职位选择",url:"business/position/toChoose.do?type=0"},// 岗位选择器parentCode:父级code  type 1表示多选，0表示单选，默认为0
		intentionChoose:{name:"意向地区选择",url:"business/dict/toChoose.do?parentCode=address&type=1"},// 意向地区选择器
		myResumeChoose:{name:"我的简历选择",url:"business/resume/toMyChoose.do?type=0"},// 我的简历选择器
		industryChoose:{name:"行业选择",url:"business/dict/toChoose.do?parentCode=industry&type=0"},// 行业选择器
		customerChoose:{name:"客户选择",url:"business/customer/toChoose.do?type=0"}// 客户选择器
}

// 定义自动填充路径
var autoComplete={
		userAutoComplete:"manage/user/userAutoComplete.do",
		dictAutoComplete:"manage/dictionary/dictAutoComplete.do",
		custAutoComplete:"manage/customer/custAutoComplete.do"
}

/**
 * 数据字典对象，如果这里有对应的key，直接获取这里的key，如果没有这直接通过
 * ajaxUrl根据typeCode进行异步获取数据字典
 */
var dictionary={
		ajaxUrl:"business/dict/queryByTypeCode.do",
		// 不要再在这里添加其他数据字典了，以后统一在msb/src/java/dictionary.properties中添加数据字典（不需要保存到数据库中的数据字典）
		role:[{"admin":"管理员","customer":"客户","applicant":"求职者"}],
		sex:[{"0":"男","1":"女"}]
}


/**
 * 提供工具类
 */

var tools={
		// 将对象编程json字符串
		objToJson:function(obj) {
	 		return JSON.stringify(obj);
	 	},
	 	jsonToObj:function(str){
	 		return JSON.parse(str);
	 	},
	 	getUserPhoto:function(sexName,photo) {
			//alert(photo);
			if(photo) {
				return {
					img : photo,
					name : sexName
				};
			}
			if (!sexName) {
				return {
					img : "http/business/home/images/tab_07.jpg",
					name : "男"
				};
			} else {
				if (sexName == "男")
					return {
						img : "http/business/home/images/tab_07.jpg",
						name : sexName
					};
				else {
					return {
						img : "http/business/home/images/tab_06.jpg",
						name : sexName
					};
				}
			}
		},
		loadUrl:function(obj,url) {
			$(obj).load(url,null,function(response,status,xhr){
				try{
					var data= eval('('+response+')');  
					tools.alert(data.msg,null,function(){
						window.top.location.reload();
					});
				}catch(e){
					
				}
			});
		},
	 	// 拼接url,obj={a:b,c:d};
	 	joinUrl:function(url,obj) {
	 		if(url.indexOf("_time=")==-1) {
	 			if(!obj) {
	 				obj={};
	 			}
	 			obj["_time"]=_time;
	 		}
	 		var param=[];
	 		if(obj) {
	 			$.each(obj,function(a,b){
	 				param.push("&"+a+"="+b);
	 			})
	 		}
	 		if(url.indexOf("?")!=-1) {
	 			url+=param.join("");
	 		}else {
	 			url+="?a=1"+param.join("");
	 		}
	 		return url;
	 	},
	 	// 判断对象是否为空
	 	isEmpty:function(obj) {
	 		if(null==obj||""==obj||undefined==obj)
	 			return true;
	 		return false;
	 	},
	 	// 打印日志
	 	log:function(str) {
	 		console.log(str);
	 	},
	 	 // 弹出对话框
		 // dialog:{name:name,url:url,close:close,width:width,height:height,id:id}
	 	dialog:function(dialog) {
	 		if(!tools.checkUrlPermission(dialog.url)) {
	 			tools.alert("您没有权限访问，请联系管理员！");
	 			return ;
	 		}
			 var api = frameElement ? frameElement.api : null;
			 var W=(api&&api.opener) ? api.opener : window;
			var setting={
					id:dialog.id||new Date().getTime(),
					content:"url:"+tools.joinUrl(dialog.url),
					lock:true,
					title:dialog.name,
					parent:api,
					p:window,
					width:dialog.width||'auto',
					height:dialog.height||'auto',
					init:null,
					// that, that.iframe.contentWindow, window
					close:function(_self,_parent){
						if(dialog.close) {
							dialog.close($(_parent.document),_parent);
						}
					}
			}
			W.$.dialog(setting);
		 },
		 closeDialog:function() {
			 try {
					return frameElement.api.close();
				}catch(e) {
					return window.close();
				}
		 },
		 // 获取弹出对话框的父窗口
		 getDialogParent:function() {
				if(frameElement==null) {
					return self;
				}
				var api = frameElement.api||null;
				if(api==null) {
					return self;
				}
				var w = api.opener;
				var list=w.$.dialog.list;
				var mydialog=[];
				$.each(list,function(i) {
					if(i.indexOf("JDG")==-1)
						mydialog.push(this.content);
				})
				var _dialog;
				if(mydialog.length==1)
					_dialog=w;
				else
					_dialog=mydialog[mydialog.length-2];
				return _dialog;
		 },
		 show:function(){
			 var str="<div id='loading-mask'></div>";
			 if($("#loading-mask").length==0) {
				 $("body").append(str);
			 }
			 $("#loading-mask").css("height",document.body.clientWidth>document.body.scrollHeight ? document.body.clientWidth : document.body.scrollHeight);
		 },
		 hide:function(){
			 $("#loading-mask").remove();
		 },
		 showDialog:function(msg,type,callback) {
			 var str='<div id="eject-box" class="eject-box" style="display: none;">'+
			 				'<div class="box-dialog">'+
						        '<div class="box-content">'+
						            '<div class="box-header">'+
						                '<h4 class="box-title">提示信息</h4>'+
						            '</div>'+
						            '<div class="box-body" id="box-body">'+
						            msg+
						            '</div>'+
						            '<div class="box-footer">'+
						                '<button class="box-btn" id="box-btn">好</button>'+
						            '</div>'+
						    '</div>'+
						    '</div>'+
						'</div>';
			var obj=$("body");
			 if($(obj).find("#eject-box").length==0) {
				 $(obj).append(str);
			 }else {
				 $(obj).find("#box-body").html(msg);
			 }
			 //$(obj).find("#eject-box").css("top",document.body.clientWidth/2);
			 tools.show();
			 $(obj).find("#eject-box").show();
			 $(obj).on("click","#box-btn",function(){
				 $(obj).find("#eject-box").hide();
				 tools.hide();
				 if(callback) {
					 callback();
				 }
			 })
		 },
	 	// 弹出消息框 type为类型 有error，success，info
	 	alert:function(str,type,callback) {
	 		var icon={};
	 		type=type||"info";
	 		icon["error"]='error.gif';
	 		icon["success"]='success.gif';
	 		icon["info"]='tips.gif';
	 		var api = frameElement ? frameElement.api : null;
			 var W=(api&&api.opener) ? api.opener : window;
			var setting={
					title:"提示信息",
					id:new Date().getTime(),
					content:str,
					lock:true,
					resize: false,
					ok: true,
					parent:api,
					p:window,
					width:'auto',
					height:'auto',
					icon: icon[type],
					fixed: true,
					init:null,
					// that, that.iframe.contentWindow, window
					close:function(_self,_parent){
						if(callback) {
							callback(_parent?$(_parent.document):_parent,_parent);
						}
						//tiger=true;
					}
			}
			W.$.dialog(setting);
	 	},
	 	checkMobile:function(value) {
	 		return /^1[3|4|5|7|8][0-9]\d{8}$/.test( value ) ||/^00852\d{8}$/.test(value);
	 	},
	 	// str是确认信息，yes是第一个按钮方法，yes2是第2个按钮方法，no是第三个按钮的方法，yesVal是第一个按钮名字，yes2Val是第2个按钮名字
	 	confirm:function(str,yes,no,yes2,yesVal,yes2Val) {
	 		var icon={};
	 		var api = frameElement ? frameElement.api : null;
			 var W=(api&&api.opener) ? api.opener : window;
			var setting={
					title:"确认",
					id:new Date().getTime(),
					content:str,
					lock:true,
					resize: false,
					parent:api,
					p:window,
					width:'auto',
					height:'auto',
					icon: "confirm.gif",
					fixed: true,
					okVal2: yes2Val,		// 确定按钮文本,默认'确定'
					okVal: yesVal,		// 确定按钮文本,默认'确定'
					init:null,
					ok: function(here){
						return yes.call(this, here);
					},
					ok2: yes2 && function(here){
						return  yes2.call(this, here);
					},
					cancel: function(here){
						return no && no.call(this, here);
					}
			}
			W.$.dialog(setting);
	 	},
	 	// ajax
	 	ajax:function(options){
	 		options.method=options.method||"post";
	 		if(options["url"]){
	 			if(!tools.checkUrlPermission(options["url"])) {
		 			tools.alert("您没有权限访问，请联系管理员！");
		 			return ;
		 		}
	 			options["url"]=tools.joinUrl(options["url"]);
	 		}
	 		if(!options["error"]){
	 			options["error"]=function(XMLHttpRequest, data, errorThrown) {
	 				if(typeof data=="string") {
		 				data= eval('('+data+')');  
		 			}
	 				var png="error";
 	            	tools.alert(data.msg,png);
	 			};
	 		}
	 		if(!options.dataType)
	 			options.dataType="json";
	 		var temp=options.success;
	 		options.success=function(data) {
	 			if(typeof data=="string") {
	 				data= eval('('+data+')');  
	 			}
	 			temp(data);
	 		}
	 		$.ajax(options);
	 	},
	 	getValueForDict:function(dictCode) {
	 		var obj=dictionary[dictCode];
	 		var ajaxObj=[];
	 		if(!obj) {
	 			//ajaxObj=allDict[dictCode];
	 			tools.ajax({
	 				url: dictionary["ajaxUrl"], 
					data: {typeCode:dictCode}, 
					async: false,
					success: function(data){
						//alert(tools.objToJson(data));
						ajaxObj=data;
					}	 				
	 			})
	 		}else {
	 			$.each(obj,function(i){
					$.each(this,function(a,b){
						ajaxObj.push({id:a,name:b})
					})
				})
	 		}
			
			return ajaxObj;
	 	},
	 	parseDate:function(str,format) {
	 		if(!str)
	 			return null;
	 		var number=parseInt(str);
	 		if(!format) {
	 			format="yyyy-MM-dd hh:mm:ss";
	 		}
	 		return new Date(number).format(format);
	 	},
	 	// * @param prop 默认为空时比较所有属性
	 	compare:function(obj1,obj2,prop){ // 比较2个对象是否属性的值都一致,相同为true,如果属性不一样多，建议将少属性的放前面
	 		if(!obj1) {
	 			if(!obj2) {
	 				return true;
	 			}
	 		}
	 		var isSame=true;
	 		if(prop) {
	 			for(var i=0;i<prop.length;i++) {
	 				var p=prop[i];
	 				if(obj1[p]!=obj2[p]) {
	 					return false;
	 				}
	 			}
	 		}else {
	 			$.each(obj1,function(a,b){
		 			if(b!=obj2[a]) {
		 				isSame= false;
		 			}
		 		})
	 		}
	 		
	 		
	 		return isSame;
	 	},
	 	/**
	 	 * 数组是否包含对象
	 	 * @param arr
	 	 * @param obj
	 	 * * @param prop 默认为空时比较所有属性
	 	 * @returns {Boolean}
	 	 */
	 	contain:function(arr,obj,prop) {
	 		if(!arr)
	 			return false;
	 		if(arr.length==0)
	 			return false;
	 		if(typeof obj=="string"){//字符串
	 			for(var i=0;i<arr.length;i++) {
 					var obj1=arr[i];
	 				if(obj==obj1) {
		 				return true;
		 			}
 				}
 			}else{
 				for(var i=0;i<arr.length;i++) {
 					var obj1=arr[i];
	 				if(tools.compare(obj, obj1,prop)) {
		 				return true;
		 			}
 				}
 			}
	 		return false;
	 	},
	 	/**
	 	 * 得到数组中对象的下标
	 	 * @param arr
	 	 * @param obj
	 	 * @param prop 默认为空时比较所有属性
	 	 * @returns {Number}
	 	 */
	 	getIndex:function(arr,obj,prop) {
	 		var index=-1
	 		if(!arr)
	 			return index;
	 		if(arr.length==0)
	 			return index;
	 		
	 		for(var i=0;i<arr.length;i++) {
	 			var obj1=arr[i];
	 			if(tools.compare(obj, obj1,prop)) {
	 				index=i;
	 				break;
	 			}
	 		}
	 		return index;
	 	},
	 	getLength : function (str) {
	 		if(!str)
	 			return 0;
	        ///<summary>获得字符串实际长度，中文2，英文1</summary>
	        ///<param name="str">要获得长度的字符串</param>
	        var realLength = 0, len = str.length, charCode = -1;
	        for (var i = 0; i < len; i++) {
	            charCode = str.charCodeAt(i);
	            if (charCode >= 0 && charCode <= 128) realLength += 1;
	            else realLength += 2;
	        }
	        return realLength;
	    },

	    //js截取字符串，中英文都能用  
	    //如果给定的字符串大于指定长度，截取指定长度返回，否者返回源字符串。  
	    //字符串，长度  

	    /** 
	     * js截取字符串，中英文都能用 
	     * @param str：需要截取的字符串 
	     * @param len: 需要截取的长度 
	     */
	    cutstr:function(str, len) {
	    	if(!str)
	    		return str;
	        var str_length = 0;
	        var str_len = 0;
	        str_cut = new String();
	        str_len = str.length;
	        for (var i = 0; i < str_len; i++) {
	            a = str.charAt(i);
	            str_length++;
	            if (escape(a).length > 4) {
	                //中文字符的长度经编码之后大于4  
	                str_length++;
	            }
	            str_cut = str_cut.concat(a);
	            if (str_length > len) {
	                str_cut = str_cut.concat("...");
	                return str_cut;
	            }
	        }
	        //如果给定字符串小于指定长度，则返回源字符串；  
	        if (str_length <= len) {
	            return str;
	        }
	    },
	    uuid:function(len,radix) {
	    	var chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".split(""),
	    	uuid = [],i;  
	        radix = radix || chars.length;  
	      
	        if (len) {  
	          // Compact form  
	          for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix];  
	        } else {  
	          // rfc4122,version 4 form  
	          var r;  
	      
	          // rfc4122 requires these characters  
	          uuid[8] = uuid[13] = uuid[18] = uuid[23] = "-";  
	          uuid[14] = "4";  
	      
	          // Fill in random data.  At i==19 set the high bits of clock sequence as  
	          // per rfc4122,sec. 4.1.5  
	          for (i = 0; i < 36; i++) {  
	            if (!uuid[i]) {  
	              r = 0 | Math.random()*16;  
	              uuid[i] = chars[(i == 19)?r & 0x3 | 0x8 : r];  
	            }  
	          }  
	        }  
	      
	        return uuid.join("");  
	    },
	    //得到下载的小图标  id为附件id
	    getDownIcoHtml:function(id){
	    	//判断是否有附件下载权限   有则显示下载图标  否则不显示
	    	if(!tools.checkUrlPermission("manage/attachment/download.do")){
	    		return "";
	    	}
	    	return "&nbsp;<a href=\"manage/attachment/download.do?id="+id+"\"  title=\"下载\" target=\"_blank\"><img src=\"http/business/attachment/down_ico.png\" /></a>";
	    },
	    //附件预览的超链接  附件id 和name len:截取长度
	    getpreAttachHtml:function(id,name,len){
	    	var showName="";
	    	if(len==name.length)//显示所有
	    		showName=name;
	    	else//截取
	    		showName=tools.cutstr(name, len);
	    	return "<a href=\"manage/preAttach/previewAttachment.do?id="+id+"\"  title=\""+name+"\" target=\"_blank\">"+showName+"</a>";
	    },
	    // 替换字符串中对应对象属性赋值
	    replaceField:function(str,obj) {
	    	if(!str)
	    		return str;
	    	$.each( obj, function( i, n ) {
	    		str = str.replace( new RegExp( "\\{" + i + "\\}", "g" ), function() {
	    			return n;
	    		});
	    	});
	    	return str;
	    },
	    // 动态生成表单
	    makeForm:function(url,param,target) {
	    	var str=[];
	    	var id=new Date().getTime()+"_form";
	    	str.push('<form action="'+url+'" id="'+id+'" style="display:none;" target="'+(target||"_blank")+'" method="post">');
	    	if(param) {
		    	$.each(param,function(i,n){
		    		str.push('<input type="hidden"  name="'+i+'"  value=\''+n+'\' />');
		    	})
	    	}
	    	str.push('</form>');
	    	//alert(str);
	    	$("body").prepend(str.join(""));
	    	return $("#"+id);
	    },
	    // 获取查询条件
		 getSearchCondition:function(searchClass,searchDiv) {
			 var query={};
			 $((searchDiv?"#"+searchDiv+" ":"")+"."+searchClass).each(function(){
				 var value=$(this).val();
				 var name=$(this).attr("name");
				 if(!tools.isEmpty(value)) {
						query[name]=value;
				 }
			 });
			 return tools.objToJson(query);
		 },
		 isHaveUrl:function(url){//判断url是否在数据库中保存
			/*if (!allUrl)
				return true;
			if (!url)
				return true;
			for (var i = 0; i < allUrl.length; i++) {
				//修改逻辑,防止链接后加参数变成无权限
				var urlI = allUrl[i];
				if (url.indexOf(urlI) == 0) {
					return true;
				}
			}*/
			return false;
		 },
		 checkUrlPermission : function(url) {//根据url检查权限
			 	if (!url)
					return true;
			 	//var isHaveUrl=tools.isHaveUrl(url);//判断当前需要判断的链接是否在数据库保存，如果没有保存说明该链接无需授权
        		if(allAccessUrl&&allAccessUrl.length==0){
        			return true;
        		}else{
        			if (!allAccessUrl)
    					return false;
					for (var i = 0; i < allAccessUrl.length; i++) {
						//修改逻辑,防止链接后加参数变成无权限
						var accessUrl = allAccessUrl[i];
						if (url.indexOf(accessUrl) == 0) {
							return true;
						}
					}
        		}
				return false;
		},
		initProgress:function(func) {
			if(!func) {
				return ;
			}
			var rate=0;
			var temp_1=window.setInterval(function(){
	 			tools.ajax(
						{ 
						url: "progress/getProgress.do", 
							data: null, 
							async: false,
							success: function(data){
								if(!data) {
									window.clearInterval(temp_1);
								}
								rate=data.rate;
								func(data);
								if(rate==100) {
					 				window.clearInterval(temp_1);
					 			}
							}
					   });
         	},500);
	 	},
	 	// 添加选择器赋值后的清除图标
	 	addChooseDelete:function(chooseId,chooseValue,left,top) {
	 		if($("#"+chooseValue).next()&&$("#"+chooseValue).next().hasClass("chooseDelete"))
	 			return;
	 		
	 		var id=chooseValue+"_"+new Date().getTime();
	 		left=left||$("#"+chooseValue).width();
	 		top=top||2;
			$("#"+chooseValue).after("<a id='"+id+"' class='chooseDelete' href='javascript:void(0)'  style='position: absolute;z-index:1000;top:"+top+"px;left:"+(left-31)+"px;' ><img src='http/common/images/delete.png' /></a>")
			$("#"+id).on("click",function(){
				if($("#"+chooseId).is("textarea")) {
					$("#"+chooseId).html("");
				}else
					$("#"+chooseId).val("");
				
				if($("#"+chooseValue).is("textarea")) {
					$("#"+chooseValue).html("");	
				}else
					$("#"+chooseValue).val("");	
				
				$(this).remove();
			})
	 	},
	 	// 右下角显示消息,type为类型英文字母，msg为消息，func为函数，time为停留时间
	 	showMessage:function(title,msg,func,time) {
	 		var id="showMessage_index"+new Date().getTime();
	 		var closeId="showMessage_close"+new Date().getTime();
	 		var viewId="showMessage_show"+new Date().getTime();
	 		time=time||10000;
	 		title=title||"新消息";
	 		var str=[];
	 		str.push('<div id="'+id+'" style="z-index:5000;width:200px; height:150px; border:4px solid #014581; position:fixed; bottom:2px; right:2px; display:none; background-color:White;">');
	 		str.push('<a href="javaScript:void(0)" id="'+closeId+'" style="position:absolute; top:8px; right:8px; font-size:12px; text-decoration:none; color:Blue;">关闭</a>');
	 		str.push('<span style=" line-height:30px;">'+title+'</span>');
	 		str.push('<h2 style="font-size:18px; text-align:center;font-family:"微软雅黑";"><a id="'+viewId+'" href="javascript:void(0)" >'+msg+'</a></h2>');
	 		str.push('</div>');
	 		$("body").prepend(str.join(""));
	 		$("#"+id).slideDown("slow");
	 		setTimeout(function(){
	 			$("#"+id).slideUp("slow"); 	
	 			 $("#"+id).remove();
	 		},time);
	 		$("#"+closeId).on("click",function () {
	 			 $("#"+id).slideUp("slow"); 	
	 			 $("#"+id).remove();
	 		});
	 		$("#"+viewId).on("click",function () {
	 			if(func) {
	 				func();
 				    $("#"+id).slideUp("slow"); 	
	 			    $("#"+id).remove();
	 			}
	 		})
	 	},
	 	showMessage2:function(title,msg,func,time){
	 		var id="showMessage_index"+new Date().getTime();
	 		var closeId="showMessage_close"+new Date().getTime();
	 		var viewId="showMessage_show"+new Date().getTime();
	 		time=time||10000;
	 		title=title||"新消息";
	 		var str=[];
	 		str.push('<div id="'+id+'" style="z-index:5000;width:200px; height:150px; position:fixed; bottom:2px; right:2px; visibility: visible; ">');
	 		//消息标题和按钮
	 		str.push('<table class="ui_border ui_state_visible ui_state_focus"><tbody><tr><td class="ui_lt"></td><td class="ui_t"></td><td class="ui_rt"></td></tr>'+
	 				'<tr><td class="ui_l"></td><td class="ui_c"><div class="ui_inner"><table class="ui_dialog"><tbody><tr><td colspan="2"><div class="ui_title_bar">'+
	 				'<div class="ui_title" unselectable="on" style="cursor: auto;">'+title+'</div>'+
	 				'<div class="ui_title_buttons"><a class="ui_close" id="'+closeId+'" href="javascript:void(0);" title="关闭(esc键)" style="display: inline-block;">×</a></div></div></td></tr>');
	 		//消息内容
	 		str.push('<tr>'+
						'<td class="ui_icon" style="display: none;"></td>'+
						'<td class="ui_main" style="width: 200px; height: 100px;"><div class="ui_content" id="'+viewId+'" style="padding: 10px;">'+msg+'</div></td>'+
					'</tr>');
	 		str.push('<tr><td colspan="2"><div class="ui_buttons" style="display: none;"></div></td></tr>'+
				'</tbody></table></div></td><td class="ui_r"></td></tr>'+
				'<tr><td class="ui_lb"></td><td class="ui_b"></td><td class="ui_rb" style="cursor: auto;"></td></tr>'+
					'</tbody>'+
	 				'</table>');
	 		str.push('</div>');
	 		$("body").prepend(str.join(""));
	 		$("#"+id).slideDown("slow");
	 		setTimeout(function(){
	 			$("#"+id).slideUp("slow"); 	
	 			 $("#"+id).remove();
	 		},time);
	 		$("#"+closeId).on("click",function () {
	 			 $("#"+id).slideUp("slow"); 	
	 			 $("#"+id).remove();
	 		});
	 		$("#"+viewId).on("click",function () {
	 			if(func) {
	 				func();
 				    $("#"+id).slideUp("slow"); 	
	 			    $("#"+id).remove();
	 			}
	 		})
	 	},
	 	//建立一個可存取到該file的url
		getObjectURL : function(file) {
			var url = null ; 
			if (window.createObjectURL!=undefined) { // basic
				url = window.createObjectURL(file) ;
			} else if (window.URL!=undefined) { // mozilla(firefox)
				url = window.URL.createObjectURL(file) ;
			} else if (window.webkitURL!=undefined) { // webkit or chrome
				url = window.webkitURL.createObjectURL(file) ;
			}
			return url ;
		},
		//生成单选按钮，样式1：方形图标
		//div:要生成单选组的div的id，name：生成单选按钮的组的name属性，dictCode：根据哪个数据字典code生成，checkId：默认选中的value，changeFun:自定义改变事件
		makeRadio : function (div,name,dictCode,checkId,changeFun,disable){
				var ajaxObj=tools.getValueForDict(dictCode);
				var _this=$("#"+div);
				if(ajaxObj) {
					$.each(ajaxObj,function(i){
						var str="";  
						if(checkId && checkId==this.id){
							str=" checked";
						}
						if(disable)
							str+=" disabled";
						$(_this).append("<label class='checkbox-inline "+str+"' style='padding-top:0px;'>"+
								""+
								"<input type='radio' name='"+name+"' "+str+" id='"+name+this.id+"' value='"+this.id+"'><span>"+this.name+
								"</span></label>");
					});
					//给生成的下拉框绑定改变事件改变样式
					$(document).on(
						"change",
						'input:radio[name="'+name+'"]',
						function() {
							$("#"+this.id).parent().addClass("checked");//当前选中的样式
							$("#"+this.id).parent().siblings().removeClass("checked");//其他的去掉选中的样式
							if(changeFun){
								changeFun(this.value,this.id);//调用自定义的改变函数
							}
					});
				}
		},
		//生成单选按钮，样式2：带背景，选中蓝色背景
		makeRadio2 : function (ul,name,dictCode,checkId){//ul:要生成单选组的ul的id，name：生成单选按钮的组的name属性，dictCode：根据哪个数据字典code生成，checkId：默认选中的value
			var ajaxObj=tools.getValueForDict(dictCode);
			var _this=$("#"+ul);
			if(ajaxObj) {
				$.each(ajaxObj,function(i){
					var str="";  
					if(checkId && checkId==this.id){
						str="checked";
					}
						$(_this).append(
							"<li>"
							+"<input type='radio' name='"+name+"' "+str+" id='"+name+this.id+"' value='"+this.id+"' data-labelauty='"+this.name+"'>"
							+" </li>");
				});
				$(':input[name="'+name+'"]').labelauty();
			}
		},
		trim : function (str){ //删除左右两端的空格
			if(!str)
				return str;
		    return str.replace(/(^\s*)|(\s*$)/g, "");
		},
		ltrim : function (str){ //删除左边的空格
			if(!str)
				return str;
		    return str.replace(/(^\s*)/g,"");
		},
		rtrim : function (str){ //删除右边的空格
			if(!str)
				return str;
		     return str.replace(/(\s*$)/g,"");
		}
		
};



(function($) {
	 $.extend($.fn, {
		 	//弹出日期框
			 popDate:function(param) {
				 $(this).calendar(param);
			 }			
	 });
})(jQuery);

//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
function banBackSpace(e){   
    var ev = e || window.event;//获取event对象   
    var obj = ev.target || ev.srcElement;//获取事件源   
    var t = obj.type || obj.getAttribute('type');//获取事件源类型  
    //获取作为判断条件的事件类型
    var vReadOnly = obj.getAttribute('readonly');
    //处理null值情况
    vReadOnly = (vReadOnly == "") ? false : vReadOnly;
    //当敲Backspace键时，事件源类型为密码或单行、多行文本的，
    //并且readonly属性为true或enabled属性为false的，则退格键失效
    var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea") 
                && vReadOnly=="readonly")?true:false;
    //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
    var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")
                ?true:false;        
    
    //判断
    if(flag2){
        return false;
    }
    if(flag1){   
        return false;   
    }   
}


/**
表格处理
*/
(function($){      
$.fn.extend({      
		// 判断是否有权限访问该对象
	roleShow:function(name,props) {
		var that=this;
		var currentRoleCode=allValidateRole["currentRoleCode"];
		var value=allValidateRole[name+"."+(props||$(that).attr("name"))];
		var tagName=$(that).attr("tagName");
		if((!value)||!tools.contain(value.split(","), currentRoleCode)) {
			var obj=$(that).clone(true);
			$(obj).css("display","none");
			$(that).after(obj);
			$(that).attr("name","").attr("id","");
			if($(that).is("input")) {
				$(that).attr("value","******");
				$(that).attr("title","******");
			}else {
				$(that).html("您没有权限访问该数据！");
			}
		}
		
	},
	// 输入框自动补全函数
	// 其中option为{code:"",callback:func,param:[],data:""}  
	//code为autoComplete中的code，callback为赋值函数,param为赋值属性，默认为["id","name"]
	//
	inputComplete:function(option) {
		var that=this;
		var param=option["param"]||["id","name"];
		var autoCode=autoComplete[option.code];
		if(!autoCode) {
			return;
		}
		$(that).autocomplete({ 
			 source: function( request, response ) {
	                tools.ajax({
	                    url: tools.joinUrl(autoCode), // 后台请求路径
	                    dataType: "json",
	                    data:{
	                        "name": request.term,    // 获取输入框内容
	                        "filter": option.data||""
	                    },
	                    success: function( data ) {
	                        response( $.map( data, function( item ) { // 此处是将返回数据转换为 JSON对象，并给每个下拉项补充对应参数
	                            return {
	                                 // 设置item信息
	                                 label: item[param[1]], // 下拉项显示内容
	                                 value:item[param[0]] // 下拉项对应数值
	                            }
	                        }));
	                    }
	                });
	            },
	            change: function( event, ui ) {
	            	if(ui.item&&option.callback)  {
	            		option.callback(ui.item.value,ui.item.label,option.seq);
	            	}else if(option.callback) {
	            		option.callback("","",option.seq);
	            	}
	            },
	            focus: function( event, ui ) {
	            	if(option.callback) {
	            		option.callback(ui.item.value,ui.item.label,option.seq);
	            	}
	            	return false;
	            },
	            select: function( event, ui ) { // 选中某项时执行的操作
	            	if(option.callback) {
	            		option.callback(ui.item.value,ui.item.label,option.seq);
	            	}
	            	return false;
	              }
			 }); 
	 }
})      
})(jQuery);





$(document).ready(function(){
		
		window.onload=function(){
		    //禁止后退键 作用于Firefox、Opera
		    document.onkeypress=banBackSpace;
		    //禁止后退键  作用于IE、Chrome
		    document.onkeydown=banBackSpace;
		}
	/*$(".choose").each(function(){
		var chooseId=$(this).attr("chooseId");
		var chooseValue=$(this).attr("chooseValue");
		if($("#"+chooseId).val())
			tools.addChooseDelete(chooseId, chooseValue, $("#"+chooseValue).width());
	});*/
	
	$(".date").each(function(){
		// yyyy-MM-dd HH:mm:ss 
		var dateFormat=$(this).attr("format");
		$(this).popDate({ format:dateFormat||'yyyy-MM-dd' });
	});
	//下拉框选择器
	$(".select").each(function(){
		var dictCode=$(this).attr("dictCode");
		var obj=tools.getValueForDict(dictCode);
		var dictValue=$(this).attr("dictValue");
		var _this=this;
		var placeholder = $(_this).attr("placeholder")||"请选择";
		$(_this).append("<option value=''>"+placeholder+"</option>");
		if(obj) {
			$.each(obj,function(i){
				var str="";
				if(this["id"]==dictValue)
					str=" selected ";
				$(_this).append("<option value='"+this["id"]+"' "+str+">"+this["name"]+"</option>");
			})
		}
	});
	
	// 定义选择器的函数
	$(".choose").on("click",function() {
		var chooseCode=$(this).attr("chooseCode");
		// dynamicParam="{key:element}"
		var dynamicParam=$(this).attr("dynamicParam");
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
		if(dynamicParam) {
			var t_data={};
			var dynamicData_=eval('('+dynamicParam+')');
			$.each(dynamicData_,function(a,b){
				if($("#"+b).is("textarea")) {
					t_data[a]=$("#"+b).html();
				}else
					t_data[a]=$("#"+b).val();
			})
			url=tools.joinUrl(url, t_data);
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
				try{
					eval("setValue_"+chooseId+"("+tools.objToJson(data)+")");
				}catch(e) {
					//alert(e.message);
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
					
					//tools.addChooseDelete(chooseId, chooseValue, $("#"+chooseValue).width());
					
					if($("#"+chooseValue).next().is("label")) {
						$("#"+chooseValue).next().css("display","none");
					}
				}
			};
			// 获取选择的值
			window.getValue=function() {
				try{
					eval("getValue_"+chooseId+"("+tools.objToJson(data)+")");
				}catch(e) {
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
				}
			};
		//}
	})
	
})


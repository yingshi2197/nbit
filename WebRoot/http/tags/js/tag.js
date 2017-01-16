/**
 * @Author:插件封装：weicb
 * @Date:2017-01-05
 * 1、实现基本的标签待选区域生成与选择/取消选择功能<br>
 * 2、计划：实现换一换功能，从指定链接请求标签集合[2017-01-08已实现]<br>
 * 3、计划：实现初始化已选标签并在候选区禁止选择[2017-01-08已实现]<br>
 * 4、计划：实现单页面多个标签组件的兼容[2017-01-16已实现]<br>
 * TODO 计划：实现添加、删除标签的回调函数[延期]<br>
 */
(function($){
	$.fn.extend({ 
			initTags : function(opt){
			    var obj=$(this);
			    var arr=[];
				var setting={
						id : null, // id为空时自动赋值
						selects : null, // 初始化时需要选中的数据
						readonly : false,// 是否只读，不显示待选区域，默认为否
						tags : [], // 初始化时候选区需要准备的标签数据
						maxTips : 10, // 最多可以选择的标签个数，默认为10个
						updateUrl : "", // 换一换的数据来源链接
						pageCount : 20 // 候选区每次显示多少条数据，待选区每页显示的标签数量，默认为20个
				}				
				$.extend(true,setting,opt);
				$(this).data("setting",setting);
				var id=setting.id||(new Date().getTime()+"_tag_n");
				$(this).data("tagId",id);
				var tags = setting.tags;
				// 已选区及已选区默认数据处理
				arr.push('<div class="plus-tag tagbtn clearfix" id="'+id+'">');
				arr.push('</div>');
				var readonly = setting.readonly;
				if (!readonly) {
					// 待选区/候选区及数据
					var cardId=setting.id||(new Date().getTime()+"_tag_card_n");
					$(this).data("cardId",cardId);
					arr.push('<div id="'+cardId+'s"><div class="default-tag tagbtn"><div class="clearfix">');
					if (!tags || tags.length<=0) {// 没有指定，从后台直接取数据
						$.ajax({
							url : setting.updateUrl,
							data : {limit:setting.pageCount},
							method : "post",
							async : false,
							success : function(data) {
								tags = data;
							}
						});
					}
					for(var i=0;i<tags.length;i++) {
					   var data_value = tags[i].id;
					   var data_name = tags[i].name;
					   arr.push('<a title="'+data_name+'" value="'+data_value+'" href="javascript:void(0);"><span>'+data_name+'</span><em></em></a>');
					}
					arr.push('</div></div>');
					if(setting.updateUrl){// 换一换
						var change_tips_id = cardId + "change_tips";
						$(this).data("changeTtipsId",change_tips_id);
						arr.push('<div align="right"><a href="javascript:void(0);" id="'+change_tips_id+'" style="color:#3366cc;">换一换</a></div>');
					}
					arr.push('</div>');
				}
				$(obj).html(arr.join(""));
				// 处理已选数据
				if(setting.selects){
					for(var i=0;i<setting.selects.length;i++) {
						var data_value = setting.selects[i].id;
					    var data_name = setting.selects[i].name;
						$(obj).setTips(data_name,data_value);
					}
				}
				$(obj).setSelectTips();
				
				// 事件处理
				if(!readonly){// 只读的时候没有待选区域，只需要显示已选标签，不需要绑定任何事件
					// 选择事件，主要是将已选择的标签在待选区禁用
					$(obj).bingSelectEvent();
					// 更换链接：点击“换一换”事件
					var $changeTips = $('#'+change_tips_id);
					$changeTips.on("click",function(){
						var crad = $(".default-tag .clearfix");
						// 数据
						var html = [];
						$.ajax({
							url : setting.updateUrl,
							data : {limit:setting.pageCount},
							method : "post",
							async : false,
							success : function(data) {
								for(var i=0;i<data.length;i++) {
								   var data_value = data[i].id;
								   var data_name = data[i].name;
								   html.push('<a title="'+data_name+'" value="'+data_value+'" href="javascript:void(0);"><span>'+data_name+'</span><em></em></a>')
								   html.push('</div></div>');
								   $(crad).html(html.join(""));
								   $(obj).setSelectTips();
								   $(obj).bingSelectEvent();
								}
							}
						});
					});	
				}
			},
			hasTips:function(n,i){
				var a=$(this).find(".plus-tag");
				var d=$("a",a),c=false;
				d.each(function(){
					if($(this).attr("title")==n && $(this).attr("value")==i){
						c=true;
						return false;
					}
				});
				return c;
			},
			bingSelectEvent:function(){
				var obj = $(this);
				$(this).find('.default-tag a').on('click', function(){
					var $this = $(this),
					name = $this.attr('title'),
					id = $this.attr('value');
					$(obj).setTips(name, id);
				});
			},
			isMaxTips:function(){
				var a=$(this).find(".plus-tag");
				var setting=$(this).data("setting");
				return $("a",a).length>=setting.maxTips;
			},
			setTips:function(n,i){
				var a=$(this).find(".plus-tag");
				var setting=$(this).data("setting");
				if($(this).hasTips(n,i)){// 已选
					return false;
				}
				if($(this).isMaxTips()){
					tools.alert("最多添加"+setting.maxTips+"个标签！");
					return false;
				}
				var b = i ? 'value="' + i + '"' : "";
				var em = setting.readonly ? "" : "<em></em>";// 标签后面的x
				a.append($('<a '+b+' title="'+n+'" href="javascript:void(0);" ><span>'+n+'</span>'+em+'</a>'));
				$(this).searchAjax(n,i,true);
				
				var obj = $(this);
				// 删除事件
				if(!setting.readonly){
					$(this).find(".plus-tag a").unbind("click");
					$(this).find(".plus-tag a").on("click",function(){
						var c=$(this),b=c.attr("title"),d=c.attr("value");
						$(obj).delTips(b,d);
					});
				}
				return true;
			},
			delTips:function(n,i){
				if(!$(this).hasTips(n,i)){
					return false;
				}
				var a=$(this).find(".plus-tag");
				$("a",a).each(function(){
					var d=$(this);
					if(d.attr("title")==n && d.attr("value")==i){
						d.remove();
						return false;
					}
				});
				$(this).searchAjax(n,i,false);
				return true;
			},
	
			getTips:function(){
				var a=$(this).find(".plus-tag");
				var b=[];
				$("a",a).each(function(){
					b.push($(this).attr("title"));
				});
				return b;
			},
	
			getTipsId:function(){
				var a=$(this).find(".plus-tag");
				var b=[];
				$("a",a).each(function(){
					b.push($(this).attr("value"));
				});
				return b;
			},
			getTipsIdAndTag:function(){
				var b=[];
				var a=$(this).find(".plus-tag");
				$("a",a).each(function(){
					b.push($(this).attr("value")+"##"+$(this).attr("title"))
				});
				return b;
			},
			// 更新高亮显示
			setSelectTips : function(){
				var arrName = $(this).getTips();
				var tagId=$(this).data("tagId");
				if(arrName.length){
					$('#'+tagId).show();
				}else{
					$('#'+tagId).hide();
				}
				$(this).find('.default-tag a').removeClass('selected');
				var _this = $(this);
				$.each(arrName, function(index,name){
					$(_this).find('.default-tag a').each(function(){
						var $this = $(this);
						if($this.attr('title') == name){
							$this.addClass('selected');
							return false;
						}
					})
				});
			},
			searchAjax : function(name, id, isAdd){
				$(this).setSelectTips();
			}
	})
})(jQuery);
/**
 * @Author:插件封装优化：weicb
 * @Date:2017-01-05
 * 1、实现基本的标签待选区域生成与选择/取消选择功能<br>
 * 2、计划：实现换一换功能，从后台随机取指定数量的标签<br>
 */
(function($){
	$.fn.extend({ 
			initTags : function(opt){
			    var obj=$(this);
			    var arr=[];
				var setting={
					id:null,  // id为空时自动赋值
					selects:null,  //需要选中的数据
					tags:[{"id":"1","name":"1"},{"id":"2","name":"2"},{"id":"3","name":"3"}], // 需要准备的标签数据
					maxTips:5, // 最多可以选择的标签个数
					updateUrl:"",//换一换的数据链接
					pageCount:20//候选区每次显示多少条数据
				}				
				$.extend(true,setting,opt);
				$(this).data("setting",setting);
				var id=setting.id||(new Date().getTime()+"_tag_n");
				$(this).data("tagId",id);
				var tags = setting.tags;
				// 已选区
				arr.push('<div class="plus-tag tagbtn clearfix" id="'+id+'"></div>')
				// 待选区/候选区
				var cardId=setting.id||(new Date().getTime()+"_tag_card_n");
				$(this).data("cardId",cardId);
				arr.push('<div id="'+cardId+'s"><div class="default-tag tagbtn"><div class="clearfix">')
				// 数据
				for(var i=0;i<tags.length;i++) {
				   var data_value = tags[i].id;
				   var data_name = tags[i].name;
				   arr.push('<a title="'+data_name+'" value="'+data_value+'" href="javascript:void(0);"><span>'+data_name+'</span><em></em></a>')
				}
				arr.push('</div></div>');
				var change_tips_id = cardId + "change_tips";
				$(this).data("changeTtipsId",change_tips_id);
				arr.push('<div align="right"><a href="javascript:void(0);" id="'+change_tips_id+'" style="color:#3366cc;">换一换</a></div>');
				arr.push('</div>');
				
				$(obj).html(arr.join(""));
				
				// 选择事件
				$('.default-tag a').on('click', function(){
					var $this = $(this),
					name = $this.attr('title'),
					id = $this.attr('value');
					$(obj).setTips(name, id);
				});
				
				// 更换链接
				/*var $changeTips = $('#'+change_tips_id),
				$d = $('.default-tag div'),
				len = $d.length,
				t = 'nowtips';
				$changeTips.click(function(){
					var i = $d.index($('.default-tag .nowtips'));
					i = (i+1 < len) ? (i+1) : 0;
					$d.hide().removeClass(t);
					$d.eq(i).show().addClass(t);
				});
				$d.eq(0).addClass(t);*/
			},
			hasTips:function(n,i){
				var a=$(".plus-tag");
				var d=$("a",a),c=false;
				d.each(function(){
					if($(this).attr("title")==n && $(this).attr("value")==i){
						c=true;
						return false;
					}
				});
				return c;
			},
			isMaxTips:function(){
				var a=$(".plus-tag");
				var setting=$(this).data("setting");
				return $("a",a).length>=setting.msxTips;
			},
			setTips:function(n,i){
				var a=$(".plus-tag");
				var setting=$(this).data("setting");
				if($(this).hasTips(n,i)){// 已选
					return false;
				}if($(this).isMaxTips()){
					alert("最多添加"+setting.maxTips+"个标签！");
					return false;
				}
				var b=i?'value="'+i+'"':"";
				a.append($('<a '+b+' title="'+n+'" href="javascript:void(0);" ><span>'+n+'</span><em></em></a>'));
				$(this).searchAjax(n,i,true);
				
				var obj = $(this);
				// 删除事件
				$(".plus-tag a").unbind("click");
				$(".plus-tag a").on("click",function(){
					var c=$(this),b=c.attr("title"),d=c.attr("value");
					$(obj).delTips(b,d)
				});
				return true
			},
			delTips:function(n,i){
				if(!$(this).hasTips(n,i)){
					return false;
				}
				var a=$(".plus-tag");
				$("a",a).each(function(){
					var d=$(this);
					if(d.attr("title")==n && d.attr("value")==i){
						d.remove();
						return false;
					}
				});
				$(this).searchAjax(n,i,false);
				return true
			},
	
			getTips:function(){
				var a=$(".plus-tag");
				var b=[];
				$("a",a).each(function(){
					b.push($(this).attr("title"))
				});
				return b;
			},
	
			getTipsId:function(){
				var a=$(".plus-tag");
				var b=[];
				$("a",a).each(function(){
					b.push($(this).attr("value"))
				});
				return b;
			},
			getTipsIdAndTag:function(){
				var b=[];
				var a=$(".plus-tag");
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
				$('.default-tag a').removeClass('selected');
				$.each(arrName, function(index,name){
					$('.default-tag a').each(function(){
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
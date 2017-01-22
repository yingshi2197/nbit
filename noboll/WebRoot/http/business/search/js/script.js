/**
 * 左侧查询 author:weicb
 * 暂时没有封装成插件，等有时间再弄吧
 * 封装成插件需要实现的功能：展示查询条件、实现选择和取消选择的回调函数
 */
$(document).ready(function() {
	/**
	 * 有子集
	 */
	$(".selectw dd").click(function() {
		$(this).addClass("selected").siblings().removeClass("selected");// 选中样式改变
		$(this).siblings().children("dl").children("dd").removeClass("selected");//其他元素子集样式改变
		// 子集处理
		var _this = this ;
		// 隐藏邻居的子集并将邻居的子集已选样式改变同时在已选区去掉并删除值
		var childrenId = $(this).parent().attr("id")+"_" + $(this).attr("code");
		$("."+ $(this).parent().attr("id")).hide();
		$(this).parent().siblings().children("dd").removeClass("selected");
		$("."+ $(this).parent().attr("id")).each(function(){
			var index = $(this).attr("id").replace('select_', '');// 条件index，用于区分清空已选
			if ($("#select_result_" + index).length > 0)
				$("#select_result_" + index).remove();
			clearQuery($(this));
		});
		
		// 显示自己的子集
		$("#"+childrenId).show();
		if($("#"+childrenId).children("dd").get(0)){
			$($("#"+childrenId).children("dd").get(0)).addClass("selected").siblings().removeClass("selected");
		}
		
		var index = $(this).parent().attr("id").replace('select_', '');// 条件index，用于区分清空已选
		if ($(this).hasClass("select-all")) {// 如果是全选，清空已选区域该类已选择内容
			$("#select_result_" + index).remove();
			clearQuery($(this).parent());// 清空自己的已选
			// 清空子集的已选
			$(this).parent().siblings().each(function(){
				clearQuery($(this));
			}); 
		} else {
				var copyThisResult = $(this).clone();
				if ($("#select_result_" + index).length > 0)
					$("#select_result_" + index).remove();
				// 已选区域点击事件：单项取消选择
				copyThisResult.on("click", function() {
					clearQuery($(this));
					$(this).remove();// 移除
					$("#select_" + index + " .select-all").addClass("selected").siblings().removeClass("selected");// 全选
					// 隐藏邻居的子集并将邻居的子集已选样式改变同时在已选区去掉并删除值
					var pId = $(this).attr("id").replace('select_result','select');
					var childrenId = pId +"_" + $(this).attr("code");
					$("."+ pId).hide();
					$(_this).parent().siblings().children("dd").removeClass("selected");
					$("."+ pId).each(function(){
						clearQuery($(this));
					});
					selectRemind();
				});
				$(".select-result .selectr").append(copyThisResult.attr("id", "select_result_" + index));// 选中
				setQuery($(this));
		}
		selectRemind();
	});
	/**
	 * 无子集
	 */
	$(".selectc dd").click(function() {
		$(this).addClass("selected").siblings().removeClass("selected");// 选中样式改变
		var index = $(this).parent().attr("id").replace('select_', '');// 条件index，用于区分清空已选
		var typeCode = $(this).attr("typeCode");
		if ($(this).hasClass("select-all")) {// 如果是全选，清空已选区域该类已选择内容
			$("#select_result_" + index).remove();
			clearQuery($(this).parent());// 清空自己的已选
			// 将父类已选
			$("#" + typeCode).click();
		} else {
				// 将父类取消显示在已选区
				var pNum = index.replace("_" + typeCode, "");
				if ($("#select_result_" + pNum).length > 0)
					$("#select_result_" + pNum).remove();
				var copyThisResult = $(this).clone();
				if ($("#select_result_" + index).length > 0)
					$("#select_result_" + index).remove();
				// 已选区域点击事件：单项取消选择
				copyThisResult.on("click", function() {
					clearQuery($(this));
					$(this).remove();// 移除
					$("#select_" + index + " .select-all").addClass("selected").siblings().removeClass("selected");// 全选
					// 将父类已选
					$("#" + typeCode).click();
					selectRemind();
				});
				$(".select-result .selectr").append(copyThisResult.attr("id", "select_result_" + index));// 选中
				setQuery($(this));
		}
		selectRemind();
	});
});

/**
 *已选区提示语 
 */
function selectRemind(){
	if ($(".select-result dd").length > 1) {
		$(".select-no").hide();
	} else {
		$(".select-no").show();
	}
}

/**
 * 清空已选值
 */
function clearQuery($element){
	var queryCode = $element.attr("queryCode");
	$("#" + queryCode).val("");
}

function setQuery($element){
	var queryCode = $element.attr("queryCode");
	var queryValue = $element.attr("queryValue");
	$("#" + queryCode).val(queryValue);
}

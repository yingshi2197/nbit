$('form').submit(function(event) {
			//阻止表单提交
			event.preventDefault();
			$.post('manage/login.do', $(this).serialize(), function(data) {
				if (data == 'success') {
					location.href = "/msb/manage/login/member/index.do";
				}else if (data == 'forbiden') {
					tools.alert("该用户已锁定！", "error");
				}else if (data == 'error') {
					tools.alert("用户名或者密码错误！", "error");
				}else if (data == 'anyerror') {
					tools.alert("未知错误！", "error");
				}else{
					location.href = "/msb"+data;
				}
			});
		});
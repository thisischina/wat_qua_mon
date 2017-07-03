//统一封装方法，作用：提示信息
function message_alert(data) {
	// 从返回的json数据中获取结果信息
	var data_v = data.resultInfo;

	// 提交结果类型
	var type = data_v.type;
	// 结果提示信息
	var message = data_v.message;

	// 失败
	if (type == 0) {
		
		$.alert({
			title : '提示框',
			content : message,
			confirmButtonClass : 'btn-info',
			confirmButton : '确认'

		});
		// 成功
	} else if (type == 1) {

		$.alert({
			title : '提示框',
			content : message,
			confirmButtonClass : 'btn-info',
			confirmButton : '确认',
			confirm : function() {
				history.go(-1);
			}
		});
		// 警告
	} else if (type == 2) {

		$.alert({
			title : '提示框',
			content : message,
			confirmButtonClass : 'btn-info',
			confirmButton : '确认',
			confirm : function() {
				history.go(-1);
			}
		});
		// 提示
	} else if (type == 3) {

		$.alert({
			title : '提示框',
			content : message,
			confirmButtonClass : 'btn-info',
			confirmButton : '确认'
		});

	}
}
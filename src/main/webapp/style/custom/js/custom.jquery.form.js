//封装的表单提交函数

function jquerySubByForId(formId,callbackFn,param,dataType){
	var formObj = jQuery("#" + formId);
	var options = {
        dataType:  ("undefined"!=dataType && null!=dataType)?dataType:"json",
		success: function(responseText) {
			if(param === null){
				callbackFn(responseText);
			}else{
				callbackFn(responseText,param);
			}
		}
	};
	formObj.ajaxSubmit(options); 
}
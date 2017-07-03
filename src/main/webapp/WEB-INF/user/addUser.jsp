<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("basepath", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>AgileUI</title>
<meta name="description" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- Favicons -->

<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="${basepath }/assets/images/icons/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="${basepath }/assets/images/icons/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="${basepath }/assets/images/icons/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="${basepath }/assets/images/icons/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon"
	href="${basepath }/assets/images/icons/favicon.png">

<!--[if lt IE 9]>
          <script src="assets/js/minified/core/html5shiv.min.js"></script>
          <script src="assets/js/minified/core/respond.min.js"></script>
        <![endif]-->

<!-- AgileUI CSS Core -->

<link rel="stylesheet" type="text/css"
	href="${basepath }/assets/css/minified/aui-production.min.css">

<!-- Theme UI -->

<link id="layout-theme" rel="stylesheet" type="text/css"
	href="${basepath }/assets/themes/minified/agileui/color-schemes/layouts/default.min.css">
<link id="elements-theme" rel="stylesheet" type="text/css"
	href="${basepath }/assets/themes/minified/agileui/color-schemes/elements/default.min.css">

<!-- AgileUI Responsive -->

<link rel="stylesheet" type="text/css"
	href="${basepath }/assets/themes/minified/agileui/responsive.min.css">

<!-- AgileUI Animations -->

<link rel="stylesheet" type="text/css"
	href="${basepath }/assets/themes/minified/agileui/animations.min.css">

<!-- AgileUI JS -->

<script type="text/javascript"
	src="${basepath }/assets/js/minified/aui-production.min.js"></script>
<script type="text/javascript" src="${basepath }/assets/js/jquery-2.0.3.min.js"></script> 
<script type="text/javascript" src="${basepath }/style/custom/js/jquery.twbsPagination.min.js"></script>
<script type="text/javascript" src="${basepath }/style/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function(){
		changeTitle();
	});
	
	function changeTitle(){
		$('#ultt', parent.document).html("");
		var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:UserInfo();'>用户信息</a></li>";
		htmlss = htmlss + "<li>添加用户</li>"; 
		$('#ultt', parent.document).html(htmlss);
	}
	
	function changeTitle2(){
		$('#ultt', parent.document).html("");
		var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:UserInfo();'>用户信息</a></li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function returnUser(){
		changeTitle2();
		window.location.href="${basepath }/User/user.action";
	}
	
	function saveUser(){
		
		document.getElementById('submitBtn').disabled=true;
		var userName=$('#userName').val();
		var password=$('#password').val();
		var email=$('#email').val();
		var tel=$('#tel').val();
		var role=$('#role').val();
		if(userName==""||password==""){
			alert("用户名或密码不能为空。");
			return
		}
		//判断用户是否已存在
		$.ajax({
			url:"${basepath }/User/validateIbusUser.action",
			type:"post",
			data:"userName="+userName,
			datatype:"json", 
			async:false,
			success: function (data) {
				if(data=="no"){
					alert("用户名已存在");
					document.getElementById('submitBtn').disabled=false;
					return;
				}else{
					$.ajax({
						url:"${basepath }/User/saveIbusUser.action",
						type:"post",
						data:"ibusUser.userName="+userName+"&ibusUser.password="+password+"&ibusUser.email="+email
							+"&ibusUser.tel="+tel+"&ibusUser.role="+role,
						datatype:"json", 
						success: function (data) {
							if(data=="true"){
								alert("添加成功");
								changeTitle2();
								window.location.href="${basepath }/User/user.action";
							}else{
								alert("添加失败");
							}
						}
					});
					
				}
			}
		}); 
	}

</script>

</head>
<body>

<div id="page-content">
	<h3>添加用户</h3>
<div class="divider"></div>
		<div class="">
			<div class="example-code clearfix">

				<form action="" class="col-md-20 center-margin" method="get">
					<div class="form-row">
						<div class="form-label col-md-2">
							<label for=""> 用户名: </label>
						</div>
						<div class="form-input col-md-5">
						 <input placeholder=""  id="userName" type="text"> 
						</div>
						<div class="form-input col-md-1">
						*
						</div>
						<div class="form-input col-md-2">
						带*为必填项
						</div>
					</div>
					<div class="form-row">
						<div class="form-label col-md-2">
							<label for=""> 密码: </label> 
						</div>
						<div class="form-input col-md-5">
						 <input name="password" id="password" type="password" value=""> 
						</div>
						<div class="form-input col-md-1">
						*
						</div>
					</div>
					<div class="form-row">
						<div class="form-label col-md-2">
							<label for=""> 角色: </label> 
						</div>
						<div class="form-input col-md-5">
						 <select name='role' id='role'>
						 	<option value='1'>管理员</option>
						 	<option value='2'>用户</option>
						 </select>
						 
						</div>
					</div>
					<div class="form-row">
						<div class="form-label col-md-2">
							<label for=""> 邮箱: </label> 
						</div>
						<div class="form-input col-md-5">
						 <input placeholder="" name="email" id="email" type="text"> 
						</div>
					</div>
					<div class="form-row">
						<div class="form-label col-md-2">
							<label for=""> 电话: </label> 
						</div>
						<div class="form-input col-md-5">
						 <input placeholder="" id="tel" name='tel' type="text">
						</div>
					</div>
					
					<div class="form-row">
						<div class="form-label col-md-2">
					
					</div>
					<div class="form-label col-md-2">
					<input class="btn medium primary-bg" id='submitBtn' style="width:80px" value="提交" type="button" onclick="saveUser();"/>
					
					</div>
					<div class="form-label col-md-2">
					<input class="btn medium primary-bg" style="width:80px" value="返回" type="button" onclick="returnUser();"/>
					</div>
					</div>
				</form>

			</div>

	</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("basepath", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="${basepath}/main/css.jsp"></jsp:include>
<title>新增</title>

<jsp:include page="${basepath}/main/js.jsp"></jsp:include>

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
			url:"${basepath}/user/add",
			type:"post",
			data:{account:userName,password:password},
			dataType:"json",
			async:false,
			success: function (data) {
                if(data.state==1){
                    alert("添加成功");
//                    changeTitle2();
                    window.location.href="${basepath }/user/tolist";
                }else{
                    alert("添加失败");
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
							<label> 用户名: </label>
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
							<label> 密码: </label>
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
							<label> 角色: </label>
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
							<label> 邮箱: </label>
						</div>
						<div class="form-input col-md-5">
						 <input placeholder="" name="email" id="email" type="text"> 
						</div>
					</div>
					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 电话: </label>
						</div>
						<div class="form-input col-md-5">
						 <input placeholder="" id="tel" name='tel' type="text">
						</div>
					</div>
					
					<div class="form-row">
						<div class="form-label col-md-2">
					
					</div>
					<div class="form-label col-md-2">
					<input class="btn medium primary-bg"  style="width:80px" value="提交" type="button" onclick="saveUser();"/>
					
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
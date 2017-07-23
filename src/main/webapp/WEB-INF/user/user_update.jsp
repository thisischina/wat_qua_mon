<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
	pageContext.setAttribute("basepath", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="${basepath}/main/css.jsp"></jsp:include>

<title>用户信息</title>

<jsp:include page="${basepath}/main/js.jsp"></jsp:include>

<script type="text/javascript">
	jQuery(document).ready(function(){
		changeTitle();
	});

	function changeTitle(){
		var htmlss = $('#ultt', parent.document).html();
		htmlss = htmlss + "<li>修改用户信息</li>"; 
		$('#ultt', parent.document).html(htmlss);
	}
	
	function changeTitle2(){
		$('#ultt', parent.document).html("");
		var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:projectInfo();'>用户信息</a></li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function update(){
		var id=$('#id').val();
		var  name=$('#name').val();
		var tel=$('#tel').val();
		var email=$('#email').val();
		var unitId=$('#unitId').val();
		var roleId=$('#roleId').val();
        var power=$('#power').val();

		if(tel==""){
			alert("手机号不能为空。");
			return
		}
		   $.ajax({
			url:"${basepath }/user/update",
			type:"post",
			data:{id:id,name:name,tel:tel,email:email,unitId:unitId,roleId:roleId,power:power},
			dataType:"json",
			success: function (data) {
				if(data==1){
					alert("修改成功");
					changeTitle2();
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
	<h3>修改项目信息</h3>
<div class="divider"></div>
		<div class="">
			<div class="example-code clearfix">
				<input type="hidden" id="id" value='${user.id }'>
				<form action="" class="col-md-20 center-margin" method="get">
					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 用户名: </label>
						</div>
						<div class="form-input col-md-5">
						 <input type="text" value="${user.account}" readonly="readonly">
						</div>

						<div class="form-input col-md-2">
						带*为必填项
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 姓名: </label>
						</div>
						<div class="form-input col-md-5">
							<input value='${user.name }' id="name" type="text">
						</div>
						<div class="form-input col-md-1">
							*
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 电话: </label>
						</div>
						<div class="form-input col-md-5">
							<input value='${user.tel }' id="tel" type="text">
						</div>
						<div class="form-input col-md-1">
							*
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 邮箱: </label>
						</div>
						<div class="form-input col-md-5">
							<input id="email" type="text" value='${user.email}'>
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 单位: </label>
						</div>
						<div class="form-input col-md-5">
							<select id='unitId'>
								<option value='1' selected>单位一</option>
								<option value='2'>单位二</option>
								<option value='3'>单位三</option>
								<option value='4'>单位四</option>
							</select>
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 角色: </label>
						</div>
						<div class="form-input col-md-5">
							<select id='roleId'>
								<option value='1' selected>角色一</option>
								<option value='2'>角色二</option>
								<option value='3'>角色三</option>
								<option value='4'>角色四</option>
							</select>
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 权限: </label>
						</div>
						<div class="form-input col-md-5">
							<input id="power" type="text" value='${user.power}'>
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
					</div>

					<div class="form-label col-md-2">
					<input class="btn medium primary-bg" style="width:80px" value="提交" type="button" onclick="update();"/>
					</div>

					<div class="form-label col-md-2">
					<input class="btn medium primary-bg" style="width:80px" value="返回" type="button" onclick="window.location.href='${basepath}/user/tolist'"/>
					</div>

					</div>
				</form>

			</div>

	</div>
	</div>
</body>
</html>
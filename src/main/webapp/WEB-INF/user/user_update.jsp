<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
	pageContext.setAttribute("basepath", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="${basepath}/main/updatepage_css.jsp"></jsp:include>
<title>更新</title>

<!-- AgileUI JS -->

<jsp:include page="${basepath}/main/updatepage_js.jsp"></jsp:include>

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
		var tel=$('#user_tel').val();
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
<body style='font-family:"Microsoft Yahei"'>

<div class="portlet box">

	<div class="portlet-body form">
		<input type="hidden" id="id" value='${user.id }'>
		<form>
			<h3 class="form-section">修改用户</h3>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">用户名</label>

						<div class="controls">

							<input type="text" value="${user.account}" readonly="readonly" class="m-wrap span12">

						</div>

					</div>

				</div>

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">姓名</label>

						<div class="controls">

							<input type="text" id="name" value='${user.name }' class="m-wrap span12">

						</div>

					</div>

				</div>
			</div>


			<div class="row-fluid">


				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">手机</label>

						<div class="controls">

							<input type="text" id="user_tel" value='${user.tel }' class="m-wrap span12">

						</div>

					</div>

				</div>

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">邮箱</label>

						<div class="controls">

							<input class="span12 m-wrap" id="email" value='${user.email}'/>

						</div>

					</div>

				</div>

			</div>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label" >所属单位</label>

						<div class="controls">

							<select id="unitId"  class="m-wrap span12">

								<option value="1">一</option>

								<option value="2">二</option>

							</select>

						</div>

					</div>

				</div>

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label" >角色</label>

						<div class="controls">

							<select id="roleId" class="m-wrap span12">

								<option value="1">一</option>

								<option value="2">二</option>

							</select>

						</div>

					</div>

				</div>

			</div>

			<div class="form-actions" style="padding-left: 10px">

				<button type="button" class="btn blue" onclick="update();">确定</button>

				<button type="button" class="btn" onclick="window.location.href='${basepath}/user/tolist'">取消</button>

			</div>

		</form>

	</div>

</div>

</body>

</html>
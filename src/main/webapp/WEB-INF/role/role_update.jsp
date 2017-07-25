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
<title>新增</title>

<!-- AgileUI JS -->

<jsp:include page="${basepath}/main/updatepage_js.jsp"></jsp:include>

<script type="text/javascript">
	jQuery(document).ready(function(){
		changeTitle();

		ifuserexis();

		App.init();

		FormComponents.init();
	});

	function changeTitle(){
		var htmlss = $('#ultt', parent.document).html();
		htmlss = htmlss + "<li>修改角色信息</li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function changeTitle2(){
		$('#ultt', parent.document).html("");
		var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:projectInfo();'>角色信息</a></li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function update(){
		var id=$('#id').val();
        var name=$('#name').val();
        var power=$('#power').val();

		if(name==""){
			alert("角色名称不能为空。");
			return
		}
		   $.ajax({
			url:"${basepath }/role/update",
			type:"post",
			data:{id:id,name:name,power:power},
			dataType:"json",
			success: function (data) {
				if(data==1){
					alert("修改成功");
					changeTitle2();
					window.location.href="${basepath }/role/tolist";
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
		<input type="hidden" id="id" value='${role.roleId }'>
		<form action="#" class="horizontal-form">

			<h3 class="form-section">修改角色信息</h3>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">角色名</label>

						<div class="controls">

							<input type="text" value="${role.name}" readonly="readonly" class="m-wrap span12">

						</div>

					</div>

				</div>

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">权限</label>

						<div class="controls">

							<input type="text" id="power" value='${role.power }'class="m-wrap span12">

						</div>

					</div>

				</div>

			</div>

			<div class="form-actions" style="padding-left: 10px">

				<button type="button" class="btn blue" onclick="update();">确定</button>

				<button type="button" class="btn" onclick="window.location.href='${basepath}/role/tolist'">取消</button>

			</div>

		</form>

	</div>

</div>

</body>
</html>
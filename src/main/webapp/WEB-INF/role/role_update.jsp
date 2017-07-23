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
<title>角色信息</title>

<jsp:include page="${basepath}/main/js.jsp"></jsp:include>

<script type="text/javascript">
	jQuery(document).ready(function(){
		changeTitle();
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
<body>
<div id="page-content">
	<h3>修改角色信息</h3>
<div class="divider"></div>
		<div class="">
			<div class="example-code clearfix">
				<input type="hidden" id="id" value='${role.roleId }'>
				<form action="" class="col-md-20 center-margin" method="get">
					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 角色名: </label>
						</div>
						<div class="form-input col-md-5">
						 <input type="text" value="${role.name}" readonly="readonly">
						</div>

						<div class="form-input col-md-2">
						带*为必填项
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 权限: </label>
						</div>
						<div class="form-input col-md-5">
							<input value='${role.power }' id="power" type="text">
						</div>
						<div class="form-input col-md-1">
							*
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
						</div>

						<div class="form-label col-md-2">
							<input class="btn medium primary-bg" style="width:80px" value="提交" type="button" onclick="update();"/>
						</div>

						<div class="form-label col-md-2">
							<input class="btn medium primary-bg" style="width:80px" value="返回" type="button" onclick="window.location.href='${basepath}/role/tolist'"/>
						</div>
					</div>
					
				</form>

			</div>

	</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
	pageContext.setAttribute("basepath", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<title>单位信息</title>

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
	  href="${basepath }/assets/themes/minified/agileui/color-schemes/elements/default-one.css">

<!-- AgileUI Responsive -->

<link rel="stylesheet" type="text/css"
	  href="${basepath }/assets/themes/minified/agileui/responsive.min.css">

<!-- AgileUI Animations -->

<link rel="stylesheet" type="text/css"
	  href="${basepath }/assets/themes/minified/agileui/animations.min.css">

<!-- AgileUI JS -->

<jsp:include page="${basepath}/main/js.jsp"></jsp:include>

<script type="text/javascript">
	jQuery(document).ready(function(){
		changeTitle();
	});

	function changeTitle(){
		var htmlss = $('#ultt', parent.document).html();
		htmlss = htmlss + "<li>修改单位信息</li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function changeTitle2(){
		$('#ultt', parent.document).html("");
		var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:projectInfo();'>单位信息</a></li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function update(){
        var  id=$('#id').val();
		var  name=$('#name').val();

		if(name==""){
			alert("单位名不能为空");
			return
		}
		   $.ajax({
			url:"${basepath }/unit/update",
			type:"post",
			data:{id:id,name:name},
			dataType:"json",
			success: function (data) {
				if(data==1){
					alert("修改成功");
					changeTitle2();
					window.location.href="${basepath }/unit/tolist";
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
	<h3>修改单位信息</h3>
<div class="divider"></div>
		<div class="">
			<div class="example-code clearfix">
				<input type="hidden" id="id" value='${unit.unitId}'>
				<form action="" class="col-md-20 center-margin" method="get">
					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 单位名: </label>
						</div>
						<div class="form-input col-md-5">
						 <input type="text" id="name" value="${unit.name}">
						</div>

						<div class="form-input col-md-2">
						带*为必填项
						</div>
					</div>


					<div class="form-row">
						<div class="form-label col-md-2">
					</div>

					<div class="form-label col-md-2">
					<input class="btn medium primary-bg" style="width:80px" value="提交" type="button" onclick="update();"/>
					</div>

					<div class="form-label col-md-2">
					<input class="btn medium primary-bg" style="width:80px" value="返回" type="button" onclick="window.location.href='${basepath}/unit/tolist'"/>
					</div>

					</div>
				</form>

			</div>

	</div>
	</div>
</body>
</html>
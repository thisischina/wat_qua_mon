<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("basepath", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>新增</title>

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

<jsp:include page="${basepath}/main/js.jsp"></jsp:include>

<script type="text/javascript">
	jQuery(document).ready(function(){
		changeTitle();
	});
	
	function changeTitle(){
		$('#ultt', parent.document).html("");
		var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:'>角色信息</a></li>";
		htmlss = htmlss + "<li>添加角色</li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function changeTitle2(){
		$('#ultt', parent.document).html("");
		var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:'>监测站信息</a></li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function returnPage(){
		changeTitle2();
        window.location.href='${basepath}/role/tolist';
	}
	
	function saveObject(){
		var name=$('#name').val();
		var power=$('#power').val();

		if(name==""){
			alert("角色名称不能为空。");
			return
		}

		//判断是否已存在
		$.ajax({
			url:"${basepath}/role/confirmexist",
			type:"post",
			data:{name:name},
			dataType:"json",
			async:false,
			success: function (data) {
				if(data.total>0){
				    alert("角色已存在");
				    return;
				}else{

				    //添加
                    $.ajax({
                        url:"${basepath}/role/addrole",
                        type:"post",
                        data:{name:name,power:power},
                        dataType:"json",
                        async:false,
                        success: function (data) {
                            if(data>0){
                                alert("添加成功");
                                changeTitle2();
                                window.location.href='${basepath}/role/tolist';
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
	<h3>添加角色</h3>
	<div class="divider"></div>

		<div class="">
			<div class="example-code clearfix">

				<form action="" class="col-md-20 center-margin" method="get">
					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 角色名名: </label>
						</div>
						<div class="form-input col-md-5">
						 <input id="name" type="text">
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
							<label> 权限: </label>
						</div>
						<div class="form-input col-md-5">
						 <input id="power" type="text">
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
					
					</div>
					<div class="form-label col-md-2">
					<input class="btn medium primary-bg"  style="width:80px" value="提交" type="button" onclick="saveObject();"/>
					
					</div>
					<div class="form-label col-md-2">
					<input class="btn medium primary-bg" style="width:80px" value="返回" type="button" onclick="returnPage();"/>
					</div>
					</div>
				</form>

			</div>

	</div>
	</div>
</body>
</html>
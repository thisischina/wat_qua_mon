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
		var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:'>维修信息</a></li>";
		htmlss = htmlss + "<li>添加维修</li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function changeTitle2(){
		$('#ultt', parent.document).html("");
		var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:'>维修信息</a></li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function returnUser(){
		changeTitle2();
        window.location.href='${basepath}/record/tolist';
	}
	
	function saveObject(){
		var content=$('#contentname').val();
		var userId=$('#userId').val();
        var recordTime=$('#recordTime').val();
		var stationId=$('#stationId').val();
        var equipmentId=$('#equipmentId').val();

		if(content==""||content==""){
			alert("维修内容不能为空。");
			return
		}

        //添加用户
        $.ajax({
            url:"${basepath}/record/addrecord",
            type:"post",
            data:{content:content,userId:userId,recordTime:recordTime,stationId:stationId,
                equipmentId:equipmentId},
            dataType:"json",
            async:false,
            success: function (data) {
                if(data>0){
                    alert("添加成功");
                    changeTitle2();
                    window.location.href='${basepath}/record/tolist';
                }
            }
        });
	}

</script>

</head>
<body>

<div id="page-content">
	<h3>添加维修记录</h3>
	<div class="divider"></div>

		<div class="">
			<div class="example-code clearfix">

				<form action="" class="col-md-20 center-margin" method="get">
					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 内容: </label>
						</div>
						<div class="form-input col-md-5">
						 <input placeholder="以字母开头" id="contentname" type="text">
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
							<label> 维修人: </label>
						</div>
						<div class="form-input col-md-5">
							<select id='userId'>
								<option value='1'>一</option>
								<option value='2'>二</option>
							</select>
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 维护时间: </label>
						</div>
						<div class="form-input col-md-5">
							<input id="recordTime" type="text">
						</div>
						<div class="form-input col-md-1">
							*
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 维护站点: </label>
						</div>
						<div class="form-input col-md-5">
							<select id='stationId'>
								<option value='1'>一</option>
								<option value='2'>二</option>
							</select>
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 维护设备: </label>
						</div>
						<div class="form-input col-md-5">
							<select id='equipmentId'>
								<option value='1'>一</option>
								<option value='2'>二</option>
							</select>
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
					</div>
					<div class="form-label col-md-2">
					<input class="btn medium primary-bg"  style="width:80px" value="提交" type="button" onclick="saveObject();"/>
					
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
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

        ifuserexis();
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
        window.location.href='${basepath}/user/tolist';
	}


	var exis=0
	function ifuserexis() {
        var account="";

        $("#account").mousedown(function(){
            $("#usernameexis").css("display","none");

            $("#account").mouseleave(function(){
                account=$('#account').val();
                if(account==""){
                    $("#namelabel").html("用户名不能为空");
                    $("#usernameexis").css("display","block");
                }else {
                    exis=0;
                    $("#usernameexis").css("display","none");
                    //判断用户是否已存在
                    $.ajax({
                        url:"${basepath}/user/confirmexist",
                        type:"post",
                        data:{account:account},
                        dataType:"json",
                        async:false,
                        success: function (data) {
                            if (data.total > 0) {
                                exis=1;
                                $("#namelabel").html("用户名已存在");
                                $("#usernameexis").css("display","block");
                            }
                        }
                    });
                }

            });
		});

    }

	function saveObject(){
		var account=$('#account').val();
		var password=$('#password').val();
        var name=$('#name').val();
		var tel=$('#tel').val();
        var email=$('#email').val();
        var unitId=$('#unitId').val();
		var roleId=$('#roleId').val();
        var power=$('#power').val();

		if(account==""||password==""){
			alert("用户名或密码不能为空。");
			return
		}
        var first = account.substring(0,1);
        if (!(first >= 'a' && first <= 'z') || (first >= 'A' && first <= 'Z'))
		{
		alert("用户名必须以小写字母开头");
		return;
        }

        if(exis==1){
            alert("用户名已存在,无法创建");
            return;
		}
		//添加用户
		$.ajax({
			url:"${basepath}/user/addobject",
			type:"post",
			data:{account:account,password:password,name:name,tel:tel,
				email:email,unitId:unitId,roleId:roleId},
			dataType:"json",
			async:false,
			success: function (data) {
				if(data>0){
					alert("添加成功");
					changeTitle2();
					window.location.href='${basepath}/user/tolist';
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

				<form class="col-md-20 center-margin">
					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 用户名: </label>
						</div>
						<div class="form-input col-md-5">
						 <input placeholder="以字母开头" id="account" type="text">
						</div>
						<div class="form-input col-md-1">
						*
						</div>
						<div class="form-input col-md-2">
						带*为必填项
						</div>
						<div class="form-input col-md-3" id="usernameexis" style="display:none">
							<label style="color: red" id="namelabel"></label>
						</div>

					</div>
					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 密码: </label>
						</div>
						<div class="form-input col-md-5">
						 <input id="password" type="password">
						</div>
						<div class="form-input col-md-1">
						*
						</div>
					</div>
					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 姓名: </label>
						</div>
						<div class="form-input col-md-5">
							<input id="name" type="text">
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 电话: </label>
						</div>
						<div class="form-input col-md-5">
							<input id="tel" type="text">
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 邮箱: </label>
						</div>
						<div class="form-input col-md-5">
							<input id="email" type="text">
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 所属单位: </label>
						</div>
						<div class="form-input col-md-5">
							<select id='unitId'>
								<option value='1'>单位一</option>
								<option value='2'>单位二</option>
							</select>
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 角色: </label>
						</div>
						<div class="form-input col-md-5">
						 <select id='roleId'>
						 	<option value='1'>管理员</option>
						 	<option value='2'>用户</option>
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
<!-- AUI Framework -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	pageContext.setAttribute("basepath", request.getContextPath());
%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="${basepath}/main/css.jsp"></jsp:include>
<title>水质监测系统</title>

<style type="text/css">
#pagination {
	margin: 0px 0px;
}

.pagination>li>a {
	background: #57697e;
	border: #c1cad5 solid 1px;
	color: #fff
}

.pagination>.active>a {
	border: #c1cad5 solid 1px;
	background: #e4e9f0;
	color: #57697e
}
.btn-xs{padding: 1px 2px;width: 43px}


.linear{ 

width:100%; 

height:600px; 

background: -ms-linear-gradient(top, #5E87B0,#CBD8E6);        /* IE 10 */

background:-moz-linear-gradient(top,#5E87B0,#CBD8E6);/*火狐*/ 

background:-webkit-gradient(linear, 0% 0%, 0% 100%,from(#b8c4cb), to(#f6f6f8));/*谷歌*/ 

background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#fff), to(#0000ff));      /* Safari 4-5, Chrome 1-9*/

background: -webkit-linear-gradient(top, #5E87B0,#CBD8E6);   /*Safari5.1 Chrome 10+ #1010FF*/

background: -o-linear-gradient(top,#5E87B0,#CBD8E6);  /*Opera 11.10+*/

} 

</style>

<script src="${basepath }/static/js/listpage/jquery-2.0.3.min.js"></script>

<script type="text/javascript">
	if(window.parent!=window){
		window.parent.location='${basepath }/index.jsp';
	}

	jQuery(document).ready(function(){

		var flag = '${param.flag}';
        if(flag==-2){
            alert("该账户已停用,请联系系统管理员！");
        }

        if(flag==-1){
            alert("账号或密码错误！");
        }
	});
	
	function login(){
		var userName= jQuery('#userName').val();
		var password= jQuery('#password').val();
		if(userName==''||userName==null||password==''||password==null){
			alert('用户名、密码不能为空');
			return;
		}else{
			jQuery("#loginForm").submit();
		}
		
	}
	
	
</script>
</head>
<body class="linear" style='background: url("${basepath}/static/images/bg.jpg") no-repeat center;background-size:cover; '>

	<div style='width: 100%;text-align: center'>

	</div>
	
		<div class='col-md-12'>
			<form class="form-inline" id='loginForm' role="form" method="post"  action='${basepath }/user/login' style='margin-top: 18%;margin-left: 57%;width: 260px '>
				<div class="form-group form-row" style=' font-family:"楷体"; font-size:30px; color: #fff;'>
					水质监测系统
				</div>
				<br><br><br>
				<div>
					<div class="form-group form-row">
						<label class="" style='color:#fff'>账号：</label>
					</div>
					<div class="form-group form-row">
						<input type="text" class="form-control"
							 value="" name='account' id='userName'>
					</div>
				</div>
				<br>
				<div>
					<div class="form-group form-row">
						<label class="" style='color:#fff'>密码：</label> 
					</div>
					<div class="form-group form-row">
						<input type="password" class="form-control" name='password' id='password'>
					</div>
				</div>
				<br>
				<div class="form-group form-row" style='text-align: right'>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-info" style='background-color: #0267B7' onclick='login()'>登录</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="reset"  style='background-color: #0267B7' class="btn btn-info">重置</button>
				</div>
				
			</form>
		</div>

</body>


</html>
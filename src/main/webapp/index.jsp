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
<jsp:include page="${basepath}/main/js.jsp"></jsp:include>
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


<script type="text/javascript">
	jQuery(document).ready(function(){
        window.location.href='${basepath }/user/login';
	});
</script>

</head>
<body>

</body>

</html>
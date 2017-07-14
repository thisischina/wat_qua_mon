<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    pageContext.setAttribute("basepath", request.getContextPath());
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    var usersession="${sessionScope.user}";
    if(usersession==""){
        window.location.href='${basepath}/index.jsp';
    }
</script>

<header class="navbar clearfix" id="header" style='padding-top: 15px;  background: url("${basepath}/assets/images/banner.jpg") no-repeat center;background-size:cover;'>
    <div class="container">
        <div class="navbar-brand" style='width: 30%;min-width: 246px' id='logoTitle'>
            <div style='color:#fff;font-family:"楷体";font-weight:bold;font-style:italic;font-size: 30px;line-height:30px;width: 250px '>水质监测系统</div>
        </div>
        <!-- 导航 -->
        <div style='float: left;margin-bottom: 10px ' style="width: 700px">
            <ul class="nav navbar-nav  " id="navbar-left" style="width: 700px">
                <li class="" style='cursor: pointer;margin-right: 30px' onclick='window.location="${basepath }/index/index_five"'>
                    <p style="font-size: larger ;color: white">监测信息</p>
                </li>
                <li class="" style='cursor: pointer;' onclick='window.location="${basepath }/index/index_one"'>
                    <p style="font-size: larger ;color: white">系统管理</p>
                </li>
                <li  style='cursor: pointer;margin-left: 50px' onclick='window.location="${basepath }/index/index_two"'>
                    <p style="font-size: larger ;color: white">统计报表</p>
                </li>
                <li class="" style='cursor: pointer;margin-left: 50px' onclick='window.location="${basepath }/index/index_three"'>
                    <p style="font-size: larger ;color: white">预警信息</p>
                </li>
                <li class="" style='cursor: pointer;margin-left: 50px' onclick='window.location="${basepath }/index/index_four"'>
                    <p style="font-size: larger ;color: white">通知公告</p>
                </li>
            </ul>
        </div>
        <!-- /导航 -->
        <div style='float: right;min-width: 150px;text-align: right;color: #fff;font-size: 16px;line-height: 65px'>
            <ul class="nav navbar-nav pull-right" style="width: 230px;min-width: 230px;">
                <!-- BEGIN NOTIFICATION DROPDOWN -->
                <li class="dropdown" id="header-notification" style="margin-top: 10px">
                    <a href="${basepath }/statistics/alarmNode.action" class="dropdown-toggle" data-toggle="dropdown" >
                        <i class="fa fa-bell" style='color:orange'></i>
                        <span id="count" class="badge"></span>
                    </a>
                </li>
                <!-- END NOTIFICATION DROPDOWN -->
                <!-- BEGIN USER LOGIN DROPDOWN -->
                <li class="dropdown user" id="header-user" style="float: right">
                    <c:if test="${sessionScope.user!=null}">
						<span>账号：${sessionScope.user.name }	&nbsp;&nbsp;&nbsp;
						<a href='${basepath }/user/logout' style='color:#fff;cursor: pointer;'> 注销</a>	</span>
                    </c:if>
                </li>
                <!-- END USER LOGIN DROPDOWN -->
            </ul>
        </div>
    </div>
</header>

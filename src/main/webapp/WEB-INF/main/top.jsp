<%@ page import="com.hd.ibus.util.shenw.Value" %><%--* Created by GitHub:thisischina .--%>
<%--* 系统顶页--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    pageContext.setAttribute("basepath", request.getContextPath());
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                        <a href='javascript:' onclick="setnewpasswoed(${sessionScope.user.id },'${sessionScope.user.account }')" style='color:#fff;cursor: pointer;'> 改密</a>
                    </c:if>
                </li>
                <!-- END USER LOGIN DROPDOWN -->
            </ul>
        </div>
    </div>

</header>

<script>
    function setnewpasswoed(id,account) {
//        使用发现swal应该为一个单利，所以每个页面的逻辑判断new Promise即可
        swal({
                title: "修改密码",
                html:
                '<input id="password" type="password" placeholder="新密码" class="swal2-input">' +
                '<input id="confirmpassword" type="password" placeholder="确认密码" class="swal2-input">',
                showCloseButton: true,
                allowOutsideClick: false,
                preConfirm: function () {
                    return new Promise(function (resolve,reject) {
                        var password=$("#password").val();
                        var confirmpassword=$("#confirmpassword").val();
                        var parm=<%=Value.USER_PASSWORD%>;


                        if (password==""||confirmpassword=="") {
                            reject('密码不能为空')
                        } if (password!=confirmpassword) {
                            reject('两次输入的密码不一致')
                        }if(!parm.test(password)){
                            reject('<%=Value.USER_PASSWORD_EM%>')
                        }
                        else{
                            $.ajax({
                                url:"${basepath }/user/updatepw",
                                type:"post",
                                data:{id:id,account:account,password:password},
                                dataType:"json",
                                success: function (data) {
                                    if(data==1){
                                        resolve();
                                    }else{
                                        swal(
                                            '未知错误',
                                            '未知错误，请联系系统管理员 :)',
                                            'error'
                                        );
                                    }
                                }
                            });
                        }


                    })
                }
            }).then(function () {
                swal({
                    title: '修改成功',
                    text: '您已经修改了密码，请重新登录.',
                    type: 'success',
                    allowOutsideClick: false,
                    preConfirm: function () {
                        return new Promise(function () {
                              window.location.href='${basepath}/index.jsp'
                        })
                    }
                })
        })
    }
</script>
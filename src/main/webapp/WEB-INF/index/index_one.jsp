<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	pageContext.setAttribute("basepath",request.getContextPath());
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="${basepath}/main/css.jsp"></jsp:include>

	<title>水质监测系统</title>

	<jsp:include page="${basepath}/main/js.jsp"></jsp:include>

	<script>
        jQuery(document).ready(function() {
            initBody();
        });

        //页面加载时设置frame高度
        function initBody(){
            var width = document.body.clientWidth //BODY对象宽度
            if(width<1000){
                jQuery('#logoTitle').css({width: "20%" });
            }
            var height = document.body.clientHeight //BODY对象高度
            jQuery('#iframe').height(height-200);
        }

        function user(){
            jQuery('.nal_li').removeClass('active');
            jQuery('#li_project').addClass('active');
            var htmlss = "<li id='title1'><i class='fa fa-home'/>系统管理</li><li id='title2'><a href='javascript:user();'>用户管理</a></li>";
            $("#ul-x").html(htmlss);
            document.getElementById("iframe").src="${basepath }/user/tolist?pageNow=0";
		}

        function station(){
            jQuery('.nal_li').removeClass('active');
            jQuery('#li_project').addClass('active');
            var htmlss = "<li id='title1'><i class='fa fa-home'/>系统管理</li><li id='title2'><a href='javascript:'>监测站管理</a></li>";
            $("#ul-x").html(htmlss);
            document.getElementById("iframe").src="${basepath }/station/tolist?pageNow=0";
        }

        function role(){
            jQuery('.nal_li').removeClass('active');
            jQuery('#li_project').addClass('active');
            var htmlss = "<li id='title1'><i class='fa fa-home'/>系统管理</li><li id='title2'><a href='javascript:'>角色管理</a></li>";
            $("#ul-x").html(htmlss);
            document.getElementById("iframe").src="${basepath }/role/tolist?pageNow=0";
        }

        function unit(){
            jQuery('.nal_li').removeClass('active');
            jQuery('#li_project').addClass('active');
            var htmlss = "<li id='title1'><i class='fa fa-home'/>系统管理</li><li id='title2'><a href='javascript:'>单位管理</a></li>";
            $("#ul-x").html(htmlss);
            document.getElementById("iframe").src="${basepath }/unit/tolist?pageNow=0";
        }

        function equipment(){
            jQuery('.nal_li').removeClass('active');
            jQuery('#li_project').addClass('active');
            var htmlss = "<li id='title1'><i class='fa fa-home'/>系统管理</li><li id='title2'><a href='javascript:'>设备管理</a></li>";
            $("#ul-x").html(htmlss);
            document.getElementById("iframe").src="${basepath }/equipment/tolist?pageNow=0";
        }

        function systemLog(){
            jQuery('.nal_li').removeClass('active');
            jQuery('#li_project').addClass('active');
            var htmlss = "<li id='title1'><i class='fa fa-home'/>系统管理</li><li id='title2'><a href='javascript:'>系统日志</a></li>";
            $("#ul-x").html(htmlss);
            document.getElementById("iframe").src="${basepath }/systemlog/tolist?pageNow=0";
        }

	</script>
</head>
<body style='overflow: hidden;font-family:"Microsoft Yahei";'>
	<!-- HEADER -->
	<jsp:include page="${basepath}/WEB-INF/main/top.jsp"></jsp:include>
	<!--/HEADER -->
	<!-- PAGE -->
	<section id="page">
		<!-- SIDEBAR -->
		<div id="sidebar" class="sidebar">
			<div class="sidebar-menu nav-collapse">
				<div class="divide-20"></div>

				<!-- SIDEBAR MENU -->
				<ul>
					<li class="nal_li active">
						<a href="javascript:user();">
							<i class="fa fa-tachometer fa-fw"></i> <span class="menu-text">用户管理</span>
							<span class="selected"></span>
						</a>
					</li>
					<li class="nal_li">
						<a href="javascript:role();">
							<i class="fa fa-tachometer fa-fw"></i> <span class="menu-text">角色管理</span>
							<span class="selected"></span>
						</a>
					</li>
					<li class="nal_li">
						<a href="javascript:">
							<i class="fa fa-tachometer fa-fw"></i> <span class="menu-text">权限管理</span>
							<span class="selected"></span>
						</a>
					</li>
					<li class="nal_li">
					<a href="javascript:unit();">
						<i class="fa fa-tachometer fa-fw"></i> <span class="menu-text">部门管理</span>
						<span class="selected"></span>
					</a>
					</li>
					<li class="nal_li">
						<a href="javascript:">
							<i class="fa fa-tachometer fa-fw"></i> <span class="menu-text">水资源信息管理</span>
							<span class="selected"></span>
						</a>
					</li>
					<li class="nal_li">
						<a href="javascript:station();">
							<i class="fa fa-tachometer fa-fw"></i> <span class="menu-text">监测站管理</span>
							<span class="selected"></span>
						</a>
					</li>
					<li class="nal_li">
						<a href="javascript:equipment();">
							<i class="fa fa-tachometer fa-fw"></i> <span class="menu-text">设备管理</span>
							<span class="selected"></span>
						</a>
					</li>
					<li class="nal_li">
						<a href="javascript:systemLog();">
							<i class="fa fa-tachometer fa-fw"></i> <span class="menu-text">系统日志</span>
							<span class="selected"></span>
						</a>
					</li>
					<li class="nal_li">
						<a href="javascript:">
							<i class="fa fa-tachometer fa-fw"></i> <span class="menu-text">参数设置</span>
							<span class="selected"></span>
						</a>
					</li>
				</ul>
				<!-- /SIDEBAR MENU -->

			</div>
		</div>
		<!-- /SIDEBAR -->
		<div id="main-content">
			<div class="container">
				<div class="row">
					<div id="content" class="col-lg-12">
						<!-- PAGE HEADER-->
						<div class="row">
							<div class="col-sm-12">
								<div class="page-header" style='min-height: 52px;margin:0 -15px 0px'>
									<!-- BREADCRUMBS -->
									<ul class="breadcrumb" id="ul-x" style='margin-top: 10px'>
									</ul>
									<!-- /BREADCRUMBS -->
								</div>
							</div>
						</div>
						<!-- /PAGE HEADER -->
						<!-- DASHBOARD CONTENT -->
						<div class="row">
							<iframe id='iframe' src="" width="100%" style='overflow-y :auto;border: 0px'>
							</iframe>
						</div>
					   <!-- /DASHBOARD CONTENT -->
					</div><!-- /CONTENT-->
				</div>
			</div>
		</div>
	</section>
	
</body>
</html>
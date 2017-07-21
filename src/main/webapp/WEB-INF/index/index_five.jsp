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
            jQuery('#iframe').height(height-120);
        }

	</script>

	<script>
        function monitordata(){
            jQuery('.nal_li').removeClass('active');
            jQuery('#li_project').addClass('active');
            var htmlss = "<li id='title1'><i class='fa fa-home'/>系统管理</li><li id='title2'><a href='javascript:nodeInfo();'>检测数据信息</a></li>";
            $("#ul-x").html(htmlss);
            document.getElementById("iframe").src="${basepath }/monitordata/tolist?pageNow=0";
        }

        function projectInfo(){
            jQuery('.nal_li').removeClass('active');
            var htmlss = "<li id='title1'><i class='fa fa-home'/>系统管理</li><li id='title2'><a href='javascript:nodeInfo();'>检测数据信息</a></li>";
            $("#ultt").html(htmlss);
            document.getElementById("iframe").src="${basepath }/user/user_list";
        }

        function map(){
            jQuery('.nal_li').removeClass('active');
            var htmlss = "<li id='title1'><i class='fa fa-home'/>系统管理</li><li id='title2'><a href='javascript:nodeInfo();'>实时站点检测</a></li>";
            $("#ul-x").html(htmlss);
            document.getElementById("iframe").src="${basepath}/main/homepage.html";
        }


        function waterSys(){
            jQuery('.nal_li').removeClass('active');
            jQuery('#li_project').addClass('active');
            var htmlss = "<li id='title1'><i class='fa fa-home'/>系统管理</li><li id='title2'><a href='javascript:waterSys();'>监测设备列表</a></li>";
            $("#ul-x").html(htmlss);
            document.getElementById("iframe").src="${basepath }/waterSys/to_list";
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
						<a href="javascript:map()">
							<i class="fa fa-tachometer fa-fw"></i> <span class="menu-text">实时站点检测</span>
							<span class="selected"></span>
						</a>
					</li>

					<li class="nal_li">
						<a href="javascript:monitordata()">
							<i class="fa fa-tachometer fa-fw"></i> <span class="menu-text">检测数据列表</span>
							<span class="selected"></span>
						</a>
					</li>

					<li class="nal_li">
						<a href="javascript:waterSys()">
							<i class="fa fa-tachometer fa-fw"></i> <span class="menu-text">监测设备列表</span>
							<span class="selected"></span>
						</a>
					</li>

				</ul>
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
										<li>
											<i class="fa fa-home"></i>
											<a href="javascript:void(0)">检测信息</a>
										</li>
										<li><span id="sp">实时站点检测</span></li>
									</ul>
									<!-- /BREADCRUMBS -->
								</div>
							</div>
						</div>
						<!-- /PAGE HEADER -->
						<!-- DASHBOARD CONTENT -->
						<div class="row">
							<iframe id='iframe' src="${basepath}/static/html/homepage.html" width="100%" style='overflow-y :auto;border: 0px'>
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
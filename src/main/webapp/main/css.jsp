<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/30 0030
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    pageContext.setAttribute("basepath",request.getContextPath());
%>

<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
<meta name="description" content="">
<meta name="author" content="qq:874114275">

<%--列表页面--%>
<link rel="stylesheet" type="text/css" href="${basepath }/assets/css/cloud-admin.css" >
<link rel="stylesheet" type="text/css" href="${basepath }/assets/css/default.css" id="skin-switcher" >
<!-- STYLESHEETS --><!--[if lt IE 9]><script src="js/flot/excanvas.min.js"></script><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script><![endif]-->
<link href="${basepath }/assets/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${basepath }/assets/css/styles.css" />
<link rel="stylesheet" type="text/css" href="${basepath }/assets/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" />

<!-- Theme UI -->
<link id="layout-theme" rel="stylesheet" type="text/css"
      href="${basepath }/assets/themes/minified/agileui/color-schemes/layouts/default.min.css">
<link id="elements-theme" rel="stylesheet" type="text/css"
      href="${basepath }/assets/themes/minified/agileui/color-schemes/elements/default.min.css">
<!-- AgileUI Responsive -->
<link rel="stylesheet" type="text/css"
      href="${basepath }/assets/themes/minified/agileui/responsive.min.css">
<!-- STYLESHEETS -->


<%--更新页面--%>
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

<!-- AgileUI CSS Core -->

<link rel="stylesheet" type="text/css"
      href="${basepath }/assets/css/minified/aui-production.min.css">

<link rel="stylesheet" type="text/css"
      href="${basepath }/assets/themes/minified/agileui/animations.min.css">

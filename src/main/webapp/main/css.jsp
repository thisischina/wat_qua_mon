<%--
  Created by github:thisischina.
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
<link rel="stylesheet" type="text/css" href="${basepath }/static/css/listpage/cloud-admin.css"/>
<link rel="stylesheet" type="text/css" href="${basepath }/static/css/listpage/default.css"/>
<link rel="stylesheet" type="text/css" href="${basepath }/static/css/listpage/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="${basepath }/static/css/listpage/styles.css"/>
<link rel="stylesheet" type="text/css" href="${basepath }/static/css/listpage/zTreeStyle.css"/>
<link rel="stylesheet" type="text/css" href="${basepath }/static/css/listpage/default.min.css"/>
<link rel="stylesheet" type="text/css" href="${basepath }/static/css/listpage/default-one.min.css"/>
<link rel="stylesheet" type="text/css" href="${basepath }/static/css/listpage/responsive.min.css"/>
<%--弹窗页--%>
<link rel="stylesheet" type="text/css" href="${basepath }/static/css/listpage/sweetalert2.min.css"/>
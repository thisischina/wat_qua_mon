<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/30 0030
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    pageContext.setAttribute("basepath",request.getContextPath());
%>

<script type="text/javascript" src="${basepath }/static/js/listpage/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="${basepath }/static/js/listpage/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="${basepath }/static/js/listpage/fuelux.tree-sampledata.js"></script>
<script type="text/javascript" src="${basepath }/static/js/listpage/fuelux.tree.min.js"></script>
<script type="text/javascript" src="${basepath }/static/js/listpage/jquery.twbsPagination.min.js"></script>

<!-- 弹窗 -->
<script type="text/javascript" src="${basepath }/static/js/listpage/sweetalert2.min.js"></script>

<script>
    jQuery(document).ready(function() {
        var usersession="${sessionScope.user}";
        if(usersession==""){
            window.location.href='${basepath}/index.jsp';
        };
    });
</script>
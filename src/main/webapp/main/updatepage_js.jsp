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

<script src="${basepath }/static/js/updatepage/jquery-1.10.1.min.js" type="text/javascript"></script>

<script src="${basepath }/static/js/updatepage/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

<script src="${basepath }/static/js/updatepage/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>

<script src="${basepath }/static/js/updatepage/bootstrap.min.js" type="text/javascript"></script>

<!--[if lt IE 9]>

<script src="${basepath }/static/js/updatepage/excanvas.min.js"></script>

<script src="${basepath }/static/js/updatepage/respond.min.js"></script>

<![endif]-->

<script src="${basepath }/static/js/updatepage/jquery.slimscroll.min.js" type="text/javascript"></script>

<script src="${basepath }/static/js/updatepage/jquery.blockui.min.js" type="text/javascript"></script>

<script src="${basepath }/static/js/updatepage/jquery.cookie.min.js" type="text/javascript"></script>

<script src="${basepath }/static/js/updatepage/jquery.uniform.min.js" type="text/javascript" ></script>

<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL PLUGINS -->

<script type="text/javascript" src="${basepath }/static/js/updatepage/ckeditor.js"></script>

<script type="text/javascript" src="${basepath }/static/js/updatepage/bootstrap-fileupload.js"></script>

<script type="text/javascript" src="${basepath }/static/js/updatepage/chosen.jquery.min.js"></script>

<script type="text/javascript" src="${basepath }/static/js/updatepage/select2.min.js"></script>

<script type="text/javascript" src="${basepath }/static/js/updatepage/wysihtml5-0.3.0.js"></script>

<script type="text/javascript" src="${basepath }/static/js/updatepage/bootstrap-wysihtml5.js"></script>

<script type="text/javascript" src="${basepath }/static/js/updatepage/jquery.tagsinput.min.js"></script>

<script type="text/javascript" src="${basepath }/static/js/updatepage/jquery.toggle.buttons.js"></script>

<script type="text/javascript" src="${basepath }/static/js/updatepage/bootstrap-datepicker.js"></script>

<script type="text/javascript" src="${basepath }/static/js/updatepage/bootstrap-datetimepicker.js"></script>

<script type="text/javascript" src="${basepath }/static/js/updatepage/clockface.js"></script>

<script type="text/javascript" src="${basepath }/static/js/updatepage/date.js"></script>

<script type="text/javascript" src="${basepath }/static/js/updatepage/daterangepicker.js"></script>

<script type="text/javascript" src="${basepath }/static/js/updatepage/bootstrap-colorpicker.js"></script>

<script type="text/javascript" src="${basepath }/static/js/updatepage/bootstrap-timepicker.js"></script>

<script type="text/javascript" src="${basepath }/static/js/updatepage/jquery.inputmask.bundle.min.js"></script>

<script type="text/javascript" src="${basepath }/static/js/updatepage/jquery.input-ip-address-control-1.0.min.js"></script>

<script type="text/javascript" src="${basepath }/static/js/updatepage/jquery.multi-select.js"></script>

<script type="text/javascript" src="${basepath }/static/js/updatepage/bootstrap-modal.js"></script>

<script type="text/javascript" src="${basepath }/static/js/updatepage/bootstrap-modalmanager.js"></script>

<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->

<script type="text/javascript" src="${basepath }/static/js/updatepage/app.js"></script>

<script type="text/javascript" src="${basepath }/static/js/updatepage/form-components.js"></script>

<script>
    jQuery(document).ready(function() {
        var usersession="${sessionScope.user}";
        if(usersession==""){
            window.location.href='${basepath}/index.jsp';
        };
    });
</script>
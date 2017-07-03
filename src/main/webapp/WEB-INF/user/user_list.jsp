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

    <title>Cloud Admin | Dashboard</title>

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

        #tbody td{text-align: center}

    </style>

    <jsp:include page="${basepath}/main/js.jsp"></jsp:include>

    <script type="text/javascript">
        jQuery(document).ready(function() {
            role = "${sessionScope.user.role }";

            loadDataGird();
        });

        var createTable = function(index, item) {
            var roleName = "";
            if(item.role==1){
                roleName="管理员"
            }
            if(item.role==2){
                roleName="用户"
            }

            var str = "";
            if(index%2==0){
                str =str+"<tr style='background:RGB(211,223,238)'>" + "<td>";
            }else{
                str =str+"<tr>" + "<td>";
            }
            str=str+ (index + 1)
                + "</td>"
                + "<td>"
                + (item.userName == null ? "" : item.userName)
                + "</td>"
                + "<td>"
                +roleName
                + "</td>"
                + "<td>"
                + (item.tel == null ? "" : item.tel)
                + "</td>"
                + "<td>"
                + (item.email == null ? "" : item.email)
                + "</td>";

            if(role==1){
                str=str+ "<td>"
                    + "<div class=''>"
                    + "<a class='btn btn-xs btn-info' href='../User/directUpdateUser.action?id="
                    + item.id
                    + "' style='height:20px;font-size:10px;margin-right:4px'>"
                    + "<i class='ace-icon fa fa-pencil bigger-120'></i>修改"
                    + "</a>"
                    + "<a class='btn btn-xs btn-danger' href='javascript:void(0)' onclick='deleteUser("
                    + item.id
                    + ")' style='height:20px;font-size:10px;'>"
                    + "<i class='ace-icon fa fa-trash-o bigger-120'></i> 删除"
                    + "</a>" + "</div>" + "</td>" + "</tr>	";
            }else{
                str=str+ "</tr>	";
            }


            //alert(item.userid)
            $("#tbody").append(str);
        }

        var loadDataGird = function() {
            $.ajax({
                    url : "${basepath }/user/listall",
                    type : "post",
                    data : {},
                    datatype : "json",
                    success : function(data) {
                        $("#tbody").html("");
                        $('#pagination_div').html("");

                        if (data == null || data.total == 0) {
                            return;
                        }
                        $.each(data.list, function(index, item) { //遍历返回的json
                            createTable(index, item);
                        });
                        //分页插件
                        $('#pagination_div')
                            .html(
                                "<ul id='pagination' class='pagination-sm' style='float:right'></ul>");
                        $('#pagination').twbsPagination(
                                {
                                    totalPages : data.pageCount,
                                    visiblePages : 5,
                                    first : '首页',
                                    prev : '上一页',
                                    next : '下一页',
                                    last : '末页',
                                    onPageClick : function(event, page) {
                                        $.ajax({
                                                url : "${basepath }/user/findList",
                                                type : "post",
                                                data : "pageNow="
                                                + page,
                                                datatype : "json",
                                                success : function(
                                                    data) {
                                                    $("#tbody")
                                                        .html(
                                                            "");
                                                    if (data == null
                                                        || data.total == 0) {
                                                        return;
                                                    }
                                                    $
                                                        .each(
                                                            data.list,
                                                            function(
                                                                index,
                                                                item) {
                                                                createTable(
                                                                    index,
                                                                    item);
                                                            });
                                                }
                                            });
                                    }
                                });
                        //分页插件end

                    }
                });
        }
    </script>
</head>
<body>


<div class='row' style="margin: 0px">
    <div class='col-md-12'  style='height: 15px'>
        <!-- <form class="form-inline" role="form"
            style='float: right;margin-bottom:10px '>
            <div class="form-group">
                <label class="sr-only" for="exampleInputEmail2">Email
                    address</label> <input type="email" class="form-control"
                    id="exampleInputEmail2" placeholder="Enter email">
            </div>
            <div class="form-group">
                <label class="sr-only" for="exampleInputPassword2">Password</label>
                <input type="password" class="form-control"
                    id="exampleInputPassword2" placeholder="Password">
            </div>
            <button type="submit" class="btn btn-success">Sign in</button>
        </form> -->
    </div>
</div>
<div class='row' style="margin: 0px">
    <div class='col-md-12'>
        <table class="table table-bordered">
            <thead style='background:#A8BC7B;color:#fff'>
            <tr>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>账号</th>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>用户名</th>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>手机</th>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>邮箱</th>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>角色</th>

                <c:if test="${ sessionScope.user.role==1}">
                    <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>操作</th>
                </c:if>
            </tr>
            </thead>
            <tbody id="tbody">

            </tbody>
        </table>
        <div class="dataTables_info" id="dynamic-table_info"
             style="float: left;">
            <c:if test="${ sessionScope.user.role==1}">
                <a class="btn btn-info" style='background: #4F81BD;border: 1px solod #4F81BD'
                   href="${basepath }/User/directAddUser.action"> <span
                        class="button-content">添加</span> </a>
            </c:if>
        </div>
        <div id="pagination_div" style='float: right;padding-right: 0px'>

        </div>
    </div>
</div>


</body>


</html>
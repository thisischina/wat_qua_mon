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

    <title>消息列表</title>

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
            var selectType=0;//表示未点击查询按钮
            loadDataGird(selectType);
        });

        var createTable = function(index, item,pageNow) {

            var str = "";
            if(index%2==0){
                str =str+"<tr style='background:RGB(211,223,238)'>" + "<td>";
            }else{
                str =str+"<tr>" + "<td>";
            }
            str=str+ (index + 1)
                + "</td>"
                + "<td>"
                + (item.title == null ? "" : item.title)
                + "</td>"
                + "<td>"
                + (item.content == null ? "" : item.content)
                + "</td>"
                + "<td>"
                + (item.sendUserId == null ? "" : item.sendUserId)
                + "</td>"
                + "<td>"
                + (item.sendTime == null ? "" : item.sendTime)
                + "</td>"
                + "<td>"
                + (item.receiveUserIds == null ? "" : item.receiveUserIds)
                + "</td>"

                str=str+ "</tr>	";

            $("#tbody").append(str);
        }

        var loadDataGird = function(selectType) {
            var url="";
            if(selectType==1){
//                表示点击查询按钮,初始化当前页为1
                url="${basepath }/news/getlist?pageNow=1";
            }else{
                url="${basepath }/news/getlist?pageNow="+${pageHelp.pageBean.pageNow};
            }

            var title = $("#title").val();
            $.ajax({
                    url : url,
                    type : "post",
                    data : {title:title},
                    dataType : "json",
                    success : function(data) {

                        $("#tbody").html("");
                        $('#pagination_div').html("");

                        if (data == null || data.total == 0) {
                            return;
                        }
                        $.each(data.list, function(index, item) { //遍历返回的json
                            createTable(index, item,data.pageNow);
                        });

                        //分页插件
                        $('#pagination_div').html(
                            "<ul id='pagination' class='pagination-sm' style='float:right'></ul>");
                        $('#pagination').twbsPagination({
                            startPage : data.pageNow,
                            totalPages : data.pageCount,
                            visiblePages : 5,
                            first : '首页',
                            prev : '上一页',
                            next : '下一页',
                            last : '末页',
                            onPageClick : function(event, page) {
                                var title = $("#title").val();
                                $.ajax({
                                    url : "${basepath }/news/getlist",
                                    type : "post",
                                    data : {pageNow:page,title:title},
                                    dataType : "json",
                                    success : function(data) {
                                        $("#tbody").html("");

                                        if (data == null|| data.total == 0) {
                                            return;
                                        }
                                        $.each(data.list,function(index,item) {
                                            createTable(index,item,data.pageNow);
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
    <div class='col-md-12'>
        <form class="form-inline" role="form"
              style='float: right;margin-bottom:10px;margin-top: 5px;margin-left: 5px '>
            <div class="form-group">
                <input type="text" class="form-control" id="title" value="${pageHelp.selectStr}" placeholder="标题">
            </div>
            <button type="button" onclick="loadDataGird(1)" class="btn btn-info"  style='background: #4F81BD;border: 1px solid #4F81BD' >查询</button>
        </form>
    </div>
</div>

<div class='row' style="margin: 0px">
    <div class='col-md-12'>
        <table class="table table-bordered">
            <thead style='background:#A8BC7B;color:#fff'>
            <tr>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>序号</th>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>标题</th>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>内容</th>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>发布人</th>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>发布时间</th>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>接收人</th>
            </tr>
            </thead>
            <tbody id="tbody">

            </tbody>
        </table>
        <div id="pagination_div" style='float: right;padding-right: 0px'>

        </div>
    </div>
</div>


</body>


</html>
<%--
  Created by IntelliJ IDEA.
  User: ThinkPad
  Date: 2017-07-31
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="${basepath}/main/css.jsp"></jsp:include>

    <title>检测站点列表</title>

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

            var createTable = function(index, item,pageNow) {

                var str = "";
                if(index%2==0){
                    str =str+"<tr style='background:RGB(211,223,238)'>" + "<td>";
                }else{
                    str =str+"<tr>" + "<td>";
                }
                str=str + (item.dtuid == null ? "" : item.dtuid)
                        + "</td>"
                        + "<td>"
                        + (item.dscaddr == null ? "" : item.dscaddr)
                        + "</td>"
                        + "<td>"
                        + (item.keepalive == null ? "" : item.keepalive)
                        + "</td>"
                        + "<td>"
                        + (item.uartcfg == null ? "" : item.uartcfg)
                        + "</td>"
                        + "<td>"
                        + (item.debugmode == null ? "" : item.debugmode)
                        + "</td>"
                        + "<td>"
                        + (item.dtufilter == null ? "" : item.dtufilter)
                        + "</td></tr>"


////            if(role==1){
//                str=str+ "<td>"
//                        + "<div class=''>"
//                        + "<a class='btn btn-xs btn-info' href='../equipment/toupdate?id="+item.id
//                        + "' style='height:20px;font-size:10px;margin-right:4px'>"
//                        + "<i class='ace-icon fa fa-pencil bigger-120'></i>修改"
//                        + "</a>"
//                        + "<a class='btn btn-xs btn-danger' href='javascript:void(0)' onclick='deleteObject("
//                        + item.id
//                        + ")' style='height:20px;font-size:10px;'>"
//                        + "<i class='ace-icon fa fa-trash-o bigger-120'></i> 删除"
//                        + "</a>" + "</div>" + "</td>" + "</tr>	";
////            }else{
//                str=str+ "</tr>	";
////            }

                $("#tbody").append(str);
            };

        jQuery(document).ready(function() {

            var url = "${basepath }/hardware/getList?pageNow=1";

            $.ajax({
                url : url,
                type : "post",
                data : {},
                dataType : "json",
                success : function(data) {

                    alert("1");

                    if (data == null || data.total == 0) {
                        return;
                    };

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
                            var name = $("#name").val();
                            $.ajax({
                                url : "${basepath }/hardware/getlist",
                                type : "post",
                                data : {},
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
        });

        </script>

</head>
<body>

<div class='row' style="margin: 0px">
    <div class='col-md-12'>
        <table class="table table-bordered">
            <thead style='background:#A8BC7B;color:#fff'>
            <tr>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>设备id</th>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>属性2</th>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>属性3</th>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>属性4</th>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>属性5</th>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>属性6</th>
            </tr>
            </thead>
            <tbody id="tbody">

            </tbody>
        </table>
        <%--<div class="dataTables_info" id="dynamic-table_info"--%>
             <%--style="float: left;">--%>
            <%--<a class="btn btn-info" style='background: #4F81BD;border: 1px solid #4F81BD'--%>
               <%--href="${basepath}/equipment/toadd">--%>
                <%--<span class="button-content">添加</span> </a>--%>
        <%--</div>--%>
        <div id="pagination_div" style='float: right;padding-right: 0px'>

        </div>
    </div>
</div>

</body>
</html>

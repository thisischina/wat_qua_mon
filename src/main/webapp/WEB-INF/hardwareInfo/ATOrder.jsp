<%--
  Created by IntelliJ IDEA.
  User: ThinkPad
  Date: 2017-08-04
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AT指令控制</title>

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

        function sendOrder() {
            var dtuId = $('#dtuId').val();
            var orderStr = $('#order').val();

            $.ajax({

                url : "${basepath}/hardware/order",
                type : "post",
                data : {dtuId:dtuId,orderStr:orderStr},
                dataType:"json",
                success : function (data) {
                    alert("成功");
                }
                
            });
        }

    </script>
</head>
<body>

<div class="span6 ">

    <div class="control-group">

        <label class="control-label">设备Id</label>

        <div class="controls">

            <input type="text" id="dtuId" class="m-wrap span12">

        </div>

    </div>

</div>

<div class="span6 ">

    <div class="control-group">

        <label class="control-label">AT指令</label>

        <div class="controls">

            <input type="text" id="order" class="m-wrap span12">

        </div>

    </div>

</div>

<button type="button" class="btn blue" onclick="sendOrder()">确定</button>

<%--<button type="button" class="btn" onclick="returnPage();">取消</button>--%>

</body>
</html>

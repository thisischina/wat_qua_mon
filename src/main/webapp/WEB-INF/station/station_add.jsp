<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("basepath", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<jsp:include page="${basepath}/main/updatepage_css.jsp"></jsp:include>
<title>新增</title>

<!-- AgileUI JS -->

<jsp:include page="${basepath}/main/updatepage_js.jsp"></jsp:include>

<script type="text/javascript">
	jQuery(document).ready(function(){
		changeTitle();

        ifuserexis();

		App.init();

		FormComponents.init();
	});
	
	function changeTitle(){
		$('#ultt', parent.document).html("");
		var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:'>监测站信息</a></li>";
		htmlss = htmlss + "<li>添加检测站点</li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function changeTitle2(){
		$('#ultt', parent.document).html("");
		var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:'>监测站信息</a></li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function returnPage(){
		changeTitle2();
        window.location.href='${basepath}/station/tolist';
	}

    var exis=0
    function ifuserexis() {
        var name="";

        $("#name").mousedown(function(){
            $("#namelabel").css("display","none");

            $("#name").mouseleave(function(){
                name=$('#name').val();
                if(name==""){
                    $("#namelabel").html("站点名不能为空");
                    $("#namelabel").css("display","block");
                }else {
                    exis=0;
                    $("#namelabel").css("display","none");

                    //判断是否已存在
                    $.ajax({
                        url:"${basepath}/station/confirmexist",
                        type:"post",
                        data:{name:name},
                        dataType:"json",
                        async:false,
                        success: function (data) {
                            if(data.total>0){
                                exis=1;
                                $("#namelabel").html("站点名已存在");
                                $("#namelabel").css("display","block");
                            }
                        }
                    });
                }

            });
        });

    }

	function saveObject(){
		var name=$('#name').val();
		var address=$('#address').val();
		var type=$('#type').val();
        var coordinate=$('#coordinate').val();
        var unitId=$('#unitId').val();

		if(name==""){
			alert("站点名称不能为空。");
			return
		}

        if(exis==1){
            alert("用户名已存在,无法创建");
            return;
        }

        //添加用户
        $.ajax({
            url:"${basepath}/station/addstation",
            type:"post",
            data:{name:name,address:address,type:type,coordinate:coordinate,
                unitId:unitId},
            dataType:"json",
            async:false,
            success: function (data) {
                if(data>0){
                    alert("添加成功");
                    changeTitle2();
                    window.location.href='${basepath}/station/tolist';
                }
            }
        });
	}

</script>

</head>

<body style='font-family:"Microsoft Yahei"'>

<div class="portlet box">

	<div class="portlet-body form">

		<form action="#" class="horizontal-form">

			<h3 class="form-section">添加检测站点</h3>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">监测站名</label>

						<div class="controls">

							<input type="text" id="name" class="m-wrap span12">

							<span class="help-block" id="namelabel" style="color:orangered;display: none"></span>
						</div>

					</div>

				</div>

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">位置名称</label>

						<div class="controls">

							<input type="text" id="address" class="m-wrap span12">

						</div>

					</div>

				</div>

			</div>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label" >所属类型</label>

						<div class="controls">

							<select id="type"  class="m-wrap span12">

								<option value="1">一</option>

								<option value="2">二</option>

							</select>

						</div>

					</div>

				</div>

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">坐标</label>

						<div class="controls">

							<input type="text" id="coordinate" class="m-wrap span12">

						</div>

					</div>

				</div>

			</div>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label" >所属单位</label>

						<div class="controls">

							<select id="unitId"  class="m-wrap span12">

								<c:forEach items="${unitList}" var="unit">
									<option value="${unit.unitId}">${unit.name}</option>
								</c:forEach>

							</select>

						</div>

					</div>

				</div>

			</div>

			<div class="form-actions" style="padding-left: 10px">

				<button type="button" class="btn blue" onclick="saveObject()">确定</button>

				<button type="button" class="btn" onclick="returnPage();">取消</button>

			</div>

		</form>

	</div>

</div>

</body>

</html>
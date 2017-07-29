<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
	pageContext.setAttribute("basepath", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="${basepath}/main/updatepage_css.jsp"></jsp:include>
	<title>更新</title>

	<!-- AgileUI JS -->

	<jsp:include page="${basepath}/main/updatepage_js.jsp"></jsp:include>

	<script type="text/javascript">
        jQuery(document).ready(function(){
            changeTitle();

            App.init();

            FormComponents.init();
        });

	function changeTitle(){
		var htmlss = $('#ultt', parent.document).html();
		htmlss = htmlss + "<li>修改监测站信息</li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function changeTitle2(){
		$('#ultt', parent.document).html("");
		var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:projectInfo();'>监测站信息</a></li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function update(){
		var id=$('#id').val();
        var name=$('#name').val();
        var address=$('#address').val();
        var type=$('#type').val();
        var coordinate=$('#coordinate').val();
        var unitId=$('#unitId').val();

		if(name==""){
			alert("监测站名称不能为空。");
			return
		}
		   $.ajax({
			url:"${basepath }/station/update",
			type:"post",
			data:{id:id,name:name,address:address,type:type,coordinate:coordinate,
                unitId:unitId},
			dataType:"json",
			success: function (data) {
				if(data==1){
					alert("修改成功");
					changeTitle2();
					window.location.href="${basepath }/station/tolist";
				}else{
					alert("修改失败");
				}
			}
		});
		
	}

</script>

</head>

<body style='font-family:"Microsoft Yahei"'>

<div class="portlet box">

	<div class="portlet-body form">
		<input type="hidden" id="id" value='${station.id }'>
		<form action="#" class="horizontal-form">

			<h3 class="form-section">添加检测站点</h3>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">监测站名</label>

						<div class="controls">

							<input type="text" value="${station.name}" readonly="readonly" class="m-wrap span12">

						</div>

					</div>

				</div>

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">位置名称</label>

						<div class="controls">

							<input type="text" id="address" value='${station.address }' class="m-wrap span12">

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

							<input type="text" id="coordinate" value='${station.coordinate}' class="m-wrap span12">

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

									<c:if test="${user.unitId==unit.unitId}">
										<option value="${unit.unitId}" selected>${unit.name}</option>
									</c:if>
									<option value="${unit.unitId}">${unit.name}</option>

								</c:forEach>

							</select>

						</div>

					</div>

				</div>

			</div>

			<div class="form-actions" style="padding-left: 10px">

				<button type="button" class="btn blue" onclick="update()">确定</button>

				<button type="button" class="btn" onclick="window.location.href='${basepath}/station/tolist'">取消</button>

			</div>

		</form>

	</div>

</div>

</body>
</html>
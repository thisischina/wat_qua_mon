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

            App.init();

            FormComponents.init();
        });

	function changeTitle(){
		$('#ultt', parent.document).html("");
		var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:'>设备信息</a></li>";
		htmlss = htmlss + "<li>添加设备</li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function changeTitle2(){
		$('#ultt', parent.document).html("");
		var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:'>设备信息</a></li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function returnPage(){
		changeTitle2();
        window.location.href='${basepath}/equipment/tolist';
	}
	
	function saveObject(){
		var name=$('#name').val();
		var number=$('#number').val();
		var typeId=$('#typeId').val();
        var manufactor=$('#manufactor').val();
        var lifetime=$('#lifetime').val();
        var max=$('#max').val();
        var min=$('#min').val();
        var samplingFrequency=$('#samplingFrequency').val();
        var installTime=$('#installTime').val();
        var stationId=$('#stationId').val();
        var state=$('#state').val();

		if(name==""){
			alert("设备名称不能为空。");
			return
		}

        //添加
        $.ajax({
            url:"${basepath}/equipment/addequipment",
            type:"post",
            data:{name:name,number:number,manufactor:manufactor,typeId:typeId,lifetime:lifetime,
                max:max,min:min,samplingFrequency:samplingFrequency,installTime:installTime,
                stationId:stationId,state:state},
            dataType:"json",
            async:false,
            success: function (data) {
                if(data>0){
                    alert("添加成功");
                    changeTitle2();
                    window.location.href='${basepath}/equipment/tolist';
                }
            }
        });
	}

</script>

</head>
<body style='font-family:"Microsoft Yahei"'>
<div class="portlet box">

	<div class="portlet-body form">

		<form class="horizontal-form">

			<h3 class="form-section">添加设备</h3>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">设备名</label>

						<div class="controls">

							<input type="text" id="name" class="m-wrap span12">

						</div>

					</div>

				</div>

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">编号</label>

						<div class="controls">

							<input type="text" id="number" class="m-wrap span12">

						</div>

					</div>

				</div>

			</div>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">厂家</label>

						<div class="controls">

							<input type="text" id="manufactor" class="m-wrap span12">

						</div>

					</div>

				</div>

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label" >所属类型</label>

						<div class="controls">

							<select id="typeId" class="m-wrap span12">

								<option value="1">一</option>

								<option value="2">二</option>

							</select>

						</div>

					</div>

				</div>

			</div>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">维保周期</label>

						<div class="controls">

							<input type="text" id="lifetime" class="m-wrap span12">

						</div>

					</div>

				</div>

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label" >阀值上限</label>

						<div class="controls">

							<input type="text" id="max" class="m-wrap span12">

						</div>


					</div>

				</div>

			</div>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">阀值下限</label>

						<div class="controls">

							<input type="text" id="min" class="m-wrap span12">

						</div>

					</div>

				</div>

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label" >采集频率</label>

						<div class="controls">

							<input type="text" id="samplingFrequency" class="m-wrap span12">

							<i class="icon-warning-sign">单位:Hz</i>

						</div>


					</div>

				</div>

			</div>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">安装日期</label>

						<div class="controls">

							<input type="text" id="installTime" size="16"  class="m-wrap m-ctrl-medium date-picker span12">

						</div>

					</div>

				</div>

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label" >所属检测站</label>

						<div class="controls">

							<select id="stationId" class="m-wrap span12">

								<c:forEach items="${stationList}" var="station">
									<option value="${station.id}">${station.name}</option>
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
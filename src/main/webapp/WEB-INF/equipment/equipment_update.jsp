<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>修改</title>

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
        var number=$('#number').val();
        var typeId=$('#typeId').val();
        var lifetime=$('#lifetime').val();
        var max=$('#max').val();
        var min=$('#min').val();
        var samplingFrequency=$('#samplingFrequency').val();
        var installTime=$('#installTime').val();
        var stationId=$('#stationId').val();
        var state=$('#state').val();

		if(name==""){
			alert("监测站名称不能为空。");
			return
		}
		   $.ajax({
			url:"${basepath }/equipment/update",
			type:"post",
			data:{id:id,name:name,number:number,typeId:typeId,lifetime:lifetime,
                max:max,min:min,samplingFrequency:samplingFrequency,installTime:installTime,
                stationId:stationId,state:state},
			dataType:"json",
			success: function (data) {
				if(data==1){
					alert("修改成功");
					changeTitle2();
					window.location.href="${basepath }/equipment/tolist";
				}else{
					alert("添加失败");
				}
			}
		});
		
	}

</script>

</head>

<body style='font-family:"Microsoft Yahei"'>

<div class="portlet box">

	<div class="portlet-body form">
		<input type="hidden" id="id" value='${equipment.id }'>
		<form class="horizontal-form">

			<h3 class="form-section">修改设备信息</h3>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">设备名</label>

						<div class="controls">

							<input type="text" id="name" value='${equipment.name}' class="m-wrap span12">

						</div>

					</div>

				</div>

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">编号</label>

						<div class="controls">

							<input type="text" id="number" value='${equipment.number}' class="m-wrap span12">

						</div>

					</div>

				</div>

			</div>

			<div class="row-fluid">

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

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">寿命</label>

						<div class="controls">

							<input type="text" id="lifetime" value='${equipment.lifetime}' class="m-wrap span12">

						</div>

					</div>

				</div>

			</div>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label" >阀值上限</label>

						<div class="controls">

							<input type="text" id="max" value='${equipment.max}' class="m-wrap span12">

						</div>


					</div>

				</div>

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">阀值下限</label>

						<div class="controls">

							<input type="text" id="min" value='${equipment.min}' class="m-wrap span12">

						</div>

					</div>

				</div>

			</div>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label" >采集频率</label>

						<div class="controls">

							<input type="text" id="samplingFrequency" value='${equipment.samplingFrequency}' class="m-wrap span12">

						</div>


					</div>

				</div>

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">安装日期</label>

						<div class="controls">
							<script>
                                jQuery(document).ready(function(){
                                    var d='${equipment.installTime}';

                                    if(d!=null){
                                        var day=moment(d).format('YYYY-MM-DD');
                                        $("#installTime").attr("placeholder",day);
                                    }
                                })
							</script>
							<input type="text" id="installTime" size="16" readonly class="m-wrap m-ctrl-medium date-picker span12">
						</div>

					</div>

				</div>

			</div>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label" >所属检测站</label>

						<div class="controls">

							<select id="stationId" class="m-wrap span12">

								<c:forEach items="${stationList}" var="station">

									<c:if test="${equipment.stationId==station.id}">
										<option value="${equipment.stationId}" selected>${station.name}</option>
									</c:if>
									<option value="${equipment.stationId}">${station.name}</option>

								</c:forEach>

							</select>

						</div>

					</div>

				</div>



			</div>

			<div class="form-actions" style="padding-left: 10px">

				<button type="button" class="btn blue" onclick="update()">确定</button>

				<button type="button" class="btn" onclick="window.location.href='${basepath}/equipment/tolist'">取消</button>

			</div>

		</form>

	</div>

</div>

</body>

</html>
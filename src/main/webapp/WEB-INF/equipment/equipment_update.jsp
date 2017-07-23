<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
	pageContext.setAttribute("basepath", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="${basepath}/main/css.jsp"></jsp:include>
<title>监测站信息</title>

<jsp:include page="${basepath}/main/js.jsp"></jsp:include>

<script type="text/javascript">
	jQuery(document).ready(function(){
		changeTitle();
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
<body>
<div id="page-content">
	<h3>修改项目信息</h3>
<div class="divider"></div>
		<div class="">
			<div class="example-code clearfix">
				<input type="hidden" id="id" value='${equipment.id }'>
				<form action="" class="col-md-20 center-margin" method="get">
					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 设备名: </label>
						</div>
						<div class="form-input col-md-5">
							<input value='${equipment.name}' id="name" type="text">
						</div>
						<div class="form-input col-md-1">
							*
						</div>
						<div class="form-input col-md-2">
							带*为必填项
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 编号: </label>
						</div>
						<div class="form-input col-md-5">
							<input value='${equipment.number}' id="number" type="text">
						</div>
						<div class="form-input col-md-1">
							*
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 所属类型: </label>
						</div>
						<div class="form-input col-md-5">
							<select id='typeId'>
								<option value='1'>类型一</option>
								<option value='2'>类型二</option>
							</select>
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 寿命: </label>
						</div>
						<div class="form-input col-md-5">
							<input value='${equipment.lifetime}' id="lifetime" type="text">
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 阈值上限: </label>
						</div>
						<div class="form-input col-md-5">
							<input value='${equipment.max}' id="max" type="text">
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 阈值下限: </label>
						</div>
						<div class="form-input col-md-5">
							<input value='${equipment.min}' id="min" type="text">
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 采集频率: </label>
						</div>
						<div class="form-input col-md-5">
							<input value='${equipment.samplingFrequency}' id="samplingFrequency" type="text">
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 安装日期: </label>
						</div>
						<input id="startDate" class="Wdate form-control" value='${startdate }'
							   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,onpicked:function() {javascript:changeTime();}})" style='height:30px;width: 165px'/>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 所属监测站: </label>
						</div>
						<div class="form-input col-md-5">
							<select id='stationId'>
								<option value='1'>一</option>
								<option value='2'>二</option>
							</select>
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
					</div>

					<div class="form-label col-md-2">
					<input class="btn medium primary-bg" style="width:80px" value="提交" type="button" onclick="update();"/>
					</div>
					<div class="form-label col-md-2">
					<input class="btn medium primary-bg" style="width:80px" value="返回" type="button" onclick="window.location.href='${basepath}/equipment/tolist'"/>
					</div>

					</div>
				</form>

			</div>

	</div>
	</div>
</body>
</html>
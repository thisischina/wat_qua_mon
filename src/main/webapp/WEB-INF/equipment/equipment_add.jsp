<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("basepath", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<jsp:include page="${basepath}/main/css.jsp"></jsp:include>
<title>新增</title>

<jsp:include page="${basepath}/main/js.jsp"></jsp:include>

<script type="text/javascript">
	jQuery(document).ready(function(){
		changeTitle();
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

		//判断用户是否已存在
		$.ajax({
			url:"${basepath}/equipment/confirmexist",
			type:"post",
			data:{name:name},
			dataType:"json",
			async:false,
			success: function (data) {
				if(data.total>0){
				    alert("设备名已存在");
				    return;
				}else{

				    //添加用户
                    $.ajax({
                        url:"${basepath}/equipment/addequipment",
                        type:"post",
                        data:{name:name,number:number,typeId:typeId,lifetime:lifetime,
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
			}
		}); 
	}

</script>

</head>
<body>

<div id="page-content">
	<h3>添加设备</h3>
	<div class="divider"></div>

		<div class="">
			<div class="example-code clearfix">

				<form action="" class="col-md-20 center-margin" method="get">
					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 设备名: </label>
						</div>
						<div class="form-input col-md-5">
						 <input id="name" type="text">
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
						 <input id="number" type="text">
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
							<input id="lifetime" type="text">
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 阈值上限: </label>
						</div>
						<div class="form-input col-md-5">
							<input id="max" type="text">
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 阈值下限: </label>
						</div>
						<div class="form-input col-md-5">
							<input id="min" type="text">
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 采集频率: </label>
						</div>
						<div class="form-input col-md-5">
							<input id="samplingFrequency" type="text">
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
					<input class="btn medium primary-bg"  style="width:80px" value="提交" type="button" onclick="saveObject();"/>
					</div>

					<div class="form-label col-md-2">
					<input class="btn medium primary-bg" style="width:80px" value="返回" type="button" onclick="returnPage();"/>
					</div>

					</div>
				</form>

			</div>

	</div>
	</div>
</body>
</html>
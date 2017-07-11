<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("basepath", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>新增</title>

<!-- Favicons -->

<link rel="apple-touch-icon-precomposed" sizes="144x144"
	  href="${basepath }/assets/images/icons/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	  href="${basepath }/assets/images/icons/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	  href="${basepath }/assets/images/icons/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	  href="${basepath }/assets/images/icons/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon"
	  href="${basepath }/assets/images/icons/favicon.png">

<!--[if lt IE 9]>
<script src="assets/js/minified/core/html5shiv.min.js"></script>
<script src="assets/js/minified/core/respond.min.js"></script>
<![endif]-->

<!-- AgileUI CSS Core -->

<link rel="stylesheet" type="text/css"
	  href="${basepath }/assets/css/minified/aui-production.min.css">

<!-- Theme UI -->

<link id="layout-theme" rel="stylesheet" type="text/css"
	  href="${basepath }/assets/themes/minified/agileui/color-schemes/layouts/default.min.css">
<link id="elements-theme" rel="stylesheet" type="text/css"
	  href="${basepath }/assets/themes/minified/agileui/color-schemes/elements/default.min.css">

<!-- AgileUI Responsive -->

<link rel="stylesheet" type="text/css"
	  href="${basepath }/assets/themes/minified/agileui/responsive.min.css">

<!-- AgileUI Animations -->

<link rel="stylesheet" type="text/css"
	  href="${basepath }/assets/themes/minified/agileui/animations.min.css">

<!-- AgileUI JS -->

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
                        url:"${basepath}/equipment/addstation",
                        type:"post",
                        data:{name:name,number:number,typeId:typeId,lifetime:lifetime,
                            max:max,min:min,samplingFrequency:samplingFrequency,installTime:installTime
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
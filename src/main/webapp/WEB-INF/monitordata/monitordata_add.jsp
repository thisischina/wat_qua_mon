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
		var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:'>检测信息</a></li>";
		htmlss = htmlss + "<li>添加检测数据</li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function changeTitle2(){
		$('#ultt', parent.document).html("");
		var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:'>检测信息</a></li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function returnPage(){
		changeTitle2();
        window.location.href='${basepath}/monitordata/tolist';
	}
	
	function saveObject(){
		var equipmentId=$('#equipmentId').val();
		var number=$('#number').val();
        var data=$('#data').val();
        var monitorTime=$('#monitorTime').val();
        var recordTime=$('#recordTime').val();

        if(number==""){
			alert("编号不能为空。");
			return
		}

        //添加
        $.ajax({
            url:"${basepath}/monitordata/addrole",
            type:"post",
            data:{equipmentId:equipmentId,number:number,data:data,
				monitorTime:monitorTime,recordTime:recordTime},
            dataType:"json",
            async:false,
            success: function (data) {
                if(data>0){
                    alert("添加成功");
                    changeTitle2();
                    window.location.href='${basepath}/monitordata/tolist';
                }
            }
        });
	}

</script>

</head>
<body>

<div id="page-content">
	<h3>添加</h3>
	<div class="divider"></div>

		<div class="">
			<div class="example-code clearfix">

				<form action="" class="col-md-20 center-margin" method="get">
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
						<div class="form-input col-md-2">
						带*为必填项
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 设备: </label>
						</div>
						<div class="form-input col-md-5">
							<select id='equipment_id'>
								<option value='1'>1</option>
								<option value='2'>2</option>
							</select>
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 监测值: </label>
						</div>
						<div class="form-input col-md-5">
							<input id="data" type="text">
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 监测时间: </label>
						</div>
						<div class="form-input col-md-5">
							<input id="monitor_time" type="text">
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 记录时间: </label>
						</div>
						<div class="form-input col-md-5">
							<input id="record_time" type="text">
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
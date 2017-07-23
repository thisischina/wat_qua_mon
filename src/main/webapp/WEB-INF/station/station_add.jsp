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

		//判断用户是否已存在
		$.ajax({
			url:"${basepath}/station/confirmexist",
			type:"post",
			data:{name:name},
			dataType:"json",
			async:false,
			success: function (data) {
				if(data.total>0){
				    alert("站点已存在");
				    return;
				}else{

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
			}
		}); 
	}

</script>

</head>
<body>

<div id="page-content">
	<h3>添加检测站点</h3>
	<div class="divider"></div>

		<div class="">
			<div class="example-code clearfix">

				<form action="" class="col-md-20 center-margin" method="get">
					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 监测站名: </label>
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
							<label> 位置名称: </label>
						</div>
						<div class="form-input col-md-5">
						 <input id="address" type="text">
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
							<select id='type'>
								<option value='1'>类型一</option>
								<option value='2'>类型二</option>
							</select>
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 坐标: </label>
						</div>
						<div class="form-input col-md-5">
							<input id="coordinate" type="text">
						</div>
					</div>

					<div class="form-row">
						<div class="form-label col-md-2">
							<label> 所属单位: </label>
						</div>
						<div class="form-input col-md-5">
							<select id='unitId'>
								<option value='1'>单位一</option>
								<option value='2'>单位二</option>
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
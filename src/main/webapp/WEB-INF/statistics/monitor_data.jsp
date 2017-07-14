<!-- AUI Framework -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	pageContext.setAttribute("basepath", request.getContextPath());
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>统计报表</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet" type="text/css"
	href="${basepath }/assets/css/cloud-admin.css">
<link rel="stylesheet" type="text/css"
	href="${basepath }/assets/css/default.css" id="skin-switcher">
<%-- <link rel="stylesheet" type="text/css"
	href="${basepath }/assets/css/minified/aui-production.min.css"> --%>
<!-- Theme UI -->
<link id="layout-theme" rel="stylesheet" type="text/css"
	href="${basepath }/assets/themes/minified/agileui/color-schemes/layouts/default.min.css">
<link id="elements-theme" rel="stylesheet" type="text/css"
	href="${basepath }/assets/themes/minified/agileui/color-schemes/elements/default.min.css">
<!-- AgileUI Responsive -->
<link rel="stylesheet" type="text/css"
	href="${basepath }/assets/themes/minified/agileui/responsive.min.css">
<!-- STYLESHEETS -->
<!--[if lt IE 9]><script src="js/flot/excanvas.min.js"></script><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script><![endif]-->
<link href="${basepath }/assets/css/font-awesome.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${basepath }/assets/css/styles.css" />
<%-- <link rel="stylesheet" href="${basepath }/style/css/bootstrap.css" /> --%>

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

th{valign: middle;}
td {
	text-align: center;
	
}
</style>

<script src="${basepath }/assets/js/jquery-2.0.3.min.js"></script>
<script type="text/javascript"
	src="${basepath }/style/custom/js/jquery.twbsPagination.min.js"></script>
	<script type="text/javascript"
	src="${basepath }/style/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
	jQuery(document).ready(function() {
		   loadDataGird();
	});
	

	
	Date.prototype.Format = function(fmt) 
	{ //author: meizz 
	  var o = { 
	    "M+" : this.getMonth()+1,                 //月份 
	    "d+" : this.getDate(),                    //日 
	    "h+" : this.getHours(),                   //小时 
	    "m+" : this.getMinutes(),                 //分 
	    "s+" : this.getSeconds(),                 //秒 
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度 
	    "S"  : this.getMilliseconds()             //毫秒 
	  }; 
	  if(/(y+)/.test(fmt)) 
	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	  for(var k in o) 
	    if(new RegExp("("+ k +")").test(fmt)) 
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
	  return fmt; 
	}
	
	function formartDate(p){
		var d = new Date();
		d.setTime(p);
		return d.Format("yyyy-MM-dd hh:mm:ss");
	}
	
	var createTable = function(index, item) {
		var str = "<tr>"
				+"<td>"+ (index + 1)+ "</td>"
				+"<td>"+ item.nodeId+ "</td>"
				+"<td id='"+(index+1)+"tp1' >"+ item.tp1+ "</td>"
				+"<td id='"+(index+1)+"tp2' >"+ item.tp2+ "</td>"
				+"<td id='"+(index+1)+"tp3' >"+ item.tp3+ "</td>"
				+"<td id='"+(index+1)+"tp4' >"+ item.tp4+ "</td>"
				+"<td id='"+(index+1)+"tp5' >"+ item.tp5+ "</td>"
				+"<td id='"+(index+1)+"tp6' >"+ item.tp6+ "</td>"
				+"<td id='"+(index+1)+"tp7' >"+ item.tp7+ "</td>"
				+"<td id='"+(index+1)+"tp8' >"+ item.tp8+ "</td>"
				+"<td id='"+(index+1)+"tp9' >"+ item.tp9+ "</td>"
				+"<td id='"+(index+1)+"va' >"+ item.va+ "</td>"
				+"<td id='"+(index+1)+"vb' >"+ item.vb+ "</td>"
				+"<td id='"+(index+1)+"vc' >"+ item.vc+ "</td>"
				+"<td id='"+(index+1)+"ia' >"+ item.ia+ "</td>"
				+"<td id='"+(index+1)+"ib' >"+ item.ib+ "</td>"
				+"<td id='"+(index+1)+"ic' >"+ item.ic+ "</td>"
				+"<td>"+formartDate(item.acTime)+ "</td>"+
				"</tr>	";
		
		//alert(item.userid)
		$("#tbody").append(str);
		var warn = item.warn;
		var arr = new Array();
		arr = warn.split(",");
		for(var i=0;i<arr.length;i++){
			if(arr[i]!=''){
				var tp1 = document.getElementById((index+1)+arr[i]);
				tp1.style.color="red";
			}
		}
		
	}
	
	var loadDataGird = function() {
		$.ajax({
					url : "${basepath }/monitordata/tolist?pageNow=0",
					type : "post",
					data : {

					},
					datatype : "json",
					success : function(data) {
                        debugger
						$("#tbody").html("");
						$('#pagination_div').html("");

						if (data == null || data.total == 0) {
							return;
						}
						$.each(data.list, function(index, item) { //遍历返回的json
							createTable(index, item);
						});
						//分页插件
						$('#pagination_div')
								.html(
										"<ul id='pagination' class='pagination-sm' style='float:right'></ul>");
						$('#pagination')
								.twbsPagination(
										{
											totalPages : data.pageCount,
											visiblePages : 5,
											first : '首页',
											prev : '上一页',
											next : '下一页',
											last : '末页',
											onPageClick : function(event, page) {
												$
														.ajax({
															url : "${basepath }/warn/findList.action",   
															type : "post",
															data : {
																"gatewayId" : "${gatewayId}",
																"nodeId" : "${nodeAddr}",
																"pageNow":page
															},
															datatype : "json",
															success : function(data) {
																$("#tbody").html("");
																
																if (data == null|| data.total == 0) {
																	return;
																}
																$.each(data.list,function(index,
																						item) {
																					createTable(
																							index,
																							item);
																				});
															}
														});
											}
										});
						//分页插件end

					}
				});
	}

</script>
</head>
<body>


<div class="row" style="margin-left: 0px;margin-right: 0px">
	<div class="col-md-12">
		<form class="form-inline" role="form" style="float: right;margin-bottom:10px;margin-top: 5px;margin-left: 5px ">

			<div class="form-group">
				<strong>网关：</strong>
			</div>
			<div class="form-group">
				<select onchange="loadFind()" id="gatewayId" class="form-control" style="height:30px;width: 140px">

					<option value="1,9001">网关一</option>

					<option value="2,9002">网关二</option>

					<option value="4,9003">网关3</option>

				</select>
			</div>

			<div class="form-group">
				<strong>群组：</strong>
			</div>
			<div class="form-group">
				<select onchange="loadFindNode()" id="groupSelect" class="form-control" style="height:30px;width: 140px"><option value="1">群组一</option><option value="2">群组二</option></select>
			</div>

			<div class="form-group">
				<strong>节点：</strong>
			</div>
			<div class="form-group">
				<select onchange="loadDataGird()" id="nodeSelect" class="form-control" style="height:30px;width: 140px"><option value="1">节点1</option><option value="2">节点3</option><option value="3">节点4</option><option value="5">??5</option><option value="9">??6</option><option value="7">??7</option></select>
			</div>
			<div class="form-group">
				<strong>时间：</strong>
			</div>
			<div class="form-group">
				<input id="startDate" class="Wdate form-control" value="2017-07-14 10:32:29" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,onpicked:function() {javascript:changeTime();}})" style="height:30px;width: 165px">
			</div>
			<div class="form-group">
				--
			</div>
			<!-- onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" -->
			<div class="form-group">
				<label class="sr-only" for="exampleInputPassword2"></label>
				<input id="endDate" class="Wdate form-control" value="2017-07-14 11:32:29" readonly="readonly" style="height:30px;width: 165px">
			</div>

			<button type="button" class="btn btn-info" style="background: #4F81BD;border: 1px solod #4F81BD" onclick="loadDataGird()">查询</button>
		</form>
	</div>
</div>
<div class="row" style="margin-left: 0px;margin-right: 0px">
	<div class="col-md-12">
		<table class="table table-bordered">
			<thead style="background:#A8BC7B;color:#fff">
			<tr>
				<th rowspan="2" style="vertical-align: middle;background:RGB(79,129,189);color:#fff" class="text-center">序号</th>
				<th rowspan="2" style="vertical-align: middle;background:RGB(79,129,189);color:#fff" class="text-center">节点编号</th>
				<th colspan="9" class="text-center" style="background:RGB(79,129,189);color:#fff">温度（℃）</th>
				<th colspan="3" class="text-center" style="background:RGB(79,129,189);color:#fff">电压（V）</th>
				<th colspan="3" class="text-center" style="background:RGB(79,129,189);color:#fff">电流（A）</th>
				<th rowspan="2" style="vertical-align: middle;background:RGB(79,129,189);color:#fff" class="text-center">时间</th>
			</tr>
			<tr>
				<th class="text-center" style="background:RGB(79,129,189);color:#fff">左A</th>
				<th class="text-center" style="background:RGB(79,129,189);color:#fff">左B</th>
				<th class="text-center" style="background:RGB(79,129,189);color:#fff">左C</th>
				<th class="text-center" style="background:RGB(79,129,189);color:#fff">左N</th>
				<th class="text-center" style="background:RGB(79,129,189);color:#fff">右A</th>
				<th class="text-center" style="background:RGB(79,129,189);color:#fff">右B</th>
				<th class="text-center" style="background:RGB(79,129,189);color:#fff">右C</th>
				<th class="text-center" style="background:RGB(79,129,189);color:#fff">右N</th>
				<th class="text-center" style="background:RGB(79,129,189);color:#fff">环境</th>
				<th class="text-center" style="background:RGB(79,129,189);color:#fff">A相</th>
				<th class="text-center" style="background:RGB(79,129,189);color:#fff">B相</th>
				<th class="text-center" style="background:RGB(79,129,189);color:#fff">C相</th>
				<th class="text-center" style="background:RGB(79,129,189);color:#fff">A相</th>
				<th class="text-center" style="background:RGB(79,129,189);color:#fff">B相</th>
				<th class="text-center" style="background:RGB(79,129,189);color:#fff">C相</th>
			</tr>
			</thead>
			<tbody id="tbody"> </tbody>
		</table>
		<div id="pagination_div" style="float: right;padding-right: 0px">

		</div>
	</div>
</div>



</body>


</html>
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
<jsp:include page="${basepath}/main/css.jsp"></jsp:include>
<title>统计报表</title>

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

<jsp:include page="${basepath}/main/js.jsp"></jsp:include>

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
				+"<td>"+ item.id+ "</td>"
				+"<td id='"+(index+1)+"tp1' >"+ item.number+ "</td>"
				+"<td id='"+(index+1)+"tp2' >"+ item.data+ "</td>"
				+"<td id='"+(index+1)+"tp3' >"+ item.number+ "</td>"
				+"<td id='"+(index+1)+"tp4' >"+ item.number+ "</td>"
				+"<td id='"+(index+1)+"tp5' >"+ item.number+ "</td>"
				+"<td id='"+(index+1)+"tp6' >"+ item.number+ "</td>"
				+"<td id='"+(index+1)+"tp7' >"+ item.number+ "</td>"
				+"<td id='"+(index+1)+"tp8' >"+ item.number+ "</td>"
				+"<td>"+formartDate(item.acTime)+ "</td>"+
				"</tr>	";

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
					url : "${basepath }/statistics/getmonitordata?pageNow=1",
					type : "post",
					data : {

					},
					datatype : "json",
					success : function(data) {

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
				<strong>设备：</strong>
			</div>
			<div class="form-group">
				<select onchange="loadFind()" id="gatewayId" class="form-control" style="height:30px;width: 140px">

					<option value="1,9001">设备一</option>

					<option value="2,9002">设备二</option>

					<option value="4,9003">设备三</option>

				</select>
			</div>

			<div class="form-group">
				<strong>检测时间：</strong>
			</div>
			<div class="form-group">
				<input id="startDate" class="Wdate form-control" value="2017-07-14 10:32:29" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,onpicked:function() {javascript:changeTime();}})" style="height:30px;width: 165px">
			</div>
			<div class="form-group">
				--
			</div>
			<!-- onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" -->
			<div class="form-group">
				<label class="sr-only"></label>
				<input id="endDate" class="Wdate form-control" value="2017-07-14 11:32:29" readonly="readonly" style="height:30px;width: 165px">
			</div>

			<button type="button" class="btn btn-info" style="background: #4F81BD;border: 1px solid #4F81BD" onclick="loadDataGird()">查询</button>
		</form>
	</div>
</div>
<div class="row" style="margin-left: 0px;margin-right: 0px">
	<div class="col-md-12">
		<table class="table table-bordered">
			<thead style="background:#A8BC7B;color:#fff">
			<tr>
				<th rowspan="2" style="vertical-align: middle;background:RGB(79,129,189);color:#fff" class="text-center">序号</th>
				<th rowspan="2" style="vertical-align: middle;background:RGB(79,129,189);color:#fff" class="text-center">编号代码</th>

				<th colspan="2" class="text-center" style="background:RGB(79,129,189);color:#fff">水温(℃)</th>
				<th rowspan="2" style="vertical-align: middle;background:RGB(79,129,189);color:#fff" class="text-center">PH</th>
				<th rowspan="2" style="vertical-align: middle;background:RGB(79,129,189);color:#fff" class="text-center">溶解氧(O2)-mg/L</th>
				<th rowspan="2" style="vertical-align: middle;background:RGB(79,129,189);color:#fff" class="text-center">电导(S)-μS/cm</th>
				<th colspan="2" class="text-center" style="background:RGB(79,129,189);color:#fff">氨氮(A)-mg/L</th>
				<th rowspan="2" style="vertical-align: middle;background:RGB(79,129,189);color:#fff" class="text-center">检测方式</th>
				<th rowspan="2" style="vertical-align: middle;background:RGB(79,129,189);color:#fff" class="text-center">时间</th>
			</tr>
			<tr>
				<th class="text-center" style="background:RGB(79,129,189);color:#fff">色度</th>
				<th class="text-center" style="background:RGB(79,129,189);color:#fff">浓度</th>

				<th class="text-center" style="background:RGB(79,129,189);color:#fff">游离氨（NH3）</th>
				<th class="text-center" style="background:RGB(79,129,189);color:#fff">铵离子（NH4+）</th>
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
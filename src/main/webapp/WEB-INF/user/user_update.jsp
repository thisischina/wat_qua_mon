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
	});

	function changeTitle(){
		var htmlss = $('#ultt', parent.document).html();
		htmlss = htmlss + "<li>修改用户信息</li>"; 
		$('#ultt', parent.document).html(htmlss);
	}
	
	function changeTitle2(){
		$('#ultt', parent.document).html("");
		var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:projectInfo();'>用户信息</a></li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function update(){
		var id=$('#id').val();
		var name=$('#name').val();
		var tel=$('#user_tel').val();
		var email=$('#email').val();
		var unitId=$('#unitId').val();
		var roleId=$('#roleId').val();
        var state=$('#state').val();

		   $.ajax({
			url:"${basepath }/user/update",
			type:"post",
			data:{id:id,name:name,tel:tel,email:email,unitId:unitId,roleId:roleId,state:state},
			dataType:"json",
			success: function (data) {
				if(data==1){
					alert("修改成功");
					changeTitle2();
					window.location.href="${basepath }/user/tolist";
				}else{
					alert("添加失败");
				}
			}
		});
		
	}

</script>

<%--//	递归所有部门--%>
<SCRIPT type="text/javascript">
	var setting = {
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
//                beforeClick: beforeClick,
			onClick: onClick
		}
	};


	var zNodes =[
		<c:forEach var="unit" items="${unitlist}">
		{id:${unit.id}, pId:${unit.superior}, name:'${unit.name}'},
		</c:forEach>
	];

	//        function beforeClick(treeId, treeNode) {
	//            var check = (treeNode && !treeNode.isParent);
	//            if (!check) alert("只能选择子部门...");
	//            return check;
	//        }

	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			v = "";
		superiorid="";
		nodes.sort(function compare(a,b){return a.id-b.id;});
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
			superiorid=nodes[i].id;
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		var cityObj = $("#superior");
		cityObj.attr("value", v);
		$("#superiorid").val(superiorid);
	}

	function showMenu() {
		var cityObj = $("#superior");
		var cityOffset = $("#superior").offset();
		$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}

	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
	//-->
</SCRIPT>

</head>
<body style='font-family:"Microsoft Yahei"'>

<div class="portlet box">

	<div class="portlet-body form">
		<input type="hidden" id="id" value='${user.id }'>
		<form>
			<h3 class="form-section">修改用户</h3>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">姓名</label>

						<div class="controls">

							<input type="text" id="name" value='${user.name }' class="m-wrap span12">

						</div>

					</div>

				</div>

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">手机</label>

						<div class="controls">

							<input type="text" id="user_tel" placeholder='${user.tel }' class="m-wrap span12">

						</div>

					</div>

				</div>

			</div>


			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">岗位</label>

						<div class="controls">

							<input class="span12 m-wrap" id="userPost" value='${user.userPost}'/>

						</div>

					</div>

				</div>

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label" >所属部门</label>

						<div class="controls">

							<input type="text" id="superior" placeholder="${unitService.selectById(user.unitId).name}" onclick="showMenu();" readonly class="m-wrap span12">

							<input type="hidden" id="superiorid"  readonly class="m-wrap span12">

						</div>

					</div>

				</div>

			</div>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label" >职务</label>

						<div class="controls">

							<select id="postId"  class="m-wrap span12">

								<option value="1">职务一</option>

								<option value="2">职务二</option>

							</select>

						</div>

					</div>

				</div>

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label" >角色</label>

						<div class="controls">

							<select id="roleId" class="m-wrap span12">

								<c:forEach items="${roleList}" var="role">
									<c:if test="${user.roleId==role.roleId}">
										<option value="${role.roleId}" selected>${role.name}</option>
									</c:if>
									<c:if test="${user.roleId!=role.roleId}">
										<option value="${role.roleId}">${role.name}</option>
									</c:if>
								</c:forEach>

							</select>

						</div>

					</div>

				</div>

			</div>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label" >状态</label>

						<div class="controls">

							<select id="state"  class="m-wrap span12">

								<option value="1">启用</option>

								<option value="0">冻结</option>

							</select>

						</div>

					</div>

				</div>

			</div>

			<div class="row-fluid">

				<div class="span12 ">

					<div class="control-group">

						<label class="control-label">备注</label>

						<div class="controls">

							<textarea class="span12 m-wrap" id="remarks" rows="3">${user.remarks}</textarea>

						</div>

					</div>

				</div>

			</div>

			<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
				<ul id="treeDemo" class="ztree" style="margin-top:0; width:180px; height: 300px;"></ul>
			</div>

			<div class="form-actions" style="padding-left: 10px">

				<button type="button" class="btn blue" onclick="update();">确定</button>

				<button type="button" class="btn" onclick="window.location.href='${basepath}/user/tolist'">取消</button>

			</div>

		</form>

	</div>

</div>

</body>

</html>
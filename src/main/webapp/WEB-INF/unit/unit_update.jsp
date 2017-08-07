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

	<jsp:include page="${basepath}/main/updatepage_js.jsp"></jsp:include>

	<script type="text/javascript">
        jQuery(document).ready(function(){
            changeTitle();

            ifuserexis();

        });

	function changeTitle(){
		var htmlss = $('#ultt', parent.document).html();
		htmlss = htmlss + "<li>修改单位信息</li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function changeTitle2(){
		$('#ultt', parent.document).html("");
		var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:projectInfo();'>单位信息</a></li>";
		$('#ultt', parent.document).html(htmlss);
	}

    function ifuserexis() {
        var name="";

        $("#name").mousedown(function(){
            $("#namelabel").css("display","none");

            $("#name").mouseleave(function(){
                name=$('#name').val();
                if(name==""){
                    $("#namelabel").html("单位名不能为空");
                    $("#namelabel").css("display","block");
                }else {
                    $("#namelabel").css("display","none");

                    //判断是否已存在
                    $.ajax({
                        url:"${basepath}/unit/confirmexist",
                        type:"post",
                        data:{name:name},
                        dataType:"json",
                        async:false,
                        success: function (data) {
                            if(data.total>0){
                                $("#namelabel").html("单位名未修改");
                                $("#namelabel").css("display","block");
                            }
                        }
                    });
                }

            });
        });

    }
	
	function update(){
        var  id=$('#id').val();
        var name=$('#name').val();
        var superior=$('#superiorid').val();
        var tel=$('#fixed_tel').val();
        var address=$('#address').val();
        var remarks=$('#remarks').val();

        if(name==""){
            alert("部门名不能为空。");
            return;
        }

        if(superior==""){
            alert("未选择上级部门");
            return;
        }

	   $.ajax({
		url:"${basepath }/unit/update",
		type:"post",
		data:{id:id,name:name,superior:superior,tel:tel,address:address,remarks:remarks},
		dataType:"json",
		success: function (data) {
			if(data==1){
				alert("修改成功");
				changeTitle2();
				window.location.href="${basepath }/unit/tolist";
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

        var	zNodes=[
            <c:forEach var="unit" items="${unitlist}">
            {id:${unit.id}, pId:${unit.superior}, name:'${unit.name}',iconSkin:"2.png"},
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

		<form action="#" class="horizontal-form">
			<input type="hidden" id="id" value='${unit.id}'>
			<h3 class="form-section">更新部门&ensp;/带*为必填项</h3>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group error">

						<label class="control-label">单位名<span class="required">*</span></label>

						<div class="controls">

							<input type="text" id="name" value="${unit.name}" class="m-wrap span12">

							<span class="help-block" id="namelabel" style="color:orangered;display: none"></span>

						</div>

					</div>

				</div>

				<div class="span6 ">

					<div class="control-group error">

						<label class="control-label">上级部门<span class="required">*</span></label>

						<div class="controls">

							<input type="text" id="superior" onclick="showMenu();" readonly class="m-wrap span12">

							<input type="hidden" id="superiorid"  readonly class="m-wrap span12">

						</div>

					</div>

				</div>

			</div>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">固定电话</label>

						<div class="controls">

							<input type="text" id="fixed_tel" value="${unit.tel}" class="m-wrap span12">

						</div>

					</div>

				</div>

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">地址</label>

						<div class="controls">

							<input type="text" id="address" class="m-wrap span12">

						</div>

					</div>

				</div>

			</div>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">备注</label>

						<div class="controls">

							<textarea class="span12 m-wrap" id="remarks" rows="3">${unit.remarks}</textarea>

						</div>

					</div>

				</div>

			</div>

			<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
				<ul id="treeDemo" class="ztree" style="margin-top:0; width:180px; height: 300px;"></ul>
			</div>

			<div class="form-actions" style="padding-left: 10px">

				<button type="button" class="btn blue" onclick="update()">确定</button>

				<button type="button" class="btn" onclick="window.location.href='${basepath}/unit/tolist'">取消</button>

			</div>

		</form>

	</div>

</div>

</body>
</html>
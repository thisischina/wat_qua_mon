<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("basepath", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<jsp:include page="${basepath}/main/updatepage_css.jsp"></jsp:include>
<title>新增</title>

<jsp:include page="${basepath}/main/updatepage_js.jsp"></jsp:include>

<script type="text/javascript">
	jQuery(document).ready(function(){
		changeTitle();

		ifuserexis();

	});
	
	function changeTitle(){
		$('#ultt', parent.document).html("");
		var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:'>部门信息</a></li>";
		htmlss = htmlss + "<li>添加部门</li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function changeTitle2(){
		$('#ultt', parent.document).html("");
		var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:'>部门信息</a></li>";
		$('#ultt', parent.document).html(htmlss);
	}
	
	function returnPage(){
		changeTitle2();
        window.location.href='${basepath}/unit/tolist';
	}

    var exis=0;
    function ifuserexis() {
        var name="";

        $("#name").mousedown(function(){
            $("#namelabel").css("display","none");

            $("#name").mouseleave(function(){
                name=$('#name').val();
                if(name==""){
                    $("#namelabel").html("部门名不能为空");
                    $("#namelabel").css("display","block");
                }else {
                    exis=0;
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
                                exis=1;
                                $("#namelabel").html("部门名已存在");
                                $("#namelabel").css("display","block");
                            }
                        }
                    });
                }

            });
        });

    }

	function saveObject(){
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

        if(exis==1){
            alert("部门已存在,无法创建");
            return;
        }

        //添加
        $.ajax({
            url:"${basepath}/unit/addunit",
            type:"post",
            data:{name:name,superior:superior,tel:tel,address:address,remarks:remarks},
            dataType:"json",
            async:false,
            success: function (data) {
                if(data>0){
                    alert("添加成功");
                    changeTitle2();
                    window.location.href='${basepath}/unit/tolist';
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


<div class="portlet box">

	<div class="portlet-body form">

		<form action="#" class="horizontal-form">

			<h3 class="form-section">添加部门&ensp;/带*为必填项</h3>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group error">

						<label class="control-label">部门名<span class="required">*</span></label>

						<div class="controls">

							<input type="text" id="name" class="m-wrap span12">

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

							<input type="text" id="fixed_tel" class="m-wrap span12">

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

							<textarea class="span12 m-wrap" id="remarks" rows="3"></textarea>

						</div>

					</div>

				</div>

			</div>

			<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
				<ul id="treeDemo" class="ztree" style="margin-top:0; width:180px; height: 300px;"></ul>
			</div>

			<div class="form-actions" style="padding-left: 10px">

				<button type="button" class="btn blue" onclick="saveObject()">确定</button>

				<button type="button" class="btn" onclick="returnPage();">取消</button>

			</div>

		</form>

	</div>

</div>

</body>
</html>
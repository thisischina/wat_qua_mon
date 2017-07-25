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

            App.init();

            FormComponents.init();
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

    var exis=0
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
                                $("#namelabel").html("旧单位名");
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
		var  name=$('#name').val();

		if(name==""){
			alert("单位名不能为空");
			return
		}
        if(exis==1){
            alert("单位名相同,无需更新");
            return;
        }
	   $.ajax({
		url:"${basepath }/unit/update",
		type:"post",
		data:{id:id,name:name},
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

</head>

<body style='font-family:"Microsoft Yahei"'>

<div class="portlet box">

	<div class="portlet-body form">

		<form action="#" class="horizontal-form">
			<input type="hidden" id="id" value='${unit.unitId}'>
			<h3 class="form-section">更新单位</h3>

			<div class="row-fluid">

				<div class="span6 ">

					<div class="control-group">

						<label class="control-label">单位名</label>

						<div class="controls">

							<input type="text" id="name" value="${unit.name}" class="m-wrap span12">

							<span class="help-block" id="namelabel" style="color:orangered;display: none"></span>

						</div>

					</div>

				</div>

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
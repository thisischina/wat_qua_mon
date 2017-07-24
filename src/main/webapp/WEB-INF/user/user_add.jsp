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

<!-- AgileUI JS -->

<jsp:include page="${basepath}/main/updatepage_js.jsp"></jsp:include>

<script type="text/javascript">
	jQuery(document).ready(function(){
        changeTitle();

        ifuserexis();

        App.init();

        FormComponents.init();
	});

    function changeTitle(){
        $('#ultt', parent.document).html("");
        var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:UserInfo();'>用户信息</a></li>";
        htmlss = htmlss + "<li>添加用户</li>";
        $('#ultt', parent.document).html(htmlss);
    }

    function changeTitle2(){
        $('#ultt', parent.document).html("");
        var htmlss = "<li id='title1'><i class='fa fa-home'></i>系统管理</li><li id='title2'><a href='javascript:UserInfo();'>用户信息</a></li>";
        $('#ultt', parent.document).html(htmlss);
    }

    function returnUser(){
        changeTitle2();
        window.location.href='${basepath}/user/tolist';
    }


    var exis=0
    function ifuserexis() {
        var account="";

        $("#account").mousedown(function(){debugger
            $("#namelabel").css("display","none");

            $("#account").mouseleave(function(){
                account=$('#account').val();
                if(account==""){
                    $("#namelabel").html("用户名不能为空");
                    $("#namelabel").css("display","block");
                }else {
                    exis=0;
                    $("#namelabel").css("display","none");
                    //判断用户是否已存在
                    $.ajax({
                        url:"${basepath}/user/confirmexist",
                        type:"post",
                        data:{account:account},
                        dataType:"json",
                        async:false,
                        success: function (data) {
                            if (data.total > 0) {
                                exis=1;
                                $("#namelabel").html("用户名已存在");
                                $("#namelabel").css("display","block");
                            }
                        }
                    });
                }

            });
        });

    }

    function saveObject(){
        var account=$('#account').val();
        var password=$('#password').val();
        var name=$('#name').val();
        var tel=$('#user_tel').val();
        var email=$('#email').val();
        var unitId=$('#unitId').val();
        var roleId=$('#roleId').val();
        var power=$('#power').val();

        if(account==""||password==""){
            alert("用户名或密码不能为空。");
            return
        }
        var first = account.substring(0,1);
        if (!(first >= 'a' && first <= 'z') || (first >= 'A' && first <= 'Z'))
        {
            alert("用户名必须以小写字母开头");
            return;
        }

        if(exis==1){
            alert("用户名已存在,无法创建");
            return;
        }
        //添加用户
        $.ajax({
            url:"${basepath}/user/addobject",
            type:"post",
            data:{account:account,password:password,name:name,tel:tel,
                email:email,unitId:unitId,roleId:roleId},
            dataType:"json",
            async:false,
            success: function (data) {
                if(data>0){
                    alert("添加成功");
                    changeTitle2();
                    window.location.href='${basepath}/user/tolist';
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

				<h3 class="form-section">添加用户</h3>

				<div class="row-fluid">

					<div class="span6 ">

						<div class="control-group">

							<label class="control-label">用户名</label>

							<div class="controls">

								<input type="text" id="account" class="m-wrap span12" placeholder="以字母开头">

								<span class="help-block" id="namelabel" style="color:orangered;display: none"></span>

							</div>

						</div>

					</div>

					<div class="span6 ">

						<div class="control-group">

							<label class="control-label">密码</label>

							<div class="controls">

								<input type="password" id="password" class="m-wrap span12">

							</div>

						</div>

					</div>

				</div>


				<div class="row-fluid">

					<div class="span6 ">

						<div class="control-group">

							<label class="control-label">姓名</label>

							<div class="controls">

								<input type="text" id="name" class="m-wrap span12">

							</div>

						</div>

					</div>

					<div class="span6 ">

						<div class="control-group">

							<label class="control-label">手机</label>

							<div class="controls">

								<input type="text" id="user_tel" class="m-wrap span12">

							</div>

						</div>

					</div>

				</div>

				<div class="row-fluid">

					<div class="span6 ">

						<div class="control-group">

							<label class="control-label">邮箱</label>

							<div class="controls">

								<input class="span12 m-wrap" id="email"/>

							</div>

						</div>

					</div>

					<div class="span6 ">

						<div class="control-group">

								<label class="control-label" >所属单位</label>

								<div class="controls">

								<select id="unitId"  class="m-wrap span12">

								<option value="1">一</option>

								<option value="2">二</option>

								</select>

								</div>

						</div>

					</div>


				</div>

				<div class="row-fluid">

					<div class="span6 ">

						<div class="control-group">

							<label class="control-label" >角色</label>

							<div class="controls">

								<select id="roleId" class="m-wrap span12">

									<option value="1">一</option>

									<option value="2">二</option>

								</select>

							</div>

						</div>

					</div>

				</div>

				<div class="form-actions" style="padding-left: 10px">

					<button type="button" class="btn blue" onclick="saveObject()">确定</button>

					<button type="button" class="btn" onclick="returnUser();">取消</button>

				</div>

			</form>

		</div>

	</div>

</body>


</html>
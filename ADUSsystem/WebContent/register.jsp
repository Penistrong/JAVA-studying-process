<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息管理系统-ADUSsystem</title>
<style type="text/css">
h1{text-align:center;}
h2{text-align:center;font-family:微软雅黑;font-size:25px;color:red;word-spacing:5px;text-shadow:2px 2px 2px black;}
body{background:url('images/RegisterPage.jpg');background-size:cover;}
a:link,a:visited{display:block;font-weight:bold;color:#23238E;text-align:center;text-decoration:none;}
a:hover,a:active{background-color:white;}
</style>

<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#form1").submit(function(){
			var name = $("#UserName").val();//获取表格中提交的账号值
			if(name.length==0){
				$("#UserNameError").html("账号不能为空！");
				return false;
			}
			
			var password = $("#PassWord").val();
			if(password.length==0){
				$("#PassWordError").html("密码不能为空！");
				return false;
			}
			
			var relpassword = $("#RelPassWord").val();
			if(relpassword.length==0){
				$("#RelPassWordError").html("确认密码不能为空！");
				return false;
			}
			
			if(password!=relpassword){
				$("#RelPassWordError").html("确认密码输入不正确，请重新输入!");
				return false;
			}
		});
	});
</script>

</head>
<body>
<form action="RegisterServlet" method="post" id="form1">
	<h1>欢迎使用学生信息管理系统</h1>
	<hr/>
	<h2>请先注册</h2>
	<table align="center" style="margin-top:75px;">
		<tr>
			<td>用户名:</td>
			<td>
				<input type="text" name="UserName" id="UserName"/>
				<div id = "UserNameError" style="display:inline;color:red;"></div>
			</td>
		</tr>
		<tr>
			<td>密码:</td>
			<td>
				<input type="password" name="PassWord" id="PassWord">
				<div id="PassWordError" style="display:inline;color:red;"></div>
			</td>
		</tr>
		<tr>
			<td>确认密码:</td>
			<td>
				<input type="password" name="RelPassWord" id="RelPassWord">
				<div id="RelPassWordError" style="display:inline;color:red;"></div>
			</td>
		</tr>
		<tr style="text-align:center;">
			<td colspan="1"></td>
			<td>
				<input type="submit" value="注册"/>
				<input type="reset" value="重置"/>
				<a href = "login.jsp">登陆</a>
			</td>
		</tr>
	</table>
</form>
</body>
</html>
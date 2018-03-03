<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息管理系统-ADUSsystem</title>
<style type="text/css">
	h1{text-align:center;color:white;}
	h2{text-align:center;font-family:微软雅黑;font-size:20px;color:red;word-spacing:5px;text-shadow:2px 2px 2px black;}
	body{background:url('images/LoginPage.jpg') no-repeat fixed center;background-size:cover;}
	a{font:italic bold,serif;text-decoration:none;}
	a:link,a:visited{display:block;font-weight:bold;color:#B3495C;text-align:center;text_decoration:none;}
	a:hover,a:active{background-color:yellow;}
	table{border:2px solid white;background-color:black;}
	table{width:300px;text-align:center;border-collapse:collapse;
		padding:2px;}
	table td{color:#00ff7f;}
</style>
</head>
<body>
<form action="LoginServlet" method = "post">
	<h1>欢迎使用学生信息管理系统</h1>
	<hr/>
	<h2>请先登陆</h2>
	<table align = "center">
		<tr>
			<td>用户名</td>
			<td><input type = "text" name = "UserName" id = "UserName"></td>
		</tr>
		<tr>
			<td>密码</td>
			<td><input type = "password" name = "PassWord" id = "PassWord"></td>
		</tr>
		<tr>
			<td colspan = "1"></td>
			<td>
				<input type = "submit" value = "登陆"/>
				<input type = "reset" value = "重置"/>
				<a href = "register.jsp">注册</a>
			</td>
		</tr>	
	</table>
</form>
</body>
</html>
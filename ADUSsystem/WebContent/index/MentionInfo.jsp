 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" import="com.ADUSsystem.TO.Info" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息管理系统-ADUSsystem</title>
<style type="text/css">
	h1{text-align:center;color:red;margin-top:250px;}
	a:link,a:visited{font-weight:bold;text-align:center;text-decoration:none;color:black;}
	a:hover,a:active{text-decoration:underline;color:#00ff7f;}
</style>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
</head>
<body>
<%Info MentionInfo = (Info)request.getAttribute("info");%>
<h1><%= MentionInfo.getMainInfo()%></h1>
<%	Integer InfoType = MentionInfo.getInfoType();
  	String url = "index/index.jsp";
  	switch(InfoType) {
  	case 1:
	  	url = "index/index.jsp";
	    break;
  	case 2:
	  	url = "login.jsp";
	  	break;
  	case 3:
  		url = "login.jsp";
  		break;
  	case 4:
  		url = "register.jsp";
		break;
  }
%>
<div style="text-align:center;"><a href="index/index.jsp" id="link">还有5s将进行自动跳转，若无反应点击这里手动跳转。。。</a></div>
<script type="text/javascript">
	var LinkType = <%=InfoType%>;
	LinkType = parseInt(LinkType);
	switch(LinkType){
	case 1:
		document.getElementById("link").href = "index/index.jsp";
		break;
	case 2:
		document.getElementById("link").href = "login.jsp";
		break;
	case 3:
		document.getElementById("link").href = "login.jsp";
		break;
	case 4:
		document.getElementById("link").href = "register.jsp";
		break;
	}
</script>
</body>
</html>
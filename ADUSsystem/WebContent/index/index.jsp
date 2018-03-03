<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.ADUSsystem.TO.StudentINFOlist"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息管理系统</title>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<link rel="stylesheet" type="text/css" href="../cssfiles/index.css"/>
<script type="text/javascript" src="../js/jQueryOfIndex.js">
</script>
</head>
<body>
	<div class="summary">
		<div style="left:30px;top:30px;width:200px;">
			<center><img alt="作者的头像" src="../images/myhead.jpg" class="head"></center>
			<center><a href="#" class="author">Penistrong</a></center>
			<div class="clicktoslide">
				<p class="introduction">华科软工1703班宅男<br>个人情况不详<br>QQ：770560618 <br>吃鸡吗？医疗兵 四级包</p>
				<center><img alt="你TM说啥" src="../images/kiddingimg.jpg" class="kiddingimg"></center>
			</div>
			<p class="flip" style="cursor:pointer;">点击展开/收起简介</p>
		</div>
	</div>
	<div id="advertisement">
		<ul>
			<li><img alt="广告位待价而沽" src="http://upload.ouliu.net/i/20171020082737gu189.jpeg" class="adver" title="广告"></li>
			<li><img alt="广告位待价而沽" src="http://upload.ouliu.net/i/201710200835097sxf8.jpeg" class="adver" title="广告"></li>
        	<li><img alt="广告位待价而沽" src="http://upload.ouliu.net/i/201710200848121eydw.jpeg" class="adver" title="广告"></li>
        	<li><img alt="广告位待价而沽" src="http://upload.ouliu.net/i/201710201250344nauz.jpeg" class="adver" title="广告"></li>
		</ul>
	</div>
	<script type="text/javascript">
		var li = document.getElementById('advertisement').getElementsByTagName("li");
		var num = 0;
		var len = li.length;
		setInterval(function(){
			li[num].style.display="none";
			num=++num==len?0:num;
			li[num].style.display="inline-block";
		},2500);
	</script>
	<div id="head"><h1>欢迎使用学生信息管理系统</h1></div>
	<div id="mainFunctions">
		<div id="ssfunction">
			<p class="searchStudent">查询学生信息</p>
			<div class="ssfunction">
			<form action="SearchStudentServlet" method="post" id="form1">
			<table align="center">
				<tr>
					<td>请输入ID</td>
					<td>
						<input type="text" placeholder="Please input ID" name="studentID" id="studentID"/>
					</td>
				</tr>
				<tr>
					<td>请输入姓名</td>
					<td>
						<input type="text" placeholder="Please input Name" name="studentName" id="studentName"/>
					</td>
				</tr>
				<tr>
					<td colspan="1"></td>
					<td>
						<input type="button" value="确定" id="SSfunctionValidate"/>
						
						<!--  <button type="button" onclick="SSfunctionValidate()">确定</button>-->
						<input type="reset" value="重置"/>
					</td>
				</tr>
				<tr><td id="ssfunctionMentionInfo" colspan="2" style="color:red;"></td></tr>
			</table>
			</form>
				<div id="ssfunctionResult" style="display:none;color:#fffbf0;">
					<table align="center" id="ssfunctionResultform">
					</table>
				</div>
			</div>
		</div>
		<div id="asfunction">
			<p class="addStudent">增添学生信息</p>
			<div class="asfunction">
				<form action="AddStudentServlet" method="post" id="form2">
				<table align="center">
					<tr>
						<td>ID</td>
						<td>
							<input type="text" placeholder="Please input ID" name="astudentID" id="astudentID"/>
							<div id="astudentIDerror" style="display:inline;color:red;"></div>
						</td>
					</tr>
					<tr>
						<td>姓名</td>
						<td>
							<input type="text" placeholder="Please input Name" name="astudentName" id="astudentName"/>
							<div id="astudentNameerror" style="display:inline;color:red;"></div>
						</td>
					</tr>
					<tr>
						<td>性别</td>
						<td>
							<input type="text" placeholder="Please input Gender" name="astudentGender" id="astudentGender"/>
							<div id="astudentGendererror" style="display:inline;color:red;"></div>
						</td>
					</tr>
					<tr>
						<td>电话</td>
						<td>
							<input type="text" placeholder="Please input Tel" name="astudentTel" id="astudentTel"/>
							<div id="astudentTelerror" style="display:inline;color:red;"></div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="确定" id="ASfunctionValidate"/>
							<input type="reset" value="重置"/>
						</td>
					</tr>
				</table>
				</form>
				<div id="asfunctionResult" style="display:none;color:#fffbf0;">
					<table align="center" id="asfunctionResultform">
					</table>
				</div>
			</div>
		</div>
		<div id="dsfunction">
			<p class="deleteStudent">删除学生信息</p>
			<div class="dsfunction">
			<form action="DeleteStudentServlet" method="post" id="form3">
			<table align="center">
				<tr>
					<td>请输入ID</td>
					<td>
						<input type="text" placeholder="Please input ID" name="VdstuentID" id="VdstudentID"/>
					</td>
				</tr>
				<tr>
					<td>请输入姓名</td>
					<td>
						<input type="text" placeholder="Please input Name" name="VdstudentName" id="VdstudentName"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="确认操作" id="DSfunctionValidate"/>
						<input type="reset" value="重置">
					</td>
				</tr>
				<tr><td colspan="2" id="dsfunctionMentionInfo"></td></tr>
			</table>
			</form>
			<div id="dsfunctionConfirm" style="display:none;">
				<table id="dsfunctionConfirmFailure" style="display:none;"></table>
				<table id="dsfunctionConfirmSuccess" align="center" style="display:none;">
					<tr><td colspan="2">请确认学生信息!</td></tr>
					<tr>
						<td>ID</td>
						<td id="dsfunctionConfirmID"></td>
					</tr>
					<tr>
						<td>姓名</td>
						<td id="dsfunctionConfirmName"></td>
					</tr>
					<tr>
						<td>性别</td>
						<td id="dsfunctionConfirmGender"></td>
					</tr>
					<tr>
						<td>电话</td>
						<td id="dsfunctionConfirmTel"></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="确认操作" id="dsfunctionConfirmButton"/>
							<input type="button" value="取消" id="dsfunctionCancel">
						</td>
					</tr>
				</table>
			</div>
			<div id="dsfunctionResult" style="display:none;color:#fffbf0;">
				<table align="center" id="dsfunctionResultform"></table>
			</div>
			</div>
		</div>
		<div id="usfunction">
			<p class="updateStudent">修改学生信息</p>
			<div class="usfunction">
				<form action="UpdateStudentServlet" method="post" id="form4">
				<table align="center">
					<tr>
					<td>请输入ID</td>
					<td>
						<input type="text" placeholder="Please input ID" name="VustuentID" id="VustudentID"/>
						<div id="VustudentIDerror" style="display:inline;color:red;"></div>
					</td>
				</tr>
				<tr>
					<td>请输入姓名</td>
					<td>
						<input type="text" placeholder="Please input Name" name="VustudentName" id="VustudentName"/>
						<div id="VustudentNameerror" style="display:none;color:red;"></div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="确认操作" id="USfunctionValidate"/>
						<input type="reset" value="重置">
					</td>
				</tr>
				<tr>
					<td id="usfunctionMentionInfo" colspan="2" style="color:red;"></td>
				</tr>
				</table>
				</form>
			</div>
			<div id="usfunctionEdit" style="display:none;">
				<table align="center">
					<tr><td colspan="2"	style="color:yellow;">请修改信息!</td></tr>
					<tr>
						<td>ID</td>
						<td><input type="text" placeholder="Please input ID" id="usID"/></td>
					</tr>
					<tr>
						<td>姓名</td>
						<td><input type="text" placeholder="Please input Name" id="usName"/></td>
					</tr>
					<tr>
						<td>性别</td>
						<td><input type="text" placeholder="Please input Gender" id="usGender"/></td>
					</tr>
					<tr>
						<td>电话</td>
						<td><input type="text" placeholder="Please input Tel" id="usTel"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="button" value="确认修改" id="usfunctionEditConfirm"/>
						<input type="reset" value="重置"/>
						</td>
					</tr>
				</table>
			</div>
			<div id="usfunctionResult" style="display:none;">
				<table align="center" id="usfunctionResultform"></table>
			</div>
		</div>
	</div>
</body>
</html>

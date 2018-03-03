var xmlhttp;

function getXMLHttpRequest(){
	var xmlhttprequest;
	//创建一个XMLHttpRequest对象
	if(window.XMLHttpRequest){
		xmlhttprequest = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		xmlhttprequest = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return xmlhttprequest;
}

function SSfunctionValidate(){
	document.getElementById("ssfunctionResult").style="display:inline;color:#fffbf0;z-index:50;";
	var studentID = document.getElementById("studentID").value;
	window.alert(studentID);
	var studentName = document.getElementById("studentName").value;
	var url = "localhost:8080/ADUSsystem/SearchStudentServlet";
	var data = "studentID="+studentID+"&studentName="+studentName;
	
	xmlhttp = getXMLHttpRequest();
	xmlhttp.open("POST",url,true);
	xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
	xmlhttp.onreadystatechange = SSfunctionCallback;//设置获取函数由哪个函数处理，不需加括号
	xmlhttp.send(data);//发送请求
}

function SSfunctionCallback(){
	if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
		var studentINFOlist = xmlhttp.responseText;
		SSfunctionShowCallback(studentINFOlist);
	}
}

function SSfunctionShowCallback(studentINFOlist){
	var htmls = "";
	var studentINFOarray = studentINFOlist.split("|");
	if(studentINFOarray[0].equalsIgnorCase("查询成功")){
		htmls = "<tr style=\"color:red\"><th rowspan=\"2\">学生</th><th></th>";
	}else if(studentINFOarray[0].equalsIgnoreCase("查询失败")){
		htmls = "<tr><td>"+"未在数据库中查询到该学生!"+"</td></tr>";
	}else{
		htmls = "<tr><td>"+"未知错误!"+"</td></tr>";
	}
	document.getElementById("ssfunctionResultform").innerHtml=htmls;
	
}
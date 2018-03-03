var IDreg = /^[A-Z]\d{9}$/;
var Namereg = /^[\u4E00-\u9FA5]{2,4}$/;
var Telreg = /^[1][3578]\d{9}$/;
	$(document).ready(function(){
		$(".flip").click(function(){
			$(".clicktoslide").slideToggle('slow');
		});
	});
	$(document).ready(function(){
		$(".searchStudent").click(function(){
			$(".ssfunction").slideToggle('slow');
		});
	});
	$(document).ready(function(){
		$(".addStudent").click(function(){
			$(".asfunction").slideToggle('slow');
		});
	});
	$(document).ready(function(){
		$(".deleteStudent").click(function(){
			$(".dsfunction").slideToggle('slow');
		});
	});
	$(document).ready(function(){
		$(".updateStudent").click(function(){
			$(".usfunction").slideToggle('slow');
		});
	});
	$(document).ready(function(){
		$("#SSfunctionValidate").click(function(){
			var studentID = $("#studentID").val();
			var studentName = $("#studentName").val();
			
			if(studentID.length!=0&&studentName.length!=0){
				if(IDreg.test(studentID)==false){
					$("#ssfunctionMentionInfo").text("您输入的ID不正确");
					return false;
				}else if(Namereg.test(studentName)==false){
					$("#ssfunctionMentionInfo").text("您输入的名字不正确");
					return false;
				}
			}else if(studentID.length == 0){
				if(Namereg.test(studentName)==false){
					$("#ssfunctionMentionInfo").text("您输入的名字不正确");
					return false;
				}
			}else if(studentName.length == 0){
				if(IDreg.test(studentID)==false){
					$("#ssfunctionMentionInfo").text("您输入的ID不正确");
					return false;
				}
			}
			
			$("#ssfunctionResult").attr("style","display:inline;color:red;");
			$.ajax({
				type:"POST",
				url:"/ADUSsystem/SearchStudentServlet",
				data:{
					"studentID":$("#studentID").val(),
					"studentName":$("#studentName").val()	
				},
				dataType:"text",
				/**beforeSend:function(XMLHttpRequest){
					alert("正在调用ajax方法");
				},*/
				success:function(studentINFOlist){
					var studentINFOarray = studentINFOlist.split("|");
					if(studentINFOarray[0]=="查询成功"){
						$("#ssfunctionResultform").html("<tr><td style=\"color:white\" colspan=\"2\">"+studentINFOarray[0]+"</td></tr>"+"<tr><td>ID</td><td>"+studentINFOarray[1]+
								"</td></tr><tr><td>姓名</td><td>"+studentINFOarray[2]+"</td></tr><tr><td>性别</td><td>"+studentINFOarray[3]+"</td></tr><tr><td>电话</td><td>"
								+studentINFOarray[4]+"</td></tr>"
						);
					}else{
						$("#ssfunctionResultform").html("<tr><td style=\"color:red\">"+studentINFOarray[0]+"</td></tr>");
					}
					},
				error:function(studentINFOlist){
					alert("查询学生功能出错!请联系Penistrong");
					$("#ssfunctionResultform").html("<tr style=\"color:red\"><td>功能出错</td></tr>");
				}
			});
		});
	});
	
	$(document).ready(function(){
		$("#ASfunctionValidate").click(function(){
			var studentID = $("#astudentID").val();
			var studentName = $("#astudentName").val();
			var studentGender = $("#astudentGender").val();
			var studentTel = $("#astudentTel").val();
			
			$("#asfunctionResult").attr("style","display:inline;color:red;");
			$.ajax({
				type:"POST",
				url:"/ADUSsystem/AddStudentServlet",
				data:{
					"studentID":$("#astudentID").val(),
					"studentName":$("#astudentName").val(),
					"gender":$("#astudentGender").val(),
					"tel":$("#astudentTel").val()
				},
				dataType:"text",
				success:function(AddStudentResult){
					var isAddSuccess = AddStudentResult;
					if(isAddSuccess=="添加学生信息成功"){
						$("#asfunctionResultform").html("<tr><td style=\"color:green\">"+isAddSuccess+"</td></tr>");
					}else{
						$("#asfunctionResultform").html("<tr><td style=\"color:red\">"+isAddSuccess+"</td></tr>");
					}
				},
				error:function(AddStudentResult){
					alert("添加学生功能出错!请联系Penistrong");
					$("#asfunctionResultform").html("<tr style=\"color:red\"><td>功能出错!</td></tr>")
				}
			});
		});
	});
	
	$(document).ready(function(){
		$("#DSfunctionValidate").click(function(){
			alert("您正在调用");
			var studentID = $("#VdstuentID").val();
			var studentName = $("#VdstudentName").val();
			
			if(studentID.length!=0&&studentName.length!=0){
				if(IDreg.test(studentID)==false || Namereg.test(studentName)==false){
					$("#dsfunctionMentionInfo").text("信息有误,请检查您输入的信息");
					$("#dsfunctionConfirm").attr("style","display:none;");
					return false;
				}
			}
			$("#dsfunctionConfirm").attr("style","display:inline;color:red;");
			$.ajax({
				type:"POST",
				url:"/ADUSsystem/SearchStudentServlet",
				data:{
					"studentID":$("#VdstudentID").val(),
					"studentName":$("#VdstudentName").val()
				},
				dataType:"text",
				success:function(VdstudentINFOlist){
					var studentINFOarray = VdstudentINFOlist.split("|");
					if(studentINFOarray[0]=="查询成功"){
						$("#dsfunctionConfirmFailure").hide();
						$("#dsfunctionConfirmSuccess").show();
						$("#dsfunctionConfirmID").text(studentINFOarray[1]);
						$("#dsfunctionConfirmName").text(studentINFOarray[2]);
						$("#dsfunctionConfirmGender").text(studentINFOarray[3]);
						$("#dsfunctionConfirmTel").text(studentINFOarray[4]);
					}else{
						$("#dsfunctionConfirmFailure").show();
						$("#dsfunctionConfirmSuccess").hide();
						$("#dsfunctionConfirmFailure").text("<tr><td style=\"color:red;\">库中没有该学生!</td></tr>");
					}
				}
			});
		});
	});
	
	$(document).ready(function(){
		$("#USfunctionValidate").click(function(){
			var studentID = $("#VustudentID").val();
			var studentName = $("#VustudentName").val();
			
			if(IDreg.test(studentID)==false||Namereg.test(studentName)==false){
				$("#usfunctionMentionInfo").text("信息出错,请检查您输入的信息!");
				return false;
			}
			$.ajax({
				type:"POST",
				url:"/ADUSsystem/SearchStudentServlet",
				data:{
					"studentID":$("#VustudentID").val(),
					"studentName":$("#VustudentName").val()
				},
				dataType:"text",
				success:function(VustudentINFOlist){
					var studentINFOarray = VustudentINFOlist.split("|");
					if(studentINFOarray[0]=="查询成功"){
						$("#usfunctionEdit").attr("style","display:inline");
						$("#usID").val(studentINFOarray[1]);
						$("#usName").val(studentINFOarray[2]);
						$("#usGender").val(studentINFOarray[3]);
						$("#usTel").val(studentINFOarray[4]);
					}else{
						$("#usfunctionEdit").attr("style","display:inline");
						$("#usfunctionMentionInfo").text("抱歉，库中没有该学生!");
					}
				}
			})
		});
	});
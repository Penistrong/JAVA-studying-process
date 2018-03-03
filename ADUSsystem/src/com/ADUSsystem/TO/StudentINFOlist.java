package com.ADUSsystem.TO;

import com.ADUSsystem.PO.Student;

public class StudentINFOlist {
	private Student studentINFO;
	private Integer functionResult;//1为成功,2为失败
	
	public void setStudentINFO(Student student) {
		this.studentINFO = student;
	}
	
	public Student getStudentINFO(){
		return studentINFO;
	}
	
	public void setFunctionResult(Integer functionResult) {
		this.functionResult = functionResult;
	}
	
	public Integer getFunctionResult() {
		return functionResult;
	}
}

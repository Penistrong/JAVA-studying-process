package com.ADUSsystem.TO;

import com.ADUSsystem.PO.Student;

public class StudentINFOlist {
	private Student studentINFO;
	private Integer functionResult;//1Ϊ�ɹ�,2Ϊʧ��
	
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

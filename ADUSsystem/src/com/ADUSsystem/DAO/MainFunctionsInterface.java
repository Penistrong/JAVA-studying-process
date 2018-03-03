package com.ADUSsystem.DAO;

import com.ADUSsystem.PO.Student;

public interface MainFunctionsInterface {
	
	public Student searchStudentByID(String studentID);
	
	public Student searchStudentByName(String studentName);
	
	public Student searchStudentWithIDName(Student student);
	
	public boolean insertStudent(Student student);
}

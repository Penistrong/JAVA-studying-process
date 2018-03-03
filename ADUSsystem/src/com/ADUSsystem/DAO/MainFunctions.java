package com.ADUSsystem.DAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.ADUSsystem.DatabaseLayer.DataBaseUtils;
import com.ADUSsystem.PO.Student;

public class MainFunctions implements MainFunctionsInterface{

	public Student searchStudentByID(String studentID) {
		String sql = "select * from studentsINFO where stu_ID = ?";
		
		List<Object> list = new ArrayList<Object>();
		list.add(studentID);
		
		DataBaseUtils dbu = new DataBaseUtils();
		ResultSet rs = dbu.startQuery(sql, list.toArray());
		Student studentinDB = new Student();
		
		try {
			if(rs.next()) {
				studentinDB.setID(rs.getString("stu_ID"));
				studentinDB.setName(rs.getString("stu_Name"));
				studentinDB.setGender(rs.getString("gender"));
				studentinDB.setTel(rs.getString("tel"));
				
				return studentinDB;
			}else {
				studentinDB.setID(null);
				return studentinDB;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return null;
	}

	public Student searchStudentByName(String studentName) {
		String sql = "select * from studentsINFO where stu_Name = ?";
		
		List<Object> list = new ArrayList<Object>();
		list.add(studentName);
		
		DataBaseUtils dbu = new DataBaseUtils();
		ResultSet rs = dbu.startQuery(sql, list.toArray());
		Student studentinDB = new Student();
		
		try {
			if(rs.next()) {
				studentinDB.setID(rs.getString("stu_ID"));
				studentinDB.setName(rs.getString("stu_Name"));
				studentinDB.setGender(rs.getString("gender"));
				studentinDB.setTel(rs.getString("tel"));
				
				return studentinDB;
			}else {
				studentinDB.setID(null);
				return studentinDB;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean insertStudent(Student student) {
		String sql = "insert into studentsINFO values(?,?,?,?)";
		
		List<Object> list = new ArrayList<Object>();
		list.add(student.getID());
		list.add(student.getName());
		list.add(student.getGender());
		list.add(student.getTel());
		
		DataBaseUtils dbu = new DataBaseUtils();
		if(dbu.startAUD(sql, list.toArray())) {
			return true;
		}else {
			return false;
		}
		
	}

	public Student searchStudentWithIDName(Student student) {
		String sql = "select * from studentsINFO where stu_ID = ? and stu_Name = ?";
		
		List<Object> list = new ArrayList<Object>();
		list.add(student.getID());
		list.add(student.getName());
		
		DataBaseUtils dbu = new DataBaseUtils();
		ResultSet rs = dbu.startQuery(sql, list.toArray());
		Student studentinDB = new Student();
		
		try {
			if(rs.next()) {
				studentinDB.setID(rs.getString("stu_ID"));
				studentinDB.setName(rs.getString("stu_Name"));
				studentinDB.setGender(rs.getString("gender"));
				studentinDB.setTel(rs.getString("tel"));
				
				return studentinDB;
			}else {
				studentinDB.setID(null);
				return studentinDB;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return null;
	}


}

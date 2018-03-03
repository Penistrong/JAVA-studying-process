package com.ADUSsystem.BusinessLayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ADUSsystem.DAO.MainFunctions;
import com.ADUSsystem.PO.Student;
import com.ADUSsystem.TO.StudentINFOlist;

/**
 * Servlet implementation class SearchStudentServlet
 */
@WebServlet("/SearchStudentServlet")
public class SearchStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//this.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println("您正在调用SearchStudentServlet/doPost");
		
		Student student = new Student();
		student.setID(request.getParameter("studentID"));
		student.setName(request.getParameter("studentName"));
		System.out.println(student.getID());
		System.out.println(student.getName());
	
		MainFunctions mf = new MainFunctions();
		Student studentinDB = new Student();
		studentinDB.InitStudentAllNull();
		
		if(student.getID()!=null && student.getName()!=null) {
			studentinDB = mf.searchStudentWithIDName(student);
		}else if(student.getID()!=null) {
			studentinDB = mf.searchStudentByID(student.getID());
		}else if(student.getName()!=null) {
			studentinDB = mf.searchStudentByName(student.getName());
		}
		
		if(studentinDB.getID()!=null) {
			String studentINFOlist = "查询成功";
			studentINFOlist+="|"+studentinDB.getID();
			studentINFOlist+="|"+studentinDB.getName();
			studentINFOlist+="|"+studentinDB.getGender();
			studentINFOlist+="|"+studentinDB.getTel();
			out.write(studentINFOlist);
			System.out.println("信息回送成功"+studentINFOlist);
		}else {
			String studentINFOlist = "查询失败:库中没有该学生!";
			out.write(studentINFOlist);
			System.out.println("信息回送成功"+studentINFOlist);
		}
	}

}

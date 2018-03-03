package com.ADUSsystem.BusinessLayer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ADUSsystem.DAO.MainFunctions;
import com.ADUSsystem.PO.Student;

/**
 * Servlet implementation class AddStudentServlet
 */
@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println("�����ڵ���AddStudentServlet/doPost");
		
		Student student = new Student();
		student.setID(request.getParameter("studentID"));
		student.setName(request.getParameter("studentName"));
		student.setGender(request.getParameter("gender"));
		student.setTel(request.getParameter("tel"));
		System.out.println(student);
		
		MainFunctions mf = new MainFunctions();
		boolean AddStudentResult = mf.insertStudent(student);
		
		if(AddStudentResult) {
			out.write("���ѧ����Ϣ�ɹ�!");
			System.out.println("��Ϣ���ͳɹ�!"+"AddStudentResult:"+AddStudentResult);
		}else {
			out.write("���ѧ����Ϣʧ��!�������и�ѧ��");
			System.out.println("��Ϣ���ͳɹ�!"+"AddStudentResult:"+AddStudentResult);
		}
	}

}

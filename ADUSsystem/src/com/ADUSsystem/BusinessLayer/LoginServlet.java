package com.ADUSsystem.BusinessLayer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ADUSsystem.DAO.SystemUserDAO;
import com.ADUSsystem.DAO.UserLRDAO;
import com.ADUSsystem.PO.User;
import com.ADUSsystem.TO.Info;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		//doGet(request, response);
		System.out.println("�����ڵ���LoginServlet");
		String userName = request.getParameter("UserName");
		String passWord = request.getParameter("PassWord");
		/*DataBaseUtils dbu = new DataBaseUtils();
		if(dbu.isLoginIn(userName, passWord)) {
			request.setAttribute("info", "��½�ɹ�");
			request.getRequestDispatcher("/index/info.jsp").forward(request, response);
			response.sendRedirect("index.jsp");
		}else {
			request.setAttribute("info", "��½ʧ��");
			request.getRequestDispatcher("/index/info.jsp").forward(request, response);
			response.sendRedirect("login.jsp");
		}*/
		User userLogin = new User();
		userLogin.setUserName(userName);
		userLogin.setPassWord(passWord);
		//�������ݽ�����
		SystemUserDAO dao = new UserLRDAO();
		User userinDB = dao.login(userLogin);
		if(userinDB != null) {
			Info mentionInfo = new Info("��½�ɹ�", 1);
			request.setAttribute("info", mentionInfo);
		}else {
			Info mentionInfo = new Info("��½ʧ��", 2);
			request.setAttribute("info", mentionInfo);
		}
		
		request.getRequestDispatcher("index/MentionInfo.jsp").forward(request, response);
	}

}

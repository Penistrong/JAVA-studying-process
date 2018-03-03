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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * 
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		User userRegister = new User();
		userRegister.setUserName(request.getParameter("UserName"));
		userRegister.setPassWord(request.getParameter("PassWord"));
		
		SystemUserDAO dao = new UserLRDAO();
		if(dao.register(userRegister)) {
			Info mentionInfo = new Info("×¢²á³É¹¦", 3);
			request.setAttribute("info", mentionInfo);
		}else {
			Info mentionInfo = new Info("×¢²áÊ§°Ü", 4);
			request.setAttribute("info", mentionInfo);
		}
		
		request.getRequestDispatcher("index/MentionInfo.jsp").forward(request, response);
	}

}

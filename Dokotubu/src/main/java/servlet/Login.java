package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.LoginLogic;
import model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//User を作る、そして椅子ログインを作る
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		
		User user = new User(name,pass);
		
		LoginLogic loginLogic = new  LoginLogic();
		boolean isLogin = loginLogic.excute(user);
		
		//この畏怖分でログインを判定
		if (isLogin) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
		}
		
		//出力に送る
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request, response);
		
	}

}

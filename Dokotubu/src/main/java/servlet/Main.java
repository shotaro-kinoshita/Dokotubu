package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GetMutterListLogic;
import model.Mutter;
import model.PostMutterLogic;
import model.User;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("あいうえお");
		//初期設定___________________________________________________________________
//		ServletContext application = this.getServletContext();
//		List<Mutter> mutterList = (List<Mutter>)application.getAttribute("mutterList");
//		
//		if ( mutterList == null ) {
//			mutterList = new ArrayList<>();
//			application.setAttribute("mutterList", mutterList);
//		}
		//初期設定2___________________________________________________________________
		//データベースから情報を引き戻して「mutterList」に保存→リクエストスコープに保存
		GetMutterListLogic gmll = new GetMutterListLogic();
		List<Mutter> mutterList =  gmll.excute();
		request.setAttribute("mutterList",mutterList);
		
		HttpSession session = request.getSession();
		//_________________________________________________________________________
		User loginUser = (User)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			response.sendRedirect("indx.jsp");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
		//__________________________________________________		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");
		System.out.println("kokomade");
		if(text != null && text.length() != 0 ) {
//			ServletContext application = this.getServletContext();
//			List<Mutter> mutterList = (List<Mutter>)application.getAttribute("mutterList");
			
			HttpSession session = request.getSession();
			User loginUser = (User)session.getAttribute("loginUser");
			//変更点
			
			Mutter mutter = new Mutter(loginUser.getName(),text);
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.excute(mutter);////////
			//application.setAttribute("mutterList", mutterList);
		} else {
			request.setAttribute("errorMsg","つぶやきがありません");
		}
		
		GetMutterListLogic gmll = new GetMutterListLogic();
		List<Mutter> mutterList = gmll.excute();
		request.setAttribute("mutterList",mutterList);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
		
	}

}

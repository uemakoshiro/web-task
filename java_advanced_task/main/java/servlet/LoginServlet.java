package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Product;
import entity.User;
import service.ProductService;
import service.UserService;

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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("loginId");
		String pass = request.getParameter("pass");
		
		if(id.isEmpty() || pass.isEmpty()) {
			if(id.isEmpty()) {
				request.setAttribute("msg1", "IDは必須です");
			}if(pass.isEmpty()) {
				request.setAttribute("msg2", "PASSは必須です");
			}
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		HttpSession session = request.getSession();
		User user = new UserService().loginCheck(id, pass);
		if(user == null) {
			request.setAttribute("msg3", "IDまたはパスワードが不正です");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			ArrayList<Product> list = new ArrayList<>();
			list = new ProductService().Search("","");
			
			request.setAttribute("SearchResult",list);
			session.setAttribute("userInfo",user);
			request.getRequestDispatcher("menu.jsp").forward(request, response);
		}
	}

}

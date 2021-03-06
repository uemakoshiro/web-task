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

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userInfo");
		if(user == null) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		String key = (String)session.getAttribute("key");
		String order = (String)session.getAttribute("order");
		ArrayList<Product> list = new ArrayList<>();
		list = new ProductService().Search(key,order);
		request.setAttribute("SearchResult", list);
		request.getRequestDispatcher("menu.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String key = (String)session.getAttribute("key");
		String order = (String)session.getAttribute("order");
		ArrayList<Product> list = new ArrayList<>();
		list = new ProductService().Search(key,order);
		request.setAttribute("SearchResult", list);
		request.getRequestDispatcher("menu.jsp").forward(request, response);
	}

}

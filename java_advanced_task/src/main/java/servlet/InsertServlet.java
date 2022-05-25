package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Category;
import entity.Product;
import service.CategoryService;
import service.ProductService;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		ArrayList<Category> list = new ArrayList<>();
		list = new CategoryService().SelectAll();
		request.setAttribute("categoryList", list);
		request.getRequestDispatcher("insert.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("loginId");
		String name = request.getParameter("productName");
		String price = request.getParameter("price");
		String categoryId = request.getParameter("roleId");
		String description = request.getParameter("description");
		String file = request.getParameter("file");
		
		if(id.isEmpty() || name.isEmpty() || price.isEmpty()) {
			if(id.isEmpty()) {
				request.setAttribute("msg1", "商品IDは必須です");
			}if(name.isEmpty()) {
				request.setAttribute("msg2", "商品名は必須です");
			}if(price.isEmpty()) {
				request.setAttribute("msg3", "単価は必須です");
			}
			request.getRequestDispatcher("insert.jsp").forward(request, response);
		}
		
		
		Product product = new Product(Integer.parseInt(id), name, Integer.parseInt(price), Integer.parseInt(categoryId), description, file);
		int result = new ProductService().Insert(product);
		if(result == 1) {
			request.setAttribute("msg", "登録が完了しました");
		}else {
			request.setAttribute("msg", "商品IDが重複しています");
		}
		request.getRequestDispatcher("insert.jsp").forward(request, response);
		
	}

}

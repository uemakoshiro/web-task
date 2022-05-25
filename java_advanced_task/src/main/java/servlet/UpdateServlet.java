package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Product;
import service.ProductService;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		
		String mode = request.getParameter("mode");
		if("detail".equals(mode)) {
			String id = request.getParameter("id");
			Product pd = new ProductService().SelectId(Integer.parseInt(id));
			
			request.setAttribute("data", pd);
			request.getRequestDispatcher("updateInput.jsp").forward(request, response);
		}else {
			String id = request.getParameter("loginId");
			String name = request.getParameter("userName");
			String price = request.getParameter("tel");
			String categoryId = request.getParameter("roleId");
			String description = request.getParameter("description");
			String file = request.getParameter("file");
			
			if(id.isEmpty() || name.isEmpty() || price.isEmpty()) {
				if(id.isEmpty()){
					request.setAttribute("msg1", "商品IDは必須です");
				}if(name.isEmpty()){
					request.setAttribute("msg2", "商品名は必須です");
				}if(price.isEmpty()){
					request.setAttribute("msg3", "単価は必須です");
				}
				
				request.getRequestDispatcher("updateInput.jsp").forward(request, response);
			}else {
				Product before = new ProductService().SelectId(Integer.parseInt(id));
				
				Product pd = new Product(Integer.parseInt(id), name, Integer.parseInt(price), Integer.parseInt(categoryId), description, file);
				int result = new ProductService().Update(pd);
				if(result == 1) {
					request.setAttribute("msg", "更新処理が完了しました");
					request.getRequestDispatcher("MenuServlet").forward(request, response);
				}else {
					request.setAttribute("data", before);
					request.setAttribute("error", "更新時にエラーが発生しました");
					request.getRequestDispatcher("updateInput.jsp").forward(request, response);
				}
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("loginId");
		String name = request.getParameter("userName");
		String price = request.getParameter("tel");
		String categoryId = request.getParameter("roleId");
		String description = request.getParameter("description");
		String file = request.getParameter("file");
		
		if(id.isEmpty() || name.isEmpty() || price.isEmpty()) {
			if(id.isEmpty()){
				request.setAttribute("msg1", "商品IDは必須です");
			}if(name.isEmpty()){
				request.setAttribute("msg2", "商品名は必須です");
			}if(price.isEmpty()){
				request.setAttribute("msg3", "単価は必須です");
			}
			Product before = new ProductService().SelectId(Integer.parseInt(id));
			request.setAttribute("data", before);
			
			request.getRequestDispatcher("updateInput.jsp").forward(request, response);
		}else {
			
			Product pd = new Product(Integer.parseInt(id), name, Integer.parseInt(price), Integer.parseInt(categoryId), description, file);
			int result = new ProductService().Update(pd);
			if(result == 1) {
				request.setAttribute("msg", "更新処理が完了しました");
				request.getRequestDispatcher("MenuServlet").forward(request, response);
			}else {
				request.setAttribute("error", "更新時にエラーが発生しました");
				request.getRequestDispatcher("updateInput.jsp").forward(request, response);
			}
		}
	}

}

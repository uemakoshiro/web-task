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
 * Servlet implementation class DetailServlet
 */
@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String mode = request.getParameter("mode");
		if("detail".equals(mode)) {
			String strId = request.getParameter("id");
			Integer id = Integer.parseInt(strId);
			
			Product pd = new ProductService().SelectId(id);
			
			request.setAttribute("info", pd);
			request.getRequestDispatcher("detail.jsp").forward(request, response);
		}else {
			String strId = request.getParameter("loginId");
			int result = new ProductService().Delete(Integer.parseInt(strId));
			if(result == 1) {
				request.setAttribute("msg", "削除に成功しました");
			}else {
				request.setAttribute("msg", "削除に失敗しました");
			}
			
			request.getRequestDispatcher("MenuServlet").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

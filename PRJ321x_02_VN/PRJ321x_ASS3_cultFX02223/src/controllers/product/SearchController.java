package controllers.product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ListProductDAO;
import models.Pagination;
import models.Product;

public class SearchController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String page = req.getParameter("page");
			String brand = req.getParameter("brand");
			String search = req.getParameter("search");
			
			int nPage = 1;

			if (page != null) {
				nPage = Integer.parseInt(page);
			}
			
			req.setAttribute("brand", brand);
			req.setAttribute("search", search);
			Pagination<Product> ls = new ListProductDAO().search(search, brand, nPage, 8);

			req.setAttribute("pagination", ls);
			req.getRequestDispatcher("search.jsp").forward(req, resp);
		} catch (SQLException e) {
			Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}

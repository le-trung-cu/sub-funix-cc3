package controllers.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ListProductDAO;
import models.Product;

public class InformationProductController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			Product p = new ListProductDAO().getProduct(id);
			req.setAttribute("product", p);
			req.getRequestDispatcher("/infoProduct.jsp").forward(req, resp);
		} catch (Exception e) {
			resp.getWriter().println(e);
		}
	}
}

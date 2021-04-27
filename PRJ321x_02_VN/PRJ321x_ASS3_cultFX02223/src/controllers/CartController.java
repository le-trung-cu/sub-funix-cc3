package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ListProductDAO;
import models.Cart;
import models.Product;

public class CartController extends HttpServlet{
	
	// add and subtract and delete a product from shopping cart
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("cart.jsp");
		String acction = req.getParameter("action");
		if(acction == null) {
			dispatcher.forward(req, resp);
		}
		try {	
			HttpSession session = req.getSession(true);
			int productId = Integer.parseInt(req.getParameter("id"));
			
			if(session.getAttribute("cart") == null) {
				session.setAttribute("cart", new Cart());
			}
			
			Cart c = (Cart) session.getAttribute("cart");
			
			if(acction.equalsIgnoreCase("add")) {
				Product p = new ListProductDAO().getProduct(productId);
				p.setNumber(1);
				c.add(p);
			}else if (acction.equalsIgnoreCase("delete")){
				c.remove(productId);
			}else if(acction.equalsIgnoreCase("subtract")) {
				Product p = new ListProductDAO().getProduct(productId);
				c.subtract(p);
			}
			dispatcher.forward(req, resp);
		}catch(Exception e) {
			resp.getWriter().println(e);
		}
	}
}

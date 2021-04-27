package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrdersDAO;
import models.Cart;
import models.Orders;

public class PayController extends HttpServlet {
	
	// checkout cart
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession(true);
			
			if(session.getAttribute("cart") == null) {
				session.setAttribute("cart", new Cart());
			}
			
			Cart c = (Cart) session.getAttribute("cart");
			
			OrdersDAO dao = new OrdersDAO();
			String username = req.getParameter("email");
			String discount = req.getParameter("discount");
			String address  = req.getParameter("address");
			
			// create new order and save into database.
			Orders d = new Orders(address, username, discount);
			int orderId = dao.insertOrder(d, c);
			
			session.setAttribute("cart", new Cart());
			session.setAttribute("message", "Thank you! Checkout success.");
			
			resp.sendRedirect("index");
			
			
		} catch (Exception e) {
			resp.getWriter().println(e);
			e.printStackTrace();
		}
	}
}

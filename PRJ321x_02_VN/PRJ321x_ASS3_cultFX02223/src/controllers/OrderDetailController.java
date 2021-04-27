package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.OrdersDAO;
import models.Account;
import models.Orders;

public class OrderDetailController extends HttpServlet {
	
	// get order detail
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession() == null || req.getSession().getAttribute("account") == null) {
			resp.sendRedirect("login");
			return;
		}
		try {
			String idd = req.getParameter("id");
			int id = Integer.parseInt(idd);

			Account acc = (Account) req.getSession().getAttribute("account");
			OrdersDAO db = new OrdersDAO();
			Orders order = db.getOrder(id);
			req.setAttribute("order", order);
			req.getRequestDispatcher("orderDetail.jsp").forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

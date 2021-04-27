package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ListProductDAO;
import dao.OrdersDAO;
import models.Account;
import models.Orders;
import models.Pagination;
import models.Product;

public class OrdersController extends HttpServlet {
	
	// view history's order of buyer
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		
		if (req.getSession() == null || req.getSession().getAttribute("account") == null) {
			resp.sendRedirect("login");
			return;
		}
		try {
			String page = req.getParameter("page");
			int nPage = 1;
			if (page != null) {
				nPage = Integer.parseInt(page);
			}
			Account acc = (Account) req.getSession().getAttribute("account");
			OrdersDAO db = new OrdersDAO();
			Pagination<Orders> orders = db.getOrders(acc.getUsr(), nPage, 2);
			req.setAttribute("pagination", orders);
			req.getRequestDispatcher("orders.jsp").forward(req, resp);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}

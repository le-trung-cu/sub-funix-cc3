package admin.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.product.ListController;
import dao.ListProductDAO;
import dao.OrdersDAO;
import models.Orders;
import models.Pagination;
import models.Product;

public class OrdersController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			String idd = req.getParameter("id");
			OrdersDAO db = new OrdersDAO();
			// get list orders
			if(idd == null) {
				String page = req.getParameter("page");
				int nPage = 1;
				if(page != null) {
					nPage = Integer.parseInt(page);
				}
				Pagination<Orders> orders = db.getOrders(nPage, 4);
				req.setAttribute("pagination",orders);
				req.getRequestDispatcher("/admin/orders.jsp").forward(req, resp);
			}else {
				// get Order detail by order id
				Orders order = db.getOrder(Integer.parseInt(idd));
				req.setAttribute("order", order);
				req.getRequestDispatcher("/admin/orderDetail.jsp?id="+idd).forward(req, resp);
			}
		} catch (Exception e) {
			Logger.getLogger(OrdersController.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}

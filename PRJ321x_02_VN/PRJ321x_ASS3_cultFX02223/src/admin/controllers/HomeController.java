package admin.controllers;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import dao.OrdersDAO;
import models.Account;
import models.Orders;
import models.Pagination;

public class HomeController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			AccountDAO db = new AccountDAO();

			String page = req.getParameter("page");
			int nPage = 1;
			if(page != null) {
				nPage = Integer.parseInt(page);
			}
			
			Pagination<Account> accoutns = db.getUsers(nPage, 4);
			req.setAttribute("pagination",accoutns);
			req.getRequestDispatcher("admin/index.jsp").forward(req, resp);
			
		} catch (Exception e) {
			Logger.getLogger(OrdersController.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}

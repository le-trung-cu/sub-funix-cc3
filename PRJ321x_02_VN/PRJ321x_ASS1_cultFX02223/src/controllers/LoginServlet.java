package controllers;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	// validate information of admin account
	private boolean validate(String userName, String password) {
		ServletContext context = getServletContext();
		
		// read information of account from web.xml
		String adminName = context.getInitParameter("username");
		String adminPass = context.getInitParameter("password");

		if (adminName.equals(userName) && adminPass.equals(password)) {
			return true;
		}
		return false;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// collect data from a login form
		String userName = req.getParameter("username");
		String passWord = req.getParameter("password");
		
		if (validate(userName, passWord)) {
			resp.sendRedirect("home.jsp");
			return;
		}
		resp.sendRedirect("login.jsp");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").include(req, resp);
	}
}

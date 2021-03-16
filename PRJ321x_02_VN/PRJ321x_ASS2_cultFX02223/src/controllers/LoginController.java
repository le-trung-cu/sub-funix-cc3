package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import models.Account;

public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// make sure that email and pass are valid
	private boolean validateSyntax(String userName, String password) {
		// make sure that email is valid
		String regexMail = "^[A-Z0-9a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
		String regex = "[a-zA-Z0-9_!@#$%^&*]+";

		if (!password.matches(regex) || !userName.matches(regexMail)) {
			return false;
		}

		return true;

	}

	private boolean vaidateAdminAccount(String userName, String password) {
		ServletContext context = getServletContext();
		// read information of account from web.xml
		String adminName = context.getInitParameter("username");
		String adminPass = context.getInitParameter("password");

		if (adminName.equalsIgnoreCase(userName) && adminPass.equals(password)) {
			return true;
		}
		return false;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession(true).invalidate();
		try {
			// collect data from a login form
			String username = req.getParameter("username");
			String passWord = req.getParameter("password");
			boolean remember = req.getParameter("remember") != null ? true : false;
			
			Account acc = new Account();
			acc.setName(username);
			acc.setPwd(passWord);

			HttpSession session = req.getSession(true);
			// validate syntax
			if (!validateSyntax(username, passWord)) {
				session.setAttribute("error", "invalid syntax");
				// set input user name
				session.setAttribute("loginUsername", username);
				resp.sendRedirect("login");
				return;
			}

			// check account
			if (!vaidateAdminAccount(username, passWord)) {
				session.setAttribute("error", "wrong username or password");
				session.setAttribute("loginUsername", username);
				resp.sendRedirect("login");
			} else {
				// login is success
				// set session login info
				session.setAttribute("account", acc);
				session.setAttribute("message", "Hello " + acc.getName());

				// if user remember login
				if (remember) {
					Cookie cookieUsername = new Cookie("username", username);
					// set max age equals a week
					cookieUsername.setMaxAge(60 * 60 * 24 * 7);
					resp.addCookie(cookieUsername);
				}else {
					Cookie cookieUsername = new Cookie("username", null);
					// set max age equals a week
					cookieUsername.setMaxAge(0);
					resp.addCookie(cookieUsername);
				}

				// now redirect to index page of admin
				resp.sendRedirect("admin/index.jsp");
			}
		} catch (NullPointerException e) {
			resp.sendRedirect("login");
		} catch (Exception e) {
			resp.getWriter().println(e);
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		HttpSession session = req.getSession(true);
		
		// if before user login but invalidate
		if(session.getAttribute("loginUsername") != null) {
			req.getRequestDispatcher("login.jsp").include(req, resp);
			return;
		}
		else if (cookies != null) {
			// check if user login before with remember me option checked
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					session.setAttribute("loginUsername", cookie.getValue());
					// now redirect to index page of admin
					req.getRequestDispatcher("login.jsp").include(req, resp);
					return;
				}
			}
		}

		req.getRequestDispatcher("login.jsp").include(req, resp);
	}
}
package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import models.Account;

public class RegisterController extends HttpServlet {
	// make sure that email and pass are valid
	private boolean validateSyntax(String mail, String password, String address, String phone) {
		// make sure that email is valid
		String regexMail = "^[A-Z0-9a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
		String regex = "[a-zA-Z0-9_!@#$%^&*]+";
		String regexPhone = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
				+ "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
				+ "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";

		if (!password.matches(regex) || !mail.matches(regexMail) || !phone.matches(regexPhone)
				|| address.length() < 1) {
			return false;
		}

		return true;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("register.jsp").include(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mail = req.getParameter("mail");
		String passWord = req.getParameter("password");
		String address = req.getParameter("address");
		String phone = req.getParameter("phone");

		if (validateSyntax(mail, passWord, address, phone)) {
			req.getRequestDispatcher("register.jsp").include(req, resp);
		}

		Account account = new Account(mail, passWord, 0, mail, address, phone, 0);

		AccountDAO db = new AccountDAO();
		try {
			db.createUser(account);
			req.getSession(true).setAttribute("account", account);
			resp.sendRedirect("list");
		} catch (SQLException e) {
			req.setAttribute("error", "Register fail");
			req.getRequestDispatcher("register.jsp").include(req, resp);
			Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}

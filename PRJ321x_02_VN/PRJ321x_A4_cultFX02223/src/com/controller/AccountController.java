package com.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dao.AccountDAO;
import com.models.Account;

@Controller
public class AccountController {
	
	@Autowired
	private AccountDAO accountService;

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin(HttpServletRequest request, HttpServletResponse response) {	
		
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView postLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Account acc = null;
		try {
			acc = accountService.findAccount(username);
		}catch (Exception e) {
			
		}
		
		
		if(username != null && password != null) {
			if(acc != null && password.equals(acc.getPwd())) {
				request.getSession(true).setAttribute("account", acc);
				
				response.sendRedirect("admin.html");
				return null;// new ModelAndView("admin/", "username", username);
			}else {
				return new ModelAndView("login", "error", "Username or password is invalid");
			}
		}
		return new ModelAndView("login", "error", "Please enter username and password");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void postLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if(session != null) {
			session.invalidate();
		}
		response.sendRedirect("login.html");
	}
}

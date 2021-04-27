package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.models.Account;

@Controller
public class AdminController {
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String getAdmin(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session != null && session.getAttribute("account") != null) {
			return "admin/index";
			
		}
		return "login";
	}
}

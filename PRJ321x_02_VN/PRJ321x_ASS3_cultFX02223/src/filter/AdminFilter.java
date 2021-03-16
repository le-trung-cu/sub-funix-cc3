package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Account;

public class AdminFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession(false);
		if(session != null) {
			Account acc = (Account) session.getAttribute("account");
			if(acc != null && acc.isAdmin()) {
				chain.doFilter(request, response);
				return;
			}
		}
		
		((HttpServletResponse) response).sendRedirect("login");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}

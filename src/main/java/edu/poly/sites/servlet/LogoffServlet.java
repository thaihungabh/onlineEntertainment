package edu.poly.sites.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import edu.poly.common.CookiesUtils;
import edu.poly.common.SessionUtils;

/**
 * Servlet implementation class LogoffServlet
 */
public class LogoffServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CookiesUtils.add("username", null, 0, response);
		
		SessionUtils.invalidate(request);
		request.setAttribute("isLogin", false);
		request.getRequestDispatcher("/Homepage").forward(request, response);
	}

}

package br.com.GoTripLogout;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			proccess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void proccess(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath()+"/index.html");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

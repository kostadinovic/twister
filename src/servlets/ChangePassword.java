package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ChangePassword extends HttpServlet{
	
	public ChangePassword() {}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response){
		String login = request.getParameter("login");
		String newPassword = request.getParameter("newPassword");
		String key = request.getParameter("key");
		response.setContentType("application/json");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		out.print(services.User.changePassword(login, newPassword, key));
		out.flush();
	}
}
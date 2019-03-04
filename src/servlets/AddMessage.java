package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AddMessage extends HttpServlet{
	
	public AddMessage() {}
	protected void doGet(HttpServletRequest request,HttpServletResponse response) {
		String monLogin = request.getParameter("monLogin");
		String key = request.getParameter("key");
		String message = request.getParameter("message");
		response.setContentType("application/json");
		PrintWriter out = null;
		try { 
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		out.print(services.Message.addMessage(monLogin, key, message));
		out.flush();	
	}
}

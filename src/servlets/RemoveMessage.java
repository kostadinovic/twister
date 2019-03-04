package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class RemoveMessage extends HttpServlet{
	
	public RemoveMessage() {}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) {
		String monLogin = request.getParameter("monLogin");
		String key = request.getParameter("key");
		String messageId = request.getParameter("messageId");
		response.setContentType("application/json");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		out.print(services.Message.removeMessage(monLogin, key, messageId));
		out.flush();
		
	}
}
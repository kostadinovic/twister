package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Message extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String key = request.getParameter("key");
			int id_message =  Integer.parseInt("id");
			String text = request.getParameter("text");
			
			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();
			
			//out.println(AddMessage.addMessage(key, id_message, text));
	}
}

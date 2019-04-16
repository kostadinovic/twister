package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ListUser extends HttpServlet{
	
	public ListUser() {}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("application/json");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		out.print(services.User.listUser());
		out.flush();
	}
}
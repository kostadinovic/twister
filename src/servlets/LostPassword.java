package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class LostPassword extends HttpServlet{
	
	public LostPassword() {}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response){
		String login = request.getParameter("login");
		String mail = request.getParameter("mail");
		String age = request.getParameter("age");
		response.setContentType("application/json");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		out.print(services.User.lostPassword(login, mail, age));
		out.flush();
	}
}
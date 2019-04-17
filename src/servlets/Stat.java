package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.User;

@SuppressWarnings("serial")
public class Stat extends HttpServlet{
	protected void doGet(HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("application/json");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		out.print(User.statUser());
		out.flush();
	}
}
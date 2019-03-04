package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import services.Friend;

@SuppressWarnings("serial")
public class RemoveFriend extends HttpServlet{
	
		public RemoveFriend() {}
		
		protected void doGet(HttpServletRequest request,HttpServletResponse response) {
			String monLogin = request.getParameter("monLogin");
			String key = request.getParameter("key");
			String friendLogin = request.getParameter("friendLogin");
			response.setContentType("application/json");
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			out.print(Friend.removeFriend(monLogin, key, friendLogin));
			out.flush();
		}
}

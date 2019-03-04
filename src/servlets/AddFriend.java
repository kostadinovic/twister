package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AddFriend extends HttpServlet{
	public AddFriend() {}
	protected void doGet(HttpServletRequest request,HttpServletResponse response) {
		String monLogin = request.getParameter("monlogin");
		String friendLogin = request.getParameter("friendlogin");
		String key = request.getParameter("key"); //verifier clé user droit + expiration
		response.setContentType("application/json");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		out.print(services.Friend.addFriend(monLogin, key, friendLogin));
		out.flush();
	}
}

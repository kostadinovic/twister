package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class LikeMessage extends HttpServlet{
	protected void doGet(HttpServletRequest request,HttpServletResponse response) {
		String key = request.getParameter("key");
		String id_message = request.getParameter("id_message");
		int id_m = Integer.parseInt(id_message);
		response.setContentType("application/json");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		out.print(services.Friend.likeMessage(key, id_m));
		out.flush();
	}
}
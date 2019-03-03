package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AddFriend extends HttpServlet{
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		    response.setContentType( "text/plain" );
		    String key = request.getParameter("key");
		    String id_friend = request.getParameter("id_friend");
		    PrintWriter out = response.getWriter ();
		    out.println(services.AddFriend.addFriend(key,Integer.parseInt(id_friend)));
		    
	}
}

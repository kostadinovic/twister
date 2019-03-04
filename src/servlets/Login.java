package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;


@SuppressWarnings("serial")
public class Login extends HttpServlet{
	
	public Login() {
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
	{
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		response.setContentType("application/json");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		out.print(services.Login.login(login, password));
		out.flush();
		
	}
}


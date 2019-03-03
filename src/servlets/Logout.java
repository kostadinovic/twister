package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.Login;
import javax.servlet.http.HttpServletRequest;


@SuppressWarnings("serial")
public class Logout extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
			String key = request.getParameter("key");
			response.setContentType("json");
			PrintWriter out = response.getWriter();
			JSONObject js = services.Logout.logout(key);
			out.println(js);
	}
}

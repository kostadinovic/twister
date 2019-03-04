package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("serial")
public class CreateUser extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String mail = request.getParameter("mail");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String age = request.getParameter("age");		
		response.setContentType("application/json");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject js = new JSONObject();
		js=services.User.createUser(nom, prenom, mail, login, password, age);
		out.println(js);
		out.flush();
	}
}

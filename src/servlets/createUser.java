package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("serial")
public class createUser extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String age = request.getParameter("age");
		String mail = request.getParameter("mail");
		
		response.setContentType("json");
		PrintWriter out= response.getWriter();
		out.println(services.CreateUser.createUser(nom,prenom,mail,login,password,age));
	}
	

}

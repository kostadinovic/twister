package services;

import org.json.*;

import tools.*;

public class CreateUser {
	
	public static JSONObject createUser(String nom, String prenom, String mail, String login, String password,String age) {
		JSONObject js = new JSONObject();
		if (nom == null || prenom == null || mail == null || login == null || password == null || age == null) {
			return ServiceTools.serviceRefused("Paramètre invalide", -1);
		}
		
		if (tools.BDTools.checkUserExist(login)) {
			return ServiceTools.serviceRefused("Login existe déja", -2);
		}
		
		if(!UserTools.checkSecurityPass(password)) {
			return tools.ServiceTools.serviceRefused("Le mots de passe est trop faible", -1);
		}
		
		if (UserTools.checkMailExist(mail)) {
			return ServiceTools.serviceRefused("L'adresse mail existe déja", -3);
		}
		
		if(Integer.parseInt(age)<16) {
			return tools.ServiceTools.serviceRefused("Age < 16", -1);
		}
		tools.BDTools.insertUserBD(nom,prenom,mail,login,password,age);
		
		js = tools.ServiceTools.serviceAccepted();
		return js;
	}
	
}

package services;

import org.json.JSONException;
import org.json.JSONObject;

import tools.ServiceTools;
import tools.UserTools;

public class User {
	
	
	public static JSONObject createUser(String nom, String prenom, String mail, String login, String password,String age) {
		JSONObject js = new JSONObject();
		if (nom == null || prenom == null || mail == null || login == null || password == null || age == null) {
			return ServiceTools.serviceRefused("Paramètre invalide", -1);
		}
		
		if (tools.UserTools.checkUserExist(login)) {
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
	
	
	public static JSONObject login(String login, String password, boolean root)
	{
		
		JSONObject obj = null;
		if(login == null || password == null)
		{
			obj = tools.ServiceTools.serviceRefused("Paramètre null", -1);
			
		}
		else if(!tools.UserTools.checkUserExist(login))
		{
			obj = tools.ServiceTools.serviceRefused("L'utilisateur n'existe pas",100);
		}
		else if(!tools.UserTools.verifyPass(login ,password))
		{
			obj = tools.ServiceTools.serviceRefused("Mots de passe incorrect",-1);
		}
		else
		{
			String key = ServiceTools.genKey();
			obj = new JSONObject();
			try {
				if(UserTools.addConnection(login, root))
				{
					obj.put("connexion", "ok ");
					obj.put("login", login);
					obj.put("key", key);
				}else
				{
					obj.put("connexion :", "échoué");
				}
				
				
			} catch (JSONException e) {
				e.printStackTrace();
				
			}
		}
		
		return obj;
		
	}
	
	public static JSONObject logout(String login, String key)
	{
		JSONObject obj = null;
		if(login == null || key == null)
		{
			obj = tools.ServiceTools.serviceRefused("Paramètre vide",-1);
			
		}
		else if(!tools.UserTools.checkUserExist(login))
		{
			obj = tools.ServiceTools.serviceRefused("L'utilisateur n'existe pas",100);
		}
		else if (tools.UserTools.removeConnection(key)) {
			try {
				obj = new JSONObject();
				obj.put("deconnection", "ok ");
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		else
		{
			try {
				obj = new JSONObject();
				obj.put("deconnection", "failed ");
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return obj;
		
	}
}

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
			return ServiceTools.serviceRefused("Login existe déja", 1000);
		}
		
		if(!UserTools.checkSecurityPass(password)) {
			return tools.ServiceTools.serviceRefused("Le mots de passe est trop faible", -1);
		}
		
		if (UserTools.checkMailExist(mail)) {
			return ServiceTools.serviceRefused("L'adresse mail existe déja", 1000);
		}
		if(!UserTools.verifierAge(age)) {
			return tools.ServiceTools.serviceRefused("Age incorrecte", -1);
		}
		js = tools.BDTools.insertUserBD(nom,prenom,mail,login,password,age);
		return js;
	}
	
	
	public static JSONObject login(String login, String password, boolean root){	
		JSONObject obj = null;
		if(login == null || password == null){
			obj = tools.ServiceTools.serviceRefused("Paramètre(s) null", -1);	
		}
		else if(!tools.UserTools.checkUserExist(login)){
			obj = tools.ServiceTools.serviceRefused("L'utilisateur n'existe pas",1000);
		}
		else if(!tools.UserTools.verifyPass(login ,password)){
			obj = tools.ServiceTools.serviceRefused("Mots de passe incorrect",-1);
		}
		else if(tools.UserTools.alreadyLog(login)) {
			obj = tools.ServiceTools.serviceRefused("Utilisateurs déja connecté",1000);
		}
		else{
			String key = ServiceTools.genKey();
			obj = new JSONObject();
			try {
				if(UserTools.addConnection(login, root, key)){
					obj.put("Connexion", "ok ");
					obj.put("Login", login);
					obj.put("Clé", key);
				}else{
					obj.put("Connexion :", "échoué");
				}	
			} catch (JSONException e) {
				e.printStackTrace();	
			}
		}	
		return obj;	
	}
	
	public static JSONObject logout(String login, String key){
		JSONObject obj = null;
		if(login == null || key == null){
			obj = tools.ServiceTools.serviceRefused("Paramètre vide",-1);	
		}
		else if(!tools.UserTools.checkUserExist(login)){
			obj = tools.ServiceTools.serviceRefused("L'utilisateur n'existe pas",1000);
		}
		else if (!tools.UserTools.keyLogin(login, key)) {
			return tools.ServiceTools.serviceRefused("Problème de session",1000);
		}
		else if (tools.UserTools.removeConnection(login)) {
			try {
				obj = new JSONObject();
				obj.put("Déconnexion", "ok");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		else{
			try {
				obj = new JSONObject();
				obj.put("Déconnexion", "échoué");	
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
	
	/* Fonction pour debugg
	public static void affmdp(String login, String mail, String age) {
		String pass = tools.UserTools.resetPassword(login, mail, age);
		System.out.println("voICI LE PASS" + pass);
	} 
	*/
	
	
	public static JSONObject lostPassword(String login, String mail, String age) {
		JSONObject obj = null;
		if(login == null || mail == null || age == null) {
			obj = tools.ServiceTools.serviceRefused("Paramètre vide",-1);
		}
		else if(!tools.UserTools.checkUserExist(login) || !tools.UserTools.checkMailExist(mail)) {
			obj = tools.ServiceTools.serviceRefused("L'utilisateur n'existe pas",1000);
		}
		String pass = tools.UserTools.resetPassword(login, mail, age);
		try {
			obj = new JSONObject();
			obj.put("Voici votre mot de passe, ne l'oublier pas", pass);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	
	public static JSONObject changePassword(String login, String newPassword,String key){
		JSONObject obj = null;
		if(login == null || key == null || newPassword == null){
			obj = tools.ServiceTools.serviceRefused("Paramètre vide",-1);
		}
		else if(!tools.UserTools.checkUserExist(login)){
			obj = tools.ServiceTools.serviceRefused("L'utilisateur n'existe pas",1000);
		}
		else if (!tools.UserTools.keyLogin(login, key) && !tools.UserTools.userRoot(login)) {
			obj = tools.ServiceTools.serviceRefused("La connexion n'est pas valide",1000);
		}
		boolean change_ok = tools.UserTools.changePass(login, newPassword);
		if(change_ok) {
			obj = new JSONObject();
			try {
				obj.put("Changement de mot de passe", "ok");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			}else {
			obj = new JSONObject();
			try {
				obj.put("Changement de mot de passe", "ok");
			} catch (JSONException e) {
				e.printStackTrace();
				}
			}
		return obj;
		}
	
	public static JSONObject removeUser(String login,String key){
		JSONObject obj = null;
		if(login == null || key == null){
			obj = tools.ServiceTools.serviceRefused("Paramètre vide",-1);
		}
		else if(!tools.UserTools.checkUserExist(login)){
			obj = tools.ServiceTools.serviceRefused("L'utilisateur n'existe pas",1000);
		}
		else if (!tools.UserTools.userRoot(tools.UserTools.getUserLogin(key))) {
			//System.out.println(!tools.UserTools.userRoot(tools.UserTools.getUserLogin(key)));
			if(!tools.UserTools.keyLogin(tools.UserTools.getUserLogin(key), key)) {
				//System.out.println(tools.UserTools.keyLogin(login, key));
				obj = tools.ServiceTools.serviceRefused("La connexion n'est pas valide",1000);
			}else {
				obj = tools.BDTools.removeUser(login);
			}
		}
		else {
			obj = tools.BDTools.removeUser(login);
		}
		return obj;
	}
}

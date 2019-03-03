package services;


import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import tools.BDTools;
import tools.ServiceTools;

public class Login {
	public static JSONObject login(String login,String password){
		JSONObject js = new JSONObject();
		try {
			
			if((login==null) || (password==null)) {
				return tools.ServiceTools.serviceRefused("Erreur arguments nul", -1);
			}
			
			if(!tools.BDTools.checkUserExist(login)) {
				return tools.ServiceTools.serviceRefused("L'utilisateurs "+login+" n'existe pas", 1000);
			}
			
			if(!tools.BDTools.checkUserPassword(login,password)) {
				return tools.ServiceTools.serviceRefused("Le mots de passe ne correspond pas à login", 1000);
			}
			
			int id_user = BDTools.getUserId(login);
			String key = BDTools.insertConnexion(id_user, false);
			
			js = tools.ServiceTools.serviceAccepted();
			js.put("key", key);
			
		} catch(SQLException e) {
			return ServiceTools.serviceRefused("SQL Exception", 1000);
		} catch(JSONException e) {
			return ServiceTools.serviceRefused("JSON Exception", 100);
		}
		return js;
	}
}

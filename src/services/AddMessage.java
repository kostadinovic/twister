package services;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.json.*;

import tools.BDTools;
import tools.MessageTools;

public class AddMessage {
	public static JSONObject addMessage(String login,String key, String Message) {
		JSONObject js=null;
	
		if(login == null || key == null || Message == null){
			js = tools.ServiceTools.serviceRefused("Paramètre vide",-1);
		}
		else if(!tools.BDTools.checkUserExist(login)){
			js = tools.ServiceTools.serviceRefused("L'utilisateur n'existe pas",2);
			}
		else if(!tools.BDTools.checkLoginConnexion(login, key)){
			js = tools.ServiceTools.serviceRefused("Session expiré",3);
			}
		else{
			int id = BDTools.getUserId(login);
			GregorianCalendar cal = new java.util.GregorianCalendar();
			cal.add(Calendar.HOUR, -1);
			Date d = cal.getTime();
			System.out.println(d);
			MessageTools.addMessage(id, login, Message, d);
			try {
				js = new JSONObject();
				js.put("Message ajouté : ", "ok ");
			} catch (JSONException e) {
				e.printStackTrace();
				}
			}
		return js;
		}
}
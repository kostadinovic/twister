package services;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.json.JSONException;
import org.json.JSONObject;

import tools.BDTools;
import tools.MessageTools;
import tools.ServiceTools;

public class Message {
	
	public static JSONObject addMessage(String login,String key, String message){
			JSONObject js = null;
			if(login == null || key == null || message == null){
				js = tools.ServiceTools.serviceRefused("Paramètre invalide",-1);	
			}
			else if(!tools.UserTools.checkUserExist(login)){
				js = tools.ServiceTools.serviceRefused("L'utilisateur n'existe pas",100);
			}
			else if (!tools.UserTools.keyLogin(login, key)) {
				return tools.ServiceTools.serviceRefused("Problème de session",1000);
			}
			else{
				int user_id = BDTools.getUserId(login);
				MessageTools.addMessage(user_id, login, message);
				try {
					js = new JSONObject();
					js.put("ajout du message", "ok ");
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return js;
		}
	
		public static JSONObject removeMessage(String login,String key, String messageId){
			JSONObject js = null;
			if(login == null || key == null || messageId == null){
				js = tools.ServiceTools.serviceRefused("Paramètre invalide",-1);
			}
			else if(!tools.UserTools.checkUserExist(login)){
				js = tools.ServiceTools.serviceRefused("L'utilisateur n'existe pas",100);
			}
			else if(!tools.UserTools.keyLogin(login, key)){
				js = tools.ServiceTools.serviceRefused("Problème de session",100);
			}	
			else{				
				MessageTools.deleteMessage(messageId);
				try {
					js = tools.ServiceTools.serviceAccepted();
					js.put("Message supprimé", "fini");	
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return js;	
		}
		
		public static JSONObject listMessages(String login, String key) {
			JSONObject js = null;
			if(login == null || key == null){
				js = tools.ServiceTools.serviceRefused("Paramètre invalide",-1);
			}
			else if(!tools.UserTools.checkUserExist(login)){
				js = tools.ServiceTools.serviceRefused("L'utilisateur n'existe pas",100);
			}
			else if(!tools.UserTools.getKey(login).equalsIgnoreCase(key)){ //verif ok
				js = tools.ServiceTools.serviceRefused("Problème de session",100);
			}	
			else{	
				int user_id = BDTools.getUserId(login);
				js = MessageTools.listMessages(user_id);
			}
			return js;	
		}

}

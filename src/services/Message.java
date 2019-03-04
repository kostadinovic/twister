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
	
	public static JSONObject addMessage(String monLogin,String key, String message){
			JSONObject js = null;
			if(monLogin == null || key == null || message == null){
				js = tools.ServiceTools.serviceRefused("Paramètre invalide",-1);	
			}
			else if(!tools.UserTools.checkUserExist(monLogin)){
				js = tools.ServiceTools.serviceRefused("L'utilisateur n'existe pas",100);
			}
			else if(!tools.UserTools.keyLogin(monLogin, key)){
				js = tools.ServiceTools.serviceRefused("Problème de session",100);
			}else{
				int user_id = BDTools.getUserId(monLogin);
				GregorianCalendar calendrier = new java.util.GregorianCalendar();
				calendrier.add(Calendar.HOUR, -1);
				Date date = calendrier.getTime();
				System.out.println(date);
				MessageTools.addMessage(user_id, monLogin, message, date);
				try {
					js = new JSONObject();
					js.put("ajout du message", "ok ");
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return js;	
		}
	
		public static JSONObject removeMessage(String monLogin,String key, String messageId){
			JSONObject js = null;
			if(monLogin == null || key == null || messageId == null){
				js = tools.ServiceTools.serviceRefused("Paramètre invalide",-1);
			}
			else if(!tools.UserTools.checkUserExist(monLogin)){
				js = tools.ServiceTools.serviceRefused("L'utilisateur n'existe pas",100);
			}
			else if(!tools.UserTools.keyLogin(monLogin, key)){
				js = tools.ServiceTools.serviceRefused("Problème de session",100);
			}
			
			/*else if(!tools.MessageTools.existsMessage(MessageId))
			{
				js = tools.ServiceTools.serviceRefused("Le message est introuvable",-1);
			}*/
			
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

}

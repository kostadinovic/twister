package services;

import org.json.JSONObject;

import tools.MessageTools;
import tools.ServiceTools;

public class RemoveMessage {
	public static JSONObject removeMessage(String key, int id_message) {
		if (key == null) 
			return ServiceTools.serviceRefused("Paramètre key vide", -1);
		if (id_message == -1) 
			return ServiceTools.serviceRefused("Le message est introuvable", -2);
		return null;
		
		//return MessageTools.deleteMessage(key, id_message);
	}
}
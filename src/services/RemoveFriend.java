package services;

import org.json.*;

import tools.FriendTools;
import tools.ServiceTools;

public class RemoveFriend {
	
	public static JSONObject removeFriend(String key, int id_friend) {
		JSONObject js = new JSONObject();
		if (key == null) {
			return ServiceTools.serviceRefused("Paramètre(s) vide(s)", -1);
		}
		boolean is_friend = FriendTools.alreadyFriend(key, id_friend);
		if (!is_friend) 
			return ServiceTools.serviceRefused("Pas amis", -2);
		
		js = FriendTools.deleteFriend(key, id_friend);
		return js;
	}
}


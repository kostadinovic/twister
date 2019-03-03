package services;

import org.json.JSONObject;

import tools.FriendTools;
import tools.ServiceTools;

public class ListFriends {
	public static JSONObject listFriends(String key, int id_user) {
		if (key == null)
			return ServiceTools.serviceRefused("Paramètre invalide", -1);
		if (id_user == -1) {
			return ServiceTools.serviceRefused("Le id est invalide", -2);
		}
		return FriendTools.listFriends(key, id_user);
	}
}


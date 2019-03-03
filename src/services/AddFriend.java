
package services;

import org.json.*;

import tools.FriendTools;
import tools.ServiceTools;

public class AddFriend {
	public static JSONObject addFriend(String key, int id_friend) {
		JSONObject js = new JSONObject();
		if (key == null)
			return ServiceTools.serviceRefused("Paramètre invalide", -1);

		boolean is_friend = FriendTools.alreadyFriend(key, id_friend);
		if (is_friend) {
			return ServiceTools.serviceRefused("Déja amis", -2);
		}
		js = FriendTools.insertFriend(key, id_friend);
		return js;
	}

}
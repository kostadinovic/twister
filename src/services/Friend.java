package services;

import org.json.JSONException;
import org.json.JSONObject;

public class Friend {
	public static JSONObject addFriend(String monLogin, String key, String friendLogin) {

		JSONObject js = null;
		if (monLogin == null || friendLogin == null || key == null) {
			js = tools.ServiceTools.serviceRefused("Parametre null",-1);

		} else if (!tools.UserTools.checkUserExist(monLogin) || !tools.UserTools.checkUserExist(friendLogin)) {
			js = tools.ServiceTools.serviceRefused("L'utilisateur n'existe pas",1);
		//} else if (!tools.UserTools.keyLogin(monLogin, key)) {
		//js = tools.ServiceTools.serviceRefused("Problème de session",-1);
		} else {
			js = tools.FriendTools.addFriend(monLogin, friendLogin);
		}
		return js;
	}

	public static JSONObject listFriends(String login) {
		JSONObject js = null;
		if (login == null) {
			js = tools.ServiceTools.serviceRefused("Paramètre null",-1);
		} else if (!tools.UserTools.checkUserExist(login)) {
			js = tools.ServiceTools.serviceRefused("L'utilisateurs n'existe pas",100);
		} else {
			js = tools.FriendTools.listFriends(login);
		}
		return js;
	}

	public static JSONObject removeFriend(String monLogin, String key, String friendLogin) {
		JSONObject js = null;
		if (monLogin == null || key == null || friendLogin == null) {
			js = tools.ServiceTools.serviceRefused("Paramètre null",-1);
		} else if (!tools.UserTools.checkUserExist(monLogin)) {
			js = tools.ServiceTools.serviceRefused("L'utilisateur n'existe pas",100);
		//} else if (!tools.UserTools.keyLogin(monLogin, key)) {
		//	js = tools.ServiceTools.serviceRefused("Pas le bon login/mdp",1000);
		}
		else if(!tools.FriendTools.alreadyFriend(monLogin, friendLogin))
		{
			js = tools.ServiceTools.serviceRefused("Ne sont pas amis",100);
		}
		else {
			tools.FriendTools.deleteFriend(monLogin,friendLogin);
			try {
				js = new JSONObject();
				js.put("suppression de l'ami", "ok ");

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return js;
	}
}

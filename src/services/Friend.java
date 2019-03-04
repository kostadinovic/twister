package services;

import org.json.JSONException;
import org.json.JSONObject;

public class Friend {
	public static JSONObject addFriend(String myLogin, String key, String hisLogin) {

		JSONObject obj = null;
		if (myLogin == null || hisLogin == null || key == null) {
			obj = tools.ServiceTools.serviceRefused("Parametre null",-1);

		} else if (!tools.UserTools.checkUserExist(myLogin) || !tools.UserTools.checkUserExist(hisLogin)) {
			obj = tools.ServiceTools.serviceRefused("L'utilisateur n'existe pas",1);
		} else if (!tools.UserTools.keyLogin(myLogin, key)) {
			obj = tools.ServiceTools.serviceRefused("Problème de session",-1);
		} else {
			obj = new JSONObject();
			try {
				if (tools.FriendTools.addfollowing(myLogin, hisLogin)) {

					obj.put("ajout de l'ami", "ok ");

				} else {
					obj.put("ajout de l'ami", "failed");
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return obj;

	}

	public static JSONObject listFriends(String login) {

		JSONObject obj = null;
		if (login == null) {
			obj = tools.ServiceTools.serviceRefused("Paramètre null",-1);

		} else if (!tools.UserTools.checkUserExist(login)) {
			obj = tools.ServiceTools.serviceRefused("L'utilisateurs n'existe pas",100);
		} else {

			try {
				obj = tools.FriendTools.listFriends(login);

				obj.put("amis ", "ok ");

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return obj;

	}

	public static JSONObject unFriend(String myLogin, String key, String hisLogin) {

		JSONObject obj = null;
		if (myLogin == null || hisLogin == null) {
			obj = tools.ServiceTools.serviceRefused("Paramètre null",-1);

		} else if (!tools.UserTools.checkUserExist(myLogin)) {
			obj = tools.ServiceTools.serviceRefused("L'utilisateur n'existe pas",100);
		} else if (!tools.UserTools.keyLogin(myLogin, key)) {
			obj = tools.ServiceTools.serviceRefused("Pas le bon login/mdp",1000);
		}
		else if(!tools.FriendTools.dejaAmis(myLogin, hisLogin))
		{
			obj = tools.ServiceTools.serviceRefused("Déja amis",100);
		}
		else {
			tools.FriendTools.unfollow(myLogin,hisLogin);
			try {
				obj = new JSONObject();
				obj.put("suppression de l'ami", "ok ");

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return obj;

	}

}

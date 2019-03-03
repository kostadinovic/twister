package services;

import org.json.*;

import tools.ServiceTools;
import tools.UserTools;

public class Logout {
	
	public static JSONObject logout(String key) {
		JSONObject js = new JSONObject();
		if (key == null) {
			return ServiceTools.serviceRefused("Paramètre invalide", -1);
		}
		UserTools.removeConnection(key);
		return js;
	}
}

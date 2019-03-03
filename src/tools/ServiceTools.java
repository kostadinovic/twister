package tools;

import org.json.JSONException;
import org.json.JSONObject;

public class ServiceTools {
	
	public static JSONObject serviceRefused(String message,int codeErreur){
		JSONObject json = new JSONObject();
		try {
			json.put("Message", message);
			json.put("CodeErreur",codeErreur);
			json.put("Status","KO");	
		} catch(JSONException e) {
			e.printStackTrace();
			return serviceRefused("JSONException", 100);
		}
		return json;
	}
	
	public static JSONObject serviceAccepted(){
		JSONObject json = new JSONObject();
		try {
			json.put("Status","OK");
			json.put("Output", new String("OK"));
		} catch(JSONException e) {
			e.printStackTrace();
			return serviceRefused("JSONException",100);
		}
		return json;
	}
}

package tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import bd.DataBase;
import services.User;

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
	
	public static boolean checkdate(String login, String key) {
		// TODO Auto-generated method stub
		
		boolean check = false;
		try {
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			String q = "SELECT * FROM Session WHERE login='" + login + "' and clef='"+ key +"'and date_exp+3600> now() ;";
			ResultSet rs = s.executeQuery(q);
			
			if (rs.next()) {
				check = true;
			}
			s.close();
			c.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		if(check)
		{
			//ServiceTools.updateExpiration(login);
		}
		else
		{
			User.logout(login, key);
		}
		
		return check;
	}
	
	public static String genKey() {
		String key="";
		String lettre ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		for(int i=0;i<32;i++) {
			int r = new Random().nextInt(lettre.length());
			key+=lettre.charAt((r));
		}
		return key;
		
	}
	
}

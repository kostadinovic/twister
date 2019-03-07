package tools;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import bd.DataBase;

public class FriendTools {
	
	public static JSONObject addFriend(String monLogin, String friendLogin) {
		Connection co = null;
		Statement st = null;
		try {
			co = DataBase.getMySQLConnection();
			st = co.createStatement();
			String query = "INSERT INTO Friends VALUES('" + monLogin + "','" + friendLogin + "','" + new Timestamp(System.currentTimeMillis()) + "')";
			st.executeUpdate(query);
			return tools.ServiceTools.serviceAccepted();
		} catch (SQLException s) {
			s.printStackTrace();
		} finally {
			try {
				st.close();
				co.close();
			} catch (SQLException ignore) {
				return ServiceTools.serviceRefused("SQLException", 1000);
			}
		}
		return ServiceTools.serviceRefused("Erreur lors de l'ajout de l'ami "+friendLogin, 1000);
	}

	public static JSONObject deleteFriend(String monLogin, String friendLogin) {
		try {
			Connection c = DataBase.getMySQLConnection();
			String query = "Delete from Friends where monLogin='" + monLogin + "' and friendLogin='" + friendLogin + "';";
			Statement s = c.createStatement();
			int rs = s.executeUpdate(query);
			s.close();
			c.close();
			return ServiceTools.serviceAccepted();
		} catch (Exception e) {
			return ServiceTools.serviceRefused("SQLException", 1000);
		}
	}
	
	public static boolean alreadyFriend(String monLogin, String loginFriend) {
		boolean check = false;
		try {
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			String q = "SELECT * FROM Friends WHERE  monLogin='" + monLogin + "' and friendLogin='" + loginFriend + "';";
			ResultSet rs = s.executeQuery(q);
			
			if (rs.next()) {
				check = true;
			}
			rs.close();
			s.close();
			c.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		
		return check;
	}

	public static JSONObject listFriends(String login) {
		JSONObject obj = new JSONObject();
		List<String> liste = new ArrayList<String>();
		try {
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			String q = "SELECT friendLogin FROM Friends WHERE monLogin='" + login + "';";
			ResultSet rs = s.executeQuery(q);
			while (rs.next()) {
				liste.add(rs.getString("friendLogin"));
			}
			obj.put("friends", liste);
			rs.close();
			s.close();
			c.close();

		} catch (SQLException e) {

		}catch(JSONException e) {
			
		}
		return obj;
	}
}

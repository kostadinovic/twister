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
	
	public static JSONObject  addFriend(String monLogin, String friendLogin) {
		Connection co = null;
		Statement st = null;
		
		try {
			co = DataBase.getMySQLConnection();
			st = co.createStatement();
			int id_user = UserTools.getIdUserByKey(key);
			String query = "INSERT INTO Friends VALUES('" + id_user + "','" + id_friend + "','" + new Timestamp(System.currentTimeMillis()) + "')";
			st.executeUpdate(query);
			return new JSONObject();
		} catch (SQLException s) {
			s.printStackTrace();
		} finally {
			try {
				st.close();
				co.close();
			} catch (SQLException ignore) {}
		}
		return ServiceTools.serviceRefused("SQLException", 1000);
	}
	
	public static JSONObject deleteFriend(String key, int id_friend) {
		Connection co = null; 
		Statement st = null;
		
		try {
			co = DataBase.getMySQLConnection();
			st = co.createStatement();
			int id_user = UserTools.getIdUserByKey(key);
			String query = "DELETE FROM Friends WHERE from_id =" + id_user
					+ " and to_id = " + id_friend;
			st.executeUpdate(query);
			
			return new JSONObject();
		} catch (SQLException s) {
			s.printStackTrace();
		} finally {
			try {
				st.close();
				co.close();
			} catch (SQLException ignore) {}
		}
		return ServiceTools.serviceRefused("SQLException", 1000);
	}
	
	public static boolean alreadyFriend(String monLogin, String loginFriend) {
		// TODO Auto-generated method stub
		boolean check = false;
		try {
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			String q = "SELECT * FROM Friends WHERE  from_login='" + monLogin + "' and to_login='" + loginFriend + "';";
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
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		List<String> liste = new ArrayList<String>();
		try {
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			String q = "SELECT to_login FROM Friends WHERE from_login='" + login + "';";
			ResultSet rs = s.executeQuery(q);
			while (rs.next()) {
				liste.add(rs.getString("to_login"));
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

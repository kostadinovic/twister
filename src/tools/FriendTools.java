package tools;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;

import bd.DataBase;

public class FriendTools {
	
	public static JSONObject  insertFriend(String key, int id_friend) {
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
	
	public static boolean alreadyFriend(String key, int id_friend) {
		Connection co = null;
		Statement st = null;
		ResultSet res = null;
		
		try {
			co = DataBase.getMySQLConnection();
			st = co.createStatement();
			int id_user = UserTools.getIdUserByKey(key);
			String query = "SELECT * FROM Friends WHERE from_id = " + id_user + " AND to_id = " + id_friend;
			res = st.executeQuery(query);
			if (res.next()) {
				return true;
			}
		} catch (SQLException s) {
			s.printStackTrace();
		} finally {
			try {
				res.close();
				st.close();
				co.close();
			} catch (SQLException ignore) {}
		}
		return false;
	}
	

	public static JSONObject listFriends(String key, int id_user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	



}

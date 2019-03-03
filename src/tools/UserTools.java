package tools;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import bd.DataBase;

public class UserTools {
	
	public static boolean checkSecurityPass(String password) {
		if(password.length() > 5) {
			return true;
		}
		return false;
	}
	
	
	public static void disconnectUser(String key) {
		// DECO USER DANS LA BD
	}

	public static boolean checkMail(String mail) {
		// TODO Auto-generated method stub
		return true;
	}

	public static void removeConnection(String key) {
		Connection co = null;
		Statement st = null;
		try {
			co = DataBase.getMySQLConnection();
			st = co.createStatement();
			String query = "DELETE from Connection where Connection.key_co ='"+key+"'";
			st.executeUpdate(query);
			
		} catch (SQLException s) {
			s.printStackTrace();
		} finally {
			try {
				st.close();
				co.close();
			} catch (SQLException ignore) {}
		}
}

	public static boolean checkMailExist(String mail) {
		// TODO Auto-generated method stub
		return false;
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
	
	public static String getKey(String login) {
		Connection co = null;
		Statement st = null;
		ResultSet res = null;
		String key = "";
		try {
			co = DataBase.getMySQLConnection();
			st = co.createStatement();
			String query = "SELECT Connection.key_co "
					+ "from Connection, users "
					+ "where users.login = '" + login + "' and users.id = Connection.id_user";
			res = st.executeQuery(query);
			if (res.next()) {
				key = res.getString("key_co");
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
		return key;
}


	public static int getIdUserByKey(String key) {
		// TODO Auto-generated method stub
		Connection co = null;
		Statement st = null;
		ResultSet res = null;
		int id_user = 0;
		try {
			co = DataBase.getMySQLConnection();
			st = co.createStatement();
			String query = "SELECT Connection.id_user from Connection where Connection.key_co = '" + key + "'";
			res = st.executeQuery(query);
			if (res.next()) {
				id_user = res.getInt("id_user");
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
		return id_user;
	}

}

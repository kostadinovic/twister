package tools;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import bd.DataBase;

public class BDTools {
	
	
	public static JSONObject insertUserBD(String nom, String prenom,String mail, String login,String password, String age) {
		JSONObject obj = new JSONObject();
		try {
			Connection c = DataBase.getMySQLConnection();
			String q = "Insert into users values(null, '" + nom + "','" + prenom + "', '" + mail + "', '" + login + "','" 
			+ password + "','" + age + "');";
			Statement s = c.createStatement();
			int rs = s.executeUpdate(q);
			s.close();
			c.close();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				obj.put("error", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			return obj;
		}
	}


	public static boolean checkUserExist(String login){
		boolean userExists = false;
		try {
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			String q = "SELECT * FROM users WHERE login='" + login + "';";
			ResultSet rs = s.executeQuery(q);
			userExists = false;
			if (rs.next()) {
				userExists = true;
			}
			rs.close();
			c.close();
			s.close();

		} catch (SQLException e) {
			System.out.println("Exception checkUserExist");

		}
		return userExists;
	}
	
	public static boolean checkUserPassword(String login,String password) throws SQLException {
		Connection co = DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		String query = "SELECT login, password from users where login = '" + login + "'"
					+ " and password = '" + password+ "'";
		ResultSet res = st.executeQuery(query);
		if (res.next()) {
			return true;
		}		
		res.close();
		st.close();
		co.close();
		return false;
	}

	public static int getUserId(String login) {
		Connection co = null;
		Statement st = null;
		ResultSet res = null;
		int id_user = 0;
		try {
			co = DataBase.getMySQLConnection();
			st = co.createStatement();
			String query = "SELECT id FROM users WHERE login = '" + login + "'";
			res = st.executeQuery(query);
			if (res.next()) {
				id_user = res.getInt("id");
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

	public static String insertConnexion(int id_user, boolean root) {
		Connection con = null;
		Statement stat = null;
		String key = tools.UserTools.genKey();
		
		try {
			con = DataBase.getMySQLConnection();
			stat = con.createStatement();
			String query = "INSERT INTO Connection VALUES('" + key + "','" +id_user+ "','" + new Timestamp(System.currentTimeMillis())
					+ "'," + root +")";
			stat.executeUpdate(query);
			
		} catch (SQLException s) {
			s.printStackTrace();
		} finally {
			try {
				stat.close();
				con.close();
			} catch (SQLException ignore) {}
		}
		return key;
	}

	public static boolean checkLoginConnexion(String login, String key) {
		boolean check = false;
		try {
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			String q = "SELECT * FROM COnnection WHERE login='" + login + "' and key='"+ key +"' ;";
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
			//check = BDTools.checkDate(login, key);
		}
		
		return check;
	} 
	
	/*
	
	public static boolean checkDate(String login, String key) {
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
			ServiceTools.updateExpiration(login);
		}
		else
		{
			User.logout(login, key);
		}
		
		return check;
	}
	
	*/
	

	
	
}

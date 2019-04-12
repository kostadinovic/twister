package tools;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.json.JSONException;
import org.json.JSONObject;

import bd.DataBase;

public class UserTools {
	
	public static JSONObject insertUserBD(String nom, String prenom,String mail, String login,String password, String age) {
		JSONObject obj = new JSONObject();
		try {
			Connection c = DataBase.getMySQLConnection();
			String q = "Insert into users values(null, '" + nom + "','" + prenom + "', '" + mail + "', '" + login + "','" 
			+ password + "','" + age + "');";
			Statement s = c.createStatement();
			s.executeUpdate(q);
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
	
	public static boolean checkSecurityPass(String password) {
		if(password.length() > 5) {
			return true;
		}
		return false;
	}
	
	public static boolean checkMail(String mail) {
		// TODO Auto-generated method stub
		return true;
	}


	public static boolean checkMailExist(String mail) {
		boolean mailExists = false;
		try {
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			String q = "SELECT * FROM users WHERE mail='" + mail + "';";
			ResultSet rs = s.executeQuery(q);
			mailExists = false;
			if (rs.next()) {
				mailExists = true;
			}
			rs.close();
			c.close();
			s.close();
		} catch (SQLException e) {
			System.out.println("Exception checkUserExist");

		}
		return mailExists;
	}
	
	public static String getKey(String login) {
		Connection co = null;
		Statement st = null;
		ResultSet res = null;
		String key = null;
		try {
			co = DataBase.getMySQLConnection();
			st = co.createStatement();
			String query = "SELECT key_co from Connection where login ='"+login+"'";
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

	public static boolean keyLogin(String monLogin, String key) {
		boolean check = false;
		try {
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			String q = "SELECT * FROM Connection WHERE login='" + monLogin + "' and key='"+ key +"' ;";
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
			check = ServiceTools.checkdate(monLogin, key);
		}
		
		return check;
	}


	public static boolean verifyPass(String login, String password) {
		// TODO Auto-generated method stub
		boolean goodPass = false;
		try {
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			String q = "SELECT login, password from users where login = '" + login + "'"
					+ " and password = '" + password+ "'";
			ResultSet rs = s.executeQuery(q);
			goodPass = false;
			if (rs.next()) {
				goodPass = true;
			}
			rs.close();
			s.close();
			c.close();

		} catch (SQLException e) {

		}
		return goodPass;
	}


	public static boolean addConnection(String id_user, boolean root) {
		Connection con = null;
		Statement stat = null;
		String key = tools.ServiceTools.genKey();
		
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
		return true;
		}
	

	public static boolean removeConnection(String login) {
		Connection co = null;
		Statement st = null;
		try {
			co = DataBase.getMySQLConnection();
			st = co.createStatement();
			String query = "DELETE from Connection where login ='"+login+"'";
			st.executeUpdate(query);
			
		} catch (SQLException s) {
			s.printStackTrace();
			return false;
		} finally {
			try {
				st.close();
				co.close();
			} catch (SQLException ignore) {}
		}
		return true;
	}
}

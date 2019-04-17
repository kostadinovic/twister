package tools;

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
			s.executeUpdate(q);
			s.close();
			c.close();
			obj = ServiceTools.serviceAccepted();
			return obj;
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

	public static int getUserId(String monLogin) {
		Connection co = null;
		Statement st = null;
		ResultSet res = null;
		int id_user = 0;
		try {
			co = DataBase.getMySQLConnection();
			st = co.createStatement();
			String query = "SELECT users.id from users where users.login = '" + monLogin + "'";
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

	public static JSONObject removeUser(String login) {
		JSONObject obj = null;
		Connection co = null;
		Statement st = null;
		try {
			co = DataBase.getMySQLConnection();
			st = co.createStatement();
			String query = "DELETE from users where login ='"+login+"'";
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return obj = ServiceTools.serviceRefused("Exception SQL", 1000);
		} finally {
			try {
				st.close();
				co.close();
			} catch (SQLException ignore) {}
		}
		UserTools.removeConnection(login);
		obj = ServiceTools.serviceAccepted();
		return obj;

	}

	public static JSONObject listUser(String login) {
		JSONObject obj = new JSONObject();
		Connection co = null;
		Statement st = null;
		String userlog = "lol";
		try {
			co = DataBase.getMySQLConnection();
			st = co.createStatement();
			String query = "SELECT login from users where login <>'"+login+"'";
			ResultSet rs = st.executeQuery(query);
			int i = 0;
			while(rs.next()) {
				userlog = rs.getString("login");
				obj.put("User"+i, userlog);	
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return obj = ServiceTools.serviceRefused("Exception SQL", 1000);
		} catch (JSONException e) {
			return obj = ServiceTools.serviceRefused("Exception JSON", 100);
		} finally {
			try {
				st.close();
				co.close();
			} catch (SQLException ignore) {}
		}
		return obj;
	}
	
	
	public static int nbUserCo() {
		Connection co = null;
		Statement st = null;
		ResultSet res = null;
		int nb = 0;
		try {
			co = DataBase.getMySQLConnection();
			st = co.createStatement();
			String query = "SELECT * from Connection";
			res = st.executeQuery(query);
			while(res.next()) {
				nb++;
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
		return nb;
	}
	
	public static int nbUser() {
		Connection co = null;
		Statement st = null;
		int nb = 0;
		try {
			co = DataBase.getMySQLConnection();
			st = co.createStatement();
			String query = "SELECT login from users";
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				nb++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				co.close();
			} catch (SQLException ignore) {}
		}
		return nb;
	}
}



package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataBase {
	private DataSource dataSource;
	private static DataBase dataBase;
	
	public DataBase(String jndiname) throws SQLException {
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:comp/env" + jndiname);
		} catch (NamingException e) {
			throw new SQLException(jndiname + " is missing JNDI! : "+e.getMessage());
		}
	}
		
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	

	public static Connection getMySQLConnection() throws SQLException{
		DataBase database = null; 
		if(DBStatic.mysql_pooling == true) {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return(DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + "/" + DBStatic.mysql_db, DBStatic.mysql_username, DBStatic.mysql_password));
		}else {
			if (database == null) {
				database = new DataBase("jdbc/db");
			}
			return database.getConnection();
		}
	}
}


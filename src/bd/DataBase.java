package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.bson.Document;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.net.UnknownHostException;


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
	
	public static Connection getMySQLConnection() throws SQLException {
		if (DBStatic.mysql_pooling == false) {
			return (Connection)(DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + "/"
					+DBStatic.mysql_db, DBStatic.mysql_username, DBStatic.mysql_password));
		} else {
			if (dataBase == null) {
				dataBase = new DataBase("jdbc/db");
			}
			return (dataBase.getConnection());
		} 
	}
	

	/*
	public static DBCollection getMyMongoDBConnection() throws UnknownHostException {
		Mongo m =  new Mongo(DBStatic.mongo_host, DBStatic.mongo_port);
		DB db = m.getDB(DBStatic.mongo_db);
		DBCollection coll = db.getCollection("comments");
			
		return coll;
	}
	*/

}


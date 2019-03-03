package test;

import java.util.GregorianCalendar;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import bd.DBStatic;

public class testMongoDB {
	
	public static void main(String[] args) {
	
		com.mongodb.client.MongoClient mongo = MongoClients.create();
		MongoDatabase mDB= mongo.getDatabase("mongo_db");
		MongoCollection<Document> message =mDB.getCollection("comments");
		
		Document query = new Document();
		query.append("user_id", "5");
		query.append("content", "un message");
		GregorianCalendar c = new GregorianCalendar();
		query.append("date", c.getTime());
		message.insertOne(query);

		ObjectId last_id = (ObjectId) query.get("_id");

		MongoCursor<Document> curs = message.find().iterator();
		while(curs.hasNext()) {
			Document o = curs.next();
			System.out.println(o);
		}
}
}

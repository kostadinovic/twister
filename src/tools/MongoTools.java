package tools;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoTools {
	public static MongoDatabase getMyMongoConnexion() {
		com.mongodb.client.MongoClient mongo = MongoClients.create();
		MongoDatabase bd = mongo.getDatabase("kostadinovic_benchara");
		return bd;
	}
}

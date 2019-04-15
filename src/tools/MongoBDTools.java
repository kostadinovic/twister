package tools;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoBDTools {
	
	public MongoBDTools() {}
	
	public static MongoDatabase getConnexionMongo() {
		com.mongodb.client.MongoClient mongo = MongoClients.create();
		MongoDatabase mongoBD = mongo.getDatabase("kostadinovic_benchara");
		return mongoBD;
	}		
}

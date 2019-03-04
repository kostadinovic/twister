package tools;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Date;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import tools.MongoTools;

public class MessageTools {
	
	public static boolean addMessage(int id, String login, String message, Date date) {
		MongoDatabase mb = MongoTools.getMyMongoConnexion();
		MongoCollection<Document> mongoc = mb.getCollection("Message");
		Document d = new Document();
		d.append("from_id", id);
		d.append("from_login", login);
		d.append("message", message);
		d.append("date", date);
		mongoc.insertOne(d);
		return true;
	}
	
	public static void deleteMessage(String id_message) {
		MongoDatabase bd = MongoTools.getMyMongoConnexion();
		MongoCollection<Document> mongoc = bd.getCollection("Message");
		Document d = new Document();
		d.append("_id", new ObjectId(id_message));
		mongoc.deleteOne(d);
	}

	public static JSONObject listMessages(int id) {
		JSONObject js = new JSONObject();
		try {
			MongoDatabase bd = MongoTools.getMyMongoConnexion();
			MongoCollection<Document> mongoc = bd.getCollection("Messages");
			Document d = new Document();
			d.append("from_id", id);
			MongoCursor<Document> cursor = mongoc.find(d).iterator();
			while (cursor.hasNext()) {
				Document dn = cursor.next();
				js.append(dn.getString("from_name"), dn.getString("message"));
				System.out.println(dn);
			}
		} catch (JSONException e) {
			e.getMessage();
		}
		return js;
	}	
} 


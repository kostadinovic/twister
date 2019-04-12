package tools;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Date;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import java.text.SimpleDateFormat;
import tools.MongoTools;

public class MessageTools {
	
	public static boolean addMessage(int id, String login, String message) {
		MongoDatabase mb = MongoTools.getMyMongoConnexion();
		MongoCollection<Document> mongoc = mb.getCollection("Message");
		String nowdate = now();
		Document doc = new Document();
		doc.append("from_id", id);
		doc.append("from_login", login);
		doc.append("message", message);
		doc.append("date", nowdate);
		mongoc.insertOne(doc);
		return !doc.isEmpty();
	}
	
	public static void deleteMessage(String id_message) {
		MongoDatabase bd = MongoTools.getMyMongoConnexion();
		MongoCollection<Document> mongoc = bd.getCollection("Message");
		Document doc = new Document();
		doc.append("_id", new ObjectId(id_message));
		mongoc.deleteOne(doc);
	}

	public static JSONObject listMessages(int id) {
		JSONObject js = new JSONObject();
		try {
			MongoDatabase bd = MongoTools.getMyMongoConnexion();
			MongoCollection<Document> mongoc = bd.getCollection("Message");
			Document d = new Document();
			d.append("from_id", id);
			MongoCursor<Document> cursor = mongoc.find(d).iterator();
			while (cursor.hasNext()) {
				Document dn = cursor.next();
				js.append(dn.getString("from_login"), dn.getString("message"));
				System.out.println(dn);
			}
		} catch (JSONException e) {
			e.getMessage();
		}
		return js;
	}	
	
	public static String now() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dateStr = format.format(new Date());
		return dateStr;
	}
}
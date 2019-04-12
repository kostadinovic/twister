package test;
import org.json.JSONObject;

import services.Message;
import tools.UserTools;
public class testMessage {
	
	public  static void main(String[] args) {
		//JSONObject js = services.Message.addMessage("neecak",UserTools.getKey("neecak"),"salut imane");
		//System.out.println(js);
		//boolean b = tools.MessageTools.addMessage(1, "nem", "sorry");
		//System.out.println(b);
		
		//JSONObject js = services.Message.removeMessage("neecak",UserTools.getKey("neecak"),"5cb0b386c2df6d7392ecdc3d");
		//System.out.println(js);
		
		JSONObject js = services.Message.listMessages("neecak",UserTools.getKey("neecak"));
		System.out.println(js);
	}

}

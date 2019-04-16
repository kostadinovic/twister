package test;
import org.json.JSONObject;

import services.Message;
import tools.UserTools;
public class testMessage {
	
	public  static void main(String[] args) {
		//JSONObject js = services.Message.addMessage("neecak",UserTools.getKey("neecak"),"salutToi");
		//System.out.println(js);
		
		//boolean b = tools.MessageTools.addMessage(1, "nem", "sorry");
		//System.out.println(b);
		
		//JSONObject js = services.Message.removeMessage("neecak",UserTools.getKey("neecak"),"5cb45d4eea0d8467f6a31cc5");
		//System.out.println(js);
		
		JSONObject js2 = services.Message.listMessages("neecak",UserTools.getKey("neecak"));
		System.out.println(js2);
	}
}

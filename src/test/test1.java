package test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import services.*;
import tools.ServiceTools;
import tools.UserTools;

public class test1 {
	
	public static void main(String[] args) {
		
		
		//JSONObject user1 = User.createUser("kostadinovic", "nemanja", "nemania-srb@li.fr", "neak", "123456789","22","123456789");
		//System.out.println(user1);
		//JSONObject user2 = User.createUser("benchara", "imane", "ben.imane@hotmail.fr", "imaneB", "123456789","23");
		//System.out.println(user2);
	
		
		//JSONObject user2 = User.createUser("benchra", "imne", "ben.imane@hotail.fr", "imane", "123456789","16");
		//System.out.println(user2);
		
		/* 
		 * http://localhost:8080/twister/CreateUser?nom=salut&prenom=fff&mail=ggg&login=hgghgg&password=123456852&age=99
		 */
		
		//boolean b = UserTools.alreadyLog("neecak");
		//System.out.println(b);
		
		/*
		JSONObject login1 = User.login("neecak", "123456789", true);
		System.out.println(login1);
		JSONObject login2 = User.login("imaneB", "123456789", false);
		System.out.println(login2);
		*/
		
		
		//JSONObject logout1 = User.logout("imaneB",UserTools.getKey("imaneB"));
		//System.out.println(logout1);
		
		//String s = UserTools.getKey("neecak");
		//System.out.println(s);
		
		//JSONObject friend = Friend.addFriend("neecak",UserTools.getKey("neecak"),"imaneB");
		//System.out.println(friend); 
		
		//System.out.println(ServiceTools.checkdate("neecak", UserTools.getKey("neecak")));
		
		
		//JSONObject friend = Friend.removeFriend("neecak",UserTools.getKey("neecak"),"imaneB");
		//System.out.println(friend);
		
		//JSONObject js = Friend.listFriends("neecak");
		//System.out.println(js);


		/* SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dateStr = format.format(new Date());
		System.out.println(dateStr); */
		
		//services.User.affmdp("neecak", "nemania-srb@live.fr", "22");
		
		//JSONObject js = User.lostPassword("neecak", "nemania-srb@live.fr", "22");
		//System.out.println(js);

		//JSONObject js = User.changePassword("neecak", "imane",UserTools.getKey("neecak"));
		//System.out.println(js);
		
		//JSONObject js = Friend.likeMessage(UserTools.getKey("neecak"),2345555);
		//System.out.println(js);
		
		//JSONObject js = Friend.unlikeMessage(UserTools.getKey("neecak"),2345555);
		//System.out.println(js);

		
		// TEST ROOT MDP
		
		//JSONObject login1 = User.login("imane", "123456789", false);
		//System.out.println(login1);
		
		//JSONObject js = User.changePassword("imaneB", "JADORE3I017",UserTools.getKey("neecak"));
		//System.out.println(js);
		
		//JSONObject js = User.lostPassword("imaneB", "ben.imane@hotmail.fr", "23");
		//System.out.println(js);
		
		//JSONObject js = User.removeUser("imaneB",UserTools.getKey("imaneB"));
		//System.out.println(js);
		
		// + removeLike ++ remove msg avec id
		
		JSONObject login1 = User.afficheProfil("neecak", UserTools.getKey("neecak"));
		System.out.println(login1);
		

	}
}

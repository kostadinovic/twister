	package test;

import org.json.JSONObject;

import tools.UserTools;

public class test1 {
	
	public static void main(String[] args) {
		//Création user
		
		//JSONObject js = services.createUser.createUser("imane","ben", "imane@hotmail.fr", "imane", "1235456789","22");
		//System.out.println(js);
		
		//JSONObject js2 = services.createUser.createUser("nem","kos", "kos@hotmail.fr", "nem", "123456789","22");
		//System.out.println(js2);
		
		//Login des users
		
		//JSONObject js3 = services.Login.login("nem", "123456789");
		//System.out.println(js3);
		
		//JSONObject js5 = services.Login.login("imane", "1235456789");
		//System.out.println(js3);
		
		//LogOut des users
		JSONObject js4 = services.Logout.logout(UserTools.getKey("nem"));
		System.out.println(js4);
		
		//addFriend
		
		//JSONObject jsm= services.AddFriend.addFriend(UserTools.getKey("nem"),tools.BDTools.getUserId("imane"));
		//System.out.println(jsm);
		
		//removeFriend
		//JSONObject js= services.RemoveFriend.removeFriend(UserTools.getKey("nem"),tools.BDTools.getUserId("imane"));
		//System.out.println(js);
		
		//System.out.println(UserTools.getKey("nem")+"id="+tools.BDTools.getUserId("imane"));
		
		
		
		
		
	}

}

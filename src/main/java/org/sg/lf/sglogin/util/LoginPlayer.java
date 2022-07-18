package org.sg.lf.sglogin.util;

import java.util.HashMap;
import java.util.Map;
import org.sg.lf.sglogin.entity.IsLogin;

public class LoginPlayer {
	private static Map<String,IsLogin> playerInfo;

	public static Map<String, IsLogin> getPlayerInfo() {
		if(playerInfo==null) {
			playerInfo=new HashMap<String,IsLogin>();
		}
		return playerInfo;
	}
	
	public static IsLogin getPlayerForOne(String username) {
		return LoginPlayer.playerInfo.get(username);
	}
	
	public static int getIpCount(String ip) {
		int i=0;
		for(IsLogin il : playerInfo.values()){
		    if(il.getIp().equals(ip)) {
		    	i=i+1;
		    }
		}
		return i;
	}

	public static void setPlayerInfo(String username,IsLogin isLogin) {
		LoginPlayer.playerInfo.put(username, isLogin);
	}

	public static void setPlayerInfo(String username) {
		LoginPlayer.playerInfo.remove(username);
	}
	
}

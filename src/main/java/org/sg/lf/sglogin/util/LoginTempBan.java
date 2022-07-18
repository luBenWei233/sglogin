package org.sg.lf.sglogin.util;

import java.util.HashMap;
import java.util.Map;

import org.sg.lf.sglogin.entity.TempBan;

public class LoginTempBan {
	private static Map<String,TempBan> tempBan;
	public static Map<String,TempBan> getTempBan(){
		if(tempBan==null) {
			tempBan=new HashMap<String,TempBan>();
		}
		return tempBan;
	}
	public static TempBan getTempBanForOne(String playerName){
		return tempBan.get(playerName);
	}
	public static void setTempBan(String playerName,TempBan tempBan) {
		LoginTempBan.tempBan.put(playerName, tempBan);
	}
	public static void setTempBan(String playerName) {
		TempBan tb=tempBan.get(playerName);
		if(tb.getErrorCount()==0) {
			LoginTempBan.tempBan.remove(playerName);
		}else {
			tb.setBanEndDate(null);
			tb.setBanStartDate(null);
			LoginTempBan.tempBan.put(playerName, tb);
		}
	}
	public static void removeTempBan(String playerName) {
		LoginTempBan.tempBan.remove(playerName);
	}
	public static void clearTempBan() {
		LoginTempBan.tempBan.clear();
	}
}

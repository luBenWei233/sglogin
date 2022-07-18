package org.sg.lf.sglogin.util;

import org.bukkit.plugin.Plugin;
import org.sg.lf.sglogin.SGLoginMain;

public class MainConfig {
	private static Plugin plugin=SGLoginMain.getPlugin(SGLoginMain.class);
	private static String cipherRegularity=plugin.getConfig().getString("cipherRegularity");
	private static Integer minPass=plugin.getConfig().getInt("minPass");
	private static Integer maxPass=plugin.getConfig().getInt("maxPass");
	private static String passerRoralert=plugin.getConfig().getString("passerRoralert");
	private static String alreadyLoggedin=plugin.getConfig().getString("alreadyLoggedin");
	private static String loginSucceeded=plugin.getConfig().getString("loginSucceeded");
	private static String loginPassError=plugin.getConfig().getString("loginPassError");
	private static Integer tempBanLoginAndIpForCount=plugin.getConfig().getInt("tempBanLoginAndIpForCount");
	private static Integer tempBanLoginAndIpForTime=plugin.getConfig().getInt("tempBanLoginAndIpForTime");
	private static Integer tempBanLoginAndIpForRefuse=plugin.getConfig().getInt("tempBanLoginAndIpForRefuse");
	private static String tempBanLoginAndIpForAlert=plugin.getConfig().getString("tempBanLoginAndIpForAlert");
	private static Integer loginInputErrorPassCount=plugin.getConfig().getInt("loginInputErrorPassCount");
	private static String loginInputErrorPassAlert=plugin.getConfig().getString("loginInputErrorPassAlert");
	private static String loginCommandError=plugin.getConfig().getString("loginCommandError");
	
	private static String registerSucceeded=plugin.getConfig().getString("registerSucceeded");
	private static Integer registerIpCount=plugin.getConfig().getInt("registerIpCount");
	private static String registerIpAlert=plugin.getConfig().getString("registerIpAlert");
	private static String registerError=plugin.getConfig().getString("registerError");
	private static String registerPassInconformity=plugin.getConfig().getString("registerPassInconformity");
	private static String registerFormatError=plugin.getConfig().getString("registerFormatError");
	
	private static String playerLoginAlert=plugin.getConfig().getString("playerLoginAlert");
	private static String playerRegisterAlert=plugin.getConfig().getString("playerRegisterAlert");
	private static Integer playerLoginTime=plugin.getConfig().getInt("playerLoginTime");
	private static Integer playerLoginCount=plugin.getConfig().getInt("playerLoginCount");
	
	private static Integer playerExitTime=plugin.getConfig().getInt("playerExitTime");
	private static Integer playerExitCount=plugin.getConfig().getInt("playerExitCount");
	private static String playerExitAlert=plugin.getConfig().getString("playerExitAlert");
	private static String playerNotLoginInputCommand=plugin.getConfig().getString("playerNotLoginInputCommand");
	
	private static String playerNotLoginAlert=plugin.getConfig().getString("playerNotLoginAlert");
	private static String playerIsOnLineAlert=plugin.getConfig().getString("playerIsOnLineAlert");
	private static Integer playerIpOnlineCount=plugin.getConfig().getInt("playerIpOnlineCount");
	private static String playerIpOnlineAlert=plugin.getConfig().getString("playerIpOnlineAlert");
	private static String playerIdenticalName=plugin.getConfig().getString("playerIdenticalName");
	private static String playerName=plugin.getConfig().getString("playerName");
	private static String playerNameAlert=plugin.getConfig().getString("playerNameAlert");
	
	private static Boolean playerJoinIpOn=plugin.getConfig().getBoolean("playerJoinIpOn");
	private static Integer playerJoinIpCount=plugin.getConfig().getInt("playerJoinIpCount");
	private static Integer playerJoinIpTime=plugin.getConfig().getInt("playerJoinIpTime");
	private static String playerJoinIpAlert=plugin.getConfig().getString("playerJoinIpAlert");
	
	private static String changePwdSuccessAlert=plugin.getConfig().getString("changePwdSuccessAlert");
	private static String changePwdFailAlert=plugin.getConfig().getString("changePwdFailAlert");
	private static String changePwdOldPwdError=plugin.getConfig().getString("changePwdOldPwdError");
	private static String changePwdFormatError=plugin.getConfig().getString("changePwdFormatError");
	
	private static boolean isplayerIpOnline=plugin.getConfig().getBoolean("isplayerIpOnline");
	private static boolean isRegister=plugin.getConfig().getBoolean("isRegister");
	private static String isRegisterAlert=plugin.getConfig().getString("isRegisterAlert");
	
	private static String notPlayer=plugin.getConfig().getString("notPlayer");
	public static String getCipherRegularity() {
		return cipherRegularity;
	}
	public static Integer getMinPass() {
		return minPass;
	}
	public static Integer getMaxPass() {
		return maxPass;
	}
	public static String getPasserRoralert() {
		return passerRoralert;
	}
	public static String getAlreadyLoggedin() {
		return alreadyLoggedin;
	}
	public static String getLoginSucceeded() {
		return loginSucceeded;
	}
	public static String getLoginPassError() {
		return loginPassError;
	}
	public static Integer getTempBanLoginAndIpForCount() {
		return tempBanLoginAndIpForCount;
	}
	public static Integer getTempBanLoginAndIpForTime() {
		return tempBanLoginAndIpForTime;
	}
	public static Integer getTempBanLoginAndIpForRefuse() {
		return tempBanLoginAndIpForRefuse;
	}
	public static String getTempBanLoginAndIpForAlert() {
		return tempBanLoginAndIpForAlert;
	}
	public static Integer getLoginInputErrorPassCount() {
		return loginInputErrorPassCount;
	}
	public static String getLoginInputErrorPassAlert() {
		return loginInputErrorPassAlert;
	}
	public static String getLoginCommandError() {
		return loginCommandError;
	}
	public static String getNotPlayer() {
		return notPlayer;
	}
	public static String getRegisterSucceeded() {
		return registerSucceeded;
	}
	public static Integer getRegisterIpCount() {
		return registerIpCount;
	}
	public static String getRegisterIpAlert() {
		return registerIpAlert;
	}
	public static String getRegisterError() {
		return registerError;
	}
	public static String getRegisterPassInconformity() {
		return registerPassInconformity;
	}
	public static String getRegisterFormatError() {
		return registerFormatError;
	}
	public static String getPlayerLoginAlert() {
		return playerLoginAlert;
	}
	public static String getPlayerRegisterAlert() {
		return playerRegisterAlert;
	}
	public static Integer getPlayerLoginTime() {
		return playerLoginTime;
	}
	public static Integer getPlayerLoginCount() {
		return playerLoginCount;
	}
	public static Integer getPlayerExitTime() {
		return playerExitTime;
	}
	public static Integer getPlayerExitCount() {
		return playerExitCount;
	}
	public static String getPlayerExitAlert() {
		return playerExitAlert;
	}
	public static String getPlayerNotLoginInputCommand() {
		return playerNotLoginInputCommand;
	}
	public static String getPlayerNotLoginAlert() {
		return playerNotLoginAlert;
	}
	public static String getPlayerIsOnLineAlert() {
		return playerIsOnLineAlert;
	}
	public static Integer getPlayerIpOnlineCount() {
		return playerIpOnlineCount;
	}
	public static String getPlayerIpOnlineAlert() {
		return playerIpOnlineAlert;
	}
	public static String getPlayerIdenticalName() {
		return playerIdenticalName;
	}
	public static String getPlayerName() {
		return playerName;
	}

	public static String getPlayerNameAlert() {
		return playerNameAlert;
	}
	public static Boolean getPlayerJoinIpOn() {
		return playerJoinIpOn;
	}
	public static Integer getPlayerJoinIpCount() {
		return playerJoinIpCount;
	}
	public static Integer getPlayerJoinIpTime() {
		return playerJoinIpTime;
	}
	public static String getPlayerJoinIpAlert() {
		return playerJoinIpAlert;
	}
	public static String getChangePwdSuccessAlert() {
		return changePwdSuccessAlert;
	}
	public static String getChangePwdFailAlert() {
		return changePwdFailAlert;
	}
	public static String getChangePwdOldPwdError() {
		return changePwdOldPwdError;
	}
	public static String getChangePwdFormatError() {
		return changePwdFormatError;
	}
	public static boolean isIsplayerIpOnline() {
		return isplayerIpOnline;
	}
	public static boolean isRegister() {
		return isRegister;
	}
	public static String getIsRegisterAlert() {
		return isRegisterAlert;
	}
	
	public static void reload() {
		plugin.reloadConfig();
		cipherRegularity=plugin.getConfig().getString("cipherRegularity");
		minPass=plugin.getConfig().getInt("minPass");
		maxPass=plugin.getConfig().getInt("maxPass");
		passerRoralert=plugin.getConfig().getString("passerRoralert");
		alreadyLoggedin=plugin.getConfig().getString("alreadyLoggedin");
		loginSucceeded=plugin.getConfig().getString("loginSucceeded");
		loginPassError=plugin.getConfig().getString("loginPassError");
		tempBanLoginAndIpForCount=plugin.getConfig().getInt("tempBanLoginAndIpForCount");
		tempBanLoginAndIpForTime=plugin.getConfig().getInt("tempBanLoginAndIpForTime");
		tempBanLoginAndIpForRefuse=plugin.getConfig().getInt("tempBanLoginAndIpForRefuse");
		tempBanLoginAndIpForAlert=plugin.getConfig().getString("tempBanLoginAndIpForAlert");
		loginInputErrorPassCount=plugin.getConfig().getInt("loginInputErrorPassCount");
		loginInputErrorPassAlert=plugin.getConfig().getString("loginInputErrorPassAlert");
		loginCommandError=plugin.getConfig().getString("loginCommandError");
		
		registerSucceeded=plugin.getConfig().getString("registerSucceeded");
		registerIpCount=plugin.getConfig().getInt("registerIpCount");
		registerIpAlert=plugin.getConfig().getString("registerIpAlert");
		registerError=plugin.getConfig().getString("registerError");
		registerPassInconformity=plugin.getConfig().getString("registerPassInconformity");
		registerFormatError=plugin.getConfig().getString("registerFormatError");
		
		playerLoginAlert=plugin.getConfig().getString("playerLoginAlert");
		playerRegisterAlert=plugin.getConfig().getString("playerRegisterAlert");
		playerLoginTime=plugin.getConfig().getInt("playerLoginTime");
		playerLoginCount=plugin.getConfig().getInt("playerLoginCount");
		
		playerExitTime=plugin.getConfig().getInt("playerExitTime");
		playerExitCount=plugin.getConfig().getInt("playerExitCount");
		playerExitAlert=plugin.getConfig().getString("playerExitAlert");
		playerNotLoginInputCommand=plugin.getConfig().getString("playerNotLoginInputCommand");
		
		playerNotLoginAlert=plugin.getConfig().getString("playerNotLoginAlert");
		playerIsOnLineAlert=plugin.getConfig().getString("playerIsOnLineAlert");
		playerIpOnlineCount=plugin.getConfig().getInt("playerIpOnlineCount");
		playerIpOnlineAlert=plugin.getConfig().getString("playerIpOnlineAlert");
		playerIdenticalName=plugin.getConfig().getString("playerIdenticalName");
		playerName=plugin.getConfig().getString("playerName");
		playerNameAlert=plugin.getConfig().getString("playerNameAlert");
		
		playerJoinIpOn=plugin.getConfig().getBoolean("playerJoinIpOn");
		playerJoinIpCount=plugin.getConfig().getInt("playerJoinIpCount");
		playerJoinIpTime=plugin.getConfig().getInt("playerJoinIpTime");
		playerJoinIpAlert=plugin.getConfig().getString("playerJoinIpAlert");
		
		changePwdSuccessAlert=plugin.getConfig().getString("changePwdSuccessAlert");
		changePwdFailAlert=plugin.getConfig().getString("changePwdFailAlert");
		changePwdOldPwdError=plugin.getConfig().getString("changePwdOldPwdError");
		changePwdFormatError=plugin.getConfig().getString("changePwdFormatError");
		
		isplayerIpOnline=plugin.getConfig().getBoolean("isplayerIpOnline");
		isRegister=plugin.getConfig().getBoolean("isRegister");
		isRegisterAlert=plugin.getConfig().getString("isRegisterAlert");
		
		notPlayer=plugin.getConfig().getString("notPlayer");
	}
}
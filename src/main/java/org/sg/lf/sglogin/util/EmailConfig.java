package org.sg.lf.sglogin.util;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.sg.lf.sglogin.SGLoginMain;

public class EmailConfig {
	private static Plugin plugin=SGLoginMain.getPlugin(SGLoginMain.class);
	private static FileConfiguration config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "email.yml"));
	private static Boolean enable=config.getBoolean("enable");
	private static String emailAccount=config.getString("emailAccount");
	private static String emailPwd=config.getString("emailPwd");
	private static String mailHost=config.getString("emailSmtpHost");
	private static String emailSmtpPort=config.getString("emailSmtpPort");
	private static Boolean sslAuthVerify=config.getBoolean("sslAuthVerify");
	private static Boolean enableDebug=config.getBoolean("enableDebug");
	private static String emailTheme=config.getString("emailTheme");
	private static String emailTitle=config.getString("emailTitle");
	private static String emailContent=config.getString("emailContent");
	
	private static Integer emailSendCount=config.getInt("emailSendCount");
	private static Integer emailSendTime=config.getInt("emailSendTime");
	private static String emailSendAlert=config.getString("emailSendAlert");
	private static String emailDisableAlert=config.getString("emailDisableAlert");
	private static String emailLoginSendAlert=config.getString("emailLoginSendAlert");
	private static Integer emailCodeOverdueTime=config.getInt("emailCodeOverdueTime");
	private static String emailCodeOverdueAlert=config.getString("emailCodeOverdueAlert");
	private static String emailSendSuccessAlert=config.getString("emailSendSuccessAlert");
	private static String emailSendFailAlert=config.getString("emailSendFailAlert");
	private static String emailInputErrorAlert=config.getString("emailInputErrorAlert");
	private static String emailNotRegAlert=config.getString("emailNotRegAlert");
	private static String emailInputCodeErrorAlert=config.getString("emailInputCodeErrorAlert");
	private static Integer emailInputCodeCount=config.getInt("emailInputCodeCount");
	private static String emailInputCodeKickAlert=config.getString("emailInputCodeKickAlert");
	private static String emailChangePwdSuccessAlert=config.getString("emailChangePwdSuccessAlert");
	private static String emailChangePwdFailAlert=config.getString("emailChangePwdFailAlert");
	private static String emailErrorCommand=config.getString("emailErrorCommand");
	private static String emailNotSendDoChangeAlert=config.getString("emailNotSendDoChangeAlert");
	private static Boolean emailIsAlert=config.getBoolean("emailIsAlert");
	private static String emailAlertContent=config.getString("emailAlertContent");
	
	public static String getMailHost() {
		return mailHost;
	}
	public static Boolean getEnable() {
		return enable;
	}
	public static String getEmailAccount() {
		return emailAccount;
	}
	public static String getEmailPwd() {
		return emailPwd;
	}
	public static String getEmailSmtpPort() {
		return emailSmtpPort;
	}
	public static Boolean getSslAuthVerify() {
		return sslAuthVerify;
	}
	public static Boolean getEnableDebug() {
		return enableDebug;
	}
	public static String getEmailTheme() {
		return emailTheme;
	}
	public static String getEmailTitle() {
		return emailTitle;
	}
	public static String getEmailContent() {
		return emailContent;
	}
	public static String getEmailDisableAlert() {
		return emailDisableAlert;
	}
	public static String getEmailLoginSendAlert() {
		return emailLoginSendAlert;
	}
	public static Integer getEmailCodeOverdueTime() {
		return emailCodeOverdueTime;
	}
	public static String getEmailCodeOverdueAlert() {
		return emailCodeOverdueAlert;
	}
	public static String getEmailSendSuccessAlert() {
		return emailSendSuccessAlert;
	}
	public static String getEmailSendFailAlert() {
		return emailSendFailAlert;
	}
	public static String getEmailInputErrorAlert() {
		return emailInputErrorAlert;
	}
	public static String getEmailNotRegAlert() {
		return emailNotRegAlert;
	}
	public static String getEmailInputCodeErrorAlert() {
		return emailInputCodeErrorAlert;
	}
	public static Integer getEmailInputCodeCount() {
		return emailInputCodeCount;
	}
	public static String getEmailInputCodeKickAlert() {
		return emailInputCodeKickAlert;
	}
	public static String getEmailChangePwdSuccessAlert() {
		return emailChangePwdSuccessAlert;
	}
	public static String getEmailChangePwdFailAlert() {
		return emailChangePwdFailAlert;
	}
	public static String getEmailErrorCommand() {
		return emailErrorCommand;
	}
	public static String getEmailNotSendDoChangeAlert() {
		return emailNotSendDoChangeAlert;
	}
	public static Integer getEmailSendCount() {
		return emailSendCount;
	}
	public static Integer getEmailSendTime() {
		return emailSendTime;
	}
	public static String getEmailSendAlert() {
		return emailSendAlert;
	}
	public static Boolean getEmailIsAlert() {
		return emailIsAlert;
	}
	public static String getEmailAlertContent() {
		return emailAlertContent;
	}
	
	public static void reload() {
		plugin.reloadConfig();
		config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "email.yml"));
		enable=config.getBoolean("enable");
		emailAccount=config.getString("emailAccount");
		emailPwd=config.getString("emailPwd");
		mailHost=config.getString("emailSmtpHost");
		emailSmtpPort=config.getString("emailSmtpPort");
		sslAuthVerify=config.getBoolean("sslAuthVerify");
		enableDebug=config.getBoolean("enableDebug");
		emailTheme=config.getString("emailTheme");
		emailTitle=config.getString("emailTitle");
		emailContent=config.getString("emailContent");
		
		emailSendCount=config.getInt("emailSendCount");
		emailSendTime=config.getInt("emailSendTime");
		emailSendAlert=config.getString("emailSendAlert");
		emailDisableAlert=config.getString("emailDisableAlert");
		emailLoginSendAlert=config.getString("emailLoginSendAlert");
		emailCodeOverdueTime=config.getInt("emailCodeOverdueTime");
		emailCodeOverdueAlert=config.getString("emailCodeOverdueAlert");
		emailSendSuccessAlert=config.getString("emailSendSuccessAlert");
		emailSendFailAlert=config.getString("emailSendFailAlert");
		emailInputErrorAlert=config.getString("emailInputErrorAlert");
		emailNotRegAlert=config.getString("emailNotRegAlert");
		emailInputCodeErrorAlert=config.getString("emailInputCodeErrorAlert");
		emailInputCodeCount=config.getInt("emailInputCodeCount");
		emailInputCodeKickAlert=config.getString("emailInputCodeKickAlert");
		emailChangePwdSuccessAlert=config.getString("emailChangePwdSuccessAlert");
		emailChangePwdFailAlert=config.getString("emailChangePwdFailAlert");
		emailErrorCommand=config.getString("emailErrorCommand");
		emailNotSendDoChangeAlert=config.getString("emailNotSendDoChangeAlert");
		//2.5更新 卢本伟 2021-03-15 15:14
		emailIsAlert=config.getBoolean("emailIsAlert");
		emailAlertContent=config.getString("emailAlertContent");
	}
	
}

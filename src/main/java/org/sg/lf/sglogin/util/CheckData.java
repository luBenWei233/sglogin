package org.sg.lf.sglogin.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckData {
	public static int CheckPwd(String pwd,String pwd2) {
		Pattern p = Pattern.compile(MainConfig.getCipherRegularity());
		Matcher matcher =p.matcher(pwd);
		if(matcher.matches()==false || pwd.length()>MainConfig.getMaxPass() || pwd.length()<MainConfig.getMinPass()) {
			//密码格式输错的提醒
			return 1;
		}
		if(pwd2!=null) {
			if(pwd.equals(pwd2)==false) {
				//两次密码输入不一致
				return 2;
			}
		}
		return 0;
	}
	public static int checkEmail(String email,String email2) {
		Pattern p = Pattern.compile("^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
		Matcher matcher =p.matcher(email);
		if(matcher.matches()==false || email.length()>50 || email.length()<7) {
			//邮箱格式输错的提醒
			return 1;
		}
		if(email2!=null) {
			if(email.equals(email2)==false) {
				//两次邮箱输入不一致
				return 2;
			}
		}
		return 0;
	}
}

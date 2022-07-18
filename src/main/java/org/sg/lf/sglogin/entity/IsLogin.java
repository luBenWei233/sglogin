package org.sg.lf.sglogin.entity;

import java.util.Date;

public class IsLogin {
	private String playerId;
	private boolean alreadyLogin=false;
	private String password;
	private int count=0;
	private String ip;
	private Date loginDate;
	public boolean getAlreadyLogin() {
		return alreadyLogin;
	}
	public void setAlreadyLogin(boolean alreadyLogin) {
		this.alreadyLogin = alreadyLogin;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
}

package org.sg.lf.sglogin.entity;

import java.util.Date;

public class TempBan {
	private String playerName;
	private String ip;
	private Date banStartDate;
	private Date banEndDate;
	private int errorCount=0;
	private String reason;
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getBanStartDate() {
		return banStartDate;
	}
	public void setBanStartDate(Date banStartDate) {
		this.banStartDate = banStartDate;
	}
	public Date getBanEndDate() {
		return banEndDate;
	}
	public void setBanEndDate(Date banEndDate) {
		this.banEndDate = banEndDate;
	}
	public int getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}

package org.sg.lf.sglogin.entity;

import java.util.Date;

/**
 * 注册记录
 * @author 帅哥
 * @date 2022-07-16 15:02
 */
public class IpLimit {
	private String playername;
	private String ip;
	private Date regdate;
	public String getPlayername() {
		return playername;
	}
	public void setPlayername(String playername) {
		this.playername = playername;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}

package org.sg.lf.sglogin.entity;

import java.util.Date;

/**
 * 玩家登陆日志
 * @author 帅哥
 * @date 2022-07-15 14:50
 */
public class PlayerLog {
	// 玩家登陆日志
	private String id;
	// 玩家id
	private String playerid;
	// ip
	private String ip;
	// 玩家登陆时间
	private Date logindate;
	// 玩家退出时间
	private Date exitdate;
	// 玩家单次在线时间，不需要太精准
	private Integer logintime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlayerid() {
		return playerid;
	}
	public void setPlayerid(String playerid) {
		this.playerid = playerid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getLogindate() {
		return logindate;
	}
	public void setLogindate(Date logindate) {
		this.logindate = logindate;
	}
	public Date getExitdate() {
		return exitdate;
	}
	public void setExitdate(Date exitdate) {
		this.exitdate = exitdate;
	}
	public Integer getLogintime() {
		return logintime;
	}
	public void setLogintime(Integer logintime) {
		this.logintime = logintime;
	}
}

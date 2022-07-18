package org.sg.lf.sglogin.entity;

import java.util.Date;

/**
 * 账号封禁
 * @author 帅哥
 * @date 2022-07-15 17:10
 */
public class PlayerBan {
	// 封禁时间，天时分秒
	private String bantime;
	// 封禁结束时间
	private Date enddate;
	// 处理人id
	private String handleid;
	private String handleidshow;
	// 玩家封禁主键
	private String id;
	// 玩家主键
	private String playerid;
	private String playername;
	private Date startdate;
	private Date createdate;
	private Integer reasonid;
	private String reasonidshow;
	public String getBantime() {
		return bantime;
	}
	public void setBantime(String bantime) {
		this.bantime = bantime;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public String getHandleid() {
		return handleid;
	}
	public void setHandleid(String handleid) {
		this.handleid = handleid;
	}
	public String getHandleidshow() {
		return handleidshow;
	}
	public void setHandleidshow(String handleidshow) {
		this.handleidshow = handleidshow;
	}
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
	public String getPlayername() {
		return playername;
	}
	public void setPlayername(String playername) {
		this.playername = playername;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Integer getReasonid() {
		return reasonid;
	}
	public void setReasonid(Integer reasonid) {
		this.reasonid = reasonid;
	}
	public String getReasonidshow() {
		return reasonidshow;
	}
	public void setReasonidshow(String reasonidshow) {
		this.reasonidshow = reasonidshow;
	}
}

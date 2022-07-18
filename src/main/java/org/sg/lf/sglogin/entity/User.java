package org.sg.lf.sglogin.entity;

import java.util.Date;

public class User {
	private String id;
	private String username;
	private String password;
	private String nickname;
	private String realName;
	private Date regDate;
	private String regDateShow;
	private String email;
	private Double x;
	private Double y;
	private Double z;
	private Float yaw;
	private Float pitch;
	private String world;
	private String realip;
	private Date lastDate;
	private String lastDateShow;
	private String lastip;
	private Date exitDate;
	private String exitDateShow;
	private Integer banif;
	private Date banstart;
	private String banstartShow;
	private Date banend;
	private String banendShow;
	private Integer online;
	private Integer adminif;
	private Integer countTime;
	private Integer banid;
	private String banreason;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWorld() {
		return world;
	}
	public void setWorld(String world) {
		this.world = world;
	}
	public Double getX() {
		return x;
	}
	public Double getY() {
		return y;
	}
	public Double getZ() {
		return z;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public void setY(Double y) {
		this.y = y;
	}
	public void setZ(Double z) {
		this.z = z;
	}
	public Float getYaw() {
		return yaw;
	}
	public Float getPitch() {
		return pitch;
	}
	public void setYaw(Float yaw) {
		this.yaw = yaw;
	}
	public void setPitch(Float pitch) {
		this.pitch = pitch;
	}
	public String getRealip() {
		return realip;
	}
	public void setRealip(String realip) {
		this.realip = realip;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	public String getLastip() {
		return lastip;
	}
	public void setLastip(String lastip) {
		this.lastip = lastip;
	}
	public Date getExitDate() {
		return exitDate;
	}
	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}
	public Integer getBanif() {
		return banif;
	}
	public void setBanif(Integer banif) {
		this.banif = banif;
	}
	public Date getBanstart() {
		return banstart;
	}
	public void setBanstart(Date banstart) {
		this.banstart = banstart;
	}
	public Date getBanend() {
		return banend;
	}
	public void setBanend(Date banend) {
		this.banend = banend;
	}
	public Integer getOnline() {
		return online;
	}
	public void setOnline(Integer online) {
		this.online = online;
	}
	public Integer getAdminif() {
		return adminif;
	}
	public void setAdminif(Integer adminif) {
		this.adminif = adminif;
	}
	public Integer getCountTime() {
		return countTime;
	}
	public void setCountTime(Integer countTime) {
		this.countTime = countTime;
	}
	public Integer getBanid() {
		return banid;
	}
	public void setBanid(Integer banid) {
		this.banid = banid;
	}
	public String getBanreason() {
		return banreason;
	}
	public void setBanreason(String banreason) {
		this.banreason = banreason;
	}
	public String getRegDateShow() {
		return regDateShow;
	}
	public void setRegDateShow(String regDateShow) {
		this.regDateShow = regDateShow;
	}
	public String getLastDateShow() {
		return lastDateShow;
	}
	public void setLastDateShow(String lastDateShow) {
		this.lastDateShow = lastDateShow;
	}
	public String getBanstartShow() {
		return banstartShow;
	}
	public void setBanstartShow(String banstartShow) {
		this.banstartShow = banstartShow;
	}
	public String getBanendShow() {
		return banendShow;
	}
	public void setBanendShow(String banendShow) {
		this.banendShow = banendShow;
	}
	public String getExitDateShow() {
		return exitDateShow;
	}
	public void setExitDateShow(String exitDateShow) {
		this.exitDateShow = exitDateShow;
	}
}

package org.sg.lf.sglogin.entity;

public class SysDataBook {
	// 创建人id
	private String createplayerid;
	// 字典表主键
	private Integer id;
	// 更新人
	private String updateplayerid;
	// 字典值
	private String value;
	public String getCreateplayerid() {
		return createplayerid;
	}
	public void setCreateplayerid(String createplayerid) {
		this.createplayerid = createplayerid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUpdateplayerid() {
		return updateplayerid;
	}
	public void setUpdateplayerid(String updateplayerid) {
		this.updateplayerid = updateplayerid;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}

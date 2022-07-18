package org.sg.lf.sglogin.dao;

import java.util.List;

import org.sg.lf.sglogin.entity.SysDataBook;

public interface SysDataBookDao {
	// 根据等差数列查找封禁理由ID，1、2、3
	public Integer getIdByLimit(int id);
	// 封禁理由
	public List<SysDataBook> getSysDataBookByList();
	// 新增封禁理由
	public int addSysDataBook(SysDataBook sysDataBook);
	// 修改封禁理由
	public int updateSysDataBook(SysDataBook sysDataBook);
	// 删除
	public int deleteSysDataBook(int id);
}

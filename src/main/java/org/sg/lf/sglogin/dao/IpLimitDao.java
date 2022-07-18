package org.sg.lf.sglogin.dao;

import org.sg.lf.sglogin.entity.IpLimit;

public interface IpLimitDao {
	public int insertIpLimit(IpLimit ipLimit);
	public int countIpLimit(String ip);
	public int deleteIpLimit();
}

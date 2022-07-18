package org.sg.lf.sglogin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.sg.lf.sglogin.dao.BaseDao;
import org.sg.lf.sglogin.dao.IpLimitDao;
import org.sg.lf.sglogin.entity.IpLimit;
import org.sg.lf.sglogin.util.DataBaseConfig;
import org.sg.lf.sglogin.util.DateUtil;

public class IpLimitImpl extends BaseDao implements IpLimitDao {

	@Override
	public int insertIpLimit(IpLimit ipLimit) {
		Connection con=getConnection();
		PreparedStatement pst=null;
		try {
			String sqlText="insert into ip_limit (playername,ip,regdate) values (?,?,?)";
			pst=con.prepareStatement(sqlText);
			pst.setString(1, ipLimit.getPlayername());
			pst.setString(2, ipLimit.getIp());
			pst.setString(3, DateUtil.getNowDate());
			return pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(con, pst, null);
		}
		return 0;
	}

	@Override
	public int countIpLimit(String ip) {
		Connection con=getConnection();
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			String sqlText=null;
			if("mysql".equals(DataBaseConfig.getDataName().toLowerCase())) {
				sqlText="select count(*) from ip_limit where ip=? and to_days(regdate) = to_days(now())";
			} else {
				sqlText="select count(*) from ip_limit where ip=? and strftime('%Y%m%d',substr(regdate,1,10),'unixepoch') = strftime('%Y%m%d',datetime('now','localtime'))";
			}
			pst=con.prepareStatement(sqlText);
			pst.setString(1,ip);
			rs=pst.executeQuery();
			rs.next();
			return rs.getInt(1);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(con, pst, rs);
		}
		return 0;
	}

	@Override
	public int deleteIpLimit() {
		Connection con=getConnection();
		PreparedStatement pst=null;
		try {
			String sqlText=null;
			if("mysql".equals(DataBaseConfig.getDataName().toLowerCase())) {
				sqlText="delete from ip_limit where DATE_FORMAT(regdate,'%Y%m%d')<=DATE_FORMAT(date_sub(now(),interval 1 day),'%Y%m%d')";
			} else {
				sqlText="delete from ip_limit where strftime('%Y%m%d',substr(regdate,1,10),'unixepoch')<=strftime('%Y%m%d',datetime('now','localtime','-24 hours'))";
			}
			pst=con.prepareStatement(sqlText);
			return pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(con, pst, null);
		}
		return 0;
	}

}

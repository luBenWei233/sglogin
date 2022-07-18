package org.sg.lf.sglogin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.sg.lf.sglogin.dao.BaseDao;
import org.sg.lf.sglogin.dao.SysDataBookDao;
import org.sg.lf.sglogin.entity.SysDataBook;
import org.sg.lf.sglogin.util.DataBaseConfig;
import org.sg.lf.sglogin.util.DateUtil;

public class SysDataBookImpl extends BaseDao implements SysDataBookDao {

	@Override
	public Integer getIdByLimit(int id) {
		Connection con=getConnection();
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			String sqlText="select id from sys_databook where groupid = 2 order by createdate asc limit ?,1";
			pst=con.prepareStatement(sqlText);
			pst.setInt(1, (id-1));
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
	public List<SysDataBook> getSysDataBookByList() {
		Connection con=getConnection();
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			String sqlText="select sys_databook.value from sys_databook where groupid = 2 order by createdate asc";
			pst=con.prepareStatement(sqlText);
			rs=pst.executeQuery();
			List<SysDataBook> sysDataBooks=new ArrayList<SysDataBook>();
			int i=1;
			while (rs.next()) {
				SysDataBook sysDataBook=new SysDataBook();
				sysDataBook.setId(i++);
				sysDataBook.setValue(rs.getString("value"));
				sysDataBooks.add(sysDataBook);
			}
			return sysDataBooks;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(con, pst, rs);
		}
		return null;
	}

	@Override
	public int addSysDataBook(SysDataBook sysDataBook) {
		Connection con=getConnection();
		PreparedStatement pst=null;
		try {
			String sqlText=null;
			if("mysql".equals(DataBaseConfig.getDataName().toLowerCase())) {
				sqlText="insert into sys_databook(groupid,sys_databook.value,valid,createplayerid,createdate)values(2,?,1,?,?)";
			}else {
				sqlText="insert into sys_databook(groupid,value,valid,createplayerid,createdate)values(2,?,1,?,?)";
			}
			pst=con.prepareStatement(sqlText);
			pst.setString(1, sysDataBook.getValue());
			pst.setString(2, sysDataBook.getCreateplayerid());
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
	public int updateSysDataBook(SysDataBook sysDataBook) {
		Connection con=getConnection();
		PreparedStatement pst=null;
		try {
			String sqlText=null;
			if("mysql".equals(DataBaseConfig.getDataName().toLowerCase())) {
				sqlText="update sys_databook set sys_databook.value=?,updateplayerid=?,updatedate=? where id=?";
			}else {
				sqlText="update sys_databook set value=?,updateplayerid=?,updatedate=? where id=?";
			}
			pst=con.prepareStatement(sqlText);
			pst.setString(1, sysDataBook.getValue());
			pst.setString(2, sysDataBook.getUpdateplayerid());
			pst.setString(3, DateUtil.getNowDate());
			pst.setInt(4, sysDataBook.getId());
			return pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(con, pst, null);
		}
		return 0;
	}

	@Override
	public int deleteSysDataBook(int id) {
		Connection con=getConnection();
		PreparedStatement pst=null;
		try {
			String sqlText="delete from sys_databook where id=?";
			pst=con.prepareStatement(sqlText);
			pst.setInt(1,id);
			return pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(con, pst, null);
		}
		return 0;
	}

}

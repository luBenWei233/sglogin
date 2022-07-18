package org.sg.lf.sglogin.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.sg.lf.sglogin.dao.BaseDao;

public class CheckDataBase extends BaseDao{
	public boolean getTable() {
		Connection con=getConnection();
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			String sqlText="SHOW TABLES LIKE 'player'";
			pst=con.prepareStatement(sqlText);
			rs=pst.executeQuery();
				System.out.println(rs.next());
				if(rs.next()) {
					return true;
				}else {
					//生成mysql表和数据
					for(String dataTable:MyFileUtil.readTextFileToString(this.getClass().getResourceAsStream("/database.sql")).split("#line#")) {
						createTableOrData(dataTable);
					}
					for(String data:MyFileUtil.readTextFileToList(this.getClass().getResourceAsStream("/insert.sql"))) {
						createTableOrData(data);
					}
					return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.closeAll(con, pst, rs);
		}
		return false;
	}
	
	private void createTableOrData(String sqlText) {
		Connection con=getConnection();
		PreparedStatement pst=null;
		try {
			pst=con.prepareStatement(sqlText);
			pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(con, pst, null);
		}
	}
}

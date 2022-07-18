package org.sg.lf.sglogin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

import org.sg.lf.sglogin.dao.BaseDao;
import org.sg.lf.sglogin.dao.PlayerLogDao;
import org.sg.lf.sglogin.entity.PlayerLog;
import org.sg.lf.sglogin.util.DateUtil;

public class PlayerLogImpl extends BaseDao implements PlayerLogDao {

	@Override
	public int insertPlayerLog(PlayerLog playerLog) {
		Connection con=getConnection();
		PreparedStatement pst=null;
		try {
			String sqlText="insert into player_log(id,playerid,ip,logindate,exitdate,logintime)values(?,?,?,?,?,?)";
			pst=con.prepareStatement(sqlText);
			pst.setString(1, UUID.randomUUID().toString());
			pst.setString(2, playerLog.getPlayerid());
			pst.setString(3, playerLog.getIp());
			pst.setString(4, DateUtil.dateToSaveDate(playerLog.getLogindate()));
			pst.setString(5, DateUtil.dateToSaveDate(playerLog.getExitdate()));
			pst.setInt(6, playerLog.getLogintime());
			return pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(con, pst, null);
		}
		return 0;
	}

}

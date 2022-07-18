package org.sg.lf.sglogin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

import org.sg.lf.sglogin.dao.BaseDao;
import org.sg.lf.sglogin.dao.PlayerBanDao;
import org.sg.lf.sglogin.entity.PlayerBan;
import org.sg.lf.sglogin.util.DateUtil;

public class PlayerBanImpl extends BaseDao implements PlayerBanDao {

	@Override
	public int insertPlayerBan(PlayerBan playerBan) {
		Connection con=getConnection();
		PreparedStatement pst=null;
		try {
			String sqlText="insert into player_ban(id,playerid,handleid,startdate,enddate,bantime,reasonid,createdate)values"
					+ "(?,(select id from player where username=?),?,?,?,?,?,?)";
			pst=con.prepareStatement(sqlText);
			pst.setString(1, UUID.randomUUID().toString());
			pst.setString(2, playerBan.getPlayername().toLowerCase());
			pst.setString(3, playerBan.getHandleid());
			pst.setString(4, DateUtil.dateToSaveDate(playerBan.getStartdate()));
			pst.setString(5, DateUtil.dateToSaveDate(playerBan.getEnddate()));
			pst.setString(6, playerBan.getBantime());
			pst.setInt(7, playerBan.getReasonid());
			pst.setString(8, DateUtil.dateToSaveDate(playerBan.getCreatedate()));
			return pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(con, pst, null);
		}
		return 0;
	}

}

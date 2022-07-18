package org.sg.lf.sglogin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
import org.sg.lf.sglogin.dao.BaseDao;
import org.sg.lf.sglogin.dao.SGLoginDao;
import org.sg.lf.sglogin.entity.User;
import org.sg.lf.sglogin.util.DateUtil;
import org.sg.lf.sglogin.util.LocaConfig;

public class SGLoginImpl extends BaseDao implements SGLoginDao{

	@Override
	public int register(User user) {
		Connection con=getConnection();
		PreparedStatement pst=null;
		try {
			String sqlText="insert into player(id,username,password,realname,regDate,realip,banif,online,adminif,countTime)values(?,?,?,?,?,?,?,?,?,?)";
			pst=con.prepareStatement(sqlText);
			if(user.getId()!=null) {
				pst.setString(1, user.getId());
			} else {
				pst.setString(1, UUID.randomUUID().toString());
			}
			pst.setString(2, user.getUsername());
			pst.setString(3, user.getPassword());
			pst.setString(4, user.getRealName());
			pst.setString(5, DateUtil.getNowDate());
			pst.setString(6, user.getRealip());
			pst.setInt(7, 0);
			pst.setInt(8, user.getOnline());
			pst.setInt(9, 0);
			pst.setInt(10, 0);
			return pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(con, pst, null);
		}
		return 0;
	}

	@Override
	public User login(User user) {
		User auser=null;
		Connection con=getConnection();
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			String sqlText="";
			if(LocaConfig.getPlayerLoca()==true) {
				sqlText="select p.id,p.username,p.password,p.realname,p.email,p.x,p.y,p.z,p.yaw,p.pitch,p.world,p.banend,p.banif,s.value banreason from player p left join sys_databook s on p.banid = s.id where username=?";
			}else {
				sqlText="select p.id,p.username,p.password,p.realname,p.email,p.banend,p.banif,s.value banreason from player p left join sys_databook s on p.banid = s.id where username=?";
			}
			pst=con.prepareStatement(sqlText);
			pst.setString(1, user.getUsername());
			rs=pst.executeQuery();
			while (rs.next()) {
				auser=new User();
				auser.setId(rs.getString("id"));
				auser.setUsername(rs.getString("username"));
				auser.setRealName(rs.getString("realname"));
				auser.setEmail(rs.getString("email"));
				auser.setBanend(DateUtil.dateToSaveDate(rs.getString("banend")));
				auser.setBanreason(rs.getString("banreason"));
				auser.setBanif(rs.getInt("banif"));
				if(LocaConfig.getEnableLoginLoca()==true) {
					auser.setX(rs.getDouble("x"));
					auser.setY(rs.getDouble("y"));
					auser.setZ(rs.getDouble("z"));
					auser.setYaw(rs.getFloat("yaw"));
					auser.setPitch(rs.getFloat("pitch"));
					auser.setWorld(rs.getString("world"));
				}
				if(user.getPassword()!=null) {
					if(!user.getPassword().equals(rs.getString("password"))) {
						return null;
					}
				}
			}
			return auser;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(con, pst, rs);
		}
		return null;
	}

	@Override
	public int isExistUser(User user) {
		Connection con=getConnection();
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			String sqlText="select count(*) from player where username=?";
			pst=con.prepareStatement(sqlText);
			pst.setString(1, user.getUsername());
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
	public int updateUser(User user) {
		Connection con=getConnection();
		PreparedStatement pst=null;
		try {
			StringBuffer sqlText=new StringBuffer();
			sqlText.append("UPDATE player SET ");
			if(user.getPassword()!=null) {
				sqlText.append("password=? ");
			}
			if(user.getEmail()!=null) {
				sqlText.append("email=? ");
			}
			if(user.getX()!=null) {
				sqlText.append("x=?,y=?,z=?,yaw=?,pitch=?,world=?,lastDate=?,lastip=?,exitDate=?,online=0,countTime=countTime+? ");
			}
			if(user.getOnline()!=null && user.getOnline()==1) {
				sqlText.append("online=1 ");
			}
			sqlText.append("WHERE username=?");
//			System.out.println("玩家更新："+sqlText.toString());
			pst=con.prepareStatement(sqlText.toString());
			int index=1;
			if(user.getPassword()!=null) {
				pst.setString(index, user.getPassword());
				index=index+1;
			}
			if(user.getEmail()!=null) {
				pst.setString(index, user.getEmail());
				index=index+1;
			}
			if(user.getX()!=null) {
				pst.setDouble(index, user.getX());
				index=index+1;
				pst.setDouble(index, user.getY());
				index=index+1;
				pst.setDouble(index, user.getZ());
				index=index+1;
				pst.setFloat(index, user.getYaw());
				index=index+1;
				pst.setFloat(index, user.getPitch());
				index=index+1;
				pst.setString(index, user.getWorld());
				index=index+1;
				pst.setString(index,DateUtil.dateToSaveDate(user.getLastDate()));
				index=index+1;
				pst.setString(index,user.getLastip());
				index=index+1;
				pst.setString(index,DateUtil.dateToSaveDate(user.getExitDate()));
				index=index+1;
				pst.setInt(index,user.getCountTime());
				index=index+1;
			}
			pst.setString(index, user.getUsername());
			return pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.closeAll(con, pst, null);
		}
		return 0;
	}

	@Override
	public int removeUser(User user) {
		Connection con=getConnection();
		PreparedStatement pst=null;
		try {
			String sqlText="delete from player where username=?";
			pst=con.prepareStatement(sqlText);
			pst.setString(1, user.getUsername());
			return pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(con, pst, null);
		}
		return 0;
	}

	@Override
	public int updateUserForBan(User user) {
		Connection con=getConnection();
		PreparedStatement pst=null;
		try {
			String sqlText="update player set banif=?,banstart=?,banend=?,banid=? where username=?";
			pst=con.prepareStatement(sqlText);
			pst.setInt(1, user.getBanif());
			pst.setString(2,DateUtil.dateToSaveDate(user.getBanstart()));
			pst.setString(3,DateUtil.dateToSaveDate(user.getBanend()));
			pst.setInt(4, user.getBanid());
			pst.setString(5, user.getUsername().toLowerCase());
			return pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(con, pst, null);
		}
		return 0;
	}

	@Override
	public User userInfo(String username) {
		User auser=null;
		Connection con=getConnection();
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			String sqlText="select p.username,p.realname,p.email,p.realip,p.regDate"
					+ ",p.lastDate,p.lastip,p.exitDate,p.banif,p.banstart,p.banend,p.adminif,p.countTime,s.value banreason "
					+ "from player p left join sys_databook s on p.banid = s.id "
					+ "where p.username=?";
			pst=con.prepareStatement(sqlText);
			pst.setString(1,username);
			rs=pst.executeQuery();
			while (rs.next()) {
				auser=new User();
				auser.setUsername(rs.getString("username"));
				auser.setRealName(rs.getString("realname"));
				auser.setEmail(rs.getString("email"));
				auser.setRealip(rs.getString("realip"));
				auser.setRegDateShow(DateUtil.getDateShow(rs.getString("regDate")));
				auser.setLastDateShow(DateUtil.getDateShow(rs.getString("lastDate")));
				auser.setLastip(rs.getString("lastip"));
				auser.setExitDateShow(DateUtil.getDateShow(rs.getString("exitDate")));
				auser.setBanif(rs.getInt("banif"));
				auser.setBanstartShow(DateUtil.getDateShow(rs.getString("banstart")));
				auser.setBanendShow(DateUtil.getDateShow(rs.getString("banend")));
				auser.setAdminif(rs.getInt("adminif"));
				auser.setCountTime(rs.getInt("countTime"));
				auser.setBanreason(rs.getString("banreason"));
			}
			return auser;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(con, pst, rs);
		}
		return null;
	}

	@Override
	public int unUserForBan(String username) {
		Connection con=getConnection();
		PreparedStatement pst=null;
		try {
			String sqlText="update player set banif=0,banstart=null,banend=null,banid=null where username=?";
			pst=con.prepareStatement(sqlText);
			pst.setString(1, username.toLowerCase());
			return pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(con, pst, null);
		}
		return 0;
	}

	@Override
	public int initUserState() {
		Connection con=getConnection();
		PreparedStatement pst=null;
		try {
			String sqlText="update player set online=0 where online=1";
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

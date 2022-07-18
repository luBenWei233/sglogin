package org.sg.lf.sglogin.event;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;
import org.sg.lf.sglogin.dao.impl.SGLoginImpl;
import org.sg.lf.sglogin.entity.TempBan;
import org.sg.lf.sglogin.entity.User;
import org.sg.lf.sglogin.util.DateUtil;
import org.sg.lf.sglogin.util.LoginPlayer;
import org.sg.lf.sglogin.util.LoginTempBan;
import org.sg.lf.sglogin.util.MainConfig;
public class SGPlayerTryLogin implements Listener{
	
	//玩家尝试登陆时
	@EventHandler
	public void asyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent event) {
		String addressIP=event.getAddress().getHostAddress();
		String nameAndIp=event.getName()+addressIP;
		TempBan tb=LoginTempBan.getTempBanForOne(nameAndIp);
		//玩家密码输入错误
		if(tb!=null) {
			if(tb.getBanEndDate()!=null) {
				if(tb.getPlayerName().equals(nameAndIp)) {
					if(new Date().compareTo(tb.getBanEndDate())>=0) {
						LoginTempBan.setTempBan(nameAndIp);
						tb.setPlayerName(nameAndIp);
						tb.setIp(addressIP);
						LoginTempBan.setTempBan(nameAndIp,tb);
					} else {
						event.disallow(Result.KICK_OTHER, tb.getReason());
					}
				}
			}
		} else {
			tb=new TempBan();
			tb.setPlayerName(nameAndIp);
			tb.setIp(addressIP);
			LoginTempBan.setTempBan(nameAndIp,tb);
		}
		
		//每日注册过多
		TempBan reg=LoginTempBan.getTempBanForOne(addressIP);
		if(reg!=null) {
			if(reg.getBanEndDate()!=null) {
				User user=new User();
				user.setUsername(event.getName());
				if(new SGLoginImpl().isExistUser(user)<=0) {
					if(new Date().compareTo(reg.getBanEndDate())>=0) {
						LoginTempBan.removeTempBan(addressIP);
					}else {
						event.disallow(Result.KICK_OTHER, reg.getReason());
					}
				}
			}
		}
		
		//登陆次数过快过多
		TempBan exit=LoginTempBan.getTempBanForOne("l"+nameAndIp);
		if(exit!=null) {
			if(exit.getBanEndDate()!=null) {
				if(new Date().compareTo(exit.getBanEndDate())>=0) {
					LoginTempBan.removeTempBan("l"+nameAndIp);
				}else {
					event.disallow(Result.KICK_OTHER, exit.getReason());
				}
			}
		}
		
		//两个一样的ID不能登陆
		Player onLinePlayer=Bukkit.getPlayer(event.getName());
//		IsLogin isLogin=LoginPlayer.getPlayerForOne(event.getName());
		if(onLinePlayer!=null) {
			//游戏中已经存在该玩家，但其他人继续登陆
			event.disallow(Result.KICK_OTHER, MainConfig.getPlayerIsOnLineAlert());
		}
		
		//每个IP的最大登陆数
		if(MainConfig.isIsplayerIpOnline()==true) {
			if(LoginPlayer.getIpCount(addressIP)>=MainConfig.getPlayerIpOnlineCount()) {
				//每个IP的最大同时登陆数
				event.disallow(Result.KICK_OTHER, MainConfig.getPlayerIpOnlineAlert());
			}
		}
		
		//两个大小写不一样的帐号，禁止它登陆
		User user=new User();
		user.setUsername(event.getName().toLowerCase());
		user=new SGLoginImpl().login(user);
		if(user!=null) {
			if(user.getRealName().equals(event.getName())==false) {
				String alert=MainConfig.getPlayerIdenticalName();
				if(alert.contains("[orderName]")) {
					alert=alert.replace("[orderName]", user.getRealName());
				}
				if(alert.contains("[playerName]")) {
					alert=alert.replace("[playerName]", event.getName());
				}
				event.disallow(Result.KICK_OTHER, alert);
			}
		}
		
		//不合法的帐号不能登陆
		Pattern p = Pattern.compile(MainConfig.getPlayerName());
		Matcher matcher =p.matcher(event.getName());
		if(matcher.matches()==false) {
			event.disallow(Result.KICK_OTHER, MainConfig.getPlayerNameAlert());
		}
		
		//每个IP加入退出游戏的次数，在规定时间内
		//key:ip127.0.0.1  是否开启IP限制
		if(MainConfig.getPlayerJoinIpOn()==true) {
			String ipAddress="ip"+addressIP;
			System.out.println(ipAddress);
			TempBan ipJoin=LoginTempBan.getTempBanForOne(ipAddress);
			if(ipJoin!=null) {
				//规定时间内，IP登陆的次数
				if(ipJoin.getErrorCount()>=MainConfig.getPlayerJoinIpCount()) {
					if(ipJoin.getBanEndDate()!=null) {
						if(new Date().compareTo(ipJoin.getBanEndDate())<=0) {
							//规定时间内，IP登陆的次数,禁止的提示
							event.disallow(Result.KICK_OTHER, MainConfig.getPlayerJoinIpAlert());
						}else {
							ipJoin.setErrorCount(0);
							ipJoin.setBanEndDate(null);
							ipJoin.setBanStartDate(new Date());
						}
					} 
					//规定时间内，IP登陆的次数
					if(ipJoin.getErrorCount()>=MainConfig.getPlayerJoinIpCount()) {
						//设置禁止登陆的时间
						ipJoin.setBanEndDate(DateUtil.getAddDate(ipJoin.getBanStartDate(), MainConfig.getPlayerJoinIpTime()));
					}
				}
				ipJoin.setErrorCount(ipJoin.getErrorCount()+1);
			}else {
				ipJoin=new TempBan();
				ipJoin.setErrorCount(1);
				ipJoin.setBanStartDate(new Date());
			}
			LoginTempBan.setTempBan(ipAddress, ipJoin);
		}
		
		//关闭注册
		if(MainConfig.isRegister()==false) {
			User login=new User();
			login.setUsername(event.getName().toLowerCase());
			if(new SGLoginImpl().login(login)==null) {
				event.disallow(Result.KICK_OTHER, MainConfig.getIsRegisterAlert());
			}
		}
	}
}

package org.sg.lf.sglogin.command;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sg.lf.sglogin.dao.SGLoginDao;
import org.sg.lf.sglogin.dao.impl.SGLoginImpl;
import org.sg.lf.sglogin.entity.IsLogin;
import org.sg.lf.sglogin.entity.TempBan;
import org.sg.lf.sglogin.entity.User;
import org.sg.lf.sglogin.util.DateUtil;
import org.sg.lf.sglogin.util.EmailConfig;
import org.sg.lf.sglogin.util.LocaConfig;
import org.sg.lf.sglogin.util.LoginPlayer;
import org.sg.lf.sglogin.util.LoginTempBan;
import org.sg.lf.sglogin.util.MainConfig;

public class Login implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {
		if(sender instanceof Player) {
			SGLoginDao sgLoginDao = new SGLoginImpl();
			Player player=(Player)sender;
			if(args.length==1) {
				//密码的正则表达式
				Pattern p = Pattern.compile(MainConfig.getCipherRegularity());
				Matcher matcher =p.matcher(args[0]);
				if(matcher.matches()==false || args[0].length()>MainConfig.getMaxPass() || args[0].length()<MainConfig.getMinPass()) {
					//密码格式输错的提醒
					player.sendMessage(MainConfig.getPasserRoralert());
					return false;
				}
				IsLogin isLogin = LoginPlayer.getPlayerInfo().get(player.getName());
				if(isLogin.getAlreadyLogin()==true) {
					//已经登陆时又登陆或注册时的提示
					player.sendMessage(MainConfig.getAlreadyLoggedin());
					return false;
				}
				User user=new User();
				user.setUsername(player.getName().toLowerCase());
				user.setPassword(args[0]);
				String nameAndIp=player.getName()+player.getAddress().getAddress().getHostAddress();
				TempBan tb=LoginTempBan.getTempBanForOne(nameAndIp);
				if(tb==null) {
					tb=new TempBan();
					tb.setPlayerName(player.getAddress().getHostString());
				}
				User loginUser=sgLoginDao.login(user);
				if(loginUser!=null) {
					//判断是否被封号
					if(loginUser.getBanif()==1) {
						if(loginUser.getBanend().compareTo(new Date())>=0) {
							player.kickPlayer("您已被封禁，理由："+loginUser.getBanreason()+"，结束时间："+DateUtil.getDateShow(loginUser.getBanend())+"，剩余时间："+DateUtil.getTwoDateTimes(new Date(), loginUser.getBanend()));
						}
					} else if(loginUser.getBanif()==2) {
						player.kickPlayer("您已被永久封禁，理由："+loginUser.getBanreason());
					}
					//登陆成功
					player.sendMessage(MainConfig.getLoginSucceeded());
					isLogin.setPlayerId(loginUser.getId());
					isLogin.setAlreadyLogin(true);
					isLogin.setLoginDate(new Date());
					isLogin.setIp(player.getAddress().getHostString());
					if(LocaConfig.getEnableLoginLoca()==true) {
						String worldName=loginUser.getWorld()!=null?loginUser.getWorld():null;
						if(worldName!=null) {
							player.teleport(LocaConfig.getBeforeLoca());
							player.sendMessage(LocaConfig.getPlayerSpawnAlert());
						} else {
							if(LocaConfig.getPlayerLoca()==true) {
								World world=Bukkit.getWorld(loginUser.getWorld());
								if(world!=null) {
									Location location=new Location(world,loginUser.getX(),loginUser.getY(),loginUser.getZ(),loginUser.getYaw(),loginUser.getPitch());
									player.teleport(location);
									player.sendMessage(LocaConfig.getPlayerOffLineAlert());
								}else {
									player.teleport(LocaConfig.getBeforeLoca());
									player.sendMessage(LocaConfig.getPlayerSpawnAlert());
								}
							}else {
								player.teleport(LocaConfig.getBeforeLoca());
								player.sendMessage(LocaConfig.getPlayerSpawnAlert());
							}
						}
					}
					tb.setErrorCount(0);
					LoginTempBan.setTempBan(nameAndIp,tb);
					LoginTempBan.setTempBan(nameAndIp);
					if(EmailConfig.getEnable()==true && loginUser.getEmail()==null && EmailConfig.getEmailIsAlert()==true) {
						player.sendMessage(EmailConfig.getEmailAlertContent());
					}
					
					//登陆成功后改为在线状态
					new Thread(new Runnable() {
						@Override
						public void run() {
							User stateUser=new User();
							stateUser.setUsername(loginUser.getUsername());
							stateUser.setOnline(1);
							sgLoginDao.updateUser(stateUser);
						}
					}).start();
					
				}else {
					//登陆时密码输错
					player.sendMessage(MainConfig.getLoginPassError());
					tb.setErrorCount(tb.getErrorCount()+1);
					if(tb.getBanStartDate()==null) {
						tb.setBanStartDate(new Date());
					}else {
						//规定输错密码的时间，单位：秒
						if(new Date().compareTo(DateUtil.getAddDate(tb.getBanStartDate(), MainConfig.getTempBanLoginAndIpForTime()))>=0) {
							tb.setBanStartDate(null);
							tb.setErrorCount(1);
						}
					}
					//在规定的时间内输错多少次密码，会临时禁止某账号和IP的登陆
					if(tb.getErrorCount()>=MainConfig.getTempBanLoginAndIpForCount()) {
						//规定时间内多次输错密码拒绝登陆的时间，单位：秒
						tb.setBanEndDate(DateUtil.getAddDate(tb.getBanStartDate(), MainConfig.getTempBanLoginAndIpForRefuse()));
						//多次输错密码的提示
						tb.setReason(MainConfig.getTempBanLoginAndIpForAlert());
						LoginTempBan.setTempBan(nameAndIp,tb);
						player.kickPlayer(MainConfig.getTempBanLoginAndIpForAlert());
						return false;
					}else {
						LoginTempBan.setTempBan(nameAndIp,tb);
					}
					isLogin.setCount(isLogin.getCount()+1);
					//登陆时输错多少次密码将被踢出服务器（一次）
					if(isLogin.getCount()>=MainConfig.getLoginInputErrorPassCount()) {
						//登陆时多次输错密码时被踢出的提示
						player.kickPlayer(MainConfig.getLoginInputErrorPassAlert());
						return false;
					}
				}
				LoginPlayer.setPlayerInfo(player.getName(), isLogin);
			}else {
				//登陆时命令格式错误的提示
				player.sendMessage(MainConfig.getLoginCommandError());
			}
		}else {
			//在控制台输入玩家输入的指令时，提示（玩家看不到）
			System.out.println(MainConfig.getNotPlayer());
		}
		return false;
	}
}

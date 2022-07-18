package org.sg.lf.sglogin.command;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sg.lf.sglogin.dao.IpLimitDao;
import org.sg.lf.sglogin.dao.impl.IpLimitImpl;
import org.sg.lf.sglogin.dao.impl.SGLoginImpl;
import org.sg.lf.sglogin.entity.IpLimit;
import org.sg.lf.sglogin.entity.IsLogin;
import org.sg.lf.sglogin.entity.User;
import org.sg.lf.sglogin.util.LocaConfig;
import org.sg.lf.sglogin.util.LoginPlayer;
import org.sg.lf.sglogin.util.MainConfig;

public class Register implements CommandExecutor{
	
	IpLimitDao ipLimitDao=new IpLimitImpl();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,String[] args) {
		if(sender instanceof Player) {
			Player player=(Player)sender;
			String ip=player.getAddress().getAddress().getHostAddress();
			if(ipLimitDao.countIpLimit(ip)>=MainConfig.getRegisterIpCount()) {
				player.kickPlayer(MainConfig.getRegisterIpAlert());
			}
			if(args.length==2) {
				if(args[0].equals(args[1])) {
					//密码的正则表达式
					Pattern p = Pattern.compile(MainConfig.getCipherRegularity());
					Matcher matcher =p.matcher(args[0]);
					if(matcher.matches()==false || args[1].length()>MainConfig.getMaxPass() || args[1].length()<MainConfig.getMinPass()) {
						//密码格式输错的提醒
						player.sendMessage(MainConfig.getPasserRoralert());
						return false;
					}
					IsLogin islogin=LoginPlayer.getPlayerInfo().get(player.getName());
					if(islogin.getAlreadyLogin()==true) {
						//已经登陆时又登陆或注册时的提示
						player.sendMessage(MainConfig.getAlreadyLoggedin());
						return false;
					}
					User user=new User();
					user.setId(UUID.randomUUID().toString());
					user.setUsername(player.getName().toLowerCase());
					user.setPassword(args[0]);
					user.setRealName(player.getName());
					user.setRealip(player.getAddress().getHostString());
					user.setOnline(1);
					SGLoginImpl sgli=new SGLoginImpl();
					if(sgli.isExistUser(user)>0) {
						return false;
					}
					if(sgli.register(user)>0) {
						//注册成功后的提醒
						player.sendMessage(MainConfig.getRegisterSucceeded());
						if(LocaConfig.getEnableLoginLoca()==true) {
							player.teleport(LocaConfig.getBeforeLoca());
							player.sendMessage(LocaConfig.getPlayerSpawnAlert());
						}
						islogin.setAlreadyLogin(true);
						islogin.setPlayerId(user.getId());
						islogin.setLoginDate(new Date());
						LoginPlayer.setPlayerInfo(player.getName(),islogin);
						new Thread(new Runnable() {
							@Override
							public void run() {
								IpLimit ipLimit=new IpLimit();
								ipLimit.setPlayername(player.getName());
								ipLimit.setIp(ip);
								ipLimitDao.insertIpLimit(ipLimit);
							}
						}).start();
					}else {
						//注册失败时的提示
						player.sendMessage(MainConfig.getRegisterError());
					}
				}else {
					//两次密码输入不一致的提示
					player.sendMessage(MainConfig.getRegisterPassInconformity());
				}
			}else {
				//注册格式错误时的提示
				player.sendMessage(MainConfig.getRegisterFormatError());
			}
		}else {
			//是否是玩家输入的指令
			System.out.println(MainConfig.getNotPlayer());
		}
		return false;
	}
}

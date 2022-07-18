package org.sg.lf.sglogin.command.admin;

import java.util.Date;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sg.lf.sglogin.dao.impl.PlayerBanImpl;
import org.sg.lf.sglogin.dao.impl.SGLoginImpl;
import org.sg.lf.sglogin.dao.impl.SysDataBookImpl;
import org.sg.lf.sglogin.entity.IsLogin;
import org.sg.lf.sglogin.entity.PlayerBan;
import org.sg.lf.sglogin.entity.User;
import org.sg.lf.sglogin.util.DateUtil;
import org.sg.lf.sglogin.util.IsPlayer;
import org.sg.lf.sglogin.util.LoginPlayer;

public class PlayerBanCommand {
	public boolean addPlayerBan(CommandSender sender,Command command,String label,String[] args) {
		String username=null;
		if(sender instanceof Player) {
			Player player=(Player)sender;
			username=player.getName();
			if(player.hasPermission("sglogin.admin.plyerban")==false) {
				player.sendMessage("你没有执行该指令的权限!");
				return false;
			}
		} else {
			username="admin";
		}
		if(args.length>=3){
			//判断有没有该玩家
			User user=new User();
			user.setUsername(args[1].toLowerCase());
			if(new SGLoginImpl().isExistUser(user)==1) {
				Date startDate=new Date();
				Date endDate=null;
				String banTime=null;
				int banif=2;
				//封禁时间为-1的时候
				if(!"-1".equals(args[2])) {
					String unit=args[2].substring(args[2].length()-1);
					int num=Integer.parseInt(args[2].substring(0,args[2].length()-1));
					switch (unit) {
					case "d":
						num=num*1000*60*60*24;
						break;
					case "h":
						num=num*1000*60*60;
						break;
					default:
						num=num*1000*60;
						break;
					}
					endDate=new Date(startDate.getTime()+num);
					banTime=DateUtil.getTwoDateTimes(startDate, endDate);
					banif=1;
				}
				PlayerBan playerBan=new PlayerBan();
				playerBan.setPlayername(args[1]);
				IsLogin isLogin = LoginPlayer.getPlayerInfo().get(username);
				if(isLogin==null) {
					playerBan.setHandleid("13945b1e-8957-4958-b7f8-74ba09516eda");
				} else {
					playerBan.setHandleid(isLogin.getPlayerId());
				}
				playerBan.setStartdate(startDate);
				playerBan.setEnddate(endDate);
				playerBan.setBantime(banTime);
				if(args.length>=4) {
					playerBan.setReasonid(new SysDataBookImpl().getIdByLimit(Integer.parseInt(args[3])));
				} else {
					playerBan.setReasonid(new SysDataBookImpl().getIdByLimit(1));
				}
				playerBan.setCreatedate(startDate);
				//更新用户表
				new PlayerBanImpl().insertPlayerBan(playerBan);
				user.setBanstart(startDate);
				user.setBanend(endDate);
				user.setBanid(playerBan.getReasonid());
				user.setBanif(banif);
				new SGLoginImpl().updateUserForBan(user);
				if(!"-1".equals(args[2])) {
					IsPlayer.SayMassageColor(sender, "已经对玩家："+args[1]+"，解封时间："+DateUtil.getDateShow(endDate));
				} else {
					IsPlayer.SayMassageColor(sender, "已经对玩家："+args[1]+"，永久封禁！");
				}
			}
		} else {
			IsPlayer.SayMassageColor(sender, "指令格式错误，正确应为：sglogin ban [用户名] [时长] [理由ID]（1d=1天、1h=1小时、1m=1分钟）");
		}
		return false;
	}
	
	public boolean unPlayerBan(CommandSender sender,String[] args) {
		if(args.length>=2) {
			if(new SGLoginImpl().unUserForBan(args[1])>0) {
				IsPlayer.SayMassageColor(sender, "已解除玩家："+args[1]+"的封禁！");
			} else {
				IsPlayer.SayMassageColor(sender, "解禁失败！");
			}
		} else {
			IsPlayer.SayMassageColor(sender, "指令格式错误，正确应为：sglogin unban [用户名]");
		}
		return false;
	}
}

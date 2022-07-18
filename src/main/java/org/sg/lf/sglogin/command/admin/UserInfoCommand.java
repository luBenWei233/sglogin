package org.sg.lf.sglogin.command.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sg.lf.sglogin.dao.impl.SGLoginImpl;
import org.sg.lf.sglogin.entity.User;
import org.sg.lf.sglogin.util.IsPlayer;

public class UserInfoCommand {
	public boolean getUserinfo(CommandSender sender,Command command,String label,String[] args) {
		if(sender instanceof Player) {
			Player player=(Player)sender;
			if(player.hasPermission("sglogin.admin.playerinfo")==false) {
				player.sendMessage("你没有执行该指令的权限!");
				return false;
			}
		}
		if(args.length==2) {
			User user = new SGLoginImpl().userInfo(args[1]);
			if(user!=null) {
				IsPlayer.SayMassageColor(sender, "================="+user.getRealName()+"===============");
				String role=user.getAdminif()==1?"管理员":"玩家";
				IsPlayer.SayMassageColor(sender, "注册日期："+user.getRegDateShow()+"，注册IP："+user.getRealip()+"，角色："+role);
				IsPlayer.SayMassageColor(sender, "最近登陆时间："+user.getLastDateShow()+"最近登陆IP："+user.getLastip());
				if(user.getBanif()==0) {
					IsPlayer.SayMassageColor(sender, "账号状态：正常");
				} else if(user.getBanif()==1) {
					IsPlayer.SayMassageColor(sender, "账号状态：封号中");
					IsPlayer.SayMassageColor(sender, "封禁开始时间："+user.getBanstartShow());
					IsPlayer.SayMassageColor(sender, "结束时间："+user.getBanendShow());
					IsPlayer.SayMassageColor(sender, "封禁理由："+user.getBanreason());
				}
				if(user.getBanif()==2) {
					IsPlayer.SayMassageColor(sender, "账号状态：永久封号，封禁开始时间："+user.getBanstartShow());
					IsPlayer.SayMassageColor(sender, "封禁理由："+user.getBanreason());
				}
				IsPlayer.SayMassageColor(sender, "======================================================");
			} else {
				IsPlayer.SayMassageColor(sender, "未找到"+args[1]+"这个玩家！");
			}
		} else {
			IsPlayer.SayMassageColor(sender, "指令格式错误，正确应为：sglogin get [用户名]");
		}
		return false;
	}
}

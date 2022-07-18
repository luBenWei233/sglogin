package org.sg.lf.sglogin.command.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sg.lf.sglogin.dao.impl.SGLoginImpl;
import org.sg.lf.sglogin.entity.User;
import org.sg.lf.sglogin.util.IsPlayer;

public class DeletePlayer{
	public boolean registerAdmin(CommandSender sender,Command command,String label,String[] args) {
		if(sender instanceof Player) {
			Player player=(Player)sender;
			if(player.hasPermission("sglogin.admin.remove")==false) {
				player.sendMessage("你没有执行该指令的权限!");
				return false;
			}
		}
		if(args.length==2){
			User user=new User();
			user.setUsername(args[1].toLowerCase());
			SGLoginImpl sgli=new SGLoginImpl();
			if(sgli.isExistUser(user)>=1) {
				if(sgli.removeUser(user)>0) {
					IsPlayer.SayMassage(sender, "[sglogin]用户"+args[1]+"移除成功！");
				}else {
					IsPlayer.SayMassage(sender, "[sglogin]用户"+args[1]+"移除失败，请检查数据库状态！");
				}
			}else {
				IsPlayer.SayMassage(sender, "[sglogin]不存在"+args[1]+"玩家，无需移除");
			}
		}else {
			IsPlayer.SayMassage(sender, "[sglogin]指令格式错误，正确应为：sglogin remove [用户名]");
		}
		return false;
	}
}

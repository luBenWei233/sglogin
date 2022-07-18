package org.sg.lf.sglogin.command.admin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sg.lf.sglogin.util.IsPlayer;
import org.sg.lf.sglogin.util.LoginTempBan;

public class ClearData {
	public boolean clear(CommandSender sender) {
		if(sender instanceof Player) {
			Player player=(Player)sender;
			if(player.hasPermission("sglogin.admin.clear")==false) {
				player.sendMessage("你没有执行该指令的权限!");
				return false;
			}
		}
		LoginTempBan.clearTempBan();
		IsPlayer.SayMassage(sender, "[sglogin]清空用户数据缓存！");
		return false;
	}
}

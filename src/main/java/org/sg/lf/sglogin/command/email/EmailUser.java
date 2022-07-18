package org.sg.lf.sglogin.command.email;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EmailUser implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {
		if(sender instanceof Player) {
			if(args.length>=1) {
				switch (args[0]) {
				case "add":
					new EmailAdd(sender, command, label, args);
					return false;
				case "change":
					new EmailChange(sender, command, label, args);
					return false;
				case "show":
					new EmailShow(sender, command, label, args);
					return false;
				default:
					((Player) sender).getPlayer().sendMessage("邮箱操作没有该指令，请输入 /email help 查看帮助！");
				}
			}else {
				((Player) sender).getPlayer().sendMessage("邮箱操作指令不正确！");
			}
		}else {
			System.out.println("些指令只能由玩家执行");
		}
		return false;
	}
	
}

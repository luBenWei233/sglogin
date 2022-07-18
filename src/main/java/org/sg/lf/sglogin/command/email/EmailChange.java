package org.sg.lf.sglogin.command.email;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sg.lf.sglogin.dao.impl.SGLoginImpl;
import org.sg.lf.sglogin.entity.User;
import org.sg.lf.sglogin.util.CheckData;

public class EmailChange {
	public EmailChange(CommandSender sender,Command command,String label,String[] args) {
		Player player=(Player)sender;
		if(args.length==4) {
			int key=CheckData.checkEmail(args[2], args[3]);
			switch (key) {
			case 1:
				player.sendMessage("邮箱格式不正确，你输入的就不是邮箱!");
				return;
			case 2:
				player.sendMessage("两次邮箱输入不一致");
				return;
			}
			User user=new User();
			user.setUsername(player.getName().toLowerCase());
			SGLoginImpl sgli=new SGLoginImpl();
			user=sgli.login(user);
			if(user.getEmail()!=null) {
				if(args[1].equals(user.getEmail())==false) {
					player.sendMessage("您的旧邮箱输入错误，请检查！");
					return;
				}
				user.setEmail(args[2]);
				if(sgli.updateUser(user)>=1) {
					player.sendMessage("邮件修改成功！");
				}else {
					player.sendMessage("邮箱修改失败，请联系管理员！");
				}
			}else {
				player.sendMessage("您还没绑定邮件，如果想绑定请输入:/email add [邮箱] [重复邮箱]");
			}
		}else {
			player.sendMessage("绑定邮箱的正确指令为：/email add [邮箱] [重复邮箱]");
		}
	}
}

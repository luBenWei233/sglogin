package org.sg.lf.sglogin.command.email;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sg.lf.sglogin.dao.impl.SGLoginImpl;
import org.sg.lf.sglogin.entity.User;
import org.sg.lf.sglogin.util.CheckData;

public class EmailAdd {
	public EmailAdd(CommandSender sender,Command command,String label,String[] args) {
		Player player=(Player)sender;
		if(args.length==3) {
			int key=CheckData.checkEmail(args[1], args[2]);
			switch (key) {
			case 1:
				player.sendMessage("邮箱格式不正确，请查看校验规则");
				return;
			case 2:
				player.sendMessage("两次邮箱输入不一致");
				return;
			}
			User user=new User();
			user.setUsername(player.getName().toLowerCase());
			SGLoginImpl sgli=new SGLoginImpl();
			user=sgli.login(user);
			if(user.getEmail()==null) {
				user.setEmail(args[1]);
				if(sgli.updateUser(user)>=1) {
					player.sendMessage("邮件绑定成功！");
				}else {
					player.sendMessage("邮箱绑定失败，请联系管理员！");
				}
			}else {
				player.sendMessage("已经绑定邮件，如果想修改请输入:/email change [旧邮箱] [邮箱] [重复邮箱]");
			}
		}else {
			player.sendMessage("绑定邮箱的正确指令为：/email add [邮箱] [重复邮箱]");
		}
	}
}

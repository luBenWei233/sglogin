package org.sg.lf.sglogin.command.email;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sg.lf.sglogin.dao.impl.SGLoginImpl;
import org.sg.lf.sglogin.entity.User;

public class EmailShow {
	public EmailShow(CommandSender sender,Command command,String label,String[] args) {
		Player player=(Player)sender;
		User user=new User();
		user.setUsername(player.getName().toLowerCase());
		user=new SGLoginImpl().login(user);
		if(user.getEmail()!=null) {
			StringBuffer email1=new StringBuffer();
			email1.append(user.getEmail().substring(0, 2));
			for(int i=0;i<user.getEmail().length()-4;i++) {
				email1.append("*");
			}
			email1.append(user.getEmail().substring(user.getEmail().length()-2, user.getEmail().length()));
			player.sendMessage("您绑定的邮箱为："+email1.toString());
		}else {
			player.sendMessage("您还没有绑定邮箱！");
		}
	}
}

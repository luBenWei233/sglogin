package org.sg.lf.sglogin.command.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sg.lf.sglogin.dao.impl.SGLoginImpl;
import org.sg.lf.sglogin.entity.User;
import org.sg.lf.sglogin.util.CheckData;
import org.sg.lf.sglogin.util.IsPlayer;

public class ChangePwd {
	public boolean changePwdAdmin(CommandSender sender,Command command,String label,String[] args) {
		if(sender instanceof Player) {
			Player player=(Player)sender;
			if(player.hasPermission("sglogin.admin.password")==false) {
				player.sendMessage("你没有执行该指令的权限!");
				return false;
			}
		}
		if(args.length==4){
			User user=new User();
			user.setUsername(args[1].toLowerCase());
			SGLoginImpl sgli=new SGLoginImpl();
			if(sgli.isExistUser(user)>=1) {
				int key=CheckData.CheckPwd(args[2], args[3]);
				switch (key) {
				case 1:
					IsPlayer.SayMassage(sender, "[sglogin]密码格式不正确，请查看校验规则");
					return false;
				case 2:
					IsPlayer.SayMassage(sender, "[sglogin]两次密码输入不一致");
					return false;
				}
				user.setPassword(args[2]);
				if(sgli.updateUser(user)>0) {
					IsPlayer.SayMassage(sender, "[sglogin]修改用户"+args[1]+"密码成功！");
				}else {
					IsPlayer.SayMassage(sender, "[sglogin]修改用户"+args[1]+"密码失败，请检查数据库状态！");
				}
			}else {
				IsPlayer.SayMassage(sender, "[sglogin]不存在"+args[1]+"玩家，请检查！");
			}
		}else {
			IsPlayer.SayMassage(sender, "[sglogin]指令格式错误，正确应为：sglogin changepassword [用户名] [密码] [重复密码]");
		}
		return false;
	}
}

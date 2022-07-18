package org.sg.lf.sglogin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sg.lf.sglogin.dao.impl.SGLoginImpl;
import org.sg.lf.sglogin.entity.User;
import org.sg.lf.sglogin.util.CheckData;
import org.sg.lf.sglogin.util.MainConfig;

public class ChangePwd implements CommandExecutor{

	//用户修改自己的密码
	@Override
	public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {
		if(sender instanceof Player) {
			Player player=(Player)sender;
			if(args.length==3) {
				int key=CheckData.CheckPwd(args[1], args[2]);
				switch (key) {
				case 1:
					//密码格式不正确
					player.sendMessage(MainConfig.getPasserRoralert());
					return false;
				case 2:
					//两次密码错误
					player.sendMessage(MainConfig.getRegisterPassInconformity());
					return false;
				}
				User user=new User();
				user.setUsername(player.getName().toLowerCase());
				user.setPassword(args[0]);
				SGLoginImpl sgli=new SGLoginImpl();
				if(sgli.login(user)!=null) {
					user.setPassword(args[1]);
					if(sgli.updateUser(user)>0) {
						//修改成功，下次请用该密码登陆！
						player.sendMessage(MainConfig.getChangePwdSuccessAlert());
					}else {
						//修改失败，请联系密码员！
						player.sendMessage(MainConfig.getChangePwdFailAlert());
					}
				}else {
					//旧密码错误，修改失败！
					player.sendMessage(MainConfig.getChangePwdOldPwdError());
				}
			}else {
				//修改密码的格式为：/changepassword [旧密码] [新密码] [重复新密码]
				player.sendMessage(MainConfig.getChangePwdFormatError());
			}
		}else {
			System.out.println(MainConfig.getNotPlayer());
		}
		return false;
	}
	
}

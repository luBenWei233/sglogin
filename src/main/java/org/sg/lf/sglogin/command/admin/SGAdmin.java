package org.sg.lf.sglogin.command.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.sg.lf.sglogin.util.IsPlayer;

public class SGAdmin implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {
		if(args.length<1) {
			IsPlayer.SayMassage(sender, "[sglogin]您的指令格式不正确，请检查！");
			return false;
		}
		String key=args[0];
		switch (key) {
		case "register":
			return new Register().registerAdmin(sender, command, label, args);
		case "changepassword":
			return new ChangePwd().changePwdAdmin(sender, command, label, args);
		case "remove":
			return new DeletePlayer().registerAdmin(sender, command, label, args);
		case "spawnset":
			return new SpawnSet().spawn(sender, command, label, args);
		case "before":
			return new SpawnSet().before(sender, command, label, args);
		case "enable":
			return new SpawnSet().enable(sender, command, label, args);
		case "tp":
			return new SpawnSet().tp(sender, command, label, args);
		case "ban":
			return new PlayerBanCommand().addPlayerBan(sender, command, label, args);
		case "unban":
			return new PlayerBanCommand().unPlayerBan(sender, args);
		case "reason":
			return new SysDataBookCommand().sysDataBookHandle(sender, command, label, args);
		case "get":
			return new UserInfoCommand().getUserinfo(sender, command, label, args);
		case "clear":
			return new ClearData().clear(sender);
		case "reload":
			return new SpawnSet().reload(sender);
		case "help":
			return new SpawnSet().help(sender);
		case "admin":
			return new SpawnSet().admin(sender,args); 
		default:
			IsPlayer.SayMassage(sender, "[sglogin]不存在该指令，请检查！");
			return false;
		}
	}

}

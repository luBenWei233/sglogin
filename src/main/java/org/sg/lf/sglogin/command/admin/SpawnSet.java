package org.sg.lf.sglogin.command.admin;

import java.io.File;
import java.io.IOException;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sg.lf.sglogin.util.CommandConfig;
import org.sg.lf.sglogin.util.DataBaseConfig;
import org.sg.lf.sglogin.util.EmailConfig;
import org.sg.lf.sglogin.util.IsPlayer;
import org.sg.lf.sglogin.util.LocaConfig;
import org.sg.lf.sglogin.util.MainConfig;

public class SpawnSet {
	public boolean spawn(CommandSender sender,Command command,String label,String[] args) {
		if(sender instanceof Player) {
			Player player=(Player)sender;
			if(player.hasPermission("sglogin.admin.spawnset")==false) {
				player.sendMessage("你没有执行该指令的权限!");
				return false;
			}
			//修改
			Location loca=player.getLocation();
			String value=player.getWorld().getName()+":"+loca.getX()+":"+loca.getY()+":"+loca.getZ()+":"+loca.getYaw()+":"+loca.getPitch();
			LocaConfig.getConfig().set("loginLoca", value);
			try {
				LocaConfig.getConfig().save(new File(LocaConfig.getPlugin().getDataFolder(),"location.yml"));
				LocaConfig.setLoginLoca(value);
				player.sendMessage("玩家登陆之前的出生点设置成功！");
			} catch (IOException e) {
				player.sendMessage("设置失败，请查看日志或去控制台查看！");
				e.printStackTrace();
			}
		}else {
			System.out.println(MainConfig.getNotPlayer());
		}
		return false;
	}
	
	public boolean before(CommandSender sender,Command command,String label,String[] args) {
		if(sender instanceof Player) {
			Player player=(Player)sender;
			if(player.hasPermission("sglogin.admin.spawnset")==false) {
				player.sendMessage("你没有执行该指令的权限!");
				return false;
			}
			//修改
			Location loca=player.getLocation();
			String value=player.getWorld().getName()+":"+loca.getX()+":"+loca.getY()+":"+loca.getZ()+":"+loca.getYaw()+":"+loca.getPitch();
			LocaConfig.getConfig().set("beforeLoca", value);
			try {
				LocaConfig.getConfig().save(new File(LocaConfig.getPlugin().getDataFolder(),"location.yml"));
				LocaConfig.setBeforeLoca(value);
				player.sendMessage("玩家登陆之后传送点设置成功！");
			} catch (IOException e) {
				player.sendMessage("设置失败，请查看日志或去控制台查看！");
				e.printStackTrace();
			}
		}else {
			System.out.println(MainConfig.getNotPlayer());
		}
		return false;
	}
	
	public boolean enable(CommandSender sender,Command command,String label,String[] args) {
		if(sender instanceof Player) {
			Player player=(Player)sender;
			if(player.hasPermission("sglogin.admin.enablelogin")==false) {
				player.sendMessage("你没有执行该指令的权限!");
				return false;
			}
		}
		if(args.length<2) {
			IsPlayer.SayMassage(sender, "[sglogin]指令格式错误！");
			return false;
		}
			//修改
			if(args[1].contains("t")) {
				LocaConfig.getConfig().set("enableLoginLoca", true);
				LocaConfig.setEnableLoginLoca(true);
			}else {
				LocaConfig.getConfig().set("enableLoginLoca", false);
				LocaConfig.setEnableLoginLoca(false);
			}
			try {
				LocaConfig.getConfig().save(new File(LocaConfig.getPlugin().getDataFolder(),"location.yml"));
				IsPlayer.SayMassage(sender, "[sglogin]已启用玩家登陆之前强制位置！");
			} catch (IOException e) {
				IsPlayer.SayMassage(sender, "[sglogin]设置失败，请查看日志或去控制台查看！");
				e.printStackTrace();
			}
		return false;
	}
	
	public boolean tp(CommandSender sender,Command command,String label,String[] args) {
		if(sender instanceof Player) {
			Player player=(Player)sender;
			if(player.hasPermission("sglogin.admin.tp")==false) {
				player.sendMessage("你没有执行该指令的权限!");
				return false;
			}
		}
		if(args.length<2) {
			IsPlayer.SayMassage(sender, "[sglogin]指令格式错误！");
			return false;
		}
			//修改
			if(args[1].contains("t")) {
				LocaConfig.getConfig().set("playerLoca", true);
				LocaConfig.setPlayerLoca(true);
			}else {
				LocaConfig.getConfig().set("playerLoca", false);
				LocaConfig.setPlayerLoca(false);
			}
			try {
				LocaConfig.getConfig().save(new File(LocaConfig.getPlugin().getDataFolder(),"location.yml"));
				IsPlayer.SayMassage(sender, "[sglogin]已启用玩家登陆后传送致上次下线位置！");
			} catch (IOException e) {
				IsPlayer.SayMassage(sender, "[sglogin]设置失败，请查看日志或去控制台查看！");
				e.printStackTrace();
			}
		return false;
	}
	
	public boolean reload(CommandSender sender) {
		if(sender instanceof Player) {
			Player player=(Player)sender;
			if(player.hasPermission("sglogin.admin.reload")==false) {
				player.sendMessage("你没有执行该指令的权限!");
				return false;
			}else {
				CommandConfig.reload();
				DataBaseConfig.reload();
				EmailConfig.reload();
				LocaConfig.reload();
				MainConfig.reload();
				player.sendMessage("配置文件重新加载成功!");
			}
		}else {
			CommandConfig.reload();
			DataBaseConfig.reload();
			EmailConfig.reload();
			LocaConfig.reload();
			MainConfig.reload();
			System.out.println("配置文件重新加载成功!");
		}
		return true;
	}
	
	public boolean help(CommandSender sender) {
		IsPlayer.SayMassage(sender, "§2------------------------------------------");
		IsPlayer.SayMassage(sender, "§2登陆游戏");
		IsPlayer.SayMassage(sender, "/l [密码] 或 /login [密码]");
		IsPlayer.SayMassage(sender, "§2注册帐号");
		IsPlayer.SayMassage(sender, "/reg [密码] [重复密码] 或 /register [密码] [重复密码]");
		IsPlayer.SayMassage(sender, "§2修改密码");
		IsPlayer.SayMassage(sender, "/changepassword [旧密码] [新密码] [重复新密码]");
		IsPlayer.SayMassage(sender, "§2查看绑定的邮箱");
		IsPlayer.SayMassage(sender, "/email show");
		IsPlayer.SayMassage(sender, "§2绑定邮箱");
		IsPlayer.SayMassage(sender, "/email add [邮箱] [重复邮箱]");
		IsPlayer.SayMassage(sender, "§2修改绑定的邮箱");
		IsPlayer.SayMassage(sender, "/email [旧邮箱] [新邮箱] [重复新邮箱]");
		IsPlayer.SayMassage(sender, "§2忘记密码时用邮箱找回密码");
		IsPlayer.SayMassage(sender, "/recover [自己绑定的邮箱]");
		IsPlayer.SayMassage(sender, "§2使用邮箱中的临时码进行登陆并修改密码");
		IsPlayer.SayMassage(sender, "/recover [code] [新密码] [重复密码]");
		IsPlayer.SayMassage(sender, "§4查看管理员命令");
		IsPlayer.SayMassage(sender, "/sglogin admin [page]");
		IsPlayer.SayMassage(sender, "§2------------------------------------------");
		return false;
	}
	
	public boolean admin(CommandSender sender,String[] args) {
		String page="1";
		if(args.length>=2) {
			page=args[1];
		}
		switch (page) {
		case "2":
			IsPlayer.SayMassage(sender, "§2--共2页---第2页-----------------------------");
			IsPlayer.SayMassage(sender, "§2清空玩家数据缓存(如遇到玩家不能登陆或注册问题)");
			IsPlayer.SayMassage(sender, "/sglogin clear");
			IsPlayer.SayMassage(sender, "§2将您当前位置设置成玩家登陆之前的世界和位置");
			IsPlayer.SayMassage(sender, "/sglogin spawnset");
			IsPlayer.SayMassage(sender, "§2将您当前位置设置成玩家登陆之后的世界和位置");
			IsPlayer.SayMassage(sender, "/sglogin before");
			IsPlayer.SayMassage(sender, "§2开启登陆强制位置");
			IsPlayer.SayMassage(sender, "/sglogin enable true/false");
			IsPlayer.SayMassage(sender, "§2开启后，登陆后的玩家将传送到他上次下线的位置");
			IsPlayer.SayMassage(sender, "/sglogin tp true/false");
			IsPlayer.SayMassage(sender, "§2重新加载配置文件");
			IsPlayer.SayMassage(sender, "/sglogin reload");
			IsPlayer.SayMassage(sender, "§2------------------------------------------");
			break;
		default:
			IsPlayer.SayMassage(sender, "§2--共2页---第1页-----------------------------");
			IsPlayer.SayMassage(sender, "§2读取某用户详细信息");
			IsPlayer.SayMassage(sender, "/sglogin get [用户名]");
			IsPlayer.SayMassage(sender, "§2修改任意用户密码");
			IsPlayer.SayMassage(sender, "/sglogin changepassword [用户名] [新密码] [重复密码]");
			IsPlayer.SayMassage(sender, "§2生成一个帐号");
			IsPlayer.SayMassage(sender, "/sglogin register [用户名] [密码] [重复密码]");
			IsPlayer.SayMassage(sender, "§2移除一名用户,但不删除游戏数据");
			IsPlayer.SayMassage(sender, "/sglogin remove [用户名]");
			IsPlayer.SayMassage(sender, "§2封禁一名用户");
			IsPlayer.SayMassage(sender, "/sglogin ban [用户名] [d/天,h/时,m/分(例:3d)] [理由ID]");
			IsPlayer.SayMassage(sender, "§2解禁一名用户");
			IsPlayer.SayMassage(sender, "/sglogin unban [用户名]");
			IsPlayer.SayMassage(sender, "§2查看所有封号理由");
			IsPlayer.SayMassage(sender, "/sglogin reason");
			IsPlayer.SayMassage(sender, "§2新增一个封号理由");
			IsPlayer.SayMassage(sender, "/sglogin reason add [理由]");
			IsPlayer.SayMassage(sender, "§2修改一个封号理由");
			IsPlayer.SayMassage(sender, "/sglogin reason update [理由ID] [理由]");
			IsPlayer.SayMassage(sender, "§2删除一个封号理由");
			IsPlayer.SayMassage(sender, "/sglogin del [理由ID]");
			IsPlayer.SayMassage(sender, "§2------------------------------------------");
			break;
		}
		return false;
	}
}

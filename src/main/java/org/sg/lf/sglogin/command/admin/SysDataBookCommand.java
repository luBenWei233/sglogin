package org.sg.lf.sglogin.command.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sg.lf.sglogin.dao.SysDataBookDao;
import org.sg.lf.sglogin.dao.impl.SysDataBookImpl;
import org.sg.lf.sglogin.entity.IsLogin;
import org.sg.lf.sglogin.entity.SysDataBook;
import org.sg.lf.sglogin.util.IsPlayer;
import org.sg.lf.sglogin.util.LoginPlayer;

public class SysDataBookCommand {
	
	private SysDataBookDao sysDataBookDao=new SysDataBookImpl();
	
	public boolean sysDataBookHandle(CommandSender sender,Command command,String label,String[] args) {
		String username=null;
		if(sender instanceof Player) {
			Player player=(Player)sender;
			username=player.getName();
			if(player.hasPermission("sglogin.admin.plyerreason")==false) {
				player.sendMessage("你没有执行该指令的权限!");
				return false;
			}
		} else {
			username="admin";
		}
		String param=args.length>=2?args[1]:"";
		switch (param) {
		case "":
			//获取封号理由
			getReason(sender);
			break;
		case "add":
			//添加封号理由
			addReason(sender,username,args);
			break;
		case "update":
			//修改封号理由
			updateReason(sender,username,args);
			break;
		case "del":
			//删除封号理由
			delReason(sender,args);
			break;
		default:
			IsPlayer.SayMassageColor(sender, "指令格式错误，正确应为：sglogin reason [add/update/del](详情sglogin help)");
			break;
		}
		return false;
	}
	
	private void getReason(CommandSender sender) {
		IsPlayer.SayMassage(sender, "----------------------------------------------");
		for(SysDataBook sysDataBook:sysDataBookDao.getSysDataBookByList()) {
			IsPlayer.SayMassageColor(sender, sysDataBook.getId()+"."+sysDataBook.getValue());
		}
	}
	
	private void addReason(CommandSender sender,String username,String[] args) {
		if(args.length>=3) {
			SysDataBook sysDataBook=new SysDataBook();
			sysDataBook.setValue(args[2]);
			IsLogin isLogin=LoginPlayer.getPlayerForOne(username);
			if(isLogin==null) {
				sysDataBook.setCreateplayerid("13945b1e-8957-4958-b7f8-74ba09516eda");
			} else {
				sysDataBook.setCreateplayerid(isLogin.getPlayerId());
			}
			if(sysDataBookDao.addSysDataBook(sysDataBook)>0) {
				IsPlayer.SayMassageColor(sender, "添加理由成功！");
			} else {
				IsPlayer.SayMassageColor(sender, "添加失败，请检查！");
			}
		} else {
			IsPlayer.SayMassageColor(sender, "指令格式错误，正确应为：sglogin reason add [理由]");
		}
	}
	
	private void updateReason(CommandSender sender,String username,String[] args) {
		if(args.length>=4) {
			SysDataBook sysDataBook=new SysDataBook();
			sysDataBook.setId(sysDataBookDao.getIdByLimit(Integer.parseInt(args[2])));
			sysDataBook.setValue(args[3]);
			IsLogin isLogin=LoginPlayer.getPlayerForOne(username);
			if(isLogin==null) {
				sysDataBook.setUpdateplayerid("13945b1e-8957-4958-b7f8-74ba09516eda");
			} else {
				sysDataBook.setUpdateplayerid(isLogin.getPlayerId());
			}
			if(sysDataBookDao.updateSysDataBook(sysDataBook)>0) {
				IsPlayer.SayMassageColor(sender, "修改理由成功！");
			} else {
				IsPlayer.SayMassageColor(sender, "修改失败，请检查！");
			}
		} else {
			IsPlayer.SayMassageColor(sender, "指令格式错误，正确应为：sglogin reason add [ID] [理由]");
		}
	}
	
	private void delReason(CommandSender sender,String[] args) {
		if(args.length>=3) {
			if(sysDataBookDao.deleteSysDataBook(sysDataBookDao.getIdByLimit(Integer.parseInt(args[2])))>0) {
				IsPlayer.SayMassageColor(sender, "删除理由成功！");
			} else {
				IsPlayer.SayMassageColor(sender, "删除失败，请检查！");
			}
		} else {
			IsPlayer.SayMassageColor(sender, "指令格式错误，正确应为：sglogin reason del [id]");
		}
	}
	
}

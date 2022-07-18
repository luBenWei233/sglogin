package org.sg.lf.sglogin.util;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IsPlayer {
	public static void SayMassage(CommandSender sender,String message) {
		if(sender instanceof Player) {
			((Player) sender).getPlayer().sendMessage(message);
		}else {
			System.out.println(message);
		}
	}
	
	public static void SayMassageColor(CommandSender sender,String message) {
		if(sender instanceof Player) {
			((Player) sender).getPlayer().sendMessage("§a[§csglogin§a]§2"+message);
		}else {
			System.out.println(message);
		}
	}
}

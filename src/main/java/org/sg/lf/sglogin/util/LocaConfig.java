package org.sg.lf.sglogin.util;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.sg.lf.sglogin.SGLoginMain;

public class LocaConfig {
	private static Plugin plugin=SGLoginMain.getPlugin(SGLoginMain.class);
	private static FileConfiguration config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "location.yml"));
	private static Boolean enableLoginLoca = config.getBoolean("enableLoginLoca");
	private static Boolean playerLoca = config.getBoolean("playerLoca");
	private static String loginLoca = config.getString("loginLoca");
	private static String beforeLoca = config.getString("beforeLoca");
	private static String playerSpawnAlert = config.getString("playerSpawnAlert");
	private static String playerOffLineAlert = config.getString("playerOffLineAlert");
	public static FileConfiguration getConfig() {
		return config;
	}
	public static Plugin getPlugin() {
		return plugin;
	}
	public static Boolean getEnableLoginLoca() {
		return enableLoginLoca;
	}
	public static Boolean getPlayerLoca() {
		return playerLoca;
	}
	public static Location getLoginLoca() {
		if(loginLoca!=null) {
			String[] loca=loginLoca.split(":");
			World world=Bukkit.getWorld(loca[0]);
			Double x=Double.valueOf(loca[1]);
			Double y=Double.valueOf(loca[2]);
			Double z=Double.valueOf(loca[3]);
			Float yaw=Float.valueOf(loca[4]);
			Float pitch=Float.valueOf(loca[5]);
			Location location=new Location(world,x,y,z,yaw,pitch);
			return location;
		}else {
			return null;
		}
	}
	public static Location getBeforeLoca() {
		if(loginLoca!=null) {
			String[] loca=beforeLoca.split(":");
			World world=Bukkit.getWorld(loca[0]);
			Double x=Double.valueOf(loca[1]);
			Double y=Double.valueOf(loca[2]);
			Double z=Double.valueOf(loca[3]);
			Float yaw=Float.valueOf(loca[4]);
			Float pitch=Float.valueOf(loca[5]);
			Location location=new Location(world,x,y,z,yaw,pitch);
			return location;
		}else {
			return null;
		}
	}
	public static String getPlayerSpawnAlert() {
		return playerSpawnAlert;
	}
	public static String getPlayerOffLineAlert() {
		return playerOffLineAlert;
	}
	public static void setEnableLoginLoca(Boolean enableLoginLoca) {
		LocaConfig.enableLoginLoca = enableLoginLoca;
	}
	public static void setPlayerLoca(Boolean playerLoca) {
		LocaConfig.playerLoca = playerLoca;
	}
	public static void setLoginLoca(String loginLoca) {
		LocaConfig.loginLoca = loginLoca;
	}
	public static void setBeforeLoca(String beforeLoca) {
		LocaConfig.beforeLoca = beforeLoca;
	}
	public static String getTpWorld() {
		if(loginLoca!=null) {
			String[] loca=loginLoca.split(":");
			return loca[0];
		}else {
			return null;
		}
	}
	
	public static void reload() {
		plugin.reloadConfig();
		config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "location.yml"));
		enableLoginLoca = config.getBoolean("enableLoginLoca");
		playerLoca = config.getBoolean("playerLoca");
		loginLoca = config.getString("loginLoca");
		beforeLoca = config.getString("beforeLoca");
		playerSpawnAlert = config.getString("playerSpawnAlert");
		playerOffLineAlert = config.getString("playerOffLineAlert");
	}
}

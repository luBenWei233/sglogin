package org.sg.lf.sglogin.util;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.sg.lf.sglogin.SGLoginMain;

public class DataBaseConfig {
	private static Plugin plugin=SGLoginMain.getPlugin(SGLoginMain.class);
	private static FileConfiguration config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "database.yml"));
	private static String dataName=config.getString("dataName").toLowerCase();
	private static String url=config.getString("url");
	private static String port=config.getString("port");
	private static String database=config.getString("database");
	private static String username=config.getString("username");
	private static String password=config.getString("password");
	private static String poolType=config.getString("poolType");
	private static Integer poolMinCount=config.getInt("poolMinCount");
	private static Integer poolMaxCount=config.getInt("poolMaxCount");
	private static Boolean ssl = config.getBoolean("ssl");
	public static String getDataName() {
		return dataName;
	}
	public static String getUrl() {
		return url;
	}
	public static String getPort() {
		return port;
	}
	public static String getDatabase() {
		return database;
	}
	public static String getUsername() {
		return username;
	}
	public static String getPassword() {
		return password;
	}
	public static String getPoolType() {
		return poolType;
	}
	public static Integer getPoolMinCount() {
		return poolMinCount;
	}
	public static Integer getPoolMaxCount() {
		return poolMaxCount;
	}
	public static Boolean getSsl() {
		return ssl;
	}
	
	public static void reload() {
		plugin.reloadConfig();
		config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "database.yml"));
		dataName=config.getString("dataName").toLowerCase();
		url=config.getString("url");
		port=config.getString("port");
		database=config.getString("database");
		username=config.getString("username");
		password=config.getString("password");
		poolType=config.getString("poolType");
		poolMinCount=config.getInt("poolMinCount");
		poolMaxCount=config.getInt("poolMaxCount");
		ssl = config.getBoolean("ssl");
	}
}

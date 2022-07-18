package org.sg.lf.sglogin.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.sg.lf.sglogin.SGLoginMain;

public class CommandConfig {
	private static Plugin plugin=SGLoginMain.getPlugin(SGLoginMain.class);
	private static FileConfiguration config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "command.yml"));
	private static Map<String,Object> commands=config.getValues(true);
	private static Boolean enableCommandDisable=config.getBoolean("enableCommandDisable");
	public static Map<String, String[]> getCommands() {
		Map<String, String[]> map=new HashMap<String,String[]>();
		for(Map.Entry<String, Object> com:commands.entrySet()) {
			String[] os = com.getValue().toString().replace("[", "").replace("]", "").split(",");
			map.put(com.getKey(), os);
		}
		return map;
	}
	public static Boolean getEnableCommandDisable() {
		return enableCommandDisable;
	}
	
	public static void reload() {
		plugin.reloadConfig();
		config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "command.yml"));
		commands=config.getValues(true);
		enableCommandDisable=config.getBoolean("enableCommandDisable");
	}
}

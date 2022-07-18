package org.sg.lf.sglogin;

import java.io.InputStream;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.sg.lf.sglogin.command.ChangePwd;
import org.sg.lf.sglogin.command.Login;
import org.sg.lf.sglogin.command.Register;
import org.sg.lf.sglogin.command.admin.SGAdmin;
import org.sg.lf.sglogin.command.email.EmailUser;
import org.sg.lf.sglogin.command.recover.RecoverPwd;
import org.sg.lf.sglogin.dao.impl.SGLoginImpl;
import org.sg.lf.sglogin.entity.IsLogin;
import org.sg.lf.sglogin.event.SGPlayerEvent;
import org.sg.lf.sglogin.event.SGPlayerLoginMassage;
import org.sg.lf.sglogin.event.SGPlayerTryLogin;
import org.sg.lf.sglogin.task.ClearData;
import org.sg.lf.sglogin.task.DeleteIpLimit;
import org.sg.lf.sglogin.util.CheckDataBase;
import org.sg.lf.sglogin.util.DataBaseConfig;
import org.sg.lf.sglogin.util.LoginPlayer;
import org.sg.lf.sglogin.util.LoginTempBan;
import org.sg.lf.sglogin.util.MyFileUtil;
/**
 * @author 卢本伟
 * @date 2020-6-2 16:00:00
 * @description 我的世界SGLogin登陆插件
 */

public class SGLoginMain extends JavaPlugin{

	@Override
	public void onDisable() {
		getLogger().info(MyFileUtil.title()+"登陆插件已卸载！");
	}

	@Override
	public void onEnable() {
		getLogger().info(MyFileUtil.title()+"登陆插件已启用！");
		//将已经登陆的玩家放到缓存中
		LoginPlayer.getPlayerInfo();
		LoginTempBan.getTempBan();
		for(Player p:Bukkit.getServer().getOnlinePlayers()) {
			IsLogin il=new IsLogin();
			il.setAlreadyLogin(false);
			il.setCount(0);
			il.setIp(p.getAddress().getHostString());
			LoginPlayer.setPlayerInfo(p.getName(), il);
		}
		saveDefaultConfig();
		getConfig().options().copyDefaults(true);
		InputStream input3 = SGLoginMain.class.getResourceAsStream("/database.yml"); 
		if(MyFileUtil.writeToLocal("plugins/SGLogin/database.yml",input3)==false) {
			getLogger().info(MyFileUtil.title()+"database.yml文件未生成！");
		}
		InputStream input4 = SGLoginMain.class.getResourceAsStream("/command.yml"); 
		if(MyFileUtil.writeToLocal("plugins/SGLogin/command.yml",input4)==false) {
			getLogger().info(MyFileUtil.title()+"command.yml文件未生成！");
		}
		if("mysql".equals(DataBaseConfig.getDataName())==true) {
			if(new CheckDataBase().getTable()==false) {
				getLogger().info(MyFileUtil.title()+"数据表不存在或创建失败，请检查数据库或查看Config文件！");
				shutDown();
			}else {
				getLogger().info(MyFileUtil.title()+"数据库连接正常！");
			}
		}else {
			InputStream input = SGLoginMain.class.getResourceAsStream("/sglogin.sqlite3"); 
			if(MyFileUtil.writeToLocal("plugins/SGLogin/sglogin.sqlite3",input)==true) {
				getLogger().info(MyFileUtil.title()+"Sqlite3文件正常！");
			}else {
				getLogger().info(MyFileUtil.title()+"Sqlite3文件创建失败！");
				shutDown();
			}
		}
		InputStream input = SGLoginMain.class.getResourceAsStream("/email.yml"); 
		if(MyFileUtil.writeToLocal("plugins/SGLogin/email.yml",input)==false) {
			getLogger().info(MyFileUtil.title()+"email.yml文件未生成！");
		}
		InputStream input2 = SGLoginMain.class.getResourceAsStream("/location.yml"); 
		if(MyFileUtil.writeToLocal("plugins/SGLogin/location.yml",input2)==false) {
			getLogger().info(MyFileUtil.title()+"location.yml文件未生成！");
		}
		
		getServer().getPluginManager().registerEvents(new SGPlayerEvent(), this);
		getServer().getPluginManager().registerEvents(new SGPlayerTryLogin(), this);
		new SGPlayerLoginMassage(this);
		new ClearData().runTaskTimer(this, 864000, 864000);
		new DeleteIpLimit().runTaskTimer(this, 86400000, 86400000);
		getCommand("reg").setExecutor(new Register());
		getCommand("l").setExecutor(new Login());
		getCommand("register").setExecutor(new Register());
		getCommand("login").setExecutor(new Login());
		getCommand("changepassword").setExecutor(new ChangePwd());
		getCommand("sglogin").setExecutor(new SGAdmin());
		getCommand("email").setExecutor(new EmailUser());
		getCommand("recover").setExecutor(new RecoverPwd());
		new SGLoginImpl().initUserState();
	}
	
	private void shutDown() {
		getLogger().info(MyFileUtil.title()+"即将关闭服务器！");
		Bukkit.shutdown();
	}
}

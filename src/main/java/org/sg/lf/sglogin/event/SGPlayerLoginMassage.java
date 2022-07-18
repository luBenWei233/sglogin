package org.sg.lf.sglogin.event;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.sg.lf.sglogin.SGLoginMain;
import org.sg.lf.sglogin.dao.impl.SGLoginImpl;
import org.sg.lf.sglogin.entity.IsLogin;
import org.sg.lf.sglogin.entity.User;
import org.sg.lf.sglogin.task.LoginMassage;
import org.sg.lf.sglogin.util.LocaConfig;
import org.sg.lf.sglogin.util.LoginPlayer;
import org.sg.lf.sglogin.util.MainConfig;

public class SGPlayerLoginMassage implements Listener{
	
	private final SGLoginMain plugin;

    public SGPlayerLoginMassage(SGLoginMain plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
	//玩家加入服务器
	@EventHandler
	public void playerJoinEvent(PlayerJoinEvent event) {
		Player player=event.getPlayer();
		if(LocaConfig.getEnableLoginLoca()==true) {
			World world=Bukkit.getWorld(LocaConfig.getTpWorld());
			if(world!=null && LocaConfig.getLoginLoca()!=null) {
				player.teleport(LocaConfig.getLoginLoca());
			}
		}
		User user=new User();
		user.setUsername(player.getName().toLowerCase());
		IsLogin isLogin=new IsLogin();
		isLogin.setIp(event.getPlayer().getAddress().getAddress().getHostAddress());
		LoginPlayer.setPlayerInfo(player.getName(), isLogin);
		String msg="";
		if(new SGLoginImpl().isExistUser(user)>0) {
			//玩家登陆时的登陆提示 §
			msg=MainConfig.getPlayerLoginAlert();
		}else {
			//玩家登陆时的注册提示
			msg=MainConfig.getPlayerRegisterAlert();
		}
		//玩家登陆时注册或登陆提示的间隔时间和提示次数
		new LoginMassage(player,msg,MainConfig.getPlayerLoginCount()).runTaskTimer(this.plugin, 20,MainConfig.getPlayerLoginTime());
	}
}

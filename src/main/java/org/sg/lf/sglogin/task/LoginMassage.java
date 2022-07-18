package org.sg.lf.sglogin.task;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.sg.lf.sglogin.entity.IsLogin;
import org.sg.lf.sglogin.util.LoginPlayer;
import org.sg.lf.sglogin.util.MainConfig;

public class LoginMassage extends BukkitRunnable{
	
	private final Player player;
	private final String msg;
	private int count;

    public LoginMassage(Player player,String msg,int count) {
        this.player = player;
        this.msg = msg;
        this.count=count;
        if(count<=0) {
        	this.count=1;
        }
    }

	//登陆或注册时提示的话
	@Override
	public void run() {
		IsLogin isLogin=LoginPlayer.getPlayerInfo().get(player.getName());
		if(isLogin!=null) {
			if(isLogin.getAlreadyLogin()==true) {
				this.cancel();
			}else {
				if(count>0) {
					player.sendMessage(msg);
					count--;
				}else {
					//规定时间未登陆或注册提示
					player.kickPlayer(MainConfig.getPlayerNotLoginAlert());
					this.cancel();
				}
			}
		}else {
			this.cancel();
		}
	}
}

package org.sg.lf.sglogin.event;

import java.util.Date;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.sg.lf.sglogin.dao.impl.PlayerLogImpl;
import org.sg.lf.sglogin.dao.impl.SGLoginImpl;
import org.sg.lf.sglogin.entity.IsLogin;
import org.sg.lf.sglogin.entity.PlayerLog;
import org.sg.lf.sglogin.entity.TempBan;
import org.sg.lf.sglogin.entity.User;
import org.sg.lf.sglogin.util.CommandConfig;
import org.sg.lf.sglogin.util.DateUtil;
import org.sg.lf.sglogin.util.LocaConfig;
import org.sg.lf.sglogin.util.LoginPlayer;
import org.sg.lf.sglogin.util.LoginTempBan;
import org.sg.lf.sglogin.util.MainConfig;
import org.sg.lf.sglogin.util.MyFileUtil;

public class SGPlayerEvent implements Listener{
	
	
	//未登陆玩家禁止移动
	@EventHandler
	public void playerMoveEvent(PlayerMoveEvent event) {
		IsLogin isLogin=LoginPlayer.getPlayerInfo().get(event.getPlayer().getName());
		if(isLogin.getAlreadyLogin()==false) {
			Location location=event.getFrom();
			Location locationTo=event.getTo();
			location.setYaw(locationTo.getYaw());
			location.setPitch(locationTo.getPitch());
			event.setTo(location);
		}
	}
	
	//玩家被踢出
	@EventHandler
	public void playerKickEvent(PlayerKickEvent event) {
		IsLogin isLogin=LoginPlayer.getPlayerInfo().get(event.getPlayer().getName());
		disLogin(event,isLogin);
		if(isLogin!=null) {
			LoginPlayer.setPlayerInfo(event.getPlayer().getName());
			System.out.println(LoginPlayer.getPlayerInfo().get(event.getPlayer().getName()));
		}
		
	}
	
	//玩家退出游戏
	@EventHandler
	public void playerQuitEvent(PlayerQuitEvent event) {
		IsLogin isLogin=LoginPlayer.getPlayerInfo().get(event.getPlayer().getName());
		disLogin(event,isLogin);
		if(isLogin!=null) {
			LoginPlayer.setPlayerInfo(event.getPlayer().getName());
		}
	}
	
	//玩家离开游戏时，要删除的HashMap
	private void disLogin(PlayerEvent event,IsLogin isLogin) {
		//保存玩家退出游戏时的世界和坐标
		if(LocaConfig.getPlayerLoca()==true) {
			if(isLogin!=null && isLogin.getAlreadyLogin()==true) {
				Player player=event.getPlayer();
				Location loca=player.getLocation();
				String world=player.getWorld().getName();
				User user=new User();
				user.setUsername(player.getName().toLowerCase());
				user.setX(loca.getX());
				user.setY(loca.getY());
				user.setZ(loca.getZ());
				user.setYaw(loca.getYaw());
				user.setPitch(loca.getPitch());
				user.setWorld(world);
				user.setLastDate(isLogin.getLoginDate());
				user.setLastip(isLogin.getIp());
				Date exitDate=new Date();
				user.setExitDate(exitDate);
				user.setCountTime(DateUtil.getTwoDateSecond(isLogin.getLoginDate(), exitDate));
				try {
					if(new SGLoginImpl().updateUser(user)<=0) {
						System.out.println(MyFileUtil.title()+"玩家座标未保存");
					}
				}catch(Exception e) {
					System.out.println("玩家座标未保存");
					e.printStackTrace();
				}
				PlayerLog playerLog=new PlayerLog();
				playerLog.setPlayerid(isLogin.getPlayerId());
				playerLog.setIp(isLogin.getIp());
				playerLog.setLogindate(isLogin.getLoginDate());
				playerLog.setExitdate(exitDate);
				playerLog.setLogintime(user.getCountTime());
				new PlayerLogImpl().insertPlayerLog(playerLog);
			}
		}
		String ip=event.getPlayer().getAddress().getAddress().getHostAddress();
		String playerName="l"+event.getPlayer().getName()+ip;
		TempBan tb=LoginTempBan.getTempBanForOne(playerName);
		if(tb!=null) {
			//玩家在规定时间内的最大退出游戏次数的时间
			if(new Date().compareTo(DateUtil.getAddDate(tb.getBanStartDate(), MainConfig.getPlayerExitTime()))>=0) {
				LoginTempBan.removeTempBan(playerName);
			}else {
				tb.setErrorCount(tb.getErrorCount()+1);
				//玩家在规定时间内的最大退出游戏的次数
				if(tb.getErrorCount()>=MainConfig.getPlayerExitCount()) {
					//玩家在规定时间内的最大退出游戏次数的时间
					tb.setBanEndDate(DateUtil.getAddDate(tb.getBanStartDate(), MainConfig.getPlayerExitTime()));
					//加入或退出游戏过于频繁
					tb.setReason(MainConfig.getPlayerExitAlert());
				}
				LoginTempBan.setTempBan(playerName, tb);
			}
		}else {
			tb=new TempBan();
			tb.setPlayerName(playerName);
			tb.setIp(ip);
			tb.setBanStartDate(new Date());
			tb.setErrorCount(1);
			LoginTempBan.setTempBan(playerName, tb);
		}
		
		//删除没用的IP ip127.0.0.1
		if(MainConfig.getPlayerJoinIpOn()==true) {
			TempBan ipJoin=LoginTempBan.getTempBanForOne("ip"+ip);
			if(ipJoin!=null) {
				if(ipJoin.getBanEndDate()!=null) {
					if(new Date().compareTo(ipJoin.getBanEndDate())>=0) {
						LoginTempBan.setTempBan("ip"+ip);
					}
				}
			}
		}
	}
	
	//玩家丢出物品
	@EventHandler
	public void playerDropItemEvent(PlayerDropItemEvent event) {
		IsLogin isLogin=LoginPlayer.getPlayerInfo().get(event.getPlayer().getName());
		if(isLogin.getAlreadyLogin()==false) {
			event.setCancelled(true);
		}
	}
	
	//玩家对物品交互
	@EventHandler
	public void playerInteractEvent(PlayerInteractEvent event) {
		IsLogin isLogin=LoginPlayer.getPlayerInfo().get(event.getPlayer().getName());
		if(isLogin.getAlreadyLogin()==false) {
			event.setCancelled(true);
		}
	}
	
	//玩家点击物品栏物品
	@EventHandler
	public void inventoryOpenEvent(InventoryClickEvent event) {
		IsLogin isLogin=LoginPlayer.getPlayerInfo().get(event.getWhoClicked().getName());
		if(isLogin.getAlreadyLogin()==false) {
			event.setCancelled(true);
		}
	}
	
	//玩家向小合成栏放置物品时关闭背包
	@EventHandler
	public void prepareItemCraftEvent(PrepareItemCraftEvent event) {
		Player player=(Player) event.getInventory().getHolder();
		IsLogin isLogin=LoginPlayer.getPlayerInfo().get(player.getName());
		if(isLogin.getAlreadyLogin()==false) {
			player.closeInventory();
		}
	}
	
	//当玩家未登陆时输入一些非登陆用的指令
	@EventHandler
	public void playerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
		Player player=event.getPlayer();
		IsLogin isLogin=LoginPlayer.getPlayerInfo().get(player.getName());
		if(isLogin.getAlreadyLogin()==false) {
			String args[]=event.getMessage().replace("/", "").toLowerCase().split(" ");
			if(!"reg".equals(args[0])) {
				if(!"l".equals(args[0])) {
					if(!"login".equals(args[0])) {
						if(!"register".equals(args[0])) {
							if(!"recover".equals(args[0])) {
								//非登陆玩家输入非登陆或注册指令时的提示
								player.sendMessage(MainConfig.getPlayerNotLoginInputCommand());
								event.setCancelled(true);
							}
						}
					}
				}
			}
		}else {
			if(CommandConfig.getEnableCommandDisable()==true) {
				String args=event.getMessage();
				String world=player.getWorld().getName();
				for(Map.Entry<String, String[]> com:CommandConfig.getCommands().entrySet()) {
					if(com.getKey().equals(world)) {
						for(String str:com.getValue()) {
							if(str.indexOf(args)!=-1) {
								player.sendMessage("§4本世界不可以输入此命令！");
								event.setCancelled(true);
							}
						}
					}
				}
			}
		}
	}
	
	//当未登陆的玩家要说话时，取消事件
	@EventHandler
	public void asyncPlayerChatEvent(AsyncPlayerChatEvent event) {
		IsLogin isLogin=LoginPlayer.getPlayerInfo().get(event.getPlayer().getName());
		if(isLogin.getAlreadyLogin()==false) {
			event.setCancelled(true);
		}
	}
	
	//登陆之前不会受到伤害
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
    	Entity entity = event.getEntity();
        if (event instanceof Player) {
        	IsLogin isLogin=LoginPlayer.getPlayerInfo().get(entity.getName());
    		if(isLogin.getAlreadyLogin()==false) {
    			event.setCancelled(true);
    		}
        }
    }
}

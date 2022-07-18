package org.sg.lf.sglogin.task;

import org.bukkit.scheduler.BukkitRunnable;
import org.sg.lf.sglogin.dao.impl.IpLimitImpl;

public class DeleteIpLimit extends BukkitRunnable{

	@Override
	public void run() {
		System.out.println("[sglogin]开始清理无效IP数据！");
		int count=new IpLimitImpl().deleteIpLimit();
		System.out.println("[sglogin]清理无效IP数据："+count+" 条！");
	}

}

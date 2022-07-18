package org.sg.lf.sglogin.task;

import java.util.Date;
import java.util.Map;

import org.bukkit.scheduler.BukkitRunnable;
import org.sg.lf.sglogin.entity.TempBan;
import org.sg.lf.sglogin.util.DateUtil;
import org.sg.lf.sglogin.util.LoginTempBan;

public class ClearData extends BukkitRunnable{

	@Override
	public void run() {
		System.out.println("[sglogin]开始清理无效数据！");
		Map<String,TempBan> litb=LoginTempBan.getTempBan();
		for(Map.Entry<String, TempBan> entry : litb.entrySet()){
			if(entry.getValue().getBanEndDate()!=null) {
				if(new Date().compareTo(entry.getValue().getBanEndDate())>=1) {
					LoginTempBan.setTempBan(entry.getKey());
				}
				continue;
			}
			if(entry.getValue().getBanStartDate()!=null) {
				if(entry.getValue().getBanStartDate().compareTo(DateUtil.getAddDate(entry.getValue().getBanStartDate(), 43200))>=1) {
					LoginTempBan.setTempBan(entry.getKey());
				}
			}
		}
		System.out.println("[sglogin]清理无效数据完毕！");
	}
}

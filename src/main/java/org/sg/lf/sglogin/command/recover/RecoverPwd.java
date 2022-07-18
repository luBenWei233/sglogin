package org.sg.lf.sglogin.command.recover;

import java.util.Date;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sg.lf.sglogin.dao.impl.SGLoginImpl;
import org.sg.lf.sglogin.entity.IsLogin;
import org.sg.lf.sglogin.entity.TempBan;
import org.sg.lf.sglogin.entity.User;
import org.sg.lf.sglogin.util.CheckData;
import org.sg.lf.sglogin.util.DateUtil;
import org.sg.lf.sglogin.util.EmailConfig;
import org.sg.lf.sglogin.util.EmailUtil;
import org.sg.lf.sglogin.util.LoginPlayer;
import org.sg.lf.sglogin.util.LoginTempBan;
import org.sg.lf.sglogin.util.MainConfig;

public class RecoverPwd implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {
		if(sender instanceof Player) {
			Player player=(Player)sender;
			if(EmailConfig.getEnable()==false) {
				//邮件功能未开启，无法使用！！！
				player.sendMessage(EmailConfig.getEmailDisableAlert());
				return false;
			}
			IsLogin isLogin=LoginPlayer.getPlayerInfo().get(player.getName());
			if(isLogin.getAlreadyLogin()==true) {
				//您已经登陆了，不用发送邮件来修改密码了
				player.sendMessage(EmailConfig.getEmailLoginSendAlert());
				return false;
			}
			SGLoginImpl sgli=new SGLoginImpl();
			if(args.length==1) {
				TempBan tbEmail=LoginTempBan.getTempBanForOne("email"+player.getName());
				if(tbEmail!=null) {
					if(new Date().compareTo(tbEmail.getBanEndDate())>0) {
						LoginTempBan.setTempBan("email"+player.getName());
					}else {
						player.sendMessage(EmailConfig.getEmailSendAlert());
						return false;
					}
				}
				User user=new User();
				user.setUsername(player.getName().toLowerCase());
				user=sgli.login(user);
				if(user!=null) {
					if(user.getEmail()!=null) {
						if(args[0].equals(user.getEmail())==true) {
							String content=EmailConfig.getEmailContent();
							if(content.contains("[date]")) {
								content=content.replace("[date]", DateUtil.getNowDate());
							}
							if(content.contains("[code]")) {
								String code=UUID.randomUUID().toString();
								if(code.contains("-") && code.length()>=15){
									code=code.replace("-", "").substring(0,10);
								}
								content=content.replace("[code]", code);
								TempBan tb=new TempBan();
								tb.setPlayerName(code);
								//验证码的超时时间，秒
								tb.setBanEndDate(DateUtil.getAddDate(new Date(), EmailConfig.getEmailCodeOverdueTime()));
								LoginTempBan.setTempBan("code"+player.getName(), tb);
							}
							
							if(EmailUtil.sendMail(args[0], EmailConfig.getEmailTitle(), content)) {
								//邮件发送成功，快快去邮箱查看吧！
								player.sendMessage(EmailConfig.getEmailSendSuccessAlert());
								if(tbEmail!=null) {
									tbEmail.setErrorCount(tbEmail.getErrorCount()+1);
									if(tbEmail.getErrorCount()>=EmailConfig.getEmailSendCount()) {
										tbEmail.setBanEndDate(DateUtil.getAddDate(tbEmail.getBanStartDate(), EmailConfig.getEmailSendTime()));
									}
								}else {
									tbEmail=new TempBan();
									tbEmail.setBanStartDate(new Date());
									tbEmail.setErrorCount(1);
								}
								LoginTempBan.setTempBan("email"+player.getName(), tbEmail);
							}else {
								//邮件发送失败，请重试或联系管理员！
								player.sendMessage(EmailConfig.getEmailSendFailAlert());
							}
						}else {
							//您输入的邮件和您绑定时的不一样，请检查后重试！
							player.sendMessage(EmailConfig.getEmailInputErrorAlert());
						}
					}
				}else {
					//您都没有注册，找回什么密码啊？
					player.sendMessage(EmailConfig.getEmailNotRegAlert());
				}
			}else if(args.length==3) {
				int key=CheckData.CheckPwd(args[1], args[2]);
				switch (key) {
				case 1:
					//密码格式不正确
					player.sendMessage(MainConfig.getPasserRoralert());
					return false;
				case 2:
					//两次密码输入错误
					player.sendMessage(MainConfig.getRegisterPassInconformity());
					return false;
				}
				String playerName="code"+player.getName();
				TempBan tb=LoginTempBan.getTempBanForOne(playerName);
				if(tb!=null) {
					if(!args[0].equals(tb.getPlayerName())) {
						tb.setErrorCount(tb.getErrorCount()+1);
						//您的验证码输入错误，还是复制邮件中的吧！
						player.sendMessage(EmailConfig.getEmailInputCodeErrorAlert());
						//您的码证码连续输错5次，现在已被系统踢出！
						if(tb.getErrorCount()%EmailConfig.getEmailInputCodeCount()==0) {
							player.kickPlayer(EmailConfig.getEmailInputCodeKickAlert());
						}
						return false;
					}
					if(tb.getBanEndDate()!=null) {
						//现有时间小于失效时间，那么才修改密码然后登陆！
						if(new Date().compareTo(tb.getBanEndDate())<0) {
							User user=new User();
							user.setUsername(player.getName().toLowerCase());
							user.setPassword(args[1]);
							if(sgli.updateUser(user)>=1) {
								//设置或删除
								LoginTempBan.setTempBan(playerName);
								IsLogin il=new IsLogin();
								il.setAlreadyLogin(true);
								LoginPlayer.setPlayerInfo(player.getName(), il);
								//密码重置成功，下次用本次修改的密码登陆哦
								player.sendMessage(EmailConfig.getEmailChangePwdSuccessAlert());
								//删除邮件缓存
								if(LoginTempBan.getTempBanForOne("email"+player.getName())!=null) {
									LoginTempBan.removeTempBan("email"+player.getName());
								}
							}else {
								//密码重置失败，请联系管理员！
								player.sendMessage(EmailConfig.getEmailChangePwdFailAlert());
							}
						}else {
							//您的验证码已经失效了！请重新发邮件找回！
							player.sendMessage(EmailConfig.getEmailCodeOverdueAlert());
						}
					}
				}else {
					//您还没有发送邮件或者验证码已经失效！
					player.sendMessage(EmailConfig.getEmailNotSendDoChangeAlert());
				}
			}else {
				//没有该邮件指令！
				player.sendMessage(EmailConfig.getEmailErrorCommand());
			}
		}else {
			System.out.println(MainConfig.getNotPlayer());
		}
		return false;
	}
	
}

package org.sg.lf.sglogin.util;

import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	public static boolean sendMail(String receiveMailAccount, String subject, String content){

		try {
	        Properties props = new Properties();
	        props.setProperty("mail.transport.protocol", "smtp");
	        props.setProperty("mail.smtp.host", EmailConfig.getMailHost());
	        props.setProperty("mail.smtp.auth", "true");
	        
	        final String smtpPort = EmailConfig.getEmailSmtpPort();
	        props.setProperty("mail.smtp.port", smtpPort);
	
	        if (EmailConfig.getSslAuthVerify()) {
	            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	            props.setProperty("mail.smtp.socketFactory.fallback", "false");
	            props.setProperty("mail.smtp.socketFactory.port", smtpPort);
	        }
	
	        String emailAccount = EmailConfig.getEmailAccount();
	        String emailPassword = EmailConfig.getEmailPwd();
	
	        Session session = Session.getInstance(props);
	
	        // 设置为debug模式, 查看详细的发送 log
	        session.setDebug(EmailConfig.getEnableDebug());
	
	        // 创建邮件
	        MimeMessage message = new MimeMessage(session);
	        
	        String osName = System.getProperty("os.name");//获取指定键（即os.name）的系统属性,如：Windows 7。
	        String code=null;
			  if (Pattern.matches("Linux.*", osName)) {
				  code="UTF-8";
			  } else if (Pattern.matches("Windows.*", osName)) {
				  code="gbk";
			  } else if (Pattern.matches("Mac.*", osName)) {
				  code="UTF-8";
			  }
	        message.setFrom(new InternetAddress(emailAccount, EmailConfig.getEmailTheme(), code));
	        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount, "", code));
	        message.setSubject(subject, code);
	        message.setContent(content, "text/html; charset="+code);
	        message.setSentDate(new Date());
	        message.saveChanges();
	
	        // 发送
	        Transport transport = session.getTransport();
	        transport.connect(emailAccount, emailPassword);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	        return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
    }
}

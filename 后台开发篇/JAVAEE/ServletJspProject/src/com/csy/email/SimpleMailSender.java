package com.csy.email;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 简单邮件（不带附件的邮件）发送器 发送邮件需要使用的基本信息 实现参考： 1.客户端通过Email发送验证码或者反馈信息的解决方案
 * https://github.com/forjunjian/AndroidEmail#android%E5%AE%A2%E6%88%B7%E7%AB%AF%E9%80%9A%E8%BF%87email%E5%8F%91%E9%80%81%E9%AA%8C%E8%AF%81%E7%A0%81%E6%88%96%E8%80%85%E5%8F%8D%E9%A6%88%E4%BF%A1%E6%81%AF%E7%9A%84%E8%A7%A3%E5%86%B3%E6%96%B9%E6%A1%88
 * 2.Java实现注册时发送激活邮件+激活
 * https://www.cnblogs.com/ganchuanpu/archive/2016/11/29/6115691.html
 * 3.Servlet向单人多人发送电子邮件http://www.runoob.com/servlet/servlet-sending-email.html
 * 4.163邮箱客户端授权密码怎么获得?
 */

public class SimpleMailSender {
	/**
	 * 以文本格式发送邮件
	 *
	 * @param mailInfo
	 *            待发送的邮件的信息
	 */
	public boolean sendTextMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		SimpleAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new SimpleAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			// Address from = new InternetAddress(mailInfo.getFromAddress());
			// 显示发件人昵称,而不是邮箱 InternetAddress(String address, String personal)
			// 这个方法，前面是发件人地址，后一个参数是你要设置的发件人名称
			Address from;
			try {
				//貌似设置昵称无效，在邮箱，设置，里面设置发件人名称即可
				//参照https://jingyan.baidu.com/article/020278118965ef1bcd9ce542.html
				from = new InternetAddress(mailInfo.getFromAddress(), "IT399技术网");
				// 设置邮件消息的发送者
				mailMessage.setFrom(from);
				//创建邮件的接收者地址，并设置到邮件消息中
				if (mailInfo.isSnedToAll()) {
					 //当前模式为群发
					 String[] mailToAddress = mailInfo.getToAddresses();  
				     int len = mailToAddress.length;  
				     Address to[] = new InternetAddress[len] ;  
				     for(int i=0;i<len;i++){  
				          to[i] = new InternetAddress(mailToAddress[i]) ;  
				     }  
					mailMessage.setRecipients(Message.RecipientType.TO, to);
				}else {
					//非群发
					Address to = new InternetAddress(mailInfo.getToAddress());
					mailMessage.setRecipient(Message.RecipientType.TO, to);
				}
				// 给自己也抄送一份，不然会被当做垃圾邮件 554 DT:SPM 163 smtp11
				//mailMessage.addRecipient(Message.RecipientType.CC, from);
				// 设置邮件消息的主题
				mailMessage.setSubject(mailInfo.getSubject());
				// 设置邮件消息发送的时间
				mailMessage.setSentDate(new Date());
				// 设置邮件消息的主要内容
				String mailContent = mailInfo.getContent();
				mailMessage.setText(mailContent);
				// 解决 no object DCH for MIME type multipart/mixed;
				MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
				mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
				mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
				mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
				mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
				mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
				CommandMap.setDefaultCommandMap(mc);
				// 发送邮件
				Transport.send(mailMessage);
				return true;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 以HTML格式发送邮件
	 *
	 * @param mailInfo
	 *            待发送的邮件信息
	 */
	public static boolean sendHtmlMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		SimpleAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			authenticator = new SimpleAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			// Address from = new InternetAddress(mailInfo.getFromAddress());
			// 显示发件人昵称,而不是邮箱 InternetAddress(String address, String personal)
			// 这个方法，前面是发件人地址，后一个参数是你要设置的发件人名称
			Address from;
			try {
				//貌似设置昵称无效，在邮箱，设置，里面设置发件人名称即可
				//参照https://jingyan.baidu.com/article/020278118965ef1bcd9ce542.html
				from = new InternetAddress(mailInfo.getFromAddress(), "IT399技术网");
				// 设置邮件消息的发送者
				mailMessage.setFrom(from);
				//创建邮件的接收者地址，并设置到邮件消息中
				if (mailInfo.isSnedToAll()) {
					//当前模式为群发
					 String[] mailToAddress = mailInfo.getToAddresses();  
				     int len = mailToAddress.length ;  
				     Address to[] = new InternetAddress[len] ;  
				     for(int i=0;i<len;i++){  
				          to[i] = new InternetAddress(mailToAddress[i]) ;  
				     }  
				}else {
					//非群发
					Address to = new InternetAddress(mailInfo.getToAddress());
					mailMessage.setRecipient(Message.RecipientType.TO, to);
				}
				// 设置邮件消息的主题
				mailMessage.setSubject(mailInfo.getSubject());
				// 给自己也抄送一份，不然会被当做垃圾邮件 554 DT:SPM 163 smtp11
				// mailMessage.addRecipient(Message.RecipientType.CC, from);
				// 设置邮件消息发送的时间
				mailMessage.setSentDate(new Date());
				// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
				Multipart mainPart = new MimeMultipart();
				// 创建一个包含HTML内容的MimeBodyPart
				BodyPart html = new MimeBodyPart();
				// 设置HTML内容
				html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
				mainPart.addBodyPart(html);
				// 将MiniMultipart对象设置为邮件内容
				mailMessage.setContent(mainPart);
				// 解决 no object DCH for MIME type multipart/mixed;
				MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
				mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
				mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
				mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
				mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
				mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
				CommandMap.setDefaultCommandMap(mc);
				// 发送邮件
				Transport.send(mailMessage);
				return true;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	
}
package com.csy.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csy.email.MailSenderInfo;
import com.csy.email.SimpleMailSender;

public class MailSend extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
		//发送邮件
		req.getAttribute("");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
		doGet(req, resp);
		
	}
	
	
	  /**
     * 发送邮件核心代码 ，部分操作请参考readme
     */
    public void preformSendEmail(String destEmailAddress, String title, String content) {
        MailSenderInfo mailInfo = new MailSenderInfo();
        // 设定邮箱代理服务器 网易的163代理为smtp.163.com
        mailInfo.setMailServerHost("smtp.163.com");
        // 设定邮箱代理服务器端口号
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        // 参考readme获取网易客户端授权密码,这里不能为空
        String username = null;
        String password = null;
        if (password == null || username == null) {
            throw new RuntimeException("请到网易申请，具体参考readme，这里帐号密码不能为空！");
        }
        //163邮箱账户名
        mailInfo.setUserName(username);
        // 您的邮箱密码或者客户端授权密码
        mailInfo.setPassword(password);
        // 默认可以没有，显示在对方邮件的抬头
        mailInfo.setFromAddress(username + "@163.com");
        mailInfo.setToAddress(destEmailAddress);
        mailInfo.setSubject(title + new Date().toString());
        mailInfo.setContent(content);
        // 这个类主要来发送邮件
        SimpleMailSender sms = new SimpleMailSender();
        sms.sendTextMail(mailInfo);// 发送文体格式
//		sms.sendHtmlMail(mailInfo);// 发送html格式
        System.out.println("sending success");
    }
}

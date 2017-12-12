package com.csy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csy.email.MailSenderInfo;
import com.csy.email.SimpleMailSender;
import com.csy.util.Constant;

public class MailSend extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 发送邮件
		String mail = req.getParameter("mail");
		String password = req.getParameter("password");
		String url = Constant.serverIp + getServletContext().getContextPath() + "/VeryCountEmail" + "?uuid="
				+ Constant.currentUserUuid;
		System.out.println("url:" + url);
		// 单发
		boolean flag = preformSendEmail(mail, "激活邮件", "此邮件为官方激活邮件,您已成功注册IT399科技网账户,请点击链接" + ":" + url + " ,激活账户！");

		// 群发
		// String[] destEmailAddresses = new
		// String[]{"xxxx@163.com","xxxxx@qq.com"};
		// boolean flag =
		// preformSendEmails(destEmailAddresses,"激活邮件","此邮件为官方激活邮件,您已成功注册IT399科技网账户,请点击链接:http://www.baidu.com
		// ,激活账户！");

		if (flag) {
			// 跳转到发送成功提示页面
			log("mail:发送成功");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/RegistSuccess.jsp");
			requestDispatcher.forward(req, resp);
		} else {
			// 发送失败,重新发送
			log("mail:发送失败");
			// resp.setCharacterEncoding("UTF-8");
			//GBK或者UTF-8都可以 不然js输出中文乱码
			resp.setContentType("text/html;charset=UTF-8");

			PrintWriter out = resp.getWriter();// 获得输出流
			//out.print("<script>" + "alert('注册失败,请输入正确的邮箱');" + "document.location.href="+req.getServletPath()+"'/Regist.jsp';" + "</script>");

			String msg="注册失败,请输入正确的邮箱";
			//String urlRelaod = req.getServletPath()+"/Regist.jsp";
			String urlRelaod = req.getServletContext().getContextPath()+"/Regist.jsp";
			out.println("<script>");
			out.println("alert('" + msg + "');");
			out.println("window.location='" + urlRelaod + "'");
			out.println("</script>");
			
			out.close();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	/**
	 * 发送邮件核心代码 ，单独发送
	 */
	public boolean preformSendEmail(String destEmailAddress, String title, String content) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		// 设定邮箱代理服务器 网易的163代理为smtp.163.com
		mailInfo.setMailServerHost("smtp.163.com");
		// 设定邮箱代理服务器端口号
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		// 参考readme获取网易客户端授权密码,这里不能为空
		String username = null ;// 不要带后缀
		String password = null ;
		if (password == null || username == null) {
			throw new RuntimeException("请到网易申请，具体参考readme，这里帐号密码不能为空！");
		}
		// 163邮箱账户名
		mailInfo.setUserName(username);
		// 您的邮箱密码或者客户端授权密码
		mailInfo.setPassword(password);
		// 显示在对方邮件的抬头
		mailInfo.setFromAddress(username + "@163.com");
		mailInfo.setToAddress(destEmailAddress);
		// mailInfo.setSubject(title + new Date().toString());
		mailInfo.setSubject(title);
		mailInfo.setContent(content);
		// 是否群发
		mailInfo.setSnedToAll(false);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		return sms.sendTextMail(mailInfo);// 发送文体格式
		// return sms.sendHtmlMail(mailInfo);// 发送html格式
	}

	/**
	 * 发送邮件核心代码 ，群发
	 */
	public boolean preformSendEmails(String[] destEmailAddresses, String title, String content) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		// 设定邮箱代理服务器 网易的163代理为smtp.163.com
		mailInfo.setMailServerHost("smtp.163.com");
		// 设定邮箱代理服务器端口号
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		// 参考readme获取网易客户端授权密码,这里不能为空
		String username = null;// 不要带后缀
		String password = null;
		if (password == null || username == null) {
			throw new RuntimeException("请到网易申请，具体参考readme，这里帐号密码不能为空！");
		}
		// 163邮箱账户名
		mailInfo.setUserName(username);
		// 您的邮箱密码或者客户端授权密码
		mailInfo.setPassword(password);
		// 显示在对方邮件的抬头
		mailInfo.setFromAddress(username + "@163.com");
		// 是否群发
		mailInfo.setSnedToAll(true);
		mailInfo.setToAddresses(destEmailAddresses);
		// mailInfo.setSubject(title + new Date().toString());
		mailInfo.setSubject(title);
		mailInfo.setContent(content);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		return sms.sendTextMail(mailInfo);// 发送文体格式
		// return sms.sendHtmlMail(mailInfo);// 发送html格式
	}
}

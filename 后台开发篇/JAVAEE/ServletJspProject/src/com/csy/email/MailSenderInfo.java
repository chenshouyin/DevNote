package com.csy.email;

import java.util.Properties;

/**
 * 发送邮件需要使用的基本信息
 * 实现参考：
	1.客户端通过Email发送验证码或者反馈信息的解决方案 https://github.com/forjunjian/AndroidEmail#android%E5%AE%A2%E6%88%B7%E7%AB%AF%E9%80%9A%E8%BF%87email%E5%8F%91%E9%80%81%E9%AA%8C%E8%AF%81%E7%A0%81%E6%88%96%E8%80%85%E5%8F%8D%E9%A6%88%E4%BF%A1%E6%81%AF%E7%9A%84%E8%A7%A3%E5%86%B3%E6%96%B9%E6%A1%88
	2.Java实现注册时发送激活邮件+激活 https://www.cnblogs.com/ganchuanpu/archive/2016/11/29/6115691.html
 	3.Servlet向单人多人发送电子邮件http://www.runoob.com/servlet/servlet-sending-email.html
 	4.163邮箱客户端授权密码怎么获得? 
 */
public class MailSenderInfo {
    // 发送邮件的服务器的IP和端口   
    private String mailServerHost;
    private String mailServerPort = "25";
    // 邮件发送者的地址   
    private String fromAddress;
    // 邮件接收者的地址   
    private String toAddress;
    // 群发邮件接收者的地址   
    private String[] toAddresses;
  
	//是否群发
    private boolean isSnedToAll = false;
    public boolean isSnedToAll() {
		return isSnedToAll;
	}

	public void setSnedToAll(boolean isSnedToAll) {
		this.isSnedToAll = isSnedToAll;
	}

	// 登陆邮件发送服务器的用户名和密码   
    private String userName;
    private String password;
    // 是否需要身份验证   
    private boolean validate = false;
    // 邮件主题   
    private String subject;
    // 邮件的文本内容   
    private String content;
    // 邮件附件的文件名   
    private String[] attachFileNames;

    /**
     * 获得邮件会话属性
     */
    public Properties getProperties() {
        Properties p = new Properties();
        p.put("mail.smtp.host", this.mailServerHost);
        p.put("mail.smtp.port", this.mailServerPort);
        p.put("mail.smtp.auth", validate ? "true" : "false");
        return p;
    }

    public String getMailServerHost() {
        return mailServerHost;
    }

    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    public String getMailServerPort() {
        return mailServerPort;
    }

    public void setMailServerPort(String mailServerPort) {
        this.mailServerPort = mailServerPort;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String[] getAttachFileNames() {
        return attachFileNames;
    }

    public void setAttachFileNames(String[] fileNames) {
        this.attachFileNames = fileNames;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String textContent) {
        this.content = textContent;
    }
    
    public String[] getToAddresses() {
		return toAddresses;
	}

	public void setToAddresses(String[] toAddresses) {
		this.toAddresses = toAddresses;
	}

	
}   
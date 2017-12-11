package com.csy.email;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 发送邮件需要使用的基本信息
 * 实现参考：
	1.客户端通过Email发送验证码或者反馈信息的解决方案 https://github.com/forjunjian/AndroidEmail#android%E5%AE%A2%E6%88%B7%E7%AB%AF%E9%80%9A%E8%BF%87email%E5%8F%91%E9%80%81%E9%AA%8C%E8%AF%81%E7%A0%81%E6%88%96%E8%80%85%E5%8F%8D%E9%A6%88%E4%BF%A1%E6%81%AF%E7%9A%84%E8%A7%A3%E5%86%B3%E6%96%B9%E6%A1%88
	2.Java实现注册时发送激活邮件+激活 https://www.cnblogs.com/ganchuanpu/archive/2016/11/29/6115691.html
 	3.Servlet向单人多人发送电子邮件http://www.runoob.com/servlet/servlet-sending-email.html
 	4.163邮箱客户端授权密码怎么获得? 
*/
public class SimpleAuthenticator extends Authenticator {
    String userName = null;
    String password = null;

    public SimpleAuthenticator() {
    }

    public SimpleAuthenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}

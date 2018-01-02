package com.email;

import java.io.File;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {

	
	public static void main(String args[]) throws Exception {
		Email email=new Email();


	       Session  session=Session.getInstance(email.getProperties(), email.GetAuthencator());
		
	       //设置邮件消息体
	       MimeMessage  message=new MimeMessage(session);
	       
	       //设置发送人
	       InternetAddress  userAddress=new InternetAddress(email.getProperties().getProperty("mail.user"));
	       message.setFrom(userAddress);
		
		   //设置接收人
	       InternetAddress  targetAddress=new InternetAddress(email.getProperties().getProperty("mail.target"));
	       message.setRecipient(RecipientType.TO, targetAddress);
	       
	       
/*
 * 上边的内容为获取mail.properties的信息,*需要配置好相应配置文件即可
 * 
 * 下边需自己配置：主题,主体（纯文字|附件,html）  //不需要File=null即可
 * 提示：html的内容也可以编辑为纯文本
 * */	       
	       
	       //设置主题
	       message.setSubject("这是测试主题");
	         
	       //Transport.send(message);
	       MimeBodyPart htmlBody=email.getHtml("htmlContent.html");   //这个是src目录下的html文件
	       //MimeBodyPart htmlBody=email.setHtml(new File("C:\\Users\\lilutong\\Desktop\\htmlContent.html"));  //本地编写的html文件
	       MimeBodyPart attachBody=email.setAattch(new File("C:\\Users\\lilutong\\Desktop\\中文.zip"));  //本地的附件
	      
	       //将附件和html添加在multpart中
	       MimeMultipart  multPart=new MimeMultipart("mixed");
	       multPart.addBodyPart(htmlBody);   
	       multPart.addBodyPart(attachBody);
	       
	       //主体二选1  第一个为纯文本  第二个为html和附件格式
	       //message.setContent("这是内容", "text/plain;charset=utf-8");  
	       message.setContent(multPart);
	       
	       Transport.send(message);
	}
}

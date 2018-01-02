package com.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.sun.mail.handlers.message_rfc822;
import com.sun.media.jfxmedia.events.NewFrameEvent;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xalan.internal.xsltc.trax.OutputSettings;
import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import jdk.nashorn.internal.ir.WhileNode;




public class Email {

	//获取本地配置信息
	public Properties getProperties() throws Exception {
		InputStream  inputStream=this.getClass().getClassLoader().getResourceAsStream("mail.properties");
		Properties  properties=new Properties();
        properties.load(inputStream);
        return properties;
        
	}
	
	//连接验证
	public Authenticator GetAuthencator() throws Exception {
		String user =this.getProperties().getProperty("mail.user");
		String password =this.getProperties().getProperty("mail.password");
		Authenticator authenticator = new Authenticator() {  
	            protected PasswordAuthentication getPasswordAuthentication() {  
	                return new PasswordAuthentication(user, password);  
	            }  
	    };
	    return authenticator;
	}
	
	
	//设置本地html
	public MimeBodyPart   setHtml(File file) throws Exception {
		MimeBodyPart  htmlBody=new MimeBodyPart();
        InputStream  inputStream=new FileInputStream(file);
        OutputStream  outputStream=new ByteOutputStream();
        byte b[]=new byte[1024];
        int size=0;
        while(inputStream.read(b)!=-1) {
        	outputStream.write(b, 0, size);
        }
        String  htmlContent= new String(b,"utf-8");
        htmlBody.setContent(htmlContent,"text/html;charset=utf-8");
        return htmlBody;
	}
	//这是src下默认的xml名字
	public MimeBodyPart   getHtml(String htmlName) throws Exception {
		MimeBodyPart  htmlBody=new MimeBodyPart();
        InputStream  inputStream=this.getClass().getClassLoader().getResourceAsStream(htmlName);
        OutputStream  outputStream=new ByteOutputStream();
        byte b[]=new byte[1024];
        int size=0;
        while(inputStream.read(b)!=-1) {
        	outputStream.write(b, 0, size);
        }
        String  htmlContent= new String(b,"utf-8");
        htmlBody.setContent(htmlContent,"text/html;charset=utf-8");
        return htmlBody;
	}
	
	
	
	//添加附件
	public MimeBodyPart  setAattch(File file) throws Exception {
		MimeBodyPart  attachBody=new MimeBodyPart();
		attachBody.setDataHandler(new DataHandler(new FileDataSource(file)));
		attachBody.setFileName(MimeUtility.encodeWord(file.getName()));
		return attachBody;
	}
	
	
	
}

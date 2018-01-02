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
		
	       //�����ʼ���Ϣ��
	       MimeMessage  message=new MimeMessage(session);
	       
	       //���÷�����
	       InternetAddress  userAddress=new InternetAddress(email.getProperties().getProperty("mail.user"));
	       message.setFrom(userAddress);
		
		   //���ý�����
	       InternetAddress  targetAddress=new InternetAddress(email.getProperties().getProperty("mail.target"));
	       message.setRecipient(RecipientType.TO, targetAddress);
	       
	       
/*
 * �ϱߵ�����Ϊ��ȡmail.properties����Ϣ,*��Ҫ���ú���Ӧ�����ļ�����
 * 
 * �±����Լ����ã�����,���壨������|����,html��  //����ҪFile=null����
 * ��ʾ��html������Ҳ���Ա༭Ϊ���ı�
 * */	       
	       
	       //��������
	       message.setSubject("���ǲ�������");
	         
	       //Transport.send(message);
	       MimeBodyPart htmlBody=email.getHtml("htmlContent.html");   //�����srcĿ¼�µ�html�ļ�
	       //MimeBodyPart htmlBody=email.setHtml(new File("C:\\Users\\lilutong\\Desktop\\htmlContent.html"));  //���ر�д��html�ļ�
	       MimeBodyPart attachBody=email.setAattch(new File("C:\\Users\\lilutong\\Desktop\\����.zip"));  //���صĸ���
	      
	       //��������html�����multpart��
	       MimeMultipart  multPart=new MimeMultipart("mixed");
	       multPart.addBodyPart(htmlBody);   
	       multPart.addBodyPart(attachBody);
	       
	       //�����ѡ1  ��һ��Ϊ���ı�  �ڶ���Ϊhtml�͸�����ʽ
	       //message.setContent("��������", "text/plain;charset=utf-8");  
	       message.setContent(multPart);
	       
	       Transport.send(message);
	}
}

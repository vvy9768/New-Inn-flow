package Listeners;


import java.io.File;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;




public class SendJavaMailReport {
	static String fileName;
	public SendJavaMailReport(String filename) {
		fileName=filename;
	
	}
	
	public  void main() {
		
	    Date d=new Date();
		String message="Hello Dear ! this is report of the day ";
		String subject ="Inn-Flow QA(Automation) : Reports "+d;
		//,"jyoti.rajpal@inn-flow.com","avinash.dubey@inn-flow.com"
		//String to[]={"jyoti.rajpal@inn-flow.com","avinash.dubey@inn-flow.com"};
		String to[]={"softech.vire0@yahoo.com"};
		//String to[]={"my702478@gmail.com","skgaud81199@gmail.com","kanha3476599@gmail.com"};
		String from="softech.vire@gmail.com";
		
		//String fileName="Reports/"+"AutomationReport_Wed_Jan_12_13_02_31_IST_2022.html";
		sendMail(message, subject, to, from);
		
	}
	
	public static void sendMail(String message,String subject,String to[],String from) {
		String host="smtp.gmail.com";
		Properties properties=System.getProperties();
		System.out.println("PROPERTIES"+properties);
		properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put( "mail.smtp.starttls.enable", true); 
		properties.put( "mail.debug", "true" );
		properties.put( "mail.smtp.socketFactory.port", 587 );
		//props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		properties.put( "mail.smtp.socketFactory.fallback", "false" );
		properties.put( "mail.smtp.ssl.trust", host );

		
		Session session=Session.getInstance(properties,new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
			
				return new PasswordAuthentication("softech.vire@gmail.com", "Softech0432!");
			}
						
			
	});
		session.setDebug(true);
		MimeMessage m=new MimeMessage(session);
		
		try {
		m.setFrom(from);
		m.setHeader("Emp.", "Suraj");
	  for(String reciver:to)
		m.addRecipient(Message.RecipientType.BCC, new InternetAddress(reciver,"Virendra Kumar Yadav"));
		
		m.setSubject(subject);
		
		m.setText(message);
		Date dt=new Date();
		String d=dt.toString().replace(":", "_").replace(" ", "_");
		
		m.setText("date",d );
		
		
		//m.setFileName(fileName);
		MimeBodyPart attachment = new MimeBodyPart();
        MimeBodyPart text = new MimeBodyPart();
        
		 File file = new File(fileName);
         attachment.attachFile(file);
       
         text.setText(message);
         //Set the actual message of the Email:
        
         Multipart mp = new MimeMultipart();
         mp.addBodyPart(text);
         mp.addBodyPart(attachment);
         m.setContent(mp);
         
		Transport.send(m);
		
		System.out.println(".....................  Sent  Success .......................!");
		
		}catch(Exception e)
		{
		e.printStackTrace();	
		}
		
		
		
	}
	
	
}

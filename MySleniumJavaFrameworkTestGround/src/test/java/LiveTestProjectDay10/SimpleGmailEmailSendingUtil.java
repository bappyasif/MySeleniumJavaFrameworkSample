package LiveTestProjectDay10;

import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.management.RuntimeErrorException;

import javassist.bytecode.stackmap.BasicBlock.Catch;

public class SimpleGmailEmailSendingUtil {

	static final String userID = "asifuzzamanbappy@gmail.com";
	static final String userPassword = "aBappy007";


	public static void main(String[] args) throws AddressException, MessagingException {

		CommenceSendingGmailEmail("asifuzzamanbappy@gmail.com",	"asifuzzamanbappy@gmail.com", 
				"Sending Gmail Email SSL", "Howdy! Receiving This Message I hope");

	}

	public static void CommenceSendingGmailEmail(String fromAddress,
			String toAddress, 
			String emailSubject, 
			String emailMessage) throws AddressException, MessagingException {

		// Getting properties object ready and loaded with SMTP values.
		Properties properties = new Properties();

		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		
		//properties.put("mail.smtp.socketFactory.port", "465");
		//properties.put("mail.smtp.socketFactory.class", "javx.net.ssl.SSLSocketFactory");
		//properties.put("mail.smtp.port", "465");

		
		// Apparently you don't need this block of code, java library has enriched enough to 
		// avoid these extended library codes
		
		//Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {

		//protected javax.mail.PasswordAuthentication getAuthentication() {

		//return new javax.mail.PasswordAuthentication(userID, userPassword);
		//	}	

		//});

		// Getting Session
		
		// Session session = Session.getInstance(properties); // This works as well.
		Session session = Session.getDefaultInstance(properties);

		// Composing Email
		MimeMessage gmailMessage = new MimeMessage(session);

		gmailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));

		gmailMessage.setSubject(emailSubject);

		gmailMessage.setFrom();

		gmailMessage.setText(emailMessage);

		// Sending Message
		Transport.send(gmailMessage, userID, userPassword);

		System.out.println("Message was sent Successfully...");

	}


}

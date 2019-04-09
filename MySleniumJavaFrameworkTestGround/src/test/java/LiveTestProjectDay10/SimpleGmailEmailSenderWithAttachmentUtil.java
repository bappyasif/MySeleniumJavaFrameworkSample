package LiveTestProjectDay10;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SimpleGmailEmailSenderWithAttachmentUtil {

	static final String userName = "asifuzzamanbappy@gmail.com";
	static final String userPassword = "aBappy007";

	static final String EMAIL_SERVER = "smtp.gmail.com";

	static final String emailSubject = "Sending an Email with Attachment";
	static final String emailText = "Howdy! Are You Getting This Email? Hope That You Do...";
	static final String attachmentFile = "C:\\Users\\BappY\\Downloads\\orders.csv";

	public static void main(String[] args) throws AddressException, MessagingException {

		SendingEmailWithAttachment("asifuzzamanbappy@gmail.com" , "asifuzzamanbappy@gmail.com");

	}


	public static void SendingEmailWithAttachment(String sendFrom, 
			String sendTo) throws AddressException, MessagingException {

		//filename = attachmentFile;

		// Getting properties object ready and loaded with SMTP values.
		Properties properties = new Properties();

		properties.put("mail.smtp.host", EMAIL_SERVER);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.prot", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail..smtp.STARTTLS.enable", "true");

		//properties.put("mail.tls", "true");
		//properties.put("mail.smtp.socketFactory.port", "465");
		//properties.put("mail.smtp.socketFactory.class", "javx.net.ssl.SSLSocketFactory");

		// Getting Session
		Session session = Session.getDefaultInstance(properties);
		
		//Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
		//}); // Just keeping it simple enough. You don't need extra layer of authentication as of now.


		// Creating Message
		MimeMessage gmailMessage = new MimeMessage(session);

		// Setting Email From Address
		gmailMessage.setFrom(new InternetAddress(sendFrom));

		// Setting Email Recipient Address
		gmailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));

		// Setting up email Subject
		gmailMessage.setSubject(emailSubject);

		// Here comes Attachment Part
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(emailText, "text/plain"); // loaded with email message body.

		// Creating a Multipart object to re construct our email structure once ready to transport.
		Multipart multiPart = new MimeMultipart();
		multiPart.addBodyPart(mimeBodyPart); // loaded with body text

		// Creating another mimeBodyPart object for attachment file.
		mimeBodyPart = new MimeBodyPart(); // allocated reference for attachment segment
		DataSource dataSource = new FileDataSource(attachmentFile);

		mimeBodyPart.setDataHandler(new DataHandler(dataSource));
		mimeBodyPart.setFileName(attachmentFile);

		multiPart.addBodyPart(mimeBodyPart); // loaded with attachment file
		gmailMessage.setContent(multiPart);

		//return gmailMessage;
		Transport.send(gmailMessage, userName, userPassword);

		System.out.println("Message Was Sent Successfully");

	}

}

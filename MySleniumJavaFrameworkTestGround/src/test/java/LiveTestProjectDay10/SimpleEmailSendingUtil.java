package LiveTestProjectDay10;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SimpleEmailSendingUtil {

	static String sendTo = "asifuzzamanbappy@gmail.com";
	static String sendFrom = "root@localhost.localdomain";

	static String emailServerHostName = "smtp.gmail.com";

	static String emailSubjct = "Simple Email Sending Test";
	static String emailBody = "Howdy partner! Receiving this email well and good?";

	static BodyPart emailBodyMessagePart;
	static Multipart multiPart = new MimeMultipart();
	static MimeMessage emailMessage;

	
	
	public static void main(String[] args) throws NoSuchProviderException {

		SendingEmail();

	}


	public static void SendingEmail() throws NoSuchProviderException {

		// Getting session ready for emaail.
		Properties properties = System.getProperties();

		// setting up mail server
		properties.setProperty("mail.smtp.host", emailServerHostName);
		properties.setProperty("mail.smtp.user", sendFrom);
		properties.setProperty("mail.password", "password");

		// getting default session object
		Session session = Session.getDefaultInstance(properties);

		// New Code
		//Transport transport = session.getTransport(emailServerHostName);

		// henceforth comes sending message part
		try {
			// Creating a default MimeMessage object
			emailMessage = new MimeMessage(session);

			// Setting From Header: from Address at header
			emailMessage.setFrom(new InternetAddress(sendFrom));

			// Setting To Header : from Address at message header.
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));

			// Setting Subject : From Message Header
			emailMessage.setSubject(emailSubjct);

			// Setting Message : For Email Body
			emailBodyMessagePart = new MimeBodyPart();

			//emailMessage.setText(emailBody); // This is a direct approach 
			emailBodyMessagePart.setText(emailBody); // This way we can use it for multipart messaging as well.

			// Setting text to message part
			multiPart.addBodyPart(emailBodyMessagePart);

			// Sending Message
			Transport.send(emailMessage);
			System.out.println("Email Text : " +emailMessage);
			System.out.println("Message Sent!!!");

			// attachment Section
			FileAttachmentInEmail();

		} catch (MessagingException ex) {
			// TODO: handle exception
			System.out.println("Couldn't Send Requested Email");
			ex.getStackTrace();
		}

	}


	private static void FileAttachmentInEmail() throws MessagingException {
		// TODO Auto-generated method stub

		// Creating a multipart message which is now declared in class
		//multiPart = new MimeMultipart();

		String attachmentFile = "user.home"+"\\Downloads\\csvOrders.csv";

		DataSource dataSource = new FileDataSource(attachmentFile);

		emailBodyMessagePart.setDataHandler(new DataHandler(dataSource));

		emailBodyMessagePart.setFileName(attachmentFile);

		multiPart.addBodyPart(emailBodyMessagePart);

		//Sending Complete Message Parts
		emailMessage.setContent(multiPart);

	}

}

/**
 *
 For Gmail:

    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class",
            "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "805");
    
    Authentication Codes:
    
        Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sender@gmail.com","Password");
        }
    });
 *
 **/


/**
 * 
 For LocalHost:

       properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", "example.com");
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.username", user);
        properties.put("mail.smtp.password", password);
        Session session = Session.getDefaultInstance(properties, null);

 Notes:

 1. Configure as per the documentation 
 2. Do not use localhost and make sure you change it in C:\Windows\System32\drivers\etc\hosts from "127.0.0.1 localhost" -> "127.0.0.1 example.com"
 3. In add domain of hmailserver give "example.com"
 4. In Domain created add accounts eg.user@example.com
 5. under setting->protocold->smtp->delivery of email add "example.com"
 * 
 */
package LiveTestProjectDay10;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.websocket.Session;

import org.apache.tools.ant.taskdefs.email.Message;
import org.glassfish.grizzly.Transport;

public class EmailUtilities {
	
	private static final String SMTP_HOST_SERVER_NAME = "smtp.gmail.com";
	private static final String SMTP_SERVER_AUTH_USER = "userid@gmail.com";
	private static final String SMTP_SERVER_AUTH_PASSWORD = "password";
	
	private static final String emailMessage = "Please Find Orders List In CSV Formatted File";
	private static final String emailSubject = "Orders List in CSV formatted file";
	private static final String emailFrom = "asifuzzamanbappy@gmail.com";
	
	 // Adding List of Email address to who email needs to be sent to
	private static final String emailLists[] = {"asifuzzamanbappy@gmail.com", "bthegreat@gmail.com"};
	
	// CSV File That was saved in earlier class now needs to be accessed here for attachment File
	private static String fileName = null;
	
	
	public static void emailUtil(String argsFile) {
		
		// Attach this exported file and email to some/other email id
		fileName = argsFile;
		// Creatuing a class object
		EmailUtilities smtpMailSenderObject = new EmailUtilities();
		smtpMailSenderObject.postMail(emailLists,emailSubject,emailMessage,emailFrom);
	}


	private void postMail(String[] emaillists2, String emailsubject2, String emailmessage2, String emailfrom2) {
		// TODO Auto-generated method stub
		Properties props = System.getProperties();
		props.put("mail.smtp.host", SMTP_HOST_SERVER_NAME);
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", "true");
		
		Authenticator smtpAuth = new SMTPAuthenticator();
		Session session = Session.getDefautInstance(props,smtpAuth);
		
		 // create a message
        Message msg = new MimeMessage(session);
        
        // set the from and to address
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);
          
        // new code added
        Multipart multipart = new MimeMultipart();
        // multipart.addBodyPart(messageBodyPart);
       
        // Part two is attachment
        messageBodyPart = new MimeBodyPart();
        String filename = sFilename;
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        
        messageBodyPart.setFileName(filename);
        messageBodyPart.setDescription(msg);
        multipart.addBodyPart(messageBodyPart);
       
        // Put parts in message
        msg.setContent(multipart);
       
        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++)
        {
            addressTo[i] = new InternetAddress(recipients[i]);
        }
        
        msg.setRecipients(Message.RecipientType.TO, addressTo); 
        
      // Setting the Subject and Content Type
        msg.setSubject(subject);
        msg.setContent(multipart);
        Transport.send(msg);
          
     }

    /**
    * SimpleAuthenticator is used to do simple authentication
    * when the SMTP server requires it.
    */
    private class SMTPAuthenticator extends Authenticator
    {

        public PasswordAuthentication getPasswordAuthentication()
        {
            String username = SMTP_SERVER_AUTH_USER;
            String password = SMTP_SERVER_AUTH_PASSWORD;
            return new PasswordAuthentication(username, password);
        }
    }

		
	}

}

package LiveTestProjectDay10;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.activation.*; 

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.websocket.Session;

import org.apache.tools.ant.taskdefs.email.Message;
import org.glassfish.grizzly.Transport;

public class EmailUtilities {
	
	static final String SMTP_HOST_SERVER_NAME = "smtp.gmail.com";
	static final String SMTP_SERVER_AUTH_USER = "userid@gmail.com";
	static final char[] SMTP_SERVER_AUTH_PASSWORD = "password";
	
	static final String emailMessage = "Please Find Orders List In CSV Formatted File";
	static final String emailSubject = "Orders List in CSV formatted file";
	static final String emailFrom = "asifuzzamanbappy@gmail.com";
	
	 // Adding List of Email address to who email needs to be sent to
	static final String emailLists[] = {"asifuzzamanbappy@gmail.com", "bthegreat@gmail.com"};
	
	// CSV File That was saved in earlier class now needs to be accessed here for attachment File
	static String csvToAtachfileName = null;
	
	
	public static void emailUtil(String argsFile) {
		
		// Attach this exported file and email to some/other email id
		csvToAtachfileName = argsFile;
		// Creatuing a class object
		EmailUtilities smtpMailSenderObject = new EmailUtilities();
		smtpMailSenderObject.postMail(emailLists,emailSubject,emailMessage,emailFrom);
	}

	
	// private BodyPart messageBodyPart;

	private void postMail(String[] emaillists2, String emailsubject2, String emailmessage2, String emailfrom2) {
		// TODO Auto-generated method stub
		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_SERVER_NAME);
		props.put("mail.smtp.auth", true);
		//props.put("mail.smtp.starttls.enable", "true");
		
		javax.mail.Session session = Session.getDefaultInstance(props
				, new javax.mail.Authenticator() {
			protected PasswordAuthentication GetAccountPasswordAuthentication() {
				
				return new PasswordAuthentication(SMTP_SERVER_AUTH_USER, SMTP_SERVER_AUTH_PASSWORD);
				
			}});
		
		//public static Session getDefaultInstance(Properties props,Authenticator auth)
		
		
		 // create a message
        MimeMessage msg = new MimeMessage(session);
        
        // set the from and to address
        InternetAddress addressFrom = new InternetAddress(SMTP_SERVER_AUTH_USER);
        msg.setFrom(addressFrom);
          
        // new code added
        //Multipart multipart = new MimeMultipart();
        // multipart.addBodyPart(messageBodyPart);
       
        // Part two is attachment
        //messageBodyPart  = new MimeBodyPart();
        //String filename = csvToAtachfileName;
        //DataSource source = new FileDataSource(filename);
        //messageBodyPart.setDataHandler(new DataHandler(source));
        
        //messageBodyPart.setFileName(filename);
        //messageBodyPart.setDescription(msg);
        //multipart.addBodyPart(messageBodyPart);
       
        // Put parts in message
        //msg.setContent(multipart);
       
        InternetAddress[] addressTo = new InternetAddress[emaillists2.length];
        for (int i = 0; i < emaillists2.length; i++)
        {
            addressTo[i] = new InternetAddress(emaillists2[i]);
        }
        
        msg.setRecipients(RecipientType.TO, addressTo); 
        
      // Setting the Subject and Content Type
        msg.setSubject(emailSubject);
        msg.setText(emailMessage);
        //msg.setContent(multipart);
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
            char[] password = SMTP_SERVER_AUTH_PASSWORD;
            return new PasswordAuthentication(username, password);
        }
    }

		
	}

}

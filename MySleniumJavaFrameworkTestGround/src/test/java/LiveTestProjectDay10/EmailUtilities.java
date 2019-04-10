package LiveTestProjectDay10;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
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
	static final String SMTP_SERVER_AUTH_PASSWORD = "password";

	static final String emailMessage = "Please Find Orders List In CSV Formatted File";
	static final String emailSubject = "Orders List in CSV formatted file";
	static final String emailFrom = "asifuzzamanbappy@gmail.com";

	// Adding List of Email address to who email needs to be sent to
	static final String emailLists[] = {"asifuzzamanbappy@gmail.com", "bthegreat@gmail.com"};

	// CSV File That was saved in earlier class now needs to be accessed here for attachment File
	static String csvToAtachfileName = null;

	
	public static void main(String[] args) throws MessagingException {
		emailUtil();
	}

	public static void emailUtil() throws MessagingException {

		// Attach this exported file and email to some/other email id
		//csvToAtachfileName = argsFile;
		// Creatuing a class object
		EmailUtilities smtpMailSenderObject = new EmailUtilities();
		smtpMailSenderObject.postMail(emailLists,emailSubject,emailMessage,emailFrom);
	}


	

	private void postMail(String[] emaillists2, String emailSubject, String emailmessage2, String emailfrom2) throws MessagingException {
		// TODO Auto-generated method stub
		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_SERVER_NAME);
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", "true");

		javax.mail.Session session = javax.mail.Session.getDefaultInstance(props, null);

		//public static Session getDefaultInstance(Properties props,Authenticator auth)

		BodyPart messageBodyPart = new BodyPart() {
			
			public void writeTo(OutputStream os) throws IOException, MessagingException {
				// TODO Auto-generated method stub
				
			}
			
			public void setText(String text) throws MessagingException {
				// TODO Auto-generated method stub
				
			}
			
			public void setHeader(String header_name, String header_value) throws MessagingException {
				// TODO Auto-generated method stub
				
			}
			
			public void setFileName(String filename) throws MessagingException {
				// TODO Auto-generated method stub
				
			}
			
			public void setDisposition(String disposition) throws MessagingException {
				// TODO Auto-generated method stub
				
			}
			
			public void setDescription(String description) throws MessagingException {
				// TODO Auto-generated method stub
				
			}
			
			public void setDataHandler(DataHandler dh) throws MessagingException {
				// TODO Auto-generated method stub
				
			}
			
			public void setContent(Object obj, String type) throws MessagingException {
				// TODO Auto-generated method stub
				
			}
			
			public void setContent(Multipart mp) throws MessagingException {
				// TODO Auto-generated method stub
				
			}
			
			public void removeHeader(String header_name) throws MessagingException {
				// TODO Auto-generated method stub
				
			}
			
			public boolean isMimeType(String mimeType) throws MessagingException {
				// TODO Auto-generated method stub
				return false;
			}
			
			public int getSize() throws MessagingException {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public Enumeration<Header> getNonMatchingHeaders(String[] header_names) throws MessagingException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Enumeration<Header> getMatchingHeaders(String[] header_names) throws MessagingException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public int getLineCount() throws MessagingException {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public InputStream getInputStream() throws IOException, MessagingException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String[] getHeader(String header_name) throws MessagingException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getFileName() throws MessagingException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getDisposition() throws MessagingException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getDescription() throws MessagingException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public DataHandler getDataHandler() throws MessagingException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getContentType() throws MessagingException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Object getContent() throws IOException, MessagingException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Enumeration<Header> getAllHeaders() throws MessagingException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public void addHeader(String header_name, String header_value) throws MessagingException {
				// TODO Auto-generated method stub
				
			}
		};
		// create a message
		MimeMessage msg = new MimeMessage(session);

		// set the from and to address
		InternetAddress addressFrom = new InternetAddress(SMTP_SERVER_AUTH_USER);
		msg.setFrom(addressFrom);

		// new code added
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// Part two is attachment
		messageBodyPart  = new MimeBodyPart();
		String filename = csvToAtachfileName;
		DataSource source = new FileDataSource(filename);
		messageBodyPart.setDataHandler(new DataHandler(source));

		messageBodyPart.setFileName(filename);
		//messageBodyPart.setDescription(msg);
		multipart.addBodyPart(messageBodyPart);

		// Put parts in message
		msg.setContent(multipart);

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
		javax.mail.Transport.send(msg, SMTP_SERVER_AUTH_USER, SMTP_SERVER_AUTH_PASSWORD);

	}

	/**
	 * SimpleAuthenticator is used to do simple authentication
	 * when the SMTP server requires it.
	 
	private class SMTPAuthenticator extends Authenticator
	{

		public PasswordAuthentication getPasswordAuthentication()
		{
			String username = SMTP_SERVER_AUTH_USER;
			String password = SMTP_SERVER_AUTH_PASSWORD;
			return new PasswordAuthentication(username, password);
		}
	}
*/

}



package ErrorHandlingExceptionSample;

import java.util.Calendar;
import java.util.Date; 
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ExcptionHandlingTestSample {
	
	
	public static void main(String[] args) throws ParseException {
	
		anotherDateParsingFucntion();
		myDateParsingIsuue();
		MyNewDateParser();
		// Example #02 where you simply throws exception in the function naming and not affect the code
		// body rather handling it outside where its been used, here in this case it's calling from main.
		
		try {
			myThrowableErrorHandlingDemo();  // calling function
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// Example #01 where you write your whole code in main or any other block for that matter
		// without throwing exception in the function name.
		
		try {
			
			System.out.println("Hello World....");
			
			int count = 1/0;
			
			System.out.println("Test Completed");
			
		}
		
		catch (Exception ex) {
			
			System.out.println("it's Inside catch block");
			
			System.out.println("Error Message :   " +ex.getMessage());
			
			System.out.println("Caused Message :   " +ex.getCause());
			
			System.out.println("Showing Stack   :"  +ex.fillInStackTrace());
			
		}
		
		finally {
			
			System.out.println("Finally Block Always Runs no matter what the code flow is in a try catch block");
			System.out.println("It's inside 'Finally' block");
			
		}
				
		
	}
	
	// called from main function to demonstrate exception being thrown and handled.
	public static void myThrowableErrorHandlingDemo() throws Exception{
		
		System.out.println("Hello World....");
		
		int count = 1/0;
		
		System.out.println("Lets throw a deleiberate wxcepttion here, if needed!");
		
		throw new Exception("Not a vaild Operatoin.");
		
		//System.out.println("Test Completed");
		
		
		
	}
	
	public static void myDateParsingIsuue() throws ParseException {
		
		String dateStamp = "May 4, 2017 7:41:10 AM";
		java.util.Date dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss aa").parse(dateStamp);
		//java.util.Date newDateFormatDisplay = dateFormat.parse(dateStamp);
		System.out.println(dateFormat);
		
	}
	
	public static void MyNewDateParser() throws ParseException {
		
		Calendar myCalendar = Calendar.getInstance();
		
		String dateStamp = "May 4, 2017 7:41:10 AM";
		
		SimpleDateFormat dateformatter = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss aa");
		
		Date checkDate = dateformatter.parse(dateStamp);
		
		myCalendar.setTime(checkDate);
		
		int checkYear = myCalendar.get(Calendar.YEAR);
		System.out.println("Year Extracted : " +checkYear);
		
		int checkMonth = myCalendar.get(Calendar.MONTH);
		System.out.println("Month Extracted : " +checkMonth);
		
		int checkDay = myCalendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("Day Extracted : " +checkDay );
		
	}
	
	public static void anotherDateParsingFucntion() throws ParseException {
		String sDate1="31/12/1998";  
	    String sDate2 = "31-Dec-1998";  
	    String sDate3 = "12 31, 1998";  
	    String sDate4 = "Thu, Dec 31 1998";  
	    String sDate5 = "Thu, Dec 31 1998 23:37:50";  
	    String sDate6 = "31-Dec-1998 23:37:50";
	    String sDate7 = "May 4, 2017 7:41:10";
	    SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");  
	    SimpleDateFormat formatter2=new SimpleDateFormat("dd-MMM-yyyy");  
	    SimpleDateFormat formatter3=new SimpleDateFormat("MM dd, yyyy");  
	    SimpleDateFormat formatter4=new SimpleDateFormat("E, MMM dd yyyy");  
	    SimpleDateFormat formatter5=new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");  
	    SimpleDateFormat formatter6=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
	    SimpleDateFormat formatter7=new SimpleDateFormat("MMM DD, yyyy HH:mm:ss");
	    Date date1=formatter1.parse(sDate1);  
	    Date date2=formatter2.parse(sDate2);  
	    Date date3=formatter3.parse(sDate3);  
	    Date date4=formatter4.parse(sDate4);  
	    Date date5=formatter5.parse(sDate5);  
	    Date date6=formatter6.parse(sDate6);
	    Date date7=formatter7.parse(sDate7);
	    System.out.println(sDate1+"\t"+date1);  
	    System.out.println(sDate2+"\t"+date2);  
	    System.out.println(sDate3+"\t"+date3);  
	    System.out.println(sDate4+"\t"+date4);  
	    System.out.println(sDate5+"\t"+date5);  
	    System.out.println(sDate6+"\t"+date6);
	    System.out.println(sDate7+"\t"+date7);
	}

}
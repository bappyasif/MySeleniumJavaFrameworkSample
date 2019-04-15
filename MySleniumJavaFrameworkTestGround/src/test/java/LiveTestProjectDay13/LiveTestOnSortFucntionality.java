package LiveTestProjectDay13;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestOnSortFucntionality {

	static WebDriver webDriver;
	static String baseUrl = "http://live.guru99.com/index.php/backendlogin/";
	static String userID = "user01";
	static String userPass = "guru99com";

	
	public static void main(String[] args) throws InterruptedException, ParseException {
		
		CommencingTestOnSortFunctionality();
		
	}
	

	public static void CommencingTestOnSortFunctionality() throws InterruptedException, ParseException {

		// Getting platform geared up.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		// visiting back-end login page
		webDriver.get(baseUrl);
		System.out.println("Navigation Successfull");

		// login in with provided credentials.
		webDriver.findElement(By.xpath("//input[@id='username']")).clear();
		webDriver.findElement(By.xpath("//input[@id='username']")).sendKeys(userID);
		webDriver.findElement(By.xpath("//input[@id='login']")).clear();
		webDriver.findElement(By.xpath("//input[@id='login']")).sendKeys(userPass);

		webDriver.findElement(By.xpath("//input[@title='Login']")).click();
		System.out.println("Back-end Login Successfull");
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Handling popup window alert message
		webDriver.findElement(By.xpath("//span[contains(text(),'close')]")).click();
		
		// Going to click on Sales --> Invoices menu
		webDriver.findElement(By.xpath("//span[contains(text(),'Sales')]")).click();
		webDriver.findElement(By.linkText("Invoices")).click();
		System.out.println("Inside Sales Invoices Page");
		Thread.sleep(2000);
		
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}
		
		// Sorting Date Column from asc --> desc and then desc --> asc order respectively.
		webDriver.findElement(By.xpath("//span[contains(text(),'Invoice Date')]")).click();
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		String checkDescStamp = webDriver.findElement(By.xpath("//td[contains(text(),'Aug 23, 2014 12:25:57 AM')]")).getText().toString().trim();
		System.out.println("Earliest Entry Date Stamp : " +checkDescStamp);
		Thread.sleep(2000);
		
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}
		
		webDriver.findElement(By.xpath("//span[contains(text(),'Invoice Date')]")).click();
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		String checkAscStatmp = webDriver.findElement(By.xpath("//td[contains(text(),'May 4, 2017 7:41:10 AM')]")).getText().toLowerCase().toString().trim(); 
		System.out.println("Latest Entry Date Stamp : " +checkAscStatmp);
		Thread.sleep(2000);
		
		//checkAscStatmp.compareTo(checkDescStamp);
		//DateFormat dateChecker = DateFormat.getInstance();
		//String checkAscDate = dateChecker.format(checkAscStatmp);
		Calendar calendar = Calendar.getInstance();
		
		SimpleDateFormat dateformatter = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss aa");
		
		Date ascDate = dateformatter.parse(checkAscStatmp);
		calendar.setTime(ascDate);
		int ascYear = calendar.get(Calendar.YEAR);
		System.out.println("Parsed ASC Time Stamp For Year :" +ascYear);
		
		Date descDate = dateformatter.parse(checkDescStamp);
		calendar.setTime(descDate);
		int descYear = calendar.get(Calendar.YEAR);
		System.out.println("Parsed DESC Time Stamp For Year :" +descYear);
		
		//DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss XM");
		
		if (ascYear > descYear) {
			System.out.println("Sorting Works Just Fine");
		} else {
			System.out.println("Something's Wrong!!!");
		}
		
		//System.out.println(ascDate.compareTo(descDate));
		//System.out.println(checkAscStatmp.compareTo(checkDescStamp));
		
		System.out.println("Test Completed...");
		webDriver.quit();
		
	}

}

package DemoBankProjectLiveTestCase10;

import java.sql.Date;
import java.util.Calendar;
import java.util.TimeZone;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestOnAccountDeletionAndVerificationBehaviours {

	static WebDriver webDriver;	
	static String baseUrl = "http://www.demo.guru99.com/V4/";

	static String accountID = "59371"; // CustID : 5779 
	static final String userID = "mngr190242";
	static final String userPassword = "hCrEd^8";


	public static void main(String[] args) {

		// Getting webDriver ready for this.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		try {
			CommencingLogin();
			CommencingLiveTestOnAccountDeletionAndVerifications();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Test Completed...");
			webDriver.quit();
		}
	}


	public static void CommencingLiveTestOnAccountDeletionAndVerifications() throws InterruptedException {

		// First Commencing Login by Using Util Class created earlier in this package. Couldn't make it work!!

		//webDriver.get(UtillClassForCommencingLogin.baseUrl);
		//DemoBankProjectLiveTestCase10.UtillClassForCommencingLogin.CommencingLogin();

		//CustomizedStatements();

		System.out.println("Login Successful \n");

		// Pre Delete
		CheckingBalance();
		System.out.println("Account Balance inquiry Checked");

		CustomizedStatements();
		System.out.println("Customized Account Balance Checked");

		MiniStatements();
		System.out.println("Account Mini Statements Checked");

		// Attempting Deleting Bank Account
		// when successful it will redirect to Manager Home page. Verifying that it happens.
		DeleteAccount();
		System.out.println("\n\n");
		// After Delete
		// Will be checking on deleted account's  deletion confirming behaviors
		// by checking Mini statements, balance inquiry and customized Statements.

		CheckingBalance();
		System.out.println("Confirmed Account Not Available");

		CustomizedStatements();
		System.out.println("Confirmed Account Not Available..");

		MiniStatements();
		System.out.println("Confirmed Account Not Available.");

		try {

			String pageTitle = webDriver.getTitle().toString();
			System.out.println("Page Title : " +pageTitle);

			String managerID = webDriver.findElement(By.xpath("//td[contains(text(),'Manger Id : mngr190242')]")).getText().toString();
			System.out.println("Message Shown: " +managerID);

			System.out.println("All Checkpoints Are Completed");

		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();

		} finally {

			System.out.println("Account Deleted \n");
		}
	}

	public static void DeleteAccount() throws InterruptedException {

		webDriver.findElement(By.linkText("Delete Account")).click();
		Thread.sleep(2000);
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		webDriver.findElement(By.xpath("//input[@name='accountno']")).clear();
		webDriver.findElement(By.xpath("//input[@name='accountno']")).sendKeys(accountID);
		webDriver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		Alert alertBox = webDriver.switchTo().alert();
		String alertMessage = alertBox.getText().toString();
		System.out.println("Alert Message : " +alertMessage);
		alertBox.accept();

		Thread.sleep(2000);
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		try {

			Alert alert = webDriver.switchTo().alert();
			String message = alert.getText().toString();
			System.out.println("Alert Message : " +message);
			alertBox.accept();

		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}		
	}

	public static void CheckingBalance() throws InterruptedException {

		webDriver.findElement(By.linkText("Balance Enquiry")).click();

		Thread.sleep(2000);
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		try {

			webDriver.findElement(By.xpath("//input[@name='accountno']")).clear();
			webDriver.findElement(By.xpath("//input[@name='accountno']")).sendKeys(accountID);
			webDriver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

			Thread.sleep(2000);
			// switching to new window. Even though it doesn't need this window switching but 
			// still expert says its better this way when you're landing on a new URL.
			for (String handle : webDriver.getWindowHandles()) {
				webDriver.switchTo().window(handle);
			}

			String balanceInquiry = webDriver.findElement(By.xpath("//p[@class='heading3']")).getText().toString();
			System.out.println("Balance Message: " +balanceInquiry);

			webDriver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();
			Thread.sleep(2000);
			// switching to new window. Even though it doesn't need this window switching but 
			// still expert says its better this way when you're landing on a new URL.
			for (String handle : webDriver.getWindowHandles()) {
				webDriver.switchTo().window(handle);
			}

		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}		

		try {

			Alert alertBox = webDriver.switchTo().alert();
			String alertMessage = alertBox.getText().toString();
			alertBox.accept();
			System.out.println("Alert Message : " +alertMessage);

			webDriver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
			System.out.println("Balanace Inquiry Checkpoint Completed");

		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}

	}


	public static void MiniStatements() throws InterruptedException {

		webDriver.findElement(By.linkText("Mini Statement")).click();
		Thread.sleep(2000);
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		try {

			webDriver.findElement(By.xpath("//input[@name='accountno']")).clear();
			webDriver.findElement(By.xpath("//input[@name='accountno']")).sendKeys(accountID);
			webDriver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

			Thread.sleep(2000);
			// switching to new window. Even though it doesn't need this window switching but 
			// still expert says its better this way when you're landing on a new URL.
			for (String handle : webDriver.getWindowHandles()) {
				webDriver.switchTo().window(handle);
			}

			String miniStatement = webDriver.findElement(By.xpath("//p[@class='heading3']")).getText().toString();
			System.out.println("Mini Statement : " +miniStatement);

			String transactionType = webDriver.findElement(By.xpath("//p[@class='heading3']")).getText().toString();
			System.out.println("Transaction Type : " +transactionType);

			webDriver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();

			Thread.sleep(2000);
			// switching to new window. Even though it doesn't need this window switching but 
			// still expert says its better this way when you're landing on a new URL.
			for (String handle : webDriver.getWindowHandles()) {
				webDriver.switchTo().window(handle);
			}

		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}

		try {

			Alert alertBox = webDriver.switchTo().alert();
			String alertMessage = alertBox.getText().toString();
			alertBox.accept();
			System.out.println("Alert Message : " +alertMessage);

			webDriver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
			System.out.println("Mini Statements Checkpoint Completed");

		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}

	}


	public static void CustomizedStatements() throws InterruptedException {

		String todayDate = "04/26/2019";

		try {

			webDriver.findElement(By.linkText("Customised Statement")).click();
			// switching to new window. Even though it doesn't need this window switching but 
			// still expert says its better this way when you're landing on a new URL.
			for (String handle : webDriver.getWindowHandles()) {
				webDriver.switchTo().window(handle);
			}
			Thread.sleep(2000);

			webDriver.findElement(By.xpath("//input[@name='accountno']")).clear();
			webDriver.findElement(By.xpath("//input[@name='accountno']")).sendKeys(accountID);
			webDriver.findElement(By.xpath("//input[@name='fdate']")).sendKeys(todayDate);
			webDriver.findElement(By.xpath("//input[@name='tdate']")).sendKeys(todayDate);
			webDriver.findElement(By.xpath("//input[@name='amountlowerlimit']")).clear();
			webDriver.findElement(By.xpath("//input[@name='amountlowerlimit']")).sendKeys("20000");
			webDriver.findElement(By.xpath("//input[@name='numtransaction']")).clear();
			webDriver.findElement(By.xpath("//input[@name='numtransaction']")).sendKeys("10");
			webDriver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

			Thread.sleep(2000);
			// switching to new window. Even though it doesn't need this window switching but 
			// still expert says its better this way when you're landing on a new URL.
			for (String handle : webDriver.getWindowHandles()) {
				webDriver.switchTo().window(handle);
			}
			
			Alert alert = webDriver.switchTo().alert();
			String alertMessage = alert.getText().toString();
			alert.accept();
			System.out.println("Alert Message : " +alertMessage);

			String cutomizedStatements = webDriver.findElement(By.xpath("//p[@class='heading3']")).getText().toString();
			System.out.println("Customized Statements : " +cutomizedStatements);
			
			String transactionType = webDriver.findElement(By.xpath("//p[@class='heading3']")).getText().toString();
			System.out.println("Transaction Type : " +transactionType);

			webDriver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click(); 

		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}
		
		try {
			
			Alert alertBox = webDriver.switchTo().alert();
			String alertMessage = alertBox.getText().toString();
			alertBox.accept();
			System.out.println("Alert Message : " +alertMessage);

			webDriver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
			System.out.println("Customized Statements Checkpoint Completed");
			
		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}
	}


	public static void CommencingLogin() throws InterruptedException {

		// Navigating to test site's landing page
		webDriver.get(baseUrl);
		System.out.println("Landing Successful");

		// Entering userID and Password to login section
		webDriver.findElement(By.xpath("//input[@name='uid']")).clear();
		webDriver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
		webDriver.findElement(By.xpath("//input[@name='password']")).clear();
		webDriver.findElement(By.xpath("//input[@name='password']")).sendKeys(userPassword);
		webDriver.findElement(By.xpath("//input[@value='LOGIN']")).click();

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		System.out.println("Inside Manager Home Page");
		Thread.sleep(2000);

		try {

			System.out.println("Vefication Of Manager Home Page");

			String userloginHeadingStatement = webDriver.findElement(By.xpath("//marquee[@class='heading3']")).getText().toString().trim();
			System.out.println("Heading Statement : " +userloginHeadingStatement);

			String userVerificationByID = webDriver.findElement(By.xpath("//td[contains(text(),'Manger Id : mngr190242')]")).getText().toString().trim();
			System.out.println("User Identification : " +userVerificationByID);

			// Test Case wanted to include Page title as a step of verification process.
			String hpTitle = webDriver.getTitle().toString().trim();
			System.out.println("Page Title : " +hpTitle);

		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Check Console For Clarification");
			ex.getStackTrace();

			Alert alert = webDriver.switchTo().alert();

			String alertMessage = alert.getText().toString();
			alert.accept();
			System.out.println("Alert Box Message Is:  " +alertMessage +"\n");
		}
	}

	//Get The Current Day
	public static String getCurrentDay (){
		//Create a Calendar Object
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

		//Get Current Day as a number
		int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("Today Int: " + todayInt +"\n");

		//Integer to String Conversion
		String todayStr = Integer.toString(todayInt);
		System.out.println("Today Str: " + todayStr + "\n");

		calendar.get(todayInt);

		return todayStr;
	}
}

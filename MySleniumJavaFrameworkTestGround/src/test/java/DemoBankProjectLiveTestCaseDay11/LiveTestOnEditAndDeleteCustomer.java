package DemoBankProjectLiveTestCaseDay11;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestOnEditAndDeleteCustomer {

	// customer ID : 41433  AccountID: 59440

	static WebDriver webDriver;	
	static String baseUrl = "http://www.demo.guru99.com/V4/";

	//static String accountID = "59371"; // CustID : 5779
	static String customerID = "5779"; // check check
	static final String userID = "mngr190242";
	static final String userPassword = "hCrEd^8";


	public static void main(String[] args) {

		try {
			CommencingTestOnEditAndDeleteCustomerAccount();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		} finally {
			System.out.println("Test Completed...");
			webDriver.quit();
		}
	}


	public static void CommencingTestOnEditAndDeleteCustomerAccount() throws InterruptedException {

		// Getting webDriver ready for this.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		CommencingLogin();
		System.out.println("Successful Login And verified \n");

		EditCustomer();
		System.out.println("Editing Customer Checkpoint Done Before Delete \n");

		DeleteCustomer();
		System.out.println("Deleting CustomerID Checkpoint Done \n");

		EditCustomer();
		System.out.println("Editing Customer Checkpoint After Delete \n");

	}

	public static void DeleteCustomer() throws InterruptedException {

		webDriver.findElement(By.linkText("Delete Customer")).click();

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		System.out.println("Inside Customer Delete Page");
		Thread.sleep(2000);

		webDriver.findElement(By.xpath("//input[@name='cusid']")).clear();
		webDriver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		webDriver.findElement(By.xpath("//input[@name='AccSubmit']")).click();


		// Even though only one method could carry out this test case but 
		// just did try something intuitive in design.
		try {

			// When CudtomerID is not Hooked With Existing AccountID
			TryDeleteCustomerWhenNotHookedWithAccount();

			// When CustomerID is still hooked with Exiting Bank Account.
			TryDeleteCustomerWhenHookedWithAccount();

			// When CudtomerID does Not Exist
			TryDeleteCustomerWithInvalidCustomerID();


		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}
	}


	public static void TryDeleteCustomerWhenHookedWithAccount() {

		Alert alert = webDriver.switchTo().alert();

		String firstAlert = alert.getText().toString();
		alert.accept();
		System.out.println("Alert Box Message Is:  " +firstAlert +"\n");

		Alert alertBox = webDriver.switchTo().alert();

		String secondAlert = alert.getText().toString();
		alertBox.accept();
		System.out.println("Alert Box Message Is:  " +secondAlert +"\n");
	}


	public static void TryDeleteCustomerWhenNotHookedWithAccount() {

		Alert alert = webDriver.switchTo().alert();

		String firstAlert = alert.getText().toString();
		alert.accept();
		System.out.println("Alert Box Message Is:  " +firstAlert +"\n");

		Alert alertBox = webDriver.switchTo().alert();

		String secondAlert = alert.getText().toString();
		alertBox.accept();
		System.out.println("Alert Box Message Is:  " +secondAlert +"\n");
	}


	public static void TryDeleteCustomerWithInvalidCustomerID() {

		Alert alert = webDriver.switchTo().alert();

		String alertMessage = alert.getText().toString();
		alert.accept();
		System.out.println("Alert Box Message Is:  " +alertMessage +"\n");
	}


	public static void EditCustomer() throws InterruptedException {

		webDriver.findElement(By.linkText("Edit Customer")).click();

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		System.out.println("Inside Customer Edit Page");
		Thread.sleep(2000);

		webDriver.findElement(By.xpath("//input[@name='cusid']")).clear();
		webDriver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		webDriver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		// When Customer ID Exist
		try {

			String editPage = webDriver.findElement(By.xpath("//p[@class='heading3']")).getText().toString();
			System.out.println("Edit Customer Page Heading : " +editPage);

			String customerCity = webDriver.findElement(By.xpath("//input[@name='city']")).getText().toString();
			System.out.println("Customer City : " +customerCity);

			System.out.println("Customer Exist Is Verified");
			webDriver.findElement(By.linkText("Home")).click();

			// switching to new window. Even though it doesn't need this window switching but 
			// still expert says its better this way when you're landing on a new URL.
			for (String handle : webDriver.getWindowHandles()) {
				webDriver.switchTo().window(handle);
			}

			//System.out.println("Inside Manager Home Page");
			Thread.sleep(2000);

		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}

		// When CustomerID Does Not Exit
		try {
			
			Alert alert = webDriver.switchTo().alert();

			String firstAlert = alert.getText().toString();
			alert.accept();
			System.out.println("Alert Box Message Is:  " +firstAlert +"\n");

			Alert alertBox = webDriver.switchTo().alert();

			String secondAlert = alert.getText().toString();
			alertBox.accept();
			System.out.println("Alert Box Message Is:  " +secondAlert +"\n");

			webDriver.findElement(By.linkText("Home")).click();
			System.out.println("Customer Does't Exist Verified");

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
}

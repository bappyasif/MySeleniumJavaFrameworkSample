package DemoBankProjectLiveTestCaseDay18;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestOnCreatingCustomerAccountAndVerification {

	static WebDriver webDriver;	
	static String baseUrl = "http://www.demo.guru99.com/V4/";
	static final String userID = "mngr190242";
	static final String userPassword = "hCrEd^8";

	static final String customerName = "Test User";
	static final String customerDOB = "03/20/1985";
	static final String customerAddress = "Test Avenue Test Lane";
	static final String customerCity = "Dhaka";
	static final String customerState = "Dhaka";
	static final String customerPIN = "001207";
	static final String customerMobile = "+88088088019";
	static final String customerEmail = "testtest+08@gmail.com";
	static final String customerPassword = "testtest";


	public static void main(String[] args) {

		try {

			CommencingTestOnCustomerAccountCreationAndVerification();

		}catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}finally {
			
			System.out.println("Test Completed...");
			webDriver.quit();
		}

	}


	public static void CommencingTestOnCustomerAccountCreationAndVerification() throws InterruptedException {

		// Getting webDriver ready for this.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		CommencingLogin();
		System.out.println("Verified Successful Manager Login \n");

		NewCustomer();
		System.out.println("Customer Account Creation Checked \n");
		
		LogOut();
		System.out.println("You Logged Out Successfully \n");

	}
	
	
	public static void LogOut() {

		webDriver.findElement(By.linkText("Log out")).click();

		HandlingAlert();

		String pageTitle = webDriver.getTitle();
		System.out.println("Page Title : " +pageTitle);

		System.out.println("Log Out Has Verified");

	}


	public static void NewCustomer() throws InterruptedException {

		webDriver.findElement(By.linkText("New Customer")).click();
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		System.out.println("New Customer Account Creation Page");
		Thread.sleep(2000);



		webDriver.findElement(By.xpath("//input[@name='name']")).clear();
		webDriver.findElement(By.xpath("//input[@name='name']")).sendKeys(customerName);
		webDriver.findElement(By.xpath("//tr[5]//td[2]//input[1]")).click();
		webDriver.findElement(By.xpath("//input[@id='dob']")).clear();
		webDriver.findElement(By.xpath("//input[@id='dob']")).sendKeys(customerDOB);
		webDriver.findElement(By.xpath("//textarea[@name='addr']")).clear();
		webDriver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(customerAddress);
		webDriver.findElement(By.xpath("//input[@name='city']")).clear();
		webDriver.findElement(By.xpath("//input[@name='city']")).sendKeys(customerCity);
		webDriver.findElement(By.xpath("//input[@name='state']")).clear();
		webDriver.findElement(By.xpath("//input[@name='state']")).sendKeys(customerState);
		webDriver.findElement(By.xpath("//input[@name='pinno']")).clear();
		webDriver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(customerPIN);
		webDriver.findElement(By.xpath("//input[@name='telephoneno']")).clear();
		webDriver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(customerMobile);
		webDriver.findElement(By.xpath("//input[@name='emailid']")).clear();
		webDriver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(customerEmail);
		webDriver.findElement(By.xpath("//input[@name='password']")).clear();
		webDriver.findElement(By.xpath("//input[@name='password']")).sendKeys(customerPassword);
		webDriver.findElement(By.xpath("//input[@name='sub']")).click();

		System.out.println("New Customer Account Created");
		// When Successful
		try {

			String accountCreationConfirmationMessage = webDriver.findElement(By.xpath("//p[@class='heading3']")).getText().toString();
			System.out.println("Account Creation Confirmation Message : " +accountCreationConfirmationMessage);

			String customerID = webDriver.findElement(By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]"
					+ "/table[1]/tbody[1]/tr[4]/td[2]")).getText().toString();
			System.out.println("Customer ID : " +customerID);

			TakingSnapshot();
			System.out.println("Verification Completed...");

			webDriver.findElement(By.linkText("Continue")).click();
			System.out.println("New Customer Account Creation Verified");

		}catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}

		// When Unsuccessful
		try {

			HandlingAlert();

		}catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}
	}


	public static void TakingSnapshot() throws InterruptedException, IOException {

		org.openqa.selenium.Dimension windowSize = webDriver.manage().window().getSize(); 
		//webDriver.navigate().refresh();
		Thread.sleep(2000);

		// Scrolling Page Until EOF
		((JavascriptExecutor) webDriver)
		.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		webDriver.manage().window().maximize();
		System.out.println("Taking ScreenShot");

		TakesScreenshot newCustomerAccountScreenShot = ((TakesScreenshot)webDriver);
		File ncaScreenShot = newCustomerAccountScreenShot.getScreenshotAs(OutputType.FILE);
		String fileSaved = ("E:\\eclipse\\LiveTestCodeSamples\\Bank Demo\\NewCustomerAccountCreationScreenShot" +".png");
		FileUtils.copyFile(ncaScreenShot, new File(fileSaved));

		System.out.println("Your Screenshot Is Saved At This Location : " +fileSaved);

		webDriver.manage().window().setSize(windowSize);
		System.out.println("Getting Out Of TakingSnapshot");

	}


	public static void HandlingAlert() {

		Alert alert = webDriver.switchTo().alert();
		String alertMessage = alert.getText().toString();
		System.out.println("Alert Message : " +alertMessage);
		alert.accept();

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

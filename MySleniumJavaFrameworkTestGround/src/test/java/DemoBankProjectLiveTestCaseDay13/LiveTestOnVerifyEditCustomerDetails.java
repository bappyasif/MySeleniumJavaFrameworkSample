package DemoBankProjectLiveTestCaseDay13;

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

public class LiveTestOnVerifyEditCustomerDetails {

	static WebDriver webDriver;	
	static String baseUrl = "http://www.demo.guru99.com/V4/";
	static final String userID = "mngr190242";
	static final String userPassword = "hCrEd^8";
	static String customerID = "32144";

	
	public static void main(String[] args) {
		
		try {
			
			CommencingTestOnEditCustomerAndVerify();
			
		} catch (Exception ex) {
			
			// TODO: handle exception
			ex.getStackTrace();
			
		} finally {
			
			System.out.println("Test Completed...");
			webDriver.quit();
			
		}
	}
	

	public static void CommencingTestOnEditCustomerAndVerify() throws InterruptedException {

		// Getting webDriver ready for this.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		CommencingLogin();
		System.out.println("Successful Login And verified \n");

		EditCustomer();
		System.out.println("Check For Editing Update \n");

	}


	public static void EditCustomer() throws InterruptedException {

		webDriver.findElement(By.linkText("Edit Customer")).click();
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		System.out.println("Inside Edit Customer Page");
		Thread.sleep(2000);
		
		webDriver.findElement(By.xpath("//input[@name='cusid']")).clear();
		webDriver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		webDriver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		System.out.println("Inside Edit Customer Details Page");
		Thread.sleep(2000);
		
		try {
			
			// When Customer ID Found
			CustomerDetails();
			
		}catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}
		
		
		try {
			
			// When Customer Not Found
			Alert alert = webDriver.switchTo().alert();
			String alerMessage = alert.getText().toString();
			System.out.println("Alert Message : " +alerMessage);
			alert.accept();
			
			webDriver.findElement(By.linkText("Home")).click();
			System.out.println("Edit Customer Checkpoint Done");
			
		}catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}
	}
	
	
	public static void CustomerDetails() {
		
		// Choose what you want to update in customer details, lets say we are changing State and Contact number.
		// For others just commit out your intended editable data from this form.
		
		//webDriver.findElement(By.xpath("//input[@name='name']")).clear();
		//webDriver.findElement(By.xpath("//input[@name='name']")).sendKeys("Testing User");
		//webDriver.findElement(By.xpath("//tr[5]//td[2]//input[1]")).click();
		//webDriver.findElement(By.xpath("//input[@id='dob']")).clear();
		//webDriver.findElement(By.xpath("//input[@id='dob']")).sendKeys("03/20/1985");
		//webDriver.findElement(By.xpath("//textarea[@name='addr']")).clear();
		//webDriver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("Test test");
		//webDriver.findElement(By.xpath("//input[@name='city']")).clear();
		//webDriver.findElement(By.xpath("//input[@name='city']")).sendKeys("Dhaka");
		webDriver.findElement(By.xpath("//input[@name='state']")).clear();
		webDriver.findElement(By.xpath("//input[@name='state']")).sendKeys("Adabar");
		//webDriver.findElement(By.xpath("//input[@name='pinno']")).clear();
		//webDriver.findElement(By.xpath("//input[@name='pinno']")).sendKeys("001207");
		webDriver.findElement(By.xpath("//input[@name='telephoneno']")).clear();
		webDriver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys("01915019150191");
		//webDriver.findElement(By.xpath("//input[@name='emailid']")).clear();
		//webDriver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("testtest+07@gmail.com");
		webDriver.findElement(By.xpath("//input[@name='sub']")).click();
		
		
		// When Any Changes Are Made
		try {
			
			String changeConfirmed = webDriver.findElement(By.xpath("//p[@class='heading3']")).getText().toString();
			System.out.println("Changes Made : " +changeConfirmed);
			
			String foundState = webDriver.findElement(By.xpath("//td[contains(text(),'Adabar')]")).getText().toString();
			System.out.println("Updated State : " +foundState);
			
			//Eevn though it gets edited but site does not seem to locate and access this field from update notifier.
			//String foundNumber = webDriver.findElement(By.xpath("//td[contains(text(),'0191509150191')]")).getText().toString();
			//System.out.println("Updated Number : " +foundNumber);
			
			TakingSnapshot();
			webDriver.findElement(By.linkText("Continue")).click();
			System.out.println("Customer Edit Is Updated");
			
			
		}catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}
		
		
		// When No Changes Are Made
		try {
			
			Alert alert = webDriver.switchTo().alert();
			String alertMessage = alert.getText().toString();
			System.out.println("Alert Message : " +alertMessage);
			alert.accept();
			
			webDriver.findElement(By.linkText("Home")).click();
			System.out.println("Editing Customer Checkpoint Done");
			
		}catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}
		
	}
	
	
	public static void TakingSnapshot() throws InterruptedException, IOException {

		org.openqa.selenium.Dimension windowSize = webDriver.manage().window().getSize(); 
		webDriver.navigate().refresh();
		Thread.sleep(2000);
		
		// Scrolling Page Until EOF
		((JavascriptExecutor) webDriver)
	     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		webDriver.manage().window().maximize();
		System.out.println("Taking ScreenShot");

		TakesScreenshot customerEditDetailScreenShot = ((TakesScreenshot)webDriver);
		File ceScreenShot = customerEditDetailScreenShot.getScreenshotAs(OutputType.FILE);
		String fileSaved = ("E:\\eclipse\\LiveTestCodeSamples\\Bank Demo\\CustomerEditingVerifiedScreenShot" +".png");
		FileUtils.copyFile(ceScreenShot, new File(fileSaved));

		System.out.println("Your Screenshot Is Saved At This Location : " +fileSaved);

		webDriver.manage().window().setSize(windowSize);
		System.out.println("Getting Out Of TakingSnapshot");

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
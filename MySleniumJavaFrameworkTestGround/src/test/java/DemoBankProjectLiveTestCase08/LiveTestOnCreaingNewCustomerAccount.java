package DemoBankProjectLiveTestCase08;

import static org.testng.Assert.assertEquals;

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

public class LiveTestOnCreaingNewCustomerAccount {

	static WebDriver webDriver;
	static String baseUrl = "http://www.demo.guru99.com/V4/";
	static final String userID = "mngr190242"; 
	static String userPassword = "hCrEd^8";


	public static void main(String[] args) {

		try {
			
			CommencingTestOnNewCustomerAccountCreation();
			
		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		} finally {
			
			System.out.println("Test Completed...");
			webDriver.quit();
		}
		

	}


	public static void CommencingTestOnNewCustomerAccountCreation() throws InterruptedException {

		// Getting webDriver ready for this.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		CommencingLogin();
		CreatingNewUserAccount();

	}

	public static void CreatingNewUserAccount() throws InterruptedException {

		webDriver.findElement(By.linkText("New Customer")).click();
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		System.out.println("New Customer Account Creation Page");
		Thread.sleep(2000);

		try {

			webDriver.findElement(By.xpath("//input[@name='name']")).clear();
			webDriver.findElement(By.xpath("//input[@name='name']")).sendKeys("Test User");
			webDriver.findElement(By.xpath("//tr[5]//td[2]//input[1]")).click();
			webDriver.findElement(By.xpath("//input[@id='dob']")).clear();
			webDriver.findElement(By.xpath("//input[@id='dob']")).sendKeys("03/20/1985");
			webDriver.findElement(By.xpath("//textarea[@name='addr']")).clear();
			webDriver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("Test test");
			webDriver.findElement(By.xpath("//input[@name='city']")).clear();
			webDriver.findElement(By.xpath("//input[@name='city']")).sendKeys("Dhaka");
			webDriver.findElement(By.xpath("//input[@name='state']")).clear();
			webDriver.findElement(By.xpath("//input[@name='state']")).sendKeys("Dhaka");
			webDriver.findElement(By.xpath("//input[@name='pinno']")).clear();
			webDriver.findElement(By.xpath("//input[@name='pinno']")).sendKeys("001207");
			webDriver.findElement(By.xpath("//input[@name='telephoneno']")).clear();
			webDriver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys("8809880880880");
			webDriver.findElement(By.xpath("//input[@name='emailid']")).clear();
			webDriver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("testtest+04@gmail.com");
			webDriver.findElement(By.xpath("//input[@name='password']")).clear();
			webDriver.findElement(By.xpath("//input[@name='password']")).sendKeys("testtest");
			webDriver.findElement(By.xpath("//input[@name='sub']")).click();

			// switching to new window. Even though it doesn't need this window switching but 
			// still expert says its better this way when you're landing on a new URL.
			for (String handle : webDriver.getWindowHandles()) {
				webDriver.switchTo().window(handle);
			}
			System.out.println("New Customer Account Created");

			String expectedMesssage = "Customer Registered Successfully!!!";
			String accountConfirmation = webDriver.findElement(By.xpath("//p[@class='heading3']")).getText().toString();
			System.out.println("Message Found : " +accountConfirmation);
			assertEquals(accountConfirmation, expectedMesssage);
			
			Thread.sleep(2000);

			TakingSnapshot();

			System.out.println("Verification Completed...");
			
			webDriver.findElement(By.linkText("Continue")).click();
			// switching to new window. Even though it doesn't need this window switching but 
			// still expert says its better this way when you're landing on a new URL.
			for (String handle : webDriver.getWindowHandles()) {
				webDriver.switchTo().window(handle);
			}
			System.out.println("New Customer Account Creation Completed.");

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

		System.out.println("Inside User Home Page");
		Thread.sleep(2000);

		try {

			System.out.println("Vefication Of User Home Page");

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

	public static void TakingSnapshot() throws InterruptedException, IOException {

		org.openqa.selenium.Dimension windowSize = webDriver.manage().window().getSize(); 
		webDriver.navigate().refresh();
		Thread.sleep(2000);
		
		// Scrolling Page Until EOF
		((JavascriptExecutor) webDriver)
	     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		webDriver.manage().window().maximize();
		System.out.println("Taking ScreenShot");

		TakesScreenshot newCustomerAccountScreenShot = ((TakesScreenshot)webDriver);
		File ncacScreenShot = newCustomerAccountScreenShot.getScreenshotAs(OutputType.FILE);
		String fileSaved = ("E:\\eclipse\\LiveTestCodeSamples\\Bank Demo\\NewCustomerAccountCreationScreenShot" +".png");
		FileUtils.copyFile(ncacScreenShot, new File(fileSaved));

		System.out.println("Your Screenshot Is Saved At This Location : " +fileSaved);

		webDriver.manage().window().setSize(windowSize);
		System.out.println("Getting Out Of TakingSnapshot");

	}

}

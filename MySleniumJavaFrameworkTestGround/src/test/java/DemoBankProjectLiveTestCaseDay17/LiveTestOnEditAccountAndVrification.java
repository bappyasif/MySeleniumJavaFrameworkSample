package DemoBankProjectLiveTestCaseDay17;

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
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestOnEditAccountAndVrification {

	static WebDriver webDriver;	
	static String baseUrl = "http://www.demo.guru99.com/V4/";

	static final String userID = "mngr190242";
	static final String userPassword = "hCrEd^8";
	
	static String accountID = "59372";
	static String accountType = "Current";
	
	
	public static void main(String[] args) {
		
		try {
			
			CommencingTestOnEditingAccountAndVericiation();
			
		}catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}finally {
			System.out.println("Test Completed...");
			webDriver.quit();
		}
	}
	

	public static void CommencingTestOnEditingAccountAndVericiation() throws InterruptedException {

		// Getting webDriver ready for this.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		CommencingLogin();
		System.out.println("Successful Login And verified \n");

		EditAccount();
		System.out.println("Edit Account Checkpoints Met \n");

	}


	public static void EditAccount() throws InterruptedException {

		webDriver.findElement(By.linkText("Edit Account")).click();

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for(String windowHandler : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(windowHandler);
		}

		System.out.println("Inside Edit Account Page");
		Thread.sleep(2000);

		webDriver.findElement(By.xpath("//input[@name='accountno']")).clear();
		webDriver.findElement(By.xpath("//input[@name='accountno']")).sendKeys(accountID);
		webDriver.findElement(By.xpath("//input[@name='AccSubmit']")).click();


		// When Successful
		try {
			
			String customerID = webDriver.findElement(By.xpath("//input[@name='txtcid']")).getText().toString();
			
			new Select(webDriver.findElement(By.xpath("//select[@name='a_type']"))).selectByVisibleText(accountType);
			String amountShowing = webDriver.findElement(By.xpath("//input[@name='txtinitdep']")).getText().toString();
			webDriver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
			
			String updatedtype = webDriver.findElement(By.xpath("//tr[8]//td[2]")).getText().toString();
			assertEquals(accountType, updatedtype);
			System.out.println("Account Type Is Updated : " +updatedtype);
			
			//String customerFound = webDriver.findElement(By.xpath("//input[@name='txtcid']")).getText().toString();
			//assertEquals(customerID, customerFound);
			//System.out.println("Customer ID Is Verified" +customerID);
			
			String accountFound = webDriver.findElement(By.xpath("//td[contains(text(),'59372')]")).getText().toString();
			assertEquals(accountID, accountFound);
			System.out.println("Account ID Is Verified : " +accountFound);
			
			//String amountShown = webDriver.findElement(By.xpath("//td[contains(text(),'31792641')]")).getText().toString();
			//assertEquals(amountShowing, amountShown);
			//System.out.println("Total Amount Is Checked" +amountShowing);
			
			TakingSnapshot(); // When Changes are made.
			
			webDriver.findElement(By.linkText("Continue")).click();
			System.out.println("Verification Completed");
			
			HandlingAlert(); // When no changes are made.

		}catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}


		// When Unsuccessful or No changes Are Made
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

		TakesScreenshot edittedAccountVerificationScreenShot = ((TakesScreenshot)webDriver);
		File eavScreenShot = edittedAccountVerificationScreenShot.getScreenshotAs(OutputType.FILE);
		String fileSaved = ("E:\\eclipse\\LiveTestCodeSamples\\Bank Demo\\EdittedAccountVerificationScreenShot" +".png");
		FileUtils.copyFile(eavScreenShot, new File(fileSaved));

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

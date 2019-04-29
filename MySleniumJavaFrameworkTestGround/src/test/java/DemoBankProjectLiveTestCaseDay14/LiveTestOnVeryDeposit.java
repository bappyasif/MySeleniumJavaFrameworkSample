package DemoBankProjectLiveTestCaseDay14;

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

public class LiveTestOnVeryDeposit {

	static WebDriver webDriver;	
	static String baseUrl = "http://www.demo.guru99.com/V4/";
	static final String userID = "mngr190242";
	static final String userPassword = "hCrEd^8";
	static String accountID = "59372";
	static String depositAmount = "7000";
	static String depositDescription = "Deposit Amount";
	
	
	public static void main(String[] args) {
		
		try {
			
			CommeencingTestOnDepositVerification();
			
		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		} finally {
			
			System.out.println("Test Completed...");
			webDriver.quit();

		}
	}
	

	public static void CommeencingTestOnDepositVerification() throws InterruptedException, IOException {

		// Getting webDriver ready for this.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		CommencingLogin();
		System.out.println("Successful Login And verified \n");
		
		DepositAmount();
		System.out.println("Deposit Amount Checkpoint Done \n");

	}
	
	
	public static void DepositAmount() throws InterruptedException, IOException {
		
		webDriver.findElement(By.linkText("Deposit")).click();
		
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		System.out.println("Inside Deposit Amount Page");
		Thread.sleep(2000);
		
		webDriver.findElement(By.xpath("//input[@name='accountno']")).clear();
		webDriver.findElement(By.xpath("//input[@name='accountno']")).sendKeys(accountID);
		webDriver.findElement(By.xpath("//input[@name='ammount']")).clear();
		webDriver.findElement(By.xpath("//input[@name='ammount']")).sendKeys(depositAmount);
		webDriver.findElement(By.xpath("//input[@name='desc']")).clear();
		webDriver.findElement(By.xpath("//input[@name='desc']")).sendKeys(depositDescription);
		webDriver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		
		// When Deposit Is Successful
		try {
			
			String depositConfirmation = webDriver.findElement(By.xpath("//p[@class='heading3']")).getText().toString();
			System.out.println("Deposit Confirmation : " +depositConfirmation);
			
			String transactionType = webDriver.findElement(By.xpath("//td[contains(text(),'Deposit')]")).getText().toString();
			System.out.println("Transaction Type : " +transactionType);
			
			// Verifying Amount And Description
			String amountShown = webDriver.findElement(By.xpath("//td[contains(text(),'7000')]")).getText().toString();
			assertEquals(depositAmount, amountShown);
			System.out.println("Amount Verified : " +amountShown);
			
			String accountShown =  webDriver.findElement(By.xpath("//td[contains(text(),'59372')]")).getText().toString();
			assertEquals(accountID, accountShown);
			System.out.println("Account Verified : " +accountShown);			
			
			TakingSnapshot();
			
			//webDriver.findElement(By.linkText("Continue")).click(); // As we are hitting refresh this step becomes redundant. 
			HandlingRefresh();
			System.out.println("Amount Deposit Was Successful");
			 
			
		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
			
		}
		
		// When Deposit Not Successful
		try {
			
			HandlingAlert();
			
		}catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}
	}
	
	
	public static void HandlingRefresh() {
		
		webDriver.navigate().refresh();
		//HandlingAlert();
		System.out.println("Successful Navigtaion to Deposit Page");
		webDriver.findElement(By.linkText("Home")).click();
		System.out.println("Amount Depositd");
		
	}
	
	
	public static void HandlingAlert() {
		
		Alert alert = webDriver.switchTo().alert();
		String alertMessage = alert.getText().toString();
		System.out.println("Alert Message : " +alertMessage);
		alert.accept();
		
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

		TakesScreenshot depositAmountScreenShot = ((TakesScreenshot)webDriver);
		File daScreenShot = depositAmountScreenShot.getScreenshotAs(OutputType.FILE);
		String fileSaved = ("E:\\eclipse\\LiveTestCodeSamples\\Bank Demo\\DepositAmountVerifiedScreenShot" +".png");
		FileUtils.copyFile(daScreenShot, new File(fileSaved));

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

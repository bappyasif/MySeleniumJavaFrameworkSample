package DemoBankProjectLiveTestCaseDay16;

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

public class LiveTestOnCutomizedStatementAndVerification {

	static WebDriver webDriver;	
	static String baseUrl = "http://www.demo.guru99.com/V4/";
	static final String userID = "mngr190242";
	static final String userPassword = "hCrEd^8";
	static String accountID = "59372";
	
	static String fromDate = "04/01/2019";
	static String toDate = "05/25/2019";
	
	static String minimumTransaction = "2000";
	static String transactionNumbers = "30";
	
	
	public static void main(String[] args) {
		
		try {
			
			CommencingTestOnCustomizedStatementAndVerification();
			
		}catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		} finally {
			
			System.out.println("Test Completed...");
			webDriver.quit();
			
		}
	}


	public static void CommencingTestOnCustomizedStatementAndVerification() throws InterruptedException {

		// Getting webDriver ready for this.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		CommencingLogin();
		System.out.println("Successful Login And verified \n");

		CustommizedStatement();

	}


	public static void CustommizedStatement() throws InterruptedException {
		
		webDriver.findElement(By.linkText("Customised Statement")).click();
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for(String windowHandler : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(windowHandler);
		}

		System.out.println("Inside Customized Statement Page");
		Thread.sleep(2000);
		
		webDriver.findElement(By.xpath("//input[@name='accountno']")).clear();
		webDriver.findElement(By.xpath("//input[@name='accountno']")).sendKeys(accountID);
		webDriver.findElement(By.xpath("//input[@name='fdate']")).sendKeys(fromDate);
		webDriver.findElement(By.xpath("//input[@name='tdate']")).sendKeys(toDate);
		webDriver.findElement(By.xpath("//input[@name='amountlowerlimit']")).clear();
		webDriver.findElement(By.xpath("//input[@name='amountlowerlimit']")).sendKeys(minimumTransaction);
		webDriver.findElement(By.xpath("//input[@name='numtransaction']")).clear();
		webDriver.findElement(By.xpath("//input[@name='numtransaction']")).sendKeys(transactionNumbers);
		webDriver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		
		
		// When Successful
		try {
			
			String statementDetails = webDriver.findElement(By.xpath("//p[@class='heading3']")).getText().toString();
			System.out.println("Message Shown : " +statementDetails);
			
			TakingSnapshot();
			
			webDriver.findElement(By.linkText("Continue")).click();
			System.out.println("Customized Statement Is Verified");
			
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

		TakesScreenshot customizedStatementVerificationScreenShot = ((TakesScreenshot)webDriver);
		File csScreenShot = customizedStatementVerificationScreenShot.getScreenshotAs(OutputType.FILE);
		String fileSaved = ("E:\\eclipse\\LiveTestCodeSamples\\Bank Demo\\CustomizedStatementVerificationScreenShot" +".png");
		FileUtils.copyFile(csScreenShot, new File(fileSaved));

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
